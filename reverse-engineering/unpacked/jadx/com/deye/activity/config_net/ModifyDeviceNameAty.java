package com.deye.activity.config_net;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.alibaba.fastjson.JSON;
import com.deye.activity.device.base.BaseActivity;
import com.deye.utils.BaseUtils;
import com.deye.views.CleanEditText;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ModifyDeviceNameBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.callback.ManageDeviceCallBack;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ModifyDeviceNameAty extends BaseActivity implements View.OnClickListener {
    private String mDeviceAlias;
    private String mDeviceId;
    private ModifyDeviceNameBinding mModifyDeviceNameBinding;

    static {
        StubApp.interface11(13970);
    }

    private native void initView();

    private native boolean isDeviceName();

    private native void updateDeviceAlias();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void finishActivityOrRefreshUIForRemovedDevice();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native List<String> getCurrentDeviceId();

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    /* renamed from: com.deye.activity.config_net.ModifyDeviceNameAty$1, reason: invalid class name */
    class AnonymousClass1 implements CleanEditText.OnTextChangeListener {
        AnonymousClass1() {
        }

        @Override // com.deye.views.CleanEditText.OnTextChangeListener
        public void OnTextChange(String str) {
            if (!str.equals(ModifyDeviceNameAty.this.mDeviceAlias) && str.length() > 0) {
                ModifyDeviceNameAty.this.mModifyDeviceNameBinding.actionbar.tvActionbarSave.setVisibility(0);
            } else {
                ModifyDeviceNameAty.this.mModifyDeviceNameBinding.actionbar.tvActionbarSave.setVisibility(8);
            }
        }
    }

    /* renamed from: com.deye.activity.config_net.ModifyDeviceNameAty$2, reason: invalid class name */
    class AnonymousClass2 implements ManageDeviceCallBack {
        @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
        public void onFailure(int i, String str) {
        }

        AnonymousClass2() {
        }

        @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
        public void onSuccess(String str) {
            if (JSON.parseObject(JSON.parseObject(str).getString(StubApp.getString2(13082))).getInteger(StubApp.getString2(109)).intValue() == 0) {
                ModifyDeviceNameAty.this.runOnUiThread(new Runnable() { // from class: com.deye.activity.config_net.ModifyDeviceNameAty.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BaseUtils.showShortToast(R.string.modify_success);
                        ModifyDeviceNameAty.this.mDeviceAlias = ModifyDeviceNameAty.this.mModifyDeviceNameBinding.edDeviceName.getContentText();
                        Intent intent = new Intent();
                        intent.putExtra(StubApp.getString2(6888), ModifyDeviceNameAty.this.mDeviceAlias);
                        ModifyDeviceNameAty.this.setResult(1, intent);
                        ModifyDeviceNameAty.this.finish();
                    }
                });
            } else {
                ModifyDeviceNameAty.this.runOnUiThread(new Runnable() { // from class: com.deye.activity.config_net.ModifyDeviceNameAty.2.2
                    @Override // java.lang.Runnable
                    public void run() {
                        BaseUtils.showShortToast(R.string.modify_failure);
                    }
                });
            }
        }
    }
}
