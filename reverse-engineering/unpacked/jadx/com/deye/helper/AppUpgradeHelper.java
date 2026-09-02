package com.deye.helper;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import androidx.multidex.MultiDexApplication;
import com.alibaba.fastjson.JSON;
import com.deye.MxchipApplication;
import com.deye.configs.Constants;
import com.deye.utils.BaseUtils;
import com.deye.utils.RegularUtils;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* compiled from: AppUpgradeHelper.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001cB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0017J\u000e\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/deye/helper/AppUpgradeHelper;", "", "()V", "APP_PACKAGE", "", "APP_STORE_URL", "URL", "forceUpdate", "", "mNewAppDownloadUrl", "mNewAppNote", "mNewAppNoteDefault", "mTipArray", "", "[Ljava/lang/String;", "showOnLaunch", "version", "checkAppUpgrade", "", "checkAppUpgradeListener", "Lcom/deye/helper/AppUpgradeHelper$ICheckAppUpgradeListener;", "downLoadApk", "context", "Landroid/content/Context;", "getLocalVersion", "", "ctx", "goAppStore", "ICheckAppUpgradeListener", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class AppUpgradeHelper {
    private static boolean forceUpdate;
    private static String[] mTipArray;
    private static boolean showOnLaunch;
    public static final String APP_PACKAGE = StubApp.getString2(14128);
    public static final String APP_STORE_URL = StubApp.getString2(14129);
    public static final String URL = StubApp.getString2(14130);
    public static final AppUpgradeHelper INSTANCE = new AppUpgradeHelper();
    private static String version = "";
    private static String mNewAppDownloadUrl = "";
    private static String mNewAppNote = "";
    private static final String mNewAppNoteDefault = StubApp.getString2(14127);

    /* compiled from: AppUpgradeHelper.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J=\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH&¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/deye/helper/AppUpgradeHelper$ICheckAppUpgradeListener;", "", "onFailure", "", "onSuccess", "version", "", "isNeedUpgrade", "", "tipArr", "", "showOnLaunch", "forceUpdate", "(Ljava/lang/String;Z[Ljava/lang/String;ZZ)V", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface ICheckAppUpgradeListener {
        void onFailure();

        void onSuccess(String version, boolean isNeedUpgrade, String[] tipArr, boolean showOnLaunch, boolean forceUpdate);
    }

    private AppUpgradeHelper() {
    }

    public final int getLocalVersion(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        try {
            return StubApp.getOrigApplicationContext(ctx.getApplicationContext()).getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public final void goAppStore(Context context) {
        String string2;
        String str;
        String string22 = StubApp.getString2(4900);
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            context.startActivity(new Intent(string22, Uri.parse(StubApp.getString2("14129"))));
        } catch (Exception e) {
            e.printStackTrace();
            if (!Intrinsics.areEqual("", mNewAppDownloadUrl) && (str = mNewAppDownloadUrl) != null && RegularUtils.isUrl(str)) {
                string2 = mNewAppDownloadUrl;
            } else {
                string2 = StubApp.getString2(14130);
            }
            Intent intent = new Intent(string22);
            intent.setData(Uri.parse(string2));
            context.startActivity(intent);
        }
    }

    public final void downLoadApk(Context context) {
        String string2;
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        if (!Intrinsics.areEqual("", mNewAppDownloadUrl) && (str = mNewAppDownloadUrl) != null && RegularUtils.isUrl(str)) {
            string2 = mNewAppDownloadUrl;
        } else {
            string2 = StubApp.getString2(14130);
        }
        Intent intent = new Intent(StubApp.getString2(4900));
        intent.setData(Uri.parse(string2));
        context.startActivity(intent);
    }

    public final void checkAppUpgrade(final ICheckAppUpgradeListener checkAppUpgradeListener) {
        DeYeHttpRequestManager.getInstance().updateVersion(Constants.APPID, new FogCallBack() { // from class: com.deye.helper.AppUpgradeHelper.checkAppUpgrade.1
            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onSuccess(String message) throws NumberFormatException {
                List listEmptyList;
                String string2 = StubApp.getString2(14121);
                String string22 = StubApp.getString2(13221);
                Intrinsics.checkNotNullParameter(message, "message");
                Log.d(StubApp.getString2(14122), message);
                if (JSON.parseObject(JSON.parseObject(message).getString(StubApp.getString2(13082))).getIntValue(StubApp.getString2(109)) == 0) {
                    try {
                        String string = JSON.parseObject(message).getString(StubApp.getString2("100"));
                        AppUpgradeHelper appUpgradeHelper = AppUpgradeHelper.INSTANCE;
                        String string3 = JSON.parseObject(string).getString(StubApp.getString2("159"));
                        Intrinsics.checkNotNullExpressionValue(string3, string22);
                        AppUpgradeHelper.version = string3;
                        AppUpgradeHelper appUpgradeHelper2 = AppUpgradeHelper.INSTANCE;
                        AppUpgradeHelper.mNewAppDownloadUrl = JSON.parseObject(string).getString(StubApp.getString2("14123"));
                        AppUpgradeHelper appUpgradeHelper3 = AppUpgradeHelper.INSTANCE;
                        String string4 = JSON.parseObject(string).getString(StubApp.getString2("14124"));
                        Intrinsics.checkNotNullExpressionValue(string4, string22);
                        AppUpgradeHelper.mNewAppNote = string4;
                        AppUpgradeHelper appUpgradeHelper4 = AppUpgradeHelper.INSTANCE;
                        Boolean bool = JSON.parseObject(string).getBoolean(StubApp.getString2("14125"));
                        Intrinsics.checkNotNullExpressionValue(bool, string2);
                        AppUpgradeHelper.showOnLaunch = bool.booleanValue();
                        AppUpgradeHelper appUpgradeHelper5 = AppUpgradeHelper.INSTANCE;
                        Boolean bool2 = JSON.parseObject(string).getBoolean(StubApp.getString2("14126"));
                        Intrinsics.checkNotNullExpressionValue(bool2, string2);
                        AppUpgradeHelper.forceUpdate = bool2.booleanValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (!Intrinsics.areEqual(AppUpgradeHelper.mNewAppNoteDefault, AppUpgradeHelper.mNewAppNote)) {
                        AppUpgradeHelper appUpgradeHelper6 = AppUpgradeHelper.INSTANCE;
                        List listSplit = new Regex(StubApp.getString2(1033)).split(AppUpgradeHelper.mNewAppNote, 0);
                        if (!listSplit.isEmpty()) {
                            ListIterator listIterator = listSplit.listIterator(listSplit.size());
                            while (listIterator.hasPrevious()) {
                                if (((String) listIterator.previous()).length() != 0) {
                                    listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                                    break;
                                }
                            }
                            listEmptyList = CollectionsKt.emptyList();
                            AppUpgradeHelper.mTipArray = (String[]) listEmptyList.toArray(new String[0]);
                        } else {
                            listEmptyList = CollectionsKt.emptyList();
                            AppUpgradeHelper.mTipArray = (String[]) listEmptyList.toArray(new String[0]);
                        }
                    }
                    if (!BaseUtils.isNullString(AppUpgradeHelper.version)) {
                        int i = Integer.parseInt(AppUpgradeHelper.version);
                        AppUpgradeHelper appUpgradeHelper7 = AppUpgradeHelper.INSTANCE;
                        MultiDexApplication mxchipApplication = MxchipApplication.getInstance();
                        Intrinsics.checkNotNullExpressionValue(mxchipApplication, "getInstance(...)");
                        if (i > appUpgradeHelper7.getLocalVersion((Context) mxchipApplication)) {
                            ICheckAppUpgradeListener iCheckAppUpgradeListener = checkAppUpgradeListener;
                            if (iCheckAppUpgradeListener != null) {
                                iCheckAppUpgradeListener.onSuccess(AppUpgradeHelper.version, true, AppUpgradeHelper.mTipArray, AppUpgradeHelper.showOnLaunch, AppUpgradeHelper.forceUpdate);
                                return;
                            }
                            return;
                        }
                        ICheckAppUpgradeListener iCheckAppUpgradeListener2 = checkAppUpgradeListener;
                        if (iCheckAppUpgradeListener2 != null) {
                            iCheckAppUpgradeListener2.onSuccess(AppUpgradeHelper.version, false, AppUpgradeHelper.mTipArray, AppUpgradeHelper.showOnLaunch, AppUpgradeHelper.forceUpdate);
                            return;
                        }
                        return;
                    }
                    ICheckAppUpgradeListener iCheckAppUpgradeListener3 = checkAppUpgradeListener;
                    if (iCheckAppUpgradeListener3 != null) {
                        iCheckAppUpgradeListener3.onSuccess(AppUpgradeHelper.version, false, null, AppUpgradeHelper.showOnLaunch, AppUpgradeHelper.forceUpdate);
                        return;
                    }
                    return;
                }
                ICheckAppUpgradeListener iCheckAppUpgradeListener4 = checkAppUpgradeListener;
                if (iCheckAppUpgradeListener4 != null) {
                    iCheckAppUpgradeListener4.onFailure();
                }
            }

            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onFailure(int code, String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                ICheckAppUpgradeListener iCheckAppUpgradeListener = checkAppUpgradeListener;
                if (iCheckAppUpgradeListener != null) {
                    iCheckAppUpgradeListener.onFailure();
                }
            }
        });
    }
}
