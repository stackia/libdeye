package com.deye.activity.advertise;

import com.deye.utils.MMKVUtils;
import com.google.gson.Gson;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.bean.AdvertiseInfoBean;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Advertise.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/deye/activity/advertise/Advertise;", "", "()V", "fetchAdvertiseInfo", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class Advertise {
    public static final Advertise INSTANCE = new Advertise();

    private Advertise() {
    }

    public final void fetchAdvertiseInfo() {
        Intrinsics.checkNotNullExpressionValue(RetrofitManager.INSTANCE.getApiService().getAdImage().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.deye.activity.advertise.Advertise$fetchAdvertiseInfo$flowable$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<AdvertiseInfoBean> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseResult.MetaBean meta = it2.getMeta();
                if (meta == null || meta.getCode() != 0) {
                    return;
                }
                MMKVUtils mMKVUtils = MMKVUtils.INSTANCE;
                String json = new Gson().toJson(it2.getData());
                Intrinsics.checkNotNullExpressionValue(json, "toJson(...)");
                mMKVUtils.setAdInfo(json);
            }
        }, new Consumer() { // from class: com.deye.activity.advertise.Advertise$fetchAdvertiseInfo$flowable$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                it2.printStackTrace();
            }
        }), "subscribe(...)");
    }
}
