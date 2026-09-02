package com.deye.utils;

import android.util.Patterns;
import android.webkit.URLUtil;
import com.stub.StubApp;
import java.util.regex.Pattern;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class RegularUtils {
    public static boolean isPhone(String str) {
        return Pattern.compile(StubApp.getString2(14413)).matcher(str).matches() && str.length() == 11 && str.startsWith(StubApp.getString2(2546));
    }

    public static boolean isPassWord(String str) {
        return Pattern.compile(StubApp.getString2(14412)).matcher(str).matches();
    }

    public static boolean isNotContainChinese(String str) {
        return !Pattern.compile(StubApp.getString2(14411)).matcher(str).find();
    }

    public static boolean isUrl(String str) {
        return Patterns.WEB_URL.matcher(str).matches() || URLUtil.isValidUrl(str);
    }

    public static boolean isEmail(String str) {
        return str.matches(StubApp.getString2(14410));
    }

    public String checkPassword(String str) {
        boolean zMatches = str.matches(StubApp.getString2(14414));
        String string2 = StubApp.getString2(14415);
        if (zMatches || str.matches(StubApp.getString2(14416)) || str.matches(StubApp.getString2(14417))) {
            return string2;
        }
        boolean zMatches2 = str.matches(StubApp.getString2(14418));
        String string22 = StubApp.getString2(14419);
        return (zMatches2 || str.matches(StubApp.getString2(14420)) || str.matches(StubApp.getString2(14421))) ? string22 : str.matches(StubApp.getString2(14422)) ? StubApp.getString2(14423) : str;
    }
}
