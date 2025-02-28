"""Tests for the Deye Cloud API module."""

import time
from typing import AsyncGenerator, Generator
from unittest.mock import MagicMock, patch

import jwt
import pytest
import pytest_asyncio
from aiohttp import ClientError, ClientSession
from aioresponses import aioresponses

from libdeye.cloud_api import (
    DeyeApiResponseEnvelope,
    DeyeCloudApi,
    DeyeCloudApiCannotConnectError,
    DeyeCloudApiInvalidAuthError,
    DeyeIotPlatform,
    ensure_valid_response_code,
)
from libdeye.const import (
    DEYE_API_END_USER_ENDPOINT,
)


@pytest.fixture
def mock_aioresponse() -> Generator[aioresponses, None, None]:
    """Fixture for mocking aiohttp responses."""
    with aioresponses() as m:
        yield m


@pytest_asyncio.fixture
async def api_client() -> AsyncGenerator[DeyeCloudApi, None]:
    """Fixture for creating a DeyeCloudApi client."""
    async with ClientSession() as session:
        client = DeyeCloudApi(
            session=session,
            username="test_user",
            password="test_password",
        )
        yield client


@pytest.fixture
def mock_auth_token() -> str:
    """Fixture for creating a mock JWT auth token."""
    # Create a token that expires in 1 day
    exp_time = int(time.time()) + 86400
    payload = {
        "enduserid": "test_user_id",
        "exp": exp_time,
    }
    token = jwt.encode(payload, "secret", algorithm="HS256")
    return token


def test_auth_token_setter_valid(mock_auth_token: str) -> None:
    """Test that setting a valid auth token correctly sets user_id and _auth_token_exp."""
    session = MagicMock()
    api = DeyeCloudApi(session, "test_user", "test_password")

    api.auth_token = mock_auth_token

    assert api.user_id == "test_user_id"
    assert api._auth_token_exp is not None
    assert api.auth_token == mock_auth_token


def test_auth_token_setter_invalid() -> None:
    """Test that setting an invalid auth token raises DeyeCloudApiInvalidAuthError."""
    session = MagicMock()
    api = DeyeCloudApi(session, "test_user", "test_password")

    with pytest.raises(DeyeCloudApiInvalidAuthError):
        api.auth_token = "invalid_token"


def test_auth_token_setter_none(mock_auth_token: str) -> None:
    """Test that setting auth_token to None clears user_id and _auth_token_exp."""
    session = MagicMock()
    api = DeyeCloudApi(
        session, "test_user", "test_password", auth_token=mock_auth_token
    )

    api.auth_token = None

    assert api.user_id is None
    assert api._auth_token_exp is None
    assert api.auth_token is None


@pytest.mark.asyncio
async def test_authenticate_success(
    api_client: DeyeCloudApi, mock_aioresponse: aioresponses, mock_auth_token: str
) -> None:
    """Test successful authentication."""
    mock_response = {
        "meta": {"code": 0, "message": "success"},
        "data": {"token": mock_auth_token},
    }

    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/login/",
        status=200,
        payload=mock_response,
    )

    await api_client.authenticate()

    assert api_client.auth_token == mock_auth_token
    assert api_client.user_id == "test_user_id"


@pytest.mark.asyncio
async def test_authenticate_invalid_credentials(
    api_client: DeyeCloudApi, mock_aioresponse: aioresponses
) -> None:
    """Test authentication with invalid credentials."""
    mock_response = {
        "meta": {"code": 1, "message": "Invalid credentials"},
        "data": None,
    }

    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/login/",
        status=200,
        payload=mock_response,
    )

    with pytest.raises(DeyeCloudApiInvalidAuthError):
        await api_client.authenticate()


@pytest.mark.asyncio
async def test_authenticate_connection_error(
    api_client: DeyeCloudApi, mock_aioresponse: aioresponses
) -> None:
    """Test authentication with connection error."""
    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/login/",
        exception=ClientError("Connection error"),
    )

    with pytest.raises(DeyeCloudApiCannotConnectError):
        await api_client.authenticate()


@pytest.mark.asyncio
async def test_authenticate_missing_token(
    api_client: DeyeCloudApi, mock_aioresponse: aioresponses
) -> None:
    """Test authentication with missing token in response."""
    mock_response = {
        "meta": {"code": 0, "message": "success"},
        "data": {},  # Missing token
    }

    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/login/",
        status=200,
        payload=mock_response,
    )

    with pytest.raises(DeyeCloudApiInvalidAuthError):
        await api_client.authenticate()


@pytest.mark.asyncio
async def test_refresh_token_not_expired(
    api_client: DeyeCloudApi, mock_auth_token: str
) -> None:
    """Test that refresh_token_if_near_expiry does nothing if token is not near expiry."""
    api_client.auth_token = mock_auth_token

    # Create a token that expires far in the future
    future_token = jwt.encode(
        {
            "enduserid": "test_user_id",
            "exp": int(time.time()) + 30 * 24 * 60 * 60,
        },  # 30 days
        "secret",
        algorithm="HS256",
    )
    api_client.auth_token = future_token

    # Mock the _make_authenticated_request method to ensure it's not called
    with patch.object(api_client, "_make_authenticated_request") as mock_request:
        await api_client.refresh_token_if_near_expiry()
        mock_request.assert_not_called()


@pytest.mark.asyncio
async def test_refresh_token_almost_expired(
    api_client: DeyeCloudApi, mock_aioresponse: aioresponses, mock_auth_token: str
) -> None:
    """Test token refresh when it's almost expired."""
    api_client.auth_token = mock_auth_token

    new_token = jwt.encode(
        {"enduserid": "test_user_id", "exp": int(time.time()) + 172800},
        "secret",
        algorithm="HS256",
    )

    mock_response = {
        "meta": {"code": 0, "message": "success"},
        "data": {"token": new_token},
    }

    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/refreshToken/",
        status=200,
        payload=mock_response,
    )

    await api_client.refresh_token_if_near_expiry()

    assert api_client.auth_token == new_token


@pytest.mark.asyncio
async def test_refresh_token_force(
    api_client: DeyeCloudApi, mock_aioresponse: aioresponses, mock_auth_token: str
) -> None:
    """Test forced token refresh."""
    api_client.auth_token = mock_auth_token

    new_token = jwt.encode(
        {"enduserid": "test_user_id", "exp": int(time.time()) + 172800},
        "secret",
        algorithm="HS256",
    )

    mock_response = {
        "meta": {"code": 0, "message": "success"},
        "data": {"token": new_token},
    }

    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/refreshToken/",
        status=200,
        payload=mock_response,
    )

    await api_client.refresh_token_if_near_expiry(force=True)

    assert api_client.auth_token == new_token


@pytest.mark.asyncio
async def test_refresh_token_callback(
    api_client: DeyeCloudApi, mock_aioresponse: aioresponses, mock_auth_token: str
) -> None:
    """Test that on_auth_token_refreshed callback is called when token is refreshed."""
    api_client.auth_token = mock_auth_token

    new_token = jwt.encode(
        {"enduserid": "test_user_id", "exp": int(time.time()) + 172800},
        "secret",
        algorithm="HS256",
    )

    mock_response = {
        "meta": {"code": 0, "message": "success"},
        "data": {"token": new_token},
    }

    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/refreshToken/",
        status=200,
        payload=mock_response,
    )

    callback_mock = MagicMock()
    api_client.on_auth_token_refreshed = callback_mock

    await api_client.refresh_token_if_near_expiry(force=True)

    callback_mock.assert_called_once_with(new_token)


@pytest.mark.asyncio
async def test_refresh_token_invalid_auth(
    api_client: DeyeCloudApi, mock_aioresponse: aioresponses, mock_auth_token: str
) -> None:
    """Test token refresh with invalid auth response."""
    api_client.auth_token = mock_auth_token

    mock_response = {
        "meta": {"code": 1, "message": "Invalid token"},
        "data": None,
    }

    # Mock refreshToken endpoint to return invalid auth
    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/refreshToken/",
        status=200,
        payload=mock_response,
    )

    # Mock login endpoint to succeed
    login_response = {
        "meta": {"code": 0, "message": "success"},
        "data": {"token": mock_auth_token},
    }

    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/login/",
        status=200,
        payload=login_response,
    )

    callback_mock = MagicMock()
    api_client.on_auth_token_refreshed = callback_mock

    await api_client.refresh_token_if_near_expiry(force=True)

    # Should have called authenticate() and the callback
    assert api_client.auth_token == mock_auth_token
    callback_mock.assert_called_once_with(mock_auth_token)


@pytest.mark.asyncio
async def test_make_authenticated_request(
    api_client: DeyeCloudApi, mock_aioresponse: aioresponses, mock_auth_token: str
) -> None:
    """Test making an authenticated request."""
    api_client.auth_token = mock_auth_token

    mock_response = {
        "meta": {"code": 0, "message": "success"},
        "data": {"some": "data"},
    }

    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/refreshToken/",
        status=200,
        payload={
            "meta": {"code": 0, "message": "success"},
            "data": {"token": mock_auth_token},
        },
    )

    mock_aioresponse.get(
        f"{DEYE_API_END_USER_ENDPOINT}/test/",
        status=200,
        payload=mock_response,
    )

    result = await api_client._make_authenticated_request("get", "test/")

    assert result == mock_response


@pytest.mark.asyncio
async def test_make_authenticated_request_invalid_method(
    api_client: DeyeCloudApi, mock_auth_token: str, mock_aioresponse: aioresponses
) -> None:
    """Test making an authenticated request with an invalid HTTP method."""
    api_client.auth_token = mock_auth_token

    # Mock the refresh token endpoint to avoid authentication errors
    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/refreshToken/",
        status=200,
        payload={
            "meta": {"code": 0, "message": "success"},
            "data": {"token": mock_auth_token},
        },
    )

    with pytest.raises(ValueError, match="Unsupported HTTP method: invalid"):
        await api_client._make_authenticated_request("invalid", "test/")


@pytest.mark.asyncio
async def test_get_device_list(
    api_client: DeyeCloudApi, mock_aioresponse: aioresponses, mock_auth_token: str
) -> None:
    """Test getting the device list."""
    api_client.auth_token = mock_auth_token

    # Mock the refresh token endpoint to avoid authentication errors
    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/refreshToken/",
        status=200,
        payload={
            "meta": {"code": 0, "message": "success"},
            "data": {"token": mock_auth_token},
        },
    )

    mock_devices = [
        {
            "producttype_id": 1,
            "device_name": "Test Device",
            "product_name": "Test Product",
            "platform": DeyeIotPlatform.Fog,
            "mac": "00:11:22:33:44:55",
            "protocol_version": "1.0",
            "gatewaytype": 1,
            "is_combo": False,
            "alias": "Test Alias",
            "deviceid": "test_device_id",
            "product_id": "test_product_id",
            "role": 1,
            "device_id": "test_device_id",
            "product_icon": "icon.png",
            "online": True,
            "product_type": "test_type",
            "payload": {},
            "picture_v3": "picture.png",
            "work_time": 100,
            "user_count": 1,
        }
    ]

    mock_response = {
        "meta": {"code": 0, "message": "success"},
        "data": mock_devices,
    }

    mock_aioresponse.get(
        f"{DEYE_API_END_USER_ENDPOINT}/deviceList/?app=new",
        status=200,
        payload=mock_response,
    )

    result = await api_client.get_device_list()

    assert result == mock_devices


@pytest.mark.asyncio
async def test_get_product_list(
    api_client: DeyeCloudApi, mock_aioresponse: aioresponses, mock_auth_token: str
) -> None:
    """Test getting the product list."""
    api_client.auth_token = mock_auth_token

    # Mock the refresh token endpoint to avoid authentication errors
    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/refreshToken/",
        status=200,
        payload={
            "meta": {"code": 0, "message": "success"},
            "data": {"token": mock_auth_token},
        },
    )

    mock_products = [
        {
            "ptype": "test_type",
            "ptypename": "Test Type",
            "pdata": [
                {
                    "productid": "test_product_id",
                    "pname": "Test Product",
                    "brand": "Test Brand",
                    "model": "Test Model",
                    "picture": "picture.png",
                    "picture_v3": "picture_v3.png",
                    "config_guide": "guide",
                    "status": 1,
                    "configType": 1,
                }
            ],
        }
    ]

    mock_response = {
        "meta": {"code": 0, "message": "success"},
        "data": {"result": mock_products},
    }

    mock_aioresponse.get(
        f"{DEYE_API_END_USER_ENDPOINT}/productlist/?app=new",
        status=200,
        payload=mock_response,
    )

    result = await api_client.get_product_list()

    assert result == mock_products


@pytest.mark.asyncio
async def test_get_deye_platform_mqtt_info(
    api_client: DeyeCloudApi, mock_aioresponse: aioresponses, mock_auth_token: str
) -> None:
    """Test getting MQTT info for the Deye platform."""
    api_client.auth_token = mock_auth_token

    # Mock the refresh token endpoint to avoid authentication errors
    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/refreshToken/",
        status=200,
        payload={
            "meta": {"code": 0, "message": "success"},
            "data": {"token": mock_auth_token},
        },
    )

    mock_mqtt_info = {
        "password": "test_password",
        "loginname": "test_login",
        "mqtthost": "test.mqtt.host",
        "mqttport": 1883,
        "clientid": "test_client_id",
        "endpoint": "test_endpoint",
        "sslport": 8883,
    }

    mock_response = {
        "meta": {"code": 0, "message": "success"},
        "data": mock_mqtt_info,
    }

    mock_aioresponse.get(
        f"{DEYE_API_END_USER_ENDPOINT}/mqttInfo/",
        status=200,
        payload=mock_response,
    )

    result = await api_client.get_deye_platform_mqtt_info()

    assert result == mock_mqtt_info


@pytest.mark.asyncio
async def test_get_fog_platform_mqtt_info(
    api_client: DeyeCloudApi, mock_aioresponse: aioresponses, mock_auth_token: str
) -> None:
    """Test getting MQTT info for the Fog platform."""
    api_client.auth_token = mock_auth_token

    # Mock the refresh token endpoint to avoid authentication errors
    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/refreshToken/",
        status=200,
        payload={
            "meta": {"code": 0, "message": "success"},
            "data": {"token": mock_auth_token},
        },
    )

    mock_mqtt_info = {
        "username": "test_username",
        "clientid": "test_client_id",
        "password": "test_password",
        "mqtt_host": "test.mqtt.host",
        "ws_port": "8083",
        "ssl_port": "8883",
        "topic": {
            "all": ["topic1", "topic2"],
            "pub": ["topic1"],
            "sub": ["topic2"],
        },
        "expire": 3600,
    }

    mock_response = {
        "meta": {"code": 0, "message": "success"},
        "data": mock_mqtt_info,
    }

    mock_aioresponse.get(
        f"{DEYE_API_END_USER_ENDPOINT}/fogmqttinfo/",
        status=200,
        payload=mock_response,
    )

    result = await api_client.get_fog_platform_mqtt_info()

    assert result == mock_mqtt_info


@pytest.mark.asyncio
async def test_get_fog_platform_device_properties(
    api_client: DeyeCloudApi, mock_aioresponse: aioresponses, mock_auth_token: str
) -> None:
    """Test getting device properties for the Fog platform."""
    api_client.auth_token = mock_auth_token
    device_id = "test_device_id"

    # Mock the refresh token endpoint to avoid authentication errors
    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/refreshToken/",
        status=200,
        payload={
            "meta": {"code": 0, "message": "success"},
            "data": {"token": mock_auth_token},
        },
    )

    mock_properties = {
        "CompressorStatus": 0,
        "CurrentAmbientTemperature": 25,
        "CurrentCoilTemperature": 20,
        "CurrentEnvironmentalHumidity": 50,
        "CurrentExhaustTemperature": 30,
        "Demisting": 0,
        "EnvironmentalRating": 0,
        "Fan": 1,
        "KeyLock": 0,
        "Mode": 0,
        "NegativeIon": 0,
        "Power": 1,
        "ProtocolVersion": 1,
        "SetHumidity": 45,
        "SolenoidValve": 0,
        "SwingingWind": 0,
        "TimedOff": 0,
        "TimedOn": 0,
        "TimedShutdownHourSetting": 0,
        "TimedShutdownMinuteSettingTime": 0,
        "TimedShutdownTimeRemainingHours": 0,
        "TimedShutdownTimeRemainingMinutes": 0,
        "TimedStartupHoursSetTime": 0,
        "TimedStartupMinuteSettingTime": 0,
        "TimedStartupTimeRemainingHours": 0,
        "TimedStartupTimeRemainingMinutes": 0,
        "WaterPump": 0,
        "WaterTank": 0,
        "WindSpeed": 1,
        "fault": {},
    }

    mock_response = {
        "meta": {"code": 0, "message": "success"},
        "data": {"properties": mock_properties},
    }

    mock_aioresponse.get(
        f"{DEYE_API_END_USER_ENDPOINT}/get/properties/?device_id={device_id}",
        status=200,
        payload=mock_response,
    )

    result = await api_client.get_fog_platform_device_properties(device_id)

    assert result == mock_properties


@pytest.mark.asyncio
async def test_poll_fog_platform_device_properties(
    api_client: DeyeCloudApi, mock_aioresponse: aioresponses, mock_auth_token: str
) -> None:
    """Test polling device properties for the Fog platform."""
    api_client.auth_token = mock_auth_token
    device_id = "test_device_id"

    # Mock the refresh token endpoint to avoid authentication errors
    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/refreshToken/",
        status=200,
        payload={
            "meta": {"code": 0, "message": "success"},
            "data": {"token": mock_auth_token},
        },
    )

    mock_response = {
        "meta": {"code": 0, "message": "success"},
        "data": {},
    }

    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/set/properties/",
        status=200,
        payload=mock_response,
    )

    await api_client.poll_fog_platform_device_properties(device_id)

    # No assertion needed, just checking that the request was made correctly


@pytest.mark.asyncio
async def test_set_fog_platform_device_properties(
    api_client: DeyeCloudApi, mock_aioresponse: aioresponses, mock_auth_token: str
) -> None:
    """Test setting device properties for the Fog platform."""
    api_client.auth_token = mock_auth_token
    device_id = "test_device_id"
    params = {"Power": 1, "Mode": 0, "WindSpeed": 1}

    # Mock the refresh token endpoint to avoid authentication errors
    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/refreshToken/",
        status=200,
        payload={
            "meta": {"code": 0, "message": "success"},
            "data": {"token": mock_auth_token},
        },
    )

    mock_response = {
        "meta": {"code": 0, "message": "success"},
        "data": {},
    }

    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/set/properties/",
        status=200,
        payload=mock_response,
    )

    await api_client.set_fog_platform_device_properties(device_id, params)

    # No assertion needed, just checking that the request was made correctly


def test_ensure_valid_response_code() -> None:
    """Test the ensure_valid_response_code function."""
    # Test valid response
    valid_response: DeyeApiResponseEnvelope = {
        "meta": {"code": 0, "message": "Success"},
        "data": {},
    }
    ensure_valid_response_code(valid_response)  # Should not raise an exception

    # Test invalid auth response
    invalid_auth_response: DeyeApiResponseEnvelope = {
        "meta": {"code": 401, "message": "Unauthorized"},
        "data": {},
    }
    with pytest.raises(DeyeCloudApiInvalidAuthError):
        ensure_valid_response_code(invalid_auth_response)

    # Test other error response
    other_error_response: DeyeApiResponseEnvelope = {
        "meta": {"code": 500, "message": "Internal Server Error"},
        "data": {},
    }
    with pytest.raises(Exception):
        ensure_valid_response_code(other_error_response)


@pytest.mark.asyncio
async def test_make_authenticated_request_connection_error(
    api_client: DeyeCloudApi, mock_auth_token: str, mock_aioresponse: aioresponses
) -> None:
    """Test making an authenticated request with a connection error."""
    api_client.auth_token = mock_auth_token

    # Mock the refresh token endpoint to avoid authentication errors
    mock_aioresponse.post(
        f"{DEYE_API_END_USER_ENDPOINT}/refreshToken/",
        status=200,
        payload={
            "meta": {"code": 0, "message": "success"},
            "data": {"token": mock_auth_token},
        },
    )

    # Mock the get method to raise a ClientError
    with patch.object(api_client._session, "get") as mock_get:
        mock_get.side_effect = ClientError("Connection error")

        with pytest.raises(DeyeCloudApiCannotConnectError):
            await api_client._make_authenticated_request("GET", "/test/endpoint")

        mock_get.assert_called_once()


@pytest.mark.asyncio
async def test_refresh_token_connection_error(
    api_client: DeyeCloudApi, mock_auth_token: str
) -> None:
    """Test refreshing token with a connection error."""
    # Create a token that expires in 10 seconds
    exp_time = int(time.time()) + 10
    payload = {
        "enduserid": "test_user_id",
        "exp": exp_time,
    }
    token = jwt.encode(payload, "secret", algorithm="HS256")
    api_client.auth_token = token

    # Mock the session.post method to raise a ClientError
    with patch.object(api_client._session, "post") as mock_post:
        mock_post.side_effect = ClientError("Connection error")

        # Should raise DeyeCloudApiCannotConnectError
        with pytest.raises(DeyeCloudApiCannotConnectError):
            await api_client.refresh_token_if_near_expiry()
