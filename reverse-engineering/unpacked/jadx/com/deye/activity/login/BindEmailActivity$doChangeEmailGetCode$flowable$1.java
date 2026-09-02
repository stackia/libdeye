package com.deye.activity.login;

import android.content.Context;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import com.mxchipapp.databinding.BindEmailAtyBinding;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.VerificationResultBean;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BindEmailActivity.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/VerificationResultBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class BindEmailActivity$doChangeEmailGetCode$flowable$1<T> implements Consumer {
    final /* synthetic */ BindEmailActivity this$0;

    BindEmailActivity$doChangeEmailGetCode$flowable$1(BindEmailActivity bindEmailActivity) {
        this.this$0 = bindEmailActivity;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<VerificationResultBean> it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        BaseResult.MetaBean meta = it2.getMeta();
        if (meta == null || meta.getCode() != 0) {
            BindEmailAtyBinding bindEmailAtyBinding = this.this$0.mBinding;
            if (bindEmailAtyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                bindEmailAtyBinding = null;
            }
            bindEmailAtyBinding.tvGetCode.finish();
            Context context = (Context) this.this$0.mContext;
            BaseResult.MetaBean meta2 = it2.getMeta();
            BaseUtils.showShortToast(context, String.valueOf(meta2 != null ? meta2.getMessage() : null));
            return;
        }
        BaseUtils.showShortToast(R.string.email_verification_code_sent);
    }
}
