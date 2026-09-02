package io.fogcloud.sdk.fog.utils;

import com.stub.StubApp;
import com.umeng.analytics.pro.ek;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class HexUtils {
    public static String str2HexStr(String str) {
        char[] charArray = StubApp.getString2(5127).toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(charArray[(bytes[i] & 240) >> 4]);
            sb.append(charArray[bytes[i] & ek.m]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    public static String hexStr2Str(String str) {
        char[] charArray = str.toCharArray();
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            char c = charArray[i2];
            String string2 = StubApp.getString2(5127);
            bArr[i] = (byte) (((string2.indexOf(c) * 16) + string2.indexOf(charArray[i2 + 1])) & 255);
        }
        return new String(bArr);
    }

    public static String byte2HexStr(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = StubApp.getString2(701) + hexString;
            }
            sb.append(hexString);
            sb.append(StubApp.getString2(626));
        }
        return sb.toString().toUpperCase().trim();
    }

    public static byte[] hexStr2Bytes(String str) {
        int length = str.length() / 2;
        System.out.println(length);
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            bArr[i] = Byte.decode(StubApp.getString2(24076) + str.substring(i2, i3) + str.substring(i3, i2 + 2)).byteValue();
        }
        return bArr;
    }

    public static String strToUnicode(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            String hexString = Integer.toHexString(cCharAt);
            if (cCharAt > 128) {
                sb.append(StubApp.getString2(30865)).append(hexString);
            } else {
                sb.append(StubApp.getString2(45198)).append(hexString);
            }
        }
        return sb.toString();
    }

    public static String unicodeToString(String str) {
        int length = str.length() / 6;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < length) {
            int i2 = i * 6;
            i++;
            String strSubstring = str.substring(i2, i * 6);
            sb.append(new String(Character.toChars(Integer.valueOf(strSubstring.substring(2, 4) + StubApp.getString2(2737), 16).intValue() + Integer.valueOf(strSubstring.substring(4), 16).intValue())));
        }
        return sb.toString();
    }

    public String btye2Str(byte[] bArr) {
        return new String(bArr);
    }

    public static final StringBuilder byte2hex(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length);
        for (byte b : bArr) {
            sb.append(String.format(StubApp.getString2(45197), Byte.valueOf(b)).trim());
        }
        return sb;
    }

    public static float byte2int_Float(byte[] bArr) {
        return (float) (((Integer.MIN_VALUE & (((bArr[0] & 255) << 24) | (((bArr[3] & 255) | ((bArr[2] & 255) << 8)) | ((bArr[1] & 255) << 16)))) != 0 ? -1 : 1) * ((r7 & 8388607) | 8388608) * Math.pow(2.0d, ((2139095040 & r7) >> 23) - 150));
    }

    public static final byte[] hex2byte(String str) throws IllegalArgumentException {
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] charArray = str.toCharArray();
        byte[] bArr = new byte[str.length() / 2];
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            bArr[i2] = new Integer(Integer.parseInt("" + charArray[i] + charArray[i + 1], 16) & 255).byteValue();
            i += 2;
            i2++;
        }
        return bArr;
    }

    public static byte[] parseHexStr2Byte(String str) throws NumberFormatException {
        if (str.length() < 1) {
            return null;
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            bArr[i] = (byte) ((Integer.parseInt(str.substring(i2, i3), 16) * 16) + Integer.parseInt(str.substring(i3, i2 + 2), 16));
        }
        return bArr;
    }

    public static String strToASCII(String str) {
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            str2 = str2 + integerToHexString(str.charAt(i));
        }
        return str2;
    }

    public static String integerToHexString(int i) {
        String hexString = Integer.toHexString(i);
        if (hexString.length() % 2 != 0) {
            hexString = StubApp.getString2(701) + hexString;
        }
        return hexString.toUpperCase();
    }

    public static String parseByte2HexStr(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = StubApp.getString2(701) + hexString;
            }
            stringBuffer.append(hexString.toUpperCase());
        }
        return stringBuffer.toString();
    }
}
