package io.fogcloud.sdk.fog.api.config_net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import com.stub.StubApp;
import io.fogcloud.sdk.deYeDeYeHttpRequestManager.R;
import io.fogcloud.sdk.easylink.api.EasyLink;
import io.fogcloud.sdk.easylink.helper.EasyLinkCallBack;
import io.fogcloud.sdk.easylink.helper.EasyLinkParams;
import io.fogcloud.sdk.fog.BaseApp;
import io.fogcloud.sdk.fog.api.base_callback.DeYeBaseCallback;
import io.fogcloud.sdk.fog.helper.MiCOConstParam;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class DeYeEasyLinkManager extends DeYeBaseCallback {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = StubApp.getString2(44633);
    private static volatile DeYeEasyLinkManager sDeYeEasyLinkManager;
    private EasyLink mEasyLink;

    private DeYeEasyLinkManager() {
    }

    public static DeYeEasyLinkManager getInstance() {
        if (sDeYeEasyLinkManager == null) {
            synchronized (DeYeEasyLinkManager.class) {
                if (sDeYeEasyLinkManager == null) {
                    sDeYeEasyLinkManager = new DeYeEasyLinkManager();
                }
            }
        }
        return sDeYeEasyLinkManager;
    }

    public String getSSID() {
        NetworkInfo activeNetworkInfo;
        if (BaseApp.getInstance() == null) {
            return null;
        }
        String string = BaseApp.getInstance().getString(R.string.unknown_ssid_tip);
        int i = Build.VERSION.SDK_INT;
        String string2 = StubApp.getString2(2294);
        if (i <= 26 || Build.VERSION.SDK_INT >= 28) {
            string = ((WifiManager) StubApp.getOrigApplicationContext(BaseApp.getInstance().getApplicationContext()).getSystemService(StubApp.getString2(9413))).getConnectionInfo().getSSID().replace(string2, "");
        } else if (Build.VERSION.SDK_INT == 27 && (activeNetworkInfo = ((ConnectivityManager) StubApp.getOrigApplicationContext(BaseApp.getInstance().getApplicationContext()).getSystemService(StubApp.getString2(790))).getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getExtraInfo() != null) {
            string = activeNetworkInfo.getExtraInfo().replace(string2, "");
        }
        return (StubApp.getString2(9502).equals(string) || StubApp.getString2(24076).equals(string)) ? BaseApp.getInstance().getString(R.string.unknown_ssid_tip) : string;
    }

    public void startEasyLink(String str, String str2, boolean z, int i, int i2, String str3, String str4, EasyLinkCallBack easyLinkCallBack) {
        if (this.mEasyLink == null) {
            this.mEasyLink = new EasyLink(BaseApp.getInstance());
        }
        if (i == 0) {
            i = 600000;
        }
        if (!checkPara(str) || i <= 0) {
            if (i <= 0) {
                failureCBEasyLink(MiCOConstParam.EMPTYCODE, MiCOConstParam.RunSecondMax, easyLinkCallBack);
                return;
            } else {
                failureCBEasyLink(MiCOConstParam.EMPTYCODE, MiCOConstParam.EMPTY, easyLinkCallBack);
                return;
            }
        }
        if (BaseApp.getInstance() != null) {
            EasyLinkParams easyLinkParams = new EasyLinkParams();
            easyLinkParams.ssid = str;
            easyLinkParams.password = str2;
            easyLinkParams.isSendIP = z;
            easyLinkParams.runSecond = i;
            easyLinkParams.sleeptime = i2;
            easyLinkParams.extraData = str3;
            easyLinkParams.rc4key = str4;
            this.mEasyLink.startEasyLink(easyLinkParams, easyLinkCallBack);
            return;
        }
        failureCBEasyLink(MiCOConstParam.EMPTYCODE, MiCOConstParam.CONTEXTISNULL, easyLinkCallBack);
    }

    public void stopEasyLink(EasyLinkCallBack easyLinkCallBack) {
        EasyLink easyLink = this.mEasyLink;
        if (easyLink == null) {
            return;
        }
        easyLink.stopEasyLink(easyLinkCallBack);
        this.mEasyLink = null;
    }
}
