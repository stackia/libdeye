package com.deye.activity.device.humidifier.base;

import android.view.View;
import com.deye.views.button.TextSwitchButton;
import io.fogcloud.sdk.fog.bean.PartBean;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final /* synthetic */ class PartsManagerAty$$ExternalSyntheticLambda2 implements TextSwitchButton.OnClick {
    public final /* synthetic */ PartsManagerAty f$0;
    public final /* synthetic */ PartBean f$1;

    public /* synthetic */ PartsManagerAty$$ExternalSyntheticLambda2(PartsManagerAty partsManagerAty, PartBean partBean) {
        this.f$0 = partsManagerAty;
        this.f$1 = partBean;
    }

    @Override // com.deye.views.button.TextSwitchButton.OnClick
    public final void onClick(View view, boolean z) {
        PartsManagerAty.initViewByData$lambda$2(this.f$0, this.f$1, view, z);
    }
}
