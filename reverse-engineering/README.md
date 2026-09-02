# Deye Smart Android 逆向资料

本目录保存对 **德业智能 Android `com.deye` 4.2.1**（versionCode 141）的脱壳过程、工具、笔记，以及解包后的业务源码，供后续协议分析使用。

官方应用不是开源软件。这里的 Java 是 JADX 从脱壳 DEX 还原的结果，版权仍归浙江德业环境电器有限公司。只用于 `libdeye` 协议对照，不要当作可再分发的 App 源码。

## 不要提交 APK 本体

APK 约 **106.4 MB**（111,556,698 字节），超过 GitHub 单文件 100 MB 限制，因此仓库里只放下载元数据和脚本。

| 项 | 值 |
| --- | --- |
| 包名 | `com.deye` |
| 应用名 | 德业智能 |
| 版本 | 4.2.1（versionCode 141） |
| 渠道 | 腾讯应用宝 |
| 详情页 | https://a.app.qq.com/o/simple.jsp?pkgname=com.deye |
| APK MD5 | `F2201AC1CA9EB94218C8990008D1E476` |
| 签名 SHA1（`META-INF/DEYE.RSA` PKCS#7） | `0caaab3d2a6a16552e44be847d825efb836d55f8` |

下载：

```bash
./reverse-engineering/apk/download-apk.sh
```

脚本会把 APK 写到 `reverse-engineering/apk/com.deye_4.2.1.apk`（该路径已 gitignore），并校验 MD5。

## 目录

```
reverse-engineering/
  README.md                 本文件
  NOTES-unpack.md           360 加固脱壳过程、踩坑、地址
  NOTES-protocol.md         Classic / Fog / FogCombo 发送接收结论
  apk/                      应用宝元数据与下载脚本（不含 APK 二进制）
  tools/                    当时用过的 Unidbg / 密钥探测脚本
  unpacked/
    dex/                    脱壳后的 classes.dex … classes9.dex
    jadx/                   JADX：com.deye + io.fogcloud
    jadx-protocol/          协议相关类的单独摘录（当时对照 libdeye 用的那批）
    control_panel/          APK 明文机型 JSON
    qh/                     加固 qh 配置/blob 中间产物
```

业务 DEX 主要在：

- `unpacked/dex/classes3.dex` — `com.deye`（含 `CommandManger` / `FogDeviceManager`）
- `unpacked/dex/classes7.dex` — `io.fogcloud` FogCloud SDK
- `unpacked/dex/classes6.dex` — 少量 `com.deye` 残留

重新反编译：

```bash
./reverse-engineering/unpacked/decompile-jadx.sh
```

## 工具与操作方式（摘要）

完整步骤见 [`NOTES-unpack.md`](NOTES-unpack.md)。

1. 从应用宝拉取 APK，核对 MD5。
2. `apktool d` 只能看到 360 加固 stub（`com.stub.StubApp`、`eyed.moc`、`libjiagu_vip*.so`）。业务 Java 不在明文 `classes.dex` 里。
3. UI 是 React Native + Hermes bytecode；机型面板 JSON 在 `assets/control_panel/dehumidifier/`，明文可直接拷。
4. 用 Unidbg（`tools/unidbg`，`unidbg-android` 0.9.8）加载 360 Jiagu VIP **1.4.0.5** 的 inner ELF / stage-2 镜像，模拟 `JNI_OnLoad`，从内存 dump 出 9 个真实 DEX。
5. JADX 1.5.3 反编译 dump 出的 DEX。协议路由以 `CommandManger` 为准。

360 把大量字符串加密成 `StubApp.getString2(id)`。方法名、`PropertyParam` JSON 字段、Retrofit path、Combo 字节 opcode 是明文。Classic 命令 topic 后缀在 APK 里仍是 `getString2(44821)`，账号实测为 `/command/hex` 与 `/status/hex`。

## 协议结论（一句话）

官方 `CommandManger`：`if isFog → Fog HTTP; else if isCombo → Classic MQTT {2,17,cmd,value}; else Classic 10 字节`。`isFog` 是 `platform == 2 || platform == 3`，所以 **platform 3 在官方 App 里走 Fog HTTP**。Combo 字节只在 Fog 为假且 `is_combo && protocol_version == combo_V1.0` 时发送。`libdeye` 目前按 `platform == 3` 走 Combo MQTT。细节见 [`NOTES-protocol.md`](NOTES-protocol.md)。
