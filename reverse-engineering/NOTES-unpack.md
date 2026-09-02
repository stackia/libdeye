# 德业智能 4.2.1 脱壳笔记

记录 2026-09-02 从应用宝 APK 挖出真实 DEX 的过程。路径已改成本仓库相对位置；当时工作目录是 `/tmp/deye-apk` 与 `/tmp/jiagu-unidbg`。

## 1. 下载 APK

- 渠道：腾讯应用宝 `https://a.app.qq.com/o/simple.jsp?pkgname=com.deye`
- 详情 JSON：当时从该页解析出 `apkUrl` / `apkMd5`，副本在 [`apk/appdetail.json`](apk/appdetail.json)
- 直链（会变，以详情 JSON 为准）：

  `http://imtt.dd.qq.com/sjy.00022/sjy.00004/16891/apk/F2201AC1CA9EB94218C8990008D1E476.apk?fsname=com.deye_4.2.1.apk`

- 大小：111,556,698 字节（106.4 MB）
- MD5：`F2201AC1CA9EB94218C8990008D1E476`（与应用宝 `apkMd5` 一致）

下载脚本：[`apk/download-apk.sh`](apk/download-apk.sh)。

## 2. 壳外能直接看到的东西

`apktool d` / 明文 JADX 只能看到加固 stub，**看不到** `com.deye.CommandManger`。

| 观察 | 含义 |
| --- | --- |
| `libjiagu_vip*.so` + `eyed.moc` stub DEX | 360 加固（Jiagu VIP **1.4.0.5**） |
| Application 名 `com.stub.StubApp`，真实 Application `com.deye.MxchipApplication` | 壳在 `attachBaseContext` 里解密 DEX 再 `InMemoryDexClassLoader` |
| `assets/index.android.bundle` | React Native + Hermes bytecode v96。协议不在 JS 里 |
| `assets/control_panel/dehumidifier/*.json` | 机型面板明文。副本在 [`unpacked/control_panel/dehumidifier/`](unpacked/control_panel/dehumidifier/) |
| 清单里的 `io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttService` | MXCHIP FogCloud SDK |

全部除湿机 JSON 的 `deviceControlStrategy` 都是 `"wan"`，没有 per-product Fog/Combo/Classic 字段。平台路由在 Java `CommandManger` 里，不在这些 JSON 里。

官方把 P40 的 `Mode=4` 叫「急速」，`WindSpeed=5` 叫「自动」。

## 3. 加固结构（360 Jiagu VIP 1.4.0.5）

`assets/libjiagu_vip_a64.so` 与从中抽出的 inner ELF 是同一文件（SHA256 前缀 `49e378c40de6676d`，1,044,120 字节）。

APK 内明文 `classes.dex` 是 STORE（ZIP method 0），local header 偏移 `0x591426`，大小 17,898,060。offset **23996** 起是 `qh` 加密尾巴（`libjiagu_enc.so`），不是明文业务 DEX。

### 第一段 SO

- 按 inner ELF 的 `PT_LOAD` 重建可加载镜像：ELF 头放在 file offset 0，代码在 vaddr 0（不要用 ELF 头覆盖第一段）。
- 手工应用 RELA/JMPREL（99/99 + 127/127）。DYNAMIC 里 `DT_RELASZ` / `DT_PLTRELSZ` 置 0，否则 Unidbg 重定位会死循环。
- `JNI_OnLoad` 地址 `0x2adb88`，返回 `JNI_VERSION_1_4`。
- Android `Signature.toByteArray()` 必须返回整份 `META-INF/DEYE.RSA`（PKCS#7，1085 字节），不是 X509 DER（701 字节）。证书 SHA1：`0caaab3d2a6a16552e44be847d825efb836d55f8`。
- JNI 环境要能走完：`ActivityThread` → `getSystemContext` → `PackageManager.getPackageInfo` → `Signature.toByteArray`。堆/空页需要 RWX，否则 `FETCH_UNMAPPED`。

`JNI_OnLoad` 逻辑：

1. `GetEnv`
2. 调 `0x2ad724`：采集包名+签名，再做完整性检查（`0x2ad470`），全部返回 0 才算通过
3. 若 `0x2ad724` 返回 0，再调 `0x2ad66c` 遍历回调表

### 第二段（stage-2）

第一段 `0x8b40` 载荷在 vaddr `0x30138`（803KB）。BMP trailer `0xf4326` 抽出 RC4 密钥 `76565734239123535674`，PRGA 从 `i=3,j=5` 起步且 `i+=2`。解密后 zlib 得到自定义镜像：XOR `0xCA` 的 4 个 section（phdr / JMPREL / RELA / DYNAMIC）+ `image_rest.bin`。

当时重建文件（未进 git，体积大且是 native 镜像）：

- `/tmp/deye-apk/native/libjiagu_stage2.so`
- `/tmp/deye-apk/native/stage2_flat.bin`（基址 `0x50000000`）

`JNI_OnLoad` 在 va `0x36f18`，跳进 `interpreter_wrap_int64_t`（`0xacf28`）。解释器函数指针由第一段填充，核心 VM 在 `0x14690`。

stage-2 字符串已含 `interface5`、`libjiagu_enc.so`、`InMemoryDexClassLoader`、`QHClassLoader`、整套 `ZSTD_decompress*`。

### ZIP / classes.dex 定位

stage-2 在 `0x500cecf0` 用 JIT `svc` 蹦床做 `openat` / `lseek` / `mmap`，再按 ZIP EOCD（`PK\x05\x06`）找 `classes.dex`。

Unicorn 会复用同一页 `0x402d4000` 写蹦床，且 `ic ivau` 清不掉 TB cache，第二次仍执行旧的 `openat`。三个生成器（`0xe8688` / `0xe8788` / `0xe8804`）改成 Java 实现后才稳定。目录 fd 的 `lseek`/`mmap` 会失败；强制把整包 APK 映射到 `0x70000000`（111,556,698 字节）后 EOCD 有效，`zip-find-ret x0=1`。

`protect-time=2026-06-04`，`0x500418a4` 有 30 天窗；分析当天已过窗，对检查点做了 NOP。

## 4. qh 配置与 DEX 尾巴

`classes.dex` offset 23996：`qh` + 配置密文 + 9 个业务 blob。中间产物在 [`unpacked/qh/`](unpacked/qh/)。

1. 第一层：`out[i] = (in[i] + 0x70) ^ 0x36`（stage-2 `0x50061654`）。得到 65 条 `pk` 记录（`qh-layer1.bin`）。
2. `es`（1,455,576 B，`qh-es.bin`）仍是密文；其后 16,417,043 B 是 9 个 `u32 size + payload` blob（`qh-rest.bin`）。
3. blob 头 `5fbfd6a4`。`fastLevel=1` 走「解密 + ZSTD」；`fastLevel=2` 走改版 RC4。RC4 PRGA：`i=3,j=5,i+=2,j+=S[i]+1`（`0x500cffd0`）。另有 SM4（`0x50165d5e`）。
4. 配置无 `embed` / `dk` / `rc4`。`pk` 头是 `'pk' + u16 flags + u32 keylen + u32 valuelen`。

与脱壳相关的开关：

- `fastLevel=1`（解密 + ZSTD，不是纯 RC4）
- `iso` / `fvp` / `nlc` / `ost` = 0，无 `embed`
- `nvc` / `cpt` / `ckf` / `vip` / `official` = 1
- `rx=37`，`mpv=502`，`ce=17`，`ln=147`
- `APPKEY=0938398a72a92af6`

现场两次 hash 调用：

- `0x50060fc8`：输入 `pts+pkg+sig+jiaguVersion+mpv` = `1780552149com.deye15032106191.4.0.5502`（38 B），tag=`0x37`（与 `rx=37` 相同）
- `0x50060be4`：输入未解的 qh 配置前 1389 字节，输出写到 `obj+0xa`，tag=`0xDE`

`0x50060be4` 会在输入里搜 `*#*#0123456789ES9876543210#*#*`，命中则把 hash 长度截到标记处。

缺 `embed` 时 `0x50040824` 会提前返回。分析时改成 `b 0x50040860` 强制走进 iso 路径。真正按 blob 解密的循环在 `0x50045bbc`。

纯离线猜密钥（[`tools/brute_dex_key.py`](tools/brute_dex_key.py)、[`tools/try_qh_decrypt.py`](tools/try_qh_decrypt.py)）没有打出 `dex\n`。最终 DEX 是 Unidbg 跑完加载路径后从内存 dump 的。

## 5. Dump 出的 DEX

9 个文件在 [`unpacked/dex/`](unpacked/dex/)，SHA256 见同目录 `SHA256SUMS`。

| 文件 | 大约体积 | 内容 |
| --- | --- | --- |
| `classes.dex` | 6.7 MB | 加固/支撑代码 |
| `classes2.dex` | 5.5 MB | 第三方 |
| `classes3.dex` | 6.9 MB | **`com.deye` 业务**（`CommandManger` 等） |
| `classes4.dex` | 5.9 MB | 第三方 |
| `classes5.dex` | 6.4 MB | 第三方 |
| `classes6.dex` | 2.5 MB | 少量 `com.deye` |
| `classes7.dex` | 7.4 MB | **`io.fogcloud` FogCloud SDK** |
| `classes8.dex` | 6.4 MB | 第三方 |
| `classes9.dex` | 30 KB | 很小的尾巴 |

## 6. JADX

- 工具：JADX **1.5.3**（当时放在 `/tmp/jadx`）
- 命令示例：`jadx -j 4 -r --show-bad-code -d out unpacked/dex/classes3.dex unpacked/dex/classes6.dex unpacked/dex/classes7.dex`
- 仓库里只收了业务包：[`unpacked/jadx/com/deye`](unpacked/jadx/com/deye) 与 [`unpacked/jadx/io/fogcloud`](unpacked/jadx/io/fogcloud)（约 849 个 `.java`）。Kotlin / OkHttp / RN 等第三方没有进 git。
- 协议对照时另外拷了一份关键类到 [`unpacked/jadx-protocol/`](unpacked/jadx-protocol/)。

360 字符串加密：很多字面量是 `StubApp.getString2(id)`。方法名、JSON 字段、Retrofit path、Combo opcode 是明文。

## 7. 用过的工具（以及没用上的）

| 工具 | 版本/来源 | 用途 |
| --- | --- | --- |
| curl / Python | 系统 | 拉应用宝详情页和 APK |
| apktool | `apktool.jar`（当时 `/tmp/deye-apk/tools/apktool.jar`） | 拆明文资源、机型 JSON、stub smali |
| JADX | 1.5.3 | 反编译 stub DEX 与脱壳 DEX |
| Unidbg | `unidbg-android` 0.9.8 | 模拟 `libjiagu` `JNI_OnLoad`，dump DEX |
| Maven + Java 11 | `tools/unidbg/pom.xml` | 跑 `dump.Stage2Dump` |
| Python 脚本 | `tools/brute_dex_key.py`、`try_qh_decrypt.py` | 离线猜 qh/RC4 钥匙（未成功，DEX 靠 Unidbg dump） |
| BlackDex / Frida | 下过 `BlackDex64.apk`、`frida-server-android-x86_64` | 云环境没有可用 Android 模拟器/真机，没用上 |
| x86 bridge | `tools` 里曾编译 `libX86Bridge.so` | 给加固 SO 补环境，不是最终路径 |

Unidbg dumper 源码在 [`tools/unidbg/`](tools/unidbg/)。`Stage2Dump.java` 里路径写死为 `/tmp/deye-apk/...`：把本目录的 APK 和当时的 `native/stage2_flat.bin` 链过去即可重跑。`native/*.so` / 平铺镜像没有进 git（体积大，且仓库已 gitignore `*.so`）。

## 8. 当时未提交 git 的中间文件

这些仍可能留在分析机的 `/tmp/deye-apk`，但不应推进仓库：

- `com.deye_4.2.1.apk`（106 MB，GitHub 单文件上限）
- `com.deye_patched.apk` / `com.deye_signed.apk`
- `native/libjiagu_mapped.so`、`stage2_flat.bin`、inner ELF
- Unidbg heap/map dump、`unidbg-stage2*.log`
- `frida-server-android-x86_64`（约 295 MB）、`apktool.jar`

需要复现时：先跑下载脚本，再按第 3–5 节用 Unidbg dump，或直接用本仓库已经放进来的 [`unpacked/dex/`](unpacked/dex/)。
