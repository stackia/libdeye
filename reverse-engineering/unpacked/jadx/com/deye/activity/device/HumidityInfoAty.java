package com.deye.activity.device;

import android.os.Bundle;
import android.view.View;
import com.deye.activity.device.base.BaseActivity;
import com.deye.adapter.HumidityInfoAdapter;
import com.mxchipapp.databinding.HumidityInfoAtyBinding;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class HumidityInfoAty extends BaseActivity implements View.OnClickListener {
    private HumidityInfoAdapter mHumidityInfoAdapter;
    private HumidityInfoAtyBinding mHumidityInfoAtyBinding;
    private String mKey = "";

    static {
        StubApp.interface11(14013);
    }

    private native void initView();

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();
}
