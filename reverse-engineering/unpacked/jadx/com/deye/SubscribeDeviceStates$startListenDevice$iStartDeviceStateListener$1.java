package com.deye;

import com.deye.thread_pool.DeviceHeartbeatManager;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.DeYeModelType;
import io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager;
import io.fogcloud.sdk.fog.api.mqtt.IRegisterDeviceTopic;
import io.fogcloud.sdk.fog.api.mqtt.IStartDeviceStateListener;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.log.LogDebug;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SubscribeDeviceStates.kt */
@Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u001a\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u0007H\u0016J?\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u00052\u0016\u0010\f\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000e0\r\"\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"com/deye/SubscribeDeviceStates$startListenDevice$iStartDeviceStateListener$1", "Lio/fogcloud/sdk/fog/api/mqtt/IStartDeviceStateListener;", "onDeviceStatusReceived", "", "code", "", "messages", "", "platform", "onStartDeviceStateFail", "message", "onStartDeviceStateSuccess", "iRegisterDeviceTopic", "", "Lio/fogcloud/sdk/fog/api/mqtt/IRegisterDeviceTopic;", "(ILjava/lang/String;I[Lio/fogcloud/sdk/fog/api/mqtt/IRegisterDeviceTopic;)V", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class SubscribeDeviceStates$startListenDevice$iStartDeviceStateListener$1 implements IStartDeviceStateListener {
    final /* synthetic */ List<DeviceListBean> $deviceListBeanList;
    final /* synthetic */ boolean $hasFog;
    final /* synthetic */ boolean $hasOld;
    final /* synthetic */ SubscribeDeviceStates this$0;

    /* JADX WARN: Multi-variable type inference failed */
    SubscribeDeviceStates$startListenDevice$iStartDeviceStateListener$1(SubscribeDeviceStates subscribeDeviceStates, List<? extends DeviceListBean> list, boolean z, boolean z2) {
        this.this$0 = subscribeDeviceStates;
        this.$deviceListBeanList = list;
        this.$hasOld = z;
        this.$hasFog = z2;
    }

    @Override // io.fogcloud.sdk.fog.api.mqtt.IStartDeviceStateListener
    public void onDeviceStatusReceived(int code, String messages, int platform) {
        LogDebug.INSTANCE.log(StubApp.getString2(13153) + code + StubApp.getString2(626) + messages);
        LogDebug.INSTANCE.log(StubApp.getString2(13154) + this.this$0.currentDeviceList.size());
        this.this$0.updateBoundDeviceList(messages, platform, this.$deviceListBeanList);
    }

    @Override // io.fogcloud.sdk.fog.api.mqtt.IStartDeviceStateListener
    public void onStartDeviceStateFail(int code, String message) {
        LogDebug.INSTANCE.log(StubApp.getString2(13155) + code + StubApp.getString2(13156) + message);
        if (this.$hasOld) {
            this.this$0.isOldMqttConnected = false;
        }
        if (this.$hasFog) {
            this.this$0.isFogMqttConnected = false;
        }
    }

    @Override // io.fogcloud.sdk.fog.api.mqtt.IStartDeviceStateListener
    public void onStartDeviceStateSuccess(int code, String message, int platform, final IRegisterDeviceTopic... iRegisterDeviceTopic) {
        String string2 = StubApp.getString2(13157);
        Intrinsics.checkNotNullParameter(iRegisterDeviceTopic, "iRegisterDeviceTopic");
        LogDebug.INSTANCE.log(StubApp.getString2(13158) + code + StubApp.getString2(13159) + platform);
        int i = 0;
        LogDebug.INSTANCE.log(StubApp.getString2(13162) + iRegisterDeviceTopic.length + StubApp.getString2(13163) + ((iRegisterDeviceTopic.length == 0) ^ true ? iRegisterDeviceTopic[0] != null ? StubApp.getString2(13160) : StubApp.getString2(1720) : StubApp.getString2(13161)));
        LogDebug.INSTANCE.log(StubApp.getString2(13164) + this.this$0.currentDeviceList.size());
        if (platform == 1) {
            this.this$0.isOldMqttConnected = true;
            LogDebug.INSTANCE.log(StubApp.getString2(13166));
        } else if (platform == 2 || platform == 3) {
            this.this$0.isFogMqttConnected = true;
            LogDebug.INSTANCE.log(StubApp.getString2(13165));
        }
        String endpoint = DeYeMqttManager.getInstance().getEndpoint();
        SubscribeDeviceStates subscribeDeviceStates = this.this$0;
        synchronized (this) {
            LogDebug.INSTANCE.log(string2 + subscribeDeviceStates.currentDeviceList.size());
            for (Object obj : subscribeDeviceStates.currentDeviceList) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                final DeviceListBean deviceListBean = (DeviceListBean) obj;
                LogDebug.INSTANCE.log(StubApp.getString2("13167") + i + StubApp.getString2("13168") + deviceListBean.getDevice_id() + StubApp.getString2("13159") + deviceListBean.getPlatform());
                if (platform == 1 && deviceListBean.getPlatform() == 1) {
                    String strCloudTypeToLocalType = DeYeModelType.cloudTypeToLocalType(deviceListBean.getProduct_type());
                    DeviceHeartbeatManager deviceHeartbeatManager = DeviceHeartbeatManager.INSTANCE;
                    String device_id = deviceListBean.getDevice_id();
                    Intrinsics.checkNotNullExpressionValue(device_id, "getDevice_id(...)");
                    String product_id = deviceListBean.getProduct_id();
                    Intrinsics.checkNotNullExpressionValue(product_id, "getProduct_id(...)");
                    Intrinsics.checkNotNull(endpoint);
                    Intrinsics.checkNotNull(strCloudTypeToLocalType);
                    deviceHeartbeatManager.startHeartbeat(device_id, product_id, endpoint, strCloudTypeToLocalType, 10000L, true);
                }
                LogDebug.INSTANCE.log(StubApp.getString2("13169") + deviceListBean.getDevice_id() + StubApp.getString2("13170") + deviceListBean.getProduct_id());
                new Thread(new Runnable() { // from class: com.deye.SubscribeDeviceStates$startListenDevice$iStartDeviceStateListener$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        SubscribeDeviceStates$startListenDevice$iStartDeviceStateListener$1.onStartDeviceStateSuccess$lambda$2$lambda$1$lambda$0(iRegisterDeviceTopic, deviceListBean);
                    }
                }).start();
                i = i2;
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onStartDeviceStateSuccess$lambda$2$lambda$1$lambda$0(IRegisterDeviceTopic[] iRegisterDeviceTopic, DeviceListBean deviceListBean) {
        Intrinsics.checkNotNullParameter(iRegisterDeviceTopic, "$iRegisterDeviceTopic");
        Intrinsics.checkNotNullParameter(deviceListBean, "$deviceListBean");
        LogDebug.INSTANCE.log(StubApp.getString2(13151));
        IRegisterDeviceTopic iRegisterDeviceTopic2 = iRegisterDeviceTopic[0];
        if (iRegisterDeviceTopic2 != null) {
            iRegisterDeviceTopic2.onRegisterDeviceTopic(deviceListBean.getProduct_id(), deviceListBean.getDevice_id());
        }
        LogDebug.INSTANCE.log(StubApp.getString2(13152));
    }
}
