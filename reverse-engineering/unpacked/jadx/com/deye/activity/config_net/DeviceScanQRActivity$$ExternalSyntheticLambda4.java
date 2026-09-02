package com.deye.activity.config_net;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final /* synthetic */ class DeviceScanQRActivity$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ DeviceScanQRActivity f$1;

    public /* synthetic */ DeviceScanQRActivity$$ExternalSyntheticLambda4(boolean z, DeviceScanQRActivity deviceScanQRActivity) {
        this.f$0 = z;
        this.f$1 = deviceScanQRActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DeviceScanQRActivity.stopWaitingDialog$lambda$6(this.f$0, this.f$1);
    }
}
