package com.deye.activity.config_net;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;
import com.deye.activity.device.base.BaseActivity;
import com.hjq.permissions.OnPermissionCallback;
import com.mxchipapp.R;
import com.mxchipapp.databinding.SetupSoftapTipAtyBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.config_net.DeYeEasyLinkManager;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class SoftApTipActivity extends BaseActivity implements View.OnClickListener {
    private boolean mIsRefreshSSID = true;
    private String mProductId;
    private SetupSoftapTipAtyBinding mSetupSoftapTipAtyBinding;
    private String mWifiPassword;
    private String mWifiSSID;

    static {
        StubApp.interface11(13974);
    }

    private native void initView();

    private native void refreshSSID();

    /* JADX INFO: Access modifiers changed from: private */
    public native void startConfigNet(String str);

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    @Override // com.deye.activity.device.base.BaseActivity
    public native boolean onKeyDown(int i, KeyEvent keyEvent);

    protected native void onPause();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onResume();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onStop();

    /* renamed from: com.deye.activity.config_net.SoftApTipActivity$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            while (SoftApTipActivity.this.mIsRefreshSSID) {
                SoftApTipActivity.this.runOnUiThread(new Runnable() { // from class: com.deye.activity.config_net.SoftApTipActivity.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (DeYeEasyLinkManager.getInstance().getSSID().contains(StubApp.getString2(13473))) {
                            SoftApTipActivity.this.startConfigNet(DeYeEasyLinkManager.getInstance().getSSID());
                        }
                    }
                });
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* renamed from: com.deye.activity.config_net.SoftApTipActivity$2, reason: invalid class name */
    class AnonymousClass2 implements OnPermissionCallback {
        final /* synthetic */ Intent val$intent;

        AnonymousClass2(Intent intent) {
            this.val$intent = intent;
        }

        public void onGranted(List<String> list, boolean z) {
            SoftApTipActivity.this.startActivity(this.val$intent);
            SoftApTipActivity.this.finish();
        }

        /* JADX WARN: Type inference failed for: r2v1, types: [android.content.Context, com.deye.activity.config_net.SoftApTipActivity] */
        public void onDenied(List<String> list, boolean z) {
            ?? r2 = SoftApTipActivity.this;
            Toast.makeText((Context) r2, r2.getString(R.string.please_open_bluetooth_permission), 1).show();
        }
    }
}
