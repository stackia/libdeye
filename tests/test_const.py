from libdeye.cloud_api import DeyeIotPlatform, iot_platform_uses_fog_client
from libdeye.const import DeyeDeviceMode, DeyeFanSpeed, get_product_feature_config


def test_get_product_feature_config() -> None:
    """get_product_feature_config() should merge default config with product-specific config"""
    assert get_product_feature_config("default") is not None
    config = get_product_feature_config("c2c2d92c049f11e8829100163e0f811e")
    assert "fan_speed" in config
    assert len(config["fan_speed"]) == 0
    assert get_product_feature_config("invalid id") == get_product_feature_config(
        "default"
    )
    default_config = get_product_feature_config("default")
    assert default_config["full_state_fog_commands"] is False
    assert default_config["fan_reports_running_state"] is True

    u20a3_config = get_product_feature_config("20eae2ea268511e8829100163e0f811e")
    assert u20a3_config["requires_power_in_fog_partial_updates"] is True
    assert u20a3_config["requires_mode_in_fog_power_on_updates"] is False
    assert u20a3_config["omit_set_humidity_in_fog_auto_updates"] is False
    assert u20a3_config["single_property_fog_commands"] is False
    assert u20a3_config["full_state_fog_commands"] is False

    u20air_config = get_product_feature_config("363b686a31ee11efb7203b3cd9717242")
    assert u20air_config["requires_power_in_fog_partial_updates"] is False
    assert u20air_config["single_property_fog_commands"] is True

    v58a3_config = get_product_feature_config("2b770cba268611e89d4c00163e0c1b21")
    assert v58a3_config["requires_power_in_fog_partial_updates"] is True
    assert v58a3_config["requires_mode_in_fog_power_on_updates"] is True
    assert v58a3_config["omit_set_humidity_in_fog_auto_updates"] is True

    dyd_612s_config = get_product_feature_config("07dddba41c3011e8829100163e0f811e")
    assert dyd_612s_config["full_state_fog_commands"] is True

    d50a3_config = get_product_feature_config("c56f9e0c7d2b11e9829100163e0f811e")
    assert d50a3_config["full_state_fog_commands"] is True

    p40_config = get_product_feature_config("d71936c6951c11f0a8200242ac480009")
    assert p40_config["fan_reports_running_state"] is False
    assert p40_config["water_pump"] is False
    assert p40_config["min_target_humidity"] == 40
    assert p40_config["max_target_humidity"] == 70
    assert DeyeDeviceMode.MANUAL_MODE not in p40_config["mode"]
    assert DeyeFanSpeed.FULL not in p40_config["fan_speed"]
    assert DeyeFanSpeed.UNKNOWN_SPEED in p40_config["fan_speed"]
    assert p40_config["full_state_fog_commands"] is False


def test_iot_platform_uses_fog_client() -> None:
    """Non-classic platforms, including FogCombo, use the Fog client."""
    assert iot_platform_uses_fog_client(DeyeIotPlatform.Classic) is False
    assert iot_platform_uses_fog_client(DeyeIotPlatform.Fog) is True
    assert iot_platform_uses_fog_client(DeyeIotPlatform.FogCombo) is True
    assert iot_platform_uses_fog_client(3) is True
    assert iot_platform_uses_fog_client(4) is True
