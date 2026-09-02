package com.deye.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.mxchipapp.R;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class WebViewProgressBar extends View {
    private static final int HEIGHT = 5;
    private Paint paint;
    private int progress;

    public WebViewProgressBar(Context context) {
        this(context, null);
    }

    public WebViewProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WebViewProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.progress = 1;
        initPaint(context);
    }

    private void initPaint(Context context) {
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, getResources().getDimension(2131166362), 5.0f, getResources().getColor(R.color.color_text_43D1FF), getResources().getColor(R.color.color_text_383AFF), Shader.TileMode.MIRROR);
        Paint paint = new Paint(4);
        this.paint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(5.0f);
        this.paint.setAntiAlias(true);
        this.paint.setDither(true);
        this.paint.setShader(linearGradient);
    }

    public void setProgress(int i) {
        this.progress = i;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, (getWidth() * this.progress) / 100, 5.0f, this.paint);
    }
}
