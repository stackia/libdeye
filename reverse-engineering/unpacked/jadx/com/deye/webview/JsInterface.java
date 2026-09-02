package com.deye.webview;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.deye.entity.UserInfoBean;
import com.deye.utils.MMKVUtils;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class JsInterface {
    private BridgeCallBack callBack;

    interface BridgeCallBack {
        void finishPage(String str);

        void openPage(String str);

        void setPageTitle(String str);
    }

    public JsInterface(BridgeCallBack bridgeCallBack) {
        this.callBack = bridgeCallBack;
    }

    @JavascriptInterface
    public String getUserToken() {
        return MMKV.defaultMMKV().decodeString(StubApp.getString2(13134), null);
    }

    @JavascriptInterface
    public String getUserPhone() {
        UserInfoBean userInfo = MMKVUtils.INSTANCE.getUserInfo();
        if (userInfo != null && userInfo.getPhone() != null) {
            return userInfo.getPhone();
        }
        return "";
    }

    @JavascriptInterface
    public String getLocation() throws JSONException {
        String strDecodeString = MMKV.defaultMMKV().decodeString(StubApp.getString2(14357), "");
        JSONObject jSONObject = new JSONObject();
        boolean zIsEmpty = TextUtils.isEmpty(strDecodeString);
        String string2 = StubApp.getString2(14692);
        if (!zIsEmpty) {
            try {
                jSONObject.put(StubApp.getString2("4611"), new JSONObject(strDecodeString));
                jSONObject.put(string2, StubApp.getString2("2546"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                jSONObject.put(string2, StubApp.getString2("701"));
            } catch (JSONException e2) {
                throw new RuntimeException(e2);
            }
        }
        return jSONObject.toString();
    }

    @JavascriptInterface
    public void setPageTitle(String str) {
        BridgeCallBack bridgeCallBack = this.callBack;
        if (bridgeCallBack != null) {
            bridgeCallBack.setPageTitle(str);
        }
    }

    @JavascriptInterface
    public void finishPage(String str) {
        BridgeCallBack bridgeCallBack = this.callBack;
        if (bridgeCallBack != null) {
            bridgeCallBack.finishPage(str);
        }
    }

    @JavascriptInterface
    public void openPage(String str) {
        BridgeCallBack bridgeCallBack = this.callBack;
        if (bridgeCallBack != null) {
            bridgeCallBack.openPage(str);
        }
    }
}
