package com.deye.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.deye.MxchipApplication;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class UserAvatarHelper {
    public static String getAvatarCachePath(Context context) {
        LogUtil.d(StubApp.getString2(14184) + (context == null));
        LogUtil.d(StubApp.getString2(14185) + (context.getCacheDir() == null));
        LogUtil.d(StubApp.getString2(14186) + (context.getCacheDir().getAbsolutePath() == null));
        return context.getCacheDir().getAbsolutePath() + StubApp.getString2(13945);
    }

    public static String getAvatarPrefixPath() {
        return MxchipApplication.getInstance().getExternalCacheDir().getPath() + StubApp.getString2(13939);
    }

    public static String getAvatarPath() {
        String avatarPrefixPath = getAvatarPrefixPath();
        File file = new File(avatarPrefixPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return avatarPrefixPath + StubApp.getString2(13945);
    }

    public static void imgSaveToSD(String str, String str2) throws IOException {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        File file = new File(str2);
        FileOutputStream fileOutputStream2 = null;
        try {
            fileInputStream = new FileInputStream(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileInputStream = null;
        }
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(fileInputStream);
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e2) {
            e = e2;
        }
        try {
            try {
                bitmapDecodeStream.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                LogUtil.d(StubApp.getString2("14187"));
            } catch (FileNotFoundException e3) {
                e = e3;
                fileOutputStream2 = fileOutputStream;
                e.printStackTrace();
                fileOutputStream = fileOutputStream2;
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    public static void downLoadFileToSD(String str, final String str2) {
        try {
            new OkHttpClient().newCall(new Request.Builder().url(str).build()).enqueue(new Callback() { // from class: com.deye.helper.UserAvatarHelper.1
                public void onFailure(Call call, IOException iOException) {
                    LogUtil.d(StubApp.getString2(14182) + iOException.getMessage());
                }

                public void onResponse(Call call, Response response) throws IOException {
                    LogUtil.d(StubApp.getString2(14183) + response.code());
                    Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(response.body().byteStream());
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
                    if (bitmapDecodeStream != null) {
                        bitmapDecodeStream.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteAvatarFile() {
        File file = new File(getAvatarPrefixPath() + StubApp.getString2(13945));
        if (file.exists()) {
            file.delete();
        }
        File file2 = new File(getAvatarPrefixPath());
        if (file2.exists()) {
            file2.delete();
        }
    }
}
