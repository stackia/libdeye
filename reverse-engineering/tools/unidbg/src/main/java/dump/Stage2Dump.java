package dump;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.Symbol;
import com.github.unidbg.arm.Arm64Svc;
import com.github.unidbg.arm.backend.Backend;
import com.github.unidbg.arm.backend.CodeHook;
import com.github.unidbg.pointer.UnidbgPointer;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.DalvikModule;
import com.github.unidbg.linux.android.dvm.AbstractJni;
import com.github.unidbg.linux.android.dvm.BaseVM;
import com.github.unidbg.linux.android.dvm.DvmClass;
import com.github.unidbg.linux.android.dvm.DvmObject;
import com.github.unidbg.linux.android.dvm.StringObject;
import com.github.unidbg.linux.android.dvm.VaList;
import com.github.unidbg.linux.android.dvm.VarArg;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.linux.android.dvm.api.ApplicationInfo;
import com.github.unidbg.linux.android.dvm.api.AssetManager;
import com.github.unidbg.linux.android.dvm.api.ClassLoader;
import com.github.unidbg.linux.android.dvm.api.PackageInfo;
import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.github.unidbg.file.FileResult;
import com.github.unidbg.file.IOResolver;
import com.github.unidbg.file.linux.AndroidFileIO;
import com.github.unidbg.linux.file.DirectoryFileIO;
import com.github.unidbg.linux.file.SimpleFileIO;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.memory.MemoryBlock;
import com.github.unidbg.memory.MemoryMap;
import com.github.unidbg.virtualmodule.android.AndroidModule;
import unicorn.Arm64Const;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Stage2Dump extends AbstractJni {
    private static final long BASE = 0x50000000L;
    private static final long STUB_PAGE = 0x60000000L;
    private static final File APK = new File("/tmp/deye-apk/com.deye_4.2.1.apk");
    private static final File FLAT = new File("/tmp/deye-apk/native/stage2_flat.bin");
    private static final File UNRES = new File("/tmp/deye-apk/native/stage2_unresolved.txt");
    private static final File OUT = new File("/tmp/deye-apk/unidbg-dump");
    private static final String PKG = "com.deye";
    private static final String DATA = "/tmp/deye-apk/unidbg-fs/data/data/com.deye";
    private static final String FILES = DATA + "/files";

    private static final byte[] PKCS7 = readPkcs7();

    private static byte[] readPkcs7() {
        try {
            java.util.zip.ZipFile zf = new java.util.zip.ZipFile(APK);
            try {
                return zf.getInputStream(zf.getEntry("META-INF/DEYE.RSA")).readAllBytes();
            } finally {
                zf.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final AndroidEmulator emulator;
    private final VM vm;
    private final Set<String> dumped = new HashSet<>();
    private final AtomicInteger zstdHits = new AtomicInteger();
    private final AtomicInteger blobHits = new AtomicInteger();
    private DvmObject<?> systemContext;
    private DvmObject<?> application;
    private long firstBase;
    private long hashDest;
    private long hashLen;
    private boolean nativeHashWired;

    public Stage2Dump() throws Exception {
        File rootfs = new File("/tmp/deye-apk/unidbg-rootfs");
        rootfs.mkdirs();
        emulator = new SafeAndroidEmulator(PKG, rootfs, java.util.Collections.emptyList());
        Memory memory = emulator.getMemory();
        memory.setLibraryResolver(new AndroidResolver(23));
        vm = emulator.createDalvikVM(APK);
        vm.setJni(this);
        vm.setVerbose(true);
        new AndroidModule(emulator, vm).register(memory);
        emulator.getSyscallHandler().addIOResolver((IOResolver<AndroidFileIO>) (emu, pathname, oflags) -> {
            System.out.println("open " + pathname);
            if (pathname == null) {
                return null;
            }
            if (pathname.startsWith("/proc") || pathname.startsWith("/sys")) {
                return null;
            }
            if (pathname.startsWith("/dev/ashmem") || pathname.equals("/dev/null")) {
                File ash = new File("/tmp/deye-apk/unidbg-fs/ashmem.bin");
                try {
                    if (!ash.exists()) {
                        ash.getParentFile().mkdirs();
                        ash.createNewFile();
                    }
                } catch (Exception ignored) {
                }
                return FileResult.success(new SimpleFileIO(oflags, ash, pathname));
            }
            if (pathname.startsWith("/dev")) {
                return null;
            }
            if (pathname.endsWith(".apk") || pathname.contains("base.apk")) {
                return FileResult.success(new SimpleFileIO(oflags, APK, pathname));
            }
            if (pathname.endsWith("classes.dex") || pathname.endsWith("internal.dex")
                    || pathname.contains("dalvik-classes.dex")) {
                File dex = new File("/tmp/deye-apk/unidbg-fs/apk-classes.dex");
                if (dex.exists()) {
                    return FileResult.success(new SimpleFileIO(oflags, dex, pathname));
                }
            }
            if (pathname.startsWith("/data/data/com.deye") || pathname.contains("/tmp/deye-apk/unidbg-fs")
                    || pathname.contains("com.deye")) {
                File host = pathname.startsWith("/data/data/com.deye")
                        ? new File(DATA + pathname.substring("/data/data/com.deye".length()))
                        : new File(pathname);
                if (host.getParentFile() != null) {
                    host.getParentFile().mkdirs();
                }
                try {
                    if (!host.exists() && (oflags & 0x40) != 0) { // O_CREAT
                        host.createNewFile();
                    }
                } catch (Exception ignored) {
                }
                if (host.isDirectory()) {
                    return FileResult.success(new DirectoryFileIO(oflags, pathname, host));
                }
                if (host.exists()) {
                    return FileResult.success(new SimpleFileIO(oflags, host, pathname));
                }
            }
            return null;
        });
        OUT.mkdirs();
        new File("/tmp/deye-apk/unidbg-fs/data/data/com.deye/files/.jiagu").mkdirs();
        extractApkDex();
    }

    private static void extractApkDex() {
        File out = new File("/tmp/deye-apk/unidbg-fs/apk-classes.dex");
        if (out.exists() && out.length() > 1000) {
            return;
        }
        try {
            java.util.zip.ZipFile zf = new java.util.zip.ZipFile(APK);
            try {
                java.util.zip.ZipEntry e = zf.getEntry("classes.dex");
                if (e != null) {
                    out.getParentFile().mkdirs();
                    try (java.io.InputStream in = zf.getInputStream(e);
                         FileOutputStream fos = new FileOutputStream(out)) {
                        in.transferTo(fos);
                    }
                    File copy = new File("/tmp/deye-apk/unidbg-fs/data/data/com.deye/files/classes.dex");
                    copy.getParentFile().mkdirs();
                    Files.copy(out.toPath(), copy.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("extracted APK classes.dex size=" + out.length());
                }
            } finally {
                zf.close();
            }
        } catch (Exception e) {
            System.out.println("extract classes.dex failed " + e);
        }
    }

    private DvmObject<?> contextImpl() {
        if (systemContext == null) {
            systemContext = vm.resolveClass(
                    "android/app/ContextImpl",
                    vm.resolveClass("android/content/ContextWrapper",
                            vm.resolveClass("android/content/Context"))
            ).newObject("ctx");
        }
        return systemContext;
    }

    private DvmObject<?> application() {
        if (application == null) {
            application = vm.resolveClass(
                    "android/app/Application",
                    vm.resolveClass("android/content/ContextWrapper",
                            vm.resolveClass("android/content/Context"))
            ).newObject("app");
        }
        return application;
    }

    private long findSym(String name) {
        for (Module m : emulator.getMemory().getLoadedModules()) {
            try {
                Symbol s = m.findSymbolByName(name, false);
                if (s != null) {
                    return s.getAddress();
                }
            } catch (Exception ignored) {
            }
        }
        return 0;
    }

    private void writeLong(long addr, long value) {
        emulator.getBackend().mem_write(addr,
                ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(value).array());
    }

    private long readLong(long addr) {
        byte[] b = emulator.getBackend().mem_read(addr, 8);
        return ByteBuffer.wrap(b).order(ByteOrder.LITTLE_ENDIAN).getLong();
    }

    private void installStubs() {
        Backend backend = emulator.getBackend();
        backend.mem_map(STUB_PAGE, 0x1000, 7);
        // +0 ret 0
        backend.mem_write(STUB_PAGE, new byte[]{
                0x00, 0x00, (byte) 0x80, (byte) 0xd2, (byte) 0xc0, 0x03, 0x5f, (byte) 0xd6
        });
        // +8 ret -1
        backend.mem_write(STUB_PAGE + 8, new byte[]{
                0x00, 0x00, (byte) 0x80, (byte) 0x92, (byte) 0xc0, 0x03, 0x5f, (byte) 0xd6
        });
        // +16 ret 1
        backend.mem_write(STUB_PAGE + 16, new byte[]{
                0x20, 0x00, (byte) 0x80, (byte) 0xd2, (byte) 0xc0, 0x03, 0x5f, (byte) 0xd6
        });
        byte[] zeros = new byte[64];
        backend.mem_write(STUB_PAGE + 0x100, zeros);
        backend.mem_write(STUB_PAGE + 0x180, ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
                .putLong(0x3737373737373737L).array());
        System.out.println("stub page at " + Long.toHexString(STUB_PAGE));
    }

    private long stubFor(String name) {
        if (name == null) {
            return STUB_PAGE;
        }
        switch (name) {
            case "fork":
            case "vfork":
            case "execve":
            case "execl":
            case "kill":
            case "raise":
            case "ptrace":
            case "prctl":
                return STUB_PAGE + 8; // -1
            case "__sF":
                return STUB_PAGE + 0x100;
            case "environ":
                return STUB_PAGE + 0x140;
            case "__stack_chk_guard":
                return STUB_PAGE + 0x180;
            default:
                return STUB_PAGE;
        }
    }

    private void mapImage() throws Exception {
        byte[] flat = Files.readAllBytes(FLAT.toPath());
        emulator.getBackend().mem_map(BASE, 0x1c0000, 7);
        emulator.getBackend().mem_write(BASE, flat);
        System.out.println("mapped stage2 at " + Long.toHexString(BASE) + " size=" + flat.length);
        installStubs();

        int resolved = 0;
        int stubbed = 0;
        if (UNRES.exists()) {
            for (String line : Files.readAllLines(UNRES.toPath())) {
                String[] p = line.trim().split(" ", 3);
                if (p.length < 2) {
                    continue;
                }
                long off = Long.decode(p[0]);
                String name = p[1];
                long addr = findSym(name);
                if (addr == 0) {
                    addr = stubFor(name);
                    stubbed++;
                    System.out.println("stub " + name + " -> " + Long.toHexString(addr));
                } else {
                    resolved++;
                }
                writeLong(BASE + off, addr);
            }
        }
        System.out.println("got resolved=" + resolved + " stubbed=" + stubbed);

        // Only rewrite leftover PLT stubs. Zero slots are real NULLs or
        // runtime function pointers filled by DT_INIT_ARRAY.
        int leftover = 0;
        for (long off = 0x19f400L; off < 0x1aa400L; off += 8) {
            long v = readLong(BASE + off);
            if (v == 0x2f000L || v == BASE + 0x2f000L) {
                writeLong(BASE + off, STUB_PAGE);
                leftover++;
            }
        }
        System.out.println("leftover plt stubs patched=" + leftover);

        // Neutralize DobbyHook so it cannot rewrite emulator code.
        emulator.getBackend().mem_write(BASE + 0x126eacL, new byte[]{
                0x00, 0x00, (byte) 0x80, (byte) 0xd2, (byte) 0xc0, 0x03, 0x5f, (byte) 0xd6
        });

        try {
            emulator.getBackend().mem_map(0, 0x10000, 7);
        } catch (Exception ignored) {
        }
        for (MemoryMap map : emulator.getMemory().getMemoryMap()) {
            try {
                emulator.getBackend().mem_protect(map.base, map.size, 7);
            } catch (Exception ignored) {
            }
        }
    }

    private void hookOpenatGuard() {
        long openat = resolveOr("openat", "__openat");
        if (openat == 0) {
            System.out.println("openat symbol missing");
            return;
        }
        MemoryBlock dummy = emulator.getMemory().malloc(64, false);
        dummy.getPointer().setString(0, "/tmp/deye-apk/unidbg-fs/missing");
        final long dummyPath = dummy.getPointer().peer;
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                long x0 = backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue();
                long x1 = backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue();
                long x2 = backend.reg_read(Arm64Const.UC_ARM64_REG_X2).longValue();
                if (x1 == 0) {
                    System.out.println(String.format("openat null path fd=%x flags=%x -> dummy", x0, x2));
                    backend.reg_write(Arm64Const.UC_ARM64_REG_X1, dummyPath);
                }
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, openat, openat + 4, null);
        System.out.println("openat guard at " + Long.toHexString(openat));
    }

    private void hookInteresting() {
        // ZSTD_decompress(dst, dstCap, src, srcSize)
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                int n = zstdHits.incrementAndGet();
                long dst = backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue();
                long dstCap = backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue();
                long src = backend.reg_read(Arm64Const.UC_ARM64_REG_X2).longValue();
                long srcSize = backend.reg_read(Arm64Const.UC_ARM64_REG_X3).longValue();
                long lr = backend.reg_read(Arm64Const.UC_ARM64_REG_LR).longValue();
                System.out.println(String.format("ZSTD_decompress #%d dst=%x cap=%x src=%x sz=%x lr=%x",
                        n, dst, dstCap, src, srcSize, lr));
                dumpPtr("zstd-src-" + n, src, srcSize);
                final int hit = n;
                final long dest = dst;
                final long cap = dstCap;
                if (lr > 0x1000) {
                    emulator.getBackend().hook_add_new(new CodeHook() {
                        @Override
                        public void hook(Backend b, long a, int s, Object u) {
                            long wrote = b.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue();
                            System.out.println(String.format("ZSTD_decompress #%d returned %x", hit, wrote));
                            dumpPtr("zstd-dst-" + hit, dest, wrote > 0 && wrote < cap ? wrote : cap);
                            scan("after-zstd-" + hit);
                            detach();
                        }
                        @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
                        @Override public void detach() {}
                    }, lr, lr + 4, null);
                }
            }

            @Override
            public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {
            }

            @Override
            public void detach() {
            }
        }, BASE + 0xae57cL, BASE + 0xae580L, null);

        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                long x0 = backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue();
                long x1 = backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue();
                long x2 = backend.reg_read(Arm64Const.UC_ARM64_REG_X2).longValue();
                long x3 = backend.reg_read(Arm64Const.UC_ARM64_REG_X3).longValue();
                System.out.println(String.format("interpreter_wrap x0=%x x1=%x x2=%x x3=%x",
                        x0, x1, x2, x3));
            }

            @Override
            public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {
            }

            @Override
            public void detach() {
            }
        }, BASE + 0xacf28L, BASE + 0xacf2cL, null);
    }

    private void hookZipScan() {
        byte[] apkPath = (APK.getAbsolutePath() + "\0").getBytes(java.nio.charset.StandardCharsets.US_ASCII);
        emulator.getBackend().mem_write(STUB_PAGE + 0x400, apkPath);
        final SafeAndroidEmulator safe = (SafeAndroidEmulator) emulator;
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                long x0 = backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue();
                long x1 = backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue();
                String path = peekStr(backend, x0);
                String name = peekStr(backend, x1);
                System.out.println(String.format("zip-find path=%s @%x name=%s", path, x0, name));
                if (path == null || !path.endsWith(".apk")) {
                    backend.reg_write(Arm64Const.UC_ARM64_REG_X0, STUB_PAGE + 0x400);
                    System.out.println("zip-find forced APK path");
                }
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, BASE + 0xcecf0L, BASE + 0xcecf4L, null);
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                System.out.println(String.format("zip-mmap x0=%x x1=%x x4=%x",
                        backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue(),
                        backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue(),
                        backend.reg_read(Arm64Const.UC_ARM64_REG_X4).longValue()));
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, BASE + 0xced44L, BASE + 0xced48L, null);
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                long x21 = backend.reg_read(Arm64Const.UC_ARM64_REG_X21).longValue();
                long x19 = backend.reg_read(Arm64Const.UC_ARM64_REG_X19).longValue();
                System.out.println(String.format("eocd-scan mapped=%x size=%x", x21, x19));
                if (x21 < 0x10000L || x19 < 0x10000L) {
                    long mapped = safe.ensureApkMapped(backend);
                    backend.reg_write(Arm64Const.UC_ARM64_REG_X21, mapped);
                    backend.reg_write(Arm64Const.UC_ARM64_REG_X19, safe.apkSize());
                    System.out.println("eocd-scan forced APK map");
                }
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, BASE + 0xcedc0L, BASE + 0xcedc4L, null);
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                System.out.println(String.format("eocd-hit x8=%x x21=%x x19=%x",
                        backend.reg_read(Arm64Const.UC_ARM64_REG_X8).longValue(),
                        backend.reg_read(Arm64Const.UC_ARM64_REG_X21).longValue(),
                        backend.reg_read(Arm64Const.UC_ARM64_REG_X19).longValue()));
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, BASE + 0xcee00L, BASE + 0xcee04L, null);
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                System.out.println(String.format("zip-find-ret x0=%x pc=%x",
                        backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue(), address));
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, BASE + 0x4162cL, BASE + 0x41630L, null);
        emulator.getBackend().hook_add_new(new CodeHook() {
            private int n;
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                if (++n % 20000 == 0) {
                    System.out.println(String.format("wrap-progress #%d pc=%x x0=%x lr=%x",
                            n,
                            backend.reg_read(Arm64Const.UC_ARM64_REG_PC).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_LR).longValue()));
                }
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, BASE + 0xacf28L, BASE + 0xacf2cL, null);
    }

    private void hookSyscallGens() {
        byte[] ret = {(byte) 0xc0, 0x03, 0x5f, (byte) 0xd6};
        // Replace JIT openat/lseek/mmap generators with a ret; the hook does the work.
        emulator.getBackend().mem_write(BASE + 0xe8688L, ret);
        emulator.getBackend().mem_write(BASE + 0xe8788L, ret);
        emulator.getBackend().mem_write(BASE + 0xe8804L, ret);
        final int[] nextFd = {200};
        final java.util.Map<Integer, String> genFds = new java.util.HashMap<Integer, String>();
        final SafeAndroidEmulator safe = (SafeAndroidEmulator) emulator;
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                if (address == BASE + 0xe8688L) {
                    String path = peekStr(backend, backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue());
                    if (path == null || !path.startsWith("/")) {
                        path = SafeAndroidEmulator.APK_PATH;
                    }
                    int fd = nextFd[0]++;
                    genFds.put(fd, path);
                    System.out.println("gen-openat " + path + " => " + fd);
                    backend.reg_write(Arm64Const.UC_ARM64_REG_X0, fd);
                } else if (address == BASE + 0xe8788L) {
                    int fd = backend.reg_read(Arm64Const.UC_ARM64_REG_X0).intValue();
                    String path = genFds.get(fd);
                    long len = 0;
                    if (path != null) {
                        File f = new File(path);
                        if (f.isFile()) {
                            len = f.length();
                        }
                    }
                    if (len < 22) {
                        len = safe.apkSize();
                    }
                    System.out.println("gen-lseek fd=" + fd + " path=" + path + " => " + len);
                    backend.reg_write(Arm64Const.UC_ARM64_REG_X0, len);
                } else if (address == BASE + 0xe8804L) {
                    long hint = backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue();
                    long len = backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue();
                    int prot = backend.reg_read(Arm64Const.UC_ARM64_REG_X2).intValue();
                    int flags = backend.reg_read(Arm64Const.UC_ARM64_REG_X3).intValue();
                    int fd = backend.reg_read(Arm64Const.UC_ARM64_REG_X4).intValue();
                    System.out.println(String.format("gen-mmap hint=%x len=%x prot=%x flags=%x fd=%d",
                            hint, len, prot, flags, fd));
                    boolean anon = (flags & 0x20) != 0 || fd < 0;
                    if (anon) {
                        com.github.unidbg.pointer.UnidbgPointer mapped =
                                emulator.getMemory().mmap(len > 0 ? (int) len : 0x1000, prot == 0 ? 7 : prot);
                        backend.reg_write(Arm64Const.UC_ARM64_REG_X0, mapped.peer);
                    } else {
                        long addr = safe.ensureApkMapped(backend);
                        backend.reg_write(Arm64Const.UC_ARM64_REG_X0, addr);
                    }
                }
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, BASE + 0xe8688L, BASE + 0xe8808L, null);
    }

    private void hookProtocol() {
        byte[] nop = {0x1f, 0x20, 0x03, (byte) 0xd5};
        // protect-time window is 30 days; APK was packed 2026-06-04.
        emulator.getBackend().mem_write(BASE + 0x418a4L, nop);
        // If config key embed != "1", 405f0 returns without decrypting DEX.
        // Force the iso/fastLevel path at 0x40860.
        emulator.getBackend().mem_write(BASE + 0x40824L, new byte[] {0x0f, 0x00, 0x00, 0x14});
        // Skip ClassLoader/maps anti-debug that crashes unidbg JNI.
        byte[] ret0 = {0x00, 0x00, (byte) 0x80, 0x52, (byte) 0xc0, 0x03, 0x5f, (byte) 0xd6};
        emulator.getBackend().mem_write(BASE + 0x3e85cL, ret0); // ckf/classloader
        emulator.getBackend().mem_write(BASE + 0xd7770L, ret0);
        emulator.getBackend().mem_write(BASE + 0xe333cL, ret0); // integrity
        emulator.getBackend().mem_write(BASE + 0xe32d8L, ret0); // maps scan
        emulator.getBackend().mem_write(BASE + 0xd7a7cL, ret0);
        emulator.getBackend().mem_write(BASE + 0xd9a18L, ret0);
        emulator.getBackend().mem_write(BASE + 0xdcb54L, ret0);
        emulator.getBackend().mem_write(BASE + 0xeaae4L, ret0);
        // Official hash is first-SO _Z9__arm_a_2PcmS_Rii at 0x8ae0 (interpreter
        // trampoline). Wire that pointer into stage-2 BSS. Fall back to a Java
        // MD5 stub only when the native slot is still NULL.
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                long fn = readLong(BASE + 0x1a1598L);
                System.out.println(String.format("hash-got-probe pc=%x fn=%x native=%s",
                        address, fn, nativeHashWired));
                if (fn != 0) {
                    emulator.getBackend().mem_write(BASE + 0x61008L,
                            new byte[] {0x20, 0x01, 0x3f, (byte) 0xd6}); // blr x9
                    emulator.getBackend().mem_write(BASE + 0x60d4cL,
                            new byte[] {0x00, 0x01, 0x3f, (byte) 0xd6}); // blr x8
                }
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, BASE + 0x61000L, BASE + 0x61000L, null);
        if (!nativeHashWired) {
            emulator.getBackend().mem_write(BASE + 0x61008L, nop);
            emulator.getBackend().mem_write(BASE + 0x60d4cL, nop);
        }
        CodeHook hashHook = new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                if (address != BASE + 0x61008L && address != BASE + 0x60d4cL) {
                    return;
                }
                long src = backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue();
                int len = backend.reg_read(Arm64Const.UC_ARM64_REG_X1).intValue();
                long dst = backend.reg_read(Arm64Const.UC_ARM64_REG_X2).longValue();
                long extra = backend.reg_read(Arm64Const.UC_ARM64_REG_X3).longValue();
                int tag = 0;
                if (extra > 0x1000) {
                    try {
                        tag = ByteBuffer.wrap(backend.mem_read(extra, 4)).order(ByteOrder.LITTLE_ENDIAN).getInt();
                    } catch (Exception ignored) {
                    }
                }
                hashDest = dst;
                hashLen = len;
                if (src < 0x1000 || len <= 0) {
                    System.out.println(String.format("hash-cb skip empty pc=%x src=%x len=%d", address, src, len));
                    return;
                }
                byte[] input = peekBytes(backend, src, Math.max(0, Math.min(len, 4096)));
                System.out.println(String.format("hash-cb pc=%x src=%x len=%d dst=%x tag=%x native=%s data=%s",
                        address, src, len, dst, tag, nativeHashWired, toHex(input)));
                if (nativeHashWired) {
                    return;
                }
                byte[] key = deriveKey(input, tag);
                if (dst > 0x1000 && key != null) {
                    backend.mem_write(dst, key);
                    System.out.println("hash-cb wrote key=" + toHex(key));
                }
                backend.reg_write(Arm64Const.UC_ARM64_REG_X0, 0);
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        };
        emulator.getBackend().hook_add_new(hashHook, BASE + 0x61008L, BASE + 0x61008L, null);
        emulator.getBackend().hook_add_new(hashHook, BASE + 0x60d4cL, BASE + 0x60d4cL, null);
        CodeHook hashRet = new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                if (hashDest > 0x1000) {
                    System.out.println(String.format("hash-ret pc=%x key=%s x0=%x",
                            address, toHex(peekBytes(backend, hashDest, 16)),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue()));
                }
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        };
        emulator.getBackend().hook_add_new(hashRet, BASE + 0x6100cL, BASE + 0x6100cL, null);
        emulator.getBackend().hook_add_new(hashRet, BASE + 0x60d50L, BASE + 0x60d50L, null);
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                System.out.println(String.format("cfg-decrypt pc=%x x0=%x x1=%x x2=%x",
                        address,
                        backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue(),
                        backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue(),
                        backend.reg_read(Arm64Const.UC_ARM64_REG_X2).longValue()));
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, BASE + 0x61654L, BASE + 0x61658L, null);
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                System.out.println(String.format("after-cfg pc=%x x0=%x",
                        address, backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue()));
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, BASE + 0x4169cL, BASE + 0x416a0L, null);
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                System.out.println(String.format("dex-load pc=%x x0=%x w1=%x",
                        address,
                        backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue(),
                        backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue()));
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, BASE + 0x405f0L, BASE + 0x405f4L, null);
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                int n = blobHits.incrementAndGet();
                long arg = backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue();
                long cfg = 0, blob = 0;
                if (arg > 0x1000) {
                    cfg = readLong(arg);
                    blob = readLong(arg + 8);
                }
                System.out.println(String.format("blob-proc #%d arg=%x cfg=%x blob=%x", n, arg, cfg, blob));
                if (blob > 0x1000) {
                    dumpPtr("blob-in-" + n, blob, 32);
                }
                if (cfg > 0x1000) {
                    System.out.println("blob-proc key=" + toHex(peekBytes(backend, cfg + 0xa, 16)));
                }
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, BASE + 0x45e24L, BASE + 0x45e28L, null);
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                long sp = backend.reg_read(Arm64Const.UC_ARM64_REG_SP).longValue();
                long plain = readLong(sp + 0x70);
                int sz = 0;
                try {
                    sz = ByteBuffer.wrap(backend.mem_read(sp + 0x6c, 4))
                            .order(ByteOrder.LITTLE_ENDIAN).getInt();
                } catch (Exception ignored) {
                }
                System.out.println(String.format("rc4-out pc=%x ptr=%x sz=%x", address, plain, sz));
                dumpPtr("rc4-out", plain, Math.max(0, Math.min(sz, 8 * 1024 * 1024)));
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, BASE + 0x460a8L, BASE + 0x460a8L, null);
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                long keyPtr = backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue();
                int keyLen = backend.reg_read(Arm64Const.UC_ARM64_REG_X2).intValue();
                System.out.println(String.format("rc4-ksa key=%s",
                        toHex(peekBytes(backend, keyPtr, Math.max(0, Math.min(keyLen, 64))))));
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, BASE + 0xcfee0L, BASE + 0xcfee4L, null);
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                long src = backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue();
                long srcSz = backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue();
                long dst = backend.reg_read(Arm64Const.UC_ARM64_REG_X2).longValue();
                long dstCap = backend.reg_read(Arm64Const.UC_ARM64_REG_X3).longValue();
                System.out.println(String.format("zstd-ad src=%x sz=%x dst=%x cap=%x",
                        src, srcSz, dst, dstCap));
                dumpPtr("zstd-ad-src", src, srcSz);
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, BASE + 0xad138L, BASE + 0xad13cL, null);
        long[] tracePcs = {
                0x40860L, 0x4096cL, 0x40a74L, 0x40b7cL, 0x41144L,
                0x40ec8L, 0x41020L, 0x40ff8L,
                0x46050L, 0xcfc44L, 0xcfbccL, 0xe32d8L, 0x3e69cL, 0xad108L,
                0x45bbcL, 0xdffa8L, 0xdba24L, 0xe2474L, 0x4ada4L, 0xc3910L
        };
        for (long pc : tracePcs) {
            final long p = pc;
            emulator.getBackend().hook_add_new(new CodeHook() {
                @Override
                public void hook(Backend backend, long address, int size, Object user) {
                    System.out.println(String.format("path pc=%x x0=%x x1=%x x2=%x w0=%x",
                            address,
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X2).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X0).intValue()));
                }
                @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
                @Override public void detach() {}
            }, BASE + p, BASE + p, null);
        }
        hookPthread();
    }

    private long[] snapRegs(Backend backend) {
        int[] regs = {
                Arm64Const.UC_ARM64_REG_X0, Arm64Const.UC_ARM64_REG_X1, Arm64Const.UC_ARM64_REG_X2,
                Arm64Const.UC_ARM64_REG_X3, Arm64Const.UC_ARM64_REG_X4, Arm64Const.UC_ARM64_REG_X5,
                Arm64Const.UC_ARM64_REG_X6, Arm64Const.UC_ARM64_REG_X7, Arm64Const.UC_ARM64_REG_X8,
                Arm64Const.UC_ARM64_REG_X9, Arm64Const.UC_ARM64_REG_X10, Arm64Const.UC_ARM64_REG_X11,
                Arm64Const.UC_ARM64_REG_X12, Arm64Const.UC_ARM64_REG_X13, Arm64Const.UC_ARM64_REG_X14,
                Arm64Const.UC_ARM64_REG_X15, Arm64Const.UC_ARM64_REG_X16, Arm64Const.UC_ARM64_REG_X17,
                Arm64Const.UC_ARM64_REG_X18, Arm64Const.UC_ARM64_REG_X19, Arm64Const.UC_ARM64_REG_X20,
                Arm64Const.UC_ARM64_REG_X21, Arm64Const.UC_ARM64_REG_X22, Arm64Const.UC_ARM64_REG_X23,
                Arm64Const.UC_ARM64_REG_X24, Arm64Const.UC_ARM64_REG_X25, Arm64Const.UC_ARM64_REG_X26,
                Arm64Const.UC_ARM64_REG_X27, Arm64Const.UC_ARM64_REG_X28, Arm64Const.UC_ARM64_REG_FP,
                Arm64Const.UC_ARM64_REG_LR, Arm64Const.UC_ARM64_REG_SP, Arm64Const.UC_ARM64_REG_NZCV
        };
        long[] v = new long[regs.length];
        for (int i = 0; i < regs.length; i++) {
            v[i] = backend.reg_read(regs[i]).longValue();
        }
        return v;
    }

    private void restoreRegs(Backend backend, long[] v) {
        int[] regs = {
                Arm64Const.UC_ARM64_REG_X0, Arm64Const.UC_ARM64_REG_X1, Arm64Const.UC_ARM64_REG_X2,
                Arm64Const.UC_ARM64_REG_X3, Arm64Const.UC_ARM64_REG_X4, Arm64Const.UC_ARM64_REG_X5,
                Arm64Const.UC_ARM64_REG_X6, Arm64Const.UC_ARM64_REG_X7, Arm64Const.UC_ARM64_REG_X8,
                Arm64Const.UC_ARM64_REG_X9, Arm64Const.UC_ARM64_REG_X10, Arm64Const.UC_ARM64_REG_X11,
                Arm64Const.UC_ARM64_REG_X12, Arm64Const.UC_ARM64_REG_X13, Arm64Const.UC_ARM64_REG_X14,
                Arm64Const.UC_ARM64_REG_X15, Arm64Const.UC_ARM64_REG_X16, Arm64Const.UC_ARM64_REG_X17,
                Arm64Const.UC_ARM64_REG_X18, Arm64Const.UC_ARM64_REG_X19, Arm64Const.UC_ARM64_REG_X20,
                Arm64Const.UC_ARM64_REG_X21, Arm64Const.UC_ARM64_REG_X22, Arm64Const.UC_ARM64_REG_X23,
                Arm64Const.UC_ARM64_REG_X24, Arm64Const.UC_ARM64_REG_X25, Arm64Const.UC_ARM64_REG_X26,
                Arm64Const.UC_ARM64_REG_X27, Arm64Const.UC_ARM64_REG_X28, Arm64Const.UC_ARM64_REG_FP,
                Arm64Const.UC_ARM64_REG_LR, Arm64Const.UC_ARM64_REG_SP, Arm64Const.UC_ARM64_REG_NZCV
        };
        for (int i = 0; i < regs.length; i++) {
            backend.reg_write(regs[i], v[i]);
        }
    }

    private void hookPthread() {
        UnidbgPointer create = emulator.getSvcMemory().registerSvc(new Arm64Svc("pthread_create_sync") {
            @Override
            public long handle(Emulator<?> emu) {
                Backend backend = emu.getBackend();
                long pthreadT = backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue();
                long start = backend.reg_read(Arm64Const.UC_ARM64_REG_X2).longValue();
                long arg = backend.reg_read(Arm64Const.UC_ARM64_REG_X3).longValue();
                System.out.println(String.format("pthread_create start=%x arg=%x pt=%x", start, arg, pthreadT));
                long result = 0;
                if (start > 0x1000) {
                    long[] regs = snapRegs(backend);
                    try {
                        Number r = Module.emulateFunction(emu, start, arg);
                        result = r == null ? 0 : r.longValue();
                        System.out.println("pthread_create sync => " + Long.toHexString(result));
                    } catch (Throwable t) {
                        System.out.println("pthread_create sync failed " + t);
                        t.printStackTrace();
                    } finally {
                        restoreRegs(backend, regs);
                    }
                }
                if (pthreadT > 0x1000) {
                    writeLong(pthreadT, result == 0 ? 1 : result);
                }
                return 0;
            }
        });
        UnidbgPointer join = emulator.getSvcMemory().registerSvc(new Arm64Svc("pthread_join_sync") {
            @Override
            public long handle(Emulator<?> emu) {
                Backend backend = emu.getBackend();
                long th = backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue();
                long retval = backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue();
                System.out.println(String.format("pthread_join th=%x retval_slot=%x", th, retval));
                if (retval > 0x1000) {
                    writeLong(retval, th == 1 ? 0 : th);
                }
                return 0;
            }
        });
        writeLong(BASE + 0x19f6e8L, create.peer);
        writeLong(BASE + 0x19f660L, join.peer);
        System.out.println("pthread stubs create=" + Long.toHexString(create.peer)
                + " join=" + Long.toHexString(join.peer));
    }

    private byte[] deriveKey(byte[] input, int tag) {
        try {
            byte[] sample = firstBlobSample();
            java.util.List<byte[]> candidates = new java.util.ArrayList<byte[]>();
            if (input != null) {
                candidates.add(md5(input));
                candidates.add(xorBytes(md5(input), tag));
                candidates.add(xorBytes(md5(input), 0xde));
                if (input.length >= 16) {
                    byte[] raw = new byte[16];
                    System.arraycopy(input, 0, raw, 0, 16);
                    candidates.add(raw);
                }
            }
            candidates.add(md5(new byte[0]));
            for (byte[] key : candidates) {
                if (key == null || key.length < 16) {
                    continue;
                }
                byte[] out = rc4Mod(key, sample);
                if (looksPlain(out)) {
                    System.out.println("deriveKey matched " + toHex(key));
                    return java.util.Arrays.copyOf(key, 16);
                }
            }
            byte[] fallback = input == null ? new byte[16] : xorBytes(md5(input), tag);
            System.out.println("deriveKey fallback " + toHex(fallback));
            return fallback;
        } catch (Exception e) {
            return new byte[16];
        }
    }

    private static byte[] md5(byte[] in) throws Exception {
        return MessageDigest.getInstance("MD5").digest(in);
    }

    private static byte[] xorBytes(byte[] in, int x) {
        byte[] out = java.util.Arrays.copyOf(in, in.length);
        for (int i = 0; i < out.length; i++) {
            out[i] ^= (byte) x;
        }
        return out;
    }

    private static boolean looksPlain(byte[] b) {
        if (b == null || b.length < 4) {
            return false;
        }
        return (b[0] == 'd' && b[1] == 'e' && b[2] == 'x')
                || (b[0] == 0x28 && b[1] == (byte) 0xb5 && b[2] == 0x2f && b[3] == (byte) 0xfd)
                || (b[0] == 0x78 && (b[1] == (byte) 0x9c || b[1] == (byte) 0xda || b[1] == 0x01));
    }

    private static byte[] rc4Mod(byte[] key, byte[] data) {
        int[] s = new int[256];
        for (int i = 0; i < 256; i++) {
            s[i] = i;
        }
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + s[i] + (key[i % key.length] & 0xff)) & 0xff;
            int t = s[i];
            s[i] = s[j];
            s[j] = t;
        }
        int i = 3;
        j = 5;
        byte[] out = new byte[data.length];
        for (int n = 0; n < data.length; n++) {
            i = (i + 2) & 0xff;
            j = (j + s[i] + 1) & 0xff;
            int t = s[i];
            s[i] = s[j];
            s[j] = t;
            out[n] = (byte) (data[n] ^ s[(s[i] + s[j]) & 0xff]);
        }
        return out;
    }

    private static byte[] firstBlobSample() {
        try {
            byte[] rest = Files.readAllBytes(new File("/tmp/deye-apk/unidbg-dump/qh-rest.bin").toPath());
            int sz = ByteBuffer.wrap(rest, 4, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
            int n = Math.min(64, sz);
            byte[] sample = new byte[n];
            System.arraycopy(rest, 12, sample, 0, n);
            return sample;
        } catch (Exception e) {
            return new byte[16];
        }
    }

    private byte[] peekBytes(Backend backend, long addr, int n) {
        if (addr < 0x1000 || n <= 0) {
            return new byte[0];
        }
        try {
            return backend.mem_read(addr, n);
        } catch (Exception e) {
            return new byte[0];
        }
    }

    private String toHex(byte[] b) {
        if (b == null || b.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (byte v : b) {
            sb.append(String.format("%02x", v));
        }
        return sb.toString();
    }

    private String peekStr(Backend backend, long addr) {
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
            return n == 0 ? null : new String(b, 0, n, java.nio.charset.StandardCharsets.US_ASCII);
        } catch (Exception e) {
            return null;
        }
    }

    private void dumpPtr(String tag, long addr, long size) {
        if (addr < 0x1000 || size <= 0 || size > 64L * 1024 * 1024) {
            return;
        }
        try {
            int n = (int) Math.min(size, 8L * 1024 * 1024);
            byte[] data = emulator.getBackend().mem_read(addr, n);
            File out = new File(OUT, tag + "-" + Long.toHexString(addr) + ".bin");
            try (FileOutputStream fos = new FileOutputStream(out)) {
                fos.write(data);
            }
            System.out.println("DUMPED PTR " + out + " size=" + n + " head=" +
                    String.format("%02x%02x%02x%02x", data[0], data[1], data[2], data[3]));
        } catch (Exception e) {
            System.out.println("dumpPtr " + tag + " failed " + e);
        }
    }

    private void dumpHeaps(String tag) {
        for (MemoryMap map : emulator.getMemory().getMemoryMap()) {
            if (map.base < 0x40600000L || map.size <= 0 || map.size > 16L * 1024 * 1024) {
                continue;
            }
            try {
                byte[] data = emulator.getBackend().mem_read(map.base, map.size);
                File out = new File(OUT, tag + "-heap-" + Long.toHexString(map.base) + ".bin");
                try (FileOutputStream fos = new FileOutputStream(out)) {
                    fos.write(data);
                }
                System.out.println("DUMPED HEAP " + out + " size=" + data.length);
            } catch (Exception ignored) {
            }
        }
    }

    private void scan(String tag) {
        System.out.println("scan " + tag);
        for (MemoryMap map : emulator.getMemory().getMemoryMap()) {
            if (map.size <= 0 || map.size > 64L * 1024 * 1024) {
                continue;
            }
            byte[] data;
            try {
                data = emulator.getBackend().mem_read(map.base, map.size);
            } catch (Exception e) {
                continue;
            }
            for (int i = 0; i + 40 < data.length; i++) {
                if (data[i] == 'd' && data[i + 1] == 'e' && data[i + 2] == 'x' && data[i + 3] == '\n'
                        && data[i + 4] == '0' && data[i + 5] == '3') {
                    int fileSize = ByteBuffer.wrap(data, i + 32, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
                    int headerSize = ByteBuffer.wrap(data, i + 36, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
                    if (headerSize != 0x70 || fileSize < 0x10000 || fileSize > 80 * 1024 * 1024) {
                        continue;
                    }
                    String key = tag + "-" + map.base + "-" + i + "-" + fileSize;
                    if (!dumped.add(key)) {
                        continue;
                    }
                    int n = Math.min(fileSize, data.length - i);
                    File out = new File(OUT, tag + "-" + Long.toHexString(map.base) + "-" + i + ".dex");
                    try (FileOutputStream fos = new FileOutputStream(out)) {
                        fos.write(data, i, n);
                    } catch (Exception e) {
                        continue;
                    }
                    System.out.println("DUMPED DEX " + out + " size=" + fileSize + " wrote=" + n);
                }
            }
        }
    }

    private Number callWithTimeout(java.util.concurrent.Callable<Number> call, long ms) {
        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(ms);
                System.out.println("TIMEOUT " + ms + "ms emu_stop");
                emulator.getBackend().emu_stop();
            } catch (InterruptedException ignored) {
            } catch (Exception e) {
                System.out.println("emu_stop " + e);
            }
        }, "emu-timeout");
        stopper.setDaemon(true);
        stopper.start();
        try {
            return call.call();
        } catch (Exception e) {
            System.out.println("call failed: " + e);
            e.printStackTrace();
            return null;
        } finally {
            stopper.interrupt();
        }
    }

    private Number call(long off, Object... args) {
        try {
            Number ret = Module.emulateFunction(emulator, BASE + off, args);
            System.out.println("call " + Long.toHexString(off) + " => " + ret);
            return ret;
        } catch (Throwable t) {
            System.out.println("call " + Long.toHexString(off) + " failed " + t);
            t.printStackTrace();
            return null;
        }
    }

    private long resolveOr(String... names) {
        for (String name : names) {
            long addr = findSym(name);
            if (addr != 0) {
                return addr;
            }
        }
        return 0;
    }

    private void applyFirstRela(long base) throws Exception {
        byte[] inner = Files.readAllBytes(new File("/tmp/deye-apk/native/inner_elf_0_0.so").toPath());
        int relaOff = 0xfdeb0;
        int relaSz = 0x948;
        int n = 0;
        for (int i = 0; i + 24 <= relaSz; i += 24) {
            ByteBuffer bb = ByteBuffer.wrap(inner, relaOff + i, 24).order(ByteOrder.LITTLE_ENDIAN);
            long off = bb.getLong();
            long info = bb.getLong();
            long add = bb.getLong();
            int typ = (int) (info & 0xffffffffL);
            if (typ == 1027) { // R_AARCH64_RELATIVE
                writeLong(base + off, base + add);
                n++;
            }
        }
        System.out.println("first RELATIVE applied " + n);
    }

    private void fixFirstPltGot(long base) {
        long malloc = resolveOr("malloc");
        long free = resolveOr("free");
        Object[][] patches = {
                {0x2fce8L, "mmap"},
                {0x2fcf0L, "abort"},
                {0x2fcf8L, "inflateInit_"},
                {0x2fd00L, "_ZdlPv"},
                {0x2fd08L, "fmod"},
                {0x2fd10L, "fgets"},
                {0x2fd18L, "feof"},
                {0x2fd20L, "calloc"},
                {0x2fd28L, "vsnprintf"},
                {0x2fd30L, "prctl"},
                {0x2fd38L, "pthread_detach"},
                {0x2fd40L, "getenv"},
                {0x2fd48L, "__stack_chk_fail"},
                {0x2fd50L, "dladdr"},
                {0x2fd58L, "select"},
                {0x2fd60L, "dlerror"},
                {0x2fd68L, "getpid"},
                {0x2fd70L, "strcpy"},
                {0x2fd78L, "readdir"},
                {0x2fd80L, "__cxa_finalize"},
                {0x2fd88L, "__aarch64_sync_cache_range"},
                {0x2fd90L, "munmap"},
                {0x2fd98L, "__errno"},
                {0x2fda0L, "memcpy"},
                {0x2fda8L, "malloc"},
                {0x2fdb0L, "interpreter_wrap_int64_t"},
                {0x2fdb8L, "pthread_create"},
                {0x2fdc0L, "puts"},
                {0x2fdc8L, "syscall"},
                {0x2fdd0L, "dl_iterate_phdr"},
                {0x2fdd8L, "inotify_init"},
                {0x2fde0L, "_Znwm"},
                {0x2fde8L, "sysconf"},
                {0x2fdf0L, "isspace"},
                {0x2fdf8L, "lseek"},
                {0x2fe00L, "kill"},
                {0x2fe08L, "strtol"},
                {0x2fe10L, "inflate"},
                {0x2fe18L, "__strlen_chk"},
                {0x2fe20L, "fmodf"},
                {0x2fe28L, "strstr"},
                {0x2fe30L, "__cxa_pure_virtual"},
                {0x2fe38L, "strncmp"},
                {0x2fe40L, "dlopen"},
                {0x2fe48L, "strncpy"},
                {0x2fe50L, "setenv"},
                {0x2fe58L, "strtok"},
                {0x2fe60L, "sscanf"},
                {0x2fe68L, "isalpha"},
                {0x2fe70L, "__strncpy_chk2"},
                {0x2fe78L, "sigaction"},
                {0x2fe80L, "dlsym"},
                {0x2fe88L, "strdup"},
                {0x2fe90L, "fopen"},
                {0x2fe98L, "memset"},
                {0x2fea0L, "__snprintf_chk"},
                {0x2fea8L, "_ZdaPv"},
                {0x2feb0L, "fclose"},
                {0x2feb8L, "time"},
                {0x2fec0L, "opendir"},
                {0x2fec8L, "strcmp"},
                {0x2fed0L, "inotify_add_watch"},
                {0x2fed8L, "_Znam"},
                {0x2fee0L, "__FD_SET_chk"},
                {0x2fee8L, "__sprintf_chk"},
                {0x2fef0L, "__read_chk"},
                {0x2fef8L, "atoi"},
                {0x2ff00L, "open"},
                {0x2ff08L, "_Z10__arm_a_20v"},
                {0x2ff10L, "inflateEnd"},
                {0x2ff18L, "mprotect"},
                {0x2ff20L, "closedir"},
                {0x2ff28L, "close"},
                {0x2ff30L, "raise"},
                {0x2ff38L, "free"},
        };
        int ok = 0;
        int stub = 0;
        for (Object[] p : patches) {
            long off = (Long) p[0];
            String name = (String) p[1];
            long addr = findSym(name);
            if (addr == 0) {
                if (name.equals("_Znwm") || name.equals("_Znam")) {
                    addr = malloc;
                } else if (name.equals("_ZdlPv") || name.equals("_ZdaPv")) {
                    addr = free;
                } else if (name.equals("interpreter_wrap_int64_t")) {
                    addr = base + 0x162ccL;
                } else if (name.equals("_Z10__arm_a_20v")) {
                    addr = base + 0x7c8cL;
                } else if (name.equals("__snprintf_chk")) {
                    addr = resolveOr("snprintf", "malloc");
                } else if (name.equals("__sprintf_chk")) {
                    addr = resolveOr("sprintf", "snprintf");
                } else if (name.equals("__strlen_chk")) {
                    addr = resolveOr("strlen");
                } else if (name.equals("__strncpy_chk2")) {
                    addr = resolveOr("strncpy");
                } else if (name.equals("__read_chk")) {
                    addr = resolveOr("read");
                } else if (name.equals("__cxa_pure_virtual") || name.equals("abort")) {
                    addr = STUB_PAGE + 8;
                }
            }
            if (addr == 0) {
                addr = stubFor(name);
                stub++;
                System.out.println("first GOT stub " + name);
            } else {
                ok++;
            }
            writeLong(base + off, addr);
        }
        // First-segment PLT names are decoys; code uses these slots as:
        long[][] decoys = {
                {0x2fd30L, malloc},
                {0x2fdf8L, resolveOr("calloc") != 0 ? resolveOr("calloc") : malloc},
                {0x2fd58L, resolveOr("memcpy")},
                {0x2fda0L, resolveOr("memcpy")},
                {0x2fdc0L, free},
                {0x2ff38L, free},
                {0x2fd00L, resolveOr("sysconf")},
                {0x2fe98L, resolveOr("memset")},
                {0x2fde0L, resolveOr("__stack_chk_fail")},
                {0x2fe10L, resolveOr("inflate")},
                {0x2fcf8L, resolveOr("inflateInit_")},
                {0x2ff10L, resolveOr("inflateEnd")},
                {0x2fdf0L, resolveOr("inflateInit_")},
                {0x2fd38L, resolveOr("inflate")},
                {0x2fd80L, malloc},
                {0x2fed8L, malloc},
        };
        for (long[] d : decoys) {
            if (d[1] != 0) {
                writeLong(base + d[0], d[1]);
            }
        }
        System.out.println("first SO GOT patched ok=" + ok + " stub=" + stub
                + " malloc=" + Long.toHexString(malloc) + " slot2340=" + Long.toHexString(readLong(base + 0x2fd30L)));
        // PLT 0x23a0 (GOT 0x2fd60, dynsym name "dlerror") is the interpreter
        // used by hash trampoline 0x8ae0.
        writeLong(base + 0x2fd60L, base + 0x162ccL);
        System.out.println("first GOT 0x2fd60 interpreter=" + Long.toHexString(base + 0x162ccL));
    }

    private long firstSoBase() {
        for (Module m : emulator.getMemory().getLoadedModules()) {
            if (m.name != null && m.name.contains("libjiagu")) {
                return m.base;
            }
        }
        return 0;
    }

    public void run() throws Exception {
        DalvikModule firstMod = vm.loadLibrary(new File("/tmp/deye-apk/native/libjiagu_mapped.so"), false);
        long first = firstMod.getModule().base;
        System.out.println("libc primed first=" + Long.toHexString(first));
        fixFirstPltGot(first);
        applyFirstRela(first);
        // First-SO JNI_OnLoad needs a fully relocated module; skip it and
        // only reuse its interpreter.
        mapImage();
        // 0x2490 is a decoy inotify_init; a plain ret preserves x1/x7 for the VM.
        writeLong(first + 0x2fdd8L, STUB_PAGE);
        if (first != 0) {
            // Stage2 wrappers call these first-SO bridges through *ptr.
            writeLong(BASE + 0x1a9ce0L, first + 0x165dcL);
            writeLong(BASE + 0x1a9ce8L, first + 0x166b8L);
            writeLong(BASE + 0x1a9cf0L, first + 0x16754L);
            System.out.println("wired interpreter bridges");
        }
        hookOpenatGuard();
        hookInteresting();
        hookZipScan();
        hookSyscallGens();
        if (first != 0) {
            firstBase = first;
            writeLong(BASE + 0x1a9ce0L, first + 0x165dcL);
            writeLong(BASE + 0x1a9ce8L, first + 0x166b8L);
            writeLong(BASE + 0x1a9cf0L, first + 0x16754L);
            writeLong(BASE + 0x1a1598L, first + 0x8ae0L);
            nativeHashWired = true;
            System.out.println("wired interpreter bridges and hash 0x8ae0 -> "
                    + Long.toHexString(first + 0x8ae0L));
        }
        hookProtocol();
        if (first != 0) {
            final long fbase = first;
            emulator.getBackend().hook_add_new(new CodeHook() {
                @Override
                public void hook(Backend backend, long address, int size, Object user) {
                    System.out.println(String.format("first-bridge pc=%x x0=%x x1=%x x2=%x x3=%x",
                            address,
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X2).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X3).longValue()));
                }
                @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
                @Override public void detach() {}
            }, fbase + 0x165dcL, fbase + 0x165e0L, null);
            emulator.getBackend().hook_add_new(new CodeHook() {
                @Override
                public void hook(Backend backend, long address, int size, Object user) {
                    System.out.println(String.format("first-vm pc=%x x0=%x x1=%x x2=%x x3=%x x4=%x",
                            address,
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X2).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X3).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X4).longValue()));
                }
                @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
                @Override public void detach() {}
            }, fbase + 0x14690L, fbase + 0x14694L, null);
            emulator.getBackend().hook_add_new(new CodeHook() {
                @Override
                public void hook(Backend backend, long address, int size, Object user) {
                    System.out.println(String.format("hash-tramp pc=%x x0=%x x1=%x x2=%x x3=%x x4=%x x5=%x x6=%x",
                            address,
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X1).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X2).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X3).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X4).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X5).longValue(),
                            backend.reg_read(Arm64Const.UC_ARM64_REG_X6).longValue()));
                }
                @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
                @Override public void detach() {}
            }, fbase + 0x8ae0L, fbase + 0x8ae4L, null);
            emulator.getBackend().hook_add_new(new CodeHook() {
                private int n;
                @Override
                public void hook(Backend backend, long address, int size, Object user) {
                    n++;
                    if (n <= 12 || n % 100 == 0) {
                        System.out.println(String.format(
                                "vm-2490 #%d x0=%x x7=%x",
                                n,
                                backend.reg_read(Arm64Const.UC_ARM64_REG_X0).longValue(),
                                backend.reg_read(Arm64Const.UC_ARM64_REG_X7).longValue()));
                    }
                }
                @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
                @Override public void detach() {}
            }, fbase + 0x13d78L, fbase + 0x13d7cL, null);
        }
        long[] inits = {
                0x301bc, 0x30234, 0x30244, 0x3037c, 0x3062c, 0x30658,
                0x306a8, 0x30748, 0x3087c, 0x308a8, 0x30968, 0x30a48, 0x30df8
        };
        for (long init : inits) {
            System.out.println("init " + Long.toHexString(init));
            callWithTimeout(() -> call(init), 5000);
        }
        scan("after-inits");
        if (first != 0) {
            writeLong(BASE + 0x1a9ce0L, first + 0x165dcL);
            writeLong(BASE + 0x1a9ce8L, first + 0x166b8L);
            writeLong(BASE + 0x1a9cf0L, first + 0x16754L);
            writeLong(BASE + 0x1a1598L, first + 0x8ae0L);
            writeLong(first + 0x2fd60L, first + 0x162ccL);
        }
        System.out.println("wrap_ptr " + Long.toHexString(readLong(BASE + 0x1a9ce0L)));
        System.out.println("wrap_got " + Long.toHexString(readLong(BASE + 0x19fe28L)));
        System.out.println("hash_got " + Long.toHexString(readLong(BASE + 0x19ff38L)));
        System.out.println("hash_fn " + Long.toHexString(readLong(BASE + 0x1a1598L)));
        System.out.println("JNI_OnLoad");
        Number jni = callWithTimeout(() -> call(0x36f18L, vm.getJavaVM(), 0), 180000);
        System.out.println("JNI_OnLoad => " + jni);
        dumpHeaps("after-jni");
        scan("after-jni");
        // qh payload starts at APK map + classes.dex data + 23996.
        // cfg-decrypt x1 is qh+12 = 0x70597220, so qh = 0x70597214.
        long qh = 0x70597214L;
        try {
            byte[] magic = emulator.getBackend().mem_read(qh, 2);
            System.out.println("qh magic=" + toHex(magic) + " size+8=" +
                    Integer.toHexString(ByteBuffer.wrap(emulator.getBackend().mem_read(qh + 8, 4))
                            .order(ByteOrder.LITTLE_ENDIAN).getInt()));
            System.out.println("direct blob-loop 45bbc");
            Number blobs = callWithTimeout(() -> call(0x45bbcL, qh), 180000);
            System.out.println("45bbc => " + blobs);
            dumpHeaps("after-45bbc");
            scan("after-45bbc");
        } catch (Exception e) {
            System.out.println("direct 45bbc failed " + e);
        }
        DvmClass stub = vm.resolveClass("com/stub/StubApp");
        final boolean[] interface5Ok = {false};
        System.out.println("interface5 via DvmClass");
        callWithTimeout(() -> {
            stub.callStaticJniMethod(emulator, "interface5(Landroid/app/Application;)V", application());
            interface5Ok[0] = true;
            return 0;
        }, 90000);
        System.out.println("interface5 DvmClass ok=" + interface5Ok[0]);
        if (!interface5Ok[0]) {
            System.out.println("interface5 via native 0x38384");
            callWithTimeout(() -> call(0x38384L, vm.getJNIEnv(), stub.hashCode(), application()), 90000);
        }
        dumpHeaps("after-interface5");
        scan("after-interface5");
        System.out.println("maps after interface5:");
        for (MemoryMap map : emulator.getMemory().getMemoryMap()) {
            System.out.println(String.format("  %s-%s sz=0x%x",
                    Long.toHexString(map.base), Long.toHexString(map.base + map.size), map.size));
        }
        System.out.println("done zstdHits=" + zstdHits.get());
    }

    private DvmObject<?> fileObj(BaseVM base, String path) {
        return base.resolveClass("java/io/File").newObject(new File(path));
    }

    private String stringArg(VaList vaList) {
        try {
            DvmObject<?> arg = vaList.getObjectArg(0);
            if (arg != null && arg.getValue() != null) {
                return String.valueOf(arg.getValue());
            }
        } catch (Exception ignored) {
        }
        return "";
    }

    private DvmObject<?> serviceManager(BaseVM base) {
        return base.resolveClass("android/os/ServiceManagerProxy",
                base.resolveClass("android/os/IServiceManager")).newObject("sm");
    }

    private DvmObject<?> packageManager(BaseVM base) {
        return base.resolveClass(
                "android/content/pm/IPackageManager$Stub$Proxy",
                base.resolveClass("android/content/pm/IPackageManager"),
                base.resolveClass("android/content/pm/PackageManager")
        ).newObject("ipm");
    }

    @Override
    public DvmObject<?> callStaticObjectMethodV(BaseVM base, DvmClass dvmClass, String signature, VaList vaList) {
        System.out.println("JNI static " + signature);
        if (signature.contains("currentPackageName") || signature.contains("getPackageName")) {
            return new StringObject(base, PKG);
        }
        if (signature.contains("currentActivityThread")) {
            return base.resolveClass("android/app/ActivityThread").newObject("at");
        }
        if (signature.contains("currentApplication") || signature.contains("getAppContext")) {
            return application();
        }
        if (signature.contains("StubApp->getDir") || signature.contains("getSoPath")) {
            return new StringObject(base, FILES);
        }
        if (signature.contains("getProperty") || signature.contains("SystemProperties->get")) {
            String key = stringArg(vaList);
            System.out.println("getProperty key=" + key);
            if ("java.vm.version".equals(key)) {
                return new StringObject(base, "2.1.0");
            }
            if ("java.vm.name".equals(key)) {
                return new StringObject(base, "Dalvik");
            }
            if ("os.arch".equals(key)) {
                return new StringObject(base, "aarch64");
            }
            if (key.startsWith("ro.product") || key.contains("model") || key.contains("MODEL")) {
                return new StringObject(base, "Pixel 6");
            }
            String sys = key.isEmpty() ? null : System.getProperty(key);
            return new StringObject(base, sys == null ? "" : sys);
        }
        if (signature.contains("IPackageManager") && signature.contains("asInterface")) {
            return packageManager(base);
        }
        if (signature.contains("getContextObject") || signature.contains("asInterface")) {
            return serviceManager(base);
        }
        if (signature.contains("getService")) {
            return packageManager(base);
        }
        if (signature.contains("Ljava/lang/String;")) {
            return new StringObject(base, "");
        }
        if (signature.contains("Landroid/content/Context;") || signature.contains("Landroid/app/Application;")) {
            return application();
        }
        String ret = signature.contains(")") ? signature.substring(signature.indexOf(')') + 1) : "";
        if (ret.startsWith("L") && ret.endsWith(";")) {
            return base.resolveClass(ret.substring(1, ret.length() - 1)).newObject("static");
        }
        return application();
    }

    @Override
    public DvmObject<?> callStaticObjectMethod(BaseVM base, DvmClass dvmClass, String signature, VarArg varArg) {
        return callStaticObjectMethodV(base, dvmClass, signature, null);
    }

    @Override
    public DvmObject<?> callObjectMethod(BaseVM base, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        return callObjectMethodV(base, dvmObject, signature, null);
    }

    @Override
    public DvmObject<?> callObjectMethodV(BaseVM base, DvmObject<?> dvmObject, String signature, VaList vaList) {
        System.out.println("JNI obj " + signature);
        if (signature.contains("getBytes")) {
            String s = "";
            if (dvmObject instanceof StringObject) {
                s = ((StringObject) dvmObject).getValue();
            } else if (dvmObject != null && dvmObject.getValue() != null) {
                s = String.valueOf(dvmObject.getValue());
            }
            System.out.println("getBytes of \"" + s + "\"");
            try {
                return new ByteArray(base, s.getBytes("UTF-8"));
            } catch (Exception e) {
                return new ByteArray(base, s.getBytes());
            }
        }
        if (signature.contains("getPackageName") || signature.contains("getProcessName")
                || signature.contains("getPackageCodePath") || signature.contains("getPackageResourcePath")) {
            if (signature.contains("CodePath") || signature.contains("ResourcePath")) {
                return new StringObject(base, APK.getAbsolutePath());
            }
            return new StringObject(base, PKG);
        }
        if (signature.contains("getPackageManager") || signature.contains("getService")) {
            return packageManager(base);
        }
        if (signature.contains("getPackageInfo")) {
            return new PackageInfo(base, PKG, 0x40);
        }
        if (signature.contains("getApplicationInfo")) {
            return new ApplicationInfo(base);
        }
        if (signature.contains("getClassLoader")) {
            return new ClassLoader(base, signature);
        }
        if (signature.contains("getAssets")) {
            return new AssetManager(base, signature);
        }
        if (signature.contains("toByteArray")) {
            return new ByteArray(base, PKCS7);
        }
        if (signature.contains("getSystemContext") || signature.contains("getApplicationContext")
                || signature.contains("getBaseContext")) {
            return contextImpl();
        }
        if (signature.contains("getParentFile")) {
            Object value = dvmObject == null ? null : dvmObject.getValue();
            if (value instanceof File) {
                File parent = ((File) value).getParentFile();
                return fileObj(base, parent == null ? DATA : parent.getAbsolutePath());
            }
            return fileObj(base, DATA);
        }
        if (signature.contains("getFilesDir") || signature.contains("getCacheDir")
                || signature.contains("getCodeCacheDir") || signature.contains("getDataDir")
                || signature.contains("getDir(") || signature.contains("getNoBackupFilesDir")) {
            return fileObj(base, FILES);
        }
        if (signature.contains("getAbsolutePath") || signature.contains("getCanonicalPath")
                || signature.contains("getPath()Ljava/lang/String;")) {
            Object value = dvmObject == null ? null : dvmObject.getValue();
            if (value instanceof File) {
                return new StringObject(base, ((File) value).getAbsolutePath());
            }
            if (value instanceof String) {
                return new StringObject(base, (String) value);
            }
            return new StringObject(base, FILES);
        }
        if (signature.contains("toString()Ljava/lang/String;")) {
            Object value = dvmObject == null ? null : dvmObject.getValue();
            return new StringObject(base, value == null ? PKG : String.valueOf(value));
        }
        if (signature.contains("[B")) {
            return new ByteArray(base, new byte[0]);
        }
        if (signature.contains("Ljava/io/File;")) {
            return fileObj(base, FILES);
        }
        if (signature.contains("Ljava/lang/String;")) {
            return new StringObject(base, PKG);
        }
        return new StringObject(base, PKG);
    }

    @Override
    public DvmObject<?> newObjectV(BaseVM base, DvmClass dvmClass, String signature, VaList vaList) {
        System.out.println("JNI new " + signature);
        if (signature.contains("java/io/File-><init>(Ljava/lang/String;)V")) {
            StringObject path = vaList.getObjectArg(0);
            return dvmClass.newObject(new File(path == null ? DATA : path.getValue()));
        }
        if (signature.contains("java/io/File-><init>(Ljava/io/File;Ljava/lang/String;)V")) {
            DvmObject<?> parent = vaList.getObjectArg(0);
            StringObject name = vaList.getObjectArg(1);
            File parentFile = parent != null && parent.getValue() instanceof File
                    ? (File) parent.getValue()
                    : new File(DATA);
            return dvmClass.newObject(new File(parentFile, name == null ? "x" : name.getValue()));
        }
        if (signature.contains("InMemoryDexClassLoader") || signature.contains("DexClassLoader")
                || signature.contains("PathClassLoader") || signature.contains("DexFile")) {
            System.out.println("DEX LOADER " + signature);
            try {
                DvmObject<?> arg0 = vaList.getObjectArg(0);
                if (arg0 instanceof ByteArray) {
                    byte[] dex = ((ByteArray) arg0).getValue();
                    File out = new File(OUT, "inmemory-" + dex.length + ".dex");
                    try (FileOutputStream fos = new FileOutputStream(out)) {
                        fos.write(dex);
                    }
                    System.out.println("DUMPED INMEMORY " + out + " size=" + dex.length);
                } else if (arg0 != null) {
                    System.out.println("DEX LOADER arg0=" + arg0.getClass() + " value=" + arg0.getValue());
                }
            } catch (Exception e) {
                System.out.println("DEX LOADER dump failed " + e);
            }
            return dvmClass.newObject("dexloader");
        }
        try {
            return super.newObjectV(base, dvmClass, signature, vaList);
        } catch (UnsupportedOperationException e) {
            return dvmClass.newObject("new");
        }
    }

    @Override
    public DvmObject<?> getObjectField(BaseVM base, DvmObject<?> dvmObject, String signature) {
        System.out.println("JNI field " + signature);
        if (signature.contains("signatures")) {
            return super.getObjectField(base, new PackageInfo(base, PKG, 0x40),
                    "android/content/pm/PackageInfo->signatures:[Landroid/content/pm/Signature;");
        }
        if (signature.contains("applicationInfo")) {
            return new ApplicationInfo(base);
        }
        if (signature.contains("sourceDir") || signature.contains("publicSourceDir")) {
            return new StringObject(base, APK.getAbsolutePath());
        }
        if (signature.contains("dataDir")) {
            return new StringObject(base, "/data/data/com.deye");
        }
        if (signature.contains("nativeLibraryDir")) {
            return new StringObject(base, "/data/app/com.deye/lib/arm64");
        }
        return new StringObject(base, PKG);
    }

    @Override
    public DvmObject<?> getStaticObjectField(BaseVM base, DvmClass dvmClass, String signature) {
        System.out.println("JNI sfield " + signature);
        if (signature.contains("CPU_ABI") || signature.contains("CPU_ABI2")) {
            return new StringObject(base, "arm64-v8a");
        }
        if (signature.contains("MODEL") || signature.contains("DEVICE") || signature.contains("PRODUCT")
                || signature.contains("MANUFACTURER") || signature.contains("BRAND")
                || signature.contains("FINGERPRINT") || signature.contains("HARDWARE")) {
            return new StringObject(base, "Pixel 6");
        }
        return new StringObject(base, "arm64-v8a");
    }

    @Override
    public int getStaticIntField(BaseVM base, DvmClass dvmClass, String signature) {
        System.out.println("JNI sInt " + signature);
        if (signature.contains("GET_SIGNATURES")) {
            return 0x40;
        }
        if (signature.contains("GET_SIGNING_CERTIFICATES")) {
            return 0x08000000;
        }
        if (signature.contains("SDK_INT")) {
            return 30;
        }
        return 0;
    }

    @Override
    public boolean callBooleanMethodV(BaseVM base, DvmObject<?> dvmObject, String signature, VaList vaList) {
        System.out.println("JNI bool " + signature);
        if (dvmObject != null && dvmObject.getValue() instanceof File) {
            File f = (File) dvmObject.getValue();
            if (signature.contains("exists")) {
                return f.exists();
            }
            if (signature.contains("isDirectory")) {
                return f.isDirectory();
            }
            if (signature.contains("isFile")) {
                return f.isFile();
            }
            if (signature.contains("mkdir")) {
                return f.mkdirs();
            }
            if (signature.contains("canRead") || signature.contains("canWrite")) {
                return true;
            }
        }
        return true;
    }

    @Override
    public int callIntMethodV(BaseVM base, DvmObject<?> dvmObject, String signature, VaList vaList) {
        System.out.println("JNI int " + signature);
        if (signature.contains("hashCode")) {
            return PKG.hashCode();
        }
        if (dvmObject != null && dvmObject.getValue() instanceof File && signature.contains("length")) {
            return (int) ((File) dvmObject.getValue()).length();
        }
        return 0;
    }

    @Override
    public int callStaticIntMethodV(BaseVM base, DvmClass dvmClass, String signature, VaList vaList) {
        System.out.println("JNI staticInt " + signature);
        if (signature.contains("myUserId") || signature.contains("getUserId") || signature.contains("myUid")) {
            return 0;
        }
        if (signature.contains("SDK_INT")) {
            return 30;
        }
        return 0;
    }

    @Override
    public int callStaticIntMethod(BaseVM base, DvmClass dvmClass, String signature, VarArg varArg) {
        return callStaticIntMethodV(base, dvmClass, signature, null);
    }

    @Override
    public int callIntMethod(BaseVM base, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        return callIntMethodV(base, dvmObject, signature, null);
    }

    @Override
    public boolean callStaticBooleanMethodV(BaseVM base, DvmClass dvmClass, String signature, VaList vaList) {
        System.out.println("JNI staticBool " + signature);
        return true;
    }

    @Override
    public boolean callBooleanMethod(BaseVM base, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        return callBooleanMethodV(base, dvmObject, signature, null);
    }

    @Override
    public void callVoidMethodV(BaseVM base, DvmObject<?> dvmObject, String signature, VaList vaList) {
        System.out.println("JNI void " + signature);
    }

    @Override
    public void callStaticVoidMethodV(BaseVM base, DvmClass dvmClass, String signature, VaList vaList) {
        System.out.println("JNI staticVoid " + signature);
    }

    @Override
    public long callLongMethodV(BaseVM base, DvmObject<?> dvmObject, String signature, VaList vaList) {
        System.out.println("JNI long " + signature);
        return 0;
    }

    @Override
    public long callStaticLongMethodV(BaseVM base, DvmClass dvmClass, String signature, VaList vaList) {
        System.out.println("JNI staticLong " + signature);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        new Stage2Dump().run();
    }
}
