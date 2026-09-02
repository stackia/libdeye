package com.deye.activity.config_net;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.databinding.ConfigNetCompleteAtyBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.callback.ManageDeviceCallBack;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class DeviceCompleteActivity extends BaseActivity implements View.OnClickListener {
    private ConfigNetCompleteAtyBinding mConfigNetCompleteAtyBinding;
    private String mConfigNetType;
    private String mDeviceId;
    private String mProductId;
    private boolean mIsBindSuccess = false;
    private final String officialEmail = StubApp.getString2(13239);

    static {
        StubApp.interface11(13920);
    }

    private native void copyEmailToClipboard();

    private native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public native void toMainPage();

    public native void goConfigWifiInfoPage(String str);

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    @Override // com.deye.activity.device.base.BaseActivity
    public native boolean onKeyDown(int i, KeyEvent keyEvent);

    /* renamed from: com.deye.activity.config_net.DeviceCompleteActivity$1, reason: invalid class name */
    class AnonymousClass1 implements ManageDeviceCallBack {
        AnonymousClass1() {
        }

        @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
        public void onSuccess(String str) {
            DeviceCompleteActivity.this.toMainPage();
        }

        @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
        public void onFailure(int i, String str) {
            DeviceCompleteActivity.this.toMainPage();
        }
    }
}
