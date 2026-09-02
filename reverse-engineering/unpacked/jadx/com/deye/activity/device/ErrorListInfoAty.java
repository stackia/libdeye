package com.deye.activity.device;

import android.os.Bundle;
import android.view.View;
import com.deye.ErrorCodeMap;
import com.deye.activity.device.base.BaseActivity;
import com.deye.adapter.ErrorListAdapter;
import com.deye.helper.DialogHelper;
import com.deye.utils.BaseUtils;
import com.hjq.permissions.OnPermissionCallback;
import com.mxchipapp.R;
import com.mxchipapp.databinding.DeviceErrorInfoAtyBinding;
import com.stub.StubApp;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ErrorListInfoAty extends BaseActivity {
    private DeviceErrorInfoAtyBinding mDeviceErrorInfoAtyBinding;
    private ErrorCodeMap<String, String> mErrorCodeMap;
    private ErrorListAdapter mErrorListAdapter;
    private final String officialEmail = StubApp.getString2(13239);

    static {
        StubApp.interface11(13984);
    }

    private native void copyEmailToClipboard();

    private native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public native void requestPermissionCallPhone(View view);

    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();

    /* renamed from: com.deye.activity.device.ErrorListInfoAty$1, reason: invalid class name */
    class AnonymousClass1 implements OnPermissionCallback {
        AnonymousClass1() {
        }

        public void onGranted(List<String> list, boolean z) {
            DialogHelper.showCallCustomerServiceDialog(ErrorListInfoAty.this.mContext, ErrorListInfoAty.this.mDeviceErrorInfoAtyBinding.tvDeyePhoneNumber.getText().toString().trim());
        }

        public void onDenied(List<String> list, boolean z) {
            BaseUtils.showShortToast(R.string.call_permission_denied);
        }
    }
}
