package com.deye.activity.mine;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.databinding.ActivityChangeEmailBinding;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChangeEmailActivity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0003J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/deye/activity/mine/ChangeEmailActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "email", "", "isPwdChange", "", "mAtyBinding", "Lcom/mxchipapp/databinding/ActivityChangeEmailBinding;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "toGetCode", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class ChangeEmailActivity extends BaseActivity {
    private String email;
    private boolean isPwdChange;
    private ActivityChangeEmailBinding mAtyBinding;

    static {
        StubApp.interface11(14333);
    }

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$0(ChangeEmailActivity changeEmailActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$1(ChangeEmailActivity changeEmailActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$2(ChangeEmailActivity changeEmailActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$5(ChangeEmailActivity changeEmailActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$5$lambda$3(ChangeEmailActivity changeEmailActivity);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$5$lambda$4(ChangeEmailActivity changeEmailActivity);

    private final native void toGetCode();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    /* compiled from: ChangeEmailActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/deye/activity/mine/ChangeEmailActivity$initView$4", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.ChangeEmailActivity$initView$4, reason: invalid class name */
    public static final class AnonymousClass4 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        AnonymousClass4() {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            ActivityChangeEmailBinding activityChangeEmailBinding = ChangeEmailActivity.this.mAtyBinding;
            if (activityChangeEmailBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAtyBinding");
                activityChangeEmailBinding = null;
            }
            activityChangeEmailBinding.btnConfirm.setEnabled(String.valueOf(s).length() >= 6);
        }
    }

    /* compiled from: ChangeEmailActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/deye/activity/mine/ChangeEmailActivity$initView$5", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.ChangeEmailActivity$initView$5, reason: invalid class name */
    public static final class AnonymousClass5 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        AnonymousClass5() {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            ActivityChangeEmailBinding activityChangeEmailBinding = ChangeEmailActivity.this.mAtyBinding;
            ActivityChangeEmailBinding activityChangeEmailBinding2 = null;
            String string2 = StubApp.getString2(13741);
            if (activityChangeEmailBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                activityChangeEmailBinding = null;
            }
            Button button = activityChangeEmailBinding.btnConfirm;
            ActivityChangeEmailBinding activityChangeEmailBinding3 = ChangeEmailActivity.this.mAtyBinding;
            if (activityChangeEmailBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                activityChangeEmailBinding2 = activityChangeEmailBinding3;
            }
            button.setEnabled(activityChangeEmailBinding2.etPwd.length() >= 6);
        }
    }
}
