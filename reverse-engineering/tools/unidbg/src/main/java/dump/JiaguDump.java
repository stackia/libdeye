package dump;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.Symbol;
import com.github.unidbg.arm.backend.Backend;
import com.github.unidbg.arm.backend.CodeHook;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.AbstractJni;
import com.github.unidbg.linux.android.dvm.BaseVM;
import com.github.unidbg.linux.android.dvm.DalvikModule;
import com.github.unidbg.linux.android.dvm.DvmClass;
import com.github.unidbg.linux.android.dvm.DvmObject;
import com.github.unidbg.linux.android.dvm.StringObject;
import com.github.unidbg.linux.android.dvm.VaList;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.linux.android.dvm.VarArg;
import com.github.unidbg.linux.android.dvm.api.ApplicationInfo;
import com.github.unidbg.linux.android.dvm.api.AssetManager;
import com.github.unidbg.linux.android.dvm.api.ClassLoader;
import com.github.unidbg.linux.android.dvm.api.PackageInfo;
import com.github.unidbg.linux.android.dvm.array.ArrayObject;
import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.github.unidbg.file.FileResult;
import com.github.unidbg.file.IOResolver;
import com.github.unidbg.file.linux.AndroidFileIO;
import com.github.unidbg.linux.file.SimpleFileIO;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.memory.MemoryBlock;
import com.github.unidbg.memory.MemoryMap;
import com.github.unidbg.virtualmodule.android.AndroidModule;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class JiaguDump extends AbstractJni {
    private static final File APK = new File("/tmp/deye-apk/com.deye_4.2.1.apk");
    private static final File SO = new File("/tmp/deye-apk/native/libjiagu_mapped.so");
    private static final int SO_HDR = 0x1000;
    private static final File OUT = new File("/tmp/deye-apk/unidbg-dump");
    private static final String PKG = "com.deye";
    private static final String DATA = "/data/data/com.deye";
    private static final byte[] PKCS7_SIG = readPkcs7();

    private static byte[] readPkcs7() {
        try {
            java.util.zip.ZipFile zf = new java.util.zip.ZipFile(APK);
            try {
                java.io.InputStream in = zf.getInputStream(zf.getEntry("META-INF/DEYE.RSA"));
                return in.readAllBytes();
            } finally {
                zf.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final AndroidEmulator emulator;
    private final VM vm;
    private final AtomicInteger dumpCount = new AtomicInteger();
    private final Set<String> dumpedKeys = new HashSet<>();
    private DvmObject<?> systemContext;
    private DvmObject<?> application;

    public JiaguDump() {
        emulator = AndroidEmulatorBuilder.for64Bit()
                .setProcessName(PKG)
                .build();
        Memory memory = emulator.getMemory();
        memory.setLibraryResolver(new AndroidResolver(23));
        vm = emulator.createDalvikVM(APK);
        vm.setJni(this);
        vm.setVerbose(true);
        new AndroidModule(emulator, vm).register(memory);
        emulator.getSyscallHandler().addIOResolver((IOResolver<AndroidFileIO>) (emu, pathname, oflags) -> {
            System.out.println("open " + pathname + " flags=" + oflags);
            if (pathname != null && pathname.endsWith(".apk")) {
                return FileResult.success(new SimpleFileIO(oflags, APK, pathname));
            }
            return null;
        });
        OUT.mkdirs();
        new File("/tmp/deye-apk/unidbg-fs" + DATA + "/files/.jiagu").mkdirs();
        new File("/tmp/deye-apk/unidbg-fs" + DATA + "/cache").mkdirs();
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

    private DvmObject<?> fileObj(BaseVM base, String path) {
        return base.resolveClass("java/io/File").newObject(new File(path));
    }

    private String remap(String signature) {
        return signature
                .replace("android/app/ContextImpl->", "android/content/Context->")
                .replace("android/content/ContextWrapper->", "android/content/Context->")
                .replace("android/app/Application->", "android/content/Context->");
    }

    private void log(String kind, String signature) {
        System.out.println("JNI " + kind + ": " + signature);
    }

    private DvmObject<?> handleObject(BaseVM base, DvmObject<?> dvmObject, String signature) {
        if (signature.contains("getSystemContext")
                || signature.contains("getApplicationContext")
                || signature.contains("getBaseContext")) {
            return contextImpl();
        }
        if (signature.contains("currentApplication") || signature.endsWith("getApplication()Landroid/app/Application;")) {
            return application();
        }
        if (signature.contains("getPackageName") || signature.contains("getProcessName")) {
            return new StringObject(base, PKG);
        }
        if (signature.contains("getApplicationInfo")) {
            return new ApplicationInfo(base);
        }
        if (signature.contains("getClassLoader")) {
            return new ClassLoader(base, signature);
        }
        if (signature.contains("getPackageManager")) {
            return base.resolveClass("android/content/pm/PackageManager").newObject("pm");
        }
        if (signature.contains("getPackageInfo")) {
            return new PackageInfo(base, PKG, 0x40);
        }
        if (signature.contains("getAssets")) {
            return new AssetManager(base, signature);
        }
        if (signature.contains("getFilesDir") || signature.contains("getCacheDir")
                || signature.contains("getCodeCacheDir") || signature.contains("getDataDir")
                || signature.contains("getDir(") || signature.contains("getNoBackupFilesDir")
                || signature.contains("getExternalFilesDir") || signature.contains("getExternalCacheDir")) {
            return fileObj(base, DATA + "/files");
        }
        if (signature.contains("getParentFile")) {
            Object value = dvmObject == null ? null : dvmObject.getValue();
            if (value instanceof File) {
                File parent = ((File) value).getParentFile();
                return fileObj(base, parent == null ? DATA : parent.getAbsolutePath());
            }
            return fileObj(base, DATA);
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
            return new StringObject(base, DATA + "/files");
        }
        if (signature.contains("toByteArray") && signature.contains("Signature")) {
            System.out.println("Signature.toByteArray using PKCS7 len=" + PKCS7_SIG.length);
            return new ByteArray(base, PKCS7_SIG);
        }
        if (signature.contains("toString()Ljava/lang/String;")) {
            Object value = dvmObject == null ? null : dvmObject.getValue();
            return new StringObject(base, value == null ? PKG : String.valueOf(value));
        }
        if (signature.contains("getName()Ljava/lang/String;")) {
            return new StringObject(base, PKG);
        }
        return null;
    }

    private DvmObject<?> objectFallback(BaseVM base, DvmObject<?> dvmObject, String signature, boolean isV, Object args) {
        DvmObject<?> handled = handleObject(base, dvmObject, signature);
        if (handled != null) {
            return handled;
        }
        String remapped = remap(signature);
        try {
            if (isV) {
                return super.callObjectMethodV(base, dvmObject, remapped, (VaList) args);
            }
            return super.callObjectMethod(base, dvmObject, remapped, (VarArg) args);
        } catch (UnsupportedOperationException e) {
            if (!remapped.equals(signature)) {
                try {
                    if (isV) {
                        return super.callObjectMethodV(base, dvmObject, signature, (VaList) args);
                    }
                    return super.callObjectMethod(base, dvmObject, signature, (VarArg) args);
                } catch (UnsupportedOperationException ignored) {
                }
            }
            System.out.println("UNHANDLED obj: " + signature);
            if (signature.contains("[B")) {
                return new ByteArray(base, new byte[0]);
            }
            if (signature.contains("Ljava/lang/String;")) {
                return new StringObject(base, "");
            }
            if (signature.contains("Ljava/io/File;")) {
                return fileObj(base, DATA + "/files");
            }
            String ret = signature.substring(signature.indexOf(')') + 1);
            if (ret.startsWith("L") && ret.endsWith(";")) {
                String cls = ret.substring(1, ret.length() - 1);
                return base.resolveClass(cls).newObject("fallback");
            }
            return new StringObject(base, "");
        }
    }

    @Override
    public DvmObject<?> callStaticObjectMethodV(BaseVM base, DvmClass dvmClass, String signature, VaList vaList) {
        log("staticV", signature);
        DvmObject<?> handled = handleObject(base, null, signature);
        if (handled != null) {
            return handled;
        }
        if (signature.contains("System->getProperty")) {
            return new StringObject(base, "Linux");
        }
        if (signature.contains("currentActivityThread")) {
            return base.resolveClass("android/app/ActivityThread").newObject("at");
        }
        try {
            return super.callStaticObjectMethodV(base, dvmClass, signature, vaList);
        } catch (UnsupportedOperationException e) {
            System.out.println("UNHANDLED static: " + signature);
            if (signature.contains("Ljava/lang/String;")) {
                return new StringObject(base, "");
            }
            String ret = signature.substring(signature.indexOf(')') + 1);
            if (ret.startsWith("L") && ret.endsWith(";")) {
                return base.resolveClass(ret.substring(1, ret.length() - 1)).newObject("fallback");
            }
            return new StringObject(base, "");
        }
    }

    @Override
    public DvmObject<?> callStaticObjectMethod(BaseVM base, DvmClass dvmClass, String signature, VarArg varArg) {
        log("static", signature);
        return callStaticObjectMethodV(base, dvmClass, signature, null);
    }

    @Override
    public DvmObject<?> callObjectMethodV(BaseVM base, DvmObject<?> dvmObject, String signature, VaList vaList) {
        log("objV", signature);
        return objectFallback(base, dvmObject, signature, true, vaList);
    }

    @Override
    public DvmObject<?> callObjectMethod(BaseVM base, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        log("obj", signature);
        return objectFallback(base, dvmObject, signature, false, varArg);
    }

    @Override
    public DvmObject<?> getObjectField(BaseVM base, DvmObject<?> dvmObject, String signature) {
        log("field", signature);
        if (signature.contains("packageName") || signature.contains("processName")
                || signature.contains("taskAffinity") || signature.contains("name:Ljava/lang/String;")) {
            return new StringObject(base, PKG);
        }
        if (signature.contains("sourceDir") || signature.contains("publicSourceDir")) {
            return new StringObject(base, APK.getAbsolutePath());
        }
        if (signature.contains("dataDir") || signature.contains("deviceProtectedDataDir")
                || signature.contains("credentialProtectedDataDir")) {
            return new StringObject(base, DATA);
        }
        if (signature.contains("nativeLibraryDir")) {
            return new StringObject(base, "/data/app/" + PKG + "-1/lib/arm64");
        }
        if (signature.contains("signatures")) {
            try {
                return super.getObjectField(base, dvmObject instanceof PackageInfo
                        ? dvmObject
                        : new PackageInfo(base, PKG, 0x40),
                        "android/content/pm/PackageInfo->signatures:[Landroid/content/pm/Signature;");
            } catch (Exception e) {
                System.out.println("signatures fallback: " + e);
            }
        }
        if (signature.contains("applicationInfo")) {
            return new ApplicationInfo(base);
        }
        if (signature.contains("versionName")) {
            return new StringObject(base, "4.2.1");
        }
        try {
            return super.getObjectField(base, dvmObject, signature);
        } catch (UnsupportedOperationException e) {
            System.out.println("UNHANDLED field: " + signature);
            return new StringObject(base, "");
        }
    }

    @Override
    public DvmObject<?> getStaticObjectField(BaseVM base, DvmClass dvmClass, String signature) {
        log("staticField", signature);
        if (signature.contains("CPU_ABI") || signature.contains("CPU_ABI2")) {
            return new StringObject(base, "arm64-v8a");
        }
        if (signature.contains("SUPPORTED_ABIS") || signature.contains("SUPPORTED_64_BIT_ABIS")) {
            return ArrayObject.newStringArray(base, "arm64-v8a");
        }
        if (signature.contains("SUPPORTED_32_BIT_ABIS")) {
            return ArrayObject.newStringArray(base);
        }
        if (signature.contains("MODEL") || signature.contains("DEVICE") || signature.contains("PRODUCT")
                || signature.contains("MANUFACTURER") || signature.contains("BRAND")
                || signature.contains("FINGERPRINT") || signature.contains("HARDWARE")
                || signature.contains("BOARD") || signature.contains("HOST")) {
            return new StringObject(base, "Pixel");
        }
        try {
            return super.getStaticObjectField(base, dvmClass, signature);
        } catch (UnsupportedOperationException e) {
            return new StringObject(base, "");
        }
    }

    @Override
    public int getStaticIntField(BaseVM base, DvmClass dvmClass, String signature) {
        log("staticInt", signature);
        if (signature.contains("SDK_INT")) {
            return 30;
        }
        try {
            return super.getStaticIntField(base, dvmClass, signature);
        } catch (UnsupportedOperationException e) {
            return 0;
        }
    }

    @Override
    public int getIntField(BaseVM base, DvmObject<?> dvmObject, String signature) {
        log("intField", signature);
        if (signature.contains("versionCode") || signature.contains("targetSdkVersion")) {
            return 141;
        }
        if (signature.contains("flags") || signature.contains("uid") || signature.contains("minSdkVersion")) {
            return 141;
        }
        try {
            return super.getIntField(base, dvmObject, signature);
        } catch (UnsupportedOperationException e) {
            return 0;
        }
    }

    @Override
    public boolean callBooleanMethodV(BaseVM base, DvmObject<?> dvmObject, String signature, VaList vaList) {
        log("boolV", signature);
        return true;
    }

    @Override
    public boolean callBooleanMethod(BaseVM base, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        log("bool", signature);
        return true;
    }

    @Override
    public boolean callStaticBooleanMethodV(BaseVM base, DvmClass dvmClass, String signature, VaList vaList) {
        log("staticBoolV", signature);
        return true;
    }

    @Override
    public boolean callStaticBooleanMethod(BaseVM base, DvmClass dvmClass, String signature, VarArg varArg) {
        log("staticBool", signature);
        return true;
    }

    @Override
    public int callIntMethodV(BaseVM base, DvmObject<?> dvmObject, String signature, VaList vaList) {
        log("intV", signature);
        try {
            return super.callIntMethodV(base, dvmObject, signature, vaList);
        } catch (UnsupportedOperationException e) {
            return 0;
        }
    }

    @Override
    public int callIntMethod(BaseVM base, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        log("int", signature);
        try {
            return super.callIntMethod(base, dvmObject, signature, varArg);
        } catch (UnsupportedOperationException e) {
            return 0;
        }
    }

    @Override
    public void callVoidMethodV(BaseVM base, DvmObject<?> dvmObject, String signature, VaList vaList) {
        log("voidV", signature);
    }

    @Override
    public void callVoidMethod(BaseVM base, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        log("void", signature);
    }

    @Override
    public void callStaticVoidMethodV(BaseVM base, DvmClass dvmClass, String signature, VaList vaList) {
        log("staticVoidV", signature);
    }

    @Override
    public void callStaticVoidMethod(BaseVM base, DvmClass dvmClass, String signature, VarArg varArg) {
        log("staticVoid", signature);
    }

    @Override
    public DvmObject<?> newObjectV(BaseVM base, DvmClass dvmClass, String signature, VaList vaList) {
        log("newV", signature);
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
        try {
            return super.newObjectV(base, dvmClass, signature, vaList);
        } catch (UnsupportedOperationException e) {
            return dvmClass.newObject("new");
        }
    }

    @Override
    public DvmObject<?> newObject(BaseVM base, DvmClass dvmClass, String signature, VarArg varArg) {
        log("new", signature);
        try {
            return super.newObject(base, dvmClass, signature, varArg);
        } catch (UnsupportedOperationException e) {
            return dvmClass.newObject("new");
        }
    }

    private void scanDex(String tag) {
        System.out.println("scan " + tag);
        try {
            for (MemoryMap map : emulator.getMemory().getMemoryMap()) {
                long size = map.size;
                if (size <= 0 || size > 64L * 1024 * 1024) {
                    continue;
                }
                byte[] data;
                try {
                    data = emulator.getBackend().mem_read(map.base, size);
                } catch (Exception e) {
                    continue;
                }
                dumpDexBytes(tag, "map-" + Long.toHexString(map.base), data);
                dumpElfBytes(tag, "map-" + Long.toHexString(map.base), data, map.base);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dumpElfBytes(String tag, String where, byte[] data, long base) {
        for (int i = 0; i + 4 < data.length; i++) {
            if (data[i] == 0x7f && data[i + 1] == 'E' && data[i + 2] == 'L' && data[i + 3] == 'F') {
                int fileSize = Math.min(data.length - i, 8 * 1024 * 1024);
                String key = "elf-" + tag + "-" + where + "-" + i;
                if (!dumpedKeys.add(key)) {
                    continue;
                }
                File out = new File(OUT, tag + "-" + where.replace('/', '_') + "-" + i + ".so");
                try (FileOutputStream fos = new FileOutputStream(out)) {
                    fos.write(data, i, fileSize);
                } catch (Exception e) {
                    System.out.println("elf dump fail " + out + " " + e);
                    continue;
                }
                System.out.println("DUMPED ELF " + out + " va=" + Long.toHexString(base + i)
                        + " size=" + fileSize);
            }
        }
    }

    private void dumpPtr(String tag, long ptr, int size) {
        if (ptr < 0x1000 || size <= 0 || size > 16 * 1024 * 1024) {
            System.out.println("skip dump " + tag + " ptr=" + Long.toHexString(ptr) + " size=" + size);
            return;
        }
        try {
            byte[] data = emulator.getBackend().mem_read(ptr, size);
            File out = new File(OUT, tag + "-" + Long.toHexString(ptr) + ".bin");
            try (FileOutputStream fos = new FileOutputStream(out)) {
                fos.write(data);
            }
            System.out.println("DUMPED " + out + " size=" + data.length
                    + " head=" + bytesToHex(java.util.Arrays.copyOf(data, Math.min(16, data.length))));
            dumpDexBytes(tag, Long.toHexString(ptr), data);
            dumpElfBytes(tag, Long.toHexString(ptr), data, ptr);
        } catch (Exception e) {
            System.out.println("dumpPtr fail " + tag + " " + e);
        }
    }

    private void dumpDexBytes(String tag, String where, byte[] data) {
        for (int i = 0; i + 8 < data.length; i++) {
            if (data[i] == 'd' && data[i + 1] == 'e' && data[i + 2] == 'x' && data[i + 3] == '\n') {
                int fileSize = (data[i + 32] & 0xff) | ((data[i + 33] & 0xff) << 8)
                        | ((data[i + 34] & 0xff) << 16) | ((data[i + 35] & 0xff) << 24);
                if (fileSize < 0x1000 || fileSize > data.length - i) {
                    fileSize = Math.min(data.length - i, 16 * 1024 * 1024);
                }
                String key = tag + "-" + where + "-" + i + "-" + fileSize;
                if (!dumpedKeys.add(key)) {
                    continue;
                }
                File out = new File(OUT, tag + "-" + where.replace('/', '_') + "-" + i + ".dex");
                try (FileOutputStream fos = new FileOutputStream(out)) {
                    fos.write(data, i, Math.min(fileSize, data.length - i));
                } catch (Exception e) {
                    System.out.println("dump fail " + out + " " + e);
                    continue;
                }
                System.out.println("DUMPED " + out + " off=" + i + " size=" + fileSize);
                dumpCount.incrementAndGet();
            }
        }
    }

    private void applyRelocs(Module mod) {
        byte[] img;
        try {
            img = Files.readAllBytes(SO.toPath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        applyTable(mod, img, SO_HDR + 0x2d1eb0, 0x948, false);
        applyTable(mod, img, SO_HDR + 0x2d12c8, 0xbe8, true);
        System.out.println("relocs applied");
    }

    private void applyTable(Module mod, byte[] img, int addr, int size, boolean jumps) {
        long base = mod.base;
        ByteBuffer bb = ByteBuffer.wrap(img).order(ByteOrder.LITTLE_ENDIAN);
        int n = size / 24;
        int ok = 0;
        for (int i = 0; i < n; i++) {
            bb.position(addr + i * 24);
            long off = bb.getLong();
            long info = bb.getLong();
            long add = bb.getLong();
            int type = (int) info;
            int sym = (int) (info >>> 32);
            try {
                if (type == 1027) {
                    writeLong(base + off, base + add);
                    ok++;
                } else if (type == 1026 || type == 1025 || type == 257) {
                    long resolved = resolveSym(img, base, sym, add);
                    if (resolved != 0) {
                        writeLong(base + off, resolved);
                        ok++;
                    } else {
                        System.out.println("unresolved type=" + type + " " + dynsymName(img, sym)
                                + " off=" + Long.toHexString(off));
                    }
                }
            } catch (Exception e) {
                System.out.println("reloc fail type=" + type + " off=" + Long.toHexString(off) + " " + e);
            }
        }
        System.out.println((jumps ? "jmp " : "rela ") + "applied " + ok + "/" + n);
    }

    private void writeLong(long addr, long value) {
        emulator.getBackend().mem_write(addr,
                ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(value).array());
    }

    private long resolveSym(byte[] img, long base, int sym, long add) {
        int shndx = dynsymShndx(img, sym);
        long value = dynsymValue(img, sym);
        if (shndx != 0 && value != 0) {
            return base + value + add;
        }
        String name = dynsymName(img, sym);
        if (name == null || name.isEmpty()) {
            return 0;
        }
        for (Module m : emulator.getMemory().getLoadedModules()) {
            try {
                Symbol s = m.findSymbolByName(name, false);
                if (s != null) {
                    return s.getAddress() + add;
                }
            } catch (Exception ignored) {
            }
        }
        return 0;
    }

    private int dynsymShndx(byte[] img, int index) {
        int symtab = SO_HDR + 0x2d0430;
        return Short.toUnsignedInt(ByteBuffer.wrap(img, symtab + index * 24 + 6, 2)
                .order(ByteOrder.LITTLE_ENDIAN).getShort());
    }

    private long dynsymValue(byte[] img, int index) {
        int symtab = SO_HDR + 0x2d0430;
        return ByteBuffer.wrap(img, symtab + index * 24 + 8, 8)
                .order(ByteOrder.LITTLE_ENDIAN).getLong();
    }

    private String dynsymName(byte[] img, int index) {
        int strtab = SO_HDR + 0x2d27f8;
        int symtab = SO_HDR + 0x2d0430;
        int nameOff = ByteBuffer.wrap(img, symtab + index * 24, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
        int start = strtab + nameOff;
        int end = start;
        while (end < img.length && img[end] != 0) {
            end++;
        }
        return new String(img, start, end - start);
    }

    public void run() {
        System.out.println("loading " + SO + " exists=" + SO.exists());
        DalvikModule dm = vm.loadLibrary(SO, false);
        System.out.println("loaded base=0x" + Long.toHexString(dm.getModule().base)
                + " size=0x" + Long.toHexString(dm.getModule().size));
        applyRelocs(dm.getModule());
        try {
            long init0 = dm.getModule().base + 0x2ad4acL;
            byte[] slot = emulator.getBackend().mem_read(dm.getModule().base + 0x2d1280L, 8);
            long slotVal = java.nio.ByteBuffer.wrap(slot).order(java.nio.ByteOrder.LITTLE_ENDIAN).getLong();
            System.out.println("init_array[0] mem=" + Long.toHexString(slotVal) + " expect=" + Long.toHexString(init0));
            System.out.println("calling relocated init at 0x2ad4ac...");
            Number iret = dm.getModule().callFunction(emulator, 0x2ad4acL);
            System.out.println("init 0x2ad4ac returned " + iret);
        } catch (Throwable t) {
            System.out.println("relocated init failed: " + t);
            t.printStackTrace();
        }
        scanDex("after-reloc-init");
        try {
            java.lang.reflect.Field f = dm.getModule().getClass().getField("initFunctionList");
            java.util.List<?> inits = (java.util.List<?>) f.get(dm.getModule());
            System.out.println("init_array count=" + inits.size());
            for (Object init : inits) {
                try {
                    java.lang.reflect.Method ga = init.getClass().getMethod("getAddress");
                    System.out.println("  init " + Long.toHexString(((Number) ga.invoke(init)).longValue()));
                } catch (Exception ignored) {
                }
            }
            java.lang.reflect.Method m = dm.getModule().getClass().getDeclaredMethod("callInitFunction", Emulator.class, boolean.class);
            m.setAccessible(true);
            System.out.println("calling init_array...");
            m.invoke(dm.getModule(), emulator, true);
            System.out.println("init_array done");
        } catch (Throwable t) {
            System.out.println("init_array failed: " + t);
            t.printStackTrace();
        }
        try {
            emulator.getBackend().mem_map(0, 0x10000, 7);
            System.out.println("mapped null page");
        } catch (Exception e) {
            System.out.println("null page: " + e.getMessage());
        }
        for (MemoryMap map : emulator.getMemory().getMemoryMap()) {
            try {
                emulator.getBackend().mem_protect(map.base, map.size, 7);
            } catch (Exception ignored) {
            }
        }
        final int[] traceCount = {0};
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                if (traceCount[0]++ > 80) {
                    return;
                }
                Number x17 = backend.reg_read(unicorn.Arm64Const.UC_ARM64_REG_X17);
                System.out.println(String.format("trace pc=%s x17=%s", Long.toHexString(address), Long.toHexString(x17.longValue())));
            }
            @Override
            public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {
            }
            @Override
            public void detach() {
            }
        }, 0x402b0fe0L, 0x402b1200L, null);
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                if (traceCount[0]++ > 120) {
                    return;
                }
                System.out.println("trace2 pc=" + Long.toHexString(address));
            }
            @Override
            public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {
            }
            @Override
            public void detach() {
            }
        }, 0x402ade00L, 0x402ae200L, null);
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                Number w0 = backend.reg_read(unicorn.Arm64Const.UC_ARM64_REG_W0);
                System.out.println(String.format("chk pc=%s w0=%s", Long.toHexString(address), Long.toHexString(w0.longValue() & 0xffffffffL)));
            }
            @Override
            public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {
            }
            @Override
            public void detach() {
            }
        }, 0x402ad77cL, 0x402ad780L, null);
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                Number w0 = backend.reg_read(unicorn.Arm64Const.UC_ARM64_REG_W0);
                System.out.println(String.format("failpath pc=%s w0=%s", Long.toHexString(address), Long.toHexString(w0.longValue() & 0xffffffffL)));
            }
            @Override
            public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {
            }
            @Override
            public void detach() {
            }
        }, 0x402adb18L, 0x402adb30L, null);
        long[] checks = {0x402ad7f8L, 0x402ad838L, 0x402ad8c0L, 0x402ad960L, 0x402ada5cL, 0x402adae4L};
        for (long c : checks) {
            final long addr = c;
            emulator.getBackend().hook_add_new(new CodeHook() {
                @Override
                public void hook(Backend backend, long address, int size, Object user) {
                    Number w0 = backend.reg_read(unicorn.Arm64Const.UC_ARM64_REG_W0);
                    System.out.println(String.format("crypto chk %s w0=%s", Long.toHexString(addr), Long.toHexString(w0.longValue() & 0xffffffffL)));
                }
                @Override
                public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {
                }
                @Override
                public void detach() {
                }
            }, addr, addr + 4, null);
        }
        System.out.println("maps after load:");
        for (MemoryMap map : emulator.getMemory().getMemoryMap()) {
            System.out.println(String.format("  %s-%s prot=%s sz=0x%x",
                    Long.toHexString(map.base),
                    Long.toHexString(map.base + map.size),
                    Integer.toHexString(map.prot),
                    map.size));
        }
        scanDex("after-load");
        try {
            System.out.println("JNI_OnLoad...");
            dm.callJNI_OnLoad(emulator);
            System.out.println("JNI_OnLoad returned");
            scanAscii("interface5");
            scanAscii("StubApp");
            scanAscii("RegisterNatives");
            scanAscii("dex\n");
            scanAscii("com.deye");
            scanAscii("combo");
            long bss = dm.getModule().base + 0xf474cL;
            byte[] bssData = emulator.getBackend().mem_read(bss, 0x4000);
            System.out.println("bss head=" + bytesToHex(java.util.Arrays.copyOf(bssData, 64)));
            int nonzero = 0;
            byte[] bssAll = emulator.getBackend().mem_read(bss, Math.min(0x1b4054L, 2L * 1024 * 1024));
            for (byte b : bssAll) {
                if (b != 0) nonzero++;
            }
            System.out.println("bss nonzero=" + nonzero + "/" + bssAll.length);
            long g = dm.getModule().base + 0x2cc0a0L;
            byte[] glob = emulator.getBackend().mem_read(g, 0x60);
            System.out.println("global+0x2cc0a0=" + java.util.Arrays.toString(glob));
            java.nio.ByteBuffer gb = java.nio.ByteBuffer.wrap(glob).order(java.nio.ByteOrder.LITTLE_ENDIAN);
            gb.position(0x30);
            long table = gb.getLong();
            System.out.println("table ptr=" + Long.toHexString(table));
            if (table > 0x1000) {
                byte[] tb = emulator.getBackend().mem_read(table, 0x80);
                System.out.println("table=" + bytesToHex(tb));
            }
            System.out.println("maps after JNI_OnLoad:");
            for (MemoryMap map : emulator.getMemory().getMemoryMap()) {
                System.out.println(String.format("  %s-%s prot=%s sz=0x%x",
                        Long.toHexString(map.base), Long.toHexString(map.base + map.size),
                        Integer.toHexString(map.prot), map.size));
            }
            dumpVtable(dm.getModule().base);
            fixFirstPltGot(dm.getModule().base);
            hookStage2(dm.getModule().base);
            System.out.println("calling 0x8b40 (payload loader)...");
            Number loaded = callWithTimeout(() -> dm.getModule().callFunction(emulator, 0x8b40L), 20000);
            System.out.println("0x8b40 returned " + loaded);
            if (loaded != null) {
                long img = loaded.longValue();
                System.out.println("loader image=" + Long.toHexString(img));
                if (img > 0x1000) {
                    dumpPtr("loader-obj", img, 0x220);
                }
            }
            scanDex("after-8b40");
            long g2 = dm.getModule().base + 0x2cc0a0L;
            byte[] glob2 = emulator.getBackend().mem_read(g2, 0x60);
            java.nio.ByteBuffer gb2 = java.nio.ByteBuffer.wrap(glob2).order(java.nio.ByteOrder.LITTLE_ENDIAN);
            gb2.position(0x30);
            System.out.println("table ptr after 8b40=" + Long.toHexString(gb2.getLong()));
            System.out.println("calling __arm_a_1...");
            MemoryBlock arg = emulator.getMemory().malloc(8, false);
            emulator.getBackend().mem_write(arg.getPointer().peer,
                    ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putInt(0).array());
            Number a1 = callWithTimeout(() -> dm.getModule().callFunction(
                    emulator, 0xa6e8L, vm.getJavaVM(), 0, 0, arg.getPointer().peer), 25000);
            System.out.println("__arm_a_1 returned " + a1);
            scanDex("after-arm-a-1");
            System.out.println("force call 0x2ad66c (real init)...");
            Number real = callWithTimeout(() -> dm.getModule().callFunction(
                    emulator, 0x2ad66cL, vm.getJavaVM(), 0), 15000);
            System.out.println("0x2ad66c returned " + real);
            scanDex("after-real-init");
        } catch (Throwable t) {
            System.out.println("JNI_OnLoad failed: " + t);
            t.printStackTrace();
            System.out.println("maps after crash:");
            for (MemoryMap map : emulator.getMemory().getMemoryMap()) {
                System.out.println(String.format("  %s-%s prot=%s sz=0x%x",
                        Long.toHexString(map.base),
                        Long.toHexString(map.base + map.size),
                        Integer.toHexString(map.prot),
                        map.size));
            }
        }
        scanDex("after-onload");
        for (String name : new String[]{"JNI_OoLoad", "JNI_OnLoad"}) {
            Symbol sym = dm.getModule().findSymbolByName(name, false);
            System.out.println("symbol " + name + " => " + sym);
        }
        try {
            System.out.println("JNI_OoLoad...");
            Number ret = dm.getModule().callFunction(emulator, 0x96bc, vm.getJavaVM(), 0);
            System.out.println("JNI_OoLoad returned " + ret);
        } catch (Throwable t) {
            System.out.println("JNI_OoLoad failed: " + t);
            t.printStackTrace();
        }
        scanDex("after-ooload");
        try {
            System.out.println("DynCryptor...");
            Number ret = dm.getModule().callFunction(emulator, 0x74bc);
            System.out.println("DynCryptor returned " + ret);
        } catch (Throwable t) {
            System.out.println("DynCryptor failed: " + t);
        }
        scanDex("after-dyncrypt");
        DvmClass stub = vm.resolveClass("com/stub/StubApp");
        try {
            System.out.println("interface5...");
            stub.callStaticJniMethod(emulator, "interface5(Landroid/app/Application;)V", application());
            System.out.println("interface5 returned");
        } catch (Throwable t) {
            System.out.println("interface5 failed: " + t);
            t.printStackTrace();
        }
        scanDex("after-interface5");
        System.out.println("done dumps=" + dumpCount.get());
    }

    private long findLibc(String name) {
        for (Module m : emulator.getMemory().getLoadedModules()) {
            try {
                Symbol s = m.findSymbolByName(name, false);
                if (s != null) {
                    System.out.println("libc " + name + " => " + Long.toHexString(s.getAddress())
                            + " via " + m.name);
                    return s.getAddress();
                }
            } catch (Exception ignored) {
            }
        }
        System.out.println("libc missing " + name);
        return 0;
    }

    private void fixFirstPltGot(long base) {
        long malloc = findLibc("malloc");
        long calloc = findLibc("calloc");
        long memcpy = findLibc("memcpy");
        long free = findLibc("free");
        long sysconf = findLibc("sysconf");
        long memset = findLibc("memset");
        long stackFail = findLibc("__stack_chk_fail");
        long inflate = findLibc("inflate");
        long inflateInit = findLibc("inflateInit_");
        long inflateEnd = findLibc("inflateEnd");
        // First-segment JMPREL names do not match how the loader calls these slots.
        long[][] patches = {
                {0x2fd30L, malloc},      // 0x2340 used as malloc
                {0x2fdf8L, calloc != 0 ? calloc : malloc}, // 0x24d0 used as calloc(n, size)
                {0x2fd58L, memcpy},      // 0x2390 used as memcpy
                {0x2fda0L, memcpy},      // 0x2420 memcpy
                {0x2fdc0L, free},        // 0x2460 used as free
                {0x2ff38L, free},        // 0x2750 free
                {0x2fd00L, sysconf},     // 0x22e0 used as sysconf
                {0x2fe98L, memset},      // 0x2610 memset
                {0x2fde0L, stackFail},   // 0x24a0 canary
                {0x2fe10L, inflate},     // 0x2500
                {0x2fcf8L, inflateInit}, // 0x22d0
                {0x2ff10L, inflateEnd},  // 0x2700
                {0x2fdf0L, inflateInit}, // 0x24c0 used as inflateInit_
                {0x2fd38L, inflate},     // 0x2350 used as inflate
        };
        for (long[] p : patches) {
            if (p[1] == 0) {
                System.out.println("skip GOT " + Long.toHexString(p[0]));
                continue;
            }
            writeLong(base + p[0], p[1]);
            System.out.println("GOT " + Long.toHexString(base + p[0]) + " => " + Long.toHexString(p[1]));
        }
    }

    private void dumpVtable(long base) {
        try {
            byte[] vt = emulator.getBackend().mem_read(base + 0x2fa00L, 0x20);
            ByteBuffer bb = ByteBuffer.wrap(vt).order(ByteOrder.LITTLE_ENDIAN);
            System.out.println("vtable 0x2fa00:");
            for (int i = 0; i < 4; i++) {
                long slot = bb.getLong();
                System.out.println("  +" + Integer.toHexString(i * 8) + " " + Long.toHexString(slot)
                        + " off=" + Long.toHexString(slot > base ? slot - base : slot));
            }
            long expect = base + 0x6ac4L;
            bb.rewind();
            bb.position(0x18);
            long decrypt = bb.getLong();
            if (decrypt != expect) {
                System.out.println("FIX vtable+0x18 -> " + Long.toHexString(expect));
                writeLong(base + 0x2fa18L, expect);
            }
        } catch (Exception e) {
            System.out.println("dumpVtable " + e);
        }
    }

    private void hookStage2(long base) {
        hookPc(base + 0x6ac4L, "decrypt_6ac4");
        hookPc(base + 0x6b18L, "after_key_6bac");
        hookPc(base + 0x6b2cL, "after_xor_66b0");
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                Number x20 = backend.reg_read(unicorn.Arm64Const.UC_ARM64_REG_X20);
                try {
                    byte[] head = backend.mem_read(x20.longValue(), 16);
                    System.out.println("xor head @ " + Long.toHexString(x20.longValue())
                            + " " + bytesToHex(head));
                } catch (Exception e) {
                    System.out.println("xor head fail " + e);
                }
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, base + 0x6b2cL, base + 0x6b30L, null);
        hookPc(base + 0x6b54L, "before_inflate");
        hookPc(base + 0x6b90L, "decrypt_ok");
        hookPc(base + 0x51b0L, "linker_51b0");
        hookPc(base + 0x51fcL, "vt_call");
        hookPc(base + 0x5048L, "map_5048");
        hookPc(base + 0x5ec8L, "parse_5ec8");
        hookPc(base + 0x5a8cL, "after_5ec8");
        hookPc(base + 0x61b0L, "mapwin_61b0");
        hookPc(base + 0x5ab0L, "after_61b0");
        hookPc(base + 0x5b3cL, "copy_5b3c");
        hookPc(base + 0x5ad0L, "after_5b3c");
        hookPc(base + 0x3d68L, "map_3d68");
        hookPc(base + 0x4eb4L, "reloc_4eb4");
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                Number x0 = backend.reg_read(unicorn.Arm64Const.UC_ARM64_REG_X0);
                Number x1 = backend.reg_read(unicorn.Arm64Const.UC_ARM64_REG_X1);
                Number x19 = backend.reg_read(unicorn.Arm64Const.UC_ARM64_REG_X19);
                Number x20 = backend.reg_read(unicorn.Arm64Const.UC_ARM64_REG_X20);
                Number x21 = backend.reg_read(unicorn.Arm64Const.UC_ARM64_REG_X21);
                System.out.println(String.format("decrypt_ok x0=%s x19=%s x20=%s x21=%s",
                        Long.toHexString(x0.longValue()), Long.toHexString(x19.longValue()),
                        Long.toHexString(x20.longValue()), Long.toHexString(x21.longValue())));
                try {
                    byte[] obj = backend.mem_read(x19.longValue(), 0x40);
                    ByteBuffer bb = ByteBuffer.wrap(obj).order(ByteOrder.LITTLE_ENDIAN);
                    bb.position(0x18);
                    long ptr = bb.getLong();
                    long sz = bb.getLong();
                    System.out.println("decrypted ptr=" + Long.toHexString(ptr) + " size=" + sz);
                    dumpPtr("decrypted", ptr, (int) Math.min(sz, 8L * 1024 * 1024));
                } catch (Exception e) {
                    System.out.println("decrypt dump " + e);
                }
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, base + 0x6b90L, base + 0x6b94L, null);
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                Number x8 = backend.reg_read(unicorn.Arm64Const.UC_ARM64_REG_X8);
                Number x0 = backend.reg_read(unicorn.Arm64Const.UC_ARM64_REG_X0);
                System.out.println("vt_call x0=" + Long.toHexString(x0.longValue())
                        + " x8=" + Long.toHexString(x8.longValue()));
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, base + 0x51fcL, base + 0x5200L, null);
    }

    private void hookPc(long addr, String name) {
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                Number x0 = backend.reg_read(unicorn.Arm64Const.UC_ARM64_REG_X0);
                Number x1 = backend.reg_read(unicorn.Arm64Const.UC_ARM64_REG_X1);
                Number x2 = backend.reg_read(unicorn.Arm64Const.UC_ARM64_REG_X2);
                Number x3 = backend.reg_read(unicorn.Arm64Const.UC_ARM64_REG_X3);
                System.out.println(String.format("%s pc=%s x0=%s x1=%s x2=%s x3=%s",
                        name, Long.toHexString(address),
                        Long.toHexString(x0.longValue()), Long.toHexString(x1.longValue()),
                        Long.toHexString(x2.longValue()), Long.toHexString(x3.longValue())));
            }
            @Override public void onAttach(com.github.unidbg.arm.backend.UnHook unHook) {}
            @Override public void detach() {}
        }, addr, addr + 4, null);
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

    private void scanAscii(String needle) {
        byte[] n = needle.getBytes();
        int hits = 0;
        for (MemoryMap map : emulator.getMemory().getMemoryMap()) {
            if (map.size <= 0 || map.size > 64L * 1024 * 1024) continue;
            byte[] data;
            try {
                data = emulator.getBackend().mem_read(map.base, map.size);
            } catch (Exception e) {
                continue;
            }
            for (int i = 0; i + n.length < data.length; i++) {
                boolean ok = true;
                for (int j = 0; j < n.length; j++) {
                    if (data[i + j] != n[j]) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    System.out.println("ASCII " + needle + " at " + Long.toHexString(map.base + i));
                    hits++;
                    if (hits >= 8) return;
                }
            }
        }
        System.out.println("ASCII " + needle + " hits=" + hits);
    }

    private static String bytesToHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new JiaguDump().run();
    }
}
