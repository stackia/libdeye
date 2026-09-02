package com.deye.thread_pool;

import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogDebug;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceHeartbeatManager.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0011\u001a\u00020\u000fJ\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0013J\u0006\u0010\u0014\u001a\u00020\u000fJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0004J\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0004J\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u0004J\u0006\u0010\u001c\u001a\u00020\u001aJ\u000e\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u0004J,\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00042\b\b\u0002\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020\u0016J:\u0010#\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020\u0016J\u0006\u0010&\u001a\u00020\u001aJ\u000e\u0010'\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u0004J\u000e\u0010(\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u00068BX\u0083\u0004¢\u0006\f\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\r¨\u0006)"}, d2 = {"Lcom/deye/thread_pool/DeviceHeartbeatManager;", "", "()V", "TAG", "", "baiduTasks", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/deye/thread_pool/DeviceHeartbeatTask;", "fogTasks", "Lcom/deye/thread_pool/FogDevicePollingTask;", "tasks", "getTasks$annotations", "getTasks", "()Ljava/util/concurrent/ConcurrentHashMap;", "getBaiduTaskCount", "", "getFogTaskCount", "getRunningTaskCount", "getStatus", "", "getTaskCount", "isFogPollingRunning", "", "deviceId", "isHeartbeatRunning", "pauseAll", "", "pauseAllExcept", "resumeAll", "resumeDevice", "startFogPolling", "deviceModelType", "period", "", "startImmediately", "startHeartbeat", "productId", "endpoint", "stopAll", "stopFogPolling", "stopHeartbeat", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DeviceHeartbeatManager {
    public static final DeviceHeartbeatManager INSTANCE = new DeviceHeartbeatManager();
    private static final String TAG = StubApp.getString2(14227);
    private static final ConcurrentHashMap<String, DeviceHeartbeatTask> baiduTasks = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, FogDevicePollingTask> fogTasks = new ConcurrentHashMap<>();

    @Deprecated(message = "Use baiduTasks or fogTasks directly", replaceWith = @ReplaceWith(expression = "baiduTasks", imports = {}))
    private static /* synthetic */ void getTasks$annotations() {
    }

    private DeviceHeartbeatManager() {
    }

    private final ConcurrentHashMap<String, DeviceHeartbeatTask> getTasks() {
        return baiduTasks;
    }

    public final void startHeartbeat(String deviceId, String productId, String endpoint, String deviceModelType, long period, boolean startImmediately) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        Intrinsics.checkNotNullParameter(deviceModelType, "deviceModelType");
        stopHeartbeat(deviceId);
        DeviceHeartbeatTask deviceHeartbeatTask = new DeviceHeartbeatTask(deviceId, productId, endpoint, deviceModelType, period);
        baiduTasks.put(deviceId, deviceHeartbeatTask);
        if (startImmediately) {
            period = 0;
        }
        deviceHeartbeatTask.start(period);
        LogDebug.INSTANCE.log(StubApp.getString2(14240) + deviceId + StubApp.getString2(14239) + startImmediately);
    }

    public static /* synthetic */ void startFogPolling$default(DeviceHeartbeatManager deviceHeartbeatManager, String str, String str2, long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        String str3 = str2;
        if ((i & 4) != 0) {
            j = 3000;
        }
        long j2 = j;
        if ((i & 8) != 0) {
            z = true;
        }
        deviceHeartbeatManager.startFogPolling(str, str3, j2, z);
    }

    public final void startFogPolling(String deviceId, String deviceModelType, long period, boolean startImmediately) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceModelType, "deviceModelType");
        stopFogPolling(deviceId);
        FogDevicePollingTask fogDevicePollingTask = new FogDevicePollingTask(deviceId, deviceModelType, period);
        fogTasks.put(deviceId, fogDevicePollingTask);
        if (startImmediately) {
            period = 0;
        }
        fogDevicePollingTask.start(period);
        LogDebug.INSTANCE.log(StubApp.getString2(14237) + deviceId + StubApp.getString2(14238) + deviceModelType + StubApp.getString2(14239) + startImmediately);
    }

    public final void stopHeartbeat(String deviceId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        DeviceHeartbeatTask deviceHeartbeatTaskRemove = baiduTasks.remove(deviceId);
        if (deviceHeartbeatTaskRemove != null) {
            deviceHeartbeatTaskRemove.stop();
            LogDebug.INSTANCE.log(StubApp.getString2(14243) + deviceId);
        }
    }

    public final void stopFogPolling(String deviceId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        FogDevicePollingTask fogDevicePollingTaskRemove = fogTasks.remove(deviceId);
        if (fogDevicePollingTaskRemove != null) {
            fogDevicePollingTaskRemove.stop();
            LogDebug.INSTANCE.log(StubApp.getString2(14242) + deviceId);
        }
    }

    public final void pauseAll() {
        ConcurrentHashMap<String, DeviceHeartbeatTask> concurrentHashMap = baiduTasks;
        int size = concurrentHashMap.size();
        ConcurrentHashMap<String, FogDevicePollingTask> concurrentHashMap2 = fogTasks;
        int size2 = size + concurrentHashMap2.size();
        LogDebug.INSTANCE.log(StubApp.getString2(14230) + concurrentHashMap.size() + StubApp.getString2(14231) + concurrentHashMap2.size() + StubApp.getString2(14232) + size2);
        Collection<DeviceHeartbeatTask> collectionValues = concurrentHashMap.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        Iterator<T> it2 = collectionValues.iterator();
        while (it2.hasNext()) {
            ((DeviceHeartbeatTask) it2.next()).pause();
        }
        Collection<FogDevicePollingTask> collectionValues2 = fogTasks.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues2, "<get-values>(...)");
        Iterator<T> it3 = collectionValues2.iterator();
        while (it3.hasNext()) {
            ((FogDevicePollingTask) it3.next()).pause();
        }
    }

    public final void resumeAll() {
        ConcurrentHashMap<String, DeviceHeartbeatTask> concurrentHashMap = baiduTasks;
        int size = concurrentHashMap.size();
        ConcurrentHashMap<String, FogDevicePollingTask> concurrentHashMap2 = fogTasks;
        int size2 = size + concurrentHashMap2.size();
        LogDebug.INSTANCE.log(StubApp.getString2(14235) + concurrentHashMap.size() + StubApp.getString2(14231) + concurrentHashMap2.size() + StubApp.getString2(14232) + size2);
        Collection<DeviceHeartbeatTask> collectionValues = concurrentHashMap.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        Iterator<T> it2 = collectionValues.iterator();
        while (it2.hasNext()) {
            ((DeviceHeartbeatTask) it2.next()).resume();
        }
        Collection<FogDevicePollingTask> collectionValues2 = fogTasks.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues2, "<get-values>(...)");
        Iterator<T> it3 = collectionValues2.iterator();
        while (it3.hasNext()) {
            ((FogDevicePollingTask) it3.next()).resume();
        }
    }

    public final void stopAll() {
        ConcurrentHashMap<String, DeviceHeartbeatTask> concurrentHashMap = baiduTasks;
        int size = concurrentHashMap.size();
        ConcurrentHashMap<String, FogDevicePollingTask> concurrentHashMap2 = fogTasks;
        int size2 = size + concurrentHashMap2.size();
        LogDebug.INSTANCE.log(StubApp.getString2(14241) + concurrentHashMap.size() + StubApp.getString2(14231) + concurrentHashMap2.size() + StubApp.getString2(14232) + size2);
        Collection<DeviceHeartbeatTask> collectionValues = concurrentHashMap.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        Iterator<T> it2 = collectionValues.iterator();
        while (it2.hasNext()) {
            ((DeviceHeartbeatTask) it2.next()).stop();
        }
        baiduTasks.clear();
        Collection<FogDevicePollingTask> collectionValues2 = fogTasks.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues2, "<get-values>(...)");
        Iterator<T> it3 = collectionValues2.iterator();
        while (it3.hasNext()) {
            ((FogDevicePollingTask) it3.next()).stop();
        }
        fogTasks.clear();
    }

    public final void pauseAllExcept(String deviceId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        int i = 0;
        for (Map.Entry<String, DeviceHeartbeatTask> entry : baiduTasks.entrySet()) {
            String key = entry.getKey();
            DeviceHeartbeatTask value = entry.getValue();
            if (!Intrinsics.areEqual(key, deviceId)) {
                value.pause();
                i++;
            }
        }
        for (Map.Entry<String, FogDevicePollingTask> entry2 : fogTasks.entrySet()) {
            String key2 = entry2.getKey();
            FogDevicePollingTask value2 = entry2.getValue();
            if (!Intrinsics.areEqual(key2, deviceId)) {
                value2.pause();
                i++;
            }
        }
        LogDebug.INSTANCE.log(StubApp.getString2(14233) + deviceId + StubApp.getString2(14234) + i);
    }

    public final void resumeDevice(String deviceId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        DeviceHeartbeatTask deviceHeartbeatTask = baiduTasks.get(deviceId);
        if (deviceHeartbeatTask != null) {
            deviceHeartbeatTask.resume();
        }
        FogDevicePollingTask fogDevicePollingTask = fogTasks.get(deviceId);
        if (fogDevicePollingTask != null) {
            fogDevicePollingTask.resume();
        }
        LogDebug.INSTANCE.log(StubApp.getString2(14236) + deviceId);
    }

    public final Map<String, String> getStatus() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, DeviceHeartbeatTask> entry : baiduTasks.entrySet()) {
            linkedHashMap.put(StubApp.getString2(14228) + entry.getKey(), entry.getValue().getStatus());
        }
        for (Map.Entry<String, FogDevicePollingTask> entry2 : fogTasks.entrySet()) {
            linkedHashMap.put(StubApp.getString2(14229) + entry2.getKey(), entry2.getValue().getStatus());
        }
        return linkedHashMap;
    }

    public final int getRunningTaskCount() {
        int i;
        Collection<DeviceHeartbeatTask> collectionValues = baiduTasks.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        Collection<DeviceHeartbeatTask> collection = collectionValues;
        int i2 = 0;
        if (collection.isEmpty()) {
            i = 0;
        } else {
            Iterator<T> it2 = collection.iterator();
            i = 0;
            while (it2.hasNext()) {
                if (((DeviceHeartbeatTask) it2.next()).getIsRunning() && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        Collection<FogDevicePollingTask> collectionValues2 = fogTasks.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues2, "<get-values>(...)");
        Collection<FogDevicePollingTask> collection2 = collectionValues2;
        if (!collection2.isEmpty()) {
            Iterator<T> it3 = collection2.iterator();
            while (it3.hasNext()) {
                if (((FogDevicePollingTask) it3.next()).getIsRunning() && (i2 = i2 + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        return i + i2;
    }

    public final boolean isHeartbeatRunning(String deviceId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        DeviceHeartbeatTask deviceHeartbeatTask = baiduTasks.get(deviceId);
        if (deviceHeartbeatTask != null) {
            return deviceHeartbeatTask.getIsRunning();
        }
        return false;
    }

    public final boolean isFogPollingRunning(String deviceId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        FogDevicePollingTask fogDevicePollingTask = fogTasks.get(deviceId);
        if (fogDevicePollingTask != null) {
            return fogDevicePollingTask.getIsRunning();
        }
        return false;
    }

    public final int getTaskCount() {
        return baiduTasks.size() + fogTasks.size();
    }

    public final int getBaiduTaskCount() {
        return baiduTasks.size();
    }

    public final int getFogTaskCount() {
        return fogTasks.size();
    }
}
