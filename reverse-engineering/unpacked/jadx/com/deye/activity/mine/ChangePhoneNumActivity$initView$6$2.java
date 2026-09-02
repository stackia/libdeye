package com.deye.activity.mine;

import android.content.Context;
import android.content.Intent;
import com.deye.activity.login.BindPhoneNumActivity;
import com.deye.utils.BaseUtils;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.SimpleResultBean;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChangePhoneNumActivity.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/SimpleResultBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class ChangePhoneNumActivity$initView$6$2<T> implements Consumer {
    final /* synthetic */ ChangePhoneNumActivity this$0;

    ChangePhoneNumActivity$initView$6$2(ChangePhoneNumActivity changePhoneNumActivity) {
        this.this$0 = changePhoneNumActivity;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<SimpleResultBean> it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        BaseResult.MetaBean meta = it2.getMeta();
        if (meta != null && meta.getCode() == 0) {
            Intent intent = new Intent((Context) this.this$0.mContext, (Class<?>) BindPhoneNumActivity.class);
            intent.putExtra(StubApp.getString2(13691), 1);
            this.this$0.startActivity(intent);
            this.this$0.finish();
            return;
        }
        BaseResult.MetaBean meta2 = it2.getMeta();
        BaseUtils.showShortToast(String.valueOf(meta2 != null ? meta2.getMessage() : null));
    }
}
