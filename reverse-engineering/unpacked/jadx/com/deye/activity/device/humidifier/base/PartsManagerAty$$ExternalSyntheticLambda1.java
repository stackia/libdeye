package com.deye.activity.device.humidifier.base;

import android.view.View;
import io.fogcloud.sdk.fog.bean.PartBean;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final /* synthetic */ class PartsManagerAty$$ExternalSyntheticLambda1 implements View.OnClickListener {
    public final /* synthetic */ PartsManagerAty f$0;
    public final /* synthetic */ PartBean f$1;

    public /* synthetic */ PartsManagerAty$$ExternalSyntheticLambda1(PartsManagerAty partsManagerAty, PartBean partBean) {
        this.f$0 = partsManagerAty;
        this.f$1 = partBean;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        PartsManagerAty.initViewByData$lambda$1(this.f$0, this.f$1, view);
    }
}
