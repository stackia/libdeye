from libdeye.const import DeyeFanSpeed
from libdeye.device_state_command import DeyeDeviceCommand, DeyeDeviceState


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


def test_deye_device_command_bytes() -> None:
    """DeyeDeviceCommand bytes() should return correct result"""
    command = DeyeDeviceCommand(power_switch=True, child_lock_switch=True)
    print(command.bytes())
    assert command.bytes() == b"\x08\x02\x05\x10\x3c\x00\x00\x00\x00\x00"
