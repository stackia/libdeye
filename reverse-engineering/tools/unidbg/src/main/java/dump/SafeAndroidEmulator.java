package dump;

import com.github.unidbg.arm.backend.Backend;
import com.github.unidbg.arm.backend.BackendFactory;
import com.github.unidbg.file.linux.AndroidFileIO;
import com.github.unidbg.linux.ARM64SyscallHandler;
import com.github.unidbg.linux.android.AndroidARM64Emulator;
import com.github.unidbg.memory.SvcMemory;
import com.github.unidbg.unix.UnixSyscallHandler;
import unicorn.Arm64Const;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ARM64 emulator whose raw SVC trampolines (360 Jiagu) do not crash.
 * openat may pass a NULL/relative pathname; mmap/lseek may target a directory fd.
 */
public class SafeAndroidEmulator extends AndroidARM64Emulator {
    public static final String APK_PATH = "/tmp/deye-apk/com.deye_4.2.1.apk";
    public static final String DEX_PATH = "/tmp/deye-apk/unidbg-fs/apk-classes.dex";
    public static final long APK_MAP = 0x70000000L;
    public static final long PATH_SLOT = 0x60000200L;

    private final Map<Integer, String> fdPaths = new ConcurrentHashMap<Integer, String>();
    private byte[] apkBytes;
    private boolean apkMapped;

    public SafeAndroidEmulator(String processName, File rootDir,
                               Collection<BackendFactory> backendFactories) {
        super(processName, rootDir, backendFactories);
    }

    public long apkSize() {
        File apk = new File(APK_PATH);
        return apk.length();
    }

    public synchronized long ensureApkMapped(Backend backend) {
        if (apkMapped) {
            return APK_MAP;
        }
        try {
            if (apkBytes == null) {
                apkBytes = Files.readAllBytes(new File(APK_PATH).toPath());
            }
            long aligned = (apkBytes.length + 0xfffL) & ~0xfffL;
            try {
                backend.mem_map(APK_MAP, aligned, 7);
            } catch (Exception ignored) {
            }
            backend.mem_write(APK_MAP, apkBytes);
            apkMapped = true;
            System.out.println("mapped APK at " + Long.toHexString(APK_MAP) + " size=" + apkBytes.length);
        } catch (Exception e) {
            System.out.println("map APK failed " + e);
        }
        return APK_MAP;
    }

    @Override
    protected UnixSyscallHandler<AndroidFileIO> createSyscallHandler(SvcMemory svcMemory) {
        return new ARM64SyscallHandler(svcMemory) {
            @Override
            public void hook(Backend backend, int intno, int swi, Object user) {
                try {
                    long nr = backend.reg_read(Arm64Const.UC_ARM64_REG_X8).longValue();
                    if (nr == 0x38) {
                        handleOpenat(backend);
                    } else if (nr == 0x3e) {
                        if (handleLseek(backend)) {
                            return;
                        }
                    } else if (nr == 0xde) {
                        if (handleMmap(backend)) {
                            return;
                        }
                    }
                    String pendingPath = lastOpenPath;
                    super.hook(backend, intno, swi, user);
                    if (nr == 0x38) {
                        int fd = backend.reg_read(Arm64Const.UC_ARM64_REG_X0).intValue();
                        if (fd >= 0 && pendingPath != null) {
                            fdPaths.put(fd, pendingPath);
                            System.out.println("openat => fd=" + fd + " path=" + pendingPath);
                        } else {
                            System.out.println("openat => " + fd);
                        }
                    } else if (nr == 0xde) {
                        System.out.println("mmap => " + Long.toHexString(
                                backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue()));
                    } else if (nr == 0x3e) {
                        System.out.println("lseek => " + backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue());
                    }
                } catch (Exception e) {
                    long nr = 0;
                    try {
                        nr = backend.reg_read(Arm64Const.UC_ARM64_REG_X8).longValue();
                    } catch (Exception ignored) {
                    }
                    System.out.println("syscall 0x" + Long.toHexString(nr) + " caught " + e);
                    if (nr == 0xde) {
                        long addr = ensureApkMapped(backend);
                        backend.reg_write(Arm64Const.UC_ARM64_REG_X0, addr);
                        return;
                    }
                    if (nr == 0x3e) {
                        backend.reg_write(Arm64Const.UC_ARM64_REG_X0, apkSize());
                        return;
                    }
                    try {
                        backend.reg_write(Arm64Const.UC_ARM64_REG_X0, -1);
                    } catch (Exception ignored) {
                    }
                }
            }

            private String lastOpenPath;

            private void handleOpenat(Backend backend) {
                lastOpenPath = null;
                long x0 = backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue();
                long x1 = backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue();
                long x2 = backend.reg_read(Arm64Const.UC_ARM64_REG_X2).longValue();
                String path = peek(backend, x1);
                if (x1 == 0 || path == null || !path.startsWith("/")) {
                    long x21 = backend.reg_read(Arm64Const.UC_ARM64_REG_X21).longValue();
                    long x22 = backend.reg_read(Arm64Const.UC_ARM64_REG_X22).longValue();
                    String s21 = peek(backend, x21);
                    String s22 = peek(backend, x22);
                    String name = path != null && !path.isEmpty() ? path
                            : (s22 != null ? s22 : s21);
                    String abs = resolveAbs(name, (int) x2);
                    System.out.println(String.format(
                            "openat rewrite fd=%x flags=%x path=%s x21=%s x22=%s -> %s",
                            x0, x2, path, s21, s22, abs));
                    writePath(backend, abs);
                    backend.reg_write(Arm64Const.UC_ARM64_REG_X0, -100);
                    backend.reg_write(Arm64Const.UC_ARM64_REG_X1, PATH_SLOT);
                    lastOpenPath = abs;
                } else {
                    lastOpenPath = path;
                    System.out.println(String.format("openat dirfd=%x flags=%x path=%s", x0, x2, path));
                }
            }

            private boolean handleLseek(Backend backend) {
                int fd = backend.reg_read(Arm64Const.UC_ARM64_REG_X0).intValue();
                long off = backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue();
                int whence = backend.reg_read(Arm64Const.UC_ARM64_REG_X2).intValue();
                String path = fdPaths.get(fd);
                System.out.println(String.format("lseek fd=%d off=%x whence=%d path=%s", fd, off, whence, path));
                File host = path == null ? null : new File(path);
                boolean forceApk = host == null || host.isDirectory() || (host.exists() && host.length() < 22);
                if (forceApk) {
                    long size = apkSize();
                    long result = whence == 2 ? size + off : (whence == 1 ? off : off);
                    if (whence == 0) {
                        result = off;
                    }
                    System.out.println("lseek force APK size=" + size + " => " + result);
                    backend.reg_write(Arm64Const.UC_ARM64_REG_X0, result);
                    return true;
                }
                return false;
            }

            private boolean handleMmap(Backend backend) {
                long start = backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue();
                long len = backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue();
                long prot = backend.reg_read(Arm64Const.UC_ARM64_REG_X2).longValue();
                long flags = backend.reg_read(Arm64Const.UC_ARM64_REG_X3).longValue();
                int fd = backend.reg_read(Arm64Const.UC_ARM64_REG_X4).intValue();
                long off = backend.reg_read(Arm64Const.UC_ARM64_REG_X5).longValue();
                System.out.println(String.format(
                        "mmap start=%x len=%x prot=%x flags=%x fd=%d off=%x",
                        start, len, prot, flags, fd, off));
                boolean anon = (flags & 0x20) != 0 || fd < 0;
                if (anon) {
                    if (len <= 0 || len > 64L * 1024 * 1024) {
                        long lr = backend.reg_read(Arm64Const.UC_ARM64_REG_LR).longValue();
                        int clamped = 32 * 1024 * 1024;
                        System.out.println(String.format("mmap clamp len=%x lr=%x -> %x", len, lr, clamped));
                        com.github.unidbg.pointer.UnidbgPointer mapped =
                                SafeAndroidEmulator.this.getMemory().mmap(clamped, 7);
                        backend.reg_write(Arm64Const.UC_ARM64_REG_X0, mapped.peer);
                        return true;
                    }
                    return false;
                }
                String path = fdPaths.get(fd);
                File host = path == null ? null : new File(path);
                boolean needApk = host == null || host.isDirectory()
                        || (host.getName() != null && host.getName().endsWith(".apk"))
                        || len < 0x10000L;
                if (needApk) {
                    long addr = ensureApkMapped(backend);
                    System.out.println("mmap force APK -> " + Long.toHexString(addr));
                    backend.reg_write(Arm64Const.UC_ARM64_REG_X0, addr);
                    return true;
                }
                return false;
            }

            private void writePath(Backend backend, String abs) {
                byte[] pathBytes = (abs + "\0").getBytes(StandardCharsets.US_ASCII);
                backend.mem_write(PATH_SLOT, pathBytes);
            }

            private String resolveAbs(String name, int flags) {
                if (name != null && name.startsWith("/")) {
                    return name;
                }
                if (name != null && (name.contains("classes.dex") || name.contains("internal.dex"))) {
                    return DEX_PATH;
                }
                if (name != null && name.contains("jiagu")) {
                    File mapped = new File("/tmp/deye-apk/native/libjiagu_mapped.so");
                    if (mapped.exists()) {
                        return mapped.getAbsolutePath();
                    }
                }
                // ZIP finder and ashmem fallbacks must see a real APK, not an empty ashmem.
                if (flags == 0 || name == null || name.isEmpty() || name.contains("ashmem")) {
                    return APK_PATH;
                }
                File underFiles = new File("/tmp/deye-apk/unidbg-fs/data/data/com.deye/files", name);
                if (underFiles.getParentFile() != null) {
                    underFiles.getParentFile().mkdirs();
                }
                return underFiles.getAbsolutePath();
            }

            private String peek(Backend backend, long addr) {
                if (addr < 0x1000) {
                    return null;
                }
                try {
                    byte[] b = backend.mem_read(addr, 128);
                    int n = 0;
                    while (n < b.length && b[n] != 0) {
                        if (b[n] < 32 || b[n] > 126) {
                            return null;
                        }
                        n++;
                    }
                    if (n == 0) {
                        return null;
                    }
                    return new String(b, 0, n, StandardCharsets.US_ASCII);
                } catch (Exception e) {
                    return null;
                }
            }
        };
    }
}
