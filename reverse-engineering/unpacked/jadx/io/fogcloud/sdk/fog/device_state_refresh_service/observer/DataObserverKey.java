package io.fogcloud.sdk.fog.device_state_refresh_service.observer;

import android.text.TextUtils;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class DataObserverKey {
    private static LinkedHashMap<DataObserver, String> mDataObserverKeyMap;

    public static LinkedHashMap<DataObserver, String> getDataObserverKeyMap() {
        if (mDataObserverKeyMap == null) {
            mDataObserverKeyMap = new LinkedHashMap<>();
        }
        return mDataObserverKeyMap;
    }

    public static void putDataObserverKeyToMap(DataObserver dataObserver, String str) {
        getDataObserverKeyMap().put(dataObserver, str);
    }

    public static void removeDataObserver(DataObserver dataObserver) {
        if (getDataObserverKeyMap().containsKey(dataObserver)) {
            getDataObserverKeyMap().remove(dataObserver);
        }
    }

    public static DataObserver removeDataObserverByDeviceId(String str) {
        ListIterator listIterator = new ArrayList(getDataObserverKeyMap().entrySet()).listIterator(getDataObserverKeyMap().size());
        while (listIterator.hasPrevious()) {
            Map.Entry entry = (Map.Entry) listIterator.previous();
            if (TextUtils.equals((CharSequence) entry.getValue(), str)) {
                StringBuilder sbAppend = new StringBuilder(StubApp.getString2(45041)).append(getDataObserverKeyMap().size());
                String string2 = StubApp.getString2(1026);
                LogUtil.d(sbAppend.append(string2).append(getDataObserverKeyMap()).toString());
                DataObserver dataObserver = (DataObserver) entry.getKey();
                removeDataObserver(dataObserver);
                LogUtil.d(StubApp.getString2(45042) + getDataObserverKeyMap().size() + string2 + getDataObserverKeyMap());
                return dataObserver;
            }
        }
        return null;
    }
}
