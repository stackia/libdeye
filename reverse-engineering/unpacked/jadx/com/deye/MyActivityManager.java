package com.deye;

import android.app.Activity;
import java.lang.ref.WeakReference;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class MyActivityManager {
    private static MyActivityManager sInstance = new MyActivityManager();
    private WeakReference<Activity> sCurrentActivityWeakRef;

    private MyActivityManager() {
    }

    public static MyActivityManager getInstance() {
        return sInstance;
    }

    public Activity getCurrentActivity() {
        WeakReference<Activity> weakReference = this.sCurrentActivityWeakRef;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public void setCurrentActivity(Activity activity) {
        this.sCurrentActivityWeakRef = new WeakReference<>(activity);
    }
}
