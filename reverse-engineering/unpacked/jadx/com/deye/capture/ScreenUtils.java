package com.deye.capture;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.PixelCopy;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ScreenUtils {
    private ScreenUtils() {
        throw new UnsupportedOperationException(StubApp.getString2(13861));
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(StubApp.getString2(13557));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(StubApp.getString2(13557));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getStatusHeight(Context context) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NumberFormatException {
        try {
            Class<?> cls = Class.forName(StubApp.getString2("13862"));
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField(StubApp.getString2("1326")).get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void snapShotWithStatusBar(Activity activity, final CaptureCallback captureCallback) {
        Window window = activity.getWindow();
        View decorView = window.getDecorView();
        int screenWidth = getScreenWidth(activity);
        int screenHeight = getScreenHeight(activity);
        if (Build.VERSION.SDK_INT >= 26) {
            Rect rect = new Rect(0, 0, screenWidth, screenHeight);
            final Bitmap bitmapCreateBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
            PixelCopy.request(window, rect, bitmapCreateBitmap, new PixelCopy.OnPixelCopyFinishedListener() { // from class: com.deye.capture.ScreenUtils.1
                @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
                public void onPixelCopyFinished(int i) {
                    if (i == 0) {
                        captureCallback.invoke(bitmapCreateBitmap);
                    } else {
                        captureCallback.invoke(null);
                    }
                }
            }, new Handler(Looper.getMainLooper()));
        } else {
            decorView.setDrawingCacheEnabled(true);
            decorView.buildDrawingCache();
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(decorView.getDrawingCache(), 0, 0, screenWidth, screenHeight);
            decorView.destroyDrawingCache();
            captureCallback.invoke(bitmapCreateBitmap2);
        }
    }
}
