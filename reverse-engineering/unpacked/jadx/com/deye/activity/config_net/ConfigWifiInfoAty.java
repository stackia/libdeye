package com.deye.activity.config_net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import com.deye.activity.device.base.BaseActivity;
import com.deye.adapter.WifiInfoAdapter;
import com.deye.fragment.WIfiInfoFragment;
import com.deye.helper.DialogHelper;
import com.deye.helper.LocationUtils;
import com.deye.helper.WifiScanCallback;
import com.deye.helper.WifiScanResult;
import com.deye.listener.OnDisMissDialog;
import com.deye.utils.ActivityRouterUtilsKt;
import com.google.gson.reflect.TypeToken;
import com.hjq.permissions.OnPermissionCallback;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ConfigWifiInfoAtyBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.config_net.DeYeEasyLinkManager;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ConfigWifiInfoAty extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, OnDisMissDialog, WifiInfoAdapter.IWifiListener {
    public static final String TAG = StubApp.getString2(13388);
    private WifiScanResult connectWifiInfo;
    private WIfiInfoFragment fragment;
    private boolean hasResult;
    private ConfigWifiInfoAtyBinding mBinding;
    private String mConfigNetType;
    private String mProductId;
    private String mWifiPassword;
    private String tempSSID;
    private boolean mIsShow = false;
    private HashMap<String, String> wifiPwdMap = new HashMap<>();
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.deye.activity.config_net.ConfigWifiInfoAty.9
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean booleanExtra = intent.getBooleanExtra(StubApp.getString2(13294), false);
            if (booleanExtra) {
                ConfigWifiInfoAty.this.mConfigNetType = StubApp.getString2(701);
            }
            LogUtil.d(StubApp.getString2(13385) + booleanExtra);
        }
    };

    static {
        StubApp.interface11(13918);
    }

    private native boolean ensureLocationAccess();

    private native void getWifiList();

    /* JADX INFO: Access modifiers changed from: private */
    public native void handleWifiList(List<WifiScanResult> list);

    private native boolean hasLocationPermission();

    private native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean isLocationServiceEnabled();

    /* JADX INFO: Access modifiers changed from: private */
    public native void refreshSSID();

    /* JADX INFO: Access modifiers changed from: private */
    public native void requestLocationPermission();

    /* JADX INFO: Access modifiers changed from: private */
    public native void setWifiPwdByMemory(String str);

    private native void showLocationPermissionDialog();

    /* JADX INFO: Access modifiers changed from: private */
    public native void showLocationServiceDialog();

    /* JADX INFO: Access modifiers changed from: private */
    public native void startConfigNet(String str);

    private native void stopBluetoothScan();

    private native void toBindWifi();

    private native void updateView();

    @Override // com.deye.listener.OnDisMissDialog
    public native void dismissDialog();

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public native void onCheckedChanged(CompoundButton compoundButton, boolean z);

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();

    protected native void onPause();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onResume();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onStop();

    @Override // com.deye.adapter.WifiInfoAdapter.IWifiListener
    public native void onWifiSelect(WifiScanResult wifiScanResult);

    /* renamed from: com.deye.activity.config_net.ConfigWifiInfoAty$1, reason: invalid class name */
    class AnonymousClass1 extends TypeToken<Map<String, String>> {
        AnonymousClass1() {
        }
    }

    /* renamed from: com.deye.activity.config_net.ConfigWifiInfoAty$2, reason: invalid class name */
    class AnonymousClass2 implements WifiScanCallback {
        AnonymousClass2() {
        }

        @Override // com.deye.helper.WifiScanCallback
        public void onScanResults(List<WifiScanResult> list) {
            ConfigWifiInfoAty.this.handleWifiList(list);
        }

        @Override // com.deye.helper.WifiScanCallback
        public void onScanFailure(String str) {
            ConfigWifiInfoAty.this.hasResult = true;
            ConfigWifiInfoAty.this.hideLoading();
        }
    }

    /* renamed from: com.deye.activity.config_net.ConfigWifiInfoAty$3, reason: invalid class name */
    class AnonymousClass3 extends DialogHelper.OnDialogListener {
        AnonymousClass3() {
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String str) {
            ConfigWifiInfoAty.this.requestLocationPermission();
        }
    }

    /* renamed from: com.deye.activity.config_net.ConfigWifiInfoAty$4, reason: invalid class name */
    class AnonymousClass4 implements OnPermissionCallback {
        AnonymousClass4() {
        }

        public void onGranted(List<String> list, boolean z) {
            if (!ConfigWifiInfoAty.this.isLocationServiceEnabled()) {
                ConfigWifiInfoAty.this.showLocationServiceDialog();
            } else {
                ConfigWifiInfoAty.this.refreshSSID();
            }
        }

        public void onDenied(List<String> list, boolean z) throws Resources.NotFoundException {
            if (z) {
                DialogHelper.permissionDeniedHint(ConfigWifiInfoAty.this, ConfigWifiInfoAty.this.getResources().getString(R.string.location) + ConfigWifiInfoAty.this.getResources().getString(R.string.location_denied_content), new DialogHelper.OnDialogListener() { // from class: com.deye.activity.config_net.ConfigWifiInfoAty.4.1
                    @Override // com.deye.helper.DialogHelper.OnDialogListener
                    public void onSure(String str) {
                        ActivityRouterUtilsKt.goAppDetailSetting();
                    }
                });
            }
        }
    }

    /* renamed from: com.deye.activity.config_net.ConfigWifiInfoAty$5, reason: invalid class name */
    class AnonymousClass5 extends DialogHelper.OnDialogListener {
        AnonymousClass5() {
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String str) {
            LocationUtils.openGpsSettings();
        }
    }

    /* renamed from: com.deye.activity.config_net.ConfigWifiInfoAty$6, reason: invalid class name */
    class AnonymousClass6 implements Runnable {
        AnonymousClass6() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LogUtil.d(StubApp.getString2(13384) + DeYeEasyLinkManager.getInstance().getSSID());
            if (ConfigWifiInfoAty.this.tempSSID != null && !ConfigWifiInfoAty.this.tempSSID.isEmpty()) {
                ConfigWifiInfoAty.this.mBinding.tvWifiName.setText(ConfigWifiInfoAty.this.tempSSID);
                ConfigWifiInfoAty.this.mBinding.tvNextStep.setEnabled(true);
                ConfigWifiInfoAty.this.mBinding.tv5gAlert.setVisibility(8);
                ConfigWifiInfoAty configWifiInfoAty = ConfigWifiInfoAty.this;
                configWifiInfoAty.setWifiPwdByMemory(configWifiInfoAty.tempSSID);
                return;
            }
            if (ConfigWifiInfoAty.this.connectWifiInfo == null) {
                ConfigWifiInfoAty.this.mBinding.tvWifiName.setText(DeYeEasyLinkManager.getInstance().getSSID());
                ConfigWifiInfoAty.this.mBinding.tvNextStep.setEnabled(false);
                ConfigWifiInfoAty.this.mBinding.tv5gAlert.setVisibility(8);
                ConfigWifiInfoAty.this.mBinding.edWifiPassword.setText("");
                return;
            }
            if (ConfigWifiInfoAty.this.connectWifiInfo.is24GHz || !ConfigWifiInfoAty.this.hasResult) {
                ConfigWifiInfoAty.this.mBinding.tvWifiName.setText(ConfigWifiInfoAty.this.connectWifiInfo.SSID);
                ConfigWifiInfoAty.this.mBinding.tv5gAlert.setVisibility(8);
                ConfigWifiInfoAty.this.mBinding.tvNextStep.setEnabled(true);
                ConfigWifiInfoAty configWifiInfoAty2 = ConfigWifiInfoAty.this;
                configWifiInfoAty2.setWifiPwdByMemory(configWifiInfoAty2.connectWifiInfo.SSID);
                return;
            }
            ConfigWifiInfoAty.this.mBinding.tvWifiName.setText(ConfigWifiInfoAty.this.getString(R.string.please_select_wifi_prompt));
            ConfigWifiInfoAty.this.mBinding.tvNextStep.setEnabled(false);
            ConfigWifiInfoAty.this.mBinding.tv5gAlert.setVisibility(0);
            ConfigWifiInfoAty.this.mBinding.edWifiPassword.setText("");
        }
    }

    /* renamed from: com.deye.activity.config_net.ConfigWifiInfoAty$7, reason: invalid class name */
    class AnonymousClass7 extends DialogHelper.OnDialogListener {
        final /* synthetic */ String val$mWifiSSID;

        AnonymousClass7(String str) {
            this.val$mWifiSSID = str;
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String str) {
            ConfigWifiInfoAty.this.startConfigNet(this.val$mWifiSSID);
        }
    }

    /* renamed from: com.deye.activity.config_net.ConfigWifiInfoAty$8, reason: invalid class name */
    class AnonymousClass8 implements OnPermissionCallback {
        final /* synthetic */ Intent val$intent;

        AnonymousClass8(Intent intent) {
            this.val$intent = intent;
        }

        public void onGranted(List<String> list, boolean z) {
            ConfigWifiInfoAty.this.startActivity(this.val$intent);
        }

        /* JADX WARN: Type inference failed for: r2v1, types: [android.content.Context, com.deye.activity.config_net.ConfigWifiInfoAty] */
        public void onDenied(List<String> list, boolean z) {
            ?? r2 = ConfigWifiInfoAty.this;
            Toast.makeText((Context) r2, r2.getString(R.string.please_open_bluetooth_permission_prompt), 1).show();
        }
    }
}
