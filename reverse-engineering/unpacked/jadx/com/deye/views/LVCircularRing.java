package com.deye.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.mxchipapp.R;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class LVCircularRing extends View {
    private float mPadding;
    private Paint mPaint;
    private int mStrokeWidth;
    private float mWidth;
    private float startAngle;
    ValueAnimator valueAnimator;

    public LVCircularRing(Context context) {
        this(context, null);
    }

    public LVCircularRing(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LVCircularRing(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mWidth = 0.0f;
        this.mPadding = 0.0f;
        this.startAngle = 0.0f;
        this.mStrokeWidth = context.obtainStyledAttributes(attributeSet, R.styleable.LVCircularRing, i, 0).getDimensionPixelSize(0, (int) getResources().getDimension(2131166294));
        initPaint();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getMeasuredWidth() > getHeight()) {
            this.mWidth = getMeasuredHeight();
        } else {
            this.mWidth = getMeasuredWidth();
        }
        this.mPadding = getContext().getResources().getDimension(2131166294);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.setColor(Color.argb(0, 255, 255, 255));
        float f = this.mWidth;
        canvas.drawCircle(f / 2.0f, f / 2.0f, f - this.mPadding, this.mPaint);
        this.mPaint.setColor(-1);
        float f2 = this.mPadding;
        float f3 = this.mWidth;
        canvas.drawArc(new RectF(f2, f2, f3 - f2, f3 - f2), this.startAngle, 280.0f, false, this.mPaint);
    }

    private void initPaint() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(-1);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setStrokeWidth(this.mStrokeWidth);
    }

    public void startAnim() {
        stopAnim();
        startViewAnim(0.0f, 1.0f, 1000L);
    }

    public void stopAnim() {
        if (this.valueAnimator != null) {
            clearAnimation();
            this.valueAnimator.setRepeatCount(1);
            this.valueAnimator.cancel();
            this.valueAnimator.end();
        }
    }

    private ValueAnimator startViewAnim(float f, float f2, long j) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f, f2);
        this.valueAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(j);
        this.valueAnimator.setInterpolator(new LinearInterpolator());
        this.valueAnimator.setRepeatCount(-1);
        this.valueAnimator.setRepeatMode(1);
        this.valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.deye.views.LVCircularRing.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                LVCircularRing.this.startAngle = ((Float) valueAnimator.getAnimatedValue()).floatValue() * 360.0f;
                LVCircularRing.this.invalidate();
            }
        });
        this.valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.deye.views.LVCircularRing.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
            }
        });
        if (!this.valueAnimator.isRunning()) {
            this.valueAnimator.start();
        }
        return this.valueAnimator;
    }
}
