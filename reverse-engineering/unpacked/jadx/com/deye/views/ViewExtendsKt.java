package com.deye.views;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.core.view.ViewGroupKt;
import com.deye.views.button.SwitchButton;
import com.deye.views.control.DelayedShutdownControlView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewExtends.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u000b"}, d2 = {"setAvailable", "", "Landroid/view/View;", "available", "", "Landroid/widget/RelativeLayout;", "Lcom/deye/views/ArcPanel;", "Lcom/deye/views/HumidifierArcPanel;", "Lcom/deye/views/HumidifierWindSpeedParentView;", "Lcom/deye/views/ItemControlView;", "Lcom/deye/views/control/DelayedShutdownControlView;", "app_homeRelease"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class ViewExtendsKt {
    public static final void setAvailable(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setAlpha(z ? 1.0f : 0.4f);
    }

    public static final void setAvailable(RelativeLayout relativeLayout, boolean z) {
        Intrinsics.checkNotNullParameter(relativeLayout, "<this>");
        relativeLayout.setAlpha(z ? 1.0f : 0.4f);
        for (View view : ViewGroupKt.getChildren(relativeLayout)) {
            if (view instanceof SwitchButton) {
                ((SwitchButton) view).setEnabled(z);
            }
        }
    }

    public static final void setAvailable(ItemControlView itemControlView, boolean z) {
        Intrinsics.checkNotNullParameter(itemControlView, "<this>");
        itemControlView.setAlpha(z ? 1.0f : 0.4f);
        itemControlView.setEnabled(z);
    }

    public static final void setAvailable(DelayedShutdownControlView delayedShutdownControlView, boolean z) {
        Intrinsics.checkNotNullParameter(delayedShutdownControlView, "<this>");
        delayedShutdownControlView.setAlpha(z ? 1.0f : 0.4f);
        delayedShutdownControlView.setEnabled(z);
    }

    public static final void setAvailable(HumidifierArcPanel humidifierArcPanel, boolean z) {
        Intrinsics.checkNotNullParameter(humidifierArcPanel, "<this>");
        humidifierArcPanel.setAlpha(z ? 1.0f : 0.4f);
        humidifierArcPanel.setEnabled(z);
    }

    public static final void setAvailable(ArcPanel arcPanel, boolean z) {
        Intrinsics.checkNotNullParameter(arcPanel, "<this>");
        arcPanel.setAlpha(z ? 1.0f : 0.4f);
        arcPanel.setEnabled(z);
    }

    public static final void setAvailable(HumidifierWindSpeedParentView humidifierWindSpeedParentView, boolean z) {
        Intrinsics.checkNotNullParameter(humidifierWindSpeedParentView, "<this>");
        humidifierWindSpeedParentView.setAlpha(z ? 1.0f : 0.4f);
        humidifierWindSpeedParentView.setEnabled(z);
    }
}
