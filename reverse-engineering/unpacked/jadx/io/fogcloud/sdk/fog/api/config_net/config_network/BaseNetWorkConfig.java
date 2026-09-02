package io.fogcloud.sdk.fog.api.config_net.config_network;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import androidx.core.app.ActivityCompat;
import com.alibaba.fastjson.JSON;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.config_net.interfaces.IDiscoveryDeviceListener;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
abstract class BaseNetWorkConfig {
    private static final String TAG = StubApp.getString2(44640);
    protected DatagramSocket mUdpSocket = null;

    BaseNetWorkConfig() {
    }

    protected WifiManager createWifiInfo(WifiManager wifiManager, String str, String str2, int i) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        String string2 = StubApp.getString2(2294);
        wifiConfiguration.SSID = string2 + str + string2;
        if (i == 0) {
            wifiConfiguration.allowedKeyManagement.set(0);
        }
        if (i == 1) {
            wifiConfiguration.preSharedKey = string2 + str2 + string2;
            wifiConfiguration.hiddenSSID = true;
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedKeyManagement.set(1);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
            wifiConfiguration.allowedProtocols.set(0);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.status = 2;
        }
        wifiManager.enableNetwork(wifiManager.addNetwork(wifiConfiguration), true);
        return wifiManager;
    }

    public String getSSID(Context context) {
        return getWifiManager(context).getConnectionInfo().getSSID().replaceAll(StubApp.getString2(2294), "");
    }

    protected void checkWifi(Context context) {
        WifiManager wifiManager = getWifiManager(context);
        wifiManager.startScan();
        for (ScanResult scanResult : wifiManager.getScanResults()) {
            if (scanResult.SSID.contains(StubApp.getString2(44641))) {
                WifiConfiguration wifiConfigurationIsExsits = isExsits(context, scanResult.SSID);
                if (wifiConfigurationIsExsits != null) {
                    wifiManager.enableNetwork(wifiConfigurationIsExsits.networkId, true);
                    return;
                } else {
                    createWifiInfo(wifiManager, scanResult.SSID, "", 0);
                    return;
                }
            }
        }
    }

    public WifiConfiguration isExsits(Context context, String str) {
        WifiManager wifiManager = getWifiManager(context);
        if (ActivityCompat.checkSelfPermission(context, StubApp.getString2(789)) != 0) {
            return null;
        }
        for (WifiConfiguration wifiConfiguration : wifiManager.getConfiguredNetworks()) {
            String str2 = wifiConfiguration.SSID;
            String string2 = StubApp.getString2(2294);
            if (str2.equals(string2 + str + string2)) {
                return wifiConfiguration;
            }
        }
        return null;
    }

    public WifiManager getWifiManager(Context context) {
        return (WifiManager) context.getSystemService(StubApp.getString2(9413));
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [io.fogcloud.sdk.fog.api.config_net.config_network.BaseNetWorkConfig$1] */
    protected void listenerDeviceId(final IDiscoveryDeviceListener iDiscoveryDeviceListener) {
        final boolean[] zArr = {true};
        new Thread() { // from class: io.fogcloud.sdk.fog.api.config_net.config_network.BaseNetWorkConfig.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() throws IOException {
                IDiscoveryDeviceListener iDiscoveryDeviceListener2;
                while (zArr[0]) {
                    try {
                        if (BaseNetWorkConfig.this.mUdpSocket == null) {
                            BaseNetWorkConfig.this.mUdpSocket = new DatagramSocket((SocketAddress) null);
                            BaseNetWorkConfig.this.mUdpSocket.setReuseAddress(true);
                            BaseNetWorkConfig.this.mUdpSocket.bind(new InetSocketAddress(8000));
                        }
                        DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024);
                        BaseNetWorkConfig.this.mUdpSocket.receive(datagramPacket);
                        String str = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                        String string = JSON.parseObject(str).getString(StubApp.getString2("6503"));
                        LogUtil.w(BaseNetWorkConfig.TAG, StubApp.getString2("44634") + str);
                        boolean[] zArr2 = zArr;
                        if (zArr2[0]) {
                            zArr2[0] = false;
                            if (string.equals(StubApp.getString2("44635"))) {
                                String string2 = JSON.parseObject(str).getString(StubApp.getString2("44636"));
                                byte[] bytes = (StubApp.getString2("44637") + string2 + StubApp.getString2("5764")).getBytes();
                                BaseNetWorkConfig.this.mUdpSocket.send(new DatagramPacket(bytes, bytes.length, new InetSocketAddress(8000)));
                                IDiscoveryDeviceListener iDiscoveryDeviceListener3 = iDiscoveryDeviceListener;
                                if (iDiscoveryDeviceListener3 != null) {
                                    iDiscoveryDeviceListener3.onDiscoveryDeviceSuccess(string2);
                                }
                            } else if (string.equals(StubApp.getString2("44638")) && (iDiscoveryDeviceListener2 = iDiscoveryDeviceListener) != null) {
                                iDiscoveryDeviceListener2.onDiscoveryDeviceFail();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        zArr[0] = false;
                        BaseNetWorkConfig.this.closeSocketListener();
                        LogUtil.v(BaseNetWorkConfig.TAG, StubApp.getString2(44639) + e.getMessage());
                    }
                }
            }
        }.start();
    }

    protected void closeSocketListener() {
        DatagramSocket datagramSocket = this.mUdpSocket;
        if (datagramSocket != null) {
            if (datagramSocket.isConnected()) {
                this.mUdpSocket.disconnect();
            }
            if (!this.mUdpSocket.isClosed()) {
                this.mUdpSocket.close();
            }
            this.mUdpSocket = null;
        }
    }
}
