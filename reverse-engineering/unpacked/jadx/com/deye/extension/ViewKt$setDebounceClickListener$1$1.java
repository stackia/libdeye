package com.deye.extension;

import android.view.View;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: View.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.deye.extension.ViewKt$setDebounceClickListener$1$1", f = "View.kt", i = {}, l = {15}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class ViewKt$setDebounceClickListener$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ View $it;
    final /* synthetic */ View.OnClickListener $listener;
    final /* synthetic */ long $time;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ViewKt$setDebounceClickListener$1$1(long j, View.OnClickListener onClickListener, View view, Continuation<? super ViewKt$setDebounceClickListener$1$1> continuation) {
        super(2, continuation);
        this.$time = j;
        this.$listener = onClickListener;
        this.$it = view;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ViewKt$setDebounceClickListener$1$1(this.$time, this.$listener, this.$it, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ViewKt$setDebounceClickListener$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (DelayKt.delay(this.$time, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException(StubApp.getString2(13735));
            }
            ResultKt.throwOnFailure(obj);
        }
        this.$listener.onClick(this.$it);
        return Unit.INSTANCE;
    }
}
