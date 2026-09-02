package com.deye;

import android.content.Context;
import com.stub.StubApp;
import java.lang.Thread;
import java.util.HashMap;
import java.util.Map;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private Map<String, String> infos = new HashMap();
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    public static final String TAG = StubApp.getString2(13000);
    private static CrashHandler INSTANCE = new CrashHandler();

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
    }

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        this.mContext = context;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
}
