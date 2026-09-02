package com.deye.activity.mine;

import com.deye.MxchipApplication;
import com.deye.helper.DialogHelper;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.api.http.RetrofitService;
import io.fogcloud.sdk.fog.bean.SimpleResultBean;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UserInfoActivity.kt */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0012\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/deye/activity/mine/UserInfoActivity$initView$6$1", "Lcom/deye/helper/DialogHelper$OnDialogListener;", "onCancel", "", "onSure", "text", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class UserInfoActivity$initView$6$1 extends DialogHelper.OnDialogListener {
    final /* synthetic */ UserInfoActivity this$0;

    @Override // com.deye.helper.DialogHelper.OnDialogListener
    public void onCancel() {
    }

    UserInfoActivity$initView$6$1(UserInfoActivity userInfoActivity) {
        this.this$0 = userInfoActivity;
    }

    @Override // com.deye.helper.DialogHelper.OnDialogListener
    public void onSure(String text) {
        RetrofitService apiService = RetrofitManager.INSTANCE.getApiService();
        String clientId = MxchipApplication.getInstance().getClientId();
        Intrinsics.checkNotNullExpressionValue(clientId, "getClientId(...)");
        Flowable<BaseResult<SimpleResultBean>> flowableObserveOn = apiService.loginOut(clientId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        final UserInfoActivity userInfoActivity = this.this$0;
        Consumer<? super BaseResult<SimpleResultBean>> consumer = new Consumer() { // from class: com.deye.activity.mine.UserInfoActivity$initView$6$1$onSure$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<SimpleResultBean> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseResult.MetaBean meta = it2.getMeta();
                if (meta == null || meta.getCode() != 0) {
                    userInfoActivity.logoutFail(true);
                } else {
                    MxchipApplication.getInstance().loginOut();
                }
            }
        };
        final UserInfoActivity userInfoActivity2 = this.this$0;
        flowableObserveOn.subscribe(consumer, new Consumer() { // from class: com.deye.activity.mine.UserInfoActivity$initView$6$1$onSure$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                userInfoActivity2.logoutFail(false);
            }
        });
    }
}
