package com.deye.activity.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import com.alibaba.fastjson.JSON;
import com.deye.activity.device.base.BaseActivity;
import com.deye.entity.CheckCodeBean;
import com.deye.utils.BaseUtils;
import com.deye.utils.MMKVUtils;
import com.mxchipapp.R;
import com.mxchipapp.databinding.SetPwdAtyBinding;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.bean.BaseBean;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import it.innove.Peripheral;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class RegisterCodeActivity extends BaseActivity implements View.OnClickListener, TextWatcher, CompoundButton.OnCheckedChangeListener {
    private static final int CHECK_CODE_FAIL = 100;
    private static final int REGISTER_PASSWORD_FAIL = 300;
    private static final int REGISTER_PASSWORD_SUCCESS = 200;
    private static final int SEND_CODE_FAIL = 500;
    private static final int SEND_CODE_SUCCESS = 400;
    private BaseBean mBaseBean;
    private CheckCodeBean mCheckCodeBean;
    private String mCodeNumber;
    private String mPwd;
    private SetPwdAtyBinding mSetPwdAtyBinding;
    private String mStrPhoneNumber;
    private String mUIFlag;
    private String modifyFailedReason = "";
    private Handler mHandler = new Handler() { // from class: com.deye.activity.login.RegisterCodeActivity.1
        /* JADX WARN: Type inference failed for: r4v20, types: [android.content.Context, com.deye.activity.login.RegisterCodeActivity] */
        /* JADX WARN: Type inference failed for: r4v21, types: [android.content.Context, com.deye.activity.login.RegisterCodeActivity] */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            RegisterCodeActivity.this.mSetPwdAtyBinding.btnSubmit.stopAnimation();
            RegisterCodeActivity.this.stopWaiting();
            int i = message.what;
            if (i == 100) {
                BaseUtils.showShortToast(R.string.verification_code_error);
                return;
            }
            String string2 = StubApp.getString2(2546);
            String string22 = StubApp.getString2(701);
            if (i == 200) {
                if (string22.equals(RegisterCodeActivity.this.mUIFlag)) {
                    BaseUtils.showShortToast(R.string.registration_success);
                } else if (string2.equals(RegisterCodeActivity.this.mUIFlag)) {
                    BaseUtils.showShortToast(R.string.reset_password_success);
                }
                MMKV.defaultMMKV().encode(StubApp.getString2(13134), "");
                MMKVUtils.INSTANCE.setUserInfo(null);
                RegisterCodeActivity registerCodeActivity = RegisterCodeActivity.this;
                registerCodeActivity.goLoginPage(registerCodeActivity, true);
                return;
            }
            if (i != 300) {
                if (i == 400) {
                    BaseUtils.showShortToast(R.string.get_verification_code_success);
                    return;
                } else {
                    if (i != 500) {
                        return;
                    }
                    BaseUtils.showShortToast(R.string.get_verification_code_failure);
                    return;
                }
            }
            if (string22.equals(RegisterCodeActivity.this.mUIFlag)) {
                ?? r4 = RegisterCodeActivity.this;
                BaseUtils.showShortToast(r4, ((RegisterCodeActivity) r4).modifyFailedReason);
            } else if (string2.equals(RegisterCodeActivity.this.mUIFlag)) {
                ?? r42 = RegisterCodeActivity.this;
                BaseUtils.showShortToast(r42, ((RegisterCodeActivity) r42).modifyFailedReason);
            }
        }
    };
    CountDownTimer mGetCodeNumberTimer = new CountDownTimer(62000, 1000) { // from class: com.deye.activity.login.RegisterCodeActivity.3
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            RegisterCodeActivity.this.mSetPwdAtyBinding.tvCountDown.setEnabled(false);
            RegisterCodeActivity.this.mSetPwdAtyBinding.tvCountDown.setText(((j / 1000) - 1) + StubApp.getString2(1740));
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            RegisterCodeActivity.this.mSetPwdAtyBinding.tvCountDown.setEnabled(true);
            RegisterCodeActivity.this.mSetPwdAtyBinding.tvCountText.setText(R.string.cant_receive_code);
            RegisterCodeActivity.this.mSetPwdAtyBinding.tvCountDown.setText(R.string.get_again);
        }
    };

    static {
        StubApp.interface11(14285);
    }

    private native void initView();

    @Override // android.text.TextWatcher
    public native void afterTextChanged(Editable editable);

    @Override // android.text.TextWatcher
    public native void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3);

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public native void onCheckedChanged(CompoundButton compoundButton, boolean z);

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();

    @Override // android.text.TextWatcher
    public native void onTextChanged(CharSequence charSequence, int i, int i2, int i3);

    /* renamed from: com.deye.activity.login.RegisterCodeActivity$2, reason: invalid class name */
    class AnonymousClass2 implements FogCallBack {
        AnonymousClass2() {
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onSuccess(String str) {
            Log.d(StubApp.getString2(Peripheral.GATT_AUTH_FAIL), str);
            RegisterCodeActivity.this.mBaseBean = (BaseBean) JSON.parseObject(str, BaseBean.class);
            Log.d(StubApp.getString2(6723), String.valueOf(RegisterCodeActivity.this.mBaseBean.getMeta().getCode()));
            if (RegisterCodeActivity.this.mBaseBean.getMeta().getCode() == 0) {
                BaseUtils.sendMessage(RegisterCodeActivity.this.mHandler, 400, "");
            } else {
                BaseUtils.sendMessage(RegisterCodeActivity.this.mHandler, 500, "");
            }
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onFailure(int i, String str) {
            BaseUtils.sendMessage(RegisterCodeActivity.this.mHandler, 500, "");
        }
    }

    /* renamed from: com.deye.activity.login.RegisterCodeActivity$4, reason: invalid class name */
    class AnonymousClass4 implements FogCallBack {
        AnonymousClass4() {
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onSuccess(String str) {
            RegisterCodeActivity.this.mCheckCodeBean = (CheckCodeBean) JSON.parseObject(str, CheckCodeBean.class);
            DeYeHttpRequestManager.getInstance().setToken(RegisterCodeActivity.this.mCheckCodeBean.getData().getToken());
            if (RegisterCodeActivity.this.mCheckCodeBean.getMeta().getCode() == 0) {
                DeYeHttpRequestManager.getInstance().setPassword(RegisterCodeActivity.this.mPwd, new FogCallBack() { // from class: com.deye.activity.login.RegisterCodeActivity.4.1
                    @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                    public void onSuccess(String str2) {
                        Log.d(StubApp.getString2(13731), str2);
                        BaseBean baseBean = (BaseBean) JSON.parseObject(str2, BaseBean.class);
                        if (baseBean != null && baseBean.getMeta().getCode() == 0) {
                            BaseUtils.sendMessage(RegisterCodeActivity.this.mHandler, 200, "");
                            return;
                        }
                        if (baseBean == null || baseBean.getMeta() == null) {
                            return;
                        }
                        RegisterCodeActivity.this.modifyFailedReason = baseBean.getMeta().getMessage();
                        if (RegisterCodeActivity.this.modifyFailedReason == null) {
                            RegisterCodeActivity.this.modifyFailedReason = StubApp.getString2(13732);
                        }
                        BaseUtils.sendMessage(RegisterCodeActivity.this.mHandler, 300, "");
                    }

                    @Override // io.fogcloud.sdk.fog.callback.FogCallBack
                    public void onFailure(int i, String str2) {
                        BaseUtils.sendMessage(RegisterCodeActivity.this.mHandler, 300, "");
                    }
                });
            } else if (((BaseBean) JSON.parseObject(str, BaseBean.class)).getMeta().getCode() == 10301 || ((BaseBean) JSON.parseObject(str, BaseBean.class)).getMeta().getCode() == 10000) {
                BaseUtils.sendMessage(RegisterCodeActivity.this.mHandler, 100, "");
            } else {
                BaseUtils.sendMessage(RegisterCodeActivity.this.mHandler, 300, "");
            }
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onFailure(int i, String str) {
            BaseUtils.sendMessage(RegisterCodeActivity.this.mHandler, 100, "");
        }
    }

    /* renamed from: com.deye.activity.login.RegisterCodeActivity$5, reason: invalid class name */
    class AnonymousClass5 implements FogCallBack {
        AnonymousClass5() {
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onSuccess(String str) {
            Log.d(StubApp.getString2(Peripheral.GATT_AUTH_FAIL), str);
            RegisterCodeActivity.this.mBaseBean = (BaseBean) JSON.parseObject(str, BaseBean.class);
            Log.d(StubApp.getString2(6723), String.valueOf(RegisterCodeActivity.this.mBaseBean.getMeta().getCode()));
            if (RegisterCodeActivity.this.mBaseBean.getMeta().getCode() == 0) {
                BaseUtils.sendMessage(RegisterCodeActivity.this.mHandler, 400, "");
            } else {
                BaseUtils.sendMessage(RegisterCodeActivity.this.mHandler, 500, "");
            }
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onFailure(int i, String str) {
            BaseUtils.sendMessage(RegisterCodeActivity.this.mHandler, 500, "");
        }
    }
}
