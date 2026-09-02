package com.deye.component;

import android.content.Context;
import com.alibaba.sdk.android.push.AliyunMessageIntentService;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.util.Map;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class MyMessageIntentService extends AliyunMessageIntentService {
    private static final String TAG = StubApp.getString2(13917);

    protected void onNotification(Context context, String str, String str2, Map<String, String> map) {
        LogUtil.i(StubApp.getString2(13917), StubApp.getString2(13918) + str + StubApp.getString2(13919) + str2);
    }

    protected void onMessage(Context context, CPushMessage cPushMessage) {
        LogUtil.i(StubApp.getString2(13917), StubApp.getString2(13916) + cPushMessage.getTitle() + StubApp.getString2(6925) + cPushMessage.getContent());
    }

    protected void onNotificationOpened(Context context, String str, String str2, String str3) {
        StringBuilder sbAppend = new StringBuilder(StubApp.getString2(13922)).append(str);
        String string2 = StubApp.getString2(13921);
        LogUtil.i(StubApp.getString2(13917), sbAppend.append(string2).append(str2).append(string2).append(str3).toString());
    }

    protected void onNotificationClickedWithNoAction(Context context, String str, String str2, String str3) {
        StringBuilder sbAppend = new StringBuilder(StubApp.getString2(13920)).append(str);
        String string2 = StubApp.getString2(13921);
        LogUtil.i(StubApp.getString2(13917), sbAppend.append(string2).append(str2).append(string2).append(str3).toString());
    }

    protected void onNotificationRemoved(Context context, String str) {
        LogUtil.i(StubApp.getString2(13917), StubApp.getString2(13924) + str);
    }

    protected void onNotificationReceivedInApp(Context context, String str, String str2, Map<String, String> map, int i, String str3, String str4) {
        StringBuilder sbAppend = new StringBuilder(StubApp.getString2(13923)).append(str);
        String string2 = StubApp.getString2(13921);
        LogUtil.i(StubApp.getString2(13917), sbAppend.append(string2).append(str2).append(StubApp.getString2(1026)).append(map).append(string2).append(i).append(string2).append(str3).append(string2).append(str4).toString());
    }
}
