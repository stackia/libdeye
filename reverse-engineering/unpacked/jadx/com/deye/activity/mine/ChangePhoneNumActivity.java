package com.deye.activity.mine;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.databinding.ActivityChangePhoneNumBinding;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChangePhoneNumActivity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0003J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/deye/activity/mine/ChangePhoneNumActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "isPwdChange", "", "mAtyBinding", "Lcom/mxchipapp/databinding/ActivityChangePhoneNumBinding;", "phoneNum", "", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "toGetCode", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class ChangePhoneNumActivity extends BaseActivity {
    private boolean isPwdChange;
    private ActivityChangePhoneNumBinding mAtyBinding;
    private String phoneNum;

    static {
        StubApp.interface11(14348);
    }

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$0(ChangePhoneNumActivity changePhoneNumActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$1(ChangePhoneNumActivity changePhoneNumActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$2(ChangePhoneNumActivity changePhoneNumActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$5(ChangePhoneNumActivity changePhoneNumActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$5$lambda$3(ChangePhoneNumActivity changePhoneNumActivity);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$5$lambda$4(ChangePhoneNumActivity changePhoneNumActivity);

    private final native void toGetCode();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    /* compiled from: ChangePhoneNumActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/deye/activity/mine/ChangePhoneNumActivity$initView$4", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.ChangePhoneNumActivity$initView$4, reason: invalid class name */
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
            ActivityChangePhoneNumBinding activityChangePhoneNumBinding = ChangePhoneNumActivity.this.mAtyBinding;
            if (activityChangePhoneNumBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAtyBinding");
                activityChangePhoneNumBinding = null;
            }
            activityChangePhoneNumBinding.btnConfirm.setEnabled(String.valueOf(s).length() >= 6);
        }
    }

    /* compiled from: ChangePhoneNumActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/deye/activity/mine/ChangePhoneNumActivity$initView$5", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.ChangePhoneNumActivity$initView$5, reason: invalid class name */
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
            ActivityChangePhoneNumBinding activityChangePhoneNumBinding = ChangePhoneNumActivity.this.mAtyBinding;
            ActivityChangePhoneNumBinding activityChangePhoneNumBinding2 = null;
            String string2 = StubApp.getString2(13741);
            if (activityChangePhoneNumBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                activityChangePhoneNumBinding = null;
            }
            Button button = activityChangePhoneNumBinding.btnConfirm;
            ActivityChangePhoneNumBinding activityChangePhoneNumBinding3 = ChangePhoneNumActivity.this.mAtyBinding;
            if (activityChangePhoneNumBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                activityChangePhoneNumBinding2 = activityChangePhoneNumBinding3;
            }
            button.setEnabled(activityChangePhoneNumBinding2.etPwd.length() >= 6);
        }
    }
}
