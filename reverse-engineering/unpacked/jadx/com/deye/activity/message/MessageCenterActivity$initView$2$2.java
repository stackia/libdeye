package com.deye.activity.message;

import com.mxchipapp.R;
import com.mxchipapp.databinding.ActivityMessageCenterBinding;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.SimpleResultBean;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MessageCenterActivity.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/SimpleResultBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class MessageCenterActivity$initView$2$2<T> implements Consumer {
    final /* synthetic */ MessageCenterActivity this$0;

    MessageCenterActivity$initView$2$2(MessageCenterActivity messageCenterActivity) {
        this.this$0 = messageCenterActivity;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<SimpleResultBean> it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        this.this$0.getMessageListAdapter().updateAllReadUi();
        ActivityMessageCenterBinding activityMessageCenterBinding = this.this$0.activityMessageCenterBinding;
        if (activityMessageCenterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityMessageCenterBinding");
            activityMessageCenterBinding = null;
        }
        activityMessageCenterBinding.actionbarTitle.setText(R.string.message_center_title);
    }
}
