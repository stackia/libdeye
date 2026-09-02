package com.deye.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.util.Log;
import com.deye.MyActivityManager;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import java.util.Locale;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class LanUtils {
    public static final String KEY_LAN = StubApp.getString2(14332);

    public static Context setAppLanguage(Context context) {
        Locale locale = Locale.getDefault();
        int language = getLanguage(context);
        Log.e(StubApp.getString2(14334), StubApp.getString2(14335) + language);
        if (language == 1) {
            locale = Locale.CHINESE;
        } else if (language == 2) {
            locale = Locale.ENGLISH;
        }
        updateHttpRequestLanguage(language);
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    public static void changeLan(Activity activity) {
        if (getLanguage(activity) == 1) {
            saveLanguage(activity, 2);
        } else {
            saveLanguage(activity, 1);
        }
    }

    public static int getLanguage(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(StubApp.getString2(14332), ChannelUtil.isOversea() ? 2 : 1);
    }

    public static boolean isZhLanguage() {
        return getLanguage(MyActivityManager.getInstance().getCurrentActivity()) == 1;
    }

    public static boolean isEnLanguage() {
        return getLanguage(MyActivityManager.getInstance().getCurrentActivity()) == 2;
    }

    public static void saveLanguage(Context context, int i) {
        Log.e(StubApp.getString2(14334), StubApp.getString2(14333) + i);
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(StubApp.getString2(14332), i).commit();
        updateHttpRequestLanguage(i);
    }

    private static void updateHttpRequestLanguage(int i) {
        String string2 = StubApp.getString2(14334);
        String string22 = StubApp.getString2(14336);
        String strConvertToLanguageTag = convertToLanguageTag(i);
        try {
            DeYeHttpRequestManager.getInstance().setLan(strConvertToLanguageTag);
            Log.d(string2, string22 + strConvertToLanguageTag);
        } catch (Exception e) {
            Log.e(string2, StubApp.getString2(14337) + e.getMessage());
        }
    }

    public static String convertToLanguageTag(int i) {
        switch (i) {
            case 1:
                return StubApp.getString2(14331);
            case 2:
                return StubApp.getString2(13761);
            case 3:
                return StubApp.getString2(14330);
            case 4:
                return StubApp.getString2(14329);
            case 5:
                return StubApp.getString2(14328);
            case 6:
                return StubApp.getString2(14327);
            case 7:
                return StubApp.getString2(14326);
            case 8:
                return StubApp.getString2(14325);
            case 9:
                return StubApp.getString2(14324);
            case 10:
                return StubApp.getString2(14323);
            case 11:
                return StubApp.getString2(14322);
            case 12:
                return StubApp.getString2(14321);
            case 13:
                return StubApp.getString2(14320);
            default:
                Locale locale = Locale.getDefault();
                return locale.getLanguage() + StubApp.getString2(1004) + locale.getCountry();
        }
    }
}
