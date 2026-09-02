# Official Classic / Fog / Combo send and receive

Compare against JADX from Deye Smart 4.2.1:

- [`unpacked/jadx/com/deye/CommandManger.java`](unpacked/jadx/com/deye/CommandManger.java)
- [`unpacked/jadx/com/deye/FogDeviceManager.java`](unpacked/jadx/com/deye/FogDeviceManager.java)
- [`unpacked/jadx-protocol/DeYeMqttManager.java`](unpacked/jadx-protocol/DeYeMqttManager.java)
- [`unpacked/jadx-protocol/DeYeFogMqttManager.java`](unpacked/jadx-protocol/DeYeFogMqttManager.java)
- [`unpacked/jadx/com/deye/combo/`](unpacked/jadx/com/deye/combo/)

`libdeye` should follow these classes. Home Assistant should stay a thin caller.

## Routing (`CommandManger`)

Send methods all take `(…, boolean isFog, boolean isCombo, DehumidifierBean bean)`:

```
if (isFog)       → FogDeviceManager          // HTTP JSON properties
else if (isCombo) → sendSingleCommand        // bytes {2, 17, cmd, value} via DeYeMqttManager
else             → Classic DehumidifierBean  // full JSON → hex MQTT
```

Every UI path passes both flags from `DeviceListBean`. There is no product-id
branch in the send path.

| Parameter | Official source |
| --- | --- |
| `isFog` | `DeviceListBean.isFogPlatform()` → `platform == 2 \|\| platform == 3` |
| `isCombo` | `DeviceListBean.isUseComboProtocol()` → `is_combo && "combo_V1.0".equals(protocolVersion)` |

**Fog is checked first.** In the official app, **platform 2 and platform 3 both
use Fog HTTP**. `{2, 17, cmd, value}` is sent only when `isFog == false` **and**
the Combo flag is true. That is typically **platform 1 + `is_combo` +
`protocol_version == combo_V1.0`**, not `platform == 3`.

Receive uses the same `isFogPlatform()` test: `SubscribeDeviceStates.registerDevice`
returns immediately for Fog and does not register Classic `status/hex` topics
on `DeYeMqttManager`.

Device-list API already includes `is_combo` and `protocol_version` (see
`DeyeApiResponseDeviceInfo`).

## Fog (`platform == 2`, and official `platform == 3`)

- Send: HTTP POST `set/properties`, body `{device_id, params:{...}}`.
  `DeYeFogMqttManager.sendSingleMsg` calls `DeYeHttpRequestManager.setFogProperties`.
  It is **not** an MQTT publish.
- Active query: HTTP `enduser/get/properties/`
- Fog MQTT topic `fogcloud/app/{username}/sub` is inbound only:
  `device_data` / `device_status`
- `FogDeviceManager.checkNeedAll`: when the cached
  `PropertyResultBean.protocolVersion == 0`, send every current param;
  otherwise send only changed fields
- Fields included only when non-null: Power, Mode, WindSpeed, SetHumidity,
  KeyLock, NegativeIon, SwingingWind, WaterPump, Sleep, SetTemperature, UV,
  PromptSound, Screendisplay, TimedOffHour

Account check: small dehumidifier `DYD-E12A3` reports `ProtocolVersion=0`, so
the official path sends full params. Humidity `25 → 30 → 25` while powered off
succeeded.

## Classic (`platform == 1`, and not Combo)

- Send: full 10-byte MQTT (`libdeye` `DeyeDeviceCommand.to_bytes()`)
- Topic: `{endpoint}/{productId}/{deviceId}/command/hex`
- Active query: `b"\x00\x01"` on the same Classic MQTT command topic
- Receive: `status/hex` + `online/json`

Account check: large dehumidifier `DYD-W20A3-京鱼座` query succeeded,
`power=off`.

The Classic topic suffix is still `StubApp.getString2(44821)` in the APK and
was not statically decoded. Live traffic on this account is `/command/hex` and
`/status/hex`.

## Combo frames (`isUseComboProtocol()`, not `platform == 3`)

`CommandManger.sendSingleCommand` wraps inner `{17, cmd, value}` as
`{2, 17, cmd, value}` and publishes through Classic `DeYeMqttManager`, not Fog
HTTP.

Official opcodes:

| Property | cmd | Frame example |
| --- | --- | --- |
| Power | 1 | `02 11 01 00/01` |
| Oscillating | 2 | `02 11 02 00/01` |
| Child lock | 3 | `02 11 03 00/01` |
| Water pump | 6 | `02 11 06 00/01` |
| Anion | 7 | `02 11 07 00/01` |
| Mode | 8 | `02 11 08 <mode>` |
| Fan | 9 | `02 11 09 <speed>` |
| Humidity or temp | 10 | `02 11 0a <value>` |
| Sleep | 15 | `02 11 0f <value>` |

Query stays Classic `b"\x00\x01"`. This account has no `is_combo` device and no
platform 3 device, so Combo send is JADX-only (no live send check).

## Product JSON labels

See [`unpacked/control_panel/dehumidifier/`](unpacked/control_panel/dehumidifier/).
P40: `Mode` 4 = turbo, `WindSpeed` 5 = auto.

Official send paths do not branch on product id for 612S / D50A3 / U20A3 /
V58A3 / U20Air / P40. Those `libdeye` product flags were report-side
workarounds, not APK send rules. Official P40 UI binds panel `Fan` to
`fan_switch` and `CompressorStatus` to `press_state`; send still uses
`WindSpeed`, not compressor status.
