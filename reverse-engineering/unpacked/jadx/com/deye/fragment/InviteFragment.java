package com.deye.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import com.mxchipapp.databinding.InviteFragmentBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.api.http.RetrofitService;
import io.fogcloud.sdk.fog.bean.ShareUserResult;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: InviteFragment.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u000eH\u0007J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\bH\u0007J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\nH\u0007J\b\u0010\u0014\u001a\u00020\u000eH\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bH\u0002J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0006H\u0016J\u0012\u0010\u001a\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J$\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/deye/fragment/InviteFragment;", "Lcom/deye/fragment/BaseFragment;", "()V", "mBinding", "Lcom/mxchipapp/databinding/InviteFragmentBinding;", "mContext", "Landroid/content/Context;", "mDeviceId", "", "mShareUserResult", "Lio/fogcloud/sdk/fog/bean/ShareUserResult;", "onInviteListener", "Lcom/deye/fragment/IOnInviteListener;", "close", "", "doInvite", "doSearch", "account", "initShareUserView", "shareUserResult", "initView", "isValidEmail", "", "email", "onAttach", "context", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class InviteFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private InviteFragmentBinding mBinding;
    private Context mContext;
    private String mDeviceId;
    private ShareUserResult mShareUserResult;
    private IOnInviteListener onInviteListener;

    @JvmStatic
    public static final InviteFragment newInstance(String str) {
        return INSTANCE.newInstance(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        this.mContext = context;
        this.onInviteListener = context instanceof IOnInviteListener ? (IOnInviteListener) context : null;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        String string = arguments != null ? arguments.getString(StubApp.getString2(13055), "") : null;
        this.mDeviceId = string != null ? string : "";
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, container, savedInstanceState);
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.invite_fragment, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        this.mBinding = (InviteFragmentBinding) viewDataBindingInflate;
        initView();
        InviteFragmentBinding inviteFragmentBinding = this.mBinding;
        if (inviteFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            inviteFragmentBinding = null;
        }
        View root = inviteFragmentBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    private final void initView() {
        InviteFragmentBinding inviteFragmentBinding = this.mBinding;
        InviteFragmentBinding inviteFragmentBinding2 = null;
        String string2 = StubApp.getString2(13230);
        if (inviteFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            inviteFragmentBinding = null;
        }
        inviteFragmentBinding.edPhoneNumber.setHint(getString(R.string.invite_input_hint_phone));
        InviteFragmentBinding inviteFragmentBinding3 = this.mBinding;
        if (inviteFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            inviteFragmentBinding3 = null;
        }
        inviteFragmentBinding3.edPhoneNumber.setInputType(3);
        InviteFragmentBinding inviteFragmentBinding4 = this.mBinding;
        if (inviteFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            inviteFragmentBinding4 = null;
        }
        inviteFragmentBinding4.tvAlertMessage.setText(getString(R.string.invite_alert_message_phone));
        InviteFragmentBinding inviteFragmentBinding5 = this.mBinding;
        if (inviteFragmentBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            inviteFragmentBinding5 = null;
        }
        inviteFragmentBinding5.fraSearch.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.InviteFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InviteFragment.initView$lambda$0(this.f$0, view);
            }
        });
        InviteFragmentBinding inviteFragmentBinding6 = this.mBinding;
        if (inviteFragmentBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            inviteFragmentBinding6 = null;
        }
        inviteFragmentBinding6.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.InviteFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InviteFragment.initView$lambda$1(this.f$0, view);
            }
        });
        InviteFragmentBinding inviteFragmentBinding7 = this.mBinding;
        if (inviteFragmentBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            inviteFragmentBinding2 = inviteFragmentBinding7;
        }
        inviteFragmentBinding2.btnInvite.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.InviteFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InviteFragment.initView$lambda$2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$0(InviteFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        InviteFragmentBinding inviteFragmentBinding = this$0.mBinding;
        if (inviteFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            inviteFragmentBinding = null;
        }
        String string = inviteFragmentBinding.edPhoneNumber.getText().toString();
        if (string.length() != 11 || !StringsKt.startsWith$default(string, StubApp.getString2(2546), false, 2, (Object) null)) {
            BaseUtils.showShortToast(this$0.getString(R.string.invite_invalid_phone));
        } else {
            this$0.doSearch(string);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$1(InviteFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$2(InviteFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.doInvite();
    }

    private final boolean isValidEmail(String email) {
        String str = email;
        return str.length() > 0 && Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }

    public final void close() {
        requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }

    public final void doInvite() {
        if (this.mShareUserResult == null) {
            return;
        }
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        showLoading(context, getString(R.string.invite_sharing));
        RetrofitService apiService = RetrofitManager.INSTANCE.getApiService();
        String str = this.mDeviceId;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDeviceId");
            str = null;
        }
        ShareUserResult shareUserResult = this.mShareUserResult;
        String str2 = shareUserResult != null ? shareUserResult.end_user_id : null;
        Intrinsics.checkNotNull(str2);
        apiService.shareInvite(str, str2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() { // from class: com.deye.fragment.InviteFragment$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                InviteFragment.doInvite$lambda$3(this.f$0);
            }
        }).subscribe(new Consumer() { // from class: com.deye.fragment.InviteFragment.doInvite.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<ShareUserResult> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseResult.MetaBean meta = it2.getMeta();
                if (meta != null && meta.getCode() == 0) {
                    BaseUtils.showShortToast(InviteFragment.this.getString(R.string.invite_success));
                    IOnInviteListener iOnInviteListener = InviteFragment.this.onInviteListener;
                    if (iOnInviteListener != null) {
                        iOnInviteListener.onInviteSuccess();
                    }
                    InviteFragment.this.close();
                    return;
                }
                BaseResult.MetaBean meta2 = it2.getMeta();
                BaseUtils.showShortToast(String.valueOf(meta2 != null ? meta2.getMessage() : null));
            }
        }, new Consumer() { // from class: com.deye.fragment.InviteFragment.doInvite.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                it2.printStackTrace();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void doInvite$lambda$3(InviteFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hideLoading();
    }

    public final void doSearch(String account) {
        Intrinsics.checkNotNullParameter(account, "account");
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        showLoading(context, getString(R.string.invite_searching));
        RetrofitService apiService = RetrofitManager.INSTANCE.getApiService();
        String str = this.mDeviceId;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDeviceId");
            str = null;
        }
        RetrofitService.DefaultImpls.findShareUser$default(apiService, str, account, null, 4, null).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() { // from class: com.deye.fragment.InviteFragment$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                InviteFragment.doSearch$lambda$4(this.f$0);
            }
        }).subscribe(new Consumer() { // from class: com.deye.fragment.InviteFragment.doSearch.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<ShareUserResult> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseResult.MetaBean meta = it2.getMeta();
                String string2 = StubApp.getString2(13230);
                InviteFragmentBinding inviteFragmentBinding = null;
                if (meta != null && meta.getCode() == 0) {
                    InviteFragmentBinding inviteFragmentBinding2 = InviteFragment.this.mBinding;
                    if (inviteFragmentBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                        inviteFragmentBinding2 = null;
                    }
                    inviteFragmentBinding2.llAlert.setVisibility(8);
                    InviteFragmentBinding inviteFragmentBinding3 = InviteFragment.this.mBinding;
                    if (inviteFragmentBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                        inviteFragmentBinding3 = null;
                    }
                    inviteFragmentBinding3.llSearchNoResult.setVisibility(8);
                    InviteFragmentBinding inviteFragmentBinding4 = InviteFragment.this.mBinding;
                    if (inviteFragmentBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                    } else {
                        inviteFragmentBinding = inviteFragmentBinding4;
                    }
                    inviteFragmentBinding.llUser.setVisibility(0);
                    InviteFragment inviteFragment = InviteFragment.this;
                    ShareUserResult data = it2.getData();
                    Intrinsics.checkNotNull(data);
                    inviteFragment.initShareUserView(data);
                    return;
                }
                BaseResult.MetaBean meta2 = it2.getMeta();
                if (meta2 != null && meta2.getCode() == 10404) {
                    InviteFragmentBinding inviteFragmentBinding5 = InviteFragment.this.mBinding;
                    if (inviteFragmentBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                        inviteFragmentBinding5 = null;
                    }
                    inviteFragmentBinding5.llAlert.setVisibility(8);
                    InviteFragmentBinding inviteFragmentBinding6 = InviteFragment.this.mBinding;
                    if (inviteFragmentBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                        inviteFragmentBinding6 = null;
                    }
                    inviteFragmentBinding6.llSearchNoResult.setVisibility(0);
                    InviteFragmentBinding inviteFragmentBinding7 = InviteFragment.this.mBinding;
                    if (inviteFragmentBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                        inviteFragmentBinding7 = null;
                    }
                    inviteFragmentBinding7.llUser.setVisibility(8);
                    InviteFragment.this.mShareUserResult = null;
                    return;
                }
                BaseResult.MetaBean meta3 = it2.getMeta();
                BaseUtils.showShortToast(String.valueOf(meta3 != null ? meta3.getMessage() : null));
                InviteFragment.this.mShareUserResult = null;
            }
        }, new Consumer() { // from class: com.deye.fragment.InviteFragment.doSearch.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                it2.printStackTrace();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void doSearch$lambda$4(InviteFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hideLoading();
    }

    public final void initShareUserView(ShareUserResult shareUserResult) {
        Intrinsics.checkNotNullParameter(shareUserResult, "shareUserResult");
        this.mShareUserResult = shareUserResult;
        RequestOptions requestOptionsError = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE).placeholder(getResources().getDrawable(R.mipmap.head_icon)).error(R.mipmap.head_icon);
        Intrinsics.checkNotNullExpressionValue(requestOptionsError, "error(...)");
        RequestBuilder<Drawable> requestBuilderApply = Glide.with(this).load(shareUserResult.avatar_url).apply((BaseRequestOptions<?>) requestOptionsError);
        InviteFragmentBinding inviteFragmentBinding = this.mBinding;
        InviteFragmentBinding inviteFragmentBinding2 = null;
        String string2 = StubApp.getString2(13230);
        if (inviteFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            inviteFragmentBinding = null;
        }
        requestBuilderApply.into(inviteFragmentBinding.ivHead);
        InviteFragmentBinding inviteFragmentBinding3 = this.mBinding;
        if (inviteFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            inviteFragmentBinding3 = null;
        }
        inviteFragmentBinding3.tvName.setText(shareUserResult.name);
        InviteFragmentBinding inviteFragmentBinding4 = this.mBinding;
        if (inviteFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            inviteFragmentBinding4 = null;
        }
        inviteFragmentBinding4.tvPhone.setText(shareUserResult.phone_number);
        InviteFragmentBinding inviteFragmentBinding5 = this.mBinding;
        if (inviteFragmentBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            inviteFragmentBinding5 = null;
        }
        LinearLayout linearLayout = inviteFragmentBinding5.llShareYet;
        Integer num = shareUserResult.share_state;
        int i = 0;
        linearLayout.setVisibility((num != null && num.intValue() == 1) ? 0 : 8);
        InviteFragmentBinding inviteFragmentBinding6 = this.mBinding;
        if (inviteFragmentBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            inviteFragmentBinding2 = inviteFragmentBinding6;
        }
        LinearLayout linearLayout2 = inviteFragmentBinding2.llInvite;
        Integer num2 = shareUserResult.share_state;
        if (num2 != null && num2.intValue() == 1) {
            i = 8;
        }
        linearLayout2.setVisibility(i);
    }

    /* compiled from: InviteFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/deye/fragment/InviteFragment$Companion;", "", "()V", "newInstance", "Lcom/deye/fragment/InviteFragment;", "deviceId", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final InviteFragment newInstance(String deviceId) {
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            InviteFragment inviteFragment = new InviteFragment();
            Bundle bundle = new Bundle();
            bundle.putString(StubApp.getString2(13055), deviceId);
            inviteFragment.setArguments(bundle);
            return inviteFragment;
        }
    }
}
