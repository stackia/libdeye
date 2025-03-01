from typing import TypedDict, cast

from libdeye.cloud_api import DeyeApiResponseFogPlatformDeviceProperties
from libdeye.const import DeyeDeviceMode, DeyeFanSpeed
from libdeye.device_state import DeyeDeviceState


# Create a minimal TypedDict for testing
class FogDevicePropertiesForTest(TypedDict, total=False):
    NegativeIon: int
    WaterPump: int
    Power: int
    SwingingWind: int
    KeyLock: int
    Demisting: int
    WaterTank: int
    Fan: int
    WindSpeed: int
    Mode: int
    SetHumidity: int
    CurrentAmbientTemperature: int
    CurrentEnvironmentalHumidity: int
    CurrentCoilTemperature: int
    CurrentExhaustTemperature: int


def test_deye_device_state_init() -> None:
    """DeyeDeviceState __init__() should correctly parse the state string"""
    state = DeyeDeviceState("14118100113B00000000000000000040300000000000")
    assert state.water_tank_full is False
    assert state.fan_running is True
    assert state.power_switch is True


def test_deye_device_state_to_command() -> None:
    """DeyeDeviceState to_command() should correctly convert to a command"""
    state = DeyeDeviceState("14118100113B00000000000000000040300000000000")
    state.power_switch = False
    command = state.to_command()
    assert command.power_switch is False
    assert command.fan_speed is DeyeFanSpeed.LOW


def test_deye_device_state_parse_classic_switches() -> None:
    """Test parsing of switch states from classic state string"""
    # This state string has anion, water pump, power, oscillating, and child lock switches on
    # Bits 0 (anion), 1 (water pump), 8 (power), 9 (oscillating), 10 (child lock) set to 1
    # 0000 0000 0000 0011 (bits 0,1) + 0000 0111 0000 0000 (bits 8,9,10) = 0x0003 + 0x0700 = 0x0703
    state = DeyeDeviceState("14110703113B00000000000000000040300000000000")
    assert state.anion_switch is True
    assert state.water_pump_switch is True
    assert state.power_switch is True
    assert state.oscillating_switch is True
    assert state.child_lock_switch is True
    assert state.defrosting is False


def test_deye_device_state_parse_classic_fan_speed() -> None:
    """Test parsing of fan speed from classic state string"""
    # Test with different fan speeds in position 8
    state_low = DeyeDeviceState("14118100113B00000000000000000040300000000000")
    assert state_low.fan_speed is DeyeFanSpeed.LOW

    state_middle = DeyeDeviceState("14118100213B00000000000000000040300000000000")
    assert state_middle.fan_speed is DeyeFanSpeed.MIDDLE

    state_high = DeyeDeviceState("14118100313B00000000000000000040300000000000")
    assert state_high.fan_speed is DeyeFanSpeed.HIGH

    state_full = DeyeDeviceState("14118100413B00000000000000000040300000000000")
    assert state_full.fan_speed is DeyeFanSpeed.FULL


def test_deye_device_state_parse_classic_mode() -> None:
    """Test parsing of device mode from classic state string"""
    # Test with different modes in position 9
    state_manual = DeyeDeviceState("14118100103B00000000000000000040300000000000")
    assert state_manual.mode is DeyeDeviceMode.MANUAL_MODE

    state_clothes_dryer = DeyeDeviceState(
        "14118100113B00000000000000000040300000000000"
    )
    assert state_clothes_dryer.mode is DeyeDeviceMode.CLOTHES_DRYER_MODE

    state_air_purifier = DeyeDeviceState("14118100123B00000000000000000040300000000000")
    assert state_air_purifier.mode is DeyeDeviceMode.AIR_PURIFIER_MODE

    state_auto = DeyeDeviceState("14118100133B00000000000000000040300000000000")
    assert state_auto.mode is DeyeDeviceMode.AUTO_MODE

    state_sleep = DeyeDeviceState("14118100163B00000000000000000040300000000000")
    assert state_sleep.mode is DeyeDeviceMode.SLEEP_MODE


def test_deye_device_state_parse_classic_humidity_and_temperature() -> None:
    """Test parsing of humidity and temperature values from classic state string"""
    # State with target humidity 50%, environment temperature 25°C, environment humidity 60%
    # target_humidity is at position 5 (byte value 50)
    # environment_temperature is at position 15 (byte value 65 = 25 + 40)
    # environment_humidity is at position 16 (byte value 60)
    state = DeyeDeviceState("141181001132000000000000000000413C0000000000")
    assert state.target_humidity == 50
    assert state.environment_temperature == 25
    assert state.environment_humidity == 60


def test_deye_device_state_parse_fog() -> None:
    """Test parsing of state from fog platform response"""
    fog_state: FogDevicePropertiesForTest = {
        "NegativeIon": 1,
        "WaterPump": 0,
        "Power": 1,
        "SwingingWind": 1,
        "KeyLock": 0,
        "Demisting": 1,
        "WaterTank": 1,
        "Fan": 1,
        "WindSpeed": 2,
        "Mode": 3,
        "SetHumidity": 45,
        "CurrentAmbientTemperature": 22,
        "CurrentEnvironmentalHumidity": 55,
        "CurrentCoilTemperature": 18,
        "CurrentExhaustTemperature": 20,
    }
    state = DeyeDeviceState(cast(DeyeApiResponseFogPlatformDeviceProperties, fog_state))

    # Test switches
    assert state.anion_switch is True
    assert state.water_pump_switch is False
    assert state.power_switch is True
    assert state.oscillating_switch is True
    assert state.child_lock_switch is False
    assert state.defrosting is True
    assert state.water_tank_full is True
    assert state.fan_running is True

    # Test values
    assert state.fan_speed is DeyeFanSpeed.MIDDLE
    assert state.mode is DeyeDeviceMode.AUTO_MODE
    assert state.target_humidity == 45
    assert state.environment_temperature == 22
    assert state.environment_humidity == 55


def test_deye_device_state_to_command_preserves_values() -> None:
    """Test that to_command() preserves all the values from the state"""
    state = DeyeDeviceState("14118100113B00000000000000000040300000000000")

    # Modify state values
    state.anion_switch = True
    state.water_pump_switch = True
    state.power_switch = True
    state.oscillating_switch = True
    state.child_lock_switch = True
    state.fan_speed = DeyeFanSpeed.HIGH
    state.mode = DeyeDeviceMode.AUTO_MODE
    state.target_humidity = 40

    # Convert to command
    command = state.to_command()

    # Verify all values are preserved
    assert command.anion_switch is True
    assert command.water_pump_switch is True
    assert command.power_switch is True
    assert command.oscillating_switch is True
    assert command.child_lock_switch is True
    assert command.fan_speed is DeyeFanSpeed.HIGH
    assert command.mode is DeyeDeviceMode.AUTO_MODE
    assert command.target_humidity == 40


def test_deye_device_state_fog_missing_values() -> None:
    """Test that fog state parsing handles missing values with defaults"""
    # Minimal fog state with missing values
    fog_state: FogDevicePropertiesForTest = {
        "Power": 1,
        "WaterTank": 0,
        "NegativeIon": 0,
        "WaterPump": 0,
        "SwingingWind": 0,
        "KeyLock": 0,
        "Demisting": 0,
        "Fan": 0,
    }
    state = DeyeDeviceState(cast(DeyeApiResponseFogPlatformDeviceProperties, fog_state))

    # Check defaults are applied
    assert state.power_switch is True
    assert state.water_tank_full is False
    assert state.fan_speed is DeyeFanSpeed.STOPPED
    assert state.mode is DeyeDeviceMode.SLEEP_MODE
    assert state.target_humidity == 60
    assert state.environment_temperature == 27
    assert state.environment_humidity == 27


def test_deye_device_state_equality_same_state() -> None:
    """Test that two device states with identical attributes are equal"""
    state1 = DeyeDeviceState("14118100113B00000000000000000040300000000000")
    state2 = DeyeDeviceState("14118100113B00000000000000000040300000000000")

    assert state1 == state2
    assert state2 == state1

    # Identity equality
    assert state1 == state1


def test_deye_device_state_equality_different_state() -> None:
    """Test that two device states with different attributes are not equal"""
    state1 = DeyeDeviceState("14118100113B00000000000000000040300000000000")
    state2 = DeyeDeviceState("14110703113B00000000000000000040300000000000")

    assert state1 != state2
    assert state2 != state1


def test_deye_device_state_equality_modified_state() -> None:
    """Test equality after modifying device state attributes"""
    state1 = DeyeDeviceState("14118100113B00000000000000000040300000000000")
    state2 = DeyeDeviceState("14118100113B00000000000000000040300000000000")

    # Initially equal
    assert state1 == state2

    # Modify one attribute
    state2.anion_switch = not state2.anion_switch
    assert state1 != state2

    # Make them equal again
    state1.anion_switch = state2.anion_switch
    assert state1 == state2

    # Test with several attributes modified
    state2.water_pump_switch = True
    state2.target_humidity = 45
    state2.fan_speed = DeyeFanSpeed.HIGH
    assert state1 != state2


def test_deye_device_state_equality_different_types() -> None:
    """Test equality between a device state and other types of objects"""
    state = DeyeDeviceState("14118100113B00000000000000000040300000000000")

    # Compare with None
    assert state is not None

    # Compare with other types
    assert state != "a string"
    assert state != 123
    assert state != {}
    assert state != []


def test_deye_device_state_equality_fog_and_classic() -> None:
    """Test equality between states created from fog platform and classic"""
    # Create state from classic protocol
    state_classic = DeyeDeviceState("14118100113B00000000000000000040300000000000")

    # Create a minimal fog state
    fog_state: FogDevicePropertiesForTest = {
        "NegativeIon": 0,
        "WaterPump": 0,
        "Power": 0,  # Different from classic
        "SwingingWind": 0,
        "KeyLock": 0,
        "Demisting": 0,
        "WaterTank": 0,
        "Fan": 0,  # Different from classic
        "WindSpeed": 2,  # Different from classic
        "Mode": 2,  # Different from classic
        "SetHumidity": 50,  # Different from classic
        "CurrentAmbientTemperature": 25,  # Different from classic
        "CurrentEnvironmentalHumidity": 40,  # Different from classic
    }

    state_fog = DeyeDeviceState(
        cast(DeyeApiResponseFogPlatformDeviceProperties, fog_state)
    )

    # Verify they are initially different
    assert state_fog != state_classic

    # Make the fog state equal to the classic state by copying all attributes
    state_fog.anion_switch = state_classic.anion_switch
    state_fog.water_pump_switch = state_classic.water_pump_switch
    state_fog.power_switch = state_classic.power_switch
    state_fog.oscillating_switch = state_classic.oscillating_switch
    state_fog.child_lock_switch = state_classic.child_lock_switch
    state_fog.defrosting = state_classic.defrosting
    state_fog.water_tank_full = state_classic.water_tank_full
    state_fog.fan_running = state_classic.fan_running
    state_fog.fan_speed = state_classic.fan_speed
    state_fog.mode = state_classic.mode
    state_fog.target_humidity = state_classic.target_humidity
    state_fog.environment_temperature = state_classic.environment_temperature
    state_fog.environment_humidity = state_classic.environment_humidity

    # Now they should be equal
    assert state_fog == state_classic
    assert state_classic == state_fog
