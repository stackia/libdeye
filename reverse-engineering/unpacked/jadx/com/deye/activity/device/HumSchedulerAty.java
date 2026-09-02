package com.deye.activity.device;

import android.os.Bundle;
import android.view.View;
import com.contrarywind.view.WheelView;
import com.deye.activity.device.base.BaseActivity;
import com.deye.entity.control_panel.dehumidifier.func.HumidityBean;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import com.mxchipapp.databinding.HumSchedulerAtyBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.SchedulerHumBean;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HumSchedulerAty.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010,\u001a\u00020-H\u0003J\b\u0010.\u001a\u00020-H\u0016J\u000e\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001600H\u0014J\b\u00101\u001a\u00020-H\u0002J\b\u00102\u001a\u00020-H\u0002J\u0010\u00103\u001a\u00020-2\u0006\u00104\u001a\u000205H\u0016J\u0012\u00106\u001a\u00020-2\b\u00107\u001a\u0004\u0018\u000108H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0018\"\u0004\b\u001f\u0010\u001aR\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020\u00160#j\b\u0012\u0004\u0012\u00020\u0016`$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0018\"\u0004\b+\u0010\u001a¨\u00069"}, d2 = {"Lcom/deye/activity/device/HumSchedulerAty;", "Lcom/deye/activity/device/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "isCreate", "", "()Z", "setCreate", "(Z)V", "mActivityBinding", "Lcom/mxchipapp/databinding/HumSchedulerAtyBinding;", "getMActivityBinding", "()Lcom/mxchipapp/databinding/HumSchedulerAtyBinding;", "setMActivityBinding", "(Lcom/mxchipapp/databinding/HumSchedulerAtyBinding;)V", "mActivityHelper", "Lcom/deye/activity/device/HumSchedulerAtyHelper;", "getMActivityHelper", "()Lcom/deye/activity/device/HumSchedulerAtyHelper;", "setMActivityHelper", "(Lcom/deye/activity/device/HumSchedulerAtyHelper;)V", "mDeviceId", "", "getMDeviceId", "()Ljava/lang/String;", "setMDeviceId", "(Ljava/lang/String;)V", "mHumidityBean", "Lcom/deye/entity/control_panel/dehumidifier/func/HumidityBean;", "mProductId", "getMProductId", "setMProductId", "mSchedulerHumBean", "Lio/fogcloud/sdk/fog/bean/SchedulerHumBean;", "mSelectedWeekList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getMSelectedWeekList", "()Ljava/util/ArrayList;", "setMSelectedWeekList", "(Ljava/util/ArrayList;)V", "mSelectedWeeks", "getMSelectedWeeks", "setMSelectedWeeks", "createTiming", "", "finishActivityOrRefreshUIForRemovedDevice", "getCurrentDeviceId", "", "initData", "initView", "onClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class HumSchedulerAty extends BaseActivity implements View.OnClickListener {
    private boolean isCreate;
    public HumSchedulerAtyBinding mActivityBinding;
    private HumSchedulerAtyHelper mActivityHelper;
    private String mDeviceId;
    private HumidityBean mHumidityBean;
    private String mProductId;
    private SchedulerHumBean mSchedulerHumBean;
    private ArrayList<String> mSelectedWeekList = new ArrayList<>();
    private String mSelectedWeeks = "";

    static {
        StubApp.interface11(13994);
    }

    private final native void createTiming();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void createTiming$lambda$3(HumSchedulerAty humSchedulerAty);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void createTiming$lambda$4(HumSchedulerAty humSchedulerAty);

    private final native void initData();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initData$lambda$1(WheelView wheelView, WheelView wheelView2, HumSchedulerAty humSchedulerAty, int i);

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$2(HumSchedulerAty humSchedulerAty, View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void finishActivityOrRefreshUIForRemovedDevice();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native List<String> getCurrentDeviceId();

    public final native HumSchedulerAtyBinding getMActivityBinding();

    public final native HumSchedulerAtyHelper getMActivityHelper();

    public final native String getMDeviceId();

    public final native String getMProductId();

    public final native ArrayList<String> getMSelectedWeekList();

    public final native String getMSelectedWeeks();

    public final native boolean isCreate();

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    public final native void setCreate(boolean z);

    public final native void setMActivityBinding(HumSchedulerAtyBinding humSchedulerAtyBinding);

    public final native void setMActivityHelper(HumSchedulerAtyHelper humSchedulerAtyHelper);

    public final native void setMDeviceId(String str);

    public final native void setMProductId(String str);

    public final native void setMSelectedWeekList(ArrayList<String> arrayList);

    public final native void setMSelectedWeeks(String str);

    /* compiled from: HumSchedulerAty.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/SchedulerHumBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.device.HumSchedulerAty$createTiming$2, reason: invalid class name */
    static final class AnonymousClass2<T> implements Consumer {
        AnonymousClass2() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(BaseResult<SchedulerHumBean> it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            BaseResult.MetaBean meta = it2.getMeta();
            if (meta == null || meta.getCode() != 0) {
                return;
            }
            BaseUtils.showShortToast(R.string.submission_successful);
            HumSchedulerAty.this.finish();
        }
    }

    /* compiled from: HumSchedulerAty.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.device.HumSchedulerAty$createTiming$3, reason: invalid class name */
    static final class AnonymousClass3<T> implements Consumer {
        public static final AnonymousClass3<T> INSTANCE = new AnonymousClass3<>();

        AnonymousClass3() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Throwable it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            it2.printStackTrace();
        }
    }

    /* compiled from: HumSchedulerAty.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/SchedulerHumBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.device.HumSchedulerAty$createTiming$5, reason: invalid class name */
    static final class AnonymousClass5<T> implements Consumer {
        AnonymousClass5() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(BaseResult<SchedulerHumBean> it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            BaseUtils.showShortToast(R.string.modify_success);
            HumSchedulerAty.this.finish();
        }
    }

    /* compiled from: HumSchedulerAty.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.device.HumSchedulerAty$createTiming$6, reason: invalid class name */
    static final class AnonymousClass6<T> implements Consumer {
        public static final AnonymousClass6<T> INSTANCE = new AnonymousClass6<>();

        AnonymousClass6() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Throwable it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            it2.printStackTrace();
        }
    }
}
