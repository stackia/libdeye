package com.deye.fragment;

import android.content.Context;
import androidx.fragment.app.Fragment;
import com.deye.utils.UmUtils;
import com.deye.views.dialog.LoadingDialog;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseFragment.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u001a\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000bJ\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/deye/fragment/BaseFragment;", "Landroidx/fragment/app/Fragment;", "()V", "loadingDialog", "Lcom/deye/views/dialog/LoadingDialog;", "hideLoading", "", "showLoading", "context", "Landroid/content/Context;", "message", "", "trackPageEndEvent", "page", "trackPageStartEvent", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class BaseFragment extends Fragment {
    private LoadingDialog loadingDialog;

    public static /* synthetic */ void showLoading$default(BaseFragment baseFragment, Context context, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException(StubApp.getString2(13555));
        }
        if ((i & 2) != 0) {
            str = null;
        }
        baseFragment.showLoading(context, str);
    }

    public final void showLoading(Context context, String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        LoadingDialog loadingDialog = new LoadingDialog(context, message);
        this.loadingDialog = loadingDialog;
        loadingDialog.show();
    }

    public final void hideLoading() {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            return;
        }
        loadingDialog.dismiss();
    }

    public final void trackPageStartEvent(String page) {
        Intrinsics.checkNotNullParameter(page, "page");
        UmUtils.INSTANCE.trackPageStartEvent(page);
    }

    public final void trackPageEndEvent(String page) {
        Intrinsics.checkNotNullParameter(page, "page");
        UmUtils.INSTANCE.trackPageEndEvent(page);
    }
}
