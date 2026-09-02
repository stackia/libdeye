package com.deye.activity;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.deye.adapter.FindListAdapter;
import com.mxchipapp.databinding.ActivitySearchBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.FindItemBean;
import io.fogcloud.sdk.fog.bean.FindPageBean;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SearchActivity.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/FindPageBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class SearchActivity$doSearch$flowable$1<T> implements Consumer {
    final /* synthetic */ List<FindItemBean> $datalist;
    final /* synthetic */ SearchActivity this$0;

    SearchActivity$doSearch$flowable$1(List<FindItemBean> list, SearchActivity searchActivity) {
        this.$datalist = list;
        this.this$0 = searchActivity;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<FindPageBean> it2) {
        List<FindItemBean> data;
        Intrinsics.checkNotNullParameter(it2, "it");
        BaseResult.MetaBean meta = it2.getMeta();
        if (meta == null || meta.getCode() != 0 || it2.getData() == null) {
            return;
        }
        List<FindItemBean> list = this.$datalist;
        SearchActivity searchActivity = this.this$0;
        FindPageBean data2 = it2.getData();
        if (data2 == null || (data = data2.getData()) == null) {
            return;
        }
        list.addAll(data);
        RecyclerView.LayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        ActivitySearchBinding activitySearchBinding = searchActivity.mBinding;
        ActivitySearchBinding activitySearchBinding2 = null;
        String string2 = StubApp.getString2(13230);
        if (activitySearchBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            activitySearchBinding = null;
        }
        activitySearchBinding.recyclerView.setLayoutManager(staggeredGridLayoutManager);
        FindListAdapter findListAdapter = new FindListAdapter((Context) searchActivity, list, StubApp.getString2(1764));
        ActivitySearchBinding activitySearchBinding3 = searchActivity.mBinding;
        if (activitySearchBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            activitySearchBinding3 = null;
        }
        activitySearchBinding3.recyclerView.setAdapter(findListAdapter);
        ActivitySearchBinding activitySearchBinding4 = searchActivity.mBinding;
        if (activitySearchBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            activitySearchBinding2 = activitySearchBinding4;
        }
        activitySearchBinding2.llEmpty.setVisibility(data.isEmpty() ? 0 : 8);
    }
}
