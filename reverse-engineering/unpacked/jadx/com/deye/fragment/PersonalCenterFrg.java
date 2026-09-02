package com.deye.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.deye.MxchipApplication;
import com.deye.MyReactActivity;
import com.deye.TabMainActivity;
import com.deye.activity.about.AboutDeyeActivity;
import com.deye.activity.config_net.DeviceListBindActivity;
import com.deye.activity.message.MessageCenterActivity;
import com.deye.activity.mine.HelperActivity;
import com.deye.activity.mine.MyCollectActivity;
import com.deye.activity.mine.MyLikeActivity;
import com.deye.activity.mine.SystemSettingsActivity;
import com.deye.activity.mine.UserInfoActivity;
import com.deye.entity.UserInfoBean;
import com.deye.helper.PersonalCenterFrgHelper;
import com.deye.utils.ChannelUtil;
import com.deye.utils.MMKVUtils;
import com.deye.utils.PagerUtils;
import com.deye.viewmodels.MainViewModel;
import com.deye.webview.AgentWebActivity;
import com.deye.webview.SmartServiceWebActivity;
import com.mxchipapp.R;
import com.mxchipapp.databinding.PersonalCenterFrgBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.api.http.RetrofitService;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PersonalCenterFrg.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 $2\u00020\u00012\u00020\u0002:\u0001$B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0014H\u0002J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bH\u0017J$\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020\u0014H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/deye/fragment/PersonalCenterFrg;", "Landroidx/fragment/app/Fragment;", "Landroid/view/View$OnClickListener;", "()V", "isNewVersion", "", "()Z", "setNewVersion", "(Z)V", "mMainViewModel", "Lcom/deye/viewmodels/MainViewModel;", "mPersonalCenterFrgBinding", "Lcom/mxchipapp/databinding/PersonalCenterFrgBinding;", "getMPersonalCenterFrgBinding", "()Lcom/mxchipapp/databinding/PersonalCenterFrgBinding;", "setMPersonalCenterFrgBinding", "(Lcom/mxchipapp/databinding/PersonalCenterFrgBinding;)V", "mPersonalCenterFrgHelper", "Lcom/deye/helper/PersonalCenterFrgHelper;", "getBanner", "", "getProgressUrl", "", "getRepairsUrl", "initView", "onClick", "view", "Landroid/view/View;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class PersonalCenterFrg extends Fragment implements View.OnClickListener {
    private boolean isNewVersion;
    private MainViewModel mMainViewModel;
    public PersonalCenterFrgBinding mPersonalCenterFrgBinding;
    private PersonalCenterFrgHelper mPersonalCenterFrgHelper;
    public static final String TAG = StubApp.getString2(14113);

    /* renamed from: isNewVersion, reason: from getter */
    public final boolean getIsNewVersion() {
        return this.isNewVersion;
    }

    public final void setNewVersion(boolean z) {
        this.isNewVersion = z;
    }

    public final PersonalCenterFrgBinding getMPersonalCenterFrgBinding() {
        PersonalCenterFrgBinding personalCenterFrgBinding = this.mPersonalCenterFrgBinding;
        if (personalCenterFrgBinding != null) {
            return personalCenterFrgBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mPersonalCenterFrgBinding");
        return null;
    }

    public final void setMPersonalCenterFrgBinding(PersonalCenterFrgBinding personalCenterFrgBinding) {
        Intrinsics.checkNotNullParameter(personalCenterFrgBinding, "<set-?>");
        this.mPersonalCenterFrgBinding = personalCenterFrgBinding;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.personal_center_frg, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        setMPersonalCenterFrgBinding((PersonalCenterFrgBinding) viewDataBindingInflate);
        this.mPersonalCenterFrgHelper = new PersonalCenterFrgHelper(this);
        View root = getMPersonalCenterFrgBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        PersonalCenterFrgHelper personalCenterFrgHelper = this.mPersonalCenterFrgHelper;
        Intrinsics.checkNotNull(personalCenterFrgHelper);
        UserInfoBean userInfo = MMKVUtils.INSTANCE.getUserInfo();
        personalCenterFrgHelper.setHeadPortrait(userInfo != null ? userInfo.getAvatar() : null);
        initView();
        return root;
    }

    public void onResume() throws Resources.NotFoundException {
        super.onResume();
        PersonalCenterFrgHelper personalCenterFrgHelper = this.mPersonalCenterFrgHelper;
        Intrinsics.checkNotNull(personalCenterFrgHelper);
        personalCenterFrgHelper.requestUserInfo();
        if (getActivity() != null) {
            TabMainActivity tabMainActivity = (TabMainActivity) getActivity();
            Intrinsics.checkNotNull(tabMainActivity);
            int deviceCount = tabMainActivity.getDeviceCount();
            getMPersonalCenterFrgBinding().tvDeviceTopCount.setText(getString(R.string.device_count, new Object[]{Integer.valueOf(deviceCount)}));
            if (deviceCount > 0) {
                getMPersonalCenterFrgBinding().tvDeviceCount.setText(getString(R.string.device_count, new Object[]{Integer.valueOf(deviceCount)}));
                getMPersonalCenterFrgBinding().tvDeviceCount.setVisibility(0);
            }
            CopyOnWriteArrayList<DeviceListBean> deviceList = tabMainActivity.getDeviceList();
            if (deviceList == null || deviceList.size() == 0) {
                getMPersonalCenterFrgBinding().ivDot.setVisibility(8);
            }
            if (deviceList != null) {
                Iterator<DeviceListBean> it2 = deviceList.iterator();
                boolean z = false;
                while (it2.hasNext()) {
                    DeviceListBean next = it2.next();
                    if (!next.has_room && next.getRole() == 1) {
                        z = true;
                    }
                }
                getMPersonalCenterFrgBinding().ivDot.setVisibility(z ? 0 : 8);
            }
        }
    }

    private final void initView() {
        MutableLiveData<Integer> unReadMsgCountLiveDate;
        ViewModelStoreOwner viewModelStoreOwnerRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(viewModelStoreOwnerRequireActivity, "requireActivity(...)");
        MainViewModel mainViewModel = new ViewModelProvider(viewModelStoreOwnerRequireActivity).get(MainViewModel.class);
        this.mMainViewModel = mainViewModel;
        if (mainViewModel != null && (unReadMsgCountLiveDate = mainViewModel.getUnReadMsgCountLiveDate()) != null) {
            unReadMsgCountLiveDate.observe(requireActivity(), new PersonalCenterFrg$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.deye.fragment.PersonalCenterFrg.initView.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke2(num);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Integer num) {
                    ImageView imageView = PersonalCenterFrg.this.getMPersonalCenterFrgBinding().messageDot;
                    Intrinsics.checkNotNull(num);
                    imageView.setVisibility(num.intValue() > 0 ? 0 : 8);
                }
            }));
        }
        PersonalCenterFrg personalCenterFrg = this;
        getMPersonalCenterFrgBinding().rlPersonalInfo.setOnClickListener(personalCenterFrg);
        getMPersonalCenterFrgBinding().rlAboutDeye.setOnClickListener(personalCenterFrg);
        getMPersonalCenterFrgBinding().rlSystemSettings.setOnClickListener(personalCenterFrg);
        getMPersonalCenterFrgBinding().rlDeviceList.setOnClickListener(personalCenterFrg);
        getMPersonalCenterFrgBinding().fraMessage.setOnClickListener(personalCenterFrg);
        getMPersonalCenterFrgBinding().rlHelper.setOnClickListener(personalCenterFrg);
        getMPersonalCenterFrgBinding().rlMyData.setOnClickListener(personalCenterFrg);
        if (!ChannelUtil.isOversea()) {
            getMPersonalCenterFrgBinding().llMineServices.setOnClickListener(personalCenterFrg);
            getMPersonalCenterFrgBinding().llMineVoice.setOnClickListener(personalCenterFrg);
            getMPersonalCenterFrgBinding().llMineRepairs.setOnClickListener(personalCenterFrg);
            getMPersonalCenterFrgBinding().llMineProgress.setOnClickListener(personalCenterFrg);
            getMPersonalCenterFrgBinding().llCollect.setOnClickListener(personalCenterFrg);
            getMPersonalCenterFrgBinding().llThumb.setOnClickListener(personalCenterFrg);
        }
        if (ChannelUtil.isOversea()) {
            getMPersonalCenterFrgBinding().llCollect.setVisibility(8);
            getMPersonalCenterFrgBinding().llThumb.setVisibility(8);
            getMPersonalCenterFrgBinding().tvDeviceTopCount.setVisibility(0);
            getMPersonalCenterFrgBinding().rlMyData.setVisibility(8);
            ViewParent parent = getMPersonalCenterFrgBinding().llMineServices.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup == null) {
                return;
            }
            viewGroup.setVisibility(8);
            return;
        }
        getBanner();
        getMPersonalCenterFrgBinding().rlSystemSettings.setVisibility(8);
    }

    private final void getBanner() {
        if (System.currentTimeMillis() - MMKVUtils.INSTANCE.getBannerHideTime() <= 604800000) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(RetrofitService.DefaultImpls.getMinePageBanner$default(RetrofitManager.INSTANCE.getApiService(), 0, 1, null).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new PersonalCenterFrg$getBanner$flowable$1(this), new Consumer() { // from class: com.deye.fragment.PersonalCenterFrg$getBanner$flowable$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
            }
        }), "subscribe(...)");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        int id = view.getId();
        String string2 = StubApp.getString2(13221);
        switch (id) {
            case R.id.fra_message /* 2131362244 */:
                startActivity(new Intent(getActivity(), (Class<?>) MessageCenterActivity.class));
                break;
            case R.id.ll_collect /* 2131362433 */:
                Context context = getContext();
                if (context != null) {
                    MyCollectActivity.INSTANCE.open(context);
                    break;
                }
                break;
            case R.id.ll_mine_progress /* 2131362458 */:
                Context context2 = getContext();
                if (context2 != null) {
                    AgentWebActivity.Companion companion = AgentWebActivity.INSTANCE;
                    String progressUrl = getProgressUrl();
                    String string = getString(R.string.repair_order_list);
                    Intrinsics.checkNotNullExpressionValue(string, string2);
                    companion.open(context2, progressUrl, string);
                    break;
                }
                break;
            case R.id.ll_mine_repairs /* 2131362459 */:
                Context context3 = getContext();
                if (context3 != null) {
                    AgentWebActivity.Companion companion2 = AgentWebActivity.INSTANCE;
                    String repairsUrl = getRepairsUrl();
                    String string3 = getString(R.string.self_service_repair);
                    Intrinsics.checkNotNullExpressionValue(string3, string2);
                    companion2.open(context3, repairsUrl, string3);
                    break;
                }
                break;
            case R.id.ll_mine_services /* 2131362460 */:
                Context context4 = getContext();
                if (context4 != null) {
                    SmartServiceWebActivity.INSTANCE.open(context4);
                    break;
                }
                break;
            case R.id.ll_mine_voice /* 2131362461 */:
                if (getContext() != null) {
                    PagerUtils.goVoiceGuidePage(getActivity());
                    break;
                }
                break;
            case R.id.ll_thumb /* 2131362509 */:
                Context context5 = getContext();
                if (context5 != null) {
                    MyLikeActivity.INSTANCE.open(context5);
                    break;
                }
                break;
            case R.id.rl_about_deye /* 2131362750 */:
                startActivity(new Intent(getActivity(), (Class<?>) AboutDeyeActivity.class));
                break;
            case R.id.rl_device_list /* 2131362764 */:
                startActivity(new Intent(getActivity(), (Class<?>) DeviceListBindActivity.class));
                break;
            case R.id.rl_helper /* 2131362775 */:
                startActivity(new Intent(getActivity(), (Class<?>) HelperActivity.class));
                break;
            case R.id.rl_my_data /* 2131362782 */:
                Intent intent = new Intent(getContext(), (Class<?>) MyReactActivity.class);
                intent.putExtra(StubApp.getString2(13144), StubApp.getString2(13591));
                startActivity(intent);
                break;
            case R.id.rl_personal_info /* 2131362786 */:
                startActivity(new Intent(getActivity(), (Class<?>) UserInfoActivity.class));
                break;
            case R.id.rl_system_settings /* 2131362806 */:
                startActivity(new Intent(getActivity(), (Class<?>) SystemSettingsActivity.class));
                break;
        }
    }

    private final String getRepairsUrl() {
        if (!MxchipApplication.checkIsReleaseEnv()) {
            return StubApp.getString2(14116);
        }
        return StubApp.getString2(14117);
    }

    private final String getProgressUrl() {
        if (!MxchipApplication.checkIsReleaseEnv()) {
            return StubApp.getString2(14114);
        }
        return StubApp.getString2(14115);
    }
}
