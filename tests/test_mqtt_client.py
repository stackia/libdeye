"""Tests for the MQTT client module."""

import asyncio
from collections.abc import Callable
import json
from typing import Any, cast, override
from unittest.mock import AsyncMock, MagicMock, patch

import paho.mqtt.client as mqtt
import pytest
import pytest_asyncio

from libdeye.cloud_api import (
    DeyeApiResponseClassicPlatformMqttInfo,
    DeyeApiResponseDeviceInfo,
    DeyeApiResponseFogPlatformDeviceProperties,
    DeyeApiResponseFogPlatformMqttInfo,
    DeyeApiResponseFogPlatformMqttTopics,
    DeyeCloudApi,
    DeyeIotPlatform,
)
from libdeye.const import (
    QUERY_DEVICE_STATE_COMMAND_CLASSIC,
    DeyeDeviceMode,
    DeyeFanSpeed,
)
from libdeye.device_command import DeyeDeviceCommand
from libdeye.device_state import DeyeDeviceState
from libdeye.mqtt_client import (
    BaseDeyeMqttClient,
    DeyeClassicMqttClient,
    DeyeFogComboMqttClient,
    DeyeFogMqttClient,
    mqtt_client_for_device,
    resolve_fog_command_payloads,
    resolve_fog_command_properties,
)


class TestBaseDeyeMqttClient:
    """Tests for the BaseDeyeMqttClient class."""

    class MockBaseDeyeMqttClient(BaseDeyeMqttClient):
        """Mock implementation of BaseDeyeMqttClient for testing."""

        _mqtt_host = "test.mqtt.host"
        _mqtt_ssl_port = 8883

        @override
        async def _set_mqtt_info(self) -> None:
            """Mock implementation of _set_mqtt_info."""

        @override
        def _process_message_payload(self, msg: mqtt.MQTTMessage) -> Any:
            """Mock implementation of _process_message_payload."""
            return json.loads(msg.payload)

        @override
        def subscribe_state_change(
            self,
            product_id: str,
            device_id: str,
            callback: Callable[[DeyeDeviceState], None],
        ) -> Callable[[], None]:
            """Mock implementation of subscribe_state_change."""
            return lambda: None

        @override
        def subscribe_availability_change(
            self,
            product_id: str,
            device_id: str,
            callback: Callable[[bool], None],
        ) -> Callable[[], None]:
            """Mock implementation of subscribe_availability_change."""
            return lambda: None

        @override
        async def publish_command(
            self,
            product_id: str,
            device_id: str,
            command: DeyeDeviceCommand,
            properties: dict[str, int] | None = None,
            baseline: DeyeDeviceState | DeyeDeviceCommand | None = None,
        ) -> None:
            """Mock implementation of publish_command."""

        @override
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
                return TestBaseDeyeMqttClient.MockBaseDeyeMqttClient(cloud_api_mock)

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

        with (
            patch.object(
                classic_client, "subscribe_state_change", side_effect=mock_subscribe
            ),
            patch.object(classic_client, "publish_command") as mock_publish_command,
        ):
            result = await classic_client.query_device_state(product_id, device_id)
            mock_publish_command.assert_called_once_with(
                product_id, device_id, QUERY_DEVICE_STATE_COMMAND_CLASSIC
            )
            assert isinstance(result, DeyeDeviceState)


class TestDeyeFogComboMqttClient:
    """Tests for official FogCombo Classic MQTT frames."""

    @pytest.fixture
    def cloud_api_mock(self) -> MagicMock:
        """Return a mock DeyeCloudApi with Classic MQTT credentials."""
        mock = MagicMock(spec=DeyeCloudApi)
        mock.get_deye_platform_mqtt_info = AsyncMock(
            return_value=cast(
                DeyeApiResponseClassicPlatformMqttInfo,
                {
                    "mqtthost": "test.mqtt.host",
                    "sslport": 8883,
                    "loginname": "test_user",
                    "password": "test_password",
                    "clientid": "test_client_id",
                    "mqttport": 1883,
                    "endpoint": "test-endpoint",
                },
            )
        )
        return mock

    @pytest_asyncio.fixture
    async def combo_client(self, cloud_api_mock: MagicMock) -> DeyeFogComboMqttClient:
        """Return a DeyeFogComboMqttClient instance."""
        with (
            patch("libdeye.mqtt_client.mqtt.Client", return_value=MagicMock()),
            patch(
                "libdeye.mqtt_client.get_running_loop",
                return_value=asyncio.get_running_loop(),
            ),
        ):
            client = DeyeFogComboMqttClient(cloud_api_mock)
            client._endpoint = "test-endpoint"
            return client

    @pytest.mark.asyncio
    async def test_publish_command_sends_official_power_frame(
        self, combo_client: DeyeFogComboMqttClient
    ) -> None:
        """Power uses CommandManger bytes {2, 17, 1, 0/1}."""
        command = DeyeDeviceCommand(power_switch=True)
        with patch.object(combo_client._mqtt, "is_connected", return_value=True):
            with patch.object(combo_client._mqtt, "publish") as mock_publish:
                await combo_client.publish_command(
                    "d71936c6951c11f0a8200242ac480009",
                    "device456",
                    command,
                    properties={"Power": 1},
                )
                mock_publish.assert_called_once()
                topic, payload = mock_publish.call_args[0]
                assert topic.endswith("/command/hex")
                assert payload == bytes([2, 17, 1, 1])

    @pytest.mark.asyncio
    async def test_publish_command_sends_one_frame_per_changed_property(
        self, combo_client: DeyeFogComboMqttClient
    ) -> None:
        """Official Combo sends one 4-byte frame per property, not Fog JSON."""
        baseline = DeyeDeviceCommand(power_switch=True, target_humidity=50)
        command = DeyeDeviceCommand(
            power_switch=True,
            target_humidity=45,
            fan_speed=DeyeFanSpeed.HIGH,
            mode=DeyeDeviceMode.AUTO_MODE,
        )
        with patch.object(combo_client._mqtt, "is_connected", return_value=True):
            with patch.object(combo_client._mqtt, "publish") as mock_publish:
                await combo_client.publish_command(
                    "d71936c6951c11f0a8200242ac480009",
                    "device456",
                    command,
                    baseline=baseline,
                )
                payloads = [
                    call_args[0][1] for call_args in mock_publish.call_args_list
                ]
                assert bytes([2, 17, 9, int(DeyeFanSpeed.HIGH)]) in payloads
                assert bytes([2, 17, 8, int(DeyeDeviceMode.AUTO_MODE)]) in payloads
                assert bytes([2, 17, 10, 45]) in payloads
                assert bytes([2, 17, 1, 1]) not in payloads
                assert mock_publish.call_count == 3

    @pytest.mark.asyncio
    async def test_query_still_uses_classic_poll_bytes(
        self, combo_client: DeyeFogComboMqttClient
    ) -> None:
        """FogCombo query keeps Classic poll bytes 00 01 on the same MQTT topic."""
        with patch.object(combo_client._mqtt, "is_connected", return_value=True):
            with patch.object(combo_client._mqtt, "publish") as mock_publish:
                await combo_client.publish_command(
                    "d71936c6951c11f0a8200242ac480009",
                    "device456",
                    QUERY_DEVICE_STATE_COMMAND_CLASSIC,
                )
                assert (
                    mock_publish.call_args[0][1] == QUERY_DEVICE_STATE_COMMAND_CLASSIC
                )

    @pytest.mark.asyncio
    async def test_empty_baseline_diff_does_not_publish(
        self, combo_client: DeyeFogComboMqttClient
    ) -> None:
        """Skip FogCombo MQTT when the command matches the baseline."""
        command = DeyeDeviceCommand(power_switch=True, target_humidity=50)
        with patch.object(combo_client._mqtt, "is_connected", return_value=True):
            with patch.object(combo_client._mqtt, "publish") as mock_publish:
                await combo_client.publish_command(
                    "d71936c6951c11f0a8200242ac480009",
                    "device456",
                    command,
                    baseline=command,
                )
                mock_publish.assert_not_called()


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
        product_id = "product123"
        device_id = "device456"
        command = DeyeDeviceCommand(power_switch=True)

        await fog_client.publish_command(product_id, device_id, command)
        assert cast(
            MagicMock, fog_client._cloud_api
        ).set_fog_platform_device_properties.call_args[0] == (
            device_id,
            command.to_json(),
        )

    @pytest.mark.asyncio
    async def test_publish_command_with_properties(
        self, fog_client: DeyeFogMqttClient
    ) -> None:
        """Test publish_command can send explicit property updates."""
        product_id = "product123"
        device_id = "device456"
        command = DeyeDeviceCommand(power_switch=True, target_humidity=70)

        await fog_client.publish_command(
            product_id,
            device_id,
            command,
            properties={"SetHumidity": 70},
        )
        cast(
            MagicMock, fog_client._cloud_api
        ).set_fog_platform_device_properties.assert_awaited_once_with(
            device_id, {"SetHumidity": 70}
        )

    @pytest.mark.asyncio
    async def test_query_device_state(self, fog_client: DeyeFogMqttClient) -> None:
        """Test query_device_state method."""
        product_id = "product123"
        device_id = "device456"

        result = await fog_client.query_device_state(product_id, device_id)
        assert cast(
            MagicMock, fog_client._cloud_api
        ).get_fog_platform_device_properties.called
        assert cast(
            MagicMock, fog_client._cloud_api
        ).get_fog_platform_device_properties.call_args[0] == (device_id,)
        assert isinstance(result, DeyeDeviceState)

    @pytest.mark.asyncio
    async def test_publish_command_keeps_partial_updates(
        self, fog_client: DeyeFogMqttClient
    ) -> None:
        """Fog products send caller-supplied diffs when ProtocolVersion is not 0."""
        command = DeyeDeviceCommand(power_switch=True, target_humidity=45)

        await fog_client.publish_command(
            "c2c2d92c049f11e8829100163e0f811e",
            "device456",
            command,
            properties={"SetHumidity": 45},
        )

        cast(
            MagicMock, fog_client._cloud_api
        ).set_fog_platform_device_properties.assert_awaited_once_with(
            "device456", {"SetHumidity": 45}
        )

    @pytest.mark.asyncio
    async def test_publish_command_builds_diff_from_baseline(
        self, fog_client: DeyeFogMqttClient
    ) -> None:
        """Callers can pass a baseline and let libdeye compute the Fog diff."""
        baseline = DeyeDeviceCommand(power_switch=True, target_humidity=50)
        command = DeyeDeviceCommand(power_switch=True, target_humidity=45)

        await fog_client.publish_command(
            "c2c2d92c049f11e8829100163e0f811e",
            "device456",
            command,
            baseline=baseline,
        )

        cast(
            MagicMock, fog_client._cloud_api
        ).set_fog_platform_device_properties.assert_awaited_once_with(
            "device456", {"SetHumidity": 45}
        )

    @pytest.mark.asyncio
    async def test_publish_command_skips_empty_baseline_diff(
        self, fog_client: DeyeFogMqttClient
    ) -> None:
        """An unchanged baseline should not publish an empty Fog update."""
        command = DeyeDeviceCommand(power_switch=True, target_humidity=50)

        await fog_client.publish_command(
            "c2c2d92c049f11e8829100163e0f811e",
            "device456",
            command,
            baseline=command,
        )

        cast(
            MagicMock, fog_client._cloud_api
        ).set_fog_platform_device_properties.assert_not_awaited()

    @pytest.mark.asyncio
    async def test_publish_command_sends_official_v0_companions(
        self, fog_client: DeyeFogMqttClient
    ) -> None:
        """ProtocolVersion 0 uses sendHumidityCommand companions, not a key union."""
        command = DeyeDeviceCommand(
            power_switch=True,
            target_humidity=45,
            oscillating_switch=True,
            sleep_switch=True,
        )
        fog_client._fog_protocol_versions["device456"] = 0
        fog_client._fog_last_properties["device456"] = {
            "Power": 0,
            "SetHumidity": 50,
            "Sleep": 1,
            "UV": 0,
            "SwingingWind": 1,
            "PromptSound": 1,
            "Screendisplay": 1,
            "TimedShutdownHourSetting": 2,
        }

        await fog_client.publish_command(
            "c2c2d92c049f11e8829100163e0f811e",
            "device456",
            command,
            properties={"SetHumidity": 45},
        )

        published = cast(
            MagicMock, fog_client._cloud_api
        ).set_fog_platform_device_properties.await_args.args[1]
        assert published["SetHumidity"] == 45
        assert published["Power"] == 1
        assert published["UV"] == 0
        assert published["TimedOffHour"] == 2
        assert published["SwingingWind"] == 1
        assert "Sleep" not in published
        assert "PromptSound" not in published
        assert "Screendisplay" not in published

    @pytest.mark.asyncio
    async def test_query_device_state_caches_protocol_version(
        self, fog_client: DeyeFogMqttClient
    ) -> None:
        """GET properties stores ProtocolVersion for later checkNeedAll."""
        cast(
            MagicMock, fog_client._cloud_api
        ).get_fog_platform_device_properties.return_value = cast(
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
                "ProtocolVersion": 0,
            },
        )
        await fog_client.query_device_state("product123", "device456")
        assert fog_client._fog_protocol_versions["device456"] == 0
        assert fog_client._fog_last_properties["device456"]["SetHumidity"] == 50


def test_resolve_fog_command_properties_generic_keeps_partial() -> None:
    """Caller-supplied partial updates are kept when ProtocolVersion is not 0."""
    command = DeyeDeviceCommand(power_switch=True, target_humidity=40)
    properties = resolve_fog_command_properties(
        command,
        properties={"SetHumidity": 40},
    )
    assert properties == {"SetHumidity": 40}


def test_resolve_fog_command_properties_protocol_version_zero_skips_unchanged() -> None:
    """ProtocolVersion == 0 still skips a publish when nothing changed."""
    command = DeyeDeviceCommand(power_switch=True, target_humidity=40)
    properties = resolve_fog_command_properties(
        command,
        baseline=command,
        protocol_version=0,
    )
    assert properties == {}


def test_resolve_fog_command_properties_protocol_version_zero_without_cache() -> None:
    """Missing cache makes checkNeedAll false, so only the changed key is sent."""
    command = DeyeDeviceCommand(
        power_switch=True, target_humidity=40, anion_switch=True
    )
    properties = resolve_fog_command_properties(
        command,
        properties={"SetHumidity": 40},
        protocol_version=0,
    )
    assert properties == {"SetHumidity": 40}


def test_resolve_fog_command_properties_protocol_version_zero_uses_command_companions() -> (
    None
):
    """ProtocolVersion == 0 humidity snapshot omits Sleep and PromptSound."""
    command = DeyeDeviceCommand(
        power_switch=True,
        target_humidity=40,
        oscillating_switch=False,
        sleep_switch=True,
        prompt_sound=True,
        screen_display=True,
    )
    properties = resolve_fog_command_properties(
        command,
        properties={"SetHumidity": 40},
        protocol_version=0,
        last_properties={
            "Power": 0,
            "Mode": 2,
            "WindSpeed": 3,
            "SetHumidity": 55,
            "KeyLock": 0,
            "NegativeIon": 1,
            "SwingingWind": 0,
            "WaterPump": 0,
            "Sleep": "1",
            "UV": "0",
            "SetTemperature": "26",
            "PromptSound": "1",
            "Screendisplay": "1",
            "TimedShutdownHourSetting": "3",
        },
    )
    assert properties["Power"] == 1
    assert properties["SetHumidity"] == 40
    assert properties["UV"] == 0
    assert properties["TimedOffHour"] == 3
    assert properties["SwingingWind"] == 0
    assert "Sleep" not in properties
    assert "SetTemperature" not in properties
    assert "PromptSound" not in properties
    assert "Screendisplay" not in properties
    assert "TimedShutdownHourSetting" not in properties


def test_resolve_fog_command_payloads_power_omits_swinging_wind() -> None:
    """Official sendPowerCommand does not include SwingingWind."""
    command = DeyeDeviceCommand(power_switch=True, oscillating_switch=True)
    payloads = resolve_fog_command_payloads(
        command,
        properties={"Power": 1},
        protocol_version=0,
        last_properties={
            "Power": 0,
            "SwingingWind": 1,
            "Mode": 0,
            "WindSpeed": 1,
            "SetHumidity": 50,
            "KeyLock": 0,
            "NegativeIon": 0,
            "WaterPump": 0,
            "Sleep": 1,
            "UV": 0,
        },
    )
    assert len(payloads) == 1
    assert payloads[0]["Power"] == 1
    assert payloads[0]["Sleep"] == 1
    assert payloads[0]["UV"] == 0
    assert "SwingingWind" not in payloads[0]


def test_resolve_fog_command_payloads_display_tone_timer_are_single_keys() -> None:
    """Display, tone, and timer commands never use checkNeedAll companions."""
    command = DeyeDeviceCommand(
        screen_display=True, prompt_sound=False, timed_off_hour=3
    )
    last = {
        "Power": 1,
        "Mode": 0,
        "WindSpeed": 1,
        "SetHumidity": 50,
        "Sleep": 1,
        "UV": 1,
        "Screendisplay": 0,
        "PromptSound": 1,
        "TimedOffHour": 0,
    }
    assert resolve_fog_command_payloads(
        command,
        properties={"Screendisplay": 1},
        protocol_version=0,
        last_properties=last,
    ) == [{"Screendisplay": 1}]
    assert resolve_fog_command_payloads(
        command,
        properties={"PromptSound": 0},
        protocol_version=0,
        last_properties=last,
    ) == [{"PromptSound": 0}]
    assert resolve_fog_command_payloads(
        command,
        properties={"TimedOffHour": 3},
        protocol_version=0,
        last_properties=last,
    ) == [{"TimedOffHour": 3}]


def test_resolve_fog_command_payloads_batches_into_official_posts() -> None:
    """Each changed key is one FogDeviceManager command POST."""
    baseline = DeyeDeviceCommand(power_switch=False, target_humidity=50)
    command = DeyeDeviceCommand(power_switch=True, target_humidity=40)
    payloads = resolve_fog_command_payloads(
        command,
        baseline=baseline,
        protocol_version=0,
        last_properties={
            "Power": 0,
            "SetHumidity": 50,
            "Mode": 0,
            "WindSpeed": 1,
            "KeyLock": 0,
            "NegativeIon": 0,
            "WaterPump": 0,
            "SwingingWind": 0,
            "Sleep": 1,
            "UV": 0,
        },
    )
    assert len(payloads) == 2
    assert payloads[0]["Power"] == 1
    assert "SwingingWind" not in payloads[0]
    assert payloads[1]["SetHumidity"] == 40
    assert "Sleep" not in payloads[1]


def test_resolve_fog_command_payloads_unknown_key_passthrough() -> None:
    """Keys without an official command still post as a single-property body."""
    payloads = resolve_fog_command_payloads(
        DeyeDeviceCommand(),
        properties={"Custom": 9},
        protocol_version=0,
        last_properties={"Power": 1},
    )
    assert payloads == [{"Custom": 9}]


def test_resolve_fog_command_properties_protocol_version_nonzero_keeps_partial() -> (
    None
):
    """ProtocolVersion != 0 keeps a caller-supplied partial Fog update."""
    command = DeyeDeviceCommand(power_switch=True, target_humidity=40)
    properties = resolve_fog_command_properties(
        command,
        properties={"SetHumidity": 40},
        protocol_version=1,
    )
    assert properties == {"SetHumidity": 40}


def _device_info(
    platform: int,
    *,
    is_combo: bool = False,
    protocol_version: str = "1.0",
) -> DeyeApiResponseDeviceInfo:
    return {
        "producttype_id": 1,
        "device_name": "Test",
        "product_name": "Test",
        "platform": platform,  # type: ignore[typeddict-item]
        "mac": "00:00:00:00:00:00",
        "protocol_version": protocol_version,
        "gatewaytype": 1,
        "is_combo": is_combo,
        "alias": "",
        "deviceid": "id",
        "product_id": "product",
        "role": 1,
        "device_id": "id",
        "product_icon": "",
        "online": True,
        "product_type": "除湿机",
        "payload": {},
        "picture_v3": "",
        "work_time": 0,
        "user_count": 1,
    }


@pytest.mark.asyncio
async def test_mqtt_client_for_device() -> None:
    """Platform 2/3 use Fog HTTP; Combo MQTT is Classic + combo_V1.0."""
    cloud_api = MagicMock(spec=DeyeCloudApi)
    with (
        patch("libdeye.mqtt_client.mqtt.Client", return_value=MagicMock()),
        patch(
            "libdeye.mqtt_client.get_running_loop",
            return_value=asyncio.get_running_loop(),
        ),
    ):
        classic = mqtt_client_for_device(
            _device_info(DeyeIotPlatform.Classic), cloud_api
        )
        fog = mqtt_client_for_device(_device_info(DeyeIotPlatform.Fog), cloud_api)
        platform_3 = mqtt_client_for_device(
            _device_info(DeyeIotPlatform.FogCombo), cloud_api
        )
        combo = mqtt_client_for_device(
            _device_info(
                DeyeIotPlatform.Classic,
                is_combo=True,
                protocol_version="combo_V1.0",
            ),
            cloud_api,
        )
        unknown = mqtt_client_for_device(_device_info(4), cloud_api)

    assert isinstance(classic, DeyeClassicMqttClient)
    assert not isinstance(classic, DeyeFogComboMqttClient)
    assert isinstance(fog, DeyeFogMqttClient)
    assert isinstance(platform_3, DeyeFogMqttClient)
    assert isinstance(combo, DeyeFogComboMqttClient)
    assert isinstance(unknown, DeyeClassicMqttClient)
    assert not isinstance(unknown, DeyeFogComboMqttClient)
