package com.deye.activity.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import com.deye.activity.device.base.BaseActivity;
import com.deye.entity.CheckCodeBean;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ForgoetPwdAtyBinding;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.bean.BaseBean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ForgetPwdActivity.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 &2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001&B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J(\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019H\u0016J\b\u0010\u001c\u001a\u00020\u0013H\u0002J\u0010\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0012\u0010 \u001a\u00020\u00132\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020\u0013H\u0014J(\u0010$\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010%\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000e8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/deye/activity/login/ForgetPwdActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "Landroid/text/TextWatcher;", "()V", "mBaseBean", "Lio/fogcloud/sdk/fog/bean/BaseBean;", "mBinding", "Lcom/mxchipapp/databinding/ForgoetPwdAtyBinding;", "mCheckCodeBean", "Lcom/deye/entity/CheckCodeBean;", "mCodeNumber", "", "mHandler", "Landroid/os/Handler;", "mPwd", "mStrPhoneNumber", "modifyFailedReason", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "initView", "onClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onTextChanged", "before", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class ForgetPwdActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    private static final int CHECK_CODE_FAIL = 100;
    private static final int REGISTER_PASSWORD_FAIL = 300;
    private static final int REGISTER_PASSWORD_SUCCESS = 200;
    private static final int SEND_CODE_FAIL = 500;
    private static final int SEND_CODE_SUCCESS = 400;
    private BaseBean mBaseBean;
    private ForgoetPwdAtyBinding mBinding;
    private CheckCodeBean mCheckCodeBean;
    private String mCodeNumber;
    private String mPwd;
    private String mStrPhoneNumber;
    private String modifyFailedReason = "";
    private final Handler mHandler = new Handler() { // from class: com.deye.activity.login.ForgetPwdActivity$mHandler$1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Intrinsics.checkNotNullParameter(msg, "msg");
            this.this$0.stopWaiting();
            int i = msg.what;
            if (i == 100) {
                BaseUtils.showShortToast(R.string.verification_code_error);
                return;
            }
            if (i == 200) {
                BaseUtils.showShortToast(R.string.reset_password_success);
                CheckCodeBean checkCodeBean = this.this$0.mCheckCodeBean;
                Intrinsics.checkNotNull(checkCodeBean);
                String token = checkCodeBean.getData().getToken();
                MMKV.defaultMMKV().encode(StubApp.getString2(13134), token);
                DeYeHttpRequestManager.getInstance().setToken(token);
                this.this$0.finish();
                this.this$0.toTabMainPage();
                return;
            }
            if (i == 300) {
                ForgetPwdActivity forgetPwdActivity = this.this$0;
                BaseUtils.showShortToast((Context) forgetPwdActivity, forgetPwdActivity.modifyFailedReason);
            } else if (i == 400) {
                BaseUtils.showShortToast(R.string.get_verification_code_success);
            } else {
                if (i != 500) {
                    return;
                }
                BaseUtils.showShortToast(R.string.get_verification_code_failure);
            }
        }
    };

    static {
        StubApp.interface11(14228);
        INSTANCE = new Companion(null);
    }

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$0(ForgetPwdActivity forgetPwdActivity, CompoundButton compoundButton, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$1(ForgetPwdActivity forgetPwdActivity, CompoundButton compoundButton, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onClick$lambda$2(ForgetPwdActivity forgetPwdActivity);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onClick$lambda$4(ForgetPwdActivity forgetPwdActivity);

    @Override // android.text.TextWatcher
    public native void afterTextChanged(Editable s);

    @Override // android.text.TextWatcher
    public native void beforeTextChanged(CharSequence s, int start, int count, int after);

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();

    @Override // android.text.TextWatcher
    public native void onTextChanged(CharSequence s, int start, int before, int count);

    /* compiled from: ForgetPwdActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/deye/activity/login/ForgetPwdActivity$initView$1", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.ForgetPwdActivity$initView$1, reason: invalid class name */
    public static final class AnonymousClass1 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        AnonymousClass1() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            ForgoetPwdAtyBinding forgoetPwdAtyBinding = ForgetPwdActivity.this.mBinding;
            if (forgoetPwdAtyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                forgoetPwdAtyBinding = null;
            }
            forgoetPwdAtyBinding.tvGetCode.setEnabled(String.valueOf(s).length() == 11);
        }
    }

    /* compiled from: ForgetPwdActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/deye/activity/login/ForgetPwdActivity$initView$2", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.ForgetPwdActivity$initView$2, reason: invalid class name */
    public static final class AnonymousClass2 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        AnonymousClass2() {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            ForgoetPwdAtyBinding forgoetPwdAtyBinding = ForgetPwdActivity.this.mBinding;
            if (forgoetPwdAtyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                forgoetPwdAtyBinding = null;
            }
            forgoetPwdAtyBinding.btnNext.setEnabled(String.valueOf(s).length() == 6);
        }
    }
}
