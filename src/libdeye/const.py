"""Constants for the Deye Cloud API."""

from enum import IntEnum
from typing import TypedDict

DEYE_API_END_USER_ENDPOINT = "https://api.deye.com.cn/v3/enduser"
DEYE_LOGIN_PARAM_APP_ID = "a774310e-a430-11e7-9d4c-00163e0c1b21"
DEYE_LOGIN_PARAM_EXTEND = '{"cid":"63d5b0df098443db906f857003f29d12","type":"1"}'
QUERY_DEVICE_STATE_COMMAND_CLASSIC = b"\x00\x01"
COMBO_PROTOCOL_VERSION = "combo_V1.0"


class DeyeDeviceMode(IntEnum):
    """Device working modes from official control-panel JSON ``mode.value``."""

    MANUAL_MODE = 0
    CLOTHES_DRYER_MODE = 1
    AIR_PURIFIER_MODE = 2
    AUTO_MODE = 3
    TURBO_MODE = 4
    UNKNOWN_MODE_2 = 5
    SLEEP_MODE = 6
    MANUAL_PURIFIER_MODE = 7
    SLEEP_PURIFIER_MODE = 8
    AUTO_PURIFIER_MODE = 9


class DeyeFanSpeed(IntEnum):
    """Fan speeds from official control-panel JSON ``speed.value``."""

    STOPPED = 0
    LOW = 1
    MIDDLE = 2
    HIGH = 3
    FULL = 4
    AUTO = 5


class DeyeProductConfig(TypedDict):
    """Feature config for a specific Deye product."""

    mode: list[DeyeDeviceMode]
    fan_speed: list[DeyeFanSpeed]
    min_target_humidity: int
    max_target_humidity: int
    anion: bool
    oscillating: bool
    water_pump: bool
    uv: bool
    prompt_sound: bool
    screen_display: bool
    timed_off: bool


class DeyeProductPartialConfig(TypedDict, total=False):
    """Feature config for a specific Deye product (partial)."""

    mode: list[DeyeDeviceMode]
    fan_speed: list[DeyeFanSpeed]
    min_target_humidity: int
    max_target_humidity: int
    anion: bool
    oscillating: bool
    water_pump: bool
    uv: bool
    prompt_sound: bool
    screen_display: bool
    timed_off: bool


# Mapped from official Deye Smart 4.2.1 ``control_panel/dehumidifier/*.json``.
# Official ``DehumidifierControlPanelUIAty.initViews`` hides a control when
# the JSON object is null; delayed shutdown uses ``hasDelayer``.
# ``anion`` / ``swingWind`` / ``waterPump`` / ``uvLight`` / ``tone`` /
# ``displayScreen`` / ``hasDelayer`` → ``anion`` / ``oscillating`` /
# ``water_pump`` / ``uv`` / ``prompt_sound`` / ``screen_display`` /
# ``timed_off``. Products without a dedicated JSON keep their previously
# known UI capabilities; extras inherit False.
PRODUCT_FEATURE_CONFIG: dict[str, DeyeProductPartialConfig] = {
    "d71936c6951c11f0a8200242ac480009": {  # DYD-P40
        "mode": [
            DeyeDeviceMode.AUTO_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.SLEEP_MODE,
            DeyeDeviceMode.AIR_PURIFIER_MODE,
            DeyeDeviceMode.TURBO_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.MIDDLE,
            DeyeFanSpeed.HIGH,
            DeyeFanSpeed.AUTO,
        ],
        "min_target_humidity": 40,
        "max_target_humidity": 70,
        "anion": True,
        "oscillating": True,
        "water_pump": False,
        "uv": True,
        "prompt_sound": True,
        "screen_display": True,
        "timed_off": False,
    },
    "07dddba41c3011e8829100163e0f811e": {  # 612S
        "mode": [],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
    },
    "775bd87e9bfc11eb9b040242ac480009": {  # 620S
        "mode": [],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
    },
    "441480dcf29611eca05a0242ac480009": {  # 8220C
        "mode": [
            DeyeDeviceMode.AUTO_MODE,
            DeyeDeviceMode.MANUAL_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 26,
        "max_target_humidity": 90,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "e69a5f54983f11ec964d0242ac480009": {  # B12A3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.AIR_PURIFIER_MODE,
            DeyeDeviceMode.SLEEP_MODE,
        ],
        "fan_speed": [],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
    },
    "c56f9e0c7d2b11e9829100163e0f811e": {  # D50A3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.SLEEP_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.MIDDLE,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "86cec9fc5c9811e8829100163e0f811e": {  # D50B3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.MIDDLE,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": False,
        "oscillating": False,
        "water_pump": True,
    },
    "c2c2d92c049f11e8829100163e0f811e": {  # E12A3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
        ],
        "fan_speed": [],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "8d52bc78f38511e89d4c00163e0c1b21": {  # G25A3
        "mode": [],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": False,
        "oscillating": True,
        "water_pump": False,
    },
    "a3850ae49ea511e89d4c00163e0c1b21": {  # N20A3
        "mode": [
            DeyeDeviceMode.AUTO_MODE,
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.MIDDLE,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 40,
        "max_target_humidity": 70,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
    },
    "5ea0feae4b1111ebb73c0242ac480009": {  # L48A3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "2c4bd0861c3011e89d4c00163e0c1b21": {  # T22A3
        "mode": [
            DeyeDeviceMode.AUTO_MODE,
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.AIR_PURIFIER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.MIDDLE,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
    },
    "6f97c340a43011e7829100163e0f811e": {  # TM208FC
        "mode": [
            DeyeDeviceMode.AUTO_MODE,
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.AIR_PURIFIER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": True,
        "oscillating": True,
        "water_pump": False,
    },
    "20eae2ea268511e8829100163e0f811e": {  # U20A3
        "mode": [
            DeyeDeviceMode.AUTO_MODE,
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.AIR_PURIFIER_MODE,
            DeyeDeviceMode.SLEEP_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.MIDDLE,
            DeyeFanSpeed.HIGH,
            DeyeFanSpeed.FULL,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
    },
    "363b686a31ee11efb7203b3cd9717242": {  # U20Air
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.SLEEP_MODE,
        ],
        "fan_speed": [],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
    },
    "2b770cba268611e89d4c00163e0c1b21": {  # V58A3
        "mode": [
            DeyeDeviceMode.AUTO_MODE,
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.AIR_PURIFIER_MODE,
            DeyeDeviceMode.SLEEP_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.MIDDLE,
            DeyeFanSpeed.HIGH,
            DeyeFanSpeed.FULL,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
    },
    "17ab051af38611e89d4c00163e0c1b21": {  # W20A3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "06e8c86cca0811e99d4c00163e0c1b21": {  # W20A3-京鱼座
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "d74ab1167d9f11e8829100163e0f811e": {  # X20A3
        "mode": [
            DeyeDeviceMode.AUTO_MODE,
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.SLEEP_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.MIDDLE,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
    },
    "ff71de22187111e99d4c00163e0c1b21": {  # Z12A3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "1b351ce6187211e99d4c00163e0c1b21": {  # Z20B3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "82547192d2a811e99d4c00163e0c1b21": {  # Z20B3-天猫精灵
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "32c309aa779011ed8cf00242ac480009": {  # 890C
        "mode": [
            DeyeDeviceMode.AUTO_MODE,
            DeyeDeviceMode.MANUAL_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 26,
        "max_target_humidity": 90,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "764c37606bc711eea9b10242ac480009": {  # 890T
        "mode": [
            DeyeDeviceMode.AUTO_MODE,
            DeyeDeviceMode.MANUAL_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 26,
        "max_target_humidity": 90,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "edd9a010778f11ed97500242ac480009": {  # 6138A
        "mode": [
            DeyeDeviceMode.AUTO_MODE,
            DeyeDeviceMode.MANUAL_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 26,
        "max_target_humidity": 90,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "246e3b9a779011ed9a5f0242ac480009": {  # 8138C
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.AUTO_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 26,
        "max_target_humidity": 90,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "5b0033e0f65411ee880a0242ac480009": {  # 8158C
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.AUTO_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 26,
        "max_target_humidity": 90,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "be47762e6bc711eea54d0242ac480009": {  # 8158T
        "mode": [
            DeyeDeviceMode.AUTO_MODE,
            DeyeDeviceMode.MANUAL_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 26,
        "max_target_humidity": 90,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "db6707b2268911e8829100163e0f811e": {  # S12A3
        "mode": [],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
    },
    "720618be0e4e11e99d4c00163e0c1b21": {  # F20C3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.AIR_PURIFIER_MODE,
            DeyeDeviceMode.AUTO_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
    },
    "b767729a234e11e8829100163e0f811e": {  # JD121EC
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
        ],
        "fan_speed": [],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "fcda68cc6a1211e8829100163e0f811e": {  # JD201FC
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.AIR_PURIFIER_MODE,
            DeyeDeviceMode.AUTO_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
    },
    "0c44950cc8b811efaf1d0242ac480009": {  # Y16A3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.AUTO_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.SLEEP_MODE,
            DeyeDeviceMode.AIR_PURIFIER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
    },
    "a83dfb084b4211f08c060242ac480009": {  # SC60Y
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.AUTO_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.SLEEP_MODE,
            DeyeDeviceMode.AIR_PURIFIER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 80,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
    },
    "744b6884fb294936b4f73f427507aaa3": {  # A10
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.SLEEP_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.MIDDLE,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 40,
        "max_target_humidity": 70,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
        "uv": False,
        "prompt_sound": True,
        "screen_display": True,
        "timed_off": True,
    },
    "be8f5e6a893111f0aebc0242ac480009": {  # ES25A3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.SLEEP_MODE,
            DeyeDeviceMode.AIR_PURIFIER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.MIDDLE,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 40,
        "max_target_humidity": 70,
        "anion": True,
        "oscillating": True,
        "water_pump": False,
        "uv": False,
        "prompt_sound": True,
        "screen_display": True,
        "timed_off": True,
    },
    "537ed2b4d4c111f080e00242ac480009": {  # P30
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.SLEEP_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.MIDDLE,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 40,
        "max_target_humidity": 70,
        "anion": False,
        "oscillating": True,
        "water_pump": False,
        "uv": False,
        "prompt_sound": True,
        "screen_display": True,
        "timed_off": True,
    },
    "e2d2d33ad99311f0abd90242ac480009": {  # B13A3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.SLEEP_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 40,
        "max_target_humidity": 70,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
        "uv": False,
        "prompt_sound": True,
        "screen_display": True,
        "timed_off": True,
    },
    "ef387edadb1011f0830f0242ac480009": {  # RT12
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.SLEEP_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.MIDDLE,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 40,
        "max_target_humidity": 70,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
        "uv": False,
        "prompt_sound": True,
        "screen_display": True,
        "timed_off": True,
    },
    "7faf2a66c8b811efb3a50242ac480009": {  # C65DZ
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.AIR_PURIFIER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 26,
        "max_target_humidity": 80,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
        "uv": True,
        "prompt_sound": False,
        "screen_display": False,
        "timed_off": False,
    },
}


def get_product_feature_config(product_id: str) -> DeyeProductConfig:
    """Get supported features of the product.

    Unknown products use ``DeYeDehumidifierModel.json``: modes 0/1/2/3/6,
    fan speeds 1-4, humidity 25-80, anion, no oscillating, water pump, UV,
    prompt sound, screen display, or timed off.
    """
    default: DeyeProductConfig = {
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.AIR_PURIFIER_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.AUTO_MODE,
            DeyeDeviceMode.SLEEP_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.MIDDLE,
            DeyeFanSpeed.HIGH,
            DeyeFanSpeed.FULL,
        ],
        "min_target_humidity": 25,
        "max_target_humidity": 80,
        "anion": True,
        "oscillating": False,
        "water_pump": False,
        "uv": False,
        "prompt_sound": False,
        "screen_display": False,
        "timed_off": False,
    }
    try:
        return default | PRODUCT_FEATURE_CONFIG[product_id]
    except KeyError:
        return default
