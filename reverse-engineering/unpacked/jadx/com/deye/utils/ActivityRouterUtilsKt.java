package com.deye.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.deye.MxchipApplication;
import com.deye.TabMainActivity;
import com.deye.activity.config_net.ConfigWifiInfoAty;
import com.deye.activity.config_net.DeviceCompleteActivity;
import com.deye.activity.config_net.DeviceConfirmActivity;
import com.deye.activity.config_net.DeviceListActivity;
import com.deye.activity.login.HomePageActivity;
import com.deye.activity.login.RegisterActivity;
import com.deye.activity.mine.UserInfoActivity;
import com.stub.StubApp;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import com.ut.device.AidConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ActivityRouterUtils.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\u0016\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0016\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0016\u0010\b\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0016\u0010\t\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0016\u0010\n\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0016\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0016\u0010\f\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0016\u0010\r\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0016\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0016\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0016\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0016\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011¨\u0006\u0014"}, d2 = {"goAppDetailSetting", "", "routingForConfigWifiActivity", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "routingForDeviceCompleteActivity", "routingForDeviceConfirmActivity", "routingForDeviceListActivity", "routingForHomePageActivity", "routingForLoginActivity", "routingForRegisterActivity", "routingForTabMainActivity", "routingForUserInfoActivity", "toBrowserApp", "url", "", "toJingDong", "toTaoBao", "app_homeRelease"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class ActivityRouterUtilsKt {
    public static final void routingForConfigWifiActivity(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        intent.setClass(context, ConfigWifiInfoAty.class);
        context.startActivity(intent);
    }

    public static final void routingForDeviceConfirmActivity(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        intent.setClass(context, DeviceConfirmActivity.class);
        context.startActivity(intent);
    }

    public static final void routingForDeviceCompleteActivity(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        intent.setClass(context, DeviceCompleteActivity.class);
        context.startActivity(intent);
    }

    public static final void routingForDeviceListActivity(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        intent.setClass(context, DeviceListActivity.class);
        context.startActivity(intent);
    }

    public static final void routingForHomePageActivity(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        intent.setClass(context, HomePageActivity.class);
        context.startActivity(intent);
    }

    public static final void routingForTabMainActivity(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        intent.setClass(context, TabMainActivity.class);
        context.startActivity(intent);
    }

    public static final void routingForLoginActivity(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        intent.setClass(context, HomePageActivity.class);
        context.startActivity(intent);
    }

    public static final void routingForRegisterActivity(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        intent.setClass(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    public static final void routingForUserInfoActivity(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        intent.setClass(context, UserInfoActivity.class);
        context.startActivity(intent);
    }

    public static final void toJingDong(Context context, String url) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        Intent intent = new Intent();
        intent.setAction(StubApp.getString2(4900));
        String strSubstring = url.substring(0, StringsKt.indexOf$default(url, StubApp.getString2(5516), 0, false, 6, (Object) null));
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        String strSubstring2 = strSubstring.substring(StringsKt.lastIndexOf$default(strSubstring, StubApp.getString2(AidConstants.EVENT_REQUEST_FAILED), 0, false, 6, (Object) null) + 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        intent.setData(Uri.parse(StubApp.getString2(14274) + strSubstring2 + StubApp.getString2(14275)));
        intent.setFlags(CommonNetImpl.FLAG_AUTH);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            toBrowserApp(context, url);
        }
    }

    public static final void toBrowserApp(Context context, String url) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        context.startActivity(new Intent(StubApp.getString2(4900), Uri.parse(url)));
    }

    public static final void toTaoBao(Context context, String url) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        Intent intent = new Intent();
        intent.setAction(StubApp.getString2(4900));
        intent.setData(Uri.parse(url));
        intent.setClassName(StubApp.getString2(14204), StubApp.getString2(14276));
        intent.setFlags(CommonNetImpl.FLAG_AUTH);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            toBrowserApp(context, url);
        }
    }

    public static final void goAppDetailSetting() {
        Intent intent = new Intent();
        intent.addFlags(CommonNetImpl.FLAG_AUTH);
        intent.setAction(StubApp.getString2(10634));
        intent.setData(Uri.fromParts(StubApp.getString2(6854), MxchipApplication.getInstance().getPackageName(), null));
        try {
            MxchipApplication.getInstance().startActivity(intent);
        } catch (Exception unused) {
        }
    }
}
