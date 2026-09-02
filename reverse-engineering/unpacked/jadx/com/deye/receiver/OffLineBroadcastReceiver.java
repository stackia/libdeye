package com.deye.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.deye.activity.device.base.BaseActivity;
import com.deye.helper.DialogHelper;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OffLineBroadcastReceiver.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0004J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\"\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010¨\u0006\u0012"}, d2 = {"Lcom/deye/receiver/OffLineBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "goLoginPage", "", "activity", "Lcom/deye/activity/device/base/BaseActivity;", "isFirstPage", "", "onReceive", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "setOffLineTipDialog", "tipFlag", "", "content", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class OffLineBroadcastReceiver extends BroadcastReceiver {
    static {
        StubApp.interface11(15148);
    }

    protected final native void goLoginPage(BaseActivity activity, boolean isFirstPage);

    @Override // android.content.BroadcastReceiver
    public native void onReceive(Context context, Intent intent);

    public final native void setOffLineTipDialog(BaseActivity activity, String tipFlag, String content);

    /* compiled from: OffLineBroadcastReceiver.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/deye/receiver/OffLineBroadcastReceiver$setOffLineTipDialog$1", "Lcom/deye/helper/DialogHelper$OnDialogListener;", "onCancel", "", "onSure", "text", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.receiver.OffLineBroadcastReceiver$setOffLineTipDialog$1, reason: invalid class name */
    public static final class AnonymousClass1 extends DialogHelper.OnDialogListener {
        final /* synthetic */ BaseActivity $activity;

        AnonymousClass1(BaseActivity baseActivity) {
            this.$activity = baseActivity;
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onCancel() {
            OffLineBroadcastReceiver.this.goLoginPage(this.$activity, true);
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            OffLineBroadcastReceiver.this.goLoginPage(this.$activity, true);
        }
    }
}
