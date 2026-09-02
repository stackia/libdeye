package io.fogcloud.sdk.easylink.helper;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class Helper {
    public static String castHexKeyboard(String str) {
        String upperCase = str.toUpperCase();
        char[] charArray = upperCase.toCharArray();
        String str2 = "";
        for (int i = 0; i < upperCase.length(); i++) {
            char c = charArray[i];
            if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != 'A' && c != 'B' && c != 'C' && c != 'D' && c != 'E') {
                charArray[i] = 'F';
            }
            str2 = str2 + charArray[i];
        }
        return str2;
    }

    public static boolean checkDataHexa(String str) {
        String upperCase = str.toUpperCase();
        char[] charArray = upperCase.toCharArray();
        boolean z = true;
        for (int i = 0; i < upperCase.length(); i++) {
            char c = charArray[i];
            if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != 'A' && c != 'B' && c != 'C' && c != 'D' && c != 'E' && c != 'F') {
                z = false;
            }
        }
        return z;
    }

    public static String checkAndChangeDataHexa(String str) {
        String upperCase = str.toUpperCase();
        char[] charArray = upperCase.toCharArray();
        String str2 = "";
        for (int i = 0; i < upperCase.length(); i++) {
            char c = charArray[i];
            if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == 'A' || c == 'B' || c == 'C' || c == 'D' || c == 'E' || c == 'F') {
                str2 = str2 + charArray[i];
            }
        }
        return str2;
    }

    public static boolean checkFileName(String str) {
        char[] charArray = str.toCharArray();
        boolean z = true;
        for (int i = 0; i < str.length(); i++) {
            char c = charArray[i];
            if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != 'a' && c != 'b' && c != 'c' && c != 'd' && c != 'e' && c != 'f' && c != 'g' && c != 'h' && c != 'i' && c != 'j' && c != 'k' && c != 'l' && c != 'm' && c != 'n' && c != 'o' && c != 'p' && c != 'q' && c != 'r' && c != 's' && c != 't' && c != 'u' && c != 'v' && c != 'w' && c != 'x' && c != 'y' && c != 'z' && c != 'A' && c != 'B' && c != 'C' && c != 'D' && c != 'E' && c != 'F' && c != 'G' && c != 'H' && c != 'I' && c != 'J' && c != 'K' && c != 'L' && c != 'M' && c != 'N' && c != 'O' && c != 'P' && c != 'Q' && c != 'R' && c != 'S' && c != 'T' && c != 'U' && c != 'V' && c != 'W' && c != 'X' && c != 'Y' && c != 'Z' && c != '.' && c != '_') {
                z = false;
            }
        }
        return z;
    }

    public static String checkAndChangeFileName(String str) {
        char[] charArray = str.toCharArray();
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            char c = charArray[i];
            if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f' || c == 'g' || c == 'h' || c == 'i' || c == 'j' || c == 'k' || c == 'l' || c == 'm' || c == 'n' || c == 'o' || c == 'p' || c == 'q' || c == 'r' || c == 's' || c == 't' || c == 'u' || c == 'v' || c == 'w' || c == 'x' || c == 'y' || c == 'z' || c == 'A' || c == 'B' || c == 'C' || c == 'D' || c == 'E' || c == 'F' || c == 'G' || c == 'H' || c == 'I' || c == 'J' || c == 'K' || c == 'L' || c == 'M' || c == 'N' || c == 'O' || c == 'P' || c == 'Q' || c == 'R' || c == 'S' || c == 'T' || c == 'U' || c == 'V' || c == 'W' || c == 'X' || c == 'Y' || c == 'Z' || c == '.' || c == '_') {
                str2 = str2 + charArray[i];
            }
        }
        return str2;
    }

    public static String StringForceDigit(String str, int i) {
        String strReplaceAll = str.replaceAll(StubApp.getString2(626), "");
        if (strReplaceAll.length() == 4) {
            return strReplaceAll;
        }
        if (strReplaceAll.length() < i) {
            while (strReplaceAll.length() != i) {
                strReplaceAll = StubApp.getString2(701).concat(strReplaceAll);
            }
        }
        return strReplaceAll;
    }

    public static String ConvertHexByteToString(byte b) {
        String string2 = StubApp.getString2(626);
        if (b < 0) {
            return "" + Integer.toString(b + 256, 16) + string2;
        }
        if (b <= 15) {
            return StubApp.getString2(701) + Integer.toString(b, 16) + string2;
        }
        return "" + Integer.toString(b, 16) + string2;
    }

    public static String ConvertHexByteArrayToString(byte[] bArr) {
        String str = "";
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            String string2 = StubApp.getString2(626);
            if (b < 0) {
                str = str + Integer.toString(bArr[i] + 256, 16) + string2;
            } else if (b <= 15) {
                str = str + StubApp.getString2(701) + Integer.toString(bArr[i], 16) + string2;
            } else {
                str = str + Integer.toString(bArr[i], 16) + string2;
            }
        }
        return str;
    }

    public static String FormatValueByteWrite(String str) {
        return castHexKeyboard(StringForceDigit(str, 2)).toUpperCase();
    }

    public static String ConvertIntToHexFormatString(int i) {
        return ConvertHexByteArrayToString(ConvertIntTo2bytesHexaFormat(i)).replaceAll(StubApp.getString2(626), "");
    }

    public static byte[] ConvertStringToHexBytes(String str) {
        char[] charArray = str.toCharArray();
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] bArr = {0, 0};
        int i = 0;
        for (int i2 = 0; i2 <= 1; i2++) {
            int i3 = 0;
            while (i3 <= 15) {
                if (charArray[i2] == cArr[i3]) {
                    if (i2 == 1) {
                        i += i3;
                        i3 = 15;
                    } else if (i2 == 0) {
                        i3 *= 16;
                        i += i3;
                        i3 = 15;
                    }
                }
                i3++;
            }
        }
        bArr[0] = (byte) i;
        int i4 = 0;
        for (int i5 = 2; i5 <= 3; i5++) {
            int i6 = 0;
            while (i6 <= 15) {
                if (charArray[i5] == cArr[i6]) {
                    if (i5 == 3) {
                        i4 += i6;
                        i6 = 15;
                    } else if (i5 == 2) {
                        i6 *= 16;
                        i4 += i6;
                        i6 = 15;
                    }
                }
                i6++;
            }
        }
        bArr[1] = (byte) i4;
        return bArr;
    }

    public static byte[] ConvertStringToHexBytesArray(String str) {
        String strReplaceAll = str.toUpperCase().replaceAll(StubApp.getString2(626), "");
        char[] charArray = strReplaceAll.toCharArray();
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] bArr = new byte[strReplaceAll.length() / 2];
        int length = strReplaceAll.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = 0;
            while (i3 <= 15) {
                if (charArray[i2] == cArr[i3]) {
                    int i4 = i2 % 2;
                    if (i4 == 1) {
                        i += i3;
                        i3 = 15;
                    } else if (i4 == 0) {
                        i3 *= 16;
                        i += i3;
                        i3 = 15;
                    }
                }
                i3++;
            }
            if (i2 % 2 == 1) {
                bArr[i2 / 2] = (byte) i;
                i = 0;
            }
        }
        return bArr;
    }

    public static byte ConvertStringToHexByte(String str) {
        char[] charArray = str.toUpperCase().toCharArray();
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int i = 0;
        for (int i2 = 0; i2 <= 1; i2++) {
            int i3 = 0;
            while (i3 <= 15) {
                if (charArray[i2] == cArr[i3]) {
                    if (i2 == 1) {
                        i += i3;
                        i3 = 15;
                    } else if (i2 == 0) {
                        i3 *= 16;
                        i += i3;
                        i3 = 15;
                    }
                }
                i3++;
            }
        }
        return (byte) i;
    }

    public static byte[] ConvertIntTo2bytesHexaFormat(int i) {
        int i2 = i / 256;
        int i3 = i - (i2 * 256);
        int i4 = i - (i3 * 256);
        byte[] bArr = {(byte) i2, (byte) i3, (byte) i4, 0};
        bArr[2] = (byte) (i - (i4 * 256));
        return bArr;
    }

    public static int Convert2bytesHexaFormatToInt(byte[] bArr) {
        int i = bArr[1];
        if (i <= -1) {
            i += 256;
        }
        int i2 = bArr[0];
        return i + (i2 <= -1 ? (i2 * 256) + 256 : i2 * 256);
    }

    public static int ConvertStringToInt(String str) {
        if (str.length() > 2) {
            return Integer.parseInt(str.substring(2, 4), 16) + (Integer.parseInt(str.substring(0, 2), 16) * 256);
        }
        return Integer.parseInt(str.substring(0, 2), 16);
    }

    public static String[] buildArrayBlocks(byte[] bArr, int i) {
        String[] strArr = new String[i];
        int i2 = bArr[1];
        if (i2 < 0) {
            i2 += 256;
        }
        int i3 = 0;
        int i4 = bArr[0];
        if (i4 < 0) {
            i4 += 256;
        }
        int i5 = i2 + (i4 * 256);
        while (i3 < i) {
            if (i3 == 14) {
                i3 = 14;
            }
            strArr[i3] = StubApp.getString2(44624) + ConvertIntToHexFormatString(i3 + i5).toUpperCase();
            i3++;
        }
        return strArr;
    }

    public static String[] buildArrayValueBlocks(byte[] bArr, int i) {
        String[] strArr = new String[i];
        int i2 = 1;
        for (int i3 = 0; i3 < i; i3++) {
            strArr[i3] = "";
            strArr[i3] = strArr[i3] + ConvertHexByteToString(bArr[i2]).toUpperCase();
            StringBuilder sbAppend = new StringBuilder().append(strArr[i3]);
            String string2 = StubApp.getString2(626);
            strArr[i3] = sbAppend.append(string2).toString();
            strArr[i3] = strArr[i3] + ConvertHexByteToString(bArr[i2 + 1]).toUpperCase();
            strArr[i3] = strArr[i3] + string2;
            strArr[i3] = strArr[i3] + ConvertHexByteToString(bArr[i2 + 2]).toUpperCase();
            strArr[i3] = strArr[i3] + string2;
            strArr[i3] = strArr[i3] + ConvertHexByteToString(bArr[i2 + 3]).toUpperCase();
            i2 += 4;
        }
        return strArr;
    }

    public static byte[] hexStringToBytes(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (charToByte(charArray[i2 + 1]) | (charToByte(charArray[i2]) << 4));
        }
        return bArr;
    }

    public static byte charToByte(char c) {
        return (byte) StubApp.getString2(5127).indexOf(c);
    }

    public static byte[] fillbyte(int i, byte[] bArr) {
        byte[] bArr2 = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 < bArr.length) {
                bArr2[i2] = bArr[i2];
            } else {
                bArr2[i2] = 0;
            }
        }
        return bArr2;
    }

    public static byte[] byteMerger(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }
}
