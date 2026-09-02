package com.deye.activity.device.dehumidifier.device;

import com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty;
import com.stub.StubApp;
import kotlin.Metadata;

/* compiled from: DeYeU20AirAty.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0006H\u0014J\b\u0010\b\u001a\u00020\u0004H\u0014¨\u0006\t"}, d2 = {"Lcom/deye/activity/device/dehumidifier/device/DeYeU20AirAty;", "Lcom/deye/activity/device/dehumidifier/base/DehumidifierControlPanelDataAty;", "()V", "createModeResult", "", "mode", "", "getDehumidifierJsonFile", "setUpCoverView", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DeYeU20AirAty extends DehumidifierControlPanelDataAty {
    static {
        StubApp.interface11(14135);
    }

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected native void createModeResult(String mode);

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected native String getDehumidifierJsonFile();

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected native void setUpCoverView();
}
