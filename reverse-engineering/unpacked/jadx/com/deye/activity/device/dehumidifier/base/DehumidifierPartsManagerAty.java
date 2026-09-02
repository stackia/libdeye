package com.deye.activity.device.dehumidifier.base;

import android.os.Bundle;
import android.view.View;
import com.deye.activity.device.base.BaseActivity;
import com.deye.helper.DialogHelper;
import com.mxchipapp.databinding.ActivityPartsManagerBinding;
import com.mxchipapp.databinding.PartsItmeViewBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.PartBean;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DehumidifierPartsManagerAty.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\bH\u0002J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0013H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\u0016\u0010\u0018\u001a\u00020\u000b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0013H\u0002J\u0012\u0010\u0019\u001a\u00020\u000b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u000bH\u0014J\u0018\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\bH\u0002J\u0010\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010 \u001a\u00020\u000bH\u0002J\u0016\u0010!\u001a\u00020\u000b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/deye/activity/device/dehumidifier/base/DehumidifierPartsManagerAty;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "binding", "Lcom/mxchipapp/databinding/ActivityPartsManagerBinding;", "deviceId", "", "isOptionalDialogShowing", "", "productId", "bindPartView", "", "partView", "Lcom/mxchipapp/databinding/PartsItmeViewBinding;", "partBean", "Lio/fogcloud/sdk/fog/bean/PartBean;", "confirmOptionalComponent", "installed", "filterVisibleParts", "", "list", "getAirPartImageRes", "", "getPetPartImageRes", "initViewByData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onSwitchClick", "isClicked", "openPartsGuide", "reqDetail", "showOptionalConfirmDialogIfNeeded", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DehumidifierPartsManagerAty extends BaseActivity {
    private ActivityPartsManagerBinding binding;
    private boolean isOptionalDialogShowing;
    private String deviceId = "";
    private String productId = "";

    static {
        StubApp.interface11(14105);
    }

    private final native void bindPartView(PartsItmeViewBinding partView, PartBean partBean);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void bindPartView$lambda$2(DehumidifierPartsManagerAty dehumidifierPartsManagerAty, PartBean partBean, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void bindPartView$lambda$3(DehumidifierPartsManagerAty dehumidifierPartsManagerAty, PartBean partBean, View view, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void confirmOptionalComponent(PartBean partBean, boolean installed);

    /* JADX INFO: Access modifiers changed from: private */
    public final native List<PartBean> filterVisibleParts(List<? extends PartBean> list);

    private final native int getAirPartImageRes();

    private final native int getPetPartImageRes();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void initViewByData(List<? extends PartBean> list);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onCreate$lambda$0(DehumidifierPartsManagerAty dehumidifierPartsManagerAty, View view);

    private final native void onSwitchClick(PartBean partBean, boolean isClicked);

    private final native void openPartsGuide(PartBean partBean);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void reqDetail();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void showOptionalConfirmDialogIfNeeded(List<? extends PartBean> list);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onResume();

    /* compiled from: DehumidifierPartsManagerAty.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/deye/activity/device/dehumidifier/base/DehumidifierPartsManagerAty$showOptionalConfirmDialogIfNeeded$1", "Lcom/deye/helper/DialogHelper$OnDialogListener;", "onCancel", "", "onSure", "text", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.device.dehumidifier.base.DehumidifierPartsManagerAty$showOptionalConfirmDialogIfNeeded$1, reason: invalid class name */
    public static final class AnonymousClass1 extends DialogHelper.OnDialogListener {
        final /* synthetic */ PartBean $target;

        AnonymousClass1(PartBean partBean) {
            this.$target = partBean;
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onCancel() {
            DehumidifierPartsManagerAty.this.confirmOptionalComponent(this.$target, false);
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            DehumidifierPartsManagerAty.this.confirmOptionalComponent(this.$target, true);
        }
    }
}
