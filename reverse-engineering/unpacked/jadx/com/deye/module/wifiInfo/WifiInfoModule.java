package com.deye.module.wifiInfo;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.stub.StubApp;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: WifiInfoModule.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\b\u0010\f\u001a\u00020\tH\u0007J\b\u0010\r\u001a\u00020\tH\u0007R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/deye/module/wifiInfo/WifiInfoModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "context", "getName", "", "getWifiInfo", "", "callback", "Lcom/facebook/react/bridge/Callback;", "openLocationSetting", "openWifiSetting", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class WifiInfoModule extends ReactContextBaseJavaModule {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = StubApp.getString2(14209);
    private final ReactApplicationContext context;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WifiInfoModule(ReactApplicationContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.context = reactContext;
    }

    /* compiled from: WifiInfoModule.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/deye/module/wifiInfo/WifiInfoModule$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return WifiInfoModule.TAG;
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return StubApp.getString2(14210);
    }

    @ReactMethod
    public final void getWifiInfo(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Object systemService = getReactApplicationContext().getSystemService(StubApp.getString2(9413));
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.wifi.WifiManager");
        String ssid = ((WifiManager) systemService).getConnectionInfo().getSSID();
        Intrinsics.checkNotNull(ssid);
        String string2 = StubApp.getString2(2294);
        if (StringsKt.startsWith$default(ssid, string2, false, 2, (Object) null)) {
            Intrinsics.checkNotNull(ssid);
            if (StringsKt.endsWith$default(ssid, string2, false, 2, (Object) null)) {
                Intrinsics.checkNotNull(ssid);
                ssid = ssid.substring(1, ssid.length() - 1);
                Intrinsics.checkNotNullExpressionValue(ssid, "substring(...)");
            }
        }
        WritableMap writableMapCreateMap = Arguments.createMap();
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        writableMapCreateMap2.putString(StubApp.getString2(14211), ssid);
        writableMapCreateMap.putMap(StubApp.getString2(14212), writableMapCreateMap2);
        Log.e(TAG, StubApp.getString2(14213) + writableMapCreateMap);
        callback.invoke(writableMapCreateMap);
    }

    @ReactMethod
    public final void openWifiSetting() {
        Intent intent = new Intent(StubApp.getString2(13391));
        intent.addFlags(CommonNetImpl.FLAG_AUTH);
        this.context.startActivity(intent);
    }

    @ReactMethod
    public final void openLocationSetting() {
        Intent intent = new Intent(StubApp.getString2(14178));
        intent.addFlags(CommonNetImpl.FLAG_AUTH);
        this.context.startActivity(intent);
    }
}
