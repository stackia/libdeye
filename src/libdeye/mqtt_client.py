"""MQTT related stuffs"""

import asyncio
import json
from abc import ABC, abstractmethod
from asyncio import Future, get_running_loop
from collections.abc import Callable
from ssl import SSLContext
from typing import Any, cast

import paho.mqtt.client as mqtt

from .cloud_api import (
    DeyeApiResponseFogPlatformDeviceProperties,
    DeyeCloudApi,
    DeyeIotPlatform,
    iot_platform_uses_fog_client,
)
from .const import (
    QUERY_DEVICE_STATE_COMMAND_CLASSIC,
    DeyeDeviceMode,
    get_product_feature_config,
)
from .device_command import DeyeDeviceCommand, fog_combo_frames_from_properties
from .device_state import DeyeDeviceState

FogCommandBaseline = DeyeDeviceCommand | DeyeDeviceState


def resolve_fog_command_properties(
    product_id: str,
    command: DeyeDeviceCommand,
    properties: dict[str, int] | None = None,
    baseline: FogCommandBaseline | None = None,
    protocol_version: int | None = None,
) -> dict[str, int]:
    """Build the Fog HTTP property payload for a product.

    Official FogDeviceManager.checkNeedAll sends every current param when
    cached ``ProtocolVersion == 0``; otherwise only the changed property.
    Product flags for 612S / D50A3 (full state) and U20A3 / V58A3 / U20Air
    stay as reported-product workarounds.
    """
    feature_config = get_product_feature_config(product_id)

    if baseline is not None:
        baseline_command = (
            baseline
            if isinstance(baseline, DeyeDeviceCommand)
            else baseline.to_command()
        )
        if command == baseline_command:
            return {}

    if protocol_version == 0 or feature_config["full_state_fog_commands"]:
        return command.to_json()

    if properties is not None:
        properties_to_publish = dict(properties)
    elif baseline is not None:
        properties_to_publish = command.to_json_diff(baseline)
    else:
        properties_to_publish = command.to_json()

    powering_on = properties_to_publish.get("Power") == 1
    if powering_on and feature_config["requires_mode_in_fog_power_on_updates"]:
        properties_to_publish["Mode"] = int(command.mode)

    if (
        (properties is not None or baseline is not None)
        and properties_to_publish
        and feature_config["requires_power_in_fog_partial_updates"]
        and "Power" not in properties_to_publish
        and command.power_switch
    ):
        properties_to_publish["Power"] = 1

    if (
        feature_config["omit_set_humidity_in_fog_auto_updates"]
        and command.mode == DeyeDeviceMode.AUTO_MODE
    ):
        properties_to_publish.pop("SetHumidity", None)

    return properties_to_publish


def mqtt_client_type_for_platform(
    platform: int | DeyeIotPlatform,
) -> type["BaseDeyeMqttClient"]:
    """Return the MQTT client class for an IoT platform value."""
    if int(platform) == DeyeIotPlatform.FogCombo:
        return DeyeFogComboMqttClient
    if iot_platform_uses_fog_client(platform):
        return DeyeFogMqttClient
    return DeyeClassicMqttClient


def mqtt_client_for_platform(
    platform: int | DeyeIotPlatform,
    cloud_api: DeyeCloudApi,
    tls_context: SSLContext | None = None,
) -> "BaseDeyeMqttClient":
    """Return the MQTT client implementation for an IoT platform value."""
    return mqtt_client_type_for_platform(platform)(cloud_api, tls_context)


class BaseDeyeMqttClient(ABC):
    """Base class for MQTT clients connected to Deye MQTT servers."""

    _mqtt_host: str
    _mqtt_ssl_port: int

    def __init__(
        self,
        cloud_api: DeyeCloudApi,
        tls_context: SSLContext | None = None,
    ) -> None:
        self._loop = get_running_loop()
        self._cloud_api = cloud_api
        self._mqtt = mqtt.Client()
        if tls_context is not None:
            self._mqtt.tls_set_context(tls_context)
        else:
            self._mqtt.tls_set()
        self._mqtt.on_connect = self._mqtt_on_connect
        self._mqtt.on_message = self._mqtt_on_message
        self._mqtt.on_disconnect = self._mqtt_on_disconnect
        self._subscribers: dict[str, set[Callable[[Any], None]]] = {}
        self._pending_commands: list[tuple[str, bytes]] = []

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
        self._mqtt.disconnect()
        self._mqtt.loop_stop()

    def _mqtt_on_connect(
        self,
        *args: Any,
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
        _userdata: None,
        result_code: int,
    ) -> None:
        if result_code == 0:  # User initiated disconnect
            return

        # Update MQTT info and wait for it to complete before reconnecting
        # (reconnect is automatically handled by paho-mqtt by default)
        asyncio.run_coroutine_threadsafe(self._set_mqtt_info(), self._loop).result()

    @abstractmethod
    def _process_message_payload(self, msg: mqtt.MQTTMessage) -> Any:
        """Process the message payload."""
        raise NotImplementedError

    def _mqtt_on_message(
        self, _mqtt: mqtt.Client, _userdata: None, msg: mqtt.MQTTMessage
    ) -> None:
        if msg.topic not in self._subscribers:
            return
        callbacks = self._subscribers[msg.topic]
        try:
            for callback in callbacks.copy():
                self._loop.call_soon_threadsafe(
                    callback, self._process_message_payload(msg)
                )
        except (json.JSONDecodeError, KeyError):
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
        """Publish commands to a device"""
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

    async def _set_mqtt_info(self) -> None:
        mqtt_info = await self._cloud_api.get_deye_platform_mqtt_info()
        self._mqtt_host = mqtt_info["mqtthost"]
        self._mqtt_ssl_port = mqtt_info["sslport"]
        self._mqtt.username_pw_set(mqtt_info["loginname"], mqtt_info["password"])
        self._endpoint = mqtt_info["endpoint"]

    def _process_message_payload(self, msg: mqtt.MQTTMessage) -> Any:
        """Process the message payload for Classic platform."""
        return json.loads(msg.payload)["data"]

    def subscribe_state_change(
        self,
        product_id: str,
        device_id: str,
        callback: Callable[[DeyeDeviceState], None],
    ) -> Callable[[], None]:
        """Subscribe to state changes of specified device."""
        return self._subscribe_topic(
            f"{self._get_topic_prefix(product_id, device_id)}/status/hex",
            lambda payload: callback(DeyeDeviceState(payload, product_id=product_id)),
        )

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
        topic = f"{self._get_topic_prefix(product_id, device_id)}/command/hex"
        if self._mqtt.is_connected():
            self._mqtt.publish(topic, command_bytes)
        else:
            self._pending_commands.append((topic, command_bytes))

    async def publish_command(
        self,
        product_id: str,
        device_id: str,
        command: DeyeDeviceCommand | bytes,
        properties: dict[str, int] | None = None,
        baseline: FogCommandBaseline | None = None,
    ) -> None:
        """Publish commands to a device"""
        command_bytes = (
            command.to_bytes() if isinstance(command, DeyeDeviceCommand) else command
        )
        self._publish_command_bytes(product_id, device_id, command_bytes)

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
    """Classic MQTT transport with official FogCombo single-property frames.

    CommandManger: if isFog → Fog HTTP; else if isCombo → sendSingleCommand
    bytes ``{2, 17, cmd, value}`` on the Classic MQTT command topic.
    Receive/query stay on Classic ``status/hex`` and ``\\x00\\x01``.
    """

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

    def __init__(
        self,
        cloud_api: DeyeCloudApi,
        tls_context: SSLContext | None = None,
    ) -> None:
        super().__init__(cloud_api, tls_context)
        self._fog_protocol_versions: dict[str, int] = {}

    def _remember_fog_protocol_version(
        self, device_id: str, properties: dict[str, Any]
    ) -> None:
        protocol_version = properties.get("ProtocolVersion")
        if protocol_version is not None:
            self._fog_protocol_versions[device_id] = int(protocol_version)

    async def _set_mqtt_info(self) -> None:
        mqtt_info = await self._cloud_api.get_fog_platform_mqtt_info()
        self._mqtt_host = mqtt_info["mqtt_host"]
        self._mqtt_ssl_port = int(mqtt_info["ssl_port"])
        self._mqtt.username_pw_set(mqtt_info["username"], mqtt_info["password"])
        self._topic = f"fogcloud/app/{mqtt_info['username']}/sub"

    def _process_message_payload(self, msg: mqtt.MQTTMessage) -> Any:
        """Process the message payload for Fog platform."""
        return json.loads(msg.payload)

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
                    cast(DeyeApiResponseFogPlatformDeviceProperties, properties),
                    product_id=product_id,
                )
            )

        return self._subscribe_topic(self._topic, on_payload)

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

    async def publish_command(
        self,
        product_id: str,
        device_id: str,
        command: DeyeDeviceCommand,
        properties: dict[str, int] | None = None,
        baseline: FogCommandBaseline | None = None,
    ) -> None:
        """
        For Fog platform, commands are not published via MQTT.
        Instead, use the cloud API to send commands.
        """
        feature_config = get_product_feature_config(product_id)
        properties_to_publish = resolve_fog_command_properties(
            product_id,
            command,
            properties=properties,
            baseline=baseline,
            protocol_version=self._fog_protocol_versions.get(device_id),
        )
        if not properties_to_publish:
            return

        if feature_config["single_property_fog_commands"]:
            if "Power" in properties_to_publish:
                await self._cloud_api.set_fog_platform_device_properties(
                    device_id, {"Power": properties_to_publish["Power"]}
                )
            for name, value in properties_to_publish.items():
                if name == "Power":
                    continue
                await self._cloud_api.set_fog_platform_device_properties(
                    device_id, {name: value}
                )
            return

        await self._cloud_api.set_fog_platform_device_properties(
            device_id, properties_to_publish
        )

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
        return DeyeDeviceState(device_properties, product_id=product_id)
