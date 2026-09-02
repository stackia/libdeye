package io.fogcloud.sdk.fog.api.mqtt.sdk.helper;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class ComHelper {
    public static boolean checkPara(String... strArr) {
        if (strArr == null || strArr.equals("") || strArr.length <= 0) {
            return false;
        }
        for (String str : strArr) {
            if (str == null || str.equals("")) {
                return false;
            }
        }
        return true;
    }

    public static void successCBCtrlDev(int i, String str, ListenDeviceCallBack listenDeviceCallBack) {
        if (listenDeviceCallBack == null) {
            return;
        }
        listenDeviceCallBack.onSuccess(i, str);
    }

    public static void failureCBCtrlDev(int i, String str, ListenDeviceCallBack listenDeviceCallBack) {
        if (listenDeviceCallBack == null) {
            return;
        }
        listenDeviceCallBack.onFailure(i, str);
    }

    public static void onDevStatusReceived(int i, String str, ListenDeviceCallBack listenDeviceCallBack) {
        if (listenDeviceCallBack == null) {
            return;
        }
        listenDeviceCallBack.onDeviceStatusReceived(i, str);
    }
}
