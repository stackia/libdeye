# Unidbg 360 Jiagu dumper

当时用来跑通 `libjiagu` `JNI_OnLoad` 并从内存 dump DEX 的 Maven 工程。

- `unidbg-android` 0.9.8
- Java 11
- 主类：`dump.Stage2Dump`（`JiaguDump.java` 是更早的第一段实验）

硬编码路径：

| 常量 | 路径 |
| --- | --- |
| `APK` | `/tmp/deye-apk/com.deye_4.2.1.apk` |
| `FLAT` | `/tmp/deye-apk/native/stage2_flat.bin` |
| `OUT` | `/tmp/deye-apk/unidbg-dump` |
| `DATA` | `/tmp/deye-apk/unidbg-fs/data/data/com.deye` |

`SafeAndroidEmulator.java` 绕开 Unidbg 在这套加固 SO 上的重定位死循环。`Stage2Dump.java` 里还有：

- 用 Java 实现三个 JIT syscall 蹦床生成器（Unicorn 指令缓存不会因 `ic ivau` 失效）
- 整包 APK mmap 到 `0x70000000` 以便 EOCD 查找 `classes.dex`
- 过期的 `protect-time` 30 天窗 NOP
- PKCS#7 `META-INF/DEYE.RSA` 作为 `Signature.toByteArray()` 返回值

没有 `stage2_flat.bin` 时不要指望能重跑；协议分析用仓库里已经 dump 好的 DEX。
