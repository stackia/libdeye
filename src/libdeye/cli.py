#!/usr/bin/env python3
"""CLI tool for testing libdeye library during development."""

import argparse
import asyncio
from datetime import datetime
import logging
import os
from pathlib import Path
from signal import SIGINT, SIGTERM
import sys
from typing import cast

import aiohttp

from .client import DeyeClient
from .cloud_api import DeyeCloudApi, DeyeIotPlatform, transport_for_device
from .const import DeyeDeviceMode, DeyeFanSpeed
from .device_state import DeyeDeviceState

CONFIG_ENV_KEYS = (
    "DEYE_USERNAME",
    "DEYE_PASSWORD",
    "DEYE_AUTH_TOKEN",
    "DEYE_DEVICE_ID",
)


def load_env_file(env_file: str = ".env") -> dict[str, str]:
    """Load environment variables from a .env file."""
    env_vars = {}
    env_path = Path(env_file)

    if env_path.exists():
        with env_path.open(encoding="utf-8") as f:
            for line in f:
                line = line.strip()
                if not line or line.startswith("#"):
                    continue
                key, value = line.split("=", 1)
                env_vars[key.strip()] = value.strip().strip("'\"")

    return env_vars


def load_config(env_file: str = ".env") -> dict[str, str]:
    """Load configuration from a .env file and process environment variables.

    Process environment variables take precedence over values from the .env
    file, matching the default behavior of python-dotenv and 12-factor app
    conventions. Command-line arguments are applied separately and take
    precedence over both.
    """
    config = load_env_file(env_file)
    for key in CONFIG_ENV_KEYS:
        value = os.environ.get(key)
        if value is not None:
            config[key] = value
    return config


async def authenticate(
    session: aiohttp.ClientSession,
    username: str,
    password: str,
    auth_token: str | None = None,
) -> DeyeCloudApi:
    """Authenticate with Deye Cloud API."""
    api = DeyeCloudApi(session, username, password, auth_token)
    if not auth_token:
        await api.authenticate()
    return api


async def list_devices(api: DeyeCloudApi) -> None:
    """List all devices associated with the account."""
    devices = await api.get_device_list()
    print(f"Found {len(devices)} device(s):")
    for i, device in enumerate(devices, 1):
        print(
            f"{i}. {device['device_name']} ({device['device_id']}) - {'Online' if device['online'] else 'Offline'}"
        )
        print(
            f"   Product: {device['product_name']} ({device['product_id']}) ({device['product_type']})"
        )
        print(f"   MAC: {device['mac']}")
        try:
            platform_name = DeyeIotPlatform(device["platform"]).name
        except ValueError:
            platform_name = f"Unknown({device['platform']})"
        transport_name = transport_for_device(device).name
        print(f"   Platform: {platform_name}")
        print(f"   Transport: {transport_name}")
        print()


async def list_products(api: DeyeCloudApi) -> None:
    """List all available products from Deye."""
    product_types = await api.get_product_list()
    print(f"Found {len(product_types)} product type(s):")

    for product_type in product_types:
        print(f"\n{product_type['ptypename']} ({product_type['ptype']}):")
        print(f"  Total products: {len(product_type['pdata'])}")

        for i, product in enumerate(product_type["pdata"], 1):
            print(f"  {i}. {product['pname']} ({product['productid']})")
            print(f"     Model: {product['model']}")
            print(f"     Brand: {product['brand']}")
            print(f"     Status: {'Inactive' if product['status'] == 1 else 'Active'}")
            print(f"     Config Type: {product['configType']}")
            if product["config_guide"]:
                print(f"     Config Guide: {product['config_guide']}")
            print()


def print_device_state(state: DeyeDeviceState) -> None:
    """Print the state of a device."""
    print(f"  Power: {'On' if state.power_switch else 'Off'}")
    print(f"  Mode: {state.mode.name}")
    print(f"  Fan Speed: {state.fan_speed.name}")
    print(f"  Target Humidity: {state.target_humidity}%")
    print(f"  Current Humidity: {state.environment_humidity}%")
    print(f"  Current Temperature: {state.environment_temperature}°C")
    print(f"  Anion: {'On' if state.anion_switch else 'Off'}")
    print(f"  Water Pump: {'On' if state.water_pump_switch else 'Off'}")
    print(f"  Oscillating: {'On' if state.oscillating_switch else 'Off'}")
    print(f"  Child Lock: {'On' if state.child_lock_switch else 'Off'}")
    print(f"  UV: {'On' if state.uv_switch else 'Off'}")
    print(f"  Prompt Sound: {'On' if state.prompt_sound else 'Off'}")
    print(f"  Screen Display: {'On' if state.screen_display else 'Off'}")
    print(f"  Water Tank Full: {'Yes' if state.water_tank_full else 'No'}")
    print(f"  Defrosting: {'Yes' if state.defrosting else 'No'}")
    if isinstance(state.timed_off_hour, int):
        print(f"  Timed Off Hour: {state.timed_off_hour}")


async def get_device_state(api: DeyeCloudApi, device_id: str) -> None:
    """Get the current state of a device."""
    client = DeyeClient(api)
    device = await client.get_device(device_id)

    if not device:
        print(f"Device {device_id} not found")
        return

    try:
        state = await asyncio.wait_for(device.refresh(), timeout=10.0)
        print(f"Device State for {device.name} ({device_id}):")
        print_device_state(state)
    except TimeoutError:
        print(f"Timeout waiting for device state for {device.name} ({device_id})")
    finally:
        client.disconnect()


async def set_device_state(
    api: DeyeCloudApi,
    device_id: str,
    power: bool | None = None,
    mode: DeyeDeviceMode | None = None,
    fan_speed: DeyeFanSpeed | None = None,
    target_humidity: int | None = None,
    anion: bool | None = None,
    water_pump: bool | None = None,
    oscillating: bool | None = None,
    child_lock: bool | None = None,
    uv: bool | None = None,
    prompt_sound: bool | None = None,
    screen_display: bool | None = None,
    timed_off_hour: int | None = None,
) -> None:
    """Set the state of a device."""
    client = DeyeClient(api)
    device = await client.get_device(device_id)

    if not device:
        print(f"Device {device_id} not found")
        return

    try:
        state = await asyncio.wait_for(device.refresh(), timeout=10.0)
        command = state.to_command()

        if power is not None:
            command.power_switch = power
        if mode is not None:
            command.mode = mode
        if fan_speed is not None:
            command.fan_speed = fan_speed
        if target_humidity is not None:
            command.target_humidity = target_humidity
        if anion is not None:
            command.anion_switch = anion
        if water_pump is not None:
            command.water_pump_switch = water_pump
        if oscillating is not None:
            command.oscillating_switch = oscillating
        if child_lock is not None:
            command.child_lock_switch = child_lock
        if uv is not None:
            command.uv_switch = uv
        if prompt_sound is not None:
            command.prompt_sound = prompt_sound
        if screen_display is not None:
            command.screen_display = screen_display
        if timed_off_hour is not None:
            command.timed_off_hour = timed_off_hour

        await device.apply(command, baseline=state)
        print(f"Command sent to device {device.name} ({device_id})")
    except TimeoutError:
        print(f"Timeout waiting for device state for {device.name} ({device_id})")
    finally:
        client.disconnect()


async def monitor_device(api: DeyeCloudApi, device_id: str) -> None:
    """Monitor a device for state updates."""
    client = DeyeClient(api)
    device = await client.get_device(device_id)

    if not device:
        print(f"Device {device_id} not found")
        return

    await device.ensure_connected()

    def on_state_update(state: DeyeDeviceState) -> None:
        print(
            f"\nState update detected at {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}:"
        )
        print_device_state(state)

    def on_availability_change(available: bool) -> None:
        print(f"\nDevice availability changed: {'Online' if available else 'Offline'}")

    unsubscribe = device.subscribe(
        on_state=on_state_update, on_availability=on_availability_change
    )

    try:
        print(f"Monitoring device {device.name} ({device_id})...")
        infinite_future: asyncio.Future[None] = asyncio.Future()
        for signal in (SIGINT, SIGTERM):
            asyncio.get_running_loop().add_signal_handler(
                signal, infinite_future.set_result, None
            )
        await infinite_future
        print("Received exit, exiting")
    finally:
        unsubscribe()
        client.disconnect()


async def print_auth_token(api: DeyeCloudApi) -> None:
    """Print the authentication token."""
    print(f"Authentication token: {api.auth_token}")
    print(
        "\nYou can add this token to your .env file to avoid sending username/password with each request:"
    )
    print("DEYE_AUTH_TOKEN=<your_token_here>")


async def refresh_token(api: DeyeCloudApi) -> None:
    """Force refresh the authentication token."""
    await api.refresh_token_if_near_expiry(force=True)
    print("Authentication token refreshed successfully.")
    print(f"New token: {api.auth_token}")
    print("\nYou can update this token in your .env file:")
    print("DEYE_AUTH_TOKEN=<your_token_here>")


async def get_classic_mqtt_info(api: DeyeCloudApi) -> None:
    """Get and display Classic platform MQTT information."""
    mqtt_info = await api.get_deye_platform_mqtt_info()
    print("Classic Platform MQTT Information:")
    print(f"  MQTT Host: {mqtt_info['mqtthost']}")
    print(f"  SSL Port: {mqtt_info['sslport']}")
    print(f"  Client ID: {mqtt_info.get('clientid', 'N/A')}")
    print(f"  Username: {mqtt_info['loginname']}")
    print(f"  Password: {mqtt_info['password']}")
    print(f"  Endpoint: {mqtt_info['endpoint']}")


async def get_fog_mqtt_info(api: DeyeCloudApi) -> None:
    """Get and display Fog platform MQTT information."""
    mqtt_info = await api.get_fog_platform_mqtt_info()
    print("Fog Platform MQTT Information:")
    print(f"  MQTT Host: {mqtt_info['mqtt_host']}")
    print(f"  SSL Port: {mqtt_info['ssl_port']}")
    print(f"  Client ID: {mqtt_info.get('clientid', 'N/A')}")
    print(f"  Username: {mqtt_info['username']}")
    print(f"  Password: {mqtt_info['password']}")
    print(f"  Expire: {mqtt_info['expire']}")
    print(f"  Topics: {mqtt_info['topic']}")


async def run_cli(
    args: argparse.Namespace,
    username: str,
    password: str,
    auth_token: str | None,
    device_id: str | None,
) -> None:
    """Run the CLI with the given arguments."""
    # Create a single aiohttp session for the entire lifetime of the CLI
    async with aiohttp.ClientSession() as session:
        # Authenticate with Deye Cloud
        api = await authenticate(session, username, password, auth_token)

        if args.command == "devices":
            await list_devices(api)
        elif args.command == "products":
            await list_products(api)
        elif args.command == "get":
            await get_device_state(api, cast(str, device_id))
        elif args.command == "set":
            # Convert string arguments to appropriate types
            power = None
            if args.power:
                power = args.power == "on"

            mode = None
            if args.mode:
                mode = DeyeDeviceMode[args.mode]

            fan_speed = None
            if args.fan_speed:
                fan_speed = DeyeFanSpeed[args.fan_speed]

            anion = None
            if args.anion:
                anion = args.anion == "on"

            water_pump = None
            if args.water_pump:
                water_pump = args.water_pump == "on"

            oscillating = None
            if args.oscillating:
                oscillating = args.oscillating == "on"

            child_lock = None
            if args.child_lock:
                child_lock = args.child_lock == "on"

            uv = None
            if args.uv:
                uv = args.uv == "on"

            prompt_sound = None
            if args.prompt_sound:
                prompt_sound = args.prompt_sound == "on"

            screen_display = None
            if args.screen_display:
                screen_display = args.screen_display == "on"

            await set_device_state(
                api,
                cast(str, device_id),
                power=power,
                mode=mode,
                fan_speed=fan_speed,
                target_humidity=args.target_humidity,
                anion=anion,
                water_pump=water_pump,
                oscillating=oscillating,
                child_lock=child_lock,
                uv=uv,
                prompt_sound=prompt_sound,
                screen_display=screen_display,
                timed_off_hour=args.timed_off_hour,
            )
        elif args.command == "monitor":
            await monitor_device(api, cast(str, device_id))
        elif args.command == "print-token":
            await print_auth_token(api)
        elif args.command == "refresh-token":
            await refresh_token(api)
        elif args.command == "classic-mqtt":
            await get_classic_mqtt_info(api)
        elif args.command == "fog-mqtt":
            await get_fog_mqtt_info(api)


def main() -> None:
    """Main entry point for the CLI."""
    parser = argparse.ArgumentParser(description="Deye Cloud CLI")
    parser.add_argument("--debug", action="store_true", help="Enable debug logging")
    parser.add_argument(
        "--env-file",
        default=".env",
        help="Path to .env file used as defaults when environment variables are unset (default: .env)",
    )

    # Authentication options
    auth_group = parser.add_argument_group("Authentication")
    auth_group.add_argument("--username", "-u", help="Deye Cloud username")
    auth_group.add_argument("--password", "-p", help="Deye Cloud password")
    auth_group.add_argument(
        "--token", help="Deye Cloud auth token (if already authenticated)"
    )

    # Subcommands
    subparsers = parser.add_subparsers(dest="command", help="Command to execute")

    # List devices command
    subparsers.add_parser(
        "devices", help="List devices with platform and command transport"
    )

    # List products command
    subparsers.add_parser("products", help="List all available products")

    # Get device state command
    get_parser = subparsers.add_parser(
        "get", help="Get device state (Classic, Fog, or Combo)"
    )
    get_parser.add_argument("--device-id", help="Device ID")

    # Set device state command
    set_parser = subparsers.add_parser(
        "set", help="Set device state (Classic, Fog, or Combo)"
    )
    set_parser.add_argument("--device-id", help="Device ID")
    set_parser.add_argument("--power", choices=["on", "off"], help="Power state")
    set_parser.add_argument(
        "--mode", choices=[mode.name for mode in DeyeDeviceMode], help="Device mode"
    )
    set_parser.add_argument(
        "--fan-speed", choices=[speed.name for speed in DeyeFanSpeed], help="Fan speed"
    )
    set_parser.add_argument(
        "--target-humidity",
        type=int,
        help="Target humidity percentage (product-specific range)",
    )
    set_parser.add_argument("--anion", choices=["on", "off"], help="Anion state")
    set_parser.add_argument(
        "--water-pump", choices=["on", "off"], help="Water pump state"
    )
    set_parser.add_argument(
        "--oscillating", choices=["on", "off"], help="Oscillating state"
    )
    set_parser.add_argument(
        "--child-lock", choices=["on", "off"], help="Child lock state"
    )
    set_parser.add_argument("--uv", choices=["on", "off"], help="UV light switch")
    set_parser.add_argument(
        "--prompt-sound", choices=["on", "off"], help="Prompt sound (Fog HTTP)"
    )
    set_parser.add_argument(
        "--screen-display", choices=["on", "off"], help="Screen display (Fog HTTP)"
    )
    set_parser.add_argument(
        "--timed-off-hour",
        type=int,
        help="Timed-off hour setting (Fog HTTP TimedOffHour)",
    )

    # Monitor device command
    monitor_parser = subparsers.add_parser(
        "monitor", help="Monitor MQTT state and availability"
    )
    monitor_parser.add_argument("--device-id", help="Device ID")

    # Print token command
    subparsers.add_parser(
        "print-token", help="Print the authentication token for use in .env file"
    )

    # Refresh token command
    subparsers.add_parser(
        "refresh-token", help="Force refresh the authentication token"
    )

    # Get Deye platform MQTT info command
    subparsers.add_parser(
        "classic-mqtt", help="Get MQTT information for Classic platform"
    )

    # Get Fog platform MQTT info command
    subparsers.add_parser("fog-mqtt", help="Get MQTT information for Fog platform")

    args = parser.parse_args()

    # Set up logging
    log_level = logging.DEBUG if args.debug else logging.INFO
    logging.basicConfig(
        level=log_level,
        format="%(asctime)s - %(name)s - %(levelname)s - %(message)s",
    )

    # Check if a command was specified
    if not args.command:
        parser.print_help()
        sys.exit(1)

    # CLI args > process environment variables > .env file
    env_vars = load_config(args.env_file)

    username = args.username or env_vars.get("DEYE_USERNAME")
    password = args.password or env_vars.get("DEYE_PASSWORD")
    auth_token = args.token or env_vars.get("DEYE_AUTH_TOKEN")

    # Check if authentication credentials were provided
    if not auth_token and (not username or not password):
        print("Error: You must provide either a token or username and password")
        print(
            "       via command line arguments, environment variables, or a .env file."
        )
        print(
            "       Expected environment variables: DEYE_USERNAME, DEYE_PASSWORD, or DEYE_AUTH_TOKEN"
        )
        sys.exit(1)

    # Get device ID from command line args, environment, or .env file
    device_id = None

    if args.command in ["get", "set", "monitor"]:
        device_id = args.device_id or env_vars.get("DEYE_DEVICE_ID")

        if not device_id:
            print(
                "Error: You must provide device ID via command line arguments, "
                "environment variables, or a .env file."
            )
            print("       Expected environment variables: DEYE_DEVICE_ID")
            sys.exit(1)

    # Run the CLI
    asyncio.run(
        run_cli(
            args,
            cast(str, username),
            cast(str, password),
            auth_token,
            device_id,
        )
    )


if __name__ == "__main__":
    main()
