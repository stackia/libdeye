package com.deye.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.stub.StubApp;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AgentWebActivity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\u0006\u0010\t\u001a\u00020\u0006¨\u0006\u000b"}, d2 = {"Lcom/deye/webview/AgentWebActivity;", "Lcom/deye/webview/BaseWebActivity;", "()V", "getUrl", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "test", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class AgentWebActivity extends BaseWebActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final String KEY_SHOW_TITLE = StubApp.getString2(14683);
    public static final String KEY_TITLE = StubApp.getString2(14682);
    public static final String KEY_URL = StubApp.getString2(14681);

    static {
        StubApp.interface11(15463);
        INSTANCE = new Companion(null);
    }

    @JvmStatic
    public static final native void open(Context context, String str, String str2);

    @Override // com.deye.webview.BaseWebActivity
    public native String getUrl();

    @Override // com.deye.webview.BaseWebActivity
    protected native void onCreate(Bundle savedInstanceState);

    public final native void test();

    /* compiled from: AgentWebActivity.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ\"\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/deye/webview/AgentWebActivity$Companion;", "", "()V", "KEY_SHOW_TITLE", "", "KEY_TITLE", "KEY_URL", "open", "", "context", "Landroid/content/Context;", "url", "showTitle", "", "title", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
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

        @JvmStatic
        public final void open(Context context, String url, String title) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(title, "title");
            Intent intent = new Intent(context, (Class<?>) AgentWebActivity.class);
            intent.putExtra(StubApp.getString2(14681), url);
            intent.putExtra(StubApp.getString2(14682), title);
            intent.addFlags(CommonNetImpl.FLAG_AUTH);
            context.startActivity(intent);
        }

        public final void open(Context context, String url, boolean showTitle) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(url, "url");
            Intent intent = new Intent(context, (Class<?>) AgentWebActivity.class);
            intent.putExtra(StubApp.getString2(14681), url);
            intent.putExtra(StubApp.getString2(14683), showTitle);
            intent.addFlags(CommonNetImpl.FLAG_AUTH);
            context.startActivity(intent);
        }
    }
}
