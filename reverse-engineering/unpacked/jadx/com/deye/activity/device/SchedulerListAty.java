package com.deye.activity.device;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.deye.activity.device.base.BaseActivity;
import com.deye.adapter.RyTimingListAdapter;
import com.deye.entity.control_panel.dehumidifier.func.HumidityBean;
import com.mxchipapp.databinding.SchedulerListAtyBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.SchedulerBean;
import io.fogcloud.sdk.fog.bean.SchedulerDateBean;
import io.fogcloud.sdk.fog.bean.SchedulerHumBean;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SchedulerListAty.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0002J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\u0019\u001a\u00020\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0006\u0010\u001c\u001a\u00020\u0010J\b\u0010\u001d\u001a\u00020\u0010H\u0014J\b\u0010\u001e\u001a\u00020\u0010H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/deye/activity/device/SchedulerListAty;", "Lcom/deye/activity/device/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "layoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "mDeviceId", "", "mHumidityBean", "Lcom/deye/entity/control_panel/dehumidifier/func/HumidityBean;", "mProductId", "mRyTimingListAdapter", "Lcom/deye/adapter/RyTimingListAdapter;", "mSchedulerListAtyBinding", "Lcom/mxchipapp/databinding/SchedulerListAtyBinding;", "finishActivityOrRefreshUIForRemovedDevice", "", "getCurrentDeviceId", "", "initData", "initEvent", "initView", "onClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onLoadFail", "onResume", "requestTiming", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class SchedulerListAty extends BaseActivity implements View.OnClickListener {
    private LinearLayoutManager layoutManager;
    private String mDeviceId;
    private HumidityBean mHumidityBean;
    private String mProductId;
    private RyTimingListAdapter mRyTimingListAdapter;
    private SchedulerListAtyBinding mSchedulerListAtyBinding;

    static {
        StubApp.interface11(14019);
    }

    private final native void initData();

    private final native void initEvent();

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$0(SchedulerListAty schedulerListAty, RefreshLayout refreshLayout);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$1(SchedulerListAty schedulerListAty, View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void finishActivityOrRefreshUIForRemovedDevice();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native List<String> getCurrentDeviceId();

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    public final native void onLoadFail();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onResume();

    public final native void requestTiming();

    /* compiled from: SchedulerListAty.kt */
    @Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00050\u0004H\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "Lio/fogcloud/sdk/fog/bean/SchedulerDateBean;", "t1", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "", "Lio/fogcloud/sdk/fog/bean/SchedulerBean;", "t2", "Lio/fogcloud/sdk/fog/bean/SchedulerHumBean;", "apply"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.device.SchedulerListAty$requestTiming$1, reason: invalid class name */
    static final class AnonymousClass1<T1, T2, R> implements BiFunction {
        public static final AnonymousClass1<T1, T2, R> INSTANCE = new AnonymousClass1<>();

        AnonymousClass1() {
        }

        @Override // io.reactivex.rxjava3.functions.BiFunction
        public final List<SchedulerDateBean> apply(BaseResult<List<SchedulerBean>> t1, BaseResult<List<SchedulerHumBean>> t2) {
            List<SchedulerBean> data;
            Intrinsics.checkNotNullParameter(t1, "t1");
            Intrinsics.checkNotNullParameter(t2, "t2");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SchedulerDateBean(3, null, null, 6, null));
            BaseResult.MetaBean meta = t1.getMeta();
            if (meta == null || meta.getCode() != 0 || ((data = t1.getData()) != null && data.isEmpty())) {
                arrayList.add(new SchedulerDateBean(5, null, null, 6, null));
            } else {
                List<SchedulerBean> data2 = t1.getData();
                if (data2 != null) {
                    Iterator<T> it2 = data2.iterator();
                    while (it2.hasNext()) {
                        arrayList.add(new SchedulerDateBean(4, (SchedulerBean) it2.next(), null, 4, null));
                    }
                }
            }
            return arrayList;
        }
    }

    /* compiled from: SchedulerListAty.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "Lio/fogcloud/sdk/fog/bean/SchedulerDateBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.device.SchedulerListAty$requestTiming$2, reason: invalid class name */
    static final class AnonymousClass2<T> implements Consumer {
        AnonymousClass2() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(List<SchedulerDateBean> it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            SchedulerListAty.this.stopWaiting();
            SchedulerListAtyBinding schedulerListAtyBinding = SchedulerListAty.this.mSchedulerListAtyBinding;
            SchedulerListAtyBinding schedulerListAtyBinding2 = null;
            String string2 = StubApp.getString2(13495);
            if (schedulerListAtyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                schedulerListAtyBinding = null;
            }
            schedulerListAtyBinding.srlRefreshLayout.finishRefresh();
            SchedulerListAtyBinding schedulerListAtyBinding3 = SchedulerListAty.this.mSchedulerListAtyBinding;
            if (schedulerListAtyBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                schedulerListAtyBinding3 = null;
            }
            schedulerListAtyBinding3.ryTimingList.setVisibility(0);
            SchedulerListAty schedulerListAty = SchedulerListAty.this;
            String str = SchedulerListAty.this.mProductId;
            Intrinsics.checkNotNull(str);
            String str2 = SchedulerListAty.this.mDeviceId;
            Intrinsics.checkNotNull(str2);
            SchedulerListAty schedulerListAty2 = SchedulerListAty.this;
            schedulerListAty.mRyTimingListAdapter = new RyTimingListAdapter(str, str2, schedulerListAty2, schedulerListAty2.mHumidityBean, it2);
            SchedulerListAtyBinding schedulerListAtyBinding4 = SchedulerListAty.this.mSchedulerListAtyBinding;
            if (schedulerListAtyBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                schedulerListAtyBinding2 = schedulerListAtyBinding4;
            }
            schedulerListAtyBinding2.ryTimingList.setAdapter(SchedulerListAty.this.mRyTimingListAdapter);
        }
    }

    /* compiled from: SchedulerListAty.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.device.SchedulerListAty$requestTiming$3, reason: invalid class name */
    static final class AnonymousClass3<T> implements Consumer {
        AnonymousClass3() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Throwable it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            it2.printStackTrace();
            SchedulerListAty.this.onLoadFail();
        }
    }
}
