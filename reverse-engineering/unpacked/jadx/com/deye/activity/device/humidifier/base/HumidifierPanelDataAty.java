package com.deye.activity.device.humidifier.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.alibaba.fastjson.JSON;
import com.amap.location.support.bean.location.AmapLocationNetwork;
import com.deye.DeviceCacheManager;
import com.deye.HumidifierCommandManager;
import com.deye.MxchipApplication;
import com.deye.configs.Constants;
import com.deye.configs.ErrorMapUtils;
import com.deye.utils.BaseUtils;
import com.deye.utils.DeviceErrorShowTimer;
import com.deye.utils.MMKVUtils;
import com.deye.views.HumidifierArcPanel;
import com.deye.views.IWindChangeListener;
import com.deye.views.ItemControlView;
import com.deye.views.PartsView;
import com.deye.views.PowerView;
import com.deye.views.recycleview.DehumidifierModeView;
import com.google.gson.Gson;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: HumidifierPanelDataAty.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007H\u0014J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\"\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0012\u0010\u001d\u001a\u00020\u00132\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0013H\u0014J\b\u0010!\u001a\u00020\u0013H\u0002J\b\u0010\"\u001a\u00020\u0013H\u0014J\b\u0010#\u001a\u00020\u0013H\u0002J\b\u0010$\u001a\u00020\u0013H\u0014J\u0010\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u0016H\u0002J\b\u0010'\u001a\u00020\u0013H\u0016R*\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/deye/activity/device/humidifier/base/HumidifierPanelDataAty;", "Lcom/deye/activity/device/humidifier/base/HumidifierPanelMqttAty;", "Landroid/view/View$OnClickListener;", "()V", "mDeviceErrorCode", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getMDeviceErrorCode", "()Ljava/util/ArrayList;", "setMDeviceErrorCode", "(Ljava/util/ArrayList;)V", "mDeviceErrorShowTimer", "Lcom/deye/utils/DeviceErrorShowTimer;", "outdoorTemp", "getErrorCodeList", "getErrorText", "str", "initView", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setErrorShowState", "setOnClickListener", "setUpDeviceStateDate", "setView", "updateBgByEnvHum", "hum", "updateViewByBean", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public abstract class HumidifierPanelDataAty extends HumidifierPanelMqttAty implements View.OnClickListener {
    private ArrayList<String> mDeviceErrorCode = new ArrayList<>();
    private DeviceErrorShowTimer mDeviceErrorShowTimer;
    private String outdoorTemp;

    @Override // com.deye.activity.device.humidifier.base.HumidifierPanelUIAty
    protected void initView() {
    }

    public void updateViewByBean() {
    }

    protected final ArrayList<String> getMDeviceErrorCode() {
        return this.mDeviceErrorCode;
    }

    protected final void setMDeviceErrorCode(ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.mDeviceErrorCode = arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.deye.activity.device.humidifier.base.HumidifierPanelMqttAty, com.deye.activity.device.humidifier.base.HumidifierPanelUIAty, com.deye.activity.device.base.PublicConstantAty, com.deye.activity.device.base.BaseActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MxchipApplication.getInstance().addActivity((Activity) this);
        if (this.mDeviceListBean != null && this.mDeviceListBean.getDevice_name() != null) {
            getMBaseAtyUiBinding().actionbarBlack.actionbarTitle.setText(this.mDeviceListBean.getDevice_name());
        }
        this.outdoorTemp = getIntent().getStringExtra(StubApp.getString2(13537));
    }

    @Override // com.deye.activity.device.humidifier.base.HumidifierPanelMqttAty, com.deye.activity.device.humidifier.base.HumidifierPanelUIAty, com.deye.activity.device.base.BaseActivity
    protected void onResume() {
        super.onResume();
        PartsView partsView = getMBaseAtyUiBinding().partsView;
        String mDeviceId = this.mDeviceId;
        Intrinsics.checkNotNullExpressionValue(mDeviceId, "mDeviceId");
        String mProductId = this.mProductId;
        Intrinsics.checkNotNullExpressionValue(mProductId, "mProductId");
        partsView.reqDetail(mDeviceId, mProductId);
    }

    @Override // com.deye.activity.device.humidifier.base.HumidifierPanelUIAty
    protected void setView() {
        stopWaiting();
        this.mDeviceErrorCode = getErrorCodeList();
        setErrorShowState();
        setUpDeviceStateDate();
    }

    private final void setUpDeviceStateDate() {
        getMBaseAtyUiBinding().rlDeviceLoading.setVisibility(8);
        LogUtil.d(StubApp.getString2(13515) + JSON.toJSON(getHumidifierBean()));
        getMBaseAtyUiBinding().controlLock.setChecked(switchToBool(getHumidifierBean().getLock_switch()));
        getMBaseAtyUiBinding().controlLight.setChecked(switchToBool(getHumidifierBean().lights_switch));
        getMBaseAtyUiBinding().controlDisplay.setChecked(switchToBool(getHumidifierBean().getScreendisplay()));
        getMBaseAtyUiBinding().controlTone.setChecked(switchToBool(getHumidifierBean().sound_switch));
        getMBaseAtyUiBinding().controlButler.setChecked(switchToBool(getHumidifierBean().getHkallowstatus()));
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getHumidifierBean().getWatertank_state())) {
            showWaterTankDialog();
            getMBaseAtyUiBinding().llWaterFull.setVisibility(0);
        } else {
            dismissWaterTankDialog();
            getMBaseAtyUiBinding().llWaterFull.setVisibility(8);
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getHumidifierBean().waterStatus)) {
            showAddWaterDialog();
        } else {
            dismissAddWaterDialog();
        }
        String current_env_hum = getHumidifierBean().getCurrent_env_hum();
        Intrinsics.checkNotNullExpressionValue(current_env_hum, "getCurrent_env_hum(...)");
        updateBgByEnvHum(Integer.parseInt(current_env_hum));
        getMBaseAtyUiBinding().waterTannView.setValue(this.mDeviceListBean.getProduct_id(), getHumidifierBean().watertankcapacity, Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getHumidifierBean().getE5()));
        updateViewByBean();
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

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id == getMBaseAtyUiBinding().actionbarBlack.actionbarBack.getId()) {
            goBack();
        } else if (id == getMBaseAtyUiBinding().actionbarBlack.actionBarRight.getId()) {
            goDeviceDetailsForResult();
        } else if (id == getMBaseAtyUiBinding().llDeviceError.getId()) {
            goDeviceErrorDetails(getMControlPanelBean().getDeviceModel());
        }
    }

    @Override // com.deye.activity.device.humidifier.base.HumidifierPanelUIAty
    protected void setOnClickListener() {
        getMBaseAtyUiBinding().partsView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.humidifier.base.HumidifierPanelDataAty$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HumidifierPanelDataAty.setOnClickListener$lambda$0(this.f$0, view);
            }
        });
        ItemControlView.OnCheckListener onCheckListener = new ItemControlView.OnCheckListener() { // from class: com.deye.activity.device.humidifier.base.HumidifierPanelDataAty$$ExternalSyntheticLambda1
            @Override // com.deye.views.ItemControlView.OnCheckListener
            public final void onChecked(View view, boolean z) {
                HumidifierPanelDataAty.setOnClickListener$lambda$1(this.f$0, view, z);
            }
        };
        getMBaseAtyUiBinding().controlLock.setOnCheckListener(onCheckListener);
        getMBaseAtyUiBinding().controlLight.setOnCheckListener(onCheckListener);
        getMBaseAtyUiBinding().controlButler.setOnCheckListener(onCheckListener);
        getMBaseAtyUiBinding().controlTone.setOnCheckListener(onCheckListener);
        getMBaseAtyUiBinding().controlDisplay.setOnCheckListener(onCheckListener);
        HumidifierPanelDataAty humidifierPanelDataAty = this;
        getMBaseAtyUiBinding().actionbarBlack.actionbarBack.setOnClickListener(humidifierPanelDataAty);
        getMBaseAtyUiBinding().actionbarBlack.actionBarRight.setOnClickListener(humidifierPanelDataAty);
        getMBaseAtyUiBinding().powerView.setOnPowerClickListener(new PowerView.IOnPowerClickListener() { // from class: com.deye.activity.device.humidifier.base.HumidifierPanelDataAty.setOnClickListener.2
            @Override // com.deye.views.PowerView.IOnPowerClickListener
            public void onPowerClick(boolean open, String fanStatus, boolean isHk) {
                String string2 = StubApp.getString2(13665);
                String string22 = StubApp.getString2(13666);
                if (open) {
                    HumidifierPanelDataAty.this.getHumidifierBean().setSys_switch(StubApp.getString2(2546));
                    HumidifierPanelDataAty.this.updateViewByBean();
                    HumidifierCommandManager humidifierCommandManager = HumidifierCommandManager.INSTANCE;
                    String str = HumidifierPanelDataAty.this.mDeviceId;
                    Intrinsics.checkNotNullExpressionValue(str, string22);
                    String str2 = HumidifierPanelDataAty.this.mProductId;
                    Intrinsics.checkNotNullExpressionValue(str2, string2);
                    humidifierCommandManager.sendPowerCommand(str, str2, 1);
                } else {
                    if (isHk) {
                        HumidifierPanelDataAty.this.getHumidifierBean().setHkmodeoperation(StubApp.getString2(7188));
                    }
                    HumidifierPanelDataAty.this.getHumidifierBean().setSys_switch(StubApp.getString2(701));
                    HumidifierPanelDataAty.this.getHumidifierBean().setFan_switch(fanStatus);
                    HumidifierPanelDataAty.this.updateViewByBean();
                    HumidifierCommandManager humidifierCommandManager2 = HumidifierCommandManager.INSTANCE;
                    String str3 = HumidifierPanelDataAty.this.mDeviceId;
                    Intrinsics.checkNotNullExpressionValue(str3, string22);
                    String str4 = HumidifierPanelDataAty.this.mProductId;
                    Intrinsics.checkNotNullExpressionValue(str4, string2);
                    humidifierCommandManager2.sendPowerCommand(str3, str4, 0);
                }
                DeviceCacheManager deviceCacheManager = DeviceCacheManager.INSTANCE;
                String str5 = HumidifierPanelDataAty.this.mDeviceId;
                Intrinsics.checkNotNullExpressionValue(str5, string22);
                String str6 = HumidifierPanelDataAty.this.mProductId;
                Intrinsics.checkNotNullExpressionValue(str6, string2);
                deviceCacheManager.setDeviceCache(str5, str6, HumidifierPanelDataAty.this.getHumidifierBean());
            }
        });
        getMBaseAtyUiBinding().llDeviceError.setOnClickListener(humidifierPanelDataAty);
        getMBaseAtyUiBinding().speedParentView.setWinChangeListener(new IWindChangeListener() { // from class: com.deye.activity.device.humidifier.base.HumidifierPanelDataAty$$ExternalSyntheticLambda2
            @Override // com.deye.views.IWindChangeListener
            public final void onWindChange(int i) {
                HumidifierPanelDataAty.setOnClickListener$lambda$2(this.f$0, i);
            }
        });
        getMBaseAtyUiBinding().mrvMode.setOnClickItemListener(new DehumidifierModeView.IOnClickItemListener() { // from class: com.deye.activity.device.humidifier.base.HumidifierPanelDataAty$$ExternalSyntheticLambda3
            @Override // com.deye.views.recycleview.DehumidifierModeView.IOnClickItemListener
            public final void onOnClickItem(int i) {
                HumidifierPanelDataAty.setOnClickListener$lambda$3(this.f$0, i);
            }
        });
        getMBaseAtyUiBinding().arcPanel.setHumidityChangeListener(new HumidifierArcPanel.OnTemperatureChangeListener() { // from class: com.deye.activity.device.humidifier.base.HumidifierPanelDataAty.setOnClickListener.5
            @Override // com.deye.views.HumidifierArcPanel.OnTemperatureChangeListener
            public void onChange(int temperature) {
                HumidifierPanelDataAty.this.getHumidifierBean().setHum_set(new StringBuilder().append(temperature).toString());
                HumidifierCommandManager humidifierCommandManager = HumidifierCommandManager.INSTANCE;
                String str = HumidifierPanelDataAty.this.mDeviceId;
                Intrinsics.checkNotNullExpressionValue(str, "access$getMDeviceId$p$s1407463062(...)");
                String str2 = HumidifierPanelDataAty.this.mProductId;
                Intrinsics.checkNotNullExpressionValue(str2, "access$getMProductId$p$s1407463062(...)");
                humidifierCommandManager.sendHumidityCommand(str, str2, temperature);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void setOnClickListener$lambda$0(HumidifierPanelDataAty this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent((Context) this$0, (Class<?>) PartsManagerAty.class);
        intent.putExtra(StubApp.getString2(13055), this$0.mDeviceId);
        intent.putExtra(StubApp.getString2(13306), this$0.mProductId);
        Log.e(StubApp.getString2(13667), new Gson().toJson(this$0.getHumidifierBean()));
        this$0.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickListener$lambda$1(HumidifierPanelDataAty this$0, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int id = view.getId();
        int id2 = this$0.getMBaseAtyUiBinding().controlLock.getId();
        String string2 = StubApp.getString2(13535);
        String string22 = StubApp.getString2(13536);
        if (id == id2) {
            this$0.getHumidifierBean().setLock_switch(this$0.switchToString(this$0.getMBaseAtyUiBinding().controlLock.isChecked()));
            this$0.updateViewByBean();
            HumidifierCommandManager humidifierCommandManager = HumidifierCommandManager.INSTANCE;
            String str = this$0.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str, string22);
            String str2 = this$0.mProductId;
            Intrinsics.checkNotNullExpressionValue(str2, string2);
            humidifierCommandManager.sendLockCommand(str, str2, this$0.getMBaseAtyUiBinding().controlLock.isChecked() ? 1 : 0);
            return;
        }
        if (id == this$0.getMBaseAtyUiBinding().controlLight.getId()) {
            this$0.getHumidifierBean().setLock_switch(this$0.switchToString(this$0.getMBaseAtyUiBinding().controlLight.isChecked()));
            HumidifierCommandManager humidifierCommandManager2 = HumidifierCommandManager.INSTANCE;
            String str3 = this$0.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str3, string22);
            String str4 = this$0.mProductId;
            Intrinsics.checkNotNullExpressionValue(str4, string2);
            humidifierCommandManager2.sendLightCommand(str3, str4, this$0.getMBaseAtyUiBinding().controlLight.isChecked() ? 1 : 0);
            return;
        }
        if (id == this$0.getMBaseAtyUiBinding().controlButler.getId()) {
            this$0.getHumidifierBean().setHkallowstatus(this$0.switchToString(this$0.getMBaseAtyUiBinding().controlButler.isChecked()));
            HumidifierCommandManager humidifierCommandManager3 = HumidifierCommandManager.INSTANCE;
            String str5 = this$0.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str5, string22);
            String str6 = this$0.mProductId;
            Intrinsics.checkNotNullExpressionValue(str6, string2);
            humidifierCommandManager3.sendHkAllowCommand(str5, str6, this$0.getMBaseAtyUiBinding().controlButler.isChecked() ? 1 : 0);
            return;
        }
        int id3 = this$0.getMBaseAtyUiBinding().controlDisplay.getId();
        String string23 = StubApp.getString2(13668);
        if (id == id3) {
            this$0.getHumidifierBean().setScreendisplay(this$0.switchToString(this$0.getMBaseAtyUiBinding().controlDisplay.isChecked()));
            MMKVUtils mMKVUtils = MMKVUtils.INSTANCE;
            DeviceListBean deviceListBean = this$0.mDeviceListBean;
            Intrinsics.checkNotNull(deviceListBean);
            String device_id = deviceListBean.getDevice_id();
            Intrinsics.checkNotNullExpressionValue(device_id, string23);
            mMKVUtils.setDisplayScreen(device_id, this$0.switchToBool(this$0.getHumidifierBean().getScreendisplay()));
            HumidifierCommandManager humidifierCommandManager4 = HumidifierCommandManager.INSTANCE;
            String str7 = this$0.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str7, string22);
            String str8 = this$0.mProductId;
            Intrinsics.checkNotNullExpressionValue(str8, string2);
            humidifierCommandManager4.sendDisPlayCommand(str7, str8, this$0.getMBaseAtyUiBinding().controlDisplay.isChecked() ? 1 : 0);
            return;
        }
        if (id == this$0.getMBaseAtyUiBinding().controlTone.getId()) {
            this$0.getHumidifierBean().sound_switch = this$0.switchToString(this$0.getMBaseAtyUiBinding().controlTone.isChecked());
            MMKVUtils mMKVUtils2 = MMKVUtils.INSTANCE;
            DeviceListBean deviceListBean2 = this$0.mDeviceListBean;
            Intrinsics.checkNotNull(deviceListBean2);
            String device_id2 = deviceListBean2.getDevice_id();
            Intrinsics.checkNotNullExpressionValue(device_id2, string23);
            mMKVUtils2.setTone(device_id2, this$0.switchToBool(this$0.getHumidifierBean().sound_switch));
            HumidifierCommandManager humidifierCommandManager5 = HumidifierCommandManager.INSTANCE;
            String str9 = this$0.mDeviceId;
            Intrinsics.checkNotNullExpressionValue(str9, string22);
            String str10 = this$0.mProductId;
            Intrinsics.checkNotNullExpressionValue(str10, string2);
            humidifierCommandManager5.sendToneCommand(str9, str10, this$0.getMBaseAtyUiBinding().controlTone.isChecked() ? 1 : 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickListener$lambda$2(HumidifierPanelDataAty this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HumidifierCommandManager humidifierCommandManager = HumidifierCommandManager.INSTANCE;
        String mDeviceId = this$0.mDeviceId;
        Intrinsics.checkNotNullExpressionValue(mDeviceId, "mDeviceId");
        String mProductId = this$0.mProductId;
        Intrinsics.checkNotNullExpressionValue(mProductId, "mProductId");
        humidifierCommandManager.sendSpeedCommand(mDeviceId, mProductId, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickListener$lambda$3(HumidifierPanelDataAty this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = this$0.getMControlPanelBean().getMode().getValue()[i];
        this$0.getHumidifierBean().setMode(str);
        Intrinsics.checkNotNull(str);
        this$0.createModeResult(str);
        this$0.updateViewByBean();
        DeviceCacheManager deviceCacheManager = DeviceCacheManager.INSTANCE;
        String mDeviceId = this$0.mDeviceId;
        Intrinsics.checkNotNullExpressionValue(mDeviceId, "mDeviceId");
        String mProductId = this$0.mProductId;
        Intrinsics.checkNotNullExpressionValue(mProductId, "mProductId");
        deviceCacheManager.setDeviceCache(mDeviceId, mProductId, this$0.getHumidifierBean());
        HumidifierCommandManager humidifierCommandManager = HumidifierCommandManager.INSTANCE;
        String mDeviceId2 = this$0.mDeviceId;
        Intrinsics.checkNotNullExpressionValue(mDeviceId2, "mDeviceId");
        String mProductId2 = this$0.mProductId;
        Intrinsics.checkNotNullExpressionValue(mProductId2, "mProductId");
        humidifierCommandManager.sendModeCommand(mDeviceId2, mProductId2, this$0.getHumidifierBean(), Integer.parseInt(str));
    }

    private final void updateBgByEnvHum(int hum) {
        if (hum < 51) {
            getMBaseAtyUiBinding().lottieAnimationView.setAnimation(StubApp.getString2(13517));
        } else if (51 <= hum && hum < 66) {
            getMBaseAtyUiBinding().lottieAnimationView.setAnimation(StubApp.getString2(13518));
        } else {
            getMBaseAtyUiBinding().lottieAnimationView.setAnimation(StubApp.getString2(13516));
        }
    }

    @Override // com.deye.activity.device.humidifier.base.HumidifierPanelUIAty
    protected ArrayList<String> getErrorCodeList() {
        if (BaseUtils.isNotNull(this.mErrorTextList)) {
            this.mErrorTextList.clear();
        }
        if (BaseUtils.isNotNull(this.mErrorTextTempList)) {
            this.mErrorTextTempList.clear();
        }
        ArrayList<String> arrayList = new ArrayList<>();
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getHumidifierBean().getP6())) {
            String string2 = StubApp.getString2(13507);
            arrayList.add(string2);
            this.mErrorTextList.add(getErrorText(string2));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getHumidifierBean().getP7())) {
            String string22 = StubApp.getString2(13581);
            arrayList.add(string22);
            this.mErrorTextList.add(getErrorText(string22));
        }
        boolean zAreEqual = Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getHumidifierBean().L2);
        String string23 = StubApp.getString2(11410);
        if (zAreEqual) {
            arrayList.add(string23);
            this.mErrorTextList.add(getErrorText(string23));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getHumidifierBean().A1)) {
            String string24 = StubApp.getString2(13669);
            arrayList.add(string24);
            this.mErrorTextList.add(getErrorText(string24));
        }
        if (!Constants.isH7Product(this.mProductId)) {
            if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getHumidifierBean().A2)) {
                String string25 = StubApp.getString2(13670);
                arrayList.add(string25);
                this.mErrorTextList.add(getErrorText(string25));
            }
            if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getHumidifierBean().A3)) {
                String string26 = StubApp.getString2(13671);
                arrayList.add(string26);
                this.mErrorTextList.add(getErrorText(string26));
            }
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getHumidifierBean().A4)) {
            String string27 = StubApp.getString2(13672);
            arrayList.add(string27);
            this.mErrorTextList.add(getErrorText(string27));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getHumidifierBean().A5)) {
            String string28 = StubApp.getString2(13673);
            arrayList.add(string28);
            this.mErrorTextList.add(getErrorText(string28));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getHumidifierBean().getE1())) {
            String string29 = StubApp.getString2(13522);
            arrayList.add(string29);
            this.mErrorTextList.add(getErrorText(string29));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getHumidifierBean().getE2())) {
            String string210 = StubApp.getString2(13523);
            arrayList.add(string210);
            this.mErrorTextList.add(getErrorText(string210));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getHumidifierBean().getE3())) {
            String string211 = StubApp.getString2(13524);
            arrayList.add(string211);
            this.mErrorTextList.add(getErrorText(string211));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getHumidifierBean().getE5())) {
            String string212 = StubApp.getString2(13500);
            arrayList.add(string212);
            this.mErrorTextList.add(getErrorText(string212));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getHumidifierBean().L2)) {
            arrayList.add(string23);
            this.mErrorTextList.add(getErrorText(string23));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final String getErrorText(String str) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.machine_fault);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String str2 = String.format(string, Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
        if (this.mProductId.equals(StubApp.getString2(13436))) {
            Context context = (Context) this;
            if (ErrorMapUtils.getF15ErrorMap(context).containsKey(str)) {
                return ErrorMapUtils.getF15ErrorMap(context).get(str);
            }
        }
        if (Constants.isH7Product(this.mProductId)) {
            Context context2 = (Context) this;
            if (ErrorMapUtils.getH7ErrorMap(context2).containsKey(str)) {
                return ErrorMapUtils.getH7ErrorMap(context2).get(str);
            }
        }
        if (Intrinsics.areEqual(str, "FL")) {
            return getString(R.string.water_full_stop);
        }
        return Intrinsics.areEqual(str, "HS") ? getString(R.string.defrosting_low_temp) : str2;
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
