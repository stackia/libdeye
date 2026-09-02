package com.deye.utils;

import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager;
import io.fogcloud.sdk.fog.callback.ControlDeviceCallBack;
import io.fogcloud.sdk.fog.helper.AppHexCharHelper;
import io.fogcloud.sdk.fog.helper.CommandPara;
import io.fogcloud.sdk.fog.log.LogUtil;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class DeviceStateChangeTrigger {
    private static volatile DeviceStateChangeTrigger sDeviceStateChangeTrigger;

    private DeviceStateChangeTrigger() {
    }

    public static DeviceStateChangeTrigger getInstance() {
        if (sDeviceStateChangeTrigger == null) {
            synchronized (DeviceStateChangeTrigger.class) {
                if (sDeviceStateChangeTrigger == null) {
                    sDeviceStateChangeTrigger = new DeviceStateChangeTrigger();
                }
            }
        }
        return sDeviceStateChangeTrigger;
    }

    public void deviceStatusChangeTrigger(int i, String str, String str2, String str3, String str4) {
        deviceStatusChangeTrigger(i, false, str, str2, str3, str4);
    }

    public void deviceStatusChangeTrigger(int i, boolean z, String str, String str2, String str3, String str4) {
        CommandPara commandPara = new CommandPara();
        commandPara.command = AppHexCharHelper.DEVICE_STATE_CHANGE_COMMAND;
        commandPara.deviceid = str2;
        commandPara.endpoint = str3;
        commandPara.productid = str4;
        LogUtil.d(StubApp.getString2(14309) + str2 + StubApp.getString2(14310) + str3 + StubApp.getString2(14311) + str4 + StubApp.getString2(14312) + commandPara.command);
        try {
            DeYeMqttManager.getInstance().sendMsg(z, Long.parseLong(StringUtils.getTime()), str, commandPara, new ControlDeviceCallBack() { // from class: com.deye.utils.DeviceStateChangeTrigger.1
                @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
                public void onSuccess(String str5) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
