package com.deye.activity.mine;

import android.os.Bundle;
import android.view.View;
import com.deye.activity.device.base.BaseActivity;
import com.deye.helper.DialogHelper;
import com.deye.utils.BaseUtils;
import com.hjq.permissions.OnPermissionCallback;
import com.mxchipapp.databinding.ActivityPrivacyManagerBinding;
import com.stub.StubApp;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PrivacyManagerActivity.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\n\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/deye/activity/mine/PrivacyManagerActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "mAtyBinding", "Lcom/mxchipapp/databinding/ActivityPrivacyManagerBinding;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "requestPermissionCallPhone", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class PrivacyManagerActivity extends BaseActivity {
    private ActivityPrivacyManagerBinding mAtyBinding;

    static {
        StubApp.interface11(14423);
    }

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$0(PrivacyManagerActivity privacyManagerActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$1(PrivacyManagerActivity privacyManagerActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$2(PrivacyManagerActivity privacyManagerActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$3(PrivacyManagerActivity privacyManagerActivity, View view);

    private final native void requestPermissionCallPhone();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    /* compiled from: PrivacyManagerActivity.kt */
    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\t\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\n\u001a\u00020\bH\u0016¨\u0006\u000b"}, d2 = {"com/deye/activity/mine/PrivacyManagerActivity$requestPermissionCallPhone$1", "Lcom/hjq/permissions/OnPermissionCallback;", "onDenied", "", "permissions", "", "", "doNotAskAgain", "", "onGranted", "allGranted", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.PrivacyManagerActivity$requestPermissionCallPhone$1, reason: invalid class name */
    public static final class AnonymousClass1 implements OnPermissionCallback {
        AnonymousClass1() {
        }

        public void onGranted(List<String> permissions, boolean allGranted) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            DialogHelper.showCallCustomerServiceDialog(PrivacyManagerActivity.this.mContext, StubApp.getString2(13747));
        }

        public void onDenied(List<String> permissions, boolean doNotAskAgain) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            BaseUtils.showShortToast(StubApp.getString2(13752));
        }
    }
}
