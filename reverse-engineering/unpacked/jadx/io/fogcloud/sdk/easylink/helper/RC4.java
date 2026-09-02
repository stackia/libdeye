package io.fogcloud.sdk.easylink.helper;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class RC4 {
    private final byte[] S = new byte[256];
    private final byte[] T = new byte[256];
    private final int keylen;

    public RC4(byte[] bArr) {
        if (bArr.length < 1 || bArr.length > 256) {
            throw new IllegalArgumentException(StubApp.getString2(10495));
        }
        this.keylen = bArr.length;
        for (int i = 0; i < 256; i++) {
            this.S[i] = (byte) i;
            this.T[i] = bArr[i % this.keylen];
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            byte[] bArr2 = this.S;
            byte b = bArr2[i3];
            i2 = (i2 + b + this.T[i3]) & 255;
            byte b2 = bArr2[i2];
            bArr2[i2] = b;
            bArr2[i3] = b2;
        }
    }

    public byte[] encrypt(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            byte[] bArr3 = this.S;
            byte b = bArr3[i];
            i2 = (i2 + b) & 255;
            byte b2 = bArr3[i2];
            bArr3[i2] = b;
            bArr3[i] = b2;
            bArr2[i3] = (byte) (bArr3[(b2 + bArr3[i2]) & 255] ^ bArr[i3]);
        }
        return bArr2;
    }

    public byte[] decrypt(byte[] bArr) {
        return encrypt(bArr);
    }
}
