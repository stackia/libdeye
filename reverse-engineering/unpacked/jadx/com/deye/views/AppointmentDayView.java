package com.deye.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.mxchipapp.databinding.ViewAppointmentDayBinding;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class AppointmentDayView extends LinearLayout {
    private ViewAppointmentDayBinding binding;
    private int dayId;
    private boolean hiddenBottom;
    private boolean isChecked;

    public AppointmentDayView(Context context) {
        super(context);
        this.hiddenBottom = false;
        this.dayId = -1;
        init(context);
    }

    public AppointmentDayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.hiddenBottom = false;
        this.dayId = -1;
        init(context);
    }

    public AppointmentDayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.hiddenBottom = false;
        this.dayId = -1;
        init(context);
    }

    private void init(Context context) {
        this.binding = ViewAppointmentDayBinding.inflate(LayoutInflater.from(context), this, true);
    }

    public void setHiddenBottom(boolean z) {
        this.hiddenBottom = z;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
        this.binding.checkboxDay.setChecked(z);
    }

    public void setHasData(boolean z) {
        this.binding.imageviewDay.setVisibility(z ? 0 : 8);
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setDayText(String str) {
        this.binding.checkboxDay.setText(str);
    }

    public void setDayId(int i) {
        this.dayId = i;
    }

    public int getDayId() {
        return this.dayId;
    }
}
