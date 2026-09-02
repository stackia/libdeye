package com.deye.activity.login;

import android.content.Context;
import com.deye.utils.BaseUtils;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OverseaRegisterActivity.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "error", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class OverseaRegisterActivity$sendEmailVerificationCode$getEmailVerCodeApi$3<T> implements Consumer {
    final /* synthetic */ OverseaRegisterActivity this$0;

    OverseaRegisterActivity$sendEmailVerificationCode$getEmailVerCodeApi$3(OverseaRegisterActivity overseaRegisterActivity) {
        this.this$0 = overseaRegisterActivity;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Throwable error) {
        Intrinsics.checkNotNullParameter(error, "error");
        this.this$0.hideLoading();
        LogUtil.e(StubApp.getString2(13704), StubApp.getString2(13715) + error.getMessage());
        Context context = (Context) this.this$0;
        String message = error.getMessage();
        if (message == null) {
            message = StubApp.getString2(13705);
        }
        BaseUtils.showShortToast(context, message);
    }
}
