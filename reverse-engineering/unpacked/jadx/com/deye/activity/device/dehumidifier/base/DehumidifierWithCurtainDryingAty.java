package com.deye.activity.device.dehumidifier.base;

import android.content.Context;
import androidx.core.content.ContextCompat;
import com.amap.location.support.bean.location.AmapLocationNetwork;
import com.deye.CommandManger;
import com.deye.helper.DialogHelper;
import com.deye.utils.LanUtils;
import com.deye.views.ItemControlView;
import com.deye.views.ViewExtendsKt;
import com.deye.views.WindSpeedParentView;
import com.mxchipapp.R;
import com.stub.StubApp;
import com.zhouyou.view.seekbar.SignUtils;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DehumidifierWithCurtainDryingAty.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0002J\b\u0010\b\u001a\u00020\u0004H\u0002J\b\u0010\t\u001a\u00020\u0004H\u0002J\b\u0010\n\u001a\u00020\u0004H\u0002J\b\u0010\u000b\u001a\u00020\u0004H\u0002J\b\u0010\f\u001a\u00020\u0004H\u0002J\b\u0010\r\u001a\u00020\u0004H\u0002¨\u0006\u000e"}, d2 = {"Lcom/deye/activity/device/dehumidifier/base/DehumidifierWithCurtainDryingAty;", "Lcom/deye/activity/device/dehumidifier/base/DehumidifierControlPanelDataAty;", "()V", "firstPowerOff", "", "initPowerView", "onPowerClick", "powerOffDeviceCompletely", "powerOnDevice", "updateControlAvailabilityOnPowerOff", "updateControlAvailabilityOnPowerOn", "updateUIAfterPowerChange", "updateViewToPowerOff", "updateViewToPowerOn", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public abstract class DehumidifierWithCurtainDryingAty extends DehumidifierControlPanelDataAty {
    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty
    public void initPowerView() {
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getSys_switch())) {
            updateViewToPowerOn();
            updateControlAvailabilityOnPowerOn();
        } else if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_GPS, getDehumidifierBean().getSys_switch())) {
            updateViewToPowerOff();
            updateControlAvailabilityOnPowerOff();
        }
    }

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty
    public void onPowerClick() {
        if (Intrinsics.areEqual(getDehumidifierBean().getSys_switch(), AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
            firstPowerOff();
            return;
        }
        boolean zContains = getMDeviceErrorCode().contains(StubApp.getString2(11410));
        boolean zContains2 = getMDeviceErrorCode().contains(StubApp.getString2(13524));
        if (Intrinsics.areEqual(getDehumidifierBean().getFan_switch(), AmapLocationNetwork.RESULT_TYPE_GPS) || zContains || zContains2) {
            powerOnDevice();
        } else {
            powerOffDeviceCompletely();
        }
    }

    private final void firstPowerOff() {
        DialogHelper.showPowerOffDialog(this, new DialogHelper.OnDialogListener() { // from class: com.deye.activity.device.dehumidifier.base.DehumidifierWithCurtainDryingAty.firstPowerOff.1
            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onSure(String text) {
                DehumidifierWithCurtainDryingAty.this.onPowerOff();
                DehumidifierBean dehumidifierBean = DehumidifierWithCurtainDryingAty.this.getDehumidifierBean();
                String string2 = StubApp.getString2(701);
                dehumidifierBean.setSys_switch(string2);
                DehumidifierWithCurtainDryingAty.this.getDehumidifierBean().setFan_switch(StubApp.getString2(2546));
                DehumidifierWithCurtainDryingAty.this.getDehumidifierBean().setPoweroff_flag(string2);
                DehumidifierWithCurtainDryingAty.this.updateUIAfterPowerChange();
                DehumidifierWithCurtainDryingAty.this.updateControlAvailabilityOnPowerOff();
                DehumidifierWithCurtainDryingAty.this.updateViewToPowerOff();
                DehumidifierWithCurtainDryingAty.this.setUpCoverView();
                CommandManger commandManger = CommandManger.INSTANCE;
                String str = DehumidifierWithCurtainDryingAty.this.mDeviceId;
                Intrinsics.checkNotNullExpressionValue(str, "access$getMDeviceId$p$s353220770(...)");
                String str2 = DehumidifierWithCurtainDryingAty.this.mProductId;
                Intrinsics.checkNotNullExpressionValue(str2, "access$getMProductId$p$s353220770(...)");
                commandManger.sendPowerCommand(str, str2, 0, DehumidifierWithCurtainDryingAty.this.mDeviceListBean.isFogPlatform(), DehumidifierWithCurtainDryingAty.this.mDeviceListBean.isUseComboProtocol(), DehumidifierWithCurtainDryingAty.this.getDehumidifierBean(), (64 & 64) != 0 ? false : false);
            }
        });
    }

    private final void powerOnDevice() {
        DehumidifierBean dehumidifierBean = getDehumidifierBean();
        String string2 = StubApp.getString2(2546);
        dehumidifierBean.setSys_switch(string2);
        getDehumidifierBean().setPoweron_flag(string2);
        updateUIAfterPowerChange();
        updateControlAvailabilityOnPowerOn();
        updateViewToPowerOn();
        setUpCoverView();
        CommandManger commandManger = CommandManger.INSTANCE;
        String mDeviceId = this.mDeviceId;
        Intrinsics.checkNotNullExpressionValue(mDeviceId, "mDeviceId");
        String mProductId = this.mProductId;
        Intrinsics.checkNotNullExpressionValue(mProductId, "mProductId");
        commandManger.sendPowerCommand(mDeviceId, mProductId, 1, this.mDeviceListBean.isFogPlatform(), this.mDeviceListBean.isUseComboProtocol(), getDehumidifierBean(), (64 & 64) != 0 ? false : false);
    }

    private final void powerOffDeviceCompletely() {
        DehumidifierBean dehumidifierBean = getDehumidifierBean();
        String string2 = StubApp.getString2(701);
        dehumidifierBean.setSys_switch(string2);
        getDehumidifierBean().setFan_switch(string2);
        getDehumidifierBean().setPoweroff_flag(string2);
        updateUIAfterPowerChange();
        updateControlAvailabilityOnPowerOff();
        updateViewToPowerOff();
        setUpCoverView();
        CommandManger commandManger = CommandManger.INSTANCE;
        String mDeviceId = this.mDeviceId;
        Intrinsics.checkNotNullExpressionValue(mDeviceId, "mDeviceId");
        String mProductId = this.mProductId;
        Intrinsics.checkNotNullExpressionValue(mProductId, "mProductId");
        commandManger.sendPowerCommand(mDeviceId, mProductId, 0, this.mDeviceListBean.isFogPlatform(), this.mDeviceListBean.isUseComboProtocol(), getDehumidifierBean(), (64 & 64) != 0 ? false : false);
    }

    private final void updateControlAvailabilityOnPowerOn() {
        getMBaseAtyUiBinding().mrvMode.setAvailable(true);
        ItemControlView controlSwingWindView = getMBaseAtyUiBinding().controlSwingWindView;
        Intrinsics.checkNotNullExpressionValue(controlSwingWindView, "controlSwingWindView");
        ViewExtendsKt.setAvailable(controlSwingWindView, true);
        ItemControlView controlWaterPumpView = getMBaseAtyUiBinding().controlWaterPumpView;
        Intrinsics.checkNotNullExpressionValue(controlWaterPumpView, "controlWaterPumpView");
        ViewExtendsKt.setAvailable(controlWaterPumpView, true);
        ItemControlView controlUvLight = getMBaseAtyUiBinding().controlUvLight;
        Intrinsics.checkNotNullExpressionValue(controlUvLight, "controlUvLight");
        ViewExtendsKt.setAvailable(controlUvLight, true);
        ItemControlView controlAnionView = getMBaseAtyUiBinding().controlAnionView;
        Intrinsics.checkNotNullExpressionValue(controlAnionView, "controlAnionView");
        ViewExtendsKt.setAvailable(controlAnionView, true);
        ItemControlView controlDisplay = getMBaseAtyUiBinding().controlDisplay;
        Intrinsics.checkNotNullExpressionValue(controlDisplay, "controlDisplay");
        ViewExtendsKt.setAvailable(controlDisplay, true);
        ItemControlView controlTone = getMBaseAtyUiBinding().controlTone;
        Intrinsics.checkNotNullExpressionValue(controlTone, "controlTone");
        ViewExtendsKt.setAvailable(controlTone, true);
        ItemControlView controlContinueView = getMBaseAtyUiBinding().controlContinueView;
        Intrinsics.checkNotNullExpressionValue(controlContinueView, "controlContinueView");
        ViewExtendsKt.setAvailable(controlContinueView, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateControlAvailabilityOnPowerOff() {
        getMBaseAtyUiBinding().mrvMode.setAvailable(false);
        ItemControlView controlSwingWindView = getMBaseAtyUiBinding().controlSwingWindView;
        Intrinsics.checkNotNullExpressionValue(controlSwingWindView, "controlSwingWindView");
        ViewExtendsKt.setAvailable(controlSwingWindView, false);
        ItemControlView controlWaterPumpView = getMBaseAtyUiBinding().controlWaterPumpView;
        Intrinsics.checkNotNullExpressionValue(controlWaterPumpView, "controlWaterPumpView");
        ViewExtendsKt.setAvailable(controlWaterPumpView, false);
        ItemControlView controlUvLight = getMBaseAtyUiBinding().controlUvLight;
        Intrinsics.checkNotNullExpressionValue(controlUvLight, "controlUvLight");
        ViewExtendsKt.setAvailable(controlUvLight, false);
        ItemControlView controlAnionView = getMBaseAtyUiBinding().controlAnionView;
        Intrinsics.checkNotNullExpressionValue(controlAnionView, "controlAnionView");
        ViewExtendsKt.setAvailable(controlAnionView, false);
        ItemControlView controlDisplay = getMBaseAtyUiBinding().controlDisplay;
        Intrinsics.checkNotNullExpressionValue(controlDisplay, "controlDisplay");
        ViewExtendsKt.setAvailable(controlDisplay, false);
        ItemControlView controlTone = getMBaseAtyUiBinding().controlTone;
        Intrinsics.checkNotNullExpressionValue(controlTone, "controlTone");
        ViewExtendsKt.setAvailable(controlTone, false);
        ItemControlView controlContinueView = getMBaseAtyUiBinding().controlContinueView;
        Intrinsics.checkNotNullExpressionValue(controlContinueView, "controlContinueView");
        ViewExtendsKt.setAvailable(controlContinueView, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateUIAfterPowerChange() {
        WindSpeedParentView windSpeedParentView = getMBaseAtyUiBinding().speedParentView;
        String mProductId = this.mProductId;
        Intrinsics.checkNotNullExpressionValue(mProductId, "mProductId");
        windSpeedParentView.setDehumidifierBean(mProductId, getDehumidifierBean());
        getMBaseAtyUiBinding().arcPanel.setData(getDehumidifierBean(), Intrinsics.areEqual(getDehumidifierBean().getHum_set(), String.valueOf(getMControlPanelBean().getHumidity().co)));
        getMBaseAtyUiBinding().mrvMode.setDehumidifierBean(getDehumidifierBean());
    }

    private final void updateViewToPowerOn() {
        getMBaseAtyUiBinding().tvPower.setText(this.mContext.getString(R.string.turned_on));
        getMBaseAtyUiBinding().ivPower.setPadding(0, 0, 0, 0);
        getMBaseAtyUiBinding().ivPower.setImageDrawable(ContextCompat.getDrawable((Context) this.mContext, R.drawable.icon_power_on));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateViewToPowerOff() {
        boolean zContains = getMDeviceErrorCode().contains(StubApp.getString2(11410));
        boolean zAreEqual = Intrinsics.areEqual(getDehumidifierBean().getE3(), AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY);
        if (Intrinsics.areEqual(getDehumidifierBean().getFan_switch(), AmapLocationNetwork.RESULT_TYPE_GPS) || zContains || zAreEqual) {
            getMBaseAtyUiBinding().ivPower.setPadding(0, 0, 0, 0);
            getMBaseAtyUiBinding().tvPower.setText(this.mContext.getString(R.string.turned_off));
            getMBaseAtyUiBinding().ivPower.setImageDrawable(ContextCompat.getDrawable((Context) this.mContext, R.drawable.icon_power_off));
        } else {
            getMBaseAtyUiBinding().ivPower.setPadding(0, SignUtils.dp2px(10), 0, 0);
            getMBaseAtyUiBinding().tvPower.setText(this.mContext.getString(R.string.device_curtain_drying));
            getMBaseAtyUiBinding().ivPower.setImageDrawable(ContextCompat.getDrawable((Context) this.mContext, LanUtils.isEnLanguage() ? R.drawable.icon_poweroff_now_en : R.drawable.icon_poweroff_now));
        }
    }
}
