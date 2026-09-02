package com.deye.activity.about;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.deye.activity.about.AboutDeyeActivity;
import com.deye.activity.app_config.ISendLogCallBack;
import com.deye.activity.device.base.BaseActivity;
import com.deye.helper.AppUpgradeHelper;
import com.deye.helper.DialogHelper;
import com.deye.utils.BaseUtils;
import com.deye.utils.JumpWeChatUtils;
import com.mxchipapp.R;
import com.mxchipapp.databinding.AboutDeyeAtyBinding;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AboutDeyeActivity.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\b\u0010\u0017\u001a\u00020\u0014H\u0002J\u0010\u0010\u0018\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ\u0006\u0010\u001b\u001a\u00020\u0014J\u0012\u0010\u001c\u001a\u00020\u00142\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0006\u0010\u001f\u001a\u00020\u0014J\u0006\u0010 \u001a\u00020\u0014J\u0006\u0010!\u001a\u00020\u0014J\u0006\u0010\"\u001a\u00020\u0014J\u0006\u0010#\u001a\u00020\u0014J\u0006\u0010$\u001a\u00020\u0014J\u0006\u0010%\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082D¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/deye/activity/about/AboutDeyeActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "COUNTS", "", "DURATION", "", "getDURATION", "()J", "mAboutDeyeAtyBinding", "Lcom/mxchipapp/databinding/AboutDeyeAtyBinding;", "mHits", "", "getMHits", "()[J", "setMHits", "([J)V", "officialEmail", "", "checkAppVersion", "", "continuousClick", "copyEmailToClipboard", "initView", "onBack", "view", "Landroid/view/View;", "onCall", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onFeedback", "onOpenWeChat", "onPrivacyManager", "onPrivacyPolicy", "onRateUs", "onServiceAgreement", "uploadLog", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class AboutDeyeActivity extends BaseActivity {
    private AboutDeyeAtyBinding mAboutDeyeAtyBinding;
    private final int COUNTS = 5;
    private final long DURATION = 2000;
    private long[] mHits = new long[5];
    private final String officialEmail = StubApp.getString2(13239);

    static {
        StubApp.interface11(13872);
    }

    private final native void continuousClick();

    private final native void copyEmailToClipboard();

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$0(AboutDeyeActivity aboutDeyeActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$1(AboutDeyeActivity aboutDeyeActivity, View view);

    public final native void checkAppVersion();

    public final native long getDURATION();

    public final native long[] getMHits();

    public final native void onBack(View view);

    public final native void onCall();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    public final native void onFeedback();

    public final native void onOpenWeChat();

    public final native void onPrivacyManager();

    public final native void onPrivacyPolicy();

    public final native void onRateUs();

    public final native void onServiceAgreement();

    public final native void setMHits(long[] jArr);

    public final native void uploadLog();

    /* compiled from: AboutDeyeActivity.kt */
    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J=\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0016¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"com/deye/activity/about/AboutDeyeActivity$checkAppVersion$1", "Lcom/deye/helper/AppUpgradeHelper$ICheckAppUpgradeListener;", "onFailure", "", "onSuccess", "version", "", "isNeedUpgrade", "", "tipArr", "", "showOnLaunch", "forceUpdate", "(Ljava/lang/String;Z[Ljava/lang/String;ZZ)V", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.about.AboutDeyeActivity$checkAppVersion$1, reason: invalid class name */
    public static final class AnonymousClass1 implements AppUpgradeHelper.ICheckAppUpgradeListener {
        AnonymousClass1() {
        }

        @Override // com.deye.helper.AppUpgradeHelper.ICheckAppUpgradeListener
        public void onSuccess(String version, boolean isNeedUpgrade, String[] tipArr, boolean showOnLaunch, boolean forceUpdate) {
            Intrinsics.checkNotNullParameter(version, "version");
            AboutDeyeActivity.this.hideLoading();
            if (isNeedUpgrade) {
                DialogHelper.updateVersionDialog(AboutDeyeActivity.this, tipArr, Boolean.valueOf(forceUpdate), null);
            } else {
                BaseUtils.showShortToast(R.string.current_version_is_latest);
            }
        }

        @Override // com.deye.helper.AppUpgradeHelper.ICheckAppUpgradeListener
        public void onFailure() {
            AboutDeyeActivity.this.hideLoading();
        }
    }

    /* compiled from: AboutDeyeActivity.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016¨\u0006\u0007"}, d2 = {"com/deye/activity/about/AboutDeyeActivity$uploadLog$1", "Lcom/deye/activity/app_config/ISendLogCallBack;", "onFinishSend", "", "isSuc", "", "onStartSend", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.about.AboutDeyeActivity$uploadLog$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01561 implements ISendLogCallBack {
        C01561() {
        }

        @Override // com.deye.activity.app_config.ISendLogCallBack
        public void onStartSend() {
            final AboutDeyeActivity aboutDeyeActivity = AboutDeyeActivity.this;
            aboutDeyeActivity.runOnUiThread(new Runnable() { // from class: com.deye.activity.about.AboutDeyeActivity$uploadLog$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AboutDeyeActivity.C01561.onStartSend$lambda$0(aboutDeyeActivity);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onStartSend$lambda$0(AboutDeyeActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.showWaiting(this$0.getString(R.string.uploading_log), true);
        }

        @Override // com.deye.activity.app_config.ISendLogCallBack
        public void onFinishSend(boolean isSuc) {
            AboutDeyeActivity.this.stopWaiting();
            if (isSuc) {
                BaseUtils.showShortToast(AboutDeyeActivity.this.getString(R.string.upload_successful));
            } else {
                BaseUtils.showShortToast(AboutDeyeActivity.this.getString(R.string.upload_failed));
            }
        }
    }

    /* compiled from: AboutDeyeActivity.kt */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/deye/activity/about/AboutDeyeActivity$onOpenWeChat$1", "Lcom/deye/helper/DialogHelper$OnDialogListener;", "onSure", "", "text", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.about.AboutDeyeActivity$onOpenWeChat$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01551 extends DialogHelper.OnDialogListener {
        C01551() {
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            Object systemService = AboutDeyeActivity.this.getSystemService(StubApp.getString2(10428));
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
            ((ClipboardManager) systemService).setText(AboutDeyeActivity.this.getResources().getString(R.string.deye_wechat_code_tip));
            JumpWeChatUtils.openWeChatApp((Context) AboutDeyeActivity.this);
        }
    }
}
