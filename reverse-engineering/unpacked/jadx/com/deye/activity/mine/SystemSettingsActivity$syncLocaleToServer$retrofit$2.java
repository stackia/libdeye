package com.deye.activity.mine;

import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.log.LogUtil;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SystemSettingsActivity.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class SystemSettingsActivity$syncLocaleToServer$retrofit$2<T> implements Consumer {
    final /* synthetic */ String $locale;
    final /* synthetic */ SystemSettingsActivity this$0;

    SystemSettingsActivity$syncLocaleToServer$retrofit$2(String str, SystemSettingsActivity systemSettingsActivity) {
        this.$locale = str;
        this.this$0 = systemSettingsActivity;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(BaseResult<Object> it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        LogUtil.e(StubApp.getString2(13759) + this.$locale);
        this.this$0.restartApp();
    }
}
