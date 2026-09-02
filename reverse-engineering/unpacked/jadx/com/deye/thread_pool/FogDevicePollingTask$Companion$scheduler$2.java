package com.deye.thread_pool;

import com.stub.StubApp;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: FogDevicePollingTask.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Ljava/util/concurrent/ScheduledExecutorService;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class FogDevicePollingTask$Companion$scheduler$2 extends Lambda implements Function0<ScheduledExecutorService> {
    public static final FogDevicePollingTask$Companion$scheduler$2 INSTANCE = new FogDevicePollingTask$Companion$scheduler$2();

    FogDevicePollingTask$Companion$scheduler$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final ScheduledExecutorService invoke() {
        return Executors.newScheduledThreadPool(4, new ThreadFactory() { // from class: com.deye.thread_pool.FogDevicePollingTask$Companion$scheduler$2$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return FogDevicePollingTask$Companion$scheduler$2.invoke$lambda$1(runnable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Thread invoke$lambda$1(Runnable runnable) {
        Thread thread = new Thread(runnable, StubApp.getString2(14258) + System.currentTimeMillis());
        thread.setDaemon(true);
        return thread;
    }
}
