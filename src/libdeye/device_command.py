"""Utilities for device command parsing"""

from enum import IntFlag, auto

from .const import (
    DeyeDeviceMode,
    DeyeFanSpeed,
)


class DeyeDeviceCommand:
    """A class to store the command to control the device"""

    def __init__(
        self,
        anion_switch: bool = False,
        water_pump_switch: bool = False,
        power_switch: bool = False,
        oscillating_switch: bool = False,
        child_lock_switch: bool = False,
        fan_speed: DeyeFanSpeed = DeyeFanSpeed.LOW,
        mode: DeyeDeviceMode = DeyeDeviceMode.MANUAL_MODE,
        target_humidity: int = 60,
    ) -> None:
        self.anion_switch = anion_switch
        self.water_pump_switch = water_pump_switch
        self.power_switch = power_switch
        self.oscillating_switch = oscillating_switch
        self.child_lock_switch = child_lock_switch
        self.fan_speed = fan_speed
        self.mode = mode
        self.target_humidity = target_humidity

    def __eq__(self, other: object) -> bool:
        """Check if two DeyeDeviceCommand instances are equal."""
        if not isinstance(other, DeyeDeviceCommand):
            return False

        return (
            self.anion_switch == other.anion_switch
            and self.water_pump_switch == other.water_pump_switch
            and self.power_switch == other.power_switch
            and self.oscillating_switch == other.oscillating_switch
            and self.child_lock_switch == other.child_lock_switch
            and self.fan_speed == other.fan_speed
            and self.mode == other.mode
            and self.target_humidity == other.target_humidity
        )

    def to_bytes(self) -> bytes:
        """Get binary representation of this command"""
        command_flag = DeyeDeviceCommandFlag(0)
        if self.anion_switch:
            command_flag |= DeyeDeviceCommandFlag.ANION_SWITCH
        if self.water_pump_switch:
            command_flag |= DeyeDeviceCommandFlag.WATER_PUMP_SWITCH
        if self.power_switch:
            command_flag |= DeyeDeviceCommandFlag.POWER_SWITCH
        if self.oscillating_switch:
            command_flag |= DeyeDeviceCommandFlag.OSCILLATING_SWITCH
        if self.child_lock_switch:
            command_flag |= DeyeDeviceCommandFlag.CHILD_LOCK_SWITCH

        return bytes(
            [
                0x08,
                0x02,
                command_flag,
                (self.fan_speed << 4) | self.mode,
                self.target_humidity,
                0,
                0,
                0,
                0,
                0,
            ]
        )

    def to_json(self) -> object:
        """Get JSON representation of this command"""
        return {
            "KeyLock": int(self.child_lock_switch),
            "Mode": int(self.mode),
            "Power": int(self.power_switch),
            "WindSpeed": int(self.fan_speed),
            "SetHumidity": self.target_humidity,
            "NegativeIon": int(self.anion_switch),
            "SwingingWind": int(self.oscillating_switch),
            "WaterPump": int(self.water_pump_switch),
        }


class DeyeDeviceCommandFlag(IntFlag):
    """Bit flags used in the command"""

    POWER_SWITCH = auto()
    OSCILLATING_SWITCH = auto()
    CHILD_LOCK_SWITCH = auto()
    POWEROFF_SWITCH = auto()
    POWERON_SWITCH = auto()
    WATER_PUMP_SWITCH = auto()
    ANION_SWITCH = auto()
