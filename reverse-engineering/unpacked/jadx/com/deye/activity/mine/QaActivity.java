package com.deye.activity.mine;

import android.os.Bundle;
import android.view.View;
import com.deye.activity.device.base.BaseActivity;
import com.deye.helper.DialogHelper;
import com.deye.utils.BaseUtils;
import com.hjq.permissions.OnPermissionCallback;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ActivityQaBinding;
import com.stub.StubApp;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QaActivity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\u0012\u0010\f\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/deye/activity/mine/QaActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "mAtyBinding", "Lcom/mxchipapp/databinding/ActivityQaBinding;", "officialEmail", "", "qaType", "", "copyEmailToClipboard", "", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "requestPermissionCallPhone", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class QaActivity extends BaseActivity {
    public static final int QUESTION_TYPE_DEHUMIDIFIER = 0;
    public static final int QUESTION_TYPE_HUMIDIFIER = 1;
    private ActivityQaBinding mAtyBinding;
    private final String officialEmail = StubApp.getString2(13239);
    private int qaType;

    static {
        StubApp.interface11(14430);
        INSTANCE = new Companion(null);
    }

    private final native void copyEmailToClipboard();

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$0(QaActivity qaActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$2(boolean z, QaActivity qaActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$3(QaActivity qaActivity, View view);

    private final native void requestPermissionCallPhone();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    /* compiled from: QaActivity.kt */
    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\t\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\n\u001a\u00020\bH\u0016¨\u0006\u000b"}, d2 = {"com/deye/activity/mine/QaActivity$requestPermissionCallPhone$1", "Lcom/hjq/permissions/OnPermissionCallback;", "onDenied", "", "permissions", "", "", "doNotAskAgain", "", "onGranted", "allGranted", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.QaActivity$requestPermissionCallPhone$1, reason: invalid class name */
    public static final class AnonymousClass1 implements OnPermissionCallback {
        AnonymousClass1() {
        }

        public void onGranted(List<String> permissions, boolean allGranted) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            DialogHelper.showCallCustomerServiceDialog(QaActivity.this.mContext, StubApp.getString2(13747));
        }

        public void onDenied(List<String> permissions, boolean doNotAskAgain) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            BaseUtils.showShortToast(QaActivity.this.getString(R.string.call_permission_denied));
        }
    }
}
