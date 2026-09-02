package com.deye.activity.advertise;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import com.deye.activity.advertise.AdvertiseActivity;
import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.databinding.ActivityAdvertiseBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.AdvertiseInfoBean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdvertiseActivity.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0004H\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0002J\b\u0010\u0012\u001a\u00020\u000eH\u0002J\u0012\u0010\u0013\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u000eH\u0014J\b\u0010\u0017\u001a\u00020\u000eH\u0014J\b\u0010\u0018\u001a\u00020\u000eH\u0002J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/deye/activity/advertise/AdvertiseActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "isCLick", "", "mAdvertiseDataBinding", "Lcom/mxchipapp/databinding/ActivityAdvertiseBinding;", "mAdvertiseDuration", "", "mAdvertiseInfo", "Lio/fogcloud/sdk/fog/bean/AdvertiseInfoBean;", "mCountDownTimer", "Landroid/os/CountDownTimer;", "getTheAdvertiseInfoFromPreferences", "", "gotoTabMainPage", "isOffLine", "initListener", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "toFillUserInfoActivity", "updateDuration", "duration", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class AdvertiseActivity extends BaseActivity {
    public static final int DEFAULT_AD_SHOW_INTERVAL = 3;
    public static final boolean IS_OPEN_FEATURE_P2 = false;
    public static final String TAG = StubApp.getString2(13242);
    private boolean isCLick;
    private ActivityAdvertiseBinding mAdvertiseDataBinding;
    private int mAdvertiseDuration = 3;
    private AdvertiseInfoBean mAdvertiseInfo;
    private CountDownTimer mCountDownTimer;

    static {
        StubApp.interface11(13881);
        INSTANCE = new Companion(null);
    }

    private final native void getTheAdvertiseInfoFromPreferences();

    private final native void gotoTabMainPage(boolean isOffLine);

    private final native void initListener();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initListener$lambda$1(AdvertiseActivity advertiseActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initListener$lambda$2(AdvertiseActivity advertiseActivity, View view);

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void toFillUserInfoActivity();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void updateDuration(int duration);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onResume();

    /* compiled from: AdvertiseActivity.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/deye/activity/advertise/AdvertiseActivity$onCreate$1", "Landroid/os/CountDownTimer;", "onFinish", "", "onTick", "millisUntilFinished", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.advertise.AdvertiseActivity$onCreate$1, reason: invalid class name */
    public static final class AnonymousClass1 extends CountDownTimer {
        AnonymousClass1(long j) {
            super(j, 1000L);
        }

        @Override // android.os.CountDownTimer
        public void onTick(long millisUntilFinished) {
            AdvertiseActivity advertiseActivity = AdvertiseActivity.this;
            advertiseActivity.mAdvertiseDuration--;
            final AdvertiseActivity advertiseActivity2 = AdvertiseActivity.this;
            advertiseActivity2.runOnUiThread(new Runnable() { // from class: com.deye.activity.advertise.AdvertiseActivity$onCreate$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AdvertiseActivity.AnonymousClass1.onTick$lambda$0(advertiseActivity2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onTick$lambda$0(AdvertiseActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (this$0.mAdvertiseDuration > 0) {
                this$0.updateDuration(this$0.mAdvertiseDuration);
            }
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            if (AdvertiseActivity.this.isCLick) {
                return;
            }
            AdvertiseActivity.this.toTabMainPage(false);
        }
    }
}
