package com.deye.capture;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import com.deye.MyActivityManager;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ScreenCapturetListenManager {
    private static final String[] KEYWORDS;
    private static final String[] MEDIA_PROJECTIONS;
    private static final String[] MEDIA_PROJECTIONS_API_16;
    private static Point sScreenRealSize;
    private Context mContext;
    private MediaContentObserver mExternalObserver;
    private String[] mFileKeyWords;
    private MediaContentObserver mInternalObserver;
    private OnScreenCapturetListen mListener;
    private long mStartListenTime;
    private final List<String> sHasCallbackPaths = new ArrayList();
    private final Handler mUiHandler = new Handler(Looper.getMainLooper());
    private long lastNotifyTime = 0;

    public interface OnScreenCapturetListen {
        void onShot(String str);
    }

    static {
        String string2 = StubApp.getString2(4271);
        String string22 = StubApp.getString2(13833);
        MEDIA_PROJECTIONS = new String[]{string2, string22};
        MEDIA_PROJECTIONS_API_16 = new String[]{string2, string22, StubApp.getString2(11232), StubApp.getString2(11233)};
        KEYWORDS = new String[]{StubApp.getString2(5240), StubApp.getString2(13834), StubApp.getString2(13835), StubApp.getString2(13836), StubApp.getString2(13837), StubApp.getString2(13838), StubApp.getString2(13839), StubApp.getString2(13840), StubApp.getString2(13841), StubApp.getString2(13842), StubApp.getString2(13843), StubApp.getString2(13844), StubApp.getString2(13845)};
    }

    private ScreenCapturetListenManager(Context context, String[] strArr) {
        if (context == null) {
            throw new IllegalArgumentException(StubApp.getString2(13850));
        }
        this.mContext = context;
        if (sScreenRealSize == null) {
            Point realScreenSize = getRealScreenSize();
            sScreenRealSize = realScreenSize;
            String string2 = StubApp.getString2(13846);
            if (realScreenSize != null) {
                Log.e(string2, StubApp.getString2(13847) + sScreenRealSize.x + StubApp.getString2(13848) + sScreenRealSize.y);
            } else {
                Log.e(string2, StubApp.getString2(13849));
            }
        }
        this.mFileKeyWords = strArr;
    }

    public static ScreenCapturetListenManager newInstance(Context context, String[] strArr) {
        assertInMainThread();
        return new ScreenCapturetListenManager(context, strArr);
    }

    public void startListen() {
        assertInMainThread();
        this.sHasCallbackPaths.clear();
        this.mStartListenTime = System.currentTimeMillis();
        this.mInternalObserver = new MediaContentObserver(MediaStore.Images.Media.INTERNAL_CONTENT_URI, this.mUiHandler);
        this.mExternalObserver = new MediaContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.mUiHandler);
        this.mContext.getContentResolver().registerContentObserver(MediaStore.Images.Media.INTERNAL_CONTENT_URI, true, this.mInternalObserver);
        this.mContext.getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, this.mExternalObserver);
    }

    public void stopListen() {
        assertInMainThread();
        if (this.mInternalObserver != null) {
            try {
                this.mContext.getContentResolver().unregisterContentObserver(this.mInternalObserver);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.mInternalObserver = null;
        }
        if (this.mExternalObserver != null) {
            try {
                this.mContext.getContentResolver().unregisterContentObserver(this.mExternalObserver);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.mExternalObserver = null;
        }
        this.mStartListenTime = 0L;
        this.sHasCallbackPaths.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMediaContentChange(Uri uri) {
        int i;
        int i2;
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.mContext.getContentResolver().query(uri.buildUpon().appendQueryParameter(StubApp.getString2("9126"), StubApp.getString2("2546")).build(), MEDIA_PROJECTIONS_API_16, null, null, StubApp.getString2("13854"));
            } catch (Exception e) {
                e.printStackTrace();
                if (0 == 0 || cursorQuery.isClosed()) {
                    return;
                }
            }
            if (cursorQuery == null) {
                Log.e("lewinScreen", StubApp.getString2("13855"));
                if (cursorQuery == null || cursorQuery.isClosed()) {
                    return;
                }
                cursorQuery.close();
                return;
            }
            if (!cursorQuery.moveToFirst()) {
                Log.e("lewinScreen", StubApp.getString2("13856"));
                if (cursorQuery == null || cursorQuery.isClosed()) {
                    return;
                }
                cursorQuery.close();
                return;
            }
            int columnIndex = cursorQuery.getColumnIndex(StubApp.getString2("4271"));
            int columnIndex2 = cursorQuery.getColumnIndex(StubApp.getString2("13833"));
            int columnIndex3 = cursorQuery.getColumnIndex(StubApp.getString2("11232"));
            int columnIndex4 = cursorQuery.getColumnIndex(StubApp.getString2("11233"));
            String string = cursorQuery.getString(columnIndex);
            long j = cursorQuery.getLong(columnIndex2);
            if (columnIndex3 < 0 || columnIndex4 < 0) {
                Point imageSize = getImageSize(string);
                int i3 = imageSize.x;
                i = imageSize.y;
                i2 = i3;
            } else {
                i2 = cursorQuery.getInt(columnIndex3);
                i = cursorQuery.getInt(columnIndex4);
            }
            handleMediaRowData(string, j, i2, i);
            if (cursorQuery == null || cursorQuery.isClosed()) {
                return;
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (0 != 0 && !cursorQuery.isClosed()) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    private Point getImageSize(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return new Point(options.outWidth, options.outHeight);
    }

    private void handleMediaRowData(String str, long j, int i, int i2) {
        boolean zCheckScreenShot = checkScreenShot(str, j, i, i2);
        String string2 = StubApp.getString2(13857);
        String string22 = StubApp.getString2(13848);
        String string23 = StubApp.getString2(13858);
        String string24 = StubApp.getString2(13846);
        if (zCheckScreenShot) {
            Log.e(string24, StubApp.getString2(13859) + str + string23 + i + string22 + i2 + string2 + j);
            if (this.mListener == null || checkCallback(str)) {
                return;
            }
            this.mListener.onShot(str);
            return;
        }
        Log.e(string24, StubApp.getString2(13860) + str + string23 + i + string22 + i2 + string2 + j);
    }

    private boolean checkScreenShot(String str, long j, int i, int i2) {
        long j2 = this.mStartListenTime;
        String string2 = StubApp.getString2(13846);
        if (j < j2 || System.currentTimeMillis() - j > 10000) {
            Log.e(string2, System.currentTimeMillis() + StubApp.getString2(13853) + j);
            return false;
        }
        Point point = sScreenRealSize;
        if (point != null && ((i > point.x || i2 > sScreenRealSize.y) && (i2 > sScreenRealSize.x || i > sScreenRealSize.y))) {
            Log.e(string2, StubApp.getString2(13852));
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        for (String str2 : KEYWORDS) {
            if (lowerCase.contains(str2)) {
                return true;
            }
        }
        String[] strArr = this.mFileKeyWords;
        if (strArr != null && strArr.length > 0) {
            for (String str3 : strArr) {
                if (lowerCase.contains(str3)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkCallback(String str) {
        if (this.sHasCallbackPaths.contains(str)) {
            return true;
        }
        if (this.sHasCallbackPaths.size() >= 20) {
            for (int i = 0; i < 5; i++) {
                this.sHasCallbackPaths.remove(0);
            }
        }
        this.sHasCallbackPaths.add(str);
        return false;
    }

    private Point getRealScreenSize() {
        Point point;
        Exception e;
        try {
            point = new Point();
        } catch (Exception e2) {
            point = null;
            e = e2;
        }
        try {
            ((WindowManager) this.mContext.getSystemService(StubApp.getString2("13557"))).getDefaultDisplay().getRealSize(point);
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return point;
        }
        return point;
    }

    public void setListener(OnScreenCapturetListen onScreenCapturetListen) {
        this.mListener = onScreenCapturetListen;
    }

    private static void assertInMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            throw new IllegalStateException(StubApp.getString2(13851) + ((stackTrace == null || stackTrace.length < 4) ? null : stackTrace[3].toString()));
        }
    }

    private class MediaContentObserver extends ContentObserver {
        private Uri mContentUri;

        public MediaContentObserver(Uri uri, Handler handler) {
            super(handler);
            this.mContentUri = uri;
        }

        public static String[] getPermission(Context context) {
            String[] strArr = new String[0];
            if (context.getApplicationInfo().targetSdkVersion > 32) {
                return new String[]{StubApp.getString2(13832)};
            }
            return new String[]{StubApp.getString2(10157)};
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            String[] permission = getPermission(ScreenCapturetListenManager.this.mContext);
            Context currentActivity = ScreenCapturetListenManager.this.mContext;
            if (MyActivityManager.getInstance().getCurrentActivity() != null) {
                currentActivity = MyActivityManager.getInstance().getCurrentActivity();
            }
            if (XXPermissions.isGranted(currentActivity, permission)) {
                ScreenCapturetListenManager.this.handleMediaContentChange(this.mContentUri);
            } else {
                if (System.currentTimeMillis() - ScreenCapturetListenManager.this.lastNotifyTime < 1000) {
                    return;
                }
                ScreenCapturetListenManager.this.lastNotifyTime = System.currentTimeMillis();
                XXPermissions.with(currentActivity).permission(permission).request(new OnPermissionCallback() { // from class: com.deye.capture.ScreenCapturetListenManager.MediaContentObserver.1
                    public void onGranted(List<String> list, boolean z2) {
                        ScreenCapturetListenManager.this.handleMediaContentChange(MediaContentObserver.this.mContentUri);
                    }
                });
            }
        }
    }
}
