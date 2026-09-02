"""Tests for Deye device commands."""

from libdeye.const import DeyeDeviceMode, DeyeFanSpeed
from libdeye.device_command import DeyeDeviceCommand


def test_deye_device_command_to_bytes() -> None:
    """DeyeDeviceCommand to_bytes() should return correct result."""
    command = DeyeDeviceCommand(power_switch=True, child_lock_switch=True)
    assert command.to_bytes() == b"\x08\x02\x05\x10\x3c\x00\x00\x00\x00\x00"


def test_deye_device_command_to_bytes_all_switches_on() -> None:
    """Test to_bytes() with all switches turned on."""
    command = DeyeDeviceCommand(
        anion_switch=True,
        water_pump_switch=True,
        power_switch=True,
        oscillating_switch=True,
        child_lock_switch=True,
    )
    assert command.to_bytes() == b"\x08\x02\x67\x10\x3c\x00\x00\x00\x00\x00"


def test_deye_device_command_to_bytes_all_switches_off() -> None:
    """Test to_bytes() with all switches turned off."""
    command = DeyeDeviceCommand()
    assert command.to_bytes() == b"\x08\x02\x00\x10\x3c\x00\x00\x00\x00\x00"


def test_deye_device_command_to_bytes_with_fan_speed() -> None:
    """Test to_bytes() with different fan speeds."""
    # Test with LOW fan speed (default)
    command = DeyeDeviceCommand()
    assert command.to_bytes()[3] & 0xF0 == 0x10

    # Test with MIDDLE fan speed
    command = DeyeDeviceCommand(fan_speed=DeyeFanSpeed.MIDDLE)
    assert command.to_bytes()[3] & 0xF0 == 0x20

    # Test with HIGH fan speed
    command = DeyeDeviceCommand(fan_speed=DeyeFanSpeed.HIGH)
    assert command.to_bytes()[3] & 0xF0 == 0x30

    # Test with FULL fan speed
    command = DeyeDeviceCommand(fan_speed=DeyeFanSpeed.FULL)
    assert command.to_bytes()[3] & 0xF0 == 0x40


def test_deye_device_command_to_bytes_with_mode() -> None:
    """Test to_bytes() with different modes."""
    # Test with MANUAL_MODE (default)
    command = DeyeDeviceCommand()
    assert command.to_bytes()[3] & 0x0F == 0x00

    # Test with CLOTHES_DRYER_MODE
    command = DeyeDeviceCommand(mode=DeyeDeviceMode.CLOTHES_DRYER_MODE)
    assert command.to_bytes()[3] & 0x0F == 0x01

    # Test with AIR_PURIFIER_MODE
    command = DeyeDeviceCommand(mode=DeyeDeviceMode.AIR_PURIFIER_MODE)
    assert command.to_bytes()[3] & 0x0F == 0x02

    # Test with AUTO_MODE
    command = DeyeDeviceCommand(mode=DeyeDeviceMode.AUTO_MODE)
    assert command.to_bytes()[3] & 0x0F == 0x03


def test_deye_device_command_to_bytes_with_target_humidity() -> None:
    """Test to_bytes() with different target humidity values."""
    # Test with default target humidity (60)
    command = DeyeDeviceCommand()
    assert command.to_bytes()[4] == 60

    # Test with minimum target humidity
    command = DeyeDeviceCommand(target_humidity=30)
    assert command.to_bytes()[4] == 30

    # Test with maximum target humidity
    command = DeyeDeviceCommand(target_humidity=90)
    assert command.to_bytes()[4] == 90


def test_deye_device_command_to_json() -> None:
    """Test to_json() returns correct JSON representation."""
    command = DeyeDeviceCommand(
        power_switch=True,
        child_lock_switch=True,
        fan_speed=DeyeFanSpeed.MIDDLE,
        mode=DeyeDeviceMode.AUTO_MODE,
        target_humidity=45,
    )

    expected_json = {
        "KeyLock": 1,
        "Mode": 3,
        "Power": 1,
        "WindSpeed": 2,
        "SetHumidity": 45,
    }

    assert command.to_json() == expected_json


def test_deye_device_command_to_json_all_on() -> None:
    """Test to_json() with all features enabled."""
    command = DeyeDeviceCommand(
        anion_switch=True,
        water_pump_switch=True,
        power_switch=True,
        oscillating_switch=True,
        child_lock_switch=True,
        fan_speed=DeyeFanSpeed.HIGH,
        mode=DeyeDeviceMode.CLOTHES_DRYER_MODE,
        target_humidity=70,
        uv_switch=True,
        prompt_sound=True,
        screen_display=True,
    )

    expected_json = {
        "KeyLock": 1,
        "Mode": 1,
        "Power": 1,
        "WindSpeed": 3,
        "SetHumidity": 70,
        "NegativeIon": 1,
        "SwingingWind": 1,
        "WaterPump": 1,
        "UV": 1,
        "PromptSound": 1,
        "Screendisplay": 1,
    }

    assert command.to_json() == expected_json


def test_deye_device_command_to_json_all_off() -> None:
    """Unset fields are omitted, matching official sendCommand skip-null."""
    command = DeyeDeviceCommand()
    assert command.to_json() == {}

    command = DeyeDeviceCommand(
        anion_switch=False,
        water_pump_switch=False,
        power_switch=False,
        oscillating_switch=False,
        child_lock_switch=False,
        fan_speed=DeyeFanSpeed.LOW,
        mode=DeyeDeviceMode.MANUAL_MODE,
        target_humidity=60,
        uv_switch=False,
        prompt_sound=False,
        screen_display=False,
    )
    assert command.to_json() == {
        "KeyLock": 0,
        "Mode": 0,
        "Power": 0,
        "WindSpeed": 1,
        "SetHumidity": 60,
        "NegativeIon": 0,
        "SwingingWind": 0,
        "WaterPump": 0,
        "UV": 0,
        "PromptSound": 0,
        "Screendisplay": 0,
    }


def test_deye_device_command_to_json_diff() -> None:
    """Test to_json_diff() returns only changed properties."""
    baseline = DeyeDeviceCommand(
        power_switch=True,
        fan_speed=DeyeFanSpeed.LOW,
        target_humidity=50,
    )
    command = DeyeDeviceCommand(
        power_switch=True,
        fan_speed=DeyeFanSpeed.HIGH,
        target_humidity=50,
    )

    assert command.to_json_diff(baseline) == {"WindSpeed": int(DeyeFanSpeed.HIGH)}


def test_deye_device_command_to_json_diff_from_state() -> None:
    """Test to_json_diff() accepts DeyeDeviceState as baseline."""
    from typing import cast

    from libdeye.cloud_api import DeyeApiResponseFogPlatformDeviceProperties
    from libdeye.device_state import DeyeDeviceState

    state = DeyeDeviceState(
        cast(
            DeyeApiResponseFogPlatformDeviceProperties,
            {
                "Power": 0,
                "Mode": 0,
                "WindSpeed": 1,
                "SetHumidity": 60,
                "NegativeIon": 0,
                "WaterPump": 0,
                "SwingingWind": 0,
                "KeyLock": 0,
                "Demisting": 0,
                "WaterTank": 0,
                "Fan": 0,
                "CurrentCoilTemperature": 25,
                "CurrentExhaustTemperature": 25,
                "CurrentAmbientTemperature": 25,
                "CurrentEnvironmentalHumidity": 60,
            },
        )
    )
    command = DeyeDeviceCommand(power_switch=True)

    assert command.to_json_diff(state) == {"Power": 1}


def test_deye_device_command_to_json_diff_skip_null_and_timed_off() -> None:
    """Unset switches are omitted; newly set keys still diff against a gap."""
    from typing import cast

    from libdeye.cloud_api import DeyeApiResponseFogPlatformDeviceProperties
    from libdeye.device_state import DeyeDeviceState

    baseline = DeyeDeviceCommand()
    command = DeyeDeviceCommand(
        uv_switch=True,
        prompt_sound=False,
        screen_display=True,
        timed_off_hour=2,
    )
    assert command.to_json_diff(baseline) == {
        "UV": 1,
        "PromptSound": 0,
        "Screendisplay": 1,
        "TimedOffHour": 2,
    }

    state = DeyeDeviceState(
        cast(
            DeyeApiResponseFogPlatformDeviceProperties,
            {
                "Power": 0,
                "Mode": 0,
                "WindSpeed": 1,
                "SetHumidity": 60,
                "NegativeIon": 0,
                "WaterPump": 0,
                "SwingingWind": 0,
                "KeyLock": 0,
                "Demisting": 0,
                "WaterTank": 0,
                "Fan": 0,
                "CurrentCoilTemperature": 25,
                "CurrentExhaustTemperature": 25,
                "CurrentAmbientTemperature": 25,
                "CurrentEnvironmentalHumidity": 60,
            },
        )
    )
    assert state.uv_switch is None
    assert state.prompt_sound is None
    assert state.screen_display is None
    assert DeyeDeviceCommand(uv_switch=False).to_json_diff(state) == {"UV": 0}
    assert DeyeDeviceCommand(uv_switch=True).to_json_diff(state) == {"UV": 1}
    assert DeyeDeviceCommand(timed_off_hour=2).to_json_diff(state) == {
        "TimedOffHour": 2
    }

    both_on = DeyeDeviceCommand(uv_switch=True)
    assert both_on.to_json_diff(DeyeDeviceCommand(uv_switch=True)) == {}


def test_deye_device_command_equality() -> None:
    """Test equality comparison between DeyeDeviceCommand instances."""
    # Test equality with identical instances
    command1 = DeyeDeviceCommand(
        anion_switch=True,
        water_pump_switch=True,
        power_switch=True,
        oscillating_switch=True,
        child_lock_switch=True,
        fan_speed=DeyeFanSpeed.HIGH,
        mode=DeyeDeviceMode.AUTO_MODE,
        target_humidity=70,
    )
    command2 = DeyeDeviceCommand(
        anion_switch=True,
        water_pump_switch=True,
        power_switch=True,
        oscillating_switch=True,
        child_lock_switch=True,
        fan_speed=DeyeFanSpeed.HIGH,
        mode=DeyeDeviceMode.AUTO_MODE,
        target_humidity=70,
    )
    assert command1 == command2
    assert command1 == command2

    # Test inequality with different switch state
    command3 = DeyeDeviceCommand(
        anion_switch=False,  # Different from command1
        water_pump_switch=True,
        power_switch=True,
        oscillating_switch=True,
        child_lock_switch=True,
        fan_speed=DeyeFanSpeed.HIGH,
        mode=DeyeDeviceMode.AUTO_MODE,
        target_humidity=70,
    )
    assert command1 != command3
    assert command1 != command3

    # Test inequality with different fan speed
    command4 = DeyeDeviceCommand(
        anion_switch=True,
        water_pump_switch=True,
        power_switch=True,
        oscillating_switch=True,
        child_lock_switch=True,
        fan_speed=DeyeFanSpeed.MIDDLE,  # Different from command1
        mode=DeyeDeviceMode.AUTO_MODE,
        target_humidity=70,
    )
    assert command1 != command4
    assert command1 != command4

    # Test inequality with different mode
    command5 = DeyeDeviceCommand(
        anion_switch=True,
        water_pump_switch=True,
        power_switch=True,
        oscillating_switch=True,
        child_lock_switch=True,
        fan_speed=DeyeFanSpeed.HIGH,
        mode=DeyeDeviceMode.MANUAL_MODE,  # Different from command1
        target_humidity=70,
    )
    assert command1 != command5
    assert command1 != command5

    # Test inequality with different target humidity
    command6 = DeyeDeviceCommand(
        anion_switch=True,
        water_pump_switch=True,
        power_switch=True,
        oscillating_switch=True,
        child_lock_switch=True,
        fan_speed=DeyeFanSpeed.HIGH,
        mode=DeyeDeviceMode.AUTO_MODE,
        target_humidity=50,  # Different from command1
    )
    assert command1 != command6
    assert command1 != command6

    # Test equality comparison with a different type
    assert command1 != "not a command"


def test_deye_device_command_default_equality() -> None:
    """Test equality with default instances."""
    # Test equality with default instances
    command1 = DeyeDeviceCommand()
    command2 = DeyeDeviceCommand()
    assert command1 == command2
    assert command1 == command2

    # Explicit False/0 is not the same as unset (null is skipped in Fog JSON)
    command3 = DeyeDeviceCommand(
        anion_switch=False,
        water_pump_switch=False,
        power_switch=False,
        oscillating_switch=False,
        child_lock_switch=False,
        fan_speed=DeyeFanSpeed.LOW,
        mode=DeyeDeviceMode.MANUAL_MODE,
        target_humidity=60,
    )
    assert command1 != command3
    assert command3.to_json()["NegativeIon"] == 0
    assert "NegativeIon" not in command1.to_json()


def test_encode_fog_combo_frame_matches_official_command_manger() -> None:
    """Official sendSingleCommand wraps {17, cmd, value} as {2, 17, cmd, value}."""
    from libdeye.device_command import (
        DeyeFogComboCommand,
        encode_fog_combo_frame,
        fog_combo_frames_from_properties,
    )

    assert encode_fog_combo_frame(DeyeFogComboCommand.POWER, 1) == bytes([2, 17, 1, 1])
    assert encode_fog_combo_frame(DeyeFogComboCommand.OSCILLATING, 0) == bytes(
        [2, 17, 2, 0]
    )
    assert encode_fog_combo_frame(DeyeFogComboCommand.CHILD_LOCK, 1) == bytes(
        [2, 17, 3, 1]
    )
    assert encode_fog_combo_frame(DeyeFogComboCommand.WATER_PUMP, 1) == bytes(
        [2, 17, 6, 1]
    )
    assert encode_fog_combo_frame(DeyeFogComboCommand.ANION, 1) == bytes([2, 17, 7, 1])
    assert encode_fog_combo_frame(DeyeFogComboCommand.MODE, 3) == bytes([2, 17, 8, 3])
    assert encode_fog_combo_frame(DeyeFogComboCommand.FAN_SPEED, 5) == bytes(
        [2, 17, 9, 5]
    )
    assert encode_fog_combo_frame(DeyeFogComboCommand.HUMIDITY_OR_TEMP, 45) == bytes(
        [2, 17, 10, 45]
    )
    assert encode_fog_combo_frame(DeyeFogComboCommand.SLEEP, 1) == bytes([2, 17, 15, 1])

    frames = fog_combo_frames_from_properties(
        {
            "Power": 1,
            "SetHumidity": 45,
            "Sleep": 1,
            "SetTemperature": 26,
            "Unknown": 9,
        }
    )
    assert frames == [
        bytes([2, 17, 1, 1]),
        bytes([2, 17, 10, 45]),
        bytes([2, 17, 15, 1]),
    ]


def test_deye_device_command_fog_json_fields() -> None:
    """Fog JSON skips null Integers, including anion/lock/UV; Sleep is never sent."""
    command = DeyeDeviceCommand()
    assert command.to_json() == {}
    assert "Sleep" not in command.to_json()
    assert "SetTemperature" not in command.to_json()
    assert command.to_bytes()[5] == 0

    command = DeyeDeviceCommand(
        anion_switch=False,
        child_lock_switch=True,
        uv_switch=False,
        prompt_sound=True,
        screen_display=False,
        timed_off_hour=3,
    )
    assert "Sleep" not in command.to_json()
    assert "SetTemperature" not in command.to_json()
    assert command.to_json()["NegativeIon"] == 0
    assert command.to_json()["KeyLock"] == 1
    assert command.to_json()["UV"] == 0
    assert command.to_json()["PromptSound"] == 1
    assert command.to_json()["Screendisplay"] == 0
    assert command.to_json()["TimedOffHour"] == 3
    assert command.to_bytes()[5] == 3

    other = DeyeDeviceCommand(timed_off_hour=3)
    assert command != other

    sleep_mode = DeyeDeviceCommand(mode=DeyeDeviceMode.SLEEP_MODE)
    assert sleep_mode.to_json()["Mode"] == int(DeyeDeviceMode.SLEEP_MODE)
    assert "Sleep" not in sleep_mode.to_json()
    assert "SetTemperature" not in sleep_mode.to_json()

    uv_on = DeyeDeviceCommand(uv_switch=True)
    uv_off = DeyeDeviceCommand(uv_switch=False)
    assert uv_on.to_json_diff(uv_off) == {"UV": 1}
