package com.deye.activity.mine;

import com.deye.entity.UserInfoBean;
import com.deye.utils.MMKVUtils;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.LoginResult;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UserInfoActivity.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/LoginResult;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class UserInfoActivity$showGenderPicker$pvOptions$1$flow$1<T> implements Consumer {
    final /* synthetic */ String $standardizedGender;
    final /* synthetic */ UserInfoActivity this$0;

    UserInfoActivity$showGenderPicker$pvOptions$1$flow$1(UserInfoActivity userInfoActivity, String str) {
        this.this$0 = userInfoActivity;
        this.$standardizedGender = str;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<LoginResult> it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        UserInfoBean userInfoBean = this.this$0.userInfo;
        if (userInfoBean != null) {
            userInfoBean.setGender(this.$standardizedGender);
        }
        MMKVUtils.INSTANCE.setUserInfo(this.this$0.userInfo);
    }
}
