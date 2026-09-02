package com.deye.activity.config_net;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.databinding.WifiOtaNetAtyBinding;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class WifiOtaDoneAty extends BaseActivity implements View.OnClickListener {
    private String mConfigNetType;
    private String mProductId;
    private WifiOtaNetAtyBinding mWifiOtaNetAtyBinding;

    static {
        StubApp.interface11(13976);
    }

    private native void initView();

    public native void goConfigWifiInfoPage(View view);

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();

    @Override // com.deye.activity.device.base.BaseActivity
    public native boolean onKeyDown(int i, KeyEvent keyEvent);
}
