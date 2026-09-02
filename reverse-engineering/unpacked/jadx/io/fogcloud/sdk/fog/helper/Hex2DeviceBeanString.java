package io.fogcloud.sdk.fog.helper;

import io.fogcloud.sdk.fog.utils.DigitalTrans;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class Hex2DeviceBeanString {
    public static int[] getItemArray(String str) {
        int length = str.length();
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return iArr;
    }

    public static String[] getBinaryStr(String str) {
        String[] hexArray = getHexArray(str);
        String[] strArr = new String[hexArray.length];
        for (int i = 0; i < hexArray.length; i++) {
            strArr[i] = DigitalTrans.hexStringToBinary(hexArray[i]);
        }
        return strArr;
    }

    public static String[] getHexArray(String str) {
        int length = str.length() / 2;
        if (length * 2 < str.length()) {
            length++;
        }
        String[] strArr = new String[length];
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (i2 % 2 == 0) {
                strArr[i] = "" + str.charAt(i2);
            } else {
                strArr[i] = strArr[i] + str.charAt(i2);
                i++;
            }
        }
        return strArr;
    }

    public static int binaryStrToInt10(String str) {
        return Integer.parseInt(str, 2);
    }
}
