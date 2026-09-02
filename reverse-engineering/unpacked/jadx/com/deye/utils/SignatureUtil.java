package com.deye.utils;

import com.stub.StubApp;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class SignatureUtil {
    public static String generateSignature(String str, String str2) throws NoSuchAlgorithmException, InvalidKeyException {
        String string2 = StubApp.getString2(985);
        try {
            Mac mac = Mac.getInstance(string2);
            mac.init(new SecretKeySpec(str2.getBytes(StandardCharsets.UTF_8), string2));
            return bytesToHex(mac.doFinal(str.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new RuntimeException(StubApp.getString2(14432), e);
        }
    }

    private static String bytesToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(String.format(StubApp.getString2(2291), Byte.valueOf(b)));
        }
        return sb.toString();
    }
}
