package com.deye.combo.application;

import android.app.Application;
import com.deye.combo.bluetooth.BluetoothSM;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ComboDebugApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        BluetoothSM.init(this);
    }
}
