package io.fogcloud.sdk.fog.api.mqtt.event;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public abstract class MqttEvent {
    private final int platform;
    private final long timestamp = System.currentTimeMillis();

    public MqttEvent(int i) {
        this.platform = i;
    }

    public int getPlatform() {
        return this.platform;
    }

    public boolean isFogPlatForm() {
        int i = this.platform;
        return i == 3 || i == 2;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getPlatformName() {
        return this.platform == 2 ? StubApp.getString2(44999) : StubApp.getString2(45000);
    }

    public String toString() {
        return getClass().getSimpleName() + StubApp.getString2(45001) + getPlatformName() + StubApp.getString2(8917) + this.timestamp + '}';
    }
}
