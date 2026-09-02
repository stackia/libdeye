package com.deye.activity.device.dehumidifier.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.databinding.ActivityPartsGuideBinding;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DehumidifierPartsGuideAty.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000fH\u0002J\u0018\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0006H\u0002J\u0012\u0010\u0018\u001a\u00020\u00122\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0012H\u0002J\b\u0010\u001c\u001a\u00020\u0012H\u0002J\b\u0010\u001d\u001a\u00020\u0012H\u0002J\b\u0010\u001e\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/deye/activity/device/dehumidifier/base/DehumidifierPartsGuideAty;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "binding", "Lcom/mxchipapp/databinding/ActivityPartsGuideBinding;", "currentOperation", "", "deviceId", "displayName", "maintenanceValue", "", "partId", "productId", "replaceValue", "supportMaintenance", "", "supportReplace", "applyTabState", "", "view", "Landroid/widget/TextView;", "selected", "getGuideImageRes", "operation", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "resetRemainingTime", "setupView", "updateGuideContent", "updateOperationState", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DehumidifierPartsGuideAty extends BaseActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final String KEY_DEVICE_ID = StubApp.getString2(13055);
    private static final String KEY_DISPLAY_NAME = StubApp.getString2(13593);
    private static final String KEY_MAINTENANCE_VALUE = StubApp.getString2(13594);
    private static final String KEY_PART_ID = StubApp.getString2(13592);
    private static final String KEY_PRODUCT_ID = StubApp.getString2(13306);
    private static final String KEY_REPLACE_VALUE = StubApp.getString2(13595);
    private static final String KEY_SUPPORT_MAINTENANCE = StubApp.getString2(13596);
    private static final String KEY_SUPPORT_REPLACE = StubApp.getString2(13597);
    private static final String OPERATION_MAINTENANCE = StubApp.getString2(13598);
    private static final String OPERATION_REPLACE = StubApp.getString2(2399);
    private ActivityPartsGuideBinding binding;
    private int partId;
    private boolean supportMaintenance;
    private int replaceValue = 100;
    private int maintenanceValue = 100;
    private String deviceId = "";
    private String productId = "";
    private String displayName = "";
    private boolean supportReplace = true;
    private String currentOperation = StubApp.getString2(2399);

    static {
        StubApp.interface11(14094);
        INSTANCE = new Companion(null);
    }

    private final native void applyTabState(TextView view, boolean selected);

    private final native int getGuideImageRes(int partId, String operation);

    private final native void resetRemainingTime();

    private final native void setupView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void setupView$lambda$0(DehumidifierPartsGuideAty dehumidifierPartsGuideAty, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void setupView$lambda$1(DehumidifierPartsGuideAty dehumidifierPartsGuideAty, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void setupView$lambda$2(DehumidifierPartsGuideAty dehumidifierPartsGuideAty, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void setupView$lambda$3(DehumidifierPartsGuideAty dehumidifierPartsGuideAty, View view);

    private final native void updateGuideContent();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void updateOperationState();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    /* compiled from: DehumidifierPartsGuideAty.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J_\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0002\u0010\u001cR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/deye/activity/device/dehumidifier/base/DehumidifierPartsGuideAty$Companion;", "", "()V", "KEY_DEVICE_ID", "", "KEY_DISPLAY_NAME", "KEY_MAINTENANCE_VALUE", "KEY_PART_ID", "KEY_PRODUCT_ID", "KEY_REPLACE_VALUE", "KEY_SUPPORT_MAINTENANCE", "KEY_SUPPORT_REPLACE", "OPERATION_MAINTENANCE", "OPERATION_REPLACE", "open", "", "context", "Landroid/content/Context;", "partId", "", "deviceId", "productId", "displayName", "maintenanceValue", "replaceValue", "supportMaintenance", "", "supportReplace", "(Landroid/content/Context;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;ZZ)V", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void open(Context context, Integer partId, String deviceId, String productId, String displayName, Integer maintenanceValue, Integer replaceValue, boolean supportMaintenance, boolean supportReplace) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, (Class<?>) DehumidifierPartsGuideAty.class);
            intent.putExtra(StubApp.getString2(13592), partId != null ? partId.intValue() : 0);
            intent.putExtra(StubApp.getString2(13055), deviceId);
            intent.putExtra(StubApp.getString2(13306), productId);
            intent.putExtra(StubApp.getString2(13593), displayName);
            intent.putExtra(StubApp.getString2(13594), maintenanceValue != null ? maintenanceValue.intValue() : 100);
            intent.putExtra(StubApp.getString2(13595), replaceValue != null ? replaceValue.intValue() : 100);
            intent.putExtra(StubApp.getString2(13596), supportMaintenance);
            intent.putExtra(StubApp.getString2(13597), supportReplace);
            context.startActivity(intent);
        }
    }
}
