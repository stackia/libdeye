package com.deye.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.deye.listener.OnItemClickListener;
import com.deye.utils.BaseUtils;
import com.deye.utils.PanelHelper;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MyDeviceListAdapter.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001 B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002J\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\fJ\u001c\u0010\u0014\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0017\u001a\u00020\fH\u0017J\u001c\u0010\u0018\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0016\u0010\u001c\u001a\u00020\u00152\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0007J\u0010\u0010\u001e\u001a\u00020\u00152\b\u0010\u001f\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/deye/adapter/MyDeviceListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/deye/adapter/MyDeviceListAdapter$ViewHolder;", "mContext", "Landroid/content/Context;", "mDeviceListBeanList", "", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "(Landroid/content/Context;Ljava/util/List;)V", "mOnItemClickListener", "Lcom/deye/listener/OnItemClickListener;", "getItemCount", "", "isEqual", "", "one", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "two", "isOnline", "index", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setDateList", "beanList", "setOnItemClickListener", "onItemClickListener", "ViewHolder", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class MyDeviceListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context mContext;
    private final List<DeviceListBean> mDeviceListBeanList;
    private OnItemClickListener mOnItemClickListener;

    public MyDeviceListAdapter(Context mContext, List<DeviceListBean> mDeviceListBeanList) {
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

    private final boolean isEqual(DehumidifierBean one, DehumidifierBean two) {
        return one != null && two != null && one.checkIsPowerOn() == two.checkIsPowerOn() && one.getMode().equals(two.getMode());
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.fragment_equipment_item_my, parent, false);
        Intrinsics.checkNotNull(viewInflate);
        return new ViewHolder(this, viewInflate);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final int bindingAdapterPosition = holder.getBindingAdapterPosition();
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.MyDeviceListAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyDeviceListAdapter.onBindViewHolder$lambda$0(this.f$0, bindingAdapterPosition, view);
            }
        });
        if (this.mDeviceListBeanList.isEmpty()) {
            return;
        }
        DeviceListBean deviceListBean = this.mDeviceListBeanList.get(bindingAdapterPosition);
        String product_icon = deviceListBean.picture_v3;
        if (BaseUtils.isNullString(product_icon)) {
            product_icon = deviceListBean.getProduct_icon();
        }
        if (!BaseUtils.isNullString(product_icon)) {
            RequestOptions requestOptionsDiskCacheStrategy = new RequestOptions().centerInside().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            Intrinsics.checkNotNullExpressionValue(requestOptionsDiskCacheStrategy, "diskCacheStrategy(...)");
            Glide.with(this.mContext).load(product_icon).apply((BaseRequestOptions<?>) requestOptionsDiskCacheStrategy).into(holder.getIv_device_icon());
        }
        boolean zIsOnline = isOnline(bindingAdapterPosition);
        holder.getTv_device_name().setText(deviceListBean.getDevice_name());
        PanelHelper.INSTANCE.getPanelBean(this.mContext, deviceListBean.getProduct_id()).getMode();
        deviceListBean.getDehumidifierBean();
        if (zIsOnline) {
            holder.getIv_device_state().setBackgroundResource(R.drawable.icon_home_online);
        } else {
            holder.getIv_device_state().setBackgroundResource(R.drawable.icon_home_offline);
        }
        TextView tv_product = holder.getTv_product();
        String string = this.mContext.getString(R.string.model);
        String product_name = deviceListBean.getProduct_name();
        StringBuilder sbAppend = new StringBuilder().append(string);
        String string2 = StubApp.getString2(626);
        tv_product.setText(sbAppend.append(string2).append(product_name).append(string2).toString());
        holder.getTv_share_count().setText(this.mContext.getString(R.string.shared_people, Integer.valueOf(deviceListBean.user_count)));
        if (!deviceListBean.has_room && deviceListBean.getRole() == 1) {
            holder.getIv_dot().setVisibility(0);
            holder.getTv_room().setText(this.mContext.getString(R.string.room_unassigned));
            holder.getTv_room().setBackgroundResource(R.drawable.radius_12_dark04);
            holder.getTv_room().setTextColor(this.mContext.getResources().getColor(R.color.dark60));
            return;
        }
        holder.getIv_dot().setVisibility(8);
        if (deviceListBean.room_display_name == null || TextUtils.isEmpty(deviceListBean.room_display_name)) {
            holder.getTv_room().setText(this.mContext.getString(R.string.room_unassigned));
        } else {
            holder.getTv_room().setText(deviceListBean.room_display_name);
        }
        holder.getTv_room().setBackgroundResource(R.drawable.radius_12_blue10);
        holder.getTv_room().setTextColor(this.mContext.getResources().getColor(R.color.blue_text));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(MyDeviceListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnItemClickListener onItemClickListener = this$0.mOnItemClickListener;
        if (onItemClickListener != null) {
            Intrinsics.checkNotNull(onItemClickListener);
            onItemClickListener.onItemClick(i);
        }
    }

    public final boolean isOnline(int index) {
        if (this.mDeviceListBeanList.isEmpty()) {
            return false;
        }
        Boolean online = this.mDeviceListBeanList.get(index).getOnline();
        Intrinsics.checkNotNullExpressionValue(online, "getOnline(...)");
        return online.booleanValue();
    }

    public int getItemCount() {
        return this.mDeviceListBeanList.size();
    }

    /* compiled from: MyDeviceListAdapter.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001a\u0010\u001a\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\u001a\u0010\u001d\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0014\"\u0004\b\u001f\u0010\u0016¨\u0006 "}, d2 = {"Lcom/deye/adapter/MyDeviceListAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/deye/adapter/MyDeviceListAdapter;Landroid/view/View;)V", "iv_device_icon", "Landroid/widget/ImageView;", "getIv_device_icon", "()Landroid/widget/ImageView;", "setIv_device_icon", "(Landroid/widget/ImageView;)V", "iv_device_state", "getIv_device_state", "setIv_device_state", "iv_dot", "getIv_dot", "setIv_dot", "tv_device_name", "Landroid/widget/TextView;", "getTv_device_name", "()Landroid/widget/TextView;", "setTv_device_name", "(Landroid/widget/TextView;)V", "tv_product", "getTv_product", "setTv_product", "tv_room", "getTv_room", "setTv_room", "tv_share_count", "getTv_share_count", "setTv_share_count", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_device_icon;
        private ImageView iv_device_state;
        private ImageView iv_dot;
        final /* synthetic */ MyDeviceListAdapter this$0;
        private TextView tv_device_name;
        private TextView tv_product;
        private TextView tv_room;
        private TextView tv_share_count;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(MyDeviceListAdapter myDeviceListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = myDeviceListAdapter;
            View viewFindViewById = itemView.findViewById(R.id.iv_device_icon);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.iv_device_icon = (ImageView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.tv_device_name);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.tv_device_name = (TextView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(R.id.iv_device_state);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
            this.iv_device_state = (ImageView) viewFindViewById3;
            View viewFindViewById4 = itemView.findViewById(R.id.tv_product);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
            this.tv_product = (TextView) viewFindViewById4;
            View viewFindViewById5 = itemView.findViewById(R.id.tv_share_count);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
            this.tv_share_count = (TextView) viewFindViewById5;
            View viewFindViewById6 = itemView.findViewById(R.id.tv_room);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(...)");
            this.tv_room = (TextView) viewFindViewById6;
            View viewFindViewById7 = itemView.findViewById(R.id.iv_dot);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(...)");
            this.iv_dot = (ImageView) viewFindViewById7;
        }

        public final ImageView getIv_device_icon() {
            return this.iv_device_icon;
        }

        public final void setIv_device_icon(ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.iv_device_icon = imageView;
        }

        public final TextView getTv_device_name() {
            return this.tv_device_name;
        }

        public final void setTv_device_name(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_device_name = textView;
        }

        public final ImageView getIv_device_state() {
            return this.iv_device_state;
        }

        public final void setIv_device_state(ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.iv_device_state = imageView;
        }

        public final TextView getTv_product() {
            return this.tv_product;
        }

        public final void setTv_product(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_product = textView;
        }

        public final TextView getTv_share_count() {
            return this.tv_share_count;
        }

        public final void setTv_share_count(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_share_count = textView;
        }

        public final TextView getTv_room() {
            return this.tv_room;
        }

        public final void setTv_room(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_room = textView;
        }

        public final ImageView getIv_dot() {
            return this.iv_dot;
        }

        public final void setIv_dot(ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.iv_dot = imageView;
        }
    }
}
