package com.deye.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PaginationScrollListener.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b&\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0006H&J\b\u0010\b\u001a\u00020\tH$J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010\u000f\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/deye/fragment/PaginationScrollListener;", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "mLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "(Landroidx/recyclerview/widget/LinearLayoutManager;)V", "isLastPage", "", "isLoading", "loadMoreItems", "", "onScrollStateChanged", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "newState", "", "onScrolled", "dx", "dy", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {
    private final LinearLayoutManager mLayoutManager;
    public static final String TAG = StubApp.getString2(14107);

    public abstract boolean isLastPage();

    public abstract boolean isLoading();

    protected abstract void loadMoreItems();

    public PaginationScrollListener(LinearLayoutManager mLayoutManager) {
        Intrinsics.checkNotNullParameter(mLayoutManager, "mLayoutManager");
        this.mLayoutManager = mLayoutManager;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onScrollStateChanged(recyclerView, newState);
    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onScrolled(recyclerView, dx, dy);
        int childCount = this.mLayoutManager.getChildCount();
        int itemCount = this.mLayoutManager.getItemCount();
        int iFindFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        String str = StubApp.getString2(14108) + childCount + StubApp.getString2(14109) + itemCount + StubApp.getString2(14110) + iFindFirstVisibleItemPosition;
        String string2 = StubApp.getString2(14107);
        LogUtil.d(string2, str);
        LogUtil.d(string2, StubApp.getString2(14111) + isLoading() + StubApp.getString2(14112) + isLastPage());
        if (isLoading() || isLastPage() || childCount + iFindFirstVisibleItemPosition < itemCount || iFindFirstVisibleItemPosition < 0) {
            return;
        }
        loadMoreItems();
    }
}
