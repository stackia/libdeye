package io.fogcloud.sdk.fog.utils;

import com.stub.StubApp;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AESUtils.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\u001a\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0002\u001a\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006\u001a\u0016\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"CipherMode", "", "ENCRYPT_ALGORITHM_NAME", "createIvParameterSpec", "Ljavax/crypto/spec/IvParameterSpec;", "decrypt", "", "rawKey", "encryptedContent", "encrypt", "pendingEncryptContent", "fog_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class AESUtilsKt {
    public static final String CipherMode = StubApp.getString2(6150);
    public static final String ENCRYPT_ALGORITHM_NAME = StubApp.getString2(6149);

    public static final byte[] encrypt(byte[] rawKey, byte[] pendingEncryptContent) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        Intrinsics.checkNotNullParameter(rawKey, "rawKey");
        Intrinsics.checkNotNullParameter(pendingEncryptContent, "pendingEncryptContent");
        SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, StubApp.getString2(6149));
        Cipher cipher = Cipher.getInstance(StubApp.getString2(6150));
        Intrinsics.checkNotNullExpressionValue(cipher, "getInstance(...)");
        cipher.init(1, secretKeySpec, createIvParameterSpec());
        byte[] bArrDoFinal = cipher.doFinal(pendingEncryptContent);
        Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
        return bArrDoFinal;
    }

    public static final byte[] decrypt(byte[] rawKey, byte[] encryptedContent) {
        Intrinsics.checkNotNullParameter(rawKey, "rawKey");
        Intrinsics.checkNotNullParameter(encryptedContent, "encryptedContent");
        SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, StubApp.getString2(6149));
        Cipher cipher = Cipher.getInstance(StubApp.getString2(6150));
        Intrinsics.checkNotNullExpressionValue(cipher, "getInstance(...)");
        cipher.init(2, secretKeySpec, createIvParameterSpec());
        try {
            byte[] bArrDoFinal = cipher.doFinal(encryptedContent);
            Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
            return bArrDoFinal;
        } catch (BadPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static final IvParameterSpec createIvParameterSpec() {
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = 0;
        }
        return new IvParameterSpec(bArr);
    }
}
