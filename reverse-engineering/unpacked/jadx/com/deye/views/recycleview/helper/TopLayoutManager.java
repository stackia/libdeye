package com.deye.views.recycleview.helper;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class TopLayoutManager extends LinearLayoutManager {
    public TopLayoutManager(Context context) {
        super(context);
    }

    public TopLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
    }

    public TopLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        TopSmoothScroller topSmoothScroller = new TopSmoothScroller(recyclerView.getContext());
        topSmoothScroller.setTargetPosition(i);
        startSmoothScroll(topSmoothScroller);
    }

    private static class TopSmoothScroller extends LinearSmoothScroller {
        public int calculateDtToFit(int i, int i2, int i3, int i4, int i5) {
            return i3 - i;
        }

        TopSmoothScroller(Context context) {
            super(context);
        }
    }
}
