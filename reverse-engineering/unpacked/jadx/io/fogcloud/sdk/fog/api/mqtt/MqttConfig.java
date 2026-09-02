package io.fogcloud.sdk.fog.api.mqtt;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class MqttConfig {
    private Platform platform;
    private boolean enableParamValidation = false;
    private boolean disableHostnameVerification = false;
    private boolean enableDetailedLogging = false;
    private boolean useRandomServiceType = false;

    public enum Platform {
        FOG,
        BAIDU
    }

    private MqttConfig() {
    }

    public static MqttConfig forFog() {
        MqttConfig mqttConfig = new MqttConfig();
        mqttConfig.platform = Platform.FOG;
        mqttConfig.enableParamValidation = true;
        mqttConfig.disableHostnameVerification = true;
        mqttConfig.enableDetailedLogging = true;
        mqttConfig.useRandomServiceType = false;
        return mqttConfig;
    }

    public static MqttConfig forBaidu() {
        MqttConfig mqttConfig = new MqttConfig();
        mqttConfig.platform = Platform.BAIDU;
        return mqttConfig;
    }

    public Platform getPlatform() {
        return this.platform;
    }

    public boolean isEnableParamValidation() {
        return this.enableParamValidation;
    }

    public boolean isDisableHostnameVerification() {
        return this.disableHostnameVerification;
    }

    public boolean isEnableDetailedLogging() {
        return this.enableDetailedLogging;
    }

    public boolean isUseRandomServiceType() {
        return this.useRandomServiceType;
    }

    public String getLogPrefix() {
        return this.platform == Platform.FOG ? StubApp.getString2(44836) : StubApp.getString2(44837);
    }

    public int getPlatformId() {
        return this.platform == Platform.FOG ? 2 : 1;
    }
}
