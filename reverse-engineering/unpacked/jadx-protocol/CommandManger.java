package com.deye;

import android.os.Handler;
import android.os.Looper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.bean.LoopFanBean;
import io.fogcloud.sdk.fog.callback.ControlDeviceCallBack;
import io.fogcloud.sdk.fog.helper.CommandPara;
import io.fogcloud.sdk.fog.log.LogDebug;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;

/* compiled from: CommandManger.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0012\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0006\u0010\u0012\u001a\u00020\fJ\"\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J6\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0018J6\u0010\u001f\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0018J \u0010!\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\"\u001a\u0004\u0018\u00010#J6\u0010$\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0018J6\u0010&\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010'\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0018J@\u0010(\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010)\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020\u0010J>\u0010+\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0018J\u001e\u0010.\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010/\u001a\u000200J@\u00101\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u00102\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020\u0010J6\u00103\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u00104\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0018J6\u00105\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0018J6\u00107\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0018J6\u00109\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010:\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0018R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006;"}, d2 = {"Lcom/deye/CommandManger;", "", "()V", "handle", "Landroid/os/Handler;", "lastSendTime", "", "getLastSendTime", "()J", "setLastSendTime", "(J)V", "execute", "", "runnable", "Ljava/lang/Runnable;", "isFilterCommand", "", "executeNow", "init", "sendCommand", "deviceId", "", "productId", "dehumidifierBeanTemp", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "sendHumidityCommand", "humidity", "", "isFog", "isCombo", "bean", "sendLockCommand", "lock", "sendLoopCommand", "mLoopFanBeanTemp", "Lio/fogcloud/sdk/fog/bean/LoopFanBean;", "sendModeCommand", "mode", "sendNegativeIonCommand", "anion", "sendPowerCommand", "power", "isHomePage", "sendPoweroffTimeCommand", "hour", "minute", "sendSingleCommand", "data", "", "sendSleepCommand", "sleep", "sendSpeedCommand", "speed", "sendTempCommand", "temp", "sendWaterPumpCommand", "waterPump", "sendWindSwitchCommand", "swingingWind", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class CommandManger {
    public static final CommandManger INSTANCE = new CommandManger();
    private static Handler handle;
    private static long lastSendTime;

    private CommandManger() {
    }

    public final long getLastSendTime() {
        return lastSendTime;
    }

    public final void setLastSendTime(long j) {
        lastSendTime = j;
    }

    public final void init() {
        Thread thread = new Thread(new Runnable() { // from class: com.deye.CommandManger$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                CommandManger.init$lambda$0();
            }
        });
        thread.setPriority(10);
        thread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0() {
        Looper.prepare();
        Looper looperMyLooper = Looper.myLooper();
        Intrinsics.checkNotNull(looperMyLooper);
        handle = new Handler(looperMyLooper);
        Looper.loop();
    }

    static /* synthetic */ void execute$default(CommandManger commandManger, Runnable runnable, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        commandManger.execute(runnable, z);
    }

    private final void execute(final Runnable runnable, boolean isFilterCommand) {
        if (handle == null) {
            LogDebug.INSTANCE.log(StubApp.getString2(12997));
        }
        Handler handler = handle;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        if (System.currentTimeMillis() - lastSendTime > 2000) {
            Handler handler2 = handle;
            if (handler2 != null) {
                handler2.post(new Runnable() { // from class: com.deye.CommandManger$$ExternalSyntheticLambda12
                    @Override // java.lang.Runnable
                    public final void run() {
                        CommandManger.execute$lambda$1(runnable);
                    }
                });
                return;
            }
            return;
        }
        Handler handler3 = handle;
        if (handler3 != null) {
            handler3.postDelayed(new Runnable() { // from class: com.deye.CommandManger$$ExternalSyntheticLambda13
                @Override // java.lang.Runnable
                public final void run() {
                    CommandManger.execute$lambda$2(runnable);
                }
            }, isFilterCommand ? 1000L : 30L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$1(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "$runnable");
        lastSendTime = System.currentTimeMillis();
        runnable.run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$2(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "$runnable");
        lastSendTime = System.currentTimeMillis();
        runnable.run();
    }

    private final void executeNow(final Runnable runnable) {
        if (handle == null) {
            LogDebug.INSTANCE.log(StubApp.getString2(12997));
        }
        Handler handler = handle;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = handle;
        if (handler2 != null) {
            handler2.post(new Runnable() { // from class: com.deye.CommandManger$$ExternalSyntheticLambda15
                @Override // java.lang.Runnable
                public final void run() {
                    CommandManger.executeNow$lambda$3(runnable);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void executeNow$lambda$3(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "$runnable");
        runnable.run();
    }

    public final void sendPowerCommand(final String deviceId, final String productId, final int power, final boolean isFog, final boolean isCombo, final DehumidifierBean bean, boolean isHomePage) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        if (isFog) {
            bean.setSys_switch(String.valueOf(power));
            DeviceCacheManager.INSTANCE.setDeviceCache(deviceId, productId, bean);
        }
        execute(new Runnable() { // from class: com.deye.CommandManger$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                CommandManger.sendPowerCommand$lambda$4(isFog, isCombo, power, deviceId, productId, bean);
            }
        }, isHomePage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendPowerCommand$lambda$4(boolean z, boolean z2, int i, String str, String str2, DehumidifierBean dehumidifierBean) throws JSONException {
        Intrinsics.checkNotNullParameter(str, "$deviceId");
        Intrinsics.checkNotNullParameter(str2, "$productId");
        Intrinsics.checkNotNullParameter(dehumidifierBean, "$bean");
        if (z) {
            FogDeviceManager.INSTANCE.sendPowerCommand(str, i, dehumidifierBean);
        } else if (z2) {
            INSTANCE.sendSingleCommand(str, str2, new byte[]{17, 1, i == 1 ? (byte) 1 : (byte) 0});
        } else {
            INSTANCE.sendCommand(str, str2, dehumidifierBean);
        }
    }

    public final void sendWindSwitchCommand(final String deviceId, final String productId, final int swingingWind, final boolean isFog, final boolean isCombo, final DehumidifierBean bean) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        execute(new Runnable() { // from class: com.deye.CommandManger$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                CommandManger.sendWindSwitchCommand$lambda$5(isFog, isCombo, swingingWind, deviceId, productId, bean);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendWindSwitchCommand$lambda$5(boolean z, boolean z2, int i, String str, String str2, DehumidifierBean dehumidifierBean) throws JSONException {
        Intrinsics.checkNotNullParameter(str, "$deviceId");
        Intrinsics.checkNotNullParameter(str2, "$productId");
        Intrinsics.checkNotNullParameter(dehumidifierBean, "$bean");
        if (z) {
            FogDeviceManager.INSTANCE.sendWindSwitchCommand(str, i, dehumidifierBean);
        } else if (z2) {
            INSTANCE.sendSingleCommand(str, str2, new byte[]{17, 2, i == 1 ? (byte) 1 : (byte) 0});
        } else {
            INSTANCE.sendCommand(str, str2, dehumidifierBean);
        }
    }

    public final void sendSpeedCommand(final String deviceId, final String productId, final int speed, final boolean isFog, final boolean isCombo, final DehumidifierBean bean) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        execute$default(this, new Runnable() { // from class: com.deye.CommandManger$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                CommandManger.sendSpeedCommand$lambda$6(isFog, isCombo, speed, deviceId, productId, bean);
            }
        }, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendSpeedCommand$lambda$6(boolean z, boolean z2, int i, String str, String str2, DehumidifierBean dehumidifierBean) throws JSONException {
        Intrinsics.checkNotNullParameter(str, "$deviceId");
        Intrinsics.checkNotNullParameter(str2, "$productId");
        Intrinsics.checkNotNullParameter(dehumidifierBean, "$bean");
        if (z) {
            FogDeviceManager.INSTANCE.sendSpeedCommand(str, i, dehumidifierBean);
        } else if (z2) {
            INSTANCE.sendSingleCommand(str, str2, new byte[]{17, 9, (byte) i});
        } else {
            INSTANCE.sendCommand(str, str2, dehumidifierBean);
        }
    }

    public final void sendWaterPumpCommand(final String deviceId, final String productId, final int waterPump, final boolean isFog, final boolean isCombo, final DehumidifierBean bean) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        execute(new Runnable() { // from class: com.deye.CommandManger$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                CommandManger.sendWaterPumpCommand$lambda$7(isFog, isCombo, waterPump, deviceId, productId, bean);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendWaterPumpCommand$lambda$7(boolean z, boolean z2, int i, String str, String str2, DehumidifierBean dehumidifierBean) throws JSONException {
        Intrinsics.checkNotNullParameter(str, "$deviceId");
        Intrinsics.checkNotNullParameter(str2, "$productId");
        Intrinsics.checkNotNullParameter(dehumidifierBean, "$bean");
        if (z) {
            FogDeviceManager.INSTANCE.sendWaterPumpCommand(str, i, dehumidifierBean);
        } else if (z2) {
            INSTANCE.sendSingleCommand(str, str2, new byte[]{17, 6, i == 1 ? (byte) 1 : (byte) 0});
        } else {
            INSTANCE.sendCommand(str, str2, dehumidifierBean);
        }
    }

    public final void sendNegativeIonCommand(final String deviceId, final String productId, final int anion, final boolean isFog, final boolean isCombo, final DehumidifierBean bean) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        execute(new Runnable() { // from class: com.deye.CommandManger$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                CommandManger.sendNegativeIonCommand$lambda$8(isFog, isCombo, anion, deviceId, productId, bean);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendNegativeIonCommand$lambda$8(boolean z, boolean z2, int i, String str, String str2, DehumidifierBean dehumidifierBean) throws JSONException {
        Intrinsics.checkNotNullParameter(str, "$deviceId");
        Intrinsics.checkNotNullParameter(str2, "$productId");
        Intrinsics.checkNotNullParameter(dehumidifierBean, "$bean");
        if (z) {
            FogDeviceManager.INSTANCE.sendNegativeIonCommand(str, i, dehumidifierBean);
        } else if (z2) {
            INSTANCE.sendSingleCommand(str, str2, new byte[]{17, 7, i == 1 ? (byte) 1 : (byte) 0});
        } else {
            INSTANCE.sendCommand(str, str2, dehumidifierBean);
        }
    }

    public final void sendLockCommand(final String deviceId, final String productId, final int lock, final boolean isFog, final boolean isCombo, final DehumidifierBean bean) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        execute(new Runnable() { // from class: com.deye.CommandManger$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                CommandManger.sendLockCommand$lambda$9(isFog, isCombo, lock, deviceId, productId, bean);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendLockCommand$lambda$9(boolean z, boolean z2, int i, String str, String str2, DehumidifierBean dehumidifierBean) throws JSONException {
        Intrinsics.checkNotNullParameter(str, "$deviceId");
        Intrinsics.checkNotNullParameter(str2, "$productId");
        Intrinsics.checkNotNullParameter(dehumidifierBean, "$bean");
        if (z) {
            FogDeviceManager.INSTANCE.sendLockCommand(str, i, dehumidifierBean);
        } else if (z2) {
            INSTANCE.sendSingleCommand(str, str2, new byte[]{17, 3, i == 1 ? (byte) 1 : (byte) 0});
        } else {
            INSTANCE.sendCommand(str, str2, dehumidifierBean);
        }
    }

    public final void sendLoopCommand(final String deviceId, final String productId, final LoopFanBean mLoopFanBeanTemp) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        execute(new Runnable() { // from class: com.deye.CommandManger$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                CommandManger.sendLoopCommand$lambda$10(mLoopFanBeanTemp, deviceId, productId);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendLoopCommand$lambda$10(LoopFanBean loopFanBean, String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "$deviceId");
        Intrinsics.checkNotNullParameter(str2, "$productId");
        JSONObject object = JSON.parseObject(JSON.toJSONString(loopFanBean));
        CommandPara commandPara = new CommandPara();
        commandPara.command = object.toString();
        commandPara.deviceid = str;
        commandPara.endpoint = DeYeMqttManager.getInstance().getEndpoint();
        commandPara.productid = str2;
        try {
            DeYeMqttManager.getInstance().sendMsg(StubApp.getString2("12999"), commandPara, new ControlDeviceCallBack() { // from class: com.deye.CommandManger$sendLoopCommand$1$1
                public void onSuccess(String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void sendModeCommand(final String deviceId, final String productId, final String mode, final boolean isFog, final boolean isCombo, final DehumidifierBean bean) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(bean, "bean");
        if (isFog) {
            bean.setMode(mode);
            DeviceCacheManager.INSTANCE.setDeviceCache(deviceId, productId, bean);
        }
        execute$default(this, new Runnable() { // from class: com.deye.CommandManger$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                CommandManger.sendModeCommand$lambda$11(isFog, isCombo, mode, deviceId, productId, bean);
            }
        }, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendModeCommand$lambda$11(boolean z, boolean z2, String str, String str2, String str3, DehumidifierBean dehumidifierBean) throws JSONException {
        Intrinsics.checkNotNullParameter(str, "$mode");
        Intrinsics.checkNotNullParameter(str2, "$deviceId");
        Intrinsics.checkNotNullParameter(str3, "$productId");
        Intrinsics.checkNotNullParameter(dehumidifierBean, "$bean");
        if (z) {
            FogDeviceManager.INSTANCE.sendModeCommand(str2, Integer.parseInt(str), dehumidifierBean);
        } else if (z2) {
            INSTANCE.sendSingleCommand(str2, str3, new byte[]{17, 8, (byte) Integer.parseInt(str)});
        } else {
            INSTANCE.sendCommand(str2, str3, dehumidifierBean);
        }
    }

    public final void sendHumidityCommand(final String deviceId, final String productId, final int humidity, final boolean isFog, final boolean isCombo, final DehumidifierBean bean) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        execute$default(this, new Runnable() { // from class: com.deye.CommandManger$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                CommandManger.sendHumidityCommand$lambda$12(isFog, isCombo, humidity, deviceId, productId, bean);
            }
        }, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendHumidityCommand$lambda$12(boolean z, boolean z2, int i, String str, String str2, DehumidifierBean dehumidifierBean) throws JSONException {
        Intrinsics.checkNotNullParameter(str, "$deviceId");
        Intrinsics.checkNotNullParameter(str2, "$productId");
        Intrinsics.checkNotNullParameter(dehumidifierBean, "$bean");
        if (z) {
            FogDeviceManager.INSTANCE.sendHumidityCommand(str, i, dehumidifierBean);
        } else if (z2) {
            INSTANCE.sendSingleCommand(str, str2, new byte[]{17, 10, (byte) i});
        } else {
            INSTANCE.sendCommand(str, str2, dehumidifierBean);
        }
    }

    public final void sendTempCommand(final String deviceId, final String productId, final int temp, final boolean isFog, final boolean isCombo, final DehumidifierBean bean) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        execute$default(this, new Runnable() { // from class: com.deye.CommandManger$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                CommandManger.sendTempCommand$lambda$13(isFog, isCombo, temp, deviceId, productId, bean);
            }
        }, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendTempCommand$lambda$13(boolean z, boolean z2, int i, String str, String str2, DehumidifierBean dehumidifierBean) throws JSONException {
        Intrinsics.checkNotNullParameter(str, "$deviceId");
        Intrinsics.checkNotNullParameter(str2, "$productId");
        Intrinsics.checkNotNullParameter(dehumidifierBean, "$bean");
        if (z) {
            FogDeviceManager.INSTANCE.sendTempCommand(str, i, dehumidifierBean);
        } else if (z2) {
            INSTANCE.sendSingleCommand(str, str2, new byte[]{17, 10, (byte) i});
        } else {
            INSTANCE.sendCommand(str, str2, dehumidifierBean);
        }
    }

    public final void sendSleepCommand(final String deviceId, final String productId, final int sleep, final boolean isFog, final boolean isCombo, final DehumidifierBean bean, boolean isHomePage) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        execute(new Runnable() { // from class: com.deye.CommandManger$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                CommandManger.sendSleepCommand$lambda$14(isFog, isCombo, sleep, deviceId, productId, bean);
            }
        }, isHomePage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendSleepCommand$lambda$14(boolean z, boolean z2, int i, String str, String str2, DehumidifierBean dehumidifierBean) throws JSONException {
        Intrinsics.checkNotNullParameter(str, "$deviceId");
        Intrinsics.checkNotNullParameter(str2, "$productId");
        Intrinsics.checkNotNullParameter(dehumidifierBean, "$bean");
        if (z) {
            FogDeviceManager.INSTANCE.sendSleepCommand(str, i, dehumidifierBean);
        } else if (z2) {
            INSTANCE.sendSingleCommand(str, str2, new byte[]{17, 15, (byte) i});
        } else {
            INSTANCE.sendCommand(str, str2, dehumidifierBean);
        }
    }

    public final void sendPoweroffTimeCommand(final String deviceId, String productId, final int hour, int minute, boolean isFog, boolean isCombo, final DehumidifierBean bean) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        executeNow(new Runnable() { // from class: com.deye.CommandManger$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                CommandManger.sendPoweroffTimeCommand$lambda$15(deviceId, hour, bean);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendPoweroffTimeCommand$lambda$15(String str, int i, DehumidifierBean dehumidifierBean) throws JSONException {
        Intrinsics.checkNotNullParameter(str, "$deviceId");
        Intrinsics.checkNotNullParameter(dehumidifierBean, "$bean");
        FogDeviceManager.INSTANCE.sendPoweroffTimeCommand(str, i, dehumidifierBean);
    }

    private final void sendCommand(String deviceId, String productId, DehumidifierBean dehumidifierBeanTemp) {
        String json = new Gson().toJson(dehumidifierBeanTemp);
        CommandPara commandPara = new CommandPara();
        commandPara.command = json.toString();
        commandPara.deviceid = deviceId;
        commandPara.endpoint = DeYeMqttManager.getInstance().getEndpoint();
        commandPara.productid = productId;
        try {
            DeYeMqttManager.getInstance().sendMsg(StubApp.getString2("12998"), commandPara, new ControlDeviceCallBack() { // from class: com.deye.CommandManger.sendCommand.1
                public void onSuccess(String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void sendSingleCommand(String deviceId, String productId, byte[] data) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(data, "data");
        byte[] bArr = {2, data[0], data[1], data[2]};
        CommandPara commandPara = new CommandPara();
        commandPara.deviceid = deviceId;
        commandPara.endpoint = DeYeMqttManager.getInstance().getEndpoint();
        commandPara.productid = productId;
        try {
            DeYeMqttManager.getInstance().sendSingleMsg(StubApp.getString2("12998"), commandPara, bArr, new ControlDeviceCallBack() { // from class: com.deye.CommandManger.sendSingleCommand.1
                public void onSuccess(String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
