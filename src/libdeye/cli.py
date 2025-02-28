#!/usr/bin/env python3
"""CLI tool for testing libdeye library during development."""

import argparse
import asyncio
import logging
import sys
from pathlib import Path
from typing import Optional, cast

import aiohttp

from libdeye.cloud_api import DeyeCloudApi
from libdeye.device_state_command import DeyeDeviceState
from libdeye.mqtt_client import DeyeMqttClient
from libdeye.types import (
    DeyeDeviceMode,
    DeyeFanSpeed,
)


def load_env_file(env_file: str = ".env") -> dict[str, str]:
    """Load environment variables from a .env file."""
    env_vars = {}
    env_path = Path(env_file)

    if env_path.exists():
        with open(env_path, "r") as f:
            for line in f:
                line = line.strip()
                if not line or line.startswith("#"):
                    continue
                key, value = line.split("=", 1)
                env_vars[key.strip()] = value.strip().strip("'\"")

    return env_vars


async def authenticate(
    session: aiohttp.ClientSession,
    username: str,
    password: str,
    auth_token: Optional[str] = None,
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
        print(f"   Product: {device['product_name']} ({device['product_id']})")
        print(f"   MAC: {device['mac']}")
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


async def get_device_state(api: DeyeCloudApi, device_id: str, product_id: str) -> None:
    """Get the current state of a device."""
    # Get MQTT info
    mqtt_info = await api.get_deye_platform_mqtt_info()

    # Connect to MQTT
    mqtt_client = DeyeMqttClient(
        host=mqtt_info["mqtthost"],
        ssl_port=mqtt_info["sslport"],
        username=mqtt_info["loginname"],
        password=mqtt_info["password"],
        endpoint=mqtt_info["endpoint"],
    )

    # Create a future to get the device state
    state_future = mqtt_client.query_device_state(product_id, device_id)

    # Connect to MQTT
    mqtt_client.connect()

    try:
        # Wait for the state with a timeout
        state = await asyncio.wait_for(state_future, timeout=10.0)

        # Print the state
        print(f"Device State for {device_id}:")
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
        print(f"  Water Tank Full: {'Yes' if state.water_tank_full else 'No'}")
        print(f"  Defrosting: {'Yes' if state.defrosting else 'No'}")

    except asyncio.TimeoutError:
        print(f"Timeout waiting for device state for {device_id}")
    finally:
        # Disconnect from MQTT
        mqtt_client.disconnect()


async def set_device_state(
    api: DeyeCloudApi,
    device_id: str,
    product_id: str,
    power: Optional[bool] = None,
    mode: Optional[DeyeDeviceMode] = None,
    fan_speed: Optional[DeyeFanSpeed] = None,
    target_humidity: Optional[int] = None,
    anion: Optional[bool] = None,
    water_pump: Optional[bool] = None,
    oscillating: Optional[bool] = None,
    child_lock: Optional[bool] = None,
) -> None:
    """Set the state of a device."""
    # Get MQTT info
    mqtt_info = await api.get_deye_platform_mqtt_info()

    # Connect to MQTT
    mqtt_client = DeyeMqttClient(
        host=mqtt_info["mqtthost"],
        ssl_port=mqtt_info["sslport"],
        username=mqtt_info["loginname"],
        password=mqtt_info["password"],
        endpoint=mqtt_info["endpoint"],
    )

    # First get the current state
    state_future = mqtt_client.query_device_state(product_id, device_id)

    # Connect to MQTT
    mqtt_client.connect()

    try:
        # Wait for the state with a timeout
        current_state = await asyncio.wait_for(state_future, timeout=10.0)

        # Create a command based on the current state
        command = current_state.to_command()

        # Update the command with the new values
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

        # Send the command
        mqtt_client.publish_command(product_id, device_id, command.bytes())

        print(f"Command sent to device {device_id}")

    except asyncio.TimeoutError:
        print(f"Timeout waiting for device state for {device_id}")
    finally:
        # Disconnect from MQTT
        mqtt_client.disconnect()


async def monitor_device(
    api: DeyeCloudApi, device_id: str, product_id: str, duration: int = 60
) -> None:
    """Monitor a device for state changes for a specified duration (in seconds)."""
    # Get MQTT info
    mqtt_info = await api.get_deye_platform_mqtt_info()

    # Connect to MQTT
    mqtt_client = DeyeMqttClient(
        host=mqtt_info["mqtthost"],
        ssl_port=mqtt_info["sslport"],
        username=mqtt_info["loginname"],
        password=mqtt_info["password"],
        endpoint=mqtt_info["endpoint"],
    )

    # Connect to MQTT
    mqtt_client.connect()

    # Set up state change callback
    def on_state_change(state: DeyeDeviceState) -> None:
        print(f"\nState change detected at {asyncio.get_event_loop().time():.2f}:")
        print(f"  Power: {'On' if state.power_switch else 'Off'}")
        print(f"  Mode: {state.mode.name}")
        print(f"  Fan Speed: {state.fan_speed.name}")
        print(f"  Target Humidity: {state.target_humidity}%")
        print(f"  Current Humidity: {state.environment_humidity}%")
        print(f"  Current Temperature: {state.environment_temperature}°C")

    # Set up availability change callback
    def on_availability_change(available: bool) -> None:
        print(f"\nDevice availability changed: {'Online' if available else 'Offline'}")

    # Subscribe to state and availability changes
    unsubscribe_state = mqtt_client.subscribe_state_change(
        product_id, device_id, on_state_change
    )
    unsubscribe_availability = mqtt_client.subscribe_availability_change(
        product_id, device_id, on_availability_change
    )

    try:
        print(f"Monitoring device {device_id} for {duration} seconds...")
        # Wait for the specified duration
        await asyncio.sleep(duration)
    finally:
        # Unsubscribe and disconnect
        unsubscribe_state()
        unsubscribe_availability()
        mqtt_client.disconnect()


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


async def run_cli(
    args: argparse.Namespace,
    username: str,
    password: str,
    auth_token: Optional[str],
    device_id: Optional[str],
    product_id: Optional[str],
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
            await get_device_state(api, cast(str, device_id), cast(str, product_id))
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

            await set_device_state(
                api,
                cast(str, device_id),
                cast(str, product_id),
                power=power,
                mode=mode,
                fan_speed=fan_speed,
                target_humidity=args.target_humidity,
                anion=anion,
                water_pump=water_pump,
                oscillating=oscillating,
                child_lock=child_lock,
            )
        elif args.command == "monitor":
            await monitor_device(
                api, cast(str, device_id), cast(str, product_id), args.duration
            )
        elif args.command == "print-token":
            await print_auth_token(api)
        elif args.command == "refresh-token":
            await refresh_token(api)


def main() -> None:
    """Main entry point for the CLI."""
    parser = argparse.ArgumentParser(description="Deye Cloud CLI")
    parser.add_argument("--debug", action="store_true", help="Enable debug logging")
    parser.add_argument(
        "--env-file",
        default=".env",
        help="Path to .env file (default: .env in current directory)",
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
    subparsers.add_parser("devices", help="List all devices")

    # List products command
    subparsers.add_parser("products", help="List all available products")

    # Get device state command
    get_parser = subparsers.add_parser("get", help="Get device state")
    get_parser.add_argument("--device-id", help="Device ID")
    get_parser.add_argument("--product-id", help="Product ID")

    # Set device state command
    set_parser = subparsers.add_parser("set", help="Set device state")
    set_parser.add_argument("--device-id", help="Device ID")
    set_parser.add_argument("--product-id", help="Product ID")
    set_parser.add_argument("--power", choices=["on", "off"], help="Power state")
    set_parser.add_argument(
        "--mode", choices=[mode.name for mode in DeyeDeviceMode], help="Device mode"
    )
    set_parser.add_argument(
        "--fan-speed", choices=[speed.name for speed in DeyeFanSpeed], help="Fan speed"
    )
    set_parser.add_argument(
        "--target-humidity", type=int, help="Target humidity percentage (30-80)"
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

    # Monitor device command
    monitor_parser = subparsers.add_parser(
        "monitor", help="Monitor device state changes"
    )
    monitor_parser.add_argument("--device-id", help="Device ID")
    monitor_parser.add_argument("--product-id", help="Product ID")
    monitor_parser.add_argument(
        "--duration",
        type=int,
        default=60,
        help="Duration to monitor in seconds (default: 60)",
    )

    # Print token command
    subparsers.add_parser(
        "print-token", help="Print the authentication token for use in .env file"
    )

    # Refresh token command
    subparsers.add_parser(
        "refresh-token", help="Force refresh the authentication token"
    )

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

    # Load environment variables from .env file
    env_vars = load_env_file(args.env_file)

    # Get username and password from command line args or .env file
    username = args.username or env_vars.get("DEYE_USERNAME")
    password = args.password or env_vars.get("DEYE_PASSWORD")
    auth_token = args.token or env_vars.get("DEYE_AUTH_TOKEN")

    # Check if authentication credentials were provided
    if not auth_token and (not username or not password):
        print("Error: You must provide either a token or username and password")
        print("       via command line arguments or in the .env file.")
        print(
            "       Expected environment variables: DEYE_USERNAME, DEYE_PASSWORD, or DEYE_AUTH_TOKEN"
        )
        sys.exit(1)

    # Get device ID and product ID from command line args or .env file
    device_id = None
    product_id = None

    if args.command in ["get", "set", "monitor"]:
        device_id = args.device_id or env_vars.get("DEYE_DEVICE_ID")
        product_id = args.product_id or env_vars.get("DEYE_PRODUCT_ID")

        if not device_id or not product_id:
            print("Error: You must provide both device ID and product ID")
            print("       via command line arguments or in the .env file.")
            print(
                "       Expected environment variables: DEYE_DEVICE_ID, DEYE_PRODUCT_ID"
            )
            sys.exit(1)

    # Run the CLI
    asyncio.run(
        run_cli(
            args,
            cast(str, username),
            cast(str, password),
            auth_token,
            device_id,
            product_id,
        )
    )


if __name__ == "__main__":
    main()
