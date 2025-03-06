"""Tests for the MQTT client module."""

import asyncio
import json
from typing import Any, Callable, cast
from unittest.mock import AsyncMock, MagicMock, patch

import paho.mqtt.client as mqtt
import pytest
import pytest_asyncio

from libdeye.cloud_api import (
    DeyeApiResponseClassicPlatformMqttInfo,
    DeyeApiResponseFogPlatformDeviceProperties,
    DeyeApiResponseFogPlatformMqttInfo,
    DeyeApiResponseFogPlatformMqttTopics,
    DeyeCloudApi,
)
from libdeye.const import QUERY_DEVICE_STATE_COMMAND_CLASSIC
from libdeye.device_command import DeyeDeviceCommand
from libdeye.device_state import DeyeDeviceState
from libdeye.mqtt_client import (
    BaseDeyeMqttClient,
    DeyeClassicMqttClient,
    DeyeFogMqttClient,
)


class TestBaseDeyeMqttClient:
    """Tests for the BaseDeyeMqttClient class."""

    class MockBaseDeyeMqttClient(BaseDeyeMqttClient):
        """Mock implementation of BaseDeyeMqttClient for testing."""

        _mqtt_host = "test.mqtt.host"
        _mqtt_ssl_port = 8883

        async def _set_mqtt_info(self) -> None:
            """Mock implementation of _set_mqtt_info."""
            pass

        def _process_message_payload(self, msg: mqtt.MQTTMessage) -> Any:
            """Mock implementation of _process_message_payload."""
            return json.loads(msg.payload)

        def subscribe_state_change(
            self,
            product_id: str,
            device_id: str,
            callback: Callable[[DeyeDeviceState], None],
        ) -> Callable[[], None]:
            """Mock implementation of subscribe_state_change."""
            return lambda: None

        def subscribe_availability_change(
            self,
            product_id: str,
            device_id: str,
            callback: Callable[[bool], None],
        ) -> Callable[[], None]:
            """Mock implementation of subscribe_availability_change."""
            return lambda: None

        async def publish_command(
            self, product_id: str, device_id: str, command: DeyeDeviceCommand
        ) -> None:
            """Mock implementation of publish_command."""
            pass

        async def query_device_state(
            self, product_id: str, device_id: str
        ) -> DeyeDeviceState:
            """Mock implementation of query_device_state."""
            return DeyeDeviceState("14118100113B00000000000000000040300000000000")

    @pytest.fixture
    def cloud_api_mock(self) -> MagicMock:
        """Return a mock DeyeCloudApi."""
        return MagicMock(spec=DeyeCloudApi)

    @pytest.fixture
    def mqtt_client_mock(self) -> MagicMock:
        """Return a mock MQTT client."""
        return MagicMock(spec=mqtt.Client)

    @pytest_asyncio.fixture
    async def base_client(self, cloud_api_mock: MagicMock) -> MockBaseDeyeMqttClient:
        """Return a MockBaseDeyeMqttClient instance."""
        with patch("libdeye.mqtt_client.mqtt.Client", return_value=MagicMock()):
            with patch(
                "libdeye.mqtt_client.get_running_loop",
                return_value=asyncio.get_running_loop(),
            ):
                client = TestBaseDeyeMqttClient.MockBaseDeyeMqttClient(cloud_api_mock)
                return client

    @pytest.mark.asyncio
    async def test_connect(self, base_client: MockBaseDeyeMqttClient) -> None:
        """Test connect method."""
        with patch.object(base_client, "_set_mqtt_info") as mock_set_mqtt_info:
            with patch.object(base_client._mqtt, "connect_async") as mock_connect_async:
                with patch.object(base_client._mqtt, "loop_start") as mock_loop_start:
                    await base_client.connect()
                    mock_set_mqtt_info.assert_called_once()
                    mock_connect_async.assert_called_once_with(
                        base_client._mqtt_host, base_client._mqtt_ssl_port
                    )
                    mock_loop_start.assert_called_once()

    def test_disconnect(self, base_client: MockBaseDeyeMqttClient) -> None:
        """Test disconnect method."""
        with patch.object(base_client._mqtt, "disconnect") as mock_disconnect:
            with patch.object(base_client._mqtt, "loop_stop") as mock_loop_stop:
                base_client.disconnect()
                mock_disconnect.assert_called_once()
                mock_loop_stop.assert_called_once()

    def test_mqtt_on_connect(self, base_client: MockBaseDeyeMqttClient) -> None:
        """Test _mqtt_on_connect method."""
        # Setup subscribers
        topic1 = "test/topic1"
        topic2 = "test/topic2"
        callback1 = MagicMock()
        base_client._subscribers = {
            topic1: {callback1},
            topic2: set(),  # Empty set should not trigger subscribe
        }

        # Setup pending commands
        pending_topic = "test/pending"
        pending_command = b"test_command"
        base_client._pending_commands = [(pending_topic, pending_command)]

        # Call _mqtt_on_connect
        with patch.object(base_client._mqtt, "subscribe") as mock_subscribe:
            with patch.object(base_client._mqtt, "publish") as mock_publish:
                base_client._mqtt_on_connect(base_client._mqtt, None, {}, 0, {})
                mock_subscribe.assert_called_once_with(topic1)
                mock_publish.assert_called_once_with(pending_topic, pending_command)
                assert len(base_client._pending_commands) == 0

    def test_mqtt_on_disconnect_user_initiated(
        self, base_client: MockBaseDeyeMqttClient
    ) -> None:
        """Test _mqtt_on_disconnect method with user initiated disconnect."""
        with patch("asyncio.run_coroutine_threadsafe") as mock_run_coroutine_threadsafe:
            base_client._mqtt_on_disconnect(base_client._mqtt, None, 0)
            mock_run_coroutine_threadsafe.assert_not_called()

    def test_mqtt_on_disconnect_unexpected(
        self, base_client: MockBaseDeyeMqttClient
    ) -> None:
        """Test _mqtt_on_disconnect method with unexpected disconnect."""
        with patch("asyncio.run_coroutine_threadsafe") as mock_run_coroutine_threadsafe:
            base_client._mqtt_on_disconnect(base_client._mqtt, None, 1)
            mock_run_coroutine_threadsafe.assert_called_once()

    def test_mqtt_on_message(self, base_client: MockBaseDeyeMqttClient) -> None:
        """Test _mqtt_on_message method."""
        # Setup
        topic = "test/topic"
        callback1 = MagicMock()
        callback2 = MagicMock()
        base_client._subscribers = {topic: {callback1, callback2}}

        # Create a message
        message = MagicMock(spec=mqtt.MQTTMessage)
        message.topic = topic
        message.payload = json.dumps({"test": "data"}).encode()

        # Call _mqtt_on_message
        with patch.object(
            base_client._loop, "call_soon_threadsafe"
        ) as mock_call_soon_threadsafe:
            base_client._mqtt_on_message(base_client._mqtt, None, message)
            assert mock_call_soon_threadsafe.call_count == 2

    def test_mqtt_on_message_unknown_topic(
        self, base_client: MockBaseDeyeMqttClient
    ) -> None:
        """Test _mqtt_on_message method with unknown topic."""
        # Create a message with unknown topic
        message = MagicMock(spec=mqtt.MQTTMessage)
        message.topic = "unknown/topic"

        # Call _mqtt_on_message
        with patch.object(
            base_client._loop, "call_soon_threadsafe"
        ) as mock_call_soon_threadsafe:
            base_client._mqtt_on_message(base_client._mqtt, None, message)
            mock_call_soon_threadsafe.assert_not_called()

    def test_mqtt_on_message_json_error(
        self, base_client: MockBaseDeyeMqttClient
    ) -> None:
        """Test _mqtt_on_message method with JSON error."""
        # Setup
        topic = "test/topic"
        callback = MagicMock()
        base_client._subscribers = {topic: {callback}}

        # Create a message with invalid JSON
        message = MagicMock(spec=mqtt.MQTTMessage)
        message.topic = topic
        message.payload = b"invalid json"

        # Call _mqtt_on_message
        with patch.object(
            base_client._loop, "call_soon_threadsafe"
        ) as mock_call_soon_threadsafe:
            base_client._mqtt_on_message(base_client._mqtt, None, message)
            mock_call_soon_threadsafe.assert_not_called()

    def test_subscribe_topic(self, base_client: MockBaseDeyeMqttClient) -> None:
        """Test _subscribe_topic method."""
        topic = "test/topic"
        callback = MagicMock()

        # Test subscribing to a new topic
        with patch.object(base_client._mqtt, "subscribe") as mock_subscribe:
            with patch.object(base_client._mqtt, "is_connected", return_value=True):
                unsubscribe = base_client._subscribe_topic(topic, callback)
                assert topic in base_client._subscribers
                assert callback in base_client._subscribers[topic]
                mock_subscribe.assert_called_once_with(topic)

        # Test unsubscribing
        with patch.object(base_client._mqtt, "unsubscribe") as mock_unsubscribe:
            with patch.object(base_client._mqtt, "is_connected", return_value=True):
                unsubscribe()
                assert callback not in base_client._subscribers[topic]
                mock_unsubscribe.assert_called_once_with(topic)


class TestDeyeClassicMqttClient:
    """Tests for the DeyeClassicMqttClient class."""

    @pytest.fixture
    def cloud_api_mock(self) -> MagicMock:
        """Return a mock DeyeCloudApi."""
        mock = MagicMock(spec=DeyeCloudApi)
        mock.get_deye_platform_mqtt_info = AsyncMock(
            return_value=cast(
                DeyeApiResponseClassicPlatformMqttInfo,
                {
                    "mqtthost": "test.mqtt.host",
                    "sslport": 8883,
                    "loginname": "test_user",
                    "password": "test_password",
                    "endpoint": "test_endpoint",
                    "clientid": "test_client_id",
                    "mqttport": 1883,
                },
            )
        )
        return mock

    @pytest_asyncio.fixture
    async def classic_client(self, cloud_api_mock: MagicMock) -> DeyeClassicMqttClient:
        """Return a DeyeClassicMqttClient instance."""
        with patch("libdeye.mqtt_client.mqtt.Client", return_value=MagicMock()):
            with patch(
                "libdeye.mqtt_client.get_running_loop",
                return_value=asyncio.get_running_loop(),
            ):
                client = DeyeClassicMqttClient(cloud_api_mock)
                # Initialize the _endpoint attribute
                client._endpoint = "test_endpoint"
                return client

    @pytest.mark.asyncio
    async def test_set_mqtt_info(self, classic_client: DeyeClassicMqttClient) -> None:
        """Test _set_mqtt_info method."""
        await classic_client._set_mqtt_info()
        assert cast(
            MagicMock, classic_client._cloud_api
        ).get_deye_platform_mqtt_info.called
        assert classic_client._mqtt_host == "test.mqtt.host"
        assert classic_client._mqtt_ssl_port == 8883
        assert classic_client._endpoint == "test_endpoint"
        cast(MagicMock, classic_client._mqtt).username_pw_set.assert_called_once_with(
            "test_user", "test_password"
        )

    def test_process_message_payload(
        self, classic_client: DeyeClassicMqttClient
    ) -> None:
        """Test _process_message_payload method."""
        # Create a message with valid JSON
        message = MagicMock(spec=mqtt.MQTTMessage)
        message.payload = json.dumps({"data": "test_data"}).encode()

        # Call _process_message_payload
        result = classic_client._process_message_payload(message)
        assert result == "test_data"

    def test_get_topic_prefix(self, classic_client: DeyeClassicMqttClient) -> None:
        """Test _get_topic_prefix method."""
        classic_client._endpoint = "test_endpoint"
        result = classic_client._get_topic_prefix("product123", "device456")
        assert result == "test_endpoint/product123/device456"

    def test_subscribe_state_change(
        self, classic_client: DeyeClassicMqttClient
    ) -> None:
        """Test subscribe_state_change method."""
        callback = MagicMock()
        with patch.object(classic_client, "_subscribe_topic") as mock_subscribe_topic:
            classic_client.subscribe_state_change("product123", "device456", callback)
            mock_subscribe_topic.assert_called_once()
            topic = mock_subscribe_topic.call_args[0][0]
            assert "product123" in topic
            assert "device456" in topic
            assert "status/hex" in topic

    def test_subscribe_availability_change(
        self, classic_client: DeyeClassicMqttClient
    ) -> None:
        """Test subscribe_availability_change method."""
        callback = MagicMock()
        with patch.object(classic_client, "_subscribe_topic") as mock_subscribe_topic:
            classic_client.subscribe_availability_change(
                "product123", "device456", callback
            )
            mock_subscribe_topic.assert_called_once()
            topic = mock_subscribe_topic.call_args[0][0]
            assert "product123" in topic
            assert "device456" in topic
            assert "online/json" in topic

    @pytest.mark.asyncio
    async def test_publish_command(self, classic_client: DeyeClassicMqttClient) -> None:
        """Test publish_command method."""
        # Setup
        product_id = "product123"
        device_id = "device456"
        command = MagicMock(spec=DeyeDeviceCommand)
        command.to_bytes.return_value = b"test_command"

        # Test when MQTT is connected
        with patch.object(classic_client._mqtt, "is_connected", return_value=True):
            with patch.object(classic_client._mqtt, "publish") as mock_publish:
                await classic_client.publish_command(product_id, device_id, command)
                mock_publish.assert_called_once()
                topic = mock_publish.call_args[0][0]
                assert "product123" in topic
                assert "device456" in topic
                assert "command/hex" in topic
                assert mock_publish.call_args[0][1] == b"test_command"

        # Test when MQTT is not connected
        with patch.object(classic_client._mqtt, "is_connected", return_value=False):
            await classic_client.publish_command(product_id, device_id, command)
            assert len(classic_client._pending_commands) == 1

    @pytest.mark.asyncio
    async def test_query_device_state(
        self, classic_client: DeyeClassicMqttClient
    ) -> None:
        """Test query_device_state method."""
        # Setup
        product_id = "product123"
        device_id = "device456"
        state = DeyeDeviceState("14118100113B00000000000000000040300000000000")

        # Mock subscribe_state_change to call the callback with the state
        def mock_subscribe(
            prod_id: str, dev_id: str, callback: Callable[[DeyeDeviceState], None]
        ) -> Callable[[], None]:
            callback(state)
            return MagicMock()

        with patch.object(
            classic_client, "subscribe_state_change", side_effect=mock_subscribe
        ):
            with patch.object(
                classic_client, "publish_command"
            ) as mock_publish_command:
                result = await classic_client.query_device_state(product_id, device_id)
                mock_publish_command.assert_called_once_with(
                    product_id, device_id, QUERY_DEVICE_STATE_COMMAND_CLASSIC
                )
                assert isinstance(result, DeyeDeviceState)


class TestDeyeFogMqttClient:
    """Tests for the DeyeFogMqttClient class."""

    @pytest.fixture
    def cloud_api_mock(self) -> MagicMock:
        """Return a mock DeyeCloudApi."""
        mock = MagicMock(spec=DeyeCloudApi)
        mock.get_fog_platform_mqtt_info = AsyncMock(
            return_value=cast(
                DeyeApiResponseFogPlatformMqttInfo,
                {
                    "mqtt_host": "test.mqtt.host",
                    "ssl_port": "8883",
                    "username": "test_user",
                    "password": "test_password",
                    "clientid": "test_client_id",
                    "ws_port": "8083",
                    "expire": 3600,
                    "topic": cast(
                        DeyeApiResponseFogPlatformMqttTopics,
                        {
                            "all": ["topic1", "topic2"],
                            "pub": ["pub_topic"],
                            "sub": ["sub_topic"],
                        },
                    ),
                },
            )
        )
        mock.get_fog_platform_device_properties = AsyncMock(
            return_value=cast(
                DeyeApiResponseFogPlatformDeviceProperties,
                {
                    "Power": 1,
                    "Mode": 0,
                    "WindSpeed": 1,
                    "SetHumidity": 50,
                    "CurrentAmbientTemperature": 25,
                    "CurrentEnvironmentalHumidity": 60,
                    "NegativeIon": 0,
                    "WaterPump": 0,
                    "SwingingWind": 0,
                    "KeyLock": 0,
                    "Demisting": 0,
                    "WaterTank": 0,
                    "Fan": 1,
                    "CurrentCoilTemperature": 25,
                    "CurrentExhaustTemperature": 25,
                },
            )
        )
        mock.set_fog_platform_device_properties = AsyncMock()
        return mock

    @pytest_asyncio.fixture
    async def fog_client(self, cloud_api_mock: MagicMock) -> DeyeFogMqttClient:
        """Return a DeyeFogMqttClient instance."""
        with patch("libdeye.mqtt_client.mqtt.Client", return_value=MagicMock()):
            with patch(
                "libdeye.mqtt_client.get_running_loop",
                return_value=asyncio.get_running_loop(),
            ):
                client = DeyeFogMqttClient(cloud_api_mock)
                # Initialize the _topic attribute
                client._topic = "fogcloud/app/test_user/sub"
                return client

    @pytest.mark.asyncio
    async def test_set_mqtt_info(self, fog_client: DeyeFogMqttClient) -> None:
        """Test _set_mqtt_info method."""
        await fog_client._set_mqtt_info()
        assert cast(MagicMock, fog_client._cloud_api).get_fog_platform_mqtt_info.called
        assert fog_client._mqtt_host == "test.mqtt.host"
        assert fog_client._mqtt_ssl_port == 8883
        assert fog_client._topic == "fogcloud/app/test_user/sub"
        cast(MagicMock, fog_client._mqtt).username_pw_set.assert_called_once_with(
            "test_user", "test_password"
        )

    def test_process_message_payload(self, fog_client: DeyeFogMqttClient) -> None:
        """Test _process_message_payload method."""
        # Create a message with valid JSON
        message = MagicMock(spec=mqtt.MQTTMessage)
        message.payload = json.dumps({"data": {"properties": {"Power": 1}}}).encode()

        # Call _process_message_payload
        result = fog_client._process_message_payload(message)
        assert result == {"data": {"properties": {"Power": 1}}}

    def test_subscribe_state_change(self, fog_client: DeyeFogMqttClient) -> None:
        """Test subscribe_state_change method."""
        callback = MagicMock()
        with patch.object(fog_client, "_subscribe_topic") as mock_subscribe_topic:
            fog_client.subscribe_state_change("product123", "device456", callback)
            mock_subscribe_topic.assert_called_once()
            topic = mock_subscribe_topic.call_args[0][0]
            assert topic == fog_client._topic

    def test_subscribe_availability_change(self, fog_client: DeyeFogMqttClient) -> None:
        """Test subscribe_availability_change method."""
        callback = MagicMock()
        with patch.object(fog_client, "_subscribe_topic") as mock_subscribe_topic:
            fog_client.subscribe_availability_change(
                "product123", "device456", callback
            )
            mock_subscribe_topic.assert_called_once()
            topic = mock_subscribe_topic.call_args[0][0]
            assert topic == fog_client._topic

    @pytest.mark.asyncio
    async def test_publish_command(self, fog_client: DeyeFogMqttClient) -> None:
        """Test publish_command method."""
        # Setup
        product_id = "product123"
        device_id = "device456"
        command = MagicMock(spec=DeyeDeviceCommand)
        command.to_json.return_value = {"Power": 1}

        # Test publish_command
        await fog_client.publish_command(product_id, device_id, command)
        assert cast(
            MagicMock, fog_client._cloud_api
        ).set_fog_platform_device_properties.called
        assert cast(
            MagicMock, fog_client._cloud_api
        ).set_fog_platform_device_properties.call_args[0] == (device_id, {"Power": 1})

    @pytest.mark.asyncio
    async def test_query_device_state(self, fog_client: DeyeFogMqttClient) -> None:
        """Test query_device_state method."""
        # Setup
        product_id = "product123"
        device_id = "device456"

        # Test query_device_state
        result = await fog_client.query_device_state(product_id, device_id)
        assert cast(
            MagicMock, fog_client._cloud_api
        ).get_fog_platform_device_properties.called
        assert cast(
            MagicMock, fog_client._cloud_api
        ).get_fog_platform_device_properties.call_args[0] == (device_id,)
        assert isinstance(result, DeyeDeviceState)
