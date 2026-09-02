package com.deye.activity.device.appointment;

import android.view.View;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final /* synthetic */ class AddAppointmentAty$$ExternalSyntheticLambda5 implements OnOptionsSelectListener {
    public final /* synthetic */ AddAppointmentAty f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ AddAppointmentAty$$ExternalSyntheticLambda5(AddAppointmentAty addAppointmentAty, List list) {
        this.f$0 = addAppointmentAty;
        this.f$1 = list;
    }

    @Override // com.bigkoo.pickerview.listener.OnOptionsSelectListener
    public final void onOptionsSelect(int i, int i2, int i3, View view) {
        AddAppointmentAty.showSpeedPicker$lambda$15(this.f$0, this.f$1, i, i2, i3, view);
    }
}
