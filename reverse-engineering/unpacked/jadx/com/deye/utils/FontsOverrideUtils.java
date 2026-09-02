package com.deye.utils;

import android.content.Context;
import android.graphics.Typeface;
import java.lang.reflect.Field;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class FontsOverrideUtils {
    public static void setDefaultFont(Context context, String str, String str2) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        replaceFont(str, Typeface.createFromAsset(context.getAssets(), str2));
    }

    protected static void replaceFont(String str, Typeface typeface) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        try {
            Field declaredField = Typeface.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(null, typeface);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }
}
