# 官方 Classic / Fog / FogCombo 发送与接收

对照源码（JADX 4.2.1 脱壳 DEX）：

- [`unpacked/jadx/com/deye/CommandManger.java`](unpacked/jadx/com/deye/CommandManger.java)
- [`unpacked/jadx/com/deye/FogDeviceManager.java`](unpacked/jadx/com/deye/FogDeviceManager.java)
- [`unpacked/jadx-protocol/DeYeMqttManager.java`](unpacked/jadx-protocol/DeYeMqttManager.java)
- [`unpacked/jadx-protocol/DeYeFogMqttManager.java`](unpacked/jadx-protocol/DeYeFogMqttManager.java)
- [`unpacked/jadx/com/deye/combo/`](unpacked/jadx/com/deye/combo/)

`libdeye` 应按这些类实现，Home Assistant 只做薄调用。

## 路由（`CommandManger`）

发送函数签名都是 `(…, boolean isFog, boolean isCombo, DehumidifierBean bean)`。实现：

```
if (isFog)       → FogDeviceManager          // HTTP JSON properties
else if (isCombo) → sendSingleCommand        // bytes {2, 17, cmd, value} via DeYeMqttManager
else             → Classic DehumidifierBean  // 全量 JSON → hex MQTT
```

UI **全部**把这两个标志从 `DeviceListBean` 传进去，没有按 product id 特判：

| 参数 | 官方来源 |
| --- | --- |
| `isFog` | `DeviceListBean.isFogPlatform()` → `platform == 2 \|\| platform == 3` |
| `isCombo` | `DeviceListBean.isUseComboProtocol()` → `is_combo && "combo_V1.0".equals(protocolVersion)` |

**Fog 判断在前。** 所以官方 App 里 **platform 2 和 platform 3 都走 Fog HTTP**。`{2, 17, cmd, value}` 只在 `isFog == false` **并且** Combo 标志为真时才会发，典型是 **platform 1 + `is_combo` + `protocol_version == combo_V1.0`**，不是 `platform == 3`。

接收同样用 `isFogPlatform()`：`SubscribeDeviceStates.registerDevice` 在 Fog 时直接 return，不往 Classic `DeYeMqttManager` 注册 `status/hex` topic。

`libdeye` 当前按 `platform` 整数路由，**没有读** 设备列表上的 `is_combo` / `protocol_version`：

| `platform` | 官方 App（4.2.1） | `libdeye` 现在 |
| --- | --- | --- |
| 1 Classic | 10 字节 Classic MQTT | 10 字节 Classic MQTT |
| 2 Fog | Fog HTTP + Fog MQTT 收 | Fog HTTP + Fog MQTT 收 |
| 3 | Fog HTTP + Fog MQTT 收（因为 `isFog` 为真） | Classic MQTT `{2, 17, cmd, value}`（`DeyeFogComboMqttClient`） |

设备列表 API 已经带了 `is_combo` 和 `protocol_version`（见 `DeyeApiResponseDeviceInfo`），只是还没用来选客户端。本账号没有 platform 3 / Combo 设备，这条差异没有实机验证。

## Fog（platform 2）

- 下发：HTTP POST `set/properties`，body `{device_id, params:{...}}`。`DeYeFogMqttManager.sendSingleMsg` 实际走 `DeYeHttpRequestManager.setFogProperties`，**不是 MQTT publish**。
- 查询：`enduser/get/properties/`
- Fog MQTT topic `fogcloud/app/{username}/sub` 只收 `device_data` / `device_status`
- `FogDeviceManager.checkNeedAll`：缓存的 `PropertyResultBean.protocolVersion == 0` 时带上当前全部 param；否则只带变更项
- 非 null 才放进 payload 的字段：Power, Mode, WindSpeed, SetHumidity, KeyLock, NegativeIon, SwingingWind, WaterPump, Sleep, SetTemperature, UV, PromptSound, Screendisplay, TimedOffHour

账号实测：小除湿机 `DYD-E12A3` 报 `ProtocolVersion=0`，因此官方路径是全量 param。关机状态下改湿度 `25 → 30 → 25` 成功。

## Classic（platform 1）

- 下发：完整 10 字节 MQTT（`libdeye` `DeyeDeviceCommand.to_bytes()`）
- topic：`{endpoint}/{productId}/{deviceId}/command/hex`
- 查询：`QUERY_DEVICE_STATE_COMMAND_CLASSIC = b"\x00\x01"`
- 接收：`status/hex` + `online/json`

账号实测：大除湿机 `DYD-W20A3-京鱼座` 查询成功，`power=off`。

APK 里 Classic topic 后缀仍是 `StubApp.getString2(44821)`，未能静态解密；本账号线上行为是 `/command/hex` 与 `/status/hex`。

## Combo 字节帧（`isUseComboProtocol()`，不是 `platform == 3`）

`CommandManger.sendSingleCommand`：内层 `{17, cmd, value}`，发出去再包一层 `{2, 17, cmd, value}`。走 Classic `DeYeMqttManager`，不是 Fog HTTP。

官方 opcode：

| 属性 | cmd | 帧示例 |
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

查询仍是 Classic `\x00\x01`。本账号没有带 `is_combo` 的设备，也没有 platform 3 设备；`libdeye` 把 platform 3 映射到这些帧，只有 JADX + 单测，没有实机发送验证。

## 产品 JSON 命名

见 [`unpacked/control_panel/dehumidifier/`](unpacked/control_panel/dehumidifier/)。P40：`Mode` 4 = 急速，`WindSpeed` 5 = 自动。

官方发送路径不按 612S / D50A3 / U20A3 / V58A3 / U20Air / P40 的 product id 分支。`libdeye` 里这些产品旗标是字段上报 workaround，不是 APK 发送规则。P40 官方把面板 `Fan` 绑到 `fan_switch`、把 `CompressorStatus` 绑到 `press_state`，发送时不会用压缩机状态去代替 `WindSpeed`。
