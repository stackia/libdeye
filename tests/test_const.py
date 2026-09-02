"""Tests for product feature configuration."""

from libdeye.const import get_product_feature_config


def test_get_product_feature_config() -> None:
    """get_product_feature_config() should merge default config with product-specific config."""
    assert get_product_feature_config("default") is not None
    config = get_product_feature_config("c2c2d92c049f11e8829100163e0f811e")
    assert "fan_speed" in config
    assert len(config["fan_speed"]) == 0
    assert get_product_feature_config("invalid id") == get_product_feature_config(
        "default"
    )

    u20a3_config = get_product_feature_config("20eae2ea268511e8829100163e0f811e")
    assert u20a3_config["requires_power_in_fog_partial_updates"] is True
    assert u20a3_config["requires_mode_in_fog_power_on_updates"] is False
    assert u20a3_config["omit_set_humidity_in_fog_auto_updates"] is False
    assert u20a3_config["single_property_fog_commands"] is False

    u20air_config = get_product_feature_config("363b686a31ee11efb7203b3cd9717242")
    assert u20air_config["requires_power_in_fog_partial_updates"] is False
    assert u20air_config["single_property_fog_commands"] is True

    v58a3_config = get_product_feature_config("2b770cba268611e89d4c00163e0c1b21")
    assert v58a3_config["requires_power_in_fog_partial_updates"] is True
    assert v58a3_config["requires_mode_in_fog_power_on_updates"] is True
    assert v58a3_config["omit_set_humidity_in_fog_auto_updates"] is True
