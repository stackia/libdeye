package com.deye.activity.message;

import android.view.View;
import io.fogcloud.sdk.fog.bean.MessageBean;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final /* synthetic */ class MsgDetailActivity$$ExternalSyntheticLambda1 implements View.OnClickListener {
    public final /* synthetic */ MessageBean f$0;
    public final /* synthetic */ MsgDetailActivity f$1;

    public /* synthetic */ MsgDetailActivity$$ExternalSyntheticLambda1(MessageBean messageBean, MsgDetailActivity msgDetailActivity) {
        this.f$0 = messageBean;
        this.f$1 = msgDetailActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        MsgDetailActivity.initView$lambda$3(this.f$0, this.f$1, view);
    }
}
