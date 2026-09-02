package com.deye.activity.device.dehumidifier.device;

import android.os.Bundle;
import com.deye.activity.device.dehumidifier.base.DehumidifierWithCurtainDryingAty;
import com.deye.views.recycleview.U20ProModeView;
import com.stub.StubApp;
import kotlin.Metadata;

/* compiled from: DeYeU20ProAty.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\bH\u0014J\u0012\u0010\n\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\r\u001a\u00020\u0006H\u0002J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\u000f\u001a\u00020\u0006H\u0014J\b\u0010\u0010\u001a\u00020\u0006H\u0016J\b\u0010\u0011\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/deye/activity/device/dehumidifier/device/DeYeU20ProAty;", "Lcom/deye/activity/device/dehumidifier/base/DehumidifierWithCurtainDryingAty;", "()V", "mU20ProModeView", "Lcom/deye/views/recycleview/U20ProModeView;", "createModeResult", "", "mode", "", "getDehumidifierJsonFile", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "replaceWithU20ProModeView", "setContinueView", "setUpCoverView", "setUpDeviceStateDate", "setupU20ProModeClickListener", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DeYeU20ProAty extends DehumidifierWithCurtainDryingAty {
    private U20ProModeView mU20ProModeView;

    static {
        StubApp.interface11(14137);
    }

    private final native void replaceWithU20ProModeView();

    private final native void setupU20ProModeClickListener();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void setupU20ProModeClickListener$lambda$0(DeYeU20ProAty deYeU20ProAty, String str);

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

    @Override // com.deye.activity.device.dehumidifier.base.DehumidifierControlPanelDataAty
    public native void setUpDeviceStateDate();
}
