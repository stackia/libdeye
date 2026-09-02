# 解包产物

## `dex/`

360 加固解密后、从 Unidbg 内存写出的 9 个 DEX。这是 JADX 的输入。SHA256 见 `dex/SHA256SUMS`。

业务集中在 `classes3.dex`（`com.deye`）和 `classes7.dex`（`io.fogcloud`）。

## `jadx/`

对 `classes3.dex` + `classes6.dex` + `classes7.dex` 跑 JADX 1.5.3 后，只保留：

- `com/deye/` — 德业业务（`CommandManger`、`FogDeviceManager`、`MxchipApplication`、combo 等）
- `io/fogcloud/` — FogCloud SDK

没有收录 OkHttp / Kotlin / React Native 等第三方反编译结果。

重新生成（需要本机有 `jadx`）：

```bash
./decompile-jadx.sh
```

## `jadx-protocol/`

对照 `libdeye` 时单独拷出的协议类，避免在整包 JADX 树里翻：

`CommandManger`、`FogDeviceManager`、`DeYeMqttManager`、`DeYeFogMqttManager`、`DeYeHttpRequestManager`、`ComboManager`、`DehumidifierBean`、`PropertyParam`、`RetrofitService`、`UnifiedMqttService` 等。

## `control_panel/dehumidifier/`

APK `assets/control_panel/dehumidifier/*.json` 的明文拷贝。机型能力在这里；IoT 平台路由不在这里。

## `qh/`

加固 `classes.dex` 尾巴解开过程中的中间 blob（`qh-layer1.bin`、`qh-es.bin`、`qh-rest.bin`）。给以后继续抠 360 算法用，不是 Java 源码。
