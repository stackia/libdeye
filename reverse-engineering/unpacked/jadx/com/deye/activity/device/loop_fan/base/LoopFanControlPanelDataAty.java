package com.deye.activity.device.loop_fan.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.amap.location.support.bean.location.AmapLocationNetwork;
import com.deye.CommandManger;
import com.deye.MxchipApplication;
import com.deye.adapter.LoopFanWindModeAdapter;
import com.deye.configs.DeYeControlUtils;
import com.deye.entity.control_panel.dehumidifier.func.ModeBean;
import com.deye.helper.DialogHelper;
import com.deye.utils.BaseUtils;
import com.deye.utils.DeviceErrorShowTimer;
import com.deye.views.ArcPanel;
import com.deye.views.ViewExtendsKt;
import com.deye.views.WindSpeedParentView;
import com.deye.views.button.SwitchButton;
import com.deye.views.recycleview.LoopFanModeView;
import com.deye.views.recycleview.LoopFanWindModeRecyclerView;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.LoopFanBean;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoopFanControlPanelDataAty.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\"\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0012\u0010\u001f\u001a\u00020\u00132\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0012\u0010\"\u001a\u00020\u00132\b\u0010#\u001a\u0004\u0018\u00010$H\u0004J\b\u0010%\u001a\u00020\u0013H\u0002J\b\u0010&\u001a\u00020\u0013H\u0014J\b\u0010'\u001a\u00020\u0013H\u0002J\b\u0010(\u001a\u00020\u0013H\u0014J\b\u0010)\u001a\u00020\u0013H\u0014J\u0010\u0010*\u001a\u00020\u00132\u0006\u0010+\u001a\u00020\u0016H\u0002R*\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/deye/activity/device/loop_fan/base/LoopFanControlPanelDataAty;", "Lcom/deye/activity/device/loop_fan/base/LoopFanControlPanelMqttAty;", "Lcom/deye/views/button/SwitchButton$OnClick;", "Landroid/view/View$OnClickListener;", "()V", "mDeviceErrorCode", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getMDeviceErrorCode", "()Ljava/util/ArrayList;", "setMDeviceErrorCode", "(Ljava/util/ArrayList;)V", "mDeviceErrorShowTimer", "Lcom/deye/utils/DeviceErrorShowTimer;", "mModeBean", "Lcom/deye/entity/control_panel/dehumidifier/func/ModeBean;", "getErrorCodeList", "initView", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "isClicked", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "sendCommand", "mLoopFanBeanTemp", "Lio/fogcloud/sdk/fog/bean/LoopFanBean;", "setErrorShowState", "setOnClickListener", "setUpDeviceStateDate", "setUpPowerSpecialLogic", "setView", "updateBgByEnvHum", "hum", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public abstract class LoopFanControlPanelDataAty extends LoopFanControlPanelMqttAty implements SwitchButton.OnClick, View.OnClickListener {
    private ArrayList<String> mDeviceErrorCode = new ArrayList<>();
    private DeviceErrorShowTimer mDeviceErrorShowTimer;
    private ModeBean mModeBean;

    @Override // com.deye.activity.device.loop_fan.base.LoopFanControlPanelUIAty
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
    @Override // com.deye.activity.device.loop_fan.base.LoopFanControlPanelMqttAty, com.deye.activity.device.loop_fan.base.LoopFanControlPanelUIAty, com.deye.activity.device.base.PublicConstantAty, com.deye.activity.device.base.BaseActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MxchipApplication.getInstance().addActivity((Activity) this);
        if (this.mDeviceListBean == null || this.mDeviceListBean.getDevice_name() == null) {
            return;
        }
        getMBaseAtyUiBinding().actionbarBlack.actionbarTitle.setText(this.mDeviceListBean.getDevice_name());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.deye.activity.device.loop_fan.base.LoopFanControlPanelUIAty
    protected void setView() {
        stopWaiting();
        boolean zAreEqual = Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getMLoopFanBean().getSys_switch());
        String string2 = StubApp.getString2(13678);
        String string22 = StubApp.getString2(13679);
        String string23 = StubApp.getString2(13680);
        if (zAreEqual) {
            getMBaseAtyUiBinding().ivPower.setImageDrawable(ContextCompat.getDrawable((Context) this, R.drawable.icon_power_on));
            getMBaseAtyUiBinding().tvPower.setText(getString(R.string.Off));
            LoopFanModeView loopFanModeView = getMBaseAtyUiBinding().mrvMode;
            Intrinsics.checkNotNullExpressionValue(loopFanModeView, string23);
            ViewExtendsKt.setAvailable((View) loopFanModeView, true);
            RelativeLayout relativeLayout = getMBaseAtyUiBinding().rlLights;
            Intrinsics.checkNotNullExpressionValue(relativeLayout, string22);
            ViewExtendsKt.setAvailable(relativeLayout, true);
            LoopFanWindModeRecyclerView loopFanWindModeRecyclerView = getMBaseAtyUiBinding().ryWindMode;
            Intrinsics.checkNotNullExpressionValue(loopFanWindModeRecyclerView, string2);
            ViewExtendsKt.setAvailable((View) loopFanWindModeRecyclerView, true);
            this.mDeviceErrorCode = getErrorCodeList();
            setErrorShowState();
            setUpCoverView();
        } else if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_GPS, getMLoopFanBean().getSys_switch())) {
            getMBaseAtyUiBinding().tvPower.setText(getString(R.string.on));
            getMBaseAtyUiBinding().ivPower.setImageDrawable(ContextCompat.getDrawable((Context) this, R.drawable.icon_power_off));
            LoopFanModeView loopFanModeView2 = getMBaseAtyUiBinding().mrvMode;
            Intrinsics.checkNotNullExpressionValue(loopFanModeView2, string23);
            ViewExtendsKt.setAvailable((View) loopFanModeView2, false);
            RelativeLayout relativeLayout2 = getMBaseAtyUiBinding().rlLights;
            Intrinsics.checkNotNullExpressionValue(relativeLayout2, string22);
            ViewExtendsKt.setAvailable(relativeLayout2, false);
            LoopFanWindModeRecyclerView loopFanWindModeRecyclerView2 = getMBaseAtyUiBinding().ryWindMode;
            Intrinsics.checkNotNullExpressionValue(loopFanWindModeRecyclerView2, string2);
            ViewExtendsKt.setAvailable((View) loopFanWindModeRecyclerView2, false);
        }
        setUpDeviceStateDate();
    }

    private final void setUpDeviceStateDate() {
        getMBaseAtyUiBinding().rlDeviceLoading.setVisibility(8);
        getMBaseAtyUiBinding().switchLight.setChecked(switchToBool(getMLoopFanBean().getLamp_off()));
        getMBaseAtyUiBinding().arcPanel.setData(getMLoopFanBean());
        this.mModeBean = getMControlPanelBean().getMode();
        getMBaseAtyUiBinding().mrvMode.setLoopFanBean(getMLoopFanBean());
        getMBaseAtyUiBinding().speedParentView.setLoopFanBean(getMLoopFanBean());
        getMBaseAtyUiBinding().ryWindMode.setSelectedItem(getMLoopFanBean().getWind_mode());
        if (getMLoopFanBean().getHum_set() != null) {
            String hum_set = getMLoopFanBean().getHum_set();
            Intrinsics.checkNotNullExpressionValue(hum_set, "getHum_set(...)");
            updateBgByEnvHum(Integer.parseInt(hum_set));
            return;
        }
        updateBgByEnvHum(60);
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
        if (id == getMBaseAtyUiBinding().rlLights.getId()) {
            goSchedulerAty();
            return;
        }
        if (id == getMBaseAtyUiBinding().ivPower.getId()) {
            if (!BaseUtils.isNull(getMLoopFanBean()) && !BaseUtils.isNull(getMLoopFanBean().getSys_switch()) && Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_GPS, getMLoopFanBean().getSys_switch())) {
                getMLoopFanBean().setSys_switch(StubApp.getString2(2546));
                getMBaseAtyUiBinding().ivPower.setImageDrawable(ContextCompat.getDrawable((Context) this, R.drawable.icon_power_on));
                getMBaseAtyUiBinding().tvPower.setText(getString(R.string.Off));
                setUpPowerSpecialLogic();
                getMBaseAtyUiBinding().speedParentView.setLoopFanBean(getMLoopFanBean());
                getMBaseAtyUiBinding().mrvMode.setLoopFanBean(getMLoopFanBean());
                LoopFanModeView mrvMode = getMBaseAtyUiBinding().mrvMode;
                Intrinsics.checkNotNullExpressionValue(mrvMode, "mrvMode");
                ViewExtendsKt.setAvailable((View) mrvMode, true);
                RelativeLayout rlLights = getMBaseAtyUiBinding().rlLights;
                Intrinsics.checkNotNullExpressionValue(rlLights, "rlLights");
                ViewExtendsKt.setAvailable(rlLights, true);
                sendCommand(getMLoopFanBean());
                return;
            }
            DialogHelper.showDeleteDialog(this, getResources().getString(R.string.power_off_dialog_tip_text), getString(R.string.sure_text), new DialogHelper.OnDialogListener() { // from class: com.deye.activity.device.loop_fan.base.LoopFanControlPanelDataAty.onClick.1
                @Override // com.deye.helper.DialogHelper.OnDialogListener
                public void onSure(String text) {
                    LoopFanControlPanelDataAty.this.getMLoopFanBean().setSys_switch(StubApp.getString2(701));
                    LoopFanControlPanelDataAty.this.getMBaseAtyUiBinding().ivPower.setImageDrawable(ContextCompat.getDrawable((Context) LoopFanControlPanelDataAty.this.mContext, R.drawable.icon_power_off));
                    LoopFanControlPanelDataAty.this.getMBaseAtyUiBinding().tvPower.setText(LoopFanControlPanelDataAty.this.getString(R.string.on));
                    LoopFanControlPanelDataAty.this.getMBaseAtyUiBinding().speedParentView.setLoopFanBean(LoopFanControlPanelDataAty.this.getMLoopFanBean());
                    LoopFanControlPanelDataAty.this.getMBaseAtyUiBinding().mrvMode.setLoopFanBean(LoopFanControlPanelDataAty.this.getMLoopFanBean());
                    LoopFanModeView mrvMode2 = LoopFanControlPanelDataAty.this.getMBaseAtyUiBinding().mrvMode;
                    Intrinsics.checkNotNullExpressionValue(mrvMode2, "mrvMode");
                    ViewExtendsKt.setAvailable((View) mrvMode2, false);
                    RelativeLayout rlLights2 = LoopFanControlPanelDataAty.this.getMBaseAtyUiBinding().rlLights;
                    Intrinsics.checkNotNullExpressionValue(rlLights2, "rlLights");
                    ViewExtendsKt.setAvailable(rlLights2, false);
                    LoopFanControlPanelDataAty loopFanControlPanelDataAty = LoopFanControlPanelDataAty.this;
                    loopFanControlPanelDataAty.sendCommand(loopFanControlPanelDataAty.getMLoopFanBean());
                }
            });
            return;
        }
        if (id == getMBaseAtyUiBinding().llDeviceError.getId()) {
            goDeviceErrorDetails(getMControlPanelBean().getDeviceModel());
        }
    }

    @Override // com.deye.views.button.SwitchButton.OnClick
    public void onClick(View v, boolean isClicked) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getId() == getMBaseAtyUiBinding().switchLight.getId()) {
            getMLoopFanBean().setLamp_off(switchToString(isClicked));
            sendCommand(getMLoopFanBean());
        }
    }

    @Override // com.deye.activity.device.loop_fan.base.LoopFanControlPanelUIAty
    protected void setOnClickListener() {
        LoopFanControlPanelDataAty loopFanControlPanelDataAty = this;
        getMBaseAtyUiBinding().actionbarBlack.actionbarBack.setOnClickListener(loopFanControlPanelDataAty);
        getMBaseAtyUiBinding().actionbarBlack.actionBarRight.setOnClickListener(loopFanControlPanelDataAty);
        getMBaseAtyUiBinding().rlAppointment.setOnClickListener(loopFanControlPanelDataAty);
        getMBaseAtyUiBinding().ivPower.setOnClickListener(loopFanControlPanelDataAty);
        getMBaseAtyUiBinding().llDeviceError.setOnClickListener(loopFanControlPanelDataAty);
        getMBaseAtyUiBinding().switchLight.setOnClick(this);
        getMBaseAtyUiBinding().speedParentView.setOnClickItemListener(new WindSpeedParentView.IOnClickItemListener() { // from class: com.deye.activity.device.loop_fan.base.LoopFanControlPanelDataAty.setOnClickListener.1
            @Override // com.deye.views.WindSpeedParentView.IOnClickItemListener
            public void onOnClickItem(int position) {
                LoopFanControlPanelDataAty.this.getMLoopFanBean().setSpeed(LoopFanControlPanelDataAty.this.getMControlPanelBean().getSpeed().getValue()[position]);
                LoopFanControlPanelDataAty loopFanControlPanelDataAty2 = LoopFanControlPanelDataAty.this;
                loopFanControlPanelDataAty2.sendCommand(loopFanControlPanelDataAty2.getMLoopFanBean());
            }
        });
        getMBaseAtyUiBinding().mrvMode.setOnClickItemListener(new LoopFanModeView.IOnClickItemListener() { // from class: com.deye.activity.device.loop_fan.base.LoopFanControlPanelDataAty$$ExternalSyntheticLambda0
            @Override // com.deye.views.recycleview.LoopFanModeView.IOnClickItemListener
            public final void onOnClickItem(LoopFanBean loopFanBean) {
                LoopFanControlPanelDataAty.setOnClickListener$lambda$0(this.f$0, loopFanBean);
            }
        });
        getMBaseAtyUiBinding().arcPanel.setHumidityChangeListener(new ArcPanel.OnHumidityChangeListener() { // from class: com.deye.activity.device.loop_fan.base.LoopFanControlPanelDataAty.setOnClickListener.3
            @Override // com.deye.views.ArcPanel.OnHumidityChangeListener
            public void onChange(int humidity) {
            }
        });
        getMBaseAtyUiBinding().ryWindMode.setOnClickItemListener(new LoopFanWindModeAdapter.IOnClickItemListener() { // from class: com.deye.activity.device.loop_fan.base.LoopFanControlPanelDataAty$$ExternalSyntheticLambda1
            @Override // com.deye.adapter.LoopFanWindModeAdapter.IOnClickItemListener
            public final void onOnClickItem(int i) {
                LoopFanControlPanelDataAty.setOnClickListener$lambda$1(this.f$0, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickListener$lambda$0(LoopFanControlPanelDataAty this$0, LoopFanBean loopFanBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNull(loopFanBean);
        this$0.setMLoopFanBean(loopFanBean);
        this$0.sendCommand(this$0.getMLoopFanBean());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickListener$lambda$1(LoopFanControlPanelDataAty this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getMLoopFanBean() != null) {
            this$0.getMLoopFanBean().setWind_mode(this$0.getMControlPanelBean().getWind_mode().getValue()[i]);
            this$0.sendCommand(this$0.getMLoopFanBean());
        }
    }

    protected final void sendCommand(LoopFanBean mLoopFanBeanTemp) {
        CommandManger commandManger = CommandManger.INSTANCE;
        String mDeviceId = this.mDeviceId;
        Intrinsics.checkNotNullExpressionValue(mDeviceId, "mDeviceId");
        String mProductId = this.mProductId;
        Intrinsics.checkNotNullExpressionValue(mProductId, "mProductId");
        commandManger.sendLoopCommand(mDeviceId, mProductId, mLoopFanBeanTemp);
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

    @Override // com.deye.activity.device.loop_fan.base.LoopFanControlPanelUIAty
    protected ArrayList<String> getErrorCodeList() {
        if (BaseUtils.isNotNull(this.mErrorTextList)) {
            this.mErrorTextList.clear();
        }
        if (BaseUtils.isNotNull(this.mErrorTextTempList)) {
            this.mErrorTextTempList.clear();
        }
        ArrayList<String> arrayList = new ArrayList<>();
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getMLoopFanBean().getE0())) {
            String string2 = StubApp.getString2(13499);
            arrayList.add(string2);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string2));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getMLoopFanBean().getE1())) {
            String string22 = StubApp.getString2(13522);
            arrayList.add(string22);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string22));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getMLoopFanBean().getE2())) {
            String string23 = StubApp.getString2(13523);
            arrayList.add(string23);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string23));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getMLoopFanBean().getE3())) {
            String string24 = StubApp.getString2(13524);
            arrayList.add(string24);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string24));
        }
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, getMLoopFanBean().getL0())) {
            String string25 = StubApp.getString2(13677);
            arrayList.add(string25);
            this.mErrorTextList.add(DeYeControlUtils.getErrorText(string25));
        }
        return arrayList;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            Intrinsics.checkNotNull(data);
            String stringExtra = data.getStringExtra(StubApp.getString2(6888));
            this.mDeviceListBean.setDevice_name(stringExtra);
            getMBaseAtyUiBinding().actionbarBlack.actionbarTitle.setText(stringExtra);
        }
    }
}
