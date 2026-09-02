# 脱壳时用过的工具

Unidbg Java dumper 期望工作目录布局与当时相同：

```
/tmp/deye-apk/com.deye_4.2.1.apk
/tmp/deye-apk/native/stage2_flat.bin
/tmp/deye-apk/unidbg-dump/          # 输出目录
```

把本仓库 `apk/download-apk.sh` 下好的 APK 链过去即可：

```bash
mkdir -p /tmp/deye-apk
ln -sf "$(pwd)/reverse-engineering/apk/com.deye_4.2.1.apk" /tmp/deye-apk/com.deye_4.2.1.apk
```

`stage2_flat.bin` 是从加固 SO 解出来的第二段镜像，没有进 git。没有它就无法重跑 `Stage2Dump`；直接使用 [`../unpacked/dex/`](../unpacked/dex/) 即可继续分析协议。

## Unidbg（最终成功路径）

Maven 项目：[`unidbg/`](unidbg/)，依赖 `unidbg-android` 0.9.8。

```bash
cd reverse-engineering/tools/unidbg
mvn -q -DskipTests exec:java
```

主类 `dump.Stage2Dump`：补 JNI 环境、映射 APK、跑 `JNI_OnLoad`、从内存写出 DEX。路径全部写死在 Java 源码里（`/tmp/deye-apk/...`）。

## 离线猜密钥（未成功）

`brute_dex_key.py` 与 `try_qh_decrypt.py` 也写死 `/tmp/deye-apk/...`。它们用来试 qh/RC4/MD5 变种，没有打出 `dex\n`。真实 DEX 来自 Unidbg dump，不来自这些脚本。

## 下过但没用上

云环境没有 Android 模拟器/真机，所以没有跑：

- BlackDex
- Frida server（约 295 MB，不进 git）
- 重打包签名后的 APK（`apksigner` + debug keystore）
