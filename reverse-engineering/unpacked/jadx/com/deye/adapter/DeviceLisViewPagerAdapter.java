package com.deye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.deye.listener.OnItemClickListener;
import com.deye.utils.PagerUtils;
import com.deye.views.HomeControlItemView;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.bean.LoopFanBean;
import io.fogcloud.sdk.fog.log.LogDebug;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceLisViewPagerAdapter.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 -2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003-./B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eJ\u0006\u0010\u0013\u001a\u00020\u000eJ\u000e\u0010\u0014\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ$\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001a\u001a\u00020\u0016H\u0002J\u001c\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001bH\u0002J\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u000eH\u0016J\u0018\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u000eH\u0016J\u0014\u0010%\u001a\u00020\u001e2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0010\u0010'\u001a\u00020\u001e2\b\u0010(\u001a\u0004\u0018\u00010\nJ\u000e\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\u0007J\u0016\u0010+\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/deye/adapter/DeviceLisViewPagerAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "mContext", "Landroid/content/Context;", "mDeviceListBeanList", "", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "(Landroid/content/Context;Ljava/util/List;)V", "mOnItemClickListener", "Lcom/deye/listener/OnItemClickListener;", "getDeviceName", "", "index", "", "getItemCount", "getItemViewType", "position", "getRealPosition", "getRealSize", "getRoomName", "isEqual", "", "one", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "two", "isHumidity", "Lio/fogcloud/sdk/fog/bean/LoopFanBean;", "isOnline", "onBindViewHolder", "", "holder", "adapterPosition", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setDateList", "beanList", "setOnItemClickListener", "onItemClickListener", "updateDehumidifyBean", "bean", "updateOnlineState", "online", "Companion", "EmptyViewHolder", "ItemViewHolder", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DeviceLisViewPagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int VIEW_TYPE_ADD = 1;
    private static final int VIEW_TYPE_ITEM = 0;
    private final Context mContext;
    private final List<DeviceListBean> mDeviceListBeanList;
    private OnItemClickListener mOnItemClickListener;

    public DeviceLisViewPagerAdapter(Context mContext, List<DeviceListBean> mDeviceListBeanList) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(mDeviceListBeanList, "mDeviceListBeanList");
        this.mContext = mContext;
        this.mDeviceListBeanList = mDeviceListBeanList;
    }

    public final void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public final void setDateList(List<DeviceListBean> beanList) {
        Intrinsics.checkNotNullParameter(beanList, "beanList");
        this.mDeviceListBeanList.clear();
        this.mDeviceListBeanList.addAll(beanList);
        notifyDataSetChanged();
    }

    public final void updateDehumidifyBean(DeviceListBean bean) {
        Intrinsics.checkNotNullParameter(bean, "bean");
        int i = 0;
        for (DeviceListBean deviceListBean : this.mDeviceListBeanList) {
            int i2 = i + 1;
            if (deviceListBean.getDevice_id().equals(bean.getDevice_id())) {
                if (deviceListBean.isLoopFan()) {
                    if (!isEqual(deviceListBean.loopFanBean, bean.loopFanBean)) {
                        deviceListBean.loopFanBean = bean.loopFanBean;
                        notifyDataSetChanged();
                    }
                } else if (!isEqual(deviceListBean.getDehumidifierBean(), bean.getDehumidifierBean(), bean.isHumidifier())) {
                    LogDebug.INSTANCE.log(StubApp.getString2(13798) + i + StubApp.getString2(626) + bean.getDehumidifierBean());
                    deviceListBean.setDehumidifierBean(bean.getDehumidifierBean());
                    notifyDataSetChanged();
                } else {
                    LogDebug.INSTANCE.log(StubApp.getString2(13799) + i);
                }
            }
            i = i2;
        }
    }

    public final void updateOnlineState(DeviceListBean bean, boolean online) {
        Intrinsics.checkNotNullParameter(bean, "bean");
        for (DeviceListBean deviceListBean : this.mDeviceListBeanList) {
            if (deviceListBean.getDevice_id().equals(bean.getDevice_id())) {
                deviceListBean.setOnline(Boolean.valueOf(online));
                notifyDataSetChanged();
            }
        }
    }

    private final boolean isEqual(DehumidifierBean one, DehumidifierBean two, boolean isHumidity) {
        if (one == null || two == null || one.getMode() == null || two.getMode() == null) {
            return false;
        }
        return isHumidity ? one.checkIsPowerOn() == two.checkIsPowerOn() && one.getMode().equals(two.getMode()) : (one.getWatertank_state() == null || two.getWatertank_state() == null || one.getMode() == null || two.getMode() == null || one.checkIsPowerOn() != two.checkIsPowerOn() || !one.getMode().equals(two.getMode()) || !one.getWatertank_state().equals(two.getWatertank_state())) ? false : true;
    }

    private final boolean isEqual(LoopFanBean one, LoopFanBean two) {
        return one != null && two != null && one.checkIsPowerOn() == two.checkIsPowerOn() && one.getHumidification().equals(two.getHumidification()) && one.getDeodorization().equals(two.getDeodorization()) && one.getFormaldehyde_removal().equals(two.getFormaldehyde_removal());
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (viewType == VIEW_TYPE_ITEM) {
            HomeControlItemView homeControlItemView = new HomeControlItemView(this.mContext);
            homeControlItemView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            return new ItemViewHolder(this, homeControlItemView);
        }
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.fragment_equipment_empty, parent, false);
        Intrinsics.checkNotNull(viewInflate);
        return new EmptyViewHolder(this, viewInflate);
    }

    public int getItemViewType(int position) {
        return getRealPosition(position) == this.mDeviceListBeanList.size() ? VIEW_TYPE_ADD : VIEW_TYPE_ITEM;
    }

    public final int getRealSize() {
        return this.mDeviceListBeanList.size() + 1;
    }

    public final int getRealPosition(int position) {
        return position % (this.mDeviceListBeanList.size() + 1);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int adapterPosition) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final int realSize = adapterPosition % getRealSize();
        if (holder instanceof ItemViewHolder) {
            DeviceListBean deviceListBean = this.mDeviceListBeanList.get(realSize);
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.DeviceLisViewPagerAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceLisViewPagerAdapter.onBindViewHolder$lambda$0(this.f$0, realSize, view);
                }
            });
            View view = holder.itemView;
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type com.deye.views.HomeControlItemView");
            ((HomeControlItemView) view).setDataBean(deviceListBean, realSize);
            return;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.DeviceLisViewPagerAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DeviceLisViewPagerAdapter.onBindViewHolder$lambda$1(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(DeviceLisViewPagerAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnItemClickListener onItemClickListener = this$0.mOnItemClickListener;
        if (onItemClickListener != null) {
            Intrinsics.checkNotNull(onItemClickListener);
            onItemClickListener.onItemClick(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$1(DeviceLisViewPagerAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity fragmentActivity = this$0.mContext;
        Intrinsics.checkNotNull(fragmentActivity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        PagerUtils.goBleScanPage(fragmentActivity);
    }

    public final String getDeviceName(int index) {
        if (index == this.mDeviceListBeanList.size() || this.mDeviceListBeanList.isEmpty()) {
            return "";
        }
        String device_name = this.mDeviceListBeanList.get(index).getDevice_name();
        Intrinsics.checkNotNullExpressionValue(device_name, "getDevice_name(...)");
        return device_name;
    }

    public final String getRoomName(int index) {
        if (index == this.mDeviceListBeanList.size() || this.mDeviceListBeanList.isEmpty() || this.mDeviceListBeanList.get(index).room_display_name == null) {
            return "";
        }
        String room_display_name = this.mDeviceListBeanList.get(index).room_display_name;
        Intrinsics.checkNotNullExpressionValue(room_display_name, "room_display_name");
        return room_display_name;
    }

    public final boolean isOnline(int index) {
        if (this.mDeviceListBeanList.isEmpty() || index == this.mDeviceListBeanList.size()) {
            return false;
        }
        Boolean online = this.mDeviceListBeanList.get(index).getOnline();
        Intrinsics.checkNotNullExpressionValue(online, "getOnline(...)");
        return online.booleanValue();
    }

    public int getItemCount() {
        return this.mDeviceListBeanList.isEmpty() ? 1 : Integer.MAX_VALUE;
    }

    /* compiled from: DeviceLisViewPagerAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/deye/adapter/DeviceLisViewPagerAdapter$ItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/deye/adapter/DeviceLisViewPagerAdapter;Landroid/view/View;)V", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class ItemViewHolder extends RecyclerView.ViewHolder {
        final /* synthetic */ DeviceLisViewPagerAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ItemViewHolder(DeviceLisViewPagerAdapter deviceLisViewPagerAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = deviceLisViewPagerAdapter;
        }
    }

    /* compiled from: DeviceLisViewPagerAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/deye/adapter/DeviceLisViewPagerAdapter$EmptyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/deye/adapter/DeviceLisViewPagerAdapter;Landroid/view/View;)V", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class EmptyViewHolder extends RecyclerView.ViewHolder {
        final /* synthetic */ DeviceLisViewPagerAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EmptyViewHolder(DeviceLisViewPagerAdapter deviceLisViewPagerAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = deviceLisViewPagerAdapter;
        }
    }

    /* compiled from: DeviceLisViewPagerAdapter.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/deye/adapter/DeviceLisViewPagerAdapter$Companion;", "", "()V", "VIEW_TYPE_ADD", "", "getVIEW_TYPE_ADD", "()I", "VIEW_TYPE_ITEM", "getVIEW_TYPE_ITEM", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getVIEW_TYPE_ADD() {
            return DeviceLisViewPagerAdapter.VIEW_TYPE_ADD;
        }

        public final int getVIEW_TYPE_ITEM() {
            return DeviceLisViewPagerAdapter.VIEW_TYPE_ITEM;
        }
    }
}
