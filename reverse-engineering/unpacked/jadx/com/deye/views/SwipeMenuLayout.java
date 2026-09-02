package com.deye.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import com.mxchipapp.R;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class SwipeMenuLayout extends ViewGroup {
    private static final String TAG = StubApp.getString2(14643);
    private static boolean isTouching;
    private static SwipeMenuLayout mViewCache;
    private Log LogUtils;
    private boolean iosInterceptFlag;
    private boolean isExpand;
    private boolean isIos;
    private boolean isLeftSwipe;
    private boolean isSwipeEnable;
    private boolean isUnMoved;
    private boolean isUserSwiped;
    private ValueAnimator mCloseAnim;
    private View mContentView;
    private ValueAnimator mExpandAnim;
    private PointF mFirstP;
    private int mHeight;
    private PointF mLastP;
    private int mLimit;
    private int mMaxVelocity;
    private int mPointerId;
    private int mRightMenuWidths;
    private int mScaleTouchSlop;
    private VelocityTracker mVelocityTracker;

    public SwipeMenuLayout(Context context) {
        this(context, null);
    }

    public SwipeMenuLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SwipeMenuLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLastP = new PointF();
        this.isUnMoved = true;
        this.mFirstP = new PointF();
        init(context, attributeSet, i);
    }

    public boolean isSwipeEnable() {
        return this.isSwipeEnable;
    }

    public void setSwipeEnable(boolean z) {
        this.isSwipeEnable = z;
    }

    public boolean isIos() {
        return this.isIos;
    }

    public SwipeMenuLayout setIos(boolean z) {
        this.isIos = z;
        return this;
    }

    public boolean isLeftSwipe() {
        return this.isLeftSwipe;
    }

    public SwipeMenuLayout setLeftSwipe(boolean z) {
        this.isLeftSwipe = z;
        return this;
    }

    public static SwipeMenuLayout getViewCache() {
        return mViewCache;
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        this.mScaleTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMaxVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        this.isSwipeEnable = true;
        this.isIos = true;
        this.isLeftSwipe = true;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SwipeMenuLayout, i, 0);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i2);
            if (index == 2) {
                this.isSwipeEnable = typedArrayObtainStyledAttributes.getBoolean(index, true);
            } else if (index == 0) {
                this.isIos = typedArrayObtainStyledAttributes.getBoolean(index, true);
            } else if (index == 1) {
                this.isLeftSwipe = typedArrayObtainStyledAttributes.getBoolean(index, true);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setClickable(true);
        this.mRightMenuWidths = 0;
        this.mHeight = 0;
        int childCount = getChildCount();
        boolean z = View.MeasureSpec.getMode(i2) != 1073741824;
        int measuredWidth = 0;
        boolean z2 = false;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            childAt.setClickable(true);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i, i2);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                this.mHeight = Math.max(this.mHeight, childAt.getMeasuredHeight());
                if (z && marginLayoutParams.height == -1) {
                    z2 = true;
                }
                if (i3 > 0) {
                    this.mRightMenuWidths += childAt.getMeasuredWidth();
                } else {
                    this.mContentView = childAt;
                    measuredWidth = childAt.getMeasuredWidth();
                }
            }
        }
        setMeasuredDimension(getPaddingLeft() + getPaddingRight() + measuredWidth, this.mHeight + getPaddingTop() + getPaddingBottom());
        this.mLimit = (this.mRightMenuWidths * 4) / 10;
        if (z2) {
            forceUniformHeight(childCount, i);
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    private void forceUniformHeight(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                if (marginLayoutParams.height == -1) {
                    int i4 = marginLayoutParams.width;
                    marginLayoutParams.width = childAt.getMeasuredWidth();
                    measureChildWithMargins(childAt, i2, 0, iMakeMeasureSpec, 0);
                    marginLayoutParams.width = i4;
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingLeft2 = getPaddingLeft();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                if (i5 == 0 || this.isLeftSwipe) {
                    childAt.layout(paddingLeft, getPaddingTop(), childAt.getMeasuredWidth() + paddingLeft, getPaddingTop() + childAt.getMeasuredHeight());
                    int measuredWidth = childAt.getMeasuredWidth();
                    paddingLeft += measuredWidth;
                } else {
                    childAt.layout(paddingLeft2 - childAt.getMeasuredWidth(), getPaddingTop(), paddingLeft2, getPaddingTop() + childAt.getMeasuredHeight());
                    paddingLeft2 -= childAt.getMeasuredWidth();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0096  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.isSwipeEnable) {
            acquireVelocityTracker(motionEvent);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            int action = motionEvent.getAction();
            if (action == 0) {
                this.isUserSwiped = false;
                this.isUnMoved = true;
                this.iosInterceptFlag = false;
                if (isTouching) {
                    return false;
                }
                isTouching = true;
                this.mLastP.set(motionEvent.getRawX(), motionEvent.getRawY());
                this.mFirstP.set(motionEvent.getRawX(), motionEvent.getRawY());
                SwipeMenuLayout swipeMenuLayout = mViewCache;
                if (swipeMenuLayout != null) {
                    if (swipeMenuLayout != this) {
                        swipeMenuLayout.smoothClose();
                        this.iosInterceptFlag = this.isIos;
                    }
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                this.mPointerId = motionEvent.getPointerId(0);
            } else if (action == 1) {
                if (Math.abs(motionEvent.getRawX() - this.mFirstP.x) > this.mScaleTouchSlop) {
                    this.isUserSwiped = true;
                }
                if (!this.iosInterceptFlag) {
                    velocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
                    float xVelocity = velocityTracker.getXVelocity(this.mPointerId);
                    if (Math.abs(xVelocity) > 1000.0f) {
                        if (xVelocity < -1000.0f) {
                            if (this.isLeftSwipe) {
                                smoothExpand();
                            } else {
                                smoothClose();
                            }
                        } else if (this.isLeftSwipe) {
                            smoothClose();
                        } else {
                            smoothExpand();
                        }
                    } else if (Math.abs(getScrollX()) > this.mLimit) {
                        smoothExpand();
                    } else {
                        smoothClose();
                    }
                }
                releaseVelocityTracker();
                isTouching = false;
            } else if (action != 2) {
                if (action == 3) {
                }
            } else if (!this.iosInterceptFlag) {
                float rawX = this.mLastP.x - motionEvent.getRawX();
                if (Math.abs(rawX) > 10.0f || Math.abs(getScrollX()) > 10) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                if (Math.abs(rawX) > this.mScaleTouchSlop) {
                    this.isUnMoved = false;
                }
                scrollBy((int) rawX, 0);
                if (this.isLeftSwipe) {
                    if (getScrollX() < 0) {
                        scrollTo(0, 0);
                    }
                    int scrollX = getScrollX();
                    int i = this.mRightMenuWidths;
                    if (scrollX > i) {
                        scrollTo(i, 0);
                    }
                } else {
                    int scrollX2 = getScrollX();
                    int i2 = this.mRightMenuWidths;
                    if (scrollX2 < (-i2)) {
                        scrollTo(-i2, 0);
                    }
                    if (getScrollX() > 0) {
                        scrollTo(0, 0);
                    }
                }
                this.mLastP.set(motionEvent.getRawX(), motionEvent.getRawY());
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.isSwipeEnable) {
            int action = motionEvent.getAction();
            if (action == 1) {
                if (this.isLeftSwipe) {
                    if (getScrollX() > this.mScaleTouchSlop && motionEvent.getX() < getWidth() - getScrollX()) {
                        if (this.isUnMoved) {
                            smoothClose();
                        }
                        return true;
                    }
                } else if ((-getScrollX()) > this.mScaleTouchSlop && motionEvent.getX() > (-getScrollX())) {
                    if (this.isUnMoved) {
                        smoothClose();
                    }
                    return true;
                }
                if (this.isUserSwiped) {
                    return true;
                }
            } else if (action == 2 && Math.abs(motionEvent.getRawX() - this.mFirstP.x) > this.mScaleTouchSlop) {
                return true;
            }
            if (this.iosInterceptFlag) {
                return true;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void smoothExpand() {
        mViewCache = this;
        View view = this.mContentView;
        if (view != null) {
            view.setLongClickable(false);
        }
        cancelAnim();
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(getScrollX(), this.isLeftSwipe ? this.mRightMenuWidths : -this.mRightMenuWidths);
        this.mExpandAnim = valueAnimatorOfInt;
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.deye.views.SwipeMenuLayout.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SwipeMenuLayout.this.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
            }
        });
        this.mExpandAnim.setInterpolator(new OvershootInterpolator());
        this.mExpandAnim.addListener(new AnimatorListenerAdapter() { // from class: com.deye.views.SwipeMenuLayout.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SwipeMenuLayout.this.isExpand = true;
            }
        });
        this.mExpandAnim.setDuration(300L).start();
    }

    private void cancelAnim() {
        ValueAnimator valueAnimator = this.mCloseAnim;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mCloseAnim.cancel();
        }
        ValueAnimator valueAnimator2 = this.mExpandAnim;
        if (valueAnimator2 == null || !valueAnimator2.isRunning()) {
            return;
        }
        this.mExpandAnim.cancel();
    }

    public void smoothClose() {
        mViewCache = null;
        View view = this.mContentView;
        if (view != null) {
            view.setLongClickable(true);
        }
        cancelAnim();
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(getScrollX(), 0);
        this.mCloseAnim = valueAnimatorOfInt;
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.deye.views.SwipeMenuLayout.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SwipeMenuLayout.this.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
            }
        });
        this.mCloseAnim.setInterpolator(new AccelerateInterpolator());
        this.mCloseAnim.addListener(new AnimatorListenerAdapter() { // from class: com.deye.views.SwipeMenuLayout.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SwipeMenuLayout.this.isExpand = false;
            }
        });
        this.mCloseAnim.setDuration(300L).start();
    }

    private void acquireVelocityTracker(MotionEvent motionEvent) {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
    }

    private void releaseVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        SwipeMenuLayout swipeMenuLayout = mViewCache;
        if (this == swipeMenuLayout) {
            swipeMenuLayout.smoothClose();
            mViewCache = null;
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean performLongClick() {
        if (Math.abs(getScrollX()) > this.mScaleTouchSlop) {
            return false;
        }
        return super.performLongClick();
    }

    public void quickClose() {
        if (this == mViewCache) {
            cancelAnim();
            mViewCache.scrollTo(0, 0);
            mViewCache = null;
        }
    }
}
