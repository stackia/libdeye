=========
Changelog
=========

Unreleased
==========

Version 3.0.3
=============

- Construct the paho-mqtt client with ``CallbackAPIVersion.VERSION2`` and
  update ``on_connect`` / ``on_disconnect`` to the v2 callback signatures.
- Fog GET / device-list payloads may omit ``Demisting``, ``WaterTank``, and
  ``Fan``. Parse them like other optional Fog flags and default to off
  instead of raising ``KeyError``.
- Re-read official dehumidifier JSON for ``anion``, ``swingWind``,
  ``waterPump``, ``uvLight``, ``tone``, ``displayScreen``, and
  ``hasDelayer``. Identify ES25A3 / P30 / B13A3 / RT12 from plaintext
  ``DeviceListBean.isFanDevice`` product IDs. ``be8f5e6a…`` is ES25A3
  (anion and air-purifier mode), not P30.

Version 3.0.2
=============

- Align Fog JSON with official ``FogDeviceManager.sendCommand``: every
  ``PropertyParam`` key is omitted when null, including child lock,
  anion, UV, prompt sound, and screen display. Unset command/state
  fields stay ``None``. Classic ``to_bytes`` still treats unset switches
  as off.
- ``DeyeDeviceCommand.to_json_diff`` uses ``.get`` so a newly set key
  still diffs when the baseline omitted it.
- ``ProtocolVersion == 0`` companions copy cached non-null Integers and
  do not invent defaults for keys the GET never reported.

Version 3.0.1
=============

- Align Fog ``ProtocolVersion == 0`` payloads with official
  ``FogDeviceManager`` per-command companion keys instead of posting a
  union of every cached property (for example power commands no longer
  include ``SwingingWind``).
- Add ``?random=`` cache-busting query params on Fog GET properties and
  Fog MQTT-info, matching official Retrofit / ``DeYeHttpRequestManager``.
- ``DeyeDevice.request_refresh()`` on Fog POSTs official ``RealData: 1``
  (``setPollFogProperties``) instead of blocking on GET.
- Expose Fog extras on ``DeyeDeviceCommand`` / ``DeyeDeviceState``: UV,
  prompt sound, screen display, and timed-off hour. Sleep is
  ``DeyeDeviceMode.SLEEP_MODE``; target temperature is not supported.
- Map official dehumidifier JSON ``uvLight``, ``tone``, ``displayScreen``,
  and ``hasDelayer`` onto product config ``uv``, ``prompt_sound``,
  ``screen_display``, and ``timed_off``.

Version 3.0.0
=============

- **Breaking:** Follow official Deye Smart 4.2.1 CommandManger routing from
  each device-list entry. ``platform`` 2 and 3 both use Fog HTTP. Combo MQTT
  frames ``{2, 17, cmd, value}`` are used only when the device is not Fog and
  ``is_combo`` is set with ``protocol_version == combo_V1.0``.
- **Breaking:** Add ``DeyeClient`` / ``DeyeDevice`` so callers do not choose
  Classic, Fog, or Combo. ``mqtt_client_for_platform`` is removed.
- **Breaking:** Drop product-specific Fog send workarounds. Fog still sends
  a full property snapshot when cached ``ProtocolVersion == 0``.
- **Breaking:** Rename ``DeyeDeviceMode.UNKNOWN_MODE`` to ``TURBO_MODE`` and
  ``DeyeFanSpeed.UNKNOWN_SPEED`` to ``AUTO``. Add U20Pro modes 7–9.
- Product capabilities now match official ``control_panel/dehumidifier`` JSON
  (P40 includes Strong/turbo mode 4; unknown products no longer advertise
  oscillating or water pump).
- Require Python 3.14.2+ and align aiohttp, PyJWT, and paho-mqtt with Home Assistant 2026.3.0.
- Migrate development tooling to uv.
- Add ``reverse-engineering/`` with a single README, Yingyongbao download
  script, decompile script (JSON + Unidbg DEX dump + JADX), and the recovered
  business sources. The APK, DEX, and qh blobs are not stored in git.

Version 2.1.3
=============

- Add support for Y16A3/SC60Y.

Version 2.1.2
=============

- Fix MQTT client disconnect callback signature.

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
