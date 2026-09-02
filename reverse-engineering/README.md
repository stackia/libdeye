# Deye Smart Android reverse-engineering

This directory holds the unpack process, tools, notes, and decompiled
business sources for **Deye Smart Android `com.deye` 4.2.1** (versionCode 141).
Use it to compare `libdeye` against the official send/receive path.

The official app is not open source. Java under `unpacked/jadx` is JADX output
from dumped DEX. Copyright remains with Zhejiang Deye. Do not redistribute it
as an app source tree.

DEX files, qh blobs, native images, and the APK itself are **not** in git.
Download the APK and dump DEX locally if you need to re-run JADX.

## Do not commit the APK

The APK is about **106.4 MB** (111,556,698 bytes), over GitHub's 100 MB file
limit. This repo only keeps download metadata and a script.

| Item | Value |
| --- | --- |
| Package | `com.deye` |
| App name | 德业智能 (Deye Smart) |
| Version | 4.2.1 (versionCode 141) |
| Channel | Tencent Yingyongbao |
| Detail page | https://a.app.qq.com/o/simple.jsp?pkgname=com.deye |
| APK MD5 | `F2201AC1CA9EB94218C8990008D1E476` |
| Signing cert SHA1 (`META-INF/DEYE.RSA` PKCS#7) | `0caaab3d2a6a16552e44be847d825efb836d55f8` |

Download:

```bash
./reverse-engineering/apk/download-apk.sh
```

The script writes `reverse-engineering/apk/com.deye_4.2.1.apk` (gitignored)
and checks the MD5.

## Layout

```
reverse-engineering/
  README.md                 this file
  NOTES-unpack.md           360 Jiagu unpack notes, pitfalls, addresses
  NOTES-protocol.md         Classic / Fog / Combo send and receive
  NOTICE.md                 copyright notice for JADX output
  apk/                      Yingyongbao metadata and download script
  tools/                    Unidbg dumper and offline key-guess scripts
  unpacked/
    jadx/                   JADX: com.deye + io.fogcloud
    jadx-protocol/          protocol classes copied for easier comparison
    control_panel/          plaintext product-panel JSON from the APK
```

Business DEX (dump locally with Unidbg; not in git):

- `classes3.dex` — `com.deye` (`CommandManger`, `FogDeviceManager`)
- `classes7.dex` — `io.fogcloud` FogCloud SDK
- `classes6.dex` — a small amount of leftover `com.deye`

Re-decompile after a local DEX dump:

```bash
./reverse-engineering/unpacked/decompile-jadx.sh
```

## Tools (short version)

Full steps: [`NOTES-unpack.md`](NOTES-unpack.md).

1. Download the APK from Yingyongbao and verify MD5.
2. `apktool d` only shows the 360 packer stub (`com.stub.StubApp`, `eyed.moc`,
   `libjiagu_vip*.so`). Business Java is not in the plaintext `classes.dex`.
3. UI is React Native + Hermes bytecode. Product-panel JSON lives in
   `assets/control_panel/dehumidifier/` and is copied here in plaintext.
4. Unidbg (`tools/unidbg`, `unidbg-android` 0.9.8) loads 360 Jiagu VIP
   **1.4.0.5** inner ELF / stage-2, runs `JNI_OnLoad`, and dumps nine real DEX
   files from memory.
5. JADX 1.5.3 decompiles the dumped DEX. Protocol routing is `CommandManger`.

360 encrypts many string literals as `StubApp.getString2(id)`. Method names,
`PropertyParam` JSON fields, Retrofit paths, and Combo opcodes are plaintext.
The Classic command topic suffix is still `getString2(44821)` in the APK;
account traffic uses `/command/hex` and `/status/hex`.

## Protocol (one paragraph)

Official `CommandManger`: `if isFog → Fog HTTP; else if isCombo → Classic MQTT
{2,17,cmd,value}; else Classic 10-byte MQTT`. `isFog` is
`platform == 2 || platform == 3`, so **platform 3 uses Fog HTTP in the official
app**. Combo bytes are sent only when Fog is false and
`is_combo && protocol_version == combo_V1.0`. See
[`NOTES-protocol.md`](NOTES-protocol.md).
