package com.deye.activity.device.dehumidifier.base;

import android.view.View;
import io.fogcloud.sdk.fog.bean.PartBean;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final /* synthetic */ class DehumidifierPartsManagerAty$$ExternalSyntheticLambda1 implements View.OnClickListener {
    public final /* synthetic */ DehumidifierPartsManagerAty f$0;
    public final /* synthetic */ PartBean f$1;

    public /* synthetic */ DehumidifierPartsManagerAty$$ExternalSyntheticLambda1(DehumidifierPartsManagerAty dehumidifierPartsManagerAty, PartBean partBean) {
        this.f$0 = dehumidifierPartsManagerAty;
        this.f$1 = partBean;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        DehumidifierPartsManagerAty.bindPartView$lambda$2(this.f$0, this.f$1, view);
    }
}
