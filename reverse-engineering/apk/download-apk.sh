#!/usr/bin/env bash
# Download Deye Smart 4.2.1 from Tencent Yingyongbao and verify MD5.
set -euo pipefail

HERE="$(cd "$(dirname "$0")" && pwd)"
APK="$HERE/com.deye_4.2.1.apk"
EXPECTED_MD5="F2201AC1CA9EB94218C8990008D1E476"
DETAIL_PAGE="https://a.app.qq.com/o/simple.jsp?pkgname=com.deye"

# Fallback taken from apk/appdetail.json (captured 2026-09-02). Prefer the
# live detail page if python3 is available, because CDN URLs rotate.
FALLBACK_URL="http://imtt.dd.qq.com/sjy.00022/sjy.00004/16891/apk/F2201AC1CA9EB94218C8990008D1E476.apk?fsname=com.deye_4.2.1.apk"

apk_url="$FALLBACK_URL"
if command -v python3 >/dev/null 2>&1; then
  html="$(mktemp)"
  trap 'rm -f "$html"' EXIT
  curl -fsSL "$DETAIL_PAGE" -o "$html"
  live_url="$(python3 - "$html" <<'PY'
import json, re, sys
from pathlib import Path
text = Path(sys.argv[1]).read_text(encoding="utf-8", errors="replace")
# Yingyongbao embeds app details as JSON-looking snippets.
m = re.search(r'"apkUrl"\s*:\s*"(http[^"]+\.apk[^"]*)"', text)
if not m:
    sys.exit(0)
print(m.group(1).encode("utf-8").decode("unicode_escape"))
PY
)"
  if [[ -n "${live_url}" ]]; then
    apk_url="$live_url"
  fi
fi

echo "Downloading: $apk_url"
curl -fL --retry 4 --retry-delay 4 "$apk_url" -o "$APK"

actual_md5="$(md5sum "$APK" | awk '{print toupper($1)}')"
if [[ "$actual_md5" != "$EXPECTED_MD5" ]]; then
  echo "MD5 mismatch: got $actual_md5 want $EXPECTED_MD5" >&2
  exit 1
fi

echo "OK $APK ($actual_md5)"
echo "Next: $(cd "$(dirname "$0")/.." && pwd)/decompile-apk.sh"
