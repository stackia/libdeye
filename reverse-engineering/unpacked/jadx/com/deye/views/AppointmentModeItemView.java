package com.deye.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ViewAppointmentModeItemBinding;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class AppointmentModeItemView extends LinearLayout {
    private ViewAppointmentModeItemBinding binding;
    private int mode;

    private void init(Context context) {
    }

    public AppointmentModeItemView(Context context) {
        this(context, null);
    }

    public AppointmentModeItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AppointmentModeItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.binding = ViewAppointmentModeItemBinding.inflate(LayoutInflater.from(context), this, true);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.AppointModeItemView, i, 0);
        String string = typedArrayObtainStyledAttributes.getString(1);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(3);
        Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(2);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        this.binding.ivNormal.setBackground(drawable2);
        this.binding.ivSelected.setBackground(drawable);
        this.binding.tvMode.setText(string);
        setSelected(z);
    }

    public void setMode(int i) {
        this.mode = i;
    }

    public int getMode() {
        return this.mode;
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        if (z) {
            this.binding.ivSelected.setVisibility(0);
            this.binding.tvMode.setTextColor(Color.parseColor(StubApp.getString2(13225)));
            this.binding.tvMode.setTypeface(null, 1);
        } else {
            this.binding.ivSelected.setVisibility(8);
            this.binding.tvMode.setTextColor(Color.parseColor(StubApp.getString2(13488)));
            this.binding.tvMode.setTypeface(null, 0);
        }
    }
}
