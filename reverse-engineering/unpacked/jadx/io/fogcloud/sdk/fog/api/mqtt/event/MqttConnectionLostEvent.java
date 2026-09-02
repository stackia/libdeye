package io.fogcloud.sdk.fog.api.mqtt.event;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class MqttConnectionLostEvent extends MqttEvent {
    private final Throwable cause;

    public MqttConnectionLostEvent(int i, Throwable th) {
        super(i);
        this.cause = th;
    }

    public Throwable getCause() {
        return this.cause;
    }

    public String getCauseMessage() {
        Throwable th = this.cause;
        return th != null ? th.getMessage() : StubApp.getString2(805);
    }

    @Override // io.fogcloud.sdk.fog.api.mqtt.event.MqttEvent
    public String toString() {
        return StubApp.getString2(44997) + getPlatformName() + StubApp.getString2(44998) + getCauseMessage() + '}';
    }
}
