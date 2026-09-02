#!/usr/bin/env bash
# Successful decompile path for Deye Smart 4.2.1:
#   APK → control-panel JSON → Unidbg DEX dump (if needed) → JADX
#
# Prerequisites:
#   ./apk/download-apk.sh
#   unzip, and JADX 1.5.3+ on PATH (or /tmp/jadx/bin/jadx)
#   For a fresh DEX dump: Maven, Java 11, and
#     /tmp/deye-apk/native/stage2_flat.bin
#
# Usage:
#   ./decompile-apk.sh
#   ./decompile-apk.sh --json-only
#   ./decompile-apk.sh --update-tree
set -euo pipefail

HERE="$(cd "$(dirname "$0")" && pwd)"
APK="${APK:-$HERE/apk/com.deye_4.2.1.apk}"
WORK="${WORK_DIR:-/tmp/deye-apk}"
DEX_DIR="${DEX_DIR:-$WORK/unidbg-dump/dex}"
JADX_FULL="${JADX_FULL:-$HERE/unpacked/jadx-full}"
JADX_TREE="$HERE/unpacked/jadx"
PROTOCOL_DIR="$HERE/unpacked/jadx-protocol"
CONTROL_DIR="$HERE/unpacked/control_panel/dehumidifier"
JSON_ONLY=0
UPDATE_TREE=0

PROTOCOL_CLASSES=(
  CommandManger.java
  FogDeviceManager.java
  ComboManager.java
  DeYeMqttManager.java
  DeYeFogMqttManager.java
  DeYeHttpRequestManager.java
  RetrofitService.java
  UnifiedMqttService.java
  DehumidifierBean.java
  PropertyParam.java
  DeviceCacheManager.java
  CommandPara.java
  DeYeModelType.java
)

usage() {
  cat <<'EOF'
Usage: ./decompile-apk.sh [--json-only] [--update-tree]

  --json-only     Extract assets/control_panel JSON from the APK and exit
  --update-tree   Copy JADX output into unpacked/jadx and unpacked/jadx-protocol
EOF
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    --json-only) JSON_ONLY=1 ;;
    --update-tree) UPDATE_TREE=1 ;;
    -h | --help)
      usage
      exit 0
      ;;
    *)
      echo "Unknown argument: $1" >&2
      usage >&2
      exit 1
      ;;
  esac
  shift
done

find_apk() {
  if [[ -f "$APK" ]]; then
    return
  fi
  if [[ -f "$WORK/com.deye_4.2.1.apk" ]]; then
    APK="$WORK/com.deye_4.2.1.apk"
    return
  fi
  echo "APK not found. Run $HERE/apk/download-apk.sh first." >&2
  exit 1
}

find_jadx() {
  if command -v jadx >/dev/null 2>&1; then
    command -v jadx
    return
  fi
  if [[ -x /tmp/jadx/bin/jadx ]]; then
    echo /tmp/jadx/bin/jadx
    return
  fi
  echo "jadx not on PATH. Install JADX 1.5.3+ (or place it at /tmp/jadx/bin/jadx)." >&2
  exit 1
}

extract_control_panel() {
  local tmp
  tmp="$(mktemp -d)"
  unzip -o -q "$APK" "assets/control_panel/dehumidifier/*" -d "$tmp"
  mkdir -p "$CONTROL_DIR"
  cp -a "$tmp/assets/control_panel/dehumidifier/." "$CONTROL_DIR/"
  rm -rf "$tmp"
  echo "Control-panel JSON: $CONTROL_DIR ($(find "$CONTROL_DIR" -name '*.json' | wc -l) files)"
}

ensure_work_layout() {
  mkdir -p "$WORK"
  local dest="$WORK/com.deye_4.2.1.apk"
  local src
  src="$(realpath "$APK")"
  if [[ -e "$dest" ]] && [[ "$(realpath "$dest")" == "$src" ]]; then
    return
  fi
  ln -sfn "$src" "$dest"
}

dex_ready() {
  [[ -f "$DEX_DIR/classes3.dex" && -f "$DEX_DIR/classes6.dex" && -f "$DEX_DIR/classes7.dex" ]]
}

dump_dex() {
  if dex_ready; then
    echo "Using existing DEX dump: $DEX_DIR"
    return
  fi
  if [[ ! -f "$WORK/native/stage2_flat.bin" ]]; then
    echo "No DEX dump at $DEX_DIR and no $WORK/native/stage2_flat.bin." >&2
    echo "stage2_flat.bin is the Unidbg stage-2 image from the 360 packer." >&2
    echo "It is not in git. Without it, use the committed unpacked/jadx tree." >&2
    echo "See reverse-engineering/README.md (Successful path / Re-running the Unidbg dump)." >&2
    exit 1
  fi
  if ! command -v mvn >/dev/null 2>&1; then
    echo "Maven is required to run tools/unidbg (dump.Stage2Dump)." >&2
    exit 1
  fi
  echo "Dumping DEX with Unidbg Stage2Dump (this can take a while)..."
  (cd "$HERE/tools/unidbg" && mvn -q -DskipTests exec:java)
  if ! dex_ready; then
    echo "Unidbg finished but $DEX_DIR/classes3.dex is still missing." >&2
    exit 1
  fi
}

jadx_sources_root() {
  local out="$1"
  if [[ -d "$out/sources/com/deye" ]]; then
    echo "$out/sources"
  elif [[ -d "$out/com/deye" ]]; then
    echo "$out"
  else
    echo "JADX output has no com/deye under $out" >&2
    exit 1
  fi
}

run_jadx() {
  local jadx_bin sources
  jadx_bin="$(find_jadx)"
  mkdir -p "$JADX_FULL"
  echo "JADX → $JADX_FULL"
  set +e
  "$jadx_bin" -j 4 -r --show-bad-code \
    -d "$JADX_FULL" \
    "$DEX_DIR/classes3.dex" \
    "$DEX_DIR/classes6.dex" \
    "$DEX_DIR/classes7.dex"
  local jadx_status=$?
  set -e
  if [[ "$jadx_status" -ne 0 ]]; then
    echo "JADX exited $jadx_status (partial decompile is expected on this DEX)."
  fi
  sources="$(jadx_sources_root "$JADX_FULL")"
  echo "JADX sources: $sources"
  if [[ "$UPDATE_TREE" -eq 1 ]]; then
    mkdir -p "$JADX_TREE/com" "$JADX_TREE/io"
    rm -rf "$JADX_TREE/com/deye" "$JADX_TREE/io/fogcloud"
    cp -a "$sources/com/deye" "$JADX_TREE/com/deye"
    cp -a "$sources/io/fogcloud" "$JADX_TREE/io/fogcloud"
    mkdir -p "$PROTOCOL_DIR"
    local name found
    for name in "${PROTOCOL_CLASSES[@]}"; do
      found="$(find "$JADX_TREE" -name "$name" -print -quit)"
      if [[ -z "$found" ]]; then
        echo "Missing protocol class $name in JADX output." >&2
        exit 1
      fi
      cp "$found" "$PROTOCOL_DIR/$name"
    done
    echo "Updated $JADX_TREE and $PROTOCOL_DIR"
  else
    echo "Skipping committed tree update (pass --update-tree to copy into unpacked/jadx)."
  fi
}

find_apk
extract_control_panel
if [[ "$JSON_ONLY" -eq 1 ]]; then
  exit 0
fi
ensure_work_layout
dump_dex
run_jadx
echo "Done. Protocol classes: CommandManger / FogDeviceManager / DeYeMqttManager."
echo "Read reverse-engineering/README.md for routing (Fog HTTP vs Classic vs Combo)."
