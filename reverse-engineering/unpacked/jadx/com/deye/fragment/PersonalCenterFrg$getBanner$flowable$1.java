package com.deye.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.deye.utils.MMKVUtils;
import com.deye.views.RoundedCornersTransform;
import com.deye.webview.AgentWebActivity;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.BannerItemBean;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: PersonalCenterFrg.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "", "Lio/fogcloud/sdk/fog/bean/BannerItemBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class PersonalCenterFrg$getBanner$flowable$1<T> implements Consumer {
    final /* synthetic */ PersonalCenterFrg this$0;

    PersonalCenterFrg$getBanner$flowable$1(PersonalCenterFrg personalCenterFrg) {
        this.this$0 = personalCenterFrg;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<List<BannerItemBean>> it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        try {
            List<BannerItemBean> data = it2.getData();
            if (data == null || data.isEmpty() || this.this$0.getActivity() == null) {
                return;
            }
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            List<BannerItemBean> data2 = it2.getData();
            Intrinsics.checkNotNull(data2);
            objectRef.element = (T) data2.get(0);
            this.this$0.getMPersonalCenterFrgBinding().fraBanner.setVisibility(0);
            Glide.with(this.this$0.requireActivity()).load(((BannerItemBean) objectRef.element).getCover_image()).apply((BaseRequestOptions<?>) new RequestOptions().transform(new RoundedCornersTransform(this.this$0.getContext(), DensityUtil.dp2px(12.0f), true, true, true, true))).into(this.this$0.getMPersonalCenterFrgBinding().ivBanner);
            ImageView imageView = this.this$0.getMPersonalCenterFrgBinding().ivBanner;
            final PersonalCenterFrg personalCenterFrg = this.this$0;
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.PersonalCenterFrg$getBanner$flowable$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PersonalCenterFrg$getBanner$flowable$1.accept$lambda$0(personalCenterFrg, objectRef, view);
                }
            });
            ImageView imageView2 = this.this$0.getMPersonalCenterFrgBinding().ivBannerDelete;
            final PersonalCenterFrg personalCenterFrg2 = this.this$0;
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.PersonalCenterFrg$getBanner$flowable$1$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PersonalCenterFrg$getBanner$flowable$1.accept$lambda$1(personalCenterFrg2, view);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void accept$lambda$0(PersonalCenterFrg this$0, Ref.ObjectRef data, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(data, "$data");
        AgentWebActivity.Companion companion = AgentWebActivity.INSTANCE;
        Context contextRequireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(contextRequireActivity, "requireActivity(...)");
        String content_url = ((BannerItemBean) data.element).getContent_url();
        Intrinsics.checkNotNull(content_url);
        AgentWebActivity.Companion.open$default(companion, contextRequireActivity, content_url, null, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void accept$lambda$1(PersonalCenterFrg this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMPersonalCenterFrgBinding().fraBanner.setVisibility(8);
        MMKVUtils.INSTANCE.setMineBannerHide();
    }
}
