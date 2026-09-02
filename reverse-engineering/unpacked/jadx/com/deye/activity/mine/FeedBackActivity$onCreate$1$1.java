package com.deye.activity.mine;

import com.deye.helper.DialogHelper;
import kotlin.Metadata;

/* compiled from: FeedBackActivity.kt */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/deye/activity/mine/FeedBackActivity$onCreate$1$1", "Lcom/deye/helper/DialogHelper$OnDialogListener;", "onSure", "", "text", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class FeedBackActivity$onCreate$1$1 extends DialogHelper.OnDialogListener {
    final /* synthetic */ FeedBackActivity this$0;

    FeedBackActivity$onCreate$1$1(FeedBackActivity feedBackActivity) {
        this.this$0 = feedBackActivity;
    }

    @Override // com.deye.helper.DialogHelper.OnDialogListener
    public void onSure(String text) {
        this.this$0.finish();
    }
}
