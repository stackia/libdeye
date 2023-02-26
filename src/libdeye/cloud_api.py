"""Deye Cloud API related stuffs"""
import time
from collections.abc import Callable
from typing import cast

import jwt
from aiohttp import ClientSession
from aiohttp.client_exceptions import ClientError

from .const import (
    DEYE_API_END_USER_ENDPOINT,
    DEYE_LOGIN_PARAM_APP_ID,
    DEYE_LOGIN_PARAM_EXTEND,
)
from .types import (
    DeyeApiResponseDeviceInfo,
    DeyeApiResponseEnvelope,
    DeyeApiResponseMqttInfo,
)


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
                data={
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

    async def refresh_token_if_near_expiry(self) -> None:
        """Get a new auth token by calling /refreshToken if the current auth token is about to be expired. This will be
        automatically called for each API call."""
        if self._auth_token_exp is None:
            raise DeyeCloudApiInvalidAuthError

        if self._auth_token_exp - time.time() > 24 * 60 * 60:
            return

        try:
            response = await self._session.post(
                f"{DEYE_API_END_USER_ENDPOINT}/refreshToken/",
                data={"token": self.auth_token},
            )
            result: DeyeApiResponseEnvelope = await response.json()
        except ClientError as err:
            raise DeyeCloudApiCannotConnectError from err

        ensure_valid_response_code(result)

        try:
            token = result["data"]["token"]
            if token:
                self.auth_token = token
                if self.on_auth_token_refreshed:
                    self.on_auth_token_refreshed(token)
                return
        except KeyError:
            pass
        raise DeyeCloudApiInvalidAuthError

    async def get_device_list(self) -> list[DeyeApiResponseDeviceInfo]:
        """Get all connected devices for current user"""
        await self.refresh_token_if_near_expiry()

        try:
            response = await self._session.get(
                f"{DEYE_API_END_USER_ENDPOINT}/deviceList/?app=new",
                headers={"Authorization": f"JWT {self.auth_token}"},
            )
            result: DeyeApiResponseEnvelope = await response.json()
        except ClientError as err:
            raise DeyeCloudApiCannotConnectError from err

        ensure_valid_response_code(result)
        return cast(list[DeyeApiResponseDeviceInfo], result["data"])

    async def get_mqtt_info(self) -> DeyeApiResponseMqttInfo:
        """Get MQTT server info / credentials for current user"""
        await self.refresh_token_if_near_expiry()

        try:
            response = await self._session.get(
                f"{DEYE_API_END_USER_ENDPOINT}/mqttInfo/",
                headers={"Authorization": f"JWT {self.auth_token}"},
            )
            result: DeyeApiResponseEnvelope = await response.json()
        except ClientError as err:
            raise DeyeCloudApiCannotConnectError from err

        ensure_valid_response_code(result)
        return cast(DeyeApiResponseMqttInfo, result["data"])


def ensure_valid_response_code(result: DeyeApiResponseEnvelope) -> None:
    """Raise errors if we don't have a valid result["meta"]["code"]"""
    try:
        if result["meta"]["code"] != 0:
            raise DeyeCloudApiInvalidAuthError
    except KeyError as err:
        raise DeyeCloudApiCannotConnectError from err


class DeyeCloudApiInvalidAuthError(Exception):
    """Error to indicate there is invalid auth."""


class DeyeCloudApiCannotConnectError(Exception):
    """Error to indicate we cannot connect."""
