package com.deye.combo.log;

import android.util.Log;
import com.deye.combo.callback.BleLogIntercept;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class BleLog {
    private static final String TAG = StubApp.getString2(13912);
    private static boolean debug;
    public static BleLogIntercept logIntercept;

    public static void setLogIntercept(BleLogIntercept bleLogIntercept) {
        logIntercept = bleLogIntercept;
    }

    public static void i(String str, String str2) {
        BleLogIntercept bleLogIntercept = logIntercept;
        String string2 = StubApp.getString2(13913);
        if (bleLogIntercept != null) {
            bleLogIntercept.onLog(string2 + str, str2);
        }
        if (debug) {
            Log.i(string2 + str, str2);
        }
    }

    public static void w(String str, String str2) {
        BleLogIntercept bleLogIntercept = logIntercept;
        String string2 = StubApp.getString2(13913);
        if (bleLogIntercept != null) {
            bleLogIntercept.onLog(string2 + str, str2);
        }
        if (debug) {
            Log.w(string2 + str, str2);
        }
    }
}
