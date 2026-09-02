package com.deye.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddAppointmentCalendarView.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001dB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0016J\u0014\u0010\u001a\u001a\u00020\u00182\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u001cR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/deye/views/AddAppointmentCalendarView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "choiceList", "", "dayArray", "", "", "getDayArray", "()[Ljava/lang/String;", "setDayArray", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "onDayCheckedListener", "Lcom/deye/views/AddAppointmentCalendarView$OnDayCheckedListener;", "init", "", "setOnDayCheckedListener", "setRepeatDay", "allDays", "", "OnDayCheckedListener", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class AddAppointmentCalendarView extends LinearLayout {
    private final List<Integer> choiceList;
    private String[] dayArray;
    private OnDayCheckedListener onDayCheckedListener;

    /* compiled from: AddAppointmentCalendarView.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/deye/views/AddAppointmentCalendarView$OnDayCheckedListener;", "", "onCheck", "", "days", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface OnDayCheckedListener {
        void onCheck(String days);
    }

    public final String[] getDayArray() {
        return this.dayArray;
    }

    public final void setDayArray(String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        this.dayArray = strArr;
    }

    public final void setOnDayCheckedListener(OnDayCheckedListener onDayCheckedListener) {
        Intrinsics.checkNotNullParameter(onDayCheckedListener, "onDayCheckedListener");
        this.onDayCheckedListener = onDayCheckedListener;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddAppointmentCalendarView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.dayArray = new String[]{StubApp.getString2(14506), StubApp.getString2(14507), StubApp.getString2(14508), StubApp.getString2(14509), StubApp.getString2(14510), StubApp.getString2(14511), StubApp.getString2(14512)};
        this.choiceList = new ArrayList();
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddAppointmentCalendarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.dayArray = new String[]{StubApp.getString2(14506), StubApp.getString2(14507), StubApp.getString2(14508), StubApp.getString2(14509), StubApp.getString2(14510), StubApp.getString2(14511), StubApp.getString2(14512)};
        this.choiceList = new ArrayList();
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddAppointmentCalendarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.dayArray = new String[]{StubApp.getString2(14506), StubApp.getString2(14507), StubApp.getString2(14508), StubApp.getString2(14509), StubApp.getString2(14510), StubApp.getString2(14511), StubApp.getString2(14512)};
        this.choiceList = new ArrayList();
        init(context);
    }

    private final void init(Context context) {
        setWeightSum(7.0f);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int i = calendar.get(7) - 1;
        for (final int i2 = 0; i2 < 7; i2++) {
            final AppointmentDayView appointmentDayView = new AppointmentDayView(context);
            appointmentDayView.setHiddenBottom(true);
            appointmentDayView.setDayText(this.dayArray[i2]);
            appointmentDayView.setDayId(i2);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
            layoutParams.weight = 1.0f;
            appointmentDayView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.AddAppointmentCalendarView$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AddAppointmentCalendarView.init$lambda$1(appointmentDayView, this, i2, view);
                }
            });
            if (i2 == i) {
                appointmentDayView.performClick();
            }
            addView(appointmentDayView, layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(AppointmentDayView view, AddAppointmentCalendarView this$0, int i, View view2) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (view.isChecked()) {
            view.setChecked(false);
            this$0.choiceList.remove(Integer.valueOf(i));
        } else {
            view.setChecked(true);
            this$0.choiceList.add(Integer.valueOf(i));
        }
        OnDayCheckedListener onDayCheckedListener = this$0.onDayCheckedListener;
        if (onDayCheckedListener != null) {
            onDayCheckedListener.onCheck(CollectionsKt.joinToString$default(this$0.choiceList, StubApp.getString2(450), null, null, 0, null, null, 62, null));
        }
    }

    public final void setRepeatDay(List<Integer> allDays) {
        Intrinsics.checkNotNullParameter(allDays, "allDays");
        this.choiceList.clear();
        this.choiceList.addAll(allDays);
        for (int i = 0; i < 7; i++) {
            View childAt = getChildAt(i);
            Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type com.deye.views.AppointmentDayView");
            ((AppointmentDayView) childAt).setChecked(allDays.contains(Integer.valueOf(i)));
        }
    }
}
