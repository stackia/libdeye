package com.deye.webview;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.deye.activity.device.base.BaseActivity;
import com.stub.StubApp;
import com.ut.device.AidConstants;
import java.io.File;
import java.io.IOException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SmartServiceWebActivity.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\t\b\u0016\u0018\u0000 %2\u00020\u0001:\u0002%&B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0003J\b\u0010\u000f\u001a\u00020\u000eH\u0003J\"\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0015J\b\u0010\u0017\u001a\u00020\u0011H\u0017J\u0012\u0010\u0018\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0017J-\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u000e\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\t2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016¢\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020\u0011H\u0002J\b\u0010!\u001a\u00020\u0011H\u0002J\b\u0010\"\u001a\u00020\u0011H\u0002J\b\u0010#\u001a\u00020\u0011H\u0002J\b\u0010$\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/deye/webview/SmartServiceWebActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "authorityOfFileProvider", "", "cameraPhotoPath", "cameraVideoPath", "filePathCallback", "Landroid/webkit/ValueCallback;", "", "Landroid/net/Uri;", "webView", "Landroid/webkit/WebView;", "createImageFile", "Ljava/io/File;", "createVideoFile", "onActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "permissions", "grantResults", "", "(I[Ljava/lang/String;[I)V", "openAppSettings", "openFileChooser", "openImageChooser", "openVideoChooser", "showPermissionDeniedDialog", "Companion", "MyWebViewClient", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class SmartServiceWebActivity extends BaseActivity {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 2;
    private static final int CAMERA_VIDEO_PERMISSION_REQUEST_CODE = 3;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final int FILE_CHOOSER_REQUEST_CODE = 1;
    private static final String SERVER_URL = StubApp.getString2(14700);
    private final String authorityOfFileProvider = StubApp.getString2(13788);
    private String cameraPhotoPath;
    private String cameraVideoPath;
    private ValueCallback<Uri[]> filePathCallback;
    private WebView webView;

    static {
        StubApp.interface11(15477);
        INSTANCE = new Companion(null);
    }

    private final native File createImageFile() throws IOException;

    private final native File createVideoFile() throws IOException;

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onCreate$lambda$0(SmartServiceWebActivity smartServiceWebActivity, View view);

    @JvmStatic
    public static final native void open(Context context);

    private final native void openAppSettings();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void openFileChooser();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void openImageChooser();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void openVideoChooser();

    private final native void showPermissionDeniedDialog();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void showPermissionDeniedDialog$lambda$1(SmartServiceWebActivity smartServiceWebActivity, DialogInterface dialogInterface, int i);

    @Deprecated(message = "Deprecated in Java")
    protected native void onActivityResult(int requestCode, int resultCode, Intent data);

    @Deprecated(message = "Deprecated in Java")
    public native void onBackPressed();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    public native void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);

    /* compiled from: SmartServiceWebActivity.kt */
    @Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"com/deye/webview/SmartServiceWebActivity$onCreate$2", "Landroid/webkit/WebChromeClient;", "onShowFileChooser", "", "webView", "Landroid/webkit/WebView;", "filePathCallback", "Landroid/webkit/ValueCallback;", "", "Landroid/net/Uri;", "fileChooserParams", "Landroid/webkit/WebChromeClient$FileChooserParams;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.webview.SmartServiceWebActivity$onCreate$2, reason: invalid class name */
    public static final class AnonymousClass2 extends WebChromeClient {
        AnonymousClass2() {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            Intrinsics.checkNotNullParameter(webView, "webView");
            Intrinsics.checkNotNullParameter(filePathCallback, "filePathCallback");
            Intrinsics.checkNotNullParameter(fileChooserParams, "fileChooserParams");
            SmartServiceWebActivity.this.filePathCallback = filePathCallback;
            String[] acceptTypes = fileChooserParams.getAcceptTypes();
            Intrinsics.checkNotNull(acceptTypes);
            if (!(acceptTypes.length == 0)) {
                String str = acceptTypes[0];
                boolean zAreEqual = Intrinsics.areEqual(str, "video/*");
                String string2 = StubApp.getString2(5140);
                if (zAreEqual || Intrinsics.areEqual(str, string2)) {
                    Context context = (Context) SmartServiceWebActivity.this;
                    String string22 = StubApp.getString2(10138);
                    if (ContextCompat.checkSelfPermission(context, string22) != 0) {
                        ActivityCompat.requestPermissions((Activity) SmartServiceWebActivity.this, new String[]{string22}, Intrinsics.areEqual(str, "video/*") ? 3 : 2);
                        return true;
                    }
                    if (Intrinsics.areEqual(str, string2)) {
                        SmartServiceWebActivity.this.openImageChooser();
                        return true;
                    }
                    if (Intrinsics.areEqual(str, "video/*")) {
                        SmartServiceWebActivity.this.openVideoChooser();
                        return true;
                    }
                }
            }
            SmartServiceWebActivity.this.openFileChooser();
            return true;
        }
    }

    /* compiled from: SmartServiceWebActivity.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\bH\u0017¨\u0006\u000e"}, d2 = {"Lcom/deye/webview/SmartServiceWebActivity$MyWebViewClient;", "Landroid/webkit/WebViewClient;", "(Lcom/deye/webview/SmartServiceWebActivity;)V", "downloadImage", "", "context", "Landroid/content/Context;", "url", "", "isImageUrl", "", "shouldOverrideUrlLoading", "view", "Landroid/webkit/WebView;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private final class MyWebViewClient extends WebViewClient {
        public MyWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        @Deprecated(message = "Deprecated in Java")
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(url, "url");
            if (!isImageUrl(url)) {
                return false;
            }
            downloadImage((Context) SmartServiceWebActivity.this, url);
            return true;
        }

        private final boolean isImageUrl(String url) {
            if (!StringsKt.endsWith$default(url, StubApp.getString2(5241), false, 2, (Object) null) && !StringsKt.endsWith$default(url, StubApp.getString2(13781), false, 2, (Object) null) && !StringsKt.endsWith$default(url, StubApp.getString2(4824), false, 2, (Object) null) && !StringsKt.endsWith$default(url, StubApp.getString2(5178), false, 2, (Object) null)) {
                String str = url;
                if (!StringsKt.contains$default(str, StubApp.getString2(14696), false, 2, (Object) null) && !StringsKt.contains$default(str, StubApp.getString2(14697), false, 2, (Object) null) && !StringsKt.contains$default(str, StubApp.getString2(14698), false, 2, (Object) null) && !StringsKt.contains$default(str, StubApp.getString2(14699), false, 2, (Object) null)) {
                    return false;
                }
            }
            return true;
        }

        private final void downloadImage(Context context, String url) {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            String str = Environment.DIRECTORY_DOWNLOADS;
            String strSubstring = url.substring(StringsKt.lastIndexOf$default(url, StubApp.getString2(AidConstants.EVENT_REQUEST_FAILED), 0, false, 6, (Object) null) + 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            request.setDestinationInExternalPublicDir(str, strSubstring);
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(1);
            Object systemService = context.getSystemService(StubApp.getString2(14694));
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.DownloadManager");
            ((DownloadManager) systemService).enqueue(request);
            Toast.makeText(context, StubApp.getString2(14695), 0).show();
        }
    }

    /* compiled from: SmartServiceWebActivity.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/deye/webview/SmartServiceWebActivity$Companion;", "", "()V", "CAMERA_PERMISSION_REQUEST_CODE", "", "CAMERA_VIDEO_PERMISSION_REQUEST_CODE", "FILE_CHOOSER_REQUEST_CODE", "SERVER_URL", "", "open", "", "context", "Landroid/content/Context;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void open(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, (Class<?>) SmartServiceWebActivity.class));
        }
    }
}
