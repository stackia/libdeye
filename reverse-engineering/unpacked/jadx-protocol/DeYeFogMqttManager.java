package io.fogcloud.sdk.fog.api.fogmqtt;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.BaseApp;
import io.fogcloud.sdk.fog.api.base_callback.DeYeBaseCallback;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.api.mqtt.IRegisterDeviceTopic;
import io.fogcloud.sdk.fog.api.mqtt.IStartDeviceStateListener;
import io.fogcloud.sdk.fog.api.mqtt.MqttConfig;
import io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttServiceAPI;
import io.fogcloud.sdk.fog.api.mqtt.event.DeviceStatusEvent;
import io.fogcloud.sdk.fog.api.mqtt.event.MqttAuthenticationFailedEvent;
import io.fogcloud.sdk.fog.api.mqtt.event.MqttConnectedEvent;
import io.fogcloud.sdk.fog.api.mqtt.event.MqttConnectionLostEvent;
import io.fogcloud.sdk.fog.api.mqtt.event.MqttEventBus;
import io.fogcloud.sdk.fog.api.mqtt.event.MqttSubscriptionManager;
import io.fogcloud.sdk.fog.api.mqtt.sdk.helper.MQTTErrCode;
import io.fogcloud.sdk.fog.api.mqtt.sdk.service.MqttServiceListener;
import io.fogcloud.sdk.fog.callback.ControlDeviceCallBack;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import io.fogcloud.sdk.fog.helper.ListenDevParFog;
import io.fogcloud.sdk.fog.helper.MiCOConstParam;
import io.fogcloud.sdk.fog.helper.MqttDeviceIdUtil;
import io.fogcloud.sdk.fog.log.LogDebug;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class DeYeFogMqttManager extends DeYeBaseCallback {
    private static final int INITIAL_RETRY_DELAY_MS = 1000;
    private static final int MAX_RETRY_COUNT = 3;
    private static final String TAG = StubApp.getString2(44686);
    private static volatile DeYeFogMqttManager sDeYeMqttManager;
    private IStartDeviceStateListener lastStartDeviceStateListener;
    private UnifiedMqttServiceAPI mMqttServiceAPI;
    private final MqttSubscriptionManager eventSubscriptions = new MqttSubscriptionManager();
    protected ListenDevParFog mMqttParams = new ListenDevParFog();
    private ArrayList<String> mTopicList = new ArrayList<>();
    public JSONObject mqttInfo = null;
    private boolean isConnected = false;
    private Set<String> currentTopics = new HashSet();
    private final Object connectionLock = new Object();
    private boolean isConnecting = false;
    private int currentRetryCount = 0;
    private Handler retryHandler = new Handler(Looper.getMainLooper());
    private ControlDeviceCallBack mControlDeviceCallBack = new ControlDeviceCallBack() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager.2
        @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
        public void onFailure(int i, String str) {
            LogDebug.INSTANCE.log(StubApp.getString2(44682) + i + StubApp.getString2(13233) + str);
            super.onFailure(i, str);
            if (this.iStartDeviceStateListener != null) {
                this.iStartDeviceStateListener.onStartDeviceStateFail(i, str);
            }
        }

        @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
        public void onSuccess(String str) {
            LogDebug.INSTANCE.log(StubApp.getString2(44683) + str);
            super.onSuccess(str);
        }

        @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
        public void onDeviceStatusReceived(int i, String str) {
            LogDebug.INSTANCE.log(StubApp.getString2(44677) + i + StubApp.getString2(13233) + str);
            if (i == MQTTErrCode._CON_CODE) {
                LogDebug.INSTANCE.log(StubApp.getString2(44678));
                this.iRegisterDeviceTopic = new IRegisterDeviceTopic() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager.2.1
                    @Override // io.fogcloud.sdk.fog.api.mqtt.IRegisterDeviceTopic
                    public void onRegisterDeviceTopic(String str2, String str3) {
                        LogDebug.INSTANCE.log(StubApp.getString2(44672) + str2 + StubApp.getString2(44673) + str3);
                        LogDebug.INSTANCE.log(StubApp.getString2(44674) + DeYeFogMqttManager.this.mTopicList.size());
                        for (int i2 = 0; i2 < DeYeFogMqttManager.this.mTopicList.size(); i2++) {
                            String str4 = (String) DeYeFogMqttManager.this.mTopicList.get(i2);
                            LogDebug.INSTANCE.log(StubApp.getString2(44675) + str4);
                            DeYeFogMqttManager.getInstance().addDeviceListener(str4, 1, DeYeFogMqttManager.this.mControlDeviceCallBack);
                        }
                        LogDebug.INSTANCE.log(StubApp.getString2(44676));
                    }
                };
                LogDebug.INSTANCE.log(StubApp.getString2(44680).concat(this.iStartDeviceStateListener != null ? StubApp.getString2(13160) : StubApp.getString2(44679)));
                if (this.iStartDeviceStateListener != null) {
                    this.iStartDeviceStateListener.onStartDeviceStateSuccess(i, str, 2, this.iRegisterDeviceTopic);
                    LogDebug.INSTANCE.log(StubApp.getString2(44681));
                }
            }
            if (this.iStartDeviceStateListener != null) {
                this.iStartDeviceStateListener.onDeviceStatusReceived(i, str, 2);
            }
        }
    };

    private DeYeFogMqttManager() {
    }

    public static DeYeFogMqttManager getInstance() {
        if (sDeYeMqttManager == null) {
            synchronized (DeYeFogMqttManager.class) {
                if (sDeYeMqttManager == null) {
                    sDeYeMqttManager = new DeYeFogMqttManager();
                }
            }
        }
        return sDeYeMqttManager;
    }

    public boolean isConnected() {
        String string2 = StubApp.getString2(44695);
        UnifiedMqttServiceAPI unifiedMqttServiceAPI = this.mMqttServiceAPI;
        if (unifiedMqttServiceAPI == null) {
            return false;
        }
        try {
            boolean zIsConnected = unifiedMqttServiceAPI.isConnected();
            if (this.isConnected != zIsConnected) {
                LogDebug.INSTANCE.log(string2 + this.isConnected + StubApp.getString2("44696") + zIsConnected);
                this.isConnected = zIsConnected;
            }
            return zIsConnected;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.connectionLock) {
            z = this.isConnecting;
        }
        return z;
    }

    public void reconnectIfNeeded() {
        if (!isConnected()) {
            synchronized (this.connectionLock) {
                if (this.isConnecting) {
                    LogDebug.INSTANCE.log(StubApp.getString2("44697"));
                    return;
                }
                this.isConnecting = true;
                LogDebug.INSTANCE.log(StubApp.getString2(44698));
                IStartDeviceStateListener iStartDeviceStateListener = this.lastStartDeviceStateListener;
                if (iStartDeviceStateListener != null) {
                    this.currentRetryCount = 0;
                    requestFogMqttInfoWithRetry(iStartDeviceStateListener);
                    return;
                } else {
                    LogDebug.INSTANCE.log(StubApp.getString2(44699));
                    synchronized (this.connectionLock) {
                        this.isConnecting = false;
                    }
                    return;
                }
            }
        }
        LogDebug.INSTANCE.log(StubApp.getString2(44700));
    }

    public void startListenDevice(IStartDeviceStateListener iStartDeviceStateListener) {
        this.lastStartDeviceStateListener = iStartDeviceStateListener;
        synchronized (this.connectionLock) {
            if (this.isConnecting) {
                LogDebug.INSTANCE.log(StubApp.getString2("44702"));
                return;
            }
            this.isConnecting = true;
            this.currentRetryCount = 0;
            requestFogMqttInfoWithRetry(iStartDeviceStateListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupEventSubscriptions(final IStartDeviceStateListener iStartDeviceStateListener) {
        this.eventSubscriptions.dispose();
        LogDebug.INSTANCE.log(StubApp.getString2(44692));
        this.eventSubscriptions.add(MqttEventBus.getInstance().toObservable(MqttConnectedEvent.class).filter(new Predicate() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return ((MqttConnectedEvent) obj).isFogPlatForm();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                LogDebug.INSTANCE.log(StubApp.getString2(44687) + ((MqttConnectedEvent) obj).getPlatform());
            }
        }));
        this.eventSubscriptions.add(MqttEventBus.getInstance().toObservable(MqttAuthenticationFailedEvent.class).filter(new Predicate() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return ((MqttAuthenticationFailedEvent) obj).isFogPlatForm();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$setupEventSubscriptions$1(iStartDeviceStateListener, (MqttAuthenticationFailedEvent) obj);
            }
        }, new Consumer() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                DeYeFogMqttManager.lambda$setupEventSubscriptions$2((Throwable) obj);
            }
        }));
        this.eventSubscriptions.add(MqttEventBus.getInstance().toObservable(DeviceStatusEvent.class).filter(new Predicate() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return ((DeviceStatusEvent) obj).isFogPlatForm();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                DeYeFogMqttManager.lambda$setupEventSubscriptions$3(iStartDeviceStateListener, (DeviceStatusEvent) obj);
            }
        }));
        this.eventSubscriptions.add(MqttEventBus.getInstance().toObservable(MqttConnectionLostEvent.class).filter(new Predicate() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager$$ExternalSyntheticLambda7
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return ((MqttConnectionLostEvent) obj).isFogPlatForm();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager$$ExternalSyntheticLambda8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$setupEventSubscriptions$4((MqttConnectionLostEvent) obj);
            }
        }));
        LogDebug.INSTANCE.log(StubApp.getString2(44693));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupEventSubscriptions$1(IStartDeviceStateListener iStartDeviceStateListener, MqttAuthenticationFailedEvent mqttAuthenticationFailedEvent) throws Throwable {
        LogDebug.INSTANCE.log(StubApp.getString2(44688) + mqttAuthenticationFailedEvent.getReasonCode() + StubApp.getString2(44689));
        synchronized (this.connectionLock) {
            this.currentRetryCount = 0;
        }
        requestFogMqttInfoWithRetry(iStartDeviceStateListener);
    }

    static /* synthetic */ void lambda$setupEventSubscriptions$2(Throwable th) throws Throwable {
        LogDebug.INSTANCE.log(StubApp.getString2(44690) + th.getMessage());
        th.printStackTrace();
    }

    static /* synthetic */ void lambda$setupEventSubscriptions$3(IStartDeviceStateListener iStartDeviceStateListener, DeviceStatusEvent deviceStatusEvent) throws Throwable {
        if (iStartDeviceStateListener != null) {
            iStartDeviceStateListener.onDeviceStatusReceived(MQTTErrCode._PAYLOAD_CODE, deviceStatusEvent.getPayload(), 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupEventSubscriptions$4(MqttConnectionLostEvent mqttConnectionLostEvent) throws Throwable {
        LogDebug.INSTANCE.log(StubApp.getString2(44691) + mqttConnectionLostEvent.getCauseMessage());
        this.isConnected = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestFogMqttInfoWithRetry(final IStartDeviceStateListener iStartDeviceStateListener) {
        DeYeHttpRequestManager.getInstance().getFogmqttinfo(new FogCallBack() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager.1
            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onSuccess(String str) {
                try {
                    try {
                        LogDebug.INSTANCE.log(StubApp.getString2(44660) + str);
                        JSONObject jSONObject = new JSONObject(str).getJSONObject(StubApp.getString2("100"));
                        DeYeFogMqttManager.this.mqttInfo = jSONObject;
                        String strOptString = jSONObject.optString(StubApp.getString2("33931"));
                        String strOptString2 = jSONObject.optString(StubApp.getString2("44661"));
                        jSONObject.optString(StubApp.getString2("44662"));
                        String strOptString3 = jSONObject.optString(StubApp.getString2("18548"));
                        String strOptString4 = jSONObject.optString(StubApp.getString2("13687"));
                        String strOptString5 = jSONObject.optString(StubApp.getString2("44663"));
                        JSONArray jSONArray = jSONObject.getJSONObject(StubApp.getString2("13184")).getJSONArray(StubApp.getString2("1727"));
                        HashSet<String> hashSet = new HashSet();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            hashSet.add(String.valueOf(jSONArray.get(i)));
                        }
                        boolean z = !DeYeFogMqttManager.this.currentTopics.equals(hashSet);
                        if (z || !DeYeFogMqttManager.this.isConnected) {
                            LogDebug.INSTANCE.log(StubApp.getString2("44666") + z + StubApp.getString2("44667") + DeYeFogMqttManager.this.isConnected);
                            LogDebug.INSTANCE.log(StubApp.getString2("44668"));
                            DeYeFogMqttManager.this.stopFogMqtt();
                            LogDebug.INSTANCE.log(StubApp.getString2("44669"));
                            DeYeFogMqttManager.this.setupEventSubscriptions(iStartDeviceStateListener);
                            DeYeFogMqttManager.this.mTopicList.clear();
                            for (String str2 : hashSet) {
                                if (!DeYeFogMqttManager.this.mTopicList.contains(str2)) {
                                    DeYeFogMqttManager.this.mTopicList.add(str2);
                                }
                            }
                            DeYeFogMqttManager.this.currentTopics = hashSet;
                            DeYeFogMqttManager.this.mMqttParams.userName = strOptString;
                            DeYeFogMqttManager.this.mMqttParams.passWord = strOptString4;
                            DeYeFogMqttManager.this.mMqttParams.host = strOptString5;
                            DeYeFogMqttManager.this.mMqttParams.port = strOptString2;
                            DeYeFogMqttManager.this.mMqttParams.clientID = MqttDeviceIdUtil.buildClientId(BaseApp.getInstance(), strOptString3);
                            DeYeFogMqttManager.this.mMqttParams.isencrypt = true;
                            DeYeFogMqttManager.this.mControlDeviceCallBack.setStartDeviceStateListener(iStartDeviceStateListener);
                            DeYeFogMqttManager deYeFogMqttManager = DeYeFogMqttManager.this;
                            deYeFogMqttManager.startListenDevice(deYeFogMqttManager.mMqttParams, DeYeFogMqttManager.this.mControlDeviceCallBack);
                            DeYeFogMqttManager.this.isConnected = true;
                            LogDebug.INSTANCE.log(StubApp.getString2("44670"));
                            DeYeFogMqttManager.this.currentRetryCount = 0;
                        } else {
                            LogDebug.INSTANCE.log(StubApp.getString2("44664"));
                            IStartDeviceStateListener iStartDeviceStateListener2 = iStartDeviceStateListener;
                            if (iStartDeviceStateListener2 != null) {
                                iStartDeviceStateListener2.onStartDeviceStateSuccess(MQTTErrCode._CON_CODE, StubApp.getString2("44665"), 2, new IRegisterDeviceTopic() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager.1.1
                                    @Override // io.fogcloud.sdk.fog.api.mqtt.IRegisterDeviceTopic
                                    public void onRegisterDeviceTopic(String str3, String str4) {
                                        LogDebug.INSTANCE.log(StubApp.getString2(44652) + str4);
                                    }
                                });
                            }
                        }
                        synchronized (DeYeFogMqttManager.this.connectionLock) {
                            DeYeFogMqttManager.this.isConnecting = false;
                            LogDebug.INSTANCE.log(StubApp.getString2("44671"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        DeYeFogMqttManager.this.isConnected = false;
                        synchronized (DeYeFogMqttManager.this.connectionLock) {
                            DeYeFogMqttManager.this.isConnecting = false;
                            LogDebug.INSTANCE.log(StubApp.getString2("44671"));
                        }
                    }
                } catch (Throwable th) {
                    synchronized (DeYeFogMqttManager.this.connectionLock) {
                        DeYeFogMqttManager.this.isConnecting = false;
                        LogDebug.INSTANCE.log(StubApp.getString2("44671"));
                        throw th;
                    }
                }
            }

            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onFailure(int i, String str) {
                Log.d(StubApp.getString2(4146), StubApp.getString2(44653) + str);
                DeYeFogMqttManager.this.isConnected = false;
                DeYeFogMqttManager.this.currentRetryCount++;
                if (DeYeFogMqttManager.this.currentRetryCount < 3) {
                    int i2 = (1 << (DeYeFogMqttManager.this.currentRetryCount - 1)) * 1000;
                    LogDebug.INSTANCE.log(StubApp.getString2(44654) + i2 + StubApp.getString2(44655) + DeYeFogMqttManager.this.currentRetryCount + StubApp.getString2(44656));
                    DeYeFogMqttManager.this.retryHandler.postDelayed(new Runnable() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            DeYeFogMqttManager.this.requestFogMqttInfoWithRetry(iStartDeviceStateListener);
                        }
                    }, i2);
                    return;
                }
                LogDebug.INSTANCE.log(StubApp.getString2(44657));
                synchronized (DeYeFogMqttManager.this.connectionLock) {
                    DeYeFogMqttManager.this.isConnecting = false;
                    DeYeFogMqttManager.this.currentRetryCount = 0;
                    LogDebug.INSTANCE.log(StubApp.getString2("44658"));
                }
                IStartDeviceStateListener iStartDeviceStateListener2 = iStartDeviceStateListener;
                if (iStartDeviceStateListener2 != null) {
                    iStartDeviceStateListener2.onDeviceStatusReceived(-1, StubApp.getString2(44659) + str, 2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startListenDevice(ListenDevParFog listenDevParFog, final ControlDeviceCallBack controlDeviceCallBack) {
        if (this.mMqttServiceAPI == null) {
            this.mMqttServiceAPI = new UnifiedMqttServiceAPI(MqttConfig.forFog());
        }
        String str = listenDevParFog.host;
        String str2 = listenDevParFog.userName;
        String str3 = listenDevParFog.passWord;
        String str4 = listenDevParFog.clientID;
        int i = listenDevParFog.mqtttype;
        boolean z = listenDevParFog.isencrypt;
        if (!checkPara(str, str2, str3, str4)) {
            failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, controlDeviceCallBack);
            return;
        }
        if (i == 0) {
            String str5 = listenDevParFog.port;
            if (BaseApp.getInstance() != null) {
                this.mMqttServiceAPI.startMqttService(BaseApp.getInstance(), str, str5, str2, str3, str4, null, z, new MqttServiceListener() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager.3
                    @Override // io.fogcloud.sdk.fog.api.mqtt.sdk.service.MqttServiceListener
                    public void onMqttReceiver(int i2, String str6) {
                        LogDebug.INSTANCE.log(StubApp.getString2(44684) + i2 + StubApp.getString2(13233) + str6);
                        DeYeFogMqttManager.this.onDevStatusReceived(i2, str6, controlDeviceCallBack);
                    }
                });
                LogDebug.INSTANCE.log(StubApp.getString2(44694));
                successCBCtrlDev(MiCOConstParam.SUCCESS, controlDeviceCallBack);
                return;
            }
            failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, controlDeviceCallBack);
        }
    }

    public synchronized void sendSingleMsg(JSONObject jSONObject) {
        String string2 = StubApp.getString2(44701);
        synchronized (this) {
            LogDebug.INSTANCE.log(string2 + jSONObject.toString());
            DeYeHttpRequestManager.getInstance().setFogProperties(jSONObject, new FogCallBack() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager.4
                @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                public void onFailure(int i, String str) {
                }

                @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                public void onSuccess(String str) {
                    LogDebug.INSTANCE.log(StubApp.getString2(44685) + str);
                }
            });
        }
    }

    public void addDeviceListener(String str, int i, ControlDeviceCallBack controlDeviceCallBack) {
        if (!checkPara(str)) {
            failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, controlDeviceCallBack);
        } else if (i < 0 || i > 2) {
            failureCBCtrlDev(MiCOConstParam.QOSERRCODE, MiCOConstParam.QOSERR, controlDeviceCallBack);
        } else {
            this.mMqttServiceAPI.subscribe(str, i);
            successCBCtrlDev(MiCOConstParam.SUCCESS, controlDeviceCallBack);
        }
    }

    public void removeDeviceListener(String str, ControlDeviceCallBack controlDeviceCallBack) {
        if (checkPara(str)) {
            this.mMqttServiceAPI.unsubscribe(str);
            successCBCtrlDev(MiCOConstParam.SUCCESS, controlDeviceCallBack);
        } else {
            failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, controlDeviceCallBack);
        }
    }

    public void stopListenDevice(ControlDeviceCallBack controlDeviceCallBack) {
        UnifiedMqttServiceAPI unifiedMqttServiceAPI;
        if (BaseApp.getInstance() != null && (unifiedMqttServiceAPI = this.mMqttServiceAPI) != null) {
            unifiedMqttServiceAPI.stopMqttService(BaseApp.getInstance());
            successCBCtrlDev(MiCOConstParam.SUCCESS, controlDeviceCallBack);
        } else {
            failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.CONTEXTISNULL, controlDeviceCallBack);
        }
    }

    public void stopFogMqtt() {
        this.eventSubscriptions.dispose();
        LogDebug.INSTANCE.log(StubApp.getString2(44703));
        if (this.mMqttServiceAPI != null) {
            for (int i = 0; i < this.mTopicList.size(); i++) {
                LogDebug.INSTANCE.log(StubApp.getString2(44704) + this.mTopicList.get(i) + StubApp.getString2(44705) + this.mTopicList.size() + StubApp.getString2(44706) + i);
                removeDeviceListener(this.mTopicList.get(i), null);
            }
            stopListenDevice(null);
            this.mMqttServiceAPI = null;
            LogDebug.INSTANCE.log(StubApp.getString2(44707));
        }
        this.isConnected = false;
        this.currentTopics.clear();
        LogDebug.INSTANCE.log(StubApp.getString2(44708));
    }
}
