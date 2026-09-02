package com.deye.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import com.deye.MxchipApplication;
import com.deye.activity.login.ForgetPwdActivity;
import com.deye.configs.Constants;
import com.deye.extension.ViewKt;
import com.deye.fragment.LoginFragment;
import com.deye.helper.DialogHelper;
import com.deye.utils.ActivityRouterUtilsKt;
import com.deye.utils.BaseUtils;
import com.deye.utils.ChannelUtil;
import com.deye.utils.MMKVUtils;
import com.deye.utils.RegularUtils;
import com.deye.utils.UmUtils;
import com.deye.utils.WebUrlManager;
import com.mxchipapp.R;
import com.mxchipapp.databinding.LoginFragmentBinding;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.api.http.RetrofitService;
import io.fogcloud.sdk.fog.bean.LoginResult;
import io.fogcloud.sdk.fog.bean.VerificationResultBean;
import io.fogcloud.sdk.fog.bean.WechatReqBean;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

/* compiled from: LoginFragment.kt */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u0000 B2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002BCB\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J(\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0018H\u0016J\u0016\u0010\u001b\u001a\u00020\u00122\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00120\u001dH\u0002J\u0014\u0010\u001e\u001a\u00020\u00122\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00120\u001dJ\u001a\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u000f2\b\b\u0002\u0010!\u001a\u00020\u000bH\u0002J\u000e\u0010\"\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u000fJ\b\u0010#\u001a\u00020\u0012H\u0002J\u0010\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\tH\u0016J\u0018\u0010&\u001a\u00020\u00122\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u000bH\u0016J\u0010\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020,H\u0016J$\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u0001012\b\u00102\u001a\u0004\u0018\u000103H\u0016J(\u00104\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00105\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0016J\u001a\u00106\u001a\u00020\u00122\u0006\u0010+\u001a\u00020,2\b\u00102\u001a\u0004\u0018\u000103H\u0017J\b\u00107\u001a\u00020\u0012H\u0002J\b\u00108\u001a\u00020\u0012H\u0002J\b\u00109\u001a\u00020\u0012H\u0002J\b\u0010:\u001a\u00020\u0012H\u0002J\b\u0010;\u001a\u00020\u0012H\u0002J\u0010\u0010<\u001a\u00020\u00122\u0006\u0010=\u001a\u00020\u000fH\u0003J\u0018\u0010>\u001a\u00020\u00122\u0006\u0010?\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020\u000fH\u0002J\b\u0010A\u001a\u00020\u0012H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lcom/deye/fragment/LoginFragment;", "Lcom/deye/fragment/BaseFragment;", "Landroid/view/View$OnClickListener;", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "Landroid/text/TextWatcher;", "()V", "loginType", "Lcom/deye/fragment/LoginFragment$LoginType;", "mContext", "Landroid/content/Context;", "mIsFirstPage", "", "mLoginAtyBinding", "Lcom/mxchipapp/databinding/LoginFragmentBinding;", "mPassword", "", "nUserName", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "checkAgreeProtocol", "callBack", "Lkotlin/Function0;", "doAgree", "doLoginSuc", "token", "weakPwd", "doOneKeyLogin", "initView", "onAttach", "context", "onCheckedChanged", "compoundButton", "Landroid/widget/CompoundButton;", "b", "onClick", "view", "Landroid/view/View;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onTextChanged", "before", "onViewCreated", "setBtnEnterStyle", "toConfirm", "toForgetPage", "toGetCode", "toHomePage", "toPhoneNumLogin", "json", "toVerCodeLogin", "phoneNum", "code", "updateViewByLoginType", "Companion", "LoginType", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class LoginFragment extends BaseFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, TextWatcher {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private LoginType loginType = LoginType.oneKey;
    private Context mContext;
    private boolean mIsFirstPage;
    private LoginFragmentBinding mLoginAtyBinding;
    private String mPassword;
    private String nUserName;

    @JvmStatic
    public static final LoginFragment newInstance(String str) {
        return INSTANCE.newInstance(str);
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable s) {
        Intrinsics.checkNotNullParameter(s, "s");
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Intrinsics.checkNotNullParameter(s, "s");
    }

    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        this.mContext = context;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        boolean zIsOversea = ChannelUtil.isOversea();
        LoginFragmentBinding loginFragmentBinding = null;
        String string2 = StubApp.getString2(14081);
        if (zIsOversea) {
            this.loginType = LoginType.phoneNum;
            LoginFragmentBinding loginFragmentBinding2 = this.mLoginAtyBinding;
            if (loginFragmentBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                loginFragmentBinding2 = null;
            }
            loginFragmentBinding2.ivWechat.setVisibility(8);
            LoginFragmentBinding loginFragmentBinding3 = this.mLoginAtyBinding;
            if (loginFragmentBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                loginFragmentBinding3 = null;
            }
            loginFragmentBinding3.ivBack.setVisibility(8);
        }
        updateViewByLoginType();
        LoginFragmentBinding loginFragmentBinding4 = this.mLoginAtyBinding;
        if (loginFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding4 = null;
        }
        loginFragmentBinding4.ivWechat.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.LoginFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
                LoginFragment.onViewCreated$lambda$0(this.f$0, view2);
            }
        });
        LoginFragmentBinding loginFragmentBinding5 = this.mLoginAtyBinding;
        if (loginFragmentBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding5 = null;
        }
        loginFragmentBinding5.ivBack.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.LoginFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LoginFragment.onViewCreated$lambda$1(this.f$0, view2);
            }
        });
        LoginFragmentBinding loginFragmentBinding6 = this.mLoginAtyBinding;
        if (loginFragmentBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding6 = null;
        }
        loginFragmentBinding6.ivPhoneLogin.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.LoginFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LoginFragment.onViewCreated$lambda$2(this.f$0, view2);
            }
        });
        LoginFragmentBinding loginFragmentBinding7 = this.mLoginAtyBinding;
        if (loginFragmentBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding7 = null;
        }
        loginFragmentBinding7.tvLoginType.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.LoginFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                LoginFragment.onViewCreated$lambda$3(this.f$0, view2);
            }
        });
        LoginFragmentBinding loginFragmentBinding8 = this.mLoginAtyBinding;
        if (loginFragmentBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding8 = null;
        }
        loginFragmentBinding8.btnOneKeyLogin.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.LoginFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
                LoginFragment.onViewCreated$lambda$4(this.f$0, view2);
            }
        });
        LoginFragmentBinding loginFragmentBinding9 = this.mLoginAtyBinding;
        if (loginFragmentBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            loginFragmentBinding = loginFragmentBinding9;
        }
        loginFragmentBinding.tvGetCode.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.LoginFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
                LoginFragment.onViewCreated$lambda$5(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(final LoginFragment this$0, View view) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.checkAgreeProtocol(new Function0<Unit>() { // from class: com.deye.fragment.LoginFragment$onViewCreated$1$1
            {
                super(0);
            }

            /* compiled from: LoginFragment.kt */
            @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "result", "", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
            /* renamed from: com.deye.fragment.LoginFragment$onViewCreated$1$1$1, reason: invalid class name */
            static final class AnonymousClass1 extends Lambda implements Function1<Map<String, String>, Unit> {
                final /* synthetic */ LoginFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(LoginFragment loginFragment) {
                    super(1);
                    this.this$0 = loginFragment;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Map<String, String> map) {
                    invoke2(map);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Map<String, String> result) {
                    Intrinsics.checkNotNullParameter(result, "result");
                    LoginFragment loginFragment = this.this$0;
                    Context context = loginFragment.mContext;
                    Intrinsics.checkNotNull(context);
                    loginFragment.showLoading(context, StubApp.getString2(14082));
                    final String str = result.get(StubApp.getString2(936));
                    if (str == null) {
                        str = "";
                    }
                    String str2 = result.get(StubApp.getString2(13770));
                    final String str3 = str2 != null ? str2 : "";
                    WechatReqBean wechatReqBean = new WechatReqBean();
                    wechatReqBean.open_id = str;
                    wechatReqBean.access_token = str3;
                    wechatReqBean.pushtype = StubApp.getString2(13695);
                    wechatReqBean.appid = Constants.APPID;
                    WechatReqBean.Extend extend = new WechatReqBean.Extend();
                    extend.cid = MxchipApplication.getInstance().getClientId();
                    wechatReqBean.extend = extend;
                    Flowable<BaseResult<LoginResult>> flowableObserveOn = RetrofitManager.INSTANCE.getApiService().loginByWeChat(wechatReqBean).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                    final LoginFragment loginFragment2 = this.this$0;
                    Flowable<BaseResult<LoginResult>> flowableDoOnComplete = flowableObserveOn.doOnComplete(
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0083: INVOKE (r5v7 'flowableDoOnComplete' io.reactivex.rxjava3.core.Flowable<io.fogcloud.sdk.fog.api.http.BaseResult<io.fogcloud.sdk.fog.bean.LoginResult>>) = 
                          (r5v6 'flowableObserveOn' io.reactivex.rxjava3.core.Flowable<io.fogcloud.sdk.fog.api.http.BaseResult<io.fogcloud.sdk.fog.bean.LoginResult>>)
                          (wrap:io.reactivex.rxjava3.functions.Action:0x0080: CONSTRUCTOR (r2v12 'loginFragment2' com.deye.fragment.LoginFragment A[DONT_INLINE]) A[MD:(com.deye.fragment.LoginFragment):void (m), WRAPPED] call: com.deye.fragment.LoginFragment$onViewCreated$1$1$1$$ExternalSyntheticLambda0.<init>(com.deye.fragment.LoginFragment):void type: CONSTRUCTOR)
                         VIRTUAL call: io.reactivex.rxjava3.core.Flowable.doOnComplete(io.reactivex.rxjava3.functions.Action):io.reactivex.rxjava3.core.Flowable A[DECLARE_VAR, MD:(io.reactivex.rxjava3.functions.Action):io.reactivex.rxjava3.core.Flowable<T> (m)] in method: com.deye.fragment.LoginFragment$onViewCreated$1$1.1.invoke(java.util.Map<java.lang.String, java.lang.String>):void, file: /tmp/deye-apk/unidbg-dump/dex/classes3.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:298)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:277)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.deye.fragment.LoginFragment$onViewCreated$1$1$1$$ExternalSyntheticLambda0, state: NOT_LOADED
                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                        	... 15 more
                        */
                    /*
                        this = this;
                        java.lang.String r0 = "result"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                        com.deye.fragment.LoginFragment r0 = r4.this$0
                        android.content.Context r1 = com.deye.fragment.LoginFragment.access$getMContext$p(r0)
                        kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                        r2 = 14082(0x3702, float:1.9733E-41)
                        java.lang.String r2 = com.stub.StubApp.getString2(r2)
                        r0.showLoading(r1, r2)
                        r0 = 936(0x3a8, float:1.312E-42)
                        java.lang.String r0 = com.stub.StubApp.getString2(r0)
                        java.lang.Object r0 = r5.get(r0)
                        java.lang.String r0 = (java.lang.String) r0
                        java.lang.String r1 = ""
                        if (r0 != 0) goto L2a
                        r0 = r1
                    L2a:
                        r2 = 13770(0x35ca, float:1.9296E-41)
                        java.lang.String r2 = com.stub.StubApp.getString2(r2)
                        java.lang.Object r5 = r5.get(r2)
                        java.lang.String r5 = (java.lang.String) r5
                        if (r5 != 0) goto L3a
                        goto L3b
                    L3a:
                        r1 = r5
                    L3b:
                        io.fogcloud.sdk.fog.bean.WechatReqBean r5 = new io.fogcloud.sdk.fog.bean.WechatReqBean
                        r5.<init>()
                        r5.open_id = r0
                        r5.access_token = r1
                        r2 = 13695(0x357f, float:1.9191E-41)
                        java.lang.String r2 = com.stub.StubApp.getString2(r2)
                        r5.pushtype = r2
                        java.lang.String r2 = com.deye.configs.Constants.APPID
                        r5.appid = r2
                        io.fogcloud.sdk.fog.bean.WechatReqBean$Extend r2 = new io.fogcloud.sdk.fog.bean.WechatReqBean$Extend
                        r2.<init>()
                        com.deye.MxchipApplication r3 = com.deye.MxchipApplication.getInstance()
                        java.lang.String r3 = r3.getClientId()
                        r2.cid = r3
                        r5.extend = r2
                        io.fogcloud.sdk.fog.api.http.RetrofitManager r2 = io.fogcloud.sdk.fog.api.http.RetrofitManager.INSTANCE
                        io.fogcloud.sdk.fog.api.http.RetrofitService r2 = r2.getApiService()
                        io.reactivex.rxjava3.core.Flowable r5 = r2.loginByWeChat(r5)
                        io.reactivex.rxjava3.core.Scheduler r2 = io.reactivex.rxjava3.schedulers.Schedulers.io()
                        io.reactivex.rxjava3.core.Flowable r5 = r5.subscribeOn(r2)
                        io.reactivex.rxjava3.core.Scheduler r2 = io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread()
                        io.reactivex.rxjava3.core.Flowable r5 = r5.observeOn(r2)
                        com.deye.fragment.LoginFragment r2 = r4.this$0
                        com.deye.fragment.LoginFragment$onViewCreated$1$1$1$$ExternalSyntheticLambda0 r3 = new com.deye.fragment.LoginFragment$onViewCreated$1$1$1$$ExternalSyntheticLambda0
                        r3.<init>(r2)
                        io.reactivex.rxjava3.core.Flowable r5 = r5.doOnComplete(r3)
                        com.deye.fragment.LoginFragment$onViewCreated$1$1$1$2 r2 = new com.deye.fragment.LoginFragment$onViewCreated$1$1$1$2
                        com.deye.fragment.LoginFragment r3 = r4.this$0
                        r2.<init>()
                        io.reactivex.rxjava3.functions.Consumer r2 = (io.reactivex.rxjava3.functions.Consumer) r2
                        com.deye.fragment.LoginFragment$onViewCreated$1$1$1$3 r0 = new com.deye.fragment.LoginFragment$onViewCreated$1$1$1$3
                        com.deye.fragment.LoginFragment r1 = r4.this$0
                        r0.<init>()
                        io.reactivex.rxjava3.functions.Consumer r0 = (io.reactivex.rxjava3.functions.Consumer) r0
                        r5.subscribe(r2, r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.deye.fragment.LoginFragment$onViewCreated$1$1.AnonymousClass1.invoke2(java.util.Map):void");
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invoke$lambda$0(LoginFragment this$0) {
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    this$0.hideLoading();
                }
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                UmUtils umUtils = UmUtils.INSTANCE;
                FragmentActivity fragmentActivityRequireActivity = this.this$0.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
                umUtils.initWechat((Activity) fragmentActivityRequireActivity, new AnonymousClass1(this.this$0));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(LoginFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.loginType = LoginType.oneKey;
        this$0.updateViewByLoginType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(LoginFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.loginType = LoginType.verificationCode;
        this$0.updateViewByLoginType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$3(LoginFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.loginType == LoginType.phoneNum) {
            this$0.loginType = LoginType.verificationCode;
        } else {
            this$0.loginType = LoginType.phoneNum;
        }
        this$0.updateViewByLoginType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$4(final LoginFragment this$0, View view) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.checkAgreeProtocol(new Function0<Unit>() { // from class: com.deye.fragment.LoginFragment$onViewCreated$5$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                UmUtils.INSTANCE.quitLogin();
                UmUtils umUtils = UmUtils.INSTANCE;
                FragmentActivity fragmentActivityRequireActivity = this.this$0.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
                final LoginFragment loginFragment = this.this$0;
                umUtils.checkOneKeyLoginEnable((Context) fragmentActivityRequireActivity, new Function4<Boolean, Integer, String, String, Unit>() { // from class: com.deye.fragment.LoginFragment$onViewCreated$5$1.1
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Integer num, String str, String str2) {
                        invoke(bool.booleanValue(), num.intValue(), str, str2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z, int i, String msg, String str) {
                        Intrinsics.checkNotNullParameter(msg, "msg");
                        if (str != null) {
                            LoginFragment loginFragment2 = loginFragment;
                            UmUtils.INSTANCE.quitLogin();
                            loginFragment2.doOneKeyLogin(str);
                        }
                        if (!z) {
                            switch (i) {
                                case 600015:
                                    Context context = loginFragment.mContext;
                                    Intrinsics.checkNotNull(context);
                                    BaseUtils.showShortToast(context, StubApp.getString2(14083));
                                    break;
                                case 700000:
                                case 700001:
                                    UmUtils.INSTANCE.quitLogin();
                                    break;
                                default:
                                    Context context2 = loginFragment.mContext;
                                    Intrinsics.checkNotNull(context2);
                                    BaseUtils.showShortToast(context2, msg);
                                    break;
                            }
                            return;
                        }
                        if (i == 600024) {
                            UmUtils umUtils2 = UmUtils.INSTANCE;
                            Context context3 = loginFragment.mContext;
                            Intrinsics.checkNotNull(context3);
                            LoginFragmentBinding loginFragmentBinding = loginFragment.mLoginAtyBinding;
                            LoginFragmentBinding loginFragmentBinding2 = null;
                            String string2 = StubApp.getString2(14081);
                            if (loginFragmentBinding == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string2);
                                loginFragmentBinding = null;
                            }
                            float width = loginFragmentBinding.llRoot.getWidth() * 1.0f;
                            LoginFragmentBinding loginFragmentBinding3 = loginFragment.mLoginAtyBinding;
                            if (loginFragmentBinding3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string2);
                            } else {
                                loginFragmentBinding2 = loginFragmentBinding3;
                            }
                            umUtils2.startOneKeyLogin(context3, width, loginFragmentBinding2.llRoot.getHeight() * 1.6f);
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$5(final LoginFragment this$0, View view) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.checkAgreeProtocol(new Function0<Unit>() { // from class: com.deye.fragment.LoginFragment$onViewCreated$6$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                this.this$0.toGetCode();
            }
        });
    }

    public final void doOneKeyLogin(String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        String str = StubApp.getString2(13693) + MxchipApplication.getInstance().getClientId() + StubApp.getString2(13694);
        Log.e(StubApp.getString2(14097), String.valueOf(Thread.currentThread().getId()));
        RetrofitService apiService = RetrofitManager.INSTANCE.getApiService();
        String APPID = Constants.APPID;
        Intrinsics.checkNotNullExpressionValue(APPID, "APPID");
        Intrinsics.checkNotNullExpressionValue(apiService.oneKeyLogin(APPID, token, StubApp.getString2(14098), str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.deye.fragment.LoginFragment$doOneKeyLogin$flowable$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<LoginResult> it2) throws Resources.NotFoundException {
                Object message;
                String str2;
                Intrinsics.checkNotNullParameter(it2, "it");
                this.this$0.hideLoading();
                BaseResult.MetaBean meta = it2.getMeta();
                if (meta == null || meta.getCode() != 0) {
                    Context context = this.this$0.mContext;
                    BaseResult.MetaBean meta2 = it2.getMeta();
                    BaseUtils.showShortToast(context, (meta2 == null || (message = meta2.getMessage()) == null) ? null : message.toString());
                    return;
                }
                LoginResult data = it2.getData();
                if (data == null || (str2 = data.token) == null) {
                    return;
                }
                LoginFragment loginFragment = this.this$0;
                LoginResult data2 = it2.getData();
                loginFragment.doLoginSuc(str2, data2 != null ? data2.is_weak_password : false);
            }
        }, new Consumer() { // from class: com.deye.fragment.LoginFragment$doOneKeyLogin$flowable$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                it2.printStackTrace();
                this.this$0.hideLoading();
            }
        }), "subscribe(...)");
    }

    private final void checkAgreeProtocol(final Function0<Unit> callBack) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        LoginFragmentBinding loginFragmentBinding = this.mLoginAtyBinding;
        if (loginFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLoginAtyBinding");
            loginFragmentBinding = null;
        }
        if (loginFragmentBinding.cbConfirmProtocol.isChecked()) {
            doAgree(callBack);
        } else {
            DialogHelper.showProtocolTipDialog(getActivity(), new DialogHelper.OnDialogListener() { // from class: com.deye.fragment.LoginFragment.checkAgreeProtocol.1
                @Override // com.deye.helper.DialogHelper.OnDialogListener
                public void onSure(String text) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
                    LoginFragmentBinding loginFragmentBinding2 = LoginFragment.this.mLoginAtyBinding;
                    if (loginFragmentBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mLoginAtyBinding");
                        loginFragmentBinding2 = null;
                    }
                    loginFragmentBinding2.cbConfirmProtocol.setChecked(true);
                    LoginFragment.this.doAgree(callBack);
                }

                @Override // com.deye.helper.DialogHelper.OnDialogListener
                public void onCancel() {
                    FragmentActivity activity = LoginFragment.this.getActivity();
                    final LoginFragment loginFragment = LoginFragment.this;
                    final Function0<Unit> function0 = callBack;
                    DialogHelper.showSecondaryProtocolDialog(activity, new DialogHelper.OnDialogListener() { // from class: com.deye.fragment.LoginFragment$checkAgreeProtocol$1$onCancel$1
                        @Override // com.deye.helper.DialogHelper.OnDialogListener
                        public void onSure(String text) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
                            LoginFragmentBinding loginFragmentBinding2 = loginFragment.mLoginAtyBinding;
                            if (loginFragmentBinding2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mLoginAtyBinding");
                                loginFragmentBinding2 = null;
                            }
                            loginFragmentBinding2.cbConfirmProtocol.setChecked(true);
                            loginFragment.doAgree(function0);
                        }
                    });
                }
            });
        }
    }

    public final void doAgree(Function0<Unit> callBack) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        UmUtils umUtils = UmUtils.INSTANCE;
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        umUtils.initUm(context);
        MMKVUtils.INSTANCE.setAgreePolicy();
        callBack.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toGetCode() {
        LoginFragmentBinding loginFragmentBinding = this.mLoginAtyBinding;
        LoginFragmentBinding loginFragmentBinding2 = null;
        String string2 = StubApp.getString2(14081);
        if (loginFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding = null;
        }
        Editable text = loginFragmentBinding.edPhoneNumber.getText();
        if (!RegularUtils.isPhone(String.valueOf(text))) {
            BaseUtils.showShortToast(requireActivity(), StubApp.getString2(14094));
            return;
        }
        LoginFragmentBinding loginFragmentBinding3 = this.mLoginAtyBinding;
        if (loginFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            loginFragmentBinding2 = loginFragmentBinding3;
        }
        loginFragmentBinding2.tvGetCode.start();
        RetrofitService apiService = RetrofitManager.INSTANCE.getApiService();
        String APPID = Constants.APPID;
        Intrinsics.checkNotNullExpressionValue(APPID, "APPID");
        Intrinsics.checkNotNullExpressionValue(apiService.getVerCode(APPID, String.valueOf(text)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.deye.fragment.LoginFragment$toGetCode$flowable$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<VerificationResultBean> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                if (it2.getData() != null) {
                    LoginFragment loginFragment = this.this$0;
                    BaseUtils.showShortToast(loginFragment.mContext, StubApp.getString2(14084));
                    if (loginFragment.loginType == LoginFragment.LoginType.verificationCode) {
                        LoginFragmentBinding loginFragmentBinding4 = loginFragment.mLoginAtyBinding;
                        if (loginFragmentBinding4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mLoginAtyBinding");
                            loginFragmentBinding4 = null;
                        }
                        loginFragmentBinding4.edCode.requestFocus();
                    }
                }
            }
        }, new Consumer() { // from class: com.deye.fragment.LoginFragment$toGetCode$flowable$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                it2.printStackTrace();
            }
        }), "subscribe(...)");
    }

    private final void updateViewByLoginType() {
        LoginFragmentBinding loginFragmentBinding = this.mLoginAtyBinding;
        LoginFragmentBinding loginFragmentBinding2 = null;
        String string2 = StubApp.getString2(14081);
        if (loginFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding = null;
        }
        loginFragmentBinding.cbConfirmProtocol.setChecked(false);
        LoginFragmentBinding loginFragmentBinding3 = this.mLoginAtyBinding;
        if (loginFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding3 = null;
        }
        loginFragmentBinding3.ivBack.setVisibility(this.loginType == LoginType.oneKey ? 8 : 0);
        LoginFragmentBinding loginFragmentBinding4 = this.mLoginAtyBinding;
        if (loginFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding4 = null;
        }
        loginFragmentBinding4.tvLoginTip.setVisibility(this.loginType == LoginType.phoneNum ? 8 : 0);
        if (this.loginType == LoginType.oneKey) {
            LoginFragmentBinding loginFragmentBinding5 = this.mLoginAtyBinding;
            if (loginFragmentBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                loginFragmentBinding5 = null;
            }
            loginFragmentBinding5.llPhone.setVisibility(8);
            LoginFragmentBinding loginFragmentBinding6 = this.mLoginAtyBinding;
            if (loginFragmentBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                loginFragmentBinding6 = null;
            }
            loginFragmentBinding6.btnOneKeyLogin.setVisibility(0);
            LoginFragmentBinding loginFragmentBinding7 = this.mLoginAtyBinding;
            if (loginFragmentBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                loginFragmentBinding2 = loginFragmentBinding7;
            }
            loginFragmentBinding2.ivPhoneLogin.setVisibility(0);
        } else {
            LoginFragmentBinding loginFragmentBinding8 = this.mLoginAtyBinding;
            if (loginFragmentBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                loginFragmentBinding8 = null;
            }
            loginFragmentBinding8.ivPhoneLogin.setVisibility(8);
            LoginFragmentBinding loginFragmentBinding9 = this.mLoginAtyBinding;
            if (loginFragmentBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                loginFragmentBinding9 = null;
            }
            loginFragmentBinding9.llPhone.setVisibility(0);
            LoginFragmentBinding loginFragmentBinding10 = this.mLoginAtyBinding;
            if (loginFragmentBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                loginFragmentBinding10 = null;
            }
            loginFragmentBinding10.btnOneKeyLogin.setVisibility(8);
            if (this.loginType == LoginType.phoneNum) {
                LoginFragmentBinding loginFragmentBinding11 = this.mLoginAtyBinding;
                if (loginFragmentBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    loginFragmentBinding11 = null;
                }
                loginFragmentBinding11.rlPassword.setVisibility(0);
                LoginFragmentBinding loginFragmentBinding12 = this.mLoginAtyBinding;
                if (loginFragmentBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    loginFragmentBinding12 = null;
                }
                loginFragmentBinding12.rlVerificationCode.setVisibility(8);
                LoginFragmentBinding loginFragmentBinding13 = this.mLoginAtyBinding;
                if (loginFragmentBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    loginFragmentBinding13 = null;
                }
                loginFragmentBinding13.tvLoginType.setText(StubApp.getString2(14095));
                LoginFragmentBinding loginFragmentBinding14 = this.mLoginAtyBinding;
                if (loginFragmentBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                } else {
                    loginFragmentBinding2 = loginFragmentBinding14;
                }
                loginFragmentBinding2.tvForgetPassword.setVisibility(0);
            } else if (this.loginType == LoginType.verificationCode) {
                LoginFragmentBinding loginFragmentBinding15 = this.mLoginAtyBinding;
                if (loginFragmentBinding15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    loginFragmentBinding15 = null;
                }
                loginFragmentBinding15.rlPassword.setVisibility(8);
                LoginFragmentBinding loginFragmentBinding16 = this.mLoginAtyBinding;
                if (loginFragmentBinding16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    loginFragmentBinding16 = null;
                }
                loginFragmentBinding16.rlVerificationCode.setVisibility(0);
                LoginFragmentBinding loginFragmentBinding17 = this.mLoginAtyBinding;
                if (loginFragmentBinding17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    loginFragmentBinding17 = null;
                }
                loginFragmentBinding17.tvLoginType.setText(StubApp.getString2(14096));
                LoginFragmentBinding loginFragmentBinding18 = this.mLoginAtyBinding;
                if (loginFragmentBinding18 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    loginFragmentBinding18 = null;
                }
                loginFragmentBinding18.tvForgetPassword.setVisibility(8);
                LoginFragmentBinding loginFragmentBinding19 = this.mLoginAtyBinding;
                if (loginFragmentBinding19 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    loginFragmentBinding19 = null;
                }
                loginFragmentBinding19.btnLogin.isEnabled();
                LoginFragmentBinding loginFragmentBinding20 = this.mLoginAtyBinding;
                if (loginFragmentBinding20 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    loginFragmentBinding20 = null;
                }
                Editable text = loginFragmentBinding20.edPhoneNumber.getText();
                if (text != null && text.length() == 11) {
                    LoginFragmentBinding loginFragmentBinding21 = this.mLoginAtyBinding;
                    if (loginFragmentBinding21 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                    } else {
                        loginFragmentBinding2 = loginFragmentBinding21;
                    }
                    loginFragmentBinding2.tvGetCode.setEnabled(true);
                } else {
                    LoginFragmentBinding loginFragmentBinding22 = this.mLoginAtyBinding;
                    if (loginFragmentBinding22 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                    } else {
                        loginFragmentBinding2 = loginFragmentBinding22;
                    }
                    loginFragmentBinding2.tvGetCode.setEnabled(false);
                }
            }
        }
        setBtnEnterStyle();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, container, savedInstanceState);
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        this.mLoginAtyBinding = (LoginFragmentBinding) viewDataBindingInflate;
        initView();
        LoginFragmentBinding loginFragmentBinding = this.mLoginAtyBinding;
        if (loginFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLoginAtyBinding");
            loginFragmentBinding = null;
        }
        View root = loginFragmentBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    private final void initView() {
        Intent intent;
        Log.d(StubApp.getString2(1279), StubApp.getString2(14089));
        FragmentActivity activity = getActivity();
        this.mIsFirstPage = (activity == null || (intent = activity.getIntent()) == null || !intent.getBooleanExtra(StubApp.getString2(13236), false)) ? false : true;
        LoginFragmentBinding loginFragmentBinding = this.mLoginAtyBinding;
        String string2 = StubApp.getString2(14081);
        LoginFragmentBinding loginFragmentBinding2 = null;
        if (loginFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding = null;
        }
        LoginFragment loginFragment = this;
        loginFragmentBinding.tvForgetPassword.setOnClickListener(loginFragment);
        LoginFragmentBinding loginFragmentBinding3 = this.mLoginAtyBinding;
        if (loginFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding3 = null;
        }
        loginFragmentBinding3.tvRegisterProtocol.setOnClickListener(loginFragment);
        LoginFragmentBinding loginFragmentBinding4 = this.mLoginAtyBinding;
        if (loginFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding4 = null;
        }
        loginFragmentBinding4.tvPrivacyPolicy.setOnClickListener(loginFragment);
        LoginFragmentBinding loginFragmentBinding5 = this.mLoginAtyBinding;
        if (loginFragmentBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding5 = null;
        }
        LoginFragment loginFragment2 = this;
        loginFragmentBinding5.edPassword.addTextChangedListener(loginFragment2);
        LoginFragmentBinding loginFragmentBinding6 = this.mLoginAtyBinding;
        if (loginFragmentBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding6 = null;
        }
        loginFragmentBinding6.edPhoneNumber.addTextChangedListener(new TextWatcher() { // from class: com.deye.fragment.LoginFragment.initView.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LoginFragment.this.setBtnEnterStyle();
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                LoginFragmentBinding loginFragmentBinding7 = null;
                String string22 = StubApp.getString2(14081);
                if (s != null && s.length() == 11 && LoginFragment.this.loginType == LoginType.phoneNum) {
                    LoginFragmentBinding loginFragmentBinding8 = LoginFragment.this.mLoginAtyBinding;
                    if (loginFragmentBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string22);
                        loginFragmentBinding8 = null;
                    }
                    loginFragmentBinding8.edPassword.requestFocus();
                }
                if (s == null || s.length() != 11 || LoginFragment.this.loginType != LoginType.verificationCode) {
                    LoginFragmentBinding loginFragmentBinding9 = LoginFragment.this.mLoginAtyBinding;
                    if (loginFragmentBinding9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string22);
                    } else {
                        loginFragmentBinding7 = loginFragmentBinding9;
                    }
                    loginFragmentBinding7.tvGetCode.setEnabled(false);
                    return;
                }
                LoginFragmentBinding loginFragmentBinding10 = LoginFragment.this.mLoginAtyBinding;
                if (loginFragmentBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string22);
                } else {
                    loginFragmentBinding7 = loginFragmentBinding10;
                }
                loginFragmentBinding7.tvGetCode.setEnabled(true);
            }
        });
        LoginFragmentBinding loginFragmentBinding7 = this.mLoginAtyBinding;
        if (loginFragmentBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding7 = null;
        }
        loginFragmentBinding7.edCode.addTextChangedListener(loginFragment2);
        LoginFragmentBinding loginFragmentBinding8 = this.mLoginAtyBinding;
        if (loginFragmentBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding8 = null;
        }
        Button btnLogin = loginFragmentBinding8.btnLogin;
        Intrinsics.checkNotNullExpressionValue(btnLogin, "btnLogin");
        ViewKt.setDebounceClickListener$default(btnLogin, loginFragment, 0L, 2, null);
        LoginFragmentBinding loginFragmentBinding9 = this.mLoginAtyBinding;
        if (loginFragmentBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding9 = null;
        }
        loginFragmentBinding9.btnLogin.setEnabled(false);
        LoginFragmentBinding loginFragmentBinding10 = this.mLoginAtyBinding;
        if (loginFragmentBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding10 = null;
        }
        loginFragmentBinding10.btnOneKeyLogin.setEnabled(true);
        setBtnEnterStyle();
        LoginFragmentBinding loginFragmentBinding11 = this.mLoginAtyBinding;
        if (loginFragmentBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding11 = null;
        }
        loginFragmentBinding11.tvRegisterProtocolTip.setOnClickListener(loginFragment);
        LoginFragmentBinding loginFragmentBinding12 = this.mLoginAtyBinding;
        if (loginFragmentBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding12 = null;
        }
        LoginFragment loginFragment3 = this;
        loginFragmentBinding12.cbConfirmProtocol.setOnCheckedChangeListener(loginFragment3);
        LoginFragmentBinding loginFragmentBinding13 = this.mLoginAtyBinding;
        if (loginFragmentBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding13 = null;
        }
        loginFragmentBinding13.cbHiddenPwd.setBackground(null);
        LoginFragmentBinding loginFragmentBinding14 = this.mLoginAtyBinding;
        if (loginFragmentBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            loginFragmentBinding2 = loginFragmentBinding14;
        }
        loginFragmentBinding2.cbHiddenPwd.setOnCheckedChangeListener(loginFragment3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBtnEnterStyle() {
        LoginFragmentBinding loginFragmentBinding = this.mLoginAtyBinding;
        LoginFragmentBinding loginFragmentBinding2 = null;
        String string2 = StubApp.getString2(14081);
        if (loginFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding = null;
        }
        String strValueOf = String.valueOf(loginFragmentBinding.edPhoneNumber.getText());
        int length = strValueOf.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) strValueOf.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                } else {
                    length--;
                }
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        String string = strValueOf.subSequence(i, length + 1).toString();
        LoginFragmentBinding loginFragmentBinding3 = this.mLoginAtyBinding;
        if (loginFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding3 = null;
        }
        String strValueOf2 = String.valueOf(loginFragmentBinding3.edPassword.getText());
        int length2 = strValueOf2.length() - 1;
        int i2 = 0;
        boolean z3 = false;
        while (i2 <= length2) {
            boolean z4 = Intrinsics.compare((int) strValueOf2.charAt(!z3 ? i2 : length2), 32) <= 0;
            if (z3) {
                if (!z4) {
                    break;
                } else {
                    length2--;
                }
            } else if (z4) {
                i2++;
            } else {
                z3 = true;
            }
        }
        String string3 = strValueOf2.subSequence(i2, length2 + 1).toString();
        LoginFragmentBinding loginFragmentBinding4 = this.mLoginAtyBinding;
        if (loginFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding4 = null;
        }
        String string4 = StringsKt.trim(String.valueOf(loginFragmentBinding4.edCode.getText())).toString();
        if (RegularUtils.isPhone(string)) {
            if (this.loginType == LoginType.phoneNum) {
                LoginFragmentBinding loginFragmentBinding5 = this.mLoginAtyBinding;
                if (loginFragmentBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                } else {
                    loginFragmentBinding2 = loginFragmentBinding5;
                }
                loginFragmentBinding2.btnLogin.setEnabled(string3.length() > 0);
                return;
            }
            LoginFragmentBinding loginFragmentBinding6 = this.mLoginAtyBinding;
            if (loginFragmentBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                loginFragmentBinding2 = loginFragmentBinding6;
            }
            loginFragmentBinding2.btnLogin.setEnabled(string4.length() == 6);
            return;
        }
        LoginFragmentBinding loginFragmentBinding7 = this.mLoginAtyBinding;
        if (loginFragmentBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            loginFragmentBinding2 = loginFragmentBinding7;
        }
        loginFragmentBinding2.btnLogin.setEnabled(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(view, "view");
        switch (view.getId()) {
            case R.id.btn_login /* 2131361976 */:
                checkAgreeProtocol(new Function0<Unit>() { // from class: com.deye.fragment.LoginFragment.onClick.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        LoginFragment.this.toConfirm();
                    }
                });
                break;
            case R.id.tv_forget_password /* 2131363108 */:
                toForgetPage();
                break;
            case R.id.tv_privacy_policy /* 2131363166 */:
                WebUrlManager webUrlManager = WebUrlManager.INSTANCE;
                Context context = this.mContext;
                Intrinsics.checkNotNull(context);
                webUrlManager.openPrivacyPolicy(context);
                break;
            case R.id.tv_register_protocol /* 2131363173 */:
                WebUrlManager webUrlManager2 = WebUrlManager.INSTANCE;
                Context context2 = this.mContext;
                Intrinsics.checkNotNull(context2);
                webUrlManager2.openUserAgreement(context2);
                break;
            case R.id.tv_register_protocol_tip /* 2131363174 */:
                LoginFragmentBinding loginFragmentBinding = this.mLoginAtyBinding;
                LoginFragmentBinding loginFragmentBinding2 = null;
                String string2 = StubApp.getString2(14081);
                if (loginFragmentBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    loginFragmentBinding = null;
                }
                boolean zIsChecked = loginFragmentBinding.cbConfirmProtocol.isChecked();
                LoginFragmentBinding loginFragmentBinding3 = this.mLoginAtyBinding;
                if (loginFragmentBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                } else {
                    loginFragmentBinding2 = loginFragmentBinding3;
                }
                loginFragmentBinding2.cbConfirmProtocol.setChecked(!zIsChecked);
                break;
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Intrinsics.checkNotNullParameter(compoundButton, "compoundButton");
        int id = compoundButton.getId();
        LoginFragmentBinding loginFragmentBinding = null;
        String string2 = StubApp.getString2(14081);
        if (id == 2131361998) {
            LoginFragmentBinding loginFragmentBinding2 = this.mLoginAtyBinding;
            if (loginFragmentBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                loginFragmentBinding = loginFragmentBinding2;
            }
            loginFragmentBinding.cbConfirmProtocol.setChecked(b);
            setBtnEnterStyle();
            return;
        }
        if (id != 2131362002) {
            return;
        }
        if (b) {
            LoginFragmentBinding loginFragmentBinding3 = this.mLoginAtyBinding;
            if (loginFragmentBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                loginFragmentBinding = loginFragmentBinding3;
            }
            loginFragmentBinding.edPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            return;
        }
        LoginFragmentBinding loginFragmentBinding4 = this.mLoginAtyBinding;
        if (loginFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            loginFragmentBinding = loginFragmentBinding4;
        }
        loginFragmentBinding.edPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toConfirm() {
        LoginFragmentBinding loginFragmentBinding = this.mLoginAtyBinding;
        LoginFragmentBinding loginFragmentBinding2 = null;
        String string2 = StubApp.getString2(14081);
        if (loginFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding = null;
        }
        String strValueOf = String.valueOf(loginFragmentBinding.edPhoneNumber.getText());
        int length = strValueOf.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) strValueOf.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                } else {
                    length--;
                }
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        this.nUserName = strValueOf.subSequence(i, length + 1).toString();
        LoginFragmentBinding loginFragmentBinding3 = this.mLoginAtyBinding;
        if (loginFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            loginFragmentBinding3 = null;
        }
        String strValueOf2 = String.valueOf(loginFragmentBinding3.edPassword.getText());
        int length2 = strValueOf2.length() - 1;
        int i2 = 0;
        boolean z3 = false;
        while (i2 <= length2) {
            boolean z4 = Intrinsics.compare((int) strValueOf2.charAt(!z3 ? i2 : length2), 32) <= 0;
            if (z3) {
                if (!z4) {
                    break;
                } else {
                    length2--;
                }
            } else if (z4) {
                i2++;
            } else {
                z3 = true;
            }
        }
        this.mPassword = strValueOf2.subSequence(i2, length2 + 1).toString();
        if (BaseUtils.isNullString(this.nUserName) || !RegularUtils.isPhone(this.nUserName)) {
            BaseUtils.showShortToast(this.mContext, StubApp.getString2(14093));
            return;
        }
        LoginFragmentBinding loginFragmentBinding4 = this.mLoginAtyBinding;
        if (loginFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            loginFragmentBinding2 = loginFragmentBinding4;
        }
        String string = StringsKt.trim(String.valueOf(loginFragmentBinding2.edCode.getText())).toString();
        if (this.loginType == LoginType.verificationCode) {
            if (string.length() == 0) {
                BaseUtils.showShortToast(this.mContext, StubApp.getString2(14090));
                return;
            }
            String str = this.nUserName;
            Intrinsics.checkNotNull(str);
            toVerCodeLogin(str, string);
            return;
        }
        if (!BaseUtils.isNullString(this.mPassword)) {
            String str2 = StubApp.getString2(13693) + MxchipApplication.getInstance().getClientId() + StubApp.getString2(13694);
            Log.d(StubApp.getString2(14091), str2);
            toPhoneNumLogin(str2);
            return;
        }
        BaseUtils.showShortToast(this.mContext, StubApp.getString2(14092));
    }

    private final void toVerCodeLogin(String phoneNum, String code) {
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        showLoading(context, StubApp.getString2(14082));
        String str = StubApp.getString2(13693) + MxchipApplication.getInstance().getClientId() + StubApp.getString2(13694);
        RetrofitService apiService = RetrofitManager.INSTANCE.getApiService();
        String APPID = Constants.APPID;
        Intrinsics.checkNotNullExpressionValue(APPID, "APPID");
        Intrinsics.checkNotNullExpressionValue(apiService.loginByCode(code, phoneNum, APPID, str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.deye.fragment.LoginFragment$toVerCodeLogin$flowable$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<LoginResult> it2) throws Resources.NotFoundException {
                Object message;
                String str2;
                Intrinsics.checkNotNullParameter(it2, "it");
                this.this$0.hideLoading();
                BaseResult.MetaBean meta = it2.getMeta();
                if (meta == null || meta.getCode() != 0) {
                    Context context2 = this.this$0.mContext;
                    BaseResult.MetaBean meta2 = it2.getMeta();
                    BaseUtils.showShortToast(context2, (meta2 == null || (message = meta2.getMessage()) == null) ? null : message.toString());
                    return;
                }
                LoginResult data = it2.getData();
                if (data == null || (str2 = data.token) == null) {
                    return;
                }
                LoginFragment loginFragment = this.this$0;
                LoginResult data2 = it2.getData();
                loginFragment.doLoginSuc(str2, data2 != null ? data2.is_weak_password : false);
            }
        }, new Consumer() { // from class: com.deye.fragment.LoginFragment$toVerCodeLogin$flowable$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                this.this$0.hideLoading();
                it2.printStackTrace();
            }
        }), "subscribe(...)");
    }

    static /* synthetic */ void doLoginSuc$default(LoginFragment loginFragment, String str, boolean z, int i, Object obj) throws Resources.NotFoundException {
        if ((i & 2) != 0) {
            z = false;
        }
        loginFragment.doLoginSuc(str, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doLoginSuc(String token, boolean weakPwd) throws Resources.NotFoundException {
        MxchipApplication.getInstance().initPushService();
        DeYeHttpRequestManager.getInstance().setToken(token);
        BaseUtils.showShortToast(this.mContext, StubApp.getString2(14088));
        MMKV.defaultMMKV().encode(StubApp.getString2(13134), token);
        DeYeHttpRequestManager.getInstance().setToken(token);
        Intent intent = new Intent();
        intent.putExtra(StubApp.getString2(13690), true);
        intent.putExtra(StubApp.getString2(13223), weakPwd);
        intent.setFlags(268468224);
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        ActivityRouterUtilsKt.routingForTabMainActivity(context, intent);
    }

    private final void toPhoneNumLogin(String json) {
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        BaseFragment.showLoading$default(this, context, null, 2, null);
        RetrofitService apiService = RetrofitManager.INSTANCE.getApiService();
        String str = this.nUserName;
        Intrinsics.checkNotNull(str);
        String str2 = this.mPassword;
        Intrinsics.checkNotNull(str2);
        String APPID = Constants.APPID;
        Intrinsics.checkNotNullExpressionValue(APPID, "APPID");
        RetrofitService.DefaultImpls.loginByPwd$default(apiService, str, str2, APPID, json, null, 16, null).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() { // from class: com.deye.fragment.LoginFragment$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                LoginFragment.toPhoneNumLogin$lambda$10(this.f$0);
            }
        }).subscribe(new Consumer() { // from class: com.deye.fragment.LoginFragment.toPhoneNumLogin.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<LoginResult> it2) throws Resources.NotFoundException {
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseResult.MetaBean meta = it2.getMeta();
                Integer numValueOf = meta != null ? Integer.valueOf(meta.getCode()) : null;
                if (numValueOf != null && numValueOf.intValue() == 0) {
                    LoginFragment loginFragment = LoginFragment.this;
                    LoginResult data = it2.getData();
                    String str3 = data != null ? data.token : null;
                    Intrinsics.checkNotNull(str3);
                    LoginResult data2 = it2.getData();
                    loginFragment.doLoginSuc(str3, data2 != null ? data2.is_weak_password : false);
                    return;
                }
                if (numValueOf != null && numValueOf.intValue() == 10250) {
                    BaseUtils.showShortToast(StubApp.getString2(14085));
                } else if (numValueOf != null && numValueOf.intValue() == 10251) {
                    BaseUtils.showShortToast(StubApp.getString2(14086));
                } else {
                    BaseResult.MetaBean meta2 = it2.getMeta();
                    BaseUtils.showShortToast(String.valueOf(meta2 != null ? meta2.getMessage() : null));
                }
            }
        }, new Consumer() { // from class: com.deye.fragment.LoginFragment.toPhoneNumLogin.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                LoginFragment.this.hideLoading();
                it2.printStackTrace();
                BaseUtils.showShortToast(StubApp.getString2(14087));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void toPhoneNumLogin$lambda$10(LoginFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hideLoading();
    }

    private final void toForgetPage() {
        Intent intent = new Intent(this.mContext, (Class<?>) ForgetPwdActivity.class);
        LoginFragmentBinding loginFragmentBinding = this.mLoginAtyBinding;
        if (loginFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLoginAtyBinding");
            loginFragmentBinding = null;
        }
        intent.putExtra(StubApp.getString2(13698), String.valueOf(loginFragmentBinding.edPhoneNumber.getText()));
        startActivity(intent);
    }

    private final void toHomePage() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Intrinsics.checkNotNullParameter(s, "s");
        setBtnEnterStyle();
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: LoginFragment.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/deye/fragment/LoginFragment$LoginType;", "", "(Ljava/lang/String;I)V", "oneKey", "phoneNum", "verificationCode", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class LoginType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ LoginType[] $VALUES;
        public static final LoginType oneKey = new LoginType(StubApp.getString2(14078), 0);
        public static final LoginType phoneNum = new LoginType(StubApp.getString2(14079), 1);
        public static final LoginType verificationCode = new LoginType(StubApp.getString2(14080), 2);

        private static final /* synthetic */ LoginType[] $values() {
            return new LoginType[]{oneKey, phoneNum, verificationCode};
        }

        public static EnumEntries<LoginType> getEntries() {
            return $ENTRIES;
        }

        public static LoginType valueOf(String str) {
            return (LoginType) Enum.valueOf(LoginType.class, str);
        }

        public static LoginType[] values() {
            return (LoginType[]) $VALUES.clone();
        }

        private LoginType(String str, int i) {
        }

        static {
            LoginType[] loginTypeArr$values = $values();
            $VALUES = loginTypeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(loginTypeArr$values);
        }
    }

    /* compiled from: LoginFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/deye/fragment/LoginFragment$Companion;", "", "()V", "newInstance", "Lcom/deye/fragment/LoginFragment;", "dataToShow", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final LoginFragment newInstance(String dataToShow) {
            LoginFragment loginFragment = new LoginFragment();
            new Bundle();
            return loginFragment;
        }
    }
}
