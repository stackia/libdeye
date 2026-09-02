package io.fogcloud.sdk.easylink.helper;

import com.stub.StubApp;
import com.umeng.analytics.pro.ek;
import java.io.UnsupportedEncodingException;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class ProbeReqData {
    private static final String ARC4_KEY = StubApp.getString2(44625);
    protected static final char[] hexArray = StubApp.getString2(5127).toCharArray();
    private static final int version = 1;

    public String[] bgProtocol(String str, String str2, int i) throws UnsupportedEncodingException {
        byte[] bArr = new byte[2];
        bArr[0] = 1;
        String string2 = StubApp.getString2(567);
        int i2 = 5;
        byte[] bArr2 = new byte[str.getBytes(string2).length + 5 + str2.getBytes(string2).length];
        bArr2[0] = (byte) (i & 255);
        bArr2[1] = (byte) ((i >> 8) & 255);
        bArr2[2] = (byte) ((i >> 16) & 255);
        bArr2[3] = (byte) ((i >> 24) & 255);
        bArr2[4] = (byte) str.getBytes(string2).length;
        byte[] bytes = str.getBytes(string2);
        int length = bytes.length;
        int i3 = 0;
        while (i3 < length) {
            bArr2[i2] = bytes[i3];
            i3++;
            i2++;
        }
        byte[] bytes2 = str2.getBytes(string2);
        int length2 = bytes2.length;
        int i4 = 0;
        while (i4 < length2) {
            bArr2[i2] = bytes2[i4];
            i4++;
            i2++;
        }
        byte[] bArrTransfer = transfer(bArr2);
        byte[] bArrTransfer2 = transfer(new RC4(StubApp.getString2(44625).getBytes(string2)).encrypt(bArr2));
        int i5 = 29;
        int length3 = bArrTransfer2.length % 29 == 0 ? bArrTransfer2.length / 29 : (bArrTransfer2.length / 29) + 1;
        String[] strArr = new String[length3 + 1];
        strArr[0] = new String(bArrTransfer);
        if (1 == length3) {
            byte[] bArr3 = new byte[bArrTransfer2.length + 3];
            int i6 = length3 & 15;
            byte b = (byte) (((byte) (i6 << 4)) + ((byte) i6));
            byte[] bArr4 = new byte[bArrTransfer2.length + 1];
            bArr4[0] = b;
            int i7 = 0;
            while (i7 < bArrTransfer2.length) {
                int i8 = i7 + 1;
                bArr4[i8] = bArrTransfer2[i7];
                i7 = i8;
            }
            bArr[1] = ek.n;
            byte bCalcCrc8 = (byte) ((Crc8Code.calcCrc8(bArr4) & ek.m) | bArr[1]);
            bArr[1] = bCalcCrc8;
            bArr3[0] = bArr[0];
            bArr3[1] = bCalcCrc8;
            bArr3[2] = b;
            for (int i9 = 0; i9 < bArrTransfer2.length; i9++) {
                bArr3[i9 + 3] = bArrTransfer2[i9];
            }
            strArr[1] = new String(bArr3, string2);
        } else {
            int i10 = 0;
            while (i10 < length3) {
                int i11 = i10 + 1;
                int length4 = i11 < length3 ? i5 : bArrTransfer2.length % i5;
                byte[] bArr5 = new byte[length4 + 3];
                byte[] bArr6 = new byte[length4 + 1];
                byte b2 = (byte) (((byte) ((length3 & 15) << 4)) + ((byte) (i11 & 15)));
                bArr6[0] = b2;
                int i12 = 0;
                while (i12 < length4) {
                    int i13 = i12 + (i10 * 29);
                    bArr5[i12 + 3] = bArrTransfer2[i13];
                    i12++;
                    bArr6[i12] = bArrTransfer2[i13];
                }
                bArr[1] = 0;
                bArr[1] = ek.n;
                byte bCalcCrc82 = (byte) ((Crc8Code.calcCrc8(bArr6) & ek.m) | bArr[1]);
                bArr[1] = bCalcCrc82;
                bArr5[0] = bArr[0];
                bArr5[1] = bCalcCrc82;
                bArr5[2] = b2;
                strArr[i11] = new String(bArr5, string2);
                i10 = i11;
                i5 = 29;
            }
        }
        return strArr;
    }

    byte[] transfer(byte[] bArr) {
        int i;
        int length = bArr.length;
        byte[] bArr2 = new byte[length * 2];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            byte b = (byte) (bArr[i3] & ByteCompanionObject.MAX_VALUE);
            if (b == 126) {
                int i4 = i2 + 1;
                bArr2[i2] = 126;
                i2 += 2;
                bArr2[i4] = 1;
            } else if (b == 0) {
                int i5 = i2 + 1;
                bArr2[i2] = 126;
                i2 += 2;
                bArr2[i5] = 2;
            } else {
                bArr2[i2] = b;
                i2++;
            }
            if (i3 % 7 == 6) {
                int i6 = i3 - 6;
                byte b2 = 0;
                for (int i7 = 0; i7 < 7; i7++) {
                    b2 = (byte) (b2 + ((bArr[i6 + i7] & 128) >> (7 - i7)));
                }
                if (b2 == 126) {
                    int i8 = i2 + 1;
                    bArr2[i2] = 126;
                    i2 += 2;
                    bArr2[i8] = 1;
                } else if (b2 == 0) {
                    int i9 = i2 + 1;
                    bArr2[i2] = 126;
                    i2 += 2;
                    bArr2[i9] = 2;
                } else {
                    i = i2 + 1;
                    bArr2[i2] = b2;
                    i2 = i;
                }
            } else if (i3 == length - 1) {
                int i10 = length % 7;
                byte b3 = 0;
                for (int i11 = 0; i11 < i10; i11++) {
                    b3 = (byte) (b3 + ((bArr[(length - i10) + i11] & 128) >> (7 - i11)));
                }
                if (b3 == 126) {
                    int i12 = i2 + 1;
                    bArr2[i2] = 126;
                    i2 += 2;
                    bArr2[i12] = 1;
                } else if (b3 == 0) {
                    int i13 = i2 + 1;
                    bArr2[i2] = 126;
                    i2 += 2;
                    bArr2[i13] = 2;
                } else {
                    i = i2 + 1;
                    bArr2[i2] = b3;
                    i2 = i;
                }
            }
        }
        byte[] bArr3 = new byte[i2];
        for (int i14 = 0; i14 < i2; i14++) {
            bArr3[i14] = bArr2[i14];
        }
        return bArr3;
    }

    public static byte[] hexStringToBytes(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        int length = str.length() / 2;
        char[] charArray = str.toCharArray();
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

    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            char[] cArr2 = hexArray;
            cArr[i2] = cArr2[(b & 255) >>> 4];
            cArr[i2 + 1] = cArr2[b & ek.m];
        }
        return new String(cArr);
    }
}
