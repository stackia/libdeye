package io.fogcloud.sdk.fog.api.mqtt.event;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class MqttAuthenticationFailedEvent extends MqttEvent {
    private final String errorMessage;
    private final int reasonCode;

    public MqttAuthenticationFailedEvent(int i, int i2, String str) {
        super(i);
        this.reasonCode = i2;
        this.errorMessage = str;
    }

    public int getReasonCode() {
        return this.reasonCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override // io.fogcloud.sdk.fog.api.mqtt.event.MqttEvent
    public String toString() {
        return StubApp.getString2(44991) + getPlatformName() + StubApp.getString2(44992) + this.reasonCode + StubApp.getString2(44993) + this.errorMessage + StubApp.getString2(7034);
    }
}
