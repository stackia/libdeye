package io.fogcloud.sdk.fog.api.config_net.config_network;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.config_net.interfaces.IDiscoverySoftApListener;
import io.fogcloud.sdk.fog.log.LogUtil;
import io.fogcloud.sdk.fog.utils.FogUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.servlet.http.HttpServletResponse;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class SoftAP_NetWorkConfigManager extends BaseNetWorkConfig {
    private static final String TAG = StubApp.getString2(44648);
    private static volatile SoftAP_NetWorkConfigManager sSoftAP_NetWorkConfigManager;
    private boolean mIsStopSoftAp = true;
    private Socket mTcpSocket;

    @Override // io.fogcloud.sdk.fog.api.config_net.config_network.BaseNetWorkConfig
    public /* bridge */ /* synthetic */ String getSSID(Context context) {
        return super.getSSID(context);
    }

    @Override // io.fogcloud.sdk.fog.api.config_net.config_network.BaseNetWorkConfig
    public /* bridge */ /* synthetic */ WifiManager getWifiManager(Context context) {
        return super.getWifiManager(context);
    }

    @Override // io.fogcloud.sdk.fog.api.config_net.config_network.BaseNetWorkConfig
    public /* bridge */ /* synthetic */ WifiConfiguration isExsits(Context context, String str) {
        return super.isExsits(context, str);
    }

    private SoftAP_NetWorkConfigManager() {
    }

    public static SoftAP_NetWorkConfigManager getInstance() {
        if (sSoftAP_NetWorkConfigManager == null) {
            synchronized (SoftAP_NetWorkConfigManager.class) {
                if (sSoftAP_NetWorkConfigManager == null) {
                    sSoftAP_NetWorkConfigManager = new SoftAP_NetWorkConfigManager();
                }
            }
        }
        return sSoftAP_NetWorkConfigManager;
    }

    public void startCheckSoftApVersion(final Context context, final String str, final String str2, final IDiscoverySoftApListener iDiscoverySoftApListener) {
        this.mIsStopSoftAp = false;
        final boolean[] zArr = {true};
        final boolean[] zArr2 = {true};
        if (zArr[0] && getSSID(context).contains(StubApp.getString2(13473))) {
            LogUtil.w(TAG, StubApp.getString2(44649));
            new Thread(new Runnable() { // from class: io.fogcloud.sdk.fog.api.config_net.config_network.SoftAP_NetWorkConfigManager.1
                @Override // java.lang.Runnable
                public void run() throws InterruptedException, IOException {
                    String str3;
                    Exception e;
                    String str4 = "";
                    boolean z = true;
                    while (zArr[0] && !SoftAP_NetWorkConfigManager.this.mIsStopSoftAp) {
                        try {
                            SoftAP_NetWorkConfigManager.this.mTcpSocket = new Socket();
                            SoftAP_NetWorkConfigManager.this.mTcpSocket.connect(new InetSocketAddress(InetAddress.getByName(StubApp.getString2("44642")), 30123), 2000);
                            LogUtil.w(SoftAP_NetWorkConfigManager.TAG, StubApp.getString2("44643"));
                            InputStream inputStream = SoftAP_NetWorkConfigManager.this.mTcpSocket.getInputStream();
                            byte[] bArr = new byte[10000];
                            while (z) {
                                int i = inputStream.read(bArr);
                                str3 = new String(bArr, 0, i, StubApp.getString2("6224"));
                                try {
                                    if (str3.getBytes().length == i) {
                                        z = false;
                                    }
                                    str4 = str3;
                                } catch (Exception e2) {
                                    e = e2;
                                    LogUtil.v(SoftAP_NetWorkConfigManager.TAG, StubApp.getString2(44646) + zArr[0] + StubApp.getString2(44647) + (!SoftAP_NetWorkConfigManager.this.mIsStopSoftAp) + e.getMessage());
                                    e.printStackTrace();
                                    str4 = str3;
                                }
                            }
                            LogUtil.w(SoftAP_NetWorkConfigManager.TAG, StubApp.getString2("44644") + str4);
                            if (!FogUtils.isNullString(str4) && FogUtils.getCode(str4) == 100 && zArr2[0]) {
                                IDiscoverySoftApListener iDiscoverySoftApListener2 = iDiscoverySoftApListener;
                                if (iDiscoverySoftApListener2 != null) {
                                    iDiscoverySoftApListener2.onDiscoverySoftAp();
                                }
                                zArr2[0] = false;
                                zArr[0] = false;
                                SoftAP_NetWorkConfigManager.this.mIsStopSoftAp = true;
                                LogUtil.w(SoftAP_NetWorkConfigManager.TAG, StubApp.getString2("44645") + str4);
                                JSON.parseObject(str4).getString(StubApp.getString2("100"));
                                SoftAP_NetWorkConfigManager.this.startSoftAp(context, str, str2);
                            }
                            Thread.sleep(1000L);
                        } catch (Exception e3) {
                            str3 = str4;
                            e = e3;
                        }
                    }
                }
            }).start();
        } else if (zArr[0]) {
            checkWifi(context);
        }
    }

    public void startSoftAp(Context context, String str, String str2) throws IOException {
        String string2 = StubApp.getString2(44650);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(StubApp.getString2(HttpServletResponse.SC_REQUEST_URI_TOO_LONG), str);
        jSONObject2.put(StubApp.getString2(13687), str2);
        jSONObject.put(StubApp.getString2(300), StubApp.getString2(6774));
        jSONObject.put(StubApp.getString2(100), jSONObject2);
        try {
            OutputStream outputStream = this.mTcpSocket.getOutputStream();
            outputStream.write(jSONObject.toString().getBytes());
            outputStream.flush();
            byte[] bArr = new byte[256];
            LogUtil.w(TAG, string2.concat(new String(bArr, 0, this.mTcpSocket.getInputStream().read(bArr))));
            if (!this.mTcpSocket.isClosed()) {
                this.mTcpSocket.close();
            }
            WifiConfiguration wifiConfigurationIsExsits = isExsits(context, str);
            WifiManager wifiManager = getWifiManager(context);
            if (wifiConfigurationIsExsits != null) {
                wifiManager.enableNetwork(wifiConfigurationIsExsits.networkId, true);
            } else {
                createWifiInfo(wifiManager, str, str2, !FogUtils.isNullString(str2) ? 1 : 0);
            }
        } catch (IOException e) {
            LogUtil.d(StubApp.getString2(44651));
            e.printStackTrace();
        }
    }

    public void stopSoftAp() {
        this.mIsStopSoftAp = true;
        closeSocketListener();
    }

    public boolean isSoftApStopped() {
        return this.mIsStopSoftAp;
    }

    public void onDestory() throws IOException {
        Socket socket = this.mTcpSocket;
        if (socket != null) {
            if (!socket.isClosed()) {
                try {
                    this.mTcpSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.mTcpSocket = null;
        }
        closeSocketListener();
    }
}
