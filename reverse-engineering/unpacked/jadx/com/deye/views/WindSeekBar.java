package com.deye.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.mxchipapp.R;
import com.stub.StubApp;
import com.zhouyou.view.seekbar.SignUtils;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class WindSeekBar extends View {
    final String TAG;
    private Paint bgPaint;
    private int curStep;
    private Paint frontPaint;
    private boolean isThumbOnDragging;
    private int lineH;
    private Paint linePaint;
    private int lineW;
    private Context mContext;
    private IWindChangeListener mWinChangeListener;
    private Bitmap maxNormalBitmap;
    private Bitmap maxWhiteBitmap;
    private Bitmap minBitmap;
    private Paint thumbPaint;
    private Rect thumbRect;
    private final int thumbW;
    private final int totalStep;
    private float touchX;
    private float touchY;
    private int unitW;

    public void setWinChangeListener(IWindChangeListener iWindChangeListener) {
        this.mWinChangeListener = iWindChangeListener;
    }

    public WindSeekBar(Context context) {
        super(context);
        this.TAG = StubApp.getString2(14658);
        this.curStep = 3;
        this.totalStep = 5;
        this.isThumbOnDragging = false;
        this.thumbW = SignUtils.dp2px(12);
        init(context);
    }

    public WindSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = StubApp.getString2(14658);
        this.curStep = 3;
        this.totalStep = 5;
        this.isThumbOnDragging = false;
        this.thumbW = SignUtils.dp2px(12);
        init(context);
    }

    public WindSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = StubApp.getString2(14658);
        this.curStep = 3;
        this.totalStep = 5;
        this.isThumbOnDragging = false;
        this.thumbW = SignUtils.dp2px(12);
        init(context);
    }

    public void setCurStep(int i) {
        this.curStep = i;
        invalidate();
    }

    private void init(Context context) {
        this.mContext = context;
        Paint paint = new Paint();
        this.bgPaint = paint;
        paint.setStrokeWidth(2.0f);
        this.bgPaint.setColor(Color.parseColor(StubApp.getString2(14659)));
        this.bgPaint.setStyle(Paint.Style.FILL);
        this.bgPaint.setStrokeCap(Paint.Cap.BUTT);
        Paint paint2 = new Paint();
        this.frontPaint = paint2;
        paint2.setStrokeWidth(2.0f);
        this.frontPaint.setColor(Color.parseColor(StubApp.getString2(13225)));
        this.frontPaint.setStyle(Paint.Style.FILL);
        this.frontPaint.setStrokeCap(Paint.Cap.BUTT);
        this.minBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.ic_seekbar_speed_min_white);
        this.maxNormalBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.ic_seekbar_speed_max_normal);
        this.maxWhiteBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.ic_seekbar_speed_max_white);
        this.lineH = SignUtils.dp2px(14);
        this.lineW = SignUtils.dp2px(1);
        Paint paint3 = new Paint();
        this.linePaint = paint3;
        paint3.setStrokeWidth(this.lineW);
        this.linePaint.setColor(Color.parseColor(StubApp.getString2(14660)));
        this.linePaint.setStyle(Paint.Style.FILL);
        this.linePaint.setStrokeCap(Paint.Cap.BUTT);
        Paint paint4 = new Paint();
        this.thumbPaint = paint4;
        paint4.setStrokeWidth(this.lineW);
        this.thumbPaint.setColor(Color.parseColor(StubApp.getString2(14539)));
        this.thumbPaint.setStyle(Paint.Style.FILL);
        this.thumbPaint.setStrokeCap(Paint.Cap.BUTT);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.unitW = getWidth() / 5;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);
        drawThumb(canvas);
    }

    protected void drawThumb(Canvas canvas) {
        int iDp2px = SignUtils.dp2px(4);
        float fDp2px = SignUtils.dp2px(8);
        if (!this.isThumbOnDragging) {
            int i = this.unitW;
            int i2 = this.curStep;
            this.thumbRect = new Rect(((i * i2) - iDp2px) - this.thumbW, iDp2px, (i * i2) - iDp2px, getHeight() - iDp2px);
            int i3 = this.unitW;
            int i4 = this.curStep;
            canvas.drawRoundRect(((i3 * i4) - iDp2px) - this.thumbW, iDp2px, (i3 * i4) - iDp2px, getHeight() - iDp2px, fDp2px, fDp2px, this.thumbPaint);
            return;
        }
        int i5 = this.unitW;
        int i6 = this.curStep;
        this.thumbRect = new Rect(((i5 * i6) - iDp2px) - this.thumbW, iDp2px, (i5 * i6) - iDp2px, getHeight() - iDp2px);
        float f = this.touchX;
        int i7 = this.thumbW;
        canvas.drawRoundRect(f - (i7 / 2.0f), iDp2px, f + (i7 / 2.0f), getHeight() - iDp2px, fDp2px, fDp2px, this.thumbPaint);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0011  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
            if (isThumbTouched(motionEvent)) {
                this.isThumbOnDragging = true;
            } else {
                this.curStep = (int) Math.ceil(motionEvent.getX() / this.unitW);
                this.isThumbOnDragging = false;
                callBackStepChange();
            }
        } else {
            if (actionMasked == 1) {
                if (this.isThumbOnDragging) {
                    this.isThumbOnDragging = false;
                    int iCeil = (int) Math.ceil(motionEvent.getX() / this.unitW);
                    this.curStep = iCeil;
                    if (iCeil > 5) {
                        this.curStep = 5;
                    }
                    if (this.curStep < 1) {
                        this.curStep = 1;
                    }
                    callBackStepChange();
                }
                getParent().requestDisallowInterceptTouchEvent(false);
            } else if (actionMasked != 2) {
                if (actionMasked == 3) {
                }
            }
            invalidate();
            return true;
        }
        if (this.isThumbOnDragging) {
            this.touchX = motionEvent.getX();
            this.touchY = motionEvent.getY();
        }
        invalidate();
        return true;
    }

    void callBackStepChange() {
        IWindChangeListener iWindChangeListener = this.mWinChangeListener;
        if (iWindChangeListener != null) {
            iWindChangeListener.onWindChange(this.curStep);
        }
    }

    private boolean isThumbTouched(MotionEvent motionEvent) {
        if (new Rect(this.thumbRect.left - 10, 0, this.thumbRect.right + 10, getHeight()).contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return true;
        }
        Log.e(StubApp.getString2(14658), StubApp.getString2(14661));
        return false;
    }

    protected void drawBg(Canvas canvas) {
        canvas.drawRoundRect(0.0f, 0.0f, getWidth(), getHeight(), SignUtils.dp2px(8), SignUtils.dp2px(8), this.bgPaint);
        if (!this.isThumbOnDragging) {
            canvas.drawRoundRect(0.0f, 0.0f, (getWidth() * this.curStep) / 5.0f, getHeight(), SignUtils.dp2px(8), SignUtils.dp2px(8), this.frontPaint);
        } else {
            float fDp2px = this.touchX + (this.thumbW / 2.0f) + SignUtils.dp2px(4);
            if (fDp2px > getWidth()) {
                fDp2px = getWidth();
            }
            int i = this.unitW;
            if (fDp2px < i) {
                fDp2px = i;
            }
            canvas.drawRoundRect(0.0f, 0.0f, fDp2px, getHeight(), SignUtils.dp2px(8), SignUtils.dp2px(8), this.frontPaint);
        }
        canvas.drawBitmap(this.minBitmap, (Rect) null, new Rect((this.unitW / 2) - (this.minBitmap.getWidth() / 2), (getHeight() / 2) - (this.minBitmap.getHeight() / 2), (this.unitW / 2) + (this.minBitmap.getWidth() / 2), (getHeight() / 2) + (this.minBitmap.getHeight() / 2)), this.frontPaint);
        Bitmap bitmap = this.maxNormalBitmap;
        if (this.curStep == 9) {
            bitmap = this.maxWhiteBitmap;
        }
        canvas.drawBitmap(bitmap, (Rect) null, new Rect((getWidth() - (this.unitW / 2)) - (bitmap.getWidth() / 2), (getHeight() / 2) - (bitmap.getHeight() / 2), (getWidth() - (this.unitW / 2)) + (bitmap.getWidth() / 2), (getHeight() / 2) + (bitmap.getHeight() / 2)), this.frontPaint);
        for (int i2 = 1; i2 <= 5; i2++) {
            if (this.isThumbOnDragging) {
                int height = getHeight();
                canvas.drawLine((this.unitW * i2) - (this.lineW / 2.0f), (getHeight() - this.lineH) / 2.0f, (this.unitW * i2) - (this.lineW / 2.0f), ((height - r3) / 2.0f) + this.lineH, this.linePaint);
            } else if (i2 != this.curStep) {
                int height2 = getHeight();
                canvas.drawLine((this.unitW * i2) - (this.lineW / 2.0f), (getHeight() - this.lineH) / 2.0f, (this.unitW * i2) - (this.lineW / 2.0f), ((height2 - r3) / 2.0f) + this.lineH, this.linePaint);
            }
        }
    }
}
