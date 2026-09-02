package com.deye.combo.utils;

import com.stub.StubApp;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class AESEncryptUtil {
    private static final String CipherMode = StubApp.getString2(6150);
    public static String v = StubApp.getString2(13914);

    private static SecretKeySpec createKey(String str) {
        return new SecretKeySpec(str.getBytes(), StubApp.getString2(6149));
    }

    private static IvParameterSpec createIV(String str) {
        if (str == null || str.isEmpty()) {
            return new IvParameterSpec(StubApp.getString2(7155).getBytes());
        }
        return new IvParameterSpec(str.getBytes());
    }

    public static byte[] encrypt(byte[] bArr, String str, String str2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKeySpec secretKeySpecCreateKey = createKey(str);
        try {
            Cipher cipher = Cipher.getInstance(StubApp.getString2("6150"));
            cipher.init(1, secretKeySpecCreateKey, createIV(str2));
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String str, String str2, String str3) throws UnsupportedEncodingException {
        byte[] bytes;
        try {
            bytes = str.getBytes(StubApp.getString2("567"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            bytes = null;
        }
        return DataUtil.bytes2Hex(encrypt(bytes, str2, str3));
    }

    public static byte[] decrypt(byte[] bArr, String str, String str2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKeySpec secretKeySpecCreateKey = createKey(str);
        try {
            Cipher cipher = Cipher.getInstance(StubApp.getString2("6150"));
            cipher.init(2, secretKeySpecCreateKey, createIV(str2));
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
