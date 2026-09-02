package com.deye.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WindSpeedView.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0016\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/deye/views/WindSpeedView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "gradientDrawable", "Landroid/graphics/drawable/GradientDrawable;", "mPaint", "Landroid/graphics/Paint;", "mRadius", "init", "", "updateStyle", "position", "isCheck", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class WindSpeedView extends LinearLayout {
    private GradientDrawable gradientDrawable;
    private final Paint mPaint;
    private int mRadius;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WindSpeedView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WindSpeedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WindSpeedView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    private final void init(Context context) {
        this.mRadius = DensityUtil.dp2px(8.0f);
    }

    public final void updateStyle(int position, boolean isCheck) {
        String string2;
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.gradientDrawable = gradientDrawable;
        Intrinsics.checkNotNull(gradientDrawable);
        gradientDrawable.setShape(0);
        if (position == 0) {
            GradientDrawable gradientDrawable2 = this.gradientDrawable;
            Intrinsics.checkNotNull(gradientDrawable2);
            int i = this.mRadius;
            gradientDrawable2.setCornerRadii(new float[]{i, i, 0.0f, 0.0f, 0.0f, 0.0f, i, i});
        } else if (position == 2) {
            GradientDrawable gradientDrawable3 = this.gradientDrawable;
            Intrinsics.checkNotNull(gradientDrawable3);
            int i2 = this.mRadius;
            gradientDrawable3.setCornerRadii(new float[]{0.0f, 0.0f, i2, i2, i2, i2, 0.0f, 0.0f});
        } else {
            GradientDrawable gradientDrawable4 = this.gradientDrawable;
            Intrinsics.checkNotNull(gradientDrawable4);
            gradientDrawable4.setCornerRadii(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f});
        }
        GradientDrawable gradientDrawable5 = this.gradientDrawable;
        Intrinsics.checkNotNull(gradientDrawable5);
        if (isCheck) {
            string2 = StubApp.getString2(13225);
        } else {
            string2 = StubApp.getString2(14662);
        }
        gradientDrawable5.setColor(Color.parseColor(string2));
        setBackground(this.gradientDrawable);
    }
}
