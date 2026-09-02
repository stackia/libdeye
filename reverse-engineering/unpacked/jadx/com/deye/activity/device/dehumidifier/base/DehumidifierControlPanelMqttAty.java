package com.deye.activity.device.dehumidifier.base;

import android.os.Bundle;
import com.deye.DeviceCacheManager;
import com.deye.FogDeviceManager;
import com.deye.SubscribeDeviceStates;
import com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelMqttAty;
import com.deye.thread_pool.DeviceHeartbeatManager;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.bean.PropertyResultBean;
import io.fogcloud.sdk.fog.helper.dehumidifier.Hex2DehumidifierBeanString;
import io.fogcloud.sdk.fog.log.LogDebug;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: DehumidifierControlPanelMqttAty.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010\b\u001a\u00020\u0005H\u0014J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0005H\u0014J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¨\u0006\u0013"}, d2 = {"Lcom/deye/activity/device/dehumidifier/base/DehumidifierControlPanelMqttAty;", "Lcom/deye/activity/device/dehumidifier/base/DehumidifierControlPanelUIAty;", "Lcom/deye/SubscribeDeviceStates$IDeviceStateListener;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDeviceDateStateUpdate", "deviceListBean", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "onDeviceOnlineStateUpdate", "online", "", "onResume", "setDeviceStateToPanel", "bean", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public abstract class DehumidifierControlPanelMqttAty extends DehumidifierControlPanelUIAty implements SubscribeDeviceStates.IDeviceStateListener {
    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty, com.deye.activity.device.base.PublicConstantAty, com.deye.activity.device.base.BaseActivity
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
                    setDehumidifierBean(deviceCache);
                    setDeviceStateToPanel(getDehumidifierBean());
                } else {
                    showWaiting(StubApp.getString2(13588));
                    FogDeviceManager fogDeviceManager = FogDeviceManager.INSTANCE;
                    String mDeviceId2 = this.mDeviceId;
                    Intrinsics.checkNotNullExpressionValue(mDeviceId2, "mDeviceId");
                    fogDeviceManager.startRequestProperties(mDeviceId2, new AnonymousClass1());
                }
            } else {
                DehumidifierBean dehumidfierBeanString = Hex2DehumidifierBeanString.getDehumidfierBeanString(this.mDeviceListBean.getPayload());
                if (dehumidfierBeanString != null) {
                    setDehumidifierBean(dehumidfierBeanString);
                    setDeviceStateToPanel(getDehumidifierBean());
                } else {
                    showWaiting(getResources().getString(R.string.loading), true);
                }
            }
        }
        SubscribeDeviceStates.INSTANCE.getInstance().addDeviceUpdateListener(this);
    }

    /* compiled from: DehumidifierControlPanelMqttAty.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "result", "Lio/fogcloud/sdk/fog/bean/PropertyResultBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelMqttAty$onCreate$1, reason: invalid class name */
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
                DehumidifierControlPanelMqttAty.this.setDehumidifierBean(FogDeviceManager.INSTANCE.convert(propertyResultBean));
                final DehumidifierControlPanelMqttAty dehumidifierControlPanelMqttAty = DehumidifierControlPanelMqttAty.this;
                dehumidifierControlPanelMqttAty.runOnUiThread(new Runnable() { // from class: com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelMqttAty$onCreate$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        DehumidifierControlPanelMqttAty.AnonymousClass1.invoke$lambda$0(dehumidifierControlPanelMqttAty);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(DehumidifierControlPanelMqttAty this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.stopWaiting();
            this$0.setView();
        }
    }

    private final void setDeviceStateToPanel(DehumidifierBean bean) {
        setDehumidifierBean(bean);
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

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty, com.deye.activity.device.base.BaseActivity
    protected void onResume() {
        super.onResume();
        if (this.mDeviceListBean == null || this.mDeviceId == null) {
            LogDebug.INSTANCE.log(StubApp.getString2(13589));
            return;
        }
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
