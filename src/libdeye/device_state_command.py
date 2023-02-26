"""Utilities for device state/command parsing"""
from .types import (
    DeyeDeviceCommandFlag,
    DeyeDeviceMode,
    DeyeDeviceStateFlag,
    DeyeFanSpeed,
)


class DeyeDeviceCommand:
    """A class to store the parsed command"""

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

    def bytes(self) -> bytes:
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


class DeyeDeviceState:
    """A class to store the parse result of state string."""

    def __init__(self, state: str) -> None:
        state_hex = bytes.fromhex(state)
        state_flag = int.from_bytes(state_hex[2:4], byteorder="big")
        self.anion_switch = (state_flag & DeyeDeviceStateFlag.ANION_SWITCH) > 0
        self.water_pump_switch = (
            state_flag & DeyeDeviceStateFlag.WATER_PUMP_SWITCH
        ) > 0
        self.power_switch = (state_flag & DeyeDeviceStateFlag.POWER_SWITCH) > 0
        self.oscillating_switch = (
            state_flag & DeyeDeviceStateFlag.OSCILLATING_SWITCH
        ) > 0
        self.child_lock_switch = (
            state_flag & DeyeDeviceStateFlag.CHILD_LOCK_SWITCH
        ) > 0
        self.defrosting = (state_flag & DeyeDeviceStateFlag.DEFROSTING_STATE) > 0
        self.water_tank_full = (
            state_flag & DeyeDeviceStateFlag.WATER_TANK_FULL_STATE
        ) > 0
        self.fan_running = (state_flag & DeyeDeviceStateFlag.FAN_RUNNING_STATE) > 0
        self.fan_speed = DeyeFanSpeed(int(state[8], 16))
        self.mode = DeyeDeviceMode(int(state[9], 16))
        self.target_humidity = state_hex[5]
        self.environment_temperature = state_hex[15] - 40
        self.environment_humidity = state_hex[16]

        # Unused attributes
        self._electromagnetic_state = (
            state_flag & DeyeDeviceStateFlag.ELECTROMAGNETIC_STATE
        ) > 0
        self._press_state = (state_flag & DeyeDeviceStateFlag.PRESS_STATE) > 0
        self._environment_degree = (
            state_flag & DeyeDeviceStateFlag.ENVIRONMENT_DEGREE
        ) > 0
        self._poweroff_switch = (state_flag & DeyeDeviceStateFlag.POWEROFF_SWITCH) > 0
        self._poweron_switch = (state_flag & DeyeDeviceStateFlag.POWERON_SWITCH) > 0
        self._coil_temperature = state_hex[14] - 40
        self._exhaust_temperature = state_hex[17] - 40

    def to_command(self) -> DeyeDeviceCommand:
        """Convert to a command that can be used to let the device get into this state"""
        return DeyeDeviceCommand(
            self.anion_switch,
            self.water_pump_switch,
            self.power_switch,
            self.oscillating_switch,
            self.child_lock_switch,
            self.fan_speed,
            self.mode,
            self.target_humidity,
        )
