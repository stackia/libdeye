package com.deye.activity.config_net;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import com.deye.activity.device.base.BaseActivity;
import com.deye.fragment.IOnInviteListener;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import com.mxchipapp.databinding.DeviceShareQrCodeBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.VercodeResult;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DeviceShowShareQrAty.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0003J\b\u0010\f\u001a\u00020\u000bH\u0002J\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u000bH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/deye/activity/config_net/DeviceShowShareQrAty;", "Lcom/deye/activity/device/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "Lcom/deye/fragment/IOnInviteListener;", "()V", "mDeviceId", "", "mDeviceName", "mDeviceShareQrCodeBinding", "Lcom/mxchipapp/databinding/DeviceShareQrCodeBinding;", "createQRCode", "", "initView", "onClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onInviteSuccess", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DeviceShowShareQrAty extends BaseActivity implements View.OnClickListener, IOnInviteListener {
    private String mDeviceId;
    private String mDeviceName;
    private DeviceShareQrCodeBinding mDeviceShareQrCodeBinding;

    static {
        StubApp.interface11(13965);
    }

    private final native void createQRCode();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void createQRCode$lambda$2(DeviceShowShareQrAty deviceShowShareQrAty);

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onCreate$lambda$0(DeviceShowShareQrAty deviceShowShareQrAty, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onCreate$lambda$1(DeviceShowShareQrAty deviceShowShareQrAty, View view);

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // com.deye.fragment.IOnInviteListener
    public native void onInviteSuccess();

    /* compiled from: DeviceShowShareQrAty.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001J\u0016\u0010\u0005\u001a\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016¨\u0006\u0007"}, d2 = {"com/deye/activity/config_net/DeviceShowShareQrAty$createQRCode$1", "Lio/reactivex/rxjava3/functions/Function;", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/VercodeResult;", "", "apply", "t", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.config_net.DeviceShowShareQrAty$createQRCode$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Function<BaseResult<VercodeResult>, String> {
        AnonymousClass1() {
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public String apply(BaseResult<VercodeResult> t) throws JSONException {
            Intrinsics.checkNotNullParameter(t, "t");
            BaseResult.MetaBean meta = t.getMeta();
            if (meta != null && meta.getCode() == 0) {
                VercodeResult data = t.getData();
                String str = data != null ? data.vercode : null;
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(StubApp.getString2(4732), DeviceShowShareQrAty.this.mDeviceId);
                jSONObject.put(StubApp.getString2(13467), str);
                String string = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                return string;
            }
            return "";
        }
    }

    /* compiled from: DeviceShowShareQrAty.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.config_net.DeviceShowShareQrAty$createQRCode$3, reason: invalid class name */
    static final class AnonymousClass3<T> implements Consumer {
        AnonymousClass3() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(String it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            if (it2.length() == 0) {
                BaseUtils.showShortToast(R.string.qr_code_generation_failed);
                return;
            }
            Bitmap bitmapSyncEncodeQRCode = QRCodeEncoder.syncEncodeQRCode(it2, (int) DeviceShowShareQrAty.this.getResources().getDimension(2131166176));
            if (bitmapSyncEncodeQRCode == null) {
                BaseUtils.showShortToast(R.string.qr_code_generation_failed);
            }
            DeviceShareQrCodeBinding deviceShareQrCodeBinding = DeviceShowShareQrAty.this.mDeviceShareQrCodeBinding;
            if (deviceShareQrCodeBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDeviceShareQrCodeBinding");
                deviceShareQrCodeBinding = null;
            }
            deviceShareQrCodeBinding.ivShareDeviceQrcode.setImageBitmap(bitmapSyncEncodeQRCode);
        }
    }

    /* compiled from: DeviceShowShareQrAty.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.config_net.DeviceShowShareQrAty$createQRCode$4, reason: invalid class name */
    static final class AnonymousClass4<T> implements Consumer {
        public static final AnonymousClass4<T> INSTANCE = new AnonymousClass4<>();

        AnonymousClass4() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Throwable it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            BaseUtils.showShortToast(R.string.qr_code_generation_failed);
        }
    }
}
