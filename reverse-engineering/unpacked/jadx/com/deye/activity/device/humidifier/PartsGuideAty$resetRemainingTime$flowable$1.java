package com.deye.activity.device.humidifier;

import com.deye.utils.BaseUtils;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.SimpleResultBean;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PartsGuideAty.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/SimpleResultBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class PartsGuideAty$resetRemainingTime$flowable$1<T> implements Consumer {
    final /* synthetic */ PartsGuideAty this$0;

    PartsGuideAty$resetRemainingTime$flowable$1(PartsGuideAty partsGuideAty) {
        this.this$0 = partsGuideAty;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<SimpleResultBean> it2) {
        BaseResult.MetaBean meta;
        Object message;
        Intrinsics.checkNotNullParameter(it2, "it");
        this.this$0.hideLoading();
        BaseResult.MetaBean meta2 = it2.getMeta();
        if ((meta2 != null && meta2.getCode() == 0) || (meta = it2.getMeta()) == null || (message = meta.getMessage()) == null) {
            return;
        }
        BaseUtils.showShortToast(message.toString());
    }
}
