from libdeye.utils import get_product_feature_config


def test_get_product_feature_config() -> None:
    """get_product_feature_config() should merge default config with product-specific config"""
    assert get_product_feature_config("default") is not None
    config = get_product_feature_config("c2c2d92c049f11e8829100163e0f811e")
    assert "fan_speed" in config
    assert len(config["fan_speed"]) == 0
    assert get_product_feature_config("invalid id") == get_product_feature_config(
        "default"
    )
