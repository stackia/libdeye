package com.deye.webview;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.databinding.ActivityVideoBinding;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.stub.StubApp;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoActivity.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\u000bH\u0016J\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u000bH\u0014J\b\u0010\u0014\u001a\u00020\u000bH\u0014J\b\u0010\u0015\u001a\u00020\u000bH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/deye/webview/VideoActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "binding", "Lcom/mxchipapp/databinding/ActivityVideoBinding;", "isPause", "", "isPlay", "orientationUtils", "Lcom/shuyu/gsyvideoplayer/utils/OrientationUtils;", "initVideoView", "", "onBackPressed", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class VideoActivity extends BaseActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final String KEY_TITLE = StubApp.getString2(14682);
    public static final String KEY_URL = StubApp.getString2(14681);
    private ActivityVideoBinding binding;
    private boolean isPause;
    private boolean isPlay;
    private OrientationUtils orientationUtils;

    static {
        StubApp.interface11(15483);
        INSTANCE = new Companion(null);
    }

    private final native void initVideoView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initVideoView$lambda$0(View view, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initVideoView$lambda$1(VideoActivity videoActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initVideoView$lambda$2(VideoActivity videoActivity, View view);

    public native void onBackPressed();

    public native void onConfigurationChanged(Configuration newConfig);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();

    protected native void onPause();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onResume();

    /* compiled from: VideoActivity.kt */
    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\bH\u0016¢\u0006\u0002\u0010\tJ)\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\u000b"}, d2 = {"com/deye/webview/VideoActivity$initVideoView$1", "Lcom/shuyu/gsyvideoplayer/listener/GSYSampleCallBack;", "onPrepared", "", "url", "", "objects", "", "", "(Ljava/lang/String;[Ljava/lang/Object;)V", "onQuitFullscreen", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.webview.VideoActivity$initVideoView$1, reason: invalid class name */
    public static final class AnonymousClass1 extends GSYSampleCallBack {
        AnonymousClass1() {
        }

        @Override // com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack, com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
        public void onPrepared(String url, Object... objects) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(objects, "objects");
            super.onPrepared(url, Arrays.copyOf(objects, objects.length));
            VideoActivity.this.isPlay = true;
        }

        @Override // com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack, com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
        public void onQuitFullscreen(String url, Object... objects) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(objects, "objects");
            super.onQuitFullscreen(url, Arrays.copyOf(objects, objects.length));
            OrientationUtils orientationUtils = VideoActivity.this.orientationUtils;
            if (orientationUtils != null) {
                orientationUtils.backToProtVideo();
            }
        }
    }

    /* compiled from: VideoActivity.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/deye/webview/VideoActivity$Companion;", "", "()V", "KEY_TITLE", "", "KEY_URL", "open", "", "context", "Landroid/content/Context;", "url", "title", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ void open$default(Companion companion, Context context, String str, String str2, int i, Object obj) {
            if ((i & 4) != 0) {
                str2 = "";
            }
            companion.open(context, str, str2);
        }

        public final void open(Context context, String url, String title) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(title, "title");
            Intent intent = new Intent(context, (Class<?>) VideoActivity.class);
            intent.putExtra(StubApp.getString2(14681), url);
            intent.putExtra(StubApp.getString2(14682), title);
            context.startActivity(intent);
        }
    }
}
