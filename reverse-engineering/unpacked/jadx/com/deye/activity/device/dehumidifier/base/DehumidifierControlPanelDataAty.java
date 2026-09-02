package com.deye.activity.device.dehumidifier.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.alibaba.fastjson.JSON;
import com.amap.location.support.bean.location.AmapLocationNetwork;
import com.deye.CommandManger;
import com.deye.FogDeviceManager;
import com.deye.MxchipApplication;
import com.deye.configs.DeYeControlUtils;
import com.deye.helper.DialogHelper;
import com.deye.utils.BaseUtils;
import com.deye.utils.DeviceErrorShowTimer;
import com.deye.utils.MMKVUtils;
import com.deye.views.ArcPanel;
import com.deye.views.ItemControlView;
import com.deye.views.PartsView;
import com.deye.views.ViewExtendsKt;
import com.deye.views.WindSpeedParentView;
import com.deye.views.recycleview.DehumidifierModeView;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DehumidifierControlPanelDataAty.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0004J\u0018\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fH\u0002J\u0018\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fH\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\"\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\u0018\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0006H\u0016J\u0010\u0010$\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\"H\u0016J\u0012\u0010%\u001a\u00020\u00192\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\b\u0010(\u001a\u00020\u0019H\u0016J\b\u0010)\u001a\u00020\u0019H\u0016J\b\u0010*\u001a\u00020\u0019H\u0016J\b\u0010+\u001a\u00020\u0019H\u0014J\u0010\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u000bH\u0016J\b\u0010.\u001a\u00020\u0019H\u0014J\u0010\u0010/\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u000bH\u0016J\b\u00100\u001a\u00020\u0019H\u0002J\b\u00101\u001a\u00020\u0019H\u0014J\b\u00102\u001a\u00020\u0019H\u0016J\b\u00103\u001a\u00020\u0019H\u0002J\b\u00104\u001a\u00020\u0019H\u0014J\b\u00105\u001a\u00020\u0019H\u0014J\u0010\u00106\u001a\u00020\u00192\u0006\u00107\u001a\u00020\u001cH\u0002J\b\u00108\u001a\u00020\u0019H\u0002R\u0014\u0010\u0005\u001a\u00020\u00068DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00068DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0007R*\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/deye/activity/device/dehumidifier/base/DehumidifierControlPanelDataAty;", "Lcom/deye/activity/device/dehumidifier/base/DehumidifierControlPanelMqttAty;", "Lcom/deye/views/ItemControlView$OnCheckListener;", "Landroid/view/View$OnClickListener;", "()V", "isSatisfyPublicHumidityCondition", "", "()Z", "isSatisfyPublicWindSpeedCondition", "mDeviceErrorCode", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getMDeviceErrorCode", "()Ljava/util/ArrayList;", "setMDeviceErrorCode", "(Ljava/util/ArrayList;)V", "mDeviceErrorShowTimer", "Lcom/deye/utils/DeviceErrorShowTimer;", "belowThresholdReturnDefaultValue", "", "humidityValue", "getDisplayErrorTextList", "getErrorCodeList", "initPowerView", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onChecked", "v", "Landroid/view/View;", "isClicked", "onClick", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPowerClick", "onPowerOff", "onPowerOn", "onResume", "parseLocalData", "mode", "reInitControlPanelBean", "setContinueView", "setErrorShowState", "setOnClickListsner", "setUpDeviceStateDate", "setUpHeadLayout", "setUpPowerSpecialLogic", "setView", "updateBgByEnvHum", "hum", "updateDelayedShutdownStatus", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public abstract class DehumidifierControlPanelDataAty extends DehumidifierControlPanelMqttAty implements ItemControlView.OnCheckListener, View.OnClickListener {
    private ArrayList<String> mDeviceErrorCode = new ArrayList<>();
    private DeviceErrorShowTimer mDeviceErrorShowTimer;

    public void onPowerOff() {
    }

    public void onPowerOn() {
    }

    public void parseLocalData(String mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
    }

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected void reInitControlPanelBean() {
    }

    protected void setUpPowerSpecialLogic() {
    }

    protected final ArrayList<String> getMDeviceErrorCode() {
        return this.mDeviceErrorCode;
    }

    protected final void setMDeviceErrorCode(ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.mDeviceErrorCode = arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelMqttAty, com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty, com.deye.activity.device.base.PublicConstantAty, com.deye.activity.device.base.BaseActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MxchipApplication.getInstance().addActivity((Activity) this);
        if (this.mDeviceListBean == null || this.mDeviceListBean.getDevice_name() == null) {
            return;
        }
        getMBaseAtyUiBinding().actionbarBlack.actionbarTitle.setText(this.mDeviceListBean.getDevice_name());
    }

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelMqttAty, com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty, com.deye.activity.device.base.BaseActivity
    protected void onResume() {
        super.onResume();
        if (getMControlPanelBean().isHasPartsManager()) {
            PartsView partsView = getMBaseAtyUiBinding().partsView;
            String mDeviceId = this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(mDeviceId, "mDeviceId");
            String mProductId = this.mProductId;
            Intrinsics.checkNotNullExpressionValue(mProductId, "mProductId");
            partsView.reqDetail(mDeviceId, mProductId);
        }
    }

    protected final boolean isSatisfyPublicHumidityCondition() throws NumberFormatException {
        String current_env_temp = getDehumidifierBean().getCurrent_env_temp();
        Intrinsics.checkNotNullExpressionValue(current_env_temp, "getCurrent_env_temp(...)");
        Integer.parseInt(current_env_temp);
        return Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getDefrost_state()) && !Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_GPS, getDehumidifierBean().getMode());
    }

    protected final boolean isSatisfyPublicWindSpeedCondition() throws NumberFormatException {
        String current_env_temp = getDehumidifierBean().getCurrent_env_temp();
        Intrinsics.checkNotNullExpressionValue(current_env_temp, "getCurrent_env_temp(...)");
        Integer.parseInt(current_env_temp);
        return Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getDefrost_state());
    }

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected void setView() throws NumberFormatException {
        stopWaiting();
        this.mDeviceErrorCode = getErrorCodeList();
        initPowerView();
        setErrorShowState();
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getDefrost_state())) {
            getMBaseAtyUiBinding().speedParentView.setAvailable(true);
            getMBaseAtyUiBinding().mrvMode.setAvailable(true);
            ItemControlView controlSwingWindView = getMBaseAtyUiBinding().controlSwingWindView;
            Intrinsics.checkNotNullExpressionValue(controlSwingWindView, "controlSwingWindView");
            ViewExtendsKt.setAvailable(controlSwingWindView, true);
            ItemControlView controlContinueView = getMBaseAtyUiBinding().controlContinueView;
            Intrinsics.checkNotNullExpressionValue(controlContinueView, "controlContinueView");
            ViewExtendsKt.setAvailable(controlContinueView, true);
            ItemControlView controlLockView = getMBaseAtyUiBinding().controlLockView;
            Intrinsics.checkNotNullExpressionValue(controlLockView, "controlLockView");
            ViewExtendsKt.setAvailable(controlLockView, true);
            ItemControlView controlAnionView = getMBaseAtyUiBinding().controlAnionView;
            Intrinsics.checkNotNullExpressionValue(controlAnionView, "controlAnionView");
            ViewExtendsKt.setAvailable(controlAnionView, true);
            ItemControlView controlWaterPumpView = getMBaseAtyUiBinding().controlWaterPumpView;
            Intrinsics.checkNotNullExpressionValue(controlWaterPumpView, "controlWaterPumpView");
            ViewExtendsKt.setAvailable(controlWaterPumpView, true);
            ItemControlView controlUvLight = getMBaseAtyUiBinding().controlUvLight;
            Intrinsics.checkNotNullExpressionValue(controlUvLight, "controlUvLight");
            ViewExtendsKt.setAvailable(controlUvLight, true);
        }
        getMBaseAtyUiBinding().arcPanel.setCo(getMControlPanelBean().getHumidity().co);
        setUpHeadLayout();
        setUpDeviceStateDate();
        setUpCoverView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void initPowerView() {
        boolean zAreEqual = Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getSys_switch());
        String string2 = StubApp.getString2(13583);
        String string22 = StubApp.getString2(13584);
        String string23 = StubApp.getString2(13585);
        String string24 = StubApp.getString2(13586);
        if (zAreEqual) {
            getMBaseAtyUiBinding().ivPower.setImageDrawable(ContextCompat.getDrawable((Context) this, R.drawable.icon_power_on));
            getMBaseAtyUiBinding().tvPower.setText(getResources().getString(R.string.turned_on));
            getMBaseAtyUiBinding().mrvMode.setAvailable(true);
            ItemControlView itemControlView = getMBaseAtyUiBinding().controlSwingWindView;
            Intrinsics.checkNotNullExpressionValue(itemControlView, string24);
            ViewExtendsKt.setAvailable(itemControlView, true);
            ItemControlView itemControlView2 = getMBaseAtyUiBinding().controlAnionView;
            Intrinsics.checkNotNullExpressionValue(itemControlView2, string23);
            ViewExtendsKt.setAvailable(itemControlView2, true);
            ItemControlView itemControlView3 = getMBaseAtyUiBinding().controlWaterPumpView;
            Intrinsics.checkNotNullExpressionValue(itemControlView3, string22);
            ViewExtendsKt.setAvailable(itemControlView3, true);
            ItemControlView itemControlView4 = getMBaseAtyUiBinding().controlUvLight;
            Intrinsics.checkNotNullExpressionValue(itemControlView4, string2);
            ViewExtendsKt.setAvailable(itemControlView4, true);
            return;
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_GPS, getDehumidifierBean().getSys_switch())) {
            getMBaseAtyUiBinding().tvPower.setText(getResources().getString(R.string.turned_off));
            getMBaseAtyUiBinding().ivPower.setImageDrawable(ContextCompat.getDrawable((Context) this, R.drawable.icon_power_off));
            getMBaseAtyUiBinding().mrvMode.setAvailable(false);
            ItemControlView itemControlView5 = getMBaseAtyUiBinding().controlAnionView;
            Intrinsics.checkNotNullExpressionValue(itemControlView5, string23);
            ViewExtendsKt.setAvailable(itemControlView5, false);
            ItemControlView itemControlView6 = getMBaseAtyUiBinding().controlSwingWindView;
            Intrinsics.checkNotNullExpressionValue(itemControlView6, string24);
            ViewExtendsKt.setAvailable(itemControlView6, false);
            ItemControlView itemControlView7 = getMBaseAtyUiBinding().controlWaterPumpView;
            Intrinsics.checkNotNullExpressionValue(itemControlView7, string22);
            ViewExtendsKt.setAvailable(itemControlView7, false);
            ItemControlView itemControlView8 = getMBaseAtyUiBinding().controlUvLight;
            Intrinsics.checkNotNullExpressionValue(itemControlView8, string2);
            ViewExtendsKt.setAvailable(itemControlView8, false);
        }
    }

    public void setUpDeviceStateDate() {
        getMBaseAtyUiBinding().rlDeviceLoading.setVisibility(8);
        LogUtil.d(StubApp.getString2(13515) + JSON.toJSON(getDehumidifierBean()));
        String hum_set = getDehumidifierBean().getHum_set();
        Intrinsics.checkNotNullExpressionValue(hum_set, "getHum_set(...)");
        boolean z = Integer.parseInt(hum_set) == getMControlPanelBean().getHumidity().co;
        getMBaseAtyUiBinding().controlContinueView.setChecked(z);
        getMBaseAtyUiBinding().controlAnionView.setChecked(switchToBool(getDehumidifierBean().getAnion_switch()));
        getMBaseAtyUiBinding().controlLockView.setChecked(switchToBool(getDehumidifierBean().getLock_switch()));
        getMBaseAtyUiBinding().controlTone.setChecked(switchToBool(getDehumidifierBean().sound_switch));
        getMBaseAtyUiBinding().controlDisplay.setChecked(switchToBool(getDehumidifierBean().getScreendisplay()));
        getMBaseAtyUiBinding().controlWaterPumpView.setChecked(switchToBool(getDehumidifierBean().getPump_switch()));
        getMBaseAtyUiBinding().controlSwingWindView.setChecked(switchToBool(getDehumidifierBean().getWind_switch()));
        getMBaseAtyUiBinding().controlUvLight.setChecked(switchToBool(getDehumidifierBean().uvLight));
        String hum_set2 = getDehumidifierBean().getHum_set();
        Intrinsics.checkNotNullExpressionValue(hum_set2, "getHum_set(...)");
        LogUtil.d(StubApp.getString2(13002), StubApp.getString2(13587) + Float.parseFloat(hum_set2));
        WindSpeedParentView windSpeedParentView = getMBaseAtyUiBinding().speedParentView;
        String mProductId = this.mProductId;
        Intrinsics.checkNotNullExpressionValue(mProductId, "mProductId");
        windSpeedParentView.setDehumidifierBean(mProductId, getDehumidifierBean());
        getMBaseAtyUiBinding().mrvMode.setDehumidifierBean(getDehumidifierBean());
        String mode = getDehumidifierBean().getMode();
        Intrinsics.checkNotNullExpressionValue(mode, "getMode(...)");
        setContinueView(mode);
        getMBaseAtyUiBinding().arcPanel.setData(getDehumidifierBean(), z);
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getWatertank_state())) {
            showWaterTankDialog();
            getMBaseAtyUiBinding().llWaterFull.setVisibility(0);
        } else {
            dismissWaterTankDialog();
            getMBaseAtyUiBinding().llWaterFull.setVisibility(8);
        }
        String current_env_hum = getDehumidifierBean().getCurrent_env_hum();
        Intrinsics.checkNotNullExpressionValue(current_env_hum, "getCurrent_env_hum(...)");
        updateBgByEnvHum(Integer.parseInt(current_env_hum));
        updateDelayedShutdownStatus();
    }

    private final void updateDelayedShutdownStatus() {
        if (getMControlPanelBean().isHasDelayer()) {
            getMBaseAtyUiBinding().delayedShutdownControl.updateStatus(getDehumidifierBean());
        }
    }

    protected final float belowThresholdReturnDefaultValue(float humidityValue) {
        int min = getMControlPanelBean().getHumidity().getMin();
        int max = getMControlPanelBean().getHumidity().getMax();
        float f = min;
        if (humidityValue >= f) {
            f = max;
            if (humidityValue <= f) {
                return humidityValue;
            }
        }
        return f;
    }

    private final void setUpHeadLayout() throws NumberFormatException {
        String current_env_hum = getDehumidifierBean().getCurrent_env_hum();
        Intrinsics.checkNotNullExpressionValue(current_env_hum, "getCurrent_env_hum(...)");
        Integer.parseInt(current_env_hum);
    }

    private final void setErrorShowState() {
        ArrayList<String> displayErrorTextList = getDisplayErrorTextList();
        if (BaseUtils.isNotNull(displayErrorTextList) && displayErrorTextList.size() > 0) {
            getMBaseAtyUiBinding().llDeviceError.setVisibility(0);
            this.mErrorTextTempList.clear();
            int size = displayErrorTextList.size();
            for (int i = 0; i < size; i++) {
                if (!this.mErrorTextTempList.contains(displayErrorTextList.get(i))) {
                    this.mErrorTextTempList.add(displayErrorTextList.get(i));
                }
            }
            DeviceErrorShowTimer deviceErrorShowTimer = this.mDeviceErrorShowTimer;
            if (deviceErrorShowTimer == null) {
                DeviceErrorShowTimer deviceErrorShowTimer2 = new DeviceErrorShowTimer(getMBaseAtyUiBinding().ivDeviceError, getMBaseAtyUiBinding().tvDeviceError, 86400000L, 2000L, displayErrorTextList);
                this.mDeviceErrorShowTimer = deviceErrorShowTimer2;
                deviceErrorShowTimer2.start();
            } else if (deviceErrorShowTimer != null) {
                deviceErrorShowTimer.notifyList(this.mErrorTextTempList);
            }
        } else {
            this.mErrorTextTempList.clear();
            getMBaseAtyUiBinding().llDeviceError.setVisibility(8);
            if (BaseUtils.isNotNull(this.mDeviceErrorShowTimer)) {
                DeviceErrorShowTimer deviceErrorShowTimer3 = this.mDeviceErrorShowTimer;
                if (deviceErrorShowTimer3 != null) {
                    deviceErrorShowTimer3.cancel();
                }
                this.mDeviceErrorShowTimer = null;
            }
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getDefrost_state())) {
            getMBaseAtyUiBinding().llDefrosting.setVisibility(0);
        } else {
            getMBaseAtyUiBinding().llDefrosting.setVisibility(8);
        }
    }

    private final ArrayList<String> getDisplayErrorTextList() {
        ArrayList<String> arrayList = new ArrayList<>(this.mErrorTextList);
        if (Intrinsics.areEqual(this.mProductId, "190e746228e111f1b1340242ac480009")) {
            arrayList.removeAll(SetsKt.hashSetOf(DeYeControlUtils.getErrorText(StubApp.getString2(11410), this.mProductId), DeYeControlUtils.getErrorText(StubApp.getString2(11260), this.mProductId), DeYeControlUtils.getErrorText(StubApp.getString2(13502), this.mProductId)));
        }
        return arrayList;
    }

    @Override // com.deye.views.ItemControlView.OnCheckListener
    public void onChecked(View v, boolean isClicked) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (getDehumidifierBean() == null) {
            return;
        }
        int id = v.getId();
        int id2 = getMBaseAtyUiBinding().controlSwingWindView.getId();
        String string2 = StubApp.getString2(13535);
        String string22 = StubApp.getString2(13536);
        if (id == id2) {
            getDehumidifierBean().setWind_switch(switchToString(isClicked));
            CommandManger commandManger = CommandManger.INSTANCE;
            String str = this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str, string22);
            String str2 = this.mProductId;
            Intrinsics.checkNotNullExpressionValue(str2, string2);
            commandManger.sendWindSwitchCommand(str, str2, isClicked ? 1 : 0, this.mDeviceListBean.isFogPlatform(), this.mDeviceListBean.isUseComboProtocol(), getDehumidifierBean());
            return;
        }
        if (v.getId() == getMBaseAtyUiBinding().controlWaterPumpView.getId()) {
            getDehumidifierBean().setPump_switch(switchToString(isClicked));
            CommandManger commandManger2 = CommandManger.INSTANCE;
            String str3 = this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str3, string22);
            String str4 = this.mProductId;
            Intrinsics.checkNotNullExpressionValue(str4, string2);
            commandManger2.sendWaterPumpCommand(str3, str4, isClicked ? 1 : 0, this.mDeviceListBean.isFogPlatform(), this.mDeviceListBean.isUseComboProtocol(), getDehumidifierBean());
            return;
        }
        if (v.getId() == getMBaseAtyUiBinding().controlAnionView.getId()) {
            getDehumidifierBean().setAnion_switch(switchToString(isClicked));
            CommandManger commandManger3 = CommandManger.INSTANCE;
            String str5 = this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str5, string22);
            String str6 = this.mProductId;
            Intrinsics.checkNotNullExpressionValue(str6, string2);
            commandManger3.sendNegativeIonCommand(str5, str6, isClicked ? 1 : 0, this.mDeviceListBean.isFogPlatform(), this.mDeviceListBean.isUseComboProtocol(), getDehumidifierBean());
            return;
        }
        if (v.getId() == getMBaseAtyUiBinding().controlLockView.getId()) {
            getDehumidifierBean().setLock_switch(switchToString(isClicked));
            CommandManger commandManger4 = CommandManger.INSTANCE;
            String str7 = this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str7, string22);
            String str8 = this.mProductId;
            Intrinsics.checkNotNullExpressionValue(str8, string2);
            commandManger4.sendLockCommand(str7, str8, isClicked ? 1 : 0, this.mDeviceListBean.isFogPlatform(), this.mDeviceListBean.isUseComboProtocol(), getDehumidifierBean());
            return;
        }
        if (v.getId() == getMBaseAtyUiBinding().controlContinueView.getId()) {
            getDehumidifierBean().setMode(StubApp.getString2(701));
            int i = isClicked ? getMControlPanelBean().getHumidity().co : 50;
            MMKVUtils mMKVUtils = MMKVUtils.INSTANCE;
            String str9 = this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str9, string22);
            mMKVUtils.setY16HumSet(str9, i);
            getDehumidifierBean().setHum_set(String.valueOf(i));
            getMBaseAtyUiBinding().arcPanel.setData(getDehumidifierBean(), isClicked);
            CommandManger commandManger5 = CommandManger.INSTANCE;
            String str10 = this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str10, string22);
            String str11 = this.mProductId;
            Intrinsics.checkNotNullExpressionValue(str11, string2);
            commandManger5.sendHumidityCommand(str10, str11, i, this.mDeviceListBean.isFogPlatform(), this.mDeviceListBean.isUseComboProtocol(), getDehumidifierBean());
            return;
        }
        if (v.getId() == getMBaseAtyUiBinding().controlUvLight.getId()) {
            getDehumidifierBean().uvLight = switchToString(isClicked);
            FogDeviceManager fogDeviceManager = FogDeviceManager.INSTANCE;
            String str12 = this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str12, string22);
            fogDeviceManager.sendUvLightCommand(str12, isClicked ? 1 : 0, getDehumidifierBean());
            return;
        }
        if (v.getId() == getMBaseAtyUiBinding().controlDisplay.getId()) {
            getDehumidifierBean().setScreendisplay(switchToString(getMBaseAtyUiBinding().controlDisplay.isChecked()));
            FogDeviceManager.INSTANCE.sendDisPlayCommand(this.mDeviceId, getMBaseAtyUiBinding().controlDisplay.isChecked() ? 1 : 0);
        } else if (v.getId() == getMBaseAtyUiBinding().controlTone.getId()) {
            getDehumidifierBean().sound_switch = switchToString(getMBaseAtyUiBinding().controlTone.isChecked());
            FogDeviceManager fogDeviceManager2 = FogDeviceManager.INSTANCE;
            String str13 = this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str13, string22);
            fogDeviceManager2.sendToneCommand(str13, getMBaseAtyUiBinding().controlTone.isChecked() ? 1 : 0);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id == getMBaseAtyUiBinding().actionbarBlack.actionbarBack.getId()) {
            goBack();
            return;
        }
        if (id == getMBaseAtyUiBinding().actionbarBlack.actionBarRight.getId()) {
            goDeviceDetailsForResult();
        } else if (id == getMBaseAtyUiBinding().ivPower.getId()) {
            onPowerClick();
        } else if (id == getMBaseAtyUiBinding().llDeviceError.getId()) {
            goDeviceErrorDetails(getMControlPanelBean().getDeviceModel());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onPowerClick() {
        if (!BaseUtils.isNull(getDehumidifierBean()) && !BaseUtils.isNull(getDehumidifierBean().getSys_switch()) && Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_GPS, getDehumidifierBean().getSys_switch())) {
            DehumidifierBean dehumidifierBean = getDehumidifierBean();
            String string2 = StubApp.getString2(2546);
            dehumidifierBean.setSys_switch(string2);
            onPowerOn();
            getMBaseAtyUiBinding().ivPower.setImageDrawable(ContextCompat.getDrawable((Context) this, R.drawable.icon_power_on));
            getMBaseAtyUiBinding().tvPower.setText(R.string.turned_on);
            setUpPowerSpecialLogic();
            getDehumidifierBean().setPoweron_flag(string2);
            WindSpeedParentView windSpeedParentView = getMBaseAtyUiBinding().speedParentView;
            String mProductId = this.mProductId;
            Intrinsics.checkNotNullExpressionValue(mProductId, "mProductId");
            windSpeedParentView.setDehumidifierBean(mProductId, getDehumidifierBean());
            getMBaseAtyUiBinding().arcPanel.setData(getDehumidifierBean(), false);
            getMBaseAtyUiBinding().mrvMode.setDehumidifierBean(getDehumidifierBean());
            getMBaseAtyUiBinding().mrvMode.setAvailable(true);
            ItemControlView controlAnionView = getMBaseAtyUiBinding().controlAnionView;
            Intrinsics.checkNotNullExpressionValue(controlAnionView, "controlAnionView");
            ViewExtendsKt.setAvailable(controlAnionView, true);
            ItemControlView controlContinueView = getMBaseAtyUiBinding().controlContinueView;
            Intrinsics.checkNotNullExpressionValue(controlContinueView, "controlContinueView");
            ViewExtendsKt.setAvailable(controlContinueView, true);
            ItemControlView controlUvLight = getMBaseAtyUiBinding().controlUvLight;
            Intrinsics.checkNotNullExpressionValue(controlUvLight, "controlUvLight");
            ViewExtendsKt.setAvailable(controlUvLight, true);
            setUpCoverView();
            CommandManger commandManger = CommandManger.INSTANCE;
            String mDeviceId = this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(mDeviceId, "mDeviceId");
            String mProductId2 = this.mProductId;
            Intrinsics.checkNotNullExpressionValue(mProductId2, "mProductId");
            commandManger.sendPowerCommand(mDeviceId, mProductId2, 1, this.mDeviceListBean.isFogPlatform(), this.mDeviceListBean.isUseComboProtocol(), getDehumidifierBean(), (64 & 64) != 0 ? false : false);
            return;
        }
        DialogHelper.showPowerOffDialog(this, new DialogHelper.OnDialogListener() { // from class: com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty.onPowerClick.1
            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onSure(String text) {
                DehumidifierControlPanelDataAty.this.onPowerOff();
                DehumidifierBean dehumidifierBean2 = DehumidifierControlPanelDataAty.this.getDehumidifierBean();
                String string22 = StubApp.getString2(701);
                dehumidifierBean2.setSys_switch(string22);
                DehumidifierControlPanelDataAty.this.getMBaseAtyUiBinding().ivPower.setImageDrawable(ContextCompat.getDrawable((Context) DehumidifierControlPanelDataAty.this.mContext, R.drawable.icon_power_off));
                DehumidifierControlPanelDataAty.this.getMBaseAtyUiBinding().tvPower.setText(DehumidifierControlPanelDataAty.this.getString(R.string.turned_off));
                DehumidifierControlPanelDataAty.this.getDehumidifierBean().setPoweroff_flag(string22);
                WindSpeedParentView windSpeedParentView2 = DehumidifierControlPanelDataAty.this.getMBaseAtyUiBinding().speedParentView;
                String str = DehumidifierControlPanelDataAty.this.mProductId;
                Intrinsics.checkNotNullExpressionValue(str, "access$getMProductId$p$s53299382(...)");
                windSpeedParentView2.setDehumidifierBean(str, DehumidifierControlPanelDataAty.this.getDehumidifierBean());
                DehumidifierControlPanelDataAty.this.getMBaseAtyUiBinding().arcPanel.setData(DehumidifierControlPanelDataAty.this.getDehumidifierBean(), false);
                DehumidifierControlPanelDataAty.this.getMBaseAtyUiBinding().mrvMode.setDehumidifierBean(DehumidifierControlPanelDataAty.this.getDehumidifierBean());
                DehumidifierControlPanelDataAty.this.getMBaseAtyUiBinding().mrvMode.setAvailable(false);
                ItemControlView controlAnionView2 = DehumidifierControlPanelDataAty.this.getMBaseAtyUiBinding().controlAnionView;
                Intrinsics.checkNotNullExpressionValue(controlAnionView2, "controlAnionView");
                ViewExtendsKt.setAvailable(controlAnionView2, false);
                ItemControlView controlContinueView2 = DehumidifierControlPanelDataAty.this.getMBaseAtyUiBinding().controlContinueView;
                Intrinsics.checkNotNullExpressionValue(controlContinueView2, "controlContinueView");
                ViewExtendsKt.setAvailable(controlContinueView2, false);
                ItemControlView controlUvLight2 = DehumidifierControlPanelDataAty.this.getMBaseAtyUiBinding().controlUvLight;
                Intrinsics.checkNotNullExpressionValue(controlUvLight2, "controlUvLight");
                ViewExtendsKt.setAvailable(controlUvLight2, false);
                DehumidifierControlPanelDataAty.this.setUpCoverView();
                CommandManger commandManger2 = CommandManger.INSTANCE;
                String str2 = DehumidifierControlPanelDataAty.this.mDeviceId;
                Intrinsics.checkNotNullExpressionValue(str2, "access$getMDeviceId$p$s53299382(...)");
                String str3 = DehumidifierControlPanelDataAty.this.mProductId;
                Intrinsics.checkNotNullExpressionValue(str3, "access$getMProductId$p$s53299382(...)");
                commandManger2.sendPowerCommand(str2, str3, 0, DehumidifierControlPanelDataAty.this.mDeviceListBean.isFogPlatform(), DehumidifierControlPanelDataAty.this.mDeviceListBean.isUseComboProtocol(), DehumidifierControlPanelDataAty.this.getDehumidifierBean(), (64 & 64) != 0 ? false : false);
            }
        });
    }

    public void setContinueView(String mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        if (Intrinsics.areEqual(mode, AmapLocationNetwork.RESULT_TYPE_GPS) || Intrinsics.areEqual(mode, AmapLocationNetwork.RESULT_TYPE_SELF_LAT_LON)) {
            getMBaseAtyUiBinding().controlContinueView.setVisibility(0);
        } else {
            getMBaseAtyUiBinding().controlContinueView.setVisibility(8);
        }
        ItemControlView controlContinueView = getMBaseAtyUiBinding().controlContinueView;
        Intrinsics.checkNotNullExpressionValue(controlContinueView, "controlContinueView");
        ViewExtendsKt.setAvailable(controlContinueView, getDehumidifierBean().checkIsPowerOn());
        ItemControlView itemControlView = getMBaseAtyUiBinding().controlContinueView;
        String hum_set = getDehumidifierBean().getHum_set();
        Intrinsics.checkNotNullExpressionValue(hum_set, "getHum_set(...)");
        itemControlView.setChecked(Integer.parseInt(hum_set) == getMControlPanelBean().getHumidity().co);
    }

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected void setOnClickListsner() {
        DehumidifierControlPanelDataAty dehumidifierControlPanelDataAty = this;
        getMBaseAtyUiBinding().controlSwingWindView.setOnCheckListener(dehumidifierControlPanelDataAty);
        getMBaseAtyUiBinding().controlWaterPumpView.setOnCheckListener(dehumidifierControlPanelDataAty);
        getMBaseAtyUiBinding().controlAnionView.setOnCheckListener(dehumidifierControlPanelDataAty);
        getMBaseAtyUiBinding().controlLockView.setOnCheckListener(dehumidifierControlPanelDataAty);
        getMBaseAtyUiBinding().controlUvLight.setOnCheckListener(dehumidifierControlPanelDataAty);
        getMBaseAtyUiBinding().controlContinueView.setOnCheckListener(dehumidifierControlPanelDataAty);
        getMBaseAtyUiBinding().controlTone.setOnCheckListener(dehumidifierControlPanelDataAty);
        getMBaseAtyUiBinding().controlDisplay.setOnCheckListener(dehumidifierControlPanelDataAty);
        DehumidifierControlPanelDataAty dehumidifierControlPanelDataAty2 = this;
        getMBaseAtyUiBinding().actionbarBlack.actionbarBack.setOnClickListener(dehumidifierControlPanelDataAty2);
        getMBaseAtyUiBinding().actionbarBlack.actionBarRight.setOnClickListener(dehumidifierControlPanelDataAty2);
        getMBaseAtyUiBinding().ivPower.setOnClickListener(dehumidifierControlPanelDataAty2);
        getMBaseAtyUiBinding().llDeviceError.setOnClickListener(dehumidifierControlPanelDataAty2);
        getMBaseAtyUiBinding().partsView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DehumidifierControlPanelDataAty.setOnClickListsner$lambda$0(this.f$0, view);
            }
        });
        getMBaseAtyUiBinding().speedParentView.setOnClickItemListener(new WindSpeedParentView.IOnClickItemListener() { // from class: com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty.setOnClickListsner.2
            @Override // com.deye.views.WindSpeedParentView.IOnClickItemListener
            public void onOnClickItem(int position) throws NumberFormatException {
                String str = DehumidifierControlPanelDataAty.this.getMControlPanelBean().getSpeed().getValue()[position];
                Intrinsics.checkNotNullExpressionValue(str, "get(...)");
                int i = Integer.parseInt(str);
                boolean zAreEqual = Intrinsics.areEqual(DehumidifierControlPanelDataAty.this.getDehumidifierBean().getMode(), AmapLocationNetwork.RESULT_TYPE_GPS);
                String string2 = StubApp.getString2(13573);
                if (zAreEqual) {
                    MMKVUtils mMKVUtils = MMKVUtils.INSTANCE;
                    String str2 = DehumidifierControlPanelDataAty.this.mDeviceId;
                    Intrinsics.checkNotNullExpressionValue(str2, string2);
                    mMKVUtils.setY16Speed(str2, i);
                }
                DehumidifierControlPanelDataAty.this.getDehumidifierBean().setSpeed(DehumidifierControlPanelDataAty.this.getMControlPanelBean().getSpeed().getValue()[position]);
                CommandManger commandManger = CommandManger.INSTANCE;
                String str3 = DehumidifierControlPanelDataAty.this.mDeviceId;
                Intrinsics.checkNotNullExpressionValue(str3, string2);
                String str4 = DehumidifierControlPanelDataAty.this.mProductId;
                Intrinsics.checkNotNullExpressionValue(str4, "access$getMProductId$p$s53299382(...)");
                commandManger.sendSpeedCommand(str3, str4, i, DehumidifierControlPanelDataAty.this.mDeviceListBean.isFogPlatform(), DehumidifierControlPanelDataAty.this.mDeviceListBean.isUseComboProtocol(), DehumidifierControlPanelDataAty.this.getDehumidifierBean());
            }
        });
        getMBaseAtyUiBinding().mrvMode.setOnClickItemListener(new DehumidifierModeView.IOnClickItemListener() { // from class: com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty$$ExternalSyntheticLambda1
            @Override // com.deye.views.recycleview.DehumidifierModeView.IOnClickItemListener
            public final void onOnClickItem(int i) {
                DehumidifierControlPanelDataAty.setOnClickListsner$lambda$1(this.f$0, i);
            }
        });
        getMBaseAtyUiBinding().arcPanel.setHumidityChangeListener(new ArcPanel.OnHumidityChangeListener() { // from class: com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty.setOnClickListsner.4
            @Override // com.deye.views.ArcPanel.OnHumidityChangeListener
            public void onChange(int humidity) {
                DehumidifierControlPanelDataAty.this.getDehumidifierBean().setHum_set(new StringBuilder().append(humidity).toString());
                MMKVUtils mMKVUtils = MMKVUtils.INSTANCE;
                String str = DehumidifierControlPanelDataAty.this.mDeviceId;
                Intrinsics.checkNotNullExpressionValue(str, "access$getMDeviceId$p$s53299382(...)");
                mMKVUtils.setY16HumSet(str, humidity);
                if (humidity != DehumidifierControlPanelDataAty.this.getMControlPanelBean().getHumidity().co) {
                    DehumidifierControlPanelDataAty.this.getMBaseAtyUiBinding().controlContinueView.setChecked(false);
                } else {
                    DehumidifierControlPanelDataAty.this.getMBaseAtyUiBinding().controlContinueView.setChecked(true);
                }
                CommandManger commandManger = CommandManger.INSTANCE;
                String str2 = DehumidifierControlPanelDataAty.this.mDeviceId;
                Intrinsics.checkNotNullExpressionValue(str2, "access$getMDeviceId$p$s53299382(...)");
                String str3 = DehumidifierControlPanelDataAty.this.mProductId;
                Intrinsics.checkNotNullExpressionValue(str3, "access$getMProductId$p$s53299382(...)");
                commandManger.sendHumidityCommand(str2, str3, humidity, DehumidifierControlPanelDataAty.this.mDeviceListBean.isFogPlatform(), DehumidifierControlPanelDataAty.this.mDeviceListBean.isUseComboProtocol(), DehumidifierControlPanelDataAty.this.getDehumidifierBean());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void setOnClickListsner$lambda$0(DehumidifierControlPanelDataAty this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent((Context) this$0, (Class<?>) DehumidifierPartsManagerAty.class);
        intent.putExtra(StubApp.getString2(13055), this$0.mDeviceId);
        intent.putExtra(StubApp.getString2(13306), this$0.mProductId);
        this$0.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickListsner$lambda$1(DehumidifierControlPanelDataAty this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = this$0.getMControlPanelBean().getMode().getValue()[i];
        Intrinsics.checkNotNull(str);
        this$0.createModeResult(str);
        this$0.parseLocalData(str);
        this$0.setContinueView(str);
        this$0.getDehumidifierBean().setMode(str);
        this$0.setUpCoverView();
        ArcPanel arcPanel = this$0.getMBaseAtyUiBinding().arcPanel;
        DehumidifierBean dehumidifierBean = this$0.getDehumidifierBean();
        String hum_set = this$0.getDehumidifierBean().getHum_set();
        Intrinsics.checkNotNullExpressionValue(hum_set, "getHum_set(...)");
        arcPanel.setData(dehumidifierBean, Integer.parseInt(hum_set) == this$0.getMControlPanelBean().getHumidity().co);
        WindSpeedParentView windSpeedParentView = this$0.getMBaseAtyUiBinding().speedParentView;
        String mProductId = this$0.mProductId;
        Intrinsics.checkNotNullExpressionValue(mProductId, "mProductId");
        windSpeedParentView.setDehumidifierBean(mProductId, this$0.getDehumidifierBean());
        CommandManger commandManger = CommandManger.INSTANCE;
        String mDeviceId = this$0.mDeviceId;
        Intrinsics.checkNotNullExpressionValue(mDeviceId, "mDeviceId");
        String mProductId2 = this$0.mProductId;
        Intrinsics.checkNotNullExpressionValue(mProductId2, "mProductId");
        commandManger.sendModeCommand(mDeviceId, mProductId2, str, this$0.mDeviceListBean.isFogPlatform(), this$0.mDeviceListBean.isUseComboProtocol(), this$0.getDehumidifierBean());
    }

    private final void updateBgByEnvHum(int hum) {
        boolean zEquals = this.mProductId.equals(StubApp.getString2(13418));
        String string2 = StubApp.getString2(13518);
        String string22 = StubApp.getString2(13516);
        String string23 = StubApp.getString2(13517);
        if (zEquals) {
            if (hum < 40) {
                getMBaseAtyUiBinding().lottieAnimationView.setAnimation(string23);
                return;
            } else if (40 <= hum && hum < 71) {
                getMBaseAtyUiBinding().lottieAnimationView.setAnimation(string2);
                return;
            } else {
                getMBaseAtyUiBinding().lottieAnimationView.setAnimation(string22);
                return;
            }
        }
        if (hum < 51) {
            getMBaseAtyUiBinding().lottieAnimationView.setAnimation(string23);
        } else if (51 <= hum && hum < 66) {
            getMBaseAtyUiBinding().lottieAnimationView.setAnimation(string2);
        } else {
            getMBaseAtyUiBinding().lottieAnimationView.setAnimation(string22);
        }
    }

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected ArrayList<String> getErrorCodeList() {
        if (BaseUtils.isNotNull(this.mErrorTextList)) {
            this.mErrorTextList.clear();
        }
        if (BaseUtils.isNotNull(this.mErrorTextTempList)) {
            this.mErrorTextTempList.clear();
        }
        ArrayList<String> arrayList = new ArrayList<>();
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE0())) {
            String string2 = StubApp.getString2(13499);
            arrayList.add(string2);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string2, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE1())) {
            String string22 = StubApp.getString2(13522);
            arrayList.add(string22);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string22, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE2())) {
            String string23 = StubApp.getString2(13523);
            arrayList.add(string23);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string23, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE3())) {
            String string24 = StubApp.getString2(13524);
            arrayList.add(string24);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string24, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE4())) {
            String string25 = StubApp.getString2(13525);
            arrayList.add(string25);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string25, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE5())) {
            String string26 = StubApp.getString2(13500);
            arrayList.add(string26);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string26, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE6())) {
            String string27 = StubApp.getString2(13501);
            arrayList.add(string27);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string27, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE7())) {
            String string28 = StubApp.getString2(13574);
            arrayList.add(string28);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string28, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE8())) {
            String string29 = StubApp.getString2(13526);
            arrayList.add(string29);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string29, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE9())) {
            String string210 = StubApp.getString2(13575);
            arrayList.add(string210);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string210, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getEA())) {
            String string211 = StubApp.getString2(13576);
            arrayList.add(string211);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string211, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getEB())) {
            String string212 = StubApp.getString2(13577);
            arrayList.add(string212);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string212, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getEC())) {
            String string213 = StubApp.getString2(13578);
            arrayList.add(string213);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string213, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getEE())) {
            String string214 = StubApp.getString2(13579);
            arrayList.add(string214);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string214, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getF4())) {
            String string215 = StubApp.getString2(13530);
            arrayList.add(string215);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string215, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getF6())) {
            String string216 = StubApp.getString2(13532);
            arrayList.add(string216);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string216));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getL1())) {
            String string217 = StubApp.getString2(11411);
            arrayList.add(string217);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string217, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getL2())) {
            String string218 = StubApp.getString2(11410);
            arrayList.add(string218);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string218, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getL3())) {
            String string219 = StubApp.getString2(11260);
            arrayList.add(string219);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string219, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getL4())) {
            String string220 = StubApp.getString2(13502);
            arrayList.add(string220);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string220, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getL6())) {
            String string221 = StubApp.getString2(13580);
            arrayList.add(string221);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string221, this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getP0())) {
            arrayList.add(StubApp.getString2(13503));
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(StubApp.getString2(13503), this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getP1())) {
            arrayList.add(StubApp.getString2(13504));
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(StubApp.getString2(13504), this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getP2())) {
            arrayList.add(StubApp.getString2(13533));
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(StubApp.getString2(13533), this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getP3())) {
            arrayList.add(StubApp.getString2(13534));
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(StubApp.getString2(13534), this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getP4())) {
            arrayList.add(StubApp.getString2(13505));
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(StubApp.getString2(13505), this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getP5())) {
            arrayList.add(StubApp.getString2(13506));
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(StubApp.getString2(13506), this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getP6())) {
            arrayList.add(StubApp.getString2(13507));
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(StubApp.getString2(13507), this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getP7())) {
            arrayList.add(StubApp.getString2(13581));
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(StubApp.getString2(13581), this.mProductId));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getP8()) && !Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getWatertank_state())) {
            arrayList.add(StubApp.getString2(13582));
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(StubApp.getString2(13582), this.mProductId));
        }
        return arrayList;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            String stringExtra = data != null ? data.getStringExtra(StubApp.getString2(6888)) : null;
            this.mDeviceListBean.setDevice_name(stringExtra);
            getMBaseAtyUiBinding().actionbarBlack.actionbarTitle.setText(stringExtra);
        }
    }
}
