package com.deye.activity.device.dehumidifier.device;

import com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty;
import com.stub.StubApp;
import kotlin.Metadata;

/* compiled from: DeYeV58A3Aty.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0014J\b\u0010\n\u001a\u00020\u0004H\u0014J\b\u0010\u000b\u001a\u00020\bH\u0014J\b\u0010\f\u001a\u00020\bH\u0002J\b\u0010\r\u001a\u00020\bH\u0002J\u0012\u0010\u000e\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\u000f\u001a\u00020\bH\u0002J\u0012\u0010\u0010\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\u0011\u001a\u00020\bH\u0002J\b\u0010\u0012\u001a\u00020\bH\u0002J\b\u0010\u0013\u001a\u00020\bH\u0002J\b\u0010\u0014\u001a\u00020\bH\u0002J\u0012\u0010\u0015\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/deye/activity/device/dehumidifier/device/DeYeV58A3Aty;", "Lcom/deye/activity/device/dehumidifier/base/DehumidifierControlPanelDataAty;", "()V", "mHumidityRecoderFlag", "", "mWindTypeRecoderFlag", "", "createModeResult", "", "mode", "getDehumidifierJsonFile", "setUpCoverView", "setupAnionCoverView", "setupDefaultWindSpeed_Humidity", "setupHumSetValue", "setupHumidityCoverView", "setupLockAndAnionSwitchValue", "setupModeCoverView", "setupRecordedHumidity", "setupSchedulerCoverView", "setupWindSpeedCoverView", "setupWindSpeedValue", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DeYeV58A3Aty extends DehumidifierControlPanelDataAty {
    private int mWindTypeRecoderFlag = -1;
    private String mHumidityRecoderFlag = "";

    static {
        StubApp.interface11(14138);
    }

    private final native void setupAnionCoverView();

    private final native void setupDefaultWindSpeed_Humidity();

    private final native void setupHumSetValue(String mode);

    private final native void setupHumidityCoverView();

    private final native void setupLockAndAnionSwitchValue(String mode);

    private final native void setupModeCoverView();

    private final native void setupRecordedHumidity();

    private final native void setupSchedulerCoverView();

    private final native void setupWindSpeedCoverView();

    private final native void setupWindSpeedValue(String mode);

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected native void createModeResult(String mode);

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected native String getDehumidifierJsonFile();

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelUIAty
    protected native void setUpCoverView();
}
