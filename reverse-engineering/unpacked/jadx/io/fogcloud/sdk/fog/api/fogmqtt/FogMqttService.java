package io.fogcloud.sdk.fog.api.fogmqtt;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.mqtt.sdk.helper.ComHelper;
import io.fogcloud.sdk.fog.api.mqtt.sdk.helper.MQTTErrCode;
import io.fogcloud.sdk.fog.api.mqtt.sdk.helper.MqttTimeStampHelper;
import io.fogcloud.sdk.fog.log.LogDebug;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

@Deprecated
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class FogMqttService extends Service {
    public static final int MESSAGE_ARRIVED = 1;
    public static final int MQTT_CONNECTED = 2;
    public static final int MQTT_CONNECT_FAILED = 3;
    private Handler handler;
    private MqttConnectOptions options;
    private ServiceBinder serviceBinder = new ServiceBinder();
    private MqttClient client = null;
    private ScheduledExecutorService scheduler = null;
    private FogMqttServiceListener mMqttServiceListener = null;
    private Boolean recvTag = false;
    private Boolean connectTag = false;
    private String[] topicList = null;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String stringExtra = intent.getStringExtra(StubApp.getString2(44717));
        String stringExtra2 = intent.getStringExtra(StubApp.getString2(44718));
        String stringExtra3 = intent.getStringExtra(StubApp.getString2(44719));
        String stringExtra4 = intent.getStringExtra(StubApp.getString2(44720));
        String stringExtra5 = intent.getStringExtra(StubApp.getString2(44721));
        String stringExtra6 = intent.getStringExtra(StubApp.getString2(44722));
        boolean booleanExtra = intent.getBooleanExtra(StubApp.getString2(44723), false);
        stopMqttService();
        startMqttService(stringExtra, stringExtra2, stringExtra3, stringExtra4, stringExtra5, stringExtra6, booleanExtra);
        return this.serviceBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.e(StubApp.getString2(44724), StubApp.getString2(7608));
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogDebug.INSTANCE.log(StubApp.getString2(44725) + hashCode());
        sendMsgToClient(MQTTErrCode._STOP_CODE, MQTTErrCode._STOP_MSG);
        stopMqttService();
    }

    public void startMqttService(String str, String str2, String str3, String str4, String str5, final String str6, boolean z) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        LogUtil.d(StubApp.getString2(44727) + str5);
        if (ComHelper.checkPara(str2)) {
            str = str + StubApp.getString2(669) + str2;
        }
        if (z) {
            mqttInit(StubApp.getString2(44728) + str, str3, str4, str5, true);
        } else {
            mqttInit(StubApp.getString2(44729) + str, str3, str4, str5, false);
        }
        this.handler = new Handler() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.FogMqttService.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 1) {
                    FogMqttService.this.sendMsgToClient(MQTTErrCode._PAYLOAD_CODE, (String) message.obj);
                    return;
                }
                if (message.what == 2) {
                    FogMqttService.this.connectTag = true;
                    if (ComHelper.checkPara(str6)) {
                        FogMqttService.this.reSubscribe(str6);
                    }
                    FogMqttService.this.sendMsgToClient(MQTTErrCode._CON_CODE, MQTTErrCode._CON_MSG);
                    return;
                }
                if (message.what == 3) {
                    FogMqttService.this.sendMsgToClient(MQTTErrCode._EXCEPTION_CODE, MQTTErrCode._EXCEP_MSG);
                }
            }
        };
        startReconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reSubscribe(String str) {
        if (!str.equals("") && this.topicList == null) {
            try {
                this.client.subscribe(str, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.topicList = new String[]{str};
        } else {
            for (String str2 : this.topicList) {
                try {
                    this.client.subscribe(str2, 0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        sendMsgToClient(MQTTErrCode._RESUB_CODE, MQTTErrCode._RESUB_MSG);
    }

    private void mqttInit(String str, String str2, String str3, String str4, boolean z) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        try {
            LogUtil.e(StubApp.getString2("44712"), str);
            LogUtil.e(StubApp.getString2("44713"), str2);
            LogUtil.e(StubApp.getString2("44714"), str3);
            LogUtil.e(StubApp.getString2("44715"), str4);
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
                this.client = new MqttClient(str, str4, new MemoryPersistence());
                MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
                this.options = mqttConnectOptions;
                mqttConnectOptions.setCleanSession(true);
                this.options.setUserName(str2);
                this.options.setPassword(str3.toCharArray());
                this.options.setConnectionTimeout(10);
                this.options.setHttpsHostnameVerificationEnabled(false);
                if (z) {
                    try {
                        try {
                            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(StubApp.getString2("24599"));
                            trustManagerFactory.init((KeyStore) null);
                            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                            SSLContext sSLContext = SSLContext.getInstance(StubApp.getString2("44716"));
                            sSLContext.init(null, trustManagers, null);
                            this.options.setSocketFactory(sSLContext.getSocketFactory());
                        } catch (KeyStoreException e) {
                            e.printStackTrace();
                        }
                    } catch (KeyManagementException e2) {
                        e2.printStackTrace();
                    } catch (NoSuchAlgorithmException e3) {
                        e3.printStackTrace();
                    }
                }
                this.client.setCallback(new MqttCallback() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.FogMqttService.2
                    public void connectionLost(Throwable th) {
                        LogUtil.w(StubApp.getString2(44709) + th.toString());
                        FogMqttService.this.connectTag = false;
                        FogMqttService.this.sendMsgToClient(MQTTErrCode._LOST_CODE, MQTTErrCode._LOST_MSG);
                    }

                    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                        FogMqttService.this.sendMsgToClient(MQTTErrCode._PUB_CODE, MQTTErrCode._PUB_MSG);
                    }

                    public void messageArrived(String str5, MqttMessage mqttMessage) throws Exception {
                        if (FogMqttService.this.recvTag.booleanValue()) {
                            Message message = new Message();
                            message.what = 1;
                            message.obj = StubApp.getString2(44710) + str5 + StubApp.getString2(44711) + mqttMessage.toString() + StubApp.getString2(14593);
                            FogMqttService.this.handler.sendMessage(message);
                        }
                    }
                });
            }
        } catch (MqttException e4) {
            e4.printStackTrace();
        }
    }

    public void subscribeService(String str, int i) {
        if (str.equals("")) {
            sendMsgToClient(MQTTErrCode._TOPIC_CODE, MQTTErrCode._TOPIC_MSG);
            return;
        }
        if (!this.connectTag.booleanValue()) {
            sendMsgToClient(MQTTErrCode._DISCON_CODE, MQTTErrCode._DISCON_MSG);
            return;
        }
        try {
            this.client.subscribe(str, i);
            int i2 = 0;
            if (this.topicList == null) {
                this.topicList = new String[0];
            }
            int length = this.topicList.length;
            String[] strArr = new String[length + 1];
            while (i2 < length) {
                strArr[i2] = this.topicList[i2];
                i2++;
            }
            strArr[i2] = str;
            this.topicList = strArr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendMsgToClient(MQTTErrCode._SUB_CODE, MQTTErrCode._SUB_MSG);
    }

    public void unSubscribeService(String str) {
        if (str.equals("")) {
            sendMsgToClient(MQTTErrCode._TOPIC_CODE, MQTTErrCode._TOPIC_MSG);
            return;
        }
        if (!this.connectTag.booleanValue()) {
            sendMsgToClient(MQTTErrCode._DISCON_CODE, MQTTErrCode._DISCON_MSG);
            return;
        }
        try {
            this.client.unsubscribe(str);
            int length = this.topicList.length;
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (str != this.topicList[i2]) {
                    i++;
                }
            }
            String[] strArr = new String[i];
            int i3 = 0;
            for (int i4 = 0; i4 < length; i4++) {
                String str2 = this.topicList[i4];
                if (str != str2) {
                    strArr[i3] = str2;
                    i3++;
                }
            }
            this.topicList = strArr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendMsgToClient(MQTTErrCode._UNSUB_CODE, MQTTErrCode._UNSUB_MSG);
    }

    private void startReconnect() {
        ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        this.scheduler = scheduledExecutorServiceNewSingleThreadScheduledExecutor;
        scheduledExecutorServiceNewSingleThreadScheduledExecutor.scheduleAtFixedRate(new Runnable() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.FogMqttService.3
            @Override // java.lang.Runnable
            public void run() {
                if (FogMqttService.this.client.isConnected()) {
                    return;
                }
                FogMqttService.this.connect();
            }
        }, 0L, 10000L, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connect() {
        new Thread(new Runnable() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.FogMqttService.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    FogMqttService.this.client.connect(FogMqttService.this.options);
                    Message message = new Message();
                    message.what = 2;
                    FogMqttService.this.handler.sendMessage(message);
                } catch (Exception unused) {
                    Message message2 = new Message();
                    message2.what = 3;
                    FogMqttService.this.handler.sendMessage(message2);
                }
            }
        }).start();
    }

    public void initMessageSend(FogMqttServiceListener fogMqttServiceListener) {
        this.mMqttServiceListener = fogMqttServiceListener;
        this.recvTag = true;
    }

    public void recvMsgService() {
        this.recvTag = true;
    }

    public void stopRecvMsgService() {
        this.recvTag = false;
    }

    public void publishService(boolean z, long j, String str, String str2, int i, boolean z2) {
        try {
            if (z) {
                byte[] bytes = str2.getBytes();
                int length = bytes.length;
                int i2 = length + 8;
                byte[] bArr = new byte[i2];
                for (int i3 = 0; i3 < length; i3++) {
                    bArr[i3] = bytes[i3];
                }
                byte[] bArrTime2byteArray = MqttTimeStampHelper.time2byteArray(j);
                for (int i4 = 0; i4 < bArrTime2byteArray.length; i4++) {
                    bArr[length + i4] = bArrTime2byteArray[i4];
                }
                StringBuffer stringBuffer = new StringBuffer();
                for (int i5 = 0; i5 < i2; i5++) {
                    stringBuffer.append(Integer.toHexString(bArr[i5] & 255) + StubApp.getString2("626"));
                }
                LogUtil.w(StubApp.getString2("13874"), StubApp.getString2("44726") + stringBuffer.toString());
                this.client.publish(str, bArr, i, z2);
                return;
            }
            this.client.publish(str, str2.getBytes(), i, z2);
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e2) {
            e2.printStackTrace();
        }
    }

    public void publishSingleService(String str, byte[] bArr, int i, boolean z) {
        try {
            this.client.publish(str, bArr, i, z);
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e2) {
            e2.printStackTrace();
        }
    }

    public void stopMqttService() {
        try {
            LogDebug.INSTANCE.log(StubApp.getString2("44730"));
            ScheduledExecutorService scheduledExecutorService = this.scheduler;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdown();
            }
            if (this.client != null && this.connectTag.booleanValue()) {
                this.client.disconnect();
            }
            MqttClient mqttClient = this.client;
            if (mqttClient != null) {
                mqttClient.close();
                this.client = null;
            }
            this.connectTag = false;
            this.recvTag = false;
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsgToClient(int i, String str) {
        FogMqttServiceListener fogMqttServiceListener = this.mMqttServiceListener;
        if (fogMqttServiceListener != null) {
            fogMqttServiceListener.onMqttReceiver(i, str);
        }
    }

    public class ServiceBinder extends Binder {
        public ServiceBinder() {
        }

        public FogMqttService getService() {
            return FogMqttService.this;
        }

        public void bindListenerAPI(FogMqttServiceListener fogMqttServiceListener) {
            FogMqttService.this.initMessageSend(fogMqttServiceListener);
        }

        public void stopRecvMsgAPI() {
            FogMqttService.this.stopRecvMsgService();
        }

        public void publishAPI(boolean z, long j, String str, String str2, int i, boolean z2) {
            FogMqttService.this.publishService(z, j, str, str2, i, z2);
        }

        public void publishAPISingle(String str, byte[] bArr, int i, boolean z) {
            FogMqttService.this.publishSingleService(str, bArr, i, z);
        }

        public void addSubscribeAPI(String str, int i) {
            FogMqttService.this.subscribeService(str, i);
        }

        public void unSubscribeAPI(String str) {
            FogMqttService.this.unSubscribeService(str);
        }

        public void recvMsgAPI() {
            FogMqttService.this.recvMsgService();
        }
    }
}
