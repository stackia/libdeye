package com.deye.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.deye.MxchipApplication;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: WifiScannerUtil.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u000e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u001c2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0006\u0010\u001e\u001a\u00020\u001cR \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\f\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/deye/helper/WifiScannerUtil;", "", "()V", "cacheList", "", "Lcom/deye/helper/WifiScanResult;", "getCacheList", "()Ljava/util/List;", "setCacheList", "(Ljava/util/List;)V", "callback", "Lcom/deye/helper/WifiScanCallback;", "connectWifiInfo", "getConnectWifiInfo", "()Lcom/deye/helper/WifiScanResult;", "wifiManager", "Landroid/net/wifi/WifiManager;", "wifiScanReceiver", "Landroid/content/BroadcastReceiver;", "checkLocationPermission", "", "is24GHz", "freq", "", "is5GHz", "is60GHz", "is6GHz", "registerReceiver", "", "startScan", "stopScan", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class WifiScannerUtil {
    public static final WifiScannerUtil INSTANCE = new WifiScannerUtil();
    private static List<WifiScanResult> cacheList;
    private static WifiScanCallback callback;
    private static final WifiManager wifiManager;
    private static BroadcastReceiver wifiScanReceiver;

    public final boolean is24GHz(int freq) {
        return freq > 2400 && freq < 2500;
    }

    public final boolean is5GHz(int freq) {
        return freq > 4900 && freq < 5900;
    }

    public final boolean is60GHz(int freq) {
        return freq > 57240 && freq < 70240;
    }

    public final boolean is6GHz(int freq) {
        return freq > 5925 && freq < 7125;
    }

    private WifiScannerUtil() {
    }

    static {
        Object systemService = MxchipApplication.getInstance().getSystemService(StubApp.getString2(9413));
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.wifi.WifiManager");
        wifiManager = (WifiManager) systemService;
        cacheList = new ArrayList();
    }

    public final List<WifiScanResult> getCacheList() {
        return cacheList;
    }

    public final void setCacheList(List<WifiScanResult> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        cacheList = list;
    }

    public final WifiScanResult getConnectWifiInfo() {
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return null;
        }
        WifiScanResult wifiScanResult = new WifiScanResult();
        wifiScanResult.BSSID = connectionInfo.getBSSID();
        String ssid = connectionInfo.getSSID();
        Intrinsics.checkNotNullExpressionValue(ssid, "getSSID(...)");
        wifiScanResult.SSID = StringsKt.replace$default(ssid, StubApp.getString2(2294), "", false, 4, (Object) null);
        wifiScanResult.frequency = connectionInfo.getFrequency();
        wifiScanResult.is24GHz = is24GHz(connectionInfo.getFrequency());
        wifiScanResult.signalLevel = WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 3);
        return wifiScanResult;
    }

    public final void startScan(WifiScanCallback callback2) {
        callback = callback2;
        if (!checkLocationPermission()) {
            if (callback2 != null) {
                callback2.onScanFailure(StubApp.getString2(14190));
            }
        } else {
            registerReceiver();
            if (wifiManager.startScan() || callback2 == null) {
                return;
            }
            callback2.onScanFailure(StubApp.getString2(14191));
        }
    }

    public final void stopScan() {
        try {
            if (wifiScanReceiver != null) {
                MxchipApplication.getInstance().unregisterReceiver(wifiScanReceiver);
                wifiScanReceiver = null;
            }
        } catch (Exception e) {
            Log.e(StubApp.getString2(7722), StubApp.getString2(14192), e);
        }
    }

    private final void registerReceiver() {
        if (wifiScanReceiver != null) {
            try {
                MxchipApplication.getInstance().unregisterReceiver(wifiScanReceiver);
            } catch (Exception unused) {
            }
        }
        wifiScanReceiver = new BroadcastReceiver() { // from class: com.deye.helper.WifiScannerUtil.registerReceiver.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                if (Intrinsics.areEqual(StubApp.getString2(8690), intent.getAction())) {
                    if (ActivityCompat.checkSelfPermission(context, StubApp.getString2(789)) != 0) {
                        WifiScanCallback wifiScanCallback = WifiScannerUtil.callback;
                        if (wifiScanCallback != null) {
                            wifiScanCallback.onScanFailure(StubApp.getString2(14188));
                            return;
                        }
                        return;
                    }
                    List<ScanResult> scanResults = WifiScannerUtil.wifiManager.getScanResults();
                    if (scanResults == null || scanResults.isEmpty()) {
                        WifiScanCallback wifiScanCallback2 = WifiScannerUtil.callback;
                        if (wifiScanCallback2 != null) {
                            wifiScanCallback2.onScanFailure(StubApp.getString2(14189));
                            return;
                        }
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    for (ScanResult scanResult : scanResults) {
                        if (!TextUtils.isEmpty(scanResult.SSID)) {
                            WifiScanResult wifiScanResult = new WifiScanResult();
                            wifiScanResult.BSSID = scanResult.BSSID;
                            String SSID = scanResult.SSID;
                            Intrinsics.checkNotNullExpressionValue(SSID, "SSID");
                            wifiScanResult.SSID = StringsKt.replace$default(SSID, StubApp.getString2(2294), "", false, 4, (Object) null);
                            wifiScanResult.frequency = scanResult.frequency;
                            wifiScanResult.is24GHz = WifiScannerUtil.INSTANCE.is24GHz(scanResult.frequency);
                            wifiScanResult.signalLevel = WifiManager.calculateSignalLevel(scanResult.level, 3);
                            if (!linkedHashMap.containsKey(scanResult.SSID)) {
                                String SSID2 = scanResult.SSID;
                                Intrinsics.checkNotNullExpressionValue(SSID2, "SSID");
                                linkedHashMap.put(SSID2, wifiScanResult);
                            } else {
                                WifiScanResult wifiScanResult2 = (WifiScanResult) linkedHashMap.get(scanResult.SSID);
                                if (wifiScanResult2 == null || !wifiScanResult2.is24GHz) {
                                    String SSID3 = scanResult.SSID;
                                    Intrinsics.checkNotNullExpressionValue(SSID3, "SSID");
                                    linkedHashMap.put(SSID3, wifiScanResult);
                                }
                            }
                        }
                    }
                    arrayList.addAll(linkedHashMap.values());
                    WifiScannerUtil.INSTANCE.getCacheList().clear();
                    WifiScannerUtil.INSTANCE.getCacheList().addAll(arrayList);
                    WifiScanCallback wifiScanCallback3 = WifiScannerUtil.callback;
                    if (wifiScanCallback3 != null) {
                        wifiScanCallback3.onScanResults(arrayList);
                    }
                }
            }
        };
        MxchipApplication.getInstance().registerReceiver(wifiScanReceiver, new IntentFilter(StubApp.getString2(8690)));
    }

    private final boolean checkLocationPermission() {
        return ActivityCompat.checkSelfPermission((Context) MxchipApplication.getInstance(), StubApp.getString2(789)) == 0;
    }
}
