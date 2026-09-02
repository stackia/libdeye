package com.deye.activity.device.appointment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.deye.activity.device.base.BaseActivity;
import com.deye.adapter.SchedulerListAdapter;
import com.deye.entity.control_panel.dehumidifier.func.HumidityBean;
import com.deye.views.AppointmentDayView;
import com.mxchipapp.databinding.ActivityAppointmentListBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.SchedulerGroupBean;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppointmentListAty.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001aH\u0002J\"\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u00172\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\u0012\u0010!\u001a\u00020\u001a2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u001aH\u0002J\u0006\u0010%\u001a\u00020\u001aJ\b\u0010&\u001a\u00020\u001aH\u0007J\b\u0010'\u001a\u00020\u001aH\u0002J\u0014\u0010(\u001a\u00020\u001a2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086.¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018¨\u0006*"}, d2 = {"Lcom/deye/activity/device/appointment/AppointmentListAty;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "binding", "Lcom/mxchipapp/databinding/ActivityAppointmentListBinding;", "dataList", "", "Lio/fogcloud/sdk/fog/bean/SchedulerGroupBean;", "dayArray", "", "", "getDayArray", "()[Ljava/lang/String;", "setDayArray", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "mAdapter", "Lcom/deye/adapter/SchedulerListAdapter;", "mDeviceId", "mHumidityBean", "Lcom/deye/entity/control_panel/dehumidifier/func/HumidityBean;", "mProductId", "selectDayId", "", "Ljava/lang/Integer;", "initData", "", "initView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDaySelect", "onLoadFail", "requestTiming", "resolveData", "setAdapter", "list", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class AppointmentListAty extends BaseActivity {
    private ActivityAppointmentListBinding binding;
    private List<SchedulerGroupBean> dataList = new ArrayList();
    public String[] dayArray;
    private SchedulerListAdapter mAdapter;
    private String mDeviceId;
    private HumidityBean mHumidityBean;
    private String mProductId;
    private Integer selectDayId;

    static {
        StubApp.interface11(14062);
    }

    private final native void initData();

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$0(AppointmentListAty appointmentListAty, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$1(AppointmentListAty appointmentListAty, RefreshLayout refreshLayout);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$2(AppointmentListAty appointmentListAty, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$3(AppointmentListAty appointmentListAty, AppointmentDayView appointmentDayView);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void onDaySelect();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void resolveData();

    public final native String[] getDayArray();

    protected native void onActivityResult(int requestCode, int resultCode, Intent data);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    public final native void onLoadFail();

    public final native void requestTiming();

    public final native void setAdapter(List<SchedulerGroupBean> list);

    public final native void setDayArray(String[] strArr);

    /* compiled from: AppointmentListAty.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "", "Lio/fogcloud/sdk/fog/bean/SchedulerGroupBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.device.appointment.AppointmentListAty$requestTiming$1, reason: invalid class name */
    static final class AnonymousClass1<T> implements Consumer {
        AnonymousClass1() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(BaseResult<List<SchedulerGroupBean>> it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            AppointmentListAty.this.stopWaiting();
            ActivityAppointmentListBinding activityAppointmentListBinding = AppointmentListAty.this.binding;
            if (activityAppointmentListBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAppointmentListBinding = null;
            }
            activityAppointmentListBinding.srlRefreshLayout.finishRefresh();
            AppointmentListAty.this.dataList.clear();
            if (it2.getData() != null) {
                List list = AppointmentListAty.this.dataList;
                List<SchedulerGroupBean> data = it2.getData();
                Intrinsics.checkNotNull(data);
                list.addAll(data);
            }
            AppointmentListAty.this.resolveData();
            AppointmentListAty.this.onDaySelect();
        }
    }

    /* compiled from: AppointmentListAty.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.device.appointment.AppointmentListAty$requestTiming$2, reason: invalid class name */
    static final class AnonymousClass2<T> implements Consumer {
        AnonymousClass2() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Throwable it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            it2.printStackTrace();
            AppointmentListAty.this.onLoadFail();
        }
    }
}
