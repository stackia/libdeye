"""Shared pytest configuration and compatibility shims."""

import inspect
from typing import Any
from unittest.mock import Mock

import aioresponses.core as aioresponses_core
from aiohttp import ClientResponse


def _client_response_with_stream_writer(*args: Any, **kwargs: Any) -> ClientResponse:
    """Construct an aiohttp 3.14 response for aioresponses 0.7.9."""
    kwargs.setdefault("stream_writer", Mock(output_size=0))
    return ClientResponse(*args, **kwargs)


# Remove this compatibility shim once a release containing
# https://github.com/pnuckowski/aioresponses/pull/288 is available.
if "stream_writer" in inspect.signature(ClientResponse).parameters:
    setattr(
        aioresponses_core,
        "ClientResponse",
        _client_response_with_stream_writer,
    )
