package com.deye;

import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceCacheManager.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u0012J\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0006J\u001e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0006R6\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/deye/DeviceCacheManager;", "", "()V", "map", "Ljava/util/HashMap;", "", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "Lkotlin/collections/HashMap;", "getMap", "()Ljava/util/HashMap;", "setMap", "(Ljava/util/HashMap;)V", "timestampMap", "", "", "getDeviceCache", "id", "checkValid", "", "setDeviceCache", "", "productId", "bean", "setHttpDeviceCache", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DeviceCacheManager {
    public static final DeviceCacheManager INSTANCE = new DeviceCacheManager();
    private static HashMap<String, DehumidifierBean> map = new HashMap<>();
    private static final Map<String, Long> timestampMap = new LinkedHashMap();

    private DeviceCacheManager() {
    }

    public final HashMap<String, DehumidifierBean> getMap() {
        return map;
    }

    public final void setMap(HashMap<String, DehumidifierBean> map2) {
        Intrinsics.checkNotNullParameter(map2, "<set-?>");
        map = map2;
    }

    public final void setDeviceCache(String id, String productId, DehumidifierBean bean) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        map.put(id, bean);
        timestampMap.put(id, Long.valueOf(System.currentTimeMillis()));
    }

    public final void setHttpDeviceCache(String id, String productId, DehumidifierBean bean) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(bean, "bean");
        if (map.get(id) != null) {
            Map<String, Long> map2 = timestampMap;
            if (map2.get(id) != null) {
                Long l = map2.get(id);
                Intrinsics.checkNotNull(l);
                if (System.currentTimeMillis() - l.longValue() > 3000) {
                    setDeviceCache(id, productId, bean);
                    return;
                }
                return;
            }
        }
        setDeviceCache(id, productId, bean);
    }

    public static /* synthetic */ DehumidifierBean getDeviceCache$default(DeviceCacheManager deviceCacheManager, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return deviceCacheManager.getDeviceCache(str, z);
    }

    public final DehumidifierBean getDeviceCache(String id, boolean checkValid) {
        Intrinsics.checkNotNullParameter(id, "id");
        if (!checkValid) {
            return map.get(id);
        }
        Long l = timestampMap.get(id);
        if (l == null) {
            return null;
        }
        if (System.currentTimeMillis() - l.longValue() > 20000) {
            return null;
        }
        return map.get(id);
    }
}
