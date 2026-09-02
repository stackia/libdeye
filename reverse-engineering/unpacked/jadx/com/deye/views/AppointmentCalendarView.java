package com.deye.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.stub.StubApp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class AppointmentCalendarView extends LinearLayout {
    String[] dayArray;
    private AppointmentDayView lastCheckedView;
    private OnDayCheckedListener onDayCheckedListener;

    public interface OnDayCheckedListener {
        void onCheck(AppointmentDayView appointmentDayView);
    }

    public void setOnDayCheckedListener(OnDayCheckedListener onDayCheckedListener) {
        this.onDayCheckedListener = onDayCheckedListener;
    }

    public AppointmentCalendarView(Context context) {
        super(context);
        this.dayArray = new String[]{StubApp.getString2(14506), StubApp.getString2(14507), StubApp.getString2(14508), StubApp.getString2(14509), StubApp.getString2(14510), StubApp.getString2(14511), StubApp.getString2(14512)};
        this.lastCheckedView = null;
        init(context);
    }

    public AppointmentCalendarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.dayArray = new String[]{StubApp.getString2(14506), StubApp.getString2(14507), StubApp.getString2(14508), StubApp.getString2(14509), StubApp.getString2(14510), StubApp.getString2(14511), StubApp.getString2(14512)};
        this.lastCheckedView = null;
        init(context);
    }

    public AppointmentCalendarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.dayArray = new String[]{StubApp.getString2(14506), StubApp.getString2(14507), StubApp.getString2(14508), StubApp.getString2(14509), StubApp.getString2(14510), StubApp.getString2(14511), StubApp.getString2(14512)};
        this.lastCheckedView = null;
        init(context);
    }

    private void init(Context context) {
        setWeightSum(7.0f);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int i = calendar.get(7) - 1;
        for (int i2 = 0; i2 < 7; i2++) {
            final AppointmentDayView appointmentDayView = new AppointmentDayView(context);
            appointmentDayView.setDayText(this.dayArray[i2]);
            appointmentDayView.setDayId(i2);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
            layoutParams.weight = 1.0f;
            appointmentDayView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.AppointmentCalendarView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (appointmentDayView.isChecked()) {
                        return;
                    }
                    if (AppointmentCalendarView.this.lastCheckedView != null) {
                        AppointmentCalendarView.this.lastCheckedView.setChecked(false);
                    }
                    AppointmentCalendarView.this.lastCheckedView = appointmentDayView;
                    appointmentDayView.setChecked(true);
                    if (AppointmentCalendarView.this.onDayCheckedListener != null) {
                        AppointmentCalendarView.this.onDayCheckedListener.onCheck(appointmentDayView);
                    }
                }
            });
            if (i2 == i) {
                appointmentDayView.performClick();
            }
            addView(appointmentDayView, layoutParams);
        }
    }

    public void setHasDataDays(List<Integer> list) {
        for (int i = 0; i < 7; i++) {
            ((AppointmentDayView) getChildAt(i)).setHasData(list.contains(Integer.valueOf(i)));
        }
    }
}
