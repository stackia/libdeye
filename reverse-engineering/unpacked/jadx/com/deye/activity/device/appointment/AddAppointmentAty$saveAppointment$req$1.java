package com.deye.activity.device.appointment;

import android.content.Context;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.SimpleResultBean;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddAppointmentAty.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/SimpleResultBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class AddAppointmentAty$saveAppointment$req$1<T> implements Consumer {
    final /* synthetic */ AddAppointmentAty this$0;

    AddAppointmentAty$saveAppointment$req$1(AddAppointmentAty addAppointmentAty) {
        this.this$0 = addAppointmentAty;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<SimpleResultBean> it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        this.this$0.stopWaiting();
        BaseResult.MetaBean meta = it2.getMeta();
        if (meta != null && meta.getCode() == 0) {
            BaseUtils.showShortToast(R.string.update_success);
            this.this$0.setResult(101);
            this.this$0.finish();
            return;
        }
        BaseResult.MetaBean meta2 = it2.getMeta();
        Intrinsics.checkNotNull(meta2);
        if (meta2.getCode() == 10501) {
            this.this$0.showConflictDialog();
            return;
        }
        Context context = (Context) this.this$0.mContext;
        BaseResult.MetaBean meta3 = it2.getMeta();
        BaseUtils.showShortToast(context, String.valueOf(meta3 != null ? meta3.getMessage() : null));
    }
}
