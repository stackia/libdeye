package com.deye.activity.device.dehumidifier.device;

import android.os.Bundle;
import com.deye.activity.device.dehumidifier.base.DehumidifierWithCurtainDryingAty;
import com.stub.StubApp;
import kotlin.Metadata;

/* compiled from: DeYeV60A3Aty.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0006H\u0014J\u0012\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\f\u001a\u00020\u0004H\u0014¨\u0006\r"}, d2 = {"Lcom/deye/activity/device/dehumidifier/device/DeYeV60A3Aty;", "Lcom/deye/activity/device/dehumidifier/base/DehumidifierWithCurtainDryingAty;", "()V", "createModeResult", "", "mode", "", "getDehumidifierJsonFile", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setContinueView", "setUpCoverView", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DeYeV60A3Aty extends DehumidifierWithCurtainDryingAty {
    static {
        StubApp.interface11(14139);
    }

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected native void createModeResult(String mode);

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected native String getDehumidifierJsonFile();

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty, com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelMqttAty, com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty, com.deye.activity.device.base.PublicConstantAty, com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty
    public native void setContinueView(String mode);

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected native void setUpCoverView();
}
