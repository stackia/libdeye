package io.fogcloud.sdk.fog.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.stub.StubApp;
import com.umeng.analytics.pro.ek;
import io.fogcloud.sdk.fog.log.LogUtil;
import org.apache.commons.codec.digest.DigestUtils;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class SharedPrefsUtil {
    private static final String HEX = StubApp.getString2(5127);
    public static final String TAG = StubApp.getString2(45202);
    private Context context;

    public SharedPrefsUtil(Context context) {
        this.context = context;
    }

    public void putValue(String str, String str2, String str3) {
        EncryptedSharedPrefsUtil.INSTANCE.setStringValue(str2, str3);
    }

    public static byte[] toByte(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }

    public static String toHex(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            appendHex(stringBuffer, b);
        }
        return stringBuffer.toString();
    }

    private static void appendHex(StringBuffer stringBuffer, byte b) {
        String string2 = StubApp.getString2(5127);
        stringBuffer.append(string2.charAt((b >> 4) & 15)).append(string2.charAt(b & ek.m));
    }

    public void putFloat(String str, String str2, float f) {
        EncryptedSharedPrefsUtil.INSTANCE.setFloatValue(str2, f);
    }

    public float getFloat(String str, String str2, float f) {
        if (EncryptedSharedPrefsUtil.INSTANCE.isContains(str2)) {
            return EncryptedSharedPrefsUtil.INSTANCE.getFloatValue(str2);
        }
        return getSharedPreferences(str).getFloat(str2, f);
    }

    public boolean getBoolean(String str, String str2, boolean z) {
        if (EncryptedSharedPrefsUtil.INSTANCE.isContains(str2)) {
            return EncryptedSharedPrefsUtil.INSTANCE.getBoolValue(str2);
        }
        return getSharedPreferences(str).getBoolean(str2, z);
    }

    public void putBoolean(String str, String str2, boolean z) {
        EncryptedSharedPrefsUtil.INSTANCE.setBoolValue(str2, Boolean.valueOf(z));
    }

    public String getValue(String str, String str2, String str3) {
        String string2 = StubApp.getString2(45200);
        StringBuilder sbAppend = new StringBuilder(string2).append(str);
        String string22 = StubApp.getString2(31747);
        String string = sbAppend.append(string22).append(str2).append(StubApp.getString2(45201)).append(str3).toString();
        String string23 = StubApp.getString2(45202);
        Log.d(string23, string);
        if (EncryptedSharedPrefsUtil.INSTANCE.isContains(str2)) {
            return EncryptedSharedPrefsUtil.INSTANCE.getStringValue(str2, str3);
        }
        SharedPreferences sharedPreferences = getSharedPreferences(str);
        byte[] bArrMd5 = DigestUtils.md5(str2);
        String string3 = sharedPreferences.getString(toHex(bArrMd5), null);
        LogUtil.d(string23, StubApp.getString2(45203) + string3);
        if (string3 == null) {
            return sharedPreferences.getString(str2, str3);
        }
        byte[] bArrDecrypt = AESUtilsKt.decrypt(bArrMd5, toByte(string3));
        if (bArrDecrypt == null) {
            return str3;
        }
        String str4 = new String(bArrDecrypt);
        Log.d(string23, string2 + str + string22 + str2 + StubApp.getString2(45204) + str4);
        return str4;
    }

    private SharedPreferences.Editor getEditor(String str) {
        return getSharedPreferences(str).edit();
    }

    private SharedPreferences getSharedPreferences(String str) {
        return this.context.getSharedPreferences(str, 0);
    }
}
