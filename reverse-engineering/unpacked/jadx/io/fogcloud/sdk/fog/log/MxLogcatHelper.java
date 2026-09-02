package io.fogcloud.sdk.fog.log;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class MxLogcatHelper extends Service {
    boolean readlog = true;
    Thread thread;

    static {
        StubApp.interface11(34425);
    }

    private native void log();

    /* JADX INFO: Access modifiers changed from: private */
    public native void log2();

    private native void writeTofile(String str);

    @Override // android.app.Service
    public native IBinder onBind(Intent intent);

    @Override // android.app.Service
    public native void onCreate();

    @Override // android.app.Service
    public native void onDestroy();

    @Override // android.app.Service
    public native void onStart(Intent intent, int i);

    /* renamed from: io.fogcloud.sdk.fog.log.MxLogcatHelper$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MxLogcatHelper.this.log2();
        }
    }
}
