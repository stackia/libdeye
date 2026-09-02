package com.deye.helper;

import androidx.fragment.app.FragmentActivity;
import com.deye.utils.BaseUtils;
import com.deye.utils.ChannelUtil;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RateManager.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\n"}, d2 = {"Lcom/deye/helper/RateManager;", "", "()V", "checkAndShowDialog", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "scene", "", "uploadShowState", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class RateManager {
    public static final RateManager INSTANCE = new RateManager();

    private RateManager() {
    }

    public final void checkAndShowDialog(final FragmentActivity activity, final int scene) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (ChannelUtil.isOversea()) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(RetrofitManager.INSTANCE.getApiService().checkNeedShowRate(scene).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.deye.helper.RateManager$checkAndShowDialog$flowable$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<Boolean> it2) {
                Object message;
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseResult.MetaBean meta = it2.getMeta();
                if (meta != null && meta.getCode() == 0) {
                    if (Intrinsics.areEqual((Object) it2.getData(), (Object) true)) {
                        DialogHelper.showRateDialog(activity);
                        RateManager.INSTANCE.uploadShowState(scene);
                        return;
                    }
                    return;
                }
                BaseResult.MetaBean meta2 = it2.getMeta();
                if (meta2 == null || (message = meta2.getMessage()) == null) {
                    return;
                }
                BaseUtils.showShortToast(message.toString());
            }
        }, new Consumer() { // from class: com.deye.helper.RateManager$checkAndShowDialog$flowable$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                it2.printStackTrace();
            }
        }), "subscribe(...)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void uploadShowState(int scene) {
        Intrinsics.checkNotNullExpressionValue(RetrofitManager.INSTANCE.getApiService().uploadShowRateState(scene).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.deye.helper.RateManager$uploadShowState$flowable$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<String> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
            }
        }, new Consumer() { // from class: com.deye.helper.RateManager$uploadShowState$flowable$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                it2.printStackTrace();
            }
        }), "subscribe(...)");
    }
}
