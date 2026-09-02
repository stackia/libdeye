package com.deye.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class CustomFontTextView extends AppCompatTextView {
    public CustomFontTextView(Context context) {
        super(context);
        init(context);
    }

    public CustomFontTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public CustomFontTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public void init(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), StubApp.getString2(14568)));
    }
}
