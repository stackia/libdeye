package com.deye.adapter;

import android.view.View;
import androidx.databinding.BindingAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BindingAdapter.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"bindIsGone", "", "view", "Landroid/view/View;", "isGone", "", "app_homeRelease"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class BindingAdapterKt {
    @BindingAdapter({"isGone"})
    public static final void bindIsGone(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setVisibility(z ? 8 : 4);
    }
}
