package io.fogcloud.sdk.easylink.helper;

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

    public static boolean isInteger(String str) throws NumberFormatException {
        int i;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
        }
        return i > 999 && i < 10000;
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
