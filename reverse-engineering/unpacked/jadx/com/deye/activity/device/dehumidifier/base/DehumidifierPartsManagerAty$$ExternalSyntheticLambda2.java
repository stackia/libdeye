package com.deye.activity.device.dehumidifier.base;

import android.view.View;
import com.deye.views.button.TextSwitchButton;
import io.fogcloud.sdk.fog.bean.PartBean;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final /* synthetic */ class DehumidifierPartsManagerAty$$ExternalSyntheticLambda2 implements TextSwitchButton.OnClick {
    public final /* synthetic */ DehumidifierPartsManagerAty f$0;
    public final /* synthetic */ PartBean f$1;

    public /* synthetic */ DehumidifierPartsManagerAty$$ExternalSyntheticLambda2(DehumidifierPartsManagerAty dehumidifierPartsManagerAty, PartBean partBean) {
        this.f$0 = dehumidifierPartsManagerAty;
        this.f$1 = partBean;
    }

    @Override // com.deye.views.button.TextSwitchButton.OnClick
    public final void onClick(View view, boolean z) {
        DehumidifierPartsManagerAty.bindPartView$lambda$3(this.f$0, this.f$1, view, z);
    }
}
