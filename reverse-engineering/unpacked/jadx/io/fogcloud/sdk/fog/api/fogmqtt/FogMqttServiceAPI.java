package io.fogcloud.sdk.fog.api.fogmqtt;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.fogmqtt.FogMqttService;
import io.fogcloud.sdk.fog.api.mqtt.sdk.helper.ComHelper;
import io.fogcloud.sdk.fog.api.mqtt.sdk.helper.MQTTErrCode;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.util.Random;

@Deprecated
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class FogMqttServiceAPI implements ServiceConnection {
    private FogMqttService.ServiceBinder binder;
    private boolean mqtttag = false;
    private boolean msgtag = false;
    private FogMqttServiceListener msl;
    private Intent serviceIntent;

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
    }

    public void startMqttService(Context context, String str, String str2, String str3, String str4, String str5, String str6, boolean z, FogMqttServiceListener fogMqttServiceListener) {
        this.msl = fogMqttServiceListener;
        if (ComHelper.checkPara(str, str3, str5)) {
            try {
                context.unbindService(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.mqtttag = false;
            Intent intent = new Intent(context, (Class<?>) FogMqttService.class);
            this.serviceIntent = intent;
            intent.putExtra(StubApp.getString2(44717), str);
            this.serviceIntent.putExtra(StubApp.getString2(44718), str2);
            this.serviceIntent.putExtra(StubApp.getString2(44719), str3);
            this.serviceIntent.putExtra(StubApp.getString2(44720), str4);
            this.serviceIntent.putExtra(StubApp.getString2(44721), str5);
            this.serviceIntent.putExtra(StubApp.getString2(44722), str6);
            this.serviceIntent.putExtra(StubApp.getString2(44723), z);
            this.serviceIntent.setType(new Random().nextInt() + "");
            LogUtil.e(StubApp.getString2(44731), String.valueOf(Boolean.valueOf(context.bindService(this.serviceIntent, this, 1))));
            this.mqtttag = true;
            return;
        }
        this.msl.onMqttReceiver(MQTTErrCode.EMPTY_CODE, MQTTErrCode.EMPTY);
    }

    public void stopMqttService(Context context) {
        if (this.mqtttag) {
            try {
                context.unbindService(this);
                this.mqtttag = false;
                this.msgtag = false;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    public void publishCommand(boolean z, long j, String str, String str2, int i, boolean z2) {
        if (this.mqtttag) {
            this.binder.publishAPI(z, j, str, str2, i, z2);
        }
    }

    public void publishSingleCommand(String str, byte[] bArr, int i, boolean z) {
        if (this.mqtttag) {
            this.binder.publishAPISingle(str, bArr, i, z);
        }
    }

    public void stopRecvMessage() {
        if (this.msgtag && this.mqtttag) {
            this.binder.stopRecvMsgAPI();
            this.msgtag = false;
        }
    }

    public void recvMessage() {
        if (this.msgtag || !this.mqtttag) {
            return;
        }
        this.binder.recvMsgAPI();
        this.msgtag = true;
    }

    public void subscribe(String str, int i) {
        if (this.mqtttag) {
            this.binder.addSubscribeAPI(str, i);
        }
    }

    public void unsubscribe(String str) {
        FogMqttService.ServiceBinder serviceBinder;
        if (!this.mqtttag || (serviceBinder = this.binder) == null) {
            return;
        }
        serviceBinder.unSubscribeAPI(str);
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        FogMqttService.ServiceBinder serviceBinder = (FogMqttService.ServiceBinder) iBinder;
        this.binder = serviceBinder;
        if (this.msgtag) {
            return;
        }
        serviceBinder.bindListenerAPI(new FogMqttServiceListener() { // from class: io.fogcloud.sdk.fog.api.fogmqtt.FogMqttServiceAPI.1
            @Override // io.fogcloud.sdk.fog.api.mqtt.sdk.service.MqttServiceListener
            public void onMqttReceiver(int i, String str) {
                if (FogMqttServiceAPI.this.msl != null) {
                    FogMqttServiceAPI.this.msl.onMqttReceiver(i, str);
                }
            }
        });
        this.msgtag = true;
    }
}
