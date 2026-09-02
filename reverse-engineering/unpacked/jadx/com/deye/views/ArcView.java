package com.deye.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.mxchipapp.R;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ArcView extends View {
    private static int[] SECTION_COLORS = new int[0];
    private float centerX;
    private float centerY;
    private float endAngle;
    private float mAngle;
    private int mAnimatorValue;
    private Paint mArcPaint;
    private String mCriticalValueTextColor;
    private float mCriticalValueTextSize;
    private String mCurrentValueTextColor;
    private float mCurrentValueTextSize;
    private String mDes;
    private String mDesTextColor;
    private float mDesTextSize;
    private float mIncludedAngle;
    private boolean mIsStartShowTip;
    private int mMaxValue;
    private int mMinValue;
    private String mProgressBgColor;
    private String mProgressColor;
    private String mProgressTip;
    private float mProgressTipTextSize;
    private float mSecondStrokeWidth;
    private float mStrokeWidth;
    private Paint mTextPaint;
    private float startAngle;

    public void setProgressTip(String str) {
        this.mProgressTip = str;
    }

    public void setProgressTipTextSize(float f) {
        this.mProgressTipTextSize = f;
    }

    public void setDesTextSize(float f) {
        this.mDesTextSize = f;
    }

    public void setCriticalValueTextSize(float f) {
        this.mCriticalValueTextSize = f;
    }

    public void setCurrentValueTextSize(float f) {
        this.mCurrentValueTextSize = f;
    }

    public void setProgressBgColor(String str) {
        this.mProgressBgColor = str;
    }

    public void setProgressColor(String str) {
        this.mProgressColor = str;
    }

    public ArcView(Context context) {
        this(context, null);
    }

    public ArcView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ArcView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.startAngle = 135.0f;
        this.endAngle = 45.0f;
        this.mAngle = 270.0f;
        this.mIncludedAngle = 0.0f;
        this.mStrokeWidth = getResources().getDimension(2131166399);
        this.mSecondStrokeWidth = getResources().getDimension(2131166377);
        this.mDes = "";
        this.mMinValue = 0;
        this.mMaxValue = 100;
        this.mIsStartShowTip = false;
        this.mProgressTip = StubApp.getString2(1004);
        String string2 = StubApp.getString2(14562);
        this.mProgressBgColor = string2;
        this.mProgressColor = StubApp.getString2(14563);
        this.mDesTextColor = StubApp.getString2(14564);
        this.mDesTextSize = getResources().getDimension(2131166477);
        this.mCriticalValueTextColor = string2;
        this.mCriticalValueTextSize = getResources().getDimension(2131166477);
        this.mCurrentValueTextColor = StubApp.getString2(14565);
        this.mCurrentValueTextSize = getResources().getDimension(2131166411);
        this.mProgressTipTextSize = getResources().getDimension(2131166588);
        SECTION_COLORS = new int[]{getResources().getColor(R.color.color_text_43D1FF), getResources().getColor(R.color.color_text_383AFF)};
    }

    private void initPaint() {
        Paint paint = new Paint(1);
        this.mArcPaint = paint;
        paint.setAntiAlias(true);
        this.mArcPaint.setColor(Color.parseColor(StubApp.getString2(14566)));
        this.mArcPaint.setAlpha(100);
        this.mArcPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mArcPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mArcPaint.setStyle(Paint.Style.STROKE);
        this.mArcPaint.setStrokeWidth(dp2px(this.mStrokeWidth));
        Paint paint2 = new Paint();
        this.mTextPaint = paint2;
        paint2.setAntiAlias(true);
        this.mTextPaint.setColor(Color.parseColor(StubApp.getString2(14567)));
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mTextPaint.setTextSize(dp2px(25.0f));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.centerX = getWidth() / 2;
        this.centerY = getHeight() / 2;
        initPaint();
        drawArc(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        String str;
        Rect rect = new Rect();
        if (!this.mIsStartShowTip) {
            str = "";
        } else {
            str = this.mProgressTip;
        }
        String strValueOf = String.valueOf(this.mAnimatorValue);
        this.mTextPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), StubApp.getString2(5595)));
        this.mTextPaint.setColor(Color.parseColor(this.mCurrentValueTextColor));
        this.mTextPaint.setTextSize(this.mCurrentValueTextSize);
        this.mTextPaint.getTextBounds(strValueOf, 0, strValueOf.length(), rect);
        if (this.mIsStartShowTip) {
            canvas.drawText(String.valueOf(this.mAnimatorValue), this.centerX, (this.centerY / 2.0f) + rect.height(), this.mTextPaint);
        } else {
            canvas.drawText(StubApp.getString2(13801), this.centerX, (this.centerY / 2.0f) + rect.height(), this.mTextPaint);
        }
        if (this.mIsStartShowTip) {
            this.mTextPaint.setColor(Color.parseColor(this.mCurrentValueTextColor));
            this.mTextPaint.setTextSize(this.mProgressTipTextSize);
            Paint paint = this.mTextPaint;
            String str2 = this.mProgressTip;
            paint.getTextBounds(str2, 0, str2.length(), rect);
            float f = this.centerX;
            canvas.drawText(str, (float) (f + (f * 0.5d)), (this.centerY / 2.0f) + rect.height(), this.mTextPaint);
        }
        this.mTextPaint.setColor(Color.parseColor(this.mDesTextColor));
        this.mTextPaint.setTextSize(this.mDesTextSize);
        Paint paint2 = this.mTextPaint;
        String str3 = this.mDes;
        paint2.getTextBounds(str3, 0, str3.length(), rect);
        canvas.drawText(this.mDes, this.centerX, this.centerY + (rect.height() * 2) + dp2px(24.0f), this.mTextPaint);
        String strValueOf2 = String.valueOf(this.mMinValue);
        String strValueOf3 = String.valueOf(this.mMaxValue);
        this.mTextPaint.setColor(Color.parseColor(this.mCriticalValueTextColor));
        this.mTextPaint.setTextSize(this.mCriticalValueTextSize);
        this.mTextPaint.getTextBounds(strValueOf2, 0, strValueOf2.length(), rect);
        float f2 = this.centerX;
        float f3 = this.centerY;
        canvas.drawText(strValueOf2, (float) ((f2 - (f2 * 0.6d)) - dp2px(5.0f)), (float) (f3 + (f3 * 0.75d) + rect.height() + dp2px(5.0f)), this.mTextPaint);
        this.mTextPaint.setColor(Color.parseColor(this.mCriticalValueTextColor));
        this.mTextPaint.setTextSize(this.mCriticalValueTextSize);
        this.mTextPaint.getTextBounds(strValueOf3, 0, strValueOf3.length(), rect);
        float f4 = this.centerX;
        float f5 = this.centerY;
        canvas.drawText(strValueOf3, (float) (f4 + (f4 * 0.6d) + dp2px(5.0f)), (float) (f5 + (f5 * 0.75d) + rect.height() + dp2px(5.0f)), this.mTextPaint);
    }

    private void drawArc(Canvas canvas) {
        RectF rectF = new RectF(this.mStrokeWidth + dp2px(5.0f), this.mStrokeWidth + dp2px(5.0f), (getWidth() - this.mStrokeWidth) - dp2px(5.0f), getHeight() - this.mStrokeWidth);
        this.mArcPaint.setColor(Color.parseColor(this.mProgressBgColor));
        this.mArcPaint.setStrokeWidth(this.mSecondStrokeWidth);
        canvas.drawArc(rectF, this.startAngle, this.mAngle, false, this.mArcPaint);
        this.mArcPaint.setStrokeWidth(this.mStrokeWidth);
        int[] iArr = SECTION_COLORS;
        int i = iArr[0];
        int i2 = iArr[1];
        this.mArcPaint.setShader(new SweepGradient(getWidth() / 2, getHeight(), new int[]{i, i, i, i, i2, i2, i2}, new float[]{0.2f, 0.4f, 0.5f, 0.65f, 0.8f, 0.9f, 1.0f}));
        canvas.drawArc(rectF, this.startAngle, this.mIncludedAngle, false, this.mArcPaint);
    }

    private void setAnimation(float f, float f2, int i, int i2) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f, f2);
        long j = i2;
        valueAnimatorOfFloat.setDuration(j);
        valueAnimatorOfFloat.setTarget(Float.valueOf(this.mIncludedAngle));
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.deye.views.ArcView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ArcView.this.mIncludedAngle = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                ArcView.this.postInvalidate();
            }
        });
        valueAnimatorOfFloat.start();
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.mAnimatorValue, i);
        valueAnimatorOfInt.setDuration(j);
        valueAnimatorOfInt.setInterpolator(new LinearInterpolator());
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.deye.views.ArcView.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ArcView.this.mAnimatorValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ArcView.this.postInvalidate();
            }
        });
        valueAnimatorOfInt.start();
    }

    public void setValues(int i, int i2, int i3, String str) {
        this.mIsStartShowTip = true;
        this.mDes = str;
        this.mMaxValue = i2;
        this.mMinValue = i;
        if (i3 > i2) {
            i3 = i2;
        }
        if (i3 < i) {
            i3 = i;
        }
        float fAbs = (Math.abs(i) + i3) / (Math.abs(this.mMinValue) + i2);
        if (fAbs > 1.0f || i3 == i2) {
            fAbs = 1.0f;
        }
        if (fAbs < 0.0f) {
            fAbs = 0.0f;
        }
        setAnimation(this.mIncludedAngle, fAbs * this.mAngle, i3, 1000);
    }

    public float dp2px(float f) {
        return f * Resources.getSystem().getDisplayMetrics().density;
    }
}
