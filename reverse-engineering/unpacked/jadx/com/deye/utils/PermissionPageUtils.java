package com.deye.utils;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import com.facebook.imageutils.JfifUtil;
import com.stub.StubApp;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class PermissionPageUtils {
    private Context mContext;
    private final String TAG = StubApp.getString2(14379);
    private String packageName = StubApp.getString2(14380);

    public PermissionPageUtils(Context context) {
        this.mContext = context;
    }

    public void jumpPermissionPage() throws Throwable {
        String str = Build.MANUFACTURER;
        Log.e(StubApp.getString2(14379), StubApp.getString2(14403) + str);
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1678088054:
                if (str.equals(StubApp.getString2(14408))) {
                    c = 0;
                    break;
                }
                break;
            case -1675632421:
                if (str.equals(StubApp.getString2(14407))) {
                    c = 1;
                    break;
                }
                break;
            case 2427:
                if (str.equals(StubApp.getString2(14406))) {
                    c = 2;
                    break;
                }
                break;
            case 2432928:
                if (str.equals(StubApp.getString2(JfifUtil.MARKER_EOI))) {
                    c = 3;
                    break;
                }
                break;
            case 2582855:
                if (str.equals(StubApp.getString2(14405))) {
                    c = 4;
                    break;
                }
                break;
            case 3620012:
                if (str.equals(StubApp.getString2(9308))) {
                    c = 5;
                    break;
                }
                break;
            case 74224812:
                if (str.equals(StubApp.getString2(14404))) {
                    c = 6;
                    break;
                }
                break;
            case 1864941562:
                if (str.equals(StubApp.getString2(10854))) {
                    c = 7;
                    break;
                }
                break;
            case 2141820391:
                if (str.equals(StubApp.getString2(7107))) {
                    c = '\b';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                goCoolpadMainager();
                break;
            case 1:
                goXiaoMiMainager();
                break;
            case 2:
                goLGMainager();
                break;
            case 3:
                goOppoMainager();
                break;
            case 4:
                goSonyMainager();
                break;
            case 5:
                goVivoMainager();
                break;
            case 6:
                goMeizuMainager();
                break;
            case 7:
                goSangXinMainager();
                break;
            case '\b':
                goHuaWeiMainager();
                break;
            default:
                goIntentSetting();
                break;
        }
    }

    private void goLGMainager() {
        try {
            Intent intent = new Intent(this.packageName);
            intent.setComponent(new ComponentName(StubApp.getString2("14386"), StubApp.getString2("14387")));
            this.mContext.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this.mContext, StubApp.getString2(14156), 1).show();
            e.printStackTrace();
            goIntentSetting();
        }
    }

    private void goSonyMainager() {
        try {
            Intent intent = new Intent(this.packageName);
            intent.setComponent(new ComponentName(StubApp.getString2("14390"), StubApp.getString2("14391")));
            this.mContext.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this.mContext, StubApp.getString2(14156), 1).show();
            e.printStackTrace();
            goIntentSetting();
        }
    }

    private void goHuaWeiMainager() {
        try {
            Intent intent = new Intent(this.packageName);
            intent.setFlags(CommonNetImpl.FLAG_AUTH);
            intent.setComponent(new ComponentName(StubApp.getString2("14384"), StubApp.getString2("14385")));
            this.mContext.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this.mContext, StubApp.getString2(14156), 1).show();
            e.printStackTrace();
            goIntentSetting();
        }
    }

    private static String getMiuiVersion() throws Throwable {
        Throwable th;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(StubApp.getString2("14382")).getInputStream()), 1024);
                try {
                    String line = bufferedReader.readLine();
                    bufferedReader.close();
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return line;
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    bufferedReader2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            bufferedReader = null;
        } catch (Throwable th3) {
            th = th3;
            bufferedReader2.close();
            throw th;
        }
    }

    private void goXiaoMiMainager() throws Throwable {
        String miuiVersion = getMiuiVersion();
        Log.e(StubApp.getString2(14379), StubApp.getString2(14393) + miuiVersion);
        Intent intent = new Intent();
        boolean zEquals = StubApp.getString2(14394).equals(miuiVersion);
        String string2 = StubApp.getString2(14395);
        String string22 = StubApp.getString2(14396);
        String string23 = StubApp.getString2(14397);
        if (zEquals || StubApp.getString2(14398).equals(miuiVersion)) {
            intent.setAction(string23);
            intent.setClassName(string22, StubApp.getString2(14402));
            intent.putExtra(string2, this.packageName);
        } else if (StubApp.getString2(14399).equals(miuiVersion) || StubApp.getString2(14400).equals(miuiVersion)) {
            intent.setAction(string23);
            intent.setClassName(string22, StubApp.getString2(14401));
            intent.putExtra(string2, this.packageName);
        } else {
            goIntentSetting();
        }
        this.mContext.startActivity(intent);
    }

    private void goMeizuMainager() {
        try {
            Intent intent = new Intent(StubApp.getString2("14388"));
            intent.addCategory(StubApp.getString2("1141"));
            intent.putExtra(StubApp.getString2("118"), this.packageName);
            this.mContext.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            goIntentSetting();
        }
    }

    private void goSangXinMainager() {
        goIntentSetting();
    }

    private void goIntentSetting() {
        Intent intent = new Intent(StubApp.getString2(10634));
        intent.setData(Uri.fromParts(StubApp.getString2(6854), this.mContext.getPackageName(), null));
        try {
            this.mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goOppoMainager() throws PackageManager.NameNotFoundException {
        doStartApplicationWithPackageName(StubApp.getString2(14389));
    }

    private void goCoolpadMainager() throws PackageManager.NameNotFoundException {
        doStartApplicationWithPackageName(StubApp.getString2(14383));
    }

    private void goVivoMainager() throws PackageManager.NameNotFoundException {
        doStartApplicationWithPackageName(StubApp.getString2(14392));
    }

    private Intent getAppDetailSettingIntent() {
        Intent intent = new Intent();
        intent.addFlags(CommonNetImpl.FLAG_AUTH);
        intent.setAction(StubApp.getString2(10634));
        intent.setData(Uri.fromParts(StubApp.getString2(6854), this.mContext.getPackageName(), null));
        return intent;
    }

    private void doStartApplicationWithPackageName(String str) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo;
        try {
            packageInfo = this.mContext.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo == null) {
            return;
        }
        String string2 = StubApp.getString2(7436);
        Intent intent = new Intent(string2, (Uri) null);
        String string22 = StubApp.getString2(10207);
        intent.addCategory(string22);
        intent.setPackage(packageInfo.packageName);
        List<ResolveInfo> listQueryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(intent, 0);
        String str2 = StubApp.getString2(14381) + listQueryIntentActivities.size();
        String string23 = StubApp.getString2(14379);
        Log.e(string23, str2);
        for (int i = 0; i < listQueryIntentActivities.size(); i++) {
            Log.e(string23, listQueryIntentActivities.get(i).activityInfo.packageName + listQueryIntentActivities.get(i).activityInfo.name);
        }
        ResolveInfo next = listQueryIntentActivities.iterator().next();
        if (next != null) {
            String str3 = next.activityInfo.packageName;
            String str4 = next.activityInfo.name;
            Intent intent2 = new Intent(string2);
            intent2.addCategory(string22);
            intent2.setComponent(new ComponentName(str3, str4));
            try {
                this.mContext.startActivity(intent2);
            } catch (Exception e2) {
                goIntentSetting();
                e2.printStackTrace();
            }
        }
    }

    public void getAppDetailSettingIntent(Context context) {
        Intent intent = new Intent();
        intent.addFlags(CommonNetImpl.FLAG_AUTH);
        intent.setAction(StubApp.getString2(10634));
        intent.setData(Uri.fromParts(StubApp.getString2(6854), context.getPackageName(), null));
        context.startActivity(intent);
    }
}
