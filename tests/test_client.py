"""Tests for the high-level DeyeClient / DeyeDevice API."""

from typing import cast
from unittest.mock import AsyncMock, MagicMock, patch

import pytest

from libdeye.client import DeyeClient, DeyeDevice
from libdeye.cloud_api import (
    DeyeApiResponseDeviceInfo,
    DeyeCloudApi,
    DeyeDeviceTransport,
    DeyeIotPlatform,
)
from libdeye.const import DeyeFanSpeed
from libdeye.device_state import DeyeDeviceState
from libdeye.mqtt_client import (
    DeyeClassicMqttClient,
    DeyeFogMqttClient,
    fog_full_snapshot_from_properties,
)


def _info(
    platform: int = DeyeIotPlatform.Classic,
    *,
    is_combo: bool = False,
    protocol_version: str = "1.0",
    product_id: str = "c2c2d92c049f11e8829100163e0f811e",
    payload: object = "14118100113B00000000000000000040300000000000",
) -> DeyeApiResponseDeviceInfo:
    return cast(
        DeyeApiResponseDeviceInfo,
        {
            "producttype_id": 1,
            "device_name": "Test Device",
            "product_name": "E12A3",
            "platform": platform,
            "mac": "00:11:22:33:44:55",
            "protocol_version": protocol_version,
            "gatewaytype": 1,
            "is_combo": is_combo,
            "alias": "",
            "deviceid": "device456",
            "product_id": product_id,
            "role": 1,
            "device_id": "device456",
            "product_icon": "",
            "online": True,
            "product_type": "除湿机",
            "payload": payload,
            "picture_v3": "",
            "work_time": 0,
            "user_count": 1,
        },
    )


@pytest.mark.asyncio
async def test_list_devices_wraps_cloud_entries() -> None:
    """list_devices returns DeyeDevice wrappers for each cloud entry."""
    cloud_api = MagicMock(spec=DeyeCloudApi)
    cloud_api.get_device_list = AsyncMock(return_value=[_info()])
    client = DeyeClient(cloud_api)

    devices = await client.list_devices()

    assert len(devices) == 1
    assert devices[0].device_id == "device456"
    assert devices[0].transport is DeyeDeviceTransport.CLASSIC
    assert devices[0].feature_config["fan_speed"] == []


@pytest.mark.asyncio
async def test_device_refresh_and_apply_use_pooled_mqtt() -> None:
    """Refresh queries through MQTT; apply sends the command with a baseline."""
    cloud_api = MagicMock(spec=DeyeCloudApi)
    client = DeyeClient(cloud_api)
    device = DeyeDevice(client, _info())
    state = DeyeDeviceState("14118100113B00000000000000000040300000000000")
    mqtt = MagicMock(spec=DeyeClassicMqttClient)
    mqtt.query_device_state = AsyncMock(return_value=state)
    mqtt.publish_command = AsyncMock()
    mqtt.connect = AsyncMock()

    with patch(
        "libdeye.client.mqtt_client_type_for_device",
        return_value=MagicMock(return_value=mqtt),
    ):
        result = await device.refresh()
        command = result.to_command()
        command.fan_speed = DeyeFanSpeed.HIGH
        await device.apply(command, baseline=result)

    mqtt.query_device_state.assert_awaited_once_with(
        device.product_id, device.device_id
    )
    mqtt.publish_command.assert_awaited_once()
    published = mqtt.publish_command.await_args
    assert published.args[2] == command
    assert published.kwargs["baseline"] == result


@pytest.mark.asyncio
async def test_platform_3_uses_fog_transport() -> None:
    """Official isFogPlatform includes platform 3."""
    device = DeyeDevice(
        DeyeClient(MagicMock(spec=DeyeCloudApi)),
        _info(DeyeIotPlatform.FogCombo),
    )
    assert device.transport is DeyeDeviceTransport.FOG


@pytest.mark.asyncio
async def test_combo_flags_on_classic_use_combo_transport() -> None:
    """Combo MQTT is is_combo + combo_V1.0 when the device is not Fog."""
    device = DeyeDevice(
        DeyeClient(MagicMock(spec=DeyeCloudApi)),
        _info(
            DeyeIotPlatform.Classic,
            is_combo=True,
            protocol_version="combo_V1.0",
        ),
    )
    assert device.transport is DeyeDeviceTransport.COMBO


@pytest.mark.asyncio
async def test_request_refresh_fog_posts_real_data_poll() -> None:
    """Fog request_refresh POSTs official RealData=1 and does not wait."""
    cloud_api = MagicMock(spec=DeyeCloudApi)
    cloud_api.poll_fog_platform_device_properties = AsyncMock()
    client = DeyeClient(cloud_api)
    device = DeyeDevice(client, _info(DeyeIotPlatform.Fog))
    mqtt = MagicMock(spec=DeyeFogMqttClient)
    mqtt.connect = AsyncMock()

    with patch(
        "libdeye.client.mqtt_client_type_for_device",
        return_value=MagicMock(return_value=mqtt),
    ):
        result = await device.request_refresh()

    assert result is None
    mqtt.query_device_state.assert_not_called()
    cloud_api.poll_fog_platform_device_properties.assert_awaited_once_with(
        device.device_id
    )


@pytest.mark.asyncio
async def test_request_refresh_classic_publishes_poll_bytes() -> None:
    """Classic request_refresh publishes 00 01 and does not wait."""
    cloud_api = MagicMock(spec=DeyeCloudApi)
    client = DeyeClient(cloud_api)
    device = DeyeDevice(client, _info(DeyeIotPlatform.Classic))
    mqtt = MagicMock(spec=DeyeClassicMqttClient)
    mqtt.publish_command = AsyncMock()
    mqtt.connect = AsyncMock()

    with patch(
        "libdeye.client.mqtt_client_type_for_device",
        return_value=MagicMock(return_value=mqtt),
    ):
        result = await device.request_refresh()

    assert result is None
    mqtt.publish_command.assert_awaited_once()


def test_subscribe_requires_connection() -> None:
    """subscribe() before MQTT connect raises RuntimeError."""
    device = DeyeDevice(DeyeClient(MagicMock(spec=DeyeCloudApi)), _info())
    with pytest.raises(RuntimeError, match="ensure_connected"):
        device.subscribe(on_state=lambda _state: None)


@pytest.mark.asyncio
async def test_subscribe_forwards_mqtt_callbacks() -> None:
    """subscribe() stores MQTT callbacks and unsubscribes them."""
    cloud_api = MagicMock(spec=DeyeCloudApi)
    client = DeyeClient(cloud_api)
    device = DeyeDevice(client, _info())
    mqtt = MagicMock(spec=DeyeClassicMqttClient)
    mqtt.connect = AsyncMock()
    unsub_state = MagicMock()
    unsub_avail = MagicMock()
    mqtt.subscribe_state_change = MagicMock(return_value=unsub_state)
    mqtt.subscribe_availability_change = MagicMock(return_value=unsub_avail)

    with patch(
        "libdeye.client.mqtt_client_type_for_device",
        return_value=MagicMock(return_value=mqtt),
    ):
        await device.ensure_connected()
        unsubscribe = device.subscribe(
            on_state=lambda _state: None,
            on_availability=lambda _available: None,
        )
        unsubscribe()

    mqtt.subscribe_state_change.assert_called_once()
    mqtt.subscribe_availability_change.assert_called_once()
    unsub_state.assert_called_once()
    unsub_avail.assert_called_once()


@pytest.mark.asyncio
async def test_from_credentials_authenticate_get_device_and_disconnect() -> None:
    """Cover DeyeClient credential, lookup, and MQTT pool helpers."""
    session = MagicMock()
    cloud_api = MagicMock(spec=DeyeCloudApi)
    cloud_api.authenticate = AsyncMock()
    cloud_api.get_device_list = AsyncMock(return_value=[_info()])

    with patch("libdeye.client.DeyeCloudApi", return_value=cloud_api):
        client = DeyeClient.from_credentials(session, "user", "pass")
    await client.authenticate()
    found = await client.get_device("device456")
    missing = await client.get_device("missing")

    mqtt = MagicMock(spec=DeyeClassicMqttClient)
    mqtt.connect = AsyncMock()
    mqtt.disconnect = MagicMock()
    with patch(
        "libdeye.client.mqtt_client_type_for_device",
        return_value=MagicMock(return_value=mqtt),
    ):
        assert found is not None
        await found.ensure_connected()
        client.disconnect()

    cloud_api.authenticate.assert_awaited_once()
    assert found is not None
    assert found.name == "Test Device"
    assert missing is None
    mqtt.disconnect.assert_called_once()
    assert client._mqtt_by_type == {}


def test_state_from_fog_payload_and_empty_fallback() -> None:
    """Fog JSON payload is parsed; empty payload uses the Classic default."""
    fog_payload = {
        "Power": 1,
        "Mode": 0,
        "WindSpeed": 1,
        "SetHumidity": 40,
        "CurrentAmbientTemperature": 22,
        "CurrentEnvironmentalHumidity": 50,
        "NegativeIon": 0,
        "WaterPump": 0,
        "SwingingWind": 1,
        "KeyLock": 0,
        "Demisting": 0,
        "WaterTank": 0,
        "Fan": 1,
        "CurrentCoilTemperature": 22,
        "CurrentExhaustTemperature": 22,
    }
    fog_device = DeyeDevice(
        DeyeClient(MagicMock(spec=DeyeCloudApi)),
        _info(DeyeIotPlatform.Fog, payload=fog_payload),
    )
    empty_device = DeyeDevice(
        DeyeClient(MagicMock(spec=DeyeCloudApi)),
        _info(payload=""),
    )
    assert fog_device.reported_state.power_switch is True
    assert fog_device.reported_state.oscillating_switch is True
    assert empty_device.reported_state.power_switch is False


@pytest.mark.asyncio
async def test_apply_defaults_to_current_state_command() -> None:
    """apply() without a command sends state.to_command()."""
    client = DeyeClient(MagicMock(spec=DeyeCloudApi))
    device = DeyeDevice(client, _info())
    mqtt = MagicMock(spec=DeyeClassicMqttClient)
    mqtt.connect = AsyncMock()
    mqtt.publish_command = AsyncMock()

    with patch(
        "libdeye.client.mqtt_client_type_for_device",
        return_value=MagicMock(return_value=mqtt),
    ):
        await device.apply()

    published = mqtt.publish_command.await_args
    assert published.args[2] == device.state.to_command()


@pytest.mark.asyncio
async def test_request_refresh_classic_rejects_non_classic_mqtt() -> None:
    """Classic poll requires a Classic MQTT client."""
    client = DeyeClient(MagicMock(spec=DeyeCloudApi))
    device = DeyeDevice(client, _info(DeyeIotPlatform.Classic))
    mqtt = MagicMock(spec=DeyeFogMqttClient)
    mqtt.connect = AsyncMock()

    with (
        patch(
            "libdeye.client.mqtt_client_type_for_device",
            return_value=MagicMock(return_value=mqtt),
        ),
        pytest.raises(TypeError, match="Classic MQTT"),
    ):
        await device.request_refresh()


@pytest.mark.asyncio
async def test_subscribe_updates_reported_state_and_availability() -> None:
    """MQTT callbacks refresh DeyeDevice and forward to callers."""
    client = DeyeClient(MagicMock(spec=DeyeCloudApi))
    device = DeyeDevice(client, _info())
    mqtt = MagicMock(spec=DeyeClassicMqttClient)
    mqtt.connect = AsyncMock()
    mqtt.subscribe_state_change = MagicMock(return_value=MagicMock())
    mqtt.subscribe_availability_change = MagicMock(return_value=MagicMock())
    seen: dict[str, object] = {}

    with patch(
        "libdeye.client.mqtt_client_type_for_device",
        return_value=MagicMock(return_value=mqtt),
    ):
        await device.ensure_connected()
        device.subscribe(
            on_state=lambda state: seen.update(state=state),
            on_availability=lambda available: seen.update(available=available),
        )

    new_state = DeyeDeviceState("14118100113B00000000000000000040300000000000")
    mqtt.subscribe_state_change.call_args.args[2](new_state)
    mqtt.subscribe_availability_change.call_args.args[2](False)
    assert device.reported_state == new_state
    assert device.available is False
    assert seen["state"] == new_state
    assert seen["available"] is False


def test_fog_full_snapshot_parses_numbers_and_skips_invalid() -> None:
    """GET cache values may be ints, integer floats, or decimal strings."""
    snapshot = fog_full_snapshot_from_properties(
        {
            "Power": 1.0,
            "Sleep": True,
            "UV": "nope",
            "Mode": None,
            "SetHumidity": "45",
            "TimedShutdownHourSetting": "0",
        }
    )
    assert snapshot == {"Power": 1, "SetHumidity": 45, "TimedOffHour": 0}
