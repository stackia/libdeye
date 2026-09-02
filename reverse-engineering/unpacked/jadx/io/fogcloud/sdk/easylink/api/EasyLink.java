package io.fogcloud.sdk.easylink.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.stub.StubApp;
import io.fogcloud.sdk.easylink.helper.ComHelper;
import io.fogcloud.sdk.easylink.helper.EasyLinkCallBack;
import io.fogcloud.sdk.easylink.helper.EasyLinkErrCode;
import io.fogcloud.sdk.easylink.helper.EasyLinkParams;
import io.fogcloud.sdk.easylink.jetty.EasyServer;
import java.net.NetworkInterface;
import java.net.SocketException;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class EasyLink {
    private static EasyServer mEasyServer = null;
    public static final int mPort = 8000;
    private Context mContext;
    private EasyLink_plus mEasylinkPlus;
    private WifiInfo mWifiInfo;
    private WifiManager mWifiManager;
    private boolean eltag = false;
    private Thread workThread = null;
    private ComHelper comfunc = new ComHelper();

    public EasyLink(Context context) {
        this.mContext = context;
    }

    public String getSSID() {
        Context context = this.mContext;
        if (context == null) {
            return null;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService(StubApp.getString2(9413));
        this.mWifiManager = wifiManager;
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        this.mWifiInfo = connectionInfo;
        return connectionInfo.getSSID().replaceAll(StubApp.getString2(2294), "");
    }

    public boolean isAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getSystemService(StubApp.getString2(790));
        if (connectivityManager.getActiveNetworkInfo() != null) {
            return connectivityManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }

    public boolean isWifiEnabled() {
        Context context = this.mContext;
        return context != null && 3 == ((WifiManager) context.getSystemService(StubApp.getString2(9413))).getWifiState();
    }

    public boolean is3rd() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService(StubApp.getString2(790))).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
    }

    public void startEasyLink(EasyLinkParams easyLinkParams, EasyLinkCallBack easyLinkCallBack) {
        if (ComHelper.checkPara(easyLinkParams.ssid)) {
            if (this.mContext != null) {
                startEasyLink(easyLinkParams.ssid, easyLinkParams.password, easyLinkParams.isSendIP, easyLinkParams.runSecond, easyLinkParams.sleeptime, easyLinkParams.extraData, easyLinkParams.rc4key, easyLinkParams.isSmallMTU, easyLinkCallBack);
                return;
            } else {
                this.comfunc.failureCBEasyLink(EasyLinkErrCode.CONTEXT_CODE, EasyLinkErrCode.CONTEXT, easyLinkCallBack);
                return;
            }
        }
        this.comfunc.failureCBEasyLink(EasyLinkErrCode.INVALID_CODE, EasyLinkErrCode.INVALID, easyLinkCallBack);
    }

    private void startEasyLink(String str, String str2, boolean z, final int i, int i2, String str3, String str4, boolean z2, final EasyLinkCallBack easyLinkCallBack) {
        if (!this.eltag) {
            if (this.workThread == null) {
                Thread thread = new Thread(new Runnable() { // from class: io.fogcloud.sdk.easylink.api.EasyLink.1
                    @Override // java.lang.Runnable
                    public void run() throws InterruptedException {
                        try {
                            Thread.sleep(i);
                            if (EasyLink.this.eltag) {
                                EasyLink.this.stopEasyLink(easyLinkCallBack);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                this.workThread = thread;
                thread.start();
            }
            try {
                startEasylink(str, str2, z, i2, str3, str4, z2, easyLinkCallBack);
                this.eltag = true;
                if (z) {
                    try {
                        EasyServer easyServer = new EasyServer(8000);
                        mEasyServer = easyServer;
                        easyServer.start(easyLinkCallBack);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                this.comfunc.successCBEasyLink(EasyLinkErrCode.START_CODE, EasyLinkErrCode.SUCCESS, easyLinkCallBack);
                return;
            } catch (Exception e2) {
                this.comfunc.failureCBEasyLink(EasyLinkErrCode.EXCEPTION_CODE, e2.getMessage(), easyLinkCallBack);
                return;
            }
        }
        this.comfunc.failureCBEasyLink(EasyLinkErrCode.BUSY_CODE, EasyLinkErrCode.BUSY, easyLinkCallBack);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [io.fogcloud.sdk.easylink.api.EasyLink$2] */
    public void stopEasyLink(final EasyLinkCallBack easyLinkCallBack) {
        new Thread() { // from class: io.fogcloud.sdk.easylink.api.EasyLink.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                if (EasyLink.this.mEasylinkPlus == null || !EasyLink.this.eltag) {
                    EasyLink.this.comfunc.failureCBEasyLink(EasyLinkErrCode.CLOSED_CODE, EasyLinkErrCode.CLOSED, easyLinkCallBack);
                    return;
                }
                if (EasyLink.this.workThread != null) {
                    EasyLink.this.workThread = null;
                }
                EasyLink.this.mEasylinkPlus.stopTransmitting();
                if (EasyLink.mEasyServer != null && EasyLink.mEasyServer.isStarted()) {
                    EasyLink.mEasyServer.stop();
                }
                EasyLink.this.eltag = false;
                EasyLink.this.comfunc.successCBEasyLink(EasyLinkErrCode.STOP_CODE, EasyLinkErrCode.SUCCESS, easyLinkCallBack);
            }
        }.start();
    }

    protected void startEasylink(String str, String str2, boolean z, int i, String str3, String str4, boolean z2, EasyLinkCallBack easyLinkCallBack) throws NumberFormatException {
        int normalIP;
        if (z) {
            normalIP = getNormalIP(this.mContext);
        } else if (ComHelper.isInteger(str3)) {
            normalIP = Integer.parseInt(str3);
            str3 = "";
        } else {
            normalIP = 1000;
        }
        int i2 = normalIP;
        String str5 = str3;
        this.mEasylinkPlus = EasyLink_plus.getInstence(this.mContext);
        try {
            if (NetworkInterface.getByName(StubApp.getString2("44618")).getMTU() < 1500 || z2) {
                this.mEasylinkPlus.setSmallMtu(true);
            }
        } catch (SocketException e) {
            e.printStackTrace();
            this.comfunc.failureCBEasyLink(EasyLinkErrCode.EXCEPTION_CODE, e.getMessage(), easyLinkCallBack);
        }
        try {
            this.mEasylinkPlus.transmitSettings(str, str2, i2, i, str5, str4);
        } catch (Exception e2) {
            e2.printStackTrace();
            this.comfunc.failureCBEasyLink(EasyLinkErrCode.EXCEPTION_CODE, e2.getMessage(), easyLinkCallBack);
        }
    }

    private int getNormalIP(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(StubApp.getString2(9413));
        this.mWifiManager = wifiManager;
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        this.mWifiInfo = connectionInfo;
        return connectionInfo.getIpAddress();
    }
}
