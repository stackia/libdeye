package io.fogcloud.sdk.fog.api.http;

import com.google.gson.GsonBuilder;
import com.stub.StubApp;
import com.ut.device.AidConstants;
import io.fogcloud.sdk.fog.helper.Configuration;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/* compiled from: RetrofitManager.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000e\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\b\u001a\u0004\b\u000f\u0010\fR\u001b\u0010\u0011\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\b\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lio/fogcloud/sdk/fog/api/http/RetrofitManager;", "", "()V", "apiService", "Lio/fogcloud/sdk/fog/api/http/RetrofitService;", "getApiService", "()Lio/fogcloud/sdk/fog/api/http/RetrofitService;", "apiService$delegate", "Lkotlin/Lazy;", "retrofit", "Lretrofit2/Retrofit;", "getRetrofit", "()Lretrofit2/Retrofit;", "retrofit$delegate", "uploadRetrofit", "getUploadRetrofit", "uploadRetrofit$delegate", "uploadService", "Lio/fogcloud/sdk/fog/api/http/RetrofitUploadService;", "getUploadService", "()Lio/fogcloud/sdk/fog/api/http/RetrofitUploadService;", "uploadService$delegate", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class RetrofitManager {
    public static final RetrofitManager INSTANCE = new RetrofitManager();

    /* renamed from: retrofit$delegate, reason: from kotlin metadata */
    private static final Lazy retrofit = LazyKt.lazy(new Function0<Retrofit>() { // from class: io.fogcloud.sdk.fog.api.http.RetrofitManager$retrofit$2
        @Override // kotlin.jvm.functions.Function0
        public final Retrofit invoke() {
            return new Retrofit.Builder().client(DeYeHttpRequestManager.getInstance().getClient()).baseUrl(Configuration.getApihost() + StubApp.getString2(AidConstants.EVENT_REQUEST_FAILED)).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build();
        }
    });

    /* renamed from: apiService$delegate, reason: from kotlin metadata */
    private static final Lazy apiService = LazyKt.lazy(new Function0<RetrofitService>() { // from class: io.fogcloud.sdk.fog.api.http.RetrofitManager$apiService$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final RetrofitService invoke() {
            return (RetrofitService) RetrofitManager.INSTANCE.getRetrofit().create(RetrofitService.class);
        }
    });

    /* renamed from: uploadRetrofit$delegate, reason: from kotlin metadata */
    private static final Lazy uploadRetrofit = LazyKt.lazy(new Function0<Retrofit>() { // from class: io.fogcloud.sdk.fog.api.http.RetrofitManager$uploadRetrofit$2
        @Override // kotlin.jvm.functions.Function0
        public final Retrofit invoke() {
            return new Retrofit.Builder().client(DeYeHttpRequestManager.getInstance().getUplaodClient()).baseUrl(Configuration.getApihost() + StubApp.getString2(AidConstants.EVENT_REQUEST_FAILED)).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build();
        }
    });

    /* renamed from: uploadService$delegate, reason: from kotlin metadata */
    private static final Lazy uploadService = LazyKt.lazy(new Function0<RetrofitUploadService>() { // from class: io.fogcloud.sdk.fog.api.http.RetrofitManager$uploadService$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final RetrofitUploadService invoke() {
            return (RetrofitUploadService) RetrofitManager.INSTANCE.getUploadRetrofit().create(RetrofitUploadService.class);
        }
    });

    private RetrofitManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Retrofit getRetrofit() {
        Object value = retrofit.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Retrofit) value;
    }

    public final RetrofitService getApiService() {
        Object value = apiService.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (RetrofitService) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Retrofit getUploadRetrofit() {
        Object value = uploadRetrofit.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Retrofit) value;
    }

    public final RetrofitUploadService getUploadService() {
        Object value = uploadService.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (RetrofitUploadService) value;
    }
}
