package io.fogcloud.sdk.fog.callback;

import io.fogcloud.sdk.fog.api.mqtt.IRegisterDeviceTopic;
import io.fogcloud.sdk.fog.api.mqtt.IStartDeviceStateListener;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public abstract class ControlDeviceCallBack {
    protected IRegisterDeviceTopic iRegisterDeviceTopic;
    protected IStartDeviceStateListener iStartDeviceStateListener;

    public void onDeviceStatusReceived(int i, String str) {
    }

    public void onFailure(int i, String str) {
    }

    public void onSuccess(String str) {
    }

    public void setStartDeviceStateListener(IStartDeviceStateListener iStartDeviceStateListener) {
        this.iStartDeviceStateListener = iStartDeviceStateListener;
    }

    public void setRegisterDeviceTopic(IRegisterDeviceTopic iRegisterDeviceTopic) {
        this.iRegisterDeviceTopic = iRegisterDeviceTopic;
    }
}
