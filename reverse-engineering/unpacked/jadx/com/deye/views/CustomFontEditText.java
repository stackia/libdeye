package com.deye.views;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class CustomFontEditText extends AppCompatEditText {
    public CustomFontEditText(Context context) {
        super(context);
        init(context);
    }

    public CustomFontEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public CustomFontEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public void init(Context context) {
        final Typeface typefaceCreateFromAsset = Typeface.createFromAsset(context.getAssets(), StubApp.getString2(14568));
        setTextSize(14.0f);
        setTypeface(Typeface.DEFAULT);
        addTextChangedListener(new TextWatcher() { // from class: com.deye.views.CustomFontEditText.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() == 0) {
                    CustomFontEditText.this.setTypeface(Typeface.DEFAULT);
                    CustomFontEditText.this.setTextSize(14.0f);
                } else {
                    CustomFontEditText.this.setTypeface(typefaceCreateFromAsset);
                    CustomFontEditText.this.setTextSize(20.0f);
                }
            }
        });
    }
}
