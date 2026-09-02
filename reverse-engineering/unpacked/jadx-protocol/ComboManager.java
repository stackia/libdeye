package com.deye.combo;

import android.os.Handler;
import com.alibaba.fastjson.JSON;
import com.deye.MxchipApplication;
import com.deye.combo.bluetooth.BluetoothSM;
import com.deye.combo.callback.FrameResponseCallback;
import com.deye.combo.callback.ScanResultCallback;
import com.deye.combo.log.BleLog;
import com.deye.entity.BindCheckBean;
import com.deye.utils.BaseUtils;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ComboManager {
    ScheduledExecutorService mQueryBindIdService = Executors.newSingleThreadScheduledExecutor();

    public ComboManager(ScanResultCallback scanResultCallback) {
        BluetoothSM.getInstance().registerScanResultCallback(scanResultCallback);
    }

    public void checkOnlineStatus(final String str, final Handler handler) {
        final FogCallBack fogCallBack = new FogCallBack() { // from class: com.deye.combo.ComboManager.1
            public void onSuccess(String str2) {
                BleLog.i(StubApp.getString2(13865), StubApp.getString2(13866) + str2);
                BindCheckBean bindCheckBean = (BindCheckBean) JSON.parseObject(str2, BindCheckBean.class);
                if (bindCheckBean.getMeta().getCode() == 0) {
                    ComboManager.this.mQueryBindIdService.shutdown();
                    BaseUtils.sendMessageExtraArg(handler, 100, bindCheckBean.getData().getDevice_id(), 2);
                }
            }

            public void onFailure(int i, String str2) {
                BleLog.i(StubApp.getString2(13865), StubApp.getString2(13864) + i + StubApp.getString2(626) + str2);
            }
        };
        this.mQueryBindIdService.scheduleWithFixedDelay(new Runnable() { // from class: com.deye.combo.ComboManager.2
            @Override // java.lang.Runnable
            public void run() {
                DeYeHttpRequestManager.getInstance().bindCheck(str, fogCallBack);
            }
        }, 3L, 3L, TimeUnit.SECONDS);
    }

    public void startScan(String str, String str2, String str3, FrameResponseCallback frameResponseCallback) {
        BluetoothSM.getInstance().startScan(str2, str3, str, frameResponseCallback, MxchipApplication.getInstance());
    }

    public void release() {
        BluetoothSM.getInstance().release();
        this.mQueryBindIdService.shutdown();
        this.mQueryBindIdService = null;
    }
}
