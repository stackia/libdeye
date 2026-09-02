package com.deye.activity.device.loop_fan.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.alibaba.fastjson.JSON;
import com.deye.activity.device.ErrorListInfoAty;
import com.deye.activity.device.SchedulerListAty;
import com.deye.activity.device.base.PublicConstantAty;
import com.deye.entity.control_panel.dehumidifier.func.SpeedBean;
import com.deye.entity.control_panel.loop_fan.LoopFanControlPanelBean;
import com.deye.utils.AssetsFileRead;
import com.deye.views.WindSpeedParentView;
import com.mxchipapp.R;
import com.mxchipapp.databinding.DeyeLoopFanBaseUiBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.LoopFanBean;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoopFanControlPanelUIAty.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H$J\b\u0010\u0019\u001a\u00020\u0018H\u0014J\u0018\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u00180\u001bj\b\u0012\u0004\u0012\u00020\u0018`\u001cH$J\u0012\u0010\u001d\u001a\u00020\u00162\b\u0010\u001e\u001a\u0004\u0018\u00010\u0018H\u0004J\b\u0010\u001f\u001a\u00020\u0016H\u0004J\b\u0010 \u001a\u00020\u0016H$J\u0012\u0010!\u001a\u00020\u00162\b\u0010\"\u001a\u0004\u0018\u00010\nH\u0004J\u0012\u0010#\u001a\u00020\u00162\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020\u0016H\u0014J\b\u0010'\u001a\u00020\u0016H\u0014J\b\u0010(\u001a\u00020\u0016H\u0002J\u0010\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020+H\u0004J\b\u0010,\u001a\u00020\u0016H$J\b\u0010-\u001a\u00020\u0016H$J\b\u0010.\u001a\u00020\u0016H$R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006/"}, d2 = {"Lcom/deye/activity/device/loop_fan/base/LoopFanControlPanelUIAty;", "Lcom/deye/activity/device/base/PublicConstantAty;", "()V", "mBaseAtyUiBinding", "Lcom/mxchipapp/databinding/DeyeLoopFanBaseUiBinding;", "getMBaseAtyUiBinding", "()Lcom/mxchipapp/databinding/DeyeLoopFanBaseUiBinding;", "setMBaseAtyUiBinding", "(Lcom/mxchipapp/databinding/DeyeLoopFanBaseUiBinding;)V", "mControlPanelBean", "Lcom/deye/entity/control_panel/loop_fan/LoopFanControlPanelBean;", "getMControlPanelBean", "()Lcom/deye/entity/control_panel/loop_fan/LoopFanControlPanelBean;", "setMControlPanelBean", "(Lcom/deye/entity/control_panel/loop_fan/LoopFanControlPanelBean;)V", "mLoopFanBean", "Lio/fogcloud/sdk/fog/bean/LoopFanBean;", "getMLoopFanBean", "()Lio/fogcloud/sdk/fog/bean/LoopFanBean;", "setMLoopFanBean", "(Lio/fogcloud/sdk/fog/bean/LoopFanBean;)V", "createModeResult", "", "mode", "", "getDehumidifierJsonFile", "getErrorCodeList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "goDeviceErrorDetails", "deviceModel", "goSchedulerAty", "initView", "initViews", "controlPanelBean", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onStop", "setHeadLayout", "setOffLineLayout", "onlineState", "", "setOnClickListener", "setUpCoverView", "setView", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public abstract class LoopFanControlPanelUIAty extends PublicConstantAty {
    public DeyeLoopFanBaseUiBinding mBaseAtyUiBinding;
    public LoopFanControlPanelBean mControlPanelBean;
    public LoopFanBean mLoopFanBean;

    private final void setHeadLayout() {
    }

    protected abstract void createModeResult(String mode);

    protected abstract ArrayList<String> getErrorCodeList();

    protected abstract void initView();

    protected abstract void setOnClickListener();

    protected abstract void setUpCoverView();

    protected abstract void setView();

    public final DeyeLoopFanBaseUiBinding getMBaseAtyUiBinding() {
        DeyeLoopFanBaseUiBinding deyeLoopFanBaseUiBinding = this.mBaseAtyUiBinding;
        if (deyeLoopFanBaseUiBinding != null) {
            return deyeLoopFanBaseUiBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mBaseAtyUiBinding");
        return null;
    }

    public final void setMBaseAtyUiBinding(DeyeLoopFanBaseUiBinding deyeLoopFanBaseUiBinding) {
        Intrinsics.checkNotNullParameter(deyeLoopFanBaseUiBinding, "<set-?>");
        this.mBaseAtyUiBinding = deyeLoopFanBaseUiBinding;
    }

    public final LoopFanBean getMLoopFanBean() {
        LoopFanBean loopFanBean = this.mLoopFanBean;
        if (loopFanBean != null) {
            return loopFanBean;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mLoopFanBean");
        return null;
    }

    public final void setMLoopFanBean(LoopFanBean loopFanBean) {
        Intrinsics.checkNotNullParameter(loopFanBean, "<set-?>");
        this.mLoopFanBean = loopFanBean;
    }

    public final LoopFanControlPanelBean getMControlPanelBean() {
        LoopFanControlPanelBean loopFanControlPanelBean = this.mControlPanelBean;
        if (loopFanControlPanelBean != null) {
            return loopFanControlPanelBean;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mControlPanelBean");
        return null;
    }

    public final void setMControlPanelBean(LoopFanControlPanelBean loopFanControlPanelBean) {
        Intrinsics.checkNotNullParameter(loopFanControlPanelBean, "<set-?>");
        this.mControlPanelBean = loopFanControlPanelBean;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected final void goDeviceErrorDetails(String deviceModel) {
        Intent intent = new Intent((Context) this, (Class<?>) ErrorListInfoAty.class);
        intent.putExtra(this.mErrorCodeKey, deviceModel);
        startActivity(intent);
    }

    protected String getDehumidifierJsonFile() {
        return "";
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.deye.activity.device.base.PublicConstantAty, com.deye.activity.device.base.BaseActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding contentView = DataBindingUtil.setContentView((Activity) this, R.layout.deye_loop_fan_base_ui);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
        setMBaseAtyUiBinding((DeyeLoopFanBaseUiBinding) contentView);
        Object object = JSON.parseObject(AssetsFileRead.getJson(getDehumidifierJsonFile(), (Context) this), LoopFanControlPanelBean.class);
        Intrinsics.checkNotNullExpressionValue(object, "parseObject(...)");
        setMControlPanelBean((LoopFanControlPanelBean) object);
        LogUtil.d(StubApp.getString2(13540) + getMControlPanelBean().getDeviceModel() + StubApp.getString2(13541) + JSON.toJSON(getMControlPanelBean()));
        initViews(getMControlPanelBean());
        setHeadLayout();
        setOnClickListener();
        ViewGroup.LayoutParams layoutParams = getMBaseAtyUiBinding().actionbarBlack.rlBarRootView.getLayoutParams();
        Intrinsics.checkNotNullExpressionValue(layoutParams, "getLayoutParams(...)");
        layoutParams.height += getStatusBarHeight();
        getMBaseAtyUiBinding().actionbarBlack.rlBarRootView.setLayoutParams(layoutParams);
        getMBaseAtyUiBinding().rlAppointment.setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.loop_fan.base.LoopFanControlPanelUIAty$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoopFanControlPanelUIAty.onCreate$lambda$0(this.f$0, view);
            }
        });
        getMBaseAtyUiBinding().scrollView2.post(new Runnable() { // from class: com.deye.activity.device.loop_fan.base.LoopFanControlPanelUIAty$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                LoopFanControlPanelUIAty.onCreate$lambda$1(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(LoopFanControlPanelUIAty this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.goSchedulerAty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(LoopFanControlPanelUIAty this$0) {
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
        startActivity(intent);
    }

    @Override // com.deye.activity.device.base.BaseActivity
    protected void onResume() {
        super.onResume();
    }

    @Override // com.deye.activity.device.base.BaseActivity
    protected void onStop() {
        super.onStop();
    }

    protected final void initViews(LoopFanControlPanelBean controlPanelBean) {
        if (controlPanelBean != null) {
            getMBaseAtyUiBinding().llDeviceError.setVisibility(8);
            if (getMControlPanelBean().getSpeed().getName() == null || getMControlPanelBean().getSpeed().getName().length <= 0) {
                getMBaseAtyUiBinding().speedParentView.setVisibility(8);
            } else {
                WindSpeedParentView windSpeedParentView = getMBaseAtyUiBinding().speedParentView;
                SpeedBean speed = getMControlPanelBean().getSpeed();
                Intrinsics.checkNotNullExpressionValue(speed, "getSpeed(...)");
                windSpeedParentView.setSpeedData(speed);
            }
            getMBaseAtyUiBinding().mrvMode.initData(getMControlPanelBean().getMode());
            getMBaseAtyUiBinding().ryWindMode.initData(getMControlPanelBean().getWind_mode());
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
