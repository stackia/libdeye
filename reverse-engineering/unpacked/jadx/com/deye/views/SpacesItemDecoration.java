package com.deye.views;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int halfSpace;

    public SpacesItemDecoration(int i) {
        this.halfSpace = i / 2;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (view.getLayoutParams().getSpanIndex() == 0) {
            rect.left = 0;
            rect.right = this.halfSpace;
        } else {
            rect.left = this.halfSpace;
            rect.right = 0;
        }
        rect.top = this.halfSpace;
        rect.bottom = this.halfSpace;
    }
}
