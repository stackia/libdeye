package com.deye.activity.app_config;

import android.app.Activity;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.deye.MyActivityManager;
import com.deye.entity.UserInfoBean;
import com.deye.utils.ChannelUtil;
import com.deye.utils.MMKVUtils;
import com.deye.utils.SignatureUtil;
import com.dianping.logan.Logan;
import com.dianping.logan.SendLogRunnable;
import com.stub.StubApp;
import com.umeng.analytics.pro.ca;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class RealSendLogRunnable extends SendLogRunnable {
    private static final String APP_ID = StubApp.getString2(1764);
    private static final String LOGAN_SIGN_SECRET = StubApp.getString2(13277);
    private static final String TAG = StubApp.getString2(13259);
    private ISendLogCallBack callBack;
    private String mUploadLogUrl = StubApp.getString2(13257);
    private String overseaUrl = StubApp.getString2(13258);

    public RealSendLogRunnable(ISendLogCallBack iSendLogCallBack) {
        this.callBack = iSendLogCallBack;
    }

    @Override // com.dianping.logan.SendLogRunnable
    public void sendLog(File file) {
        ISendLogCallBack iSendLogCallBack = this.callBack;
        if (iSendLogCallBack != null) {
            iSendLogCallBack.onStartSend();
        }
        boolean zDoSendFileByAction = doSendFileByAction(file);
        String str = StubApp.getString2(13288) + zDoSendFileByAction + StubApp.getString2(13289) + file.getName();
        String string2 = StubApp.getString2(13259);
        Log.d(string2, str);
        finish();
        ISendLogCallBack iSendLogCallBack2 = this.callBack;
        if (iSendLogCallBack2 != null) {
            iSendLogCallBack2.onFinishSend(zDoSendFileByAction);
        }
        if (file.getName().contains(StubApp.getString2(13290))) {
            file.delete();
        }
        if (zDoSendFileByAction) {
            deleteAllLogFiles();
        } else {
            Log.w(string2, StubApp.getString2(13291));
        }
    }

    private void deleteAllLogFiles() {
        String string2 = StubApp.getString2(13259);
        try {
            Map<String, Long> allFilesInfo = Logan.getAllFilesInfo();
            if (allFilesInfo != null && !allFilesInfo.isEmpty()) {
                File loganLogDirectory = getLoganLogDirectory();
                if (loganLogDirectory != null && loganLogDirectory.exists()) {
                    File[] fileArrListFiles = loganLogDirectory.listFiles();
                    if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                        int i = 0;
                        int i2 = 0;
                        for (File file : fileArrListFiles) {
                            if (file.isFile()) {
                                String name = file.getName();
                                long length = file.length();
                                try {
                                    if (file.delete()) {
                                        i2++;
                                        Log.d(string2, StubApp.getString2("13260") + name + StubApp.getString2("13261") + length + StubApp.getString2("13262"));
                                    } else {
                                        i++;
                                        Log.e(string2, StubApp.getString2("13263") + name);
                                    }
                                } catch (Exception e) {
                                    i++;
                                    Log.e(string2, StubApp.getString2("13264") + name + StubApp.getString2("7134") + e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                        }
                        Log.d(string2, StubApp.getString2("13265") + allFilesInfo.size() + StubApp.getString2("13266") + fileArrListFiles.length + StubApp.getString2("13267") + i2 + StubApp.getString2("13268") + i);
                        return;
                    }
                    Log.d(string2, StubApp.getString2("13269"));
                    return;
                }
                Log.w(string2, StubApp.getString2("13270"));
                return;
            }
            Log.d(string2, StubApp.getString2("13271"));
        } catch (Exception e2) {
            Log.e(string2, StubApp.getString2(13272) + e2.getMessage());
            e2.printStackTrace();
        }
    }

    private File getLoganLogDirectory() {
        String string2 = StubApp.getString2(13259);
        String string22 = StubApp.getString2(13281);
        String string23 = StubApp.getString2(13282);
        try {
            Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
            if (currentActivity != null) {
                File externalFilesDir = currentActivity.getExternalFilesDir(null);
                File file = externalFilesDir != null ? new File(externalFilesDir, "logan_v1") : new File(currentActivity.getFilesDir(), "logan_v1");
                if (file.exists()) {
                    Log.d(string2, string22 + file.getAbsolutePath());
                    return file;
                }
                Log.w(string2, string23 + file.getAbsolutePath());
            } else {
                Log.w(string2, StubApp.getString2("13283"));
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                if (externalStorageDirectory != null && externalStorageDirectory.exists()) {
                    File file2 = new File(externalStorageDirectory, StubApp.getString2("13284"));
                    if (file2.exists()) {
                        return file2;
                    }
                }
            }
        } catch (Exception e) {
            Log.e(string2, StubApp.getString2(13285) + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private HashMap<String, String> getActionHeader() throws NoSuchAlgorithmException, InvalidKeyException {
        String enduserid;
        HashMap<String, String> map = new HashMap<>();
        String strValueOf = String.valueOf(System.currentTimeMillis());
        map.put(StubApp.getString2(557), StubApp.getString2(13273));
        map.put(StubApp.getString2(1443), StubApp.getString2(827));
        String string2 = StubApp.getString2(6942);
        String string22 = StubApp.getString2(1764);
        map.put(string2, string22);
        map.put(StubApp.getString2(4778), strValueOf);
        UserInfoBean userInfo = MMKVUtils.INSTANCE.getUserInfo();
        if (userInfo == null) {
            enduserid = "";
        } else {
            enduserid = userInfo.getEnduserid();
            if (!TextUtils.isEmpty(userInfo.getPhone())) {
                map.put(StubApp.getString2(831), userInfo.getPhone());
            }
        }
        map.put(StubApp.getString2(13274), enduserid);
        String str = StubApp.getString2(13275) + enduserid + StubApp.getString2(7166) + strValueOf;
        LogUtil.e(StubApp.getString2(13276) + str);
        String strGenerateSignature = SignatureUtil.generateSignature(str, StubApp.getString2(13277));
        LogUtil.e(StubApp.getString2(13278) + strGenerateSignature);
        map.put(StubApp.getString2(13279), strGenerateSignature);
        StringBuilder sbAppend = new StringBuilder().append(Build.MANUFACTURER);
        String string23 = StubApp.getString2(3);
        map.put(StubApp.getString2(13280), sbAppend.append(string23).append(Build.MODEL).append(string23).append(Build.VERSION.RELEASE).toString());
        map.put(StubApp.getString2(89), StubApp.getString2(13136));
        map.put(StubApp.getString2(840), string22);
        return map;
    }

    private boolean doSendFileByAction(File file) {
        try {
            return handleSendLogBackData(doPostRequest(ChannelUtil.isOversea() ? this.overseaUrl : this.mUploadLogUrl, new FileInputStream(file), getActionHeader()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0162 A[PHI: r0 r5 r9 r10
      0x0162: PHI (r0v14 ??) = (r0v11 ??), (r0v12 ??), (r0v15 ??) binds: [B:91:0x0110, B:133:0x0160, B:112:0x0138] A[DONT_GENERATE, DONT_INLINE]
      0x0162: PHI (r5v9 java.io.InputStream) = (r5v6 java.io.InputStream), (r5v7 java.io.InputStream), (r5v10 java.io.InputStream) binds: [B:91:0x0110, B:133:0x0160, B:112:0x0138] A[DONT_GENERATE, DONT_INLINE]
      0x0162: PHI (r9v13 ??) = (r9v10 ??), (r9v11 ??), (r9v14 ??) binds: [B:91:0x0110, B:133:0x0160, B:112:0x0138] A[DONT_GENERATE, DONT_INLINE]
      0x0162: PHI (r10v9 'e' ??) = (r10v5 'e' ??), (r10v7 'e' ??), (r10v11 'e' ??) binds: [B:91:0x0110, B:133:0x0160, B:112:0x0138] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x00f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0130 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0144 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x00fe A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x014e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0108 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0126 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x011c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0158 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v1, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v37 */
    /* JADX WARN: Type inference failed for: r0v38 */
    /* JADX WARN: Type inference failed for: r0v39 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v10, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r11v29, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v21, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private byte[] doPostRequest(String str, InputStream e, Map<String, String> map) throws Throwable {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        HttpURLConnection httpURLConnection2;
        InputStream inputStream2;
        HttpURLConnection httpURLConnection3;
        InputStream inputStream3;
        HttpURLConnection httpURLConnection4;
        InputStream inputStream4;
        InputStream inputStream5;
        InputStream inputStream6;
        HttpURLConnection httpURLConnection5;
        ?? inputStream7;
        byte[] byteArray;
        ?? r0 = new byte[2048];
        OutputStream outputStream = null;
        InputStream inputStream8 = null;
        outputStream = null;
        try {
            try {
                httpURLConnection5 = (HttpURLConnection) new URL(str).openConnection();
            } catch (Throwable th) {
                th = th;
                outputStream = r0;
                inputStream = inputStream5;
                httpURLConnection = str;
                inputStream6 = e;
            }
        } catch (MalformedURLException e2) {
            e = e2;
            httpURLConnection4 = null;
            inputStream4 = null;
        } catch (ProtocolException e3) {
            e = e3;
            httpURLConnection3 = null;
            inputStream3 = null;
        } catch (IOException e4) {
            e = e4;
            httpURLConnection2 = null;
            inputStream2 = null;
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection = null;
            inputStream = null;
            inputStream6 = e;
        }
        try {
            if (httpURLConnection5 instanceof HttpsURLConnection) {
                ((HttpsURLConnection) httpURLConnection5).setHostnameVerifier(new HostnameVerifier() { // from class: com.deye.activity.app_config.RealSendLogRunnable.1
                    @Override // javax.net.ssl.HostnameVerifier
                    public boolean verify(String str2, SSLSession sSLSession) {
                        return true;
                    }
                });
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpURLConnection5.addRequestProperty(entry.getKey(), entry.getValue());
            }
            httpURLConnection5.setReadTimeout(ca.b);
            httpURLConnection5.setConnectTimeout(ca.b);
            httpURLConnection5.setDoInput(true);
            httpURLConnection5.setDoOutput(true);
            httpURLConnection5.setRequestMethod(StubApp.getString2("558"));
            ?? outputStream2 = httpURLConnection5.getOutputStream();
            while (true) {
                try {
                    int i = e.read(r0);
                    if (i == -1) {
                        break;
                    }
                    outputStream2.write(r0, 0, i);
                } catch (MalformedURLException e5) {
                    e = e5;
                    inputStream7 = 0;
                } catch (ProtocolException e6) {
                    e = e6;
                    inputStream7 = 0;
                } catch (IOException e7) {
                    e = e7;
                    inputStream7 = 0;
                } catch (Throwable th3) {
                    th = th3;
                    inputStream7 = 0;
                }
            }
            outputStream2.flush();
            if (httpURLConnection5.getResponseCode() == 200) {
                ?? byteArrayOutputStream = new ByteArrayOutputStream();
                inputStream7 = httpURLConnection5.getInputStream();
                while (true) {
                    try {
                        int i2 = inputStream7.read(r0);
                        if (i2 == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(r0, 0, i2);
                    } catch (MalformedURLException e8) {
                        e = e8;
                        MalformedURLException malformedURLException = e;
                        r0 = outputStream2;
                        e = malformedURLException;
                        inputStream5 = inputStream7;
                        str = httpURLConnection5;
                        e.printStackTrace();
                        if (r0 != 0) {
                            try {
                                r0.close();
                            } catch (IOException e9) {
                                e9.printStackTrace();
                            }
                        }
                        if (inputStream5 != null) {
                            try {
                                inputStream5.close();
                            } catch (IOException e10) {
                                e10.printStackTrace();
                            }
                        }
                        if (e != 0) {
                            try {
                                e.close();
                            } catch (IOException e11) {
                                e = e11;
                                e.printStackTrace();
                            }
                        }
                        if (str != 0) {
                            str.disconnect();
                        }
                        return null;
                    } catch (ProtocolException e12) {
                        e = e12;
                        ProtocolException protocolException = e;
                        r0 = outputStream2;
                        e = protocolException;
                        inputStream5 = inputStream7;
                        str = httpURLConnection5;
                        e.printStackTrace();
                        if (r0 != 0) {
                            try {
                                r0.close();
                            } catch (IOException e13) {
                                e13.printStackTrace();
                            }
                        }
                        if (inputStream5 != null) {
                            try {
                                inputStream5.close();
                            } catch (IOException e14) {
                                e14.printStackTrace();
                            }
                        }
                        if (e != 0) {
                            try {
                                e.close();
                            } catch (IOException e15) {
                                e = e15;
                                e.printStackTrace();
                            }
                        }
                        if (str != 0) {
                        }
                        return null;
                    } catch (IOException e16) {
                        e = e16;
                        IOException iOException = e;
                        r0 = outputStream2;
                        e = iOException;
                        inputStream5 = inputStream7;
                        str = httpURLConnection5;
                        e.printStackTrace();
                        if (r0 != 0) {
                            try {
                                r0.close();
                            } catch (IOException e17) {
                                e17.printStackTrace();
                            }
                        }
                        if (inputStream5 != null) {
                            try {
                                inputStream5.close();
                            } catch (IOException e18) {
                                e18.printStackTrace();
                            }
                        }
                        if (e != 0) {
                            try {
                                e.close();
                            } catch (IOException e19) {
                                e = e19;
                                e.printStackTrace();
                            }
                        }
                        if (str != 0) {
                        }
                        return null;
                    } catch (Throwable th4) {
                        th = th4;
                        outputStream = outputStream2;
                        th = th;
                        inputStream = inputStream7;
                        httpURLConnection = httpURLConnection5;
                        inputStream6 = e;
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e20) {
                                e20.printStackTrace();
                            }
                        }
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e21) {
                                e21.printStackTrace();
                            }
                        }
                        if (inputStream6 != null) {
                            try {
                                inputStream6.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        if (httpURLConnection == null) {
                            throw th;
                        }
                        httpURLConnection.disconnect();
                        throw th;
                    }
                }
                byteArray = byteArrayOutputStream.toByteArray();
                inputStream8 = inputStream7;
            } else {
                byteArray = null;
            }
            if (outputStream2 != 0) {
                try {
                    outputStream2.close();
                } catch (IOException e23) {
                    e23.printStackTrace();
                }
            }
            if (inputStream8 != null) {
                try {
                    inputStream8.close();
                } catch (IOException e24) {
                    e24.printStackTrace();
                }
            }
            if (e != 0) {
                try {
                    e.close();
                } catch (IOException e25) {
                    e25.printStackTrace();
                }
            }
            if (httpURLConnection5 != null) {
                httpURLConnection5.disconnect();
            }
            return byteArray;
        } catch (MalformedURLException e26) {
            e = e26;
            inputStream4 = null;
            httpURLConnection4 = httpURLConnection5;
            inputStream5 = inputStream4;
            r0 = inputStream4;
            str = httpURLConnection4;
            e.printStackTrace();
            if (r0 != 0) {
            }
            if (inputStream5 != null) {
            }
            if (e != 0) {
            }
            if (str != 0) {
            }
            return null;
        } catch (ProtocolException e27) {
            e = e27;
            inputStream3 = null;
            httpURLConnection3 = httpURLConnection5;
            inputStream5 = inputStream3;
            r0 = inputStream3;
            str = httpURLConnection3;
            e.printStackTrace();
            if (r0 != 0) {
            }
            if (inputStream5 != null) {
            }
            if (e != 0) {
            }
            if (str != 0) {
            }
            return null;
        } catch (IOException e28) {
            e = e28;
            inputStream2 = null;
            httpURLConnection2 = httpURLConnection5;
            inputStream5 = inputStream2;
            r0 = inputStream2;
            str = httpURLConnection2;
            e.printStackTrace();
            if (r0 != 0) {
            }
            if (inputStream5 != null) {
            }
            if (e != 0) {
            }
            if (str != 0) {
            }
            return null;
        } catch (Throwable th5) {
            th = th5;
            inputStream = null;
            httpURLConnection = httpURLConnection5;
            inputStream6 = e;
        }
    }

    private boolean handleSendLogBackData(byte[] bArr) throws JSONException {
        if (bArr != null) {
            String str = new String(bArr);
            if (!TextUtils.isEmpty(str)) {
                z = new JSONObject(str).optInt(StubApp.getString2(109)) == 200;
                Log.i(StubApp.getString2(13287), StubApp.getString2(13286).concat(str));
            }
        }
        return z;
    }
}
