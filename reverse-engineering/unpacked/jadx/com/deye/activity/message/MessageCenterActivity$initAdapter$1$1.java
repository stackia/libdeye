package com.deye.activity.message;

import androidx.recyclerview.widget.LinearLayoutManager;
import com.deye.fragment.PaginationScrollListener;
import kotlin.Metadata;

/* compiled from: MessageCenterActivity.kt */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0014¨\u0006\u0007"}, d2 = {"com/deye/activity/message/MessageCenterActivity$initAdapter$1$1", "Lcom/deye/fragment/PaginationScrollListener;", "isLastPage", "", "isLoading", "loadMoreItems", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class MessageCenterActivity$initAdapter$1$1 extends PaginationScrollListener {
    final /* synthetic */ MessageCenterActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MessageCenterActivity$initAdapter$1$1(LinearLayoutManager linearLayoutManager, MessageCenterActivity messageCenterActivity) {
        super(linearLayoutManager);
        this.this$0 = messageCenterActivity;
    }

    @Override // com.deye.fragment.PaginationScrollListener
    protected void loadMoreItems() {
        this.this$0.setLoading(true);
        this.this$0.loadNextPage();
    }

    @Override // com.deye.fragment.PaginationScrollListener
    public boolean isLastPage() {
        return this.this$0.isLastPage();
    }

    @Override // com.deye.fragment.PaginationScrollListener
    public boolean isLoading() {
        return this.this$0.isLoading();
    }
}
