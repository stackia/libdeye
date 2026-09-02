package io.fogcloud.sdk.fog.api.http;

import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;
import okhttp3.logging.HttpLoggingInterceptor;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class HttpLogger implements HttpLoggingInterceptor.Logger {
    public void log(String str) {
        LogUtil.d(StubApp.getString2(44778), str);
    }
}
