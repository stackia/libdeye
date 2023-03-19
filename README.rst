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

* DYD-612S
* DYD-6158EB/6160A
* DYD-B12A3
* DYD-D50A3
* DYD-D50B3
* DYD-E12A3
* DYD-G25A3
* DYD-N20A3
* DYD-RLS48A3
* DYD-T22A3
* DYD-TM208
* DYD-U20A3
* DYD-V58A3
* DYD-W20A3
* DYD-W20A3-JD
* DYD-X20A3
* DYD-Z12A3
* DYD-Z20B3
* DYD-Z20B3-QMX
* DYD-890C
* DYD-6138A
* DYD-8138C
* DYD-S12A3
* DYD-620S
* DYD-F20C3
* DYD-JD121EC
* DYD-JD201FC

For devices not in the above list, consider `adding your own definitions here <https://github.com/stackia/libdeye/blob/master/src/libdeye/const.py#L63>`_.

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


    loop = asyncio.get_event_loop()
    loop.create_task(main())
    loop.run_forever()
