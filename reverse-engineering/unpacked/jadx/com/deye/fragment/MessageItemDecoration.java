package com.deye.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.deye.adapter.MessageListAdapter;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MessageItemDecoration.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J(\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J \u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/deye/fragment/MessageItemDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "mContext", "Landroid/content/Context;", "messageListAdapter", "Lcom/deye/adapter/MessageListAdapter;", "(Landroid/content/Context;Lcom/deye/adapter/MessageListAdapter;)V", "mItemHeaderHeight", "", "mItemHeaderPaint", "Landroid/graphics/Paint;", "mTextPaint", "mTextRect", "Landroid/graphics/Rect;", "getItemOffsets", "", "outRect", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onDrawOver", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class MessageItemDecoration extends RecyclerView.ItemDecoration {
    private final Context mContext;
    private final int mItemHeaderHeight;
    private Paint mItemHeaderPaint;
    private Paint mTextPaint;
    private final Rect mTextRect;
    private final MessageListAdapter messageListAdapter;

    public MessageItemDecoration(Context mContext, MessageListAdapter messageListAdapter) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(messageListAdapter, "messageListAdapter");
        this.mContext = mContext;
        this.messageListAdapter = messageListAdapter;
        Paint paint = new Paint(1);
        this.mTextPaint = paint;
        paint.setTextSize(BaseUtils.sp2px(12.0f));
        this.mTextPaint.setColor(mContext.getResources().getColor(R.color.color_9AA1AA));
        Paint paint2 = new Paint(1);
        this.mItemHeaderPaint = paint2;
        paint2.setColor(mContext.getResources().getColor(R.color.color_F4F5F8));
        this.mItemHeaderHeight = DensityUtil.dp2px(47.0f);
        this.mTextRect = new Rect();
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(outRect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        parent.getChildLayoutPosition(view);
    }

    public void onDraw(Canvas canvas, RecyclerView parent) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(parent, "parent");
        super.onDraw(canvas, parent);
    }

    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        super.onDrawOver(canvas, parent, state);
    }
}
