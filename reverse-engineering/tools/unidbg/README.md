# Unidbg 360 Jiagu dumper

Maven project used to run `libjiagu` `JNI_OnLoad` and dump DEX from memory.

- `unidbg-android` 0.9.8
- Java 11
- Main class: `dump.Stage2Dump` (`JiaguDump.java` is an earlier first-stage
  experiment)

Hardcoded paths:

| Constant | Path |
| --- | --- |
| `APK` | `/tmp/deye-apk/com.deye_4.2.1.apk` |
| `FLAT` | `/tmp/deye-apk/native/stage2_flat.bin` |
| `OUT` | `/tmp/deye-apk/unidbg-dump` |
| `DATA` | `/tmp/deye-apk/unidbg-fs/data/data/com.deye` |

`SafeAndroidEmulator.java` avoids Unidbg's relocation loop on this packer SO.
`Stage2Dump.java` also:

- Implements three JIT syscall trampoline generators in Java (Unicorn's
  instruction cache is not invalidated by `ic ivau`)
- mmaps the whole APK at `0x70000000` so EOCD can find `classes.dex`
- NOPs the expired 30-day `protect-time` window
- Returns PKCS#7 `META-INF/DEYE.RSA` from `Signature.toByteArray()`

Do not expect a re-run without `stage2_flat.bin`. Protocol analysis uses the
JADX sources already in this repo.
