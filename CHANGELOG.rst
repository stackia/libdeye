=========
Changelog
=========

Version 2.1.1
=============

- Expand paho-mqtt version constraint to allow minor version updates up to version 3.

Version 2.1.0
=============

- Add equality comparison support for device command and state classes.
- Enable direct comparison of DeyeDeviceCommand and DeyeDeviceState attributes.
- Include comprehensive test cases for equality operations.

Version 2.0.1
=============

- Fix MQTT authentication by refreshing auth info on client disconnects to avoid expired password issues.
- Add additional tests for MQTT client implementation.
- Improve documentation and README.

Version 2.0.0
=============

- Add full support for both Classic and Fog platforms.
- The public interface has been redesigned to better support both platforms.
- Add product definition for 8158C.
- Add a new command-line tool for testing and interacting with Deye devices.

Version 1.3.2
=============

- Add support for passing an existing SSLContext to the MQTT client

Version 1.3.1
=============

- Add support for DYD-U20Air

Version 1.3.0
=============

- Add fog api control support for new machine like DYD-T22A3, DYD-D50A3

Version 1.2.0
=============

- Add support for DYD-890T / DYD-8138T

Version 1.1.3
=============

- Retry login if the auth token is expired

Version 1.1.2
=============

- Fix incorrect type hint for device payload


Version 1.1.1
=============

- Allow unknown device mode to be parsed without exceptions


Version 1.1.0
=============

- Add support for device availability subscription.


Version 1.0.2
=============

- Update some type hints.


Version 1.0.1
=============

- Tweak build configs. No feature changes.


Version 1.0.0
=============

- Initial release
