package com.deye.activity.mine;

import android.view.View;
import com.deye.views.UploadPicView;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final /* synthetic */ class FeedBackActivity$$ExternalSyntheticLambda2 implements View.OnClickListener {
    public final /* synthetic */ FeedBackActivity f$0;
    public final /* synthetic */ UploadPicView f$1;

    public /* synthetic */ FeedBackActivity$$ExternalSyntheticLambda2(FeedBackActivity feedBackActivity, UploadPicView uploadPicView) {
        this.f$0 = feedBackActivity;
        this.f$1 = uploadPicView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        FeedBackActivity.onPicResult$lambda$5(this.f$0, this.f$1, view);
    }
}
