package io.fogcloud.sdk.fog.api.mqtt.event;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class MqttConnectedEvent extends MqttEvent {
    public MqttConnectedEvent(int i) {
        super(i);
    }

    @Override // io.fogcloud.sdk.fog.api.mqtt.event.MqttEvent
    public String toString() {
        return StubApp.getString2(44994) + getPlatformName() + StubApp.getString2(14593);
    }
}
