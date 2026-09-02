package com.deye.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import com.facebook.imagepipeline.common.RotationOptions;
import com.mxchipapp.R;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.stub.StubApp;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.umverify.UMConstant;
import com.umeng.umverify.UMVerifyHelper;
import com.umeng.umverify.listener.UMTokenResultListener;
import com.umeng.umverify.view.UMAuthUIConfig;
import io.fogcloud.sdk.fog.bean.FindItemBean;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UmUtils.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jr\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2b\u0010\r\u001a^\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\n0\u000eJ\u000e\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ=\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00192-\u0010\r\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u001b¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\n0\u001aJ\u0006\u0010\u001d\u001a\u00020\nJ\u000e\u0010\u001e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\u001f\u001a\u00020\nJ\u000e\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\u0019J\u0006\u0010\"\u001a\u00020\nJ\u001e\u0010#\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%J\u0016\u0010'\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010(\u001a\u00020)J\u000e\u0010*\u001a\u00020\n2\u0006\u0010+\u001a\u00020\u0004J\u000e\u0010,\u001a\u00020\n2\u0006\u0010+\u001a\u00020\u0004J\u000e\u0010-\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/deye/utils/UmUtils;", "", "()V", "TAG", "", "TOKEN", "appKey", "umVerifyHelper", "Lcom/umeng/umverify/UMVerifyHelper;", "checkOneKeyLoginEnable", "", "context", "Landroid/content/Context;", "callback", "Lkotlin/Function4;", "", "Lkotlin/ParameterName;", "name", "suc", "", "code", "msg", "token", "initUm", "initWechat", "Landroid/app/Activity;", "Lkotlin/Function1;", "", "date", "loginOUt", "preInitUm", "quitLogin", "setShareConfig", "activity", "signIn", "startOneKeyLogin", "width", "", "height", "trackEvent", "findItemBean", "Lio/fogcloud/sdk/fog/bean/FindItemBean;", "trackPageEndEvent", "page", "trackPageStartEvent", "trackTabFindClickEvent", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class UmUtils {
    private static UMVerifyHelper umVerifyHelper;
    public static final String TAG = StubApp.getString2(14456);
    public static final String TOKEN = StubApp.getString2(14457);
    public static final String appKey = StubApp.getString2(14098);
    public static final UmUtils INSTANCE = new UmUtils();

    public final void loginOUt() {
    }

    private UmUtils() {
    }

    public final void preInitUm(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        UMConfigure.preInit(context, StubApp.getString2(14098), StubApp.getString2(1347));
    }

    public final void initUm(Context context) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(context, "context");
        if (UMConfigure.isInit) {
            return;
        }
        UMConfigure.init(context, StubApp.getString2(14098), StubApp.getString2(1347), 1, "");
        UMConfigure.setLogEnabled(false);
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
    }

    public final void signIn() {
        MMKVUtils.INSTANCE.getUserInfo();
    }

    public final void trackPageStartEvent(String page) {
        Intrinsics.checkNotNullParameter(page, "page");
        MobclickAgent.onPageStart(page);
    }

    public final void trackPageEndEvent(String page) {
        Intrinsics.checkNotNullParameter(page, "page");
        MobclickAgent.onPageEnd(page);
    }

    public final void trackEvent(Context context, FindItemBean findItemBean) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(findItemBean, "findItemBean");
        HashMap map = new HashMap();
        map.put(StubApp.getString2(14462), String.valueOf(findItemBean.getArticle_id()));
        String url = findItemBean.getUrl();
        if (url == null) {
            url = "";
        }
        map.put(StubApp.getString2(4700), url);
        String title = findItemBean.getTitle();
        map.put(StubApp.getString2(14463), title != null ? title : "");
        MobclickAgent.onEventObject(context, StubApp.getString2(14464), map);
    }

    public final void trackTabFindClickEvent(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        MobclickAgent.onEvent(context, StubApp.getString2(14465));
    }

    public final void setShareConfig(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        UMShareConfig uMShareConfig = new UMShareConfig();
        uMShareConfig.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(activity).setShareConfig(uMShareConfig);
    }

    public final void initWechat(final Activity context, final Function1<? super Map<String, String>, Unit> callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        PlatformConfig.setWeixin(StubApp.getString2(14458), StubApp.getString2(14459));
        UMShareAPI.get(context).getPlatformInfo(context, SHARE_MEDIA.WEIXIN, new UMAuthListener() { // from class: com.deye.utils.UmUtils.initWechat.1
            @Override // com.umeng.socialize.UMAuthListener
            public void onStart(SHARE_MEDIA p0) {
                LogUtil.e(StubApp.getString2(14455));
            }

            @Override // com.umeng.socialize.UMAuthListener
            public void onComplete(SHARE_MEDIA p0, int p1, Map<String, String> p2) {
                Intrinsics.checkNotNullParameter(p2, "p2");
                LogUtil.e(StubApp.getString2(14453) + p2);
                callback.invoke(p2);
            }

            @Override // com.umeng.socialize.UMAuthListener
            public void onError(SHARE_MEDIA p0, int p1, Throwable p2) {
                LogUtil.e(StubApp.getString2(14454) + (p2 != null ? p2.getMessage() : null));
                BaseUtils.showShortToast(context, StubApp.getString2(14087) + (p2 != null ? p2.getMessage() : null));
            }

            @Override // com.umeng.socialize.UMAuthListener
            public void onCancel(SHARE_MEDIA p0, int p1) {
                LogUtil.e(StubApp.getString2(14451));
                BaseUtils.showShortToast(context, StubApp.getString2(14452));
            }
        });
    }

    public final void checkOneKeyLoginEnable(Context context, final Function4<? super Boolean, ? super Integer, ? super String, ? super String, Unit> callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        UMVerifyHelper uMVerifyHelper = UMVerifyHelper.getInstance(context, new UMTokenResultListener() { // from class: com.deye.utils.UmUtils.checkOneKeyLoginEnable.1
            @Override // com.umeng.umverify.listener.UMTokenResultListener
            public void onTokenSuccess(String p0) throws JSONException {
                Intrinsics.checkNotNullParameter(p0, "p0");
                LogUtil.e(StubApp.getString2(14450) + p0);
                try {
                    JSONObject jSONObject = new JSONObject(p0);
                    String string = jSONObject.getString(StubApp.getString2("109"));
                    String string2 = jSONObject.getString(StubApp.getString2("449"));
                    Intrinsics.checkNotNull(string);
                    String string3 = Integer.parseInt(string) == 600000 ? jSONObject.getString(StubApp.getString2("4716")) : null;
                    Function4<Boolean, Integer, String, String, Unit> function4 = callback;
                    Integer numValueOf = Integer.valueOf(Integer.parseInt(string));
                    Intrinsics.checkNotNull(string2);
                    function4.invoke(true, numValueOf, string2, string3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.umeng.umverify.listener.UMTokenResultListener
            public void onTokenFailed(String p0) throws JSONException {
                Intrinsics.checkNotNullParameter(p0, "p0");
                LogUtil.e(StubApp.getString2(14449) + p0);
                try {
                    JSONObject jSONObject = new JSONObject(p0);
                    String string = jSONObject.getString(StubApp.getString2("109"));
                    String string2 = jSONObject.getString(StubApp.getString2("449"));
                    Function4<Boolean, Integer, String, String, Unit> function4 = callback;
                    Intrinsics.checkNotNull(string);
                    Integer numValueOf = Integer.valueOf(Integer.parseInt(string));
                    Intrinsics.checkNotNull(string2);
                    function4.invoke(false, numValueOf, string2, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        umVerifyHelper = uMVerifyHelper;
        if (uMVerifyHelper != null) {
            uMVerifyHelper.setLoggerEnable(false);
        }
        UMVerifyHelper uMVerifyHelper2 = umVerifyHelper;
        if (uMVerifyHelper2 != null) {
            uMVerifyHelper2.setAuthSDKInfo(StubApp.getString2(14457));
        }
        UMVerifyHelper uMVerifyHelper3 = umVerifyHelper;
        if (uMVerifyHelper3 != null) {
            uMVerifyHelper3.checkEnvAvailable(2);
        }
    }

    public final void startOneKeyLogin(Context context, float width, float height) {
        Intrinsics.checkNotNullParameter(context, "context");
        UMAuthUIConfig.Builder navText = new UMAuthUIConfig.Builder().setPageBackgroundDrawable(context.getDrawable(R.drawable.home_device_bg3)).setDialogWidth((int) DensityUtil.px2dp(width)).setDialogHeight(HttpServletResponse.SC_REQUEST_TIMEOUT).setNavText(StubApp.getString2(14460));
        String string2 = StubApp.getString2(13488);
        UMAuthUIConfig.Builder sloganOffsetY = navText.setNavTextColor(Color.parseColor(string2)).setNavTextSizeDp(16).setNavReturnHidden(false).setNavReturnImgDrawable(context.getDrawable(R.drawable.icon_delete)).setSloganOffsetY(60);
        String string22 = StubApp.getString2(14461);
        UMAuthUIConfig uMAuthUIConfigCreate = sloganOffsetY.setSloganTextColor(Color.parseColor(string22)).setSloganTextSizeDp(14).setNumFieldOffsetY(90).setNumberColor(Color.parseColor(string2)).setNumberSizeDp(20).setLogBtnOffsetY(RotationOptions.ROTATE_180).setLogBtnTextColor(-1).setLogBtnTextSizeDp(16).setLogBtnBackgroundDrawable(context.getDrawable(R.drawable.btn_selector)).setLogBtnHeight(44).setPrivacyOffsetY_B(60).setAppPrivacyColor(Color.parseColor(string22), Color.parseColor(string2)).setPrivacyMargin(0).setProtocolLayoutGravity(8388611).setProtocolGravity(8388611).setSwitchAccHidden(true).setDialogBottom(true).create();
        UMVerifyHelper uMVerifyHelper = umVerifyHelper;
        if (uMVerifyHelper != null) {
            uMVerifyHelper.setAuthUIConfig(uMAuthUIConfigCreate);
        }
        UMVerifyHelper uMVerifyHelper2 = umVerifyHelper;
        if (uMVerifyHelper2 != null) {
            uMVerifyHelper2.getLoginToken(context, UMConstant.DEFAULT_TIMEOUT);
        }
    }

    public final void quitLogin() {
        UMVerifyHelper uMVerifyHelper = umVerifyHelper;
        if (uMVerifyHelper != null) {
            uMVerifyHelper.quitLoginPage();
        }
    }
}
