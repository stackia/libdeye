package com.deye.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.mxchipapp.R;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class CustomFontTextUnitView extends LinearLayout {
    private final CustomFontTextView textUnitView;
    private final CustomFontTextView textValueView;

    public CustomFontTextUnitView(Context context) {
        this(context, null);
    }

    public CustomFontTextUnitView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomFontTextUnitView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R.layout.customer_text_unit_view, this);
        CustomFontTextView customFontTextView = (CustomFontTextView) findViewById(R.id.text_value);
        this.textValueView = customFontTextView;
        CustomFontTextView customFontTextView2 = (CustomFontTextView) findViewById(R.id.text_unit);
        this.textUnitView = customFontTextView2;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.CustomFontTextUnitView, i, 0);
        String string = typedArrayObtainStyledAttributes.getString(2);
        float integer = typedArrayObtainStyledAttributes.getInteger(3, 24);
        String string2 = typedArrayObtainStyledAttributes.getString(0);
        float integer2 = typedArrayObtainStyledAttributes.getInteger(1, 12);
        typedArrayObtainStyledAttributes.recycle();
        customFontTextView.setTextSize(integer);
        customFontTextView.setText(string);
        customFontTextView2.setTextSize(integer2);
        customFontTextView2.setText(string2);
    }

    public void setHighlight(boolean z) {
        if (z) {
            Typeface typefaceCreateFromAsset = Typeface.createFromAsset(getContext().getAssets(), StubApp.getString2(14568));
            this.textUnitView.setTypeface(typefaceCreateFromAsset);
            this.textUnitView.setTypeface(typefaceCreateFromAsset);
            this.textUnitView.setTextSize(14.0f);
            this.textValueView.setTextSize(24.0f);
            return;
        }
        Typeface typefaceCreateFromAsset2 = Typeface.createFromAsset(getContext().getAssets(), StubApp.getString2(14569));
        this.textUnitView.setTypeface(typefaceCreateFromAsset2);
        this.textUnitView.setTypeface(typefaceCreateFromAsset2);
        this.textUnitView.setTextSize(12.0f);
        this.textValueView.setTextSize(16.0f);
    }

    public void setValueText(String str) {
        this.textValueView.setText(str);
    }

    public void setValueTextSpSize(int i) {
        this.textValueView.setTextSize(i);
    }

    public void setUnitTextSpSize(int i) {
        this.textUnitView.setTextSize(i);
    }

    public void setUnitText(String str) {
        this.textUnitView.setText(str);
    }

    public void setDark60Color() {
        this.textUnitView.setTextColor(getResources().getColor(R.color.dark60));
        this.textValueView.setTextColor(getResources().getColor(R.color.dark60));
    }

    public void setTextColor(int i) {
        this.textUnitView.setTextColor(i);
        this.textValueView.setTextColor(i);
    }
}
