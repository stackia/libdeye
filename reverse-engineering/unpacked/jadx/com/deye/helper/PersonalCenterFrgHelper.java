package com.deye.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.deye.entity.UserInfoBean;
import com.deye.entity.UserInfoResult;
import com.deye.fragment.PersonalCenterFrg;
import com.deye.utils.MMKVUtils;
import com.deye.utils.StringUtils;
import com.google.gson.Gson;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.io.File;
import org.json.JSONException;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class PersonalCenterFrgHelper {
    public static final int GET_VERSION_SUCCESS = 400;
    public static final int TMall_Off = 200;
    public static final int TMall_On = 100;
    public static final int TMall_Set = 300;
    private Activity mActivity;
    private PersonalCenterFrg mPersonalCenterFrg;
    private UserInfoBean userInfoBean = MMKVUtils.INSTANCE.getUserInfo();

    public PersonalCenterFrgHelper(PersonalCenterFrg personalCenterFrg) {
        this.mPersonalCenterFrg = personalCenterFrg;
        this.mActivity = personalCenterFrg.getActivity();
    }

    public static String getVerName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void requestUserInfo() throws Resources.NotFoundException {
        UserInfoBean userInfoBean = this.userInfoBean;
        if (userInfoBean != null) {
            String nickname = userInfoBean.getNickname();
            if (nickname != null && !"".equals(nickname)) {
                this.mPersonalCenterFrg.mPersonalCenterFrgBinding.tvNickname.setText(nickname);
            }
            setHeadPortrait(this.userInfoBean.getAvatar());
        }
        DeYeHttpRequestManager.getInstance().getUserInfo(new FogCallBack() { // from class: com.deye.helper.PersonalCenterFrgHelper.1
            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onSuccess(String str) throws JSONException {
                LogUtil.d(StubApp.getString2(14180), str);
                UserInfoResult userInfoResult = (UserInfoResult) new Gson().fromJson(str, UserInfoResult.class);
                PersonalCenterFrgHelper.this.userInfoBean = userInfoResult.getData();
                MMKVUtils.INSTANCE.setUserInfo(PersonalCenterFrgHelper.this.userInfoBean);
                PersonalCenterFrgHelper personalCenterFrgHelper = PersonalCenterFrgHelper.this;
                personalCenterFrgHelper.setUserNickName(personalCenterFrgHelper.userInfoBean.getNickname(), PersonalCenterFrgHelper.this.userInfoBean.getAvatar());
            }

            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onFailure(int i, String str) {
                Log.d(StubApp.getString2(14179), str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUserNickName(String str, final String str2) throws JSONException {
        if (str == null || "".equals(str)) {
            final String str3 = StubApp.getString2(14181) + StringUtils.createRandom(0, 4) + StringUtils.createRandom(2, 5);
            DeYeHttpRequestManager.getInstance().setUserNickName(str3, StubApp.getString2(7682), new FogCallBack() { // from class: com.deye.helper.PersonalCenterFrgHelper.2
                @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                public void onFailure(int i, String str4) {
                }

                @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                public void onSuccess(String str4) {
                    if (PersonalCenterFrgHelper.this.mActivity == null) {
                        return;
                    }
                    PersonalCenterFrgHelper.this.mActivity.runOnUiThread(new Runnable() { // from class: com.deye.helper.PersonalCenterFrgHelper.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PersonalCenterFrgHelper.this.mPersonalCenterFrg.mPersonalCenterFrgBinding.tvNickname.setText(str3 + "");
                        }
                    });
                }
            });
            return;
        }
        Activity activity = this.mActivity;
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.deye.helper.PersonalCenterFrgHelper.3
            @Override // java.lang.Runnable
            public void run() throws Resources.NotFoundException {
                PersonalCenterFrgHelper.this.mPersonalCenterFrg.mPersonalCenterFrgBinding.tvNickname.setText(PersonalCenterFrgHelper.this.userInfoBean.getNickname());
                PersonalCenterFrgHelper.this.setHeadPortrait(str2);
            }
        });
    }

    public void setHeadPortrait(String str) throws Resources.NotFoundException {
        Activity activity;
        Drawable drawable = this.mActivity.getResources().getDrawable(R.mipmap.head_icon);
        File file = new File(UserAvatarHelper.getAvatarCachePath(this.mActivity));
        if (file.exists()) {
            drawable = Drawable.createFromPath(file.getAbsolutePath());
        }
        RequestOptions requestOptionsError = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE).placeholder(drawable).error(R.mipmap.head_icon);
        if (this.mPersonalCenterFrg == null || (activity = this.mActivity) == null || activity.isDestroyed()) {
            return;
        }
        Glide.with(StubApp.getOrigApplicationContext(this.mActivity.getApplicationContext())).load(str).apply((BaseRequestOptions<?>) requestOptionsError).into(this.mPersonalCenterFrg.mPersonalCenterFrgBinding.ivHeadPortrait);
    }
}
