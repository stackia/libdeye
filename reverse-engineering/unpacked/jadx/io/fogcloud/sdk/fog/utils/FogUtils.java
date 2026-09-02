package io.fogcloud.sdk.fog.utils;

import android.content.Context;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class FogUtils {
    private static Toast toast;

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static String getTime() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static boolean isNullString(String str) {
        return str == null || str.length() <= 0;
    }

    public static void showShortToast(Context context, CharSequence charSequence) {
        Toast toastMakeText = Toast.makeText(context, charSequence, 0);
        toast = toastMakeText;
        toastMakeText.setGravity(17, 0, 0);
        toast.show();
    }

    public static String getTimeString(String str) {
        int iIntValue = Integer.valueOf(str).intValue();
        if (iIntValue < 10) {
            return StubApp.getString2(701) + iIntValue;
        }
        return "" + iIntValue;
    }

    public static int getCode(String str) {
        return JSON.parseObject(JSON.parseObject(str).getString(StubApp.getString2(13082))).getInteger(StubApp.getString2(109)).intValue();
    }
}
