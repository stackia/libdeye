package com.deye.activity.mine;

import android.view.View;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final /* synthetic */ class SystemSettingsActivity$$ExternalSyntheticLambda2 implements OnOptionsSelectListener {
    public final /* synthetic */ Integer[] f$0;
    public final /* synthetic */ SystemSettingsActivity f$1;

    public /* synthetic */ SystemSettingsActivity$$ExternalSyntheticLambda2(Integer[] numArr, SystemSettingsActivity systemSettingsActivity) {
        this.f$0 = numArr;
        this.f$1 = systemSettingsActivity;
    }

    @Override // com.bigkoo.pickerview.listener.OnOptionsSelectListener
    public final void onOptionsSelect(int i, int i2, int i3, View view) {
        SystemSettingsActivity.showTemperaturePicker$lambda$2(this.f$0, this.f$1, i, i2, i3, view);
    }
}
