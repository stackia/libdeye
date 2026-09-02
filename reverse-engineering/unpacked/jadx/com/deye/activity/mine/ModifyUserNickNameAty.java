package com.deye.activity.mine;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.alibaba.fastjson.JSON;
import com.deye.activity.device.base.BaseActivity;
import com.deye.entity.UserInfoBean;
import com.deye.utils.BaseUtils;
import com.deye.utils.MMKVUtils;
import com.deye.views.CleanEditText;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ModifyUserNicknameBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.BaseBean;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import io.fogcloud.sdk.fog.log.LogUtil;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ModifyUserNickNameAty extends BaseActivity implements View.OnClickListener {
    private ModifyUserNicknameBinding mModifyUserNicknameBinding;
    private String mNickName;
    private UserInfoBean userInfoBean;

    static {
        StubApp.interface11(14395);
    }

    private native void initView();

    private native boolean isNickName();

    private native void setUserInfo();

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    /* renamed from: com.deye.activity.mine.ModifyUserNickNameAty$1, reason: invalid class name */
    class AnonymousClass1 implements CleanEditText.OnTextChangeListener {
        AnonymousClass1() {
        }

        @Override // com.deye.views.CleanEditText.OnTextChangeListener
        public void OnTextChange(String str) {
            if (!str.equals(ModifyUserNickNameAty.this.mNickName) && str.length() > 0) {
                ModifyUserNickNameAty.this.mModifyUserNicknameBinding.actionbar.tvActionbarSave.setVisibility(0);
            } else {
                ModifyUserNickNameAty.this.mModifyUserNicknameBinding.actionbar.tvActionbarSave.setVisibility(8);
            }
        }
    }

    /* renamed from: com.deye.activity.mine.ModifyUserNickNameAty$2, reason: invalid class name */
    class AnonymousClass2 implements FogCallBack {
        AnonymousClass2() {
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onSuccess(final String str) {
            Log.d(StubApp.getString2(13749), str);
            ModifyUserNickNameAty.this.runOnUiThread(new Runnable() { // from class: com.deye.activity.mine.ModifyUserNickNameAty.2.1
                @Override // java.lang.Runnable
                public void run() {
                    ModifyUserNickNameAty.this.stopWaiting();
                    BaseBean baseBean = (BaseBean) JSON.parseObject(str, BaseBean.class);
                    if (baseBean.getMeta() != null && baseBean.getMeta().getCode() == 0) {
                        ModifyUserNickNameAty.this.mNickName = ModifyUserNickNameAty.this.mModifyUserNicknameBinding.edUserNickname.getContentText();
                        BaseUtils.showShortToast(ModifyUserNickNameAty.this, ModifyUserNickNameAty.this.getString(R.string.nickname_update_successful));
                        if (ModifyUserNickNameAty.this.userInfoBean != null) {
                            ModifyUserNickNameAty.this.userInfoBean.setNickname(ModifyUserNickNameAty.this.mNickName);
                            MMKVUtils.INSTANCE.setUserInfo(ModifyUserNickNameAty.this.userInfoBean);
                        }
                        ModifyUserNickNameAty.this.finish();
                        return;
                    }
                    BaseUtils.showShortToast(ModifyUserNickNameAty.this, ModifyUserNickNameAty.this.getString(R.string.nickname_update_failed));
                }
            });
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onFailure(int i, String str) {
            LogUtil.d(StubApp.getString2(13749), str);
            ModifyUserNickNameAty.this.runOnUiThread(new Runnable() { // from class: com.deye.activity.mine.ModifyUserNickNameAty.2.2
                @Override // java.lang.Runnable
                public void run() {
                    ModifyUserNickNameAty.this.stopWaiting();
                    BaseUtils.showShortToast(ModifyUserNickNameAty.this, ModifyUserNickNameAty.this.getString(R.string.nickname_update_failed));
                }
            });
        }
    }
}
