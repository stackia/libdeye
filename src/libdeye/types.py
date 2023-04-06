"""Common types used in the library"""
from enum import IntEnum, IntFlag, auto
from typing import Any, TypedDict


class DeyeDeviceStateFlag(IntFlag):
    """Bit flags used in the state string"""

    ANION_SWITCH = auto()
    WATER_PUMP_SWITCH = auto()
    ELECTROMAGNETIC_STATE = auto()
    PRESS_STATE = auto()
    ENVIRONMENT_DEGREE = auto()
    _5 = auto()
    _6 = auto()
    _7 = auto()
    POWER_SWITCH = auto()
    OSCILLATING_SWITCH = auto()
    CHILD_LOCK_SWITCH = auto()
    POWEROFF_SWITCH = auto()  # Do not use this flag to check power off state
    POWERON_SWITCH = auto()  # Do not use this flag to check power on state
    DEFROSTING_STATE = auto()
    WATER_TANK_FULL_STATE = auto()
    FAN_RUNNING_STATE = auto()


class DeyeDeviceCommandFlag(IntFlag):
    """Bit flags used in the command"""

    POWER_SWITCH = auto()
    OSCILLATING_SWITCH = auto()
    CHILD_LOCK_SWITCH = auto()
    POWEROFF_SWITCH = auto()
    POWERON_SWITCH = auto()
    WATER_PUMP_SWITCH = auto()
    ANION_SWITCH = auto()


class DeyeDeviceMode(IntEnum):
    """All supported mode"""

    MANUAL_MODE = 0
    CLOTHES_DRYER_MODE = 1
    AIR_PURIFIER_MODE = 2
    AUTO_MODE = 3
    UNKNOWN_MODE = 4
    UNKNOWN_MODE_2 = 5
    SLEEP_MODE = 6


class DeyeFanSpeed(IntEnum):
    """All supported fan speed"""

    STOPPED = 0
    LOW = 1
    MIDDLE = 2
    HIGH = 3
    FULL = 4


class DeyeProductConfig(TypedDict):
    """Feature config for a specific Deye product"""

    mode: list[DeyeDeviceMode]
    fan_speed: list[DeyeFanSpeed]
    min_target_humidity: int
    max_target_humidity: int
    anion: bool
    oscillating: bool
    water_pump: bool


class DeyeProductPartialConfig(TypedDict, total=False):
    """Feature config for a specific Deye product (partial)"""

    mode: list[DeyeDeviceMode]
    fan_speed: list[DeyeFanSpeed]
    min_target_humidity: int
    max_target_humidity: int
    anion: bool
    oscillating: bool
    water_pump: bool


class DeyeApiResponseEnvelopeMeta(TypedDict):
    """Meta information for the API message envelope"""

    code: int
    message: str


class DeyeApiResponseEnvelope(TypedDict):
    """Message envelope for all API responses"""

    meta: DeyeApiResponseEnvelopeMeta
    data: Any


class DeyeApiResponseMqttInfo(TypedDict):
    """MQTT information returned by the API"""

    password: str
    loginname: str
    mqtthost: str
    mqttport: int
    clientid: str
    endpoint: str
    sslport: int


class DeyeApiResponseDeviceInfo(TypedDict):
    """Device information returned by the API"""

    producttype_id: int
    device_name: str
    product_name: str
    mac: str
    protocol_version: str
    gatewaytype: int
    is_combo: bool
    alias: str
    deviceid: str
    product_id: str
    role: int
    device_id: str
    product_icon: str
    online: bool
    product_type: str
    payload: Any
