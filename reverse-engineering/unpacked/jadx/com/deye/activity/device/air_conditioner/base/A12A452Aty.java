package com.deye.activity.device.air_conditioner.base;

import com.stub.StubApp;
import kotlin.Metadata;

/* compiled from: A12A452Aty.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0014J\b\u0010\n\u001a\u00020\u0004H\u0014J\b\u0010\u000b\u001a\u00020\bH\u0014J\u0012\u0010\f\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/deye/activity/device/air_conditioner/base/A12A452Aty;", "Lcom/deye/activity/device/air_conditioner/base/AirConditionerControlPanelDataAty;", "()V", "mHumidityRecoderFlag", "", "mWindTypeRecoderFlag", "", "createModeResult", "", "mode", "getDehumidifierJsonFile", "setUpCoverView", "setupHumSetValue", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class A12A452Aty extends AirConditionerControlPanelDataAty {
    private int mWindTypeRecoderFlag = -1;
    private String mHumidityRecoderFlag = "";

    static {
        StubApp.interface11(14020);
    }

    private final native void setupHumSetValue(String mode);

    @Override // com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelUIAty
    protected native void createModeResult(String mode);

    @Override // com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelUIAty
    protected native String getDehumidifierJsonFile();

    @Override // com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelUIAty
    protected native void setUpCoverView();
}
