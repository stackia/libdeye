package com.deye.activity.app_config;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.deye.activity.SplashActivity;
import com.deye.activity.device.base.BaseActivity;
import com.deye.utils.LanUtils;
import com.deye.utils.MMKVUtils;
import com.deye.views.dialog.AppEnvChangedDialog;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.log.LogUtil;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class AppConfigActivity extends BaseActivity {
    public static final String TAG = StubApp.getString2(13253);
    private static final String TOKEN_LOGIN_CHANGED_TEXT = StubApp.getString2(13251);
    private CheckBox mCbQueryAllDevices;
    private CheckBox mCbUseDebugTokenLogin;
    private EditText mEtDebugToken;
    private ImageView mIvBack;
    private RadioButton mRbProduction;
    private RadioButton mRbTesting;
    private TextView mTvPageTitle;
    private String mCurrentConfig = "";
    private String mChangedConfig = "";
    private String mChangedConfigText = "";
    private boolean mInitialUseDebugTokenLogin = false;
    private String mInitialDebugToken = "";

    static {
        StubApp.interface11(13889);
    }

    private native void configChangedTip();

    /* JADX INFO: Access modifiers changed from: private */
    public native String getCurrentDebugToken();

    private native boolean hasTokenLoginConfigChanged(String str);

    private native void readAppConfigInfo();

    /* JADX INFO: Access modifiers changed from: private */
    public native void saveDebugConfig(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void showRestartDialog(String str, boolean z);

    public native String getChangedConfigText();

    public native void onBack(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    public native void onDeYeOnLineATesting(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native boolean onKeyDown(int i, KeyEvent keyEvent);

    public native void onMongo42Testing(View view);

    public native void onPerformanceTesting(View view);

    public native void onProductiont(View view);

    public native void onTesting(View view);

    /* renamed from: com.deye.activity.app_config.AppConfigActivity$1, reason: invalid class name */
    class AnonymousClass1 implements CompoundButton.OnCheckedChangeListener {
        AnonymousClass1() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (compoundButton.isPressed() && z != AppConfigActivity.this.mInitialUseDebugTokenLogin) {
                AppConfigActivity appConfigActivity = AppConfigActivity.this;
                appConfigActivity.saveDebugConfig(appConfigActivity.getCurrentDebugToken());
                AppConfigActivity.this.showRestartDialog(StubApp.getString2(13251), false);
            }
        }
    }

    /* renamed from: com.deye.activity.app_config.AppConfigActivity$2, reason: invalid class name */
    class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LanUtils.changeLan(AppConfigActivity.this);
            Intent intent = new Intent((Context) AppConfigActivity.this, (Class<?>) SplashActivity.class);
            intent.addFlags(268468224);
            AppConfigActivity.this.startActivity(intent);
        }
    }

    /* renamed from: com.deye.activity.app_config.AppConfigActivity$3, reason: invalid class name */
    class AnonymousClass3 implements AppEnvChangedDialog.IOnClickListsner {
        final /* synthetic */ boolean val$saveEnvConfig;

        @Override // com.deye.views.dialog.AppEnvChangedDialog.IOnClickListsner
        public void onCancel() {
        }

        AnonymousClass3(boolean z) {
            this.val$saveEnvConfig = z;
        }

        @Override // com.deye.views.dialog.AppEnvChangedDialog.IOnClickListsner
        public void onSure(String str) throws RuntimeException {
            if (this.val$saveEnvConfig) {
                MMKV.defaultMMKV().putString(StubApp.getString2(13111), AppConfigActivity.this.mChangedConfig);
                LogUtil.d(StubApp.getString2(13253), StubApp.getString2(13252) + AppConfigActivity.this.mChangedConfig);
            }
            new Handler().postDelayed(new Runnable() { // from class: com.deye.activity.app_config.AppConfigActivity.3.1
                @Override // java.lang.Runnable
                public void run() {
                    MMKVUtils.INSTANCE.setUserInfo(null);
                    MMKV.defaultMMKV().encode(StubApp.getString2(13134), "");
                    MMKV.defaultMMKV().encode(StubApp.getString2(13118), "");
                    DeYeHttpRequestManager.getInstance().setToken("");
                    AppConfigActivity.this.finishAffinity();
                    Process.killProcess(Process.myPid());
                    AppConfigActivity.this.finish();
                }
            }, 100L);
        }
    }
}
