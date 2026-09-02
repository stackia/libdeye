package io.fogcloud.sdk.easylink.plus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import com.stub.StubApp;
import io.fogcloud.sdk.easylink.helper.ProbeReqData;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class EasyLink_minus {
    private Thread mCallbackThread;
    private Context mContext;
    private int mErrorId;
    private IntentFilter mIntentFilter;
    private List<Integer> mNetId;
    private BroadcastReceiver mReceiver;
    private boolean mScanning;
    boolean stopSending;

    public boolean isScanning() {
        return this.mScanning;
    }

    public EasyLink_minus(Context context, Thread thread) {
        this(context);
        this.mCallbackThread = thread;
    }

    public EasyLink_minus(Context context) {
        this.stopSending = false;
        this.mIntentFilter = null;
        this.mErrorId = 0;
        this.mReceiver = new BroadcastReceiver() { // from class: io.fogcloud.sdk.easylink.plus.EasyLink_minus.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                EasyLink_minus.this.mScanning = false;
                EasyLink_minus.this.mContext.unregisterReceiver(this);
                if (intent.getAction().equals(StubApp.getString2(8690))) {
                    System.out.println(StubApp.getString2(44627));
                    EasyLink_minus.this.mScanning = false;
                }
                if (intent.getAction().equals(StubApp.getString2(8677))) {
                    try {
                        Parcelable parcelableExtra = intent.getParcelableExtra(StubApp.getString2("44628"));
                        if (parcelableExtra != null && !((NetworkInfo) parcelableExtra).isAvailable()) {
                            EasyLink_minus.this.mErrorId = 102;
                            EasyLink_minus.this.mScanning = false;
                            EasyLink_minus.this.clearNetList();
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                try {
                    if (intent.getIntExtra(StubApp.getString2("36188"), 0) != 0) {
                        EasyLink_minus.this.mErrorId = 101;
                        EasyLink_minus.this.mScanning = false;
                    } else {
                        EasyLink_minus.this.clearNetList();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        };
        this.mNetId = new ArrayList();
        this.mContext = context;
        IntentFilter intentFilter = new IntentFilter();
        this.mIntentFilter = intentFilter;
        intentFilter.addAction(StubApp.getString2(8690));
        this.mIntentFilter.addAction(StubApp.getString2(8675));
        this.mIntentFilter.addAction(StubApp.getString2(8677));
        this.mContext.registerReceiver(this.mReceiver, this.mIntentFilter);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [io.fogcloud.sdk.easylink.plus.EasyLink_minus$2] */
    public void transmitSettings(final String str, final String str2, final int i) {
        new Thread() { // from class: io.fogcloud.sdk.easylink.plus.EasyLink_minus.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                EasyLink_minus.this.startTransmit(str, str2, i);
            }
        }.start();
    }

    public boolean startTransmit(String str, String str2, int i) {
        String[] strArrBgProtocol;
        ArrayList arrayList;
        this.stopSending = false;
        try {
            strArrBgProtocol = new ProbeReqData().bgProtocol(str, str2, i);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            strArrBgProtocol = null;
        }
        WifiManager wifiManager = (WifiManager) this.mContext.getSystemService(StubApp.getString2(9413));
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.BSSID = null;
        wifiConfiguration.preSharedKey = null;
        wifiConfiguration.wepKeys = new String[4];
        wifiConfiguration.wepTxKeyIndex = 0;
        wifiConfiguration.priority = 0;
        wifiConfiguration.hiddenSSID = true;
        wifiConfiguration.allowedKeyManagement.set(0);
        wifiConfiguration.allowedGroupCiphers.set(0);
        wifiConfiguration.allowedGroupCiphers.set(1);
        wifiConfiguration.allowedGroupCiphers.set(2);
        wifiConfiguration.allowedGroupCiphers.set(3);
        wifiConfiguration.allowedPairwiseCiphers.set(1);
        wifiConfiguration.allowedPairwiseCiphers.set(2);
        wifiConfiguration.allowedProtocols.set(0);
        wifiConfiguration.allowedProtocols.set(1);
        while (!this.stopSending) {
            for (int i2 = 1; i2 < strArrBgProtocol.length; i2++) {
                wifiConfiguration.SSID = String.format(StubApp.getString2(33813), strArrBgProtocol[i2]);
                wifiManager.addNetwork(wifiConfiguration);
                wifiManager.saveConfiguration();
                for (WifiConfiguration wifiConfiguration2 : wifiManager.getConfiguredNetworks()) {
                    if (wifiConfiguration2.SSID.equals(wifiConfiguration.SSID)) {
                        this.mNetId.add(Integer.valueOf(wifiConfiguration2.networkId));
                    }
                }
                try {
                    Iterator<Integer> it2 = this.mNetId.iterator();
                    while (it2.hasNext()) {
                        int iIntValue = it2.next().intValue();
                        wifiManager.disableNetwork(iIntValue);
                        wifiManager.enableNetwork(iIntValue, false);
                        wifiManager.startScan();
                        Thread.sleep(50L);
                    }
                    arrayList = new ArrayList();
                } catch (Exception unused) {
                    arrayList = new ArrayList();
                } catch (Throwable th) {
                    this.mNetId = new ArrayList();
                    throw th;
                }
                this.mNetId = arrayList;
            }
        }
        return true;
    }

    private void sendProbeRequest(WifiManager wifiManager, List<Integer> list) {
        ArrayList arrayList;
        while (!this.stopSending) {
            try {
                Iterator<Integer> it2 = list.iterator();
                while (it2.hasNext()) {
                    int iIntValue = it2.next().intValue();
                    wifiManager.disableNetwork(iIntValue);
                    wifiManager.enableNetwork(iIntValue, false);
                    wifiManager.startScan();
                    Thread.sleep(50L);
                }
            } catch (Exception unused) {
                arrayList = new ArrayList();
            } catch (Throwable th) {
                this.mNetId = new ArrayList();
                throw th;
            }
        }
        arrayList = new ArrayList();
        this.mNetId = arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNetList() {
        WifiManager wifiManager = (WifiManager) this.mContext.getSystemService(StubApp.getString2(9413));
        if (wifiManager == null || wifiManager.getConfiguredNetworks() == null) {
            return;
        }
        for (WifiConfiguration wifiConfiguration : wifiManager.getConfiguredNetworks()) {
            for (byte b : wifiConfiguration.SSID.replaceAll(StubApp.getString2(2294), "").getBytes()) {
                if (b == 1) {
                    wifiManager.removeNetwork(wifiConfiguration.networkId);
                    wifiManager.saveConfiguration();
                }
            }
        }
    }

    public void stopTransmitting() {
        this.stopSending = true;
        clearNetList();
    }
}
