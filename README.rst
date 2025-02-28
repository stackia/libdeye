.. image:: https://img.shields.io/badge/-PyScaffold-005CA0?logo=pyscaffold
    :alt: Project generated with PyScaffold
    :target: https://pyscaffold.org/
.. image:: https://results.pre-commit.ci/badge/github/stackia/libdeye/main.svg
    :target: https://results.pre-commit.ci/latest/github/stackia/libdeye/main
    :alt: pre-commit.ci status
.. image:: https://codecov.io/gh/stackia/libdeye/graph/badge.svg?token=DUKdoWnYcw
    :target: https://codecov.io/gh/stackia/libdeye
    :alt: Codecov
.. image:: https://img.shields.io/pypi/v/libdeye.svg
    :target: https://pypi.org/project/libdeye/
    :alt: PyPI-Server
.. image:: https://pepy.tech/badge/libdeye/month
    :alt: Monthly Downloads
    :target: https://pepy.tech/project/libdeye
.. image:: https://readthedocs.org/projects/libdeye/badge/?version=latest
    :alt: ReadTheDocs
    :target: https://libdeye.readthedocs.io/en/stable/

=======
libdeye
=======


    A Python library to connect to `Deye 德业 <https://deye.com/>`_ Cloud and control Deye dehumidifier devices.


Supported devices:

* DYD-B12A3
* DYD-D50A3
* DYD-D50B3
* DYD-E12A3
* DYD-G25A3
* DYD-N20A3
* DYD-L48A3
* DYD-T22A3
* DYD-U20A3
* DYD-U20Air
* DYD-V58A3
* DYD-W20A3
* DYD-W20A3-京鱼座
* DYD-X20A3
* DYD-Z12A3
* DYD-Z20B3
* DYD-Z20B3-天猫精灵
* DYD-S12A3
* DYD-F20C3
* JD121EC
* JD201FC
* TM208FC
* DY-612S
* DY-620S
* DY-8220C
* DY-890C
* DY-890T
* DY-6138A
* DY-8138C
* DY-8158C
* DY-8158T


For devices not in the above list, consider `adding your own definitions here <https://github.com/stackia/libdeye/blob/master/src/libdeye/const.py>`_.

-----------------
Command Line Tool
-----------------

This library also includes a command-line tool for testing and interacting with Deye devices.

Installation
------------

The CLI tool is automatically installed when you install the library:

.. code-block:: bash

    pip install libdeye

Usage
-----

.. code-block:: bash

    # List all devices
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD devices

    # List all available product types
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD products

    # Get device state
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD get --device-id YOUR_DEVICE_ID

    # Set device state
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD set --device-id YOUR_DEVICE_ID --power on --target-humidity 50

    # Set device mode and fan speed
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD set --device-id YOUR_DEVICE_ID --mode Auto --fan-speed High

    # Set additional device features
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD set --device-id YOUR_DEVICE_ID --anion on --oscillating on --child-lock off

    # Monitor device state changes in real-time
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD monitor --device-id YOUR_DEVICE_ID

    # Get MQTT information for Classic platform
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD classic-mqtt

    # Get MQTT information for Fog platform
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD fog-mqtt

    # Enable debug logging
    deye-cli --debug --username YOUR_USERNAME --password YOUR_PASSWORD devices

    # Print authentication token (useful for saving to .env file)
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD print-token

    # Force refresh the authentication token
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD refresh-token

Using .env File
---------------

You can store your credentials in a .env file to avoid typing them in each command:

.. code-block:: bash

    # Create a .env file in your working directory
    echo "DEYE_USERNAME=your_username" > .env
    echo "DEYE_PASSWORD=your_password" >> .env

    # Now you can run commands without specifying credentials
    deye-cli devices

    # You can also specify a different .env file location
    deye-cli --env-file /path/to/your/.env devices

The .env file format is simple:

.. code-block:: text

    DEYE_USERNAME=your_phone_number_or_username
    DEYE_PASSWORD=your_password
    # Optional: store auth token to avoid login each time
    DEYE_AUTH_TOKEN=your_auth_token
    # Optional: store device and product IDs for quick access
    DEYE_DEVICE_ID=your_device_id

With device and product IDs in your .env file, you can simplify commands:

.. code-block:: bash

    # Get device state without specifying device-id
    deye-cli get

    # Set device state without specifying device-id
    deye-cli set --power on --target-humidity 50

    # Monitor device state changes
    deye-cli monitor

Getting and Using Authentication Tokens
---------------------------------------

To avoid sending your username and password with each request, you can use an authentication token:

.. code-block:: bash

    # Get your authentication token
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD print-token

    # Copy the token and add it to your .env file
    echo "DEYE_AUTH_TOKEN=your_token_here" >> .env

    # Now you can use the token instead of username/password
    deye-cli devices

    # If your token expires, you can refresh it
    deye-cli --token YOUR_TOKEN refresh-token

Available Commands
------------------

- ``devices``: List all devices connected to your account
- ``products``: List all available product types
- ``get``: Get the current state of a device
- ``set``: Set the state of a device (power, mode, fan speed, etc.)
- ``monitor``: Monitor device state changes in real-time
- ``print-token``: Print the authentication token for use in .env file
- ``refresh-token``: Force refresh the authentication token
- ``classic-mqtt``: Get MQTT information for Classic platform
- ``fog-mqtt``: Get MQTT information for Fog platform

For more options, run:

.. code-block:: bash

    deye-cli --help

-------------
Example Usage
-------------

.. code-block:: python

    import asyncio
    from typing import List, Optional, Union

    import aiohttp
    from libdeye.cloud_api import DeyeApiResponseDeviceInfo, DeyeCloudApi, DeyeIotPlatform
    from libdeye.device_state import DeyeDeviceState
    from libdeye.mqtt_client import DeyeClassicMqttClient, DeyeFogMqttClient


    async def main() -> None:
        async with aiohttp.ClientSession() as client:
            # You can authenticate with username/password
            cloud_api = DeyeCloudApi(client, "18976602834", "jsq2627_tf2")
            await cloud_api.authenticate()

            # Get the list of devices
            devices: List[DeyeApiResponseDeviceInfo] = await cloud_api.get_device_list()
            if not devices:
                print("No devices found")
                return

            # Get the first device
            device = devices[0]
            product_id: str = device["product_id"]
            device_id: str = device["device_id"]
            platform: DeyeIotPlatform = DeyeIotPlatform(device["platform"])

            print(f"Device: {device['device_name']} (ID: {device_id})")
            print(
                f"Platform: {'Classic' if platform == DeyeIotPlatform.Classic else 'Fog'}"
            )

            mqtt_client: Optional[Union[DeyeClassicMqttClient, DeyeFogMqttClient]] = None

            # Handle device based on platform
            if platform == DeyeIotPlatform.Classic:
                # Create MQTT client for Classic platform
                mqtt_client = DeyeClassicMqttClient(cloud_api)
                await mqtt_client.connect()
            elif platform == DeyeIotPlatform.Fog:
                # Create MQTT client for Fog platform
                mqtt_client = DeyeFogMqttClient(cloud_api)
                await mqtt_client.connect()

            assert mqtt_client is not None

            # Query current state
            state: DeyeDeviceState = await mqtt_client.query_device_state(
                product_id, device_id
            )
            print(
                f"Current humidity: {state.environment_humidity}% (Target: {state.target_humidity}%)"
            )

            # Subscribe to state changes
            def on_state_update(state: DeyeDeviceState) -> None:
                print(
                    f"Device state updated. Current humidity: {state.environment_humidity}% (Target: {state.target_humidity}%)"
                )

            # Subscribe to availability changes
            def on_availability_change(available: bool) -> None:
                print(
                    f"Device availability changed: {'Online' if available else 'Offline'}"
                )

            # Set up subscriptions
            unsubscribe_state = mqtt_client.subscribe_state_change(
                product_id, device_id, on_state_update
            )
            unsubscribe_availability = mqtt_client.subscribe_availability_change(
                product_id, device_id, on_availability_change
            )

            # Set target humidity
            state.target_humidity = 40
            await mqtt_client.publish_command(product_id, device_id, state.to_command())

            await asyncio.sleep(30)

            # Unsubscribe from state changes
            unsubscribe_state()
            unsubscribe_availability()
            mqtt_client.disconnect()


    # Run the example
    if __name__ == "__main__":
        asyncio.run(main())
