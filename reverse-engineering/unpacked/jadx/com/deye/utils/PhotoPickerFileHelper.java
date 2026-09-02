package com.deye.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.amap.location.support.bean.location.AmapLocationNetwork;
import com.luck.picture.lib.entity.LocalMedia;
import com.stub.StubApp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: PhotoPickerFileHelper.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nJ\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH\u0002J\u001a\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\u0010"}, d2 = {"Lcom/deye/utils/PhotoPickerFileHelper;", "", "()V", "copyUriToCache", "Ljava/io/File;", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "prefix", "", "createLocalMediaFromUri", "Lcom/luck/picture/lib/entity/LocalMedia;", "guessMimeType", "fileName", "queryDisplayName", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class PhotoPickerFileHelper {
    public static final PhotoPickerFileHelper INSTANCE = new PhotoPickerFileHelper();

    private PhotoPickerFileHelper() {
    }

    public final LocalMedia createLocalMediaFromUri(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        File fileCopyUriToCache$default = copyUriToCache$default(this, context, uri, null, 4, null);
        if (fileCopyUriToCache$default == null) {
            return null;
        }
        String type = context.getContentResolver().getType(uri);
        if (type == null) {
            String name = fileCopyUriToCache$default.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            type = guessMimeType(name);
        }
        Intrinsics.checkNotNull(type);
        LocalMedia localMedia = new LocalMedia();
        localMedia.setPath(uri.toString());
        localMedia.setRealPath(fileCopyUriToCache$default.getAbsolutePath());
        localMedia.setSandboxPath(fileCopyUriToCache$default.getAbsolutePath());
        localMedia.setFileName(fileCopyUriToCache$default.getName());
        localMedia.setSize(fileCopyUriToCache$default.length());
        localMedia.setMimeType(type);
        return localMedia;
    }

    public static /* synthetic */ File copyUriToCache$default(PhotoPickerFileHelper photoPickerFileHelper, Context context, Uri uri, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = StubApp.getString2(14409);
        }
        return photoPickerFileHelper.copyUriToCache(context, uri, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005b A[PHI: r0
      0x005b: PHI (r0v18 java.lang.String) = (r0v7 java.lang.String), (r0v20 java.lang.String) binds: [B:19:0x0058, B:16:0x0045] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final File copyUriToCache(Context context, Uri uri, String prefix) {
        String extensionFromMimeType;
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (Intrinsics.areEqual(uri.getScheme(), AmapLocationNetwork.TYPE_OFFLINE_CELL)) {
            String path = uri.getPath();
            if (path != null) {
                return new File(path);
            }
            return null;
        }
        String strQueryDisplayName = queryDisplayName(context, uri);
        if (strQueryDisplayName != null && (extensionFromMimeType = StringsKt.substringAfterLast(strQueryDisplayName, '.', "")) != null) {
            if (!(!StringsKt.isBlank(extensionFromMimeType))) {
                extensionFromMimeType = null;
            }
            if (extensionFromMimeType != null) {
            }
        } else {
            extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(context.getContentResolver().getType(uri));
            str = extensionFromMimeType != null ? extensionFromMimeType : "";
        }
        File fileCreateTempFile = File.createTempFile(prefix, StringsKt.isBlank(str) ? null : StubApp.getString2(1382) + str, context.getCacheDir());
        InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
        if (inputStreamOpenInputStream == null) {
            return null;
        }
        FileOutputStream fileOutputStream = inputStreamOpenInputStream;
        try {
            InputStream inputStream = fileOutputStream;
            fileOutputStream = new FileOutputStream(fileCreateTempFile);
            try {
                ByteStreamsKt.copyTo$default(inputStream, fileOutputStream, 0, 2, null);
                CloseableKt.closeFinally(fileOutputStream, null);
                CloseableKt.closeFinally(fileOutputStream, null);
                return fileCreateTempFile;
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } finally {
            }
        }
    }

    private final String queryDisplayName(Context context, Uri uri) throws IOException {
        String string2 = StubApp.getString2(5075);
        Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{string2}, null, null, null);
        if (cursorQuery != null) {
            Cursor cursor = cursorQuery;
            try {
                Cursor cursor2 = cursor;
                int columnIndex = cursor2.getColumnIndex(string2);
                if (columnIndex != -1 && cursor2.moveToFirst()) {
                    String string = cursor2.getString(columnIndex);
                    CloseableKt.closeFinally(cursor, null);
                    return string;
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursor, null);
            } finally {
            }
        }
        return null;
    }

    private final String guessMimeType(String fileName) {
        String lowerCase = StringsKt.substringAfterLast(fileName, '.', "").toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(lowerCase);
        return mimeTypeFromExtension == null ? StubApp.getString2(4039) : mimeTypeFromExtension;
    }
}
