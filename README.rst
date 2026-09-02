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
* DY-Y16A3
* DY-SC60Y
* DYD-P40
* A10
* DYD-P30
* DY-C65DZ/A

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

    # or
    uv add libdeye

Usage
-----

.. code-block:: bash

    # List devices (prints platform and CLASSIC / FOG / COMBO transport)
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD devices

    # List all available product types
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD products

    # Get device state (works for Classic, Fog, and Combo)
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD get --device-id YOUR_DEVICE_ID

    # Set device state
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD set --device-id YOUR_DEVICE_ID --power on --target-humidity 50

    # Set device mode and fan speed (enum names, not display labels)
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD set --device-id YOUR_DEVICE_ID --mode AUTO_MODE --fan-speed HIGH

    # Set additional device features
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD set --device-id YOUR_DEVICE_ID --anion on --oscillating on --water-pump off --child-lock off

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

Using Environment Variables and .env Files
------------------------------------------

Credentials can be provided via command-line arguments, process environment
variables, or a ``.env`` file. Precedence (highest to lowest):

1. Command-line arguments (``--username``, ``--password``, ``--token``, ``--device-id``)
2. Process environment variables (``DEYE_USERNAME``, ``DEYE_PASSWORD``, ...)
3. Values from a ``.env`` file

This matches the default behavior of python-dotenv and 12-factor app conventions:
``.env`` is a local default, and already-set environment variables are not overwritten.

You can export variables in your shell:

.. code-block:: bash

    export DEYE_USERNAME=your_username
    export DEYE_PASSWORD=your_password
    deye-cli devices

Or store them in a .env file to avoid typing them in each command:

.. code-block:: bash

    # Create a .env file in your working directory
    echo "DEYE_USERNAME=your_username" > .env
    echo "DEYE_PASSWORD=your_password" >> .env

    # Now you can run commands without specifying credentials
    deye-cli devices

    # You can also specify a different .env file location
    deye-cli --env-file /path/to/your/.env devices

The supported variable names (used by both the environment and .env files) are:

.. code-block:: text

    DEYE_USERNAME=your_phone_number_or_username
    DEYE_PASSWORD=your_password
    # Optional: store auth token to avoid login each time
    DEYE_AUTH_TOKEN=your_auth_token
    # Optional: default device for get / set / monitor
    DEYE_DEVICE_ID=your_device_id

With ``DEYE_DEVICE_ID`` configured, you can omit ``--device-id``:

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

- ``devices``: List devices on the account, including IoT platform and command transport (``CLASSIC``, ``FOG``, or ``COMBO``)
- ``products``: List all available product types
- ``get``: Query current state via ``DeyeClient`` (Classic MQTT poll, Fog HTTP GET, or Combo MQTT poll)
- ``set``: Send a command via ``DeyeClient.apply`` (power, mode, fan speed, humidity, anion, water pump, oscillating, child lock, plus optional Fog sleep / UV / temperature / prompt sound / screen display / timed-off hour)
- ``monitor``: Subscribe to MQTT state and availability updates
- ``print-token``: Print the authentication token for use in .env file
- ``refresh-token``: Force refresh the authentication token
- ``classic-mqtt``: Get MQTT broker credentials for Classic / Combo devices
- ``fog-mqtt``: Get MQTT broker credentials for Fog devices (inbound state only; Fog commands use HTTP)

``set --mode`` and ``set --fan-speed`` take ``DeyeDeviceMode`` / ``DeyeFanSpeed``
enum names, for example ``MANUAL_MODE``, ``CLOTHES_DRYER_MODE``, ``AUTO_MODE``,
``LOW``, ``HIGH``. Humidity range depends on the product (often 25-80 or 26-90).

For more options, run:

.. code-block:: bash

    deye-cli --help

----------
Public API
----------

Callers use ``DeyeClient`` and ``DeyeDevice``. The library selects Classic
MQTT, Fog HTTP, or Combo MQTT from each device-list entry. Do not construct
platform MQTT clients yourself.

Call ``refresh()`` or ``ensure_connected()`` before ``subscribe()``.
Send commands with ``device.apply(command, baseline=...)``. Fog devices
with cached ``ProtocolVersion == 0`` send the official companion snapshot
for each changed property (not a union of every cached key); otherwise
only changed fields are posted.

``DeyeDeviceCommand`` also carries Fog extras that official dehumidifier
product JSON optionally defines: ``uv_switch``, ``prompt_sound``,
``screen_display``, and ``timed_off_hour``.

The product JSON (``uvLight``, ``tone``, ``displayScreen``,
``hasDelayer``) only shows or hides those controls, the same way it
gates anion and oscillating. Use ``get_product_feature_config`` (``uv``,
``prompt_sound``, ``screen_display``, ``timed_off``) to decide whether a
product advertises them. If it does, callers should expose the matching
command/state fields.

The Fog send path does not invent values. ``FogDeviceManager.sendCommand``
posts a key only when the cached bean or the user action set it.
``ProtocolVersion == 0`` companions copy ``UV`` and ``TimedOffHour`` from
cache when present; display, tone, and timer commands stay single-key.
Unset library fields stay ``None`` and are omitted from Fog JSON. Sleep is
``DeyeDeviceMode.SLEEP_MODE``.

.. code-block:: python

    import asyncio

    import aiohttp
    from libdeye import DeyeClient


    async def main() -> None:
        async with aiohttp.ClientSession() as session:
            client = DeyeClient.from_credentials(
                session, "<phone_number>", "<password>"
            )
            await client.authenticate()

            devices = await client.list_devices()
            if not devices:
                print("No devices found")
                return

            device = devices[0]
            print(f"Device: {device.name} (ID: {device.device_id})")
            print(f"Transport: {device.transport.name}")

            state = await device.refresh()
            print(
                f"Current humidity: {state.environment_humidity}% "
                f"(Target: {state.target_humidity}%)"
            )

            def on_state_update(state) -> None:
                print(
                    f"Device state updated. Current humidity: "
                    f"{state.environment_humidity}%"
                )

            unsubscribe = device.subscribe(on_state=on_state_update)

            command = state.to_command()
            command.target_humidity = 40
            await device.apply(command, baseline=state)

            await asyncio.sleep(30)
            unsubscribe()
            client.disconnect()


    if __name__ == "__main__":
        asyncio.run(main())
