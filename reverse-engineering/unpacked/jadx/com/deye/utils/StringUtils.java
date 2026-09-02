package com.deye.utils;

import com.stub.StubApp;
import java.util.regex.Pattern;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class StringUtils {
    public static String WAIT_SET = StubApp.getString2(14434);

    public static String createRandom(int i, int i2) {
        String string2;
        String str;
        boolean z = true;
        if (i == 0) {
            string2 = StubApp.getString2(14435);
        } else {
            string2 = (i != 1 && i == 2) ? StubApp.getString2(14437) : StubApp.getString2(14436);
        }
        int length = string2.length();
        do {
            str = "";
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                int iFloor = (int) Math.floor(Math.random() * length);
                char cCharAt = string2.charAt(iFloor);
                if ('0' <= cCharAt && cCharAt <= '9') {
                    i3++;
                }
                str = str + string2.charAt(iFloor);
            }
            if (i3 >= 2) {
                z = false;
            }
        } while (z);
        return str;
    }

    public static String stringFilter(String str) {
        String strReplaceAll = str.replaceAll(StubApp.getString2(14440), StubApp.getString2(2566)).replaceAll(StubApp.getString2(14441), StubApp.getString2(499));
        String string2 = StubApp.getString2(14442);
        String strReplaceAll2 = strReplaceAll.replaceAll(string2, string2);
        String string22 = StubApp.getString2(669);
        return Pattern.compile(StubApp.getString2(14443)).matcher(strReplaceAll2.replaceAll(string22, string22)).replaceAll("").trim();
    }

    public static String getTime() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String maskPhoneNumber(String str) {
        return (str == null || str.length() != 11) ? str : str.replaceAll(StubApp.getString2(14438), StubApp.getString2(14439));
    }
}
