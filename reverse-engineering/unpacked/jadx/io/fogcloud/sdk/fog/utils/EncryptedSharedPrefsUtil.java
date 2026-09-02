package io.fogcloud.sdk.fog.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EncryptedSharedPrefsUtil.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u0006J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u0006J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u0006J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006J\u001d\u0010\u001a\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u000eJ\u001d\u0010\u001e\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u001fJ\u001d\u0010 \u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010!J\u0018\u0010\"\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lio/fogcloud/sdk/fog/utils/EncryptedSharedPrefsUtil;", "", "()V", "keyGenParameterSpec", "Landroid/security/keystore/KeyGenParameterSpec;", "mainKeyAlias", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "sharedPrefsFile", "getBoolValue", "", "key", "getFloatValue", "", "getIntValue", "", "getLongValue", "", "getStringValue", "defValue", "init", "", "context", "Landroid/content/Context;", "isContains", "setBoolValue", "value", "(Ljava/lang/String;Ljava/lang/Boolean;)V", "setFloatValue", "setIntValue", "(Ljava/lang/String;Ljava/lang/Integer;)V", "setLongValue", "(Ljava/lang/String;Ljava/lang/Long;)V", "setStringValue", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class EncryptedSharedPrefsUtil {
    private static final KeyGenParameterSpec keyGenParameterSpec;
    private static final String mainKeyAlias;
    private static SharedPreferences sharedPreferences;
    private static final String sharedPrefsFile = StubApp.getString2(45196);
    public static final EncryptedSharedPrefsUtil INSTANCE = new EncryptedSharedPrefsUtil();

    private EncryptedSharedPrefsUtil() {
    }

    static {
        KeyGenParameterSpec AES256_GCM_SPEC = MasterKeys.AES256_GCM_SPEC;
        Intrinsics.checkNotNullExpressionValue(AES256_GCM_SPEC, "AES256_GCM_SPEC");
        keyGenParameterSpec = AES256_GCM_SPEC;
        String orCreate = MasterKeys.getOrCreate(AES256_GCM_SPEC);
        Intrinsics.checkNotNullExpressionValue(orCreate, "getOrCreate(...)");
        mainKeyAlias = orCreate;
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferencesCreate = EncryptedSharedPreferences.create(StubApp.getString2(45196), mainKeyAlias, context, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        Intrinsics.checkNotNullExpressionValue(sharedPreferencesCreate, "create(...)");
        sharedPreferences = sharedPreferencesCreate;
    }

    public final void setBoolValue(String key, Boolean value) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            sharedPreferences2 = null;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences2.edit();
        if (value == null) {
            editorEdit.putBoolean(key, false);
        } else {
            editorEdit.putBoolean(key, value.booleanValue());
        }
        editorEdit.apply();
    }

    public final boolean getBoolValue(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            sharedPreferences2 = null;
        }
        return sharedPreferences2.getBoolean(key, false);
    }

    public final void setStringValue(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            sharedPreferences2 = null;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences2.edit();
        String str = value;
        if (str == null || str.length() == 0) {
            editorEdit.putString(key, "");
        } else {
            editorEdit.putString(key, value);
        }
        editorEdit.apply();
    }

    public final String getStringValue(String key, String defValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            sharedPreferences2 = null;
        }
        String string = sharedPreferences2.getString(key, "");
        String str = string;
        return (str == null || str.length() == 0) ? defValue : string;
    }

    public final void setIntValue(String key, Integer value) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            sharedPreferences2 = null;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences2.edit();
        if (value == null) {
            editorEdit.putInt(key, 0);
        } else {
            editorEdit.putInt(key, value.intValue());
        }
        editorEdit.apply();
    }

    public final int getIntValue(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            sharedPreferences2 = null;
        }
        return sharedPreferences2.getInt(key, 0);
    }

    public final void setFloatValue(String key, float value) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            sharedPreferences2 = null;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences2.edit();
        editorEdit.putFloat(key, value);
        editorEdit.apply();
    }

    public final float getFloatValue(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            sharedPreferences2 = null;
        }
        return sharedPreferences2.getFloat(key, 0.0f);
    }

    public final void setLongValue(String key, Long value) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            sharedPreferences2 = null;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences2.edit();
        if (value == null) {
            editorEdit.putLong(key, 0L);
        } else {
            editorEdit.putLong(key, value.longValue());
        }
        editorEdit.apply();
    }

    public final long getLongValue(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            sharedPreferences2 = null;
        }
        return sharedPreferences2.getLong(key, 0L);
    }

    public final boolean isContains(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
            sharedPreferences2 = null;
        }
        return sharedPreferences2.contains(key);
    }
}
