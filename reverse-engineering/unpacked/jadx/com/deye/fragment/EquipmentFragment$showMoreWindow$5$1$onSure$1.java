package com.deye.fragment;

import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.deye.utils.BaseUtils;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.callback.ManageDeviceCallBack;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EquipmentFragment.kt */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"com/deye/fragment/EquipmentFragment$showMoreWindow$5$1$onSure$1", "Lio/fogcloud/sdk/fog/callback/ManageDeviceCallBack;", "onFailure", "", "code", "", "message", "", "onSuccess", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class EquipmentFragment$showMoreWindow$5$1$onSure$1 implements ManageDeviceCallBack {
    final /* synthetic */ DeviceListBean $deviceListBean;
    final /* synthetic */ String $text;
    final /* synthetic */ EquipmentFragment this$0;

    @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
    public void onFailure(int code, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    EquipmentFragment$showMoreWindow$5$1$onSure$1(EquipmentFragment equipmentFragment, String str, DeviceListBean deviceListBean) {
        this.this$0 = equipmentFragment;
        this.$text = str;
        this.$deviceListBean = deviceListBean;
    }

    @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
    public void onSuccess(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Integer integer = JSON.parseObject(JSON.parseObject(message).getString(StubApp.getString2(13082))).getInteger(StubApp.getString2(109));
        if (integer != null && integer.intValue() == 0) {
            BaseUtils.showShortToast(StubApp.getString2(14037));
            TextView textView = this.this$0.getDataBinding().tvName;
            final EquipmentFragment equipmentFragment = this.this$0;
            final String str = this.$text;
            final DeviceListBean deviceListBean = this.$deviceListBean;
            textView.post(new Runnable() { // from class: com.deye.fragment.EquipmentFragment$showMoreWindow$5$1$onSure$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    EquipmentFragment$showMoreWindow$5$1$onSure$1.onSuccess$lambda$0(equipmentFragment, str, deviceListBean);
                }
            });
            return;
        }
        BaseUtils.showShortToast(StubApp.getString2(14038));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onSuccess$lambda$0(EquipmentFragment this$0, String str, DeviceListBean deviceListBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getDataBinding().tvName.setText(str);
        if (deviceListBean == null) {
            return;
        }
        deviceListBean.setDevice_name(str);
    }
}
