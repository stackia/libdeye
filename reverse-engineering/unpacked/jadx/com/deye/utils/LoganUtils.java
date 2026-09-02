package com.deye.utils;

import com.deye.activity.app_config.ISendLogCallBack;
import com.deye.activity.app_config.RealSendLogRunnable;
import com.dianping.logan.Logan;
import com.stub.StubApp;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class LoganUtils {
    public static void uploadLog(ISendLogCallBack iSendLogCallBack) {
        Logan.f();
        Logan.s(new String[]{new SimpleDateFormat(StubApp.getString2(13775)).format(new Date(System.currentTimeMillis()))}, new RealSendLogRunnable(iSendLogCallBack));
    }
}
