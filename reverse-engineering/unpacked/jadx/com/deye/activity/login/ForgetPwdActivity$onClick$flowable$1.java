package com.deye.activity.login;

import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.VerificationResultBean;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ForgetPwdActivity.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/VerificationResultBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class ForgetPwdActivity$onClick$flowable$1<T> implements Consumer {
    public static final ForgetPwdActivity$onClick$flowable$1<T> INSTANCE = new ForgetPwdActivity$onClick$flowable$1<>();

    ForgetPwdActivity$onClick$flowable$1() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<VerificationResultBean> it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        BaseResult.MetaBean meta = it2.getMeta();
        if (meta == null || meta.getCode() != 0) {
            return;
        }
        VerificationResultBean data = it2.getData();
        if (StringsKt.equals$default(data != null ? data.type : null, StubApp.getString2(701), false, 2, (Object) null)) {
            BaseUtils.showShortToast(R.string.phone_not_registered);
        } else {
            BaseUtils.showShortToast(R.string.verification_code_sent_successfully);
        }
    }
}
