package com.deye.views;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ObservableScrollView.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001:\u0001#B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\nJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J(\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\bH\u0014J&\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\bJ\u0010\u0010!\u001a\u00020\u00172\b\u0010\"\u001a\u0004\u0018\u00010\u000fR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/deye/views/ObservableScrollView;", "Landroid/widget/ScrollView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mDownPosX", "", "mDownPosY", "mOnScrollChangedListener", "Lcom/deye/views/ObservableScrollView$OnScrollChangedListener;", "rect", "Landroid/graphics/Rect;", "onInterceptTouchEvent", "", "ev", "Landroid/view/MotionEvent;", "onScrollChanged", "", "x", "y", "oldx", "oldy", "setDisAllowRect", "left", "top", "right", "bottom", "setOnScrollChangedListener", "onScrollChangedListener", "OnScrollChangedListener", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class ObservableScrollView extends ScrollView {
    private float mDownPosX;
    private float mDownPosY;
    private OnScrollChangedListener mOnScrollChangedListener;
    private Rect rect;

    /* compiled from: ObservableScrollView.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J2\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H&¨\u0006\u000b"}, d2 = {"Lcom/deye/views/ObservableScrollView$OnScrollChangedListener;", "", "onScrollChanged", "", "scrollView", "Lcom/deye/views/ObservableScrollView;", "x", "", "y", "oldx", "oldy", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface OnScrollChangedListener {
        void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);
    }

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.mOnScrollChangedListener = onScrollChangedListener;
    }

    @Override // android.view.View
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Rect rect;
        Intrinsics.checkNotNullParameter(ev, "ev");
        float x = ev.getX();
        float y = ev.getY();
        int action = ev.getAction();
        if (action == 0) {
            this.mDownPosX = x;
            this.mDownPosY = y;
        } else if (action == 2 && (rect = this.rect) != null && rect.contains((int) ev.getX(), (int) ev.getY())) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public final void setDisAllowRect(int left, int top, int right, int bottom) {
        StringBuilder sbAppend = new StringBuilder(StubApp.getString2(14625)).append(left);
        String string2 = StubApp.getString2(626);
        LogUtil.e(sbAppend.append(string2).append(top).append(string2).append(right).append(string2).append(bottom).append(string2).toString());
        this.rect = new Rect(left, top, right, bottom);
    }
}
