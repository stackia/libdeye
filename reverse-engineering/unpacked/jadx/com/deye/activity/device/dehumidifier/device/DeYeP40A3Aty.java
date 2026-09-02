package com.deye.activity.device.dehumidifier.device;

import android.os.Bundle;
import com.deye.activity.device.dehumidifier.base.DehumidifierWithCurtainDryingAty;
import com.deye.entity.control_panel.dehumidifier.func.SpeedBean;
import com.stub.StubApp;
import java.util.List;
import kotlin.Metadata;

/* compiled from: DeYeP40A3Aty.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0014J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\b\u0010\u0010\u001a\u00020\u0004H\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0014J\u0012\u0010\u0012\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u0016\u001a\u00020\fH\u0014J\u0012\u0010\u0017\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u0019\u001a\u00020\fH\u0014J\b\u0010\u001a\u001a\u00020\fH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/deye/activity/device/dehumidifier/device/DeYeP40A3Aty;", "Lcom/deye/activity/device/dehumidifier/base/DehumidifierWithCurtainDryingAty;", "()V", "fullSpeedBean", "Lcom/deye/entity/control_panel/dehumidifier/func/SpeedBean;", "buildSpeedBean", "source", "indexes", "", "", "copySpeedBean", "createModeResult", "", "mode", "", "createSpeedBeanWithoutAuto", "ensureFullSpeedBean", "getDehumidifierJsonFile", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "parseLocalData", "reInitControlPanelBean", "refreshSpeedOptions", "setContinueView", "setUpCoverView", "setUpDeviceStateDate", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DeYeP40A3Aty extends DehumidifierWithCurtainDryingAty {
    private SpeedBean fullSpeedBean;

    static {
        StubApp.interface11(14128);
    }

    private final native SpeedBean buildSpeedBean(SpeedBean source, List<Integer> indexes);

    private final native SpeedBean copySpeedBean(SpeedBean source);

    private final native SpeedBean createSpeedBeanWithoutAuto(SpeedBean source);

    private final native SpeedBean ensureFullSpeedBean();

    private final native void refreshSpeedOptions(String mode);

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected native void createModeResult(String mode);

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected native String getDehumidifierJsonFile();

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty, com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelMqttAty, com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty, com.deye.activity.device.base.PublicConstantAty, com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty
    public native void parseLocalData(String mode);

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty, com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected native void reInitControlPanelBean();

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty
    public native void setContinueView(String mode);

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected native void setUpCoverView();

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty
    public native void setUpDeviceStateDate();
}
