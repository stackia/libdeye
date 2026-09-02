package com.deye.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class NotificationClickReceiver extends BroadcastReceiver {
    static {
        StubApp.interface11(15146);
    }

    @Override // android.content.BroadcastReceiver
    public native void onReceive(Context context, Intent intent);
}
