package com.deye.activity.mine;

import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.reactivestreams.Subscription;

/* compiled from: MyLikeActivity.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lorg/reactivestreams/Subscription;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class MyLikeActivity$requestPageList$flowable$1<T> implements Consumer {
    final /* synthetic */ MyLikeActivity this$0;

    MyLikeActivity$requestPageList$flowable$1(MyLikeActivity myLikeActivity) {
        this.this$0 = myLikeActivity;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Subscription it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        this.this$0.setLoading(true);
    }
}
