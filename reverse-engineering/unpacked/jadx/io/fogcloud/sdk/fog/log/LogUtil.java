package io.fogcloud.sdk.fog.log;

import android.util.Log;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class LogUtil {
    private static final String FLAG = StubApp.getString2(45164);
    private static final String TAG = StubApp.getString2(45165);
    private static boolean isDebug = true;

    public static void isEnableDebug(boolean z) {
        isDebug = z;
    }

    public static void i(String str, String str2) {
        if (isDebug) {
            StringBuilder sbAppend = new StringBuilder().append(str).append(StubApp.getString2(45164));
            if (str2 == null) {
                str2 = "";
            }
            Log.i(StubApp.getString2(45165), sbAppend.append(str2).toString());
        }
    }

    public static void i(Object obj, String str) {
        if (isDebug) {
            StringBuilder sbAppend = new StringBuilder().append(obj.getClass().getSimpleName()).append(StubApp.getString2(45164));
            if (str == null) {
                str = "";
            }
            Log.i(StubApp.getString2(45165), sbAppend.append(str).toString());
        }
    }

    public static void i(String str) {
        if (isDebug) {
            StringBuilder sb = new StringBuilder(StubApp.getString2(45168));
            if (str == null) {
                str = "";
            }
            Log.i(StubApp.getString2(45165), sb.append(str).toString());
        }
    }

    public static void d(String str, String str2) {
        if (isDebug) {
            StringBuilder sbAppend = new StringBuilder().append(str).append(StubApp.getString2(45164));
            if (str2 == null) {
                str2 = "";
            }
            Log.d(StubApp.getString2(45165), sbAppend.append(str2).toString());
        }
    }

    public static void d(Object obj, String str) {
        if (isDebug) {
            StringBuilder sbAppend = new StringBuilder().append(obj.getClass().getSimpleName()).append(StubApp.getString2(45164));
            if (str == null) {
                str = "";
            }
            Log.d(StubApp.getString2(45165), sbAppend.append(str).toString());
        }
    }

    public static void d(String str) {
        if (isDebug) {
            StringBuilder sb = new StringBuilder(StubApp.getString2(45166));
            if (str == null) {
                str = "";
            }
            Log.d(StubApp.getString2(45165), sb.append(str).toString());
        }
    }

    public static void w(String str, String str2) {
        if (isDebug) {
            StringBuilder sbAppend = new StringBuilder().append(str).append(StubApp.getString2(45164));
            if (str2 == null) {
                str2 = "";
            }
            Log.w(StubApp.getString2(45165), sbAppend.append(str2).toString());
        }
    }

    public static void w(Object obj, String str) {
        if (isDebug) {
            StringBuilder sbAppend = new StringBuilder().append(obj.getClass().getSimpleName()).append(StubApp.getString2(45164));
            if (str == null) {
                str = "";
            }
            Log.w(StubApp.getString2(45165), sbAppend.append(str).toString());
        }
    }

    public static void w(String str) {
        if (isDebug) {
            StringBuilder sb = new StringBuilder(StubApp.getString2(45170));
            if (str == null) {
                str = "";
            }
            Log.w(StubApp.getString2(45165), sb.append(str).toString());
        }
    }

    public static void e(String str, String str2) {
        if (isDebug) {
            StringBuilder sbAppend = new StringBuilder().append(str).append(StubApp.getString2(45164));
            if (str2 == null) {
                str2 = "";
            }
            Log.e(StubApp.getString2(45165), sbAppend.append(str2).toString());
        }
    }

    public static void e(Object obj, String str) {
        if (isDebug) {
            StringBuilder sbAppend = new StringBuilder().append(obj.getClass().getSimpleName()).append(StubApp.getString2(45164));
            if (str == null) {
                str = "";
            }
            Log.e(StubApp.getString2(45165), sbAppend.append(str).toString());
        }
    }

    public static void e(String str) {
        if (isDebug) {
            StringBuilder sb = new StringBuilder(StubApp.getString2(45167));
            if (str == null) {
                str = "";
            }
            Log.e(StubApp.getString2(45165), sb.append(str).toString());
        }
    }

    public static void v(String str, String str2) {
        if (isDebug) {
            StringBuilder sbAppend = new StringBuilder().append(str).append(StubApp.getString2(45164));
            if (str2 == null) {
                str2 = "";
            }
            Log.v(StubApp.getString2(45165), sbAppend.append(str2).toString());
        }
    }

    public static void v(Object obj, String str) {
        if (isDebug) {
            StringBuilder sbAppend = new StringBuilder().append(obj.getClass().getSimpleName()).append(StubApp.getString2(45164));
            if (str == null) {
                str = "";
            }
            Log.v(StubApp.getString2(45165), sbAppend.append(str).toString());
        }
    }

    public static void v(String str) {
        if (isDebug) {
            StringBuilder sb = new StringBuilder(StubApp.getString2(45169));
            if (str == null) {
                str = "";
            }
            Log.v(StubApp.getString2(45165), sb.append(str).toString());
        }
    }
}
