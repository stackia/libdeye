package com.deye.extension;

import android.view.View;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: View.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"setDebounceClickListener", "", "Landroid/view/View;", "listener", "Landroid/view/View$OnClickListener;", "time", "", "app_homeRelease"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class ViewKt {
    public static /* synthetic */ void setDebounceClickListener$default(View view, View.OnClickListener onClickListener, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 200;
        }
        setDebounceClickListener(view, onClickListener, j);
    }

    public static final void setDebounceClickListener(View view, final View.OnClickListener listener, final long j) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(listener, "listener");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        view.setOnClickListener(new View.OnClickListener() { // from class: com.deye.extension.ViewKt$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ViewKt.setDebounceClickListener$lambda$0(objectRef, j, listener, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r9v1, types: [T, kotlinx.coroutines.Job] */
    public static final void setDebounceClickListener$lambda$0(Ref.ObjectRef job, long j, View.OnClickListener listener, View view) {
        Intrinsics.checkNotNullParameter(job, "$job");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Job job2 = (Job) job.element;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        job.element = BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), (CoroutineContext) null, (CoroutineStart) null, new ViewKt$setDebounceClickListener$1$1(j, listener, view, null), 3, (Object) null);
    }
}
