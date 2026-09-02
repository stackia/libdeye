package com.deye.data;

import com.alibaba.fastjson.JSON;
import com.deye.MxchipApplication;
import com.mxchipapp.R;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import io.fogcloud.sdk.fog.BaseApp;
import io.fogcloud.sdk.fog.api.http.DeYeBaseHttpRequest;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.bean.BaseBean;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import io.fogcloud.sdk.fog.helper.SendOffLineBr;
import io.fogcloud.sdk.fog.log.LogUtil;
import io.fogcloud.sdk.fog.utils.SharedPrefsUtil;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DeyeMessageRepository.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJ$\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000f2\u0006\u0010\f\u001a\u00020\rJ1\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\n2!\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\b0\u0013J\b\u0010\u0017\u001a\u00020\bH\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0016\u0010\u001a\u001a\u00020\u00192\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000fH\u0002J\u001e\u0010\u001b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/deye/data/DeyeMessageRepository;", "Lio/fogcloud/sdk/fog/api/http/DeYeBaseHttpRequest;", "()V", "mSharedPrefsUtil", "Lio/fogcloud/sdk/fog/utils/SharedPrefsUtil;", "requestBuilder", "Lokhttp3/Request$Builder;", "deleteMessage", "", "url", "", "messageId", "fogCallBack", "Lio/fogcloud/sdk/fog/callback/FogCallBack;", "messageIdList", "", "doHttpGetRepository", "httpsUrl", "result", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "dataJson", "initOkHttpClient", "jsonBodyMessageId", "Lokhttp3/RequestBody;", "jsonBodyMessageIdList", "postReadMessage", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class DeyeMessageRepository extends DeYeBaseHttpRequest {
    private SharedPrefsUtil mSharedPrefsUtil;
    private Request.Builder requestBuilder;
    public static final String TAG = StubApp.getString2(13971);

    public DeyeMessageRepository() {
        initOkHttpClient();
    }

    private final void initOkHttpClient() {
        SharedPrefsUtil sharePrefsUtil = MxchipApplication.getInstance().getSharePrefsUtil();
        Intrinsics.checkNotNullExpressionValue(sharePrefsUtil, "getSharePrefsUtil(...)");
        this.mSharedPrefsUtil = sharePrefsUtil;
        this.mToken = MMKV.defaultMMKV().decodeString(StubApp.getString2(13134));
        Request.Builder builderAddHeader = new Request.Builder().addHeader(StubApp.getString2(557), StubApp.getString2(4019));
        String mPushClientIdKey = this.mPushClientIdKey;
        Intrinsics.checkNotNullExpressionValue(mPushClientIdKey, "mPushClientIdKey");
        String pushClientId = getPushClientId();
        Intrinsics.checkNotNullExpressionValue(pushClientId, "getPushClientId(...)");
        this.requestBuilder = builderAddHeader.addHeader(mPushClientIdKey, pushClientId).addHeader(StubApp.getString2(672), StubApp.getString2(13974) + this.mToken);
    }

    public final void doHttpGetRepository(String httpsUrl, final Function1<? super String, Unit> result) {
        Intrinsics.checkNotNullParameter(httpsUrl, "httpsUrl");
        Intrinsics.checkNotNullParameter(result, "result");
        if (getClient() != null) {
            try {
                Request.Builder builder = this.requestBuilder;
                Request.Builder builder2 = null;
                String string2 = StubApp.getString2(13976);
                if (builder == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    builder = null;
                }
                builder.url(httpsUrl);
                OkHttpClient client = getClient();
                Request.Builder builder3 = this.requestBuilder;
                if (builder3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                } else {
                    builder2 = builder3;
                }
                client.newCall(builder2.build()).enqueue(new Callback() { // from class: com.deye.data.DeyeMessageRepository.doHttpGetRepository.1
                    public void onFailure(Call call, IOException e) throws IOException {
                        String string22 = StubApp.getString2(13969);
                        Intrinsics.checkNotNullParameter(call, "call");
                        Intrinsics.checkNotNullParameter(e, "e");
                        LogUtil.d(StubApp.getString2(13971), StubApp.getString2(13970) + e.getMessage());
                        try {
                            throw new IOException(string22 + e.getMessage());
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }

                    public void onResponse(Call call, Response response) throws IOException {
                        String string22 = StubApp.getString2(13969);
                        String string23 = StubApp.getString2(13972);
                        Intrinsics.checkNotNullParameter(call, "call");
                        Intrinsics.checkNotNullParameter(response, "response");
                        try {
                            ResponseBody responseBodyBody = response.body();
                            Intrinsics.checkNotNull(responseBodyBody);
                            String strString = responseBodyBody.string();
                            LogUtil.d(StubApp.getString2("13971"), string23 + strString);
                            if (response.isSuccessful()) {
                                BaseBean baseBean = (BaseBean) JSON.parseObject(strString, BaseBean.class);
                                if (baseBean.getMeta() != null && baseBean.getMeta().getCode() == 10101) {
                                    SendOffLineBr.sendOffLineBr(StubApp.getString2("13935"), BaseApp.getInstance().getResources().getString(R.string.offline_tip_content));
                                } else if (baseBean.getMeta() != null && baseBean.getMeta().getCode() == 10100) {
                                    SendOffLineBr.sendOffLineBr(StubApp.getString2("13973"), BaseApp.getInstance().getResources().getString(R.string.token_expire_tip_content));
                                }
                                result.invoke(strString);
                                return;
                            }
                            result.invoke(strString);
                            throw new IOException(string22 + response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final void deleteMessage(String url, String messageId, FogCallBack fogCallBack) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(messageId, "messageId");
        Intrinsics.checkNotNullParameter(fogCallBack, "fogCallBack");
        if (getClient() == null) {
            return;
        }
        doHttpDeleteWithBody(url, jsonBodyMessageId(messageId), fogCallBack);
    }

    public final void postReadMessage(String url, String messageId, FogCallBack fogCallBack) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(messageId, "messageId");
        Intrinsics.checkNotNullParameter(fogCallBack, "fogCallBack");
        if (getClient() == null) {
            return;
        }
        doHttpPost(url, jsonBodyMessageId(messageId), fogCallBack);
    }

    private final RequestBody jsonBodyMessageId(String messageId) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        SharedPrefsUtil sharedPrefsUtil = this.mSharedPrefsUtil;
        if (sharedPrefsUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSharedPrefsUtil");
            sharedPrefsUtil = null;
        }
        String string2 = StubApp.getString2(13360);
        String string22 = StubApp.getString2(13948);
        String value = sharedPrefsUtil.getValue(string2, string22, "");
        jSONObject.put(StubApp.getString2(13229), messageId);
        jSONObject.put(string22, value);
        RequestBody.Companion companion = RequestBody.Companion;
        MediaType mediaType = DeYeHttpRequestManager.JSON_Type;
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return companion.create(mediaType, string);
    }

    public final void deleteMessage(String url, List<String> messageIdList, FogCallBack fogCallBack) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(messageIdList, "messageIdList");
        Intrinsics.checkNotNullParameter(fogCallBack, "fogCallBack");
        if (getClient() == null) {
            return;
        }
        doHttpDeleteWithBody(url, jsonBodyMessageIdList(messageIdList), fogCallBack);
    }

    private final RequestBody jsonBodyMessageIdList(List<String> messageIdList) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        SharedPrefsUtil sharedPrefsUtil = this.mSharedPrefsUtil;
        if (sharedPrefsUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSharedPrefsUtil");
            sharedPrefsUtil = null;
        }
        String string2 = StubApp.getString2(13360);
        String string22 = StubApp.getString2(13948);
        String value = sharedPrefsUtil.getValue(string2, string22, "");
        JSONArray jSONArray = new JSONArray();
        Iterator<T> it2 = messageIdList.iterator();
        while (it2.hasNext()) {
            jSONArray.put(((String) it2.next()).toString());
        }
        jSONObject.put(StubApp.getString2(13975), jSONArray);
        jSONObject.put(string22, value);
        RequestBody.Companion companion = RequestBody.Companion;
        MediaType mediaType = DeYeHttpRequestManager.JSON_Type;
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return companion.create(mediaType, string);
    }
}
