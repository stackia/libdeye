from libdeye.const import DeyeDeviceMode, DeyeFanSpeed
from libdeye.device_command import DeyeDeviceCommand


def test_deye_device_command_to_bytes() -> None:
    """DeyeDeviceCommand to_bytes() should return correct result"""
    command = DeyeDeviceCommand(power_switch=True, child_lock_switch=True)
    print(command.to_bytes())
    assert command.to_bytes() == b"\x08\x02\x05\x10\x3c\x00\x00\x00\x00\x00"


def test_deye_device_command_to_bytes_all_switches_on() -> None:
    """Test to_bytes() with all switches turned on"""
    command = DeyeDeviceCommand(
        anion_switch=True,
        water_pump_switch=True,
        power_switch=True,
        oscillating_switch=True,
        child_lock_switch=True,
    )
    assert command.to_bytes() == b"\x08\x02\x67\x10\x3c\x00\x00\x00\x00\x00"


def test_deye_device_command_to_bytes_all_switches_off() -> None:
    """Test to_bytes() with all switches turned off"""
    command = DeyeDeviceCommand()
    assert command.to_bytes() == b"\x08\x02\x00\x10\x3c\x00\x00\x00\x00\x00"


def test_deye_device_command_to_bytes_with_fan_speed() -> None:
    """Test to_bytes() with different fan speeds"""
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
    """Test to_bytes() with different modes"""
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
    """Test to_bytes() with different target humidity values"""
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
    """Test to_json() returns correct JSON representation"""
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
        "NegativeIon": 0,
        "SwingingWind": 0,
        "WaterPump": 0,
    }

    assert command.to_json() == expected_json


def test_deye_device_command_to_json_all_on() -> None:
    """Test to_json() with all features enabled"""
    command = DeyeDeviceCommand(
        anion_switch=True,
        water_pump_switch=True,
        power_switch=True,
        oscillating_switch=True,
        child_lock_switch=True,
        fan_speed=DeyeFanSpeed.HIGH,
        mode=DeyeDeviceMode.CLOTHES_DRYER_MODE,
        target_humidity=70,
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
    }

    assert command.to_json() == expected_json


def test_deye_device_command_to_json_all_off() -> None:
    """Test to_json() with all features disabled"""
    command = DeyeDeviceCommand()

    expected_json = {
        "KeyLock": 0,
        "Mode": 0,
        "Power": 0,
        "WindSpeed": 1,  # Default is LOW (1)
        "SetHumidity": 60,
        "NegativeIon": 0,
        "SwingingWind": 0,
        "WaterPump": 0,
    }

    assert command.to_json() == expected_json


def test_deye_device_command_equality() -> None:
    """Test equality comparison between DeyeDeviceCommand instances"""
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
    assert not (command1 != command2)

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
    assert not (command1 == command3)

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
    assert not (command1 == command4)

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
    assert not (command1 == command5)

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
    assert not (command1 == command6)

    # Test equality comparison with a different type
    assert command1 != "not a command"


def test_deye_device_command_default_equality() -> None:
    """Test equality with default instances"""
    # Test equality with default instances
    command1 = DeyeDeviceCommand()
    command2 = DeyeDeviceCommand()
    assert command1 == command2
    assert not (command1 != command2)

    # Create a command with explicit defaults that should equal a default command
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
    assert command1 == command3
    assert not (command1 != command3)
