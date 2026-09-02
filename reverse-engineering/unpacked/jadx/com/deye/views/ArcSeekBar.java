package com.deye.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ArcSeekBar extends View {
    private static final float CIRCLE_ANGLE = 360.0f;
    private static final int DEFAULT_EDGE_LENGTH = 260;
    private static final int DEFAULT_MAX_VALUE = 80;
    private static final int DEFAULT_MIN_VALUE = 30;
    private static final float DEFAULT_OPEN_ANGLE = 60.0f;
    private static final float DEFAULT_ROTATE_ANGLE = 90.0f;
    private static final int DEFAULT_THUMB_RADIUS = 50;
    private static final String KEY_PROGRESS_PRESENT = StubApp.getString2(14558);
    private int colorMode;
    private boolean colorModeEnable;
    private RectF content;
    private boolean enable;
    private boolean isDark;
    private boolean isTouchEnable;
    private int lastProgress;
    private boolean mAllowTouchSkip;
    private int[] mArcColors;
    private Paint mArcPaint;
    private Region mArcRegion;
    private float mArcWidth;
    private int[] mBgArcColors;
    private Paint mBgArcPaint;
    private RectF mBgContent;
    private Path mBorderPath;
    private boolean mCanDrag;
    private float mCenterX;
    private float mCenterY;
    private GestureDetector mDetector;
    private PathMeasure mHelperSeekPathMeasure;
    private float mHelperX;
    private float mHelperY;
    private int mInnerArcGap;
    private RectF mInnerContent;
    private Paint mInnerPaint;
    private Path mInnerPath;
    private Matrix mInvertMatrix;
    private Paint mLinePaint;
    private int mMaxValue;
    private int mMinValue;
    private OnProgressChangeListener mOnProgressChangeListener;
    private float mOpenAngle;
    private int[] mOutArcColors;
    private RectF mOutCirCleLineContent;
    private Paint mOutCirCleLinePaint;
    private RectF mOutContent;
    private Paint mOutPaint;
    private int mOutPaintWidth;
    private Path mOutPath;
    private float mProgressPresent;
    private float mRotateAngle;
    private Path mSeekPath;
    private PathMeasure mSeekPathMeasure;
    private int mStep;
    private float[] mTempPos;
    private float[] mTempTan;
    private float mThumbRadius;
    private float mThumbWidth;
    private float mThumbX;
    private float mThumbY;
    private boolean moved;

    /* compiled from: D8$$SyntheticClass */
    /* renamed from: com.deye.views.ArcSeekBar$OnClickListener-IA, reason: invalid class name */
    public final /* synthetic */ class OnClickListenerIA {
    }

    public interface OnProgressChangeListener {
        void onProgressChanged(ArcSeekBar arcSeekBar, int i, boolean z);

        void onStartTrackingTouch(ArcSeekBar arcSeekBar);

        void onStopTrackingTouch(ArcSeekBar arcSeekBar);
    }

    public ArcSeekBar(Context context) {
        this(context, null);
    }

    public ArcSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ArcSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mArcWidth = dp2px(30);
        this.mInnerArcGap = dp2px(8);
        this.mOutPaintWidth = dp2px(8);
        this.mProgressPresent = 0.0f;
        this.mCanDrag = false;
        this.mAllowTouchSkip = false;
        this.enable = true;
        this.colorModeEnable = false;
        this.colorMode = -1;
        this.isDark = false;
        this.isTouchEnable = true;
        this.moved = false;
        this.lastProgress = -1;
        setSaveEnabled(true);
        setLayerType(1, null);
        init();
        initData();
        initPaint();
    }

    private void init() {
        this.mOpenAngle = DEFAULT_OPEN_ANGLE;
        this.mRotateAngle = DEFAULT_ROTATE_ANGLE;
        this.mMaxValue = 80;
        this.mMinValue = 30;
        setProgress(30);
        this.mThumbRadius = dp2px(50);
    }

    private void initData() {
        this.mSeekPath = new Path();
        this.mBorderPath = new Path();
        this.mSeekPathMeasure = new PathMeasure();
        this.mHelperSeekPathMeasure = new PathMeasure();
        this.mTempPos = new float[2];
        this.mTempTan = new float[2];
        this.mDetector = new GestureDetector(getContext(), new OnClickListener(this, null));
        this.mInvertMatrix = new Matrix();
        this.mArcRegion = new Region();
    }

    private void initPaint() {
        initArcPaint();
        initBgArcPaint();
        intOutPaint();
        initInnerPaint();
        initLinerPaint();
        initOutCirCleLinePaint();
    }

    private void initOutCirCleLinePaint() {
        Paint paint = new Paint();
        this.mOutCirCleLinePaint = paint;
        paint.setStrokeWidth(dp2px(1));
        this.mOutCirCleLinePaint.setColor(Color.parseColor(StubApp.getString2(14540)));
        this.mOutCirCleLinePaint.setAntiAlias(true);
        this.mOutCirCleLinePaint.setStyle(Paint.Style.STROKE);
    }

    private void initInnerPaint() {
        Paint paint = new Paint();
        this.mInnerPaint = paint;
        paint.setStrokeWidth(dp2px(1));
        this.mInnerPaint.setColor(Color.parseColor(StubApp.getString2(14539)));
        this.mInnerPaint.setAntiAlias(true);
        this.mInnerPaint.setStyle(Paint.Style.FILL);
    }

    private void initLinerPaint() {
        Paint paint = new Paint();
        this.mLinePaint = paint;
        paint.setStrokeWidth(dp2px(1));
        this.mLinePaint.setColor(-16777216);
        this.mLinePaint.setAntiAlias(true);
        this.mLinePaint.setStyle(Paint.Style.STROKE);
    }

    private void intOutPaint() {
        Paint paint = new Paint();
        this.mOutPaint = paint;
        paint.setStrokeWidth(this.mOutPaintWidth);
        this.mOutPaint.setAntiAlias(true);
        this.mOutPaint.setStyle(Paint.Style.STROKE);
    }

    private void initArcPaint() {
        Paint paint = new Paint();
        this.mArcPaint = paint;
        paint.setAntiAlias(true);
        this.mArcPaint.setStrokeWidth(this.mArcWidth);
        this.mArcPaint.setStyle(Paint.Style.STROKE);
        this.mArcPaint.setStrokeCap(Paint.Cap.BUTT);
    }

    private void initBgArcPaint() {
        Paint paint = new Paint();
        this.mBgArcPaint = paint;
        paint.setAntiAlias(true);
        this.mBgArcPaint.setStrokeWidth(this.mArcWidth + this.mInnerArcGap);
        this.mBgArcPaint.setStyle(Paint.Style.STROKE);
        this.mBgArcPaint.setStrokeCap(Paint.Cap.BUTT);
        this.mBgArcPaint.setColor(-16777216);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(StubApp.getString2(14559), super.onSaveInstanceState());
        bundle.putFloat(StubApp.getString2(14558), this.mProgressPresent);
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.mProgressPresent = bundle.getFloat(StubApp.getString2(14558));
            parcelable = bundle.getParcelable(StubApp.getString2(14559));
        }
        OnProgressChangeListener onProgressChangeListener = this.mOnProgressChangeListener;
        if (onProgressChangeListener != null) {
            onProgressChangeListener.onProgressChanged(this, getProgress(), false);
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        float f = (this.mArcWidth + this.mInnerArcGap) / 2.0f;
        this.mOutCirCleLineContent = new RectF(dp2px(1) / 2, dp2px(1) / 2, i - (dp2px(1) / 2), i2 - (dp2px(1) / 2));
        float f2 = (this.mArcWidth / 2.0f) + this.mInnerArcGap;
        float f3 = i;
        float f4 = i2;
        RectF rectF = new RectF(f2, f2, f3 - f2, f4 - f2);
        this.content = rectF;
        this.mCenterX = rectF.centerX();
        this.mCenterY = this.content.centerY();
        this.mSeekPath.reset();
        Path path = this.mSeekPath;
        RectF rectF2 = this.content;
        float f5 = this.mOpenAngle;
        path.addArc(rectF2, f5 / 2.0f, CIRCLE_ANGLE - f5);
        this.mSeekPathMeasure.setPath(this.mSeekPath, false);
        computeThumbPos(this.mProgressPresent);
        float fDp2px = this.mArcWidth + this.mInnerArcGap + dp2px(12);
        this.mInnerArcGap += (int) (this.mArcWidth / 2.0f);
        this.mInnerContent = new RectF(fDp2px, fDp2px, f3 - fDp2px, f4 - fDp2px);
        Path path2 = new Path();
        this.mInnerPath = path2;
        RectF rectF3 = this.mInnerContent;
        float f6 = this.mOpenAngle;
        path2.addArc(rectF3, f6 / 2.0f, CIRCLE_ANGLE - f6);
        this.mHelperSeekPathMeasure.setPath(this.mInnerPath, false);
        float fDp2px2 = ((this.mArcWidth - this.mInnerArcGap) / 2.0f) + dp2px(1);
        this.mOutContent = new RectF(fDp2px2, fDp2px2, f3 - fDp2px2, f4 - fDp2px2);
        Path path3 = new Path();
        this.mOutPath = path3;
        RectF rectF4 = this.mOutContent;
        float f7 = this.mOpenAngle;
        path3.addArc(rectF4, f7 / 2.0f, CIRCLE_ANGLE - f7);
        this.mBgContent = new RectF(f, f, f3 - f, f4 - f);
        resetShaderColor();
        this.mInvertMatrix.reset();
        this.mInvertMatrix.preRotate(-this.mRotateAngle, this.mCenterX, this.mCenterY);
        this.mArcPaint.getFillPath(this.mSeekPath, this.mBorderPath);
        this.mBorderPath.close();
        this.mArcRegion.setPath(this.mBorderPath, new Region(0, 0, i, i2));
    }

    private void resetShaderColor() {
        boolean z = this.colorModeEnable;
        String string2 = StubApp.getString2(14542);
        String string22 = StubApp.getString2(14543);
        String string23 = StubApp.getString2(14544);
        String string24 = StubApp.getString2(13225);
        String string25 = StubApp.getString2(14545);
        String string26 = StubApp.getString2(14546);
        if (!z) {
            this.mArcColors = new int[]{Color.parseColor(string26), Color.parseColor(StubApp.getString2(14547)), Color.parseColor(StubApp.getString2(14548)), Color.parseColor(string25), Color.parseColor(string25), Color.parseColor(string22), Color.parseColor(string26)};
            this.mOutArcColors = new int[]{Color.parseColor(string24), Color.parseColor(StubApp.getString2(14549)), Color.parseColor(StubApp.getString2(14550)), Color.parseColor(string23), Color.parseColor(string23), Color.parseColor(string2), Color.parseColor(string24)};
            float[] fArr = {0.0f, 0.14f, 0.23f, 0.49f, 0.7f, 0.85f, 1.0f};
            this.mArcPaint.setShader(new SweepGradient(this.mCenterX, this.mCenterY, this.mArcColors, fArr));
            this.mOutPaint.setShader(new SweepGradient(this.mCenterX, this.mCenterY, this.mOutArcColors, fArr));
        } else {
            if (this.colorMode == 1) {
                this.mArcColors = new int[]{Color.parseColor(StubApp.getString2(14551)), Color.parseColor(StubApp.getString2(14552)), Color.parseColor(StubApp.getString2(14553))};
                this.mOutArcColors = new int[]{Color.parseColor(StubApp.getString2(14554)), Color.parseColor(StubApp.getString2(14555)), Color.parseColor(StubApp.getString2(14556))};
            } else {
                this.mArcColors = new int[]{Color.parseColor(string26), Color.parseColor(string22), Color.parseColor(string25)};
                this.mOutArcColors = new int[]{Color.parseColor(string24), Color.parseColor(string2), Color.parseColor(string23)};
            }
            float[] fArr2 = {0.0f, 0.5f, 1.0f};
            this.mArcPaint.setShader(new SweepGradient(this.mCenterX, this.mCenterY, this.mArcColors, fArr2));
            this.mOutPaint.setShader(new SweepGradient(this.mCenterX, this.mCenterY, this.mOutArcColors, fArr2));
        }
        String string27 = StubApp.getString2(14557);
        this.mBgArcColors = new int[]{Color.parseColor(string27), Color.parseColor(string27), Color.parseColor(string27)};
        this.mBgArcPaint.setShader(new SweepGradient(this.mCenterX, this.mCenterY, this.mBgArcColors, new float[]{0.0f, 0.5f, 1.0f}));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.rotate(this.mRotateAngle, this.mCenterX, this.mCenterY);
        this.mSeekPath.reset();
        this.mSeekPath.addArc(this.mOutCirCleLineContent, 0.0f, CIRCLE_ANGLE);
        canvas.drawPath(this.mSeekPath, this.mOutCirCleLinePaint);
        if (this.enable) {
            this.mSeekPath.reset();
            Path path = this.mSeekPath;
            RectF rectF = this.mBgContent;
            float f = this.mOpenAngle;
            path.addArc(rectF, f / 2.0f, CIRCLE_ANGLE - f);
            canvas.drawPath(this.mSeekPath, this.mBgArcPaint);
            this.mSeekPath.reset();
            Path path2 = this.mSeekPath;
            RectF rectF2 = this.content;
            float f2 = this.mOpenAngle;
            path2.addArc(rectF2, f2 / 2.0f, (CIRCLE_ANGLE - f2) * this.mProgressPresent);
            canvas.drawPath(this.mSeekPath, this.mArcPaint);
            this.mOutPath.reset();
            Path path3 = this.mOutPath;
            RectF rectF3 = this.mOutContent;
            float f3 = this.mOpenAngle;
            path3.addArc(rectF3, f3 / 2.0f, (CIRCLE_ANGLE - f3) * this.mProgressPresent);
            canvas.drawPath(this.mOutPath, this.mOutPaint);
        }
        canvas.restore();
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        motionEvent.getX();
        motionEvent.getY();
        if (!this.enable || !this.isTouchEnable) {
            getParent().requestDisallowInterceptTouchEvent(false);
            return super.dispatchTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
            judgeCanDrag(motionEvent);
        } else if (action == 2) {
            getParent().requestDisallowInterceptTouchEvent(this.mCanDrag);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0065  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (!this.enable || !this.isTouchEnable) {
            return false;
        }
        if (actionMasked == 0) {
            this.moved = false;
            judgeCanDrag(motionEvent);
            OnProgressChangeListener onProgressChangeListener = this.mOnProgressChangeListener;
            if (onProgressChangeListener != null) {
                onProgressChangeListener.onStartTrackingTouch(this);
            }
        } else if (actionMasked == 1) {
            if (this.mOnProgressChangeListener != null && this.moved) {
                alignProgress();
                this.mOnProgressChangeListener.onStopTrackingTouch(this);
            }
        } else if (actionMasked != 2) {
            if (actionMasked == 3) {
            }
        } else if (this.mCanDrag) {
            float currentProgress = getCurrentProgress(motionEvent.getX(), motionEvent.getY());
            if (this.mAllowTouchSkip || Math.abs(currentProgress - this.mProgressPresent) <= 0.5f) {
                this.mProgressPresent = currentProgress;
                computeThumbPos(currentProgress);
                if (this.mOnProgressChangeListener != null && getProgress() != this.lastProgress) {
                    this.mOnProgressChangeListener.onProgressChanged(this, getProgress(), true);
                    this.lastProgress = getProgress();
                }
                this.moved = true;
            }
        }
        this.mDetector.onTouchEvent(motionEvent);
        invalidate();
        return true;
    }

    void alignProgress() {
        int progress = getProgress();
        if (this.mStep == 0) {
            return;
        }
        int progress2 = getProgress() % this.mStep;
        if (progress2 > 0 && progress2 < 3) {
            setProgress(progress - progress2);
        } else if (progress2 > 0) {
            setProgress((progress - progress2) + 5);
        }
    }

    private void judgeCanDrag(MotionEvent motionEvent) {
        this.mInvertMatrix.mapPoints(new float[]{motionEvent.getX(), motionEvent.getY()});
        if (getDistance(r1[0], r1[1]) <= this.mThumbRadius * 1.2d) {
            this.mCanDrag = true;
        } else {
            this.mCanDrag = false;
        }
        Log.e(StubApp.getString2(14541), this.mCanDrag + "");
    }

    public void setStep(int i) {
        this.mStep = i;
    }

    private class OnClickListener extends GestureDetector.SimpleOnGestureListener {
        /* synthetic */ OnClickListener(ArcSeekBar arcSeekBar, OnClickListenerIA onClickListenerIA) {
            this();
        }

        private OnClickListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (!ArcSeekBar.this.isInArcProgress(motionEvent.getX(), motionEvent.getY())) {
                return false;
            }
            ArcSeekBar arcSeekBar = ArcSeekBar.this;
            arcSeekBar.mProgressPresent = arcSeekBar.getCurrentProgress(motionEvent.getX(), motionEvent.getY());
            ArcSeekBar arcSeekBar2 = ArcSeekBar.this;
            arcSeekBar2.computeThumbPos(arcSeekBar2.mProgressPresent);
            ArcSeekBar.this.alignProgress();
            if (ArcSeekBar.this.mOnProgressChangeListener != null) {
                OnProgressChangeListener onProgressChangeListener = ArcSeekBar.this.mOnProgressChangeListener;
                ArcSeekBar arcSeekBar3 = ArcSeekBar.this;
                onProgressChangeListener.onProgressChanged(arcSeekBar3, arcSeekBar3.getProgress(), true);
                ArcSeekBar.this.mOnProgressChangeListener.onStopTrackingTouch(ArcSeekBar.this);
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isInArcProgress(float f, float f2) {
        float[] fArr = {f, f2};
        this.mInvertMatrix.mapPoints(fArr);
        return this.mArcRegion.contains((int) fArr[0], (int) fArr[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getCurrentProgress(float f, float f2) {
        float diffAngle = getDiffAngle(f, f2) / (CIRCLE_ANGLE - this.mOpenAngle);
        if (diffAngle < 0.0f) {
            diffAngle = 0.0f;
        }
        if (diffAngle > 1.0f) {
            return 1.0f;
        }
        return diffAngle;
    }

    private float getDiffAngle(float f, float f2) {
        float angle = getAngle(f, f2) - this.mRotateAngle;
        if (angle < 0.0f) {
            angle = (angle + CIRCLE_ANGLE) % CIRCLE_ANGLE;
        }
        return angle - (this.mOpenAngle / 2.0f);
    }

    private float getAngle(float f, float f2) {
        float fAtan2 = (float) ((Math.atan2(f2 - this.mCenterY, f - this.mCenterX) * 180.0d) / 3.140000104904175d);
        return fAtan2 < 0.0f ? fAtan2 + CIRCLE_ANGLE : fAtan2;
    }

    private float getDistance(float f, float f2) {
        float f3 = this.mThumbX;
        float f4 = (f - f3) * (f - f3);
        float f5 = this.mThumbY;
        return (float) Math.sqrt(f4 + ((f2 - f5) * (f2 - f5)));
    }

    private int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, i, getContext().getResources().getDisplayMetrics());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void computeThumbPos(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        PathMeasure pathMeasure = this.mSeekPathMeasure;
        if (pathMeasure == null) {
            return;
        }
        this.mSeekPathMeasure.getPosTan(pathMeasure.getLength() * f, this.mTempPos, this.mTempTan);
        float[] fArr = this.mTempPos;
        this.mThumbX = fArr[0];
        this.mThumbY = fArr[1];
        PathMeasure pathMeasure2 = this.mHelperSeekPathMeasure;
        if (pathMeasure2 == null) {
            return;
        }
        float[] fArr2 = new float[2];
        this.mHelperSeekPathMeasure.getPosTan(pathMeasure2.getLength() * f, fArr2, new float[2]);
        this.mHelperX = fArr2[0];
        this.mHelperY = fArr2[1];
    }

    public void setProgress(int i) {
        System.out.println(StubApp.getString2(14560) + i);
        int i2 = this.mMaxValue;
        if (i > i2) {
            i = i2;
        }
        int i3 = this.mMinValue;
        if (i < i3) {
            i = i3;
        }
        this.mProgressPresent = ((i - i3) * 1.0f) / (i2 - i3);
        System.out.println(StubApp.getString2(14561) + this.mProgressPresent);
        OnProgressChangeListener onProgressChangeListener = this.mOnProgressChangeListener;
        if (onProgressChangeListener != null) {
            onProgressChangeListener.onProgressChanged(this, i, false);
        }
        computeThumbPos(this.mProgressPresent);
        postInvalidate();
    }

    public int getProgress() {
        return Math.round(this.mProgressPresent * (this.mMaxValue - this.mMinValue)) + this.mMinValue;
    }

    public void changeArcStyle(int i, boolean z) {
        this.enable = z;
        if (!z) {
            setProgress(100);
        }
        if (i != -1) {
            this.colorModeEnable = true;
            this.colorMode = i;
        } else {
            this.colorModeEnable = false;
        }
        resetShaderColor();
        postInvalidate();
    }

    public void forbidTouch(boolean z) {
        this.isTouchEnable = z;
    }

    public void setMaxValue(int i) {
        this.mMaxValue = i;
    }

    public void setMinValue(int i) {
        this.mMinValue = i;
    }

    public void setOnProgressChangeListener(OnProgressChangeListener onProgressChangeListener) {
        this.mOnProgressChangeListener = onProgressChangeListener;
    }
}
