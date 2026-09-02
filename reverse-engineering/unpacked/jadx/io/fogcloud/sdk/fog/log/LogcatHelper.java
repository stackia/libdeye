package io.fogcloud.sdk.fog.log;

import android.content.Context;
import android.os.Environment;
import android.os.Process;
import com.stub.StubApp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class LogcatHelper {
    private static LogcatHelper INSTANCE;
    private static String PATH_LOGCAT;
    private LogDumper mLogDumper = null;
    private int mPId;

    public void init(Context context) {
        if (Environment.getExternalStorageState().equals(StubApp.getString2(3450))) {
            PATH_LOGCAT = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + context.getApplicationInfo().processName;
        } else {
            PATH_LOGCAT = context.getFilesDir().getAbsolutePath() + File.separator + context.getApplicationInfo().processName;
        }
        File file = new File(PATH_LOGCAT);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    public static LogcatHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LogcatHelper(context);
        }
        return INSTANCE;
    }

    private LogcatHelper(Context context) {
        init(context);
        this.mPId = Process.myPid();
    }

    public void start() {
        if (this.mLogDumper == null) {
            this.mLogDumper = new LogDumper(this, String.valueOf(this.mPId), PATH_LOGCAT);
        }
        if (this.mLogDumper.isAlive()) {
            return;
        }
        this.mLogDumper.start();
    }

    public void stop() {
        LogDumper logDumper = this.mLogDumper;
        if (logDumper != null) {
            logDumper.stopLogs();
            this.mLogDumper = null;
        }
    }

    private class LogDumper extends Thread {
        String cmds;
        private Process logcatProc;
        private String mPID;
        private BufferedReader mReader;
        private boolean mRunning;
        private FileOutputStream out;
        final /* synthetic */ LogcatHelper this$0;

        public LogDumper(LogcatHelper logcatHelper, String str, String str2) {
            String string2 = StubApp.getString2(45171);
            this.this$0 = logcatHelper;
            this.mReader = null;
            this.mRunning = true;
            this.cmds = null;
            this.out = null;
            this.mPID = str;
            try {
                this.out = new FileOutputStream(new File(str2, string2 + LogcatDate.getFileName() + StubApp.getString2("6040")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            this.cmds = StubApp.getString2(45172);
        }

        public void stopLogs() {
            this.mRunning = false;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws IOException {
            FileOutputStream fileOutputStream;
            String line;
            try {
                try {
                    this.logcatProc = Runtime.getRuntime().exec(this.cmds);
                    this.mReader = new BufferedReader(new InputStreamReader(this.logcatProc.getInputStream()), 1024);
                    while (this.mRunning && (line = this.mReader.readLine()) != null && this.mRunning) {
                        if (line.length() != 0 && this.out != null && line.contains(this.mPID)) {
                            this.out.write((LogcatDate.getDateEN() + StubApp.getString2("1026") + line + StubApp.getString2("1033")).getBytes());
                        }
                    }
                    Process process = this.logcatProc;
                    if (process != null) {
                        process.destroy();
                        this.logcatProc = null;
                    }
                    BufferedReader bufferedReader = this.mReader;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                            this.mReader = null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    fileOutputStream = this.out;
                } catch (Throwable th) {
                    Process process2 = this.logcatProc;
                    if (process2 != null) {
                        process2.destroy();
                        this.logcatProc = null;
                    }
                    BufferedReader bufferedReader2 = this.mReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                            this.mReader = null;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    FileOutputStream fileOutputStream2 = this.out;
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        this.out = null;
                        throw th;
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
                Process process3 = this.logcatProc;
                if (process3 != null) {
                    process3.destroy();
                    this.logcatProc = null;
                }
                BufferedReader bufferedReader3 = this.mReader;
                if (bufferedReader3 != null) {
                    try {
                        bufferedReader3.close();
                        this.mReader = null;
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                FileOutputStream fileOutputStream3 = this.out;
                if (fileOutputStream3 == null) {
                    return;
                }
                try {
                    fileOutputStream3.close();
                } catch (IOException e6) {
                    e = e6;
                    e.printStackTrace();
                    this.out = null;
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e7) {
                    e = e7;
                    e.printStackTrace();
                    this.out = null;
                }
                this.out = null;
            }
        }
    }

    public static class LogcatDate {
        public static String getFileName() {
            return StubApp.getString2(45173) + new SimpleDateFormat(StubApp.getString2(13238)).format(new Date(System.currentTimeMillis()));
        }

        public static String getDateEN() {
            return new SimpleDateFormat(StubApp.getString2(13238)).format(new Date(System.currentTimeMillis()));
        }
    }
}
