#!/usr/bin/env python3
"""Try signature-derived keys against the Jiagu qh DEX tail.

Hardcoded paths match the 2026-09-02 unpack workspace (/tmp/deye-apk/...).
See reverse-engineering/README.md (Failed attempts). This script did not recover DEX.
"""
from __future__ import annotations

import hashlib
import itertools
import struct
import zipfile
from pathlib import Path

APK = Path("/tmp/deye-apk/com.deye_4.2.1.apk")
OUT = Path("/tmp/deye-apk/unpacked")


def rc4(key: bytes, data: bytes, i0: int = 0, j0: int = 0, istep: int = 1) -> bytes:
    s = list(range(256))
    j = 0
    for i in range(256):
        j = (j + s[i] + key[i % len(key)]) & 0xFF
        s[i], s[j] = s[j], s[i]
    i = i0
    j = j0
    out = bytearray(len(data))
    for n, b in enumerate(data):
        i = (i + istep) & 0xFF
        j = (j + s[i] + (1 if istep == 2 else 0)) & 0xFF
        s[i], s[j] = s[j], s[i]
        out[n] = b ^ s[(s[i] + s[j]) & 0xFF]
    return bytes(out)


def looks_plain(b: bytes) -> str | None:
    if b.startswith(b"dex\n035"):
        return "dex"
    if b.startswith(b"\x28\xb5\x2f\xfd"):
        return "zstd"
    if b.startswith((b"\x78\x9c", b"\x78\xda", b"\x78\x01")):
        return "zlib"
    if b.startswith(b"PK\x03\x04"):
        return "zip"
    return None


def main() -> None:
    zf = zipfile.ZipFile(APK)
    dex = zf.read("classes.dex")
    sig = zf.read("META-INF/DEYE.RSA")
    tail = dex[23996:]
    body = tail[12:]
    print("tail", len(tail), "body", len(body), "sig", len(sig), hashlib.sha1(sig).hexdigest())

    cert_sha1 = bytes.fromhex("0CAAAB3D2A6A16552E44BE847D825EFB836D55F8")
    keys = {
        "sig": sig,
        "sig16": sig[:16],
        "sig20": sig[:20],
        "sha1sig": hashlib.sha1(sig).digest(),
        "md5sig": hashlib.md5(sig).digest(),
        "certsha1": cert_sha1,
        "pkg": b"com.deye",
        "bmpkey": bytes.fromhex("76565734239123535674"),
        "jiagu": b"libjiagu_enc.so",
        "qh": b"qh",
    }
    # first 32 of body xor 0xc6 looked structured
    keys["c6rep"] = bytes([0xC6])

    candidates = []
    for name, key in keys.items():
        for variant, fn in (
            ("rc4", lambda k, d: rc4(k, d[:64])),
            ("rc4_i3j5", lambda k, d: rc4(k, d[:64], 3, 5, 2)),
            ("xor", lambda k, d: bytes(b ^ k[i % len(k)] for i, b in enumerate(d[:64]))),
        ):
            try:
                out = fn(key, body)
            except Exception:
                continue
            kind = looks_plain(out)
            if kind:
                print("HIT", name, variant, kind, out[:16].hex())
                candidates.append((name, variant, key))
            # also check after skipping 4/8
            for skip in (4, 8):
                kind = looks_plain(out[skip:])
                if kind:
                    print("HIT skip", skip, name, variant, kind)

    # brute xor single byte on body
    for k in range(256):
        out = bytes(b ^ k for b in body[:8])
        kind = looks_plain(out)
        if kind:
            print("HIT xorbyte", hex(k), kind)

    print("done candidates", len(candidates))


if __name__ == "__main__":
    main()
