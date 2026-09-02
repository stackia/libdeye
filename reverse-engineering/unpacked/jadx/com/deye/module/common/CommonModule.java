package com.deye.module.common;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import com.deye.MxchipApplication;
import com.deye.MyActivityManager;
import com.deye.MyReactActivity;
import com.deye.activity.config_net.ConfigWifiInfoAty;
import com.deye.activity.config_net.DeviceCompleteActivity;
import com.deye.activity.config_net.DeviceConfirmActivity;
import com.deye.activity.config_net.DeviceListActivity;
import com.deye.helper.DialogHelper;
import com.deye.utils.ActivityRouterUtilsKt;
import com.deye.utils.BluetoothDialogUtils;
import com.deye.utils.LanUtils;
import com.deye.utils.WechatShareHelper;
import com.deye.webview.AgentWebActivity;
import com.deye.webview.SmartServiceWebActivity;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import io.fogcloud.sdk.fog.helper.Configuration;
import java.net.URL;
import java.util.List;
import java.util.TimeZone;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class CommonModule extends ReactContextBaseJavaModule {
    private static Activity mActivity;
    private Context context;

    public CommonModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.context = reactApplicationContext;
    }

    public static void initActivity(Activity activity) {
        mActivity = activity;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return StubApp.getString2(14201);
    }

    @ReactMethod
    public void getHttpHeaders(Promise promise) {
        String string2 = StubApp.getString2(13974);
        try {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString(StubApp.getString2("672"), string2 + MxchipApplication.getInstance().getToken());
            writableMapCreateMap.putString(StubApp.getString2("300"), StubApp.getString2("701"));
            writableMapCreateMap.putString(StubApp.getString2("89"), StubApp.getString2("13136"));
            writableMapCreateMap.putString(StubApp.getString2("14199"), LanUtils.convertToLanguageTag(LanUtils.getLanguage(mActivity)));
            writableMapCreateMap.putString(StubApp.getString2("14200"), TimeZone.getDefault().getID());
            promise.resolve(writableMapCreateMap);
        } catch (Exception e) {
            promise.reject(StubApp.getString2(14198), e);
        }
    }

    @ReactMethod
    public void getBaseUrl(Promise promise) {
        try {
            promise.resolve(Configuration.getApihost());
        } catch (Exception e) {
            promise.reject(StubApp.getString2(14198), e);
        }
    }

    @ReactMethod
    public void currentIsTestEnv(Promise promise) {
        if (MxchipApplication.isReleaseEnv) {
            promise.resolve(StubApp.getString2(701));
        } else {
            promise.resolve(StubApp.getString2(2546));
        }
    }

    @ReactMethod
    public void getProductListStatus(Promise promise) {
        try {
            if (MMKV.defaultMMKV().getBoolean(StubApp.getString2("13148"), false)) {
                promise.resolve(2);
            } else {
                promise.resolve(Integer.valueOf(MxchipApplication.isReleaseEnv ? 1 : 2));
            }
        } catch (Exception e) {
            promise.reject(StubApp.getString2(14198), e);
        }
    }

    @ReactMethod
    public void canOpenMiHome(Promise promise) {
        promise.resolve(Boolean.valueOf(this.context.getPackageManager().getLaunchIntentForPackage(StubApp.getString2(14195)) != null));
    }

    @ReactMethod
    public void openShopDetail(String str) {
        try {
            String lowerCase = new URL(str).getHost().toLowerCase();
            Intent intent = new Intent(StubApp.getString2("4900"), Uri.parse(str));
            intent.addFlags(CommonNetImpl.FLAG_AUTH);
            if (lowerCase.contains(StubApp.getString2("14203"))) {
                intent.setPackage(StubApp.getString2("14204"));
                this.context.startActivity(intent);
            } else if (lowerCase.contains(StubApp.getString2("14205"))) {
                intent.setPackage(StubApp.getString2("14206"));
                this.context.startActivity(intent);
            } else if (lowerCase.contains(StubApp.getString2("14207"))) {
                intent.setPackage(StubApp.getString2("14208"));
                this.context.startActivity(intent);
            } else {
                openShopBySystem(str);
            }
        } catch (Exception unused) {
            openShopBySystem(str);
        }
    }

    public void openShopBySystem(String str) {
        AgentWebActivity.open(this.context, str, "");
    }

    @ReactMethod
    public void openMiHome() {
        Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage(StubApp.getString2(14195));
        if (launchIntentForPackage != null) {
            this.context.startActivity(launchIntentForPackage);
        }
    }

    @ReactMethod
    public void finishPage(String str) {
        if (str.equals(StubApp.getString2(14197))) {
            Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
            Intent intent = new Intent();
            intent.setFlags(268468224);
            ActivityRouterUtilsKt.routingForTabMainActivity(currentActivity, intent);
            currentActivity.finish();
            return;
        }
        Activity currentActivity2 = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity2 instanceof MyReactActivity) {
            currentActivity2.finish();
        }
    }

    @ReactMethod
    public void openScanPage() {
        FragmentActivity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity instanceof MyReactActivity) {
            DialogHelper.requestPermissionForCamera(currentActivity);
        }
    }

    @ReactMethod
    public void openManualAddPage() {
        Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity instanceof MyReactActivity) {
            currentActivity.startActivity(new Intent(currentActivity, (Class<?>) DeviceListActivity.class));
        }
    }

    @ReactMethod
    public void openConfigNetStartPage(String str) {
        Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity instanceof MyReactActivity) {
            Intent intent = new Intent(currentActivity, (Class<?>) DeviceConfirmActivity.class);
            intent.putExtra(StubApp.getString2(13306), str);
            currentActivity.startActivity(intent);
        }
    }

    @ReactMethod
    public void openConfigNetCompletePage(String str, String str2) {
        Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        Intent intent = new Intent(currentActivity, (Class<?>) DeviceCompleteActivity.class);
        intent.putExtra(StubApp.getString2(13306), str);
        intent.putExtra(StubApp.getString2(13055), str2);
        intent.putExtra(StubApp.getString2(13392), true);
        ActivityRouterUtilsKt.routingForDeviceCompleteActivity(currentActivity, intent);
    }

    @ReactMethod
    public void openConfigNetPage(String str, String str2) {
        Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity instanceof MyReactActivity) {
            Intent intent = new Intent(currentActivity, (Class<?>) ConfigWifiInfoAty.class);
            intent.putExtra(StubApp.getString2(13306), str);
            intent.putExtra(StubApp.getString2(13372), true);
            if (str2 != null && !str2.isEmpty()) {
                intent.putExtra(StubApp.getString2(14202), str2);
            }
            currentActivity.startActivity(intent);
        }
    }

    @ReactMethod
    public void openOnlineServices() {
        Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity instanceof MyReactActivity) {
            SmartServiceWebActivity.open(currentActivity);
        }
    }

    @ReactMethod
    public void openBluetoothDialog() {
        FragmentActivity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity instanceof MyReactActivity) {
            BluetoothDialogUtils.openBluetoothDialog(currentActivity);
        }
    }

    @ReactMethod
    public void openBleScanPermission(final Promise promise) {
        String[] strArr = {StubApp.getString2(8631), StubApp.getString2(8633), StubApp.getString2(13387)};
        Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity instanceof MyReactActivity) {
            if (Build.VERSION.SDK_INT >= 31) {
                XXPermissions.with(currentActivity).permission(strArr).request(new OnPermissionCallback() { // from class: com.deye.module.common.CommonModule.1
                    public void onGranted(List<String> list, boolean z) {
                        if (z) {
                            promise.resolve(1);
                        } else {
                            promise.resolve(0);
                        }
                    }

                    public void onDenied(List<String> list, boolean z) {
                        if (z) {
                            promise.resolve(-1);
                        } else {
                            promise.resolve(-2);
                        }
                    }
                });
            } else {
                promise.resolve(1);
            }
        }
    }

    @ReactMethod
    public void checkBleScanPermission(Promise promise) {
        Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity == null) {
            promise.resolve(-1);
            return;
        }
        if (Build.VERSION.SDK_INT >= 31) {
            boolean zIsGranted = XXPermissions.isGranted(currentActivity, new String[]{StubApp.getString2(8631)});
            boolean zIsGranted2 = XXPermissions.isGranted(currentActivity, new String[]{StubApp.getString2(8633)});
            boolean zIsGranted3 = XXPermissions.isGranted(currentActivity, new String[]{StubApp.getString2(13387)});
            if (zIsGranted && zIsGranted2 && zIsGranted3) {
                promise.resolve(1);
                return;
            } else if (zIsGranted || zIsGranted2) {
                promise.resolve(0);
                return;
            } else {
                promise.resolve(-1);
                return;
            }
        }
        promise.resolve(1);
    }

    @ReactMethod
    public void wechatShareAction(int i, String str, String str2, String str3, String str4) {
        WechatShareHelper.INSTANCE.getINSTANCE().share(this.context, i, str, str2, str3, str4);
    }

    public static void sendEvent(String str, WritableMap writableMap) {
        Activity activity = mActivity;
        if (activity == null || !(activity instanceof MyReactActivity)) {
            return;
        }
        try {
            ReactContext currentReactContext = ((MxchipApplication) activity.getApplication()).getReactNativeHost().getReactInstanceManager().getCurrentReactContext();
            if (currentReactContext != null) {
                currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(str, writableMap);
            }
        } catch (Exception e) {
            Log.e(StubApp.getString2(14194), StubApp.getString2(14193) + e.getMessage());
        }
    }

    @ReactMethod
    public void copyToClipboard(String str) {
        try {
            ((ClipboardManager) mActivity.getSystemService(StubApp.getString2("10428"))).setPrimaryClip(ClipData.newPlainText(StubApp.getString2("4681"), str));
        } catch (Exception e) {
            Log.e(StubApp.getString2(14194), StubApp.getString2(14196) + e.getMessage());
        }
    }
}
