.. image:: https://img.shields.io/badge/-PyScaffold-005CA0?logo=pyscaffold
    :alt: Project generated with PyScaffold
    :target: https://pyscaffold.org/
.. image:: https://results.pre-commit.ci/badge/github/stackia/libdeye/main.svg
    :target: https://results.pre-commit.ci/latest/github/stackia/libdeye/main
    :alt: pre-commit.ci status
.. image:: https://img.shields.io/pypi/v/libdeye.svg
    :alt: PyPI-Server
    :target: https://pypi.org/project/libdeye/
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
* DYD-RLS48A3
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

This library includes a command-line tool for testing and interacting with Deye devices during development.

Installation
-----------

The CLI tool is automatically installed when you install the library:

.. code-block:: bash

    pip install libdeye

Usage
-----

.. code-block:: bash

    # List all devices
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD devices

    # Get device state
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD get --device-id YOUR_DEVICE_ID --product-id YOUR_PRODUCT_ID

    # Set device state
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD set --device-id YOUR_DEVICE_ID --product-id YOUR_PRODUCT_ID --power on --target-humidity 50

    # Monitor device state changes for 120 seconds
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD monitor --device-id YOUR_DEVICE_ID --product-id YOUR_PRODUCT_ID --duration 120

    # Enable debug logging
    deye-cli --debug --username YOUR_USERNAME --password YOUR_PASSWORD devices

    # Print authentication token (useful for saving to .env file)
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD print-token

Using .env File
--------------

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
    DEYE_PRODUCT_ID=your_product_id

With device and product IDs in your .env file, you can simplify commands:

.. code-block:: bash

    # Get device state without specifying device-id and product-id
    deye-cli get

    # Set device state without specifying device-id and product-id
    deye-cli set --power on --target-humidity 50

    # Monitor device state changes
    deye-cli monitor --duration 120

Getting and Using Authentication Tokens
--------------------------------------

To avoid sending your username and password with each request, you can use an authentication token:

.. code-block:: bash

    # Get your authentication token
    deye-cli --username YOUR_USERNAME --password YOUR_PASSWORD print-token

    # Copy the token and add it to your .env file
    echo "DEYE_AUTH_TOKEN=your_token_here" >> .env

    # Now you can use the token instead of username/password
    deye-cli devices

For more options, run:

.. code-block:: bash

    deye-cli --help

-------------
Example Usage
-------------

.. code-block:: python

    import asyncio

    import aiohttp

    from libdeye.cloud_api import DeyeCloudApi
    from libdeye.device_state_command import DeyeDeviceState
    from libdeye.mqtt_client import DeyeMqttClient


    async def main():
        async with aiohttp.ClientSession() as client:
            cloud_api = DeyeCloudApi(
                client, "your-login-phone-number-here", "your-password-here"
            )
            await cloud_api.authenticate()

            devices = await cloud_api.get_device_list()
            mqtt_info = await cloud_api.get_mqtt_info()

            mqtt = DeyeMqttClient(
                mqtt_info["mqtthost"],
                mqtt_info["sslport"],
                mqtt_info["loginname"],
                mqtt_info["password"],
                mqtt_info["endpoint"],
            )
            mqtt.connect()

            product_id = devices[0]["product_id"]
            device_id = devices[0]["device_id"]
            platform = devices[0]["platform"]

            if platform == 1:
                def on_deye_device_state_update(state: DeyeDeviceState) -> None:
                    print(
                        f"Device state updated. Current humidity: {state.environment_humidity}"
                    )
                    if state.environment_humidity < 60:
                        state.power_switch = False  # Turn off the power switch
                        mqtt.publish_command(product_id, device_id, state.to_command().bytes())

                mqtt.subscribe_state_change(
                    product_id,
                    device_id,
                    on_deye_device_state_update,
                )

            elif platform == 2:
               state = DeyeDeviceState(await cloud_api.get_fog_platform_device_properties(device_id))
               if state.environment_humidity < 60:
                   state.power_switch = False   # Turn off the power switch
                   await cloud_api.set_fog_platform_device_properties(device_id, state.to_command().json())


    loop = asyncio.get_event_loop()
    loop.create_task(main())
    loop.run_forever()
