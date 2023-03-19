"""MQTT related stuffs"""
import json
from asyncio import Future, get_running_loop
from collections.abc import Callable
from typing import Any

import paho.mqtt.client as mqtt

from .const import QUERY_DEVICE_STATE_COMMAND
from .device_state_command import DeyeDeviceState


class DeyeMqttClient:
    """An wrapper around the MQTT client connected to the Deye MQTT server."""

    def __init__(
        self,
        host: str,
        ssl_port: int,
        username: str,
        password: str,
        endpoint: str,
    ) -> None:
        self._loop = get_running_loop()
        self._mqtt = mqtt.Client()
        self._mqtt.username_pw_set(username, password)
        self._mqtt.tls_set()
        self._mqtt.on_connect = self._mqtt_on_connect
        self._mqtt.on_message = self._mqtt_on_message
        self._mqtt_host = host
        self._mqtt_ssl_port = ssl_port
        self._endpoint = endpoint
        self._subscribers: dict[str, set[Callable[[Any], None]]] = {}
        self._pending_commands: list[tuple[str, bytes]] = []

    def connect(self) -> None:
        """Connect the MQTT client to the server."""
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
        _result_code: int,
        _properties: mqtt.Properties | None = None,
    ) -> None:
        for topic, callbacks in self._subscribers.items():
            if len(callbacks) > 0:
                self._mqtt.subscribe(topic)
        if len(self._pending_commands) > 0:
            for topic, command in self._pending_commands:
                self._mqtt.publish(topic, command)
            self._pending_commands.clear()

    def _mqtt_on_message(
        self, _mqtt: mqtt.Client, _userdata: None, msg: mqtt.MQTTMessage
    ) -> None:
        if msg.topic not in self._subscribers:
            return
        callbacks = self._subscribers[msg.topic]
        try:
            data = json.loads(msg.payload)["data"]
            for callback in callbacks.copy():
                self._loop.call_soon_threadsafe(callback, data)
        except (json.JSONDecodeError, KeyError):
            pass

    def _get_topic_prefix(self, product_id: str, device_id: str) -> str:
        return f"{self._endpoint}/{product_id}/{device_id}"

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

    def subscribe_state_change(
        self,
        product_id: str,
        device_id: str,
        callback: Callable[[DeyeDeviceState], None],
    ) -> Callable[[], None]:
        """Subscribe to state changes of specified device."""
        return self._subscribe_topic(
            f"{self._get_topic_prefix(product_id, device_id)}/status/hex",
            lambda d: callback(DeyeDeviceState(d)),
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
            lambda d: callback(d["online"]),
        )

    def publish_command(self, product_id: str, device_id: str, command: bytes) -> None:
        """Publish commands to a device"""
        topic = f"{self._get_topic_prefix(product_id, device_id)}/command/hex"
        if self._mqtt.is_connected():
            self._mqtt.publish(topic, command)
        else:
            self._pending_commands.append((topic, command))

    def query_device_state(
        self, product_id: str, device_id: str
    ) -> Future[DeyeDeviceState]:
        """Query the latest device state."""
        future: Future[DeyeDeviceState] = Future()
        unsubscribe: Callable[[], None] | None = None

        def on_message(state: DeyeDeviceState) -> None:
            future.set_result(state)
            if unsubscribe is not None:
                unsubscribe()

        unsubscribe = self.subscribe_state_change(product_id, device_id, on_message)
        self.publish_command(product_id, device_id, QUERY_DEVICE_STATE_COMMAND)

        return future
