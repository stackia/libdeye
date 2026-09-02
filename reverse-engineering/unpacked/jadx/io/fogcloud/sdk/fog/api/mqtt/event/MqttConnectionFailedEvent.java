package io.fogcloud.sdk.fog.api.mqtt.event;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class MqttConnectionFailedEvent extends MqttEvent {
    private final String reason;

    public MqttConnectionFailedEvent(int i, String str) {
        super(i);
        this.reason = str;
    }

    public String getReason() {
        return this.reason;
    }

    @Override // io.fogcloud.sdk.fog.api.mqtt.event.MqttEvent
    public String toString() {
        return StubApp.getString2(44995) + getPlatformName() + StubApp.getString2(44996) + this.reason + StubApp.getString2(7034);
    }
}
