package com.deye.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import com.deye.MxchipApplication;
import com.deye.activity.device.base.BaseActivity;
import com.deye.helper.DialogHelper;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import com.mxchipapp.databinding.CleanAccountAtyBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.SimpleResultBean;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CleanAccountActivity.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0003J\u0018\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0003J\b\u0010\u000e\u001a\u00020\u000bH\u0002J\u0010\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0006\u0010\u0012\u001a\u00020\u000bJ\u0012\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u000bH\u0002J\b\u0010\u0017\u001a\u00020\u000bH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/deye/activity/mine/CleanAccountActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "email", "", "isOversea", "", "mCleanAccountAtyBinding", "Lcom/mxchipapp/databinding/CleanAccountAtyBinding;", "phoneNum", "cleanAccount", "", "code", "cleanAccountByEmail", "initView", "onBack", "view", "Landroid/view/View;", "onCleanAccount", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "sendEmailVerificationCode", "toGetCode", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class CleanAccountActivity extends BaseActivity {
    private String email;
    private boolean isOversea;
    private CleanAccountAtyBinding mCleanAccountAtyBinding;
    private String phoneNum;

    static {
        StubApp.interface11(14367);
    }

    private final native void cleanAccount(String phoneNum, String code);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void cleanAccount$lambda$4(CleanAccountActivity cleanAccountActivity);

    private final native void cleanAccountByEmail(String email, String code);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void cleanAccountByEmail$lambda$5(CleanAccountActivity cleanAccountActivity);

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$0(CleanAccountActivity cleanAccountActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$1(CleanAccountActivity cleanAccountActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$2(CleanAccountActivity cleanAccountActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$3(CleanAccountActivity cleanAccountActivity, View view);

    private final native void sendEmailVerificationCode();

    private final native void toGetCode();

    public final native void onBack(View view);

    public final native void onCleanAccount();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    /* compiled from: CleanAccountActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/deye/activity/mine/CleanAccountActivity$initView$4", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.CleanAccountActivity$initView$4, reason: invalid class name */
    public static final class AnonymousClass4 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        AnonymousClass4() {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            CleanAccountAtyBinding cleanAccountAtyBinding = CleanAccountActivity.this.mCleanAccountAtyBinding;
            if (cleanAccountAtyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCleanAccountAtyBinding");
                cleanAccountAtyBinding = null;
            }
            cleanAccountAtyBinding.btnConfirm.setEnabled(String.valueOf(s).length() >= 6);
        }
    }

    /* compiled from: CleanAccountActivity.kt */
    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/deye/activity/mine/CleanAccountActivity$initView$5", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "onCheckedChanged", "", "buttonView", "Landroid/widget/CompoundButton;", "isChecked", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.CleanAccountActivity$initView$5, reason: invalid class name */
    public static final class AnonymousClass5 implements CompoundButton.OnCheckedChangeListener {
        AnonymousClass5() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            CleanAccountAtyBinding cleanAccountAtyBinding = CleanAccountActivity.this.mCleanAccountAtyBinding;
            if (cleanAccountAtyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCleanAccountAtyBinding");
                cleanAccountAtyBinding = null;
            }
            cleanAccountAtyBinding.btnNextStep.setEnabled(isChecked);
        }
    }

    /* compiled from: CleanAccountActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/SimpleResultBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.CleanAccountActivity$cleanAccount$2, reason: invalid class name */
    static final class AnonymousClass2<T> implements Consumer {
        AnonymousClass2() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(BaseResult<SimpleResultBean> it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            BaseResult.MetaBean meta = it2.getMeta();
            if (meta != null && meta.getCode() == 0) {
                CleanAccountActivity cleanAccountActivity = CleanAccountActivity.this;
                BaseUtils.showShortToast((Context) cleanAccountActivity, cleanAccountActivity.getString(R.string.account_cancellation_success));
                MxchipApplication.getInstance().loginOut();
                return;
            }
            BaseResult.MetaBean meta2 = it2.getMeta();
            if (meta2 != null && meta2.getCode() == 10354) {
                CleanAccountActivity cleanAccountActivity2 = CleanAccountActivity.this;
                DialogHelper.cleanAccountUnbindDeviceDialog(cleanAccountActivity2, cleanAccountActivity2.getResources().getString(R.string.clean_account_dialog_unbind_device_tip), new DialogHelper.OnDialogListener() { // from class: com.deye.activity.mine.CleanAccountActivity.cleanAccount.2.1
                    @Override // com.deye.helper.DialogHelper.OnDialogListener
                    public void onCancel() {
                    }

                    @Override // com.deye.helper.DialogHelper.OnDialogListener
                    public void onSure(String text) {
                        Intrinsics.checkNotNullParameter(text, "text");
                    }
                });
            } else {
                BaseResult.MetaBean meta3 = it2.getMeta();
                BaseUtils.showShortToast(String.valueOf(meta3 != null ? meta3.getMessage() : null));
            }
        }
    }

    /* compiled from: CleanAccountActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.CleanAccountActivity$cleanAccount$3, reason: invalid class name */
    static final class AnonymousClass3<T> implements Consumer {
        public static final AnonymousClass3<T> INSTANCE = new AnonymousClass3<>();

        AnonymousClass3() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Throwable it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            it2.printStackTrace();
        }
    }

    /* compiled from: CleanAccountActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/SimpleResultBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.CleanAccountActivity$cleanAccountByEmail$2, reason: invalid class name and case insensitive filesystem */
    static final class C01722<T> implements Consumer {
        C01722() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(BaseResult<SimpleResultBean> it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            BaseResult.MetaBean meta = it2.getMeta();
            if (meta != null && meta.getCode() == 0) {
                CleanAccountActivity cleanAccountActivity = CleanAccountActivity.this;
                BaseUtils.showShortToast((Context) cleanAccountActivity, cleanAccountActivity.getString(R.string.account_cancellation_success));
                MxchipApplication.getInstance().loginOut();
                return;
            }
            BaseResult.MetaBean meta2 = it2.getMeta();
            if (meta2 != null && meta2.getCode() == 10354) {
                CleanAccountActivity cleanAccountActivity2 = CleanAccountActivity.this;
                DialogHelper.cleanAccountUnbindDeviceDialog(cleanAccountActivity2, cleanAccountActivity2.getResources().getString(R.string.clean_account_dialog_unbind_device_tip), new DialogHelper.OnDialogListener() { // from class: com.deye.activity.mine.CleanAccountActivity.cleanAccountByEmail.2.1
                    @Override // com.deye.helper.DialogHelper.OnDialogListener
                    public void onCancel() {
                    }

                    @Override // com.deye.helper.DialogHelper.OnDialogListener
                    public void onSure(String text) {
                        Intrinsics.checkNotNullParameter(text, "text");
                    }
                });
            } else {
                BaseResult.MetaBean meta3 = it2.getMeta();
                BaseUtils.showShortToast(String.valueOf(meta3 != null ? meta3.getMessage() : null));
            }
        }
    }

    /* compiled from: CleanAccountActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.CleanAccountActivity$cleanAccountByEmail$3, reason: invalid class name and case insensitive filesystem */
    static final class C01733<T> implements Consumer {
        public static final C01733<T> INSTANCE = new C01733<>();

        C01733() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Throwable it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            it2.printStackTrace();
        }
    }
}
