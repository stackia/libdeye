"""Utilities for device state parsing"""

from enum import IntFlag, auto

from .cloud_api import DeyeApiResponseFogPlatformDeviceProperties
from .const import (
    DeyeDeviceMode,
    DeyeFanSpeed,
)
from .device_command import DeyeDeviceCommand


class DeyeDeviceState:
    """A class to store the device state."""

    def __init__(self, state: str | DeyeApiResponseFogPlatformDeviceProperties) -> None:
        self.anion_switch: bool = False
        self.water_pump_switch: bool = False
        self.power_switch: bool = False
        self.oscillating_switch: bool = False
        self.child_lock_switch: bool = False
        self.defrosting: bool = False
        self.water_tank_full: bool = False
        self.fan_running: bool = False
        self.fan_speed: DeyeFanSpeed = DeyeFanSpeed.STOPPED
        self.mode: DeyeDeviceMode = DeyeDeviceMode.SLEEP_MODE
        self.target_humidity: int = 60
        self.environment_temperature: int = 27
        self.environment_humidity: int = 27
        # Unused attributes
        self._electromagnetic_state: bool = False
        self._press_state: bool = False
        self._environment_degree: bool = False
        self._poweroff_switch: bool = False
        self._poweron_switch: bool = False
        self._coil_temperature: int = 27
        self._exhaust_temperature: int = 27
        if isinstance(state, str):
            self._parse_state_classic(state)
        else:
            self._parse_state_fog(state)

    def _parse_state_classic(self, state: str) -> None:
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

    def _parse_state_fog(
        self, state: DeyeApiResponseFogPlatformDeviceProperties
    ) -> None:
        self.anion_switch = bool(state["NegativeIon"])
        self.water_pump_switch = bool(state["WaterPump"])
        self.power_switch = bool(state["Power"])
        self.oscillating_switch = bool(state["SwingingWind"])
        self.child_lock_switch = bool(state["KeyLock"])
        self.defrosting = bool(state["Demisting"])
        self.water_tank_full = bool(state["WaterTank"])
        self.fan_running = bool(state["Fan"])
        self.fan_speed = DeyeFanSpeed(state.get("WindSpeed", DeyeFanSpeed.STOPPED))
        self.mode = DeyeDeviceMode(state.get("Mode", DeyeDeviceMode.SLEEP_MODE))
        self.target_humidity = state.get("SetHumidity", 60)
        self.environment_temperature = state.get("CurrentAmbientTemperature", 27)
        self.environment_humidity = state.get("CurrentEnvironmentalHumidity", 27)

        # Unused attributes
        self._coil_temperature = state.get("CurrentCoilTemperature", 27)
        self._exhaust_temperature = state.get("CurrentExhaustTemperature", 27)

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

    def __eq__(self, other: object) -> bool:
        """Check if two device states are equal.

        Only compares public attributes that represent the actual device state.
        """
        if not isinstance(other, DeyeDeviceState):
            return False

        return (
            self.anion_switch == other.anion_switch
            and self.water_pump_switch == other.water_pump_switch
            and self.power_switch == other.power_switch
            and self.oscillating_switch == other.oscillating_switch
            and self.child_lock_switch == other.child_lock_switch
            and self.defrosting == other.defrosting
            and self.water_tank_full == other.water_tank_full
            and self.fan_running == other.fan_running
            and self.fan_speed == other.fan_speed
            and self.mode == other.mode
            and self.target_humidity == other.target_humidity
            and self.environment_temperature == other.environment_temperature
            and self.environment_humidity == other.environment_humidity
        )


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
