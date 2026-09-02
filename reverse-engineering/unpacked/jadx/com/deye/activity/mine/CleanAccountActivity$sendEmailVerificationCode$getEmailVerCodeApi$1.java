package com.deye.activity.mine;

import android.content.Context;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.VerificationResultBean;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CleanAccountActivity.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "response", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/VerificationResultBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class CleanAccountActivity$sendEmailVerificationCode$getEmailVerCodeApi$1<T> implements Consumer {
    final /* synthetic */ CleanAccountActivity this$0;

    CleanAccountActivity$sendEmailVerificationCode$getEmailVerCodeApi$1(CleanAccountActivity cleanAccountActivity) {
        this.this$0 = cleanAccountActivity;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<VerificationResultBean> response) {
        Object string;
        Intrinsics.checkNotNullParameter(response, "response");
        BaseResult.MetaBean meta = response.getMeta();
        if (meta != null && meta.getCode() == 0) {
            CleanAccountActivity cleanAccountActivity = this.this$0;
            BaseUtils.showShortToast((Context) cleanAccountActivity, cleanAccountActivity.getString(R.string.verification_code_sent_successfully));
            return;
        }
        BaseResult.MetaBean meta2 = response.getMeta();
        if (meta2 == null || (string = meta2.getMessage()) == null) {
            string = this.this$0.getString(R.string.get_verification_code_failure);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        }
        BaseUtils.showShortToast((Context) this.this$0, string.toString());
    }
}
