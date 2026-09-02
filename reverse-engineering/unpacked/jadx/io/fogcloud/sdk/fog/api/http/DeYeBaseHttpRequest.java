package io.fogcloud.sdk.fog.api.http;

import com.alibaba.fastjson.JSON;
import com.stub.StubApp;
import io.fogcloud.sdk.deYeDeYeHttpRequestManager.R;
import io.fogcloud.sdk.fog.BaseApp;
import io.fogcloud.sdk.fog.api.base_callback.DeYeBaseCallback;
import io.fogcloud.sdk.fog.bean.BaseBean;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import io.fogcloud.sdk.fog.helper.SendOffLineBr;
import io.fogcloud.sdk.fog.log.LogDebug;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletResponse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class DeYeBaseHttpRequest extends DeYeBaseCallback {
    private static String TAG = StubApp.getString2(44739);
    private OkHttpClient client;
    protected String mPushClientId = "";
    protected String mPushClientIdKey = StubApp.getString2(13220);
    private String lan = StubApp.getString2(13761);
    protected String mToken = "";
    public String appVersion = "";

    /* compiled from: D8$$SyntheticClass */
    /* renamed from: io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest$TrustAllHostnameVerifier-IA, reason: invalid class name */
    public final /* synthetic */ class TrustAllHostnameVerifierIA {
    }

    /* compiled from: D8$$SyntheticClass */
    /* renamed from: io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest$TrustAllManager-IA, reason: invalid class name */
    public final /* synthetic */ class TrustAllManagerIA {
    }

    public void setLan(String str) {
        this.lan = str;
    }

    protected void setPushClientId(String str) {
        this.mPushClientId = str;
    }

    protected String getPushClientId() {
        return this.mPushClientId;
    }

    protected void setToken(String str) {
        this.mToken = str;
    }

    protected String getToken() {
        return this.mToken;
    }

    private static SSLSocketFactory createSSLSocketFactory() throws NoSuchAlgorithmException, KeyManagementException {
        TrustAllManagerIA trustAllManagerIA = null;
        try {
            SSLContext sSLContext = SSLContext.getInstance(StubApp.getString2("18467"));
            sSLContext.init(null, new TrustManager[]{new TrustAllManager(trustAllManagerIA)}, new SecureRandom());
            return sSLContext.getSocketFactory();
        } catch (Exception unused) {
            return null;
        }
    }

    private static class TrustAllManager implements X509TrustManager {
        /* synthetic */ TrustAllManager(TrustAllManagerIA trustAllManagerIA) {
            this();
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        private TrustAllManager() {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        /* synthetic */ TrustAllHostnameVerifier(TrustAllHostnameVerifierIA trustAllHostnameVerifierIA) {
            this();
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }

        private TrustAllHostnameVerifier() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DeYeBaseHttpRequest() {
        this.client = null;
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        this.client = new OkHttpClient.Builder().sslSocketFactory(createSSLSocketFactory(), new TrustAllManager(null)).hostnameVerifier(new TrustAllHostnameVerifier(0 == true ? 1 : 0)).addNetworkInterceptor(httpLoggingInterceptor).addInterceptor(new Interceptor() { // from class: io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest.2
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request.Builder builderNewBuilder = chain.request().newBuilder();
                Request request = chain.request();
                String string2 = StubApp.getString2(672);
                if (request.header(string2) == null && DeYeBaseHttpRequest.this.mToken != null && !DeYeBaseHttpRequest.this.mToken.isEmpty()) {
                    builderNewBuilder.addHeader(string2, StubApp.getString2(13974) + DeYeBaseHttpRequest.this.mToken);
                }
                Request request2 = chain.request();
                String string22 = StubApp.getString2(557);
                if (request2.header(string22) == null) {
                    builderNewBuilder.addHeader(string22, StubApp.getString2(4019));
                }
                if (chain.request().header(DeYeBaseHttpRequest.this.mPushClientIdKey) == null) {
                    builderNewBuilder.addHeader(DeYeBaseHttpRequest.this.mPushClientIdKey, DeYeBaseHttpRequest.this.getPushClientId());
                }
                builderNewBuilder.addHeader(StubApp.getString2(300), StubApp.getString2(701));
                builderNewBuilder.addHeader(StubApp.getString2(89), DeYeBaseHttpRequest.this.appVersion);
                builderNewBuilder.addHeader(StubApp.getString2(14199), DeYeBaseHttpRequest.this.lan);
                builderNewBuilder.addHeader(StubApp.getString2(14200), TimeZone.getDefault().getID());
                return chain.proceed(builderNewBuilder.build());
            }
        }).addInterceptor(new Interceptor() { // from class: io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest.1
            public Response intercept(Interceptor.Chain chain) throws IOException {
                String string2 = StubApp.getString2(109);
                String string22 = StubApp.getString2(13082);
                Response responseProceed = chain.proceed(chain.request());
                String string = chain.request().url().toString();
                ResponseBody responseBodyBody = responseProceed.body();
                if (responseBodyBody != null) {
                    String strString = responseBodyBody.string();
                    int iCode = responseProceed.code();
                    String string23 = StubApp.getString2(20951);
                    if (iCode == 200 && !string.contains(string23)) {
                        try {
                            JSONObject jSONObject = new JSONObject(strString);
                            if (jSONObject.getJSONObject(string22).getInt(string2) == 10101) {
                                SendOffLineBr.sendOffLineBr(StubApp.getString2("13935"), BaseApp.getInstance().getResources().getString(R.string.offline_tip_content));
                            } else if (jSONObject.getJSONObject(string22).getInt(string2) == 10100 || jSONObject.getJSONObject(string22).getInt(string2) == 10110) {
                                SendOffLineBr.sendOffLineBr(StubApp.getString2("13973"), BaseApp.getInstance().getResources().getString(R.string.token_expire_tip_content));
                            }
                        } catch (JSONException unused) {
                        }
                    } else if (!string.contains(string23)) {
                        LogDebug.INSTANCE.log(StubApp.getString2(44733) + chain.request().url().toString() + StubApp.getString2(1026) + strString);
                    }
                    return responseProceed.newBuilder().body(ResponseBody.create(responseBodyBody.contentType(), strString)).build();
                }
                return responseProceed.newBuilder().body(ResponseBody.create(responseBodyBody.contentType(), responseBodyBody.string())).build();
            }
        }).build();
    }

    public OkHttpClient getClient() {
        return this.client;
    }

    public OkHttpClient getUplaodClient() {
        return this.client.newBuilder().readTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).connectTimeout(30L, TimeUnit.SECONDS).build();
    }

    public void setClient(OkHttpClient okHttpClient) {
        this.client = okHttpClient;
    }

    protected Request getRequest(String str, RequestBody requestBody) {
        if (this.mToken == null) {
            this.mToken = "";
        }
        int length = this.mToken.length();
        String string2 = StubApp.getString2(4019);
        String string22 = StubApp.getString2(557);
        if (length > 0) {
            return new Request.Builder().url(str).post(requestBody).addHeader(string22, string2).addHeader(this.mPushClientIdKey, getPushClientId()).addHeader(StubApp.getString2(672), StubApp.getString2(13974) + this.mToken).build();
        }
        return new Request.Builder().url(str).post(requestBody).addHeader(string22, string2).addHeader(this.mPushClientIdKey, getPushClientId()).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Request getRequestWithoutPushClientId(String str, RequestBody requestBody) {
        if (this.mToken == null) {
            this.mToken = "";
        }
        int length = this.mToken.length();
        String string2 = StubApp.getString2(4019);
        String string22 = StubApp.getString2(557);
        if (length > 0) {
            return new Request.Builder().url(str).post(requestBody).addHeader(string22, string2).addHeader(StubApp.getString2(672), StubApp.getString2(13974) + this.mToken).build();
        }
        return new Request.Builder().url(str).post(requestBody).addHeader(string22, string2).build();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest$3] */
    protected void doHttpPost(final String str, final RequestBody requestBody, final FogCallBack fogCallBack) {
        if (this.client != null) {
            new Thread() { // from class: io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest.3
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        DeYeBaseHttpRequest.this.requestCallback(DeYeBaseHttpRequest.this.getRequest(str, requestBody), fogCallBack, StubApp.getString2("44734"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest$4] */
    protected void doHttpPostWithoutClientId(final String str, final RequestBody requestBody, final FogCallBack fogCallBack) {
        if (this.client != null) {
            new Thread() { // from class: io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest.4
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        DeYeBaseHttpRequest.this.requestCallback(DeYeBaseHttpRequest.this.getRequestWithoutPushClientId(str, requestBody), fogCallBack, StubApp.getString2("44734"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest$5] */
    protected void doUploadFileHttpPost(final String str, final RequestBody requestBody, final FogCallBack fogCallBack) {
        if (this.client != null) {
            new Thread() { // from class: io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest.5
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        DeYeBaseHttpRequest.this.requestCallback(new Request.Builder().url(str).post(requestBody).addHeader(StubApp.getString2("557"), StubApp.getString2("18454")).addHeader(DeYeBaseHttpRequest.this.mPushClientIdKey, DeYeBaseHttpRequest.this.getPushClientId()).addHeader(StubApp.getString2("672"), StubApp.getString2(13974) + DeYeBaseHttpRequest.this.mToken).build(), fogCallBack, StubApp.getString2("44735"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestCallback(final Request request, final FogCallBack fogCallBack, String str) {
        if (request != null) {
            this.client.newCall(request).enqueue(new Callback() { // from class: io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest.6
                public void onFailure(Call call, IOException iOException) throws IOException {
                    String string2 = StubApp.getString2(13969);
                    LogUtil.d(DeYeBaseHttpRequest.TAG, StubApp.getString2(13970) + iOException.getMessage());
                    DeYeBaseHttpRequest.this.failureCBFilterUser(HttpServletResponse.SC_NOT_FOUND, StubApp.getString2(44736), fogCallBack);
                    try {
                        throw new IOException(string2 + iOException.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void onResponse(Call call, Response response) throws IOException {
                    String string2 = StubApp.getString2(13969);
                    String string22 = StubApp.getString2(13972);
                    try {
                        String strString = response.body().string();
                        LogUtil.d(DeYeBaseHttpRequest.TAG, string22 + strString);
                        if (!response.isSuccessful()) {
                            DeYeBaseHttpRequest.this.failureCBFilterUser(response.code(), response.message(), fogCallBack);
                            throw new IOException(string2 + response);
                        }
                        BaseBean baseBean = (BaseBean) JSON.parseObject(strString, BaseBean.class);
                        if (!request.url().url().toString().contains(StubApp.getString2("20951"))) {
                            if (baseBean.getMeta() != null && baseBean.getMeta().getCode() == 10101) {
                                SendOffLineBr.sendOffLineBr(StubApp.getString2("13935"), BaseApp.getInstance().getResources().getString(R.string.offline_tip_content));
                            } else if (baseBean.getMeta() != null && (baseBean.getMeta().getCode() == 10100 || baseBean.getMeta().getCode() == 10110)) {
                                SendOffLineBr.sendOffLineBr(StubApp.getString2("13973"), BaseApp.getInstance().getResources().getString(R.string.token_expire_tip_content));
                            }
                        }
                        DeYeBaseHttpRequest.this.succeesCBFilterUser(strString, fogCallBack);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest$7] */
    protected void doHttpPut(final String str, final RequestBody requestBody, final FogCallBack fogCallBack) {
        if (this.client != null) {
            new Thread() { // from class: io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest.7
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        DeYeBaseHttpRequest.this.requestCallback(new Request.Builder().url(str).put(requestBody).addHeader(StubApp.getString2("557"), StubApp.getString2("4019")).addHeader(DeYeBaseHttpRequest.this.mPushClientIdKey, DeYeBaseHttpRequest.this.getPushClientId()).addHeader(StubApp.getString2("672"), StubApp.getString2(13974) + DeYeBaseHttpRequest.this.mToken).build(), fogCallBack, StubApp.getString2("44737"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest$8] */
    protected void doHttpGet(final String str, final FogCallBack fogCallBack) {
        if (this.client != null) {
            new Thread() { // from class: io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest.8
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        DeYeBaseHttpRequest.this.requestCallback(new Request.Builder().url(str).addHeader(StubApp.getString2("557"), StubApp.getString2("4019")).addHeader(DeYeBaseHttpRequest.this.mPushClientIdKey, DeYeBaseHttpRequest.this.getPushClientId()).addHeader(StubApp.getString2("672"), StubApp.getString2(13974) + DeYeBaseHttpRequest.this.mToken).build(), fogCallBack, StubApp.getString2("44738"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest$9] */
    protected void doHttpDelete(final String str, final FogCallBack fogCallBack) {
        if (this.client != null) {
            new Thread() { // from class: io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest.9
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        DeYeBaseHttpRequest.this.requestCallback(new Request.Builder().url(str).delete().addHeader(StubApp.getString2("557"), StubApp.getString2("4019")).addHeader(DeYeBaseHttpRequest.this.mPushClientIdKey, DeYeBaseHttpRequest.this.getPushClientId()).addHeader(StubApp.getString2("672"), StubApp.getString2(13974) + DeYeBaseHttpRequest.this.mToken).build(), fogCallBack, StubApp.getString2("44732"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest$10] */
    protected void doHttpDeleteWithBody(final String str, final RequestBody requestBody, final FogCallBack fogCallBack) {
        if (this.client != null) {
            new Thread() { // from class: io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest.10
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        DeYeBaseHttpRequest.this.requestCallback(new Request.Builder().url(str).delete(requestBody).addHeader(StubApp.getString2("557"), StubApp.getString2("4019")).addHeader(DeYeBaseHttpRequest.this.mPushClientIdKey, DeYeBaseHttpRequest.this.getPushClientId()).addHeader(StubApp.getString2("672"), StubApp.getString2(13974) + DeYeBaseHttpRequest.this.mToken).build(), fogCallBack, StubApp.getString2("44732"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
