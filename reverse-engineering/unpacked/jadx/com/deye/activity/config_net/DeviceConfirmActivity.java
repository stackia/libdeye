package com.deye.activity.config_net;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.CompoundButton;
import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.databinding.ConfigNetConfirmAtyBinding;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class DeviceConfirmActivity extends BaseActivity implements View.OnClickListener {
    private AnimationDrawable mAnimationDrawable;
    private ConfigNetConfirmAtyBinding mConfigNetConfirmAtyBinding;
    private String mProductId;

    static {
        StubApp.interface11(13922);
    }

    private native SpannableStringBuilder createConfigInstructionSpan(int i, int... iArr);

    private native void initView();

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onResume();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onStop();

    /* renamed from: com.deye.activity.config_net.DeviceConfirmActivity$1, reason: invalid class name */
    class AnonymousClass1 implements CompoundButton.OnCheckedChangeListener {
        AnonymousClass1() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                DeviceConfirmActivity.this.mConfigNetConfirmAtyBinding.tvConfirmWifiTip.setEnabled(true);
                DeviceConfirmActivity.this.mConfigNetConfirmAtyBinding.tvNextStep.setEnabled(true);
            } else {
                DeviceConfirmActivity.this.mConfigNetConfirmAtyBinding.tvConfirmWifiTip.setEnabled(false);
                DeviceConfirmActivity.this.mConfigNetConfirmAtyBinding.tvNextStep.setEnabled(false);
            }
        }
    }
}
