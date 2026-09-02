#!/usr/bin/env python3
"""Brute 360 Jiagu DEX blob keys from qh config + official hash inputs.

Hardcoded paths match the 2026-09-02 unpack workspace (/tmp/deye-apk/...).
See reverse-engineering/README.md (Failed attempts). This script did not recover DEX.
"""
from __future__ import annotations

import hashlib
import hmac
import struct
import zlib
from pathlib import Path

LAYER1 = Path("/tmp/deye-apk/unidbg-dump/qh-layer1.bin")
REST = Path("/tmp/deye-apk/unidbg-dump/qh-rest.bin")
ES = Path("/tmp/deye-apk/unidbg-dump/qh-es.bin")
APK = Path("/tmp/deye-apk/com.deye_4.2.1.apk")


def rc4_mod(key: bytes, data: bytes) -> bytes:
    s = list(range(256))
    j = 0
    for i in range(256):
        j = (j + s[i] + key[i % len(key)]) & 0xFF
        s[i], s[j] = s[j], s[i]
    i, j = 3, 5
    out = bytearray(len(data))
    for n, b in enumerate(data):
        i = (i + 2) & 0xFF
        j = (j + s[i] + 1) & 0xFF
        s[i], s[j] = s[j], s[i]
        out[n] = b ^ s[(s[i] + s[j]) & 0xFF]
    return bytes(out)


def rc4_std(key: bytes, data: bytes) -> bytes:
    s = list(range(256))
    j = 0
    for i in range(256):
        j = (j + s[i] + key[i % len(key)]) & 0xFF
        s[i], s[j] = s[j], s[i]
    i = j = 0
    out = bytearray(len(data))
    for n, b in enumerate(data):
        i = (i + 1) & 0xFF
        j = (j + s[i]) & 0xFF
        s[i], s[j] = s[j], s[i]
        out[n] = b ^ s[(s[i] + s[j]) & 0xFF]
    return bytes(out)


def looks(b: bytes) -> str | None:
    if len(b) < 4:
        return None
    if b.startswith(b"dex\n"):
        return "dex"
    if b.startswith(b"\x28\xb5\x2f\xfd"):
        return "zstd"
    if b[0] == 0x78 and b[1] in (0x01, 0x9C, 0xDA):
        return "zlib"
    if b.startswith(b"PK\x03\x04"):
        return "zip"
    # inner u32 size then magic
    if len(b) >= 8:
        return looks(b[4:])
    return None


def md5(b: bytes) -> bytes:
    return hashlib.md5(b).digest()


def sha1(b: bytes) -> bytes:
    return hashlib.sha1(b).digest()


def sha256(b: bytes) -> bytes:
    return hashlib.sha256(b).digest()


def xor_tag(b: bytes, tag: int) -> bytes:
    return bytes(x ^ (tag & 0xFF) for x in b)


def parse_pk(data: bytes) -> dict[str, bytes]:
    off = 0
    recs = {}
    while off + 12 <= len(data) and data[off : off + 2] == b"pk":
        klen, vlen = struct.unpack_from("<II", data, off + 4)
        off += 12
        key = data[off : off + klen]
        off += klen
        val = data[off : off + vlen]
        off += vlen
        recs[key.decode("latin1", "replace")] = val
    return recs


def main() -> None:
    layer1 = LAYER1.read_bytes()
    recs = parse_pk(layer1)
    rest = REST.read_bytes()
    # rest: u32 count, then {u32 size, payload}
    count = struct.unpack_from("<I", rest, 0)[0]
    sz0 = struct.unpack_from("<I", rest, 4)[0]
    blob0 = rest[8 : 8 + sz0]
    # inner u32 then payload
    inner = struct.unpack_from("<I", blob0, 0)[0]
    sample = blob0[4 : 4 + min(64, inner)]
    print("blob0", sz0, "inner", inner, "head", blob0[:16].hex(), "count", count)

    enc_qh = None
    # encrypted qh+12 first 1389 from packed dex
    import zipfile

    with zipfile.ZipFile(APK) as zf:
        dex = zf.read("classes.dex")
    qh = dex[23996:]
    enc_prefix = qh[12 : 12 + 1389]
    print("enc_prefix", len(enc_prefix), enc_prefix[:8].hex())

    concat = b"1780552149com.deye15032106191.4.0.5502"
    appkey = recs["APPKEY"]
    sign = recs["sign"]
    sig = recs["sig"]
    pts = recs["pts"]
    pkg = recs["pkg"]
    ver = recs["jiaguVersion"]
    mpv = recs["mpv"]
    rx = recs["rx"]
    apkmd5 = recs["apk-md5"]
    checksum = recs["checkSum"]

    materials: dict[str, bytes] = {
        "enc1389": enc_prefix,
        "dec1389": layer1[:1389],
        "concat": concat,
        "appkey": appkey,
        "appkey_hex": bytes.fromhex(appkey.decode()),
        "sign": sign,
        "sign_hex": bytes.fromhex(sign.decode()),
        "sig": sig,
        "pts": pts,
        "pkg": pkg,
        "ver": ver,
        "mpv": mpv,
        "rx": rx,
        "apkmd5": apkmd5,
        "apkmd5_hex": bytes.fromhex(apkmd5.decode()),
        "checksum": checksum,
        "bmp": bytes.fromhex("76565734239123535674"),
        "empty": b"",
        "de": bytes([0xDE]),
        "rx37": bytes([37]),
        "ce17": bytes([17]),
        "ln147": struct.pack("<H", 147),
        "pts_pkg_sig_ver_mpv": pts + pkg + sig + ver + mpv,
    }

    keys: list[tuple[str, bytes]] = []
    for name, mat in materials.items():
        keys.append((f"raw:{name}", mat[:16] if len(mat) >= 16 else mat.ljust(16, b"\x00")))
        keys.append((f"md5:{name}", md5(mat)))
        keys.append((f"md5^de:{name}", xor_tag(md5(mat), 0xDE)))
        keys.append((f"md5^37:{name}", xor_tag(md5(mat), 0x37)))
        keys.append((f"sha1:{name}", sha1(mat)[:16]))
        keys.append((f"sha256:{name}", sha256(mat)[:16]))
        keys.append((f"hmac_md5_de:{name}", hmac.new(b"\xde", mat, hashlib.md5).digest()))
        keys.append((f"hmac_md5_37:{name}", hmac.new(b"\x37", mat, hashlib.md5).digest()))
        keys.append((f"hmac_md5_app:{name}", hmac.new(appkey, mat, hashlib.md5).digest()))

    # tagged MD5 IV
    for tag in (0xDE, 0x37, 17, 147, 37):
        h = hashlib.md5()
        # custom: prepend tag dword
        h.update(struct.pack("<I", tag))
        h.update(enc_prefix)
        keys.append((f"md5_pre_tag{tag:x}:enc", h.digest()))
        h = hashlib.md5()
        h.update(enc_prefix)
        h.update(struct.pack("<I", tag))
        keys.append((f"md5_post_tag{tag:x}:enc", h.digest()))

    seen = set()
    hits = 0
    tried = 0
    for name, key in keys:
        if not key or len(key) < 4:
            continue
        key16 = key[:16] if len(key) >= 16 else key.ljust(16, b"\x00")
        sigk = key16
        if sigk in seen:
            continue
        seen.add(sigk)
        tried += 1
        for fn_name, fn in (("rc4mod", rc4_mod), ("rc4", rc4_std)):
            out = fn(key16, sample)
            kind = looks(out)
            if kind:
                print("HIT", name, fn_name, kind, out[:16].hex(), "key", key16.hex())
                hits += 1
            # also skip first 4 of sample (already skipped) and try blob0 raw
            out2 = fn(key16, blob0[:32])
            kind2 = looks(out2)
            if kind2:
                print("HIT rawblob", name, fn_name, kind2, out2[:16].hex())
                hits += 1
    print("tried", tried, "hits", hits, "unique_keys", len(seen))


if __name__ == "__main__":
    main()
