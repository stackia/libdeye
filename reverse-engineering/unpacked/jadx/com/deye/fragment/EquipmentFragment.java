package com.deye.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.deye.FogDeviceManager;
import com.deye.SubscribeDeviceStates;
import com.deye.activity.config_net.DeviceShowShareQrAty;
import com.deye.activity.device.air_conditioner.base.A12A452Aty;
import com.deye.activity.device.base.BaseActivity;
import com.deye.activity.device.dehumidifier.device.A10Activity;
import com.deye.activity.device.dehumidifier.device.DY6138AAty;
import com.deye.activity.device.dehumidifier.device.DY8138CAty;
import com.deye.activity.device.dehumidifier.device.DY8138DAty;
import com.deye.activity.device.dehumidifier.device.DY8138TAty;
import com.deye.activity.device.dehumidifier.device.DY890CAty;
import com.deye.activity.device.dehumidifier.device.DY890TAty;
import com.deye.activity.device.dehumidifier.device.DeYe612SAty;
import com.deye.activity.device.dehumidifier.device.DeYe6158EBAty;
import com.deye.activity.device.dehumidifier.device.DeYeA06A4Aty;
import com.deye.activity.device.dehumidifier.device.DeYeB12A3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeB13A3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeC65DZAty;
import com.deye.activity.device.dehumidifier.device.DeYeD50A3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeD50B3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeE12A3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeES25A3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeG25A3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeN20A3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeP30Aty;
import com.deye.activity.device.dehumidifier.device.DeYeP40A3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeRLS48A3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeRT12Aty;
import com.deye.activity.device.dehumidifier.device.DeYeSC60Aty;
import com.deye.activity.device.dehumidifier.device.DeYeT22A3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeTM208Aty;
import com.deye.activity.device.dehumidifier.device.DeYeU20A3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeU20AirAty;
import com.deye.activity.device.dehumidifier.device.DeYeU20ProAty;
import com.deye.activity.device.dehumidifier.device.DeYeV58A3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeV60A3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeW20A3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeX20A3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeY16Aty;
import com.deye.activity.device.dehumidifier.device.DeYeZ12A3Aty;
import com.deye.activity.device.dehumidifier.device.DeYeZ20B3Aty;
import com.deye.activity.device.humidifier.F15ProAty;
import com.deye.activity.device.humidifier.H7Aty;
import com.deye.activity.device.humidifier.base.F15Aty;
import com.deye.activity.device.loop_fan.device.LoopFanAty;
import com.deye.activity.message.MessageCenterActivity;
import com.deye.bluetooth.BluetoothTools;
import com.deye.event.RefreshDeviceEvent;
import com.deye.fragment.EquipmentFragment;
import com.deye.helper.DialogHelper;
import com.deye.helper.EquipmentFragmentHelper;
import com.deye.listener.OnItemClickListener;
import com.deye.listener.OnRefreshViewListener;
import com.deye.thread_pool.DeviceHeartbeatManager;
import com.deye.utils.BaseUtils;
import com.deye.utils.ChannelUtil;
import com.deye.utils.MMKVUtils;
import com.deye.utils.PagerUtils;
import com.deye.utils.TestTime;
import com.deye.viewmodels.MainViewModel;
import com.deye.views.HomeHeader;
import com.deye.webview.SmartServiceWebActivity;
import com.google.gson.Gson;
import com.mxchipapp.R;
import com.mxchipapp.databinding.HomeEquipmentFrgBinding;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.api.http.RetrofitService;
import io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.bean.LocationWeather;
import io.fogcloud.sdk.fog.bean.NowWeatherBean;
import io.fogcloud.sdk.fog.callback.ManageDeviceCallBack;
import io.fogcloud.sdk.fog.log.LogDebug;
import io.fogcloud.sdk.fog.log.LogUtil;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;

/* compiled from: EquipmentFragment.kt */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 w2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0001wB\u0005¢\u0006\u0002\u0010\u0006J\b\u0010N\u001a\u00020OH\u0002J\u0016\u0010P\u001a\u00020\u00182\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\"0RH\u0002J\b\u0010S\u001a\u00020OH\u0002J\b\u0010T\u001a\u00020OH\u0002J\u0010\u0010U\u001a\u00020O2\u0006\u0010V\u001a\u00020\u0016H\u0016J$\u0010W\u001a\u00020\u00162\u0006\u0010X\u001a\u00020Y2\b\u0010Z\u001a\u0004\u0018\u00010[2\b\u0010\\\u001a\u0004\u0018\u00010]H\u0016J\b\u0010^\u001a\u00020OH\u0016J\u0010\u0010_\u001a\u00020O2\u0006\u0010`\u001a\u00020\"H\u0016J\u0018\u0010a\u001a\u00020O2\u0006\u0010`\u001a\u00020\"2\u0006\u0010b\u001a\u00020\u0018H\u0016J\u0010\u0010c\u001a\u00020O2\u0006\u0010d\u001a\u00020\u0018H\u0016J\u0010\u0010e\u001a\u00020O2\u0006\u0010f\u001a\u00020;H\u0016J\b\u0010g\u001a\u00020OH\u0016J\u0012\u0010h\u001a\u00020O2\b\u0010i\u001a\u0004\u0018\u00010jH\u0007J\b\u0010k\u001a\u00020OH\u0016J\b\u0010l\u001a\u00020OH\u0016J\b\u0010m\u001a\u00020OH\u0016J\b\u0010n\u001a\u00020OH\u0002J\u0012\u0010o\u001a\u00020O2\b\u0010`\u001a\u0004\u0018\u00010\"H\u0002J\u0006\u0010p\u001a\u00020OJ$\u0010q\u001a\u00020O2\u0006\u0010r\u001a\u00020\t2\u0012\u0010s\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020O0tH\u0002J\u0016\u0010u\u001a\u00020O2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\"0RH\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010)\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010+\"\u0004\b0\u0010-R\u001c\u00101\u001a\u0004\u0018\u000102X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001a\u00107\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010+\"\u0004\b9\u0010-R\u001a\u0010:\u001a\u00020;X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001a\u0010@\u001a\u00020AX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER:\u0010F\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020H\u0018\u00010Gj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020H\u0018\u0001`IX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010M¨\u0006x"}, d2 = {"Lcom/deye/fragment/EquipmentFragment;", "Lcom/deye/fragment/BaseFragment;", "Lcom/deye/listener/OnItemClickListener;", "Landroid/view/View$OnClickListener;", "Lcom/deye/listener/OnRefreshViewListener;", "Lcom/deye/SubscribeDeviceStates$IDeviceStateListener;", "()V", "cachedDeviceIds", "", "", "<set-?>", "Lcom/mxchipapp/databinding/HomeEquipmentFrgBinding;", "dataBinding", "getDataBinding", "()Lcom/mxchipapp/databinding/HomeEquipmentFrgBinding;", "deviceIdList", "", "getDeviceIdList", "()Ljava/util/List;", "setDeviceIdList", "(Ljava/util/List;)V", "draggableView", "Landroid/view/View;", "hasOld", "", "getHasOld", "()Ljava/lang/Boolean;", "setHasOld", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "lastRefreshTime", "", "mDeviceListBeanList", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "getMDeviceListBeanList", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "setMDeviceListBeanList", "(Ljava/util/concurrent/CopyOnWriteArrayList;)V", "mEquipmentFragmentHelper", "Lcom/deye/helper/EquipmentFragmentHelper;", "mEvnHumidity", "getMEvnHumidity", "()Ljava/lang/String;", "setMEvnHumidity", "(Ljava/lang/String;)V", "mEvnTemperature", "getMEvnTemperature", "setMEvnTemperature", "mMainViewModel", "Lcom/deye/viewmodels/MainViewModel;", "getMMainViewModel", "()Lcom/deye/viewmodels/MainViewModel;", "setMMainViewModel", "(Lcom/deye/viewmodels/MainViewModel;)V", "mPlace", "getMPlace", "setMPlace", "mWeatherInfoPosition", "", "getMWeatherInfoPosition", "()I", "setMWeatherInfoPosition", "(I)V", "subscribeDeviceStates", "Lcom/deye/SubscribeDeviceStates;", "getSubscribeDeviceStates", "()Lcom/deye/SubscribeDeviceStates;", "setSubscribeDeviceStates", "(Lcom/deye/SubscribeDeviceStates;)V", "weatherMap", "Ljava/util/HashMap;", "Lio/fogcloud/sdk/fog/bean/LocationWeather;", "Lkotlin/collections/HashMap;", "getWeatherMap", "()Ljava/util/HashMap;", "setWeatherMap", "(Ljava/util/HashMap;)V", "getShareContent", "", "hasDeviceListChanged", "newDeviceList", "", "initData", "initView", "onClick", "view", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDeviceDateStateUpdate", "deviceListBean", "onDeviceOnlineStateUpdate", "online", "onHiddenChanged", "hidden", "onItemClick", "i", "onRefresh", "onRefreshDeviceEvent", "refreshDeviceEvent", "Lcom/deye/event/RefreshDeviceEvent;", "onResume", "onStart", "onStop", "requestPermissions", "showMoreWindow", "toBindDevice", "unBind", "deviceId", "callback", "Lkotlin/Function1;", "updateUIOnFetchDeviceListSuccess", "deviceListBeans", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class EquipmentFragment extends BaseFragment implements OnItemClickListener, View.OnClickListener, OnRefreshViewListener, SubscribeDeviceStates.IDeviceStateListener {
    private HomeEquipmentFrgBinding dataBinding;
    private View draggableView;
    private Boolean hasOld;
    private long lastRefreshTime;
    private EquipmentFragmentHelper mEquipmentFragmentHelper;
    private MainViewModel mMainViewModel;
    private HashMap<String, LocationWeather> weatherMap;
    public static final String TAG = StubApp.getString2(14041);
    private String mPlace = "";
    private String mEvnTemperature = "";
    private String mEvnHumidity = "";
    private CopyOnWriteArrayList<DeviceListBean> mDeviceListBeanList = new CopyOnWriteArrayList<>();
    private int mWeatherInfoPosition = 11;
    private List<String> deviceIdList = new ArrayList();
    private SubscribeDeviceStates subscribeDeviceStates = SubscribeDeviceStates.INSTANCE.getInstance();
    private Set<String> cachedDeviceIds = SetsKt.emptySet();

    public final HashMap<String, LocationWeather> getWeatherMap() {
        return this.weatherMap;
    }

    public final void setWeatherMap(HashMap<String, LocationWeather> map) {
        this.weatherMap = map;
    }

    public final MainViewModel getMMainViewModel() {
        return this.mMainViewModel;
    }

    public final void setMMainViewModel(MainViewModel mainViewModel) {
        this.mMainViewModel = mainViewModel;
    }

    public final String getMPlace() {
        return this.mPlace;
    }

    public final void setMPlace(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mPlace = str;
    }

    public final String getMEvnTemperature() {
        return this.mEvnTemperature;
    }

    public final void setMEvnTemperature(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mEvnTemperature = str;
    }

    public final String getMEvnHumidity() {
        return this.mEvnHumidity;
    }

    public final void setMEvnHumidity(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mEvnHumidity = str;
    }

    public final CopyOnWriteArrayList<DeviceListBean> getMDeviceListBeanList() {
        return this.mDeviceListBeanList;
    }

    public final void setMDeviceListBeanList(CopyOnWriteArrayList<DeviceListBean> copyOnWriteArrayList) {
        Intrinsics.checkNotNullParameter(copyOnWriteArrayList, "<set-?>");
        this.mDeviceListBeanList = copyOnWriteArrayList;
    }

    public final int getMWeatherInfoPosition() {
        return this.mWeatherInfoPosition;
    }

    public final void setMWeatherInfoPosition(int i) {
        this.mWeatherInfoPosition = i;
    }

    public final Boolean getHasOld() {
        return this.hasOld;
    }

    public final void setHasOld(Boolean bool) {
        this.hasOld = bool;
    }

    public final HomeEquipmentFrgBinding getDataBinding() {
        HomeEquipmentFrgBinding homeEquipmentFrgBinding = this.dataBinding;
        if (homeEquipmentFrgBinding != null) {
            return homeEquipmentFrgBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dataBinding");
        return null;
    }

    public final List<String> getDeviceIdList() {
        return this.deviceIdList;
    }

    public final void setDeviceIdList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.deviceIdList = list;
    }

    public final SubscribeDeviceStates getSubscribeDeviceStates() {
        return this.subscribeDeviceStates;
    }

    public final void setSubscribeDeviceStates(SubscribeDeviceStates subscribeDeviceStates) {
        Intrinsics.checkNotNullParameter(subscribeDeviceStates, "<set-?>");
        this.subscribeDeviceStates = subscribeDeviceStates;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        if (getActivity() != null) {
            FragmentActivity activity = getActivity();
            View decorView = (activity == null || (window = activity.getWindow()) == null) ? null : window.getDecorView();
            if (decorView != null) {
                decorView.setSystemUiVisibility(9216);
            }
        }
        this.mEquipmentFragmentHelper = new EquipmentFragmentHelper(this);
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.home_equipment_frg, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        this.dataBinding = (HomeEquipmentFrgBinding) viewDataBindingInflate;
        Log.e(StubApp.getString2(14050), StubApp.getString2(14051));
        TestTime.INSTANCE.logTime(StubApp.getString2(14052));
        ViewGroup.LayoutParams layoutParams = getDataBinding().llRootView.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        int i = layoutParams2.topMargin;
        BaseActivity baseActivity = (BaseActivity) getActivity();
        Intrinsics.checkNotNull(baseActivity);
        layoutParams2.topMargin = i + baseActivity.getStatusBarHeight();
        getDataBinding().llRootView.setLayoutParams(layoutParams2);
        View root = getDataBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        initView();
        return root;
    }

    @Override // com.deye.SubscribeDeviceStates.IDeviceStateListener
    public void onDeviceDateStateUpdate(DeviceListBean deviceListBean) {
        Intrinsics.checkNotNullParameter(deviceListBean, "deviceListBean");
        EquipmentFragmentHelper equipmentFragmentHelper = this.mEquipmentFragmentHelper;
        Intrinsics.checkNotNull(equipmentFragmentHelper);
        equipmentFragmentHelper.updateDehumidifyBean(deviceListBean);
    }

    @Override // com.deye.SubscribeDeviceStates.IDeviceStateListener
    public void onDeviceOnlineStateUpdate(DeviceListBean deviceListBean, boolean online) {
        Intrinsics.checkNotNullParameter(deviceListBean, "deviceListBean");
        EquipmentFragmentHelper equipmentFragmentHelper = this.mEquipmentFragmentHelper;
        Intrinsics.checkNotNull(equipmentFragmentHelper);
        equipmentFragmentHelper.updateOnlineState(deviceListBean, online);
    }

    public void onResume() {
        super.onResume();
        String string2 = StubApp.getString2(14058);
        String string22 = StubApp.getString2(14041);
        Log.d(string22, string2);
        Log.d(string22, StubApp.getString2(14059) + this.subscribeDeviceStates.isConnected());
        MainViewModel mainViewModel = this.mMainViewModel;
        if (mainViewModel != null) {
            mainViewModel.requestLocation();
        }
        initData();
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        CopyOnWriteArrayList<DeviceListBean> copyOnWriteArrayList = this.mDeviceListBeanList;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            return;
        }
        this.subscribeDeviceStates.addDeviceUpdateListener(this);
        Log.d(StubApp.getString2(14041), StubApp.getString2(14060));
    }

    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        EquipmentFragmentHelper equipmentFragmentHelper = this.mEquipmentFragmentHelper;
        Intrinsics.checkNotNull(equipmentFragmentHelper);
        equipmentFragmentHelper.stopLocation();
        CopyOnWriteArrayList<DeviceListBean> copyOnWriteArrayList = this.mDeviceListBeanList;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            return;
        }
        this.subscribeDeviceStates.removeDeviceUpdateListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onRefreshDeviceEvent(RefreshDeviceEvent refreshDeviceEvent) {
        Log.e(StubApp.getString2(14056), StubApp.getString2(14057));
        onRefresh();
    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        String str = StubApp.getString2(14053) + hidden;
        String string2 = StubApp.getString2(14041);
        Log.d(string2, str);
        if (hidden) {
            return;
        }
        Log.d(string2, StubApp.getString2(14054));
        if (BaseUtils.isNetworkConnected(getActivity())) {
            getDataBinding().iNoNet.llNetError.setVisibility(8);
            initData();
        } else {
            EquipmentFragmentHelper equipmentFragmentHelper = this.mEquipmentFragmentHelper;
            Intrinsics.checkNotNull(equipmentFragmentHelper);
            equipmentFragmentHelper.netIsBad();
        }
    }

    private final void initData() {
        boolean zIsNetworkConnected = BaseUtils.isNetworkConnected(getActivity());
        String string2 = StubApp.getString2(14041);
        if (!zIsNetworkConnected) {
            Log.w(string2, StubApp.getString2(14042));
            EquipmentFragmentHelper equipmentFragmentHelper = this.mEquipmentFragmentHelper;
            Intrinsics.checkNotNull(equipmentFragmentHelper);
            equipmentFragmentHelper.netIsBad();
            return;
        }
        Log.d(string2, StubApp.getString2(14043));
        getDataBinding().iNoNet.llNetError.setVisibility(8);
        TestTime.INSTANCE.logTime(StubApp.getString2(14044));
        Intrinsics.checkNotNullExpressionValue(RetrofitService.DefaultImpls.getDeviceList$default(RetrofitManager.INSTANCE.getApiService(), String.valueOf(Math.random()), null, 2, null).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new EquipmentFragment$initData$flowable$1(this), new Consumer() { // from class: com.deye.fragment.EquipmentFragment$initData$flowable$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                it2.printStackTrace();
            }
        }), "subscribe(...)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateUIOnFetchDeviceListSuccess(List<? extends DeviceListBean> deviceListBeans) {
        final boolean zHasDeviceListChanged = hasDeviceListChanged(deviceListBeans);
        this.mDeviceListBeanList.clear();
        this.mDeviceListBeanList.addAll(deviceListBeans);
        EquipmentFragmentHelper equipmentFragmentHelper = this.mEquipmentFragmentHelper;
        if (equipmentFragmentHelper != null) {
            equipmentFragmentHelper.setAdapter();
        }
        getDataBinding().srlRefreshLayout.finishRefresh();
        Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.deye.fragment.EquipmentFragment$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                EquipmentFragment.updateUIOnFetchDeviceListSuccess$lambda$0(this.f$0, zHasDeviceListChanged);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateUIOnFetchDeviceListSuccess$lambda$0(EquipmentFragment this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.mDeviceListBeanList.size() > 0) {
            this$0.subscribeDeviceStates.addDeviceUpdateListener(this$0);
            FogDeviceManager.INSTANCE.getDeviceList().clear();
            this$0.deviceIdList.clear();
            int size = this$0.mDeviceListBeanList.size();
            for (int i = 0; i < size; i++) {
                DeviceListBean deviceListBean = this$0.mDeviceListBeanList.get(i);
                if (!this$0.deviceIdList.contains(deviceListBean.getDevice_id())) {
                    List<String> list = this$0.deviceIdList;
                    String device_id = deviceListBean.getDevice_id();
                    Intrinsics.checkNotNullExpressionValue(device_id, "getDevice_id(...)");
                    list.add(device_id);
                }
                SubscribeDeviceStates subscribeDeviceStates = this$0.subscribeDeviceStates;
                Intrinsics.checkNotNull(deviceListBean);
                subscribeDeviceStates.registerDevice(deviceListBean);
                if (deviceListBean.isFogPlatform()) {
                    FogDeviceManager.INSTANCE.getDeviceList().add(deviceListBean);
                }
            }
            this$0.hasOld = Boolean.valueOf(FogDeviceManager.INSTANCE.getDeviceList().size() < this$0.mDeviceListBeanList.size());
            boolean z2 = !FogDeviceManager.INSTANCE.getDeviceList().isEmpty();
            DeviceHeartbeatManager.INSTANCE.stopAll();
            LogDebug.INSTANCE.log(StubApp.getString2(14048) + z + StubApp.getString2(14049) + this$0.subscribeDeviceStates.isConnected());
            SubscribeDeviceStates subscribeDeviceStates2 = this$0.subscribeDeviceStates;
            CopyOnWriteArrayList<DeviceListBean> copyOnWriteArrayList = this$0.mDeviceListBeanList;
            Boolean bool = this$0.hasOld;
            subscribeDeviceStates2.startListenDevice(copyOnWriteArrayList, z2, bool != null ? bool.booleanValue() : false);
        }
    }

    private final boolean hasDeviceListChanged(List<? extends DeviceListBean> newDeviceList) {
        List<? extends DeviceListBean> list = newDeviceList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(((DeviceListBean) it2.next()).getDevice_id());
        }
        Set<String> set = CollectionsKt.toSet(arrayList);
        boolean z = !Intrinsics.areEqual(this.cachedDeviceIds, set);
        this.cachedDeviceIds = set;
        return z;
    }

    private final void initView() {
        MutableLiveData<Integer> unReadMsgCountLiveDate;
        MutableLiveData<Boolean> locationPermissionGranted;
        MutableLiveData<HashMap<String, LocationWeather>> weatherMapLiveData;
        ViewModelStoreOwner viewModelStoreOwnerRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(viewModelStoreOwnerRequireActivity, "requireActivity(...)");
        this.mMainViewModel = new ViewModelProvider(viewModelStoreOwnerRequireActivity).get(MainViewModel.class);
        EquipmentFragment equipmentFragment = this;
        getDataBinding().ivClocking.setOnClickListener(equipmentFragment);
        getDataBinding().ivMore.setOnClickListener(equipmentFragment);
        getDataBinding().iNoNet.tvReload.setOnClickListener(equipmentFragment);
        getDataBinding().llLocation.setOnClickListener(equipmentFragment);
        getDataBinding().srlRefreshLayout.setHeaderMaxDragRate(1.3f);
        getDataBinding().srlRefreshLayout.setDragRate(1.3f);
        getDataBinding().srlRefreshLayout.setRefreshHeader((RefreshHeader) new HomeHeader(getActivity()));
        getDataBinding().srlRefreshLayout.setHeaderHeight(90.0f);
        getDataBinding().srlRefreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.deye.fragment.EquipmentFragment$$ExternalSyntheticLambda8
            @Override // com.scwang.smartrefresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                EquipmentFragment.initView$lambda$2(this.f$0, refreshLayout);
            }
        });
        getDataBinding().ivSmartService.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.EquipmentFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EquipmentFragment.initView$lambda$3(this.f$0, view);
            }
        });
        if (ChannelUtil.isOversea()) {
            getDataBinding().ivSmartService.setVisibility(8);
        }
        MainViewModel mainViewModel = this.mMainViewModel;
        if (mainViewModel != null && (weatherMapLiveData = mainViewModel.getWeatherMapLiveData()) != null) {
            weatherMapLiveData.observe(requireActivity(), new Observer() { // from class: com.deye.fragment.EquipmentFragment$$ExternalSyntheticLambda10
                public final void onChanged(Object obj) {
                    EquipmentFragment.initView$lambda$4(this.f$0, (HashMap) obj);
                }
            });
        }
        MainViewModel mainViewModel2 = this.mMainViewModel;
        if (mainViewModel2 != null && (locationPermissionGranted = mainViewModel2.getLocationPermissionGranted()) != null) {
            locationPermissionGranted.observe(requireActivity(), new EquipmentFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: com.deye.fragment.EquipmentFragment.initView.4
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke2(bool);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Boolean bool) {
                    LinearLayout linearLayout = EquipmentFragment.this.getDataBinding().llLocation;
                    Intrinsics.checkNotNull(bool);
                    linearLayout.setVisibility(bool.booleanValue() ? 8 : 0);
                }
            }));
        }
        MainViewModel mainViewModel3 = this.mMainViewModel;
        if (mainViewModel3 == null || (unReadMsgCountLiveDate = mainViewModel3.getUnReadMsgCountLiveDate()) == null) {
            return;
        }
        unReadMsgCountLiveDate.observe(requireActivity(), new EquipmentFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.deye.fragment.EquipmentFragment.initView.5
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
                ImageView imageView = EquipmentFragment.this.getDataBinding().messageDot;
                Intrinsics.checkNotNull(num);
                imageView.setVisibility(num.intValue() > 0 ? 0 : 8);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$2(EquipmentFragment this$0, RefreshLayout refreshLayout) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.e(StubApp.getString2(14045), StubApp.getString2(14046));
        this$0.lastRefreshTime = System.currentTimeMillis();
        this$0.initData();
        MainViewModel mainViewModel = this$0.mMainViewModel;
        if (mainViewModel != null) {
            mainViewModel.requestLocation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$3(EquipmentFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SmartServiceWebActivity.Companion companion = SmartServiceWebActivity.INSTANCE;
        Context contextRequireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        companion.open(contextRequireContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$4(EquipmentFragment this$0, HashMap map) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.weatherMap = map;
        EquipmentFragmentHelper equipmentFragmentHelper = this$0.mEquipmentFragmentHelper;
        if (equipmentFragmentHelper != null) {
            equipmentFragmentHelper.updateWeather();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0466  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0484  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x04c0  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x05b0  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x060a  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0644  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x06a6  */
    @Override // com.deye.listener.OnItemClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onItemClick(int i) {
        Intent intent;
        MutableLiveData<LocationWeather> locationLiveDate;
        LocationWeather locationWeather;
        NowWeatherBean realtime;
        DeviceListBean deviceListBean = this.mDeviceListBeanList.get(i);
        String device_id = deviceListBean.getDevice_id();
        String product_id = deviceListBean.getProduct_id();
        boolean zIsIs_combo = deviceListBean.isIs_combo();
        Log.d(StubApp.getString2(14041), StubApp.getString2(14055) + product_id);
        if (product_id != null) {
            switch (product_id.hashCode()) {
                case -2121259445:
                    if (!product_id.equals(StubApp.getString2(13447))) {
                        intent = null;
                        break;
                    } else {
                        intent = new Intent(getActivity(), (Class<?>) DY8138TAty.class);
                        break;
                    }
                case -2051528042:
                    if (product_id.equals(StubApp.getString2(13446))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeN20A3Aty.class);
                        break;
                    }
                    break;
                case -1479425774:
                    if (product_id.equals(StubApp.getString2(13444))) {
                        intent = new Intent(getActivity(), (Class<?>) DY890CAty.class);
                        break;
                    }
                    break;
                case -1376148786:
                    if (product_id.equals(StubApp.getString2(13443))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYe612SAty.class);
                        break;
                    }
                    break;
                case -1293175428:
                    if (product_id.equals(StubApp.getString2(13442))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeG25A3Aty.class);
                        break;
                    }
                    break;
                case -1192116076:
                    if (product_id.equals(StubApp.getString2(13441))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeE12A3Aty.class);
                        break;
                    }
                    break;
                case -1182160595:
                    if (product_id.equals(StubApp.getString2(13440))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeY16Aty.class);
                        break;
                    }
                    break;
                case -1028755433:
                    if (product_id.equals(StubApp.getString2(13439))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeC65DZAty.class);
                        break;
                    }
                    break;
                case -863412840:
                    if (product_id.equals(StubApp.getString2(13438))) {
                        intent = new Intent(getActivity(), (Class<?>) H7Aty.class);
                        break;
                    }
                    break;
                case -707141345:
                    if (product_id.equals(StubApp.getString2(13437))) {
                        intent = new Intent(getActivity(), (Class<?>) A10Activity.class);
                        break;
                    }
                    break;
                case -684380250:
                    if (product_id.equals(StubApp.getString2(13436))) {
                        intent = new Intent(getActivity(), (Class<?>) F15ProAty.class);
                        break;
                    }
                    break;
                case -669203127:
                    if (product_id.equals(StubApp.getString2(13435))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeES25A3Aty.class);
                        break;
                    }
                    break;
                case -559814530:
                    if (product_id.equals(StubApp.getString2(13434))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeD50A3Aty.class);
                        break;
                    }
                    break;
                case -399705960:
                    if (product_id.equals(StubApp.getString2(13433))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeU20AirAty.class);
                        break;
                    }
                    break;
                case -360193437:
                    if (product_id.equals(StubApp.getString2(13432))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeA06A4Aty.class);
                        break;
                    }
                    break;
                case -358908287:
                    if (product_id.equals(StubApp.getString2(13431))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeRLS48A3Aty.class);
                        break;
                    }
                    break;
                case -310911671:
                    if (product_id.equals(StubApp.getString2(13356))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeW20A3Aty.class);
                        break;
                    }
                    break;
                case -216261114:
                    if (product_id.equals(StubApp.getString2(13396))) {
                        intent = new Intent(getActivity(), (Class<?>) A12A452Aty.class);
                        break;
                    }
                    break;
                case -207450233:
                    if (product_id.equals(StubApp.getString2(13430))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeTM208Aty.class);
                        break;
                    }
                    break;
                case -178347431:
                    if (product_id.equals(StubApp.getString2(13354))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeZ20B3Aty.class);
                        break;
                    }
                    break;
                case -122543548:
                    if (product_id.equals(StubApp.getString2(13429))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeY16Aty.class);
                        break;
                    }
                    break;
                case -60953835:
                    if (product_id.equals(StubApp.getString2(13428))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeB12A3Aty.class);
                        break;
                    }
                    break;
                case 19922734:
                    if (!product_id.equals(StubApp.getString2(13355))) {
                    }
                    break;
                case 41651821:
                    if (product_id.equals(StubApp.getString2(13427))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeX20A3Aty.class);
                        break;
                    }
                    break;
                case 89766214:
                    if (product_id.equals(StubApp.getString2(13426))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeP30Aty.class);
                        break;
                    }
                    break;
                case 169083815:
                    if (product_id.equals(StubApp.getString2(13395))) {
                        intent = new Intent(getActivity(), (Class<?>) F15Aty.class);
                        break;
                    }
                    break;
                case 328331063:
                    if (product_id.equals(StubApp.getString2(13425))) {
                        intent = new Intent(getActivity(), (Class<?>) DY6138AAty.class);
                        break;
                    }
                    break;
                case 457826758:
                    if (product_id.equals(StubApp.getString2(13424))) {
                        intent = new Intent(getActivity(), (Class<?>) LoopFanAty.class);
                        break;
                    }
                    break;
                case 501388422:
                    if (!product_id.equals(StubApp.getString2(13423))) {
                    }
                    break;
                case 509564895:
                    if (product_id.equals(StubApp.getString2(13422))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeD50B3Aty.class);
                        break;
                    }
                    break;
                case 604171478:
                    if (product_id.equals(StubApp.getString2(13421))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeB13A3Aty.class);
                        break;
                    }
                    break;
                case 672956835:
                    if (product_id.equals(StubApp.getString2(13420))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeC65DZAty.class);
                        break;
                    }
                    break;
                case 685390594:
                    if (product_id.equals(StubApp.getString2(13419))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeSC60Aty.class);
                        break;
                    }
                    break;
                case 692443469:
                    if (product_id.equals(StubApp.getString2(13418))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeP40A3Aty.class);
                        break;
                    }
                    break;
                case 836371159:
                    if (product_id.equals(StubApp.getString2(13417))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeC65DZAty.class);
                        break;
                    }
                    break;
                case 892870982:
                    if (product_id.equals(StubApp.getString2(13416))) {
                        intent = new Intent(getActivity(), (Class<?>) DY8138CAty.class);
                        break;
                    }
                    break;
                case 999470824:
                    if (!product_id.equals(StubApp.getString2(13349))) {
                    }
                    break;
                case 1021448129:
                    if (product_id.equals(StubApp.getString2(13415))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeU20ProAty.class);
                        break;
                    }
                    break;
                case 1035709668:
                    if (product_id.equals(StubApp.getString2(13414))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeZ12A3Aty.class);
                        break;
                    }
                    break;
                case 1220291005:
                    if (product_id.equals(StubApp.getString2(13413))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeC65DZAty.class);
                        break;
                    }
                    break;
                case 1374214106:
                    if (product_id.equals(StubApp.getString2(13412))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeY16Aty.class);
                        break;
                    }
                    break;
                case 1400513971:
                    if (product_id.equals(StubApp.getString2(13411))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeY16Aty.class);
                        break;
                    }
                    break;
                case 1428687435:
                    if (product_id.equals(StubApp.getString2(13410))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeV58A3Aty.class);
                        break;
                    }
                    break;
                case 1485107781:
                    if (!product_id.equals(StubApp.getString2(13409))) {
                    }
                    break;
                case 1516469754:
                    if (!product_id.equals(StubApp.getString2(13408))) {
                    }
                    break;
                case 1633379062:
                    if (!product_id.equals(StubApp.getString2(13348))) {
                    }
                    break;
                case 1659254645:
                    if (product_id.equals(StubApp.getString2(13407))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeY16Aty.class);
                        break;
                    }
                    break;
                case 1669942571:
                    if (product_id.equals(StubApp.getString2(13406))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYe6158EBAty.class);
                        break;
                    }
                    break;
                case 1713175096:
                    if (product_id.equals(StubApp.getString2(13405))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeRT12Aty.class);
                        break;
                    }
                    break;
                case 1728471171:
                    if (product_id.equals(StubApp.getString2(13404))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeU20A3Aty.class);
                        break;
                    }
                    break;
                case 1747977970:
                    if (product_id.equals(StubApp.getString2(13403))) {
                        intent = new Intent(getActivity(), (Class<?>) DY8138DAty.class);
                        break;
                    }
                    break;
                case 1752070527:
                    if (product_id.equals(StubApp.getString2(13402))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeY16Aty.class);
                        break;
                    }
                    break;
                case 1756990026:
                    if (!product_id.equals(StubApp.getString2(13401))) {
                    }
                    break;
                case 1778865536:
                    if (!product_id.equals(StubApp.getString2(13353))) {
                    }
                    break;
                case 1801910713:
                    if (!product_id.equals(StubApp.getString2(13400))) {
                    }
                    break;
                case 1931451897:
                    if (!product_id.equals(StubApp.getString2(13347))) {
                    }
                    break;
                case 1973993191:
                    if (product_id.equals(StubApp.getString2(13399))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeV60A3Aty.class);
                        break;
                    }
                    break;
                case 1998570969:
                    if (product_id.equals(StubApp.getString2(13947))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeV58A3Aty.class);
                        break;
                    }
                    break;
                case 2021966687:
                    if (product_id.equals(StubApp.getString2(13398))) {
                        intent = new Intent(getActivity(), (Class<?>) DeYeT22A3Aty.class);
                        break;
                    }
                    break;
                case 2098451586:
                    if (product_id.equals(StubApp.getString2(13397))) {
                        intent = new Intent(getActivity(), (Class<?>) DY890TAty.class);
                        break;
                    }
                    break;
            }
        }
        MainViewModel mainViewModel = this.mMainViewModel;
        if (mainViewModel != null && (locationLiveDate = mainViewModel.getLocationLiveDate()) != null && (locationWeather = (LocationWeather) locationLiveDate.getValue()) != null && (realtime = locationWeather.getRealtime()) != null && intent != null) {
            intent.putExtra(StubApp.getString2(13537), String.valueOf(realtime.getTemp()));
        }
        if (BaseUtils.isNotNull(intent)) {
            Intrinsics.checkNotNull(intent);
            intent.putExtra(StubApp.getString2(13569), this.mPlace);
            intent.putExtra(StubApp.getString2(13567), this.mEvnTemperature);
            intent.putExtra(StubApp.getString2(13568), this.mEvnHumidity);
            intent.putExtra(StubApp.getString2(6267), this.mWeatherInfoPosition);
            intent.putExtra(StubApp.getString2(13055), device_id);
            intent.putExtra(StubApp.getString2(13306), product_id);
            intent.putExtra(StubApp.getString2(13454), new Gson().toJson(deviceListBean));
            intent.putExtra(StubApp.getString2(13570), zIsIs_combo);
            intent.putExtra(StubApp.getString2(13571), deviceListBean.getProtocolVersion());
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.startActivity(intent);
            }
        }
    }

    private final void requestPermissions() {
        FragmentActivity activity = getActivity();
        LogUtil.d(StubApp.getString2(14041), StubApp.getString2(14047) + activity);
        if (activity == null) {
            return;
        }
        DialogHelper.requestPermissionForCamera((BaseActivity) activity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        switch (view.getId()) {
            case R.id.iv_clocking /* 2131362336 */:
                startActivity(new Intent(getActivity(), (Class<?>) MessageCenterActivity.class));
                break;
            case R.id.iv_more /* 2131362360 */:
                EquipmentFragmentHelper equipmentFragmentHelper = this.mEquipmentFragmentHelper;
                Intrinsics.checkNotNull(equipmentFragmentHelper);
                showMoreWindow(equipmentFragmentHelper.getCurrentBean());
                break;
            case R.id.ll_location /* 2131362457 */:
                DialogHelper.showLocationDialog((BaseActivity) getActivity());
                break;
            case R.id.tv_reload /* 2131363175 */:
                if (BaseUtils.isNetworkConnected(getActivity())) {
                    getDataBinding().iNoNet.llNetError.setVisibility(8);
                    initData();
                    break;
                }
                break;
        }
    }

    private final void showMoreWindow(final DeviceListBean deviceListBean) {
        getDataBinding().ivMore.setImageResource(R.drawable.icon_more_blue);
        final PopupWindow popupWindow = new PopupWindow();
        popupWindow.setWidth(-2);
        popupWindow.setHeight(-2);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        View viewInflate = LayoutInflater.from(getActivity()).inflate(R.layout.more_popupwindow, (ViewGroup) null, false);
        popupWindow.setContentView(viewInflate);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.deye.fragment.EquipmentFragment$$ExternalSyntheticLambda2
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                EquipmentFragment.showMoreWindow$lambda$6(this.f$0);
            }
        });
        popupWindow.showAsDropDown(getDataBinding().ivMore, 0, (int) getResources().getDimension(2131166765), 8388613);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.ll_share);
        LinearLayout linearLayout2 = (LinearLayout) viewInflate.findViewById(R.id.ll_rename);
        LinearLayout linearLayout3 = (LinearLayout) viewInflate.findViewById(R.id.ll_delete);
        LinearLayout linearLayout4 = (LinearLayout) viewInflate.findViewById(R.id.ll_add);
        LinearLayout linearLayout5 = (LinearLayout) viewInflate.findViewById(R.id.ll_scan);
        linearLayout4.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.EquipmentFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EquipmentFragment.showMoreWindow$lambda$7(popupWindow, this, view);
            }
        });
        linearLayout5.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.EquipmentFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EquipmentFragment.showMoreWindow$lambda$8(popupWindow, this, view);
            }
        });
        if (deviceListBean == null) {
            linearLayout.setVisibility(8);
            linearLayout2.setVisibility(8);
            linearLayout3.setVisibility(8);
        } else if (deviceListBean.getRole() != 1) {
            linearLayout.setVisibility(8);
        }
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.EquipmentFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EquipmentFragment.showMoreWindow$lambda$9(this.f$0, deviceListBean, popupWindow, view);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.EquipmentFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EquipmentFragment.showMoreWindow$lambda$10(popupWindow, this, deviceListBean, view);
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.EquipmentFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EquipmentFragment.showMoreWindow$lambda$11(popupWindow, this, deviceListBean, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMoreWindow$lambda$6(EquipmentFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getDataBinding().ivMore.setImageResource(R.drawable.icon_more);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMoreWindow$lambda$7(PopupWindow popupWindow, EquipmentFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(popupWindow, "$popupWindow");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        popupWindow.dismiss();
        this$0.toBindDevice();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMoreWindow$lambda$8(PopupWindow popupWindow, EquipmentFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(popupWindow, "$popupWindow");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        popupWindow.dismiss();
        this$0.requestPermissions();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMoreWindow$lambda$9(EquipmentFragment this$0, DeviceListBean deviceListBean, PopupWindow popupWindow, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(popupWindow, "$popupWindow");
        Intent intent = new Intent(this$0.getActivity(), (Class<?>) DeviceShowShareQrAty.class);
        intent.putExtra(StubApp.getString2(13055), deviceListBean != null ? deviceListBean.getDevice_id() : null);
        intent.putExtra(StubApp.getString2(13453), deviceListBean != null ? deviceListBean.getDevice_name() : null);
        this$0.startActivity(intent);
        popupWindow.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMoreWindow$lambda$10(PopupWindow popupWindow, final EquipmentFragment this$0, final DeviceListBean deviceListBean, View view) {
        Intrinsics.checkNotNullParameter(popupWindow, "$popupWindow");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        popupWindow.dismiss();
        DialogHelper.showRenameDialog(this$0.requireActivity(), deviceListBean != null ? deviceListBean.getDevice_name() : null, new DialogHelper.OnDialogListener() { // from class: com.deye.fragment.EquipmentFragment$showMoreWindow$5$1
            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onCancel() {
            }

            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onSure(String text) throws JSONException {
                DeYeHttpRequestManager deYeHttpRequestManager = DeYeHttpRequestManager.getInstance();
                DeviceListBean deviceListBean2 = deviceListBean;
                deYeHttpRequestManager.updateDeviceAlias(deviceListBean2 != null ? deviceListBean2.getDevice_id() : null, String.valueOf(text), new EquipmentFragment$showMoreWindow$5$1$onSure$1(this$0, text, deviceListBean));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMoreWindow$lambda$11(PopupWindow popupWindow, final EquipmentFragment this$0, final DeviceListBean deviceListBean, View view) {
        Intrinsics.checkNotNullParameter(popupWindow, "$popupWindow");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        popupWindow.dismiss();
        String string = this$0.getString(R.string.confirm_delete_device, new Object[]{deviceListBean != null ? deviceListBean.getDevice_name() : null});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        DialogHelper.showDeleteDialog(this$0.requireActivity(), string, "", new DialogHelper.OnDialogListener() { // from class: com.deye.fragment.EquipmentFragment$showMoreWindow$6$1
            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onSure(String text) throws JSONException {
                EquipmentFragment equipmentFragment = this.this$0;
                DeviceListBean deviceListBean2 = deviceListBean;
                Intrinsics.checkNotNull(deviceListBean2);
                String device_id = deviceListBean2.getDevice_id();
                Intrinsics.checkNotNullExpressionValue(device_id, "getDevice_id(...)");
                final EquipmentFragment equipmentFragment2 = this.this$0;
                equipmentFragment.unBind(device_id, new Function1<Boolean, Unit>() { // from class: com.deye.fragment.EquipmentFragment$showMoreWindow$6$1$onSure$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z) {
                        equipmentFragment2.getDataBinding().srlRefreshLayout.autoRefresh();
                    }
                });
            }
        });
    }

    /* compiled from: EquipmentFragment.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"com/deye/fragment/EquipmentFragment$unBind$1", "Lio/fogcloud/sdk/fog/callback/ManageDeviceCallBack;", "onFailure", "", "code", "", "message", "", "onSuccess", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.fragment.EquipmentFragment$unBind$1, reason: invalid class name */
    public static final class AnonymousClass1 implements ManageDeviceCallBack {
        final /* synthetic */ Function1<Boolean, Unit> $callback;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function1<? super Boolean, Unit> function1) {
            this.$callback = function1;
        }

        @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
        public void onSuccess(final String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Handler handler = new Handler(Looper.getMainLooper());
            final EquipmentFragment equipmentFragment = EquipmentFragment.this;
            final Function1<Boolean, Unit> function1 = this.$callback;
            handler.post(new Runnable() { // from class: com.deye.fragment.EquipmentFragment$unBind$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    EquipmentFragment.AnonymousClass1.onSuccess$lambda$0(equipmentFragment, message, function1);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSuccess$lambda$0(EquipmentFragment this$0, String message, Function1 callback) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(message, "$message");
            Intrinsics.checkNotNullParameter(callback, "$callback");
            this$0.hideLoading();
            Log.d(StubApp.getString2(13452), message);
            callback.invoke(true);
            BaseUtils.showShortToast(this$0.getActivity(), StubApp.getString2(14039));
        }

        @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
        public void onFailure(int code, String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            BaseUtils.showShortToast(EquipmentFragment.this.getActivity(), StubApp.getString2(14040));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unBind(String deviceId, Function1<? super Boolean, Unit> callback) throws JSONException {
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        showLoading((Context) fragmentActivityRequireActivity, "");
        DeYeHttpRequestManager.getInstance().unBindDevice(deviceId, new AnonymousClass1(callback));
    }

    public final void toBindDevice() {
        PagerUtils.goBleScanPage(getActivity());
    }

    @Override // com.deye.listener.OnRefreshViewListener
    public void onRefresh() {
        initData();
    }

    private final void getShareContent() {
        Intrinsics.checkNotNullExpressionValue(RetrofitManager.INSTANCE.getApiService().getShareContent().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.deye.fragment.EquipmentFragment$getShareContent$flowable$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<String> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseResult.MetaBean meta = it2.getMeta();
                if (meta == null || meta.getCode() != 0) {
                    return;
                }
                MMKVUtils mMKVUtils = MMKVUtils.INSTANCE;
                String data = it2.getData();
                if (data == null) {
                    data = "";
                }
                mMKVUtils.setShareContent(data);
            }
        }, new Consumer() { // from class: com.deye.fragment.EquipmentFragment$getShareContent$flowable$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                it2.printStackTrace();
            }
        }), "subscribe(...)");
    }

    public void onDestroy() {
        DeviceHeartbeatManager.INSTANCE.stopAll();
        new Thread(new Runnable() { // from class: com.deye.fragment.EquipmentFragment$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                EquipmentFragment.onDestroy$lambda$12();
            }
        }).start();
        BluetoothTools.getInstance(getActivity()).onDestory();
        EquipmentFragmentHelper equipmentFragmentHelper = this.mEquipmentFragmentHelper;
        Intrinsics.checkNotNull(equipmentFragmentHelper);
        equipmentFragmentHelper.onLocationDestroy();
        this.subscribeDeviceStates.removeDeviceUpdateListener(this);
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onDestroy$lambda$12() {
        DeYeMqttManager.getInstance().stopMqtt();
    }
}
