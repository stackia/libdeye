package com.deye.activity.device.humidifier;

import com.deye.activity.device.humidifier.base.HumidifierPanelDataAty;
import com.stub.StubApp;
import kotlin.Metadata;

/* compiled from: H7Aty.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0006H\u0014J\b\u0010\b\u001a\u00020\u0004H\u0016¨\u0006\t"}, d2 = {"Lcom/deye/activity/device/humidifier/H7Aty;", "Lcom/deye/activity/device/humidifier/base/HumidifierPanelDataAty;", "()V", "createModeResult", "", "mode", "", "getDehumidifierJsonFile", "updateViewByBean", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class H7Aty extends HumidifierPanelDataAty {
    static {
        StubApp.interface11(14146);
    }

    @Override // com.deye.activity.device.humidifier.base.HumidifierPanelUIAty
    protected native void createModeResult(String mode);

    @Override // com.deye.activity.device.humidifier.base.HumidifierPanelUIAty
    protected native String getDehumidifierJsonFile();

    @Override // com.deye.activity.device.humidifier.base.HumidifierPanelDataAty
    public native void updateViewByBean();
}
