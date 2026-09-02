# Deye Smart 4.2.1 unpack notes

How the real DEX was recovered from the Yingyongbao APK on 2026-09-02. Paths
below are repo-relative; the working directories at the time were
`/tmp/deye-apk` and `/tmp/jiagu-unidbg`.

DEX, qh blobs, and native images are **not** stored in git. Dump them locally
with Unidbg if you need to re-run JADX.

## 1. Download the APK

- Channel: Tencent Yingyongbao `https://a.app.qq.com/o/simple.jsp?pkgname=com.deye`
- Detail JSON: `apkUrl` / `apkMd5` captured in [`apk/appdetail.json`](apk/appdetail.json)
- Direct URL (rotates; prefer the detail JSON):

  `http://imtt.dd.qq.com/sjy.00022/sjy.00004/16891/apk/F2201AC1CA9EB94218C8990008D1E476.apk?fsname=com.deye_4.2.1.apk`

- Size: 111,556,698 bytes (106.4 MB)
- MD5: `F2201AC1CA9EB94218C8990008D1E476` (matches Yingyongbao `apkMd5`)

Download script: [`apk/download-apk.sh`](apk/download-apk.sh).

## 2. What is visible without unpacking

`apktool d` / plaintext JADX only see the packer stub. They do **not** show
`com.deye.CommandManger`.

| Observation | Meaning |
| --- | --- |
| `libjiagu_vip*.so` + `eyed.moc` stub DEX | 360 Jiagu VIP **1.4.0.5** |
| Application name `com.stub.StubApp`, real Application `com.deye.MxchipApplication` | The packer decrypts DEX in `attachBaseContext` and loads it with `InMemoryDexClassLoader` |
| `assets/index.android.bundle` | React Native + Hermes bytecode v96. Protocol is not in JS |
| `assets/control_panel/dehumidifier/*.json` | Product-panel JSON. Copy in [`unpacked/control_panel/dehumidifier/`](unpacked/control_panel/dehumidifier/) |
| Manifest service `io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttService` | MXCHIP FogCloud SDK |

Every dehumidifier JSON uses `deviceControlStrategy` `"wan"`. There is no
per-product Fog/Combo/Classic field. Platform routing is Java `CommandManger`,
not these JSON files.

Official P40 labels: `Mode=4` turbo, `WindSpeed=5` auto.

## 3. Packer layout (360 Jiagu VIP 1.4.0.5)

`assets/libjiagu_vip_a64.so` and the inner ELF extracted from it are the same
file (SHA256 prefix `49e378c40de6676d`, 1,044,120 bytes).

The APK's plaintext `classes.dex` is STORE (ZIP method 0), local header offset
`0x591426`, size 17,898,060. From offset **23996** it is a `qh` encrypted tail
(`libjiagu_enc.so`), not plaintext business DEX.

### First-stage SO

- Rebuild a loadable image from inner ELF `PT_LOAD`: put the ELF header at
  file offset 0, code at vaddr 0 (do not overlay the ELF header onto the first
  segment).
- Apply RELA/JMPREL by hand (99/99 + 127/127). Set `DT_RELASZ` / `DT_PLTRELSZ`
  in DYNAMIC to 0, otherwise Unidbg relocation loops forever.
- `JNI_OnLoad` at `0x2adb88`, returns `JNI_VERSION_1_4`.
- Android `Signature.toByteArray()` must return the full `META-INF/DEYE.RSA`
  (PKCS#7, 1085 bytes), not X509 DER (701 bytes). Certificate SHA1:
  `0caaab3d2a6a16552e44be847d825efb836d55f8`.
- JNI must walk `ActivityThread` → `getSystemContext` →
  `PackageManager.getPackageInfo` → `Signature.toByteArray()`. Heap and empty
  pages need RWX, or you get `FETCH_UNMAPPED`.

`JNI_OnLoad` flow:

1. `GetEnv`
2. Call `0x2ad724`: collect package name + signature, then integrity check
   (`0x2ad470`). All must return 0.
3. If `0x2ad724` returned 0, call `0x2ad66c` to walk the callback table.

### Stage-2

First-stage payload at `0x8b40` maps to vaddr `0x30138` (803KB). BMP trailer
`0xf4326` yields RC4 key `76565734239123535674`. PRGA starts at `i=3,j=5` and
increments `i+=2`. After zlib, a custom image: four sections XOR `0xCA`
(phdr / JMPREL / RELA / DYNAMIC) plus `image_rest.bin`.

Rebuilt files (not in git; large native images):

- `/tmp/deye-apk/native/libjiagu_stage2.so`
- `/tmp/deye-apk/native/stage2_flat.bin` (base `0x50000000`)

`JNI_OnLoad` at va `0x36f18` jumps into `interpreter_wrap_int64_t` (`0xacf28`).
Interpreter function pointers are filled by stage-1. Core VM at `0x14690`.

Stage-2 strings already include `interface5`, `libjiagu_enc.so`,
`InMemoryDexClassLoader`, `QHClassLoader`, and the `ZSTD_decompress*` set.

### ZIP / classes.dex lookup

Stage-2 at `0x500cecf0` uses a JIT `svc` trampoline for `openat` / `lseek` /
`mmap`, then finds `classes.dex` via ZIP EOCD (`PK\x05\x06`).

Unicorn reuses page `0x402d4000` for trampolines, and `ic ivau` does not
invalidate the TB cache, so the second call still runs the old `openat`.
Replacing the three generators (`0xe8688` / `0xe8788` / `0xe8804`) with Java
implementations made this stable. `lseek`/`mmap` on a directory fd fail; mapping
the whole APK at `0x70000000` (111,556,698 bytes) made EOCD work
(`zip-find-ret x0=1`).

`protect-time=2026-06-04`; `0x500418a4` has a 30-day window. Analysis day was
past the window, so that check was NOP'd.

## 4. qh config and DEX tail

`classes.dex` offset 23996: `qh` + encrypted config + 9 business blobs. Dump
these locally if you continue the 360 crypto work; they are not in git.

1. First layer: `out[i] = (in[i] + 0x70) ^ 0x36` (stage-2 `0x50061654`).
   Produces 65 `pk` records (`qh-layer1.bin`).
2. `es` (1,455,576 B, `qh-es.bin`) stays ciphertext. The following 16,417,043 B
   are 9 `u32 size + payload` blobs (`qh-rest.bin`).
3. Blob header `5fbfd6a4`. `fastLevel=1` is decrypt + ZSTD; `fastLevel=2` is a
   modified RC4. RC4 PRGA: `i=3,j=5,i+=2,j+=S[i]+1` (`0x500cffd0`). SM4 also
   exists (`0x50165d5e`).
4. Config has no `embed` / `dk` / `rc4`. `pk` header is
   `'pk' + u16 flags + u32 keylen + u32 valuelen`.

Switches that mattered for unpacking:

- `fastLevel=1` (decrypt + ZSTD, not plain RC4)
- `iso` / `fvp` / `nlc` / `ost` = 0, no `embed`
- `nvc` / `cpt` / `ckf` / `vip` / `official` = 1
- `rx=37`, `mpv=502`, `ce=17`, `ln=147`
- `APPKEY=0938398a72a92af6`

Two live hash calls:

- `0x50060fc8`: input `pts+pkg+sig+jiaguVersion+mpv` =
  `1780552149com.deye15032106191.4.0.5502` (38 B), tag=`0x37` (same as `rx=37`)
- `0x50060be4`: first 1389 bytes of the still-encrypted qh config, output at
  `obj+0xa`, tag=`0xDE`

`0x50060be4` searches the input for `*#*#0123456789ES9876543210#*#*` and
truncates the hash length to that marker when found.

Without `embed`, `0x50040824` returns early. Analysis forced `b 0x50040860` to
enter the iso path. The real blob-decrypt loop is `0x50045bbc`.

Offline key guessing ([`tools/brute_dex_key.py`](tools/brute_dex_key.py),
[`tools/try_qh_decrypt.py`](tools/try_qh_decrypt.py)) never produced `dex\n`.
The DEX files came from an Unidbg memory dump after the loader ran.

## 5. Dumped DEX (local Unidbg output)

Write these under `/tmp/deye-apk/unidbg-dump/dex/` (not in git). SHA256 from
the 2026-09-02 dump:

| File | ~Size | Contents | SHA256 |
| --- | --- | --- | --- |
| `classes.dex` | 6.7 MB | packer / support | `bb53b4b5f3fa2512ced842163d5171212de30b7902a3a8ea34888ad16dc84d85` |
| `classes2.dex` | 5.5 MB | third-party | `ebb3f352471953d3c4ffff309dcb4270f351be1122629fcddcb64cf6f1da7fdf` |
| `classes3.dex` | 6.9 MB | **`com.deye` business** | `f264983bda6d4bb927f623f238f74ba40236add1ad0cbc4f1aa32e79141d6d24` |
| `classes4.dex` | 5.9 MB | third-party | `c6679d138870f92c3a8f6da6419d8def3de41006c1b3632a634b887866597e1e` |
| `classes5.dex` | 6.4 MB | third-party | `a9d4f5219d614e246c5b8ef96af2ac63516009ce05c9707a74392a7d0f8f7da5` |
| `classes6.dex` | 2.5 MB | leftover `com.deye` | `c43421ee253a74391340024f460f869b572152842960665942b6050c90a01273` |
| `classes7.dex` | 7.4 MB | **`io.fogcloud` FogCloud SDK** | `79c5ba8b242c124744c0ef6f327fe9bc7f8acc3ac806a84d5561f836005f35ce` |
| `classes8.dex` | 6.4 MB | third-party | `98fbbd7e1d926fe5d348a8c8fb5eebe5895c18eaa646e451ae1176e44b29d2a7` |
| `classes9.dex` | 30 KB | small tail | `845591962c59a755e96335800feca2a4da4e7880568cb9cefd9067a26e20b94d` |

## 6. JADX

- Tool: JADX **1.5.3** (then at `/tmp/jadx`)
- Example: `jadx -j 4 -r --show-bad-code -d out classes3.dex classes6.dex classes7.dex`
- This repo keeps only business packages:
  [`unpacked/jadx/com/deye`](unpacked/jadx/com/deye) and
  [`unpacked/jadx/io/fogcloud`](unpacked/jadx/io/fogcloud) (~849 `.java` files).
  Kotlin / OkHttp / RN third-party output is not in git.
- Protocol comparison copies live in [`unpacked/jadx-protocol/`](unpacked/jadx-protocol/).

360 string encryption: many literals are `StubApp.getString2(id)`. Method names,
JSON fields, Retrofit paths, and Combo opcodes are plaintext.

## 7. Tools used (and not used)

| Tool | Version / source | Role |
| --- | --- | --- |
| curl / Python | system | Fetch Yingyongbao detail page and APK |
| apktool | `apktool.jar` (then `/tmp/deye-apk/tools/apktool.jar`) | Extract plaintext resources, product JSON, stub smali |
| JADX | 1.5.3 | Decompile stub DEX and dumped DEX |
| Unidbg | `unidbg-android` 0.9.8 | Emulate `libjiagu` `JNI_OnLoad`, dump DEX |
| Maven + Java 11 | `tools/unidbg/pom.xml` | Run `dump.Stage2Dump` |
| Python scripts | `tools/brute_dex_key.py`, `try_qh_decrypt.py` | Offline qh/RC4 guesses (failed; DEX came from Unidbg) |
| BlackDex / Frida | downloaded `BlackDex64.apk`, `frida-server-android-x86_64` | No usable Android emulator/device in the cloud VM |
| x86 bridge | `libX86Bridge.so` was built under `tools` | Support SO for the packer; not the final path |

Unidbg dumper sources: [`tools/unidbg/`](tools/unidbg/). `Stage2Dump.java` hardcodes
`/tmp/deye-apk/...`: symlink this directory's APK and the local
`native/stage2_flat.bin` to re-run. `native/*.so` / flattened images are not in
git (large, and `*.so` is gitignored).

## 8. Intermediate files that must stay out of git

These may still exist on the analysis machine under `/tmp/deye-apk`:

- `com.deye_4.2.1.apk` (106 MB, GitHub file limit)
- `com.deye_patched.apk` / `com.deye_signed.apk`
- `native/libjiagu_mapped.so`, `stage2_flat.bin`, inner ELF
- dumped DEX and qh blobs
- Unidbg heap/map dumps, `unidbg-stage2*.log`
- `frida-server-android-x86_64` (~295 MB), `apktool.jar`

To reproduce: run the download script, then dump with Unidbg as in sections 3–5,
then JADX. Protocol work can use the Java already under `unpacked/jadx/`.
