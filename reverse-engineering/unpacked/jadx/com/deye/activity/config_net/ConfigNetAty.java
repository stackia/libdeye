package com.deye.activity.config_net;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import com.deye.activity.device.base.BaseActivity;
import com.deye.combo.ComboManager;
import com.deye.combo.callback.BleLogIntercept;
import com.deye.combo.callback.ScanResultCallback;
import com.deye.entity.DevinfoBean;
import com.deye.helper.ConfigNetAtyHelper;
import com.deye.helper.DialogHelper;
import com.deye.udp.UdpManager;
import com.deye.utils.BaseUtils;
import com.deye.utils.MMKVUtils;
import com.dianping.logan.Logan;
import com.facebook.imagepipeline.common.RotationOptions;
import com.mxchipapp.R;
import com.mxchipapp.databinding.EasylinkConfigNetAtyBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.easylink.helper.EasyLinkCallBack;
import io.fogcloud.sdk.easylink.helper.EasyLinkErrCode;
import io.fogcloud.sdk.fog.api.config_net.interfaces.IDiscoverySoftApListener;
import io.fogcloud.sdk.fog.callback.ManageDeviceCallBack;
import io.fogcloud.sdk.fog.log.LogUtil;
import it.innove.Peripheral;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ConfigNetAty extends BaseActivity implements View.OnClickListener {
    private static final int BIND_FAIL = 400;
    private static final int BIND_SUCCESS = 300;
    private static final int SEARCH_DEVICE_SUCCESS = 100;
    public static final String TAG = StubApp.getString2(13329);
    private String bindId;
    public String mAddress;
    public String mCity;
    private ComboManager mComboManager;
    private ConfigNetAtyHelper mConfigNetAtyHelper;
    public String mDeviceId;
    private EasylinkConfigNetAtyBinding mEasylinkConfigNetAtyBinding;
    public String mLatitude;
    public String mLongitude;
    public String mProductId;
    public String mProvince;
    public UdpManager mUdpManager;
    public String mWifiPwd;
    public String mWifiSSID;
    public String mConfigNetType = StubApp.getString2(701);
    private String mDeviceMac = "";
    public boolean mIsAlreadyGetSoftApCallback = false;
    private int mConfigNetTotalTime = 10;
    private int mEasyLinkConfigNetTotalTime = 80;
    private int mSoftApConfigNetTotalTime = RotationOptions.ROTATE_180;
    private int mConfigNetProgress = 0;
    private boolean mIsStopConfigNetProgress = false;
    private boolean mIsWifiOtaing = false;
    private boolean mIsJumpPage = true;
    private int mProvisionedSuccessRouteType = 0;
    private volatile boolean mEasyLinkStartSuccess = false;
    private volatile boolean mHasReceivedUdpDevice = false;
    private volatile boolean mHasStartedBindDevice = false;
    private volatile boolean mHasBindFinished = false;
    private volatile boolean mEasyLinkFailureHandled = false;
    private volatile int mLastEasyLinkCode = -1;
    private volatile String mLastEasyLinkMessage = "";
    private Handler mHandler = new Handler() { // from class: com.deye.activity.config_net.ConfigNetAty.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ConfigNetAty.this.stopConfigNet();
            int i = message.what;
            if (i == 100) {
                ConfigNetAty.this.bindDevice(message);
            } else if (i == 300) {
                ConfigNetAty.this.mConfigNetAtyHelper.toCompletePage(true);
            } else {
                if (i != 400) {
                    return;
                }
                ConfigNetAty.this.mConfigNetAtyHelper.toCompletePage(false);
            }
        }
    };
    private boolean mIsStopCheckSoftAp = false;
    private ScanResultCallback mScanResultCallback = new ScanResultCallback() { // from class: com.deye.activity.config_net.ConfigNetAty.9
        @Override // com.deye.combo.callback.ScanResultCallback
        public void onBatchScanResults(List<ScanResult> list) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onCharacteristicChanged(byte[] bArr) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onCharacteristicWriteFailure(byte[] bArr) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onCharacteristicWriteSuccess(byte[] bArr) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onConnected(BluetoothGatt bluetoothGatt, int i, int i2) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onDisconnceted(BluetoothGatt bluetoothGatt, int i, int i2) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onScanFailed(int i) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onScanMatched(ScanResult scanResult) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onScanResult(int i, ScanResult scanResult) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onServiceDiscovery(BluetoothGatt bluetoothGatt, int i) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onStartConnect(String str) {
            ConfigNetAty.this.bindId = str;
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onScanTimeOut() {
            ConfigNetAty.this.mConfigNetAtyHelper.startConfigNet();
        }
    };

    /* compiled from: D8$$SyntheticClass */
    /* renamed from: com.deye.activity.config_net.ConfigNetAty$ConfigNetEasyLinkCallBack-IA, reason: invalid class name */
    public final /* synthetic */ class ConfigNetEasyLinkCallBackIA {
    }

    static {
        StubApp.interface11(13905);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native void bindDevice(Message message);

    /* JADX INFO: Access modifiers changed from: private */
    public native String buildTimeoutReason();

    private native void goSoftApTipAty();

    /* JADX INFO: Access modifiers changed from: private */
    public native void handleEasyLinkStartFailure(int i, String str);

    private native void initView();

    private native void isStopConfigNet();

    /* JADX INFO: Access modifiers changed from: private */
    public native void logConfigNetStage(String str, String str2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void popupDialog(DevinfoBean devinfoBean);

    /* JADX INFO: Access modifiers changed from: private */
    public native void stopConfigNet();

    private native void updateProgress();

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();

    @Override // com.deye.activity.device.base.BaseActivity
    public native boolean onKeyDown(int i, KeyEvent keyEvent);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onResume();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onStop();

    public native void startSoftAp();

    public native void toStartEasyLink();

    public native void toStartSearchDevicesUDP();

    /* renamed from: com.deye.activity.config_net.ConfigNetAty$2, reason: invalid class name */
    class AnonymousClass2 implements BleLogIntercept {
        AnonymousClass2() {
        }

        @Override // com.deye.combo.callback.BleLogIntercept
        public void onLog(String str, String str2) {
            Logan.w(str + StubApp.getString2(13292) + str2, 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(byte[] bArr) {
        this.mComboManager.checkOnlineStatus(this.bindId, this.mHandler);
    }

    /* renamed from: com.deye.activity.config_net.ConfigNetAty$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {
        AnonymousClass3() {
        }

        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            while (!ConfigNetAty.this.mIsStopConfigNetProgress && !ConfigNetAty.this.mIsWifiOtaing) {
                int i = ConfigNetAty.this.mConfigNetProgress / ConfigNetAty.this.mConfigNetTotalTime;
                ConfigNetAty.this.mEasylinkConfigNetAtyBinding.sbConfig.setProgress((ConfigNetAty.this.mConfigNetProgress * 100) / ConfigNetAty.this.mConfigNetTotalTime);
                try {
                    ConfigNetAty.this.mConfigNetProgress++;
                    Thread.sleep(1000L);
                    if (ConfigNetAty.this.mConfigNetTotalTime == ConfigNetAty.this.mConfigNetProgress && !ConfigNetAty.this.mIsStopConfigNetProgress) {
                        ConfigNetAty configNetAty = ConfigNetAty.this;
                        configNetAty.logConfigNetStage(StubApp.getString2("250"), configNetAty.buildTimeoutReason());
                        BaseUtils.sendMessage(ConfigNetAty.this.mHandler, 400, "");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (ConfigNetAty.this.mIsStopConfigNetProgress) {
                ConfigNetAty.this.mEasylinkConfigNetAtyBinding.sbConfig.setProgress(100.0f);
            }
        }
    }

    /* renamed from: com.deye.activity.config_net.ConfigNetAty$4, reason: invalid class name */
    class AnonymousClass4 extends DialogHelper.OnDialogListener {
        AnonymousClass4() {
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String str) {
            ConfigNetAty.this.stopConfigNet();
            if (ConfigNetAty.this.mIsAlreadyGetSoftApCallback) {
                ConfigNetAty.this.sendBroadcast(new Intent().setAction(StubApp.getString2(13293)).putExtra(StubApp.getString2(13294), true));
            }
            ConfigNetAty.this.finish();
        }
    }

    private class ConfigNetEasyLinkCallBack implements EasyLinkCallBack {
        /* synthetic */ ConfigNetEasyLinkCallBack(ConfigNetAty configNetAty, ConfigNetEasyLinkCallBackIA configNetEasyLinkCallBackIA) {
            this();
        }

        private ConfigNetEasyLinkCallBack() {
        }

        @Override // io.fogcloud.sdk.easylink.helper.EasyLinkCallBack
        public void onSuccess(int i, String str) {
            ConfigNetAty.this.mLastEasyLinkCode = i;
            ConfigNetAty.this.mLastEasyLinkMessage = str;
            int i2 = EasyLinkErrCode.START_CODE;
            String string2 = StubApp.getString2(13308);
            String string22 = StubApp.getString2(13320);
            if (i == i2) {
                ConfigNetAty.this.mEasyLinkStartSuccess = true;
                ConfigNetAty.this.logConfigNetStage(StubApp.getString2(13322), string22 + i + string2 + str);
                return;
            }
            if (i == EasyLinkErrCode.STOP_CODE) {
                ConfigNetAty.this.logConfigNetStage(StubApp.getString2(13323), string22 + i + string2 + str);
            } else {
                ConfigNetAty.this.logConfigNetStage(StubApp.getString2(13324), string22 + i + string2 + str);
            }
        }

        @Override // io.fogcloud.sdk.easylink.helper.EasyLinkCallBack
        public void onFailure(int i, String str) {
            ConfigNetAty.this.mLastEasyLinkCode = i;
            ConfigNetAty.this.mLastEasyLinkMessage = str;
            ConfigNetAty.this.logConfigNetStage(StubApp.getString2(13321), StubApp.getString2(13320) + i + StubApp.getString2(13308) + str);
            ConfigNetAty.this.handleEasyLinkStartFailure(i, str);
        }
    }

    /* renamed from: com.deye.activity.config_net.ConfigNetAty$5, reason: invalid class name */
    class AnonymousClass5 implements UdpManager.OnSearchCallBack {
        final /* synthetic */ WifiManager.MulticastLock val$finalMulticastLock;

        AnonymousClass5(WifiManager.MulticastLock multicastLock) {
            this.val$finalMulticastLock = multicastLock;
        }

        @Override // com.deye.udp.UdpManager.OnSearchCallBack
        public void onSuccess(DevinfoBean devinfoBean) {
            ConfigNetAty.this.mHasReceivedUdpDevice = true;
            WifiManager.MulticastLock multicastLock = this.val$finalMulticastLock;
            if (multicastLock != null) {
                multicastLock.release();
            }
            ConfigNetAty configNetAty = ConfigNetAty.this;
            String string2 = StubApp.getString2(13296);
            configNetAty.logConfigNetStage(StubApp.getString2(13299), string2 + devinfoBean.getFog_v3_deviceid() + StubApp.getString2(13297) + devinfoBean.getFog_v3_productid() + StubApp.getString2(13298) + devinfoBean.getStop_easylink());
            if (StubApp.getString2(2546).equals(ConfigNetAty.this.mConfigNetType) && (ConfigNetAty.this.mDeviceMac == null || devinfoBean.getFog_v3_devicesn() == null || !devinfoBean.getFog_v3_devicesn().contains(ConfigNetAty.this.mDeviceMac))) {
                ConfigNetAty.this.logConfigNetStage(StubApp.getString2(13302), StubApp.getString2(13300) + ConfigNetAty.this.mDeviceMac + StubApp.getString2(13301) + devinfoBean.getFog_v3_devicesn());
                return;
            }
            if (devinfoBean.getStop_easylink()) {
                ConfigNetAty.this.logConfigNetStage(StubApp.getString2(13303), string2 + devinfoBean.getFog_v3_deviceid());
                ConfigNetAty.this.mConfigNetAtyHelper.stopEasylink();
            } else {
                ConfigNetAty.this.mConfigNetAtyHelper.stopConfigNet();
                ConfigNetAty.this.popupDialog(devinfoBean);
            }
            Log.d(StubApp.getString2(13304), devinfoBean.getFog_v3_deviceid());
        }

        @Override // com.deye.udp.UdpManager.OnSearchCallBack
        public void onFailure(String str) {
            ConfigNetAty.this.logConfigNetStage(StubApp.getString2(13295), str);
            WifiManager.MulticastLock multicastLock = this.val$finalMulticastLock;
            if (multicastLock != null) {
                try {
                    multicastLock.release();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* renamed from: com.deye.activity.config_net.ConfigNetAty$6, reason: invalid class name */
    class AnonymousClass6 implements DialogHelper.IWifiOtaDoneListener {
        AnonymousClass6() {
        }

        @Override // com.deye.helper.DialogHelper.IWifiOtaDoneListener
        public void onWifiOtaDoneListener() {
            Intent intent = new Intent((Context) ConfigNetAty.this, (Class<?>) WifiOtaDoneAty.class);
            intent.putExtra(StubApp.getString2(13305), ConfigNetAty.this.mConfigNetType);
            intent.putExtra(StubApp.getString2(13306), ConfigNetAty.this.mProductId);
            ConfigNetAty.this.startActivity(intent);
            ConfigNetAty.this.finish();
        }
    }

    /* renamed from: com.deye.activity.config_net.ConfigNetAty$7, reason: invalid class name */
    class AnonymousClass7 implements IDiscoverySoftApListener {
        AnonymousClass7() {
        }

        @Override // io.fogcloud.sdk.fog.api.config_net.interfaces.IDiscoverySoftApListener
        public void onDiscoverySoftAp() {
            ConfigNetAty.this.mIsAlreadyGetSoftApCallback = true;
        }
    }

    /* renamed from: com.deye.activity.config_net.ConfigNetAty$8, reason: invalid class name */
    class AnonymousClass8 implements ManageDeviceCallBack {
        AnonymousClass8() {
        }

        @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
        public void onSuccess(String str) throws JSONException, Resources.NotFoundException {
            String string2 = StubApp.getString2(Peripheral.GATT_AUTH_FAIL);
            String string22 = StubApp.getString2(13082);
            try {
                ConfigNetAty.this.mIsStopConfigNetProgress = true;
                JSONObject jSONObject = new JSONObject(str);
                int i = jSONObject.getJSONObject(string22).getInt(StubApp.getString2("109"));
                LogUtil.d(string2, str);
                String string23 = StubApp.getString2(13312);
                String string24 = StubApp.getString2(13296);
                if (i == 0) {
                    ConfigNetAty.this.mHasBindFinished = true;
                    ConfigNetAty.this.logConfigNetStage(StubApp.getString2("13313"), string24 + ConfigNetAty.this.mDeviceId + string23 + i);
                    String string = ConfigNetAty.this.getString(R.string.bindsuccess);
                    LogUtil.d("in", StubApp.getString2("13314"));
                    BaseUtils.sendMessage(ConfigNetAty.this.mHandler, 300, string);
                    return;
                }
                ConfigNetAty.this.mHasBindFinished = true;
                ConfigNetAty.this.logConfigNetStage(StubApp.getString2("13315"), string24 + ConfigNetAty.this.mDeviceId + string23 + i + StubApp.getString2("13316") + str);
                if (i == 10352) {
                    String nickname = MMKVUtils.INSTANCE.getUserInfo().getNickname();
                    String string3 = jSONObject.getJSONObject(StubApp.getString2("100")).getString(StubApp.getString2("13317"));
                    String string25 = StubApp.getString2(13318);
                    if (string3 != null && string3.equals(nickname)) {
                        ConfigNetAty.this.mConfigNetAtyHelper.showTipDialog(String.format(ConfigNetAty.this.getResources().getString(R.string.already_bind_device_error), string3.substring(0, 3) + string25 + string3.substring(7, 11)), "", ConfigNetAty.this.getString(R.string.sure_text), true, false);
                    } else {
                        ConfigNetAty.this.mConfigNetAtyHelper.showTipDialog(String.format(ConfigNetAty.this.getResources().getString(R.string.has_super_user_error), string3.substring(0, 3) + string25 + string3.substring(7, 11)), "", ConfigNetAty.this.getString(R.string.sure_text), true, false);
                    }
                } else if (i == 30001) {
                    String string4 = jSONObject.getJSONObject(string22).getString(string2);
                    if (TextUtils.isEmpty(string4)) {
                        string4 = "";
                    }
                    ConfigNetAty.this.mConfigNetAtyHelper.showTipDialog(string4, "", ConfigNetAty.this.getString(R.string.sure_text), true, false);
                } else {
                    BaseUtils.sendMessage(ConfigNetAty.this.mHandler, 400, ConfigNetAty.this.getResources().getString(R.string.bind_device_fail_error));
                }
                LogUtil.d("in", StubApp.getString2("13319"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
        public void onFailure(int i, String str) throws Resources.NotFoundException {
            String string = ConfigNetAty.this.getResources().getString(R.string.bind_device_fail_error);
            ConfigNetAty.this.mIsStopConfigNetProgress = true;
            ConfigNetAty.this.mHasBindFinished = true;
            ConfigNetAty.this.logConfigNetStage(StubApp.getString2(13309), StubApp.getString2(13296) + ConfigNetAty.this.mDeviceId + StubApp.getString2(13307) + i + StubApp.getString2(13308) + str);
            BaseUtils.sendMessage(ConfigNetAty.this.mHandler, 400, string);
            LogUtil.d(StubApp.getString2(13310), StubApp.getString2(13311));
        }
    }
}
