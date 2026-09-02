package com.deye.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class NoInterceptCoordinatorLayout extends CoordinatorLayout {
    public NoInterceptCoordinatorLayout(Context context) {
        super(context);
    }

    public NoInterceptCoordinatorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 2) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }
}
