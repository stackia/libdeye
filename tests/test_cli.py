"""Tests for the CLI module."""

import argparse
import asyncio
import logging
from pathlib import Path
from unittest.mock import ANY, AsyncMock, MagicMock, patch

import aiohttp
import pytest

from libdeye.cli import (
    authenticate,
    get_classic_mqtt_info,
    get_device_state,
    get_fog_mqtt_info,
    list_devices,
    list_products,
    load_env_file,
    main,
    monitor_device,
    print_auth_token,
    print_device_state,
    refresh_token,
    run_cli,
    set_device_state,
)
from libdeye.cloud_api import DeyeCloudApi, DeyeIotPlatform
from libdeye.const import DeyeDeviceMode, DeyeFanSpeed
from libdeye.device_state import DeyeDeviceState
from libdeye.mqtt_client import (
    DeyeClassicMqttClient,
)


@pytest.fixture
def mock_env_file(tmp_path: Path) -> Path:
    """Create a mock .env file for testing."""
    env_file = tmp_path / ".env"
    env_file.write_text(
        "DEYE_USERNAME=test_user\n"
        "DEYE_PASSWORD=test_password\n"
        "DEYE_AUTH_TOKEN=test_token\n"
        "DEYE_DEVICE_ID=test_device_id\n"
    )
    return env_file


def test_load_env_file(mock_env_file: Path) -> None:
    """Test loading environment variables from a .env file."""
    env_vars = load_env_file(str(mock_env_file))

    assert env_vars["DEYE_USERNAME"] == "test_user"
    assert env_vars["DEYE_PASSWORD"] == "test_password"
    assert env_vars["DEYE_AUTH_TOKEN"] == "test_token"
    assert env_vars["DEYE_DEVICE_ID"] == "test_device_id"


def test_load_env_file_nonexistent() -> None:
    """Test loading environment variables from a nonexistent .env file."""
    env_vars = load_env_file("nonexistent_file.env")

    assert env_vars == {}


def test_load_env_file_with_comments_and_empty_lines(tmp_path: Path) -> None:
    """Test loading environment variables with comments and empty lines."""
    env_file = tmp_path / ".env"
    env_file.write_text(
        "# This is a comment\n"
        "\n"
        "DEYE_USERNAME=test_user\n"
        "# Another comment\n"
        "DEYE_PASSWORD=test_password\n"
        "\n"
    )

    env_vars = load_env_file(str(env_file))

    assert env_vars["DEYE_USERNAME"] == "test_user"
    assert env_vars["DEYE_PASSWORD"] == "test_password"
    assert len(env_vars) == 2


def test_load_env_file_with_quoted_values(tmp_path: Path) -> None:
    """Test loading environment variables with quoted values."""
    env_file = tmp_path / ".env"
    env_file.write_text(
        'DEYE_USERNAME="quoted_user"\n' "DEYE_PASSWORD='quoted_password'\n"
    )

    env_vars = load_env_file(str(env_file))

    assert env_vars["DEYE_USERNAME"] == "quoted_user"
    assert env_vars["DEYE_PASSWORD"] == "quoted_password"


@pytest.mark.asyncio
async def test_authenticate_with_username_password() -> None:
    """Test authentication with username and password."""
    mock_session = MagicMock(spec=aiohttp.ClientSession)
    mock_api = AsyncMock(spec=DeyeCloudApi)

    with patch("libdeye.cli.DeyeCloudApi", return_value=mock_api) as mock_api_class:
        await authenticate(mock_session, "test_user", "test_password")

        mock_api_class.assert_called_once_with(
            mock_session, "test_user", "test_password", None
        )
        mock_api.authenticate.assert_called_once()


@pytest.mark.asyncio
async def test_authenticate_with_token() -> None:
    """Test authentication with token."""
    mock_session = MagicMock(spec=aiohttp.ClientSession)
    mock_api = AsyncMock(spec=DeyeCloudApi)

    with patch("libdeye.cli.DeyeCloudApi", return_value=mock_api) as mock_api_class:
        await authenticate(mock_session, "test_user", "test_password", "test_token")

        mock_api_class.assert_called_once_with(
            mock_session, "test_user", "test_password", "test_token"
        )
        mock_api.authenticate.assert_not_called()


@pytest.mark.asyncio
async def test_list_devices() -> None:
    """Test listing devices."""
    mock_api = AsyncMock(spec=DeyeCloudApi)
    mock_api.get_device_list.return_value = [
        {
            "device_name": "Test Device",
            "device_id": "test_device_id",
            "online": True,
            "product_name": "Test Product",
            "product_id": "test_product_id",
            "mac": "00:11:22:33:44:55",
            "platform": DeyeIotPlatform.Classic.value,
        }
    ]

    with patch("sys.stdout") as mock_stdout:
        await list_devices(mock_api)

        mock_api.get_device_list.assert_called_once()
        # Check that output contains device information
        output = "".join(call.args[0] for call in mock_stdout.write.call_args_list)
        assert "Test Device" in output
        assert "test_device_id" in output
        assert "Online" in output
        assert "Test Product" in output
        assert "00:11:22:33:44:55" in output
        assert "Classic" in output


@pytest.mark.asyncio
async def test_list_products() -> None:
    """Test listing products."""
    mock_api = AsyncMock(spec=DeyeCloudApi)
    mock_api.get_product_list.return_value = [
        {
            "ptypename": "Test Product Type",
            "ptype": "test_type",
            "pdata": [
                {
                    "pname": "Test Product",
                    "productid": "test_product_id",
                    "model": "Test Model",
                    "brand": "Test Brand",
                    "status": 0,
                    "configType": "test_config",
                    "config_guide": "test_guide",
                }
            ],
        }
    ]

    with patch("sys.stdout") as mock_stdout:
        await list_products(mock_api)

        mock_api.get_product_list.assert_called_once()
        # Check that output contains product information
        output = "".join(call.args[0] for call in mock_stdout.write.call_args_list)
        assert "Test Product Type" in output
        assert "test_type" in output
        assert "Test Product" in output
        assert "test_product_id" in output
        assert "Test Model" in output
        assert "Test Brand" in output
        assert "Active" in output
        assert "test_config" in output
        assert "test_guide" in output


def test_print_device_state() -> None:
    """Test printing device state."""
    mock_state = MagicMock(spec=DeyeDeviceState)
    mock_state.power_switch = True
    mock_state.mode = DeyeDeviceMode.AUTO_MODE
    mock_state.fan_speed = DeyeFanSpeed.LOW
    mock_state.target_humidity = 50
    mock_state.environment_humidity = 60
    mock_state.environment_temperature = 25
    mock_state.anion_switch = True
    mock_state.water_pump_switch = False
    mock_state.oscillating_switch = True
    mock_state.child_lock_switch = False
    mock_state.water_tank_full = False
    mock_state.defrosting = False

    with patch("sys.stdout") as mock_stdout:
        print_device_state(mock_state)

        # Check that output contains state information
        output = "".join(call.args[0] for call in mock_stdout.write.call_args_list)
        assert "Power: On" in output
        assert "Mode: AUTO_MODE" in output
        assert "Fan Speed: LOW" in output
        assert "Target Humidity: 50%" in output
        assert "Current Humidity: 60%" in output
        assert "Current Temperature: 25°C" in output
        assert "Anion: On" in output
        assert "Water Pump: Off" in output
        assert "Oscillating: On" in output
        assert "Child Lock: Off" in output
        assert "Water Tank Full: No" in output
        assert "Defrosting: No" in output


@pytest.mark.asyncio
async def test_get_device_state() -> None:
    """Test getting device state."""
    mock_api = AsyncMock(spec=DeyeCloudApi)
    mock_api.get_device_list.return_value = [
        {
            "device_name": "Test Device",
            "device_id": "test_device_id",
            "product_id": "test_product_id",
            "platform": DeyeIotPlatform.Classic.value,
        }
    ]

    mock_mqtt_client = AsyncMock(spec=DeyeClassicMqttClient)
    mock_state = MagicMock(spec=DeyeDeviceState)
    mock_mqtt_client.query_device_state.return_value = mock_state

    with patch(
        "libdeye.cli.DeyeClassicMqttClient", return_value=mock_mqtt_client
    ), patch("libdeye.cli.print_device_state") as mock_print_state:
        await get_device_state(mock_api, "test_device_id")

        mock_api.get_device_list.assert_called_once()
        mock_mqtt_client.connect.assert_called_once()
        mock_mqtt_client.query_device_state.assert_called_once_with(
            "test_product_id", "test_device_id"
        )
        mock_print_state.assert_called_once_with(mock_state)
        mock_mqtt_client.disconnect.assert_called_once()


@pytest.mark.asyncio
async def test_set_device_state() -> None:
    """Test setting device state."""
    mock_api = AsyncMock(spec=DeyeCloudApi)
    mock_api.get_device_list.return_value = [
        {
            "device_name": "Test Device",
            "device_id": "test_device_id",
            "product_id": "test_product_id",
            "platform": DeyeIotPlatform.Classic.value,
        }
    ]

    mock_mqtt_client = AsyncMock(spec=DeyeClassicMqttClient)
    mock_state = MagicMock(spec=DeyeDeviceState)
    mock_command = MagicMock()
    mock_state.to_command.return_value = mock_command

    # Configure AsyncMock to return awaitable values
    mock_mqtt_client.connect.return_value = None
    mock_mqtt_client.query_device_state.return_value = mock_state
    mock_mqtt_client.publish_command.return_value = None
    mock_mqtt_client.disconnect.return_value = None

    # Mock the wait_for function to return the mock_state directly
    with patch("libdeye.cli.DeyeClassicMqttClient", return_value=mock_mqtt_client):
        await set_device_state(
            mock_api,
            "test_device_id",
            power=True,
            mode=DeyeDeviceMode.AUTO_MODE,
            fan_speed=DeyeFanSpeed.HIGH,
            target_humidity=50,
            anion=True,
            water_pump=False,
            oscillating=True,
            child_lock=False,
        )

        mock_api.get_device_list.assert_called_once()
        mock_mqtt_client.connect.assert_called_once()
        mock_mqtt_client.query_device_state.assert_called_once_with(
            "test_product_id", "test_device_id"
        )
        mock_state.to_command.assert_called_once()

        # Check that command properties were set correctly
        assert mock_command.power_switch is True
        assert mock_command.mode is DeyeDeviceMode.AUTO_MODE
        assert mock_command.fan_speed is DeyeFanSpeed.HIGH
        assert mock_command.target_humidity == 50
        assert mock_command.anion_switch is True
        assert mock_command.water_pump_switch is False
        assert mock_command.oscillating_switch is True
        assert mock_command.child_lock_switch is False

        mock_mqtt_client.publish_command.assert_called_once_with(
            "test_product_id", "test_device_id", mock_command
        )
        mock_mqtt_client.disconnect.assert_called_once()


@pytest.mark.asyncio
async def test_monitor_device() -> None:
    """Test monitoring device."""
    mock_api = AsyncMock(spec=DeyeCloudApi)
    mock_api.get_device_list.return_value = [
        {
            "device_name": "Test Device",
            "device_id": "test_device_id",
            "product_id": "test_product_id",
            "platform": DeyeIotPlatform.Classic.value,
        }
    ]

    mock_mqtt_client = AsyncMock(spec=DeyeClassicMqttClient)
    mock_mqtt_client.connect.return_value = None
    mock_mqtt_client.disconnect.return_value = None
    mock_mqtt_client.subscribe_state_change.return_value = lambda: None
    mock_mqtt_client.subscribe_availability_change.return_value = lambda: None

    # Mock the infinite_future to be a future that we can control
    mock_infinite_future: asyncio.Future[None] = asyncio.Future()

    with patch(
        "libdeye.cli.DeyeClassicMqttClient", return_value=mock_mqtt_client
    ), patch("asyncio.Future", return_value=mock_infinite_future):

        # Start the monitor_device function in a task
        task = asyncio.create_task(monitor_device(mock_api, "test_device_id"))

        # Wait a short time for the function to execute
        await asyncio.sleep(0.1)

        # Now set the result of the infinite_future to exit the function
        mock_infinite_future.set_result(None)

        # Wait for the task to complete
        await task

        # Verify the function executed correctly
        mock_api.get_device_list.assert_called_once()
        mock_mqtt_client.connect.assert_called_once()
        mock_mqtt_client.subscribe_state_change.assert_called_once()
        mock_mqtt_client.subscribe_availability_change.assert_called_once()
        mock_mqtt_client.disconnect.assert_called_once()


@pytest.mark.asyncio
async def test_print_auth_token() -> None:
    """Test printing authentication token."""
    mock_api = AsyncMock(spec=DeyeCloudApi)
    mock_api.auth_token = "test_token"

    with patch("sys.stdout") as mock_stdout:
        await print_auth_token(mock_api)

        # Check that output contains token information
        output = "".join(call.args[0] for call in mock_stdout.write.call_args_list)
        assert "Authentication token: test_token" in output
        assert "DEYE_AUTH_TOKEN=<your_token_here>" in output


@pytest.mark.asyncio
async def test_refresh_token() -> None:
    """Test refreshing authentication token."""
    mock_api = AsyncMock(spec=DeyeCloudApi)
    mock_api.auth_token = "new_test_token"

    with patch("sys.stdout") as mock_stdout:
        await refresh_token(mock_api)

        mock_api.refresh_token_if_near_expiry.assert_called_once_with(force=True)

        # Check that output contains token information
        output = "".join(call.args[0] for call in mock_stdout.write.call_args_list)
        assert "Authentication token refreshed successfully" in output
        assert "New token: new_test_token" in output
        assert "DEYE_AUTH_TOKEN=<your_token_here>" in output


@pytest.mark.asyncio
async def test_run_cli_devices_command() -> None:
    """Test running CLI with devices command."""
    mock_args = MagicMock(spec=argparse.Namespace)
    mock_args.command = "devices"

    mock_api = AsyncMock(spec=DeyeCloudApi)

    with patch(
        "libdeye.cli.authenticate", return_value=mock_api
    ) as mock_authenticate, patch("libdeye.cli.list_devices") as mock_list_devices:
        await run_cli(mock_args, "test_user", "test_password", None, None)

        mock_authenticate.assert_called_once()
        mock_list_devices.assert_called_once_with(mock_api)


@pytest.mark.asyncio
async def test_run_cli_products_command() -> None:
    """Test running CLI with products command."""
    mock_args = MagicMock(spec=argparse.Namespace)
    mock_args.command = "products"

    mock_api = AsyncMock(spec=DeyeCloudApi)

    with patch(
        "libdeye.cli.authenticate", return_value=mock_api
    ) as mock_authenticate, patch("libdeye.cli.list_products") as mock_list_products:
        await run_cli(mock_args, "test_user", "test_password", None, None)

        mock_authenticate.assert_called_once()
        mock_list_products.assert_called_once_with(mock_api)


@pytest.mark.asyncio
async def test_run_cli_get_command() -> None:
    """Test running CLI with get command."""
    mock_args = MagicMock(spec=argparse.Namespace)
    mock_args.command = "get"

    mock_api = AsyncMock(spec=DeyeCloudApi)

    with patch(
        "libdeye.cli.authenticate", return_value=mock_api
    ) as mock_authenticate, patch(
        "libdeye.cli.get_device_state"
    ) as mock_get_device_state:
        await run_cli(mock_args, "test_user", "test_password", None, "test_device_id")

        mock_authenticate.assert_called_once()
        mock_get_device_state.assert_called_once_with(mock_api, "test_device_id")


@pytest.mark.asyncio
async def test_run_cli_set_command() -> None:
    """Test running CLI with set command."""
    mock_args = MagicMock(spec=argparse.Namespace)
    mock_args.command = "set"
    mock_args.power = "on"
    mock_args.mode = "AUTO_MODE"
    mock_args.fan_speed = "HIGH"
    mock_args.target_humidity = 50
    mock_args.anion = "on"
    mock_args.water_pump = "off"
    mock_args.oscillating = "on"
    mock_args.child_lock = "off"

    mock_api = AsyncMock(spec=DeyeCloudApi)

    with patch(
        "libdeye.cli.authenticate", return_value=mock_api
    ) as mock_authenticate, patch(
        "libdeye.cli.set_device_state"
    ) as mock_set_device_state:
        await run_cli(mock_args, "test_user", "test_password", None, "test_device_id")

        mock_authenticate.assert_called_once()
        mock_set_device_state.assert_called_once_with(
            mock_api,
            "test_device_id",
            power=True,
            mode=DeyeDeviceMode.AUTO_MODE,
            fan_speed=DeyeFanSpeed.HIGH,
            target_humidity=50,
            anion=True,
            water_pump=False,
            oscillating=True,
            child_lock=False,
        )


@pytest.mark.asyncio
async def test_run_cli_monitor_command() -> None:
    """Test running CLI with monitor command."""
    mock_args = MagicMock(spec=argparse.Namespace)
    mock_args.command = "monitor"

    mock_api = AsyncMock(spec=DeyeCloudApi)

    with patch(
        "libdeye.cli.authenticate", return_value=mock_api
    ) as mock_authenticate, patch("libdeye.cli.monitor_device") as mock_monitor_device:
        await run_cli(mock_args, "test_user", "test_password", None, "test_device_id")

        mock_authenticate.assert_called_once()
        mock_monitor_device.assert_called_once_with(mock_api, "test_device_id")


def test_main_no_command() -> None:
    """Test main function with no command."""
    mock_parser = MagicMock()
    mock_parser.parse_args.return_value.command = None

    with patch("argparse.ArgumentParser", return_value=mock_parser), patch(
        "libdeye.cli.run_cli"
    ) as mock_run:
        with pytest.raises(SystemExit) as excinfo:
            main()

        assert excinfo.value.code == 1
        mock_run.assert_not_called()
        mock_parser.print_help.assert_called_once()


def test_main_no_auth_credentials() -> None:
    """Test main function with no authentication credentials."""
    mock_args = MagicMock()
    mock_args.command = "devices"
    mock_args.username = None
    mock_args.password = None
    mock_args.token = None
    mock_args.env_file = ".env"
    mock_args.debug = False

    mock_parser = MagicMock()
    mock_parser.parse_args.return_value = mock_args

    with patch("argparse.ArgumentParser", return_value=mock_parser), patch(
        "libdeye.cli.load_env_file", return_value={}
    ), patch("builtins.print") as mock_print, patch("libdeye.cli.run_cli") as mock_run:
        with pytest.raises(SystemExit) as excinfo:
            main()

        assert excinfo.value.code == 1
        mock_run.assert_not_called()
        # Check that error message was printed
        mock_print.assert_any_call(
            "Error: You must provide either a token or username and password"
        )


def test_main_no_device_id() -> None:
    """Test main function with no device ID for commands that require it."""
    for command in ["get", "set", "monitor"]:
        mock_args = MagicMock()
        mock_args.command = command
        mock_args.username = "test_user"
        mock_args.password = "test_password"
        mock_args.token = None
        mock_args.device_id = None
        mock_args.env_file = ".env"
        mock_args.debug = False

        mock_parser = MagicMock()
        mock_parser.parse_args.return_value = mock_args

        with patch("argparse.ArgumentParser", return_value=mock_parser), patch(
            "libdeye.cli.load_env_file", return_value={}
        ), patch("builtins.print") as mock_print, patch(
            "libdeye.cli.run_cli"
        ) as mock_run:
            with pytest.raises(SystemExit) as excinfo:
                main()

            assert excinfo.value.code == 1
            mock_run.assert_not_called()
            # Check that error message was printed
            mock_print.assert_any_call(
                "Error: You must provide device ID via command line arguments or in the .env file."
            )


def test_main_successful_run() -> None:
    """Test main function with successful run."""
    mock_args = MagicMock()
    mock_args.command = "devices"
    mock_args.username = "test_user"
    mock_args.password = "test_password"
    mock_args.token = None
    mock_args.env_file = ".env"
    mock_args.debug = False

    mock_parser = MagicMock()
    mock_parser.parse_args.return_value = mock_args

    with patch("argparse.ArgumentParser", return_value=mock_parser), patch(
        "libdeye.cli.load_env_file", return_value={}
    ), patch("libdeye.cli.run_cli") as mock_run, patch(
        "logging.basicConfig"
    ) as mock_logging:
        main()

        mock_logging.assert_called_once_with(
            level=logging.INFO,
            format="%(asctime)s - %(name)s - %(levelname)s - %(message)s",
        )
        mock_run.assert_called_once()


@pytest.mark.asyncio
async def test_get_classic_mqtt_info() -> None:
    """Test getting classic MQTT info."""
    mock_api = AsyncMock(spec=DeyeCloudApi)
    mock_mqtt_info = {
        "password": "test_password",
        "loginname": "test_login",
        "mqtthost": "test.mqtt.host",
        "mqttport": 1883,
        "clientid": "test_client_id",
        "endpoint": "test_endpoint",
        "sslport": 8883,
    }
    mock_api.get_deye_platform_mqtt_info.return_value = mock_mqtt_info

    with patch("builtins.print") as mock_print:
        await get_classic_mqtt_info(mock_api)

    mock_api.get_deye_platform_mqtt_info.assert_called_once()
    assert mock_print.call_count > 0
    # Check that all MQTT info values are printed in formatted strings
    mock_print.assert_any_call("Classic Platform MQTT Information:")
    mock_print.assert_any_call("  MQTT Host: test.mqtt.host")
    mock_print.assert_any_call("  SSL Port: 8883")
    mock_print.assert_any_call("  Client ID: test_client_id")
    mock_print.assert_any_call("  Username: test_login")
    mock_print.assert_any_call("  Password: test_password")
    mock_print.assert_any_call("  Endpoint: test_endpoint")


@pytest.mark.asyncio
async def test_get_fog_mqtt_info() -> None:
    """Test getting fog MQTT info."""
    mock_api = AsyncMock(spec=DeyeCloudApi)
    mock_mqtt_info = {
        "username": "test_username",
        "clientid": "test_client_id",
        "password": "test_password",
        "mqtt_host": "test.mqtt.host",
        "ws_port": "8083",
        "ssl_port": "8883",
        "topic": {
            "all": ["topic1", "topic2"],
            "pub": ["pub_topic"],
            "sub": ["sub_topic"],
        },
        "expire": 3600,
    }
    mock_api.get_fog_platform_mqtt_info.return_value = mock_mqtt_info

    with patch("builtins.print") as mock_print:
        await get_fog_mqtt_info(mock_api)

    mock_api.get_fog_platform_mqtt_info.assert_called_once()
    assert mock_print.call_count > 0
    # Check that all MQTT info values are printed in formatted strings
    mock_print.assert_any_call("Fog Platform MQTT Information:")
    mock_print.assert_any_call("  MQTT Host: test.mqtt.host")
    mock_print.assert_any_call("  SSL Port: 8883")
    mock_print.assert_any_call("  Client ID: test_client_id")
    mock_print.assert_any_call("  Username: test_username")
    mock_print.assert_any_call("  Password: test_password")
    mock_print.assert_any_call("  Expire: 3600")
    mock_print.assert_any_call(
        "  Topics: {'all': ['topic1', 'topic2'], 'pub': ['pub_topic'], 'sub': ['sub_topic']}"
    )


@pytest.mark.asyncio
async def test_run_cli_classic_mqtt_command() -> None:
    """Test running the CLI with the classic-mqtt command."""
    mock_args = MagicMock()
    mock_args.command = "classic-mqtt"

    with patch("libdeye.cli.authenticate") as mock_authenticate:
        mock_api = AsyncMock()
        mock_authenticate.return_value = mock_api

        with patch("libdeye.cli.get_classic_mqtt_info") as mock_get_classic_mqtt_info:
            await run_cli(mock_args, "test_user", "test_password", None, None)

    mock_authenticate.assert_called_once_with(ANY, "test_user", "test_password", None)
    mock_get_classic_mqtt_info.assert_called_once_with(mock_api)


@pytest.mark.asyncio
async def test_run_cli_fog_mqtt_command() -> None:
    """Test running the CLI with the fog-mqtt command."""
    mock_args = MagicMock()
    mock_args.command = "fog-mqtt"

    with patch("libdeye.cli.authenticate") as mock_authenticate:
        mock_api = AsyncMock()
        mock_authenticate.return_value = mock_api

        with patch("libdeye.cli.get_fog_mqtt_info") as mock_get_fog_mqtt_info:
            await run_cli(mock_args, "test_user", "test_password", None, None)

    mock_authenticate.assert_called_once_with(ANY, "test_user", "test_password", None)
    mock_get_fog_mqtt_info.assert_called_once_with(mock_api)


@pytest.mark.asyncio
async def test_run_cli_token_command() -> None:
    """Test running the CLI with the token command."""
    mock_args = MagicMock()
    mock_args.command = "print-token"

    with patch("libdeye.cli.authenticate") as mock_authenticate:
        mock_api = AsyncMock()
        mock_authenticate.return_value = mock_api

        with patch("libdeye.cli.print_auth_token") as mock_print_auth_token:
            await run_cli(mock_args, "test_user", "test_password", None, None)

    mock_authenticate.assert_called_once_with(ANY, "test_user", "test_password", None)
    mock_print_auth_token.assert_called_once_with(mock_api)


@pytest.mark.asyncio
async def test_run_cli_refresh_command() -> None:
    """Test running the CLI with the refresh command."""
    mock_args = MagicMock()
    mock_args.command = "refresh-token"

    with patch("libdeye.cli.authenticate") as mock_authenticate:
        mock_api = AsyncMock()
        mock_authenticate.return_value = mock_api

        with patch("libdeye.cli.refresh_token") as mock_refresh_token:
            await run_cli(mock_args, "test_user", "test_password", "test_token", None)

    mock_authenticate.assert_called_once_with(
        ANY, "test_user", "test_password", "test_token"
    )
    mock_refresh_token.assert_called_once_with(mock_api)
