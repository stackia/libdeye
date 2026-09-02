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
from libdeye.mqtt_client import DeyeClassicMqttClient, DeyeFogMqttClient


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
async def test_request_refresh_fog_uses_http_get() -> None:
    """Fog request_refresh performs HTTP GET via query_device_state."""
    cloud_api = MagicMock(spec=DeyeCloudApi)
    client = DeyeClient(cloud_api)
    device = DeyeDevice(client, _info(DeyeIotPlatform.Fog))
    state = DeyeDeviceState("14118100113B00000000000000000040300000000000")
    mqtt = MagicMock(spec=DeyeFogMqttClient)
    mqtt.query_device_state = AsyncMock(return_value=state)
    mqtt.connect = AsyncMock()

    with patch(
        "libdeye.client.mqtt_client_type_for_device",
        return_value=MagicMock(return_value=mqtt),
    ):
        result = await device.request_refresh()

    assert result == state
    mqtt.query_device_state.assert_awaited_once()


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
