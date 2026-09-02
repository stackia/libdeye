package com.deye.utils;

import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ControlUtil.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\bR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/deye/utils/ControlUtil;", "", "()V", "CONTINUE_SETTING", "", "getCONTINUE_SETTING", "()Ljava/lang/String;", "isContinueOpen", "", "deviceId", "setContinueOpen", "", "isOpen", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class ControlUtil {
    public static final ControlUtil INSTANCE = new ControlUtil();
    private static final String CONTINUE_SETTING = StubApp.getString2(14306);

    private ControlUtil() {
    }

    public final String getCONTINUE_SETTING() {
        return CONTINUE_SETTING;
    }

    public final boolean isContinueOpen(String deviceId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        return MMKV.mmkvWithID(CONTINUE_SETTING).getBoolean(deviceId, false);
    }

    public final void setContinueOpen(String deviceId, boolean isOpen) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        MMKV.mmkvWithID(CONTINUE_SETTING).putBoolean(deviceId, isOpen);
    }
}
