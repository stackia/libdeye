package com.deye.component;

import android.content.Context;
import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.stub.StubApp;
import java.util.Map;
import kotlin.Metadata;

/* compiled from: MyMessageReceiver.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J4\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u0010H\u0016J(\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0014J(\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0016JL\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\rH\u0014J\u0018\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\rH\u0016¨\u0006\u001b"}, d2 = {"Lcom/deye/component/MyMessageReceiver;", "Lcom/alibaba/sdk/android/push/MessageReceiver;", "()V", "isForeground", "", "context", "Landroid/content/Context;", "onMessage", "", "cPushMessage", "Lcom/alibaba/sdk/android/push/notification/CPushMessage;", "onNotification", "title", "", "summary", "extraMap", "", "onNotificationClickedWithNoAction", "onNotificationOpened", "onNotificationReceivedInApp", "openType", "", "openActivity", "openUrl", "onNotificationRemoved", "messageId", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class MyMessageReceiver extends MessageReceiver {
    public static final String REC_TAG = StubApp.getString2(6710);

    static {
        StubApp.interface11(14705);
        INSTANCE = new Companion(null);
    }

    private final native boolean isForeground(Context context);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onNotification$lambda$1(String str);

    public native void onMessage(Context context, CPushMessage cPushMessage);

    public native void onNotification(Context context, String title, String summary, Map<String, String> extraMap);

    protected native void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap);

    public native void onNotificationOpened(Context context, String title, String summary, String extraMap);

    protected native void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl);

    public native void onNotificationRemoved(Context context, String messageId);
}
