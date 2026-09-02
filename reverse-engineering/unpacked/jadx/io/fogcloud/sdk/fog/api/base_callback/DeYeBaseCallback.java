package io.fogcloud.sdk.fog.api.base_callback;

import io.fogcloud.sdk.easylink.helper.EasyLinkCallBack;
import io.fogcloud.sdk.fog.api.mqtt.sdk.helper.MQTTErrCode;
import io.fogcloud.sdk.fog.callback.ControlDeviceCallBack;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import io.fogcloud.sdk.fog.callback.ManageDeviceCallBack;
import io.fogcloud.sdk.fog.callback.SinSocketCallBack;
import io.fogcloud.sdk.fog.helper.MiCOConstParam;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class DeYeBaseCallback {
    protected boolean checkPara(String... strArr) {
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

    protected void illegalCallBack(FogCallBack fogCallBack) {
        failureCBFilterUser(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, fogCallBack);
    }

    protected void succeesCBFilterUser(String str, FogCallBack fogCallBack) {
        if (fogCallBack == null) {
            return;
        }
        fogCallBack.onSuccess(str);
    }

    protected void failureCBFilterUser(int i, String str, FogCallBack fogCallBack) {
        if (fogCallBack == null) {
            return;
        }
        fogCallBack.onFailure(i, str);
    }

    protected void successCBShareQrCode(String str, ManageDeviceCallBack manageDeviceCallBack) {
        if (manageDeviceCallBack == null) {
            return;
        }
        manageDeviceCallBack.onSuccess(str);
    }

    protected void failureCBShareQrCode(int i, String str, ManageDeviceCallBack manageDeviceCallBack) {
        if (manageDeviceCallBack == null) {
            return;
        }
        manageDeviceCallBack.onFailure(i, str);
    }

    protected void successCBBindDev(String str, ManageDeviceCallBack manageDeviceCallBack) {
        if (manageDeviceCallBack == null) {
            return;
        }
        manageDeviceCallBack.onSuccess(str);
    }

    protected void failureCBBindDev(int i, String str, ManageDeviceCallBack manageDeviceCallBack) {
        if (manageDeviceCallBack == null) {
            return;
        }
        manageDeviceCallBack.onFailure(i, str);
    }

    protected void successCBCtrlDev(String str, ControlDeviceCallBack controlDeviceCallBack) {
        if (controlDeviceCallBack == null) {
            return;
        }
        controlDeviceCallBack.onSuccess(str);
    }

    protected void failureCBCtrlDev(int i, String str, ControlDeviceCallBack controlDeviceCallBack) {
        if (controlDeviceCallBack == null) {
            return;
        }
        controlDeviceCallBack.onFailure(i, str);
    }

    protected void onDevStatusReceived(int i, String str, ControlDeviceCallBack controlDeviceCallBack) {
        if (controlDeviceCallBack == null || i == MQTTErrCode._PUB_CODE) {
            return;
        }
        controlDeviceCallBack.onDeviceStatusReceived(i, str);
    }

    protected void successCBLocalCtrl(String str, SinSocketCallBack sinSocketCallBack) {
        if (sinSocketCallBack == null) {
            return;
        }
        sinSocketCallBack.onSuccess(str);
    }

    protected void failureCBLocalCtrl(int i, String str, SinSocketCallBack sinSocketCallBack) {
        if (sinSocketCallBack == null) {
            return;
        }
        sinSocketCallBack.onFailure(i, str);
    }

    protected void lostCBLocalCtrl(SinSocketCallBack sinSocketCallBack) {
        if (sinSocketCallBack == null) {
            return;
        }
        sinSocketCallBack.onLost();
    }

    protected void msgReadCBLocalCtrl(String str, SinSocketCallBack sinSocketCallBack) {
        if (sinSocketCallBack == null) {
            return;
        }
        sinSocketCallBack.onMessageRead(str);
    }

    public void successCBEasyLink(int i, String str, EasyLinkCallBack easyLinkCallBack) {
        if (easyLinkCallBack == null) {
            return;
        }
        easyLinkCallBack.onSuccess(i, str);
    }

    public void failureCBEasyLink(int i, String str, EasyLinkCallBack easyLinkCallBack) {
        if (easyLinkCallBack == null) {
            return;
        }
        easyLinkCallBack.onFailure(i, str);
    }
}
