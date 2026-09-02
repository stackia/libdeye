# Deye Smart 4.2.1 reverse-engineering

Compare `libdeye` with the official Deye Smart Android app (`com.deye` 4.2.1,
versionCode 141). The app is not open source. Java under `unpacked/jadx` is
JADX output from dumped DEX. Copyright remains with Zhejiang Deye. Do not
redistribute it as an app source tree.

The APK (~106 MB), DEX dumps, qh blobs, and native packer images are **not**
in git. This directory keeps the two scripts that reproduce the path that
worked, plus the JADX tree already recovered.

## Successful path

Two scripts. Run them from this directory:

```bash
./apk/download-apk.sh    # Yingyongbao APK → apk/com.deye_4.2.1.apk (gitignored)
./decompile-apk.sh       # APK → control-panel JSON + DEX dump + JADX
```

That is the whole working pipeline:

1. **Download the APK** from Tencent Yingyongbao and check MD5
   `F2201AC1CA9EB94218C8990008D1E476`.
2. **Extract `assets/control_panel/dehumidifier/*.json`.** These files are
   plaintext in the APK. They define product UI capabilities, not IoT
   transport. Every dehumidifier JSON uses `deviceControlStrategy: "wan"`.
3. **Dump the real DEX from packer memory with Unidbg.** Plaintext
   `apktool` / JADX only see the 360 Jiagu VIP 1.4.0.5 stub
   (`com.stub.StubApp`). Business code is decrypted at runtime and loaded
   with `InMemoryDexClassLoader`. Offline qh/RC4 guessing never produced
   `dex\n`. The dump that worked is `tools/unidbg` (`dump.Stage2Dump`)
   after `JNI_OnLoad`.
4. **JADX the business DEX** (`classes3.dex` + `classes6.dex` +
   `classes7.dex`) and keep `com.deye` + `io.fogcloud`.

`./decompile-apk.sh` performs steps 2–4. It reuses an existing DEX dump
when present. Pass `--update-tree` to copy JADX output into the committed
`unpacked/jadx` and `unpacked/jadx-protocol` trees.

| DEX (from Unidbg memory) | Role |
| --- | --- |
| `classes3.dex` | `com.deye` (`CommandManger`, `FogDeviceManager`) |
| `classes7.dex` | `io.fogcloud` FogCloud SDK |
| `classes6.dex` | leftover `com.deye` |

`libdeye` should follow those classes. Home Assistant should stay a thin
caller.

## Layout

```
reverse-engineering/
  README.md                 this file
  decompile-apk.sh          successful decompile path (JSON + Unidbg + JADX)
  apk/download-apk.sh       Yingyongbao download + MD5
  apk/appdetail.json        captured apkUrl / apkMd5 (CDN URLs rotate)
  tools/unidbg/             Unidbg dumper that recovered the DEX
  tools/brute_dex_key.py    failed offline qh/RC4 guesses
  tools/try_qh_decrypt.py   failed offline qh/RC4 guesses
  unpacked/jadx/            committed JADX: com.deye + io.fogcloud
  unpacked/jadx-protocol/   protocol classes copied for easier comparison
  unpacked/control_panel/   plaintext product-panel JSON
```

## Official protocol

Source: `CommandManger`, `FogDeviceManager`, `DeviceListBean`,
`DeYeMqttManager`, `DeYeFogMqttManager`, `SubscribeDeviceStates`.

Send methods take `(…, boolean isFog, boolean isCombo, DehumidifierBean bean)`:

```
if (isFog)       → Fog HTTP POST set/properties
else if (isCombo) → Classic MQTT {2, 17, cmd, value}
else             → Classic 10-byte MQTT
```

| Flag | Official test |
| --- | --- |
| `isFog` | `DeviceListBean.isFogPlatform()` → `platform == 2 \|\| platform == 3` |
| `isCombo` | `DeviceListBean.isUseComboProtocol()` → `is_combo && protocol_version == "combo_V1.0"` |

Fog is checked first. **Platform 2 and 3 both use Fog HTTP.** Combo frames
run only when Fog is false (typically platform 1 + combo flags). There is
no product-id branch on the send path.

**Fog**

- Send: HTTP POST `set/properties` (`DeYeFogMqttManager.sendSingleMsg` is
  not an MQTT publish).
- Query: HTTP GET `enduser/get/properties/?device_id=...&random=...`.
  Official Retrofit also sends `random` on `fogmqttinfo/`.
- Receive: MQTT `fogcloud/app/{username}/sub` (`device_data` /
  `device_status`) only. `SubscribeDeviceStates.registerDevice` returns
  immediately for Fog and does not subscribe Classic `status/hex`.
- `FogDeviceManager.checkNeedAll`: cached `ProtocolVersion == 0` sends
  that command's companion keys from the cached bean (not every key).
  `sendPowerCommand` omits `SwingingWind`; humidity omits `Sleep` /
  `SetTemperature`; display, tone, and timer never snapshot. Missing/null
  cache is partial. GET reports `TimedShutdownHourSetting`; the send key
  is `TimedOffHour`.
- `setPollFogProperties` POSTs `{RealData: 1}` on `set/properties`.
  `FogDevicePollingTask` repeats this every 3s while a Fog panel is open
  so the device publishes fresh MQTT `thing_property`. It is not a GET.

**Classic** (platform 1, not Combo)

- Send: JSON `DehumidifierBean` → `DehumidifierBeanString2Hex` → 10-byte
  MQTT on `{endpoint}/{productId}/{deviceId}/command/hex`.
- Query: `b"\x00\x01"` on that topic (`QUERY_DEVICE_STATE`). The App
  `DeviceHeartbeatTask` repeats this every 10s for Classic devices on
  the home list; it is a state poll, not an MQTT keepalive. `b"\x00\x04"`
  (`DEVICE_STATE_CHANGE_COMMAND`) exists in `DeviceStateChangeTrigger`
  but has no callers in 4.2.1.
- Receive: `status/hex` + `online/json`.
- The topic suffix is still `StubApp.getString2(44821)` in the APK. Live
  traffic is `/command/hex` and `/status/hex`.

**Combo** (`isUseComboProtocol()`, not platform 3)

`sendSingleCommand` wraps `{17, cmd, value}` as `{2, 17, cmd, value}` and
publishes through Classic `DeYeMqttManager`. Query stays `b"\x00\x01"`.
Receive stays Classic `status/hex`. `ComboManager` is BLE bind, not WAN.

| Property | cmd |
| --- | --- |
| Power | 1 |
| Oscillating | 2 |
| Child lock | 3 |
| Water pump | 6 |
| Anion | 7 |
| Mode | 8 |
| Fan | 9 |
| Humidity or temp | 10 |
| Sleep | 15 |

360 encrypts many literals as `StubApp.getString2(id)`. Method names, JSON
property keys, Retrofit paths, and Combo opcodes are plaintext.

## Failed attempts

Do not treat these as the decompile path. They are kept so the same work
is not repeated.

| Attempt | Result |
| --- | --- |
| `apktool d` / JADX on the APK's plaintext `classes.dex` | Packer stub only. No `com.deye.CommandManger`. |
| Hermes `assets/index.android.bundle` | UI only. Protocol is Java. |
| `tools/brute_dex_key.py`, `tools/try_qh_decrypt.py` | qh/RC4/MD5 guesses never produced `dex\n`. |
| BlackDex / Frida | Downloaded; no Android emulator/device on the analysis VM. |
| `libX86Bridge.so` | Packer support SO. Not the DEX dump path. |
| First-stage `dump.JiaguDump` | Intermediate. The dump that produced DEX is `dump.Stage2Dump`. |

The qh tail (plaintext `classes.dex` offset 23996) is encrypted config plus
nine business blobs (`fastLevel=1` = decrypt + ZSTD). That crypto was not
broken offline. DEX came from the Unidbg memory dump after the loader ran.

## Re-running the Unidbg dump

`decompile-apk.sh` calls this automatically when `classes3.dex` is missing
and `/tmp/deye-apk/native/stage2_flat.bin` is present. The flattened
stage-2 image is large and is not in git (and `*.so` is gitignored).

`tools/unidbg` (`unidbg-android` 0.9.8, Java 11) expects:

```
/tmp/deye-apk/com.deye_4.2.1.apk
/tmp/deye-apk/native/stage2_flat.bin
/tmp/deye-apk/unidbg-dump/          # output
```

Pitfalls that made `Stage2Dump` work:

- Rebuild the inner ELF with the ELF header at file offset 0 and code at
  vaddr 0. Set `DT_RELASZ` / `DT_PLTRELSZ` to 0 or Unidbg relocation loops.
- `Signature.toByteArray()` must return the full PKCS#7 `META-INF/DEYE.RSA`
  (1085 bytes), not X509 DER.
- Map the whole APK at `0x70000000` so ZIP EOCD can find `classes.dex`.
- Replace the three JIT `svc` trampoline generators in Java. Unicorn does
  not invalidate its TB cache on `ic ivau`.
- NOP the expired 30-day `protect-time` window (`protect-time=2026-06-04`).

SHA256 of the 2026-09-02 DEX dump (write under `/tmp/deye-apk/unidbg-dump/dex/`):

| File | SHA256 |
| --- | --- |
| `classes.dex` | `bb53b4b5f3fa2512ced842163d5171212de30b7902a3a8ea34888ad16dc84d85` |
| `classes2.dex` | `ebb3f352471953d3c4ffff309dcb4270f351be1122629fcddcb64cf6f1da7fdf` |
| `classes3.dex` | `f264983bda6d4bb927f623f238f74ba40236add1ad0cbc4f1aa32e79141d6d24` |
| `classes4.dex` | `c6679d138870f92c3a8f6da6419d8def3de41006c1b3632a634b887866597e1e` |
| `classes5.dex` | `a9d4f5219d614e246c5b8ef96af2ac63516009ce05c9707a74392a7d0f8f7da5` |
| `classes6.dex` | `c43421ee253a74391340024f460f869b572152842960665942b6050c90a01273` |
| `classes7.dex` | `79c5ba8b242c124744c0ef6f327fe9bc7f8acc3ac806a84d5561f836005f35ce` |
| `classes8.dex` | `98fbbd7e1d926fe5d348a8c8fb5eebe5895c18eaa646e451ae1176e44b29d2a7` |
| `classes9.dex` | `845591962c59a755e96335800feca2a4da4e7880568cb9cefd9067a26e20b94d` |

Protocol work can use the Java already under `unpacked/jadx/` without
re-running Unidbg.
