package com.deye.activity.login;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import com.deye.MxchipApplication;
import com.deye.utils.ActivityRouterUtilsKt;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.bean.LoginResult;
import io.fogcloud.sdk.fog.log.LogUtil;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OverseaRegisterActivity.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "response", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/LoginResult;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class OverseaRegisterActivity$doRegisterEmail$registerAPi$2<T> implements Consumer {
    final /* synthetic */ OverseaRegisterActivity this$0;

    OverseaRegisterActivity$doRegisterEmail$registerAPi$2(OverseaRegisterActivity overseaRegisterActivity) {
        this.this$0 = overseaRegisterActivity;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<LoginResult> response) throws Resources.NotFoundException {
        Object string2;
        Intrinsics.checkNotNullParameter(response, "response");
        this.this$0.hideLoading();
        BaseResult.MetaBean meta = response.getMeta();
        if (meta != null && meta.getCode() == 0) {
            LogUtil.d(StubApp.getString2(13704), StubApp.getString2(13709));
            OverseaRegisterActivity overseaRegisterActivity = this.this$0;
            BaseUtils.showShortToast((Context) overseaRegisterActivity, overseaRegisterActivity.getString(R.string.registration_success));
            LoginResult data = response.getData();
            String str = data != null ? data.token : null;
            if (str != null) {
                MMKV.defaultMMKV().encode(StubApp.getString2(13134), str);
                DeYeHttpRequestManager.getInstance().setToken(str);
                MxchipApplication.getInstance().initPushService();
                Intent intent = new Intent();
                intent.putExtra(StubApp.getString2(13690), true);
                LoginResult data2 = response.getData();
                intent.putExtra(StubApp.getString2(13223), data2 != null ? data2.is_weak_password : false);
                intent.setFlags(268468224);
                ActivityRouterUtilsKt.routingForTabMainActivity((Context) this.this$0, intent);
                this.this$0.finish();
                return;
            }
            this.this$0.finish();
            return;
        }
        BaseResult.MetaBean meta2 = response.getMeta();
        if (meta2 == null || (string2 = meta2.getMessage()) == null) {
            string2 = StubApp.getString2(13710);
        }
        BaseUtils.showShortToast((Context) this.this$0, string2.toString());
    }
}
