package com.deye.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.format.DateUtils;
import com.alibaba.fastjson.JSON;
import com.amap.api.location.AMapLocationClient;
import com.deye.MxchipApplication;
import com.deye.TabMainActivity;
import com.deye.activity.advertise.AdvertiseActivity;
import com.deye.activity.device.base.BaseActivity;
import com.deye.activity.login.HomePageActivity;
import com.deye.entity.LoginBean;
import com.deye.helper.DialogHelper;
import com.deye.utils.ActivityRouterUtilsKt;
import com.deye.utils.BaseUtils;
import com.deye.utils.MMKVUtils;
import com.google.gson.Gson;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import eyed.moc.DtcLoader;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.bean.AdvertiseInfoBean;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SplashActivity.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\b\u0010\u0010\u001a\u00020\bH\u0002J\b\u0010\u0011\u001a\u00020\bH\u0002J\b\u0010\u0012\u001a\u00020\bH\u0002J\b\u0010\u0013\u001a\u00020\bH\u0002J\b\u0010\u0014\u001a\u00020\bH\u0002J\u0012\u0010\u0015\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\bH\u0014J\b\u0010\u0019\u001a\u00020\u000eH\u0002J\b\u0010\u001a\u001a\u00020\bH\u0002J\u0010\u0010\u001b\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\b\u0010\u001c\u001a\u00020\bH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/deye/activity/SplashActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "mHandler", "Landroid/os/Handler;", "mToken", "", "checkLoginStatus", "", "decideNextNavigation", "ensureMinSplashTime", "startTime", "", "isTokenValid", "", "message", "navigateToAdvertisement", "navigateToLogin", "navigateToLoginPage", "navigateToLoginWithTokenExpired", "navigateToMain", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "shouldShowAdvertisement", "showPrivacyPolicyDialog", "updateToken", "validateToken", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class SplashActivity extends BaseActivity {
    private static final long DELAY_TO_LOGIN = 100;
    private static final long HOME_SPLASH_TIME = 2000;
    private Handler mHandler;
    private String mToken;

    static {
        DtcLoader.init();
        INSTANCE = new Companion(null);
    }

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();

    /* JADX WARN: Multi-variable type inference failed */
    private final void checkLoginStatus() throws RuntimeException {
        String str;
        MMKV mmkvDefaultMMKV = MMKV.defaultMMKV();
        boolean zDecodeBool = mmkvDefaultMMKV.decodeBool(StubApp.getString2(13218), false);
        String strDecodeString = mmkvDefaultMMKV.decodeString(StubApp.getString2(13235), null);
        String string = strDecodeString != null ? StringsKt.trim(strDecodeString).toString() : null;
        if (!zDecodeBool || (str = string) == null || str.length() == 0) {
            string = mmkvDefaultMMKV.decodeString(StubApp.getString2(13134), null);
        }
        this.mToken = string;
        if (TextUtils.isEmpty(string)) {
            navigateToLogin();
        } else if (BaseUtils.isNetworkConnected((Context) this)) {
            validateToken();
        } else {
            navigateToLogin();
        }
    }

    private final void navigateToLogin() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.deye.activity.SplashActivity$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    SplashActivity.navigateToLogin$lambda$0(this.f$0);
                }
            }, HOME_SPLASH_TIME);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void navigateToLogin$lambda$0(SplashActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean zCheckAgreePolicy = MMKVUtils.INSTANCE.checkAgreePolicy();
        AMapLocationClient.updatePrivacyShow((Context) this$0.mContext, true, true);
        if (zCheckAgreePolicy) {
            this$0.navigateToLoginPage();
        } else {
            this$0.showPrivacyPolicyDialog();
        }
    }

    private final void showPrivacyPolicyDialog() {
        DialogHelper.showFirstProtocolTipDialog(this, new DialogHelper.OnDialogListener() { // from class: com.deye.activity.SplashActivity.showPrivacyPolicyDialog.1
            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onSure(String text) {
                MMKVUtils.INSTANCE.setAgreePolicy();
                AMapLocationClient.updatePrivacyAgree((Context) SplashActivity.this.mContext, true);
                SplashActivity.this.navigateToLoginPage();
            }

            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onCancel() {
                super.onCancel();
                SplashActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void navigateToLoginPage() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.deye.activity.SplashActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SplashActivity.navigateToLoginPage$lambda$1(this.f$0);
                }
            }, DELAY_TO_LOGIN);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void navigateToLoginPage$lambda$1(SplashActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent((Context) this$0, (Class<?>) HomePageActivity.class));
        this$0.finish();
    }

    private final void validateToken() {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        DeYeHttpRequestManager.getInstance().refreshToken(this.mToken, new FogCallBack() { // from class: com.deye.activity.SplashActivity.validateToken.1
            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onSuccess(String message) throws InterruptedException {
                Intrinsics.checkNotNullParameter(message, "message");
                LogUtil.d(StubApp.getString2(13234) + message);
                if (!SplashActivity.this.isTokenValid(message)) {
                    SplashActivity.this.navigateToLoginWithTokenExpired();
                    return;
                }
                SplashActivity.this.ensureMinSplashTime(jCurrentTimeMillis);
                SplashActivity.this.updateToken(message);
                SplashActivity.this.decideNextNavigation();
            }

            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onFailure(int code, String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                LogUtil.v(StubApp.getString2(13232) + code + StubApp.getString2(13233) + message);
                SplashActivity.this.navigateToLoginWithTokenExpired();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isTokenValid(String message) throws JSONException {
        try {
            int i = new JSONObject(message).getJSONObject(StubApp.getString2("13082")).getInt(StubApp.getString2("109"));
            return (i == 10002 || i == 10110 || i == 10101) ? false : true;
        } catch (JSONException e) {
            e.printStackTrace();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void ensureMinSplashTime(long startTime) throws InterruptedException {
        long jCurrentTimeMillis = System.currentTimeMillis() - startTime;
        if (jCurrentTimeMillis < HOME_SPLASH_TIME) {
            try {
                Thread.sleep(HOME_SPLASH_TIME - jCurrentTimeMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateToken(String message) {
        LoginBean.DataBean data;
        String token;
        try {
            LoginBean loginBean = (LoginBean) JSON.parseObject(message, LoginBean.class);
            if (loginBean == null || (data = loginBean.getData()) == null || (token = data.getToken()) == null) {
                return;
            }
            DeYeHttpRequestManager.getInstance().setToken(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void decideNextNavigation() {
        if (shouldShowAdvertisement()) {
            MxchipApplication.setColdBootApp(true);
            navigateToAdvertisement();
        } else {
            navigateToMain();
        }
    }

    private final void navigateToAdvertisement() {
        Intent intent = new Intent(StubApp.getOrigApplicationContext(getApplicationContext()), (Class<?>) AdvertiseActivity.class);
        intent.setFlags(268468224);
        startActivity(intent);
        finish();
    }

    private final void navigateToMain() {
        Intent intent = new Intent((Context) this.mContext, (Class<?>) TabMainActivity.class);
        intent.putExtra(StubApp.getString2(13237), true);
        startActivityWithFadeIn(intent);
        finishNoAnim();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void navigateToLoginWithTokenExpired() {
        Context context = (Context) this;
        Intent intent = new Intent(context, (Class<?>) HomePageActivity.class);
        intent.setFlags(268468224);
        intent.putExtra(StubApp.getString2(13236), true);
        ActivityRouterUtilsKt.routingForLoginActivity(context, intent);
        finish();
    }

    private final boolean shouldShowAdvertisement() {
        String str;
        Date date;
        String adInfo = MMKVUtils.INSTANCE.getAdInfo();
        String str2 = adInfo;
        if (str2 != null && str2.length() != 0) {
            try {
                AdvertiseInfoBean advertiseInfoBean = (AdvertiseInfoBean) new Gson().fromJson(adInfo, AdvertiseInfoBean.class);
                if (advertiseInfoBean == null) {
                    return false;
                }
                String show_start = advertiseInfoBean.getShow_start();
                String show_end = advertiseInfoBean.getShow_end();
                String str3 = show_start;
                if (str3 == null || str3.length() == 0 || (str = show_end) == null || str.length() == 0) {
                    return false;
                }
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(StubApp.getString2("13238"));
                    Date date2 = new Date();
                    Date date3 = simpleDateFormat.parse(show_start);
                    if (date3 == null || (date = simpleDateFormat.parse(show_end)) == null || !date2.after(date3) || !date2.before(date) || DateUtils.isToday(MMKVUtils.INSTANCE.getAdShowTime())) {
                        return false;
                    }
                    MMKVUtils.INSTANCE.setAdShowTime(System.currentTimeMillis());
                    return true;
                } catch (ParseException e) {
                    e.printStackTrace();
                    return false;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
}
