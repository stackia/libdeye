package com.deye.activity.device.loop_fan.base;

import android.os.Bundle;
import com.deye.SubscribeDeviceStates;
import com.deye.thread_pool.DeviceHeartbeatManager;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.bean.LoopFanBean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoopFanControlPanelMqttAty.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010\b\u001a\u00020\u0005H\u0014J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0005H\u0014J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¨\u0006\u0013"}, d2 = {"Lcom/deye/activity/device/loop_fan/base/LoopFanControlPanelMqttAty;", "Lcom/deye/activity/device/loop_fan/base/LoopFanControlPanelUIAty;", "Lcom/deye/SubscribeDeviceStates$IDeviceStateListener;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDeviceDateStateUpdate", "deviceListBean", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "onDeviceOnlineStateUpdate", "online", "", "onResume", "setDeviceStateToPanel", "bean", "Lio/fogcloud/sdk/fog/bean/LoopFanBean;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public abstract class LoopFanControlPanelMqttAty extends LoopFanControlPanelUIAty implements SubscribeDeviceStates.IDeviceStateListener {
    @Override // com.deye.activity.device.loop_fan.base.LoopFanControlPanelUIAty, com.deye.activity.device.base.PublicConstantAty, com.deye.activity.device.base.BaseActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mDeviceListBean != null) {
            if (!this.mDeviceListBean.getOnline().booleanValue()) {
                setOffLineLayout(false);
            }
            if (this.mDeviceListBean.loopFanBean != null) {
                LoopFanBean loopFanBean = this.mDeviceListBean.loopFanBean;
                Intrinsics.checkNotNullExpressionValue(loopFanBean, "loopFanBean");
                setMLoopFanBean(loopFanBean);
                setDeviceStateToPanel(getMLoopFanBean());
            } else {
                Boolean online = this.mDeviceListBean.getOnline();
                Intrinsics.checkNotNullExpressionValue(online, "getOnline(...)");
                if (online.booleanValue()) {
                    showWaiting(getResources().getString(R.string.loading), true);
                }
            }
        }
        SubscribeDeviceStates.INSTANCE.getInstance().addDeviceUpdateListener(this);
    }

    private final void setDeviceStateToPanel(LoopFanBean bean) {
        setMLoopFanBean(bean);
        setView();
    }

    @Override // com.deye.SubscribeDeviceStates.IDeviceStateListener
    public void onDeviceDateStateUpdate(DeviceListBean deviceListBean) {
        Intrinsics.checkNotNullParameter(deviceListBean, "deviceListBean");
        if (Intrinsics.areEqual(deviceListBean.getDevice_id(), this.mDeviceId)) {
            stopWaiting();
            LoopFanBean loopFanBean = deviceListBean.loopFanBean;
            Intrinsics.checkNotNullExpressionValue(loopFanBean, "loopFanBean");
            setDeviceStateToPanel(loopFanBean);
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

    @Override // com.deye.activity.device.loop_fan.base.LoopFanControlPanelUIAty, com.deye.activity.device.base.BaseActivity
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
