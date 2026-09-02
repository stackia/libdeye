"""Python library for Deye Cloud and Deye dehumidifier devices."""

from importlib.metadata import PackageNotFoundError, version

from .client import DeyeClient, DeyeDevice
from .cloud_api import (
    DeyeCloudApi,
    DeyeCloudApiCannotConnectError,
    DeyeCloudApiInvalidAuthError,
    DeyeDeviceTransport,
    DeyeIotPlatform,
    transport_for_device,
)
from .const import DeyeDeviceMode, DeyeFanSpeed, get_product_feature_config
from .device_command import DeyeDeviceCommand
from .device_state import DeyeDeviceState

try:
    # Change here if project is renamed and does not equal the package name
    dist_name = __name__
    __version__ = version(dist_name)
except PackageNotFoundError:  # pragma: no cover
    __version__ = "unknown"
finally:
    del version, PackageNotFoundError

__all__ = [
    "DeyeClient",
    "DeyeCloudApi",
    "DeyeCloudApiCannotConnectError",
    "DeyeCloudApiInvalidAuthError",
    "DeyeDevice",
    "DeyeDeviceCommand",
    "DeyeDeviceMode",
    "DeyeDeviceState",
    "DeyeDeviceTransport",
    "DeyeFanSpeed",
    "DeyeIotPlatform",
    "get_product_feature_config",
    "transport_for_device",
]
