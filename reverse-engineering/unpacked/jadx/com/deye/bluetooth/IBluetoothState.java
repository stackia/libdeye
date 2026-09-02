package com.deye.bluetooth;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class IBluetoothState {
    public static final int STATE_OFF = 10;
    public static final int STATE_ON = 12;
    public static final int STATE_TURNING_OFF = 13;
    public static final int STATE_TURNING_ON = 11;

    @Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface value {
    }
}
