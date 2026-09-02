package io.fogcloud.sdk.fog.log;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;
import com.stub.StubApp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class MxCrashHandler implements Thread.UncaughtExceptionHandler {
    private Map<String, String> infos = new HashMap();
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    public static final String TAG = StubApp.getString2(13000);
    private static MxCrashHandler INSTANCE = new MxCrashHandler();

    private MxCrashHandler() {
    }

    public static MxCrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        this.mContext = context;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) throws InterruptedException {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        if (!handleException(th) && (uncaughtExceptionHandler = this.mDefaultHandler) != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
            return;
        }
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            Log.e(StubApp.getString2(13000), StubApp.getString2(45179), e);
        }
        Process.killProcess(Process.myPid());
        System.exit(1);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [io.fogcloud.sdk.fog.log.MxCrashHandler$1] */
    private boolean handleException(final Throwable th) throws PackageManager.NameNotFoundException, IOException {
        if (th == null) {
            return false;
        }
        new Thread() { // from class: io.fogcloud.sdk.fog.log.MxCrashHandler.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Looper.prepare();
                th.printStackTrace();
                Toast.makeText(MxCrashHandler.this.mContext, StubApp.getString2(45174), 1).show();
                Looper.loop();
            }
        }.start();
        collectDeviceInfo(this.mContext);
        saveCrashInfo2File(th);
        return true;
    }

    public void collectDeviceInfo(Context context) throws PackageManager.NameNotFoundException {
        String string2 = StubApp.getString2(13000);
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                String str = packageInfo.versionName == null ? "null" : packageInfo.versionName;
                String str2 = packageInfo.versionCode + "";
                this.infos.put(StubApp.getString2("27145"), str);
                this.infos.put(StubApp.getString2("21686"), str2);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(string2, StubApp.getString2(45177), e);
        }
        for (Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                this.infos.put(field.getName(), field.get(null).toString());
                Log.d(string2, field.getName() + StubApp.getString2("13921") + field.get(null));
            } catch (Exception e2) {
                Log.e(string2, StubApp.getString2(45178), e2);
            }
        }
    }

    private String saveCrashInfo2File(Throwable th) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : this.infos.entrySet()) {
            stringBuffer.append(entry.getKey()).append(StubApp.getString2(704)).append(entry.getValue()).append(StubApp.getString2(1033));
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();
        stringBuffer.append(LogFileUtil.getTime()).append(stringWriter.toString());
        try {
            System.currentTimeMillis();
            String str = LogFileUtil.getDateFlag(this.mContext) + StubApp.getString2("45175");
            if (Environment.getExternalStorageState().equals(StubApp.getString2("3450"))) {
                String mxChipLogFilePath = LogFileUtil.getMxChipLogFilePath(this.mContext);
                File file = new File(mxChipLogFilePath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(mxChipLogFilePath + str, true);
                fileOutputStream.write(stringBuffer.toString().getBytes());
                fileOutputStream.close();
            }
            return str;
        } catch (Exception e) {
            Log.e(StubApp.getString2(13000), StubApp.getString2(45176), e);
            return null;
        }
    }
}
