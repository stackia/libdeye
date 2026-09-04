"""MQTT related stuffs."""

from abc import ABC, abstractmethod
import asyncio
from asyncio import Future, get_running_loop
from collections.abc import Callable
import concurrent.futures
import json
import logging
from ssl import SSLContext
from typing import Any, cast, override

import paho.mqtt.client as mqtt

from .cloud_api import (
    DeyeApiResponseDeviceInfo,
    DeyeApiResponseFogPlatformDeviceProperties,
    DeyeCloudApi,
    DeyeDeviceTransport,
    transport_for_device,
)
from .const import QUERY_DEVICE_STATE_COMMAND_CLASSIC
from .device_command import DeyeDeviceCommand, fog_combo_frames_from_properties
from .device_state import DeyeDeviceState

_LOGGER = logging.getLogger(__name__)

# Upper bound for re-fetching MQTT credentials after an unexpected disconnect.
# The cloud is often unreachable at that moment (the disconnect and the cloud
# outage usually share a cause), so this must not wait for aiohttp's much
# longer default total timeout.
MQTT_INFO_REFRESH_TIMEOUT = 30

FogCommandBaseline = DeyeDeviceCommand | DeyeDeviceState

# FogDeviceManager.sendCommand JSON keys, plus GET→SET aliases.
# TimedOffHour is sent; GET reports TimedShutdownHourSetting.
FOG_SETTABLE_PROPERTY_ALIASES: tuple[tuple[str, str], ...] = (
    ("KeyLock", "KeyLock"),
    ("Mode", "Mode"),
    ("Power", "Power"),
    ("UV", "UV"),
    ("WindSpeed", "WindSpeed"),
    ("SetHumidity", "SetHumidity"),
    ("NegativeIon", "NegativeIon"),
    ("SwingingWind", "SwingingWind"),
    ("WaterPump", "WaterPump"),
    ("Sleep", "Sleep"),
    ("SetTemperature", "SetTemperature"),
    ("PromptSound", "PromptSound"),
    ("Screendisplay", "Screendisplay"),
    ("TimedShutdownHourSetting", "TimedOffHour"),
    ("TimedOffHour", "TimedOffHour"),
)

FOG_SETTABLE_PROPERTY_ORDER: tuple[str, ...] = (
    "KeyLock",
    "Mode",
    "Power",
    "UV",
    "WindSpeed",
    "SetHumidity",
    "NegativeIon",
    "SwingingWind",
    "WaterPump",
    "Sleep",
    "SetTemperature",
    "PromptSound",
    "Screendisplay",
    "TimedOffHour",
)

# Companion keys copied from the cached bean when ProtocolVersion == 0.
# Primary command key is applied separately. Matches FogDeviceManager
# send*Command checkNeedAll blocks, including keys those methods omit
# (for example sendPowerCommand does not send SwingingWind).
FOG_PROTOCOL_V0_COMPANIONS: dict[str, frozenset[str]] = {
    "Power": frozenset(
        {
            "KeyLock",
            "UV",
            "Mode",
            "SetHumidity",
            "WindSpeed",
            "NegativeIon",
            "WaterPump",
            "Sleep",
            "SetTemperature",
            "TimedOffHour",
        }
    ),
    "WindSpeed": frozenset(
        {
            "UV",
            "KeyLock",
            "Mode",
            "SetHumidity",
            "Power",
            "NegativeIon",
            "WaterPump",
            "SwingingWind",
            "Sleep",
            "SetTemperature",
            "TimedOffHour",
        }
    ),
    "Mode": frozenset(
        {
            "UV",
            "KeyLock",
            "Power",
            "SetHumidity",
            "WindSpeed",
            "NegativeIon",
            "WaterPump",
            "SwingingWind",
            "Sleep",
            "SetTemperature",
            "TimedOffHour",
        }
    ),
    "SetHumidity": frozenset(
        {
            "UV",
            "KeyLock",
            "Power",
            "Mode",
            "WindSpeed",
            "NegativeIon",
            "WaterPump",
            "SwingingWind",
            "TimedOffHour",
        }
    ),
    "SetTemperature": frozenset(
        {
            "KeyLock",
            "Power",
            "Mode",
            "WindSpeed",
            "NegativeIon",
            "WaterPump",
            "SwingingWind",
            "Sleep",
            "TimedOffHour",
        }
    ),
    "KeyLock": frozenset(
        {
            "UV",
            "SetHumidity",
            "Power",
            "Mode",
            "WindSpeed",
            "NegativeIon",
            "WaterPump",
            "SwingingWind",
            "Sleep",
            "SetTemperature",
            "TimedOffHour",
        }
    ),
    "NegativeIon": frozenset(
        {
            "UV",
            "SetHumidity",
            "Power",
            "Mode",
            "WindSpeed",
            "KeyLock",
            "WaterPump",
            "SwingingWind",
            "Sleep",
            "SetTemperature",
            "TimedOffHour",
        }
    ),
    "UV": frozenset(
        {
            "NegativeIon",
            "SetHumidity",
            "Power",
            "Mode",
            "WindSpeed",
            "KeyLock",
            "WaterPump",
            "SwingingWind",
            "Sleep",
            "SetTemperature",
            "TimedOffHour",
        }
    ),
    "SwingingWind": frozenset(
        {
            "UV",
            "SetHumidity",
            "Power",
            "Mode",
            "WindSpeed",
            "KeyLock",
            "WaterPump",
            "NegativeIon",
            "Sleep",
            "SetTemperature",
            "TimedOffHour",
        }
    ),
    "WaterPump": frozenset(
        {
            "UV",
            "SetHumidity",
            "Power",
            "Mode",
            "WindSpeed",
            "KeyLock",
            "SwingingWind",
            "NegativeIon",
            "Sleep",
            "SetTemperature",
            "TimedOffHour",
        }
    ),
    "Sleep": frozenset(
        {
            "SetHumidity",
            "Power",
            "Mode",
            "WindSpeed",
            "KeyLock",
            "SwingingWind",
            "NegativeIon",
            "WaterPump",
            "SetTemperature",
            "TimedOffHour",
        }
    ),
    "Screendisplay": frozenset(),
    "PromptSound": frozenset(),
    "TimedOffHour": frozenset(),
}


def _int_property(value: object) -> int | None:
    """Parse a Fog property value the way official toIntOrNull does."""
    if value is None or isinstance(value, bool):
        return None
    if isinstance(value, int):
        return value
    if isinstance(value, float) and value.is_integer():
        return int(value)
    if isinstance(value, str):
        stripped = value.strip()
        if stripped.lstrip("-").isdigit():
            return int(stripped)
    return None


def fog_full_snapshot_from_properties(properties: dict[str, Any]) -> dict[str, int]:
    """Copy cached Fog GET/MQTT params into official set/properties keys."""
    snapshot: dict[str, int] = {}
    for source_key, dest_key in FOG_SETTABLE_PROPERTY_ALIASES:
        parsed = _int_property(properties.get(source_key))
        if parsed is not None:
            snapshot[dest_key] = parsed
    return snapshot


def _fog_v0_payload_for_key(
    primary: str,
    value: int,
    command: DeyeDeviceCommand,
    last_properties: dict[str, Any],
) -> dict[str, int]:
    """Build one official ProtocolVersion 0 set/properties body."""
    companions = FOG_PROTOCOL_V0_COMPANIONS.get(primary)
    if companions is None:
        return {primary: value}

    values = fog_full_snapshot_from_properties(last_properties)
    # Overlay only keys the command actually serializes. Official
    # companions use toIntOrNull on the bean and sendCommand skips nulls.
    values.update(command.to_json())
    payload: dict[str, int] = {primary: value}
    for key in companions:
        if key in values:
            payload[key] = values[key]
    payload[primary] = value
    return payload


def _changed_fog_properties(
    command: DeyeDeviceCommand,
    properties: dict[str, int] | None,
    baseline: FogCommandBaseline | None,
) -> dict[str, int]:
    """Return the Fog keys that should be sent for this apply."""
    if baseline is not None:
        baseline_command = (
            baseline
            if isinstance(baseline, DeyeDeviceCommand)
            else baseline.to_command()
        )
        if command == baseline_command:
            return {}

    if properties is not None:
        return dict(properties)
    if baseline is not None:
        return command.to_json_diff(baseline)
    return command.to_json()


def resolve_fog_command_payloads(
    command: DeyeDeviceCommand,
    properties: dict[str, int] | None = None,
    baseline: FogCommandBaseline | None = None,
    protocol_version: int | None = None,
    last_properties: dict[str, Any] | None = None,
) -> list[dict[str, int]]:
    """Build Fog HTTP POST bodies matching FogDeviceManager.

    ``ProtocolVersion == 0`` with a cached bean uses each command's
    official companion set (not a union of every cached key). Missing
    cache is partial. Otherwise only the changed properties are sent.
    Multiple changed keys become one POST each, like successive App taps.
    """
    changed = _changed_fog_properties(command, properties, baseline)
    if not changed:
        return []

    if protocol_version != 0 or last_properties is None:
        return [changed]

    payloads: list[dict[str, int]] = []
    remaining = dict(changed)
    for key in FOG_SETTABLE_PROPERTY_ORDER:
        if key not in remaining:
            continue
        payloads.append(
            _fog_v0_payload_for_key(key, remaining.pop(key), command, last_properties)
        )
    for key, value in remaining.items():
        payloads.append(_fog_v0_payload_for_key(key, value, command, last_properties))
    return payloads


def resolve_fog_command_properties(
    command: DeyeDeviceCommand,
    properties: dict[str, int] | None = None,
    baseline: FogCommandBaseline | None = None,
    protocol_version: int | None = None,
    last_properties: dict[str, Any] | None = None,
) -> dict[str, int]:
    """Return the first Fog payload, or ``{}`` when nothing should be sent."""
    payloads = resolve_fog_command_payloads(
        command,
        properties=properties,
        baseline=baseline,
        protocol_version=protocol_version,
        last_properties=last_properties,
    )
    if not payloads:
        return {}
    return payloads[0]


def mqtt_client_type_for_device(
    device: DeyeApiResponseDeviceInfo,
) -> type[BaseDeyeMqttClient]:
    """Return the MQTT client class for a device-list entry."""
    transport = transport_for_device(device)
    if transport is DeyeDeviceTransport.FOG:
        return DeyeFogMqttClient
    if transport is DeyeDeviceTransport.COMBO:
        return DeyeFogComboMqttClient
    return DeyeClassicMqttClient


def mqtt_client_for_device(
    device: DeyeApiResponseDeviceInfo,
    cloud_api: DeyeCloudApi,
    tls_context: SSLContext | None = None,
) -> BaseDeyeMqttClient:
    """Return the MQTT client implementation for a device-list entry."""
    return mqtt_client_type_for_device(device)(cloud_api, tls_context)


class BaseDeyeMqttClient(ABC):
    """Base class for MQTT clients connected to Deye MQTT servers."""

    _mqtt_host: str
    _mqtt_ssl_port: int

    def __init__(
        self,
        cloud_api: DeyeCloudApi,
        tls_context: SSLContext | None = None,
    ) -> None:
        """Initialize the MQTT client."""
        self._loop = get_running_loop()
        self._cloud_api = cloud_api
        self._endpoint = ""
        self._topic = ""
        self._mqtt = mqtt.Client(mqtt.CallbackAPIVersion.VERSION2)
        if tls_context is not None:
            self._mqtt.tls_set_context(tls_context)
        else:
            self._mqtt.tls_set()
        self._mqtt.on_connect = self._mqtt_on_connect
        self._mqtt.on_message = self._mqtt_on_message
        self._mqtt.on_disconnect = self._mqtt_on_disconnect
        self._subscribers: dict[str, set[Callable[[Any], None]]] = {}
        self._pending_commands: list[tuple[str, bytes]] = []
        self._mqtt_info_refresh: concurrent.futures.Future[None] | None = None

    @abstractmethod
    async def _set_mqtt_info(self) -> None:
        """Get the MQTT info from the cloud API."""
        raise NotImplementedError

    async def connect(self) -> None:
        """Connect the MQTT client to the server."""
        await self._set_mqtt_info()
        self._mqtt.connect_async(self._mqtt_host, self._mqtt_ssl_port)
        self._mqtt.loop_start()

    def disconnect(self) -> None:
        """Disconnect the MQTT client to the server."""
        if self._mqtt_info_refresh is not None:
            self._mqtt_info_refresh.cancel()
            self._mqtt_info_refresh = None
        self._mqtt.disconnect()
        self._mqtt.loop_stop()

    def _mqtt_on_connect(
        self,
        _mqtt: mqtt.Client,
        _userdata: Any,
        _connect_flags: mqtt.ConnectFlags,
        _reason_code: mqtt.ReasonCode,
        _properties: mqtt.Properties | None,
    ) -> None:
        for topic, callbacks in self._subscribers.items():
            if len(callbacks) > 0:
                self._mqtt.subscribe(topic)
        if len(self._pending_commands) > 0:
            for topic, command in self._pending_commands:
                self._mqtt.publish(topic, command)
            self._pending_commands.clear()

    def _mqtt_on_disconnect(
        self,
        _mqtt: mqtt.Client,
        _userdata: Any,
        _disconnect_flags: mqtt.DisconnectFlags,
        reason_code: mqtt.ReasonCode,
        _properties: mqtt.Properties | None,
    ) -> None:
        if reason_code == 0:  # User initiated disconnect
            return

        # This callback runs on paho's network thread, which also performs the
        # automatic reconnect. It must neither block on the event loop (the
        # loop may be inside ``disconnect()`` joining this very thread) nor
        # raise: an exception escaping a paho callback terminates the network
        # thread, so the client would never reconnect while ``is_connected()``
        # keeps returning True. Refresh the credentials in the background;
        # paho reconnects with the previous ones meanwhile, and a refused
        # CONNACK simply brings us back here.
        if self._mqtt_info_refresh is not None and not self._mqtt_info_refresh.done():
            return
        refresh = self._refresh_mqtt_info_after_disconnect(reason_code)
        try:
            self._mqtt_info_refresh = asyncio.run_coroutine_threadsafe(
                refresh, self._loop
            )
        except RuntimeError:  # Event loop is closed
            refresh.close()

    async def _refresh_mqtt_info_after_disconnect(
        self, reason_code: mqtt.ReasonCode
    ) -> None:
        """Re-fetch MQTT credentials so paho's automatic reconnect can use them."""
        try:
            async with asyncio.timeout(MQTT_INFO_REFRESH_TIMEOUT):
                await self._set_mqtt_info()
        except Exception as err:  # noqa: BLE001
            _LOGGER.warning(
                "MQTT disconnected (%s) and refreshing the MQTT connection info "
                "failed (%s); reconnecting with the previous credentials",
                reason_code,
                str(err.__cause__ or err) or type(err).__name__,
            )

    @abstractmethod
    def _process_message_payload(self, msg: mqtt.MQTTMessage) -> Any:
        """Process the message payload."""
        raise NotImplementedError

    def _mqtt_on_message(
        self, _mqtt: mqtt.Client, _userdata: Any, msg: mqtt.MQTTMessage
    ) -> None:
        if msg.topic not in self._subscribers:
            return
        callbacks = self._subscribers[msg.topic]
        try:
            for callback in callbacks.copy():
                self._loop.call_soon_threadsafe(
                    callback, self._process_message_payload(msg)
                )
        except json.JSONDecodeError, KeyError:
            pass

    def _subscribe_topic(
        self,
        topic: str,
        callback: Callable[[Any], None],
    ) -> Callable[[], None]:
        if topic not in self._subscribers:
            self._subscribers[topic] = set()
        current_callback_len = len(self._subscribers[topic])
        self._subscribers[topic].add(callback)
        if self._mqtt.is_connected() and current_callback_len == 0:
            self._mqtt.subscribe(topic)

        def unsubscribe() -> None:
            self._subscribers[topic].remove(callback)
            if self._mqtt.is_connected() and len(self._subscribers[topic]) == 0:
                self._mqtt.unsubscribe(topic)

        return unsubscribe

    @abstractmethod
    def subscribe_state_change(
        self,
        product_id: str,
        device_id: str,
        callback: Callable[[DeyeDeviceState], None],
    ) -> Callable[[], None]:
        """Subscribe to state changes of specified device."""
        raise NotImplementedError

    @abstractmethod
    def subscribe_availability_change(
        self,
        product_id: str,
        device_id: str,
        callback: Callable[[bool], None],
    ) -> Callable[[], None]:
        """Subscribe to availability changes of specified device."""
        raise NotImplementedError

    @abstractmethod
    async def publish_command(
        self,
        product_id: str,
        device_id: str,
        command: DeyeDeviceCommand,
        properties: dict[str, int] | None = None,
        baseline: FogCommandBaseline | None = None,
    ) -> None:
        """Publish commands to a device."""
        raise NotImplementedError

    @abstractmethod
    async def query_device_state(
        self, product_id: str, device_id: str
    ) -> DeyeDeviceState:
        """Query the latest device state."""
        raise NotImplementedError


class DeyeClassicMqttClient(BaseDeyeMqttClient):
    """MQTT client for the Classic platform."""

    def _get_topic_prefix(self, product_id: str, device_id: str) -> str:
        return f"{self._endpoint}/{product_id}/{device_id}"

    @override
    async def _set_mqtt_info(self) -> None:
        mqtt_info = await self._cloud_api.get_deye_platform_mqtt_info()
        self._mqtt_host = mqtt_info["mqtthost"]
        self._mqtt_ssl_port = mqtt_info["sslport"]
        self._mqtt.username_pw_set(mqtt_info["loginname"], mqtt_info["password"])
        self._endpoint = mqtt_info["endpoint"]

    @override
    def _process_message_payload(self, msg: mqtt.MQTTMessage) -> Any:
        """Process the message payload for Classic platform."""
        return json.loads(msg.payload)["data"]

    @override
    def subscribe_state_change(
        self,
        product_id: str,
        device_id: str,
        callback: Callable[[DeyeDeviceState], None],
    ) -> Callable[[], None]:
        """Subscribe to state changes of specified device."""
        return self._subscribe_topic(
            f"{self._get_topic_prefix(product_id, device_id)}/status/hex",
            lambda payload: callback(DeyeDeviceState(payload)),
        )

    @override
    def subscribe_availability_change(
        self,
        product_id: str,
        device_id: str,
        callback: Callable[[bool], None],
    ) -> Callable[[], None]:
        """Subscribe to availability changes of specified device."""
        return self._subscribe_topic(
            f"{self._get_topic_prefix(product_id, device_id)}/online/json",
            lambda payload: callback(payload["online"]),
        )

    def _publish_command_bytes(
        self, product_id: str, device_id: str, command_bytes: bytes
    ) -> None:
        """Publish Classic MQTT command bytes, or queue them if disconnected."""
        topic = f"{self._get_topic_prefix(product_id, device_id)}/command/hex"
        if self._mqtt.is_connected():
            self._mqtt.publish(topic, command_bytes)
        else:
            self._pending_commands.append((topic, command_bytes))

    @override
    async def publish_command(
        self,
        product_id: str,
        device_id: str,
        command: DeyeDeviceCommand | bytes,
        properties: dict[str, int] | None = None,
        baseline: FogCommandBaseline | None = None,
    ) -> None:
        """Publish commands to a device."""
        command_bytes = (
            command.to_bytes() if isinstance(command, DeyeDeviceCommand) else command
        )
        self._publish_command_bytes(product_id, device_id, command_bytes)

    @override
    async def query_device_state(
        self, product_id: str, device_id: str
    ) -> DeyeDeviceState:
        """Query the latest device state."""
        future: Future[DeyeDeviceState] = Future()
        unsubscribe: Callable[[], None] | None = None

        def on_message(state: DeyeDeviceState) -> None:
            if not future.done():
                future.set_result(state)
                if unsubscribe is not None:
                    unsubscribe()

        unsubscribe = self.subscribe_state_change(product_id, device_id, on_message)
        await self.publish_command(
            product_id, device_id, QUERY_DEVICE_STATE_COMMAND_CLASSIC
        )

        return await future


class DeyeFogComboMqttClient(DeyeClassicMqttClient):
    """Classic MQTT transport with official Combo single-property frames.

    CommandManger: if isFog then Fog HTTP; else if isCombo then
    sendSingleCommand bytes {2, 17, cmd, value} on the Classic MQTT command
    topic. Receive and query stay on Classic status/hex and poll bytes 00 01.
    """

    @override
    async def publish_command(
        self,
        product_id: str,
        device_id: str,
        command: DeyeDeviceCommand | bytes,
        properties: dict[str, int] | None = None,
        baseline: FogCommandBaseline | None = None,
    ) -> None:
        """Publish FogCombo frames, or a raw Classic query payload."""
        if isinstance(command, bytes):
            self._publish_command_bytes(product_id, device_id, command)
            return

        if properties is not None:
            properties_to_publish = dict(properties)
        elif baseline is not None:
            properties_to_publish = command.to_json_diff(baseline)
        else:
            properties_to_publish = command.to_json()

        for frame in fog_combo_frames_from_properties(properties_to_publish):
            self._publish_command_bytes(product_id, device_id, frame)


class DeyeFogMqttClient(BaseDeyeMqttClient):
    """MQTT client for the Fog platform."""

    @override
    def __init__(
        self,
        cloud_api: DeyeCloudApi,
        tls_context: SSLContext | None = None,
    ) -> None:
        """Initialize the Fog MQTT client and ProtocolVersion cache."""
        super().__init__(cloud_api, tls_context)
        self._fog_protocol_versions: dict[str, int] = {}
        self._fog_last_properties: dict[str, dict[str, Any]] = {}

    def _remember_fog_protocol_version(
        self, device_id: str, properties: dict[str, Any]
    ) -> None:
        """Cache ProtocolVersion and settable params from Fog GET/MQTT."""
        self._fog_last_properties[device_id] = dict(properties)
        protocol_version = properties.get("ProtocolVersion")
        if protocol_version is not None:
            self._fog_protocol_versions[device_id] = int(protocol_version)

    @override
    async def _set_mqtt_info(self) -> None:
        mqtt_info = await self._cloud_api.get_fog_platform_mqtt_info()
        self._mqtt_host = mqtt_info["mqtt_host"]
        self._mqtt_ssl_port = int(mqtt_info["ssl_port"])
        self._mqtt.username_pw_set(mqtt_info["username"], mqtt_info["password"])
        self._topic = f"fogcloud/app/{mqtt_info['username']}/sub"

    @override
    def _process_message_payload(self, msg: mqtt.MQTTMessage) -> Any:
        """Process the message payload for Fog platform."""
        return json.loads(msg.payload)

    @override
    def subscribe_state_change(
        self,
        product_id: str,
        device_id: str,
        callback: Callable[[DeyeDeviceState], None],
    ) -> Callable[[], None]:
        """Subscribe to state changes of specified device."""

        def on_payload(payload: Any) -> None:
            if (
                payload.get("device_id") != device_id
                or payload.get("biz_code") != "device_data"
                or payload.get("data", {}).get("message_type") != "thing_property"
            ):
                return
            properties = payload["data"]["properties"]
            self._remember_fog_protocol_version(device_id, properties)
            callback(
                DeyeDeviceState(
                    cast(DeyeApiResponseFogPlatformDeviceProperties, properties)
                )
            )

        return self._subscribe_topic(self._topic, on_payload)

    @override
    def subscribe_availability_change(
        self,
        product_id: str,
        device_id: str,
        callback: Callable[[bool], None],
    ) -> Callable[[], None]:
        """Subscribe to availability changes of specified device."""
        return self._subscribe_topic(
            self._topic,
            lambda payload: (
                callback(payload["data"]["status"] == "online")
                if payload["device_id"] == device_id
                and payload["biz_code"] == "device_status"
                else None
            ),
        )

    @override
    async def publish_command(
        self,
        product_id: str,
        device_id: str,
        command: DeyeDeviceCommand,
        properties: dict[str, int] | None = None,
        baseline: FogCommandBaseline | None = None,
    ) -> None:
        """Publish commands to a Fog-platform device via the cloud API."""
        payloads = resolve_fog_command_payloads(
            command,
            properties=properties,
            baseline=baseline,
            protocol_version=self._fog_protocol_versions.get(device_id),
            last_properties=self._fog_last_properties.get(device_id),
        )
        for payload in payloads:
            await self._cloud_api.set_fog_platform_device_properties(device_id, payload)

    @override
    async def query_device_state(
        self, product_id: str, device_id: str
    ) -> DeyeDeviceState:
        """Query the latest device state."""
        device_properties = await self._cloud_api.get_fog_platform_device_properties(
            device_id
        )
        self._remember_fog_protocol_version(
            device_id, cast(dict[str, Any], device_properties)
        )
        return DeyeDeviceState(device_properties)
