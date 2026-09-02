package io.fogcloud.sdk.fog.api.mqtt;

import io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttService;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final /* synthetic */ class UnifiedMqttService$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ UnifiedMqttService f$0;
    public final /* synthetic */ UnifiedMqttService.MqttConnection f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ UnifiedMqttService$$ExternalSyntheticLambda2(UnifiedMqttService unifiedMqttService, UnifiedMqttService.MqttConnection mqttConnection, int i, Runnable runnable) {
        this.f$0 = unifiedMqttService;
        this.f$1 = mqttConnection;
        this.f$2 = i;
        this.f$3 = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.lambda$stopMqttServiceForPlatform$2(this.f$1, this.f$2, this.f$3);
    }
}
