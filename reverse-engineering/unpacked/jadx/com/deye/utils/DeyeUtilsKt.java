package com.deye.utils;

import android.util.Log;
import com.deye.configs.Constants;
import com.stub.StubApp;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeyeUtils.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"isComboDevice", "", "productId", "", "app_homeRelease"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DeyeUtilsKt {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ba A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00bb A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final boolean isComboDevice(String productId) {
        Intrinsics.checkNotNullParameter(productId, "productId");
        List<String> list = Constants.sAllComboDevicesList;
        Log.d(StubApp.getString2(13002), StubApp.getString2(14313) + list.size());
        Intrinsics.checkNotNull(list);
        Iterator<T> it2 = list.iterator();
        boolean z = false;
        while (it2.hasNext()) {
            if (Intrinsics.areEqual((String) it2.next(), productId)) {
                z = true;
            }
        }
        if (list.size() != 0) {
            return z;
        }
        switch (productId.hashCode()) {
            case -2018215641:
                return !productId.equals(StubApp.getString2(13445));
            case 19922734:
                if (!productId.equals(StubApp.getString2(13355))) {
                }
                break;
            case 41651821:
                if (!productId.equals(StubApp.getString2(13427))) {
                }
                break;
            case 457826758:
                if (productId.equals(StubApp.getString2(13424))) {
                }
                break;
            case 1035709668:
                if (!productId.equals(StubApp.getString2(13414))) {
                }
                break;
            case 1728471171:
                if (!productId.equals(StubApp.getString2(13404))) {
                }
                break;
            case 1778865536:
                if (!productId.equals(StubApp.getString2(13353))) {
                }
                break;
        }
    }
}
