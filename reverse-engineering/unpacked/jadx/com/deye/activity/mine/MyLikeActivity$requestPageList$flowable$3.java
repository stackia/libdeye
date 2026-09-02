package com.deye.activity.mine;

import com.deye.utils.BaseUtils;
import com.mxchipapp.databinding.ActiivityCollectBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.FindItemBean;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MyLikeActivity.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "", "Lio/fogcloud/sdk/fog/bean/FindItemBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class MyLikeActivity$requestPageList$flowable$3<T> implements Consumer {
    final /* synthetic */ String $startId;
    final /* synthetic */ MyLikeActivity this$0;

    MyLikeActivity$requestPageList$flowable$3(MyLikeActivity myLikeActivity, String str) {
        this.this$0 = myLikeActivity;
        this.$startId = str;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<List<FindItemBean>> it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        this.this$0.finishRefresh();
        BaseResult.MetaBean meta = it2.getMeta();
        if (meta != null && meta.getCode() == 0) {
            List<FindItemBean> data = it2.getData();
            if (data != null) {
                String str = this.$startId;
                MyLikeActivity myLikeActivity = this.this$0;
                if (str == null) {
                    myLikeActivity.getAdapter().setData(data);
                } else {
                    myLikeActivity.getAdapter().addData(data);
                }
            }
            int itemCount = this.this$0.getAdapter().getItemCount();
            ActiivityCollectBinding actiivityCollectBinding = null;
            String string2 = StubApp.getString2(13230);
            if (itemCount == 0) {
                ActiivityCollectBinding actiivityCollectBinding2 = this.this$0.mBinding;
                if (actiivityCollectBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                } else {
                    actiivityCollectBinding = actiivityCollectBinding2;
                }
                actiivityCollectBinding.llEmpty.setVisibility(0);
                return;
            }
            ActiivityCollectBinding actiivityCollectBinding3 = this.this$0.mBinding;
            if (actiivityCollectBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                actiivityCollectBinding = actiivityCollectBinding3;
            }
            actiivityCollectBinding.llEmpty.setVisibility(8);
            return;
        }
        BaseUtils.showShortToast(StubApp.getString2(13751));
    }
}
