package com.deye.activity.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import com.alibaba.fastjson.JSON;
import com.deye.activity.device.base.BaseActivity;
import com.deye.helper.DialogHelper;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import com.mxchipapp.databinding.GetVerificationCodeAtyBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.VerificationCodeResponseBean;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import it.innove.Peripheral;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private static final int SEND_CODE_FAIL = 200;
    private static final int SEND_CODE_FREQUENTLY = 99;
    private static final int SEND_CODE_SUCCESS = 100;
    private Handler handler = new Handler() { // from class: com.deye.activity.login.RegisterActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            RegisterActivity.this.mGetVerificationCodeAtyBinding.btnGetCode.stopAnimation();
            int i = message.what;
            if (i == 99) {
                BaseUtils.showShortToast(R.string.operation_too_frequent);
                return;
            }
            if (i != 100) {
                if (i != 200) {
                    return;
                }
                BaseUtils.showShortToast(R.string.get_verification_code_failure);
                return;
            }
            String str = RegisterActivity.this.mUIFlag;
            String string2 = StubApp.getString2(701);
            boolean zEquals = string2.equals(str);
            String string22 = StubApp.getString2(13722);
            String string23 = StubApp.getString2(2546);
            if (zEquals) {
                if (RegisterActivity.this.isNullBaseBean() && string23.equals(RegisterActivity.this.mBaseBean.getData().getType())) {
                    DialogHelper.twoBtnDialog(RegisterActivity.this, true, StubApp.getString2(13724), StubApp.getString2(13725), string22 + RegisterActivity.this.mGetVerificationCodeAtyBinding.edPhoneNumber.getText().toString() + StubApp.getString2(13723), new DialogHelper.OnDialogListener() { // from class: com.deye.activity.login.RegisterActivity.1.1
                        @Override // com.deye.helper.DialogHelper.OnDialogListener
                        public void onCancel() {
                            RegisterActivity.this.mUIFlag = StubApp.getString2(2546);
                            RegisterActivity.this.toSetupInfoPage();
                        }

                        @Override // com.deye.helper.DialogHelper.OnDialogListener
                        public void onSure(String str2) {
                            RegisterActivity.this.toLoginPage();
                        }
                    });
                    return;
                }
            } else if (string23.equals(RegisterActivity.this.mUIFlag) && RegisterActivity.this.isNullBaseBean() && string2.equals(RegisterActivity.this.mBaseBean.getData().getType())) {
                DialogHelper.twoBtnDialog(RegisterActivity.this, true, StubApp.getString2(13727), StubApp.getString2(13728), string22 + RegisterActivity.this.mGetVerificationCodeAtyBinding.edPhoneNumber.getText().toString() + StubApp.getString2(13726), new DialogHelper.OnDialogListener() { // from class: com.deye.activity.login.RegisterActivity.1.2
                    @Override // com.deye.helper.DialogHelper.OnDialogListener
                    public void onCancel() {
                    }

                    @Override // com.deye.helper.DialogHelper.OnDialogListener
                    public void onSure(String str2) {
                        RegisterActivity.this.mUIFlag = StubApp.getString2(701);
                        RegisterActivity.this.toSetupInfoPage();
                    }
                });
                return;
            }
            BaseUtils.showShortToast(R.string.get_verification_code_success);
            RegisterActivity.this.toSetupInfoPage();
        }
    };
    private VerificationCodeResponseBean mBaseBean;
    private GetVerificationCodeAtyBinding mGetVerificationCodeAtyBinding;
    private String mStrPhoneNumber;
    private String mUIFlag;
    private String mUserNamePhone;

    static {
        StubApp.interface11(14278);
    }

    private native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public native void isCanRegister();

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean isNullBaseBean();

    private native void setUserNameText();

    /* JADX INFO: Access modifiers changed from: private */
    public native void toLoginPage();

    /* JADX INFO: Access modifiers changed from: private */
    public native void toSetupInfoPage();

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    /* renamed from: com.deye.activity.login.RegisterActivity$2, reason: invalid class name */
    class AnonymousClass2 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        AnonymousClass2() {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            RegisterActivity.this.isCanRegister();
        }
    }

    /* renamed from: com.deye.activity.login.RegisterActivity$3, reason: invalid class name */
    class AnonymousClass3 implements CompoundButton.OnCheckedChangeListener {
        AnonymousClass3() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            RegisterActivity.this.isCanRegister();
        }
    }

    /* renamed from: com.deye.activity.login.RegisterActivity$4, reason: invalid class name */
    class AnonymousClass4 implements View.OnClickListener {
        AnonymousClass4() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (RegisterActivity.this.mGetVerificationCodeAtyBinding.cbConfirmProtocol.isChecked()) {
                RegisterActivity.this.mGetVerificationCodeAtyBinding.cbConfirmProtocol.setChecked(false);
            } else {
                RegisterActivity.this.mGetVerificationCodeAtyBinding.cbConfirmProtocol.setChecked(true);
            }
        }
    }

    /* renamed from: com.deye.activity.login.RegisterActivity$5, reason: invalid class name */
    class AnonymousClass5 implements FogCallBack {
        AnonymousClass5() {
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onSuccess(String str) {
            Log.d(StubApp.getString2(Peripheral.GATT_AUTH_FAIL), str);
            RegisterActivity.this.mBaseBean = (VerificationCodeResponseBean) JSON.parseObject(str, VerificationCodeResponseBean.class);
            Log.d(StubApp.getString2(6723), String.valueOf(RegisterActivity.this.mBaseBean.getMeta().getCode()));
            if (RegisterActivity.this.mBaseBean.getMeta().getCode() == 0) {
                BaseUtils.sendMessage(RegisterActivity.this.handler, 100, "");
            } else if (RegisterActivity.this.mBaseBean.getMeta().getCode() == 10000) {
                BaseUtils.sendMessage(RegisterActivity.this.handler, 99, "");
            } else {
                BaseUtils.sendMessage(RegisterActivity.this.handler, 200, "");
            }
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onFailure(int i, String str) {
            BaseUtils.sendMessage(RegisterActivity.this.handler, 200, "");
        }
    }
}
