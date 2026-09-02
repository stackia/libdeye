package com.deye.activity.config_net;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deye.activity.device.base.BaseActivity;
import com.deye.helper.DialogHelper;
import com.deye.utils.BaseUtils;
import com.deye.utils.PagerUtils;
import com.mxchipapp.R;
import com.mxchipapp.databinding.DeviceInfoAtyBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import io.fogcloud.sdk.fog.callback.ManageDeviceCallBack;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.util.List;
import org.json.JSONException;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class DeviceInfoActivity extends BaseActivity implements View.OnClickListener {
    private String mDeviceAlias;
    private String mDeviceId;
    private String mDeviceInfo;
    private DeviceInfoAtyBinding mDeviceInfoAtyBinding;
    private DeviceListBean mDeviceListBean;
    private String roomName;
    private String wifiVersion;

    static {
        StubApp.interface11(13932);
    }

    private native void copyText(String str, String str2);

    private native void deviceVersion();

    private native void initView();

    private native void requestInfo();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void finishActivityOrRefreshUIForRemovedDevice();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native List<String> getCurrentDeviceId();

    protected native void onActivityResult(int i, int i2, Intent intent);

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    @Override // com.deye.activity.device.base.BaseActivity
    public native boolean onKeyDown(int i, KeyEvent keyEvent);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onResume();

    /* renamed from: com.deye.activity.config_net.DeviceInfoActivity$1, reason: invalid class name */
    class AnonymousClass1 implements FogCallBack {
        AnonymousClass1() {
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onSuccess(String str) {
            String string = JSON.parseObject(str).getString(StubApp.getString2(100));
            if (!TextUtils.isEmpty(string)) {
                JSONObject object = JSON.parseObject(string);
                String string2 = StubApp.getString2(159);
                if (object.containsKey(string2)) {
                    DeviceInfoActivity.this.wifiVersion = JSON.parseObject(string).getString(string2);
                }
            }
            LogUtil.d(StubApp.getString2(13450) + DeviceInfoActivity.this.wifiVersion);
            DeviceInfoActivity.this.runOnUiThread(new Runnable() { // from class: com.deye.activity.config_net.DeviceInfoActivity.1.1
                @Override // java.lang.Runnable
                public void run() {
                    DeviceInfoActivity.this.mDeviceInfoAtyBinding.tvDeviceWifiVersion.setText(DeviceInfoActivity.this.wifiVersion);
                }
            });
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onFailure(int i, String str) {
            LogUtil.d(StubApp.getString2(13448) + i + StubApp.getString2(13449) + str);
        }
    }

    /* renamed from: com.deye.activity.config_net.DeviceInfoActivity$2, reason: invalid class name */
    class AnonymousClass2 implements FogCallBack {
        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onFailure(int i, String str) {
        }

        AnonymousClass2() {
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onSuccess(String str) {
            String string = JSON.parseObject(str).getString(StubApp.getString2(100));
            DeviceInfoActivity.this.roomName = JSON.parseObject(string).getString(StubApp.getString2(13451));
            DeviceInfoActivity.this.runOnUiThread(new Runnable() { // from class: com.deye.activity.config_net.DeviceInfoActivity.2.1
                @Override // java.lang.Runnable
                public void run() {
                    if (DeviceInfoActivity.this.roomName != null && !TextUtils.isEmpty(DeviceInfoActivity.this.roomName)) {
                        DeviceInfoActivity.this.mDeviceInfoAtyBinding.tvRoomName.setText(DeviceInfoActivity.this.roomName);
                        DeviceInfoActivity.this.mDeviceInfoAtyBinding.ivDot.setVisibility(8);
                    } else {
                        DeviceInfoActivity.this.mDeviceInfoAtyBinding.tvRoomName.setText("");
                        DeviceInfoActivity.this.mDeviceInfoAtyBinding.ivDot.setVisibility(0);
                    }
                }
            });
        }
    }

    /* renamed from: com.deye.activity.config_net.DeviceInfoActivity$3, reason: invalid class name */
    class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String product_icon = DeviceInfoActivity.this.mDeviceListBean.picture_v3;
            if (product_icon == null || product_icon.isEmpty()) {
                product_icon = DeviceInfoActivity.this.mDeviceListBean.getProduct_icon();
            }
            PagerUtils.goSettingRoom(DeviceInfoActivity.this.mContext, DeviceInfoActivity.this.mDeviceListBean.getDevice_id(), product_icon, DeviceInfoActivity.this.roomName);
        }
    }

    /* renamed from: com.deye.activity.config_net.DeviceInfoActivity$4, reason: invalid class name */
    class AnonymousClass4 extends DialogHelper.OnDialogListener {
        AnonymousClass4() {
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String str) throws JSONException {
            DeviceInfoActivity deviceInfoActivity = DeviceInfoActivity.this;
            deviceInfoActivity.showLoading(deviceInfoActivity.mContext, "");
            DeYeHttpRequestManager.getInstance().unBindDevice(DeviceInfoActivity.this.mDeviceId, new ManageDeviceCallBack() { // from class: com.deye.activity.config_net.DeviceInfoActivity.4.1
                @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
                public void onSuccess(String str2) {
                    Log.d(StubApp.getString2(13452), str2);
                    DeviceInfoActivity.this.runOnUiThread(new Runnable() { // from class: com.deye.activity.config_net.DeviceInfoActivity.4.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            DeviceInfoActivity.this.hideLoading();
                            BaseUtils.showShortToast(StubApp.getOrigApplicationContext(DeviceInfoActivity.this.getApplicationContext()), DeviceInfoActivity.this.getString(R.string.delete_success));
                            DeviceInfoActivity.this.toTabMainPage();
                        }
                    });
                }

                @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
                public void onFailure(int i, String str2) {
                    DeviceInfoActivity.this.runOnUiThread(new Runnable() { // from class: com.deye.activity.config_net.DeviceInfoActivity.4.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            DeviceInfoActivity.this.hideLoading();
                            BaseUtils.showShortToast(StubApp.getOrigApplicationContext(DeviceInfoActivity.this.getApplicationContext()), DeviceInfoActivity.this.getString(R.string.unbind_failed));
                        }
                    });
                }
            });
        }
    }
}
