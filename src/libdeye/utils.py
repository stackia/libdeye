"""Utility functions"""
from typing import cast

from .const import PRODUCT_FEATURE_CONFIG
from .types import DeyeProductConfig


def get_product_feature_config(product_id: str) -> DeyeProductConfig:
    """Get supported features of the product"""
    default = PRODUCT_FEATURE_CONFIG["default"]
    try:
        product_specific = PRODUCT_FEATURE_CONFIG[product_id]
        return cast(DeyeProductConfig, default | product_specific)
    except KeyError:
        return cast(DeyeProductConfig, default)
