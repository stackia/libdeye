# APK download

This directory keeps metadata and a download script only. The APK binary is
about 106 MB, over GitHub's 100 MB file limit, and is not in git.

```bash
./download-apk.sh
```

On success you get `com.deye_4.2.1.apk` (gitignored). MD5 must be
`F2201AC1CA9EB94218C8990008D1E476`.

`appdetail.json` is a 2026-09-02 snapshot of Yingyongbao detail fields
(`apkUrl`, `apkMd5`, `fileSize`). CDN paths rotate; the script tries the live
detail page first, then this fallback URL.
