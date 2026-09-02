package io.fogcloud.sdk.fog.api.mqtt;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttService;
import io.fogcloud.sdk.fog.api.mqtt.sdk.helper.ComHelper;
import io.fogcloud.sdk.fog.api.mqtt.sdk.helper.MQTTErrCode;
import io.fogcloud.sdk.fog.api.mqtt.sdk.service.MqttServiceListener;
import io.fogcloud.sdk.fog.log.LogDebug;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class UnifiedMqttServiceAPI implements ServiceConnection {
    private IBinder binder;
    private String clientID;
    private final MqttConfig config;
    private String host;
    private boolean isencrypt;
    private boolean mqtttag = false;
    private boolean msgtag = false;
    private MqttServiceListener msl;
    private String passWord;
    private String port;
    private Intent serviceIntent;
    private String topic;
    private String userName;

    public UnifiedMqttServiceAPI(MqttConfig mqttConfig) {
        this.config = mqttConfig;
    }

    public void startMqttService(Context context, String str, String str2, String str3, String str4, String str5, String str6, boolean z, MqttServiceListener mqttServiceListener) {
        this.msl = mqttServiceListener;
        this.host = str;
        this.port = str2;
        this.userName = str3;
        this.passWord = str4;
        this.clientID = str5;
        this.topic = str6;
        this.isencrypt = z;
        if (ComHelper.checkPara(str, str3, str5)) {
            if (!this.mqtttag) {
                Intent intent = new Intent(context, (Class<?>) UnifiedMqttService.class);
                this.serviceIntent = intent;
                intent.putExtra(StubApp.getString2(44717), str);
                this.serviceIntent.putExtra(StubApp.getString2(44718), str2);
                this.serviceIntent.putExtra(StubApp.getString2(44719), str3);
                this.serviceIntent.putExtra(StubApp.getString2(44720), str4);
                this.serviceIntent.putExtra(StubApp.getString2(44721), str5);
                this.serviceIntent.putExtra(StubApp.getString2(44722), str6);
                this.serviceIntent.putExtra(StubApp.getString2(44723), z);
                this.serviceIntent.putExtra(StubApp.getString2(44968), this.config.getPlatformId());
                LogDebug.INSTANCE.log(StubApp.getString2(44982) + this.config.getLogPrefix() + StubApp.getString2(44983) + String.valueOf(Boolean.valueOf(context.bindService(this.serviceIntent, this, 1))));
                this.mqtttag = true;
                return;
            }
            LogDebug.INSTANCE.log(StubApp.getString2(44984) + this.config.getLogPrefix() + StubApp.getString2(44985));
            return;
        }
        this.msl.onMqttReceiver(MQTTErrCode.EMPTY_CODE, MQTTErrCode.EMPTY);
    }

    public void stopMqttService(Context context) {
        String string2 = StubApp.getString2(2566);
        if (this.mqtttag) {
            try {
                IBinder iBinder = this.binder;
                if (iBinder != null) {
                    try {
                        ((UnifiedMqttService.ServiceBinder) iBinder).stopPlatformConnection(this.config.getPlatformId());
                        LogDebug.INSTANCE.log(string2 + this.config.getLogPrefix() + StubApp.getString2("44986"));
                    } catch (Exception e) {
                        LogDebug.INSTANCE.log(string2 + this.config.getLogPrefix() + StubApp.getString2("44987") + e.getMessage());
                    }
                }
                context.unbindService(this);
                this.mqtttag = false;
                this.msgtag = false;
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            }
        }
    }

    public boolean isConnected() {
        IBinder iBinder;
        if (this.mqtttag && (iBinder = this.binder) != null) {
            try {
                return ((UnifiedMqttService.ServiceBinder) iBinder).isConnectedAPI(this.config.getPlatformId());
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public void reconnect() {
        IBinder iBinder;
        boolean z = this.mqtttag;
        String string2 = StubApp.getString2(2566);
        if (!z || (iBinder = this.binder) == null) {
            LogDebug.INSTANCE.log(string2 + this.config.getLogPrefix() + StubApp.getString2(44981));
            return;
        }
        try {
            ((UnifiedMqttService.ServiceBinder) iBinder).reconnectPlatform(this.config.getPlatformId());
        } catch (Exception e) {
            LogDebug.INSTANCE.log(string2 + this.config.getLogPrefix() + StubApp.getString2(44980) + e.getMessage());
        }
    }

    public void publishCommand(boolean z, long j, String str, String str2, int i, boolean z2) {
        IBinder iBinder;
        if (!this.mqtttag || (iBinder = this.binder) == null) {
            return;
        }
        ((UnifiedMqttService.ServiceBinder) iBinder).publishAPI(this.config.getPlatformId(), z, j, str, str2, i, z2);
    }

    public void publishSingleCommand(String str, byte[] bArr, int i, boolean z) {
        IBinder iBinder;
        if (!this.mqtttag || (iBinder = this.binder) == null) {
            return;
        }
        ((UnifiedMqttService.ServiceBinder) iBinder).publishAPISingle(this.config.getPlatformId(), str, bArr, i, z);
    }

    public void subscribe(String str, int i) {
        IBinder iBinder;
        if (!this.mqtttag || (iBinder = this.binder) == null) {
            return;
        }
        ((UnifiedMqttService.ServiceBinder) iBinder).addSubscribeAPI(this.config.getPlatformId(), str, i);
    }

    public void unsubscribe(String str) {
        IBinder iBinder;
        if (!this.mqtttag || (iBinder = this.binder) == null) {
            return;
        }
        ((UnifiedMqttService.ServiceBinder) iBinder).unSubscribeAPI(this.config.getPlatformId(), str);
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.binder = iBinder;
        LogDebug.INSTANCE.log(StubApp.getString2(2566) + this.config.getLogPrefix() + StubApp.getString2(44976) + this.config.getPlatformId() + StubApp.getString2(44977));
        ((UnifiedMqttService.ServiceBinder) this.binder).ensurePlatformConnection(this.config.getPlatformId(), this.host, this.port, this.userName, this.passWord, this.clientID, this.topic, this.isencrypt);
        if (this.msgtag) {
            return;
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttServiceAPI.1
            @Override // java.lang.Runnable
            public void run() {
                if (UnifiedMqttServiceAPI.this.binder != null) {
                    LogDebug.INSTANCE.log(StubApp.getString2(2566) + UnifiedMqttServiceAPI.this.config.getLogPrefix() + StubApp.getString2(44975));
                    ((UnifiedMqttService.ServiceBinder) UnifiedMqttServiceAPI.this.binder).bindListenerAPI(UnifiedMqttServiceAPI.this.config.getPlatformId(), new MqttServiceListener() { // from class: io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttServiceAPI.1.1
                        @Override // io.fogcloud.sdk.fog.api.mqtt.sdk.service.MqttServiceListener
                        public void onMqttReceiver(int i, String str) {
                            LogDebug.INSTANCE.log(StubApp.getString2(2566) + UnifiedMqttServiceAPI.this.config.getLogPrefix() + StubApp.getString2(44974) + i + StubApp.getString2(13233) + str);
                            if (UnifiedMqttServiceAPI.this.msl != null) {
                                UnifiedMqttServiceAPI.this.msl.onMqttReceiver(i, str);
                            }
                        }
                    });
                }
            }
        }, 200L);
        this.msgtag = true;
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        LogDebug logDebug = LogDebug.INSTANCE;
        String string2 = StubApp.getString2(2566);
        logDebug.log(string2 + this.config.getLogPrefix() + StubApp.getString2(44978));
        this.mqtttag = false;
        this.msgtag = false;
        this.binder = null;
        LogDebug.INSTANCE.log(string2 + this.config.getLogPrefix() + StubApp.getString2(44979));
    }

    public MqttConfig getConfig() {
        return this.config;
    }
}
