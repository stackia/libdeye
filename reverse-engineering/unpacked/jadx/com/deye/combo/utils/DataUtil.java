package com.deye.combo.utils;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class DataUtil {
    public static String bytes2Hex(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = i; i3 < i + i2; i3++) {
            sb.append(String.format(StubApp.getString2(2291), Byte.valueOf(bArr[i3])));
        }
        return sb.toString();
    }

    public static String bytes2Hex(byte[] bArr) {
        return bytes2Hex(bArr, 0, bArr.length);
    }

    public static byte[] stringSlice(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        int i = 0;
        while (i < length) {
            int i2 = i + 2;
            bArr[i / 2] = (byte) (Integer.parseInt(str.substring(i, i2), 16) & 255);
            i = i2;
        }
        return bArr;
    }

    public static int crc16(byte[] bArr, int i) {
        int i2 = 65535;
        for (int i3 = 0; i3 < i; i3++) {
            i2 ^= bArr[i3] << 8;
            for (int i4 = 0; i4 < 8; i4++) {
                i2 = (i2 & 32768) == 32768 ? (i2 << 1) ^ 4129 : i2 << 1;
            }
        }
        return i2 & 65535;
    }
}
