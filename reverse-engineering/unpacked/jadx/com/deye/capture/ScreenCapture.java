package com.deye.capture;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import com.deye.MyActivityManager;
import com.deye.TabMainActivity;
import com.deye.capture.ScreenCapturetListenManager;
import com.stub.StubApp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ScreenCapture {
    private static ScreenCapturetListenManager manager;
    private static ScreenCapture sInstance = new ScreenCapture();
    private Activity activity;

    /* JADX WARN: Multi-variable type inference failed */
    public void init(TabMainActivity tabMainActivity) {
        this.activity = tabMainActivity;
    }

    private ScreenCapture() {
    }

    public static ScreenCapture getInstance() {
        return sInstance;
    }

    public void startListener() {
        startListenerCapture(new String[]{StubApp.getString2(13831)});
    }

    public void stopListener() {
        try {
            ScreenCapturetListenManager screenCapturetListenManager = manager;
            if (screenCapturetListenManager != null) {
                screenCapturetListenManager.stopListen();
                manager = null;
            }
        } catch (Exception unused) {
        }
    }

    private void startListenerCapture(String[] strArr) {
        Activity activity;
        if (manager == null && (activity = this.activity) != null) {
            try {
                ScreenCapturetListenManager screenCapturetListenManagerNewInstance = ScreenCapturetListenManager.newInstance(activity, strArr);
                manager = screenCapturetListenManagerNewInstance;
                screenCapturetListenManagerNewInstance.setListener(new ScreenCapturetListenManager.OnScreenCapturetListen() { // from class: com.deye.capture.ScreenCapture.1
                    @Override // com.deye.capture.ScreenCapturetListenManager.OnScreenCapturetListen
                    public void onShot(String str) {
                        Bundle bundle = new Bundle();
                        bundle.putString(StubApp.getString2(109), StubApp.getString2(1429));
                        String string2 = StubApp.getString2(10508);
                        bundle.putString(StubApp.getString2(11117), str.indexOf(string2) == 0 ? str : string2 + str);
                        bundle.putString(StubApp.getString2(13829), ScreenCapture.bitmapToBase64(BitmapFactory.decodeFile(str), StubApp.getString2(1063), 100));
                        FloatWindowManager.showFloatWindow(MyActivityManager.getInstance().getCurrentActivity(), str);
                        new Handler(ScreenCapture.this.activity.getMainLooper()).postDelayed(new Runnable() { // from class: com.deye.capture.ScreenCapture.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                FloatWindowManager.removeFloatWindow();
                            }
                        }, 3000L);
                    }
                });
                manager.startListen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Bitmap.CompressFormat extToCompressFormat(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 105441:
                if (str.equals(StubApp.getString2(1064))) {
                    c = 0;
                    break;
                }
                break;
            case 111145:
                if (str.equals(StubApp.getString2(1063))) {
                    c = 1;
                    break;
                }
                break;
            case 3268712:
                if (str.equals(StubApp.getString2(13830))) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
        }
        return Bitmap.CompressFormat.PNG;
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0038: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:20:0x0038 */
    public static String bitmapToBase64(Bitmap bitmap, String str, int i) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        String strEncodeToString;
        ByteArrayOutputStream byteArrayOutputStream3 = null;
        try {
            if (bitmap != null) {
                try {
                    byteArrayOutputStream2 = new ByteArrayOutputStream();
                    try {
                        bitmap.compress(extToCompressFormat(str), i, byteArrayOutputStream2);
                        byteArrayOutputStream2.flush();
                        byteArrayOutputStream2.close();
                        strEncodeToString = Base64.encodeToString(byteArrayOutputStream2.toByteArray(), 0);
                        byteArrayOutputStream3 = byteArrayOutputStream2;
                    } catch (IOException e) {
                        e = e;
                        e.printStackTrace();
                        if (byteArrayOutputStream2 == null) {
                            return null;
                        }
                        try {
                            byteArrayOutputStream2.flush();
                            byteArrayOutputStream2.close();
                            return null;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            return null;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    byteArrayOutputStream2 = null;
                } catch (Throwable th) {
                    th = th;
                    if (byteArrayOutputStream3 != null) {
                        try {
                            byteArrayOutputStream3.flush();
                            byteArrayOutputStream3.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } else {
                strEncodeToString = null;
            }
            if (byteArrayOutputStream3 != null) {
                try {
                    byteArrayOutputStream3.flush();
                    byteArrayOutputStream3.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            return strEncodeToString;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream3 = byteArrayOutputStream;
        }
    }
}
