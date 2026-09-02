package com.deye.activity.message;

import android.content.Context;
import android.content.Intent;
import com.deye.TabMainActivity;
import com.deye.event.AcceptEvent;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.ShareUserResult;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* compiled from: AcceptActivity.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/ShareUserResult;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class AcceptActivity$initView$2$1<T> implements Consumer {
    final /* synthetic */ AcceptActivity this$0;

    AcceptActivity$initView$2$1(AcceptActivity acceptActivity) {
        this.this$0 = acceptActivity;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<ShareUserResult> it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        BaseResult.MetaBean meta = it2.getMeta();
        if (meta != null && meta.getCode() == 0) {
            BaseUtils.showShortToast(R.string.device_addition_success);
            this.this$0.mContext.startActivity(new Intent((Context) this.this$0.mContext, (Class<?>) TabMainActivity.class));
            EventBus.getDefault().post(new AcceptEvent());
            this.this$0.finish();
            return;
        }
        BaseResult.MetaBean meta2 = it2.getMeta();
        BaseUtils.showShortToast(String.valueOf(meta2 != null ? meta2.getMessage() : null));
    }
}
