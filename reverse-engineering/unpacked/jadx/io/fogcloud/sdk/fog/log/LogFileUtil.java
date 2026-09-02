package io.fogcloud.sdk.fog.log;

import android.content.Context;
import android.os.Environment;
import com.stub.StubApp;
import com.ut.device.AidConstants;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class LogFileUtil {
    public static String getMxChipLogFilePath(Context context) {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + StubApp.getString2(45162) + getPid(context);
    }

    public static String getTimeFlag(Context context) {
        String pid = getPid(context);
        String string2 = StubApp.getString2(1382);
        String string22 = StubApp.getString2(3);
        pid.replace(string2, string22);
        return (StubApp.getString2(AidConstants.EVENT_REQUEST_FAILED) + pid + string22 + getTime() + string22).replace(StubApp.getString2(1004), string22).replace(string2, string22);
    }

    public static String getDateFlag(Context context) {
        String pid = getPid(context);
        String string2 = StubApp.getString2(1382);
        String string22 = StubApp.getString2(3);
        pid.replace(string2, string22);
        return (StubApp.getString2(AidConstants.EVENT_REQUEST_FAILED) + pid + string22 + getDate() + string22).replace(StubApp.getString2(1004), string22).replace(string2, string22);
    }

    public static String getTime() {
        return new SimpleDateFormat(StubApp.getString2(45163)).format(new Date());
    }

    public static String getDate() {
        return new SimpleDateFormat(StubApp.getString2(13775)).format(new Date());
    }

    public static String getPid(Context context) {
        String str = context.getApplicationInfo().processName;
        String string2 = StubApp.getString2(1004);
        String string22 = StubApp.getString2(3);
        return str.replace(string2, string22).replace(StubApp.getString2(1382), string22);
    }
}
