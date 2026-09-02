package com.deye.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import com.stub.StubApp;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class JumpWeChatUtils {
    public static int DRAWABLE = 1;
    public static int TEXT;
    public static String WECHAT_LAUNCHER_UI_NAME = StubApp.getString2(14318);
    public static String WECHAT_PACKAGE_NAME = StubApp.getString2(5462);
    private Context context;

    public JumpWeChatUtils(Context context) {
        this.context = context;
    }

    public static boolean isAvilible(Context context, String str) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < installedPackages.size(); i++) {
            if (installedPackages.get(i).packageName.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public static void openWeChatApp(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(WECHAT_PACKAGE_NAME);
        if (launchIntentForPackage == null) {
            BaseUtils.showShortToast(context, StubApp.getString2(14319));
        } else {
            context.startActivity(launchIntentForPackage);
        }
    }
}
