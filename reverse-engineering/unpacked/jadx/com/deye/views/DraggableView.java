package com.deye.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import com.deye.activity.device.base.BaseActivity;
import com.deye.webview.SmartServiceWebActivity;
import com.mxchipapp.R;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.stub.StubApp;
import com.zhouyou.view.seekbar.SignUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: DraggableView.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 .2\u00020\u0001:\u0002./B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001aH\u0002J\b\u0010!\u001a\u00020\u001fH\u0016J\u0010\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$H\u0002J\u0010\u0010&\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020$H\u0016J(\u0010(\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u0007H\u0014J\u0010\u0010-\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$H\u0017R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/deye/views/DraggableView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "childImageRightView", "Landroid/widget/ImageView;", "childImageView", "childImageViewContainer", "childViewHeight", "childViewWidth", "clickTimeout", "", "containerHeight", "containerWidth", "downTime", "downX", "", "downY", "dragHelper", "Landroidx/customview/widget/ViewDragHelper;", "isDragging", "", "isTouchInChildViewOnDown", "mCanDrag", "touchSlop", "changeChildViewImage", "", "isLeft", "computeScroll", "dispatchTouchEvent", "event", "Landroid/view/MotionEvent;", "isTouchInsideChildView", "onInterceptTouchEvent", "ev", "onSizeChanged", "w", "h", "oldw", "oldh", "onTouchEvent", "Companion", "DragCallback", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DraggableView extends FrameLayout {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private ImageView childImageRightView;
    private ImageView childImageView;
    private FrameLayout childImageViewContainer;
    private int childViewHeight;
    private int childViewWidth;
    private final long clickTimeout;
    private int containerHeight;
    private int containerWidth;
    private long downTime;
    private float downX;
    private float downY;
    private final ViewDragHelper dragHelper;
    private boolean isDragging;
    private boolean isTouchInChildViewOnDown;
    private boolean mCanDrag;
    private int touchSlop;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DraggableView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DraggableView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @JvmStatic
    public static final View addTo(BaseActivity baseActivity) {
        return INSTANCE.addTo(baseActivity);
    }

    @JvmStatic
    public static final View addTo(BaseActivity baseActivity, ViewGroup viewGroup) {
        return INSTANCE.addTo(baseActivity, viewGroup);
    }

    public /* synthetic */ DraggableView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DraggableView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.childViewWidth = DensityUtil.dp2px(74.65f);
        this.childViewHeight = DensityUtil.dp2px(66.1f);
        this.clickTimeout = ViewConfiguration.getTapTimeout();
        this.touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        ViewDragHelper viewDragHelperCreate = ViewDragHelper.create(this, 1.0f, new DragCallback());
        Intrinsics.checkNotNullExpressionValue(viewDragHelperCreate, "create(...)");
        this.dragHelper = viewDragHelperCreate;
        this.childImageViewContainer = new FrameLayout(context);
        ImageView imageView = new ImageView(context);
        this.childImageView = imageView;
        imageView.setImageResource(R.drawable.icon_smart_service_draggable);
        ImageView imageView2 = new ImageView(context);
        this.childImageRightView = imageView2;
        imageView2.setImageResource(R.drawable.icon_smart_service_draggable_right);
        this.childImageRightView.setVisibility(4);
        FrameLayout frameLayout = this.childImageViewContainer;
        ImageView imageView3 = this.childImageView;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -1);
        layoutParams.gravity = 8388611;
        layoutParams.setMarginStart(SignUtils.dp2px(10));
        Unit unit = Unit.INSTANCE;
        frameLayout.addView(imageView3, layoutParams);
        FrameLayout frameLayout2 = this.childImageViewContainer;
        ImageView imageView4 = this.childImageRightView;
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -1);
        layoutParams2.gravity = 8388611;
        layoutParams2.setMarginStart(SignUtils.dp2px(26));
        Unit unit2 = Unit.INSTANCE;
        frameLayout2.addView(imageView4, layoutParams2);
        addView(this.childImageViewContainer, new FrameLayout.LayoutParams(this.childViewWidth, this.childViewHeight));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeChildViewImage(boolean isLeft) {
        if (isLeft) {
            this.childImageView.setVisibility(0);
            this.childImageRightView.setVisibility(4);
        } else {
            this.childImageView.setVisibility(4);
            this.childImageRightView.setVisibility(0);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.containerWidth = w;
        this.containerHeight = h;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        event.getX();
        event.getY();
        int action = event.getAction();
        if (action == 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
            isTouchInsideChildView(event);
        } else if (action == 2) {
            getParent().requestDisallowInterceptTouchEvent(this.mCanDrag);
        }
        return super.dispatchTouchEvent(event);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        int action = ev.getAction();
        String string2 = StubApp.getString2(14570);
        if (action == 0) {
            Log.w(string2, StubApp.getString2(14582));
        } else if (action == 1) {
            Log.w(string2, StubApp.getString2(14581));
        } else if (action == 2) {
            Log.w(string2, StubApp.getString2(14580));
        } else if (action == 3) {
            Log.w(string2, StubApp.getString2(14579));
        }
        boolean zShouldInterceptTouchEvent = this.dragHelper.shouldInterceptTouchEvent(ev);
        Log.w(string2, StubApp.getString2(14583) + zShouldInterceptTouchEvent);
        return zShouldInterceptTouchEvent;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        int action = event.getAction();
        String string2 = StubApp.getString2(14570);
        if (action == 0) {
            Log.w(string2, StubApp.getString2(14590));
            this.downX = event.getX();
            this.downY = event.getY();
            this.downTime = System.currentTimeMillis();
            this.isTouchInChildViewOnDown = isTouchInsideChildView(event);
        } else if (action == 1) {
            Log.w(string2, StubApp.getString2(14586));
            boolean z = false;
            boolean z2 = System.currentTimeMillis() - this.downTime <= this.clickTimeout;
            float x = event.getX() - this.downX;
            float y = event.getY() - this.downY;
            float f = (x * x) + (y * y);
            int i = this.touchSlop;
            boolean z3 = f > ((float) (i * i));
            if (this.isTouchInChildViewOnDown && z2 && !z3) {
                z = true;
            }
            Log.d(string2, StubApp.getString2(14587) + z3 + StubApp.getString2(14588) + z2);
            if (z) {
                Log.d(string2, StubApp.getString2(14589));
                SmartServiceWebActivity.Companion companion = SmartServiceWebActivity.INSTANCE;
                Context context = getContext();
                Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                companion.open(context);
                return true;
            }
        } else if (action == 2) {
            Log.w(string2, StubApp.getString2(14585));
        } else if (action == 3) {
            Log.w(string2, StubApp.getString2(14584));
        }
        this.dragHelper.processTouchEvent(event);
        return this.isDragging;
    }

    private final boolean isTouchInsideChildView(MotionEvent event) {
        int i;
        int[] iArr = new int[2];
        this.childImageViewContainer.getLocationOnScreen(iArr);
        boolean z = false;
        int i2 = iArr[0];
        int i3 = iArr[1];
        int width = this.childImageViewContainer.getWidth() + i2;
        int height = this.childImageViewContainer.getHeight() + i3;
        float rawX = event.getRawX();
        float rawY = event.getRawY();
        int i4 = (int) rawX;
        if (i2 <= i4 && i4 <= width && i3 <= (i = (int) rawY) && i <= height) {
            z = true;
        }
        this.mCanDrag = z;
        Log.e(StubApp.getString2(14578), String.valueOf(z));
        return this.mCanDrag;
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* compiled from: DraggableView.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0016J \u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0004H\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0004H\u0016J0\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0016J \u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0004H\u0016¨\u0006\u001c"}, d2 = {"Lcom/deye/views/DraggableView$DragCallback;", "Landroidx/customview/widget/ViewDragHelper$Callback;", "(Lcom/deye/views/DraggableView;)V", "clampViewPositionHorizontal", "", "child", "Landroid/view/View;", "left", "dx", "clampViewPositionVertical", "top", "dy", "onViewCaptured", "", "capturedChild", "activePointerId", "onViewDragStateChanged", "state", "onViewPositionChanged", "changedView", "onViewReleased", "releasedChild", "xvel", "", "yvel", "tryCaptureView", "", "pointerId", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private final class DragCallback extends ViewDragHelper.Callback {
        public DragCallback() {
        }

        public boolean tryCaptureView(View child, int pointerId) {
            Intrinsics.checkNotNullParameter(child, "child");
            boolean zAreEqual = Intrinsics.areEqual(DraggableView.this.childImageViewContainer, child);
            Log.d(StubApp.getString2(14570), StubApp.getString2(14577) + zAreEqual);
            return zAreEqual;
        }

        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Intrinsics.checkNotNullParameter(child, "child");
            if (left < DraggableView.this.containerWidth - DraggableView.this.childViewWidth) {
                DraggableView.this.changeChildViewImage(true);
            } else {
                DraggableView.this.changeChildViewImage(false);
            }
            return RangesKt.coerceIn(left, 0, DraggableView.this.containerWidth - child.getWidth());
        }

        public int clampViewPositionVertical(View child, int top, int dy) {
            Intrinsics.checkNotNullParameter(child, "child");
            return RangesKt.coerceIn(top, 0, DraggableView.this.containerHeight - child.getHeight());
        }

        public void onViewCaptured(View capturedChild, int activePointerId) {
            Intrinsics.checkNotNullParameter(capturedChild, "capturedChild");
            super.onViewCaptured(capturedChild, activePointerId);
            Log.d(StubApp.getString2(14570), StubApp.getString2(14571));
        }

        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            Intrinsics.checkNotNullParameter(releasedChild, "releasedChild");
            int width = releasedChild.getWidth();
            int left = releasedChild.getLeft();
            int top = releasedChild.getTop();
            boolean z = left < (DraggableView.this.containerWidth - width) / 2;
            DraggableView.this.dragHelper.settleCapturedViewAt(z ? 0 : DraggableView.this.containerWidth - width, top);
            DraggableView.this.changeChildViewImage(z);
            DraggableView.this.invalidate();
            Log.d(StubApp.getString2(14570), StubApp.getString2(14576));
        }

        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
            String string2 = StubApp.getString2(14570);
            if (state == 0) {
                Log.d(string2, StubApp.getString2(14574));
                DraggableView.this.isDragging = false;
            } else if (state == 1) {
                Log.d(string2, StubApp.getString2(14573));
                DraggableView.this.isDragging = true;
            } else {
                if (state != 2) {
                    return;
                }
                Log.d(string2, StubApp.getString2(14572));
                DraggableView.this.isDragging = false;
            }
        }

        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            Intrinsics.checkNotNullParameter(changedView, "changedView");
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            Log.d(StubApp.getString2(14570), StubApp.getString2(14575));
            changedView.layout(left, top, changedView.getWidth() + left, changedView.getHeight() + top);
        }
    }

    /* compiled from: DraggableView.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007¨\u0006\t"}, d2 = {"Lcom/deye/views/DraggableView$Companion;", "", "()V", "addTo", "Landroid/view/View;", "activity", "Lcom/deye/activity/device/base/BaseActivity;", "parentViewGroup", "Landroid/view/ViewGroup;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final View addTo(BaseActivity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            return addTo$default(this, activity, null, 2, null);
        }

        private Companion() {
        }

        public static /* synthetic */ View addTo$default(Companion companion, BaseActivity baseActivity, ViewGroup viewGroup, int i, Object obj) {
            if ((i & 2) != 0) {
                viewGroup = null;
            }
            return companion.addTo(baseActivity, viewGroup);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        public final View addTo(BaseActivity activity, ViewGroup parentViewGroup) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            DraggableView draggableView = new DraggableView((Context) activity, null, 0, 6, null);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(DensityUtil.dp2px(144.0f), DensityUtil.dp2px(508.0f), 8388613);
            layoutParams.topMargin = DensityUtil.dp2px(49.0f) + activity.getStatusBarHeight();
            layoutParams.rightMargin = -DensityUtil.dp2px(20.0f);
            draggableView.setLayoutParams(layoutParams);
            if (parentViewGroup == null) {
                View decorView = activity.getWindow().getDecorView();
                Intrinsics.checkNotNull(decorView, "null cannot be cast to non-null type android.widget.FrameLayout");
                parentViewGroup = (FrameLayout) decorView;
            }
            DraggableView draggableView2 = draggableView;
            parentViewGroup.addView(draggableView2);
            draggableView.setVisibility(4);
            return draggableView2;
        }
    }
}
