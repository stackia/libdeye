package com.deye.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.autonavi.aps.amapapi.config.Const;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.util.DensityUtil;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class HomeHeader extends View implements RefreshHeader {
    private static final int DURATION_FINISH = 800;
    private static final int TARGET_DEGREE = 270;
    private Paint mBackPaint;
    private float mBollRadius;
    private float mBollY;
    private float mFinishRatio;
    private Paint mFrontPaint;
    private float mHeadHeight;
    private boolean mOuterIsStart;
    private Paint mOuterPaint;
    private Path mPath;
    private int mRefreshStart;
    private int mRefreshStop;
    private boolean mShowBoll;
    private boolean mShowBollTail;
    private boolean mShowOuter;
    private float mSpringRatio;
    private RefreshState mState;
    private float mWaveHeight;

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    public View getView() {
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    public void onHorizontalDrag(float f, int i, int i2) {
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    public void onInitialized(RefreshKernel refreshKernel, int i, int i2) {
    }

    public HomeHeader(Context context) {
        super(context, null, 0);
        this.mRefreshStop = 90;
        this.mRefreshStart = 90;
        this.mOuterIsStart = true;
        initView(context, null);
    }

    public HomeHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.mRefreshStop = 90;
        this.mRefreshStart = 90;
        this.mOuterIsStart = true;
        initView(context, attributeSet);
    }

    public HomeHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRefreshStop = 90;
        this.mRefreshStart = 90;
        this.mOuterIsStart = true;
        initView(context, attributeSet);
    }

    public HomeHeader(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mRefreshStop = 90;
        this.mRefreshStart = 90;
        this.mOuterIsStart = true;
        initView(context, attributeSet);
    }

    private void initView(Context context, AttributeSet attributeSet) {
        setMinimumHeight(DensityUtil.dp2px(100.0f));
        Paint paint = new Paint();
        this.mBackPaint = paint;
        paint.setColor(0);
        this.mBackPaint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.mFrontPaint = paint2;
        paint2.setColor(-1);
        this.mFrontPaint.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.mOuterPaint = paint3;
        paint3.setAntiAlias(true);
        this.mOuterPaint.setColor(-1);
        this.mOuterPaint.setStyle(Paint.Style.STROKE);
        this.mOuterPaint.setStrokeWidth(DensityUtil.dp2px(2.0f));
        this.mPath = new Path();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(resolveSize(getSuggestedMinimumWidth(), i), resolveSize(getSuggestedMinimumHeight(), i2));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isInEditMode()) {
            this.mShowBoll = true;
            this.mShowOuter = true;
            float height = getHeight();
            this.mHeadHeight = height;
            this.mRefreshStop = 270;
            this.mBollY = height / 2.0f;
            this.mBollRadius = height / 6.0f;
        }
        int width = getWidth();
        drawWave(canvas, width, getHeight());
        drawSpringUp(canvas, width);
        drawBoll(canvas, width);
        drawOuter(canvas, width);
        drawFinish(canvas, width);
    }

    private void drawWave(Canvas canvas, int i, int i2) {
        float fMin = Math.min(this.mHeadHeight, i2);
        if (this.mWaveHeight != 0.0f) {
            this.mPath.reset();
            float f = i;
            this.mPath.lineTo(f, 0.0f);
            this.mPath.lineTo(f, fMin);
            this.mPath.quadTo(i / 2, (this.mWaveHeight * 2.0f) + fMin, 0.0f, fMin);
            this.mPath.close();
            canvas.drawPath(this.mPath, this.mBackPaint);
            return;
        }
        canvas.drawRect(0.0f, 0.0f, i, fMin, this.mBackPaint);
    }

    private void drawSpringUp(Canvas canvas, int i) {
        float f = this.mSpringRatio;
        if (f > 0.0f) {
            float f2 = i / 2;
            float f3 = this.mBollRadius;
            float f4 = (f2 - (4.0f * f3)) + (3.0f * f * f3);
            if (f < 0.9d) {
                this.mPath.reset();
                this.mPath.moveTo(f4, this.mBollY);
                Path path = this.mPath;
                float f5 = this.mBollY;
                path.quadTo(f2, f5 - ((this.mBollRadius * this.mSpringRatio) * 2.0f), i - f4, f5);
                canvas.drawPath(this.mPath, this.mFrontPaint);
                return;
            }
            canvas.drawCircle(f2, this.mBollY, f3, this.mFrontPaint);
        }
    }

    private void drawBoll(Canvas canvas, int i) {
        if (this.mShowBoll) {
            canvas.drawCircle(i / 2, this.mBollY, this.mBollRadius, this.mFrontPaint);
            float f = this.mHeadHeight;
            drawBollTail(canvas, i, (this.mWaveHeight + f) / f);
        }
    }

    private void drawBollTail(Canvas canvas, int i, float f) {
        if (this.mShowBollTail) {
            float f2 = this.mHeadHeight + this.mWaveHeight;
            float f3 = this.mBollY + ((this.mBollRadius * f) / 2.0f);
            float f4 = i / 2;
            float fSqrt = ((float) Math.sqrt(r2 * r2 * (1.0f - ((f * f) / 4.0f)))) + f4;
            float f5 = this.mBollRadius;
            float f6 = f4 + (((3.0f * f5) / 4.0f) * (1.0f - f));
            float f7 = f5 + f6;
            this.mPath.reset();
            this.mPath.moveTo(fSqrt, f3);
            this.mPath.quadTo(f6, f2, f7, f2);
            float f8 = i;
            this.mPath.lineTo(f8 - f7, f2);
            this.mPath.quadTo(f8 - f6, f2, f8 - fSqrt, f3);
            canvas.drawPath(this.mPath, this.mFrontPaint);
        }
    }

    private void drawOuter(Canvas canvas, int i) {
        if (this.mShowOuter) {
            float strokeWidth = this.mBollRadius + (this.mOuterPaint.getStrokeWidth() * 2.0f);
            int i2 = this.mRefreshStart;
            boolean z = this.mOuterIsStart;
            int i3 = i2 + (z ? 3 : 10);
            this.mRefreshStart = i3;
            int i4 = this.mRefreshStop + (z ? 10 : 3);
            this.mRefreshStop = i4;
            int i5 = i3 % Const.I_MAX_MEM;
            this.mRefreshStart = i5;
            int i6 = i4 % Const.I_MAX_MEM;
            this.mRefreshStop = i6;
            int i7 = i6 - i5;
            if (i7 < 0) {
                i7 += Const.I_MAX_MEM;
            }
            float f = i / 2;
            float f2 = this.mBollY;
            canvas.drawArc(new RectF(f - strokeWidth, f2 - strokeWidth, f + strokeWidth, f2 + strokeWidth), this.mRefreshStart, i7, false, this.mOuterPaint);
            if (i7 >= 270) {
                this.mOuterIsStart = false;
            } else if (i7 <= 10) {
                this.mOuterIsStart = true;
            }
            invalidate();
        }
    }

    private void drawFinish(Canvas canvas, int i) {
        if (this.mFinishRatio > 0.0f) {
            int color = this.mOuterPaint.getColor();
            if (this.mFinishRatio < 0.3d) {
                canvas.drawCircle(i / 2, this.mBollY, this.mBollRadius, this.mFrontPaint);
                float f = this.mBollRadius;
                float strokeWidth = this.mOuterPaint.getStrokeWidth() * 2.0f;
                float f2 = this.mFinishRatio;
                this.mOuterPaint.setColor(Color.argb((int) ((1.0f - (f2 / 0.3f)) * 255.0f), Color.red(color), Color.green(color), Color.blue(color)));
                float f3 = this.mBollY;
                float f4 = (int) (f + (strokeWidth * ((f2 / 0.3f) + 1.0f)));
                canvas.drawArc(new RectF(r1 - r2, f3 - f4, r1 + r2, f3 + f4), 0.0f, 360.0f, false, this.mOuterPaint);
            }
            this.mOuterPaint.setColor(color);
            float f5 = this.mFinishRatio;
            if (f5 >= 0.3d && f5 < 0.7d) {
                float f6 = (f5 - 0.3f) / 0.4f;
                float f7 = this.mHeadHeight;
                float f8 = (int) ((f7 / 2.0f) + ((f7 - (f7 / 2.0f)) * f6));
                this.mBollY = f8;
                canvas.drawCircle(i / 2, f8, this.mBollRadius, this.mFrontPaint);
                if (this.mBollY >= this.mHeadHeight - (this.mBollRadius * 2.0f)) {
                    this.mShowBollTail = true;
                    drawBollTail(canvas, i, f6);
                }
                this.mShowBollTail = false;
            }
            float f9 = this.mFinishRatio;
            if (f9 < 0.7d || f9 > 1.0f) {
                return;
            }
            float f10 = (f9 - 0.7f) / 0.3f;
            float f11 = i / 2;
            float f12 = this.mBollRadius;
            this.mPath.reset();
            this.mPath.moveTo((int) ((f11 - f12) - ((f12 * 2.0f) * f10)), this.mHeadHeight);
            Path path = this.mPath;
            float f13 = this.mHeadHeight;
            path.quadTo(f11, f13 - (this.mBollRadius * (1.0f - f10)), i - r3, f13);
            canvas.drawPath(this.mPath, this.mFrontPaint);
        }
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshHeader
    public void onPullingDown(float f, int i, int i2, int i3) {
        this.mHeadHeight = i2;
        this.mWaveHeight = Math.max(i - i2, 0) * 0.8f;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshHeader
    public void onReleasing(float f, int i, int i2, int i3) {
        if (this.mState != RefreshState.Refreshing) {
            onPullingDown(f, i, i2, i3);
        }
    }

    @Override // com.scwang.smartrefresh.layout.listener.OnStateChangedListener
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState refreshState, RefreshState refreshState2) {
        this.mState = refreshState2;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    public void onStartAnimator(RefreshLayout refreshLayout, int i, int i2) {
        this.mHeadHeight = i;
        this.mBollRadius = i / 6;
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
        final float fMin = Math.min(this.mWaveHeight * 0.8f, this.mHeadHeight / 2.0f);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.mWaveHeight, 0.0f, -(1.0f * fMin), 0.0f, -(0.4f * fMin), 0.0f);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.deye.views.HomeHeader.1
            float springBollY;
            float speed = 0.0f;
            float springRatio = 0.0f;
            int springstatus = 0;

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (this.springstatus == 0 && fFloatValue <= 0.0f) {
                    this.springstatus = 1;
                    this.speed = Math.abs(fFloatValue - HomeHeader.this.mWaveHeight);
                }
                if (this.springstatus == 1) {
                    float f = (-fFloatValue) / fMin;
                    this.springRatio = f;
                    if (f >= HomeHeader.this.mSpringRatio) {
                        HomeHeader.this.mSpringRatio = this.springRatio;
                        HomeHeader homeHeader = HomeHeader.this;
                        homeHeader.mBollY = homeHeader.mHeadHeight + fFloatValue;
                        this.speed = Math.abs(fFloatValue - HomeHeader.this.mWaveHeight);
                    } else {
                        this.springstatus = 2;
                        HomeHeader.this.mSpringRatio = 0.0f;
                        HomeHeader.this.mShowBoll = true;
                        HomeHeader.this.mShowBollTail = true;
                        this.springBollY = HomeHeader.this.mBollY;
                    }
                }
                if (this.springstatus == 2 && HomeHeader.this.mBollY > HomeHeader.this.mHeadHeight / 2.0f) {
                    HomeHeader homeHeader2 = HomeHeader.this;
                    homeHeader2.mBollY = Math.max(homeHeader2.mHeadHeight / 2.0f, HomeHeader.this.mBollY - this.speed);
                    float animatedFraction = valueAnimator.getAnimatedFraction();
                    float f2 = HomeHeader.this.mHeadHeight / 2.0f;
                    float f3 = this.springBollY;
                    float f4 = (animatedFraction * (f2 - f3)) + f3;
                    if (HomeHeader.this.mBollY > f4) {
                        HomeHeader.this.mBollY = f4;
                    }
                }
                if (HomeHeader.this.mShowBollTail && fFloatValue < HomeHeader.this.mWaveHeight) {
                    HomeHeader.this.mShowOuter = true;
                    HomeHeader.this.mShowBollTail = false;
                    HomeHeader.this.mOuterIsStart = true;
                    HomeHeader.this.mRefreshStart = 90;
                    HomeHeader.this.mRefreshStop = 90;
                }
                HomeHeader.this.mWaveHeight = fFloatValue;
                HomeHeader.this.invalidate();
            }
        });
        valueAnimatorOfFloat.setInterpolator(decelerateInterpolator);
        valueAnimatorOfFloat.setDuration(1000L);
        valueAnimatorOfFloat.start();
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    public int onFinish(RefreshLayout refreshLayout, boolean z) {
        this.mShowOuter = false;
        this.mShowBoll = false;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.deye.views.HomeHeader.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                HomeHeader.this.mFinishRatio = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                HomeHeader.this.invalidate();
            }
        });
        valueAnimatorOfFloat.setInterpolator(new AccelerateInterpolator());
        valueAnimatorOfFloat.setDuration(800L);
        valueAnimatorOfFloat.start();
        return DURATION_FINISH;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    public void setPrimaryColors(int... iArr) {
        if (iArr.length > 0) {
            this.mBackPaint.setColor(iArr[0]);
            if (iArr.length > 1) {
                this.mFrontPaint.setColor(iArr[1]);
                this.mOuterPaint.setColor(iArr[1]);
            }
        }
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Scale;
    }
}
