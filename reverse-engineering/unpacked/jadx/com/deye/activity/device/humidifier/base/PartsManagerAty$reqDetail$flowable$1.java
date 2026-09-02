package com.deye.activity.device.humidifier.base;

import com.deye.utils.BaseUtils;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.PartBean;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PartsManagerAty.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "", "Lio/fogcloud/sdk/fog/bean/PartBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class PartsManagerAty$reqDetail$flowable$1<T> implements Consumer {
    final /* synthetic */ PartsManagerAty this$0;

    PartsManagerAty$reqDetail$flowable$1(PartsManagerAty partsManagerAty) {
        this.this$0 = partsManagerAty;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<List<PartBean>> it2) {
        Object message;
        Intrinsics.checkNotNullParameter(it2, "it");
        this.this$0.hideLoading();
        BaseResult.MetaBean meta = it2.getMeta();
        if (meta != null && meta.getCode() == 0) {
            List<PartBean> data = it2.getData();
            if (data != null) {
                this.this$0.initViewByData(data);
                return;
            }
            return;
        }
        BaseResult.MetaBean meta2 = it2.getMeta();
        if (meta2 == null || (message = meta2.getMessage()) == null) {
            return;
        }
        BaseUtils.showShortToast(message.toString());
    }
}
