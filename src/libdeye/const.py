"""Constants for the Deye Cloud API."""
from .types import DeyeDeviceMode, DeyeFanSpeed, DeyeProductPartialConfig

DEYE_API_END_USER_ENDPOINT = "https://api.deye.com.cn/v3/enduser"
DEYE_LOGIN_PARAM_APP_ID = "a774310e-a430-11e7-9d4c-00163e0c1b21"
DEYE_LOGIN_PARAM_EXTEND = '{"cid":"63d5b0df098443db906f857003f29d12","type":"1"}'
QUERY_DEVICE_STATE_COMMAND = b"\x00\x01"


PRODUCT_FEATURE_CONFIG: dict[str, DeyeProductPartialConfig] = {
    "default": {
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.AIR_PURIFIER_MODE,
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
        "oscillating": True,
        "water_pump": True,
    },
    "07dddba41c3011e8829100163e0f811e": {  # 612S
        "mode": [],
        "fan_speed": [DeyeFanSpeed.LOW, DeyeFanSpeed.HIGH],
        "oscillating": False,
        "water_pump": False,
    },
    "441480dcf29611eca05a0242ac480009": {  # 6158EB/6160A
        "mode": [DeyeDeviceMode.MANUAL_MODE, DeyeDeviceMode.AUTO_MODE],
        "fan_speed": [DeyeFanSpeed.LOW, DeyeFanSpeed.HIGH],
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
        "anion": False,
        "oscillating": False,
    },
    "c2c2d92c049f11e8829100163e0f811e": {  # E12A3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
        ],
        "fan_speed": [],
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
        "anion": False,
        "water_pump": False,
    },
    "a3850ae49ea511e89d4c00163e0c1b21": {  # N20A3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.AUTO_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.MIDDLE,
            DeyeFanSpeed.HIGH,
        ],
        "min_target_humidity": 30,
        "max_target_humidity": 70,
        "oscillating": False,
        "water_pump": False,
    },
    "5ea0feae4b1111ebb73c0242ac480009": {  # RLS48A3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "2c4bd0861c3011e89d4c00163e0c1b21": {  # T22A3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.AIR_PURIFIER_MODE,
            DeyeDeviceMode.AUTO_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.MIDDLE,
            DeyeFanSpeed.HIGH,
        ],
        "oscillating": False,
        "water_pump": False,
    },
    "6f97c340a43011e7829100163e0f811e": {  # TM208
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
        "oscillating": False,
        "water_pump": False,
    },
    "20eae2ea268511e8829100163e0f811e": {  # U20A3
        "oscillating": False,
        "water_pump": False,
    },
    "2b770cba268611e89d4c00163e0c1b21": {  # V58A3
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
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "06e8c86cca0811e99d4c00163e0c1b21": {  # W20A3-JD
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "d74ab1167d9f11e8829100163e0f811e": {  # X20A3
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
            DeyeDeviceMode.AUTO_MODE,
            DeyeDeviceMode.SLEEP_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.MIDDLE,
            DeyeFanSpeed.HIGH,
        ],
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
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "82547192d2a811e99d4c00163e0c1b21": {  # Z20B3-QMX
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "32c309aa779011ed8cf00242ac480009": {  # 890C
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.AUTO_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
        "max_target_humidity": 90,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "edd9a010778f11ed97500242ac480009": {  # 6138A
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.AUTO_MODE,
        ],
        "fan_speed": [
            DeyeFanSpeed.LOW,
            DeyeFanSpeed.HIGH,
        ],
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
        "max_target_humidity": 90,
        "anion": False,
        "oscillating": False,
        "water_pump": False,
    },
    "db6707b2268911e8829100163e0f811e": {  # S12A3
        "mode": [],
        "fan_speed": [DeyeFanSpeed.LOW, DeyeFanSpeed.HIGH],
        "oscillating": False,
        "water_pump": False,
    },
    "775bd87e9bfc11eb9b040242ac480009": {  # 620S
        "mode": [],
        "fan_speed": [DeyeFanSpeed.LOW, DeyeFanSpeed.HIGH],
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
        "oscillating": False,
        "water_pump": False,
    },
    "b767729a234e11e8829100163e0f811e": {  # JD121EC
        "mode": [
            DeyeDeviceMode.MANUAL_MODE,
            DeyeDeviceMode.CLOTHES_DRYER_MODE,
        ],
        "fan_speed": [],
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
        "oscillating": False,
        "water_pump": False,
    },
}
