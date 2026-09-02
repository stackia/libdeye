package io.fogcloud.sdk.fog.api.mqtt;

import io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttService;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final /* synthetic */ class UnifiedMqttService$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ UnifiedMqttService f$0;
    public final /* synthetic */ UnifiedMqttService.MqttConnection f$1;

    public /* synthetic */ UnifiedMqttService$$ExternalSyntheticLambda3(UnifiedMqttService unifiedMqttService, UnifiedMqttService.MqttConnection mqttConnection) {
        this.f$0 = unifiedMqttService;
        this.f$1 = mqttConnection;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.lambda$scheduleNextReconnectForPlatform$3(this.f$1);
    }
}
