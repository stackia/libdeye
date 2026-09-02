package com.deye.utils;

import android.content.Context;
import com.stub.StubApp;
import java.lang.reflect.InvocationTargetException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WechatShareHelper.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u0000 \r2\u00020\u0001:\u0001\rJ8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH&¨\u0006\u000e"}, d2 = {"Lcom/deye/utils/WechatShareHelper;", "", "share", "", "context", "Landroid/content/Context;", "platform", "", "title", "", "desc", "thumbPath", "url", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public interface WechatShareHelper {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    void share(Context context, int platform, String title, String desc, String thumbPath, String url);

    /* compiled from: WechatShareHelper.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/deye/utils/WechatShareHelper$Companion;", "", "()V", "INSTANCE", "Lcom/deye/utils/WechatShareHelper;", "getINSTANCE", "()Lcom/deye/utils/WechatShareHelper;", "INSTANCE$delegate", "Lkotlin/Lazy;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        /* renamed from: INSTANCE$delegate, reason: from kotlin metadata */
        private static final Lazy<WechatShareHelper> INSTANCE = LazyKt.lazy(new Function0<WechatShareHelper>() { // from class: com.deye.utils.WechatShareHelper$Companion$INSTANCE$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final WechatShareHelper invoke() throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
                try {
                    Object objNewInstance = Class.forName(StubApp.getString2("14483")).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    Intrinsics.checkNotNull(objNewInstance, "null cannot be cast to non-null type com.deye.utils.WechatShareHelper");
                    return (WechatShareHelper) objNewInstance;
                } catch (Exception e) {
                    throw new RuntimeException(StubApp.getString2(14484), e);
                }
            }
        });

        private Companion() {
        }

        public final WechatShareHelper getINSTANCE() {
            return INSTANCE.getValue();
        }
    }
}
