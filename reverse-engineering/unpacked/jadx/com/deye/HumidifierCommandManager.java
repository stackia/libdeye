package com.deye;

import android.os.Handler;
import android.os.HandlerThread;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.bean.PropertyParam;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: HumidifierCommandManager.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u001e\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019J\u001e\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019J\u001e\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0019J\u001e\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019J\u001e\u0010\u001e\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u0019J&\u0010 \u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0019J\u001e\u0010$\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u0019J\u0016\u0010&\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u0019J\u001e\u0010(\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010)\u001a\u00020\u0019J\u001e\u0010*\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u0019J\u001e\u0010,\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019J\u001e\u0010-\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/deye/HumidifierCommandManager;", "", "()V", "handle", "Landroid/os/Handler;", "getHandle", "()Landroid/os/Handler;", "handle$delegate", "Lkotlin/Lazy;", "handlerThread", "Landroid/os/HandlerThread;", "execute", "", "runnable", "Ljava/lang/Runnable;", "isFilterCommand", "", "sendCommand", "propertyParam", "Lio/fogcloud/sdk/fog/bean/PropertyParam;", "sendDisPlayCommand", "deviceId", "", "productId", "temp", "", "sendHkAllowCommand", "sendHumidityCommand", "humidity", "sendLightCommand", "sendLockCommand", "lock", "sendModeCommand", "bean", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "mode", "sendNegativeIonCommand", "anion", "sendPartsResetCommand", "part", "sendPowerCommand", "power", "sendSpeedCommand", "speed", "sendToneCommand", "sendVoiceCommand", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class HumidifierCommandManager {
    public static final HumidifierCommandManager INSTANCE = new HumidifierCommandManager();

    /* renamed from: handle$delegate, reason: from kotlin metadata */
    private static final Lazy handle = LazyKt.lazy(new Function0<Handler>() { // from class: com.deye.HumidifierCommandManager$handle$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Handler invoke() {
            return new Handler(HumidifierCommandManager.handlerThread.getLooper());
        }
    });
    private static final HandlerThread handlerThread;

    private HumidifierCommandManager() {
    }

    static {
        HandlerThread handlerThread2 = new HandlerThread(StubApp.getString2(13072), 10);
        handlerThread2.start();
        handlerThread = handlerThread2;
    }

    private final Handler getHandle() {
        return (Handler) handle.getValue();
    }

    static /* synthetic */ void execute$default(HumidifierCommandManager humidifierCommandManager, Runnable runnable, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        humidifierCommandManager.execute(runnable, z);
    }

    private final void execute(final Runnable runnable, boolean isFilterCommand) {
        getHandle().removeCallbacksAndMessages(null);
        if (System.currentTimeMillis() - CommandManger.INSTANCE.getLastSendTime() > 2000) {
            getHandle().post(new Runnable() { // from class: com.deye.HumidifierCommandManager$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    HumidifierCommandManager.execute$lambda$1(runnable);
                }
            });
        } else {
            getHandle().postDelayed(new Runnable() { // from class: com.deye.HumidifierCommandManager$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    HumidifierCommandManager.execute$lambda$2(runnable);
                }
            }, isFilterCommand ? 1000L : 30L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$1(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "$runnable");
        CommandManger.INSTANCE.setLastSendTime(System.currentTimeMillis());
        runnable.run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$2(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "$runnable");
        CommandManger.INSTANCE.setLastSendTime(System.currentTimeMillis());
        runnable.run();
    }

    public final void sendPowerCommand(String deviceId, String productId, int power) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        final PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setPower(Integer.valueOf(power));
        }
        execute(new Runnable() { // from class: com.deye.HumidifierCommandManager$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                HumidifierCommandManager.sendPowerCommand$lambda$3(propertyParam);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendPowerCommand$lambda$3(PropertyParam param) throws JSONException {
        Intrinsics.checkNotNullParameter(param, "$param");
        INSTANCE.sendCommand(param);
    }

    public final void sendSpeedCommand(String deviceId, String productId, int speed) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        final PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setWindSpeed(Integer.valueOf(speed));
        }
        execute$default(this, new Runnable() { // from class: com.deye.HumidifierCommandManager$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                HumidifierCommandManager.sendSpeedCommand$lambda$4(propertyParam);
            }
        }, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendSpeedCommand$lambda$4(PropertyParam param) throws JSONException {
        Intrinsics.checkNotNullParameter(param, "$param");
        INSTANCE.sendCommand(param);
    }

    public final void sendModeCommand(String deviceId, String productId, DehumidifierBean bean, int mode) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        bean.setMode(String.valueOf(mode));
        DeviceCacheManager.INSTANCE.setDeviceCache(deviceId, productId, bean);
        final PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setMode(Integer.valueOf(mode));
        }
        execute(new Runnable() { // from class: com.deye.HumidifierCommandManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                HumidifierCommandManager.sendModeCommand$lambda$5(propertyParam);
            }
        }, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendModeCommand$lambda$5(PropertyParam param) throws JSONException {
        Intrinsics.checkNotNullParameter(param, "$param");
        INSTANCE.sendCommand(param);
    }

    public final void sendHumidityCommand(String deviceId, String productId, int humidity) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        final PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setSetHumidity(Integer.valueOf(humidity));
        }
        execute$default(this, new Runnable() { // from class: com.deye.HumidifierCommandManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                HumidifierCommandManager.sendHumidityCommand$lambda$6(propertyParam);
            }
        }, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendHumidityCommand$lambda$6(PropertyParam param) throws JSONException {
        Intrinsics.checkNotNullParameter(param, "$param");
        INSTANCE.sendCommand(param);
    }

    public final void sendPartsResetCommand(String deviceId, int part) throws JSONException {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        if (part == 1) {
            PropertyParam.Params params = propertyParam.getParams();
            if (params != null) {
                params.setWetcurtaincleaningpromptreset(1);
            }
        } else if (part == 2) {
            PropertyParam.Params params2 = propertyParam.getParams();
            if (params2 != null) {
                params2.setFilterreplacementpromptreset(1);
            }
        } else if (part == 3) {
            PropertyParam.Params params3 = propertyParam.getParams();
            if (params3 != null) {
                params3.setReleadereplacementpromptreset(1);
            }
        } else {
            PropertyParam.Params params4 = propertyParam.getParams();
            if (params4 != null) {
                params4.setFiltermentpromptreset(1);
            }
        }
        sendCommand(propertyParam);
    }

    public final void sendLightCommand(String deviceId, String productId, int temp) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        final PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setAtmosphereLights(Integer.valueOf(temp));
        }
        execute(new Runnable() { // from class: com.deye.HumidifierCommandManager$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                HumidifierCommandManager.sendLightCommand$lambda$7(propertyParam);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendLightCommand$lambda$7(PropertyParam param) throws JSONException {
        Intrinsics.checkNotNullParameter(param, "$param");
        INSTANCE.sendCommand(param);
    }

    public final void sendToneCommand(String deviceId, String productId, int temp) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        final PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setPromptSound(Integer.valueOf(temp));
        }
        execute(new Runnable() { // from class: com.deye.HumidifierCommandManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                HumidifierCommandManager.sendToneCommand$lambda$8(propertyParam);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendToneCommand$lambda$8(PropertyParam param) throws JSONException {
        Intrinsics.checkNotNullParameter(param, "$param");
        INSTANCE.sendCommand(param);
    }

    public final void sendDisPlayCommand(String deviceId, String productId, int temp) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        final PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setScreendisplay(Integer.valueOf(temp));
        }
        execute(new Runnable() { // from class: com.deye.HumidifierCommandManager$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                HumidifierCommandManager.sendDisPlayCommand$lambda$9(propertyParam);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendDisPlayCommand$lambda$9(PropertyParam param) throws JSONException {
        Intrinsics.checkNotNullParameter(param, "$param");
        INSTANCE.sendCommand(param);
    }

    public final void sendHkAllowCommand(String deviceId, String productId, int temp) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        final PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setHkallowstatus(Integer.valueOf(temp));
        }
        execute(new Runnable() { // from class: com.deye.HumidifierCommandManager$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                HumidifierCommandManager.sendHkAllowCommand$lambda$10(propertyParam);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendHkAllowCommand$lambda$10(PropertyParam param) throws JSONException {
        Intrinsics.checkNotNullParameter(param, "$param");
        INSTANCE.sendCommand(param);
    }

    public final void sendVoiceCommand(String deviceId, String productId, int temp) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        final PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setPromptSound(Integer.valueOf(temp));
        }
        execute(new Runnable() { // from class: com.deye.HumidifierCommandManager$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                HumidifierCommandManager.sendVoiceCommand$lambda$11(propertyParam);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendVoiceCommand$lambda$11(PropertyParam param) throws JSONException {
        Intrinsics.checkNotNullParameter(param, "$param");
        INSTANCE.sendCommand(param);
    }

    public final void sendLockCommand(String deviceId, String productId, int lock) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        final PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setKeyLock(Integer.valueOf(lock));
        }
        execute(new Runnable() { // from class: com.deye.HumidifierCommandManager$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                HumidifierCommandManager.sendLockCommand$lambda$12(propertyParam);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendLockCommand$lambda$12(PropertyParam param) throws JSONException {
        Intrinsics.checkNotNullParameter(param, "$param");
        INSTANCE.sendCommand(param);
    }

    public final void sendNegativeIonCommand(String deviceId, String productId, int anion) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        final PropertyParam propertyParam = new PropertyParam();
        propertyParam.device_id = deviceId;
        propertyParam.setParams(new PropertyParam.Params());
        PropertyParam.Params params = propertyParam.getParams();
        if (params != null) {
            params.setNegativeIon(Integer.valueOf(anion));
        }
        execute(new Runnable() { // from class: com.deye.HumidifierCommandManager$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                HumidifierCommandManager.sendNegativeIonCommand$lambda$13(propertyParam);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendNegativeIonCommand$lambda$13(PropertyParam param) throws JSONException {
        Intrinsics.checkNotNullParameter(param, "$param");
        INSTANCE.sendCommand(param);
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
                if (params.getHkallowstatus() != null) {
                    jSONObject2.put(StubApp.getString2("13073"), params.getHkallowstatus());
                }
                if (params.getAtmosphereLights() != null) {
                    jSONObject2.put(StubApp.getString2("13074"), params.getAtmosphereLights());
                }
                if (params.getWetcurtaincleaningpromptreset() != null) {
                    jSONObject2.put(StubApp.getString2("13075"), params.getWetcurtaincleaningpromptreset());
                }
                if (params.getFilterreplacementpromptreset() != null) {
                    jSONObject2.put(StubApp.getString2("13076"), params.getFilterreplacementpromptreset());
                }
                if (params.getReleadereplacementpromptreset() != null) {
                    jSONObject2.put(StubApp.getString2("13077"), params.getReleadereplacementpromptreset());
                }
                if (params.getFiltermentpromptreset() != null) {
                    jSONObject2.put(StubApp.getString2("13078"), params.getFiltermentpromptreset());
                }
            }
            jSONObject.put(StubApp.getString2("13070"), jSONObject2);
            DeYeFogMqttManager.getInstance().sendSingleMsg(jSONObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
