"""High-level client that hides Classic / Fog / Combo transport."""

from collections.abc import Callable
from ssl import SSLContext
from typing import cast

from aiohttp import ClientSession

from .cloud_api import (
    DeyeApiResponseDeviceInfo,
    DeyeApiResponseFogPlatformDeviceProperties,
    DeyeCloudApi,
    DeyeDeviceTransport,
    transport_for_device,
)
from .const import (
    QUERY_DEVICE_STATE_COMMAND_CLASSIC,
    DeyeProductConfig,
    get_product_feature_config,
)
from .device_command import DeyeDeviceCommand
from .device_state import DeyeDeviceState
from .mqtt_client import (
    BaseDeyeMqttClient,
    DeyeClassicMqttClient,
    mqtt_client_type_for_device,
)

DEFAULT_CLASSIC_STATE_HEX = "1411000000370000000000000000003C3C0000000000"


class DeyeClient:
    """Authenticate, list devices, and share MQTT connections per transport."""

    def __init__(
        self,
        cloud_api: DeyeCloudApi,
        tls_context: SSLContext | None = None,
    ) -> None:
        """Wrap an authenticated (or authenticatable) cloud API client."""
        self.cloud_api = cloud_api
        self._tls_context = tls_context
        self._mqtt_by_type: dict[type[BaseDeyeMqttClient], BaseDeyeMqttClient] = {}

    @classmethod
    def from_credentials(
        cls,
        session: ClientSession,
        username: str,
        password: str,
        auth_token: str | None = None,
        tls_context: SSLContext | None = None,
    ) -> DeyeClient:
        """Create a client from Deye Cloud credentials."""
        return cls(
            DeyeCloudApi(session, username, password, auth_token),
            tls_context,
        )

    async def authenticate(self) -> None:
        """Authenticate with username/password and store the auth token."""
        await self.cloud_api.authenticate()

    async def list_devices(self) -> list[DeyeDevice]:
        """Return a device wrapper for each cloud device-list entry."""
        infos = await self.cloud_api.get_device_list()
        return [DeyeDevice(self, info) for info in infos]

    async def get_device(self, device_id: str) -> DeyeDevice | None:
        """Return the device with the given id, or None if it is missing."""
        for device in await self.list_devices():
            if device.device_id == device_id:
                return device
        return None

    async def mqtt_for(self, device: DeyeDevice) -> BaseDeyeMqttClient:
        """Return a connected MQTT client for the device's transport."""
        client_cls = mqtt_client_type_for_device(device.info)
        if client_cls not in self._mqtt_by_type:
            client = client_cls(self.cloud_api, self._tls_context)
            await client.connect()
            self._mqtt_by_type[client_cls] = client
        return self._mqtt_by_type[client_cls]

    def disconnect(self) -> None:
        """Disconnect all pooled MQTT clients."""
        for client in self._mqtt_by_type.values():
            client.disconnect()
        self._mqtt_by_type.clear()


class DeyeDevice:
    """One dehumidifier. Callers do not choose Classic, Fog, or Combo."""

    def __init__(self, client: DeyeClient, info: DeyeApiResponseDeviceInfo) -> None:
        """Create a device wrapper from a device-list entry."""
        self.client = client
        self.info = info
        self.available = bool(info.get("online", False))
        self.reported_state = self._state_from_payload(info.get("payload"))
        self.state = self.reported_state.copy()
        self._mqtt: BaseDeyeMqttClient | None = None
        self._unsubscribers: list[Callable[[], None]] = []

    @property
    def device_id(self) -> str:
        """Cloud device id."""
        return self.info["device_id"]

    @property
    def product_id(self) -> str:
        """Product UUID."""
        return self.info["product_id"]

    @property
    def name(self) -> str:
        """User-facing device name."""
        return self.info["device_name"]

    @property
    def transport(self) -> DeyeDeviceTransport:
        """Official CommandManger path for this device."""
        return transport_for_device(self.info)

    @property
    def feature_config(self) -> DeyeProductConfig:
        """Official control-panel capabilities for this product."""
        return get_product_feature_config(self.product_id)

    @staticmethod
    def _state_from_payload(payload: object) -> DeyeDeviceState:
        if isinstance(payload, dict):
            return DeyeDeviceState(
                cast(DeyeApiResponseFogPlatformDeviceProperties, payload)
            )
        if isinstance(payload, str) and payload:
            return DeyeDeviceState(payload)
        return DeyeDeviceState(DEFAULT_CLASSIC_STATE_HEX)

    def _set_reported(self, state: DeyeDeviceState) -> None:
        self.reported_state = state
        self.state = state.copy()

    async def ensure_connected(self) -> BaseDeyeMqttClient:
        """Connect the transport MQTT client if needed."""
        self._mqtt = await self.client.mqtt_for(self)
        return self._mqtt

    async def refresh(self) -> DeyeDeviceState:
        """Query current state and wait for the result.

        Fog uses HTTP GET ``get/properties``. Classic and Combo publish
        MQTT poll bytes ``00 01`` and wait for ``status/hex``.
        """
        mqtt = await self.ensure_connected()
        state = await mqtt.query_device_state(self.product_id, self.device_id)
        self._set_reported(state)
        return state

    async def request_refresh(self) -> DeyeDeviceState | None:
        """Trigger a state refresh without waiting for the new payload.

        Fog POSTs official ``RealData: 1`` (``setPollFogProperties``). Classic
        and Combo publish ``00 01``. The next MQTT state message updates
        ``reported_state``. Use ``refresh()`` when a blocking GET is needed.
        """
        mqtt = await self.ensure_connected()
        if self.transport is DeyeDeviceTransport.FOG:
            await self.client.cloud_api.poll_fog_platform_device_properties(
                self.device_id
            )
            return None
        if not isinstance(mqtt, DeyeClassicMqttClient):
            raise TypeError("Classic/Combo query requires Classic MQTT")
        await mqtt.publish_command(
            self.product_id,
            self.device_id,
            QUERY_DEVICE_STATE_COMMAND_CLASSIC,
        )
        return None

    async def apply(
        self,
        command: DeyeDeviceCommand | None = None,
        *,
        baseline: DeyeDeviceState | DeyeDeviceCommand | None = None,
    ) -> None:
        """Send a command using the official path for this device."""
        if command is None:
            command = self.state.to_command()
        mqtt = await self.ensure_connected()
        await mqtt.publish_command(
            self.product_id,
            self.device_id,
            command,
            baseline=self.reported_state if baseline is None else baseline,
        )

    def subscribe(
        self,
        on_state: Callable[[DeyeDeviceState], None] | None = None,
        on_availability: Callable[[bool], None] | None = None,
    ) -> Callable[[], None]:
        """Subscribe to MQTT state and availability.

        Call ``ensure_connected()`` or ``refresh()`` first.
        """
        if self._mqtt is None:
            raise RuntimeError(
                "Call ensure_connected() or refresh() before subscribe()"
            )
        mqtt = self._mqtt

        def handle_state(state: DeyeDeviceState) -> None:
            self._set_reported(state)
            if on_state is not None:
                on_state(state)

        def handle_availability(available: bool) -> None:
            self.available = available
            if on_availability is not None:
                on_availability(available)

        if on_state is not None:
            self._unsubscribers.append(
                mqtt.subscribe_state_change(
                    self.product_id, self.device_id, handle_state
                )
            )
        if on_availability is not None:
            self._unsubscribers.append(
                mqtt.subscribe_availability_change(
                    self.product_id, self.device_id, handle_availability
                )
            )

        def unsubscribe() -> None:
            while self._unsubscribers:
                self._unsubscribers.pop()()

        return unsubscribe
