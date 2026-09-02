package com.deye.activity.config_net;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.alibaba.fastjson.JSON;
import com.deye.ProductManager;
import com.deye.activity.config_net.DeviceListActivity;
import com.deye.activity.config_net.bluetooth.ProductListFragment;
import com.deye.activity.device.base.BaseActivity;
import com.deye.adapter.DeviceListAdapter;
import com.deye.adapter.RyBleSearchDeviceListAdapter;
import com.deye.entity.ProductListBean;
import com.deye.listener.OnItemClickListener;
import com.deye.views.recycleview.SearchDeviceLayout;
import com.google.android.material.tabs.TabLayout;
import com.mxchipapp.databinding.DeviceListAtyBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DeviceListActivity.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\b\u0018\u0000 ,2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001,B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u00020\u00132\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0013H\u0014J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J+\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u001e2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010$\u001a\u00020%H\u0016¢\u0006\u0002\u0010&J\b\u0010'\u001a\u00020\u0013H\u0002J\b\u0010(\u001a\u00020\u0013H\u0002J\b\u0010)\u001a\u00020\u0013H\u0002J\b\u0010*\u001a\u00020\u0013H\u0002J\b\u0010+\u001a\u00020\u0013H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/deye/activity/config_net/DeviceListActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "Lcom/deye/listener/OnItemClickListener;", "()V", "mAdapter", "Lcom/deye/adapter/DeviceListAdapter;", "mDeviceListAtyBinding", "Lcom/mxchipapp/databinding/DeviceListAtyBinding;", "mProductListBean", "Lcom/deye/entity/ProductListBean;", "mRyBleSearchDeviceListAdapter", "Lcom/deye/adapter/RyBleSearchDeviceListAdapter;", "mSearchDeviceFilterList", "Ljava/util/ArrayList;", "Lcom/deye/entity/ProductListBean$Pdata;", "Lkotlin/collections/ArrayList;", "mSearchDeviceList", "initView", "", "onBackPressed", "onClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onItemClick", "position", "", "onRequestPermissionsResult", "requestCode", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "requestData", "requestPermission", "setAllComboDevice", "setAllSearchDevice", "updateDataAndUI", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DeviceListActivity extends BaseActivity implements View.OnClickListener, OnItemClickListener {
    public static final String TAG = StubApp.getString2(13456);
    private final DeviceListAdapter mAdapter;
    private DeviceListAtyBinding mDeviceListAtyBinding;
    private ProductListBean mProductListBean;
    private RyBleSearchDeviceListAdapter mRyBleSearchDeviceListAdapter;
    private final ArrayList<ProductListBean.Pdata> mSearchDeviceList = new ArrayList<>();
    private final ArrayList<ProductListBean.Pdata> mSearchDeviceFilterList = new ArrayList<>();

    static {
        StubApp.interface11(13942);
        INSTANCE = new Companion(null);
    }

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onCreate$lambda$0(DeviceListActivity deviceListActivity);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void requestData();

    private final native void requestPermission();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void setAllComboDevice();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void setAllSearchDevice();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void updateDataAndUI();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void updateDataAndUI$lambda$1(DeviceListActivity deviceListActivity, TabLayout.Tab tab, int i);

    public native void onBackPressed();

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();

    @Override // com.deye.listener.OnItemClickListener
    public native void onItemClick(int position);

    public native void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);

    /* compiled from: DeviceListActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0007\"\u00020\u0005H\u0016¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"com/deye/activity/config_net/DeviceListActivity$initView$1", "Lcom/deye/views/recycleview/SearchDeviceLayout$IContentChanged;", "onContentChanged", "", "text", "", "textPinYin", "", "(Ljava/lang/String;[Ljava/lang/String;)V", "onFocusChange", "hasFocus", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.config_net.DeviceListActivity$initView$1, reason: invalid class name */
    public static final class AnonymousClass1 implements SearchDeviceLayout.IContentChanged {
        AnonymousClass1() {
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0076  */
        @Override // com.deye.views.recycleview.SearchDeviceLayout.IContentChanged
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onContentChanged(String text, String... textPinYin) {
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(textPinYin, "textPinYin");
            DeviceListActivity.this.mSearchDeviceFilterList.clear();
            if (TextUtils.isEmpty(text)) {
                DeviceListActivity.this.setAllSearchDevice();
                return;
            }
            ArrayList<String> arrayList = new ArrayList();
            for (String str : textPinYin) {
                int size = DeviceListActivity.this.mSearchDeviceList.size();
                for (int i = 0; i < size; i++) {
                    Object obj = DeviceListActivity.this.mSearchDeviceList.get(i);
                    Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                    ProductListBean.Pdata pdata = (ProductListBean.Pdata) obj;
                    String pnameJianPinYinLower = pdata.getPnameJianPinYinLower();
                    String pnameQuanPinYinLower = pdata.getPnameQuanPinYinLower();
                    if (!DeviceListActivity.this.mSearchDeviceFilterList.contains(pdata)) {
                        Intrinsics.checkNotNull(pnameJianPinYinLower);
                        String str2 = str;
                        if (!StringsKt.contains$default(pnameJianPinYinLower, str2, false, 2, (Object) null)) {
                            Intrinsics.checkNotNull(pnameQuanPinYinLower);
                            if (StringsKt.contains$default(pnameQuanPinYinLower, str2, false, 2, (Object) null)) {
                                DeviceListActivity.this.mSearchDeviceFilterList.add(pdata);
                                String pname = pdata.getPname();
                                Intrinsics.checkNotNullExpressionValue(pname, "getPname(...)");
                                arrayList.add(pname);
                            }
                        }
                    }
                }
            }
            Collections.sort(arrayList);
            ArrayList arrayList2 = new ArrayList();
            for (String str3 : arrayList) {
                Iterator it2 = DeviceListActivity.this.mSearchDeviceFilterList.iterator();
                while (it2.hasNext()) {
                    ProductListBean.Pdata pdata2 = (ProductListBean.Pdata) it2.next();
                    if (TextUtils.equals(pdata2.getPname(), str3)) {
                        arrayList2.add(pdata2);
                    }
                }
            }
            DeviceListActivity.this.mSearchDeviceFilterList.clear();
            DeviceListActivity.this.mSearchDeviceFilterList.addAll(arrayList2);
            RyBleSearchDeviceListAdapter ryBleSearchDeviceListAdapter = DeviceListActivity.this.mRyBleSearchDeviceListAdapter;
            Intrinsics.checkNotNull(ryBleSearchDeviceListAdapter);
            ryBleSearchDeviceListAdapter.notifyDataSetChanged();
        }

        @Override // com.deye.views.recycleview.SearchDeviceLayout.IContentChanged
        public void onFocusChange(boolean hasFocus) {
            DeviceListAtyBinding deviceListAtyBinding = null;
            String string2 = StubApp.getString2(13455);
            if (hasFocus) {
                DeviceListActivity.this.setAllSearchDevice();
                DeviceListAtyBinding deviceListAtyBinding2 = DeviceListActivity.this.mDeviceListAtyBinding;
                if (deviceListAtyBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    deviceListAtyBinding2 = null;
                }
                deviceListAtyBinding2.srlRefreshLayout.setVisibility(8);
                DeviceListAtyBinding deviceListAtyBinding3 = DeviceListActivity.this.mDeviceListAtyBinding;
                if (deviceListAtyBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                } else {
                    deviceListAtyBinding = deviceListAtyBinding3;
                }
                deviceListAtyBinding.ryDeviceList.setVisibility(0);
                return;
            }
            ArrayList arrayList = DeviceListActivity.this.mSearchDeviceFilterList;
            Intrinsics.checkNotNull(arrayList);
            arrayList.clear();
            DeviceListAtyBinding deviceListAtyBinding4 = DeviceListActivity.this.mDeviceListAtyBinding;
            if (deviceListAtyBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                deviceListAtyBinding4 = null;
            }
            deviceListAtyBinding4.srlRefreshLayout.setVisibility(0);
            DeviceListAtyBinding deviceListAtyBinding5 = DeviceListActivity.this.mDeviceListAtyBinding;
            if (deviceListAtyBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                deviceListAtyBinding = deviceListAtyBinding5;
            }
            deviceListAtyBinding.ryDeviceList.setVisibility(8);
        }
    }

    /* compiled from: DeviceListActivity.kt */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/deye/activity/config_net/DeviceListActivity$initView$2", "Lcom/scwang/smartrefresh/layout/listener/OnRefreshListener;", "onRefresh", "", "refreshLayout", "Lcom/scwang/smartrefresh/layout/api/RefreshLayout;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.config_net.DeviceListActivity$initView$2, reason: invalid class name */
    public static final class AnonymousClass2 implements OnRefreshListener {
        AnonymousClass2() {
        }

        @Override // com.scwang.smartrefresh.layout.listener.OnRefreshListener
        public void onRefresh(RefreshLayout refreshLayout) {
            Intrinsics.checkNotNullParameter(refreshLayout, "refreshLayout");
            DeviceListActivity.this.requestData();
        }
    }

    /* compiled from: DeviceListActivity.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"com/deye/activity/config_net/DeviceListActivity$requestData$1", "Lio/fogcloud/sdk/fog/callback/FogCallBack;", "onFailure", "", "code", "", "message", "", "onSuccess", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.config_net.DeviceListActivity$requestData$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01571 implements FogCallBack {
        C01571() {
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onSuccess(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            DeviceListAtyBinding deviceListAtyBinding = DeviceListActivity.this.mDeviceListAtyBinding;
            if (deviceListAtyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDeviceListAtyBinding");
                deviceListAtyBinding = null;
            }
            deviceListAtyBinding.srlRefreshLayout.finishRefresh();
            DeviceListActivity.this.mProductListBean = (ProductListBean) JSON.parseObject(message, ProductListBean.class);
            if (DeviceListActivity.this.mProductListBean != null) {
                ProductListBean productListBean = DeviceListActivity.this.mProductListBean;
                Intrinsics.checkNotNull(productListBean);
                if (productListBean.getMeta() != null) {
                    ProductListBean productListBean2 = DeviceListActivity.this.mProductListBean;
                    Intrinsics.checkNotNull(productListBean2);
                    if (productListBean2.getMeta().getCode() == 0) {
                        ProductListBean productListBean3 = DeviceListActivity.this.mProductListBean;
                        Intrinsics.checkNotNull(productListBean3);
                        if (productListBean3.getData() != null) {
                            ProductListBean productListBean4 = DeviceListActivity.this.mProductListBean;
                            Intrinsics.checkNotNull(productListBean4);
                            if (productListBean4.getData().getResult() != null) {
                                ProductListBean productListBean5 = DeviceListActivity.this.mProductListBean;
                                Intrinsics.checkNotNull(productListBean5);
                                List<ProductListBean.Result> result = productListBean5.getData().getResult();
                                int size = result.size();
                                for (int i = 0; i < size; i++) {
                                    ProductListBean.Result result2 = result.get(i);
                                    ArrayList arrayList = new ArrayList();
                                    int size2 = result2.getPdata().size();
                                    for (int i2 = 0; i2 < size2; i2++) {
                                        ProductListBean.Pdata pdata = result2.getPdata().get(i2);
                                        String status = pdata.getStatus();
                                        ProductManager productManager = ProductManager.INSTANCE;
                                        Intrinsics.checkNotNull(pdata);
                                        productManager.addProductItem(pdata);
                                        if (TextUtils.equals(status, StubApp.getString2(701))) {
                                            arrayList.add(pdata);
                                        }
                                    }
                                    result2.getPdata().clear();
                                    result2.getPdata().addAll(arrayList);
                                }
                                ArrayList arrayList2 = new ArrayList();
                                int size3 = result.size();
                                for (int i3 = 0; i3 < size3; i3++) {
                                    if (result.get(i3).getPdata().size() > 0) {
                                        ProductListBean.Result result3 = result.get(i3);
                                        Intrinsics.checkNotNullExpressionValue(result3, "get(...)");
                                        arrayList2.add(result3);
                                    }
                                }
                                ProductListBean productListBean6 = DeviceListActivity.this.mProductListBean;
                                Intrinsics.checkNotNull(productListBean6);
                                productListBean6.getData().setResult(arrayList2);
                            }
                        }
                    }
                }
            }
            DeviceListActivity.this.setAllComboDevice();
            final DeviceListActivity deviceListActivity = DeviceListActivity.this;
            deviceListActivity.runOnUiThread(new Runnable() { // from class: com.deye.activity.config_net.DeviceListActivity$requestData$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceListActivity.C01571.onSuccess$lambda$0(deviceListActivity);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSuccess$lambda$0(DeviceListActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.updateDataAndUI();
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onFailure(int code, String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            DeviceListAtyBinding deviceListAtyBinding = DeviceListActivity.this.mDeviceListAtyBinding;
            if (deviceListAtyBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDeviceListAtyBinding");
                deviceListAtyBinding = null;
            }
            deviceListAtyBinding.srlRefreshLayout.finishRefresh();
        }
    }

    /* compiled from: DeviceListActivity.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\b\u0010\u0006\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"com/deye/activity/config_net/DeviceListActivity$updateDataAndUI$1", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "createFragment", "Landroidx/fragment/app/Fragment;", "position", "", "getItemCount", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.config_net.DeviceListActivity$updateDataAndUI$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01581 extends FragmentStateAdapter {
        C01581() {
            super(DeviceListActivity.this);
        }

        public Fragment createFragment(int position) {
            ProductListFragment.Companion companion = ProductListFragment.INSTANCE;
            ProductListBean productListBean = DeviceListActivity.this.mProductListBean;
            Intrinsics.checkNotNull(productListBean);
            return companion.newInstance(new ArrayList<>(productListBean.getData().getResult().get(position).getPdata()));
        }

        public int getItemCount() {
            ProductListBean productListBean = DeviceListActivity.this.mProductListBean;
            Intrinsics.checkNotNull(productListBean);
            return productListBean.getData().getResult().size();
        }
    }

    /* compiled from: DeviceListActivity.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\b"}, d2 = {"com/deye/activity/config_net/DeviceListActivity$updateDataAndUI$2", "Lcom/google/android/material/tabs/TabLayout$OnTabSelectedListener;", "onTabReselected", "", "p0", "Lcom/google/android/material/tabs/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.config_net.DeviceListActivity$updateDataAndUI$2, reason: invalid class name and case insensitive filesystem */
    public static final class C01592 implements TabLayout.OnTabSelectedListener {
        public void onTabReselected(TabLayout.Tab p0) {
        }

        C01592() {
        }

        public void onTabSelected(TabLayout.Tab p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            View childAt = p0.view.getChildAt(1);
            TextView textView = childAt instanceof TextView ? (TextView) childAt : null;
            if (textView != null) {
                textView.setTextSize(16.0f);
            }
            if (textView != null) {
                textView.setTypeface(null, 1);
            }
        }

        public void onTabUnselected(TabLayout.Tab p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            View childAt = p0.view.getChildAt(1);
            TextView textView = childAt instanceof TextView ? (TextView) childAt : null;
            if (textView != null) {
                textView.setTextSize(14.0f);
            }
            if (textView != null) {
                textView.setTypeface(null, 0);
            }
        }
    }
}
