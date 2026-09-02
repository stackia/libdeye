package io.fogcloud.sdk.fog.api.http;

import io.reactivex.rxjava3.core.Flowable;
import java.util.List;
import kotlin.Metadata;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/* compiled from: RetrofitUploadService.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00040\u00032\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H'¨\u0006\b"}, d2 = {"Lio/fogcloud/sdk/fog/api/http/RetrofitUploadService;", "", "fileUpload", "Lio/reactivex/rxjava3/core/Flowable;", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "list", "", "Lokhttp3/MultipartBody$Part;", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public interface RetrofitUploadService {
    @POST("feedback/file/upload/")
    @Multipart
    Flowable<BaseResult<Object>> fileUpload(@Part List<MultipartBody.Part> list);
}
