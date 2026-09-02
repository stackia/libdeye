#!/usr/bin/env bash
# Re-run JADX on dumped business DEX files (not stored in git).
set -euo pipefail

HERE="$(cd "$(dirname "$0")" && pwd)"
DEX="${DEX_DIR:-/tmp/deye-apk/unidbg-dump/dex}"
OUT="${1:-$HERE/jadx-full}"

if ! command -v jadx >/dev/null 2>&1; then
  echo "jadx not on PATH. Install JADX 1.5.3+ and retry." >&2
  exit 1
fi

for name in classes3.dex classes6.dex classes7.dex; do
  if [[ ! -f "$DEX/$name" ]]; then
    echo "Missing $DEX/$name. Dump DEX with tools/unidbg first (see NOTES-unpack.md)." >&2
    exit 1
  fi
done

mkdir -p "$OUT"
jadx -j 4 -r --show-bad-code \
  -d "$OUT" \
  "$DEX/classes3.dex" \
  "$DEX/classes6.dex" \
  "$DEX/classes7.dex"

echo "Full JADX output: $OUT/sources"
echo "This repo keeps only com/deye and io/fogcloud under unpacked/jadx/."
