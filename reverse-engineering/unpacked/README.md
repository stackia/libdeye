# Unpacked artifacts

## `jadx/`

JADX 1.5.3 output from dumped `classes3.dex` + `classes6.dex` + `classes7.dex`,
keeping only:

- `com/deye/` — Deye business (`CommandManger`, `FogDeviceManager`,
  `MxchipApplication`, combo, …)
- `io/fogcloud/` — FogCloud SDK

OkHttp / Kotlin / React Native third-party decompilation is not in git.

To regenerate (needs `jadx` on PATH and a local DEX dump):

```bash
./decompile-jadx.sh
```

By default the script reads DEX from `/tmp/deye-apk/unidbg-dump/dex`.

## `jadx-protocol/`

Protocol classes copied out so comparison with `libdeye` does not require
walking the full JADX tree:

`CommandManger`, `FogDeviceManager`, `DeYeMqttManager`, `DeYeFogMqttManager`,
`DeYeHttpRequestManager`, `ComboManager`, `DehumidifierBean`, `PropertyParam`,
`RetrofitService`, `UnifiedMqttService`, and related types.

## `control_panel/dehumidifier/`

Plaintext copy of APK `assets/control_panel/dehumidifier/*.json`. Product
capabilities live here. IoT platform routing does not.

DEX dumps and qh blobs are not stored in this repository. See
[`../NOTES-unpack.md`](../NOTES-unpack.md).
