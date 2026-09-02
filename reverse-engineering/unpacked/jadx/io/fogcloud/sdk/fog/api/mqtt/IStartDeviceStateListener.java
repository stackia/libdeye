package io.fogcloud.sdk.fog.api.mqtt;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public interface IStartDeviceStateListener {
    void onDeviceStatusReceived(int i, String str, int i2);

    void onStartDeviceStateFail(int i, String str);

    void onStartDeviceStateSuccess(int i, String str, int i2, IRegisterDeviceTopic... iRegisterDeviceTopicArr);
}
