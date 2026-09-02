package com.deye.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class CenterAlignImageSpan extends ImageSpan {
    public CenterAlignImageSpan(Drawable drawable) {
        super(drawable);
    }

    public CenterAlignImageSpan(Context context, int i, int i2) {
        super(context, i, i2);
    }

    public CenterAlignImageSpan(Bitmap bitmap) {
        super(bitmap);
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        int i6;
        Drawable drawable = getDrawable();
        canvas.save();
        int i7 = i5 - drawable.getBounds().bottom;
        int i8 = this.mVerticalAlignment;
        if (this.mVerticalAlignment == 1) {
            i6 = i7 - paint.getFontMetricsInt().descent;
        } else {
            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            i6 = ((((fontMetricsInt.descent + i4) + i4) + fontMetricsInt.ascent) / 2) - (drawable.getBounds().bottom / 2);
        }
        canvas.translate(f, i6);
        drawable.draw(canvas);
        canvas.restore();
    }
}
