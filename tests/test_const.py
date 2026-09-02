"""Tests for product feature configuration and official transport routing."""

from libdeye.cloud_api import (
    DeyeApiResponseDeviceInfo,
    DeyeDeviceTransport,
    DeyeIotPlatform,
    device_uses_combo_protocol,
    device_uses_fog_platform,
    transport_for_device,
)
from libdeye.const import DeyeDeviceMode, DeyeFanSpeed, get_product_feature_config


def _device(
    platform: int,
    *,
    is_combo: bool = False,
    protocol_version: str = "1.0",
) -> DeyeApiResponseDeviceInfo:
    return {
        "producttype_id": 1,
        "device_name": "Test",
        "product_name": "Test",
        "platform": DeyeIotPlatform(platform) if platform in {1, 2, 3} else platform,  # type: ignore[typeddict-item]
        "mac": "00:00:00:00:00:00",
        "protocol_version": protocol_version,
        "gatewaytype": 1,
        "is_combo": is_combo,
        "alias": "",
        "deviceid": "id",
        "product_id": "product",
        "role": 1,
        "device_id": "id",
        "product_icon": "",
        "online": True,
        "product_type": "除湿机",
        "payload": {},
        "picture_v3": "",
        "work_time": 0,
        "user_count": 1,
    }


def test_get_product_feature_config() -> None:
    """Unknown products use DeYeDehumidifierModel.json; known products match JSON."""
    default_config = get_product_feature_config("default")
    assert default_config["anion"] is True
    assert default_config["oscillating"] is False
    assert default_config["water_pump"] is False
    assert default_config["min_target_humidity"] == 25
    assert default_config["max_target_humidity"] == 80
    assert DeyeDeviceMode.MANUAL_MODE in default_config["mode"]
    assert DeyeDeviceMode.TURBO_MODE not in default_config["mode"]
    assert DeyeFanSpeed.FULL in default_config["fan_speed"]
    assert get_product_feature_config("invalid id") == default_config

    e12a3 = get_product_feature_config("c2c2d92c049f11e8829100163e0f811e")
    assert e12a3["fan_speed"] == []
    assert e12a3["anion"] is False
    assert "full_state_fog_commands" not in e12a3

    u20a3 = get_product_feature_config("20eae2ea268511e8829100163e0f811e")
    assert u20a3["min_target_humidity"] == 30
    assert u20a3["oscillating"] is False

    u20air = get_product_feature_config("363b686a31ee11efb7203b3cd9717242")
    assert u20air["fan_speed"] == []
    assert u20air["mode"] == [
        DeyeDeviceMode.MANUAL_MODE,
        DeyeDeviceMode.CLOTHES_DRYER_MODE,
        DeyeDeviceMode.SLEEP_MODE,
    ]

    v58a3 = get_product_feature_config("2b770cba268611e89d4c00163e0c1b21")
    assert v58a3["min_target_humidity"] == 30
    assert DeyeFanSpeed.FULL in v58a3["fan_speed"]

    dyd_612s = get_product_feature_config("07dddba41c3011e8829100163e0f811e")
    assert dyd_612s["mode"] == []
    assert dyd_612s["fan_speed"] == [DeyeFanSpeed.LOW, DeyeFanSpeed.HIGH]

    d50a3 = get_product_feature_config("c56f9e0c7d2b11e9829100163e0f811e")
    assert d50a3["anion"] is False
    assert DeyeDeviceMode.SLEEP_MODE in d50a3["mode"]

    d50b3 = get_product_feature_config("86cec9fc5c9811e8829100163e0f811e")
    assert d50b3["water_pump"] is True

    p40 = get_product_feature_config("d71936c6951c11f0a8200242ac480009")
    assert p40["water_pump"] is False
    assert p40["oscillating"] is True
    assert p40["min_target_humidity"] == 40
    assert p40["max_target_humidity"] == 70
    assert DeyeDeviceMode.MANUAL_MODE not in p40["mode"]
    assert DeyeDeviceMode.TURBO_MODE in p40["mode"]
    assert DeyeFanSpeed.FULL not in p40["fan_speed"]
    assert DeyeFanSpeed.AUTO in p40["fan_speed"]

    tm208 = get_product_feature_config("6f97c340a43011e7829100163e0f811e")
    assert tm208["oscillating"] is True


def test_transport_for_device_follows_official_command_manger() -> None:
    """Platform 2/3 are Fog HTTP; Combo MQTT is is_combo + combo_V1.0 on Classic."""
    classic = _device(DeyeIotPlatform.Classic)
    fog = _device(DeyeIotPlatform.Fog)
    platform_3 = _device(DeyeIotPlatform.FogCombo)
    combo = _device(
        DeyeIotPlatform.Classic, is_combo=True, protocol_version="combo_V1.0"
    )
    combo_ignored_on_fog = _device(
        DeyeIotPlatform.FogCombo, is_combo=True, protocol_version="combo_V1.0"
    )
    unknown = _device(4)

    assert device_uses_fog_platform(classic) is False
    assert device_uses_fog_platform(fog) is True
    assert device_uses_fog_platform(platform_3) is True
    assert device_uses_combo_protocol(combo) is True
    assert device_uses_combo_protocol(classic) is False

    assert transport_for_device(classic) is DeyeDeviceTransport.CLASSIC
    assert transport_for_device(fog) is DeyeDeviceTransport.FOG
    assert transport_for_device(platform_3) is DeyeDeviceTransport.FOG
    assert transport_for_device(combo) is DeyeDeviceTransport.COMBO
    assert transport_for_device(combo_ignored_on_fog) is DeyeDeviceTransport.FOG
    assert transport_for_device(unknown) is DeyeDeviceTransport.CLASSIC
