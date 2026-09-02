package io.fogcloud.sdk.fog.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogDebug;
import java.util.UUID;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class MqttDeviceIdUtil {
    private static final String KEY_DEVICE_ID = StubApp.getString2(45126);
    private static final String PREFS_NAME = StubApp.getString2(45125);

    public static String getStableDeviceId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(StubApp.getString2(45125), 0);
        String string2 = StubApp.getString2(45126);
        String string = sharedPreferences.getString(string2, null);
        if (string != null && !string.isEmpty()) {
            LogDebug.INSTANCE.log(StubApp.getString2(45127) + string);
            return string;
        }
        String strGenerateDeviceId = generateDeviceId(context);
        sharedPreferences.edit().putString(string2, strGenerateDeviceId).apply();
        LogDebug.INSTANCE.log(StubApp.getString2(45128) + strGenerateDeviceId);
        return strGenerateDeviceId;
    }

    private static String generateDeviceId(Context context) {
        String string2 = StubApp.getString2(45122);
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), StubApp.getString2("10449"));
            if (string != null && !string.isEmpty() && !StubApp.getString2("10450").equals(string)) {
                String strSubstring = string.substring(0, Math.min(8, string.length()));
                LogDebug.INSTANCE.log(string2 + strSubstring);
                return strSubstring;
            }
        } catch (Exception e) {
            LogDebug.INSTANCE.log(StubApp.getString2(45123) + e.getMessage());
        }
        String strSubstring2 = UUID.randomUUID().toString().replace(StubApp.getString2(1004), "").substring(0, 8);
        LogDebug.INSTANCE.log(StubApp.getString2(45124) + strSubstring2);
        return strSubstring2;
    }

    public static String buildClientId(Context context, String str) {
        String str2 = str + StubApp.getString2(3) + getStableDeviceId(context);
        LogDebug.INSTANCE.log(StubApp.getString2(45120) + str2 + StubApp.getString2(45121));
        return str2;
    }
}
