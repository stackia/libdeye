package com.deye.activity.device.appointment;

import android.view.View;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import io.fogcloud.sdk.fog.bean.SchedulerGroupBean;
import java.util.Date;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final /* synthetic */ class AddAppointmentAty$$ExternalSyntheticLambda0 implements OnTimeSelectListener {
    public final /* synthetic */ SchedulerGroupBean.SchedulerSwitchBean f$0;
    public final /* synthetic */ AddAppointmentAty f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ AddAppointmentAty$$ExternalSyntheticLambda0(SchedulerGroupBean.SchedulerSwitchBean schedulerSwitchBean, AddAppointmentAty addAppointmentAty, boolean z) {
        this.f$0 = schedulerSwitchBean;
        this.f$1 = addAppointmentAty;
        this.f$2 = z;
    }

    @Override // com.bigkoo.pickerview.listener.OnTimeSelectListener
    public final void onTimeSelect(Date date, View view) {
        AddAppointmentAty.showTimePicker$lambda$10(this.f$0, this.f$1, this.f$2, date, view);
    }
}
