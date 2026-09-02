package com.deye.activity.login;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import com.deye.MxchipApplication;
import com.deye.activity.device.base.BaseActivity;
import com.deye.utils.BaseUtils;
import com.mxchipapp.databinding.BindEmailAtyBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.LoginResult;
import io.fogcloud.sdk.fog.bean.SimpleResultBean;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BindEmailActivity.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 )2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001)B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J(\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u000bH\u0016J\b\u0010\u0018\u001a\u00020\u0010H\u0003J\b\u0010\u0019\u001a\u00020\u0010H\u0002J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0006H\u0002J\b\u0010\u001c\u001a\u00020\u0010H\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0006H\u0002J\u0010\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\"H\u0017J\u0012\u0010#\u001a\u00020\u00102\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020\u0010H\u0014J(\u0010'\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000bH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/deye/activity/login/BindEmailActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "Landroid/text/TextWatcher;", "()V", "idToken", "", "mBinding", "Lcom/mxchipapp/databinding/BindEmailAtyBinding;", "mCodeNumber", "mPageType", "", "mStrEmail", "oldEmailVerCode", "password", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "count", "after", "doChangeEmail", "doChangeEmailGetCode", "handleBindSuccess", "token", "initView", "isValidEmail", "", "email", "onClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onTextChanged", "before", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class BindEmailActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    public static final int TYPE_BIND_EMAIL_GOOGLE = 2;
    public static final int TYPE_CHANGE_EMAIL = 1;
    private String idToken;
    private BindEmailAtyBinding mBinding;
    private String mCodeNumber;
    private int mPageType;
    private String mStrEmail = "";
    private String oldEmailVerCode;
    private String password;

    /* compiled from: BindEmailActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.BindEmailActivity$doChangeEmail$6, reason: invalid class name */
    static final class AnonymousClass6<T> implements Consumer {
        public static final AnonymousClass6<T> INSTANCE = new AnonymousClass6<>();

        AnonymousClass6() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Throwable it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
        }
    }

    static {
        StubApp.interface11(14199);
        INSTANCE = new Companion(null);
    }

    private final native void doChangeEmail();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void doChangeEmail$lambda$0(BindEmailActivity bindEmailActivity);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void doChangeEmail$lambda$1(BindEmailActivity bindEmailActivity);

    private final native void doChangeEmailGetCode();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void handleBindSuccess(String token);

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public final native boolean isValidEmail(String email);

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

    /* compiled from: BindEmailActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/deye/activity/login/BindEmailActivity$initView$1", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.BindEmailActivity$initView$1, reason: invalid class name */
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
            BindEmailAtyBinding bindEmailAtyBinding = BindEmailActivity.this.mBinding;
            if (bindEmailAtyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                bindEmailAtyBinding = null;
            }
            bindEmailAtyBinding.tvGetCode.setEnabled(BindEmailActivity.this.isValidEmail(String.valueOf(s)));
        }
    }

    /* compiled from: BindEmailActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/deye/activity/login/BindEmailActivity$initView$2", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.BindEmailActivity$initView$2, reason: invalid class name and case insensitive filesystem */
    public static final class C01652 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        C01652() {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            BindEmailAtyBinding bindEmailAtyBinding = BindEmailActivity.this.mBinding;
            if (bindEmailAtyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                bindEmailAtyBinding = null;
            }
            Button button = bindEmailAtyBinding.btnNext;
            BindEmailActivity bindEmailActivity = BindEmailActivity.this;
            button.setEnabled(bindEmailActivity.isValidEmail(bindEmailActivity.mStrEmail) && String.valueOf(s).length() == 6);
        }
    }

    /* compiled from: BindEmailActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/LoginResult;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.BindEmailActivity$doChangeEmail$2, reason: invalid class name */
    static final class AnonymousClass2<T> implements Consumer {
        AnonymousClass2() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(BaseResult<LoginResult> it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            BaseResult.MetaBean meta = it2.getMeta();
            if (meta != null && meta.getCode() == 0) {
                LoginResult data = it2.getData();
                String str = data != null ? data.token : null;
                if (str != null) {
                    BindEmailActivity.this.handleBindSuccess(str);
                    return;
                } else {
                    BaseUtils.showShortToast((Context) BindEmailActivity.this.mContext, StubApp.getString2(13682));
                    return;
                }
            }
            BaseResult.MetaBean meta2 = it2.getMeta();
            BaseUtils.showShortToast(String.valueOf(meta2 != null ? meta2.getMessage() : null));
        }
    }

    /* compiled from: BindEmailActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.BindEmailActivity$doChangeEmail$3, reason: invalid class name */
    static final class AnonymousClass3<T> implements Consumer {
        AnonymousClass3() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Throwable it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            it2.printStackTrace();
            BaseUtils.showShortToast((Context) BindEmailActivity.this.mContext, StubApp.getString2(13683));
        }
    }

    /* compiled from: BindEmailActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/SimpleResultBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.BindEmailActivity$doChangeEmail$5, reason: invalid class name */
    static final class AnonymousClass5<T> implements Consumer {
        public static final AnonymousClass5<T> INSTANCE = new AnonymousClass5<>();

        AnonymousClass5() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(BaseResult<SimpleResultBean> it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            BaseResult.MetaBean meta = it2.getMeta();
            if (meta != null && meta.getCode() == 0) {
                MxchipApplication.getInstance().loginOut();
            } else {
                BaseResult.MetaBean meta2 = it2.getMeta();
                BaseUtils.showShortToast(String.valueOf(meta2 != null ? meta2.getMessage() : null));
            }
        }
    }
}
