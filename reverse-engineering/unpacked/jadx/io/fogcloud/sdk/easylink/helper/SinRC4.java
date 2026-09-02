package io.fogcloud.sdk.easylink.helper;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class SinRC4 {
    public static String decry_RC4(byte[] bArr, String str) {
        if (bArr == null || str == null) {
            return null;
        }
        return asString(RC4Base(bArr, str));
    }

    public static String decry_RC4(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        return new String(RC4Base(HexString2Bytes(str), str2));
    }

    public static byte[] encry_RC4_byte(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        return RC4Base(str.getBytes(), str2);
    }

    public static byte[] encry_RC4_byte(byte[] bArr, String str) {
        if (bArr == null || str == null) {
            return null;
        }
        return RC4Base(bArr, str);
    }

    public static byte[] intToBytes2(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static String encry_RC4_string(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        return toHexString(asString(encry_RC4_byte(str, str2)));
    }

    private static String asString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length);
        for (byte b : bArr) {
            stringBuffer.append((char) b);
        }
        return stringBuffer.toString();
    }

    private static byte[] initKey(String str) {
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[256];
        for (int i = 0; i < 256; i++) {
            bArr[i] = (byte) i;
        }
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        int length = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            int i4 = bytes[length] & 255;
            byte b = bArr[i3];
            i2 = (i4 + (b & 255) + i2) & 255;
            bArr[i3] = bArr[i2];
            bArr[i2] = b;
            length = (length + 1) % bytes.length;
        }
        return bArr;
    }

    private static String toHexString(String str) {
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            String hexString = Integer.toHexString(str.charAt(i) & 255);
            if (hexString.length() == 1) {
                hexString = StubApp.getString2(701) + hexString;
            }
            str2 = str2 + hexString;
        }
        return str2;
    }

    private static byte[] HexString2Bytes(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        byte[] bytes = str.getBytes();
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = uniteBytes(bytes[i2], bytes[i2 + 1]);
        }
        return bArr;
    }

    private static byte uniteBytes(byte b, byte b2) {
        String str = new String(new byte[]{b});
        String string2 = StubApp.getString2(24076);
        return (byte) (((char) Byte.decode(string2.concat(new String(new byte[]{b2}))).byteValue()) ^ ((char) (((char) Byte.decode(string2.concat(str)).byteValue()) << 4)));
    }

    private static byte[] RC4Base(byte[] bArr, String str) {
        byte[] bArrInitKey = initKey(str);
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            byte b = bArrInitKey[i];
            i2 = ((b & 255) + i2) & 255;
            bArrInitKey[i] = bArrInitKey[i2];
            bArrInitKey[i2] = b;
            bArr2[i3] = (byte) (bArrInitKey[((bArrInitKey[i] & 255) + (b & 255)) & 255] ^ bArr[i3]);
        }
        return bArr2;
    }
}
