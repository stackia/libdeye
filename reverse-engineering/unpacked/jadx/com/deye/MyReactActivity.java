package com.deye;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import com.deye.utils.ChannelUtil;
import com.deye.utils.LanUtils;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.defaults.DefaultReactActivityDelegate;
import com.stub.StubApp;
import kotlin.Metadata;

/* compiled from: MyReactActivity.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\bH\u0014J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\b\u0010\r\u001a\u00020\nH\u0014J\b\u0010\u000e\u001a\u00020\nH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/deye/MyReactActivity;", "Lcom/facebook/react/ReactActivity;", "()V", "isBackGestureDisabled", "", "createReactActivityDelegate", "Lcom/facebook/react/ReactActivityDelegate;", "getMainComponentName", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class MyReactActivity extends ReactActivity {
    private boolean isBackGestureDisabled;

    static {
        StubApp.interface11(13817);
    }

    @Override // com.facebook.react.ReactActivity
    protected native ReactActivityDelegate createReactActivityDelegate();

    @Override // com.facebook.react.ReactActivity
    protected native String getMainComponentName();

    @Override // com.facebook.react.ReactActivity
    protected native void onCreate(Bundle savedInstanceState);

    @Override // com.facebook.react.ReactActivity
    protected native void onPause();

    @Override // com.facebook.react.ReactActivity
    protected native void onResume();

    /* compiled from: MyReactActivity.kt */
    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/deye/MyReactActivity$onCreate$1", "Landroidx/activity/OnBackPressedCallback;", "handleOnBackPressed", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.MyReactActivity$onCreate$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01421 extends OnBackPressedCallback {
        public void handleOnBackPressed() {
        }

        C01421() {
            super(true);
        }
    }

    /* compiled from: MyReactActivity.kt */
    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0014¨\u0006\u0004"}, d2 = {"com/deye/MyReactActivity$createReactActivityDelegate$1", "Lcom/facebook/react/defaults/DefaultReactActivityDelegate;", "getLaunchOptions", "Landroid/os/Bundle;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.MyReactActivity$createReactActivityDelegate$1, reason: invalid class name */
    public static final class AnonymousClass1 extends DefaultReactActivityDelegate {
        AnonymousClass1(String str, boolean z) {
            super(MyReactActivity.this, str, z);
        }

        @Override // com.facebook.react.ReactActivityDelegate
        protected Bundle getLaunchOptions() {
            String string2;
            Intent intent = MyReactActivity.this.getIntent();
            Bundle bundle = new Bundle();
            if (LanUtils.getLanguage((Context) MyReactActivity.this) == 2) {
                string2 = StubApp.getString2(9978);
            } else {
                string2 = StubApp.getString2(13141);
            }
            bundle.putString(StubApp.getString2(13142), string2);
            bundle.putBoolean(StubApp.getString2(13143), ChannelUtil.isOversea());
            if (intent != null) {
                String string22 = StubApp.getString2(13144);
                if (intent.hasExtra(string22)) {
                    bundle.putString(string22, intent.getStringExtra(string22));
                }
            }
            String string23 = StubApp.getString2(13145);
            bundle.putBundle(string23, intent.getBundleExtra(string23));
            return bundle;
        }
    }
}
