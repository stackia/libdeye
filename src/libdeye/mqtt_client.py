"""MQTT related stuffs"""

import asyncio
import json
from abc import ABC, abstractmethod
from asyncio import Future, get_running_loop
from collections.abc import Callable
from ssl import SSLContext
from typing import Any, cast

import paho.mqtt.client as mqtt

from .cloud_api import DeyeApiResponseFogPlatformDeviceProperties, DeyeCloudApi
from .const import QUERY_DEVICE_STATE_COMMAND_CLASSIC
from .device_command import DeyeDeviceCommand
from .device_state import DeyeDeviceState


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
        _mqtt: mqtt.Client,
        _userdata: None,
        _flags: dict[str, int],
        _result_code: Any,
        _properties: Any,
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
        self, product_id: str, device_id: str, command: DeyeDeviceCommand
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
            lambda payload: callback(DeyeDeviceState(payload)),
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

    async def publish_command(
        self, product_id: str, device_id: str, command: DeyeDeviceCommand | bytes
    ) -> None:
        """Publish commands to a device"""
        topic = f"{self._get_topic_prefix(product_id, device_id)}/command/hex"
        command_bytes = (
            command.to_bytes() if isinstance(command, DeyeDeviceCommand) else command
        )
        if self._mqtt.is_connected():
            self._mqtt.publish(topic, command_bytes)
        else:
            self._pending_commands.append((topic, command_bytes))

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


class DeyeFogMqttClient(BaseDeyeMqttClient):
    """MQTT client for the Fog platform."""

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
        return self._subscribe_topic(
            self._topic,
            lambda payload: (
                callback(
                    DeyeDeviceState(
                        cast(
                            DeyeApiResponseFogPlatformDeviceProperties,
                            payload["data"]["properties"],
                        )
                    )
                )
                if payload["device_id"] == device_id
                and payload["biz_code"] == "device_data"
                and payload["data"]["message_type"] == "thing_property"
                else None
            ),
        )

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
        self, product_id: str, device_id: str, command: DeyeDeviceCommand
    ) -> None:
        """
        For Fog platform, commands are not published via MQTT.
        Instead, use the cloud API to send commands.
        """
        await self._cloud_api.set_fog_platform_device_properties(
            device_id, command.to_json()
        )

    async def query_device_state(
        self, product_id: str, device_id: str
    ) -> DeyeDeviceState:
        """Query the latest device state."""
        device_properties = await self._cloud_api.get_fog_platform_device_properties(
            device_id
        )
        return DeyeDeviceState(device_properties)
