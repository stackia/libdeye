package com.deye.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.core.content.ContextCompat;
import androidx.credentials.Credential;
import androidx.credentials.CredentialManager;
import androidx.credentials.CustomCredential;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import com.alibaba.fastjson.JSON;
import com.deye.MxchipApplication;
import com.deye.activity.login.BindEmailActivity;
import com.deye.activity.login.OverseaRegisterActivity;
import com.deye.configs.Constants;
import com.deye.utils.ActivityRouterUtilsKt;
import com.deye.utils.BaseUtils;
import com.deye.utils.WebUrlManager;
import com.deye.views.CustomFontEditText;
import com.facebook.imageutils.TiffUtil;
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption;
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential;
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException;
import com.mxchipapp.R;
import com.mxchipapp.databinding.OverseaLoginFragmentBinding;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.bean.LoginResult;
import io.fogcloud.sdk.fog.log.LogUtil;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/* compiled from: OverseaLoginFragment.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 =2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001=B\u0005¢\u0006\u0002\u0010\u0005J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J*\u0010\u0015\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0018H\u0016J\b\u0010\u001b\u001a\u00020\u0010H\u0002J\b\u0010\u001c\u001a\u00020\u0010H\u0002J\u001a\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u0010H\u0002J\u0010\u0010%\u001a\u00020 2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010&\u001a\u00020 2\u0006\u0010\u000e\u001a\u00020\u000bH\u0002J\b\u0010'\u001a\u00020\u0010H\u0002J\b\u0010(\u001a\u00020\u0010H\u0002J\u0010\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\rH\u0016J\u001a\u0010+\u001a\u00020\u00102\b\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020 H\u0016J\u0012\u0010/\u001a\u00020\u00102\b\u00100\u001a\u0004\u0018\u000101H\u0016J$\u00102\u001a\u0002012\u0006\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u0001062\b\u00107\u001a\u0004\u0018\u000108H\u0016J*\u00109\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010:\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0016J\b\u0010;\u001a\u00020\u0010H\u0002J\b\u0010<\u001a\u00020\u0010H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lcom/deye/fragment/OverseaLoginFragment;", "Lcom/deye/fragment/BaseFragment;", "Landroid/view/View$OnClickListener;", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "Landroid/text/TextWatcher;", "()V", "binding", "Lcom/mxchipapp/databinding/OverseaLoginFragmentBinding;", "credentialManager", "Landroidx/credentials/CredentialManager;", "email", "", "mContext", "Landroid/content/Context;", "password", "afterTextChanged", "", "s", "Landroid/text/Editable;", "authenticateWithGoogleToken", "idToken", "beforeTextChanged", "", "start", "", "count", "after", "doEmailLogin", "doGoogleSignIn", "doLoginSuccess", "token", "weakPwd", "", "handleGoogleSignInResult", "result", "Landroidx/credentials/GetCredentialResponse;", "initView", "isValidEmail", "isValidPassword", "navigateToForgotPassword", "navigateToRegister", "onAttach", "context", "onCheckedChanged", "buttonView", "Landroid/widget/CompoundButton;", "isChecked", "onClick", "v", "Landroid/view/View;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onTextChanged", "before", "setupTermsAgreementText", "updateSignInButtonState", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class OverseaLoginFragment extends BaseFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, TextWatcher {
    private OverseaLoginFragmentBinding binding;
    private CredentialManager credentialManager;
    private String email;
    private Context mContext;
    private String password;
    private static final String TAG = StubApp.getString2(14099);
    private static final String WEB_CLIENT_ID = StubApp.getString2(13772);

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final OverseaLoginFragment newInstance(String str) {
        return INSTANCE.newInstance(str);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    /* compiled from: OverseaLoginFragment.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/deye/fragment/OverseaLoginFragment$Companion;", "", "()V", "TAG", "", "WEB_CLIENT_ID", "newInstance", "Lcom/deye/fragment/OverseaLoginFragment;", "dataToShow", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final OverseaLoginFragment newInstance(String dataToShow) {
            return new OverseaLoginFragment();
        }
    }

    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        this.mContext = context;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, container, savedInstanceState);
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.oversea_login_fragment, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        this.binding = (OverseaLoginFragmentBinding) viewDataBindingInflate;
        CredentialManager.Companion companion = CredentialManager.Companion;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        this.credentialManager = companion.create(contextRequireContext);
        initView();
        OverseaLoginFragmentBinding overseaLoginFragmentBinding = this.binding;
        if (overseaLoginFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            overseaLoginFragmentBinding = null;
        }
        View root = overseaLoginFragmentBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    private final void initView() {
        OverseaLoginFragmentBinding overseaLoginFragmentBinding = this.binding;
        String string2 = StubApp.getString2(13474);
        OverseaLoginFragmentBinding overseaLoginFragmentBinding2 = null;
        if (overseaLoginFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            overseaLoginFragmentBinding = null;
        }
        OverseaLoginFragment overseaLoginFragment = this;
        overseaLoginFragmentBinding.btnSignIn.setOnClickListener(overseaLoginFragment);
        OverseaLoginFragmentBinding overseaLoginFragmentBinding3 = this.binding;
        if (overseaLoginFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            overseaLoginFragmentBinding3 = null;
        }
        overseaLoginFragmentBinding3.tvForgotPassword.setOnClickListener(overseaLoginFragment);
        OverseaLoginFragmentBinding overseaLoginFragmentBinding4 = this.binding;
        if (overseaLoginFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            overseaLoginFragmentBinding4 = null;
        }
        overseaLoginFragmentBinding4.tvSignUp.setOnClickListener(overseaLoginFragment);
        OverseaLoginFragmentBinding overseaLoginFragmentBinding5 = this.binding;
        if (overseaLoginFragmentBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            overseaLoginFragmentBinding5 = null;
        }
        overseaLoginFragmentBinding5.btnGoogleSignIn.setOnClickListener(overseaLoginFragment);
        setupTermsAgreementText();
        OverseaLoginFragmentBinding overseaLoginFragmentBinding6 = this.binding;
        if (overseaLoginFragmentBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            overseaLoginFragmentBinding6 = null;
        }
        OverseaLoginFragment overseaLoginFragment2 = this;
        overseaLoginFragmentBinding6.etEmail.addTextChangedListener(overseaLoginFragment2);
        OverseaLoginFragmentBinding overseaLoginFragmentBinding7 = this.binding;
        if (overseaLoginFragmentBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            overseaLoginFragmentBinding7 = null;
        }
        overseaLoginFragmentBinding7.etPassword.addTextChangedListener(overseaLoginFragment2);
        OverseaLoginFragmentBinding overseaLoginFragmentBinding8 = this.binding;
        if (overseaLoginFragmentBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            overseaLoginFragmentBinding8 = null;
        }
        overseaLoginFragmentBinding8.cbTerms.setOnCheckedChangeListener(this);
        OverseaLoginFragmentBinding overseaLoginFragmentBinding9 = this.binding;
        if (overseaLoginFragmentBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            overseaLoginFragmentBinding9 = null;
        }
        overseaLoginFragmentBinding9.cbHiddenPwd.setBackground(null);
        OverseaLoginFragmentBinding overseaLoginFragmentBinding10 = this.binding;
        if (overseaLoginFragmentBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            overseaLoginFragmentBinding10 = null;
        }
        overseaLoginFragmentBinding10.cbHiddenPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.deye.fragment.OverseaLoginFragment$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                OverseaLoginFragment.initView$lambda$0(this.f$0, compoundButton, z);
            }
        });
        OverseaLoginFragmentBinding overseaLoginFragmentBinding11 = this.binding;
        if (overseaLoginFragmentBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            overseaLoginFragmentBinding2 = overseaLoginFragmentBinding11;
        }
        overseaLoginFragmentBinding2.btnSignIn.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$0(OverseaLoginFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OverseaLoginFragmentBinding overseaLoginFragmentBinding = this$0.binding;
        OverseaLoginFragmentBinding overseaLoginFragmentBinding2 = null;
        String string2 = StubApp.getString2(13474);
        if (overseaLoginFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            overseaLoginFragmentBinding = null;
        }
        CustomFontEditText customFontEditText = overseaLoginFragmentBinding.etPassword;
        OverseaLoginFragmentBinding overseaLoginFragmentBinding3 = this$0.binding;
        if (overseaLoginFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            overseaLoginFragmentBinding3 = null;
        }
        customFontEditText.setSelection(String.valueOf(overseaLoginFragmentBinding3.etPassword.getText()).length());
        if (z) {
            OverseaLoginFragmentBinding overseaLoginFragmentBinding4 = this$0.binding;
            if (overseaLoginFragmentBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                overseaLoginFragmentBinding2 = overseaLoginFragmentBinding4;
            }
            overseaLoginFragmentBinding2.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            return;
        }
        OverseaLoginFragmentBinding overseaLoginFragmentBinding5 = this$0.binding;
        if (overseaLoginFragmentBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            overseaLoginFragmentBinding2 = overseaLoginFragmentBinding5;
        }
        overseaLoginFragmentBinding2.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    private final void setupTermsAgreementText() {
        String string = getString(R.string.i_have_read_and_agree);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = getString(R.string.service_agreement_text);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = getString(R.string.privacy_policy_text);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        String str = string + string2 + StubApp.getString2(13719) + string3;
        SpannableString spannableString = new SpannableString(str);
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        final int color = ContextCompat.getColor(context, R.color.color_dark);
        int length = string.length();
        int length2 = string2.length() + length;
        int i = length2 + 3;
        int length3 = str.length();
        spannableString.setSpan(new ForegroundColorSpan(-904651213), 0, length, 33);
        spannableString.setSpan(new ForegroundColorSpan(color), length, length2, 33);
        spannableString.setSpan(new UnderlineSpan(), length, length2, 33);
        spannableString.setSpan(new ClickableSpan() { // from class: com.deye.fragment.OverseaLoginFragment.setupTermsAgreementText.1
            @Override // android.text.style.ClickableSpan
            public void onClick(View widget) {
                Intrinsics.checkNotNullParameter(widget, "widget");
                WebUrlManager webUrlManager = WebUrlManager.INSTANCE;
                Context context2 = OverseaLoginFragment.this.mContext;
                Intrinsics.checkNotNull(context2);
                webUrlManager.openUserAgreement(context2);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                Intrinsics.checkNotNullParameter(ds, "ds");
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
                ds.setColor(color);
            }
        }, length, length2, 33);
        spannableString.setSpan(new ForegroundColorSpan(color), length2, i, 33);
        spannableString.setSpan(new ForegroundColorSpan(color), i, length3, 33);
        spannableString.setSpan(new UnderlineSpan(), i, length3, 33);
        spannableString.setSpan(new ClickableSpan() { // from class: com.deye.fragment.OverseaLoginFragment.setupTermsAgreementText.2
            @Override // android.text.style.ClickableSpan
            public void onClick(View widget) {
                Intrinsics.checkNotNullParameter(widget, "widget");
                WebUrlManager webUrlManager = WebUrlManager.INSTANCE;
                Context context2 = OverseaLoginFragment.this.mContext;
                Intrinsics.checkNotNull(context2);
                webUrlManager.openPrivacyPolicy(context2);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                Intrinsics.checkNotNullParameter(ds, "ds");
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
                ds.setColor(color);
            }
        }, i, length3, 33);
        OverseaLoginFragmentBinding overseaLoginFragmentBinding = this.binding;
        OverseaLoginFragmentBinding overseaLoginFragmentBinding2 = null;
        String string22 = StubApp.getString2(13474);
        if (overseaLoginFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            overseaLoginFragmentBinding = null;
        }
        overseaLoginFragmentBinding.tvTermsAgreement.setText(spannableString);
        OverseaLoginFragmentBinding overseaLoginFragmentBinding3 = this.binding;
        if (overseaLoginFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            overseaLoginFragmentBinding3 = null;
        }
        overseaLoginFragmentBinding3.tvTermsAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        OverseaLoginFragmentBinding overseaLoginFragmentBinding4 = this.binding;
        if (overseaLoginFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
        } else {
            overseaLoginFragmentBinding2 = overseaLoginFragmentBinding4;
        }
        overseaLoginFragmentBinding2.tvTermsAgreement.setHighlightColor(0);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == 2131361984) {
            doEmailLogin();
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 2131363109) {
            navigateToForgotPassword();
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 2131363194) {
            navigateToRegister();
        } else if (numValueOf != null && numValueOf.intValue() == 2131361973) {
            doGoogleSignIn();
        }
    }

    private final void doGoogleSignIn() {
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        BaseFragment.showLoading$default(this, context, null, 2, null);
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope((LifecycleOwner) this), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(new GetCredentialRequest.Builder().addCredentialOption(new GetSignInWithGoogleOption.Builder(StubApp.getString2(13772)).build()).build(), null), 3, (Object) null);
    }

    /* compiled from: OverseaLoginFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.deye.fragment.OverseaLoginFragment$doGoogleSignIn$1", f = "OverseaLoginFragment.kt", i = {}, l = {TiffUtil.TIFF_TAG_ORIENTATION}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.deye.fragment.OverseaLoginFragment$doGoogleSignIn$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ GetCredentialRequest $request;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(GetCredentialRequest getCredentialRequest, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$request = getCredentialRequest;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return OverseaLoginFragment.this.new AnonymousClass1(this.$request, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            String string2 = StubApp.getString2(14099);
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CredentialManager credentialManager = OverseaLoginFragment.this.credentialManager;
                    if (credentialManager == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("credentialManager");
                        credentialManager = null;
                    }
                    Context contextRequireContext = OverseaLoginFragment.this.requireContext();
                    Intrinsics.checkNotNull(contextRequireContext);
                    this.label = 1;
                    obj = credentialManager.getCredential(contextRequireContext, this.$request, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException(StubApp.getString2(13735));
                    }
                    ResultKt.throwOnFailure(obj);
                }
                OverseaLoginFragment.this.handleGoogleSignInResult((GetCredentialResponse) obj);
            } catch (Exception e) {
                OverseaLoginFragment.this.hideLoading();
                LogUtil.e(string2, StubApp.getString2(13765) + e.getMessage());
                BaseUtils.showShortToast(OverseaLoginFragment.this.mContext, OverseaLoginFragment.this.getString(R.string.google_sign_in_failed));
            } catch (GetCredentialException e2) {
                OverseaLoginFragment.this.hideLoading();
                LogUtil.e(string2, StubApp.getString2(13766) + e2.getMessage());
                BaseUtils.showShortToast(OverseaLoginFragment.this.mContext, OverseaLoginFragment.this.getString(R.string.google_sign_in_failed));
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleGoogleSignInResult(GetCredentialResponse result) {
        String string2 = StubApp.getString2(13776);
        Credential credential = result.getCredential();
        boolean z = credential instanceof CustomCredential;
        String string22 = StubApp.getString2(14099);
        if (z) {
            if (Intrinsics.areEqual(credential.getType(), "com.google.android.libraries.identity.googleid.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL")) {
                try {
                    GoogleIdTokenCredential googleIdTokenCredentialCreateFrom = GoogleIdTokenCredential.Companion.createFrom(credential.getData());
                    String idToken = googleIdTokenCredentialCreateFrom.getIdToken();
                    String id = googleIdTokenCredentialCreateFrom.getId();
                    LogUtil.d(string22, string2 + id);
                    authenticateWithGoogleToken(idToken, id);
                    return;
                } catch (GoogleIdTokenParsingException e) {
                    hideLoading();
                    LogUtil.e(string22, StubApp.getString2(13777) + e.getMessage());
                    BaseUtils.showShortToast(this.mContext, getString(R.string.google_sign_in_failed));
                    return;
                }
            }
            hideLoading();
            LogUtil.e(string22, StubApp.getString2(13778) + credential.getType());
            BaseUtils.showShortToast(this.mContext, getString(R.string.google_sign_in_failed));
            return;
        }
        hideLoading();
        LogUtil.e(string22, StubApp.getString2(13779));
        BaseUtils.showShortToast(this.mContext, getString(R.string.google_sign_in_failed));
    }

    private final void authenticateWithGoogleToken(final String idToken, String email) {
        String jSONString = JSON.toJSONString(MapsKt.mapOf(TuplesKt.to(StubApp.getString2(13684), idToken), TuplesKt.to(StubApp.getString2(5433), Constants.APPID), TuplesKt.to(StubApp.getString2(7682), StubApp.getString2(13693) + MxchipApplication.getInstance().getClientId() + StubApp.getString2(13694))));
        RequestBody.Companion companion = RequestBody.Companion;
        MediaType mediaType = Constants.JSON_Type;
        Intrinsics.checkNotNull(jSONString);
        Intrinsics.checkNotNullExpressionValue(RetrofitManager.INSTANCE.getApiService().loginByGoogle(companion.create(mediaType, jSONString)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() { // from class: com.deye.fragment.OverseaLoginFragment$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                OverseaLoginFragment.authenticateWithGoogleToken$lambda$1(this.f$0);
            }
        }).subscribe(new Consumer() { // from class: com.deye.fragment.OverseaLoginFragment$authenticateWithGoogleToken$api$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<LoginResult> response) throws Resources.NotFoundException {
                Object string2;
                Intrinsics.checkNotNullParameter(response, "response");
                this.this$0.hideLoading();
                BaseResult.MetaBean meta = response.getMeta();
                String string22 = StubApp.getString2(14099);
                if (meta != null && meta.getCode() == 0) {
                    LoginResult data = response.getData();
                    String str = data != null ? data.token : null;
                    LoginResult data2 = response.getData();
                    boolean z = data2 != null ? data2.is_weak_password : false;
                    if (str == null) {
                        BaseUtils.showShortToast(this.this$0.mContext, StubApp.getString2(13682));
                        return;
                    } else {
                        LogUtil.d(string22, StubApp.getString2(14100) + str);
                        this.this$0.doLoginSuccess(str, z);
                        return;
                    }
                }
                BaseResult.MetaBean meta2 = response.getMeta();
                if (meta2 != null && meta2.getCode() == 10310) {
                    LogUtil.d(string22, StubApp.getString2(14101));
                    Intent intent = new Intent(this.this$0.mContext, (Class<?>) BindEmailActivity.class);
                    intent.putExtra(StubApp.getString2(13691), 2);
                    intent.putExtra(StubApp.getString2(13692), idToken);
                    this.this$0.startActivity(intent);
                    return;
                }
                BaseResult.MetaBean meta3 = response.getMeta();
                if (meta3 == null || (string2 = meta3.getMessage()) == null) {
                    string2 = StubApp.getString2(14102);
                }
                BaseUtils.showShortToast(this.this$0.mContext, string2.toString());
            }
        }, new Consumer() { // from class: com.deye.fragment.OverseaLoginFragment$authenticateWithGoogleToken$api$3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable error) {
                Intrinsics.checkNotNullParameter(error, "error");
                this.this$0.hideLoading();
                LogUtil.e(StubApp.getString2(14099), StubApp.getString2(14103) + error.getMessage());
                Context context = this.this$0.mContext;
                String message = error.getMessage();
                if (message == null) {
                    message = StubApp.getString2(13705);
                }
                BaseUtils.showShortToast(context, message);
            }
        }), "subscribe(...)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void authenticateWithGoogleToken$lambda$1(OverseaLoginFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hideLoading();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        updateSignInButtonState();
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable s) {
        OverseaLoginFragmentBinding overseaLoginFragmentBinding = this.binding;
        OverseaLoginFragmentBinding overseaLoginFragmentBinding2 = null;
        String string2 = StubApp.getString2(13474);
        if (overseaLoginFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            overseaLoginFragmentBinding = null;
        }
        this.email = String.valueOf(overseaLoginFragmentBinding.etEmail.getText());
        OverseaLoginFragmentBinding overseaLoginFragmentBinding3 = this.binding;
        if (overseaLoginFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            overseaLoginFragmentBinding2 = overseaLoginFragmentBinding3;
        }
        this.password = String.valueOf(overseaLoginFragmentBinding2.etPassword.getText());
        updateSignInButtonState();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void updateSignInButtonState() {
        boolean z;
        String str = this.email;
        if (str == null) {
            str = "";
        }
        boolean zIsValidEmail = isValidEmail(str);
        OverseaLoginFragmentBinding overseaLoginFragmentBinding = null;
        String string2 = StubApp.getString2(13474);
        if (zIsValidEmail) {
            String str2 = this.password;
            if (isValidPassword(str2 != null ? str2 : "")) {
                OverseaLoginFragmentBinding overseaLoginFragmentBinding2 = this.binding;
                if (overseaLoginFragmentBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    overseaLoginFragmentBinding2 = null;
                }
                if (overseaLoginFragmentBinding2.cbTerms.isChecked()) {
                    z = true;
                }
            }
        } else {
            z = false;
        }
        OverseaLoginFragmentBinding overseaLoginFragmentBinding3 = this.binding;
        if (overseaLoginFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            overseaLoginFragmentBinding = overseaLoginFragmentBinding3;
        }
        overseaLoginFragmentBinding.btnSignIn.setEnabled(z);
    }

    private final boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private final boolean isValidPassword(String password) {
        return password.length() >= 6;
    }

    private final void doEmailLogin() {
        String str = this.email;
        if (str == null) {
            str = "";
        }
        if (!isValidEmail(str)) {
            BaseUtils.showShortToast(this.mContext, getString(R.string.invalid_email));
            return;
        }
        String str2 = this.password;
        if (!isValidPassword(str2 != null ? str2 : "")) {
            BaseUtils.showShortToast(this.mContext, getString(R.string.password_too_short));
            return;
        }
        OverseaLoginFragmentBinding overseaLoginFragmentBinding = this.binding;
        if (overseaLoginFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            overseaLoginFragmentBinding = null;
        }
        if (!overseaLoginFragmentBinding.cbTerms.isChecked()) {
            BaseUtils.showShortToast(this.mContext, getString(R.string.must_accept_terms));
            return;
        }
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        BaseFragment.showLoading$default(this, context, null, 2, null);
        String str3 = StubApp.getString2(13693) + MxchipApplication.getInstance().getClientId() + StubApp.getString2(13694);
        String str4 = this.email;
        Intrinsics.checkNotNull(str4);
        String str5 = this.password;
        Intrinsics.checkNotNull(str5);
        String jSONString = JSON.toJSONString(MapsKt.mapOf(TuplesKt.to(StubApp.getString2(13240), str4), TuplesKt.to(StubApp.getString2(13687), str5), TuplesKt.to(StubApp.getString2(5433), Constants.APPID), TuplesKt.to(StubApp.getString2(7682), str3)));
        RequestBody.Companion companion = RequestBody.Companion;
        MediaType mediaType = Constants.JSON_Type;
        Intrinsics.checkNotNull(jSONString);
        Intrinsics.checkNotNullExpressionValue(RetrofitManager.INSTANCE.getApiService().loginByEmail(companion.create(mediaType, jSONString)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() { // from class: com.deye.fragment.OverseaLoginFragment$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                OverseaLoginFragment.doEmailLogin$lambda$2(this.f$0);
            }
        }).subscribe(new Consumer() { // from class: com.deye.fragment.OverseaLoginFragment$doEmailLogin$retrofit$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<LoginResult> response) throws Resources.NotFoundException {
                Object string2;
                Intrinsics.checkNotNullParameter(response, "response");
                this.this$0.hideLoading();
                BaseResult.MetaBean meta = response.getMeta();
                if (meta != null && meta.getCode() == 0) {
                    LoginResult data = response.getData();
                    String str6 = data != null ? data.token : null;
                    LoginResult data2 = response.getData();
                    boolean z = data2 != null ? data2.is_weak_password : false;
                    if (str6 == null) {
                        BaseUtils.showShortToast(this.this$0.mContext, StubApp.getString2(13682));
                        return;
                    } else {
                        LogUtil.d(StubApp.getString2(14099), StubApp.getString2(14104) + str6);
                        this.this$0.doLoginSuccess(str6, z);
                        return;
                    }
                }
                BaseResult.MetaBean meta2 = response.getMeta();
                if (meta2 == null || (string2 = meta2.getMessage()) == null) {
                    string2 = StubApp.getString2(14105);
                }
                BaseUtils.showShortToast(this.this$0.mContext, string2.toString());
            }
        }, new Consumer() { // from class: com.deye.fragment.OverseaLoginFragment$doEmailLogin$retrofit$3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable error) {
                Intrinsics.checkNotNullParameter(error, "error");
                this.this$0.hideLoading();
                LogUtil.e(StubApp.getString2(14099), StubApp.getString2(14106) + error.getMessage());
                Context context2 = this.this$0.mContext;
                String message = error.getMessage();
                if (message == null) {
                    message = StubApp.getString2(13705);
                }
                BaseUtils.showShortToast(context2, message);
            }
        }), "subscribe(...)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void doEmailLogin$lambda$2(OverseaLoginFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hideLoading();
    }

    static /* synthetic */ void doLoginSuccess$default(OverseaLoginFragment overseaLoginFragment, String str, boolean z, int i, Object obj) throws Resources.NotFoundException {
        if ((i & 2) != 0) {
            z = false;
        }
        overseaLoginFragment.doLoginSuccess(str, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doLoginSuccess(String token, boolean weakPwd) throws Resources.NotFoundException {
        MxchipApplication.getInstance().initPushService();
        DeYeHttpRequestManager.getInstance().setToken(token);
        MMKV.defaultMMKV().encode(StubApp.getString2(13134), token);
        DeYeHttpRequestManager.getInstance().setToken(token);
        BaseUtils.showShortToast(this.mContext, getString(R.string.login_success));
        Intent intent = new Intent();
        intent.putExtra(StubApp.getString2(13690), true);
        intent.putExtra(StubApp.getString2(13223), weakPwd);
        intent.setFlags(268468224);
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        ActivityRouterUtilsKt.routingForTabMainActivity(context, intent);
    }

    private final void navigateToForgotPassword() {
        Intent intent = new Intent(this.mContext, (Class<?>) OverseaRegisterActivity.class);
        intent.putExtra(StubApp.getString2(112), StubApp.getString2(13717));
        startActivity(intent);
    }

    private final void navigateToRegister() {
        Intent intent = new Intent(this.mContext, (Class<?>) OverseaRegisterActivity.class);
        intent.putExtra(StubApp.getString2(112), StubApp.getString2(6636));
        startActivity(intent);
    }
}
