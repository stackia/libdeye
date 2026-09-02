package io.fogcloud.sdk.fog.api.mqtt;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.amap.location.type.location.Location;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.mqtt.event.MqttConnectedEvent;
import io.fogcloud.sdk.fog.api.mqtt.event.MqttConnectionLostEvent;
import io.fogcloud.sdk.fog.api.mqtt.event.MqttEventBus;
import io.fogcloud.sdk.fog.api.mqtt.sdk.helper.ComHelper;
import io.fogcloud.sdk.fog.api.mqtt.sdk.helper.MQTTErrCode;
import io.fogcloud.sdk.fog.api.mqtt.sdk.helper.MqttTimeStampHelper;
import io.fogcloud.sdk.fog.api.mqtt.sdk.service.MqttServiceListener;
import io.fogcloud.sdk.fog.log.LogDebug;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.X509TrustManager;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class UnifiedMqttService extends Service {
    public static final int MESSAGE_ARRIVED = 1;
    public static final int MQTT_CONNECTED = 2;
    public static final int MQTT_CONNECT_FAILED = 3;
    private static volatile boolean sIsAppInForeground = true;

    @Deprecated
    private MqttClient client;

    @Deprecated
    private MqttConfig config;

    @Deprecated
    private Handler handler;

    @Deprecated
    private MqttServiceListener mMqttServiceListener;

    @Deprecated
    private MqttConnectOptions options;

    @Deprecated
    private ScheduledExecutorService scheduler;
    private ServiceBinder serviceBinder = new ServiceBinder();

    @Deprecated
    private Boolean recvTag = false;

    @Deprecated
    private Boolean connectTag = false;
    private final ConcurrentHashMap<Integer, MqttConnection> connections = new ConcurrentHashMap<>();
    private final Set<Integer> cleaningUpPlatforms = Collections.newSetFromMap(new ConcurrentHashMap());
    private final Object connectionManagementLock = new Object();

    /* compiled from: D8$$SyntheticClass */
    /* renamed from: io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttService$MqttConnection-IA, reason: invalid class name */
    public final /* synthetic */ class MqttConnectionIA {
    }

    static {
        StubApp.interface11(34295);
    }

    private native long calculateReconnectDelayForPlatform(MqttConnection mqttConnection);

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: connectForPlatform, reason: merged with bridge method [inline-methods] */
    public native void lambda$scheduleNextReconnectForPlatform$3(MqttConnection mqttConnection);

    private native void handleConnectException(MqttConnection mqttConnection, MqttException mqttException);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean isAppInForeground();

    private native void mqttInitForPlatform(MqttConnection mqttConnection, String str, String str2, String str3, String str4, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public native void reSubscribeForPlatform(MqttConnection mqttConnection, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void scheduleNextReconnectForPlatform(MqttConnection mqttConnection);

    private native void sendMsgToClient(int i, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void sendMsgToClientForPlatform(MqttConnection mqttConnection, int i, String str);

    public static native void setAppForegroundState(boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public native void startMqttServiceForPlatform(int i, String str, String str2, String str3, String str4, String str5, String str6, boolean z);

    private native void startReconnectForPlatform(MqttConnection mqttConnection);

    /* JADX INFO: Access modifiers changed from: private */
    public native void stopMqttServiceForPlatform(int i, Runnable runnable);

    /* JADX INFO: Access modifiers changed from: private */
    public native void triggerReconnectIfNeeded(MqttConnection mqttConnection, String str);

    @Override // android.app.Service
    public native IBinder onBind(Intent intent);

    @Override // android.app.Service
    public native void onCreate();

    @Override // android.app.Service
    public native void onDestroy();

    public native void stopMqttService();

    /* JADX INFO: Access modifiers changed from: private */
    static class MqttConnection {
        MqttClient client;
        String clientID;
        MqttConfig config;
        Boolean connectTag;
        Handler handler;
        String host;
        volatile boolean isCleaningUp;
        boolean isencrypt;
        MqttServiceListener listener;
        MqttConnectOptions options;
        String passWord;
        int platformId;
        String port;
        int reconnectAttempts;
        Boolean recvTag;
        ScheduledExecutorService scheduler;
        String topic;
        String[] topicList;
        String userName;

        /* synthetic */ MqttConnection(MqttConnectionIA mqttConnectionIA) {
            this();
        }

        private MqttConnection() {
            this.recvTag = false;
            this.connectTag = false;
            this.reconnectAttempts = 0;
            this.topicList = null;
            this.isCleaningUp = false;
        }
    }

    /* renamed from: io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttService$1, reason: invalid class name */
    class AnonymousClass1 extends Handler {
        final /* synthetic */ MqttConnection val$conn;
        final /* synthetic */ int val$platformId;
        final /* synthetic */ String val$topic;

        AnonymousClass1(MqttConnection mqttConnection, int i, String str) {
            this.val$conn = mqttConnection;
            this.val$platformId = i;
            this.val$topic = str;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            String logPrefix = this.val$conn.config.getLogPrefix();
            int i = message.what;
            String string2 = StubApp.getString2(2566);
            if (i == 1) {
                String str = (String) message.obj;
                LogDebug.INSTANCE.log(string2 + logPrefix + StubApp.getString2(44838) + (this.val$conn.listener != null ? StubApp.getString2(44839) : StubApp.getString2(2369)));
                UnifiedMqttService.this.sendMsgToClientForPlatform(this.val$conn, MQTTErrCode._PAYLOAD_CODE, str);
                LogDebug.INSTANCE.log(string2 + logPrefix + StubApp.getString2(44840));
                return;
            }
            if (message.what == 2) {
                this.val$conn.reconnectAttempts = 0;
                LogDebug.INSTANCE.log(string2 + logPrefix + StubApp.getString2(44841) + this.val$platformId);
                MqttEventBus.getInstance().post(new MqttConnectedEvent(this.val$platformId));
                this.val$conn.connectTag = true;
                if (ComHelper.checkPara(this.val$topic)) {
                    UnifiedMqttService.this.reSubscribeForPlatform(this.val$conn, this.val$topic);
                }
                UnifiedMqttService.this.sendMsgToClientForPlatform(this.val$conn, MQTTErrCode._CON_CODE, MQTTErrCode._CON_MSG);
                return;
            }
            if (message.what == 3) {
                LogDebug.INSTANCE.log(string2 + logPrefix + StubApp.getString2(44842) + this.val$platformId);
                UnifiedMqttService.this.sendMsgToClientForPlatform(this.val$conn, MQTTErrCode._EXCEPTION_CODE, MQTTErrCode._EXCEP_MSG);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$stopMqttServiceForPlatform$2(MqttConnection mqttConnection, final int i, final Runnable runnable) {
        String string2 = StubApp.getString2(44934);
        String string22 = StubApp.getString2(44935);
        String string23 = StubApp.getString2(44936);
        String string24 = StubApp.getString2(44936);
        String string25 = StubApp.getString2(44937);
        String string26 = StubApp.getString2(44938);
        try {
            if (mqttConnection.scheduler != null && !mqttConnection.scheduler.isShutdown()) {
                mqttConnection.scheduler.shutdownNow();
                try {
                    if (!mqttConnection.scheduler.awaitTermination(500L, TimeUnit.MILLISECONDS)) {
                        LogDebug.INSTANCE.log(string2 + i + StubApp.getString2("44939"));
                    } else {
                        LogDebug.INSTANCE.log(string22 + i + StubApp.getString2("44940"));
                    }
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
            if (mqttConnection.client != null) {
                try {
                    if (mqttConnection.client.isConnected()) {
                        mqttConnection.client.disconnect();
                        LogDebug.INSTANCE.log(string23 + i + StubApp.getString2("44941"));
                    }
                    mqttConnection.client.close();
                    LogDebug.INSTANCE.log(string24 + i + StubApp.getString2("44942"));
                } catch (Exception e) {
                    LogDebug.INSTANCE.log(string26 + e.getMessage());
                }
            }
            this.cleaningUpPlatforms.remove(Integer.valueOf(i));
            LogDebug.INSTANCE.log(string25 + i + StubApp.getString2("44943"));
            if (runnable != null) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttService$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        UnifiedMqttService.lambda$stopMqttServiceForPlatform$0(i, runnable);
                    }
                });
            }
        } catch (Exception e2) {
            LogDebug.INSTANCE.log(StubApp.getString2(44944) + e2.getMessage());
            e2.printStackTrace();
            synchronized (this.cleaningUpPlatforms) {
                this.cleaningUpPlatforms.remove(Integer.valueOf(i));
                if (runnable != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttService$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            UnifiedMqttService.lambda$stopMqttServiceForPlatform$1(i, runnable);
                        }
                    });
                }
            }
        }
    }

    static /* synthetic */ void lambda$stopMqttServiceForPlatform$0(int i, Runnable runnable) {
        LogDebug.INSTANCE.log(StubApp.getString2(44932) + i);
        runnable.run();
    }

    static /* synthetic */ void lambda$stopMqttServiceForPlatform$1(int i, Runnable runnable) {
        LogDebug.INSTANCE.log(StubApp.getString2(44933) + i);
        runnable.run();
    }

    /* renamed from: io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttService$2, reason: invalid class name */
    class AnonymousClass2 implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        AnonymousClass2() {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /* renamed from: io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttService$3, reason: invalid class name */
    class AnonymousClass3 implements MqttCallback {
        final /* synthetic */ MqttConnection val$conn;
        final /* synthetic */ String val$logPrefix;

        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        }

        AnonymousClass3(String str, MqttConnection mqttConnection) {
            this.val$logPrefix = str;
            this.val$conn = mqttConnection;
        }

        public void connectionLost(Throwable th) {
            boolean z;
            String string2 = UnifiedMqttService.this.isAppInForeground() ? StubApp.getString2(44843) : StubApp.getString2(44844);
            String str = StubApp.getString2(44845) + Thread.currentThread().getId() + StubApp.getString2(499);
            LogDebug.INSTANCE.log(str + StubApp.getString2(10730) + this.val$logPrefix + StubApp.getString2(44846) + string2 + StubApp.getString2(44847) + this.val$conn.platformId + StubApp.getString2(44848) + Integer.toHexString(this.val$conn.hashCode()) + StubApp.getString2(44849) + (th != null ? th.getMessage() : StubApp.getString2(Location.SubType.GNSS_NEMA_INS)));
            synchronized (UnifiedMqttService.this.connectionManagementLock) {
                MqttConnection mqttConnection = (MqttConnection) UnifiedMqttService.this.connections.get(Integer.valueOf(this.val$conn.platformId));
                if (mqttConnection != this.val$conn) {
                    LogDebug.INSTANCE.log(str + StubApp.getString2("10730") + this.val$logPrefix + StubApp.getString2("44850") + Integer.toHexString(this.val$conn.hashCode()) + StubApp.getString2("44851") + (mqttConnection != null ? Integer.toHexString(mqttConnection.hashCode()) : StubApp.getString2("1720")) + StubApp.getString2("44852"));
                    if (this.val$conn.scheduler != null && !this.val$conn.scheduler.isShutdown()) {
                        this.val$conn.scheduler.shutdownNow();
                        this.val$conn.scheduler = null;
                    }
                    z = false;
                } else {
                    z = true;
                }
            }
            if (z) {
                MqttEventBus.getInstance().post(new MqttConnectionLostEvent(this.val$conn.platformId, th));
                this.val$conn.connectTag = false;
                if (UnifiedMqttService.this.isAppInForeground()) {
                    if (this.val$conn.scheduler == null || this.val$conn.scheduler.isShutdown()) {
                        LogDebug.INSTANCE.log(StubApp.getString2(2566) + this.val$logPrefix + StubApp.getString2(44853));
                        this.val$conn.scheduler = Executors.newSingleThreadScheduledExecutor();
                    }
                    UnifiedMqttService.this.scheduleNextReconnectForPlatform(this.val$conn);
                    return;
                }
                LogDebug.INSTANCE.log(StubApp.getString2(2566) + this.val$logPrefix + StubApp.getString2(44854));
                if (this.val$conn.scheduler == null || this.val$conn.scheduler.isShutdown()) {
                    return;
                }
                this.val$conn.scheduler.shutdownNow();
                this.val$conn.scheduler = null;
            }
        }

        public void messageArrived(String str, MqttMessage mqttMessage) {
            String str2 = new String(mqttMessage.getPayload());
            LogDebug logDebug = LogDebug.INSTANCE;
            String string2 = StubApp.getString2(2566);
            StringBuilder sbAppend = new StringBuilder(string2).append(this.val$logPrefix).append(StubApp.getString2(44855)).append(str).append(StubApp.getString2(44856)).append(this.val$conn.recvTag).append(StubApp.getString2(44857));
            Handler handler = this.val$conn.handler;
            String string22 = StubApp.getString2(44839);
            String string23 = StubApp.getString2(2369);
            StringBuilder sbAppend2 = sbAppend.append(handler != null ? string22 : string23).append(StubApp.getString2(44858));
            if (this.val$conn.listener == null) {
                string22 = string23;
            }
            logDebug.log(sbAppend2.append(string22).toString());
            if (this.val$conn.recvTag.booleanValue() && this.val$conn.handler != null) {
                Message messageObtainMessage = this.val$conn.handler.obtainMessage();
                messageObtainMessage.what = 1;
                messageObtainMessage.obj = StubApp.getString2(44710) + str + StubApp.getString2(44711) + str2 + StubApp.getString2(14593);
                this.val$conn.handler.sendMessage(messageObtainMessage);
                LogDebug.INSTANCE.log(string2 + this.val$logPrefix + StubApp.getString2(44859));
                return;
            }
            LogDebug.INSTANCE.log(string2 + this.val$logPrefix + StubApp.getString2(44860) + this.val$conn.recvTag + StubApp.getString2(44861) + (this.val$conn.handler != null));
        }
    }

    public class ServiceBinder extends Binder {
        public ServiceBinder() {
        }

        public UnifiedMqttService getService() {
            return UnifiedMqttService.this;
        }

        public void bindListenerAPI(int i, MqttServiceListener mqttServiceListener) {
            MqttConnection mqttConnection = (MqttConnection) UnifiedMqttService.this.connections.get(Integer.valueOf(i));
            if (mqttConnection != null) {
                mqttConnection.listener = mqttServiceListener;
                mqttConnection.recvTag = true;
                LogDebug.INSTANCE.log(StubApp.getString2(44871) + i + StubApp.getString2(44857) + (mqttConnection.handler != null ? StubApp.getString2(44839) : StubApp.getString2(2369)) + StubApp.getString2(44872) + mqttConnection.connectTag + StubApp.getString2(44856) + mqttConnection.recvTag);
                return;
            }
            LogDebug.INSTANCE.log(StubApp.getString2(44873) + i + StubApp.getString2(44874));
        }

        public void stopRecvMsgAPI(int i) {
            MqttConnection mqttConnection = (MqttConnection) UnifiedMqttService.this.connections.get(Integer.valueOf(i));
            if (mqttConnection != null) {
                mqttConnection.recvTag = false;
                LogDebug.INSTANCE.log(StubApp.getString2(44900) + i);
            }
        }

        public boolean isConnectedAPI(int i) {
            MqttConnection mqttConnection = (MqttConnection) UnifiedMqttService.this.connections.get(Integer.valueOf(i));
            if (mqttConnection != null && mqttConnection.client != null) {
                try {
                    return mqttConnection.client.isConnected();
                } catch (Exception unused) {
                }
            }
            return false;
        }

        public void reconnectPlatform(int i) {
            MqttConnection mqttConnection = (MqttConnection) UnifiedMqttService.this.connections.get(Integer.valueOf(i));
            if (mqttConnection == null || mqttConnection.client == null) {
                LogDebug.INSTANCE.log(StubApp.getString2(44896) + i + StubApp.getString2(44897));
                return;
            }
            String logPrefix = mqttConnection.config.getLogPrefix();
            boolean zIsConnected = mqttConnection.client.isConnected();
            String string2 = StubApp.getString2(2566);
            if (!zIsConnected) {
                LogDebug.INSTANCE.log(string2 + logPrefix + StubApp.getString2(44894));
                mqttConnection.reconnectAttempts = 0;
                UnifiedMqttService.this.lambda$scheduleNextReconnectForPlatform$3(mqttConnection);
                return;
            }
            LogDebug.INSTANCE.log(string2 + logPrefix + StubApp.getString2(44895));
        }

        public void stopPlatformConnection(int i) {
            LogDebug.INSTANCE.log(StubApp.getString2(44896) + i + StubApp.getString2(44899));
            UnifiedMqttService.this.stopMqttServiceForPlatform(i, null);
        }

        public void publishAPI(int i, boolean z, long j, String str, String str2, int i2, boolean z2) {
            MqttConnection mqttConnection = (MqttConnection) UnifiedMqttService.this.connections.get(Integer.valueOf(i));
            String string2 = StubApp.getString2(44863);
            String string22 = StubApp.getString2(44887);
            if (mqttConnection != null && mqttConnection.client != null) {
                try {
                    if (!mqttConnection.client.isConnected()) {
                        LogDebug.INSTANCE.log(string22 + i + StubApp.getString2("44888") + str);
                        UnifiedMqttService.this.triggerReconnectIfNeeded(mqttConnection, StubApp.getString2("44889"));
                        return;
                    } else {
                        if (z) {
                            byte[] bytes = str2.getBytes();
                            int length = bytes.length;
                            byte[] bArr = new byte[length + 8];
                            System.arraycopy(bytes, 0, bArr, 0, length);
                            byte[] bArrTime2byteArray = MqttTimeStampHelper.time2byteArray(j);
                            System.arraycopy(bArrTime2byteArray, 0, bArr, length, bArrTime2byteArray.length);
                            mqttConnection.client.publish(str, bArr, i2, z2);
                            return;
                        }
                        mqttConnection.client.publish(str, str2.getBytes(), i2, z2);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LogDebug.INSTANCE.log(StubApp.getString2(44890) + i + string2 + str + StubApp.getString2(7134) + e.getMessage());
                    return;
                }
            }
            LogDebug.INSTANCE.log(string22 + i + StubApp.getString2(626) + (mqttConnection == null ? StubApp.getString2(44891) : StubApp.getString2(18531)) + string2 + str);
        }

        public void publishAPISingle(int i, String str, byte[] bArr, int i2, boolean z) {
            MqttConnection mqttConnection = (MqttConnection) UnifiedMqttService.this.connections.get(Integer.valueOf(i));
            String string2 = StubApp.getString2(44863);
            String string22 = StubApp.getString2(44887);
            if (mqttConnection != null && mqttConnection.client != null) {
                try {
                    if (mqttConnection.client.isConnected()) {
                        mqttConnection.client.publish(str, bArr, i2, z);
                    } else {
                        LogDebug.INSTANCE.log(string22 + i + StubApp.getString2("44888") + str);
                        UnifiedMqttService.this.triggerReconnectIfNeeded(mqttConnection, StubApp.getString2("44892"));
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    LogDebug.INSTANCE.log(StubApp.getString2(44893) + i + string2 + str + StubApp.getString2(7134) + e.getMessage());
                    return;
                }
            }
            LogDebug.INSTANCE.log(string22 + i + StubApp.getString2(626) + (mqttConnection == null ? StubApp.getString2(44891) : StubApp.getString2(18531)) + string2 + str);
        }

        public void addSubscribeAPI(int i, String str, int i2) {
            MqttConnection mqttConnection = (MqttConnection) UnifiedMqttService.this.connections.get(Integer.valueOf(i));
            if (mqttConnection == null || mqttConnection.client == null) {
                LogDebug.INSTANCE.log(StubApp.getString2(44869) + i + StubApp.getString2(44870));
                return;
            }
            String logPrefix = mqttConnection.config.getLogPrefix();
            String string2 = StubApp.getString2(2566);
            if (str == null || str.isEmpty()) {
                LogDebug.INSTANCE.log(string2 + logPrefix + StubApp.getString2(44868));
                return;
            }
            if (!mqttConnection.client.isConnected()) {
                LogDebug.INSTANCE.log(string2 + logPrefix + StubApp.getString2(44862) + Integer.toHexString(mqttConnection.hashCode()) + StubApp.getString2(44863) + str);
                UnifiedMqttService.this.triggerReconnectIfNeeded(mqttConnection, StubApp.getString2(44864));
                return;
            }
            try {
                mqttConnection.client.subscribe(str, i2);
                if (mqttConnection.topicList == null) {
                    mqttConnection.topicList = new String[]{str};
                } else {
                    String[] strArr = mqttConnection.topicList;
                    int length = strArr.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 < length) {
                            if (str.equals(strArr[i3])) {
                                break;
                            } else {
                                i3++;
                            }
                        } else {
                            String[] strArr2 = new String[mqttConnection.topicList.length + 1];
                            System.arraycopy(mqttConnection.topicList, 0, strArr2, 0, mqttConnection.topicList.length);
                            strArr2[mqttConnection.topicList.length] = str;
                            mqttConnection.topicList = strArr2;
                            break;
                        }
                    }
                }
                LogDebug.INSTANCE.log(string2 + logPrefix + StubApp.getString2("44865") + str + StubApp.getString2("44866") + (mqttConnection.topicList != null ? mqttConnection.topicList.length : 0));
            } catch (Exception e) {
                e.printStackTrace();
                LogDebug.INSTANCE.log(string2 + logPrefix + StubApp.getString2(44867) + str + StubApp.getString2(7134) + e.getMessage());
            }
        }

        public void unSubscribeAPI(int i, String str) {
            MqttConnection mqttConnection = (MqttConnection) UnifiedMqttService.this.connections.get(Integer.valueOf(i));
            if (mqttConnection == null || mqttConnection.client == null) {
                LogDebug.INSTANCE.log(StubApp.getString2(44907) + i + StubApp.getString2(44870));
                return;
            }
            String logPrefix = mqttConnection.config.getLogPrefix();
            String string2 = StubApp.getString2(2566);
            if (str == null || str.isEmpty()) {
                LogDebug.INSTANCE.log(string2 + logPrefix + StubApp.getString2(44906));
                return;
            }
            if (!mqttConnection.client.isConnected()) {
                LogDebug.INSTANCE.log(string2 + logPrefix + StubApp.getString2(44901) + str);
                return;
            }
            try {
                LogDebug.INSTANCE.log(string2 + logPrefix + StubApp.getString2("44902") + str);
                mqttConnection.client.unsubscribe(str);
                if (mqttConnection.topicList != null) {
                    ArrayList arrayList = new ArrayList();
                    for (String str2 : mqttConnection.topicList) {
                        if (!str.equals(str2)) {
                            arrayList.add(str2);
                        }
                    }
                    mqttConnection.topicList = (String[]) arrayList.toArray(new String[0]);
                }
                LogDebug.INSTANCE.log(string2 + logPrefix + StubApp.getString2("44903") + str + StubApp.getString2("44904") + (mqttConnection.topicList != null ? mqttConnection.topicList.length : 0));
            } catch (Exception e) {
                e.printStackTrace();
                LogDebug.INSTANCE.log(string2 + logPrefix + StubApp.getString2(44905) + str + StubApp.getString2(7134) + e.getMessage());
            }
        }

        public void recvMsgAPI(int i) {
            MqttConnection mqttConnection = (MqttConnection) UnifiedMqttService.this.connections.get(Integer.valueOf(i));
            if (mqttConnection != null) {
                mqttConnection.recvTag = true;
                LogDebug.INSTANCE.log(StubApp.getString2(44898) + i);
            }
        }

        public void ensurePlatformConnection(final int i, final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final boolean z) {
            String string2 = StubApp.getString2(44845);
            synchronized (UnifiedMqttService.this.connectionManagementLock) {
                String str7 = string2 + Thread.currentThread().getId() + StubApp.getString2("499");
                LogDebug.INSTANCE.log(str7 + StubApp.getString2("44875") + i + StubApp.getString2("44876") + str5);
                if (UnifiedMqttService.this.cleaningUpPlatforms.contains(Integer.valueOf(i))) {
                    LogDebug.INSTANCE.log(str7 + StubApp.getString2("44877") + i + StubApp.getString2("44878"));
                    LogDebug.INSTANCE.log(str7 + StubApp.getString2("44879"));
                    return;
                }
                MqttConnection mqttConnection = (MqttConnection) UnifiedMqttService.this.connections.get(Integer.valueOf(i));
                if (mqttConnection != null) {
                    if (mqttConnection.isCleaningUp) {
                        LogDebug.INSTANCE.log(str7 + StubApp.getString2("44877") + i + StubApp.getString2("44880"));
                        return;
                    }
                    if (str.equals(mqttConnection.host) && str2.equals(mqttConnection.port)) {
                        if (str3.equals(mqttConnection.userName)) {
                            if (!str4.equals(mqttConnection.passWord) || !str5.equals(mqttConnection.clientID) || ((str6 != null && !str6.equals(mqttConnection.topic)) || z != mqttConnection.isencrypt)) {
                                LogDebug.INSTANCE.log(str7 + StubApp.getString2("44881") + i + StubApp.getString2("44883") + mqttConnection.clientID + StubApp.getString2("44696") + str5 + StubApp.getString2("44884"));
                                UnifiedMqttService.this.stopMqttServiceForPlatform(i, new Runnable() { // from class: io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttService$ServiceBinder$$ExternalSyntheticLambda0
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        this.f$0.lambda$ensurePlatformConnection$0(i, str, str2, str3, str4, str5, str6, z);
                                    }
                                });
                            } else {
                                LogDebug.INSTANCE.log(str7 + StubApp.getString2("44881") + i + StubApp.getString2("44882"));
                            }
                        }
                        LogDebug.INSTANCE.log(str7 + StubApp.getString2("44881") + i + StubApp.getString2("44883") + mqttConnection.clientID + StubApp.getString2("44696") + str5 + StubApp.getString2("44884"));
                        UnifiedMqttService.this.stopMqttServiceForPlatform(i, new Runnable() { // from class: io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttService$ServiceBinder$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.lambda$ensurePlatformConnection$0(i, str, str2, str3, str4, str5, str6, z);
                            }
                        });
                    }
                    LogDebug.INSTANCE.log(str7 + StubApp.getString2("44881") + i + StubApp.getString2("44883") + mqttConnection.clientID + StubApp.getString2("44696") + str5 + StubApp.getString2("44884"));
                    UnifiedMqttService.this.stopMqttServiceForPlatform(i, new Runnable() { // from class: io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttService$ServiceBinder$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$ensurePlatformConnection$0(i, str, str2, str3, str4, str5, str6, z);
                        }
                    });
                } else {
                    LogDebug.INSTANCE.log(str7 + StubApp.getString2("44885") + i + StubApp.getString2("44886"));
                    UnifiedMqttService.this.startMqttServiceForPlatform(i, str, str2, str3, str4, str5, str6, z);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$ensurePlatformConnection$0(int i, String str, String str2, String str3, String str4, String str5, String str6, boolean z) {
            UnifiedMqttService.this.startMqttServiceForPlatform(i, str, str2, str3, str4, str5, str6, z);
        }
    }
}
