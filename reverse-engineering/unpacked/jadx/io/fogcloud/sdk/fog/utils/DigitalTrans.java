package io.fogcloud.sdk.fog.utils;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class DigitalTrans {
    public static String StringToAsciiString(String str) {
        int length = str.length();
        String str2 = "";
        for (int i = 0; i < length; i++) {
            str2 = str2 + Integer.toHexString(str.charAt(i));
        }
        return str2;
    }

    public static String hexStringToString(String str, int i) {
        int length = str.length() / i;
        String str2 = "";
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 * i;
            i2++;
            str2 = str2 + ((char) hexStringToAlgorism(str.substring(i3, i2 * i)));
        }
        return str2;
    }

    public static int hexStringToAlgorism(String str) {
        String upperCase = str.toUpperCase();
        int iPow = 0;
        for (int length = upperCase.length(); length > 0; length--) {
            char cCharAt = upperCase.charAt(length - 1);
            iPow = (int) (iPow + (Math.pow(16.0d, r0 - length) * ((cCharAt < '0' || cCharAt > '9') ? cCharAt - '7' : cCharAt - '0')));
        }
        return iPow;
    }

    public static String hexStringToBinary(String str) {
        String upperCase = str.toUpperCase();
        int length = upperCase.length();
        String str2 = "";
        for (int i = 0; i < length; i++) {
            char cCharAt = upperCase.charAt(i);
            switch (cCharAt) {
                case '0':
                    str2 = str2 + StubApp.getString2(26813);
                    break;
                case '1':
                    str2 = str2 + StubApp.getString2(45135);
                    break;
                case '2':
                    str2 = str2 + StubApp.getString2(45134);
                    break;
                case '3':
                    str2 = str2 + StubApp.getString2(45133);
                    break;
                case '4':
                    str2 = str2 + StubApp.getString2(45132);
                    break;
                case '5':
                    str2 = str2 + StubApp.getString2(45136);
                    break;
                case '6':
                    str2 = str2 + StubApp.getString2(45137);
                    break;
                case '7':
                    str2 = str2 + StubApp.getString2(45195);
                    break;
                case '8':
                    str2 = str2 + StubApp.getString2(874);
                    break;
                case '9':
                    str2 = str2 + StubApp.getString2(3942);
                    break;
                default:
                    switch (cCharAt) {
                        case 'A':
                            str2 = str2 + StubApp.getString2(45194);
                            break;
                        case 'B':
                            str2 = str2 + StubApp.getString2(45193);
                            break;
                        case 'C':
                            str2 = str2 + StubApp.getString2(45192);
                            break;
                        case 'D':
                            str2 = str2 + StubApp.getString2(3945);
                            break;
                        case 'E':
                            str2 = str2 + StubApp.getString2(45191);
                            break;
                        case 'F':
                            str2 = str2 + StubApp.getString2(45190);
                            break;
                    }
            }
        }
        return str2;
    }

    public static String AsciiStringToString(String str) {
        int length = str.length() / 2;
        String str2 = "";
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            str2 = str2 + String.valueOf((char) hexStringToAlgorism(str.substring(i2, i2 + 2)));
        }
        return str2;
    }

    public static String algorismToHEXString(int i, int i2) {
        String hexString = Integer.toHexString(i);
        if (hexString.length() % 2 == 1) {
            hexString = StubApp.getString2(701) + hexString;
        }
        return patchHexString(hexString.toUpperCase(), i2);
    }

    public static String bytetoString(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            str = str + ((char) b);
        }
        return str;
    }

    public static int binaryToAlgorism(String str) {
        int iPow = 0;
        for (int length = str.length(); length > 0; length--) {
            iPow = (int) (iPow + (Math.pow(2.0d, r0 - length) * (str.charAt(length - 1) - '0')));
        }
        return iPow;
    }

    public static String algorismToHEXString(int i) {
        String hexString = Integer.toHexString(i);
        if (hexString.length() % 2 == 1) {
            hexString = StubApp.getString2(701) + hexString;
        }
        return StubApp.getString2(24076) + hexString.toUpperCase();
    }

    public static String patchHexString(String str, int i) {
        String str2 = "";
        for (int i2 = 0; i2 < i - str.length(); i2++) {
            str2 = StubApp.getString2(701) + str2;
        }
        return (str2 + str).substring(0, i);
    }

    public static int parseToInt(String str, int i, int i2) {
        try {
            return Integer.parseInt(str, i2);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static int parseToInt(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static byte[] hexStringToByte(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        String strHexStringToBinary = hexStringToBinary(str);
        int i = 0;
        while (i < length) {
            int i2 = i * 8;
            int i3 = i + 1;
            bArr[i] = (byte) binaryToAlgorism(strHexStringToBinary.substring(i2 + 1, i3 * 8));
            if (strHexStringToBinary.charAt(i2) == '1') {
                bArr[i] = (byte) (0 - bArr[i]);
            }
            i = i3;
        }
        return bArr;
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

    public static final String byte2hex(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException(StubApp.getString2(45189));
        }
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                str = str + StubApp.getString2(701) + hexString;
            } else {
                str = str + hexString;
            }
        }
        return str.toUpperCase();
    }
}
