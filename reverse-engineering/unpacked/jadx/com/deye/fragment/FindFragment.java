package com.deye.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import com.deye.activity.SearchActivity;
import com.deye.activity.message.MessageCenterActivity;
import com.deye.adapter.FindBannerAdapter;
import com.deye.adapter.FindPagerAdapter;
import com.deye.fragment.FindFragment;
import com.deye.helper.RateManager;
import com.deye.utils.BaseUtils;
import com.deye.utils.PermissionPageUtils;
import com.deye.viewmodels.MainViewModel;
import com.deye.views.CustomFontTextUnitView;
import com.deye.views.LoopCircleIndicator3;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mxchipapp.R;
import com.mxchipapp.databinding.FindFragmentBinding;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stub.StubApp;
import com.zhpan.bannerview.transform.ScaleInTransformer;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.bean.BannerItemBean;
import io.fogcloud.sdk.fog.bean.ChannelsBean;
import io.fogcloud.sdk.fog.bean.LocationInfoBean;
import io.fogcloud.sdk.fog.bean.LocationWeather;
import io.fogcloud.sdk.fog.bean.NowWeatherBean;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

/* compiled from: FindFragment.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u00020\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002J\b\u0010\u001b\u001a\u00020\u0016H\u0003J$\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u0016H\u0016J\b\u0010%\u001a\u00020\u0016H\u0016J\u001a\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u001d2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010(\u001a\u00020\u0016H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/deye/fragment/FindFragment;", "Lcom/deye/fragment/BaseFragment;", "()V", "bannerAdapter", "Lcom/deye/adapter/FindBannerAdapter;", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "mBinding", "Lcom/mxchipapp/databinding/FindFragmentBinding;", "mMainViewModel", "Lcom/deye/viewmodels/MainViewModel;", "pagerAdapter", "Lcom/deye/adapter/FindPagerAdapter;", "runnable", "Ljava/lang/Runnable;", "getRunnable", "()Ljava/lang/Runnable;", "tabIndex", "", "initBanner", "", "initListViewPager", "result", "", "Lio/fogcloud/sdk/fog/bean/ChannelsBean;", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "onViewCreated", "view", "requestViewPagerData", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class FindFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FindBannerAdapter bannerAdapter;
    private FindFragmentBinding mBinding;
    private MainViewModel mMainViewModel;
    private FindPagerAdapter pagerAdapter;
    private int tabIndex;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable runnable = new Runnable() { // from class: com.deye.fragment.FindFragment$runnable$1
        @Override // java.lang.Runnable
        public void run() {
            if (this.this$0.bannerAdapter == null) {
                return;
            }
            FindFragmentBinding findFragmentBinding = this.this$0.mBinding;
            FindFragmentBinding findFragmentBinding2 = null;
            String string2 = StubApp.getString2(13230);
            if (findFragmentBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                findFragmentBinding = null;
            }
            if (findFragmentBinding.viewpagerBanner.getVisibility() == 8) {
                return;
            }
            FindFragmentBinding findFragmentBinding3 = this.this$0.mBinding;
            if (findFragmentBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                findFragmentBinding3 = null;
            }
            int currentItem = findFragmentBinding3.viewpagerBanner.getCurrentItem() + 1;
            FindBannerAdapter findBannerAdapter = this.this$0.bannerAdapter;
            Intrinsics.checkNotNull(findBannerAdapter);
            int itemCount = currentItem % findBannerAdapter.getItemCount();
            FindFragmentBinding findFragmentBinding4 = this.this$0.mBinding;
            if (findFragmentBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                findFragmentBinding2 = findFragmentBinding4;
            }
            findFragmentBinding2.viewpagerBanner.setCurrentItem(itemCount, true);
            this.this$0.getHandler().postDelayed(this, 3000L);
        }
    };

    public final Handler getHandler() {
        return this.handler;
    }

    public final Runnable getRunnable() {
        return this.runnable;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, container, savedInstanceState);
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.find_fragment, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        this.mBinding = (FindFragmentBinding) viewDataBindingInflate;
        initView();
        FindFragmentBinding findFragmentBinding = this.mBinding;
        if (findFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            findFragmentBinding = null;
        }
        View root = findFragmentBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            RateManager.INSTANCE.checkAndShowDialog(activity, 3);
        }
    }

    public void onResume() {
        super.onResume();
        this.handler.postDelayed(this.runnable, 3000L);
        trackPageStartEvent(StubApp.getString2(14075));
    }

    public void onPause() {
        super.onPause();
        this.handler.removeCallbacks(this.runnable);
        trackPageEndEvent(StubApp.getString2(14075));
    }

    private final void requestViewPagerData() {
        Intrinsics.checkNotNullExpressionValue(RetrofitManager.INSTANCE.getApiService().getChannels().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() { // from class: com.deye.fragment.FindFragment$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                FindFragment.requestViewPagerData$lambda$1(this.f$0);
            }
        }).subscribe(new Consumer() { // from class: com.deye.fragment.FindFragment$requestViewPagerData$flowable$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<List<ChannelsBean>> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseResult.MetaBean meta = it2.getMeta();
                if (meta != null && meta.getCode() == 0 && it2.getData() != null) {
                    FindFragment findFragment = this.this$0;
                    List<ChannelsBean> data = it2.getData();
                    Intrinsics.checkNotNull(data);
                    findFragment.initListViewPager(data);
                    return;
                }
                BaseResult.MetaBean meta2 = it2.getMeta();
                BaseUtils.showShortToast(String.valueOf(meta2 != null ? meta2.getMessage() : null));
            }
        }, new Consumer() { // from class: com.deye.fragment.FindFragment$requestViewPagerData$flowable$3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                it2.printStackTrace();
            }
        }), "subscribe(...)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestViewPagerData$lambda$1(FindFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FindFragmentBinding findFragmentBinding = this$0.mBinding;
        if (findFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            findFragmentBinding = null;
        }
        findFragmentBinding.srlRefreshLayout.finishRefresh();
    }

    private final void initView() {
        MutableLiveData<LocationWeather> locationLiveDate;
        MutableLiveData<String> addressLiveData;
        MutableLiveData<Boolean> locationPermissionGranted;
        MutableLiveData<Integer> unReadMsgCountLiveDate;
        initBanner();
        requestViewPagerData();
        ViewModelStoreOwner viewModelStoreOwnerRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(viewModelStoreOwnerRequireActivity, "requireActivity(...)");
        MainViewModel mainViewModel = new ViewModelProvider(viewModelStoreOwnerRequireActivity).get(MainViewModel.class);
        this.mMainViewModel = mainViewModel;
        if (mainViewModel != null && (unReadMsgCountLiveDate = mainViewModel.getUnReadMsgCountLiveDate()) != null) {
            unReadMsgCountLiveDate.observe(requireActivity(), new FindFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.deye.fragment.FindFragment.initView.1
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
                    FindFragmentBinding findFragmentBinding = FindFragment.this.mBinding;
                    if (findFragmentBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                        findFragmentBinding = null;
                    }
                    ImageView imageView = findFragmentBinding.messageDot;
                    Intrinsics.checkNotNull(num);
                    imageView.setVisibility(num.intValue() > 0 ? 0 : 8);
                }
            }));
        }
        MainViewModel mainViewModel2 = this.mMainViewModel;
        if (mainViewModel2 != null && (locationPermissionGranted = mainViewModel2.getLocationPermissionGranted()) != null) {
            locationPermissionGranted.observe(requireActivity(), new FindFragment$sam$androidx_lifecycle_Observer$0(new C01862()));
        }
        MainViewModel mainViewModel3 = this.mMainViewModel;
        if (mainViewModel3 != null && (addressLiveData = mainViewModel3.getAddressLiveData()) != null) {
            addressLiveData.observe(requireActivity(), new FindFragment$sam$androidx_lifecycle_Observer$0(new Function1<String, Unit>() { // from class: com.deye.fragment.FindFragment.initView.3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String str) {
                    FindFragmentBinding findFragmentBinding = FindFragment.this.mBinding;
                    if (findFragmentBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                        findFragmentBinding = null;
                    }
                    findFragmentBinding.tvCity.setText(str);
                }
            }));
        }
        MainViewModel mainViewModel4 = this.mMainViewModel;
        if (mainViewModel4 != null && (locationLiveDate = mainViewModel4.getLocationLiveDate()) != null) {
            locationLiveDate.observe(requireActivity(), new FindFragment$sam$androidx_lifecycle_Observer$0(new Function1<LocationWeather, Unit>() { // from class: com.deye.fragment.FindFragment.initView.4
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(LocationWeather locationWeather) {
                    invoke2(locationWeather);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LocationWeather locationWeather) {
                    LocationInfoBean location;
                    String path;
                    String code;
                    Integer numValueOf;
                    Integer rh;
                    FindFragmentBinding findFragmentBinding = FindFragment.this.mBinding;
                    String string2 = StubApp.getString2(13230);
                    if (findFragmentBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                        findFragmentBinding = null;
                    }
                    CharSequence text = findFragmentBinding.tvCity.getText();
                    if ((text == null || text.length() == 0) && (location = locationWeather.getLocation()) != null && (path = location.getPath()) != null) {
                        FindFragment findFragment = FindFragment.this;
                        String str = path;
                        String string22 = StubApp.getString2(450);
                        List listSplit$default = StringsKt.split$default(str, new String[]{string22}, false, 0, 6, (Object) null);
                        if (listSplit$default.size() == 4) {
                            FindFragmentBinding findFragmentBinding2 = findFragment.mBinding;
                            if (findFragmentBinding2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string2);
                                findFragmentBinding2 = null;
                            }
                            findFragmentBinding2.tvCity.setText(listSplit$default.get(1) + string22 + listSplit$default.get(0));
                        } else {
                            FindFragmentBinding findFragmentBinding3 = findFragment.mBinding;
                            if (findFragmentBinding3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string2);
                                findFragmentBinding3 = null;
                            }
                            findFragmentBinding3.tvCity.setText(str);
                        }
                    }
                    NowWeatherBean realtime = locationWeather.getRealtime();
                    if (realtime != null && (rh = realtime.getRh()) != null) {
                        FindFragment findFragment2 = FindFragment.this;
                        int iIntValue = rh.intValue();
                        FindFragmentBinding findFragmentBinding4 = findFragment2.mBinding;
                        if (findFragmentBinding4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            findFragmentBinding4 = null;
                        }
                        findFragmentBinding4.tvHumidity.setValueText(String.valueOf(iIntValue));
                    }
                    NowWeatherBean realtime2 = locationWeather.getRealtime();
                    if (realtime2 != null && (code = realtime2.getCode()) != null) {
                        FindFragment findFragment3 = FindFragment.this;
                        if (Intrinsics.areEqual(code, "00")) {
                            numValueOf = Integer.valueOf(R.drawable.icon_weather_01);
                        } else if (Intrinsics.areEqual(code, "001")) {
                            numValueOf = Integer.valueOf(R.drawable.icon_weather_02);
                        } else if (Intrinsics.areEqual(code, "01") || Intrinsics.areEqual(code, "02")) {
                            numValueOf = Integer.valueOf(R.drawable.icon_weather_03);
                        } else {
                            String string23 = StubApp.getString2(9302);
                            String string24 = StubApp.getString2(14062);
                            if (ArraysKt.contains(new String[]{StubApp.getString2(12885), StubApp.getString2(12886), StubApp.getString2(12889), StubApp.getString2(12890), StubApp.getString2(12891), StubApp.getString2(10133), StubApp.getString2(8591), StubApp.getString2(1751), StubApp.getString2(8590), StubApp.getString2(1754), StubApp.getString2(10817), string23, string24}, code)) {
                                numValueOf = Integer.valueOf(R.drawable.icon_weather_04);
                            } else if (ArraysKt.contains(new String[]{StubApp.getString2(12888), StubApp.getString2(1794), StubApp.getString2(1795), StubApp.getString2(1798), StubApp.getString2(13497), StubApp.getString2(14063), StubApp.getString2(13496), StubApp.getString2(14064), StubApp.getString2(14065), StubApp.getString2(10818), StubApp.getString2(14066), string23, string24}, code)) {
                                numValueOf = Integer.valueOf(R.drawable.icon_weather_05);
                            } else if (Intrinsics.areEqual(code, "33")) {
                                numValueOf = Integer.valueOf(R.drawable.icon_weather_06);
                            } else if (ArraysKt.contains(new String[]{StubApp.getString2(14067), StubApp.getString2(1755), StubApp.getString2(10819), StubApp.getString2(14068), StubApp.getString2(14069), StubApp.getString2(14070)}, code)) {
                                numValueOf = Integer.valueOf(R.drawable.icon_weather_07);
                            } else if (ArraysKt.contains(new String[]{StubApp.getString2(14071), StubApp.getString2(8589), StubApp.getString2(14072), StubApp.getString2(8588), StubApp.getString2(10830), StubApp.getString2(10831), StubApp.getString2(13619)}, code)) {
                                numValueOf = Integer.valueOf(R.drawable.icon_weather_08);
                            } else {
                                numValueOf = ArraysKt.contains(new String[]{StubApp.getString2(12887), StubApp.getString2(14073)}, code) ? Integer.valueOf(R.drawable.icon_weather_09) : null;
                            }
                        }
                        if (numValueOf != null) {
                            FindFragmentBinding findFragmentBinding5 = findFragment3.mBinding;
                            if (findFragmentBinding5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string2);
                                findFragmentBinding5 = null;
                            }
                            findFragmentBinding5.ivWeather.setVisibility(0);
                            FindFragmentBinding findFragmentBinding6 = findFragment3.mBinding;
                            if (findFragmentBinding6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string2);
                                findFragmentBinding6 = null;
                            }
                            findFragmentBinding6.ivWeather.setImageDrawable(findFragment3.getResources().getDrawable(numValueOf.intValue()));
                        } else {
                            FindFragmentBinding findFragmentBinding7 = findFragment3.mBinding;
                            if (findFragmentBinding7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string2);
                                findFragmentBinding7 = null;
                            }
                            findFragmentBinding7.ivWeather.setVisibility(8);
                        }
                    }
                    FindFragmentBinding findFragmentBinding8 = FindFragment.this.mBinding;
                    if (findFragmentBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                        findFragmentBinding8 = null;
                    }
                    CustomFontTextUnitView customFontTextUnitView = findFragmentBinding8.tvEnvHum;
                    NowWeatherBean realtime3 = locationWeather.getRealtime();
                    customFontTextUnitView.setValueText(String.valueOf(realtime3 != null ? Double.valueOf(realtime3.getTemp()) : null));
                }
            }));
        }
        FindFragmentBinding findFragmentBinding = this.mBinding;
        FindFragmentBinding findFragmentBinding2 = null;
        String string2 = StubApp.getString2(13230);
        if (findFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            findFragmentBinding = null;
        }
        findFragmentBinding.ivMessage.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.FindFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FindFragment.initView$lambda$2(this.f$0, view);
            }
        });
        FindFragmentBinding findFragmentBinding3 = this.mBinding;
        if (findFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            findFragmentBinding3 = null;
        }
        findFragmentBinding3.ivSearch.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.FindFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FindFragment.initView$lambda$3(this.f$0, view);
            }
        });
        FindFragmentBinding findFragmentBinding4 = this.mBinding;
        if (findFragmentBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            findFragmentBinding4 = null;
        }
        findFragmentBinding4.srlRefreshLayout.setHeaderMaxDragRate(1.3f);
        FindFragmentBinding findFragmentBinding5 = this.mBinding;
        if (findFragmentBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            findFragmentBinding5 = null;
        }
        findFragmentBinding5.srlRefreshLayout.setDragRate(1.3f);
        FindFragmentBinding findFragmentBinding6 = this.mBinding;
        if (findFragmentBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            findFragmentBinding6 = null;
        }
        findFragmentBinding6.srlRefreshLayout.setRefreshHeader((RefreshHeader) new MaterialHeader(getActivity()));
        FindFragmentBinding findFragmentBinding7 = this.mBinding;
        if (findFragmentBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            findFragmentBinding7 = null;
        }
        findFragmentBinding7.srlRefreshLayout.setHeaderHeight(60.0f);
        FindFragmentBinding findFragmentBinding8 = this.mBinding;
        if (findFragmentBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            findFragmentBinding2 = findFragmentBinding8;
        }
        findFragmentBinding2.srlRefreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.deye.fragment.FindFragment$$ExternalSyntheticLambda4
            @Override // com.scwang.smartrefresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                FindFragment.initView$lambda$4(this.f$0, refreshLayout);
            }
        });
    }

    /* compiled from: FindFragment.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.fragment.FindFragment$initView$2, reason: invalid class name and case insensitive filesystem */
    static final class C01862 extends Lambda implements Function1<Boolean, Unit> {
        C01862() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean bool) {
            boolean zBooleanValue = bool.booleanValue();
            FindFragmentBinding findFragmentBinding = null;
            String string2 = StubApp.getString2(13230);
            if (!zBooleanValue) {
                FindFragmentBinding findFragmentBinding2 = FindFragment.this.mBinding;
                if (findFragmentBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    findFragmentBinding2 = null;
                }
                findFragmentBinding2.tvCity.setText(StubApp.getString2(14061));
                FindFragmentBinding findFragmentBinding3 = FindFragment.this.mBinding;
                if (findFragmentBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    findFragmentBinding3 = null;
                }
                findFragmentBinding3.llInfo.setVisibility(8);
                FindFragmentBinding findFragmentBinding4 = FindFragment.this.mBinding;
                if (findFragmentBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                } else {
                    findFragmentBinding = findFragmentBinding4;
                }
                TextView textView = findFragmentBinding.tvCity;
                final FindFragment findFragment = FindFragment.this;
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.FindFragment$initView$2$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FindFragment.C01862.invoke$lambda$0(findFragment, view);
                    }
                });
                return;
            }
            FindFragmentBinding findFragmentBinding5 = FindFragment.this.mBinding;
            if (findFragmentBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                findFragmentBinding = findFragmentBinding5;
            }
            findFragmentBinding.llInfo.setVisibility(0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(FindFragment this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            new PermissionPageUtils(this$0.getActivity()).getAppDetailSettingIntent((Context) this$0.getActivity());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$2(FindFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.requireActivity(), (Class<?>) MessageCenterActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$3(FindFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.requireActivity(), (Class<?>) SearchActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$4(final FindFragment this$0, RefreshLayout refreshLayout) {
        FindListFragment fragmentByIndex;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.initBanner();
        FindPagerAdapter findPagerAdapter = this$0.pagerAdapter;
        if (findPagerAdapter == null || (fragmentByIndex = findPagerAdapter.getFragmentByIndex(this$0.tabIndex)) == null) {
            return;
        }
        fragmentByIndex.onRefresh(new Function0<Unit>() { // from class: com.deye.fragment.FindFragment$initView$7$1
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
                BaseUtils.showShortToast(StubApp.getString2(14074));
                FindFragmentBinding findFragmentBinding = this.this$0.mBinding;
                if (findFragmentBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                    findFragmentBinding = null;
                }
                findFragmentBinding.srlRefreshLayout.finishRefresh();
            }
        });
    }

    public final void initBanner() {
        Intrinsics.checkNotNullExpressionValue(RetrofitManager.INSTANCE.getApiService().getBannerList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.deye.fragment.FindFragment$initBanner$flowable$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<List<BannerItemBean>> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseResult.MetaBean meta = it2.getMeta();
                FindFragmentBinding findFragmentBinding = null;
                String string2 = StubApp.getString2(13230);
                if (meta == null || meta.getCode() != 0) {
                    FindFragmentBinding findFragmentBinding2 = this.this$0.mBinding;
                    if (findFragmentBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                        findFragmentBinding2 = null;
                    }
                    findFragmentBinding2.indicator.setVisibility(8);
                    FindFragmentBinding findFragmentBinding3 = this.this$0.mBinding;
                    if (findFragmentBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                        findFragmentBinding3 = null;
                    }
                    findFragmentBinding3.viewpagerBanner.setVisibility(8);
                    FindFragmentBinding findFragmentBinding4 = this.this$0.mBinding;
                    if (findFragmentBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                        findFragmentBinding4 = null;
                    }
                    findFragmentBinding4.viewShapeLeft.setVisibility(8);
                    FindFragmentBinding findFragmentBinding5 = this.this$0.mBinding;
                    if (findFragmentBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                    } else {
                        findFragmentBinding = findFragmentBinding5;
                    }
                    findFragmentBinding.viewShapeRight.setVisibility(8);
                    return;
                }
                if (it2.getData() != null) {
                    FindFragment findFragment = this.this$0;
                    Context contextRequireContext = findFragment.requireContext();
                    Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
                    List<BannerItemBean> data = it2.getData();
                    Intrinsics.checkNotNull(data);
                    findFragment.bannerAdapter = new FindBannerAdapter(contextRequireContext, data);
                    FindFragmentBinding findFragmentBinding6 = findFragment.mBinding;
                    if (findFragmentBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                        findFragmentBinding6 = null;
                    }
                    ViewPager2 viewPager2 = findFragmentBinding6.viewpagerBanner;
                    viewPager2.setOffscreenPageLimit(1);
                    RecyclerView childAt = viewPager2.getChildAt(0);
                    Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
                    RecyclerView recyclerView = childAt;
                    int iDp2px = BaseUtils.dp2px(24.0f);
                    recyclerView.setPadding(iDp2px, 0, iDp2px, 0);
                    recyclerView.setClipToPadding(false);
                    recyclerView.setAdapter(findFragment.bannerAdapter);
                    ViewPager2.PageTransformer compositePageTransformer = new CompositePageTransformer();
                    compositePageTransformer.addTransformer(new ScaleInTransformer(0.9f));
                    compositePageTransformer.addTransformer(new MarginPageTransformer((int) recyclerView.getResources().getDimension(R.dimen.color_pointer_radius)));
                    viewPager2.setPageTransformer(compositePageTransformer);
                    List<BannerItemBean> data2 = it2.getData();
                    Intrinsics.checkNotNull(data2);
                    if (data2.size() > 1) {
                        FindFragmentBinding findFragmentBinding7 = findFragment.mBinding;
                        if (findFragmentBinding7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            findFragmentBinding7 = null;
                        }
                        findFragmentBinding7.indicator.setVisibility(0);
                        FindFragmentBinding findFragmentBinding8 = findFragment.mBinding;
                        if (findFragmentBinding8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            findFragmentBinding8 = null;
                        }
                        LoopCircleIndicator3 loopCircleIndicator3 = findFragmentBinding8.indicator;
                        FindFragmentBinding findFragmentBinding9 = findFragment.mBinding;
                        if (findFragmentBinding9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                        } else {
                            findFragmentBinding = findFragmentBinding9;
                        }
                        loopCircleIndicator3.setViewPager(findFragmentBinding.viewpagerBanner);
                        return;
                    }
                    List<BannerItemBean> data3 = it2.getData();
                    Intrinsics.checkNotNull(data3);
                    if (data3.isEmpty()) {
                        FindFragmentBinding findFragmentBinding10 = findFragment.mBinding;
                        if (findFragmentBinding10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            findFragmentBinding10 = null;
                        }
                        findFragmentBinding10.indicator.setVisibility(8);
                        FindFragmentBinding findFragmentBinding11 = findFragment.mBinding;
                        if (findFragmentBinding11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            findFragmentBinding11 = null;
                        }
                        findFragmentBinding11.viewpagerBanner.setVisibility(8);
                        FindFragmentBinding findFragmentBinding12 = findFragment.mBinding;
                        if (findFragmentBinding12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            findFragmentBinding12 = null;
                        }
                        findFragmentBinding12.viewShapeLeft.setVisibility(8);
                        FindFragmentBinding findFragmentBinding13 = findFragment.mBinding;
                        if (findFragmentBinding13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                        } else {
                            findFragmentBinding = findFragmentBinding13;
                        }
                        findFragmentBinding.viewShapeRight.setVisibility(8);
                        return;
                    }
                    FindFragmentBinding findFragmentBinding14 = findFragment.mBinding;
                    if (findFragmentBinding14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                    } else {
                        findFragmentBinding = findFragmentBinding14;
                    }
                    findFragmentBinding.indicator.setVisibility(8);
                }
            }
        }, new Consumer() { // from class: com.deye.fragment.FindFragment$initBanner$flowable$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                FindFragmentBinding findFragmentBinding = this.this$0.mBinding;
                FindFragmentBinding findFragmentBinding2 = null;
                String string2 = StubApp.getString2(13230);
                if (findFragmentBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    findFragmentBinding = null;
                }
                findFragmentBinding.indicator.setVisibility(8);
                FindFragmentBinding findFragmentBinding3 = this.this$0.mBinding;
                if (findFragmentBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    findFragmentBinding3 = null;
                }
                findFragmentBinding3.viewpagerBanner.setVisibility(8);
                FindFragmentBinding findFragmentBinding4 = this.this$0.mBinding;
                if (findFragmentBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    findFragmentBinding4 = null;
                }
                findFragmentBinding4.viewShapeLeft.setVisibility(8);
                FindFragmentBinding findFragmentBinding5 = this.this$0.mBinding;
                if (findFragmentBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                } else {
                    findFragmentBinding2 = findFragmentBinding5;
                }
                findFragmentBinding2.viewShapeRight.setVisibility(8);
                it2.printStackTrace();
            }
        }), "subscribe(...)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initListViewPager(final List<ChannelsBean> result) {
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        this.pagerAdapter = new FindPagerAdapter(fragmentActivityRequireActivity);
        FindFragmentBinding findFragmentBinding = this.mBinding;
        FindFragmentBinding findFragmentBinding2 = null;
        String string2 = StubApp.getString2(13230);
        if (findFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            findFragmentBinding = null;
        }
        findFragmentBinding.viewPager.setOffscreenPageLimit(100);
        FindFragmentBinding findFragmentBinding3 = this.mBinding;
        if (findFragmentBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            findFragmentBinding3 = null;
        }
        findFragmentBinding3.viewPager.setAdapter(this.pagerAdapter);
        FindPagerAdapter findPagerAdapter = this.pagerAdapter;
        if (findPagerAdapter != null) {
            findPagerAdapter.setDataList(result);
        }
        if (this.tabIndex < result.size()) {
            FindFragmentBinding findFragmentBinding4 = this.mBinding;
            if (findFragmentBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                findFragmentBinding4 = null;
            }
            findFragmentBinding4.viewPager.setCurrentItem(this.tabIndex, true);
        }
        FindFragmentBinding findFragmentBinding5 = this.mBinding;
        if (findFragmentBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            findFragmentBinding5 = null;
        }
        findFragmentBinding5.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.deye.fragment.FindFragment.initListViewPager.1
            public void onPageSelected(int position) {
                FindFragment.this.tabIndex = position;
            }
        });
        FindFragmentBinding findFragmentBinding6 = this.mBinding;
        if (findFragmentBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            findFragmentBinding6 = null;
        }
        findFragmentBinding6.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.deye.fragment.FindFragment.initListViewPager.2
            public void onTabReselected(TabLayout.Tab p0) {
            }

            public void onTabSelected(TabLayout.Tab p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                View customView = p0.getCustomView();
                TextView textView = customView != null ? (TextView) customView.findViewById(R.id.tab_text) : null;
                if (textView != null) {
                    textView.setTextSize(16.0f);
                }
                if (textView != null) {
                    textView.setTypeface(null, 1);
                }
            }

            public void onTabUnselected(TabLayout.Tab p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                View customView = p0.getCustomView();
                TextView textView = customView != null ? (TextView) customView.findViewById(R.id.tab_text) : null;
                if (textView != null) {
                    textView.setTextSize(14.0f);
                }
                if (textView != null) {
                    textView.setTypeface(null, 0);
                }
            }
        });
        FindFragmentBinding findFragmentBinding7 = this.mBinding;
        if (findFragmentBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            findFragmentBinding7 = null;
        }
        TabLayout tabLayout = findFragmentBinding7.tabLayout;
        FindFragmentBinding findFragmentBinding8 = this.mBinding;
        if (findFragmentBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            findFragmentBinding2 = findFragmentBinding8;
        }
        new TabLayoutMediator(tabLayout, findFragmentBinding2.viewPager, new TabLayoutMediator.TabConfigurationStrategy() { // from class: com.deye.fragment.FindFragment$$ExternalSyntheticLambda0
            public final void onConfigureTab(TabLayout.Tab tab, int i) {
                FindFragment.initListViewPager$lambda$5(result, tab, i);
            }
        }).attach();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListViewPager$lambda$5(List result, TabLayout.Tab tab, int i) {
        Intrinsics.checkNotNullParameter(result, "$result");
        Intrinsics.checkNotNullParameter(tab, "tab");
        tab.setCustomView(R.layout.custom_tab);
        View customView = tab.getCustomView();
        TextView textView = customView != null ? (TextView) customView.findViewById(R.id.tab_text) : null;
        if (textView == null) {
            return;
        }
        textView.setText(((ChannelsBean) result.get(i)).getChannel_name());
    }

    /* compiled from: FindFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/deye/fragment/FindFragment$Companion;", "", "()V", "newInstance", "Lcom/deye/fragment/FindFragment;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FindFragment newInstance() {
            return new FindFragment();
        }
    }
}
