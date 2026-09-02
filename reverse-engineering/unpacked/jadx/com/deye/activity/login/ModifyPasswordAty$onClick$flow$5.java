package com.deye.activity.login;

import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ModifyPwdAtyBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.bean.CheckVerCodeResult;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ModifyPasswordAty.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/CheckVerCodeResult;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class ModifyPasswordAty$onClick$flow$5<T> implements Consumer {
    final /* synthetic */ ModifyPasswordAty this$0;

    ModifyPasswordAty$onClick$flow$5(ModifyPasswordAty modifyPasswordAty) {
        this.this$0 = modifyPasswordAty;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<CheckVerCodeResult> it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        BaseResult.MetaBean meta = it2.getMeta();
        if (meta == null || meta.getCode() != 0) {
            BaseUtils.showShortToast(R.string.verification_code_error);
            return;
        }
        DeYeHttpRequestManager deYeHttpRequestManager = DeYeHttpRequestManager.getInstance();
        CheckVerCodeResult data = it2.getData();
        ModifyPwdAtyBinding modifyPwdAtyBinding = null;
        deYeHttpRequestManager.setToken(data != null ? data.token : null);
        ModifyPwdAtyBinding modifyPwdAtyBinding2 = this.this$0.mModifyPwdAtyBinding;
        String string2 = StubApp.getString2(13702);
        if (modifyPwdAtyBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            modifyPwdAtyBinding2 = null;
        }
        modifyPwdAtyBinding2.llPwd.setVisibility(0);
        ModifyPwdAtyBinding modifyPwdAtyBinding3 = this.this$0.mModifyPwdAtyBinding;
        if (modifyPwdAtyBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            modifyPwdAtyBinding = modifyPwdAtyBinding3;
        }
        modifyPwdAtyBinding.llStep.setVisibility(8);
    }
}
