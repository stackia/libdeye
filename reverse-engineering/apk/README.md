# APK 下载

本目录只放元数据和脚本。APK 二进制约 106 MB，超过 GitHub 100 MB 单文件限制，不进 git。

```bash
./download-apk.sh
```

成功后得到 `com.deye_4.2.1.apk`（gitignore）。MD5 必须是 `F2201AC1CA9EB94218C8990008D1E476`。

`appdetail.json` 是 2026-09-02 从应用宝详情页抓到的字段副本（`apkUrl`、`apkMd5`、`fileSize`）。CDN 路径会变，脚本会先尝试再解析详情页。
