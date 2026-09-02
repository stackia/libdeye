package com.deye.activity.mine;

import android.content.Context;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.VerificationResultBean;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChangePhoneNumActivity.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/VerificationResultBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class ChangePhoneNumActivity$toGetCode$flowable$1<T> implements Consumer {
    final /* synthetic */ ChangePhoneNumActivity this$0;

    ChangePhoneNumActivity$toGetCode$flowable$1(ChangePhoneNumActivity changePhoneNumActivity) {
        this.this$0 = changePhoneNumActivity;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<VerificationResultBean> it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        if (it2.getData() != null) {
            ChangePhoneNumActivity changePhoneNumActivity = this.this$0;
            BaseUtils.showShortToast((Context) changePhoneNumActivity.mContext, changePhoneNumActivity.getString(R.string.get_verification_code_success));
        }
    }
}
