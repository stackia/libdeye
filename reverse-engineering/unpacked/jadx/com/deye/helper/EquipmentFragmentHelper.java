package com.deye.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import com.alibaba.fastjson.JSON;
import com.amap.location.support.bean.location.AmapLocationNetwork;
import com.deye.DeviceCacheManager;
import com.deye.FogDeviceManager;
import com.deye.MxchipApplication;
import com.deye.adapter.DeviceLisViewPagerAdapter;
import com.deye.adapter.DeviceListAdapter;
import com.deye.fragment.EquipmentFragment;
import com.deye.thread_pool.DeviceHeartbeatManager;
import com.deye.utils.BaseUtils;
import com.deye.utils.TestTime;
import com.deye.viewmodels.MainViewModel;
import com.deye.views.CustomFontTextView;
import com.deye.views.LoopCircleIndicator3;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mxchipapp.R;
import com.stub.StubApp;
import com.zhouyou.view.seekbar.SignUtils;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.bean.LocationWeather;
import io.fogcloud.sdk.fog.bean.NowWeatherBean;
import io.fogcloud.sdk.fog.bean.PropertyResultBean;
import io.fogcloud.sdk.fog.helper.Hex2DeviceBeanString;
import io.fogcloud.sdk.fog.helper.dehumidifier.Hex2DehumidifierBeanString;
import io.fogcloud.sdk.fog.helper.loop_fan.Hex2LoopFanBeanString;
import io.fogcloud.sdk.fog.log.LogDebug;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* compiled from: EquipmentFragmentHelper.kt */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u00107\u001a\u00020\u00062\u0006\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u0006H\u0002J\u0006\u0010:\u001a\u00020;J\b\u0010<\u001a\u00020;H\u0007J\u0016\u0010=\u001a\u00020;2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00150?H\u0002J$\u0010@\u001a\u00020;2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00150A2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020;0CH\u0002J\u0006\u0010D\u001a\u00020;J\u0012\u0010E\u001a\u00020;2\b\u0010F\u001a\u0004\u0018\u00010GH\u0002J\u0010\u0010H\u001a\u00020;2\b\u0010I\u001a\u0004\u0018\u00010JJ\u001e\u0010K\u001a\u00020;2\u0006\u0010L\u001a\u00020M2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00150AH\u0002J\u0012\u0010N\u001a\u00020;2\b\u0010O\u001a\u0004\u0018\u00010PH\u0002J\u001e\u0010Q\u001a\u00020;2\u0006\u0010L\u001a\u00020M2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00150AH\u0002J\b\u0010R\u001a\u00020;H\u0007J\u0010\u0010S\u001a\u00020;2\b\u0010T\u001a\u0004\u0018\u00010\u0015J\u001e\u0010U\u001a\u00020;2\u0006\u0010V\u001a\u00020\u00062\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00150AH\u0007J\u0018\u0010W\u001a\u00020;2\b\u0010T\u001a\u0004\u0018\u00010\u00152\u0006\u0010X\u001a\u00020YJ\u0012\u0010Z\u001a\u00020;2\b\u0010T\u001a\u0004\u0018\u00010\u0015H\u0002J\u0006\u0010[\u001a\u00020;R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0014\u0010\u000b\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0014\u0010\r\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0014\u0010\u000f\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0014\u0010\u0011\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010 \u001a\u00020!8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010)\"\u0004\b.\u0010+R\u001c\u0010/\u001a\u0004\u0018\u000100X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\\"}, d2 = {"Lcom/deye/helper/EquipmentFragmentHelper;", "", "mFragment", "Lcom/deye/fragment/EquipmentFragment;", "(Lcom/deye/fragment/EquipmentFragment;)V", "GET_DEVICE_LIST_FAIL", "", "getGET_DEVICE_LIST_FAIL", "()I", "GET_DEVICE_LIST_SUCCESS", "getGET_DEVICE_LIST_SUCCESS", "GET_VERSION_SUCCESS", "getGET_VERSION_SUCCESS", "GET_WEATHER_FAIL", "getGET_WEATHER_FAIL", "GET_WEATHER_SUCCESS", "getGET_WEATHER_SUCCESS", "STOP_REFRESH", "getSTOP_REFRESH", "curViewPagerPosition", "currentBean", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "getCurrentBean", "()Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "setCurrentBean", "(Lio/fogcloud/sdk/fog/bean/DeviceListBean;)V", "mDeviceListAdapter", "Lcom/deye/adapter/DeviceListAdapter;", "getMDeviceListAdapter", "()Lcom/deye/adapter/DeviceListAdapter;", "setMDeviceListAdapter", "(Lcom/deye/adapter/DeviceListAdapter;)V", "mHandler", "Landroid/os/Handler;", "getMHandler", "()Landroid/os/Handler;", "setMHandler", "(Landroid/os/Handler;)V", "mLatitude", "", "getMLatitude", "()D", "setMLatitude", "(D)V", "mLongitude", "getMLongitude", "setMLongitude", "mViewPageAdapter", "Lcom/deye/adapter/DeviceLisViewPagerAdapter;", "getMViewPageAdapter", "()Lcom/deye/adapter/DeviceLisViewPagerAdapter;", "setMViewPageAdapter", "(Lcom/deye/adapter/DeviceLisViewPagerAdapter;)V", "pageChangeCallback", "Landroidx/viewpager2/widget/ViewPager2$OnPageChangeCallback;", "getStartPosition", "realCount", "postion", "netIsBad", "", "onLocationDestroy", "parseBeanList", "beanList", "", "requestAllFogProperties", "", "callBack", "Lkotlin/Function0;", "setAdapter", "setLeft", "value", "", "setOutDoor", "it", "Lio/fogcloud/sdk/fog/bean/LocationWeather;", "setRecyclerViewAdapter", "context", "Landroid/content/Context;", "setRight", "bean", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "setViewPagerAdapter", "stopLocation", "updateDehumidifyBean", "deviceListBean", "updateInfoByPosition", "adapterPosition", "updateOnlineState", "online", "", "updateTopView", "updateWeather", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class EquipmentFragmentHelper {
    private final int GET_DEVICE_LIST_FAIL;
    private final int GET_DEVICE_LIST_SUCCESS;
    private final int GET_VERSION_SUCCESS;
    private final int GET_WEATHER_FAIL;
    private final int GET_WEATHER_SUCCESS;
    private final int STOP_REFRESH;
    private int curViewPagerPosition;
    private DeviceListBean currentBean;
    private DeviceListAdapter mDeviceListAdapter;
    private final EquipmentFragment mFragment;
    private Handler mHandler;
    private double mLatitude;
    private double mLongitude;
    private DeviceLisViewPagerAdapter mViewPageAdapter;
    private ViewPager2.OnPageChangeCallback pageChangeCallback;

    public EquipmentFragmentHelper(EquipmentFragment mFragment) {
        Intrinsics.checkNotNullParameter(mFragment, "mFragment");
        this.mFragment = mFragment;
        this.GET_DEVICE_LIST_SUCCESS = 100;
        this.GET_DEVICE_LIST_FAIL = 200;
        this.GET_WEATHER_SUCCESS = 300;
        this.GET_WEATHER_FAIL = 600;
        this.GET_VERSION_SUCCESS = 400;
        this.STOP_REFRESH = 500;
        this.mHandler = new Handler() { // from class: com.deye.helper.EquipmentFragmentHelper$mHandler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                int i = msg.what;
                if (i == this.this$0.getGET_DEVICE_LIST_SUCCESS()) {
                    this.this$0.mFragment.getDataBinding().srlRefreshLayout.finishRefresh();
                    this.this$0.setAdapter();
                } else if (i != this.this$0.getGET_WEATHER_FAIL()) {
                    if (i == this.this$0.getGET_DEVICE_LIST_FAIL()) {
                        this.this$0.mFragment.getDataBinding().srlRefreshLayout.finishRefresh();
                        BaseUtils.showShortToast(this.this$0.mFragment.getActivity(), StubApp.getString2(14158));
                    } else if (i == this.this$0.getSTOP_REFRESH()) {
                        this.this$0.mFragment.getDataBinding().srlRefreshLayout.finishRefresh();
                    } else {
                        this.this$0.getGET_VERSION_SUCCESS();
                    }
                }
            }
        };
    }

    public final int getGET_DEVICE_LIST_SUCCESS() {
        return this.GET_DEVICE_LIST_SUCCESS;
    }

    public final int getGET_DEVICE_LIST_FAIL() {
        return this.GET_DEVICE_LIST_FAIL;
    }

    public final int getGET_WEATHER_SUCCESS() {
        return this.GET_WEATHER_SUCCESS;
    }

    public final int getGET_WEATHER_FAIL() {
        return this.GET_WEATHER_FAIL;
    }

    public final int getGET_VERSION_SUCCESS() {
        return this.GET_VERSION_SUCCESS;
    }

    public final int getSTOP_REFRESH() {
        return this.STOP_REFRESH;
    }

    public final double getMLongitude() {
        return this.mLongitude;
    }

    public final void setMLongitude(double d) {
        this.mLongitude = d;
    }

    public final double getMLatitude() {
        return this.mLatitude;
    }

    public final void setMLatitude(double d) {
        this.mLatitude = d;
    }

    public final DeviceLisViewPagerAdapter getMViewPageAdapter() {
        return this.mViewPageAdapter;
    }

    public final void setMViewPageAdapter(DeviceLisViewPagerAdapter deviceLisViewPagerAdapter) {
        this.mViewPageAdapter = deviceLisViewPagerAdapter;
    }

    public final DeviceListAdapter getMDeviceListAdapter() {
        return this.mDeviceListAdapter;
    }

    public final void setMDeviceListAdapter(DeviceListAdapter deviceListAdapter) {
        this.mDeviceListAdapter = deviceListAdapter;
    }

    public final DeviceListBean getCurrentBean() {
        return this.currentBean;
    }

    public final void setCurrentBean(DeviceListBean deviceListBean) {
        this.currentBean = deviceListBean;
    }

    public final void netIsBad() {
        this.mFragment.getDataBinding().iNoNet.llNetError.setVisibility(0);
        BaseUtils.sendMessage(this.mHandler, this.STOP_REFRESH, "");
    }

    public final Handler getMHandler() {
        return this.mHandler;
    }

    public final void setMHandler(Handler handler) {
        Intrinsics.checkNotNullParameter(handler, "<set-?>");
        this.mHandler = handler;
    }

    private final void parseBeanList(List<? extends DeviceListBean> beanList) {
        int size = beanList.size();
        for (int i = 0; i < size; i++) {
            DeviceListBean deviceListBean = beanList.get(i);
            String payload = deviceListBean.getPayload();
            if (!deviceListBean.isFogPlatform() && !TextUtils.isEmpty(payload) && !payload.equals(StubApp.getString2(13461))) {
                Intrinsics.checkNotNull(payload);
                String strSubstring = payload.substring(4, payload.length());
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                String strSubstring2 = payload.substring(2, 4);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                boolean zIsLoopFan = deviceListBean.isLoopFan();
                String string2 = StubApp.getString2(8591);
                if (zIsLoopFan) {
                    if (Intrinsics.areEqual(string2, strSubstring2)) {
                        deviceListBean.loopFanBean = Hex2LoopFanBeanString.hex2Json(Hex2DeviceBeanString.getBinaryStr(strSubstring));
                    }
                } else if (Intrinsics.areEqual(string2, strSubstring2) || Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_FILTERED_GPS, strSubstring2)) {
                    DehumidifierBean dehumidifierBeanHex2Json = Hex2DehumidifierBeanString.hex2Json(Hex2DeviceBeanString.getBinaryStr(strSubstring));
                    Intrinsics.checkNotNullExpressionValue(dehumidifierBeanHex2Json, "hex2Json(...)");
                    deviceListBean.setDehumidifierBean(dehumidifierBeanHex2Json);
                }
                Log.d(StubApp.getString2(13551), StubApp.getString2(14162) + JSON.toJSONString(deviceListBean));
            }
        }
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [com.deye.helper.EquipmentFragmentHelper$setAdapter$beanList$1] */
    public final void setAdapter() {
        final FragmentActivity activity = this.mFragment.getActivity();
        if (activity == null) {
            return;
        }
        Gson gson = new Gson();
        final List<? extends DeviceListBean> list = (List) gson.fromJson(gson.toJson(this.mFragment.getMDeviceListBeanList()), new TypeToken<List<DeviceListBean>>() { // from class: com.deye.helper.EquipmentFragmentHelper$setAdapter$beanList$1
        }.getType());
        int size = list.size();
        String string2 = StubApp.getString2(14166);
        int i = 8;
        if (size < 6) {
            this.mFragment.getDataBinding().relRoot.setBackgroundResource(R.drawable.home_device_bg);
            this.mFragment.getDataBinding().tvName.setTextSize(18.0f);
            this.mFragment.getDataBinding().tvRoomName.setVisibility(0);
            this.mFragment.getDataBinding().recyclerView.setVisibility(8);
            this.mFragment.getDataBinding().deviceViewpager.setVisibility(0);
            this.mFragment.getDataBinding().circleIndicator.setVisibility(0);
            this.mFragment.getDataBinding().rlEnvValue.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = this.mFragment.getDataBinding().relContent.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, string2);
            ((FrameLayout.LayoutParams) layoutParams).setMargins(0, 0, 0, 0);
            Intrinsics.checkNotNull(list);
            parseBeanList(list);
            if (list.size() == 0) {
                DeviceHeartbeatManager.INSTANCE.stopAll();
            }
            TestTime.INSTANCE.logTime(StubApp.getString2(14167));
            requestAllFogProperties(list, new Function0<Unit>() { // from class: com.deye.helper.EquipmentFragmentHelper.setAdapter.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    LogDebug.INSTANCE.log(StubApp.getString2(14161));
                    EquipmentFragmentHelper equipmentFragmentHelper = EquipmentFragmentHelper.this;
                    Context context = activity;
                    List<DeviceListBean> beanList = list;
                    Intrinsics.checkNotNullExpressionValue(beanList, "$beanList");
                    equipmentFragmentHelper.setViewPagerAdapter(context, beanList);
                }
            });
        } else {
            Intrinsics.checkNotNull(list);
            parseBeanList(list);
            this.mFragment.getDataBinding().relRoot.setBackgroundResource(R.drawable.home_device_bg2);
            this.mFragment.getDataBinding().tvName.setTextSize(18.0f);
            this.mFragment.getDataBinding().tvRoomName.setVisibility(8);
            this.mFragment.getDataBinding().recyclerView.setVisibility(0);
            this.mFragment.getDataBinding().deviceViewpager.setVisibility(8);
            this.mFragment.getDataBinding().circleIndicator.setVisibility(8);
            this.mFragment.getDataBinding().rlEnvValue.setVisibility(8);
            TextView textView = this.mFragment.getDataBinding().tvName;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format(StubApp.getString2(14168), Arrays.copyOf(new Object[]{Integer.valueOf(list.size())}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            textView.setText(str);
            ViewGroup.LayoutParams layoutParams2 = this.mFragment.getDataBinding().relContent.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams2, string2);
            ((FrameLayout.LayoutParams) layoutParams2).setMargins(0, SignUtils.dp2px(90), 0, SignUtils.dp2px(80));
            requestAllFogProperties(list, new Function0<Unit>() { // from class: com.deye.helper.EquipmentFragmentHelper.setAdapter.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    EquipmentFragmentHelper equipmentFragmentHelper = EquipmentFragmentHelper.this;
                    Context context = activity;
                    List<DeviceListBean> beanList = list;
                    Intrinsics.checkNotNullExpressionValue(beanList, "$beanList");
                    equipmentFragmentHelper.setRecyclerViewAdapter(context, beanList);
                }
            });
        }
        LoopCircleIndicator3 loopCircleIndicator3 = this.mFragment.getDataBinding().circleIndicator;
        int size2 = list.size();
        if (1 <= size2 && size2 < 6) {
            i = 0;
        }
        loopCircleIndicator3.setVisibility(i);
    }

    private final void requestAllFogProperties(List<DeviceListBean> beanList, Function0<Unit> callBack) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : beanList) {
            if (((DeviceListBean) obj).isFogPlatform()) {
                arrayList.add(obj);
            }
        }
        ArrayList<DeviceListBean> arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            callBack.invoke();
            return;
        }
        Ref.IntRef intRef = new Ref.IntRef();
        for (DeviceListBean deviceListBean : arrayList2) {
            DeviceCacheManager deviceCacheManager = DeviceCacheManager.INSTANCE;
            String device_id = deviceListBean.getDevice_id();
            Intrinsics.checkNotNullExpressionValue(device_id, "getDevice_id(...)");
            DehumidifierBean deviceCache = deviceCacheManager.getDeviceCache(device_id, true);
            if (deviceCache != null) {
                LogDebug.INSTANCE.log(StubApp.getString2(14163) + deviceListBean);
                deviceListBean.setDehumidifierBean(deviceCache);
                intRef.element++;
                if (intRef.element == arrayList2.size()) {
                    callBack.invoke();
                }
            } else {
                TestTime.INSTANCE.logTime(StubApp.getString2(14164));
                FogDeviceManager fogDeviceManager = FogDeviceManager.INSTANCE;
                String device_id2 = deviceListBean.getDevice_id();
                Intrinsics.checkNotNullExpressionValue(device_id2, "getDevice_id(...)");
                fogDeviceManager.startRequestProperties(device_id2, new EquipmentFragmentHelper$requestAllFogProperties$1$1(deviceListBean, this, intRef, arrayList2, callBack));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestAllFogProperties$handleResult(Ref.IntRef intRef, List<? extends DeviceListBean> list, Function0<Unit> function0, DeviceListBean deviceListBean, PropertyResultBean propertyResultBean, boolean z) {
        if (propertyResultBean != null) {
            deviceListBean.setDehumidifierBean(FogDeviceManager.INSTANCE.convert(propertyResultBean));
        }
        LogDebug.INSTANCE.log(StubApp.getString2(14165) + deviceListBean);
        intRef.element++;
        if (!z) {
            DeviceCacheManager deviceCacheManager = DeviceCacheManager.INSTANCE;
            String device_id = deviceListBean.getDevice_id();
            Intrinsics.checkNotNullExpressionValue(device_id, "getDevice_id(...)");
            String product_id = deviceListBean.getProduct_id();
            Intrinsics.checkNotNullExpressionValue(product_id, "getProduct_id(...)");
            DehumidifierBean dehumidifierBean = deviceListBean.getDehumidifierBean();
            Intrinsics.checkNotNullExpressionValue(dehumidifierBean, "getDehumidifierBean(...)");
            deviceCacheManager.setHttpDeviceCache(device_id, product_id, dehumidifierBean);
        }
        if (intRef.element == list.size()) {
            function0.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setViewPagerAdapter(Context context, final List<DeviceListBean> beanList) {
        TestTime.INSTANCE.logTime(StubApp.getString2(14161));
        DeviceLisViewPagerAdapter deviceLisViewPagerAdapter = new DeviceLisViewPagerAdapter(context, beanList);
        this.mViewPageAdapter = deviceLisViewPagerAdapter;
        Intrinsics.checkNotNull(deviceLisViewPagerAdapter);
        deviceLisViewPagerAdapter.setOnItemClickListener(this.mFragment);
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.pageChangeCallback;
        if (onPageChangeCallback != null) {
            this.mFragment.getDataBinding().deviceViewpager.unregisterOnPageChangeCallback(onPageChangeCallback);
        }
        this.pageChangeCallback = new ViewPager2.OnPageChangeCallback() { // from class: com.deye.helper.EquipmentFragmentHelper.setViewPagerAdapter.2
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                EquipmentFragmentHelper.this.updateInfoByPosition(position, beanList);
            }
        };
        this.mFragment.getDataBinding().deviceViewpager.setAdapter(this.mViewPageAdapter);
        if (this.curViewPagerPosition >= beanList.size() + 1 || this.curViewPagerPosition == 0) {
            updateInfoByPosition(0, beanList);
            DeviceLisViewPagerAdapter deviceLisViewPagerAdapter2 = this.mViewPageAdapter;
            Intrinsics.checkNotNull(deviceLisViewPagerAdapter2);
            this.mFragment.getDataBinding().deviceViewpager.setCurrentItem(getStartPosition(deviceLisViewPagerAdapter2.getRealSize(), 0), false);
        } else {
            DeviceLisViewPagerAdapter deviceLisViewPagerAdapter3 = this.mViewPageAdapter;
            Intrinsics.checkNotNull(deviceLisViewPagerAdapter3);
            this.mFragment.getDataBinding().deviceViewpager.setCurrentItem(getStartPosition(deviceLisViewPagerAdapter3.getRealSize(), this.curViewPagerPosition), false);
        }
        ViewPager2 viewPager2 = this.mFragment.getDataBinding().deviceViewpager;
        ViewPager2.OnPageChangeCallback onPageChangeCallback2 = this.pageChangeCallback;
        Intrinsics.checkNotNull(onPageChangeCallback2);
        viewPager2.registerOnPageChangeCallback(onPageChangeCallback2);
        this.mFragment.getDataBinding().circleIndicator.setViewPager(this.mFragment.getDataBinding().deviceViewpager);
    }

    private final int getStartPosition(int realCount, int postion) {
        if (realCount == 0) {
            return 0;
        }
        return (1073741823 - (1073741823 % realCount)) + postion;
    }

    public final synchronized void updateInfoByPosition(int adapterPosition, List<DeviceListBean> beanList) {
        int realPosition;
        DeviceLisViewPagerAdapter deviceLisViewPagerAdapter;
        String string2 = StubApp.getString2(14169);
        synchronized (this) {
            Intrinsics.checkNotNullParameter(beanList, "beanList");
            try {
                DeviceLisViewPagerAdapter deviceLisViewPagerAdapter2 = this.mViewPageAdapter;
                Intrinsics.checkNotNull(deviceLisViewPagerAdapter2);
                realPosition = deviceLisViewPagerAdapter2.getRealPosition(adapterPosition);
                LogDebug.INSTANCE.log(string2 + realPosition + StubApp.getString2("14170") + beanList.size() + StubApp.getString2("14171") + this.mFragment.getMDeviceListBeanList().size());
                deviceLisViewPagerAdapter = this.mViewPageAdapter;
                Intrinsics.checkNotNull(deviceLisViewPagerAdapter);
            } catch (Throwable unused) {
            }
            if (realPosition >= deviceLisViewPagerAdapter.getRealSize()) {
                return;
            }
            this.curViewPagerPosition = realPosition;
            if (realPosition < beanList.size()) {
                TextView textView = this.mFragment.getDataBinding().tvName;
                DeviceLisViewPagerAdapter deviceLisViewPagerAdapter3 = this.mViewPageAdapter;
                Intrinsics.checkNotNull(deviceLisViewPagerAdapter3);
                textView.setText(deviceLisViewPagerAdapter3.getDeviceName(realPosition));
                TextView textView2 = this.mFragment.getDataBinding().tvRoomName;
                DeviceLisViewPagerAdapter deviceLisViewPagerAdapter4 = this.mViewPageAdapter;
                Intrinsics.checkNotNull(deviceLisViewPagerAdapter4);
                textView2.setText(deviceLisViewPagerAdapter4.getRoomName(realPosition));
                DeviceLisViewPagerAdapter deviceLisViewPagerAdapter5 = this.mViewPageAdapter;
                Intrinsics.checkNotNull(deviceLisViewPagerAdapter5);
                this.mFragment.getDataBinding().tvName.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, MxchipApplication.getInstance().getDrawable(deviceLisViewPagerAdapter5.isOnline(realPosition) ? R.drawable.icon_home_online : R.drawable.icon_home_offline), (Drawable) null);
                DeviceListBean deviceListBean = this.mFragment.getMDeviceListBeanList().get(realPosition);
                this.currentBean = deviceListBean;
                this.mFragment.getDataBinding().ivMore.setVisibility(0);
                if (deviceListBean.isFogPlatform() && deviceListBean.getDehumidifierBean() == null) {
                    DeviceHeartbeatManager deviceHeartbeatManager = DeviceHeartbeatManager.INSTANCE;
                    String device_id = deviceListBean.getDevice_id();
                    Intrinsics.checkNotNullExpressionValue(device_id, "getDevice_id(...)");
                    String device_name = deviceListBean.getDevice_name();
                    Intrinsics.checkNotNullExpressionValue(device_name, "getDevice_name(...)");
                    DeviceHeartbeatManager.startFogPolling$default(deviceHeartbeatManager, device_id, device_name, 0L, false, 12, null);
                }
                if (beanList.get(realPosition).isAirConditioner()) {
                    this.mFragment.getDataBinding().tvLeftTip.setText(StubApp.getString2("14172"));
                    this.mFragment.getDataBinding().tvRightTip.setText(this.mFragment.requireActivity().getString(R.string.indoor_temperature));
                    this.mFragment.getDataBinding().tvLeftUnit.setText(StubApp.getString2("13800"));
                    this.mFragment.getDataBinding().tvRightUnit.setText(StubApp.getString2("13800"));
                } else {
                    this.mFragment.getDataBinding().tvLeftTip.setText(this.mFragment.requireActivity().getString(R.string.indoor_humidity));
                    this.mFragment.getDataBinding().tvRightTip.setText(this.mFragment.requireActivity().getString(R.string.outdoor_humidity));
                    this.mFragment.getDataBinding().tvLeftUnit.setText(StubApp.getString2("5130"));
                    this.mFragment.getDataBinding().tvRightUnit.setText(StubApp.getString2("5130"));
                }
                updateTopView(beanList.get(realPosition));
                MainViewModel mMainViewModel = this.mFragment.getMMainViewModel();
                if (mMainViewModel != null) {
                    Intrinsics.checkNotNull(deviceListBean);
                    mMainViewModel.getWeatherByDevice(deviceListBean);
                }
                if (deviceListBean.location != null && deviceListBean.location.latitude != null && deviceListBean.location.longitude != null) {
                    this.mFragment.getDataBinding().llLocation.setVisibility(8);
                }
            } else {
                this.mFragment.getDataBinding().relRoot.setBackgroundResource(R.drawable.home_device_bg);
                this.mFragment.getDataBinding().tvName.post(new Runnable() { // from class: com.deye.helper.EquipmentFragmentHelper$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        EquipmentFragmentHelper.updateInfoByPosition$lambda$4(this.f$0);
                    }
                });
                this.mFragment.getDataBinding().tvRoomName.setText("");
                this.mFragment.getDataBinding().tvName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                this.mFragment.getDataBinding().tvLeft.setText(StubApp.getString2("13801"));
                this.mFragment.getDataBinding().tvRight.setText(StubApp.getString2("13801"));
                this.mFragment.getDataBinding().tvLeftTip.setText(this.mFragment.requireActivity().getString(R.string.indoor_humidity));
                this.mFragment.getDataBinding().tvRightTip.setText(this.mFragment.requireActivity().getString(R.string.outdoor_humidity));
                this.currentBean = null;
                updateTopView(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateInfoByPosition$lambda$4(EquipmentFragmentHelper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mFragment.getDataBinding().tvName.setText(this$0.mFragment.requireActivity().getString(R.string.welcome_to_deye_smart));
    }

    public final void updateWeather() {
        HashMap<String, LocationWeather> weatherMap;
        DeviceListBean deviceListBean = this.currentBean;
        if ((deviceListBean == null || deviceListBean == null || !deviceListBean.isAirConditioner()) && this.mFragment.getWeatherMap() != null) {
            HashMap<String, LocationWeather> weatherMap2 = this.mFragment.getWeatherMap();
            Intrinsics.checkNotNull(weatherMap2);
            if (weatherMap2.isEmpty() || (weatherMap = this.mFragment.getWeatherMap()) == null) {
                return;
            }
            DeviceListBean deviceListBean2 = this.currentBean;
            String string2 = StubApp.getString2(13951);
            if (deviceListBean2 == null) {
                setOutDoor(weatherMap.get(string2));
                return;
            }
            if ((deviceListBean2 != null ? deviceListBean2.location : null) != null) {
                DeviceListBean deviceListBean3 = this.currentBean;
                Intrinsics.checkNotNull(deviceListBean3);
                if (weatherMap.get(deviceListBean3.getDevice_id()) != null) {
                    DeviceListBean deviceListBean4 = this.currentBean;
                    Intrinsics.checkNotNull(deviceListBean4);
                    setOutDoor(weatherMap.get(deviceListBean4.getDevice_id()));
                    return;
                }
            }
            if (weatherMap.get(string2) != null) {
                setOutDoor(weatherMap.get(string2));
            }
        }
    }

    public final void setOutDoor(LocationWeather it2) {
        NowWeatherBean realtime;
        if (((it2 == null || (realtime = it2.getRealtime()) == null) ? null : realtime.getRh()) == null) {
            this.mFragment.getDataBinding().tvRight.setText(StubApp.getString2(13801));
            return;
        }
        CustomFontTextView customFontTextView = this.mFragment.getDataBinding().tvRight;
        NowWeatherBean realtime2 = it2.getRealtime();
        customFontTextView.setText(String.valueOf(realtime2 != null ? realtime2.getRh() : null));
    }

    private final void setLeft(String value) {
        if (value != null) {
            String str = value;
            if (!TextUtils.isEmpty(str)) {
                this.mFragment.getDataBinding().tvLeft.setText(str);
                return;
            }
        }
        this.mFragment.getDataBinding().tvLeft.setText(StubApp.getString2(13801));
    }

    private final void setRight(DehumidifierBean bean) {
        if (bean != null && bean.getCurrent_env_temp() != null) {
            this.mFragment.getDataBinding().tvRight.requestLayout();
            CustomFontTextView customFontTextView = this.mFragment.getDataBinding().tvRight;
            Intrinsics.checkNotNull(bean.getCurrent_env_temp());
            customFontTextView.setText(String.valueOf(Integer.parseInt(r2) - 40));
            return;
        }
        this.mFragment.getDataBinding().tvRight.setText(StubApp.getString2(13801));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRecyclerViewAdapter(Context context, List<DeviceListBean> beanList) {
        DeviceListAdapter deviceListAdapter = this.mDeviceListAdapter;
        if (deviceListAdapter == null) {
            DeviceListAdapter deviceListAdapter2 = new DeviceListAdapter(context, beanList);
            this.mDeviceListAdapter = deviceListAdapter2;
            Intrinsics.checkNotNull(deviceListAdapter2);
            deviceListAdapter2.setOnItemClickListener(this.mFragment);
            this.mFragment.getDataBinding().recyclerView.setLayoutManager(new LinearLayoutManager(context));
            this.mFragment.getDataBinding().recyclerView.setAdapter(this.mDeviceListAdapter);
            return;
        }
        Intrinsics.checkNotNull(deviceListAdapter);
        deviceListAdapter.setDateList(beanList);
    }

    public final void stopLocation() {
        LocationUtils.unregister();
    }

    public final void onLocationDestroy() {
        LocationUtils.unregister();
    }

    public final void updateDehumidifyBean(DeviceListBean deviceListBean) {
        DeviceLisViewPagerAdapter deviceLisViewPagerAdapter = this.mViewPageAdapter;
        if (deviceLisViewPagerAdapter != null) {
            Intrinsics.checkNotNull(deviceLisViewPagerAdapter);
            Intrinsics.checkNotNull(deviceListBean);
            deviceLisViewPagerAdapter.updateDehumidifyBean(deviceListBean);
            DeviceLisViewPagerAdapter deviceLisViewPagerAdapter2 = this.mViewPageAdapter;
            if (deviceLisViewPagerAdapter2 != null && deviceLisViewPagerAdapter2.getItemCount() == 2) {
                updateTopView(deviceListBean);
            } else {
                DeviceListBean deviceListBean2 = this.currentBean;
                if (StringsKt.equals$default(deviceListBean2 != null ? deviceListBean2.getDevice_id() : null, deviceListBean.getDevice_id(), false, 2, (Object) null)) {
                    updateTopView(deviceListBean);
                }
            }
        }
        DeviceListAdapter deviceListAdapter = this.mDeviceListAdapter;
        if (deviceListAdapter != null) {
            Intrinsics.checkNotNull(deviceListAdapter);
            Intrinsics.checkNotNull(deviceListBean);
            deviceListAdapter.updateDehumidifyBean(deviceListBean);
        }
    }

    private final void updateTopView(DeviceListBean deviceListBean) {
        updateWeather();
        if ((deviceListBean != null ? deviceListBean.getDehumidifierBean() : null) != null) {
            if (deviceListBean.isAirConditioner()) {
                setLeft(deviceListBean.getDehumidifierBean().temperature_set);
                setRight(deviceListBean.getDehumidifierBean());
                return;
            } else {
                setLeft(deviceListBean.getDehumidifierBean().getCurrent_env_hum());
                return;
            }
        }
        if ((deviceListBean != null ? deviceListBean.loopFanBean : null) != null) {
            setLeft(deviceListBean.loopFanBean.getCurrent_env_hum());
        } else {
            this.mFragment.getDataBinding().tvLeft.setText(StubApp.getString2(13801));
        }
    }

    public final void updateOnlineState(DeviceListBean deviceListBean, boolean online) {
        DeviceLisViewPagerAdapter deviceLisViewPagerAdapter = this.mViewPageAdapter;
        if (deviceLisViewPagerAdapter != null) {
            Intrinsics.checkNotNull(deviceLisViewPagerAdapter);
            Intrinsics.checkNotNull(deviceListBean);
            deviceLisViewPagerAdapter.updateOnlineState(deviceListBean, online);
        }
        DeviceListAdapter deviceListAdapter = this.mDeviceListAdapter;
        if (deviceListAdapter != null) {
            Intrinsics.checkNotNull(deviceListAdapter);
            Intrinsics.checkNotNull(deviceListBean);
            deviceListAdapter.updateOnlineState(deviceListBean, online);
        }
    }
}
