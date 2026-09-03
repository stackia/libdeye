"""Tests for product feature configuration and official transport routing."""

import json
from pathlib import Path

from libdeye.cloud_api import (
    DeyeApiResponseDeviceInfo,
    DeyeDeviceTransport,
    DeyeIotPlatform,
    device_uses_combo_protocol,
    device_uses_fog_platform,
    transport_for_device,
)
from libdeye.const import DeyeDeviceMode, DeyeFanSpeed, get_product_feature_config

_OFFICIAL_JSON_DIR = (
    Path(__file__).resolve().parents[1]
    / "reverse-engineering"
    / "unpacked"
    / "control_panel"
    / "dehumidifier"
)

# product_id → official control-panel JSON. IDs for ES25A3 / P30 / B13A3 / RT12
# come from plaintext ``DeviceListBean.isFanDevice`` (Java String.hashCode
# matches PanelHelper / EquipmentFragment).
_PRODUCT_OFFICIAL_JSON = {
    "d71936c6951c11f0a8200242ac480009": "DeYeP40A3.json",
    "07dddba41c3011e8829100163e0f811e": "DeYe612S.json",
    "e69a5f54983f11ec964d0242ac480009": "DeYeB12A3.json",
    "c56f9e0c7d2b11e9829100163e0f811e": "DeYeD50A3.json",
    "86cec9fc5c9811e8829100163e0f811e": "DeYeD50B3.json",
    "c2c2d92c049f11e8829100163e0f811e": "DeYeE12A3.json",
    "8d52bc78f38511e89d4c00163e0c1b21": "DeYeG25A3.json",
    "a3850ae49ea511e89d4c00163e0c1b21": "DeYeN20A3.json",
    "5ea0feae4b1111ebb73c0242ac480009": "DeYeRLS48A3.json",
    "2c4bd0861c3011e89d4c00163e0c1b21": "DeYeT22A3.json",
    "6f97c340a43011e7829100163e0f811e": "DeYeTM208.json",
    "20eae2ea268511e8829100163e0f811e": "DeYeU20A3.json",
    "363b686a31ee11efb7203b3cd9717242": "DeYeU20Air.json",
    "2b770cba268611e89d4c00163e0c1b21": "DeYeV58A3.json",
    "17ab051af38611e89d4c00163e0c1b21": "DeYeW20A3.json",
    "06e8c86cca0811e99d4c00163e0c1b21": "DeYeW20A3.json",
    "d74ab1167d9f11e8829100163e0f811e": "DeYeX20A3.json",
    "ff71de22187111e99d4c00163e0c1b21": "DeYeZ12A3.json",
    "1b351ce6187211e99d4c00163e0c1b21": "DeYeZ20B3.json",
    "82547192d2a811e99d4c00163e0c1b21": "DeYeZ20B3.json",
    "32c309aa779011ed8cf00242ac480009": "DY890C.json",
    "764c37606bc711eea9b10242ac480009": "DY890T.json",
    "edd9a010778f11ed97500242ac480009": "DY6138A.json",
    "246e3b9a779011ed9a5f0242ac480009": "DY8138C.json",
    "5b0033e0f65411ee880a0242ac480009": "DeYe6158EB.json",
    "be47762e6bc711eea54d0242ac480009": "DY8138T.json",
    "0c44950cc8b811efaf1d0242ac480009": "DeyeY16.json",
    "a83dfb084b4211f08c060242ac480009": "DYSC60Y.json",
    "744b6884fb294936b4f73f427507aaa3": "DeYeA10.json",
    "be8f5e6a893111f0aebc0242ac480009": "DeYeES25A3.json",
    "537ed2b4d4c111f080e00242ac480009": "DeYeP30.json",
    "e2d2d33ad99311f0abd90242ac480009": "DeYeB13A3.json",
    "ef387edadb1011f0830f0242ac480009": "DeYeRT12.json",
    "7faf2a66c8b811efb3a50242ac480009": "DeyeC65DZ.json",
}



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
    assert default_config["uv"] is False
    assert default_config["prompt_sound"] is False
    assert default_config["screen_display"] is False
    assert default_config["timed_off"] is False
    assert default_config["min_target_humidity"] == 25
    assert default_config["max_target_humidity"] == 80
    assert DeyeDeviceMode.MANUAL_MODE in default_config["mode"]
    assert DeyeDeviceMode.TURBO_MODE not in default_config["mode"]
    assert DeyeFanSpeed.FULL in default_config["fan_speed"]
    assert get_product_feature_config("invalid id") == default_config

    e12a3 = get_product_feature_config("c2c2d92c049f11e8829100163e0f811e")
    assert e12a3["fan_speed"] == []
    assert e12a3["anion"] is False
    assert e12a3["uv"] is False
    assert e12a3["prompt_sound"] is False
    assert e12a3["screen_display"] is False
    assert e12a3["timed_off"] is False
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
    assert p40["uv"] is True
    assert p40["prompt_sound"] is True
    assert p40["screen_display"] is True
    assert p40["timed_off"] is False

    a10 = get_product_feature_config("744b6884fb294936b4f73f427507aaa3")
    assert a10["uv"] is False
    assert a10["prompt_sound"] is True
    assert a10["screen_display"] is True
    assert a10["timed_off"] is True
    assert DeyeDeviceMode.SLEEP_MODE in a10["mode"]

    es25a3 = get_product_feature_config("be8f5e6a893111f0aebc0242ac480009")
    assert es25a3["anion"] is True
    assert es25a3["oscillating"] is True
    assert es25a3["prompt_sound"] is True
    assert es25a3["screen_display"] is True
    assert es25a3["timed_off"] is True
    assert DeyeDeviceMode.AIR_PURIFIER_MODE in es25a3["mode"]

    p30 = get_product_feature_config("537ed2b4d4c111f080e00242ac480009")
    assert p30["oscillating"] is True
    assert p30["anion"] is False
    assert p30["prompt_sound"] is True
    assert p30["screen_display"] is True
    assert p30["timed_off"] is True
    assert DeyeDeviceMode.SLEEP_MODE in p30["mode"]
    assert DeyeDeviceMode.AIR_PURIFIER_MODE not in p30["mode"]

    b13a3 = get_product_feature_config("e2d2d33ad99311f0abd90242ac480009")
    assert b13a3["anion"] is True
    assert b13a3["oscillating"] is False
    assert b13a3["prompt_sound"] is True
    assert b13a3["timed_off"] is True
    assert b13a3["fan_speed"] == [DeyeFanSpeed.LOW, DeyeFanSpeed.HIGH]

    rt12 = get_product_feature_config("ef387edadb1011f0830f0242ac480009")
    assert rt12["anion"] is False
    assert rt12["prompt_sound"] is True
    assert rt12["screen_display"] is True
    assert rt12["timed_off"] is True

    c65dz = get_product_feature_config("7faf2a66c8b811efb3a50242ac480009")
    assert c65dz["uv"] is True
    assert c65dz["prompt_sound"] is False
    assert c65dz["screen_display"] is False
    assert c65dz["timed_off"] is False
    assert DeyeDeviceMode.AIR_PURIFIER_MODE in c65dz["mode"]

    tm208 = get_product_feature_config("6f97c340a43011e7829100163e0f811e")
    assert tm208["oscillating"] is True


def test_product_flags_match_official_control_panel_json() -> None:
    """anion/oscillating/pump/uv/tone/display/delayer follow official JSON keys."""
    for product_id, filename in _PRODUCT_OFFICIAL_JSON.items():
        payload = json.loads((_OFFICIAL_JSON_DIR / filename).read_text())
        config = get_product_feature_config(product_id)
        assert config["anion"] is ("anion" in payload), filename
        assert config["oscillating"] is ("swingWind" in payload), filename
        assert config["water_pump"] is ("waterPump" in payload), filename
        assert config["uv"] is ("uvLight" in payload), filename
        assert config["prompt_sound"] is ("tone" in payload), filename
        assert config["screen_display"] is ("displayScreen" in payload), filename
        assert config["timed_off"] is bool(payload.get("hasDelayer")), filename


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
