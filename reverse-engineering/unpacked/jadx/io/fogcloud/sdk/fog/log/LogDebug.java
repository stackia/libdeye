package io.fogcloud.sdk.fog.log;

import com.dianping.logan.Logan;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LogDebug.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lio/fogcloud/sdk/fog/log/LogDebug;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "log", "", "msg", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class LogDebug {
    public static final LogDebug INSTANCE = new LogDebug();
    private static final String TAG = StubApp.getString2(45161);

    private LogDebug() {
    }

    public final String getTAG() {
        return TAG;
    }

    public final void log(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        LogUtil.d(TAG, msg);
        Logan.w(msg, 2);
    }
}
