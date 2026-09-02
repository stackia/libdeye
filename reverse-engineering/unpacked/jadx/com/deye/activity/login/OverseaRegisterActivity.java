package com.deye.activity.login;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.CompoundButton;
import com.deye.activity.device.base.BaseActivity;
import com.deye.utils.WebUrlManager;
import com.mxchipapp.databinding.OverseaRegisterAtyBinding;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OverseaRegisterActivity.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 +2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001+B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J(\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0016J\b\u0010\u0017\u001a\u00020\u000eH\u0002J\b\u0010\u0018\u001a\u00020\u000eH\u0002J\b\u0010\u0019\u001a\u00020\u000eH\u0002J\b\u0010\u001a\u001a\u00020\u000eH\u0002J\b\u0010\u001b\u001a\u00020\u000eH\u0002J\b\u0010\u001c\u001a\u00020\bH\u0002J\b\u0010\u001d\u001a\u00020\u000eH\u0002J\b\u0010\u001e\u001a\u00020\u000eH\u0002J\u0010\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020!H\u0016J\u0012\u0010\"\u001a\u00020\u000e2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J(\u0010%\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010'\u001a\u00020\u000eH\u0002J\b\u0010(\u001a\u00020\u000eH\u0002J\b\u0010)\u001a\u00020\u000eH\u0002J\b\u0010*\u001a\u00020\u000eH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/deye/activity/login/OverseaRegisterActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "Landroid/text/TextWatcher;", "()V", "binding", "Lcom/mxchipapp/databinding/OverseaRegisterAtyBinding;", "confirmPassword", "", "email", "mode", "password", "verificationCode", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "checkEmailExists", "doChangePassword", "doRegister", "doRegisterEmail", "doResetPassword", "getScene", "initTitle", "initView", "onClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onTextChanged", "before", "sendEmailVerificationCode", "setupTermsAgreementText", "updateNextButtonState", "verifyCode", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class OverseaRegisterActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    public static final String EXTRA_MODE = StubApp.getString2(112);
    public static final String MODE_CHANGE_PASSWORD = StubApp.getString2(13207);
    public static final String MODE_FORGOT_PASSWORD = StubApp.getString2(13717);
    public static final String MODE_REGISTER = StubApp.getString2(6636);
    private static final String TAG = StubApp.getString2(13704);
    private OverseaRegisterAtyBinding binding;
    private String confirmPassword;
    private String email;
    private String mode = StubApp.getString2(6636);
    private String password;
    private String verificationCode;

    static {
        StubApp.interface11(14270);
        INSTANCE = new Companion(null);
    }

    private final native void checkEmailExists();

    private final native void doChangePassword();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void doChangePassword$lambda$7(OverseaRegisterActivity overseaRegisterActivity);

    private final native void doRegister();

    private final native void doRegisterEmail();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void doRegisterEmail$lambda$5(OverseaRegisterActivity overseaRegisterActivity);

    private final native void doResetPassword();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void doResetPassword$lambda$6(OverseaRegisterActivity overseaRegisterActivity);

    private final native String getScene();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void initTitle();

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$0(OverseaRegisterActivity overseaRegisterActivity, CompoundButton compoundButton, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$1(OverseaRegisterActivity overseaRegisterActivity, CompoundButton compoundButton, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$2(OverseaRegisterActivity overseaRegisterActivity, CompoundButton compoundButton, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void sendEmailVerificationCode();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void sendEmailVerificationCode$lambda$3(OverseaRegisterActivity overseaRegisterActivity);

    private final native void setupTermsAgreementText();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void updateNextButtonState();

    private final native void verifyCode();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void verifyCode$lambda$4(OverseaRegisterActivity overseaRegisterActivity);

    @Override // android.text.TextWatcher
    public native void afterTextChanged(Editable s);

    @Override // android.text.TextWatcher
    public native void beforeTextChanged(CharSequence s, int start, int count, int after);

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // android.text.TextWatcher
    public native void onTextChanged(CharSequence s, int start, int before, int count);

    /* compiled from: OverseaRegisterActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/deye/activity/login/OverseaRegisterActivity$initView$1", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.OverseaRegisterActivity$initView$1, reason: invalid class name */
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
            boolean zMatches = Patterns.EMAIL_ADDRESS.matcher(String.valueOf(s)).matches();
            OverseaRegisterAtyBinding overseaRegisterAtyBinding = OverseaRegisterActivity.this.binding;
            if (overseaRegisterAtyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                overseaRegisterAtyBinding = null;
            }
            overseaRegisterAtyBinding.tvGetCode.setEnabled(zMatches);
        }
    }

    /* compiled from: OverseaRegisterActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/deye/activity/login/OverseaRegisterActivity$initView$2", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.OverseaRegisterActivity$initView$2, reason: invalid class name */
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
            OverseaRegisterActivity.this.updateNextButtonState();
        }
    }

    /* compiled from: OverseaRegisterActivity.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/deye/activity/login/OverseaRegisterActivity$setupTermsAgreementText$1", "Landroid/text/style/ClickableSpan;", "onClick", "", "widget", "Landroid/view/View;", "updateDrawState", "ds", "Landroid/text/TextPaint;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.OverseaRegisterActivity$setupTermsAgreementText$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01701 extends ClickableSpan {
        final /* synthetic */ int $darkColor;

        C01701(int i) {
            this.$darkColor = i;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View widget) {
            Intrinsics.checkNotNullParameter(widget, "widget");
            WebUrlManager.INSTANCE.openUserAgreement((Context) OverseaRegisterActivity.this);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint ds) {
            Intrinsics.checkNotNullParameter(ds, "ds");
            super.updateDrawState(ds);
            ds.setUnderlineText(true);
            ds.setColor(this.$darkColor);
        }
    }

    /* compiled from: OverseaRegisterActivity.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/deye/activity/login/OverseaRegisterActivity$setupTermsAgreementText$2", "Landroid/text/style/ClickableSpan;", "onClick", "", "widget", "Landroid/view/View;", "updateDrawState", "ds", "Landroid/text/TextPaint;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.OverseaRegisterActivity$setupTermsAgreementText$2, reason: invalid class name and case insensitive filesystem */
    public static final class C01712 extends ClickableSpan {
        final /* synthetic */ int $darkColor;

        C01712(int i) {
            this.$darkColor = i;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View widget) {
            Intrinsics.checkNotNullParameter(widget, "widget");
            WebUrlManager.INSTANCE.openPrivacyPolicy((Context) OverseaRegisterActivity.this);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint ds) {
            Intrinsics.checkNotNullParameter(ds, "ds");
            super.updateDrawState(ds);
            ds.setUnderlineText(true);
            ds.setColor(this.$darkColor);
        }
    }
}
