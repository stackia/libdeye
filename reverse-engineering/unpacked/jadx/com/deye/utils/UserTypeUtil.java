package com.deye.utils;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class UserTypeUtil {
    public static String getUserType(int i) {
        String string2 = StubApp.getString2(14466);
        if (i == 1 || i == 2) {
            return string2;
        }
        if (i == 3) {
            return StubApp.getString2(14467);
        }
        return "";
    }
}
