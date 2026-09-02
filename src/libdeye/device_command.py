"""Utilities for device command parsing."""

from enum import IntEnum, IntFlag, auto
from typing import TYPE_CHECKING, override

from .const import DeyeDeviceMode, DeyeFanSpeed

if TYPE_CHECKING:
    from .device_state import DeyeDeviceState


class DeyeDeviceCommand:
    """A class to store the command to control the device."""

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
        *,
        uv_switch: bool | None = None,
        prompt_sound: bool | None = None,
        screen_display: bool | None = None,
        timed_off_hour: int | None = None,
    ) -> None:
        """Initialize the command with the desired device settings.

        Extra Fog keys (UV, prompt sound, screen display, timed-off hour)
        default to ``None`` and are omitted from JSON, matching official
        ``sendCommand`` skipping null params. Sleep is
        ``DeyeDeviceMode.SLEEP_MODE``.
        """
        self.anion_switch = anion_switch
        self.water_pump_switch = water_pump_switch
        self.power_switch = power_switch
        self.oscillating_switch = oscillating_switch
        self.child_lock_switch = child_lock_switch
        self.fan_speed = fan_speed
        self.mode = mode
        self.target_humidity = target_humidity
        self.uv_switch = uv_switch
        self.prompt_sound = prompt_sound
        self.screen_display = screen_display
        self.timed_off_hour = timed_off_hour

    @override
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
            and self.uv_switch == other.uv_switch
            and self.prompt_sound == other.prompt_sound
            and self.screen_display == other.screen_display
            and self.timed_off_hour == other.timed_off_hour
        )

    def to_bytes(self) -> bytes:
        """Get binary representation of this command."""
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

        timed_off_hour = self.timed_off_hour if self.timed_off_hour is not None else 0

        return bytes(
            [
                0x08,
                0x02,
                command_flag,
                (self.fan_speed << 4) | self.mode,
                self.target_humidity,
                timed_off_hour & 0xFF,
                0,
                0,
                0,
                0,
            ]
        )

    def to_json(self) -> dict[str, int]:
        """Get JSON representation of this command."""
        payload: dict[str, int] = {
            "KeyLock": int(self.child_lock_switch),
            "Mode": int(self.mode),
            "Power": int(self.power_switch),
            "WindSpeed": int(self.fan_speed),
            "SetHumidity": self.target_humidity,
            "NegativeIon": int(self.anion_switch),
            "SwingingWind": int(self.oscillating_switch),
            "WaterPump": int(self.water_pump_switch),
        }
        if self.uv_switch is not None:
            payload["UV"] = int(self.uv_switch)
        if self.prompt_sound is not None:
            payload["PromptSound"] = int(self.prompt_sound)
        if self.screen_display is not None:
            payload["Screendisplay"] = int(self.screen_display)
        if self.timed_off_hour is not None:
            payload["TimedOffHour"] = self.timed_off_hour
        return payload

    def to_json_diff(
        self, baseline: DeyeDeviceCommand | DeyeDeviceState
    ) -> dict[str, int]:
        """Get JSON with only properties that differ from the baseline."""
        baseline_command = (
            baseline
            if isinstance(baseline, DeyeDeviceCommand)
            else baseline.to_command()
        )
        command_json = self.to_json()
        baseline_json = baseline_command.to_json()
        return {k: v for k, v in command_json.items() if baseline_json[k] != v}


class DeyeFogComboCommand(IntEnum):
    """FogCombo opcodes from official CommandManger.sendSingleCommand.

    Inner payload is ``{17, cmd, value}``, then wrapped as
    ``{2, 17, cmd, value}`` before Classic MQTT publish.
    """

    POWER = 1
    OSCILLATING = 2
    CHILD_LOCK = 3
    WATER_PUMP = 6
    ANION = 7
    MODE = 8
    FAN_SPEED = 9
    HUMIDITY_OR_TEMP = 10
    SLEEP = 15


FOG_COMBO_FRAME_PREFIX = 2
FOG_COMBO_COMMAND_HEADER = 17

FOG_COMBO_PROPERTY_COMMANDS: dict[str, DeyeFogComboCommand] = {
    "Power": DeyeFogComboCommand.POWER,
    "SwingingWind": DeyeFogComboCommand.OSCILLATING,
    "KeyLock": DeyeFogComboCommand.CHILD_LOCK,
    "WaterPump": DeyeFogComboCommand.WATER_PUMP,
    "NegativeIon": DeyeFogComboCommand.ANION,
    "Mode": DeyeFogComboCommand.MODE,
    "WindSpeed": DeyeFogComboCommand.FAN_SPEED,
    "SetHumidity": DeyeFogComboCommand.HUMIDITY_OR_TEMP,
    "Sleep": DeyeFogComboCommand.SLEEP,
}


def encode_fog_combo_frame(command: int, value: int) -> bytes:
    """Encode one official FogCombo MQTT payload: ``{2, 17, cmd, value}``."""
    return bytes(
        [
            FOG_COMBO_FRAME_PREFIX,
            FOG_COMBO_COMMAND_HEADER,
            int(command) & 0xFF,
            int(value) & 0xFF,
        ]
    )


def fog_combo_frames_from_properties(properties: dict[str, int]) -> list[bytes]:
    """Convert Fog JSON property names into official FogCombo MQTT frames."""
    frames: list[bytes] = []
    for name, value in properties.items():
        opcode = FOG_COMBO_PROPERTY_COMMANDS.get(name)
        if opcode is not None:
            frames.append(encode_fog_combo_frame(opcode, value))
    return frames


class DeyeDeviceCommandFlag(IntFlag):
    """Bit flags used in the command."""

    POWER_SWITCH = auto()
    OSCILLATING_SWITCH = auto()
    CHILD_LOCK_SWITCH = auto()
    POWEROFF_SWITCH = auto()
    POWERON_SWITCH = auto()
    WATER_PUMP_SWITCH = auto()
    ANION_SWITCH = auto()
