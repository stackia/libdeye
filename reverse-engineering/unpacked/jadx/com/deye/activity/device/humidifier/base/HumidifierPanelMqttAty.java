package com.deye.activity.device.humidifier.base;

import android.os.Bundle;
import com.deye.DeviceCacheManager;
import com.deye.FogDeviceManager;
import com.deye.SubscribeDeviceStates;
import com.deye.activity.device.humidifier.base.HumidifierPanelMqttAty;
import com.deye.thread_pool.DeviceHeartbeatManager;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.bean.PropertyResultBean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: HumidifierPanelMqttAty.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010\b\u001a\u00020\u0005H\u0014J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0005H\u0014J\u0006\u0010\u0010\u001a\u00020\u0005J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0002¨\u0006\u0014"}, d2 = {"Lcom/deye/activity/device/humidifier/base/HumidifierPanelMqttAty;", "Lcom/deye/activity/device/humidifier/base/HumidifierPanelUIAty;", "Lcom/deye/SubscribeDeviceStates$IDeviceStateListener;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDeviceDateStateUpdate", "deviceListBean", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "onDeviceOnlineStateUpdate", "online", "", "onResume", "refresh", "setDeviceStateToPanel", "bean", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public abstract class HumidifierPanelMqttAty extends HumidifierPanelUIAty implements SubscribeDeviceStates.IDeviceStateListener {
    @Override // com.deye.activity.device.humidifier.base.HumidifierPanelUIAty, com.deye.activity.device.base.PublicConstantAty, com.deye.activity.device.base.BaseActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mDeviceListBean != null) {
            if (!this.mDeviceListBean.getOnline().booleanValue()) {
                setOffLineLayout(false);
            }
            if (this.mDeviceListBean.isFogPlatform()) {
                DeviceCacheManager deviceCacheManager = DeviceCacheManager.INSTANCE;
                String mDeviceId = this.mDeviceId;
                Intrinsics.checkNotNullExpressionValue(mDeviceId, "mDeviceId");
                DehumidifierBean deviceCache = deviceCacheManager.getDeviceCache(mDeviceId, false);
                if (deviceCache != null) {
                    setHumidifierBean(deviceCache);
                    setDeviceStateToPanel(getHumidifierBean());
                } else {
                    showWaiting("");
                    refresh();
                }
            }
        }
        SubscribeDeviceStates.INSTANCE.getInstance().addDeviceUpdateListener(this);
    }

    /* compiled from: HumidifierPanelMqttAty.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "result", "Lio/fogcloud/sdk/fog/bean/PropertyResultBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.device.humidifier.base.HumidifierPanelMqttAty$refresh$1, reason: invalid class name */
    static final class AnonymousClass1 extends Lambda implements Function1<PropertyResultBean, Unit> {
        AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(PropertyResultBean propertyResultBean) {
            invoke2(propertyResultBean);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(PropertyResultBean propertyResultBean) {
            if (propertyResultBean != null) {
                HumidifierPanelMqttAty.this.setHumidifierBean(FogDeviceManager.INSTANCE.convert(propertyResultBean));
                final HumidifierPanelMqttAty humidifierPanelMqttAty = HumidifierPanelMqttAty.this;
                humidifierPanelMqttAty.runOnUiThread(new Runnable() { // from class: com.deye.activity.device.humidifier.base.HumidifierPanelMqttAty$refresh$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        HumidifierPanelMqttAty.AnonymousClass1.invoke$lambda$0(humidifierPanelMqttAty);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(HumidifierPanelMqttAty this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.stopWaiting();
            this$0.setView();
        }
    }

    public final void refresh() {
        FogDeviceManager fogDeviceManager = FogDeviceManager.INSTANCE;
        String mDeviceId = this.mDeviceId;
        Intrinsics.checkNotNullExpressionValue(mDeviceId, "mDeviceId");
        fogDeviceManager.startRequestProperties(mDeviceId, new AnonymousClass1());
    }

    private final void setDeviceStateToPanel(DehumidifierBean bean) {
        setHumidifierBean(bean);
        setView();
    }

    @Override // com.deye.SubscribeDeviceStates.IDeviceStateListener
    public void onDeviceDateStateUpdate(DeviceListBean deviceListBean) {
        Intrinsics.checkNotNullParameter(deviceListBean, "deviceListBean");
        if (Intrinsics.areEqual(deviceListBean.getDevice_id(), this.mDeviceId)) {
            stopWaiting();
            DehumidifierBean dehumidifierBean = deviceListBean.getDehumidifierBean();
            Intrinsics.checkNotNullExpressionValue(dehumidifierBean, "getDehumidifierBean(...)");
            setDeviceStateToPanel(dehumidifierBean);
        }
    }

    @Override // com.deye.SubscribeDeviceStates.IDeviceStateListener
    public void onDeviceOnlineStateUpdate(DeviceListBean deviceListBean, boolean online) {
        Intrinsics.checkNotNullParameter(deviceListBean, "deviceListBean");
        if (Intrinsics.areEqual(deviceListBean.getDevice_id(), this.mDeviceId)) {
            setOffLineLayout(online);
        }
    }

    @Override // com.deye.activity.device.base.BaseActivity
    protected void onDestroy() {
        super.onDestroy();
        SubscribeDeviceStates.INSTANCE.getInstance().removeDeviceUpdateListener(this);
    }

    @Override // com.deye.activity.device.humidifier.base.HumidifierPanelUIAty, com.deye.activity.device.base.BaseActivity
    protected void onResume() {
        super.onResume();
        boolean zIsFogPlatform = this.mDeviceListBean.isFogPlatform();
        String string2 = StubApp.getString2(13536);
        if (zIsFogPlatform) {
            DeviceHeartbeatManager deviceHeartbeatManager = DeviceHeartbeatManager.INSTANCE;
            String str = this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str, string2);
            String device_name = this.mDeviceListBean.getDevice_name();
            if (device_name == null) {
                device_name = StubApp.getString2(13539);
            }
            DeviceHeartbeatManager.startFogPolling$default(deviceHeartbeatManager, str, device_name, 0L, false, 12, null);
        } else {
            DeviceHeartbeatManager deviceHeartbeatManager2 = DeviceHeartbeatManager.INSTANCE;
            String str2 = this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str2, string2);
            deviceHeartbeatManager2.resumeDevice(str2);
        }
        DeviceHeartbeatManager deviceHeartbeatManager3 = DeviceHeartbeatManager.INSTANCE;
        String str3 = this.mDeviceId;
        Intrinsics.checkNotNullExpressionValue(str3, string2);
        deviceHeartbeatManager3.pauseAllExcept(str3);
    }
}
