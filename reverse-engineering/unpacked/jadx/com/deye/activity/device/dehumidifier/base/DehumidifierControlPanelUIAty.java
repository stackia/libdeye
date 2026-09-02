package com.deye.activity.device.dehumidifier.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.alibaba.fastjson.JSON;
import com.deye.CommandManger;
import com.deye.MyReactActivity;
import com.deye.activity.device.ErrorListInfoAty;
import com.deye.activity.device.SchedulerListAty;
import com.deye.activity.device.base.PublicConstantAty;
import com.deye.entity.control_panel.dehumidifier.DehumidifierControlPanelBean;
import com.deye.entity.control_panel.dehumidifier.func.HumidityBean;
import com.deye.entity.control_panel.dehumidifier.func.SpeedBean;
import com.deye.helper.DelayedShutdownTimePickerHelper;
import com.deye.utils.AssetsFileRead;
import com.deye.views.ArcPanel;
import com.deye.views.WindSpeedParentView;
import com.deye.views.control.DelayedShutdownControlView;
import com.mxchipapp.R;
import com.mxchipapp.databinding.DeyeDehumidifierBaseUiBinding;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DehumidifierControlPanelUIAty.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H$J\b\u0010\u0019\u001a\u00020\u0018H\u0014J\u0018\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u00180\u001bj\b\u0012\u0004\u0012\u00020\u0018`\u001cH$J\u0012\u0010\u001d\u001a\u00020\u00162\b\u0010\u001e\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u001f\u001a\u00020\u0016H\u0004J\u0012\u0010 \u001a\u00020\u00162\b\u0010!\u001a\u0004\u0018\u00010\u0010H\u0004J\u0012\u0010\"\u001a\u00020\u00162\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0010\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00020'H\u0014J\b\u0010(\u001a\u00020\u0016H\u0014J\b\u0010)\u001a\u00020\u0016H\u0014J\b\u0010*\u001a\u00020\u0016H\u0014J\b\u0010+\u001a\u00020\u0016H$J\b\u0010,\u001a\u00020\u0016H\u0002J\u0010\u0010-\u001a\u00020\u00162\u0006\u0010.\u001a\u00020'H\u0004J\b\u0010/\u001a\u00020\u0016H$J\b\u00100\u001a\u00020\u0016H$J\b\u00101\u001a\u00020\u0016H$J\b\u00102\u001a\u00020\u0016H\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u00063"}, d2 = {"Lcom/deye/activity/device/dehumidifier/base/DehumidifierControlPanelUIAty;", "Lcom/deye/activity/device/base/PublicConstantAty;", "()V", "dehumidifierBean", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "getDehumidifierBean", "()Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "setDehumidifierBean", "(Lio/fogcloud/sdk/fog/bean/DehumidifierBean;)V", "mBaseAtyUiBinding", "Lcom/mxchipapp/databinding/DeyeDehumidifierBaseUiBinding;", "getMBaseAtyUiBinding", "()Lcom/mxchipapp/databinding/DeyeDehumidifierBaseUiBinding;", "setMBaseAtyUiBinding", "(Lcom/mxchipapp/databinding/DeyeDehumidifierBaseUiBinding;)V", "mControlPanelBean", "Lcom/deye/entity/control_panel/dehumidifier/DehumidifierControlPanelBean;", "getMControlPanelBean", "()Lcom/deye/entity/control_panel/dehumidifier/DehumidifierControlPanelBean;", "setMControlPanelBean", "(Lcom/deye/entity/control_panel/dehumidifier/DehumidifierControlPanelBean;)V", "createModeResult", "", "mode", "", "getDehumidifierJsonFile", "getErrorCodeList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "goDeviceErrorDetails", "deviceModel", "goSchedulerAty", "initViews", "controlPanelBean", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDelayedShutdownSwitchChanged", "isChecked", "", "onDelayedShutdownTimeClick", "onResume", "onStop", "reInitControlPanelBean", "setHeadLayout", "setOffLineLayout", "onlineState", "setOnClickListsner", "setUpCoverView", "setView", "showDelayedShutdownTimePicker", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public abstract class DehumidifierControlPanelUIAty extends PublicConstantAty {
    public DehumidifierBean dehumidifierBean;
    public DeyeDehumidifierBaseUiBinding mBaseAtyUiBinding;
    public DehumidifierControlPanelBean mControlPanelBean;

    private final void setHeadLayout() {
    }

    protected abstract void createModeResult(String mode);

    protected abstract ArrayList<String> getErrorCodeList();

    protected abstract void reInitControlPanelBean();

    protected abstract void setOnClickListsner();

    protected abstract void setUpCoverView();

    protected abstract void setView();

    public final DeyeDehumidifierBaseUiBinding getMBaseAtyUiBinding() {
        DeyeDehumidifierBaseUiBinding deyeDehumidifierBaseUiBinding = this.mBaseAtyUiBinding;
        if (deyeDehumidifierBaseUiBinding != null) {
            return deyeDehumidifierBaseUiBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mBaseAtyUiBinding");
        return null;
    }

    public final void setMBaseAtyUiBinding(DeyeDehumidifierBaseUiBinding deyeDehumidifierBaseUiBinding) {
        Intrinsics.checkNotNullParameter(deyeDehumidifierBaseUiBinding, "<set-?>");
        this.mBaseAtyUiBinding = deyeDehumidifierBaseUiBinding;
    }

    public final DehumidifierBean getDehumidifierBean() {
        DehumidifierBean dehumidifierBean = this.dehumidifierBean;
        if (dehumidifierBean != null) {
            return dehumidifierBean;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dehumidifierBean");
        return null;
    }

    public final void setDehumidifierBean(DehumidifierBean dehumidifierBean) {
        Intrinsics.checkNotNullParameter(dehumidifierBean, "<set-?>");
        this.dehumidifierBean = dehumidifierBean;
    }

    public final DehumidifierControlPanelBean getMControlPanelBean() {
        DehumidifierControlPanelBean dehumidifierControlPanelBean = this.mControlPanelBean;
        if (dehumidifierControlPanelBean != null) {
            return dehumidifierControlPanelBean;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mControlPanelBean");
        return null;
    }

    public final void setMControlPanelBean(DehumidifierControlPanelBean dehumidifierControlPanelBean) {
        Intrinsics.checkNotNullParameter(dehumidifierControlPanelBean, "<set-?>");
        this.mControlPanelBean = dehumidifierControlPanelBean;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void goDeviceErrorDetails(String deviceModel) {
        Intent intent = new Intent((Context) this, (Class<?>) ErrorListInfoAty.class);
        intent.putExtra(this.mErrorCodeKey, deviceModel);
        if (StringsKt.equals$default(deviceModel, StubApp.getString2(13030), false, 2, (Object) null) && (this.mDeviceListBean.getProduct_id().equals(StubApp.getString2(13440)) || this.mDeviceListBean.getProduct_id().equals(StubApp.getString2(13402)))) {
            intent.putExtra(this.mErrorCodeKey, StubApp.getString2(13005));
        }
        startActivity(intent);
    }

    protected String getDehumidifierJsonFile() {
        return "";
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.deye.activity.device.base.PublicConstantAty, com.deye.activity.device.base.BaseActivity
    public void onCreate(Bundle savedInstanceState) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        super.onCreate(savedInstanceState);
        ViewDataBinding contentView = DataBindingUtil.setContentView((Activity) this, R.layout.deye_dehumidifier_base_ui);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
        setMBaseAtyUiBinding((DeyeDehumidifierBaseUiBinding) contentView);
        Object object = JSON.parseObject(AssetsFileRead.getJson(getDehumidifierJsonFile(), (Context) this), DehumidifierControlPanelBean.class);
        Intrinsics.checkNotNullExpressionValue(object, "parseObject(...)");
        setMControlPanelBean((DehumidifierControlPanelBean) object);
        LogUtil.d(StubApp.getString2(13540) + getMControlPanelBean().getDeviceModel() + StubApp.getString2(13541) + JSON.toJSON(getMControlPanelBean()));
        reInitControlPanelBean();
        initViews(getMControlPanelBean());
        setHeadLayout();
        setOnClickListsner();
        ViewGroup.LayoutParams layoutParams = getMBaseAtyUiBinding().actionbarBlack.rlBarRootView.getLayoutParams();
        Intrinsics.checkNotNullExpressionValue(layoutParams, "getLayoutParams(...)");
        layoutParams.height += getStatusBarHeight();
        getMBaseAtyUiBinding().actionbarBlack.rlBarRootView.setLayoutParams(layoutParams);
        if (getMControlPanelBean().isHasScheduler()) {
            getMBaseAtyUiBinding().rlAppointment.setVisibility(0);
        } else {
            getMBaseAtyUiBinding().rlAppointment.setVisibility(8);
        }
        getMBaseAtyUiBinding().rlAppointment.setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DehumidifierControlPanelUIAty.onCreate$lambda$0(this.f$0, view);
            }
        });
        getMBaseAtyUiBinding().rlHumidityData.setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DehumidifierControlPanelUIAty.onCreate$lambda$1(this.f$0, view);
            }
        });
        if (getMControlPanelBean().isHasDelayer()) {
            getMBaseAtyUiBinding().delayedShutdownControl.setVisibility(0);
            getMBaseAtyUiBinding().delayedShutdownControl.setOnSwitchChangeListener(new DelayedShutdownControlView.OnSwitchChangeListener() { // from class: com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty.onCreate.3
                @Override // com.deye.views.control.DelayedShutdownControlView.OnSwitchChangeListener
                public void onSwitchChanged(boolean isChecked) {
                    DehumidifierControlPanelUIAty.this.onDelayedShutdownSwitchChanged(isChecked);
                }
            });
            getMBaseAtyUiBinding().delayedShutdownControl.setOnTimeClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DehumidifierControlPanelUIAty.onCreate$lambda$2(this.f$0, view);
                }
            });
        } else {
            getMBaseAtyUiBinding().delayedShutdownControl.setVisibility(8);
        }
        getMBaseAtyUiBinding().scrollView2.post(new Runnable() { // from class: com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                DehumidifierControlPanelUIAty.onCreate$lambda$3(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(DehumidifierControlPanelUIAty this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.goSchedulerAty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(DehumidifierControlPanelUIAty this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIntent(new Intent((Context) this$0.mContext, (Class<?>) MyReactActivity.class));
        this$0.getIntent().putExtra(StubApp.getString2(13144), StubApp.getString2(13591));
        Bundle bundle = new Bundle();
        bundle.putString(StubApp.getString2(831), this$0.mDeviceId);
        this$0.getIntent().putExtra(StubApp.getString2(13145), bundle);
        this$0.startActivity(this$0.getIntent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(DehumidifierControlPanelUIAty this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onDelayedShutdownTimeClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3(DehumidifierControlPanelUIAty this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int[] iArr = new int[2];
        this$0.getMBaseAtyUiBinding().arcPanel.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        this$0.getMBaseAtyUiBinding().scrollView2.setDisAllowRect(i, i2, this$0.getMBaseAtyUiBinding().arcPanel.getWidth() + i, this$0.getMBaseAtyUiBinding().arcPanel.getHeight() + i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected final void goSchedulerAty() {
        Intent intent = new Intent((Context) this, (Class<?>) SchedulerListAty.class);
        intent.putExtra(StubApp.getString2(13055), this.mDeviceId);
        intent.putExtra(StubApp.getString2(13306), this.mProductId);
        intent.putExtra(StubApp.getString2(13485), getMControlPanelBean().getHumidity());
        startActivity(intent);
    }

    protected void onDelayedShutdownSwitchChanged(boolean isChecked) {
        if (this.dehumidifierBean == null) {
            return;
        }
        if (isChecked) {
            showDelayedShutdownTimePicker();
            return;
        }
        getDehumidifierBean().setPoweroff_hour(StubApp.getString2(701));
        getMBaseAtyUiBinding().delayedShutdownControl.updateStatus(getDehumidifierBean());
        CommandManger commandManger = CommandManger.INSTANCE;
        String mDeviceId = this.mDeviceId;
        Intrinsics.checkNotNullExpressionValue(mDeviceId, "mDeviceId");
        String mProductId = this.mProductId;
        Intrinsics.checkNotNullExpressionValue(mProductId, "mProductId");
        commandManger.sendPoweroffTimeCommand(mDeviceId, mProductId, 0, 0, this.mDeviceListBean.isFogPlatform(), this.mDeviceListBean.isUseComboProtocol(), getDehumidifierBean());
    }

    protected void onDelayedShutdownTimeClick() {
        showDelayedShutdownTimePicker();
    }

    protected void showDelayedShutdownTimePicker() {
        if (this.dehumidifierBean == null) {
            return;
        }
        int i = MMKV.defaultMMKV().getInt(StubApp.getString2(13590), 6);
        DelayedShutdownTimePickerHelper delayedShutdownTimePickerHelper = new DelayedShutdownTimePickerHelper(this);
        delayedShutdownTimePickerHelper.setOnTimeSelectedListener(new DelayedShutdownTimePickerHelper.OnTimeSelectedListener() { // from class: com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty.showDelayedShutdownTimePicker.1
            @Override // com.deye.helper.DelayedShutdownTimePickerHelper.OnTimeSelectedListener
            public void onTimeSelected(int hour) {
                MMKV.defaultMMKV().putInt(StubApp.getString2(13590), hour);
                DehumidifierControlPanelUIAty.this.getMBaseAtyUiBinding().delayedShutdownControl.setChecked(true);
                CommandManger commandManger = CommandManger.INSTANCE;
                String str = DehumidifierControlPanelUIAty.this.mDeviceId;
                Intrinsics.checkNotNullExpressionValue(str, "access$getMDeviceId$p$s-1647576020(...)");
                String str2 = DehumidifierControlPanelUIAty.this.mProductId;
                Intrinsics.checkNotNullExpressionValue(str2, "access$getMProductId$p$s-1647576020(...)");
                commandManger.sendPoweroffTimeCommand(str, str2, hour, 0, DehumidifierControlPanelUIAty.this.mDeviceListBean.isFogPlatform(), DehumidifierControlPanelUIAty.this.mDeviceListBean.isUseComboProtocol(), DehumidifierControlPanelUIAty.this.getDehumidifierBean());
                DehumidifierControlPanelUIAty.this.getDehumidifierBean().setPoweroff_remain_hour(String.valueOf(hour));
                DehumidifierControlPanelUIAty.this.getDehumidifierBean().setPoweroff_remain_minute(StubApp.getString2(701));
                DehumidifierControlPanelUIAty.this.getDehumidifierBean().setPoweroff_hour(String.valueOf(hour));
                DehumidifierControlPanelUIAty.this.getMBaseAtyUiBinding().delayedShutdownControl.updateStatus(DehumidifierControlPanelUIAty.this.getDehumidifierBean());
            }
        });
        delayedShutdownTimePickerHelper.showTimePickerDialog(i);
    }

    @Override // com.deye.activity.device.base.BaseActivity
    protected void onResume() {
        super.onResume();
    }

    @Override // com.deye.activity.device.base.BaseActivity
    protected void onStop() {
        super.onStop();
    }

    protected final void initViews(DehumidifierControlPanelBean controlPanelBean) {
        if (controlPanelBean != null) {
            if (controlPanelBean.getAnion() == null) {
                getMBaseAtyUiBinding().controlAnionView.setVisibility(8);
            }
            if (controlPanelBean.getWaterPump() == null) {
                getMBaseAtyUiBinding().controlWaterPumpView.setVisibility(8);
            }
            if (controlPanelBean.getChildLock() == null) {
                getMBaseAtyUiBinding().controlLockView.setVisibility(8);
            }
            if (controlPanelBean.getSwingWind() == null) {
                getMBaseAtyUiBinding().controlSwingWindView.setVisibility(8);
            }
            if (controlPanelBean.getUvLight() == null) {
                getMBaseAtyUiBinding().controlUvLight.setVisibility(8);
            }
            if (controlPanelBean.getDisplayScreen() == null) {
                getMBaseAtyUiBinding().controlDisplay.setVisibility(8);
            }
            if (controlPanelBean.getTone() == null) {
                getMBaseAtyUiBinding().controlTone.setVisibility(8);
            }
            if (controlPanelBean.getHasHumidityData() == null || !controlPanelBean.getHasHumidityData().booleanValue()) {
                getMBaseAtyUiBinding().rlHumidityData.setVisibility(8);
            } else {
                getMBaseAtyUiBinding().rlHumidityData.setVisibility(0);
            }
            if (controlPanelBean.isHasPartsManager()) {
                getMBaseAtyUiBinding().partsView.setVisibility(0);
            } else {
                getMBaseAtyUiBinding().partsView.setVisibility(8);
            }
            getMBaseAtyUiBinding().llDeviceError.setVisibility(8);
            if (getMControlPanelBean().getSpeed().getName() == null || getMControlPanelBean().getSpeed().getName().length <= 0) {
                getMBaseAtyUiBinding().speedParentView.setVisibility(8);
            } else {
                WindSpeedParentView windSpeedParentView = getMBaseAtyUiBinding().speedParentView;
                SpeedBean speed = getMControlPanelBean().getSpeed();
                Intrinsics.checkNotNullExpressionValue(speed, "getSpeed(...)");
                windSpeedParentView.setSpeedData(speed);
            }
            getMBaseAtyUiBinding().mrvMode.initData(getMControlPanelBean().getMode(), getMControlPanelBean().getDeviceModel());
            ArcPanel arcPanel = getMBaseAtyUiBinding().arcPanel;
            HumidityBean humidity = getMControlPanelBean().getHumidity();
            Intrinsics.checkNotNullExpressionValue(humidity, "getHumidity(...)");
            arcPanel.setMinMax(humidity);
        }
    }

    protected final void setOffLineLayout(boolean onlineState) {
        if (onlineState) {
            dismissNoNetTipDialog();
        } else {
            stopWaiting();
            showNoNetTipDialog();
        }
    }
}
