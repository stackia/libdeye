package com.deye.activity.login;

import android.content.Context;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.CheckEmailResult;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OverseaRegisterActivity.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "response", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/CheckEmailResult;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class OverseaRegisterActivity$checkEmailExists$checkEmailApi$1<T> implements Consumer {
    final /* synthetic */ OverseaRegisterActivity this$0;

    OverseaRegisterActivity$checkEmailExists$checkEmailApi$1(OverseaRegisterActivity overseaRegisterActivity) {
        this.this$0 = overseaRegisterActivity;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<CheckEmailResult> response) {
        Intrinsics.checkNotNullParameter(response, "response");
        BaseResult.MetaBean meta = response.getMeta();
        if (meta == null || meta.getCode() != 0) {
            this.this$0.sendEmailVerificationCode();
            return;
        }
        CheckEmailResult data = response.getData();
        boolean z = false;
        if (data != null && data.isRegistered) {
            z = true;
        }
        if (Intrinsics.areEqual(this.this$0.mode, "register")) {
            if (!z) {
                this.this$0.sendEmailVerificationCode();
                return;
            }
            this.this$0.hideLoading();
            OverseaRegisterActivity overseaRegisterActivity = this.this$0;
            BaseUtils.showShortToast((Context) overseaRegisterActivity, overseaRegisterActivity.getString(R.string.email_already_registered));
            return;
        }
        if (z) {
            this.this$0.sendEmailVerificationCode();
            return;
        }
        this.this$0.hideLoading();
        OverseaRegisterActivity overseaRegisterActivity2 = this.this$0;
        BaseUtils.showShortToast((Context) overseaRegisterActivity2, overseaRegisterActivity2.getString(R.string.email_not_registered));
    }
}
