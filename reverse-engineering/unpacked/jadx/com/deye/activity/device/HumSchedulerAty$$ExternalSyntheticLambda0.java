package com.deye.activity.device;

import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final /* synthetic */ class HumSchedulerAty$$ExternalSyntheticLambda0 implements OnItemSelectedListener {
    public final /* synthetic */ WheelView f$0;
    public final /* synthetic */ WheelView f$1;
    public final /* synthetic */ HumSchedulerAty f$2;

    public /* synthetic */ HumSchedulerAty$$ExternalSyntheticLambda0(WheelView wheelView, WheelView wheelView2, HumSchedulerAty humSchedulerAty) {
        this.f$0 = wheelView;
        this.f$1 = wheelView2;
        this.f$2 = humSchedulerAty;
    }

    @Override // com.contrarywind.listener.OnItemSelectedListener
    public final void onItemSelected(int i) {
        HumSchedulerAty.initData$lambda$1(this.f$0, this.f$1, this.f$2, i);
    }
}
