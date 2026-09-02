package com.deye;

import com.deye.utils.TestTime;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.bean.PropertyParam;
import io.fogcloud.sdk.fog.bean.PropertyResultBean;
import io.fogcloud.sdk.fog.bean.PropertyWrapperBean;
import io.fogcloud.sdk.fog.log.LogDebug;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: FogDeviceManager.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0004H\u0002J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0010J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0010H\u0002J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0017\u001a\u00020\u0004J\u0010\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!H\u0002J\u0018\u0010\"\u001a\u00020\u001c2\b\u0010#\u001a\u0004\u0018\u00010\u00042\u0006\u0010$\u001a\u00020%J\u001e\u0010&\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010'\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u0019J\u001e\u0010(\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010)\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u0019J\u001e\u0010*\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010+\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u0019J\u001e\u0010,\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010-\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u0019J\u001e\u0010.\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010/\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u0019J\u001e\u00100\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u00101\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u0019J\u001e\u00102\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u00103\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u0019J\u001e\u00104\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u00105\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u0019J\u001e\u00106\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u00107\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u0019J\u0016\u00108\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u00107\u001a\u00020%J\u001e\u00109\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010:\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u0019J\u001e\u0010;\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010<\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u0019J\u001e\u0010=\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010>\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u0019J3\u0010?\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u00042#\u0010@\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(D\u0012\u0004\u0012\u00020\u001c0AR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR&\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00100\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006E"}, d2 = {"Lcom/deye/FogDeviceManager;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "deviceList", "", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "getDeviceList", "()Ljava/util/List;", "setDeviceList", "(Ljava/util/List;)V", "propertyMap", "", "Lio/fogcloud/sdk/fog/bean/PropertyResultBean;", "getPropertyMap", "()Ljava/util/Map;", "setPropertyMap", "(Ljava/util/Map;)V", "checkNeedAll", "", "deviceId", "convert", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "property", "convertBase", "", "bean", "getDeviceBeanById", "sendCommand", "propertyParam", "Lio/fogcloud/sdk/fog/bean/PropertyParam;", "sendDisPlayCommand", "mDeviceId", "i", "", "sendHumidityCommand", "humidity", "sendLockCommand", "lock", "sendModeCommand", "mode", "sendNegativeIonCommand", "anion", "sendPowerCommand", "power", "sendPoweroffTimeCommand", "hour", "sendSleepCommand", "sleep", "sendSpeedCommand", "speed", "sendTempCommand", "temp", "sendToneCommand", "sendUvLightCommand", "uv", "sendWaterPumpCommand", "waterPump", "sendWindSwitchCommand", "swingingWind", "startRequestProperties", "listener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "arg", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class FogDeviceManager {
    public static final FogDeviceManager INSTANCE = new FogDeviceManager();
    private static final String TAG = StubApp.getString2(13054);
    private static List<DeviceListBean> deviceList = new ArrayList();
    private static Map<String, PropertyResultBean> propertyMap = new LinkedHashMap();

    private FogDeviceManager() {
    }

    public final String getTAG() {
        return TAG;
    }

    public final List<DeviceListBean> getDeviceList() {
        return deviceList;
    }

    public final void setDeviceList(List<DeviceListBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        deviceList = list;
    }

    public final Map<String, PropertyResultBean> getPropertyMap() {
        return propertyMap;
    }

    public final void setPropertyMap(Map<String, PropertyResultBean> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        propertyMap = map;
    }

    public final DeviceListBean getDeviceBeanById(String deviceId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        if (deviceList.isEmpty()) {
            return null;
        }
        for (DeviceListBean deviceListBean : deviceList) {
            if (deviceListBean.getDevice_id().equals(deviceId)) {
                return deviceListBean;
            }
        }
        return null;
    }

    public final void startRequestProperties(final String deviceId, final Function1<? super PropertyResultBean, Unit> listener) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        TestTime.INSTANCE.logTime(StubApp.getString2(13071));
        Intrinsics.checkNotNullExpressionValue(RetrofitManager.INSTANCE.getApiService().getProperties(deviceId, String.valueOf(Math.random())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.deye.FogDeviceManager$startRequestProperties$flowable$1
            public final void accept(BaseResult<PropertyWrapperBean> baseResult) {
                Intrinsics.checkNotNullParameter(baseResult, "it");
                TestTime.INSTANCE.logTime(StubApp.getString2(13052));
                BaseResult.MetaBean meta = baseResult.getMeta();
                if (meta == null || meta.getCode() != 0) {
                    return;
                }
                PropertyWrapperBean propertyWrapperBean = (PropertyWrapperBean) baseResult.getData();
                PropertyResultBean propertyResultBean = propertyWrapperBean != null ? propertyWrapperBean.properties : null;
                if (propertyResultBean != null) {
                    FogDeviceManager.INSTANCE.getPropertyMap().put(deviceId, propertyResultBean);
                    listener.invoke(propertyResultBean);
                } else {
                    listener.invoke((Object) null);
                }
            }
        }, new Consumer() { // from class: com.deye.FogDeviceManager$startRequestProperties$flowable$2
            public final void accept(Throwable th) {
                Intrinsics.checkNotNullParameter(th, "it");
                LogDebug.INSTANCE.log(StubApp.getString2(13053) + th.getMessage());
                th.printStackTrace();
            }
        }), "subscribe(...)");
    }

    public final DehumidifierBean convert(PropertyResultBean property) {
        Intrinsics.checkNotNullParameter(property, "property");
        DehumidifierBean dehumidifierBean = new DehumidifierBean();
        convertBase(dehumidifierBean, property);
        return dehumidifierBean;
    }

    private final void convertBase(DehumidifierBean bean, PropertyResultBean property) {
        bean.setWind_switch(property.getSwingingWind());
        bean.setLock_switch(property.getKeyLock());
        bean.setSys_switch(property.getPower());
        bean.setPoweroff_flag(property.getTimedOff());
        bean.setDefrost_state(property.getDemisting());
        bean.setWatertank_state(property.getWaterTank());
        bean.setFan_switch(property.getFan());
        bean.setAnion_switch(property.getNegativeIon());
        bean.setPump_switch(property.getWaterPump());
        bean.setPress_state(property.getCompressorStatus());
        bean.setEnvironment_degree(property.getEnvironmentalRating());
        bean.setMode(property.getMode());
        bean.setScreendisplay(property.getScreendisplay());
        bean.setHkallowstatus(property.getHkallowstatus());
        bean.setHkmodeoperation(property.getHkmodeoperation());
        bean.setSpeed(property.getWindSpeed());
        bean.setHum_set(property.getSetHumidity());
        bean.setPoweroff_hour(property.getTimedShutdownHourSetting());
        bean.setPoweroff_minute(property.getTimedShutdownMinuteSettingTime());
        bean.setPoweroff_remain_hour(property.getTimedShutdownTimeRemainingHours());
        bean.setPoweroff_remain_minute(property.getTimedShutdownTimeRemainingMinutes());
        bean.setPoweron_hour(property.getTimedStartupHoursSetTime());
        bean.setPoweroff_minute(property.getTimedStartupMinuteSettingTime());
        bean.setPoweron_remain_hour(property.getTimedStartupTimeRemainingHours());
        bean.setPoweron_remain_minute(property.getTimedStartupTimeRemainingMinutes());
        bean.setCurrent_coil_temp(property.getCurrentCoilTemperature());
        String currentAmbientTemperature = property.getCurrentAmbientTemperature();
        if (currentAmbientTemperature != null) {
            bean.setCurrent_env_temp(String.valueOf(Integer.parseInt(currentAmbientTemperature) + 40));
        }
        bean.setCurrent_env_hum(property.getCurrentEnvironmentalHumidity());
        bean.setCurrent_exhaust_temp(property.getCurrentExhaustTemperature());
        bean.sleep_switch = property.getSleep();
        bean.temperature_set = property.getSetTemperature();
        bean.sound_switch = property.getPromptSound();
        bean.lights_switch = property.getAtmosphereLights();
        Integer operatingTimeOfCarbonRodFilterElement = property.getOperatingTimeOfCarbonRodFilterElement();
        bean.operatingTimeOfCarbonRodFilterElement = 100 - (operatingTimeOfCarbonRodFilterElement != null ? operatingTimeOfCarbonRodFilterElement.intValue() : 0);
        Integer durationOfOperationOfBactericidalSlowRelease = property.getDurationOfOperationOfBactericidalSlowRelease();
        bean.durationOfOperationOfBactericidalSlowRelease = 100 - (durationOfOperationOfBactericidalSlowRelease != null ? durationOfOperationOfBactericidalSlowRelease.intValue() : 0);
        Integer wetCurtainOperationDuration = property.getWetCurtainOperationDuration();
        bean.wetCurtainOperationDuration = 100 - (wetCurtainOperationDuration != null ? wetCurtainOperationDuration.intValue() : 0);
        Integer filterRunningTime = property.getFilterRunningTime();
        bean.filterRunningTime = 100 - (filterRunningTime != null ? filterRunningTime.intValue() : 0);
        Integer watertankcapacity = property.getWatertankcapacity();
        if (watertankcapacity != null) {
            bean.watertankcapacity = watertankcapacity.intValue();
        }
        bean.uvLight = property.getUv();
        bean.waterStatus = property.getWaterStatus();
        if (property.getFault() != null) {
            PropertyResultBean.Fault fault = property.getFault();
            Intrinsics.checkNotNull(fault);
            String a1 = fault.getA1();
            if (a1 == null) {
                a1 = "";
            }
            bean.A1 = a1;
            PropertyResultBean.Fault fault2 = property.getFault();
            Intrinsics.checkNotNull(fault2);
            String a2 = fault2.getA2();
            if (a2 == null) {
                a2 = "";
            }
            bean.A2 = a2;
            PropertyResultBean.Fault fault3 = property.getFault();
            Intrinsics.checkNotNull(fault3);
            String a3 = fault3.getA3();
            if (a3 == null) {
                a3 = "";
            }
            bean.A3 = a3;
            PropertyResultBean.Fault fault4 = property.getFault();
            Intrinsics.checkNotNull(fault4);
            String a4 = fault4.getA4();
            if (a4 == null) {
                a4 = "";
            }
            bean.A4 = a4;
            PropertyResultBean.Fault fault5 = property.getFault();
            Intrinsics.checkNotNull(fault5);
            String a5 = fault5.getA5();
            if (a5 == null) {
                a5 = "";
            }
            bean.A5 = a5;
            PropertyResultBean.Fault fault6 = property.getFault();
            Intrinsics.checkNotNull(fault6);
            String c5 = fault6.getC5();
            if (c5 == null) {
                c5 = "";
            }
            bean.setC5(c5);
            PropertyResultBean.Fault fault7 = property.getFault();
            Intrinsics.checkNotNull(fault7);
            String e0 = fault7.getE0();
            if (e0 == null) {
                e0 = "";
            }
            bean.setE0(e0);
            PropertyResultBean.Fault fault8 = property.getFault();
            Intrinsics.checkNotNull(fault8);
            String e1 = fault8.getE1();
            if (e1 == null) {
                e1 = "";
            }
            bean.setE1(e1);
            PropertyResultBean.Fault fault9 = property.getFault();
            Intrinsics.checkNotNull(fault9);
            String e2 = fault9.getE2();
            if (e2 == null) {
                e2 = "";
            }
            bean.setE2(e2);
            PropertyResultBean.Fault fault10 = property.getFault();
            Intrinsics.checkNotNull(fault10);
            String e3 = fault10.getE3();
            if (e3 == null) {
                e3 = "";
            }
            bean.setE3(e3);
            PropertyResultBean.Fault fault11 = property.getFault();
            Intrinsics.checkNotNull(fault11);
            String e4 = fault11.getE4();
            if (e4 == null) {
                e4 = "";
            }
            bean.setE4(e4);
            PropertyResultBean.Fault fault12 = property.getFault();
            Intrinsics.checkNotNull(fault12);
            String e5 = fault12.getE5();
            if (e5 == null) {
                e5 = "";
            }
            bean.setE5(e5);
            PropertyResultBean.Fault fault13 = property.getFault();
            Intrinsics.checkNotNull(fault13);
            String e6 = fault13.getE6();
            if (e6 == null) {
                e6 = "";
            }
            bean.setE6(e6);
            PropertyResultBean.Fault fault14 = property.getFault();
            Intrinsics.checkNotNull(fault14);
            String e7 = fault14.getE7();
            if (e7 == null) {
                e7 = "";
            }
            bean.setE7(e7);
            PropertyResultBean.Fault fault15 = property.getFault();
            Intrinsics.checkNotNull(fault15);
            String e8 = fault15.getE8();
            if (e8 == null) {
                e8 = "";
            }
            bean.setE8(e8);
            PropertyResultBean.Fault fault16 = property.getFault();
            Intrinsics.checkNotNull(fault16);
            String e9 = fault16.getE9();
            if (e9 == null) {
                e9 = "";
            }
            bean.setE9(e9);
            PropertyResultBean.Fault fault17 = property.getFault();
            Intrinsics.checkNotNull(fault17);
            String ea = fault17.getEA();
            if (ea == null) {
                ea = "";
            }
            bean.setEA(ea);
            PropertyResultBean.Fault fault18 = property.getFault();
            Intrinsics.checkNotNull(fault18);
            String eb = fault18.getEB();
            if (eb == null) {
                eb = "";
            }
            bean.setEB(eb);
            PropertyResultBean.Fault fault19 = property.getFault();
            Intrinsics.checkNotNull(fault19);
            String ec = fault19.getEC();
            if (ec == null) {
                ec = "";
            }
            bean.setEC(ec);
            PropertyResultBean.Fault fault20 = property.getFault();
            Intrinsics.checkNotNull(fault20);
            String ee = fault20.getEE();
            if (ee == null) {
                ee = "";
            }
            bean.setEE(ee);
            PropertyResultBean.Fault fault21 = property.getFault();
            Intrinsics.checkNotNull(fault21);
            String f1 = fault21.getF1();
            if (f1 == null) {
                f1 = "";
            }
            bean.setF1(f1);
            PropertyResultBean.Fault fault22 = property.getFault();
            Intrinsics.checkNotNull(fault22);
            String f2 = fault22.getF2();
            if (f2 == null) {
                f2 = "";
            }
            bean.setF2(f2);
            PropertyResultBean.Fault fault23 = property.getFault();
            Intrinsics.checkNotNull(fault23);
            String f3 = fault23.getF3();
            if (f3 == null) {
                f3 = "";
            }
            bean.setF3(f3);
            PropertyResultBean.Fault fault24 = property.getFault();
            Intrinsics.checkNotNull(fault24);
            String f4 = fault24.getF4();
            if (f4 == null) {
                f4 = "";
            }
            bean.setF4(f4);
            PropertyResultBean.Fault fault25 = property.getFault();
            Intrinsics.checkNotNull(fault25);
            String f5 = fault25.getF5();
            if (f5 == null) {
                f5 = "";
            }
            bean.setF5(f5);
            PropertyResultBean.Fault fault26 = property.getFault();
            Intrinsics.checkNotNull(fault26);
            String f6 = fault26.getF6();
            if (f6 == null) {
                f6 = "";
            }
            bean.setF6(f6);
            PropertyResultBean.Fault fault27 = property.getFault();
            Intrinsics.checkNotNull(fault27);
            String f7 = fault27.getF7();
            if (f7 == null) {
                f7 = "";
            }
            bean.setF7(f7);
            PropertyResultBean.Fault fault28 = property.getFault();
            Intrinsics.checkNotNull(fault28);
            String f8 = fault28.getF8();
            if (f8 == null) {
                f8 = "";
            }
            bean.setF8(f8);
            PropertyResultBean.Fault fault29 = property.getFault();
            Intrinsics.checkNotNull(fault29);
            String f9 = fault29.getF9();
            if (f9 == null) {
                f9 = "";
            }
            bean.setF9(f9);
            PropertyResultBean.Fault fault30 = property.getFault();
            Intrinsics.checkNotNull(fault30);
            String l1 = fault30.getL1();
            if (l1 == null) {
                l1 = "";
            }
            bean.setL1(l1);
            PropertyResultBean.Fault fault31 = property.getFault();
            Intrinsics.checkNotNull(fault31);
            String l2 = fault31.getL2();
            if (l2 == null) {
                l2 = "";
            }
            bean.setL2(l2);
            PropertyResultBean.Fault fault32 = property.getFault();
            Intrinsics.checkNotNull(fault32);
            String l3 = fault32.getL3();
            if (l3 == null) {
                l3 = "";
            }
            bean.setL3(l3);
            PropertyResultBean.Fault fault33 = property.getFault();
            Intrinsics.checkNotNull(fault33);
            String l4 = fault33.getL4();
            if (l4 == null) {
                l4 = "";
            }
            bean.setL4(l4);
            PropertyResultBean.Fault fault34 = property.getFault();
            Intrinsics.checkNotNull(fault34);
            String l6 = fault34.getL6();
            if (l6 == null) {
                l6 = "";
            }
            bean.setL6(l6);
            PropertyResultBean.Fault fault35 = property.getFault();
            Intrinsics.checkNotNull(fault35);
            String p0 = fault35.getP0();
            if (p0 == null) {
                p0 = "";
            }
            bean.setP0(p0);
            PropertyResultBean.Fault fault36 = property.getFault();
            Intrinsics.checkNotNull(fault36);
            String p1 = fault36.getP1();
            if (p1 == null) {
                p1 = "";
            }
            bean.setP1(p1);
            PropertyResultBean.Fault fault37 = property.getFault();
            Intrinsics.checkNotNull(fault37);
            String p2 = fault37.getP2();
            if (p2 == null) {
                p2 = "";
            }
            bean.setP2(p2);
            PropertyResultBean.Fault fault38 = property.getFault();
            Intrinsics.checkNotNull(fault38);
            String p3 = fault38.getP3();
            if (p3 == null) {
                p3 = "";
            }
            bean.setP3(p3);
            PropertyResultBean.Fault fault39 = property.getFault();
            Intrinsics.checkNotNull(fault39);
            String p4 = fault39.getP4();
            if (p4 == null) {
                p4 = "";
            }
            bean.setP4(p4);
            PropertyResultBean.Fault fault40 = property.getFault();
            Intrinsics.checkNotNull(fault40);
            String p5 = fault40.getP5();
            if (p5 == null) {
                p5 = "";
            }
            bean.setP5(p5);
            PropertyResultBean.Fault fault41 = property.getFault();
            Intrinsics.checkNotNull(fault41);
            String p6 = fault41.getP6();
            if (p6 == null) {
                p6 = "";
            }
            bean.setP6(p6);
            PropertyResultBean.Fault fault42 = property.getFault();
            Intrinsics.checkNotNull(fault42);
            String p7 = fault42.getP7();
            if (p7 == null) {
                p7 = "";
            }
            bean.setP7(p7);
            PropertyResultBean.Fault fault43 = property.getFault();
            Intrinsics.checkNotNull(fault43);
            String p8 = fault43.getP8();
            if (p8 == null) {
                p8 = "";
            }
            bean.setP8(p8);
            PropertyResultBean.Fault fault44 = property.getFault();
            Intrinsics.checkNotNull(fault44);
            String d1 = fault44.getD1();
            if (d1 == null) {
                d1 = "";
            }
            bean.d1 = d1;
            PropertyResultBean.Fault fault45 = property.getFault();
            Intrinsics.checkNotNull(fault45);
            String d2 = fault45.getD2();
            if (d2 == null) {
                d2 = "";
            }
            bean.d2 = d2;
            PropertyResultBean.Fault fault46 = property.getFault();
            Intrinsics.checkNotNull(fault46);
            String d3 = fault46.getD3();
            if (d3 == null) {
                d3 = "";
            }
            bean.d3 = d3;
            PropertyResultBean.Fault fault47 = property.getFault();
            Intrinsics.checkNotNull(fault47);
            String pl = fault47.getPL();
            if (pl == null) {
                pl = "";
            }
            bean.PL = pl;
            PropertyResultBean.Fault fault48 = property.getFault();
            Intrinsics.checkNotNull(fault48);
            String d4 = fault48.getD4();
            if (d4 == null) {
                d4 = "";
            }
            bean.d4 = d4;
            PropertyResultBean.Fault fault49 = property.getFault();
            Intrinsics.checkNotNull(fault49);
            String hs = fault49.getHS();
            if (hs == null) {
                hs = "";
            }
            bean.HS = hs;
            PropertyResultBean.Fault fault50 = property.getFault();
            Intrinsics.checkNotNull(fault50);
            String fl = fault50.getFL();
            bean.FL = fl != null ? fl : "";
        }
    }

    private final boolean checkNeedAll(String deviceId) {
        Integer protocolVersion;
        PropertyResultBean propertyResultBean = propertyMap.get(deviceId);
        return (propertyResultBean == null || (protocolVersion = propertyResultBean.getProtocolVersion()) == null || protocolVersion.intValue() != 0) ? false : true;
    }

    private final void sendCommand(PropertyParam propertyParam) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put(StubApp.getString2("13055"), propertyParam.device_id);
            PropertyParam.Params params = propertyParam.getParams();
            if (params != null) {
                if (params.getKeyLock() != null) {
                    jSONObject2.put(StubApp.getString2("13056"), params.getKeyLock());
                }
                if (params.getMode() != null) {
                    jSONObject2.put(StubApp.getString2("13057"), params.getMode());
                }
                if (params.getPower() != null) {
                    jSONObject2.put(StubApp.getString2("13058"), params.getPower());
                }
                if (params.getUV() != null) {
                    jSONObject2.put(StubApp.getString2("13059"), params.getUV());
                }
                if (params.getWindSpeed() != null) {
                    jSONObject2.put(StubApp.getString2("13060"), params.getWindSpeed());
                }
                if (params.getSetHumidity() != null) {
                    jSONObject2.put(StubApp.getString2("13061"), params.getSetHumidity());
                }
                if (params.getNegativeIon() != null) {
                    jSONObject2.put(StubApp.getString2("13062"), params.getNegativeIon());
                }
                if (params.getSwingingWind() != null) {
                    jSONObject2.put(StubApp.getString2("13063"), params.getSwingingWind());
                }
                if (params.getWaterPump() != null) {
                    jSONObject2.put(StubApp.getString2("13064"), params.getWaterPump());
                }
                if (params.getSleep() != null) {
                    jSONObject2.put(StubApp.getString2("13065"), params.getSleep());
                }
                if (params.getSetTemperature() != null) {
                    jSONObject2.put(StubApp.getString2("13066"), params.getSetTemperature());
                }
                if (params.getPromptSound() != null) {
                    jSONObject2.put(StubApp.getString2("13067"), params.getPromptSound());
                }
                if (params.getScreendisplay() != null) {
                    jSONObject2.put(StubApp.getString2("13068"), params.getScreendisplay());
                }
                if (params.getTimedOffHour() != null) {
                    jSONObject2.put(StubApp.getString2("13069"), params.getTimedOffHour());
                }
            }
            jSONObject.put(StubApp.getString2("13070"), jSONObject2);
            DeYeFogMqttManager.getInstance().sendSingleMsg(jSONObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public final void sendPowerCommand(String deviceId, int power, DehumidifierBean bean) throws JSONException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setPower(Integer.valueOf(power));
        }
        if (checkNeedAll(deviceId)) {
            PropertyParam.Params params2 = propertyParam.getParams();
            if (params2 != null) {
                String lock_switch = bean.getLock_switch();
                params2.setKeyLock(lock_switch != null ? StringsKt.toIntOrNull(lock_switch) : null);
            }
            PropertyParam.Params params3 = propertyParam.getParams();
            if (params3 != null) {
                String str = bean.uvLight;
                params3.setUV(str != null ? StringsKt.toIntOrNull(str) : null);
            }
            PropertyParam.Params params4 = propertyParam.getParams();
            if (params4 != null) {
                String mode = bean.getMode();
                params4.setMode(mode != null ? StringsKt.toIntOrNull(mode) : null);
            }
            PropertyParam.Params params5 = propertyParam.getParams();
            if (params5 != null) {
                String hum_set = bean.getHum_set();
                params5.setSetHumidity(hum_set != null ? StringsKt.toIntOrNull(hum_set) : null);
            }
            PropertyParam.Params params6 = propertyParam.getParams();
            if (params6 != null) {
                String speed = bean.getSpeed();
                params6.setWindSpeed(speed != null ? StringsKt.toIntOrNull(speed) : null);
            }
            PropertyParam.Params params7 = propertyParam.getParams();
            if (params7 != null) {
                String anion_switch = bean.getAnion_switch();
                params7.setNegativeIon(anion_switch != null ? StringsKt.toIntOrNull(anion_switch) : null);
            }
            PropertyParam.Params params8 = propertyParam.getParams();
            if (params8 != null) {
                String pump_switch = bean.getPump_switch();
                params8.setWaterPump(pump_switch != null ? StringsKt.toIntOrNull(pump_switch) : null);
            }
            PropertyParam.Params params9 = propertyParam.getParams();
            if (params9 != null) {
                String str2 = bean.sleep_switch;
                params9.setSleep(str2 != null ? StringsKt.toIntOrNull(str2) : null);
            }
            PropertyParam.Params params10 = propertyParam.getParams();
            if (params10 != null) {
                String str3 = bean.temperature_set;
                params10.setSetTemperature(str3 != null ? StringsKt.toIntOrNull(str3) : null);
            }
            PropertyParam.Params params11 = propertyParam.getParams();
            if (params11 != null) {
                String poweroff_hour = bean.getPoweroff_hour();
                params11.setTimedOffHour(poweroff_hour != null ? StringsKt.toIntOrNull(poweroff_hour) : null);
            }
        }
        sendCommand(propertyParam);
    }

    public final void sendSpeedCommand(String deviceId, int speed, DehumidifierBean bean) throws JSONException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setWindSpeed(Integer.valueOf(speed));
        }
        if (checkNeedAll(deviceId)) {
            PropertyParam.Params params2 = propertyParam.getParams();
            if (params2 != null) {
                String str = bean.uvLight;
                params2.setUV(str != null ? StringsKt.toIntOrNull(str) : null);
            }
            PropertyParam.Params params3 = propertyParam.getParams();
            if (params3 != null) {
                String lock_switch = bean.getLock_switch();
                params3.setKeyLock(lock_switch != null ? StringsKt.toIntOrNull(lock_switch) : null);
            }
            PropertyParam.Params params4 = propertyParam.getParams();
            if (params4 != null) {
                String mode = bean.getMode();
                params4.setMode(mode != null ? StringsKt.toIntOrNull(mode) : null);
            }
            PropertyParam.Params params5 = propertyParam.getParams();
            if (params5 != null) {
                String hum_set = bean.getHum_set();
                params5.setSetHumidity(hum_set != null ? StringsKt.toIntOrNull(hum_set) : null);
            }
            PropertyParam.Params params6 = propertyParam.getParams();
            if (params6 != null) {
                String sys_switch = bean.getSys_switch();
                params6.setPower(sys_switch != null ? StringsKt.toIntOrNull(sys_switch) : null);
            }
            PropertyParam.Params params7 = propertyParam.getParams();
            if (params7 != null) {
                String anion_switch = bean.getAnion_switch();
                params7.setNegativeIon(anion_switch != null ? StringsKt.toIntOrNull(anion_switch) : null);
            }
            PropertyParam.Params params8 = propertyParam.getParams();
            if (params8 != null) {
                String pump_switch = bean.getPump_switch();
                params8.setWaterPump(pump_switch != null ? StringsKt.toIntOrNull(pump_switch) : null);
            }
            PropertyParam.Params params9 = propertyParam.getParams();
            if (params9 != null) {
                String wind_switch = bean.getWind_switch();
                params9.setSwingingWind(wind_switch != null ? StringsKt.toIntOrNull(wind_switch) : null);
            }
            PropertyParam.Params params10 = propertyParam.getParams();
            if (params10 != null) {
                String str2 = bean.sleep_switch;
                params10.setSleep(str2 != null ? StringsKt.toIntOrNull(str2) : null);
            }
            PropertyParam.Params params11 = propertyParam.getParams();
            if (params11 != null) {
                String str3 = bean.temperature_set;
                params11.setSetTemperature(str3 != null ? StringsKt.toIntOrNull(str3) : null);
            }
            PropertyParam.Params params12 = propertyParam.getParams();
            if (params12 != null) {
                String poweroff_hour = bean.getPoweroff_hour();
                params12.setTimedOffHour(poweroff_hour != null ? StringsKt.toIntOrNull(poweroff_hour) : null);
            }
        }
        sendCommand(propertyParam);
    }

    public final void sendModeCommand(String deviceId, int mode, DehumidifierBean bean) throws JSONException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setMode(Integer.valueOf(mode));
        }
        if (checkNeedAll(deviceId)) {
            PropertyParam.Params params2 = propertyParam.getParams();
            if (params2 != null) {
                String str = bean.uvLight;
                params2.setUV(str != null ? StringsKt.toIntOrNull(str) : null);
            }
            PropertyParam.Params params3 = propertyParam.getParams();
            if (params3 != null) {
                String lock_switch = bean.getLock_switch();
                params3.setKeyLock(lock_switch != null ? StringsKt.toIntOrNull(lock_switch) : null);
            }
            PropertyParam.Params params4 = propertyParam.getParams();
            if (params4 != null) {
                String sys_switch = bean.getSys_switch();
                params4.setPower(sys_switch != null ? StringsKt.toIntOrNull(sys_switch) : null);
            }
            PropertyParam.Params params5 = propertyParam.getParams();
            if (params5 != null) {
                String hum_set = bean.getHum_set();
                params5.setSetHumidity(hum_set != null ? StringsKt.toIntOrNull(hum_set) : null);
            }
            PropertyParam.Params params6 = propertyParam.getParams();
            if (params6 != null) {
                String speed = bean.getSpeed();
                params6.setWindSpeed(speed != null ? StringsKt.toIntOrNull(speed) : null);
            }
            PropertyParam.Params params7 = propertyParam.getParams();
            if (params7 != null) {
                String anion_switch = bean.getAnion_switch();
                params7.setNegativeIon(anion_switch != null ? StringsKt.toIntOrNull(anion_switch) : null);
            }
            PropertyParam.Params params8 = propertyParam.getParams();
            if (params8 != null) {
                String pump_switch = bean.getPump_switch();
                params8.setWaterPump(pump_switch != null ? StringsKt.toIntOrNull(pump_switch) : null);
            }
            PropertyParam.Params params9 = propertyParam.getParams();
            if (params9 != null) {
                String wind_switch = bean.getWind_switch();
                params9.setSwingingWind(wind_switch != null ? StringsKt.toIntOrNull(wind_switch) : null);
            }
            PropertyParam.Params params10 = propertyParam.getParams();
            if (params10 != null) {
                String str2 = bean.sleep_switch;
                params10.setSleep(str2 != null ? StringsKt.toIntOrNull(str2) : null);
            }
            PropertyParam.Params params11 = propertyParam.getParams();
            if (params11 != null) {
                String str3 = bean.temperature_set;
                params11.setSetTemperature(str3 != null ? StringsKt.toIntOrNull(str3) : null);
            }
            PropertyParam.Params params12 = propertyParam.getParams();
            if (params12 != null) {
                String poweroff_hour = bean.getPoweroff_hour();
                params12.setTimedOffHour(poweroff_hour != null ? StringsKt.toIntOrNull(poweroff_hour) : null);
            }
        }
        sendCommand(propertyParam);
    }

    public final void sendHumidityCommand(String deviceId, int humidity, DehumidifierBean bean) throws JSONException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setSetHumidity(Integer.valueOf(humidity));
        }
        if (checkNeedAll(deviceId)) {
            PropertyParam.Params params2 = propertyParam.getParams();
            if (params2 != null) {
                String str = bean.uvLight;
                params2.setUV(str != null ? StringsKt.toIntOrNull(str) : null);
            }
            PropertyParam.Params params3 = propertyParam.getParams();
            if (params3 != null) {
                String lock_switch = bean.getLock_switch();
                params3.setKeyLock(lock_switch != null ? StringsKt.toIntOrNull(lock_switch) : null);
            }
            PropertyParam.Params params4 = propertyParam.getParams();
            if (params4 != null) {
                String sys_switch = bean.getSys_switch();
                params4.setPower(sys_switch != null ? StringsKt.toIntOrNull(sys_switch) : null);
            }
            PropertyParam.Params params5 = propertyParam.getParams();
            if (params5 != null) {
                String mode = bean.getMode();
                params5.setMode(mode != null ? StringsKt.toIntOrNull(mode) : null);
            }
            PropertyParam.Params params6 = propertyParam.getParams();
            if (params6 != null) {
                String speed = bean.getSpeed();
                params6.setWindSpeed(speed != null ? StringsKt.toIntOrNull(speed) : null);
            }
            PropertyParam.Params params7 = propertyParam.getParams();
            if (params7 != null) {
                String anion_switch = bean.getAnion_switch();
                params7.setNegativeIon(anion_switch != null ? StringsKt.toIntOrNull(anion_switch) : null);
            }
            PropertyParam.Params params8 = propertyParam.getParams();
            if (params8 != null) {
                String pump_switch = bean.getPump_switch();
                params8.setWaterPump(pump_switch != null ? StringsKt.toIntOrNull(pump_switch) : null);
            }
            PropertyParam.Params params9 = propertyParam.getParams();
            if (params9 != null) {
                String wind_switch = bean.getWind_switch();
                params9.setSwingingWind(wind_switch != null ? StringsKt.toIntOrNull(wind_switch) : null);
            }
            PropertyParam.Params params10 = propertyParam.getParams();
            if (params10 != null) {
                String poweroff_hour = bean.getPoweroff_hour();
                params10.setTimedOffHour(poweroff_hour != null ? StringsKt.toIntOrNull(poweroff_hour) : null);
            }
        }
        sendCommand(propertyParam);
    }

    public final void sendTempCommand(String deviceId, int temp, DehumidifierBean bean) throws JSONException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setSetTemperature(Integer.valueOf(temp));
        }
        if (checkNeedAll(deviceId)) {
            PropertyParam.Params params2 = propertyParam.getParams();
            if (params2 != null) {
                String lock_switch = bean.getLock_switch();
                params2.setKeyLock(lock_switch != null ? StringsKt.toIntOrNull(lock_switch) : null);
            }
            PropertyParam.Params params3 = propertyParam.getParams();
            if (params3 != null) {
                String sys_switch = bean.getSys_switch();
                params3.setPower(sys_switch != null ? StringsKt.toIntOrNull(sys_switch) : null);
            }
            PropertyParam.Params params4 = propertyParam.getParams();
            if (params4 != null) {
                String mode = bean.getMode();
                params4.setMode(mode != null ? StringsKt.toIntOrNull(mode) : null);
            }
            PropertyParam.Params params5 = propertyParam.getParams();
            if (params5 != null) {
                String speed = bean.getSpeed();
                params5.setWindSpeed(speed != null ? StringsKt.toIntOrNull(speed) : null);
            }
            PropertyParam.Params params6 = propertyParam.getParams();
            if (params6 != null) {
                String anion_switch = bean.getAnion_switch();
                params6.setNegativeIon(anion_switch != null ? StringsKt.toIntOrNull(anion_switch) : null);
            }
            PropertyParam.Params params7 = propertyParam.getParams();
            if (params7 != null) {
                String pump_switch = bean.getPump_switch();
                params7.setWaterPump(pump_switch != null ? StringsKt.toIntOrNull(pump_switch) : null);
            }
            PropertyParam.Params params8 = propertyParam.getParams();
            if (params8 != null) {
                String wind_switch = bean.getWind_switch();
                params8.setSwingingWind(wind_switch != null ? StringsKt.toIntOrNull(wind_switch) : null);
            }
            PropertyParam.Params params9 = propertyParam.getParams();
            if (params9 != null) {
                String str = bean.sleep_switch;
                params9.setSleep(str != null ? StringsKt.toIntOrNull(str) : null);
            }
            PropertyParam.Params params10 = propertyParam.getParams();
            if (params10 != null) {
                String poweroff_hour = bean.getPoweroff_hour();
                params10.setTimedOffHour(poweroff_hour != null ? StringsKt.toIntOrNull(poweroff_hour) : null);
            }
        }
        sendCommand(propertyParam);
    }

    public final void sendLockCommand(String deviceId, int lock, DehumidifierBean bean) throws JSONException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setKeyLock(Integer.valueOf(lock));
        }
        if (checkNeedAll(deviceId)) {
            PropertyParam.Params params2 = propertyParam.getParams();
            if (params2 != null) {
                String str = bean.uvLight;
                params2.setUV(str != null ? StringsKt.toIntOrNull(str) : null);
            }
            PropertyParam.Params params3 = propertyParam.getParams();
            if (params3 != null) {
                String hum_set = bean.getHum_set();
                params3.setSetHumidity(hum_set != null ? StringsKt.toIntOrNull(hum_set) : null);
            }
            PropertyParam.Params params4 = propertyParam.getParams();
            if (params4 != null) {
                String sys_switch = bean.getSys_switch();
                params4.setPower(sys_switch != null ? StringsKt.toIntOrNull(sys_switch) : null);
            }
            PropertyParam.Params params5 = propertyParam.getParams();
            if (params5 != null) {
                String mode = bean.getMode();
                params5.setMode(mode != null ? StringsKt.toIntOrNull(mode) : null);
            }
            PropertyParam.Params params6 = propertyParam.getParams();
            if (params6 != null) {
                String speed = bean.getSpeed();
                params6.setWindSpeed(speed != null ? StringsKt.toIntOrNull(speed) : null);
            }
            PropertyParam.Params params7 = propertyParam.getParams();
            if (params7 != null) {
                String anion_switch = bean.getAnion_switch();
                params7.setNegativeIon(anion_switch != null ? StringsKt.toIntOrNull(anion_switch) : null);
            }
            PropertyParam.Params params8 = propertyParam.getParams();
            if (params8 != null) {
                String pump_switch = bean.getPump_switch();
                params8.setWaterPump(pump_switch != null ? StringsKt.toIntOrNull(pump_switch) : null);
            }
            PropertyParam.Params params9 = propertyParam.getParams();
            if (params9 != null) {
                String wind_switch = bean.getWind_switch();
                params9.setSwingingWind(wind_switch != null ? StringsKt.toIntOrNull(wind_switch) : null);
            }
            PropertyParam.Params params10 = propertyParam.getParams();
            if (params10 != null) {
                String str2 = bean.sleep_switch;
                params10.setSleep(str2 != null ? StringsKt.toIntOrNull(str2) : null);
            }
            PropertyParam.Params params11 = propertyParam.getParams();
            if (params11 != null) {
                String str3 = bean.temperature_set;
                params11.setSetTemperature(str3 != null ? StringsKt.toIntOrNull(str3) : null);
            }
            PropertyParam.Params params12 = propertyParam.getParams();
            if (params12 != null) {
                String poweroff_hour = bean.getPoweroff_hour();
                params12.setTimedOffHour(poweroff_hour != null ? StringsKt.toIntOrNull(poweroff_hour) : null);
            }
        }
        sendCommand(propertyParam);
    }

    public final void sendNegativeIonCommand(String deviceId, int anion, DehumidifierBean bean) throws JSONException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setNegativeIon(Integer.valueOf(anion));
        }
        if (checkNeedAll(deviceId)) {
            PropertyParam.Params params2 = propertyParam.getParams();
            if (params2 != null) {
                String str = bean.uvLight;
                params2.setUV(str != null ? StringsKt.toIntOrNull(str) : null);
            }
            PropertyParam.Params params3 = propertyParam.getParams();
            if (params3 != null) {
                String hum_set = bean.getHum_set();
                params3.setSetHumidity(hum_set != null ? StringsKt.toIntOrNull(hum_set) : null);
            }
            PropertyParam.Params params4 = propertyParam.getParams();
            if (params4 != null) {
                String sys_switch = bean.getSys_switch();
                params4.setPower(sys_switch != null ? StringsKt.toIntOrNull(sys_switch) : null);
            }
            PropertyParam.Params params5 = propertyParam.getParams();
            if (params5 != null) {
                String mode = bean.getMode();
                params5.setMode(mode != null ? StringsKt.toIntOrNull(mode) : null);
            }
            PropertyParam.Params params6 = propertyParam.getParams();
            if (params6 != null) {
                String speed = bean.getSpeed();
                params6.setWindSpeed(speed != null ? StringsKt.toIntOrNull(speed) : null);
            }
            PropertyParam.Params params7 = propertyParam.getParams();
            if (params7 != null) {
                String lock_switch = bean.getLock_switch();
                params7.setKeyLock(lock_switch != null ? StringsKt.toIntOrNull(lock_switch) : null);
            }
            PropertyParam.Params params8 = propertyParam.getParams();
            if (params8 != null) {
                String pump_switch = bean.getPump_switch();
                params8.setWaterPump(pump_switch != null ? StringsKt.toIntOrNull(pump_switch) : null);
            }
            PropertyParam.Params params9 = propertyParam.getParams();
            if (params9 != null) {
                String wind_switch = bean.getWind_switch();
                params9.setSwingingWind(wind_switch != null ? StringsKt.toIntOrNull(wind_switch) : null);
            }
            PropertyParam.Params params10 = propertyParam.getParams();
            if (params10 != null) {
                String str2 = bean.sleep_switch;
                params10.setSleep(str2 != null ? StringsKt.toIntOrNull(str2) : null);
            }
            PropertyParam.Params params11 = propertyParam.getParams();
            if (params11 != null) {
                String str3 = bean.temperature_set;
                params11.setSetTemperature(str3 != null ? StringsKt.toIntOrNull(str3) : null);
            }
            PropertyParam.Params params12 = propertyParam.getParams();
            if (params12 != null) {
                String poweroff_hour = bean.getPoweroff_hour();
                params12.setTimedOffHour(poweroff_hour != null ? StringsKt.toIntOrNull(poweroff_hour) : null);
            }
        }
        sendCommand(propertyParam);
    }

    public final void sendUvLightCommand(String deviceId, int uv, DehumidifierBean bean) throws JSONException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setUV(Integer.valueOf(uv));
        }
        if (checkNeedAll(deviceId)) {
            PropertyParam.Params params2 = propertyParam.getParams();
            if (params2 != null) {
                String anion_switch = bean.getAnion_switch();
                params2.setNegativeIon(anion_switch != null ? StringsKt.toIntOrNull(anion_switch) : null);
            }
            PropertyParam.Params params3 = propertyParam.getParams();
            if (params3 != null) {
                String hum_set = bean.getHum_set();
                params3.setSetHumidity(hum_set != null ? StringsKt.toIntOrNull(hum_set) : null);
            }
            PropertyParam.Params params4 = propertyParam.getParams();
            if (params4 != null) {
                String sys_switch = bean.getSys_switch();
                params4.setPower(sys_switch != null ? StringsKt.toIntOrNull(sys_switch) : null);
            }
            PropertyParam.Params params5 = propertyParam.getParams();
            if (params5 != null) {
                String mode = bean.getMode();
                params5.setMode(mode != null ? StringsKt.toIntOrNull(mode) : null);
            }
            PropertyParam.Params params6 = propertyParam.getParams();
            if (params6 != null) {
                String speed = bean.getSpeed();
                params6.setWindSpeed(speed != null ? StringsKt.toIntOrNull(speed) : null);
            }
            PropertyParam.Params params7 = propertyParam.getParams();
            if (params7 != null) {
                String lock_switch = bean.getLock_switch();
                params7.setKeyLock(lock_switch != null ? StringsKt.toIntOrNull(lock_switch) : null);
            }
            PropertyParam.Params params8 = propertyParam.getParams();
            if (params8 != null) {
                String pump_switch = bean.getPump_switch();
                params8.setWaterPump(pump_switch != null ? StringsKt.toIntOrNull(pump_switch) : null);
            }
            PropertyParam.Params params9 = propertyParam.getParams();
            if (params9 != null) {
                String wind_switch = bean.getWind_switch();
                params9.setSwingingWind(wind_switch != null ? StringsKt.toIntOrNull(wind_switch) : null);
            }
            PropertyParam.Params params10 = propertyParam.getParams();
            if (params10 != null) {
                String str = bean.sleep_switch;
                params10.setSleep(str != null ? StringsKt.toIntOrNull(str) : null);
            }
            PropertyParam.Params params11 = propertyParam.getParams();
            if (params11 != null) {
                String str2 = bean.temperature_set;
                params11.setSetTemperature(str2 != null ? StringsKt.toIntOrNull(str2) : null);
            }
            PropertyParam.Params params12 = propertyParam.getParams();
            if (params12 != null) {
                String poweroff_hour = bean.getPoweroff_hour();
                params12.setTimedOffHour(poweroff_hour != null ? StringsKt.toIntOrNull(poweroff_hour) : null);
            }
        }
        sendCommand(propertyParam);
    }

    public final void sendWindSwitchCommand(String deviceId, int swingingWind, DehumidifierBean bean) throws JSONException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setSwingingWind(Integer.valueOf(swingingWind));
        }
        if (checkNeedAll(deviceId)) {
            PropertyParam.Params params2 = propertyParam.getParams();
            if (params2 != null) {
                String str = bean.uvLight;
                params2.setUV(str != null ? StringsKt.toIntOrNull(str) : null);
            }
            PropertyParam.Params params3 = propertyParam.getParams();
            if (params3 != null) {
                String hum_set = bean.getHum_set();
                params3.setSetHumidity(hum_set != null ? StringsKt.toIntOrNull(hum_set) : null);
            }
            PropertyParam.Params params4 = propertyParam.getParams();
            if (params4 != null) {
                String sys_switch = bean.getSys_switch();
                params4.setPower(sys_switch != null ? StringsKt.toIntOrNull(sys_switch) : null);
            }
            PropertyParam.Params params5 = propertyParam.getParams();
            if (params5 != null) {
                String mode = bean.getMode();
                params5.setMode(mode != null ? StringsKt.toIntOrNull(mode) : null);
            }
            PropertyParam.Params params6 = propertyParam.getParams();
            if (params6 != null) {
                String speed = bean.getSpeed();
                params6.setWindSpeed(speed != null ? StringsKt.toIntOrNull(speed) : null);
            }
            PropertyParam.Params params7 = propertyParam.getParams();
            if (params7 != null) {
                String lock_switch = bean.getLock_switch();
                params7.setKeyLock(lock_switch != null ? StringsKt.toIntOrNull(lock_switch) : null);
            }
            PropertyParam.Params params8 = propertyParam.getParams();
            if (params8 != null) {
                String pump_switch = bean.getPump_switch();
                params8.setWaterPump(pump_switch != null ? StringsKt.toIntOrNull(pump_switch) : null);
            }
            PropertyParam.Params params9 = propertyParam.getParams();
            if (params9 != null) {
                String anion_switch = bean.getAnion_switch();
                params9.setNegativeIon(anion_switch != null ? StringsKt.toIntOrNull(anion_switch) : null);
            }
            PropertyParam.Params params10 = propertyParam.getParams();
            if (params10 != null) {
                String str2 = bean.sleep_switch;
                params10.setSleep(str2 != null ? StringsKt.toIntOrNull(str2) : null);
            }
            PropertyParam.Params params11 = propertyParam.getParams();
            if (params11 != null) {
                String str3 = bean.temperature_set;
                params11.setSetTemperature(str3 != null ? StringsKt.toIntOrNull(str3) : null);
            }
            PropertyParam.Params params12 = propertyParam.getParams();
            if (params12 != null) {
                String poweroff_hour = bean.getPoweroff_hour();
                params12.setTimedOffHour(poweroff_hour != null ? StringsKt.toIntOrNull(poweroff_hour) : null);
            }
        }
        sendCommand(propertyParam);
    }

    public final void sendWaterPumpCommand(String deviceId, int waterPump, DehumidifierBean bean) throws JSONException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setWaterPump(Integer.valueOf(waterPump));
        }
        if (checkNeedAll(deviceId)) {
            PropertyParam.Params params2 = propertyParam.getParams();
            if (params2 != null) {
                String str = bean.uvLight;
                params2.setUV(str != null ? StringsKt.toIntOrNull(str) : null);
            }
            PropertyParam.Params params3 = propertyParam.getParams();
            if (params3 != null) {
                String hum_set = bean.getHum_set();
                params3.setSetHumidity(hum_set != null ? StringsKt.toIntOrNull(hum_set) : null);
            }
            PropertyParam.Params params4 = propertyParam.getParams();
            if (params4 != null) {
                String sys_switch = bean.getSys_switch();
                params4.setPower(sys_switch != null ? StringsKt.toIntOrNull(sys_switch) : null);
            }
            PropertyParam.Params params5 = propertyParam.getParams();
            if (params5 != null) {
                String mode = bean.getMode();
                params5.setMode(mode != null ? StringsKt.toIntOrNull(mode) : null);
            }
            PropertyParam.Params params6 = propertyParam.getParams();
            if (params6 != null) {
                String speed = bean.getSpeed();
                params6.setWindSpeed(speed != null ? StringsKt.toIntOrNull(speed) : null);
            }
            PropertyParam.Params params7 = propertyParam.getParams();
            if (params7 != null) {
                String lock_switch = bean.getLock_switch();
                params7.setKeyLock(lock_switch != null ? StringsKt.toIntOrNull(lock_switch) : null);
            }
            PropertyParam.Params params8 = propertyParam.getParams();
            if (params8 != null) {
                String wind_switch = bean.getWind_switch();
                params8.setSwingingWind(wind_switch != null ? StringsKt.toIntOrNull(wind_switch) : null);
            }
            PropertyParam.Params params9 = propertyParam.getParams();
            if (params9 != null) {
                String anion_switch = bean.getAnion_switch();
                params9.setNegativeIon(anion_switch != null ? StringsKt.toIntOrNull(anion_switch) : null);
            }
            PropertyParam.Params params10 = propertyParam.getParams();
            if (params10 != null) {
                String str2 = bean.sleep_switch;
                params10.setSleep(str2 != null ? StringsKt.toIntOrNull(str2) : null);
            }
            PropertyParam.Params params11 = propertyParam.getParams();
            if (params11 != null) {
                String str3 = bean.temperature_set;
                params11.setSetTemperature(str3 != null ? StringsKt.toIntOrNull(str3) : null);
            }
            PropertyParam.Params params12 = propertyParam.getParams();
            if (params12 != null) {
                String poweroff_hour = bean.getPoweroff_hour();
                params12.setTimedOffHour(poweroff_hour != null ? StringsKt.toIntOrNull(poweroff_hour) : null);
            }
        }
        sendCommand(propertyParam);
    }

    public final void sendSleepCommand(String deviceId, int sleep, DehumidifierBean bean) throws JSONException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setSleep(Integer.valueOf(sleep));
        }
        if (checkNeedAll(deviceId)) {
            PropertyParam.Params params2 = propertyParam.getParams();
            if (params2 != null) {
                String hum_set = bean.getHum_set();
                params2.setSetHumidity(hum_set != null ? StringsKt.toIntOrNull(hum_set) : null);
            }
            PropertyParam.Params params3 = propertyParam.getParams();
            if (params3 != null) {
                String sys_switch = bean.getSys_switch();
                params3.setPower(sys_switch != null ? StringsKt.toIntOrNull(sys_switch) : null);
            }
            PropertyParam.Params params4 = propertyParam.getParams();
            if (params4 != null) {
                String mode = bean.getMode();
                params4.setMode(mode != null ? StringsKt.toIntOrNull(mode) : null);
            }
            PropertyParam.Params params5 = propertyParam.getParams();
            if (params5 != null) {
                String speed = bean.getSpeed();
                params5.setWindSpeed(speed != null ? StringsKt.toIntOrNull(speed) : null);
            }
            PropertyParam.Params params6 = propertyParam.getParams();
            if (params6 != null) {
                String lock_switch = bean.getLock_switch();
                params6.setKeyLock(lock_switch != null ? StringsKt.toIntOrNull(lock_switch) : null);
            }
            PropertyParam.Params params7 = propertyParam.getParams();
            if (params7 != null) {
                String wind_switch = bean.getWind_switch();
                params7.setSwingingWind(wind_switch != null ? StringsKt.toIntOrNull(wind_switch) : null);
            }
            PropertyParam.Params params8 = propertyParam.getParams();
            if (params8 != null) {
                String anion_switch = bean.getAnion_switch();
                params8.setNegativeIon(anion_switch != null ? StringsKt.toIntOrNull(anion_switch) : null);
            }
            PropertyParam.Params params9 = propertyParam.getParams();
            if (params9 != null) {
                String pump_switch = bean.getPump_switch();
                params9.setWaterPump(pump_switch != null ? StringsKt.toIntOrNull(pump_switch) : null);
            }
            PropertyParam.Params params10 = propertyParam.getParams();
            if (params10 != null) {
                String str = bean.temperature_set;
                params10.setSetTemperature(str != null ? StringsKt.toIntOrNull(str) : null);
            }
            PropertyParam.Params params11 = propertyParam.getParams();
            if (params11 != null) {
                String poweroff_hour = bean.getPoweroff_hour();
                params11.setTimedOffHour(poweroff_hour != null ? StringsKt.toIntOrNull(poweroff_hour) : null);
            }
        }
        sendCommand(propertyParam);
    }

    public final void sendDisPlayCommand(String mDeviceId, int i) throws JSONException {
        PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = mDeviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setScreendisplay(Integer.valueOf(i));
        }
        sendCommand(propertyParam);
    }

    public final void sendToneCommand(String deviceId, int temp) throws JSONException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setPromptSound(Integer.valueOf(temp));
        }
        sendCommand(propertyParam);
    }

    public final void sendPoweroffTimeCommand(String deviceId, int hour, DehumidifierBean bean) throws JSONException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setTimedOffHour(Integer.valueOf(hour));
        }
        sendCommand(propertyParam);
    }
}
