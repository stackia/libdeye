package io.fogcloud.sdk.fog.api.http;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.stub.StubApp;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import io.fogcloud.sdk.fog.BaseApp;
import io.fogcloud.sdk.fog.callback.ControlDeviceCallBack;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import io.fogcloud.sdk.fog.callback.ManageDeviceCallBack;
import io.fogcloud.sdk.fog.helper.Configuration;
import io.fogcloud.sdk.fog.helper.MiCOConstParam;
import io.fogcloud.sdk.fog.helper.ScheduleTaskParam;
import io.fogcloud.sdk.fog.helper.ShareDeviceParams;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.io.File;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class DeYeHttpRequestManager extends DeYeBaseHttpRequest {
    public static final MediaType JSON_Type = MediaType.parse(StubApp.getString2(13940));
    private static final String TAG = StubApp.getString2(44741);
    private static volatile DeYeHttpRequestManager sDeYeHttpRequestManager = null;

    private DeYeHttpRequestManager() {
    }

    public static class NetworkRequestPara {
        private boolean isBluetooth = false;
        private String mDeviceId;
        private String mExtend;
        private ManageDeviceCallBack mManageDeviceCallBack;

        public static class Builder {
            private NetworkRequestPara mNetworkRequestPara = new NetworkRequestPara();

            public Builder setIsBluetooth(boolean z) {
                this.mNetworkRequestPara.isBluetooth = z;
                return this;
            }

            public Builder setManageDeviceCallBack(ManageDeviceCallBack manageDeviceCallBack) {
                this.mNetworkRequestPara.mManageDeviceCallBack = manageDeviceCallBack;
                return this;
            }

            public Builder setDeviceId(String str) {
                this.mNetworkRequestPara.mDeviceId = str;
                return this;
            }

            public Builder setExtend(String str) {
                this.mNetworkRequestPara.mExtend = str;
                return this;
            }

            public NetworkRequestPara build() {
                if (TextUtils.isEmpty(this.mNetworkRequestPara.mDeviceId)) {
                    throw new IllegalStateException(StubApp.getString2(44740));
                }
                return this.mNetworkRequestPara;
            }
        }
    }

    public static DeYeHttpRequestManager getInstance() {
        if (sDeYeHttpRequestManager == null) {
            synchronized (DeYeHttpRequestManager.class) {
                if (sDeYeHttpRequestManager == null) {
                    sDeYeHttpRequestManager = new DeYeHttpRequestManager();
                }
            }
        }
        return sDeYeHttpRequestManager;
    }

    public static void init(String str) {
        Configuration._APIHOST = str;
    }

    @Override // io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest
    public void setPushClientId(String str) {
        super.setPushClientId(str);
    }

    @Override // io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest
    public void setToken(String str) {
        LogUtil.e(StubApp.getString2(44769), str);
        BaseApp.getInstance().setToken(str);
        super.setToken(str);
    }

    @Override // io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest
    public String getToken() {
        if (super.getToken() == null || "".equals(super.getToken())) {
            String token = BaseApp.getInstance().getToken();
            Log.d(TAG, StubApp.getString2(44761) + token);
            if ("".equals(token)) {
                Toast.makeText((Context) BaseApp.getInstance(), (CharSequence) StubApp.getString2(44762), 1).show();
                startActivityForLogin();
            } else {
                super.setToken(token);
            }
        }
        return super.getToken();
    }

    public void startActivityForLogin() {
        ComponentName componentName = new ComponentName(StubApp.getString2(14154), StubApp.getString2(44772));
        Intent intent = new Intent();
        intent.addFlags(CommonNetImpl.FLAG_AUTH);
        intent.addFlags(32768);
        intent.setComponent(componentName);
        try {
            BaseApp.getInstance().startActivity(intent);
        } catch (Exception e) {
            Log.d(TAG, StubApp.getString2(44773) + e);
        }
    }

    public void getVerifyCode(String str, String str2, FogCallBack fogCallBack) {
        if (checkPara(str, str2)) {
            doHttpPost(Configuration.GETVERCODE(), new FormBody.Builder().add(StubApp.getString2(44745), str).add(StubApp.getString2(5433), str2).build(), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void checkVerifyCode(String str, String str2, String str3, FogCallBack fogCallBack) {
        if (checkPara(str, str2, str3)) {
            doHttpPost(Configuration.CHECKVERCODE(), new FormBody.Builder().add(StubApp.getString2(44745), str).add(StubApp.getString2(13467), str2).add(StubApp.getString2(5433), str3).build(), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void setPassword(String str, FogCallBack fogCallBack) {
        if (checkPara(str, getToken())) {
            doHttpPost(Configuration.RESETPASSWORD(), new FormBody.Builder().add(StubApp.getString2(44766), str).add(StubApp.getString2(44767), str).build(), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void login(String str, String str2, String str3, FogCallBack fogCallBack) {
        login(str, str2, str3, "", fogCallBack);
    }

    public void logout(FogCallBack fogCallBack) {
        if (checkPara(getToken())) {
            try {
                doHttpPut(Configuration.LOGOUT(), RequestBody.create(JSON_Type, new JSONObject().toString()), fogCallBack);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        fogIllegalCallBack(fogCallBack);
    }

    public void login(String str, String str2, String str3, String str4, FogCallBack fogCallBack) {
        if (checkPara(str, str2, str3)) {
            doHttpPost(Configuration.LOGININ(), new FormBody.Builder().add(StubApp.getString2(44745), str).add(StubApp.getString2(13687), str2).add(StubApp.getString2(5433), str3).add(StubApp.getString2(7682), str4).add(StubApp.getString2(44764), StubApp.getString2(13695)).build(), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void refreshToken(String str, FogCallBack fogCallBack) {
        if (checkPara(str)) {
            doHttpPost(Configuration.REFRESHTOKEN(), new FormBody.Builder().add(StubApp.getString2(4716), str).build(), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void checkToken(String str, FogCallBack fogCallBack) {
        if (checkPara(str)) {
            doHttpPost(Configuration.VERIFYTOKEN(), new FormBody.Builder().add(StubApp.getString2(4716), str).build(), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void resetPassword(String str, FogCallBack fogCallBack) {
        if (checkPara(str, getToken())) {
            doHttpPost(Configuration.RESETPASSWORD(), new FormBody.Builder().add(StubApp.getString2(44766), str).add(StubApp.getString2(44767), str).build(), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void getShareVerCode(String str, int i, final ManageDeviceCallBack manageDeviceCallBack) {
        if (checkPara(str, String.valueOf(i), getToken())) {
            doHttpPost(Configuration.GETSHAREVERCODE(), new FormBody.Builder().add(StubApp.getString2(4732), str).add(StubApp.getString2(18817), String.valueOf(i)).build(), new FogCallBack() { // from class: io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager.1
                @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                public void onSuccess(String str2) throws JSONException {
                    DeYeHttpRequestManager.this.successCBShareQrCode(str2, manageDeviceCallBack);
                    try {
                        if (!new JSONObject(new JSONObject(str2).getString(StubApp.getString2("13082"))).getString(StubApp.getString2("109")).equals(StubApp.getString2("701"))) {
                            DeYeHttpRequestManager.this.failureCBShareQrCode(MiCOConstParam.EXCEPTIONCODE, str2, manageDeviceCallBack);
                        } else {
                            DeYeHttpRequestManager.this.successCBShareQrCode(new JSONObject(new JSONObject(str2).getString(StubApp.getString2("100"))).getString(StubApp.getString2("13467")), manageDeviceCallBack);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                public void onFailure(int i2, String str2) {
                    DeYeHttpRequestManager.this.failureCBShareQrCode(i2, str2, manageDeviceCallBack);
                }
            });
        } else {
            failureCBShareQrCode(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, manageDeviceCallBack);
        }
    }

    public void addDeviceByVerCode(ShareDeviceParams shareDeviceParams, final ManageDeviceCallBack manageDeviceCallBack) throws JSONException {
        if (checkPara(shareDeviceParams.bindvercode, shareDeviceParams.deviceid, getToken())) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(StubApp.getString2("13467"), shareDeviceParams.bindvercode);
                jSONObject.put(StubApp.getString2("4732"), shareDeviceParams.deviceid);
                jSONObject.put(StubApp.getString2("44742"), shareDeviceParams.bindingtype);
                doHttpPost(Configuration.ADDDEVBYVERCODE(), RequestBody.create(JSON_Type, jSONObject.toString()), new FogCallBack() { // from class: io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager.2
                    @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                    public void onSuccess(String str) {
                        DeYeHttpRequestManager.this.successCBShareQrCode(str, manageDeviceCallBack);
                    }

                    @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                    public void onFailure(int i, String str) {
                        DeYeHttpRequestManager.this.failureCBShareQrCode(i, str, manageDeviceCallBack);
                    }
                });
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        failureCBShareQrCode(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, manageDeviceCallBack);
    }

    public void bindDevice(String str, ManageDeviceCallBack manageDeviceCallBack) throws JSONException {
        if (checkPara(str, getToken())) {
            getBindVerCodeFromDevice(str, manageDeviceCallBack);
        } else {
            failureCBBindDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, manageDeviceCallBack);
        }
    }

    public void bindCheck(String str, FogCallBack fogCallBack) throws JSONException {
        if (checkPara(str, getToken())) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(StubApp.getString2("44743"), str);
                doHttpPost(Configuration.BINDCHECK(), RequestBody.create(JSON_Type, jSONObject.toString()), fogCallBack);
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        illegalCallBack(fogCallBack);
    }

    public void bindDevice(NetworkRequestPara networkRequestPara) throws JSONException {
        if (checkPara(networkRequestPara.mDeviceId, networkRequestPara.mExtend, getToken())) {
            getBindVerCodeFromDevice(networkRequestPara);
        } else {
            failureCBBindDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, networkRequestPara.mManageDeviceCallBack);
        }
    }

    private void getBindVerCodeFromDevice(String str, ManageDeviceCallBack manageDeviceCallBack) throws JSONException {
        toBindingFogCloud(str, manageDeviceCallBack);
    }

    private void getBindVerCodeFromDevice(NetworkRequestPara networkRequestPara) throws JSONException {
        toBindingFogCloud(networkRequestPara);
    }

    private void toBindingFogCloud(String str, ManageDeviceCallBack manageDeviceCallBack) throws JSONException {
        toBindingFogCloud(new NetworkRequestPara.Builder().setDeviceId(str).setExtend("").setIsBluetooth(false).setManageDeviceCallBack(manageDeviceCallBack).build());
    }

    private void toBindingFogCloud(NetworkRequestPara networkRequestPara) throws JSONException {
        String str = networkRequestPara.mDeviceId;
        String str2 = networkRequestPara.mExtend;
        final ManageDeviceCallBack manageDeviceCallBack = networkRequestPara.mManageDeviceCallBack;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(StubApp.getString2("4732"), str);
            if (str2 != "") {
                jSONObject.put(StubApp.getString2("7682"), str2);
            }
            String strBINDDEVICE = Configuration.BINDDEVICE();
            if (networkRequestPara.isBluetooth) {
                strBINDDEVICE = Configuration.BIND_BLUETOOTH_DEVICE();
            }
            doHttpPost(strBINDDEVICE, RequestBody.create(JSON_Type, jSONObject.toString()), new FogCallBack() { // from class: io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager.3
                @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                public void onSuccess(String str3) {
                    DeYeHttpRequestManager.this.successCBBindDev(str3, manageDeviceCallBack);
                }

                @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                public void onFailure(int i, String str3) {
                    DeYeHttpRequestManager.this.failureCBBindDev(i, str3, manageDeviceCallBack);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void unBindDevice(String str, final ManageDeviceCallBack manageDeviceCallBack) throws JSONException {
        if (checkPara(str, getToken())) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(StubApp.getString2("4732"), str);
                doHttpPut(Configuration.UNBINDDEVICE(), RequestBody.create(JSON_Type, jSONObject.toString()), new FogCallBack() { // from class: io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager.4
                    @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                    public void onSuccess(String str2) {
                        DeYeHttpRequestManager.this.successCBBindDev(str2, manageDeviceCallBack);
                    }

                    @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                    public void onFailure(int i, String str2) {
                        DeYeHttpRequestManager.this.failureCBBindDev(i, str2, manageDeviceCallBack);
                    }
                });
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        failureCBBindDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, manageDeviceCallBack);
    }

    public void updateDeviceAlias(String str, String str2, final ManageDeviceCallBack manageDeviceCallBack) throws JSONException {
        if (checkPara(str, str2, getToken())) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(StubApp.getString2("4732"), str);
                jSONObject.put(StubApp.getString2("6888"), str2);
                doHttpPut(Configuration.UPDATEDEVALIAS(), RequestBody.create(JSON_Type, jSONObject.toString()), new FogCallBack() { // from class: io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager.5
                    @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                    public void onSuccess(String str3) {
                        DeYeHttpRequestManager.this.successCBBindDev(str3, manageDeviceCallBack);
                    }

                    @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                    public void onFailure(int i, String str3) {
                        DeYeHttpRequestManager.this.failureCBBindDev(i, str3, manageDeviceCallBack);
                    }
                });
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        failureCBBindDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, manageDeviceCallBack);
    }

    public void getMqttInfo(FogCallBack fogCallBack) {
        if (checkPara(getToken())) {
            new FormBody.Builder().build();
            doHttpGet(Configuration.GETMQTTINFO(), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void getFogmqttinfo(FogCallBack fogCallBack) {
        if (checkPara(getToken())) {
            doHttpGet(Configuration.FOGMQTTINFO() + (StubApp.getString2(566) + Math.random() + StubApp.getString2(44749)), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void getFogProperties(String str, FogCallBack fogCallBack) {
        if (checkPara(str, getToken())) {
            doHttpGet(Configuration.GETPROPERTIES() + (StubApp.getString2(44750) + str + StubApp.getString2(844) + Math.random()), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void setPollFogProperties(String str, FogCallBack fogCallBack) throws JSONException {
        if (checkPara(str, getToken())) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject.put(StubApp.getString2("13055"), str);
                jSONObject2.put(StubApp.getString2("44768"), 1);
                jSONObject.put(StubApp.getString2("13070"), jSONObject2);
                doHttpPost(Configuration.SETPROPERTIES(), RequestBody.create(JSON_Type, jSONObject.toString()), fogCallBack);
                return;
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        fogIllegalCallBack(fogCallBack);
    }

    public void setFogProperties(JSONObject jSONObject, FogCallBack fogCallBack) {
        doHttpPost(Configuration.SETPROPERTIES(), RequestBody.create(JSON_Type, jSONObject.toString()), fogCallBack);
    }

    public void getProductList(Integer num, FogCallBack fogCallBack) {
        if (checkPara(getToken())) {
            doHttpGet(Configuration.PRODUCTLIST() + (StubApp.getString2(44756) + String.valueOf(num)), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void createScheduleTask(ScheduleTaskParam scheduleTaskParam, final ControlDeviceCallBack controlDeviceCallBack) throws JSONException {
        if (checkPara(scheduleTaskParam.device_id, scheduleTaskParam.commands, scheduleTaskParam.hour, scheduleTaskParam.minute, getToken())) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(StubApp.getString2("39533"), 0);
                jSONObject.put(StubApp.getString2("13055"), scheduleTaskParam.device_id);
                jSONObject.put(StubApp.getString2("13480"), scheduleTaskParam.commands);
                scheduleTaskParam.enable = !scheduleTaskParam.enable || scheduleTaskParam.enable;
                scheduleTaskParam.month = checkPara(scheduleTaskParam.month) ? scheduleTaskParam.month : "*";
                scheduleTaskParam.day_of_month = checkPara(scheduleTaskParam.day_of_month) ? scheduleTaskParam.day_of_month : "*";
                jSONObject.put(StubApp.getString2("184"), scheduleTaskParam.enable);
                jSONObject.put(StubApp.getString2("16486"), scheduleTaskParam.month);
                jSONObject.put(StubApp.getString2("44746"), scheduleTaskParam.day_of_month);
                if (checkPara(scheduleTaskParam.day_of_week)) {
                    jSONObject.put(StubApp.getString2("13479"), scheduleTaskParam.day_of_week);
                }
                jSONObject.put(StubApp.getString2("13481"), scheduleTaskParam.hour);
                jSONObject.put(StubApp.getString2("13482"), scheduleTaskParam.minute);
                doHttpPost(Configuration._SCHEDULETASK(), RequestBody.create(JSON_Type, String.valueOf(jSONObject)), new FogCallBack() { // from class: io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager.6
                    @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                    public void onSuccess(String str) {
                        DeYeHttpRequestManager.this.successCBCtrlDev(str, controlDeviceCallBack);
                    }

                    @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                    public void onFailure(int i, String str) {
                        DeYeHttpRequestManager.this.failureCBCtrlDev(i, str, controlDeviceCallBack);
                    }
                });
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, controlDeviceCallBack);
    }

    public void getTaskList(String str, int i, int i2, final ControlDeviceCallBack controlDeviceCallBack) {
        if (checkPara(str, getToken()) && (i2 == 0 || 1 == i2)) {
            String str2 = StubApp.getString2(44757) + i + StubApp.getString2(44758) + i2 + StubApp.getString2(44759) + str + StubApp.getString2(44760);
            Log.d(StubApp.getString2(13825), Configuration._SCHEDULETASK() + str2);
            doHttpGet(Configuration._SCHEDULETASK() + str2, new FogCallBack() { // from class: io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager.7
                @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                public void onSuccess(String str3) {
                    DeYeHttpRequestManager.this.successCBCtrlDev(str3, controlDeviceCallBack);
                }

                @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                public void onFailure(int i3, String str3) {
                    DeYeHttpRequestManager.this.failureCBCtrlDev(i3, str3, controlDeviceCallBack);
                }
            });
            return;
        }
        failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, controlDeviceCallBack);
    }

    public void createDelayTask(ScheduleTaskParam scheduleTaskParam, final ControlDeviceCallBack controlDeviceCallBack) throws JSONException {
        if (checkPara(scheduleTaskParam.device_id, scheduleTaskParam.commands, getToken())) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(StubApp.getString2("39533"), 1);
                jSONObject.put(StubApp.getString2("13055"), scheduleTaskParam.device_id);
                jSONObject.put(StubApp.getString2("13480"), scheduleTaskParam.commands);
                jSONObject.put(StubApp.getString2("184"), scheduleTaskParam.enable);
                jSONObject.put(StubApp.getString2("16487"), scheduleTaskParam.second);
                doHttpPost(Configuration._SCHEDULETASK(), RequestBody.create(JSON_Type, String.valueOf(jSONObject)), new FogCallBack() { // from class: io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager.8
                    @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                    public void onSuccess(String str) {
                        DeYeHttpRequestManager.this.successCBCtrlDev(str, controlDeviceCallBack);
                    }

                    @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                    public void onFailure(int i, String str) {
                        DeYeHttpRequestManager.this.failureCBCtrlDev(i, str, controlDeviceCallBack);
                    }
                });
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, controlDeviceCallBack);
    }

    public void deleteTask(String str, final ControlDeviceCallBack controlDeviceCallBack) {
        if (checkPara(str, getToken())) {
            String str2 = StubApp.getString2(44747) + str;
            Log.d(StubApp.getString2(5607), str2);
            doHttpDelete(Configuration._SCHEDULETASK() + str2, new FogCallBack() { // from class: io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager.9
                @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                public void onSuccess(String str3) {
                    DeYeHttpRequestManager.this.successCBCtrlDev(str3, controlDeviceCallBack);
                }

                @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                public void onFailure(int i, String str3) {
                    DeYeHttpRequestManager.this.failureCBCtrlDev(i, str3, controlDeviceCallBack);
                }
            });
            return;
        }
        failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, controlDeviceCallBack);
    }

    public void updateScheduleTask(ScheduleTaskParam scheduleTaskParam, final ControlDeviceCallBack controlDeviceCallBack) throws JSONException {
        if (checkPara(scheduleTaskParam.device_id, scheduleTaskParam.task_id, scheduleTaskParam.commands, scheduleTaskParam.hour, scheduleTaskParam.minute, getToken())) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(StubApp.getString2("39533"), 0);
                jSONObject.put(StubApp.getString2("44774"), scheduleTaskParam.task_id);
                jSONObject.put(StubApp.getString2("13055"), scheduleTaskParam.device_id);
                jSONObject.put(StubApp.getString2("13480"), scheduleTaskParam.commands);
                scheduleTaskParam.enable = !scheduleTaskParam.enable || scheduleTaskParam.enable;
                scheduleTaskParam.month = checkPara(scheduleTaskParam.month) ? scheduleTaskParam.month : "*";
                scheduleTaskParam.day_of_month = checkPara(scheduleTaskParam.day_of_month) ? scheduleTaskParam.day_of_month : "*";
                jSONObject.put(StubApp.getString2("184"), scheduleTaskParam.enable);
                jSONObject.put(StubApp.getString2("16486"), scheduleTaskParam.month);
                jSONObject.put(StubApp.getString2("44746"), scheduleTaskParam.day_of_month);
                if (checkPara(scheduleTaskParam.day_of_week)) {
                    jSONObject.put(StubApp.getString2("13479"), scheduleTaskParam.day_of_week);
                }
                jSONObject.put(StubApp.getString2("13481"), scheduleTaskParam.hour);
                jSONObject.put(StubApp.getString2("13482"), scheduleTaskParam.minute);
                doHttpPut(Configuration._SCHEDULETASK(), RequestBody.create(JSON_Type, jSONObject.toString()), new FogCallBack() { // from class: io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager.10
                    @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                    public void onSuccess(String str) {
                        DeYeHttpRequestManager.this.successCBCtrlDev(str, controlDeviceCallBack);
                    }

                    @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                    public void onFailure(int i, String str) {
                        DeYeHttpRequestManager.this.failureCBCtrlDev(i, str, controlDeviceCallBack);
                    }
                });
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, controlDeviceCallBack);
    }

    public void updateDelayTask(ScheduleTaskParam scheduleTaskParam, final ControlDeviceCallBack controlDeviceCallBack) throws JSONException {
        if (checkPara(scheduleTaskParam.device_id, scheduleTaskParam.commands, getToken())) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(StubApp.getString2("39533"), 1);
                jSONObject.put(StubApp.getString2("44774"), scheduleTaskParam.task_id);
                jSONObject.put(StubApp.getString2("13055"), scheduleTaskParam.device_id);
                jSONObject.put(StubApp.getString2("13480"), scheduleTaskParam.commands);
                jSONObject.put(StubApp.getString2("184"), scheduleTaskParam.enable);
                jSONObject.put(StubApp.getString2("16487"), scheduleTaskParam.second);
                doHttpPut(Configuration._SCHEDULETASK(), RequestBody.create(JSON_Type, jSONObject.toString()), new FogCallBack() { // from class: io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager.11
                    @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                    public void onSuccess(String str) {
                        DeYeHttpRequestManager.this.successCBCtrlDev(str, controlDeviceCallBack);
                    }

                    @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                    public void onFailure(int i, String str) {
                        DeYeHttpRequestManager.this.failureCBCtrlDev(i, str, controlDeviceCallBack);
                    }
                });
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, controlDeviceCallBack);
    }

    public void updateBindRole(String str, String str2, String str3, FogCallBack fogCallBack) throws JSONException {
        if (checkPara(str, str2, str3, getToken())) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(StubApp.getString2("4732"), str);
                jSONObject.put(StubApp.getString2("44765"), str2);
                jSONObject.put(StubApp.getString2("18817"), str3);
                doHttpPut(Configuration.UPDATEBINDROLE(), RequestBody.create(JSON_Type, jSONObject.toString()), fogCallBack);
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        fogIllegalCallBack(fogCallBack);
    }

    public void removeBindRole(String str, String str2, FogCallBack fogCallBack) throws JSONException {
        if (checkPara(str, str2, getToken())) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(StubApp.getString2("4732"), str);
                jSONObject.put(StubApp.getString2("44765"), str2);
                doHttpPut(Configuration.REMOVEBINDROLE(), RequestBody.create(JSON_Type, jSONObject.toString()), fogCallBack);
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        fogIllegalCallBack(fogCallBack);
    }

    public void transferAminUser(String str, String str2, FogCallBack fogCallBack) throws JSONException {
        if (checkPara(str, str2, getToken())) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(StubApp.getString2("4732"), str);
                jSONObject.put(StubApp.getString2("44765"), str2);
                doHttpPost(Configuration.TRANSFERADMIN(), RequestBody.create(JSON_Type, jSONObject.toString()), fogCallBack);
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        fogIllegalCallBack(fogCallBack);
    }

    public void getDeviceList(FogCallBack fogCallBack) {
        if (checkPara(getToken())) {
            doHttpGet(Configuration.GETDEVICELIST() + (StubApp.getString2(566) + Math.random() + StubApp.getString2(44749)), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void getMemberList(String str, FogCallBack fogCallBack) {
        if (checkPara(str, getToken())) {
            doHttpGet(Configuration.GETMEMBERLIST() + (StubApp.getString2(44748) + str + StubApp.getString2(844) + Math.random()), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void getDeviceVersion(String str, FogCallBack fogCallBack) {
        if (checkPara(str, getToken())) {
            doHttpGet(Configuration.DEVICE_VERSION() + (StubApp.getString2(44750) + str), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void getDeviceInfo(String str, FogCallBack fogCallBack) {
        if (checkPara(str, getToken())) {
            doHttpGet(Configuration.DEVICEINFO() + (StubApp.getString2(44748) + str + StubApp.getString2(844) + Math.random() + StubApp.getString2(44749)), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void getOnlineStatus(String str, String str2, String str3, FogCallBack fogCallBack) {
        if (checkPara(getToken())) {
            doHttpGet(Configuration.ONLINESTATUS() + (StubApp.getString2(44751) + str2 + StubApp.getString2(44752) + str3 + StubApp.getString2(844) + Math.random()), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void getWeatherInfo(double d, double d2, FogCallBack fogCallBack) {
        if (checkPara(getToken())) {
            String str = StubApp.getString2(44763) + d + StubApp.getString2(44755) + d2;
            doHttpGet(Configuration.NOWWEATHER() + str, fogCallBack);
            Log.d(StubApp.getString2(5607), Configuration.NOWWEATHER() + str);
            return;
        }
        fogIllegalCallBack(fogCallBack);
    }

    public void getOutDoor(int i, double d, double d2, FogCallBack fogCallBack) {
        if (checkPara(getToken())) {
            String str = StubApp.getString2(44753) + i + StubApp.getString2(44754) + d + StubApp.getString2(44755) + d2;
            doHttpGet(Configuration.OUTDOOR() + str, fogCallBack);
            Log.d(StubApp.getString2(5607), Configuration.OUTDOOR() + str);
            return;
        }
        fogIllegalCallBack(fogCallBack);
    }

    public void setUserInfo(JSONObject jSONObject, FogCallBack fogCallBack) {
        if (checkPara(getToken())) {
            Log.d(StubApp.getString2(44771), jSONObject.toString());
            doHttpPut(Configuration.USERINFO(), RequestBody.create(JSON_Type, jSONObject.toString()), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void uploadClientID(JSONObject jSONObject, FogCallBack fogCallBack) {
        if (checkPara(getToken())) {
            Log.d(StubApp.getString2(44776), jSONObject.toString());
            doHttpPut(Configuration.UPLOAD_CLIENT_ID(), RequestBody.create(JSON_Type, jSONObject.toString()), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void submitIssue(String str, FogCallBack fogCallBack) throws JSONException {
        if (checkPara(str, getToken())) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(StubApp.getString2("5072"), str);
                doHttpPost(Configuration.SUBMIT_ISSUE(), RequestBody.create(JSON_Type, String.valueOf(jSONObject)), fogCallBack);
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        fogIllegalCallBack(fogCallBack);
    }

    public void pushSwitchPost(String str, FogCallBack fogCallBack) throws JSONException {
        if (checkPara(str, getToken())) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(StubApp.getString2("211"), str);
                doHttpPost(Configuration.PUSH_SWITCH(), RequestBody.create(JSON_Type, String.valueOf(jSONObject)), fogCallBack);
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        fogIllegalCallBack(fogCallBack);
    }

    public void pushSwitchGet(FogCallBack fogCallBack) {
        if (checkPara(getToken())) {
            doHttpGet(Configuration.PUSH_SWITCH(), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void getUserInfo(FogCallBack fogCallBack) {
        if (checkPara(getToken())) {
            doHttpGet(Configuration.USERINFO(), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void setUserNickName(String str, String str2, FogCallBack fogCallBack) throws JSONException {
        if (checkPara(str, str2, getToken())) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(StubApp.getString2("4720"), str);
                doHttpPut(Configuration.GETUSERINFO(), RequestBody.create(JSON_Type, String.valueOf(jSONObject)), fogCallBack);
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        fogIllegalCallBack(fogCallBack);
    }

    public void setUserAvatar(String str, String str2, FogCallBack fogCallBack) throws JSONException {
        if (checkPara(str, str2, getToken())) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(StubApp.getString2("44770"), str);
                doHttpPut(Configuration.GETUSERINFO(), RequestBody.create(JSON_Type, String.valueOf(jSONObject)), fogCallBack);
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        fogIllegalCallBack(fogCallBack);
    }

    public void uploadUserAvatar(File file, FogCallBack fogCallBack) {
        if (checkPara(getToken())) {
            try {
                doHttpPost(Configuration.UPDATE_USERAVATAR(), new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart(StubApp.getString2("1006"), file.getName(), RequestBody.create(JSON_Type, file)).build(), fogCallBack);
                return;
            } catch (Exception e) {
                LogUtil.d(StubApp.getString2(44777) + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        fogIllegalCallBack(fogCallBack);
    }

    public void setUserArea(String str, FogCallBack fogCallBack) throws JSONException {
        if (checkPara(str, getToken())) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(StubApp.getString2("5441"), str);
                doHttpPut(Configuration.GETUSERINFO(), RequestBody.create(JSON_Type, String.valueOf(jSONObject)), fogCallBack);
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        fogIllegalCallBack(fogCallBack);
    }

    public void updateTimingTask(RequestBody requestBody, final ControlDeviceCallBack controlDeviceCallBack) {
        doHttpPut(Configuration._SCHEDULETASK(), requestBody, new FogCallBack() { // from class: io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager.12
            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onSuccess(String str) {
                DeYeHttpRequestManager.this.successCBCtrlDev(str, controlDeviceCallBack);
            }

            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onFailure(int i, String str) {
                DeYeHttpRequestManager.this.failureCBCtrlDev(i, str, controlDeviceCallBack);
            }
        });
    }

    public void createTimingTask(RequestBody requestBody, final ControlDeviceCallBack controlDeviceCallBack) {
        if (checkPara(getToken())) {
            doHttpPost(Configuration._SCHEDULETASK(), requestBody, new FogCallBack() { // from class: io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager.13
                @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                public void onSuccess(String str) {
                    DeYeHttpRequestManager.this.successCBCtrlDev(str, controlDeviceCallBack);
                }

                @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                public void onFailure(int i, String str) {
                    DeYeHttpRequestManager.this.failureCBCtrlDev(i, str, controlDeviceCallBack);
                }
            });
        } else {
            failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, controlDeviceCallBack);
        }
    }

    public void updateVersion(String str, FogCallBack fogCallBack) {
        String str2 = Configuration.UPDATEVERSION() + StubApp.getString2(44775) + str;
        Log.d(StubApp.getString2(1279), str2);
        if (checkPara(getToken())) {
            doHttpGet(str2, fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    protected void fogIllegalCallBack(FogCallBack fogCallBack) {
        illegalCallBack(fogCallBack);
    }

    public void cleanAccount(String str, FogCallBack fogCallBack) throws JSONException {
        if (checkPara(str, getToken())) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(StubApp.getString2("13687"), str);
                doHttpPut(Configuration.CLEAN_ACCOUNT(), RequestBody.create(JSON_Type, jSONObject.toString()), fogCallBack);
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        fogIllegalCallBack(fogCallBack);
    }

    public void checkApkIntegrity(int i, String str, FogCallBack fogCallBack) {
        if (checkPara(String.valueOf(i), str)) {
            doHttpPostWithoutClientId(Configuration.CHECK_APK_INTEGRITY(), new FormBody.Builder().add(StubApp.getString2(159), String.valueOf(i)).add(StubApp.getString2(44744), str).build(), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void fetchAllMessages(FogCallBack fogCallBack) {
        if (checkPara(getToken())) {
            doHttpGet(Configuration.GET_MESSAGE_LIST(), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void getMessageIsAllRead(FogCallBack fogCallBack) {
        if (checkPara(getToken())) {
            doHttpGet(Configuration.GET_MESSAGE_IS_ALL_READ(), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }

    public void isLoadUrlByBrowser(FogCallBack fogCallBack) {
        if (checkPara(getToken())) {
            doHttpGet(Configuration.IS_LOAD_URL_BY_BROWSER(), fogCallBack);
        } else {
            fogIllegalCallBack(fogCallBack);
        }
    }
}
