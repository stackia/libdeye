package com.deye.activity.mine;

import android.widget.TextView;
import com.deye.entity.UserInfoBean;
import com.deye.utils.MMKVUtils;
import com.mxchipapp.R;
import com.mxchipapp.databinding.PersonalCenterAtyBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.LocationWeather;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UserInfoActivity.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/LocationWeather;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class UserInfoActivity$showTimePicker$pvTime$1$flow$1<T> implements Consumer {
    final /* synthetic */ Date $date;
    final /* synthetic */ UserInfoActivity this$0;

    UserInfoActivity$showTimePicker$pvTime$1$flow$1(UserInfoActivity userInfoActivity, Date date) {
        this.this$0 = userInfoActivity;
        this.$date = date;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<LocationWeather> it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        PersonalCenterAtyBinding personalCenterAtyBinding = this.this$0.mPersonalCenterAtyBinding;
        PersonalCenterAtyBinding personalCenterAtyBinding2 = null;
        String string2 = StubApp.getString2(13762);
        if (personalCenterAtyBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            personalCenterAtyBinding = null;
        }
        TextView textView = personalCenterAtyBinding.tvAgeValue;
        UserInfoActivity userInfoActivity = this.this$0;
        Date date = this.$date;
        Intrinsics.checkNotNullExpressionValue(date, "$date");
        textView.setText(userInfoActivity.getTime(date));
        PersonalCenterAtyBinding personalCenterAtyBinding3 = this.this$0.mPersonalCenterAtyBinding;
        if (personalCenterAtyBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            personalCenterAtyBinding2 = personalCenterAtyBinding3;
        }
        personalCenterAtyBinding2.tvAgeValue.setTextColor(this.this$0.getColor(R.color.color_dark));
        UserInfoBean userInfoBean = this.this$0.userInfo;
        if (userInfoBean != null) {
            UserInfoActivity userInfoActivity2 = this.this$0;
            Date date2 = this.$date;
            Intrinsics.checkNotNullExpressionValue(date2, "$date");
            userInfoBean.setBirthday(userInfoActivity2.getTime(date2));
        }
        MMKVUtils.INSTANCE.setUserInfo(this.this$0.userInfo);
    }
}
