package com.deye.activity.config_net;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import com.deye.activity.config_net.DeviceScanQRActivity;
import com.deye.activity.device.base.BaseActivity;
import com.deye.helper.DialogHelper;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ScanQrCodeAtyBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.callback.ManageDeviceCallBack;
import io.fogcloud.sdk.fog.log.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DeviceScanQRActivity.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\rH\u0002J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0006H\u0016J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0012\u0010\u0016\u001a\u00020\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\rH\u0014J\b\u0010\u001a\u001a\u00020\rH\u0016J\u0010\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u000fH\u0016J\b\u0010\u001d\u001a\u00020\rH\u0014J\b\u0010\u001e\u001a\u00020\rH\u0014J\b\u0010\u001f\u001a\u00020\rH\u0002J\u0010\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\u0006H\u0002J\b\u0010\"\u001a\u00020\rH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/deye/activity/config_net/DeviceScanQRActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "Lcn/bingoogolapple/qrcode/core/QRCodeView$Delegate;", "()V", "isOpenFlash", "", "mScanQrCodeAtyBinding", "Lcom/mxchipapp/databinding/ScanQrCodeAtyBinding;", "photoPickerLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroidx/activity/result/PickVisualMediaRequest;", "initDialogResult", "", "content", "", "initView", "onCameraAmbientBrightnessChanged", "isDark", "onClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onScanQRCodeOpenCameraError", "onScanQRCodeSuccess", "result", "onStart", "onStop", "openPhotoPicker", "stopWaitingDialog", "isSucc", "vibrate", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DeviceScanQRActivity extends BaseActivity implements View.OnClickListener, QRCodeView.Delegate {
    private boolean isOpenFlash;
    private ScanQrCodeAtyBinding mScanQrCodeAtyBinding;
    private final ActivityResultLauncher<PickVisualMediaRequest> photoPickerLauncher = registerForActivityResult((ActivityResultContract) new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback() { // from class: com.deye.activity.config_net.DeviceScanQRActivity$$ExternalSyntheticLambda3
        public final void onActivityResult(Object obj) {
            DeviceScanQRActivity.photoPickerLauncher$lambda$1(this.f$0, (Uri) obj);
        }
    });

    static {
        StubApp.interface11(13958);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final native void initDialogResult(String content);

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$2(DeviceScanQRActivity deviceScanQRActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$3(DeviceScanQRActivity deviceScanQRActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onScanQRCodeSuccess$lambda$5(DeviceScanQRActivity deviceScanQRActivity);

    private final native void openPhotoPicker();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void photoPickerLauncher$lambda$1(DeviceScanQRActivity deviceScanQRActivity, Uri uri);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void stopWaitingDialog(boolean isSucc);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void stopWaitingDialog$lambda$6(boolean z, DeviceScanQRActivity deviceScanQRActivity);

    private final native void vibrate();

    public native void onCameraAmbientBrightnessChanged(boolean isDark);

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();

    public native void onScanQRCodeOpenCameraError();

    public native void onScanQRCodeSuccess(String result);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onStart();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onStop();

    /* compiled from: DeviceScanQRActivity.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"com/deye/activity/config_net/DeviceScanQRActivity$onScanQRCodeSuccess$1", "Lio/fogcloud/sdk/fog/callback/ManageDeviceCallBack;", "onFailure", "", "code", "", "message", "", "onSuccess", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.config_net.DeviceScanQRActivity$onScanQRCodeSuccess$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01601 implements ManageDeviceCallBack {
        C01601() {
        }

        @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
        public void onSuccess(String message) throws JSONException {
            String string2 = StubApp.getString2(109);
            String string22 = StubApp.getString2(13082);
            Intrinsics.checkNotNullParameter(message, "message");
            DeviceScanQRActivity.this.stopWaitingDialog(true);
            LogUtil.d("message", message);
            try {
                JSONObject jSONObject = new JSONObject(message);
                if (jSONObject.getJSONObject(string22).getInt(string2) == 0) {
                    DeviceScanQRActivity.this.toTabMainPage();
                    DeviceScanQRActivity.this.finish();
                } else if (jSONObject.getJSONObject(string22).getInt(string2) == 10400 && Intrinsics.areEqual(StubApp.getString2("13464"), jSONObject.getJSONObject(string22).getString("message"))) {
                    final DeviceScanQRActivity deviceScanQRActivity = DeviceScanQRActivity.this;
                    deviceScanQRActivity.runOnUiThread(new Runnable() { // from class: com.deye.activity.config_net.DeviceScanQRActivity$onScanQRCodeSuccess$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            DeviceScanQRActivity.C01601.onSuccess$lambda$0(deviceScanQRActivity);
                        }
                    });
                } else {
                    final String string = jSONObject.getJSONObject(string22).getString("message");
                    final DeviceScanQRActivity deviceScanQRActivity2 = DeviceScanQRActivity.this;
                    deviceScanQRActivity2.runOnUiThread(new Runnable() { // from class: com.deye.activity.config_net.DeviceScanQRActivity$onScanQRCodeSuccess$1$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            DeviceScanQRActivity.C01601.onSuccess$lambda$1(deviceScanQRActivity2, string);
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
                final DeviceScanQRActivity deviceScanQRActivity3 = DeviceScanQRActivity.this;
                deviceScanQRActivity3.runOnUiThread(new Runnable() { // from class: com.deye.activity.config_net.DeviceScanQRActivity$onScanQRCodeSuccess$1$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        DeviceScanQRActivity.C01601.onSuccess$lambda$2(deviceScanQRActivity3);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSuccess$lambda$0(DeviceScanQRActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            String string = this$0.getString(R.string.qr_code_expired);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            this$0.initDialogResult(string);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSuccess$lambda$1(DeviceScanQRActivity this$0, String str) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.initDialogResult(StubApp.getString2(626) + str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSuccess$lambda$2(DeviceScanQRActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            String string = this$0.getString(R.string.invalid_qr_code);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            this$0.initDialogResult(string);
        }

        @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
        public void onFailure(int code, String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            DeviceScanQRActivity.this.stopWaitingDialog(false);
            LogUtil.d(StubApp.getString2(13463), message);
        }
    }

    /* compiled from: DeviceScanQRActivity.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"com/deye/activity/config_net/DeviceScanQRActivity$onScanQRCodeSuccess$2", "Lio/fogcloud/sdk/fog/callback/ManageDeviceCallBack;", "onFailure", "", "code", "", "message", "", "onSuccess", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.config_net.DeviceScanQRActivity$onScanQRCodeSuccess$2, reason: invalid class name */
    public static final class AnonymousClass2 implements ManageDeviceCallBack {
        AnonymousClass2() {
        }

        @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
        public void onSuccess(String message) throws JSONException {
            String string2 = StubApp.getString2(13082);
            Intrinsics.checkNotNullParameter(message, "message");
            DeviceScanQRActivity.this.stopWaitingDialog(true);
            LogUtil.d("message", message);
            try {
                JSONObject jSONObject = new JSONObject(message);
                if (jSONObject.getJSONObject(string2).getInt(StubApp.getString2("109")) == 0) {
                    DeviceScanQRActivity.this.toTabMainPage();
                    DeviceScanQRActivity.this.finish();
                } else {
                    BaseUtils.showShortToast((Context) DeviceScanQRActivity.this, jSONObject.getJSONObject(string2).getString("message"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
        public void onFailure(int code, String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            DeviceScanQRActivity.this.stopWaitingDialog(false);
            LogUtil.d(StubApp.getString2(13463), message);
        }
    }

    /* compiled from: DeviceScanQRActivity.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/deye/activity/config_net/DeviceScanQRActivity$initDialogResult$1", "Lcom/deye/helper/DialogHelper$OnDialogListener;", "onCancel", "", "onSure", "text", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.config_net.DeviceScanQRActivity$initDialogResult$1, reason: invalid class name */
    public static final class AnonymousClass1 extends DialogHelper.OnDialogListener {
        AnonymousClass1() {
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onCancel() {
            DeviceScanQRActivity.this.finish();
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            ScanQrCodeAtyBinding scanQrCodeAtyBinding = DeviceScanQRActivity.this.mScanQrCodeAtyBinding;
            ScanQrCodeAtyBinding scanQrCodeAtyBinding2 = null;
            String string2 = StubApp.getString2(13462);
            if (scanQrCodeAtyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                scanQrCodeAtyBinding = null;
            }
            if (scanQrCodeAtyBinding.zvScanQrCode != null) {
                try {
                    ScanQrCodeAtyBinding scanQrCodeAtyBinding3 = DeviceScanQRActivity.this.mScanQrCodeAtyBinding;
                    if (scanQrCodeAtyBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                        scanQrCodeAtyBinding3 = null;
                    }
                    scanQrCodeAtyBinding3.zvScanQrCode.startCamera();
                    ScanQrCodeAtyBinding scanQrCodeAtyBinding4 = DeviceScanQRActivity.this.mScanQrCodeAtyBinding;
                    if (scanQrCodeAtyBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                        scanQrCodeAtyBinding4 = null;
                    }
                    scanQrCodeAtyBinding4.zvScanQrCode.showScanRect();
                    ScanQrCodeAtyBinding scanQrCodeAtyBinding5 = DeviceScanQRActivity.this.mScanQrCodeAtyBinding;
                    if (scanQrCodeAtyBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                    } else {
                        scanQrCodeAtyBinding2 = scanQrCodeAtyBinding5;
                    }
                    scanQrCodeAtyBinding2.zvScanQrCode.startSpot();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
