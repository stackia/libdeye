package com.deye.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TestTime.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/deye/utils/TestTime;", "", "()V", "startTime", "", "getStartTime", "()J", "setStartTime", "(J)V", "logTime", "", "msg", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class TestTime {
    public static final TestTime INSTANCE = new TestTime();
    private static long startTime;

    public final void logTime(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
    }

    private TestTime() {
    }

    public final long getStartTime() {
        return startTime;
    }

    public final void setStartTime(long j) {
        startTime = j;
    }
}
