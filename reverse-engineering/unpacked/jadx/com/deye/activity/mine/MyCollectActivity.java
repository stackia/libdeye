package com.deye.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.deye.activity.device.base.BaseActivity;
import com.deye.adapter.FindListAdapter;
import com.mxchipapp.databinding.ActiivityCollectBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MyCollectActivity.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 %2\u00020\u0001:\u0001%B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0018H\u0002J\b\u0010\u001d\u001a\u00020\u0018H\u0002J\u0012\u0010\u001e\u001a\u00020\u00182\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0006\u0010!\u001a\u00020\u0018J\u0012\u0010\"\u001a\u00020\u00182\b\u0010#\u001a\u0004\u0018\u00010$H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/deye/activity/mine/MyCollectActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "SERVER_PAGE_SIZE", "", "getSERVER_PAGE_SIZE", "()I", "adapter", "Lcom/deye/adapter/FindListAdapter;", "getAdapter", "()Lcom/deye/adapter/FindListAdapter;", "setAdapter", "(Lcom/deye/adapter/FindListAdapter;)V", "hasMore", "", "getHasMore", "()Z", "setHasMore", "(Z)V", "isLoading", "setLoading", "mBinding", "Lcom/mxchipapp/databinding/ActiivityCollectBinding;", "finishRefresh", "", "getMaxPosition", "positions", "", "initView", "loadMoreData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRefresh", "requestPageList", "startId", "", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class MyCollectActivity extends BaseActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final int SERVER_PAGE_SIZE = 10;
    public FindListAdapter adapter;
    private boolean hasMore;
    private boolean isLoading;
    private ActiivityCollectBinding mBinding;

    static {
        StubApp.interface11(14404);
        INSTANCE = new Companion(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final native void finishRefresh();

    /* JADX INFO: Access modifiers changed from: private */
    public final native int getMaxPosition(int[] positions);

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$2(MyCollectActivity myCollectActivity, RefreshLayout refreshLayout);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void loadMoreData();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onCreate$lambda$0(MyCollectActivity myCollectActivity, View view);

    private final native void requestPageList(String startId);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void requestPageList$lambda$3(MyCollectActivity myCollectActivity);

    public final native FindListAdapter getAdapter();

    public final native boolean getHasMore();

    public final native int getSERVER_PAGE_SIZE();

    public final native boolean isLoading();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    public final native void onRefresh();

    public final native void setAdapter(FindListAdapter findListAdapter);

    public final native void setHasMore(boolean z);

    public final native void setLoading(boolean z);

    /* compiled from: MyCollectActivity.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"com/deye/activity/mine/MyCollectActivity$initView$3", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "onScrolled", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "dx", "", "dy", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.MyCollectActivity$initView$3, reason: invalid class name */
    public static final class AnonymousClass3 extends RecyclerView.OnScrollListener {
        AnonymousClass3() {
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            StaggeredGridLayoutManager layoutManager = recyclerView.getLayoutManager();
            Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.StaggeredGridLayoutManager");
            StaggeredGridLayoutManager staggeredGridLayoutManager = layoutManager;
            int[] iArrFindLastVisibleItemPositions = staggeredGridLayoutManager.findLastVisibleItemPositions((int[]) null);
            Intrinsics.checkNotNullExpressionValue(iArrFindLastVisibleItemPositions, "findLastVisibleItemPositions(...)");
            int maxPosition = MyCollectActivity.this.getMaxPosition(iArrFindLastVisibleItemPositions);
            int itemCount = staggeredGridLayoutManager.getItemCount();
            if (MyCollectActivity.this.isLoading() || maxPosition < itemCount - 3 || !MyCollectActivity.this.getHasMore()) {
                return;
            }
            MyCollectActivity.this.loadMoreData();
        }
    }

    /* compiled from: MyCollectActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/deye/activity/mine/MyCollectActivity$Companion;", "", "()V", "open", "", "innerContext", "Landroid/content/Context;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void open(Context innerContext) {
            Intrinsics.checkNotNullParameter(innerContext, "innerContext");
            innerContext.startActivity(new Intent(innerContext, (Class<?>) MyCollectActivity.class));
        }
    }
}
