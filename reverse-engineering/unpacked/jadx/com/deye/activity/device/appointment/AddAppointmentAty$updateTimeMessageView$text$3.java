package com.deye.activity.device.appointment;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: AddAppointmentAty.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lkotlin/Pair;", "", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class AddAppointmentAty$updateTimeMessageView$text$3 extends Lambda implements Function1<Pair<? extends String, ? extends Integer>, CharSequence> {
    public static final AddAppointmentAty$updateTimeMessageView$text$3 INSTANCE = new AddAppointmentAty$updateTimeMessageView$text$3();

    AddAppointmentAty$updateTimeMessageView$text$3() {
        super(1);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final CharSequence invoke2(Pair<String, Integer> it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        String first = it2.getFirst();
        Intrinsics.checkNotNullExpressionValue(first, "<get-first>(...)");
        return first;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ CharSequence invoke(Pair<? extends String, ? extends Integer> pair) {
        return invoke2((Pair<String, Integer>) pair);
    }
}
