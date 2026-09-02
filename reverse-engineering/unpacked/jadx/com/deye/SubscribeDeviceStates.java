package com.deye;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.deye.event.RefreshDeviceEvent;
import com.google.gson.Gson;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.fogmqtt.DeYeFogMqttManager;
import io.fogcloud.sdk.fog.api.mqtt.DeYeMqttManager;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.bean.PropertyResultBean;
import io.fogcloud.sdk.fog.helper.dehumidifier.Hex2DehumidifierBeanString;
import io.fogcloud.sdk.fog.helper.humidifier.Hex2HumidifierBeanString;
import io.fogcloud.sdk.fog.helper.loop_fan.Hex2LoopFanBeanString;
import io.fogcloud.sdk.fog.log.LogDebug;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SubscribeDeviceStates.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 92\u00020\u0001:\u00039:;B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0016J\u0010\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020\u0005H\u0002J\u0018\u0010&\u001a\u00020\"2\u0006\u0010%\u001a\u00020\u00052\u0006\u0010'\u001a\u00020\u0007H\u0002J\u0006\u0010(\u001a\u00020\"J\u0006\u0010)\u001a\u00020\"J\u0010\u0010*\u001a\u00020\"2\u0006\u0010%\u001a\u00020\u0005H\u0002J\u0006\u0010+\u001a\u00020\u0007J\u0016\u0010,\u001a\u00020\u00072\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\u000e\u0010.\u001a\u00020\"2\u0006\u0010%\u001a\u00020\u0005J\u000e\u0010/\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0016J\u0006\u00100\u001a\u00020\"J\u0010\u00101\u001a\u00020\"2\u0006\u0010%\u001a\u00020\u0005H\u0002J$\u00102\u001a\u00020\"2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u00103\u001a\u00020\u00072\u0006\u00104\u001a\u00020\u0007J(\u00105\u001a\u00020\"2\b\u00106\u001a\u0004\u0018\u00010\u000b2\u0006\u00107\u001a\u0002082\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0019\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0011\u001a\u0004\b\u001a\u0010\u000fR\u001a\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020 0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/deye/SubscribeDeviceStates;", "", "()V", "currentDeviceList", "", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "currentHasFog", "", "currentHasOld", "currentSubscribedDeviceIds", "", "", "debounceHandler", "Landroid/os/Handler;", "getDebounceHandler", "()Landroid/os/Handler;", "debounceHandler$delegate", "Lkotlin/Lazy;", "debounceHandlerThread", "Landroid/os/HandlerThread;", "deviceStateUpdateList", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/deye/SubscribeDeviceStates$IDeviceStateListener;", "isFogMqttConnected", "isOldMqttConnected", "mainHandler", "getMainHandler", "mainHandler$delegate", "pendingOnlineUpdates", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/deye/SubscribeDeviceStates$PendingOnlineUpdate;", "pendingRunnables", "Ljava/lang/Runnable;", "addDeviceUpdateListener", "", "listener", "callBackDeviceDateUpdate", "deviceListBean", "callBackDeviceOnlineUpdate", "online", "checkAndReconnectIfNeeded", "cleanup", "executeDataUpdateCallback", "isConnected", "needResubscribe", "deviceListBeanList", "registerDevice", "removeDeviceUpdateListener", "resetConnectionState", "scheduleDataUpdateDebounced", "startListenDevice", "hasFog", "hasOld", "updateBoundDeviceList", "messages", "platform", "", "Companion", "IDeviceStateListener", "PendingOnlineUpdate", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class SubscribeDeviceStates {
    public static final long ONLINE_UPDATE_IMMEDIATE_THRESHOLD_MS = 3000;
    private List<? extends DeviceListBean> currentDeviceList;
    private boolean currentHasFog;
    private boolean currentHasOld;
    private Set<String> currentSubscribedDeviceIds;

    /* renamed from: debounceHandler$delegate, reason: from kotlin metadata */
    private final Lazy debounceHandler;
    private final HandlerThread debounceHandlerThread;
    private CopyOnWriteArrayList<IDeviceStateListener> deviceStateUpdateList;
    private boolean isFogMqttConnected;
    private boolean isOldMqttConnected;

    /* renamed from: mainHandler$delegate, reason: from kotlin metadata */
    private final Lazy mainHandler;
    private final ConcurrentHashMap<String, PendingOnlineUpdate> pendingOnlineUpdates;
    private final ConcurrentHashMap<String, Runnable> pendingRunnables;
    public static final String TAG = StubApp.getString2(13171);

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<SubscribeDeviceStates> instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<SubscribeDeviceStates>() { // from class: com.deye.SubscribeDeviceStates$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SubscribeDeviceStates invoke() {
            return new SubscribeDeviceStates(null);
        }
    });

    /* compiled from: SubscribeDeviceStates.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/deye/SubscribeDeviceStates$IDeviceStateListener;", "", "onDeviceDateStateUpdate", "", "deviceListBean", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "onDeviceOnlineStateUpdate", "online", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface IDeviceStateListener {
        void onDeviceDateStateUpdate(DeviceListBean deviceListBean);

        void onDeviceOnlineStateUpdate(DeviceListBean deviceListBean, boolean online);
    }

    public /* synthetic */ SubscribeDeviceStates(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: SubscribeDeviceStates.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/deye/SubscribeDeviceStates$Companion;", "", "()V", "ONLINE_UPDATE_IMMEDIATE_THRESHOLD_MS", "", "TAG", "", "instance", "Lcom/deye/SubscribeDeviceStates;", "getInstance", "()Lcom/deye/SubscribeDeviceStates;", "instance$delegate", "Lkotlin/Lazy;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SubscribeDeviceStates getInstance() {
            return (SubscribeDeviceStates) SubscribeDeviceStates.instance$delegate.getValue();
        }
    }

    private SubscribeDeviceStates() {
        this.deviceStateUpdateList = new CopyOnWriteArrayList<>();
        HandlerThread handlerThread = new HandlerThread(StubApp.getString2(13172), 5);
        handlerThread.start();
        this.debounceHandlerThread = handlerThread;
        this.debounceHandler = LazyKt.lazy(new Function0<Handler>() { // from class: com.deye.SubscribeDeviceStates$debounceHandler$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Handler invoke() {
                return new Handler(this.this$0.debounceHandlerThread.getLooper());
            }
        });
        this.mainHandler = LazyKt.lazy(new Function0<Handler>() { // from class: com.deye.SubscribeDeviceStates$mainHandler$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Handler invoke() {
                return new Handler(Looper.getMainLooper());
            }
        });
        this.pendingOnlineUpdates = new ConcurrentHashMap<>();
        this.pendingRunnables = new ConcurrentHashMap<>();
        this.currentSubscribedDeviceIds = SetsKt.emptySet();
        this.currentDeviceList = CollectionsKt.emptyList();
    }

    /* compiled from: SubscribeDeviceStates.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/deye/SubscribeDeviceStates$PendingOnlineUpdate;", "", "deviceListBean", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "online", "", "timestamp", "", "(Lio/fogcloud/sdk/fog/bean/DeviceListBean;ZJ)V", "getDeviceListBean", "()Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "getOnline", "()Z", "getTimestamp", "()J", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final /* data */ class PendingOnlineUpdate {
        private final DeviceListBean deviceListBean;
        private final boolean online;
        private final long timestamp;

        public static /* synthetic */ PendingOnlineUpdate copy$default(PendingOnlineUpdate pendingOnlineUpdate, DeviceListBean deviceListBean, boolean z, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                deviceListBean = pendingOnlineUpdate.deviceListBean;
            }
            if ((i & 2) != 0) {
                z = pendingOnlineUpdate.online;
            }
            if ((i & 4) != 0) {
                j = pendingOnlineUpdate.timestamp;
            }
            return pendingOnlineUpdate.copy(deviceListBean, z, j);
        }

        /* renamed from: component1, reason: from getter */
        public final DeviceListBean getDeviceListBean() {
            return this.deviceListBean;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getOnline() {
            return this.online;
        }

        /* renamed from: component3, reason: from getter */
        public final long getTimestamp() {
            return this.timestamp;
        }

        public final PendingOnlineUpdate copy(DeviceListBean deviceListBean, boolean online, long timestamp) {
            Intrinsics.checkNotNullParameter(deviceListBean, "deviceListBean");
            return new PendingOnlineUpdate(deviceListBean, online, timestamp);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PendingOnlineUpdate)) {
                return false;
            }
            PendingOnlineUpdate pendingOnlineUpdate = (PendingOnlineUpdate) other;
            return Intrinsics.areEqual(this.deviceListBean, pendingOnlineUpdate.deviceListBean) && this.online == pendingOnlineUpdate.online && this.timestamp == pendingOnlineUpdate.timestamp;
        }

        public int hashCode() {
            return (((this.deviceListBean.hashCode() * 31) + Boolean.hashCode(this.online)) * 31) + Long.hashCode(this.timestamp);
        }

        public String toString() {
            return StubApp.getString2(13149) + this.deviceListBean + StubApp.getString2(13150) + this.online + StubApp.getString2(8917) + this.timestamp + StubApp.getString2(2345);
        }

        public PendingOnlineUpdate(DeviceListBean deviceListBean, boolean z, long j) {
            Intrinsics.checkNotNullParameter(deviceListBean, "deviceListBean");
            this.deviceListBean = deviceListBean;
            this.online = z;
            this.timestamp = j;
        }

        public final DeviceListBean getDeviceListBean() {
            return this.deviceListBean;
        }

        public final boolean getOnline() {
            return this.online;
        }

        public final long getTimestamp() {
            return this.timestamp;
        }
    }

    private final Handler getDebounceHandler() {
        return (Handler) this.debounceHandler.getValue();
    }

    private final Handler getMainHandler() {
        return (Handler) this.mainHandler.getValue();
    }

    public final boolean isConnected() {
        boolean zIsConnected = this.currentHasOld ? DeYeMqttManager.getInstance().isConnected() : true;
        boolean zIsConnected2 = this.currentHasFog ? DeYeFogMqttManager.getInstance().isConnected() : true;
        LogDebug.INSTANCE.log(StubApp.getString2(13189) + this.currentHasOld + StubApp.getString2(13190) + zIsConnected + StubApp.getString2(13191) + this.currentHasFog + StubApp.getString2(13192) + zIsConnected2);
        return zIsConnected && zIsConnected2;
    }

    private final boolean needResubscribe(List<? extends DeviceListBean> deviceListBeanList) {
        List<? extends DeviceListBean> list = deviceListBeanList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(((DeviceListBean) it2.next()).getDevice_id());
        }
        return !Intrinsics.areEqual(this.currentSubscribedDeviceIds, CollectionsKt.toSet(arrayList));
    }

    public final void resetConnectionState() {
        this.isOldMqttConnected = false;
        this.isFogMqttConnected = false;
        this.currentSubscribedDeviceIds = SetsKt.emptySet();
        this.currentHasFog = false;
        this.currentHasOld = false;
        this.currentDeviceList = CollectionsKt.emptyList();
        LogDebug.INSTANCE.log(StubApp.getString2(13193));
    }

    public final void checkAndReconnectIfNeeded() {
        if (this.currentDeviceList.isEmpty()) {
            LogDebug.INSTANCE.log(StubApp.getString2(13186));
            return;
        }
        LogDebug.INSTANCE.log(StubApp.getString2(13187));
        if (this.currentHasOld) {
            DeYeMqttManager.getInstance().forceReconnectOnForeground();
        }
        if (this.currentHasFog) {
            DeYeFogMqttManager.getInstance().reconnectIfNeeded();
        }
    }

    public final void registerDevice(DeviceListBean deviceListBean) {
        Intrinsics.checkNotNullParameter(deviceListBean, "deviceListBean");
        if (deviceListBean.isFogPlatform()) {
            return;
        }
        DeYeMqttManager.getInstance().setTopicList(deviceListBean.getDevice_id(), deviceListBean.getProduct_id());
    }

    public final void startListenDevice(List<? extends DeviceListBean> deviceListBeanList, boolean hasFog, boolean hasOld) {
        Intrinsics.checkNotNullParameter(deviceListBeanList, "deviceListBeanList");
        this.currentHasFog = hasFog;
        this.currentHasOld = hasOld;
        this.currentDeviceList = deviceListBeanList;
        List<? extends DeviceListBean> list = deviceListBeanList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(((DeviceListBean) it2.next()).getDevice_id());
        }
        this.currentSubscribedDeviceIds = CollectionsKt.toSet(arrayList);
        LogDebug.INSTANCE.log(StubApp.getString2(13194) + deviceListBeanList.size() + StubApp.getString2(13195) + hasOld + StubApp.getString2(13196) + hasFog);
        SubscribeDeviceStates$startListenDevice$iStartDeviceStateListener$1 subscribeDeviceStates$startListenDevice$iStartDeviceStateListener$1 = new SubscribeDeviceStates$startListenDevice$iStartDeviceStateListener$1(this, deviceListBeanList, hasOld, hasFog);
        DeYeMqttManager.getInstance().isConnected();
        boolean zIsConnected = DeYeFogMqttManager.getInstance().isConnected();
        if (hasOld) {
            LogDebug.INSTANCE.log(StubApp.getString2(13197));
            DeYeMqttManager.getInstance().startListenDevice(subscribeDeviceStates$startListenDevice$iStartDeviceStateListener$1);
        }
        if (hasFog && !zIsConnected) {
            LogDebug.INSTANCE.log(StubApp.getString2(13198));
            DeYeFogMqttManager.getInstance().startListenDevice(subscribeDeviceStates$startListenDevice$iStartDeviceStateListener$1);
            StringBuilder sb = new StringBuilder();
            Iterator<? extends DeviceListBean> it3 = deviceListBeanList.iterator();
            while (it3.hasNext()) {
                sb.append(it3.next().toSimpleString() + StubApp.getString2(450));
            }
            return;
        }
        LogDebug.INSTANCE.log(StubApp.getString2(13199));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateBoundDeviceList(String messages, int platform, List<? extends DeviceListBean> deviceListBeanList) {
        JSONObject jSONObject;
        boolean zOptBoolean;
        Object next;
        JSONObject jSONObjectOptJSONObject;
        JSONObject jSONObjectOptJSONObject2;
        if (messages == null) {
            return;
        }
        try {
            jSONObject = new JSONObject(messages);
        } catch (JSONException e) {
            e.printStackTrace();
            jSONObject = null;
        }
        if (jSONObject == null) {
            return;
        }
        String string2 = StubApp.getString2(6619);
        if (jSONObject.has(string2)) {
            String string22 = StubApp.getString2(13184);
            if (jSONObject.has(string22)) {
                String strOptString = jSONObject.optString(string22);
                JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject(string2);
                Iterator<T> it2 = this.currentDeviceList.iterator();
                while (true) {
                    zOptBoolean = false;
                    if (!it2.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it2.next();
                    String device_id = ((DeviceListBean) next).getDevice_id();
                    Intrinsics.checkNotNull(strOptString);
                    Intrinsics.checkNotNull(device_id);
                    String str = device_id;
                    if (StringsKt.contains$default(strOptString, str, false, 2, (Object) null)) {
                        break;
                    }
                    String strOptString2 = jSONObject.optString(string2);
                    Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                    if (StringsKt.contains$default(strOptString2, str, false, 2, (Object) null)) {
                        break;
                    }
                }
                DeviceListBean deviceListBean = (DeviceListBean) next;
                if (deviceListBean != null) {
                    String string23 = StubApp.getString2(1311);
                    String string24 = StubApp.getString2(100);
                    if (platform == 1) {
                        Intrinsics.checkNotNull(strOptString);
                        if (StringsKt.indexOf$default(strOptString, StubApp.getString2(1311), 0, false, 6, (Object) null) < 0) {
                            if (deviceListBean.isLoopFan()) {
                                deviceListBean.loopFanBean = Hex2LoopFanBeanString.getLoopFanBeanString(jSONObject);
                            } else if (deviceListBean.isHumidifier()) {
                                deviceListBean.setDehumidifierBean(Hex2HumidifierBeanString.getHumidifierBeanString(jSONObject));
                            } else {
                                deviceListBean.setDehumidifierBean(Hex2DehumidifierBeanString.getDehumidfierBeanString(jSONObject));
                            }
                            deviceListBean.setOnline(true);
                            callBackDeviceDateUpdate(deviceListBean);
                            return;
                        }
                        if (jSONObjectOptJSONObject3 != null && (jSONObjectOptJSONObject2 = jSONObjectOptJSONObject3.optJSONObject(string24)) != null) {
                            zOptBoolean = jSONObjectOptJSONObject2.optBoolean(string23);
                        }
                        deviceListBean.setOnline(Boolean.valueOf(zOptBoolean));
                        callBackDeviceOnlineUpdate(deviceListBean, zOptBoolean);
                        return;
                    }
                    String strOptString3 = (jSONObjectOptJSONObject3 == null || (jSONObjectOptJSONObject = jSONObjectOptJSONObject3.optJSONObject(string24)) == null) ? null : jSONObjectOptJSONObject.optString(StubApp.getString2(211));
                    if (strOptString3 != null && strOptString3.length() == 0) {
                        JSONObject jSONObjectOptJSONObject4 = jSONObjectOptJSONObject3 != null ? jSONObjectOptJSONObject3.optJSONObject(string24) : null;
                        PropertyResultBean propertyResultBean = (PropertyResultBean) new Gson().fromJson(jSONObjectOptJSONObject4 != null ? jSONObjectOptJSONObject4.optString(StubApp.getString2(13185)) : null, PropertyResultBean.class);
                        if (propertyResultBean != null) {
                            deviceListBean.setDehumidifierBean(FogDeviceManager.INSTANCE.convert(propertyResultBean));
                            callBackDeviceDateUpdate(deviceListBean);
                            return;
                        }
                        return;
                    }
                    boolean z = !StringsKt.equals$default(strOptString3, StubApp.getString2(9429), false, 2, (Object) null);
                    if (strOptString3 != null && strOptString3.equals(string23)) {
                        EventBus.getDefault().post(new RefreshDeviceEvent());
                    }
                    deviceListBean.setOnline(Boolean.valueOf(z));
                    callBackDeviceOnlineUpdate(deviceListBean, z);
                }
            }
        }
    }

    private final void callBackDeviceDateUpdate(DeviceListBean deviceListBean) {
        if (this.deviceStateUpdateList.size() == 0) {
            return;
        }
        scheduleDataUpdateDebounced(deviceListBean);
    }

    private final void scheduleDataUpdateDebounced(DeviceListBean deviceListBean) {
        final String device_id = deviceListBean.getDevice_id();
        final long jCurrentTimeMillis = System.currentTimeMillis();
        long lastSendTime = jCurrentTimeMillis - CommandManger.INSTANCE.getLastSendTime();
        Runnable runnable = this.pendingRunnables.get(device_id);
        if (runnable != null) {
            getDebounceHandler().removeCallbacks(runnable);
        }
        String string2 = StubApp.getString2(13178);
        if (lastSendTime >= 3000) {
            LogDebug.INSTANCE.log(StubApp.getString2(13179) + lastSendTime + string2 + device_id);
            executeDataUpdateCallback(deviceListBean);
            return;
        }
        LogDebug.INSTANCE.log(StubApp.getString2(13180) + lastSendTime + string2 + device_id);
        ConcurrentHashMap<String, PendingOnlineUpdate> concurrentHashMap = this.pendingOnlineUpdates;
        Intrinsics.checkNotNull(device_id);
        concurrentHashMap.put(device_id, new PendingOnlineUpdate(deviceListBean, true, jCurrentTimeMillis));
        Runnable runnable2 = new Runnable() { // from class: com.deye.SubscribeDeviceStates$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SubscribeDeviceStates.scheduleDataUpdateDebounced$lambda$6(this.f$0, device_id, jCurrentTimeMillis);
            }
        };
        this.pendingRunnables.put(device_id, runnable2);
        getDebounceHandler().postDelayed(runnable2, 3000 - lastSendTime);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void scheduleDataUpdateDebounced$lambda$6(SubscribeDeviceStates this$0, String str, long j) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PendingOnlineUpdate pendingOnlineUpdate = this$0.pendingOnlineUpdates.get(str);
        if (pendingOnlineUpdate == null || pendingOnlineUpdate.getTimestamp() != j) {
            LogDebug.INSTANCE.log(StubApp.getString2(13183) + str);
            return;
        }
        if (pendingOnlineUpdate.getTimestamp() < CommandManger.INSTANCE.getLastSendTime()) {
            LogDebug.INSTANCE.log(StubApp.getString2(13181) + str);
            this$0.pendingOnlineUpdates.remove(str);
            this$0.pendingRunnables.remove(str);
        } else {
            LogDebug.INSTANCE.log(StubApp.getString2(13182) + str);
            this$0.executeDataUpdateCallback(pendingOnlineUpdate.getDeviceListBean());
            this$0.pendingOnlineUpdates.remove(str);
            this$0.pendingRunnables.remove(str);
        }
    }

    private final void executeDataUpdateCallback(final DeviceListBean deviceListBean) {
        if (this.deviceStateUpdateList.size() == 0) {
            return;
        }
        getMainHandler().post(new Runnable() { // from class: com.deye.SubscribeDeviceStates$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SubscribeDeviceStates.executeDataUpdateCallback$lambda$7(deviceListBean, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void executeDataUpdateCallback$lambda$7(DeviceListBean deviceListBean, SubscribeDeviceStates this$0) {
        Intrinsics.checkNotNullParameter(deviceListBean, "$deviceListBean");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (deviceListBean.isFogPlatform()) {
            DeviceCacheManager deviceCacheManager = DeviceCacheManager.INSTANCE;
            String device_id = deviceListBean.getDevice_id();
            Intrinsics.checkNotNullExpressionValue(device_id, "getDevice_id(...)");
            String product_id = deviceListBean.getProduct_id();
            Intrinsics.checkNotNullExpressionValue(product_id, "getProduct_id(...)");
            DehumidifierBean dehumidifierBean = deviceListBean.getDehumidifierBean();
            Intrinsics.checkNotNullExpressionValue(dehumidifierBean, "getDehumidifierBean(...)");
            deviceCacheManager.setDeviceCache(device_id, product_id, dehumidifierBean);
        }
        if (this$0.deviceStateUpdateList.isEmpty()) {
            LogDebug.INSTANCE.log(StubApp.getString2(13175) + deviceListBean.getDevice_id() + StubApp.getString2(13176));
            return;
        }
        Iterator<IDeviceStateListener> it2 = this$0.deviceStateUpdateList.iterator();
        while (it2.hasNext()) {
            try {
                it2.next().onDeviceDateStateUpdate(deviceListBean);
            } catch (Exception e) {
                LogDebug.INSTANCE.log(StubApp.getString2(13177) + deviceListBean.getDevice_id() + StubApp.getString2(1521) + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private final void callBackDeviceOnlineUpdate(DeviceListBean deviceListBean, boolean online) {
        if (this.deviceStateUpdateList.size() == 0) {
            return;
        }
        LogDebug.INSTANCE.log(StubApp.getString2(13173) + online + StubApp.getString2(13174) + deviceListBean);
        Iterator<IDeviceStateListener> it2 = this.deviceStateUpdateList.iterator();
        while (it2.hasNext()) {
            it2.next().onDeviceOnlineStateUpdate(deviceListBean, online);
        }
    }

    public final void addDeviceUpdateListener(IDeviceStateListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.deviceStateUpdateList.contains(listener)) {
            return;
        }
        this.deviceStateUpdateList.add(listener);
    }

    public final void removeDeviceUpdateListener(IDeviceStateListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.deviceStateUpdateList.remove(listener);
    }

    public final void cleanup() {
        getDebounceHandler().removeCallbacksAndMessages(null);
        this.debounceHandlerThread.quitSafely();
        this.pendingOnlineUpdates.clear();
        this.pendingRunnables.clear();
        LogDebug.INSTANCE.log(StubApp.getString2(13188));
    }
}
