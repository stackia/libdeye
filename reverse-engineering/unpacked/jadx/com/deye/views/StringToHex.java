package com.deye.views;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class StringToHex {
    public String convertStringToHex(String str) {
        char[] charArray = str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (char c : charArray) {
            stringBuffer.append(Integer.toHexString(c));
        }
        return stringBuffer.toString();
    }

    public String convertHexToString(String str) throws NumberFormatException {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int i = 0;
        while (i < str.length() - 1) {
            int i2 = i + 2;
            int i3 = Integer.parseInt(str.substring(i, i2), 16);
            sb.append((char) i3);
            sb2.append(i3);
            i = i2;
        }
        return sb.toString();
    }

    public static void main(String[] strArr) {
        StringToHex stringToHex = new StringToHex();
        System.out.println(StubApp.getString2(14636));
        System.out.println(StubApp.getString2(14637));
        String strConvertStringToHex = stringToHex.convertStringToHex(StubApp.getString2(14638));
        System.out.println(StubApp.getString2(14639) + strConvertStringToHex);
        System.out.println(StubApp.getString2(14640));
        System.out.println(StubApp.getString2(14641) + strConvertStringToHex);
        System.out.println(StubApp.getString2(14642) + stringToHex.convertHexToString(strConvertStringToHex));
    }
}
