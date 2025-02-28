"""Deye Cloud API related stuffs"""

import time
from collections.abc import Callable
from enum import IntEnum
from typing import Any, TypedDict, cast

import jwt
from aiohttp import ClientSession
from aiohttp.client_exceptions import ClientError

from .const import (
    DEYE_API_END_USER_ENDPOINT,
    DEYE_LOGIN_PARAM_APP_ID,
    DEYE_LOGIN_PARAM_EXTEND,
)


class DeyeCloudApiInvalidAuthError(Exception):
    """Error to indicate there is invalid auth."""


class DeyeCloudApiCannotConnectError(Exception):
    """Error to indicate we cannot connect."""


class DeyeIotPlatform(IntEnum):
    """IoT platform of devices"""

    Classic = 1
    Fog = 2


class DeyeApiResponseEnvelopeMeta(TypedDict):
    """Meta information for the API message envelope"""

    code: int
    message: str


class DeyeApiResponseEnvelope(TypedDict):
    """Message envelope for all API responses"""

    meta: DeyeApiResponseEnvelopeMeta
    data: Any


def ensure_valid_response_code(result: DeyeApiResponseEnvelope) -> None:
    """Raise errors if we don't have a valid result["meta"]["code"]"""
    try:
        if result["meta"]["code"] != 0:
            raise DeyeCloudApiInvalidAuthError
    except KeyError as err:
        raise DeyeCloudApiCannotConnectError from err


class DeyeApiResponseClassicPlatformMqttInfo(TypedDict):
    """MQTT information for the Deye platform returned by the API"""

    password: str
    loginname: str
    mqtthost: str
    mqttport: int
    clientid: str
    endpoint: str
    sslport: int


class DeyeApiResponseFogPlatformMqttTopics(TypedDict):
    """MQTT topics returned by the API"""

    all: list[str]
    pub: list[str]
    sub: list[str]


class DeyeApiResponseFogPlatformMqttInfo(TypedDict):
    """MQTT information for the Fog platform returned by the API"""

    username: str
    clientid: str
    password: str
    mqtt_host: str
    ws_port: str
    ssl_port: str
    topic: DeyeApiResponseFogPlatformMqttTopics
    expire: int


class DeyeApiResponseFogPlatformDeviceProperties(TypedDict):
    """Device properties for the Fog platform returned by the API"""

    CompressorStatus: int
    CurrentAmbientTemperature: int
    CurrentCoilTemperature: int
    CurrentEnvironmentalHumidity: int
    CurrentExhaustTemperature: int
    Demisting: int
    EnvironmentalRating: int
    Fan: int
    KeyLock: int
    Mode: int
    NegativeIon: int
    Power: int
    ProtocolVersion: int
    SetHumidity: int
    SolenoidValve: int
    SwingingWind: int
    TimedOff: int
    TimedOn: int
    TimedShutdownHourSetting: int
    TimedShutdownMinuteSettingTime: int
    TimedShutdownTimeRemainingHours: int
    TimedShutdownTimeRemainingMinutes: int
    TimedStartupHoursSetTime: int
    TimedStartupMinuteSettingTime: int
    TimedStartupTimeRemainingHours: int
    TimedStartupTimeRemainingMinutes: int
    WaterPump: int
    WaterTank: int
    WindSpeed: int
    fault: dict[str, int]


class DeyeApiRequestFogPlatformDeviceCommands(TypedDict):
    """Device Command for the Fog platform sent to the API"""

    KeyLock: int
    Mode: int
    Power: int
    WindSpeed: int
    SetHumidity: int
    NegativeIon: int
    SwingingWind: int
    WaterPump: int


class DeyeApiResponseDeviceInfo(TypedDict):
    """Device information returned by the API"""

    producttype_id: int
    device_name: str
    product_name: str
    platform: DeyeIotPlatform
    mac: str
    protocol_version: str
    gatewaytype: int
    is_combo: bool
    alias: str
    deviceid: str
    product_id: str
    role: int
    device_id: str
    product_icon: str
    online: bool
    product_type: str
    payload: Any
    picture_v3: str
    work_time: int
    user_count: int


class DeyeApiResponseProductDefinition(TypedDict):
    """Product definition information returned by the API"""

    productid: str
    pname: str
    brand: str
    model: str
    picture: str
    picture_v3: str | None
    config_guide: str
    status: int
    configType: int


class DeyeApiResponseProductType(TypedDict):
    """Product type information returned by the API"""

    ptype: str
    ptypename: str
    pdata: list[DeyeApiResponseProductDefinition]


class DeyeCloudApi:
    """Interact with Deye Cloud APIs."""

    user_id: str | None
    _auth_token_exp: int | None
    on_auth_token_refreshed: Callable[[str], None] | None

    def __init__(
        self,
        session: ClientSession,
        username: str,
        password: str,
        auth_token: str | None = None,
    ) -> None:
        self._session = session
        self._username = username
        self._password = password
        self.auth_token = auth_token
        self.on_auth_token_refreshed = None

    @property
    def auth_token(self) -> str | None:
        """Get the auth token"""
        return self._auth_token

    @auth_token.setter
    def auth_token(self, value: str | None) -> None:
        """Set the auth token and decode user_id/_auth_token_exp"""
        self._auth_token = value
        if value:
            try:
                decoded = jwt.decode(value, options={"verify_signature": False})
                self.user_id = decoded["enduserid"]
                self._auth_token_exp = decoded["exp"]
            except jwt.DecodeError as err:
                self.user_id = None
                self._auth_token_exp = None
                raise DeyeCloudApiInvalidAuthError from err
        else:
            self.user_id = None
            self._auth_token_exp = None

    async def authenticate(self) -> None:
        """Authenticate by username/password and set the auth token."""
        try:
            response = await self._session.post(
                f"{DEYE_API_END_USER_ENDPOINT}/login/",
                json={
                    "appid": DEYE_LOGIN_PARAM_APP_ID,
                    "extend": DEYE_LOGIN_PARAM_EXTEND,
                    "pushtype": "Ali",
                    "loginname": self._username,
                    "password": self._password,
                },
            )
            result: DeyeApiResponseEnvelope = await response.json()
        except ClientError as err:
            raise DeyeCloudApiCannotConnectError from err

        ensure_valid_response_code(result)

        try:
            token = result["data"]["token"]
            if token:
                self.auth_token = token
                return
        except KeyError:
            pass
        raise DeyeCloudApiInvalidAuthError

    async def refresh_token_if_near_expiry(self, force: bool = False) -> None:
        """Get a new auth token by calling /refreshToken if the current auth token is about to be expired. This will be
        automatically called for each API call.

        Args:
            force: If True, refresh the token regardless of expiry time.
        """
        if self._auth_token_exp is None:
            raise DeyeCloudApiInvalidAuthError

        if not force and self._auth_token_exp - time.time() > 24 * 60 * 60:
            return

        try:
            response = await self._session.post(
                f"{DEYE_API_END_USER_ENDPOINT}/refreshToken/",
                json={"token": self.auth_token},
            )
            result: DeyeApiResponseEnvelope = await response.json()
        except ClientError as err:
            raise DeyeCloudApiCannotConnectError from err

        try:
            ensure_valid_response_code(result)
            token = result["data"]["token"]
            if token:
                self.auth_token = token
                if self.on_auth_token_refreshed:
                    self.on_auth_token_refreshed(token)
            return
        except DeyeCloudApiInvalidAuthError:
            await self.authenticate()
            if self.auth_token and self.on_auth_token_refreshed:
                self.on_auth_token_refreshed(self.auth_token)
            return
        except KeyError:
            pass

        raise DeyeCloudApiInvalidAuthError

    async def _make_authenticated_request(
        self, method: str, endpoint: str, **kwargs: Any
    ) -> DeyeApiResponseEnvelope:
        """Make an authenticated request to the Deye API.

        Args:
            method: HTTP method (get, post)
            endpoint: API endpoint path
            **kwargs: Additional arguments to pass to the request

        Returns:
            The API response
        """
        await self.refresh_token_if_near_expiry()

        # Add authorization header if not present
        headers = kwargs.get("headers", {})
        if "Authorization" not in headers and self.auth_token:
            headers["Authorization"] = f"JWT {self.auth_token}"
            kwargs["headers"] = headers

        url = f"{DEYE_API_END_USER_ENDPOINT}/{endpoint}"

        try:
            if method.lower() == "get":
                response = await self._session.get(url, **kwargs)
            elif method.lower() == "post":
                response = await self._session.post(url, **kwargs)
            else:
                raise ValueError(f"Unsupported HTTP method: {method}")

            result: DeyeApiResponseEnvelope = await response.json()
        except ClientError as err:
            raise DeyeCloudApiCannotConnectError from err

        ensure_valid_response_code(result)
        return result

    async def get_device_list(self) -> list[DeyeApiResponseDeviceInfo]:
        """Get all connected devices for current user"""
        result = await self._make_authenticated_request("get", "deviceList/?app=new")
        return cast(list[DeyeApiResponseDeviceInfo], result["data"])

    async def get_product_list(self) -> list[DeyeApiResponseProductType]:
        """Get all available products"""
        result = await self._make_authenticated_request("get", "productlist/?app=new")
        return cast(list[DeyeApiResponseProductType], result["data"]["result"])

    async def get_deye_platform_mqtt_info(
        self,
    ) -> DeyeApiResponseClassicPlatformMqttInfo:
        """Get MQTT server info / credentials for current user (Deye platform)"""
        result = await self._make_authenticated_request("get", "mqttInfo/")
        return cast(DeyeApiResponseClassicPlatformMqttInfo, result["data"])

    async def get_fog_platform_mqtt_info(self) -> DeyeApiResponseFogPlatformMqttInfo:
        """Get MQTT server info / credentials for current user (Fog platform)"""
        result = await self._make_authenticated_request("get", "fogmqttinfo/")
        return cast(DeyeApiResponseFogPlatformMqttInfo, result["data"])

    async def get_fog_platform_device_properties(
        self, device_id: str
    ) -> DeyeApiResponseFogPlatformDeviceProperties:
        """Get properties for a device on the Fog platform"""
        result = await self._make_authenticated_request(
            "get", f"get/properties/?device_id={device_id}"
        )
        return cast(
            DeyeApiResponseFogPlatformDeviceProperties, result["data"]["properties"]
        )

    async def poll_fog_platform_device_properties(self, device_id: str) -> None:
        """Poll properties for a device on the Fog platform"""
        await self._make_authenticated_request(
            "post",
            "set/properties/",
            json={
                "device_id": device_id,
                "params": {"RealData": 1},
            },
        )

    async def set_fog_platform_device_properties(
        self, device_id: str, params: object
    ) -> None:
        """Set properties for a device on the Fog platform"""
        await self._make_authenticated_request(
            "post",
            "set/properties/",
            json={
                "device_id": device_id,
                "params": params,
            },
        )
