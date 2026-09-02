# Unpack tools

The Unidbg Java dumper expects the same working-directory layout used during
the original dump:

```
/tmp/deye-apk/com.deye_4.2.1.apk
/tmp/deye-apk/native/stage2_flat.bin
/tmp/deye-apk/unidbg-dump/          # output directory (DEX, not committed)
```

Symlink the APK from this repo after running `apk/download-apk.sh`:

```bash
mkdir -p /tmp/deye-apk
ln -sf "$(pwd)/reverse-engineering/apk/com.deye_4.2.1.apk" /tmp/deye-apk/com.deye_4.2.1.apk
```

`stage2_flat.bin` is the second-stage image extracted from the packer SO. It
is not in git. Without it, `Stage2Dump` cannot be re-run. Protocol analysis
can use the JADX sources under [`../unpacked/jadx/`](../unpacked/jadx/).

## Unidbg (path that worked)

Maven project: [`unidbg/`](unidbg/), depends on `unidbg-android` 0.9.8.

```bash
cd reverse-engineering/tools/unidbg
mvn -q -DskipTests exec:java
```

Main class `dump.Stage2Dump`: stub the JNI environment, map the APK, run
`JNI_OnLoad`, write DEX from memory. Paths are hardcoded in the Java sources
(`/tmp/deye-apk/...`).

## Offline key guessing (did not work)

`brute_dex_key.py` and `try_qh_decrypt.py` also hardcode `/tmp/deye-apk/...`.
They tried qh/RC4/MD5 variants and never produced `dex\n`. Real DEX came from
the Unidbg dump, not these scripts.

## Downloaded but unused

The cloud VM had no Android emulator or device, so these were not run:

- BlackDex
- Frida server (~295 MB, not in git)
- Repacked/signed APK (`apksigner` + debug keystore)
