package com.deye.utils;

import android.content.Context;
import com.mxchipapp.R;
import com.stub.StubApp;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GenderUtil.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0004J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\f2\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\r\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/deye/utils/GenderUtil;", "", "()V", "GENDER_FEMALE", "", "GENDER_MALE", "GENDER_UNKNOWN", "getLocalizedGender", "context", "Landroid/content/Context;", "backendGender", "getLocalizedGenderList", "", "getStandardizedGender", "localizedGender", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class GenderUtil {
    public static final String GENDER_FEMALE = StubApp.getString2(14314);
    public static final String GENDER_MALE = StubApp.getString2(14315);
    public static final String GENDER_UNKNOWN = StubApp.getString2(805);
    public static final GenderUtil INSTANCE = new GenderUtil();

    private GenderUtil() {
    }

    public final String getLocalizedGender(Context context, String backendGender) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = backendGender;
        String string2 = StubApp.getString2(13221);
        if (str == null || str.length() == 0) {
            String string = context.getString(R.string.to_be_set);
            Intrinsics.checkNotNullExpressionValue(string, string2);
            return string;
        }
        if (Intrinsics.areEqual(backendGender, "Male") || Intrinsics.areEqual(backendGender, "男")) {
            String string3 = context.getString(R.string.male);
            Intrinsics.checkNotNullExpressionValue(string3, string2);
            return string3;
        }
        if (Intrinsics.areEqual(backendGender, "Female") || Intrinsics.areEqual(backendGender, "女")) {
            String string4 = context.getString(R.string.female);
            Intrinsics.checkNotNullExpressionValue(string4, string2);
            return string4;
        }
        if (!Intrinsics.areEqual(backendGender, "Unknown") && !Intrinsics.areEqual(backendGender, "未知")) {
            return backendGender;
        }
        String string5 = context.getString(R.string.unknown);
        Intrinsics.checkNotNullExpressionValue(string5, string2);
        return string5;
    }

    public final String getStandardizedGender(Context context, String localizedGender) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(localizedGender, "localizedGender");
        return Intrinsics.areEqual(localizedGender, context.getString(R.string.male)) ? StubApp.getString2(14315) : Intrinsics.areEqual(localizedGender, context.getString(R.string.female)) ? StubApp.getString2(14314) : Intrinsics.areEqual(localizedGender, context.getString(R.string.unknown)) ? StubApp.getString2(805) : localizedGender;
    }

    public final List<String> getLocalizedGenderList(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.male);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = context.getString(R.string.female);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = context.getString(R.string.unknown);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        return CollectionsKt.listOf((Object[]) new String[]{string, string2, string3});
    }
}
