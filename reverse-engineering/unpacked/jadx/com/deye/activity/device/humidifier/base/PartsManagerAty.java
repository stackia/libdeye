package com.deye.activity.device.humidifier.base;

import android.os.Bundle;
import android.view.View;
import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.databinding.ActivityPartsManagerBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.PartBean;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* compiled from: PartsManagerAty.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0012\u0010\u000e\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0011\u001a\u00020\fH\u0014J\u0018\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0018\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\tH\u0002J\b\u0010\u0019\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/deye/activity/device/humidifier/base/PartsManagerAty;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "binding", "Lcom/mxchipapp/databinding/ActivityPartsManagerBinding;", "deviceId", "", "mList", "", "Lio/fogcloud/sdk/fog/bean/PartBean;", "productId", "initViewByData", "", "list", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onSwitchClick", "partBean", "isClieked", "", "openPartsGuide", "partId", "", "reqDetail", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class PartsManagerAty extends BaseActivity {
    private ActivityPartsManagerBinding binding;
    private String deviceId = "";
    private String productId = "";
    private List<? extends PartBean> mList = new ArrayList();

    static {
        StubApp.interface11(14176);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final native void initViewByData(List<? extends PartBean> list);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initViewByData$lambda$1(PartsManagerAty partsManagerAty, PartBean partBean, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initViewByData$lambda$2(PartsManagerAty partsManagerAty, PartBean partBean, View view, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onCreate$lambda$0(PartsManagerAty partsManagerAty, View view);

    private final native void onSwitchClick(PartBean partBean, boolean isClieked);

    private final native void openPartsGuide(int partId, PartBean partBean);

    private final native void reqDetail();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onResume();
}
