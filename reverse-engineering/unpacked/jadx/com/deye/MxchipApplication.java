package com.deye;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Process;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.ha.adapter.AliHaAdapter;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.huawei.HuaWeiRegister;
import com.alibaba.sdk.android.push.impl.HuaweiMsgParseImpl;
import com.alibaba.sdk.android.push.impl.OppoMsgParseImpl;
import com.alibaba.sdk.android.push.impl.VivoMsgParseImpl;
import com.alibaba.sdk.android.push.impl.XiaoMiMsgParseImpl;
import com.alibaba.sdk.android.push.noonesdk.PushInitConfig;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.alibaba.sdk.android.push.register.GcmRegister;
import com.alibaba.sdk.android.push.register.MiPushRegister;
import com.alibaba.sdk.android.push.register.OppoRegister;
import com.alibaba.sdk.android.push.register.ThirdPushManager;
import com.alibaba.sdk.android.push.register.VivoRegister;
import com.deye.activity.SplashActivity;
import com.deye.activity.device.base.PublicConstantAty;
import com.deye.activity.device.humidifier.H7Aty;
import com.deye.activity.login.HomePageActivity;
import com.deye.combo.bluetooth.BluetoothSM;
import com.deye.configs.Constants;
import com.deye.module.common.CommonReactPackage;
import com.deye.receiver.PopupPushActivity;
import com.deye.thread_pool.DeviceHeartbeatManager;
import com.deye.utils.ChannelUtil;
import com.deye.utils.FontsOverrideUtils;
import com.deye.utils.LanUtils;
import com.deye.utils.MMKVUtils;
import com.deye.utils.UmUtils;
import com.dianping.logan.Logan;
import com.dianping.logan.LoganConfig;
import com.facebook.imageutils.JfifUtil;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactHost;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.defaults.DefaultReactHost;
import com.facebook.react.defaults.DefaultReactNativeHost;
import com.facebook.soloader.SoLoader;
import com.hjq.toast.Toaster;
import com.hjq.toast.style.BlackToastStyle;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import io.fogcloud.sdk.fog.BaseApp;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.api.mqtt.UnifiedMqttService;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import io.fogcloud.sdk.fog.helper.Configuration;
import io.fogcloud.sdk.fog.log.LogDebug;
import io.fogcloud.sdk.fog.log.LogUtil;
import io.fogcloud.sdk.fog.log.MxLogcatHelper;
import io.fogcloud.sdk.fog.utils.SharedPrefsUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class MxchipApplication extends BaseApp implements ReactApplication {
    private static final boolean IS_DEBUG = false;
    private static final String TAG = StubApp.getString2(13080);
    private static MxchipApplication instance = null;
    private static boolean isColdBootApp = false;
    public static boolean isReleaseEnv = true;
    public static int sResumeActivityCount;
    private boolean isForeground;
    private long mCurrentShowOffLineDialogTime;
    private final long time = new Date().getTime();
    private final List<Activity> activities = new ArrayList();

    public void addActivity(Activity activity) {
    }

    public void setCurrentShowOffLineDialogTime(long j) {
        this.mCurrentShowOffLineDialogTime = j;
    }

    public long getCurrentShowOffLineDialogTime() {
        return this.mCurrentShowOffLineDialogTime;
    }

    public static boolean isAppInForeground() {
        MxchipApplication mxchipApplication = getInstance();
        return mxchipApplication != null && mxchipApplication.isForeground;
    }

    private void initPhotoError() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.fogcloud.sdk.fog.BaseApp
    public void onCreate() throws IllegalAccessException, NoSuchFieldException, RuntimeException {
        super.onCreate();
        SoLoader.init(this, false);
        DeYeHttpRequestManager.getInstance().appVersion = StubApp.getString2(13136);
        if (ChannelUtil.isOversea()) {
            Constants.APPID = StubApp.getString2(13137);
        }
        PushServiceFactory.init(this);
        AliHaAdapter.getInstance().preStart(this);
        Toaster.init(this);
        Toaster.setStyle(new BlackToastStyle());
        Toaster.setGravity(17);
        MMKV.initialize((Context) this);
        readAppConfigInfo();
        if (MMKVUtils.INSTANCE.checkAgreePolicy()) {
            initPushService();
        }
        isColdBootApp = true;
        String processName = getProcessName(this);
        LogUtil.d(StubApp.getString2(13138) + processName);
        if (processName != null) {
            if (TextUtils.equals(processName, getPackageName())) {
                LogUtil.d(StubApp.getString2(13139));
                initMyPid();
            } else {
                TextUtils.equals(processName, StubApp.getString2(13140));
            }
        }
        checkDebuggableInNotDebugModel(this);
    }

    public static void exitProcess(Context context) {
        Intent intent = new Intent(StubApp.getString2(7436));
        intent.addCategory(StubApp.getString2(10273));
        intent.setFlags(CommonNetImpl.FLAG_AUTH);
        context.startActivity(intent);
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void checkApkIntegrity() throws NoSuchAlgorithmException, IOException {
        String packageCodePath = getPackageCodePath();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(StubApp.getString2("6317"));
            byte[] bArr = new byte[1024];
            FileInputStream fileInputStream = new FileInputStream(new File(packageCodePath));
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i > 0) {
                    messageDigest.update(bArr, 0, i);
                } else {
                    String string = new BigInteger(1, messageDigest.digest()).toString(16);
                    Log.d(StubApp.getString2("13080"), StubApp.getString2("13115") + string.substring(0, 8));
                    fileInputStream.close();
                    DeYeHttpRequestManager.getInstance().checkApkIntegrity(packageCode(this), string, new FogCallBack() { // from class: com.deye.MxchipApplication.1
                        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                        public void onSuccess(String str) {
                            String str2 = StubApp.getString2(13081) + str;
                            String string2 = StubApp.getString2(13080);
                            Log.d(string2, str2);
                            JSONObject object = JSON.parseObject(str);
                            if (JSON.parseObject(object.getString(StubApp.getString2(13082))).getIntValue(StubApp.getString2(109)) == 0) {
                                boolean booleanValue = object.getBooleanValue(StubApp.getString2(100));
                                LogUtil.d(string2, StubApp.getString2(13083) + booleanValue);
                                if (booleanValue) {
                                    return;
                                }
                                MxchipApplication.exitProcess(MxchipApplication.this);
                                return;
                            }
                            LogUtil.d(string2, StubApp.getString2(13084));
                        }

                        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                        public void onFailure(int i2, String str) {
                            LogUtil.d(StubApp.getString2(13080), StubApp.getString2(13079) + str);
                        }
                    });
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int packageCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier(StubApp.getString2(1326), StubApp.getString2(1325), StubApp.getString2(827)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void initMyPid() throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        LogUtil.isEnableDebug(false);
        CommandManger.INSTANCE.init();
        UmUtils.INSTANCE.preInitUm(this);
        initLogan();
        initPhotoError();
        FontsOverrideUtils.setDefaultFont(this, StubApp.getString2(1399), StubApp.getString2(5595));
        instance = this;
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.deye.MxchipApplication.2
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                MxchipApplication.sResumeActivityCount++;
                if (MxchipApplication.sResumeActivityCount != 1 || MxchipApplication.this.isForeground) {
                    return;
                }
                MxchipApplication.this.isForeground = true;
                UnifiedMqttService.setAppForegroundState(true);
                if ((activity instanceof TabMainActivity) || (activity instanceof SplashActivity) || (activity instanceof HomePageActivity)) {
                    LogDebug.INSTANCE.log(StubApp.getString2(13094) + activity.getClass().getSimpleName());
                } else {
                    LogDebug.INSTANCE.log(StubApp.getString2(13093) + activity.getClass().getSimpleName());
                    SubscribeDeviceStates.INSTANCE.getInstance().checkAndReconnectIfNeeded();
                }
                if (activity instanceof H7Aty) {
                    ((H7Aty) activity).refresh();
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                String simpleName = activity.getClass().getSimpleName();
                String str = StubApp.getString2(13085) + simpleName;
                String string2 = StubApp.getString2(13080);
                Log.d(string2, str);
                MyActivityManager.getInstance().setCurrentActivity(activity);
                if (activity instanceof TabMainActivity) {
                    Log.d(string2, StubApp.getString2(13086));
                    DeviceHeartbeatManager.INSTANCE.resumeAll();
                } else if (activity instanceof PublicConstantAty) {
                    Log.d(string2, StubApp.getString2(13087) + simpleName + StubApp.getString2(13088));
                    DeviceHeartbeatManager.INSTANCE.resumeAll();
                } else {
                    Log.d(string2, StubApp.getString2(13089) + simpleName + StubApp.getString2(13090));
                    DeviceHeartbeatManager.INSTANCE.pauseAll();
                    Log.d(string2, StubApp.getString2(13091));
                }
                if (activity instanceof PopupPushActivity) {
                    String token = MxchipApplication.this.getToken();
                    Log.d(string2, StubApp.getString2(13092) + token);
                    if (!token.isEmpty()) {
                        MxchipApplication.getInstance().startActivity(new Intent((Context) MxchipApplication.this, (Class<?>) TabMainActivity.class).addFlags(270532608));
                    } else {
                        MxchipApplication.getInstance().startActivity(new Intent((Context) MxchipApplication.this, (Class<?>) HomePageActivity.class).addFlags(270532608));
                    }
                }
                MxchipApplication.this.activities.add(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                MxchipApplication.this.activities.remove(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                MxchipApplication.sResumeActivityCount--;
                String str = StubApp.getString2(13095) + activity.getClass().getSimpleName() + StubApp.getString2(13096) + MxchipApplication.sResumeActivityCount;
                String string2 = StubApp.getString2(13080);
                Log.d(string2, str);
                if (MxchipApplication.sResumeActivityCount == 0) {
                    Log.d(string2, StubApp.getString2(13097));
                    DeviceHeartbeatManager.INSTANCE.pauseAll();
                }
                if (MxchipApplication.sResumeActivityCount == 0 && MxchipApplication.this.isForeground) {
                    MxchipApplication.this.isForeground = false;
                    Log.d(string2, StubApp.getString2(13098));
                    UnifiedMqttService.setAppForegroundState(false);
                }
            }
        });
    }

    private void initLogan() {
        File file;
        File externalFilesDir = getExternalFilesDir(null);
        String string2 = StubApp.getString2(13107);
        if (externalFilesDir != null) {
            file = new File(externalFilesDir, string2);
        } else {
            file = new File(getFilesDir(), string2);
        }
        LoganConfig.Builder path = new LoganConfig.Builder().setCachePath(StubApp.getOrigApplicationContext(getApplicationContext()).getFilesDir().getAbsolutePath()).setPath(file.getAbsolutePath());
        String string22 = StubApp.getString2(13108);
        LoganConfig loganConfigBuild = path.setEncryptKey16(string22.getBytes()).setEncryptIV16(string22.getBytes()).build();
        Logan.setDebug(false);
        Logan.init(loganConfigBuild);
    }

    private void readAppConfigInfo() throws RuntimeException {
        Configuration.setOversea(ChannelUtil.isOversea());
        MMKV mmkvDefaultMMKV = MMKV.defaultMMKV();
        String string2 = StubApp.getString2(13111);
        String string = mmkvDefaultMMKV.getString(string2, "");
        boolean zIsEmpty = string.isEmpty();
        String string22 = StubApp.getString2(13112);
        if (zIsEmpty) {
            MMKV.defaultMMKV().putString(string2, string22);
            Configuration.setHost(Configuration.ApiHostType.PRODUCTION_HOST_API);
            isReleaseEnv = true;
        } else if (!string22.equals(string) && StubApp.getString2(13113).equals(string)) {
            Configuration.setHost(Configuration.ApiHostType.TEST_HOST_API);
            isReleaseEnv = false;
        } else {
            Configuration.setHost(Configuration.ApiHostType.PRODUCTION_HOST_API);
            isReleaseEnv = true;
        }
        BluetoothSM.getInstance().setEnvUrl(Configuration.getApihost().replace(StubApp.getString2(13114), ""));
    }

    public static MxchipApplication getInstance() {
        return instance;
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            this.activities.remove(activity);
            activity.finish();
        }
    }

    public void exit() {
        for (Activity activity : this.activities) {
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onTerminate() {
        super.onTerminate();
        stopService(new Intent((Context) this, (Class<?>) MxLogcatHelper.class));
    }

    private String getProcessName(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            runningAppProcesses = ((ActivityManager) context.getSystemService(StubApp.getString2("1025"))).getRunningAppProcesses();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == Process.myPid() && runningAppProcessInfo.processName != null) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public static boolean checkIsReleaseEnv() {
        return isReleaseEnv;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void initPushService() throws Resources.NotFoundException {
        String string2;
        String string22;
        createNotificationChannel();
        CloudPushService cloudPushService = PushServiceFactory.getCloudPushService();
        Log.e(StubApp.getString2(13121), isReleaseEnv + "");
        if (ChannelUtil.isOversea()) {
            if (!isReleaseEnv) {
                string2 = StubApp.getString2(13122);
                string22 = StubApp.getString2(13123);
            } else {
                string2 = StubApp.getString2(13124);
                string22 = StubApp.getString2(13125);
            }
        } else if (!isReleaseEnv) {
            string2 = StubApp.getString2(13126);
            string22 = StubApp.getString2(13127);
        } else {
            string2 = StubApp.getString2(13128);
            string22 = StubApp.getString2(13129);
        }
        PushServiceFactory.init(new PushInitConfig.Builder().application(this).appKey(string2).appSecret(string22).build());
        cloudPushService.register(this, new CommonCallback() { // from class: com.deye.MxchipApplication.3
            public void onSuccess(String str) {
                LogUtil.i(StubApp.getString2(13080), StubApp.getString2(13101) + str);
                MxchipApplication.this.getClientId();
                ThirdPushManager.registerImpl(new HuaweiMsgParseImpl());
                ThirdPushManager.registerImpl(new XiaoMiMsgParseImpl());
                ThirdPushManager.registerImpl(new OppoMsgParseImpl());
                ThirdPushManager.registerImpl(new VivoMsgParseImpl());
                HuaWeiRegister.register(MxchipApplication.this);
                MiPushRegister.register(MxchipApplication.this, StubApp.getString2(13102), StubApp.getString2(13103));
                VivoRegister.register(MxchipApplication.this);
                OppoRegister.register(MxchipApplication.this, StubApp.getString2(13104), StubApp.getString2(13105));
            }

            public void onFailed(String str, String str2) {
                LogUtil.e(StubApp.getString2(13080), StubApp.getString2(13099) + str + StubApp.getString2(13100) + str2);
            }
        });
        if (ChannelUtil.isOversea()) {
            GcmRegister.register(this, StubApp.getString2(13132), StubApp.getString2(13133), StubApp.getString2(13130), StubApp.getString2(13131));
        }
    }

    public synchronized String getClientId() {
        String string2 = StubApp.getString2(13116);
        String string22 = StubApp.getString2(13117);
        synchronized (this) {
            String string = MMKV.defaultMMKV().getString(StubApp.getString2("13118"), "");
            if (!TextUtils.isEmpty(string)) {
                DeYeHttpRequestManager.getInstance().setPushClientId(string);
                Log.d(StubApp.getString2("13080"), string22 + string);
                return string;
            }
            String deviceId = PushServiceFactory.getCloudPushService().getDeviceId();
            MMKV.defaultMMKV().putString(StubApp.getString2("13118"), deviceId);
            DeYeHttpRequestManager.getInstance().setPushClientId(deviceId);
            Log.d(StubApp.getString2("13080"), string2 + deviceId);
            return deviceId;
        }
    }

    private void createNotificationChannel() throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(StubApp.getString2(7383));
            String string = getResources().getString(2131951727);
            String string2 = getResources().getString(2131951727);
            NotificationChannel notificationChannel = new NotificationChannel(StubApp.getString2(2546), string, 4);
            notificationChannel.setDescription(string2);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(-65536);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{1000});
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public static boolean isDebuggable(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void checkDebuggableInNotDebugModel(final Context context) {
        if (isDebuggable(context)) {
            exitProcess(context);
        }
        new Thread(new Runnable() { // from class: com.deye.MxchipApplication.4
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                while (true) {
                    try {
                        Thread.sleep(300L);
                        if (Debug.isDebuggerConnected()) {
                            MxchipApplication.exitProcess(context);
                        }
                        if (MxchipApplication.isUnderTraced()) {
                            MxchipApplication.exitProcess(context);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, StubApp.getString2(13106)).start();
        if (isUnderTraced()) {
            exitProcess(context);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void loginOut() {
        MMKVUtils.INSTANCE.setUserInfo(null);
        MMKV.defaultMMKV().encode(StubApp.getString2(13134), "");
        MMKV.defaultMMKV().encode(StubApp.getString2(13118), "");
        DeYeHttpRequestManager.getInstance().setToken("");
        UmUtils.INSTANCE.loginOUt();
        Intent intent = new Intent((Context) this, (Class<?>) HomePageActivity.class);
        intent.putExtra(StubApp.getString2(13135), true);
        intent.setFlags(268468224);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isUnderTraced() throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(String.format(Locale.US, StubApp.getString2(13109), Integer.valueOf(Process.myPid())))));
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    if (line.contains(StubApp.getString2("13110"))) {
                        String[] strArrSplit = line.split(StubApp.getString2("669"));
                        if (strArrSplit.length == 2 && Integer.parseInt(strArrSplit[1].trim()) != 0) {
                            return true;
                        }
                    }
                } else {
                    bufferedReader.close();
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isColdBootApp() {
        return isColdBootApp;
    }

    public static void setColdBootApp(boolean z) {
        isColdBootApp = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SharedPrefsUtil getSharePrefsUtil() {
        if (this.mSharedPrefsUtil == null) {
            this.mSharedPrefsUtil = new SharedPrefsUtil(this);
        }
        return this.mSharedPrefsUtil;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.react.ReactApplication
    public ReactNativeHost getReactNativeHost() {
        return new DefaultReactNativeHost(this) { // from class: com.deye.MxchipApplication.5
            @Override // com.facebook.react.ReactNativeHost
            public boolean getUseDeveloperSupport() {
                return false;
            }

            @Override // com.facebook.react.defaults.DefaultReactNativeHost
            protected boolean isNewArchEnabled() {
                return false;
            }

            @Override // com.facebook.react.ReactNativeHost
            protected List<ReactPackage> getPackages() {
                ArrayList<ReactPackage> packages = new PackageList(this).getPackages();
                packages.add(new CommonReactPackage());
                return packages;
            }

            @Override // com.facebook.react.ReactNativeHost
            protected String getJSMainModuleName() {
                return StubApp.getString2(11241);
            }

            @Override // com.facebook.react.defaults.DefaultReactNativeHost
            protected Boolean isHermesEnabled() {
                return true;
            }
        };
    }

    @Override // com.facebook.react.ReactApplication
    public ReactHost getReactHost() {
        return DefaultReactHost.getDefaultReactHost(StubApp.getOrigApplicationContext(getApplicationContext()), getReactNativeHost());
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'FCM' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:293)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:266)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class ThirdPushReportKeyword {
        private static final /* synthetic */ ThirdPushReportKeyword[] $VALUES;
        public static final ThirdPushReportKeyword FCM;
        public String thirdMsgKeyword;
        public String thirdTokenKeyword;
        public static final ThirdPushReportKeyword HUAWEI = new ThirdPushReportKeyword(StubApp.getString2(7107), 0, StubApp.getString2(7108), StubApp.getString2(6944));
        public static final ThirdPushReportKeyword XIAOMI = new ThirdPushReportKeyword(StubApp.getString2(1951), 1, StubApp.getString2(7110), StubApp.getString2(7073));
        public static final ThirdPushReportKeyword OPPO = new ThirdPushReportKeyword(StubApp.getString2(JfifUtil.MARKER_EOI), 2, StubApp.getString2(7112), StubApp.getString2(6958));
        public static final ThirdPushReportKeyword VIVO = new ThirdPushReportKeyword(StubApp.getString2(1954), 3, StubApp.getString2(7113), StubApp.getString2(9308));
        public static final ThirdPushReportKeyword MEIZU = new ThirdPushReportKeyword(StubApp.getString2(1966), 4, StubApp.getString2(7115), StubApp.getString2(6954));

        private static /* synthetic */ ThirdPushReportKeyword[] $values() {
            return new ThirdPushReportKeyword[]{HUAWEI, XIAOMI, OPPO, VIVO, MEIZU, FCM};
        }

        public static ThirdPushReportKeyword valueOf(String str) {
            return (ThirdPushReportKeyword) Enum.valueOf(ThirdPushReportKeyword.class, str);
        }

        public static ThirdPushReportKeyword[] values() {
            return (ThirdPushReportKeyword[]) $VALUES.clone();
        }

        static {
            String string2 = StubApp.getString2(7116);
            FCM = new ThirdPushReportKeyword(StubApp.getString2(7118), 5, string2, string2);
            $VALUES = $values();
        }

        private ThirdPushReportKeyword(String str, int i, String str2, String str3) {
            this.thirdTokenKeyword = str2;
            this.thirdMsgKeyword = str3;
        }
    }

    public String getSignatures(Context context) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance(StubApp.getString2("10304")).digest(context.getPackageManager().getPackageInfo(getPackageName(), 64).signatures[0].toByteArray());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bArrDigest) {
                String upperCase = Integer.toHexString(b & 255).toUpperCase(Locale.US);
                if (upperCase.length() == 1) {
                    stringBuffer.append(StubApp.getString2("701"));
                }
                stringBuffer.append(upperCase);
                stringBuffer.append(StubApp.getString2("669"));
            }
            String string = stringBuffer.toString();
            Log.d(StubApp.getString2("13080"), StubApp.getString2("13120") + (StubApp.getString2("13119") + string.substring(0, string.length() - 1)));
            return string.substring(0, string.length() - 1);
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // io.fogcloud.sdk.fog.BaseApp
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LanUtils.setAppLanguage(context));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(android.content.res.Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LanUtils.setAppLanguage(this);
    }
}
