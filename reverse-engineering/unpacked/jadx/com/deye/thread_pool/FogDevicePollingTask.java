package com.deye.thread_pool;

import android.util.Log;
import com.deye.event.RefreshDeviceEvent;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import io.fogcloud.sdk.fog.log.LogDebug;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: FogDevicePollingTask.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0006\u0010\u0012\u001a\u00020\u0003J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fJ\u0006\u0010\u0013\u001a\u00020\u0011J\u0006\u0010\u0014\u001a\u00020\u0011J\u0010\u0010\u0015\u001a\u00020\u00112\b\b\u0002\u0010\u0016\u001a\u00020\u0006J\u0006\u0010\u0017\u001a\u00020\u0011R\u000e\u0010\b\u001a\u00020\u0003X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/deye/thread_pool/FogDevicePollingTask;", "", "deviceId", "", "deviceModelType", "period", "", "(Ljava/lang/String;Ljava/lang/String;J)V", "TAG", "executionCount", "", "isPaused", "", "isRunning", "scheduledFuture", "Ljava/util/concurrent/ScheduledFuture;", "executePolling", "", "getStatus", "pause", "resume", "start", "initialDelay", "stop", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class FogDevicePollingTask {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<ScheduledExecutorService> scheduler$delegate = LazyKt.lazy(FogDevicePollingTask$Companion$scheduler$2.INSTANCE);
    private final String TAG;
    private final String deviceId;
    private final String deviceModelType;
    private int executionCount;
    private volatile boolean isPaused;
    private volatile boolean isRunning;
    private final long period;
    private ScheduledFuture<?> scheduledFuture;

    public FogDevicePollingTask(String deviceId, String deviceModelType, long j) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceModelType, "deviceModelType");
        this.deviceId = deviceId;
        this.deviceModelType = deviceModelType;
        this.period = j;
        this.TAG = StubApp.getString2(14263);
    }

    public /* synthetic */ FogDevicePollingTask(String str, String str2, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? 3000L : j);
    }

    /* compiled from: FogDevicePollingTask.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/deye/thread_pool/FogDevicePollingTask$Companion;", "", "()V", "scheduler", "Ljava/util/concurrent/ScheduledExecutorService;", "getScheduler", "()Ljava/util/concurrent/ScheduledExecutorService;", "scheduler$delegate", "Lkotlin/Lazy;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ScheduledExecutorService getScheduler() {
            Object value = FogDevicePollingTask.scheduler$delegate.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (ScheduledExecutorService) value;
        }
    }

    public static /* synthetic */ void start$default(FogDevicePollingTask fogDevicePollingTask, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 0;
        }
        fogDevicePollingTask.start(j);
    }

    public final void start(long initialDelay) {
        boolean z = this.isRunning;
        String string2 = StubApp.getString2(2566);
        if (z) {
            Log.w(this.TAG, string2 + this.deviceId + StubApp.getString2(14254));
            return;
        }
        this.isRunning = true;
        this.isPaused = false;
        this.executionCount = 0;
        LogDebug.INSTANCE.log(string2 + this.deviceId + StubApp.getString2(2567) + this.deviceModelType + StubApp.getString2(14267) + this.period + StubApp.getString2(14256) + initialDelay + StubApp.getString2(6070));
        this.scheduledFuture = INSTANCE.getScheduler().scheduleWithFixedDelay(new Runnable() { // from class: com.deye.thread_pool.FogDevicePollingTask$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FogDevicePollingTask.start$lambda$0(this.f$0);
            }
        }, initialDelay, this.period, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$0(FogDevicePollingTask this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isPaused) {
            return;
        }
        this$0.executePolling();
    }

    public final void pause() {
        if (this.isRunning) {
            this.isPaused = true;
            LogDebug.INSTANCE.log(StubApp.getString2(2566) + this.deviceId + StubApp.getString2(2567) + this.deviceModelType + StubApp.getString2(14265));
        }
    }

    public final void resume() {
        if (!this.isRunning) {
            start$default(this, 0L, 1, null);
            return;
        }
        this.isPaused = false;
        LogDebug.INSTANCE.log(StubApp.getString2(2566) + this.deviceId + StubApp.getString2(2567) + this.deviceModelType + StubApp.getString2(14266));
    }

    public final void stop() {
        this.isRunning = false;
        this.isPaused = false;
        ScheduledFuture<?> scheduledFuture = this.scheduledFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.scheduledFuture = null;
        LogDebug.INSTANCE.log(StubApp.getString2(2566) + this.deviceId + StubApp.getString2(2567) + this.deviceModelType + StubApp.getString2(14268) + this.executionCount);
    }

    private final void executePolling() {
        try {
            this.executionCount++;
            DeYeHttpRequestManager.getInstance().setPollFogProperties(this.deviceId, new FogCallBack() { // from class: com.deye.thread_pool.FogDevicePollingTask.executePolling.1
                @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                public void onSuccess(String message) throws JSONException {
                    String string2 = StubApp.getString2(2566);
                    try {
                        int i = new JSONObject(message).getJSONObject(StubApp.getString2("13082")).getInt(StubApp.getString2("109"));
                        String string22 = StubApp.getString2(2567);
                        if (i != 10350) {
                            if (FogDevicePollingTask.this.executionCount % 50 == 0) {
                                LogDebug.INSTANCE.log(string2 + FogDevicePollingTask.this.deviceId + string22 + FogDevicePollingTask.this.deviceModelType + StubApp.getString2("14261") + FogDevicePollingTask.this.executionCount);
                            }
                        } else {
                            LogDebug.INSTANCE.log(string2 + FogDevicePollingTask.this.deviceId + string22 + FogDevicePollingTask.this.deviceModelType + StubApp.getString2("14260"));
                            FogDevicePollingTask.this.stop();
                            EventBus.getDefault().post(new RefreshDeviceEvent());
                        }
                    } catch (Exception e) {
                        Log.e(FogDevicePollingTask.this.TAG, string2 + FogDevicePollingTask.this.deviceId + StubApp.getString2(14262), e);
                    }
                }

                @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                public void onFailure(int code, String message) {
                    LogDebug.INSTANCE.log(StubApp.getString2(2566) + FogDevicePollingTask.this.deviceId + StubApp.getString2(2567) + FogDevicePollingTask.this.deviceModelType + StubApp.getString2(14259) + code + StubApp.getString2(14247) + message);
                }
            });
        } catch (Exception e) {
            Log.e(this.TAG, StubApp.getString2(2566) + this.deviceId + StubApp.getString2(14264), e);
        }
    }

    public final String getStatus() {
        if (!this.isRunning) {
            return StubApp.getString2(14250);
        }
        if (this.isPaused) {
            return StubApp.getString2(12086);
        }
        return StubApp.getString2(14251) + this.executionCount + StubApp.getString2(2345);
    }

    /* renamed from: isRunning, reason: from getter */
    public final boolean getIsRunning() {
        return this.isRunning;
    }

    /* renamed from: isPaused, reason: from getter */
    public final boolean getIsPaused() {
        return this.isPaused;
    }
}
