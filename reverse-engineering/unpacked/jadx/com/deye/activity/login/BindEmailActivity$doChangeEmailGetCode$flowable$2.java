package com.deye.activity.login;

import com.mxchipapp.databinding.BindEmailAtyBinding;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BindEmailActivity.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class BindEmailActivity$doChangeEmailGetCode$flowable$2<T> implements Consumer {
    final /* synthetic */ BindEmailActivity this$0;

    BindEmailActivity$doChangeEmailGetCode$flowable$2(BindEmailActivity bindEmailActivity) {
        this.this$0 = bindEmailActivity;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Throwable it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        BindEmailAtyBinding bindEmailAtyBinding = this.this$0.mBinding;
        if (bindEmailAtyBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            bindEmailAtyBinding = null;
        }
        bindEmailAtyBinding.tvGetCode.finish();
        it2.printStackTrace();
    }
}
