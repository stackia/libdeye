package com.deye.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import com.deye.MxchipApplication;
import com.deye.activity.device.base.BaseActivity;
import com.deye.utils.ActivityRouterUtilsKt;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import com.mxchipapp.databinding.BindPhoneAtyBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.bean.LoginResult;
import io.fogcloud.sdk.fog.bean.SimpleResultBean;
import io.fogcloud.sdk.fog.bean.VerificationResultBean;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BindPhoneNumActivity.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 %2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001%B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J(\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000bH\u0016J\b\u0010\u0017\u001a\u00020\u000fH\u0007J\b\u0010\u0018\u001a\u00020\u000fH\u0003J\b\u0010\u0019\u001a\u00020\u000fH\u0002J\b\u0010\u001a\u001a\u00020\u000fH\u0003J\b\u0010\u001b\u001a\u00020\u000fH\u0002J\u0010\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0017J\u0012\u0010\u001f\u001a\u00020\u000f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u000fH\u0014J(\u0010#\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/deye/activity/login/BindPhoneNumActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "Landroid/text/TextWatcher;", "()V", "accessToken", "", "mBinding", "Lcom/mxchipapp/databinding/BindPhoneAtyBinding;", "mCodeNumber", "mPageType", "", "mStrPhoneNumber", "openId", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "count", "after", "doBindPHone", "doChangePhone", "doChangePhoneGetCode", "doWeChatGetCode", "initView", "onClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onTextChanged", "before", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class BindPhoneNumActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    public static final int TYPE_CHANGE_PHONE_NUM = 1;
    public static final int TYPE_WECHAT_BIND = 0;
    private BindPhoneAtyBinding mBinding;
    private String mCodeNumber;
    private int mPageType;
    private String mStrPhoneNumber = "";
    private String openId = "";
    private String accessToken = "";

    /* compiled from: BindPhoneNumActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.BindPhoneNumActivity$doChangePhone$3, reason: invalid class name and case insensitive filesystem */
    static final class C01663<T> implements Consumer {
        public static final C01663<T> INSTANCE = new C01663<>();

        C01663() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Throwable it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
        }
    }

    static {
        StubApp.interface11(14213);
        INSTANCE = new Companion(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void doBindPHone$lambda$2(BindPhoneNumActivity bindPhoneNumActivity);

    private final native void doChangePhone();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void doChangePhone$lambda$0(BindPhoneNumActivity bindPhoneNumActivity);

    private final native void doChangePhoneGetCode();

    private final native void doWeChatGetCode();

    private final native void initView();

    @Override // android.text.TextWatcher
    public native void afterTextChanged(Editable s);

    @Override // android.text.TextWatcher
    public native void beforeTextChanged(CharSequence s, int start, int count, int after);

    public final native void doBindPHone();

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();

    @Override // android.text.TextWatcher
    public native void onTextChanged(CharSequence s, int start, int before, int count);

    /* compiled from: BindPhoneNumActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/deye/activity/login/BindPhoneNumActivity$initView$1", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.BindPhoneNumActivity$initView$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01681 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        C01681() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            BindPhoneAtyBinding bindPhoneAtyBinding = BindPhoneNumActivity.this.mBinding;
            if (bindPhoneAtyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                bindPhoneAtyBinding = null;
            }
            bindPhoneAtyBinding.tvGetCode.setEnabled(String.valueOf(s).length() == 11);
        }
    }

    /* compiled from: BindPhoneNumActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/deye/activity/login/BindPhoneNumActivity$initView$2", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.BindPhoneNumActivity$initView$2, reason: invalid class name and case insensitive filesystem */
    public static final class C01692 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        C01692() {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            BindPhoneAtyBinding bindPhoneAtyBinding = BindPhoneNumActivity.this.mBinding;
            if (bindPhoneAtyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                bindPhoneAtyBinding = null;
            }
            bindPhoneAtyBinding.btnNext.setEnabled(BindPhoneNumActivity.this.mStrPhoneNumber.length() == 11 && String.valueOf(s).length() == 6);
        }
    }

    /* compiled from: BindPhoneNumActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/VerificationResultBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.BindPhoneNumActivity$doWeChatGetCode$1, reason: invalid class name */
    static final class AnonymousClass1<T> implements Consumer {
        public static final AnonymousClass1<T> INSTANCE = new AnonymousClass1<>();

        AnonymousClass1() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(BaseResult<VerificationResultBean> it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            BaseResult.MetaBean meta = it2.getMeta();
            if (meta != null && meta.getCode() == 0) {
                BaseUtils.showShortToast(R.string.verification_code_sent_successfully);
            } else {
                BaseResult.MetaBean meta2 = it2.getMeta();
                BaseUtils.showShortToast(String.valueOf(meta2 != null ? meta2.getMessage() : null));
            }
        }
    }

    /* compiled from: BindPhoneNumActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.BindPhoneNumActivity$doWeChatGetCode$2, reason: invalid class name and case insensitive filesystem */
    static final class C01672<T> implements Consumer {
        public static final C01672<T> INSTANCE = new C01672<>();

        C01672() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Throwable it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            it2.printStackTrace();
        }
    }

    /* compiled from: BindPhoneNumActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/SimpleResultBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.BindPhoneNumActivity$doChangePhone$2, reason: invalid class name */
    static final class AnonymousClass2<T> implements Consumer {
        public static final AnonymousClass2<T> INSTANCE = new AnonymousClass2<>();

        AnonymousClass2() {
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

    /* compiled from: BindPhoneNumActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/LoginResult;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.BindPhoneNumActivity$doBindPHone$3, reason: invalid class name */
    static final class AnonymousClass3<T> implements Consumer {
        AnonymousClass3() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(BaseResult<LoginResult> it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            BaseResult.MetaBean meta = it2.getMeta();
            if (meta == null || meta.getCode() != 0) {
                BaseUtils.showShortToast(R.string.verification_code_error);
                return;
            }
            DeYeHttpRequestManager deYeHttpRequestManager = DeYeHttpRequestManager.getInstance();
            LoginResult data = it2.getData();
            deYeHttpRequestManager.setToken(data != null ? data.token : null);
            Intent intent = new Intent();
            intent.putExtra(StubApp.getString2(13690), true);
            intent.setFlags(268468224);
            ActivityRouterUtilsKt.routingForTabMainActivity((Context) BindPhoneNumActivity.this.mContext, intent);
        }
    }

    /* compiled from: BindPhoneNumActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.login.BindPhoneNumActivity$doBindPHone$4, reason: invalid class name */
    static final class AnonymousClass4<T> implements Consumer {
        public static final AnonymousClass4<T> INSTANCE = new AnonymousClass4<>();

        AnonymousClass4() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Throwable it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            it2.printStackTrace();
        }
    }
}
