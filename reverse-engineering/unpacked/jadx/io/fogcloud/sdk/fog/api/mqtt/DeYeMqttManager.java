package io.fogcloud.sdk.fog.api.mqtt;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.BaseApp;
import io.fogcloud.sdk.fog.api.base_callback.DeYeBaseCallback;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
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
import io.fogcloud.sdk.fog.helper.AppHexCharHelper;
import io.fogcloud.sdk.fog.helper.CommandPara;
import io.fogcloud.sdk.fog.helper.Configuration;
import io.fogcloud.sdk.fog.helper.ListenDevParFog;
import io.fogcloud.sdk.fog.helper.MiCOConstParam;
import io.fogcloud.sdk.fog.helper.MqttDeviceIdUtil;
import io.fogcloud.sdk.fog.helper.dehumidifier.DehumidifierBeanString2Hex;
import io.fogcloud.sdk.fog.helper.loop_fan.LoopFanBeanString2Hex;
import io.fogcloud.sdk.fog.helper.quilt_dryer.loop_fan.QuiltDryerBeanString2Hex;
import io.fogcloud.sdk.fog.log.LogDebug;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class DeYeMqttManager extends DeYeBaseCallback {
    private static final int INITIAL_RETRY_DELAY_MS = 1000;
    private static final int MAX_RETRY_COUNT = 30;
    private static final String TAG = StubApp.getString2(44799);
    private static volatile DeYeMqttManager sDeYeMqttManager;
    private IStartDeviceStateListener lastStartDeviceStateListener;
    private UnifiedMqttServiceAPI mMqttServiceAPI;
    private final MqttSubscriptionManager eventSubscriptions = new MqttSubscriptionManager();
    protected ListenDevParFog mMqttParams = new ListenDevParFog();
    private String mEndpoint = "";
    private ArrayList<String> mTopicList = new ArrayList<>();
    private boolean isConnected = false;
    private Set<String> currentTopics = new HashSet();
    private final Object connectionLock = new Object();
    private boolean isConnecting = false;
    private int currentRetryCount = 0;
    private Handler retryHandler = new Handler(Looper.getMainLooper());
    private ControlDeviceCallBack mControlDeviceCallBack = new ControlDeviceCallBack() { // from class: io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager.3
        @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
        public void onFailure(int i, String str) {
            super.onFailure(i, str);
            if (this.iStartDeviceStateListener != null) {
                this.iStartDeviceStateListener.onStartDeviceStateFail(i, str);
            }
        }

        @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
        public void onSuccess(String str) {
            super.onSuccess(str);
        }

        @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
        public void onDeviceStatusReceived(int i, String str) {
            if (i == MQTTErrCode._CON_CODE) {
                this.iRegisterDeviceTopic = new IRegisterDeviceTopic() { // from class: io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager.3.1
                    @Override // io.fogcloud.sdk.fog.api.mqtt.IRegisterDeviceTopic
                    public void onRegisterDeviceTopic(String str2, String str3) {
                        for (int i2 = 0; i2 < DeYeMqttManager.this.mTopicList.size(); i2++) {
                            if (DeYeMqttManager.this.mTopicList.get(i2) != null && ((String) DeYeMqttManager.this.mTopicList.get(i2)).contains(str3)) {
                                String str4 = DeYeMqttManager.this.getEndpoint() + ((String) DeYeMqttManager.this.mTopicList.get(i2));
                                LogDebug.INSTANCE.log(StubApp.getString2(44798) + str4);
                                DeYeMqttManager.getInstance().addDeviceListener(str4, 1, DeYeMqttManager.this.mControlDeviceCallBack);
                            }
                        }
                    }
                };
                if (this.iStartDeviceStateListener != null) {
                    this.iStartDeviceStateListener.onStartDeviceStateSuccess(i, str, 1, this.iRegisterDeviceTopic);
                }
            }
            if (this.iStartDeviceStateListener != null) {
                this.iStartDeviceStateListener.onDeviceStatusReceived(i, str, 1);
            }
        }
    };

    public String getEndpoint() {
        String str = this.mEndpoint;
        return str != null ? str : "";
    }

    public ListenDevParFog getMqttParams() {
        return this.mMqttParams;
    }

    public void setTopicList(String str, String str2) {
        String topic = Configuration.getTopic("", str2, str);
        String onlineTopic = Configuration.getOnlineTopic("", str2, str);
        if (!this.mTopicList.contains(topic)) {
            this.mTopicList.add(topic);
        }
        if (this.mTopicList.contains(onlineTopic)) {
            return;
        }
        this.mTopicList.add(onlineTopic);
    }

    private DeYeMqttManager() {
    }

    public static DeYeMqttManager getInstance() {
        if (sDeYeMqttManager == null) {
            synchronized (DeYeMqttManager.class) {
                if (sDeYeMqttManager == null) {
                    sDeYeMqttManager = new DeYeMqttManager();
                }
            }
        }
        return sDeYeMqttManager;
    }

    public boolean isConnected() {
        String string2 = StubApp.getString2(44814);
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
                    LogDebug.INSTANCE.log(StubApp.getString2("44815"));
                    return;
                }
                this.isConnecting = true;
                LogDebug.INSTANCE.log(StubApp.getString2(44816));
                IStartDeviceStateListener iStartDeviceStateListener = this.lastStartDeviceStateListener;
                if (iStartDeviceStateListener != null) {
                    startListenDevice(iStartDeviceStateListener);
                    return;
                }
                LogDebug.INSTANCE.log(StubApp.getString2(44817));
                synchronized (this.connectionLock) {
                    this.isConnecting = false;
                }
                return;
            }
        }
        LogDebug.INSTANCE.log(StubApp.getString2(44818));
    }

    public void forceReconnectOnForeground() {
        synchronized (this.connectionLock) {
            if (this.isConnecting) {
                LogDebug.INSTANCE.log(StubApp.getString2("44811"));
                return;
            }
            this.isConnecting = true;
            LogDebug.INSTANCE.log(StubApp.getString2(44812));
            stopMqtt();
            IStartDeviceStateListener iStartDeviceStateListener = this.lastStartDeviceStateListener;
            if (iStartDeviceStateListener != null) {
                startListenDevice(iStartDeviceStateListener);
                return;
            }
            LogDebug.INSTANCE.log(StubApp.getString2(44813));
            synchronized (this.connectionLock) {
                this.isConnecting = false;
            }
        }
    }

    public void startListenDevice(IStartDeviceStateListener iStartDeviceStateListener) {
        this.lastStartDeviceStateListener = iStartDeviceStateListener;
        synchronized (this.connectionLock) {
            if (this.isConnecting) {
                LogDebug.INSTANCE.log(StubApp.getString2("44826"));
                return;
            }
            this.isConnecting = true;
            this.currentRetryCount = 0;
            setupEventSubscriptions(iStartDeviceStateListener);
            HashSet hashSet = new HashSet(this.mTopicList);
            boolean z = !this.currentTopics.equals(hashSet);
            if (z || !this.isConnected) {
                LogDebug.INSTANCE.log(StubApp.getString2(44829) + z + StubApp.getString2(44667) + this.isConnected);
                this.currentTopics = hashSet;
                stopMqtt();
                requestMqttInfoWithRetry(iStartDeviceStateListener);
                return;
            }
            LogDebug.INSTANCE.log(StubApp.getString2(44827));
            if (iStartDeviceStateListener != null) {
                iStartDeviceStateListener.onStartDeviceStateSuccess(MQTTErrCode._CON_CODE, StubApp.getString2(44665), 1, new IRegisterDeviceTopic() { // from class: io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager.1
                    @Override // io.fogcloud.sdk.fog.api.mqtt.IRegisterDeviceTopic
                    public void onRegisterDeviceTopic(String str, String str2) {
                        LogDebug.INSTANCE.log(StubApp.getString2(44788) + str2);
                    }
                });
            }
            synchronized (this.connectionLock) {
                this.isConnecting = false;
                LogDebug.INSTANCE.log(StubApp.getString2("44828"));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestMqttInfoWithRetry(final IStartDeviceStateListener iStartDeviceStateListener) {
        DeYeHttpRequestManager.getInstance().getMqttInfo(new FogCallBack() { // from class: io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager.2
            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onSuccess(String str) throws JSONException {
                try {
                    LogDebug.INSTANCE.log(StubApp.getString2(44793) + str);
                    JSONObject jSONObject = new JSONObject(str).getJSONObject(StubApp.getString2("100"));
                    String strOptString = jSONObject.optString(StubApp.getString2("44745"));
                    String strOptString2 = jSONObject.optString(StubApp.getString2("44794"));
                    String strOptString3 = jSONObject.optString(StubApp.getString2("18548"));
                    DeYeMqttManager.this.mEndpoint = jSONObject.optString(StubApp.getString2("35440"));
                    jSONObject.optString(StubApp.getString2("44795"));
                    String strOptString4 = jSONObject.optString(StubApp.getString2("13687"));
                    String strOptString5 = jSONObject.optString(StubApp.getString2("44796"));
                    DeYeMqttManager.this.mMqttParams.userName = strOptString;
                    DeYeMqttManager.this.mMqttParams.passWord = strOptString4;
                    DeYeMqttManager.this.mMqttParams.host = strOptString5;
                    DeYeMqttManager.this.mMqttParams.port = strOptString2;
                    DeYeMqttManager.this.mMqttParams.clientID = MqttDeviceIdUtil.buildClientId(BaseApp.getInstance(), strOptString3);
                    DeYeMqttManager.this.mMqttParams.isencrypt = true;
                    DeYeMqttManager.this.mControlDeviceCallBack.setStartDeviceStateListener(iStartDeviceStateListener);
                    DeYeMqttManager deYeMqttManager = DeYeMqttManager.this;
                    deYeMqttManager.startListenDevice(deYeMqttManager.mMqttParams, DeYeMqttManager.this.mControlDeviceCallBack);
                    DeYeMqttManager.this.currentRetryCount = 0;
                } catch (JSONException e) {
                    e.printStackTrace();
                    synchronized (DeYeMqttManager.this.connectionLock) {
                        DeYeMqttManager.this.isConnecting = false;
                        LogDebug.INSTANCE.log(StubApp.getString2("44797"));
                    }
                }
            }

            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onFailure(int i, String str) {
                Log.d(StubApp.getString2(4146), StubApp.getString2(44653) + str);
                DeYeMqttManager.this.isConnected = false;
                DeYeMqttManager.this.currentRetryCount++;
                if (DeYeMqttManager.this.currentRetryCount < 30) {
                    int i2 = (1 << (DeYeMqttManager.this.currentRetryCount - 1)) * 1000;
                    LogDebug.INSTANCE.log(StubApp.getString2(44789) + i2 + StubApp.getString2(44655) + DeYeMqttManager.this.currentRetryCount + StubApp.getString2(44790));
                    DeYeMqttManager.this.retryHandler.postDelayed(new Runnable() { // from class: io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            DeYeMqttManager.this.requestMqttInfoWithRetry(iStartDeviceStateListener);
                        }
                    }, i2);
                    return;
                }
                LogDebug.INSTANCE.log(StubApp.getString2(44791));
                synchronized (DeYeMqttManager.this.connectionLock) {
                    DeYeMqttManager.this.isConnecting = false;
                    DeYeMqttManager.this.currentRetryCount = 0;
                    LogDebug.INSTANCE.log(StubApp.getString2("44792"));
                }
                IStartDeviceStateListener iStartDeviceStateListener2 = iStartDeviceStateListener;
                if (iStartDeviceStateListener2 != null) {
                    iStartDeviceStateListener2.onDeviceStatusReceived(-1, StubApp.getString2(44659) + str, 1);
                }
            }
        });
    }

    private void setupEventSubscriptions(final IStartDeviceStateListener iStartDeviceStateListener) {
        this.eventSubscriptions.dispose();
        LogDebug.INSTANCE.log(StubApp.getString2(44807));
        this.eventSubscriptions.add(MqttEventBus.getInstance().toObservable(MqttConnectedEvent.class).filter(new Predicate() { // from class: io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return DeYeMqttManager.lambda$setupEventSubscriptions$0((MqttConnectedEvent) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$setupEventSubscriptions$1((MqttConnectedEvent) obj);
            }
        }));
        this.eventSubscriptions.add(MqttEventBus.getInstance().toObservable(MqttAuthenticationFailedEvent.class).filter(new Predicate() { // from class: io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return DeYeMqttManager.lambda$setupEventSubscriptions$2((MqttAuthenticationFailedEvent) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$setupEventSubscriptions$3(iStartDeviceStateListener, (MqttAuthenticationFailedEvent) obj);
            }
        }));
        this.eventSubscriptions.add(MqttEventBus.getInstance().toObservable(DeviceStatusEvent.class).filter(new Predicate() { // from class: io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return DeYeMqttManager.lambda$setupEventSubscriptions$4((DeviceStatusEvent) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                DeYeMqttManager.lambda$setupEventSubscriptions$5(iStartDeviceStateListener, (DeviceStatusEvent) obj);
            }
        }));
        this.eventSubscriptions.add(MqttEventBus.getInstance().toObservable(MqttConnectionLostEvent.class).filter(new Predicate() { // from class: io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return DeYeMqttManager.lambda$setupEventSubscriptions$6((MqttConnectionLostEvent) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager$$ExternalSyntheticLambda7
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$setupEventSubscriptions$7((MqttConnectionLostEvent) obj);
            }
        }));
        LogDebug.INSTANCE.log(StubApp.getString2(44808));
    }

    static /* synthetic */ boolean lambda$setupEventSubscriptions$0(MqttConnectedEvent mqttConnectedEvent) throws Throwable {
        return mqttConnectedEvent.getPlatform() == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupEventSubscriptions$1(MqttConnectedEvent mqttConnectedEvent) throws Throwable {
        LogDebug.INSTANCE.log(StubApp.getString2(44800) + mqttConnectedEvent.getPlatform());
        this.isConnected = true;
        LogDebug.INSTANCE.log(StubApp.getString2(44801));
        synchronized (this.connectionLock) {
            this.isConnecting = false;
            LogDebug.INSTANCE.log(StubApp.getString2("44802"));
        }
    }

    static /* synthetic */ boolean lambda$setupEventSubscriptions$2(MqttAuthenticationFailedEvent mqttAuthenticationFailedEvent) throws Throwable {
        return mqttAuthenticationFailedEvent.getPlatform() == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupEventSubscriptions$3(IStartDeviceStateListener iStartDeviceStateListener, MqttAuthenticationFailedEvent mqttAuthenticationFailedEvent) throws Throwable {
        LogDebug.INSTANCE.log(StubApp.getString2(44803) + mqttAuthenticationFailedEvent.getReasonCode() + StubApp.getString2(44689));
        this.isConnected = false;
        synchronized (this.connectionLock) {
            this.isConnecting = false;
            LogDebug.INSTANCE.log(StubApp.getString2("44804"));
        }
        startListenDevice(iStartDeviceStateListener);
    }

    static /* synthetic */ boolean lambda$setupEventSubscriptions$4(DeviceStatusEvent deviceStatusEvent) throws Throwable {
        return deviceStatusEvent.getPlatform() == 1;
    }

    static /* synthetic */ void lambda$setupEventSubscriptions$5(IStartDeviceStateListener iStartDeviceStateListener, DeviceStatusEvent deviceStatusEvent) throws Throwable {
        if (iStartDeviceStateListener != null) {
            iStartDeviceStateListener.onDeviceStatusReceived(MQTTErrCode._PAYLOAD_CODE, deviceStatusEvent.getPayload(), 1);
        }
    }

    static /* synthetic */ boolean lambda$setupEventSubscriptions$6(MqttConnectionLostEvent mqttConnectionLostEvent) throws Throwable {
        return mqttConnectionLostEvent.getPlatform() == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupEventSubscriptions$7(MqttConnectionLostEvent mqttConnectionLostEvent) throws Throwable {
        LogDebug.INSTANCE.log(StubApp.getString2(44805) + mqttConnectionLostEvent.getCauseMessage());
        this.isConnected = false;
        synchronized (this.connectionLock) {
            if (this.isConnecting) {
                this.isConnecting = false;
                LogDebug.INSTANCE.log(StubApp.getString2("44806"));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startListenDevice(ListenDevParFog listenDevParFog, final ControlDeviceCallBack controlDeviceCallBack) {
        if (this.mMqttServiceAPI == null) {
            this.mMqttServiceAPI = new UnifiedMqttServiceAPI(MqttConfig.forBaidu());
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
                this.mMqttServiceAPI.startMqttService(BaseApp.getInstance(), str, str5, str2, str3, str4, null, z, new MqttServiceListener() { // from class: io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager.4
                    @Override // io.fogcloud.sdk.fog.api.mqtt.sdk.service.MqttServiceListener
                    public void onMqttReceiver(int i2, String str6) {
                        DeYeMqttManager.this.onDevStatusReceived(i2, str6, controlDeviceCallBack);
                    }
                });
                successCBCtrlDev(MiCOConstParam.SUCCESS, controlDeviceCallBack);
            } else {
                failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, controlDeviceCallBack);
            }
        }
    }

    public synchronized void sendSingleMsg(String str, CommandPara commandPara, byte[] bArr, ControlDeviceCallBack controlDeviceCallBack) {
        String string2 = StubApp.getString2(44820);
        synchronized (this) {
            if (checkPara(commandPara.deviceid, commandPara.endpoint, commandPara.productid)) {
                if (this.mMqttServiceAPI != null) {
                    String str2 = commandPara.endpoint + StubApp.getString2("1002") + commandPara.productid + StubApp.getString2("1002") + commandPara.deviceid + StubApp.getString2("44821");
                    LogDebug.INSTANCE.log(string2 + str + StubApp.getString2("44822") + str2 + StubApp.getString2("44823") + bArr);
                    this.mMqttServiceAPI.publishSingleCommand(str2, bArr, 0, false);
                } else {
                    LogDebug.INSTANCE.log(StubApp.getString2("44825"));
                    failureCBCtrlDev(MiCOConstParam.EMPTYCODE, StubApp.getString2("44810"), controlDeviceCallBack);
                }
            } else {
                failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, controlDeviceCallBack);
            }
        }
    }

    public synchronized void sendMsg(String str, CommandPara commandPara, ControlDeviceCallBack controlDeviceCallBack) {
        sendMsg(false, 0L, str, commandPara, controlDeviceCallBack);
    }

    public synchronized void sendMsg(boolean z, long j, String str, CommandPara commandPara, ControlDeviceCallBack controlDeviceCallBack) {
        String string2 = StubApp.getString2(44820);
        synchronized (this) {
            if (checkPara(commandPara.deviceid, commandPara.endpoint, commandPara.productid, commandPara.command)) {
                String str2 = commandPara.endpoint + StubApp.getString2("1002") + commandPara.productid + StubApp.getString2("1002") + commandPara.deviceid + StubApp.getString2("44821");
                String hexString = commandPara.command;
                if (StubApp.getString2("12998").equals(str)) {
                    if (!AppHexCharHelper.QUERY_DEVICE_STATE.equals(hexString) && !AppHexCharHelper.DEVICE_STATE_CHANGE_COMMAND.equals(hexString)) {
                        hexString = DehumidifierBeanString2Hex.getHexString(hexString);
                    }
                } else if (StubApp.getString2("12999").equals(str)) {
                    if (!AppHexCharHelper.QUERY_DEVICE_STATE.equals(hexString) && !AppHexCharHelper.DEVICE_STATE_CHANGE_COMMAND.equals(hexString)) {
                        hexString = LoopFanBeanString2Hex.getHexString(hexString);
                    }
                } else if (StubApp.getString2("14008").equals(str) && !AppHexCharHelper.QUERY_DEVICE_STATE.equals(hexString) && !AppHexCharHelper.DEVICE_STATE_CHANGE_COMMAND.equals(hexString)) {
                    hexString = QuiltDryerBeanString2Hex.getHexString(hexString);
                }
                String str3 = hexString;
                if (this.mMqttServiceAPI != null) {
                    LogDebug.INSTANCE.log(string2 + str + StubApp.getString2("44822") + str2 + StubApp.getString2("44823") + str3);
                    this.mMqttServiceAPI.publishCommand(z, j, str2, str3, 0, false);
                } else {
                    LogDebug.INSTANCE.log(StubApp.getString2("44824"));
                    failureCBCtrlDev(MiCOConstParam.EMPTYCODE, StubApp.getString2("44810"), controlDeviceCallBack);
                }
            } else {
                failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, controlDeviceCallBack);
            }
        }
    }

    public void addDeviceListener(String str, int i, ControlDeviceCallBack controlDeviceCallBack) {
        if (!checkPara(str)) {
            failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, controlDeviceCallBack);
            return;
        }
        if (i < 0 || i > 2) {
            failureCBCtrlDev(MiCOConstParam.QOSERRCODE, MiCOConstParam.QOSERR, controlDeviceCallBack);
            return;
        }
        UnifiedMqttServiceAPI unifiedMqttServiceAPI = this.mMqttServiceAPI;
        if (unifiedMqttServiceAPI != null) {
            unifiedMqttServiceAPI.subscribe(str, i);
            successCBCtrlDev(MiCOConstParam.SUCCESS, controlDeviceCallBack);
        } else {
            LogDebug.INSTANCE.log(StubApp.getString2(44809));
            failureCBCtrlDev(MiCOConstParam.EMPTYCODE, StubApp.getString2(44810), controlDeviceCallBack);
        }
    }

    public void removeDeviceListener(String str, ControlDeviceCallBack controlDeviceCallBack) {
        if (checkPara(str)) {
            UnifiedMqttServiceAPI unifiedMqttServiceAPI = this.mMqttServiceAPI;
            if (unifiedMqttServiceAPI != null) {
                unifiedMqttServiceAPI.unsubscribe(str);
                successCBCtrlDev(MiCOConstParam.SUCCESS, controlDeviceCallBack);
                return;
            } else {
                LogDebug.INSTANCE.log(StubApp.getString2(44819));
                successCBCtrlDev(MiCOConstParam.SUCCESS, controlDeviceCallBack);
                return;
            }
        }
        failureCBCtrlDev(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, controlDeviceCallBack);
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

    public void stopMqtt() {
        this.retryHandler.removeCallbacksAndMessages(null);
        LogDebug.INSTANCE.log(StubApp.getString2(44830));
        this.eventSubscriptions.dispose();
        LogDebug.INSTANCE.log(StubApp.getString2(44831));
        if (!TextUtils.isEmpty(getEndpoint()) && this.mMqttServiceAPI != null) {
            for (int i = 0; i < this.mTopicList.size(); i++) {
                LogDebug.INSTANCE.log(StubApp.getString2(44704) + this.mTopicList.get(i) + StubApp.getString2(44705) + this.mTopicList.size() + StubApp.getString2(44832) + i);
                removeDeviceListener(getEndpoint() + this.mTopicList.get(i), null);
            }
            stopListenDevice(null);
            this.mMqttServiceAPI = null;
            LogDebug.INSTANCE.log(StubApp.getString2(44833));
        }
        this.isConnected = false;
        this.currentTopics.clear();
        synchronized (this.connectionLock) {
            if (this.isConnecting) {
                this.isConnecting = false;
                LogDebug.INSTANCE.log(StubApp.getString2("44834"));
            }
        }
        LogDebug.INSTANCE.log(StubApp.getString2(44835));
    }
}
