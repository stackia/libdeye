package com.deye.helper;

import android.content.Context;
import android.content.Intent;
import com.deye.MyReactActivity;
import com.deye.ProductManager;
import com.deye.activity.config_net.ConfigNetAty;
import com.deye.activity.config_net.DeviceCompleteActivity;
import com.deye.helper.DialogHelper;
import com.deye.utils.ActivityRouterUtilsKt;
import com.deye.utils.PagerUtils;
import com.stub.StubApp;
import io.fogcloud.sdk.easylink.helper.EasyLinkCallBack;
import io.fogcloud.sdk.fog.api.config_net.DeYeEasyLinkManager;
import io.fogcloud.sdk.fog.api.config_net.config_network.SoftAP_NetWorkConfigManager;
import io.fogcloud.sdk.fog.log.LogUtil;
import it.innove.Peripheral;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ConfigNetAtyHelper {
    private ConfigNetAty mConfigNetAty;

    public ConfigNetAtyHelper(ConfigNetAty configNetAty) {
        this.mConfigNetAty = configNetAty;
    }

    public void goBackMain() {
        this.mConfigNetAty.toTabMainPage();
        this.mConfigNetAty.finish();
    }

    public void goSelectDevicePage() {
        Intent intent = new Intent((Context) this.mConfigNetAty, (Class<?>) MyReactActivity.class);
        intent.putExtra(StubApp.getString2(13144), StubApp.getString2(14131));
        this.mConfigNetAty.startActivity(intent);
    }

    public void stopEasylink() {
        DeYeEasyLinkManager.getInstance().stopEasyLink(new EasyLinkCallBack() { // from class: com.deye.helper.ConfigNetAtyHelper.1
            @Override // io.fogcloud.sdk.easylink.helper.EasyLinkCallBack
            public void onSuccess(int i, String str) {
                LogUtil.d(StubApp.getString2(Peripheral.GATT_AUTH_FAIL), str);
            }

            @Override // io.fogcloud.sdk.easylink.helper.EasyLinkCallBack
            public void onFailure(int i, String str) {
                LogUtil.d(StubApp.getString2(Peripheral.GATT_AUTH_FAIL), str);
            }
        });
    }

    public void stopSearch() {
        if (this.mConfigNetAty.mUdpManager != null) {
            this.mConfigNetAty.mUdpManager.stopSearchDevices();
        }
    }

    public void stopConfigNet() {
        LogUtil.d(StubApp.getString2(14133));
        SoftAP_NetWorkConfigManager.getInstance().stopSoftAp();
        stopEasylink();
        stopSearch();
    }

    public void showTipDialog(String str, String str2, String str3, boolean z, final boolean z2) {
        DialogHelper.twoBtnDialog(this.mConfigNetAty, z, str2, str3, str, new DialogHelper.OnDialogListener() { // from class: com.deye.helper.ConfigNetAtyHelper.2
            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onCancel() {
                if (z2) {
                    ConfigNetAtyHelper.this.goSelectDevicePage();
                } else if (ConfigNetAtyHelper.this.mConfigNetAty.mIsAlreadyGetSoftApCallback) {
                    ConfigNetAtyHelper.this.mConfigNetAty.sendBroadcast(new Intent().setAction(StubApp.getString2(13293)).putExtra(StubApp.getString2(13294), true));
                }
                ConfigNetAtyHelper.this.mConfigNetAty.finish();
            }

            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onSure(String str4) {
                ConfigNetAtyHelper.this.goBackMain();
            }
        });
    }

    public void startConfigNet() {
        LogUtil.d(StubApp.getString2(14132) + this.mConfigNetAty.mConfigNetType);
        if (StubApp.getString2(701).equals(this.mConfigNetAty.mConfigNetType)) {
            this.mConfigNetAty.toStartEasyLink();
            this.mConfigNetAty.toStartSearchDevicesUDP();
            return;
        }
        if (StubApp.getString2(2546).equals(this.mConfigNetAty.mConfigNetType)) {
            this.mConfigNetAty.startSoftAp();
            this.mConfigNetAty.toStartSearchDevicesUDP();
        }
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [android.content.Context, com.deye.activity.config_net.ConfigNetAty] */
    public void toCompletePage(boolean z) {
        if (z) {
            String productIcon = ProductManager.INSTANCE.getProductIcon(this.mConfigNetAty.mProductId);
            ?? r0 = this.mConfigNetAty;
            PagerUtils.goConfigSuccess(r0, r0.mDeviceId, this.mConfigNetAty.mProductId, productIcon);
            return;
        }
        Intent intent = new Intent((Context) this.mConfigNetAty, (Class<?>) DeviceCompleteActivity.class);
        intent.putExtra(StubApp.getString2(13306), this.mConfigNetAty.mProductId);
        intent.putExtra(StubApp.getString2(13055), this.mConfigNetAty.mDeviceId);
        intent.putExtra(StubApp.getString2(13305), this.mConfigNetAty.mConfigNetType);
        intent.putExtra(StubApp.getString2(13392), false);
        ActivityRouterUtilsKt.routingForDeviceCompleteActivity(this.mConfigNetAty, intent);
        this.mConfigNetAty.finish();
    }
}
