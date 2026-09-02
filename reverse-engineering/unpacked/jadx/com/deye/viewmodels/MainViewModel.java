package com.deye.viewmodels;

import android.app.Application;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.deye.helper.LocationUtils;
import com.deye.utils.MMKVUtils;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.api.http.RetrofitService;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.bean.LocationWeather;
import io.fogcloud.sdk.fog.log.LogUtil;
import io.fogcloud.sdk.fog.utils.SharedPrefsUtil;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONObject;

/* compiled from: MainViewModel.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010 \u001a\u00020!H\u0007J\u0010\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020$H\u0007J\u001a\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0003J\u0006\u0010*\u001a\u00020!R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\tR6\u0010\u0015\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\u0016j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b`\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR<\u0010\u001c\u001a$\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\u0016j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b`\u00170\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\t\"\u0004\b\u001e\u0010\u001f¨\u0006+"}, d2 = {"Lcom/deye/viewmodels/MainViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "addressLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "getAddressLiveData", "()Landroidx/lifecycle/MutableLiveData;", "locationLiveDate", "Lio/fogcloud/sdk/fog/bean/LocationWeather;", "getLocationLiveDate", "locationPermissionGranted", "", "getLocationPermissionGranted", "mOnLocationChangeListener", "Lcom/deye/helper/LocationUtils$OnLocationChangeListener;", "unReadMsgCountLiveDate", "", "getUnReadMsgCountLiveDate", "weatherMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getWeatherMap", "()Ljava/util/HashMap;", "setWeatherMap", "(Ljava/util/HashMap;)V", "weatherMapLiveData", "getWeatherMapLiveData", "setWeatherMapLiveData", "(Landroidx/lifecycle/MutableLiveData;)V", "getUnReadMsg", "", "getWeatherByDevice", "deviceListBean", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "handleLocation", "location", "Landroid/location/Location;", "callbackAddress", "Landroid/location/Address;", "requestLocation", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class MainViewModel extends AndroidViewModel {
    private final MutableLiveData<String> addressLiveData;
    private final MutableLiveData<LocationWeather> locationLiveDate;
    private final MutableLiveData<Boolean> locationPermissionGranted;
    private final LocationUtils.OnLocationChangeListener mOnLocationChangeListener;
    private final MutableLiveData<Integer> unReadMsgCountLiveDate;
    private HashMap<String, LocationWeather> weatherMap;
    private MutableLiveData<HashMap<String, LocationWeather>> weatherMapLiveData;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.weatherMap = new HashMap<>();
        this.weatherMapLiveData = new MutableLiveData<>();
        this.locationLiveDate = new MutableLiveData<>();
        this.addressLiveData = new MutableLiveData<>();
        this.locationPermissionGranted = new MutableLiveData<>();
        this.unReadMsgCountLiveDate = new MutableLiveData<>();
        this.mOnLocationChangeListener = new LocationUtils.OnLocationChangeListener() { // from class: com.deye.viewmodels.MainViewModel$mOnLocationChangeListener$1
            @Override // com.deye.helper.LocationUtils.OnLocationChangeListener
            public void onLocationChanged(Location location, Address address) {
                Intrinsics.checkNotNullParameter(location, "location");
            }

            @Override // com.deye.helper.LocationUtils.OnLocationChangeListener
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Intrinsics.checkNotNullParameter(provider, "provider");
                Intrinsics.checkNotNullParameter(extras, "extras");
            }

            @Override // com.deye.helper.LocationUtils.OnLocationChangeListener
            public void getLastKnownLocation(Location location, Address address) {
                Intrinsics.checkNotNullParameter(location, "location");
                if (this.this$0.getLocationLiveDate().getValue() != null) {
                    return;
                }
                this.this$0.handleLocation(location, address);
            }
        };
    }

    public final HashMap<String, LocationWeather> getWeatherMap() {
        return this.weatherMap;
    }

    public final void setWeatherMap(HashMap<String, LocationWeather> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.weatherMap = map;
    }

    public final MutableLiveData<HashMap<String, LocationWeather>> getWeatherMapLiveData() {
        return this.weatherMapLiveData;
    }

    public final void setWeatherMapLiveData(MutableLiveData<HashMap<String, LocationWeather>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.weatherMapLiveData = mutableLiveData;
    }

    public final MutableLiveData<LocationWeather> getLocationLiveDate() {
        return this.locationLiveDate;
    }

    public final MutableLiveData<String> getAddressLiveData() {
        return this.addressLiveData;
    }

    public final MutableLiveData<Boolean> getLocationPermissionGranted() {
        return this.locationPermissionGranted;
    }

    public final MutableLiveData<Integer> getUnReadMsgCountLiveDate() {
        return this.unReadMsgCountLiveDate;
    }

    /* compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.deye.viewmodels.MainViewModel$handleLocation$1", f = "MainViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.deye.viewmodels.MainViewModel$handleLocation$1, reason: invalid class name and case insensitive filesystem */
    static final class C01991 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Address $callbackAddress;
        final /* synthetic */ Location $location;
        int label;
        final /* synthetic */ MainViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01991(Address address, Location location, MainViewModel mainViewModel, Continuation<? super C01991> continuation) {
            super(2, continuation);
            this.$callbackAddress = address;
            this.$location = location;
            this.this$0 = mainViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01991(this.$callbackAddress, this.$location, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01991) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            C01991 c01991 = this;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (c01991.label != 0) {
                throw new IllegalStateException(StubApp.getString2(13735));
            }
            ResultKt.throwOnFailure(obj);
            Address address = c01991.$callbackAddress;
            if (address == null) {
                address = LocationUtils.getAddress(c01991.$location.getLatitude(), c01991.$location.getLongitude());
            }
            LogUtil.e(StubApp.getString2(14486) + c01991.$location.getProvider() + StubApp.getString2(9974) + c01991.$location.getLatitude() + StubApp.getString2(14487) + c01991.$location.getLongitude() + StubApp.getString2(9988) + c01991.$location.getAccuracy() + StubApp.getString2(14488) + c01991.$location.getTime() + StubApp.getString2(14489) + (c01991.$callbackAddress != null ? StubApp.getString2(5171) : StubApp.getString2(14485)));
            SharedPrefsUtil sharedPrefsUtil = new SharedPrefsUtil(c01991.this$0.getApplication());
            String strValueOf = String.valueOf(c01991.$location.getLongitude());
            String string2 = StubApp.getString2(13360);
            sharedPrefsUtil.putValue(string2, StubApp.getString2(13359), strValueOf);
            sharedPrefsUtil.putValue(string2, StubApp.getString2(13361), String.valueOf(c01991.$location.getLatitude()));
            if (address != null) {
                String adminArea = address.getAdminArea();
                String locality = address.getLocality();
                String subLocality = address.getSubLocality();
                String featureName = address.getFeatureName();
                String addressLine = address.getAddressLine(0);
                LogUtil.e(StubApp.getString2(14490) + address.getCountryName() + StubApp.getString2(14491) + adminArea + StubApp.getString2(14492) + locality + StubApp.getString2(14493) + subLocality + StubApp.getString2(14494) + address.getThoroughfare() + StubApp.getString2(14495) + featureName + StubApp.getString2(14496) + addressLine + StubApp.getString2(14497) + address.getLatitude() + StubApp.getString2(14498) + address.getLongitude());
                sharedPrefsUtil.putValue(string2, StubApp.getString2(13363), adminArea);
                sharedPrefsUtil.putValue(string2, StubApp.getString2(13362), addressLine);
                sharedPrefsUtil.putValue(string2, StubApp.getString2(13364), locality);
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(StubApp.getString2("5440"), address.getCountryName());
                    jSONObject.put(StubApp.getString2("14499"), address.getAdminArea());
                    jSONObject.put(StubApp.getString2("14500"), address.getLocality());
                    jSONObject.put(StubApp.getString2("14501"), address.getSubLocality());
                    jSONObject.put(StubApp.getString2("14502"), address.getThoroughfare());
                    jSONObject.put(StubApp.getString2("14503"), addressLine);
                    jSONObject.put(StubApp.getString2("7790"), address.getFeatureName());
                    jSONObject.put(StubApp.getString2("4653"), address.getLatitude());
                    jSONObject.put(StubApp.getString2("4661"), address.getLongitude());
                    MMKVUtils mMKVUtils = MMKVUtils.INSTANCE;
                    String string = jSONObject.toString();
                    Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                    mMKVUtils.setLocationInfo(string);
                } catch (Throwable unused) {
                }
                if (subLocality == null) {
                    c01991 = this;
                    c01991.this$0.getAddressLiveData().postValue(locality);
                } else {
                    c01991 = this;
                    c01991.this$0.getAddressLiveData().postValue(locality + StubApp.getString2(14504) + subLocality);
                }
            }
            Flowable<BaseResult<LocationWeather>> flowableObserveOn = RetrofitManager.INSTANCE.getApiService().getWeatherInfo(String.valueOf(c01991.$location.getLongitude()), String.valueOf(c01991.$location.getLatitude())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            final MainViewModel mainViewModel = c01991.this$0;
            flowableObserveOn.subscribe(new Consumer() { // from class: com.deye.viewmodels.MainViewModel.handleLocation.1.1
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(BaseResult<LocationWeather> it2) {
                    LocationWeather data;
                    Intrinsics.checkNotNullParameter(it2, "it");
                    BaseResult.MetaBean meta = it2.getMeta();
                    if (meta == null || meta.getCode() != 0 || (data = it2.getData()) == null) {
                        return;
                    }
                    MainViewModel mainViewModel2 = mainViewModel;
                    mainViewModel2.getLocationLiveDate().postValue(data);
                    mainViewModel2.getWeatherMap().put(StubApp.getString2(13951), data);
                    mainViewModel2.getWeatherMapLiveData().postValue(mainViewModel2.getWeatherMap());
                }
            }, new Consumer() { // from class: com.deye.viewmodels.MainViewModel.handleLocation.1.2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Throwable it2) {
                    Intrinsics.checkNotNullParameter(it2, "it");
                    it2.printStackTrace();
                }
            });
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleLocation(Location location, Address callbackAddress) {
        BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), Dispatchers.getIO(), (CoroutineStart) null, new C01991(callbackAddress, location, this, null), 2, (Object) null);
    }

    public final void getWeatherByDevice(final DeviceListBean deviceListBean) {
        Intrinsics.checkNotNullParameter(deviceListBean, "deviceListBean");
        if (this.weatherMap.get(deviceListBean.getDevice_id()) != null) {
            this.weatherMapLiveData.postValue(this.weatherMap);
            return;
        }
        if (deviceListBean.location != null && deviceListBean.location.latitude != null && deviceListBean.location.longitude != null) {
            RetrofitService apiService = RetrofitManager.INSTANCE.getApiService();
            String longitude = deviceListBean.location.longitude;
            Intrinsics.checkNotNullExpressionValue(longitude, "longitude");
            String latitude = deviceListBean.location.latitude;
            Intrinsics.checkNotNullExpressionValue(latitude, "latitude");
            apiService.getWeatherInfo(longitude, latitude).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.deye.viewmodels.MainViewModel.getWeatherByDevice.1
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(BaseResult<LocationWeather> it2) {
                    Intrinsics.checkNotNullParameter(it2, "it");
                    BaseResult.MetaBean meta = it2.getMeta();
                    if (meta == null || meta.getCode() != 0 || it2.getData() == null) {
                        return;
                    }
                    MainViewModel mainViewModel = MainViewModel.this;
                    DeviceListBean deviceListBean2 = deviceListBean;
                    HashMap<String, LocationWeather> weatherMap = mainViewModel.getWeatherMap();
                    String device_id = deviceListBean2.getDevice_id();
                    LocationWeather data = it2.getData();
                    Intrinsics.checkNotNull(data);
                    weatherMap.put(device_id, data);
                    mainViewModel.getWeatherMapLiveData().postValue(mainViewModel.getWeatherMap());
                }
            }, new Consumer() { // from class: com.deye.viewmodels.MainViewModel.getWeatherByDevice.2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Throwable it2) {
                    Intrinsics.checkNotNullParameter(it2, "it");
                    it2.printStackTrace();
                }
            });
            return;
        }
        this.weatherMapLiveData.postValue(this.weatherMap);
    }

    public final void getUnReadMsg() {
        RetrofitManager.INSTANCE.getApiService().getUnReadMsgCount().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.deye.viewmodels.MainViewModel.getUnReadMsg.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<Integer> it2) throws RuntimeException {
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseResult.MetaBean meta = it2.getMeta();
                if (meta == null || meta.getCode() != 0) {
                    return;
                }
                MainViewModel.this.getUnReadMsgCountLiveDate().postValue(it2.getData());
                MMKV mmkvDefaultMMKV = MMKV.defaultMMKV();
                Integer data = it2.getData();
                Intrinsics.checkNotNull(data);
                mmkvDefaultMMKV.putInt(StubApp.getString2(13739), data.intValue());
            }
        }, new Consumer() { // from class: com.deye.viewmodels.MainViewModel.getUnReadMsg.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
            }
        });
    }

    public final void requestLocation() {
        if (this.locationLiveDate.getValue() != null) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(getApplication(), StubApp.getString2(789)) != 0) {
            this.locationPermissionGranted.postValue(false);
            return;
        }
        this.locationPermissionGranted.postValue(true);
        LocationUtils.unregister();
        LocationUtils.register(0L, 0L, this.mOnLocationChangeListener);
    }
}
