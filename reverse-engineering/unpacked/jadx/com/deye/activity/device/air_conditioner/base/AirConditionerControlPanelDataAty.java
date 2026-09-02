package com.deye.activity.device.air_conditioner.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.alibaba.fastjson.JSON;
import com.amap.location.support.bean.location.AmapLocationNetwork;
import com.deye.CommandManger;
import com.deye.MxchipApplication;
import com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelDataAty;
import com.deye.configs.DeYeControlUtils;
import com.deye.helper.DialogHelper;
import com.deye.utils.BaseUtils;
import com.deye.utils.DeviceErrorShowTimer;
import com.deye.views.AirConditionerArcPanel;
import com.deye.views.AirWindSpeedParentView;
import com.deye.views.ViewExtendsKt;
import com.deye.views.button.SwitchButton;
import com.deye.views.recycleview.DehumidifierModeView;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AirConditionerControlPanelDataAty.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\"\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0012\u0010\u001e\u001a\u00020\u00122\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010!\u001a\u00020\u0012H\u0002J\b\u0010\"\u001a\u00020\u0012H\u0014J\b\u0010#\u001a\u00020\u0012H\u0002J\b\u0010$\u001a\u00020\u0012H\u0014J\b\u0010%\u001a\u00020\u0012H\u0014J\u0010\u0010&\u001a\u00020\u00122\u0006\u0010'\u001a\u00020\u0007H\u0002R*\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/deye/activity/device/air_conditioner/base/AirConditionerControlPanelDataAty;", "Lcom/deye/activity/device/air_conditioner/base/AirConditionerControlPanelMqttAty;", "Lcom/deye/views/button/SwitchButton$OnClick;", "Landroid/view/View$OnClickListener;", "()V", "mDeviceErrorCode", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getMDeviceErrorCode", "()Ljava/util/ArrayList;", "setMDeviceErrorCode", "(Ljava/util/ArrayList;)V", "mDeviceErrorShowTimer", "Lcom/deye/utils/DeviceErrorShowTimer;", "outdoorTemp", "getErrorCodeList", "initView", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "isClicked", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setErrorShowState", "setOnClickListsner", "setUpDeviceStateDate", "setUpPowerSpecialLogic", "setView", "updateBgByMode", "mode", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public abstract class AirConditionerControlPanelDataAty extends AirConditionerControlPanelMqttAty implements SwitchButton.OnClick, View.OnClickListener {
    private ArrayList<String> mDeviceErrorCode = new ArrayList<>();
    private DeviceErrorShowTimer mDeviceErrorShowTimer;
    private String outdoorTemp;

    @Override // com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelUIAty
    protected void initView() {
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
    @Override // com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelMqttAty, com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelUIAty, com.deye.activity.device.base.PublicConstantAty, com.deye.activity.device.base.BaseActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MxchipApplication.getInstance().addActivity((Activity) this);
        if (this.mDeviceListBean != null && this.mDeviceListBean.getDevice_name() != null) {
            getMBaseAtyUiBinding().actionbarBlack.actionbarTitle.setText(this.mDeviceListBean.getDevice_name());
        }
        String stringExtra = getIntent().getStringExtra(StubApp.getString2(13537));
        this.outdoorTemp = stringExtra;
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        AirConditionerArcPanel airConditionerArcPanel = getMBaseAtyUiBinding().arcPanel;
        String str = this.outdoorTemp;
        Intrinsics.checkNotNull(str);
        airConditionerArcPanel.setOutDoorTemp(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelUIAty
    protected void setView() {
        stopWaiting();
        boolean zAreEqual = Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getSys_switch());
        String string2 = StubApp.getString2(13538);
        String string22 = StubApp.getString2(13511);
        String string23 = StubApp.getString2(13512);
        String string24 = StubApp.getString2(13514);
        String string25 = StubApp.getString2(13513);
        if (zAreEqual) {
            getMBaseAtyUiBinding().ivPower.setImageDrawable(ContextCompat.getDrawable((Context) this, R.drawable.icon_power_on));
            getMBaseAtyUiBinding().tvPower.setText(getString(R.string.turned_on));
            getMBaseAtyUiBinding().mrvMode.setAvailable(true);
            RelativeLayout relativeLayout = getMBaseAtyUiBinding().rlSwingWind;
            Intrinsics.checkNotNullExpressionValue(relativeLayout, string25);
            ViewExtendsKt.setAvailable(relativeLayout, true);
            RelativeLayout relativeLayout2 = getMBaseAtyUiBinding().rlAnion;
            Intrinsics.checkNotNullExpressionValue(relativeLayout2, string24);
            ViewExtendsKt.setAvailable(relativeLayout2, true);
            RelativeLayout relativeLayout3 = getMBaseAtyUiBinding().rlWaterPump;
            Intrinsics.checkNotNullExpressionValue(relativeLayout3, string23);
            ViewExtendsKt.setAvailable(relativeLayout3, true);
            RelativeLayout relativeLayout4 = getMBaseAtyUiBinding().rlSleep;
            Intrinsics.checkNotNullExpressionValue(relativeLayout4, string22);
            ViewExtendsKt.setAvailable(relativeLayout4, true);
            RelativeLayout relativeLayout5 = getMBaseAtyUiBinding().rlLock;
            Intrinsics.checkNotNullExpressionValue(relativeLayout5, string2);
            ViewExtendsKt.setAvailable(relativeLayout5, true);
            setUpCoverView();
        } else if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_GPS, getDehumidifierBean().getSys_switch())) {
            getMBaseAtyUiBinding().tvPower.setText(getString(R.string.turned_off));
            getMBaseAtyUiBinding().ivPower.setImageDrawable(ContextCompat.getDrawable((Context) this, R.drawable.icon_power_off));
            getMBaseAtyUiBinding().mrvMode.setAvailable(false);
            RelativeLayout relativeLayout6 = getMBaseAtyUiBinding().rlAnion;
            Intrinsics.checkNotNullExpressionValue(relativeLayout6, string24);
            ViewExtendsKt.setAvailable(relativeLayout6, false);
            RelativeLayout relativeLayout7 = getMBaseAtyUiBinding().rlSwingWind;
            Intrinsics.checkNotNullExpressionValue(relativeLayout7, string25);
            ViewExtendsKt.setAvailable(relativeLayout7, false);
            RelativeLayout relativeLayout8 = getMBaseAtyUiBinding().rlWaterPump;
            Intrinsics.checkNotNullExpressionValue(relativeLayout8, string23);
            ViewExtendsKt.setAvailable(relativeLayout8, false);
            RelativeLayout relativeLayout9 = getMBaseAtyUiBinding().rlSleep;
            Intrinsics.checkNotNullExpressionValue(relativeLayout9, string22);
            ViewExtendsKt.setAvailable(relativeLayout9, false);
            RelativeLayout relativeLayout10 = getMBaseAtyUiBinding().rlLock;
            Intrinsics.checkNotNullExpressionValue(relativeLayout10, string2);
            ViewExtendsKt.setAvailable(relativeLayout10, true);
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getSys_switch()) && !Intrinsics.areEqual(getDehumidifierBean().getPump_switch(), AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
            dismissWaterPumpDialog();
        }
        this.mDeviceErrorCode = getErrorCodeList();
        setErrorShowState();
        setUpDeviceStateDate();
    }

    private final void setUpDeviceStateDate() {
        getMBaseAtyUiBinding().rlDeviceLoading.setVisibility(8);
        LogUtil.d(StubApp.getString2(13515) + JSON.toJSON(getDehumidifierBean()));
        getMBaseAtyUiBinding().switchAnion.setChecked(switchToBool(getDehumidifierBean().getAnion_switch()));
        getMBaseAtyUiBinding().switchCLock.setChecked(switchToBool(getDehumidifierBean().getLock_switch()));
        getMBaseAtyUiBinding().switchWaterPump.setChecked(switchToBool(getDehumidifierBean().getPump_switch()));
        getMBaseAtyUiBinding().switchWd.setChecked(switchToBool(getDehumidifierBean().getWind_switch()));
        getMBaseAtyUiBinding().switchSleep.setChecked(switchToBool(getDehumidifierBean().sleep_switch));
        getMBaseAtyUiBinding().speedParentView.setDehumidifierBean(getDehumidifierBean());
        getMBaseAtyUiBinding().mrvMode.setDehumidifierBean(getDehumidifierBean());
        if (!TextUtils.isEmpty(this.outdoorTemp)) {
            AirConditionerArcPanel airConditionerArcPanel = getMBaseAtyUiBinding().arcPanel;
            String str = this.outdoorTemp;
            Intrinsics.checkNotNull(str);
            airConditionerArcPanel.setOutDoorTemp(str);
        }
        getMBaseAtyUiBinding().arcPanel.setData(getDehumidifierBean());
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getWatertank_state())) {
            getMBaseAtyUiBinding().llWaterFull.setVisibility(0);
        } else {
            getMBaseAtyUiBinding().llWaterFull.setVisibility(8);
        }
        String mode = getDehumidifierBean().getMode();
        Intrinsics.checkNotNullExpressionValue(mode, "getMode(...)");
        updateBgByMode(mode);
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getDefrost_state())) {
            getMBaseAtyUiBinding().llDefrosting.setVisibility(0);
        } else {
            getMBaseAtyUiBinding().llDefrosting.setVisibility(8);
        }
    }

    private final void setErrorShowState() {
        if (BaseUtils.isNotNull(this.mErrorTextList) && this.mErrorTextList.size() > 0) {
            getMBaseAtyUiBinding().llDeviceError.setVisibility(0);
            int size = this.mErrorTextList.size();
            for (int i = 0; i < size; i++) {
                if (!this.mErrorTextTempList.contains(this.mErrorTextList.get(i))) {
                    this.mErrorTextTempList.add(this.mErrorTextList.get(i));
                }
            }
            DeviceErrorShowTimer deviceErrorShowTimer = this.mDeviceErrorShowTimer;
            if (deviceErrorShowTimer != null) {
                if (deviceErrorShowTimer != null) {
                    deviceErrorShowTimer.notifyList(this.mErrorTextTempList);
                    return;
                }
                return;
            } else {
                DeviceErrorShowTimer deviceErrorShowTimer2 = new DeviceErrorShowTimer(getMBaseAtyUiBinding().ivDeviceError, getMBaseAtyUiBinding().tvDeviceError, 86400000L, 2000L, this.mErrorTextList);
                this.mDeviceErrorShowTimer = deviceErrorShowTimer2;
                deviceErrorShowTimer2.start();
                return;
            }
        }
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

    @Override // com.deye.views.button.SwitchButton.OnClick
    public void onClick(View v, boolean isClicked) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        int id2 = getMBaseAtyUiBinding().switchWd.getId();
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
        if (id == getMBaseAtyUiBinding().switchWaterPump.getId()) {
            if (isClicked) {
                DialogHelper.showWaterPumpDialog(this, new AnonymousClass1());
                return;
            }
            getMBaseAtyUiBinding().switchWaterPump.setChecked(false);
            getDehumidifierBean().setPump_switch(switchToString(false));
            CommandManger commandManger2 = CommandManger.INSTANCE;
            String str3 = this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str3, string22);
            String str4 = this.mProductId;
            Intrinsics.checkNotNullExpressionValue(str4, string2);
            commandManger2.sendWaterPumpCommand(str3, str4, 0, this.mDeviceListBean.isFogPlatform(), this.mDeviceListBean.isUseComboProtocol(), getDehumidifierBean());
            return;
        }
        if (id == getMBaseAtyUiBinding().switchAnion.getId()) {
            getDehumidifierBean().setAnion_switch(switchToString(isClicked));
            CommandManger commandManger3 = CommandManger.INSTANCE;
            String str5 = this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str5, string22);
            String str6 = this.mProductId;
            Intrinsics.checkNotNullExpressionValue(str6, string2);
            commandManger3.sendNegativeIonCommand(str5, str6, isClicked ? 1 : 0, this.mDeviceListBean.isFogPlatform(), this.mDeviceListBean.isUseComboProtocol(), getDehumidifierBean());
            return;
        }
        if (id == getMBaseAtyUiBinding().switchCLock.getId()) {
            getDehumidifierBean().setLock_switch(switchToString(isClicked));
            setUpCoverView();
            CommandManger commandManger4 = CommandManger.INSTANCE;
            String str7 = this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str7, string22);
            String str8 = this.mProductId;
            Intrinsics.checkNotNullExpressionValue(str8, string2);
            commandManger4.sendLockCommand(str7, str8, isClicked ? 1 : 0, this.mDeviceListBean.isFogPlatform(), this.mDeviceListBean.isUseComboProtocol(), getDehumidifierBean());
            return;
        }
        if (id == getMBaseAtyUiBinding().switchSleep.getId()) {
            getDehumidifierBean().sleep_switch = switchToString(isClicked);
            getMBaseAtyUiBinding().speedParentView.setDehumidifierBean(getDehumidifierBean());
            CommandManger commandManger5 = CommandManger.INSTANCE;
            String str9 = this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str9, string22);
            String str10 = this.mProductId;
            Intrinsics.checkNotNullExpressionValue(str10, string2);
            commandManger5.sendSleepCommand(str9, str10, isClicked ? 1 : 0, this.mDeviceListBean.isFogPlatform(), this.mDeviceListBean.isUseComboProtocol(), getDehumidifierBean(), (64 & 64) != 0 ? false : false);
        }
    }

    /* compiled from: AirConditionerControlPanelDataAty.kt */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/deye/activity/device/air_conditioner/base/AirConditionerControlPanelDataAty$onClick$1", "Lcom/deye/helper/DialogHelper$OnDialogListener;", "onSure", "", "text", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelDataAty$onClick$1, reason: invalid class name */
    public static final class AnonymousClass1 extends DialogHelper.OnDialogListener {
        AnonymousClass1() {
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String text) {
            AirConditionerControlPanelDataAty.this.getMBaseAtyUiBinding().switchWaterPump.setChecked(true);
            AirConditionerControlPanelDataAty.this.getDehumidifierBean().setPump_switch(AirConditionerControlPanelDataAty.this.switchToString(true));
            CommandManger commandManger = CommandManger.INSTANCE;
            String str = AirConditionerControlPanelDataAty.this.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str, "access$getMDeviceId$p$s-2069777001(...)");
            String str2 = AirConditionerControlPanelDataAty.this.mProductId;
            Intrinsics.checkNotNullExpressionValue(str2, "access$getMProductId$p$s-2069777001(...)");
            commandManger.sendWaterPumpCommand(str, str2, 1, AirConditionerControlPanelDataAty.this.mDeviceListBean.isFogPlatform(), AirConditionerControlPanelDataAty.this.mDeviceListBean.isUseComboProtocol(), AirConditionerControlPanelDataAty.this.getDehumidifierBean());
            SwitchButton switchButton = AirConditionerControlPanelDataAty.this.getMBaseAtyUiBinding().switchWaterPump;
            final AirConditionerControlPanelDataAty airConditionerControlPanelDataAty = AirConditionerControlPanelDataAty.this;
            switchButton.postDelayed(new Runnable() { // from class: com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelDataAty$onClick$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AirConditionerControlPanelDataAty.AnonymousClass1.onSure$lambda$0(airConditionerControlPanelDataAty);
                }
            }, 1000L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSure$lambda$0(AirConditionerControlPanelDataAty this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.showWaterPumpDialog();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
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
            return;
        }
        if (id == getMBaseAtyUiBinding().ivPower.getId()) {
            if (!BaseUtils.isNull(getDehumidifierBean()) && !BaseUtils.isNull(getDehumidifierBean().getSys_switch()) && Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_GPS, getDehumidifierBean().getSys_switch())) {
                DehumidifierBean dehumidifierBean = getDehumidifierBean();
                String string2 = StubApp.getString2(2546);
                dehumidifierBean.setSys_switch(string2);
                getMBaseAtyUiBinding().ivPower.setImageDrawable(ContextCompat.getDrawable((Context) this, R.drawable.icon_power_on));
                getMBaseAtyUiBinding().tvPower.setText(getString(R.string.turned_on));
                setUpPowerSpecialLogic();
                getMBaseAtyUiBinding().switchSleep.setChecked(false);
                getDehumidifierBean().setPoweron_flag(string2);
                getMBaseAtyUiBinding().speedParentView.setDehumidifierBean(getDehumidifierBean());
                getMBaseAtyUiBinding().arcPanel.setData(getDehumidifierBean());
                getMBaseAtyUiBinding().mrvMode.setDehumidifierBean(getDehumidifierBean());
                getMBaseAtyUiBinding().mrvMode.setAvailable(true);
                RelativeLayout rlSwingWind = getMBaseAtyUiBinding().rlSwingWind;
                Intrinsics.checkNotNullExpressionValue(rlSwingWind, "rlSwingWind");
                ViewExtendsKt.setAvailable(rlSwingWind, true);
                RelativeLayout rlAnion = getMBaseAtyUiBinding().rlAnion;
                Intrinsics.checkNotNullExpressionValue(rlAnion, "rlAnion");
                ViewExtendsKt.setAvailable(rlAnion, true);
                RelativeLayout rlWaterPump = getMBaseAtyUiBinding().rlWaterPump;
                Intrinsics.checkNotNullExpressionValue(rlWaterPump, "rlWaterPump");
                ViewExtendsKt.setAvailable(rlWaterPump, true);
                RelativeLayout rlSleep = getMBaseAtyUiBinding().rlSleep;
                Intrinsics.checkNotNullExpressionValue(rlSleep, "rlSleep");
                ViewExtendsKt.setAvailable(rlSleep, true);
                RelativeLayout rlLock = getMBaseAtyUiBinding().rlLock;
                Intrinsics.checkNotNullExpressionValue(rlLock, "rlLock");
                ViewExtendsKt.setAvailable(rlLock, true);
                CommandManger commandManger = CommandManger.INSTANCE;
                String mDeviceId = this.mDeviceId;
                Intrinsics.checkNotNullExpressionValue(mDeviceId, "mDeviceId");
                String mProductId = this.mProductId;
                Intrinsics.checkNotNullExpressionValue(mProductId, "mProductId");
                commandManger.sendPowerCommand(mDeviceId, mProductId, 1, this.mDeviceListBean.isFogPlatform(), this.mDeviceListBean.isUseComboProtocol(), getDehumidifierBean(), (64 & 64) != 0 ? false : false);
                return;
            }
            DialogHelper.showPowerOffDialog(this, new DialogHelper.OnDialogListener() { // from class: com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelDataAty.onClick.2
                @Override // com.deye.helper.DialogHelper.OnDialogListener
                public void onSure(String text) {
                    DehumidifierBean dehumidifierBean2 = AirConditionerControlPanelDataAty.this.getDehumidifierBean();
                    String string22 = StubApp.getString2(701);
                    dehumidifierBean2.setSys_switch(string22);
                    AirConditionerControlPanelDataAty.this.getMBaseAtyUiBinding().ivPower.setImageDrawable(ContextCompat.getDrawable((Context) AirConditionerControlPanelDataAty.this.mContext, R.drawable.icon_power_off));
                    AirConditionerControlPanelDataAty.this.getMBaseAtyUiBinding().tvPower.setText(AirConditionerControlPanelDataAty.this.getString(R.string.turned_off));
                    AirConditionerControlPanelDataAty.this.getMBaseAtyUiBinding().switchSleep.setChecked(false);
                    AirConditionerControlPanelDataAty.this.getDehumidifierBean().setPoweroff_flag(string22);
                    AirConditionerControlPanelDataAty.this.getMBaseAtyUiBinding().speedParentView.setDehumidifierBean(AirConditionerControlPanelDataAty.this.getDehumidifierBean());
                    AirConditionerControlPanelDataAty.this.getMBaseAtyUiBinding().arcPanel.setData(AirConditionerControlPanelDataAty.this.getDehumidifierBean());
                    AirConditionerControlPanelDataAty.this.getMBaseAtyUiBinding().mrvMode.setDehumidifierBean(AirConditionerControlPanelDataAty.this.getDehumidifierBean());
                    AirConditionerControlPanelDataAty.this.getMBaseAtyUiBinding().mrvMode.setAvailable(false);
                    RelativeLayout rlAnion2 = AirConditionerControlPanelDataAty.this.getMBaseAtyUiBinding().rlAnion;
                    Intrinsics.checkNotNullExpressionValue(rlAnion2, "rlAnion");
                    ViewExtendsKt.setAvailable(rlAnion2, false);
                    RelativeLayout rlSwingWind2 = AirConditionerControlPanelDataAty.this.getMBaseAtyUiBinding().rlSwingWind;
                    Intrinsics.checkNotNullExpressionValue(rlSwingWind2, "rlSwingWind");
                    ViewExtendsKt.setAvailable(rlSwingWind2, false);
                    RelativeLayout rlWaterPump2 = AirConditionerControlPanelDataAty.this.getMBaseAtyUiBinding().rlWaterPump;
                    Intrinsics.checkNotNullExpressionValue(rlWaterPump2, "rlWaterPump");
                    ViewExtendsKt.setAvailable(rlWaterPump2, false);
                    RelativeLayout rlSleep2 = AirConditionerControlPanelDataAty.this.getMBaseAtyUiBinding().rlSleep;
                    Intrinsics.checkNotNullExpressionValue(rlSleep2, "rlSleep");
                    ViewExtendsKt.setAvailable(rlSleep2, false);
                    RelativeLayout rlLock2 = AirConditionerControlPanelDataAty.this.getMBaseAtyUiBinding().rlLock;
                    Intrinsics.checkNotNullExpressionValue(rlLock2, "rlLock");
                    ViewExtendsKt.setAvailable(rlLock2, true);
                    CommandManger commandManger2 = CommandManger.INSTANCE;
                    String str = AirConditionerControlPanelDataAty.this.mDeviceId;
                    Intrinsics.checkNotNullExpressionValue(str, "access$getMDeviceId$p$s-2069777001(...)");
                    String str2 = AirConditionerControlPanelDataAty.this.mProductId;
                    Intrinsics.checkNotNullExpressionValue(str2, "access$getMProductId$p$s-2069777001(...)");
                    commandManger2.sendPowerCommand(str, str2, 0, AirConditionerControlPanelDataAty.this.mDeviceListBean.isFogPlatform(), AirConditionerControlPanelDataAty.this.mDeviceListBean.isUseComboProtocol(), AirConditionerControlPanelDataAty.this.getDehumidifierBean(), (64 & 64) != 0 ? false : false);
                }
            });
            return;
        }
        if (id == getMBaseAtyUiBinding().llDeviceError.getId()) {
            goDeviceErrorDetails(getMControlPanelBean().getDeviceModel());
        }
    }

    @Override // com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelUIAty
    protected void setOnClickListsner() {
        AirConditionerControlPanelDataAty airConditionerControlPanelDataAty = this;
        getMBaseAtyUiBinding().switchWd.setOnClick(airConditionerControlPanelDataAty);
        getMBaseAtyUiBinding().switchWaterPump.setOnClick(airConditionerControlPanelDataAty);
        getMBaseAtyUiBinding().switchWaterPump.setRespondNow(false);
        getMBaseAtyUiBinding().switchAnion.setOnClick(airConditionerControlPanelDataAty);
        getMBaseAtyUiBinding().switchCLock.setOnClick(airConditionerControlPanelDataAty);
        getMBaseAtyUiBinding().switchSleep.setOnClick(airConditionerControlPanelDataAty);
        AirConditionerControlPanelDataAty airConditionerControlPanelDataAty2 = this;
        getMBaseAtyUiBinding().actionbarBlack.actionbarBack.setOnClickListener(airConditionerControlPanelDataAty2);
        getMBaseAtyUiBinding().actionbarBlack.actionBarRight.setOnClickListener(airConditionerControlPanelDataAty2);
        getMBaseAtyUiBinding().ivPower.setOnClickListener(airConditionerControlPanelDataAty2);
        getMBaseAtyUiBinding().llDeviceError.setOnClickListener(airConditionerControlPanelDataAty2);
        getMBaseAtyUiBinding().speedParentView.setOnClickItemListener(new AirWindSpeedParentView.IOnClickItemListener() { // from class: com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelDataAty.setOnClickListsner.1
            @Override // com.deye.views.AirWindSpeedParentView.IOnClickItemListener
            public void onOnClickItem(int position) throws NumberFormatException {
                String str = AirConditionerControlPanelDataAty.this.getMControlPanelBean().getSpeed().getValue()[position];
                Intrinsics.checkNotNullExpressionValue(str, "get(...)");
                int i = Integer.parseInt(str);
                AirConditionerControlPanelDataAty.this.getDehumidifierBean().setSpeed(AirConditionerControlPanelDataAty.this.getMControlPanelBean().getSpeed().getValue()[position]);
                AirConditionerControlPanelDataAty.this.getMBaseAtyUiBinding().arcPanel.setData(AirConditionerControlPanelDataAty.this.getDehumidifierBean());
                CommandManger commandManger = CommandManger.INSTANCE;
                String str2 = AirConditionerControlPanelDataAty.this.mDeviceId;
                Intrinsics.checkNotNullExpressionValue(str2, "access$getMDeviceId$p$s-2069777001(...)");
                String str3 = AirConditionerControlPanelDataAty.this.mProductId;
                Intrinsics.checkNotNullExpressionValue(str3, "access$getMProductId$p$s-2069777001(...)");
                commandManger.sendSpeedCommand(str2, str3, i, AirConditionerControlPanelDataAty.this.mDeviceListBean.isFogPlatform(), AirConditionerControlPanelDataAty.this.mDeviceListBean.isUseComboProtocol(), AirConditionerControlPanelDataAty.this.getDehumidifierBean());
            }
        });
        getMBaseAtyUiBinding().mrvMode.setOnClickItemListener(new DehumidifierModeView.IOnClickItemListener() { // from class: com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelDataAty$$ExternalSyntheticLambda0
            @Override // com.deye.views.recycleview.DehumidifierModeView.IOnClickItemListener
            public final void onOnClickItem(int i) {
                AirConditionerControlPanelDataAty.setOnClickListsner$lambda$0(this.f$0, i);
            }
        });
        getMBaseAtyUiBinding().arcPanel.setHumidityChangeListener(new AirConditionerArcPanel.OnTemperatureChangeListener() { // from class: com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelDataAty.setOnClickListsner.3
            @Override // com.deye.views.AirConditionerArcPanel.OnTemperatureChangeListener
            public void onChange(int temperature) {
                AirConditionerControlPanelDataAty.this.getDehumidifierBean().temperature_set = new StringBuilder().append(temperature).toString();
                CommandManger commandManger = CommandManger.INSTANCE;
                String str = AirConditionerControlPanelDataAty.this.mDeviceId;
                Intrinsics.checkNotNullExpressionValue(str, "access$getMDeviceId$p$s-2069777001(...)");
                String str2 = AirConditionerControlPanelDataAty.this.mProductId;
                Intrinsics.checkNotNullExpressionValue(str2, "access$getMProductId$p$s-2069777001(...)");
                commandManger.sendTempCommand(str, str2, temperature, AirConditionerControlPanelDataAty.this.mDeviceListBean.isFogPlatform(), AirConditionerControlPanelDataAty.this.mDeviceListBean.isUseComboProtocol(), AirConditionerControlPanelDataAty.this.getDehumidifierBean());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickListsner$lambda$0(AirConditionerControlPanelDataAty this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = this$0.getMControlPanelBean().getMode().getValue()[i];
        Intrinsics.checkNotNull(str);
        this$0.updateBgByMode(str);
        this$0.createModeResult(str);
        if (Intrinsics.areEqual(this$0.getDehumidifierBean().sleep_switch, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
            this$0.getDehumidifierBean().sleep_switch = StubApp.getString2(701);
            this$0.getMBaseAtyUiBinding().switchSleep.setChecked(false);
        }
        this$0.getDehumidifierBean().setMode(str);
        this$0.getMBaseAtyUiBinding().arcPanel.setData(this$0.getDehumidifierBean());
        this$0.getMBaseAtyUiBinding().speedParentView.setDehumidifierBean(this$0.getDehumidifierBean());
        this$0.setUpCoverView();
        CommandManger commandManger = CommandManger.INSTANCE;
        String mDeviceId = this$0.mDeviceId;
        Intrinsics.checkNotNullExpressionValue(mDeviceId, "mDeviceId");
        String mProductId = this$0.mProductId;
        Intrinsics.checkNotNullExpressionValue(mProductId, "mProductId");
        commandManger.sendModeCommand(mDeviceId, mProductId, str, this$0.mDeviceListBean.isFogPlatform(), this$0.mDeviceListBean.isUseComboProtocol(), this$0.getDehumidifierBean());
    }

    private final void updateBgByMode(String mode) {
        int iHashCode = mode.hashCode();
        String string2 = StubApp.getString2(13516);
        switch (iHashCode) {
            case 48:
                if (mode.equals(StubApp.getString2(701))) {
                    getMBaseAtyUiBinding().lottieAnimationView.setAnimation(string2);
                    return;
                }
                break;
            case 49:
                if (mode.equals(StubApp.getString2(2546))) {
                    getMBaseAtyUiBinding().lottieAnimationView.setAnimation(StubApp.getString2(13517));
                    return;
                }
                break;
            case 50:
                if (mode.equals(StubApp.getString2(1764))) {
                    getMBaseAtyUiBinding().lottieAnimationView.setAnimation(string2);
                    return;
                }
                break;
        }
        getMBaseAtyUiBinding().lottieAnimationView.setAnimation(StubApp.getString2(13518));
    }

    @Override // com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelUIAty
    protected ArrayList<String> getErrorCodeList() {
        if (BaseUtils.isNotNull(this.mErrorTextList)) {
            this.mErrorTextList.clear();
        }
        if (BaseUtils.isNotNull(this.mErrorTextTempList)) {
            this.mErrorTextTempList.clear();
        }
        ArrayList<String> arrayList = new ArrayList<>();
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().d1)) {
            String string2 = StubApp.getString2(13519);
            arrayList.add(string2);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string2));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().d2)) {
            String string22 = StubApp.getString2(13520);
            arrayList.add(string22);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string22));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().d3)) {
            String string23 = StubApp.getString2(13521);
            arrayList.add(string23);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string23));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE1())) {
            String string24 = StubApp.getString2(13522);
            arrayList.add(string24);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string24));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE2())) {
            String string25 = StubApp.getString2(13523);
            arrayList.add(string25);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string25));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE3())) {
            String string26 = StubApp.getString2(13524);
            arrayList.add(string26);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string26));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE4())) {
            String string27 = StubApp.getString2(13525);
            arrayList.add(string27);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string27));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE5())) {
            String string28 = StubApp.getString2(13500);
            arrayList.add(string28);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string28));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE6())) {
            String string29 = StubApp.getString2(13501);
            arrayList.add(string29);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string29));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getE8())) {
            String string210 = StubApp.getString2(13526);
            arrayList.add(string210);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string210));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getF1())) {
            String string211 = StubApp.getString2(13527);
            arrayList.add(string211);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string211));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getF2())) {
            String string212 = StubApp.getString2(13528);
            arrayList.add(string212);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string212));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getF3())) {
            String string213 = StubApp.getString2(13529);
            arrayList.add(string213);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string213));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getF4())) {
            String string214 = StubApp.getString2(13530);
            arrayList.add(string214);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string214));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getF5())) {
            String string215 = StubApp.getString2(13531);
            arrayList.add(string215);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string215));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getF6())) {
            String string216 = StubApp.getString2(13532);
            arrayList.add(string216);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string216));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getP1())) {
            String string217 = StubApp.getString2(13504);
            arrayList.add(string217);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string217));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getP2())) {
            String string218 = StubApp.getString2(13533);
            arrayList.add(string218);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string218));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().getP3())) {
            String string219 = StubApp.getString2(13534);
            arrayList.add(string219);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string219));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getDehumidifierBean().PL)) {
            String string220 = StubApp.getString2(7573);
            arrayList.add(string220);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string220));
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
