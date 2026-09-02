package com.deye.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.deye.activity.config_net.DeviceConfirmActivity;
import com.deye.entity.ProductListBean;
import com.mxchipapp.R;
import com.stub.StubApp;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProductListAdapter.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0017B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u001f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\b\u0010\f\u001a\u00020\rH\u0016J\u001c\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\rH\u0016J\u001c\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0016J\u0016\u0010\u0016\u001a\u00020\u000f2\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/deye/adapter/ProductListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/deye/adapter/ProductListAdapter$ViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "list", "", "Lcom/deye/entity/ProductListBean$Pdata;", "(Landroid/content/Context;Ljava/util/List;)V", "mContext", "mList", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setDataList", "ViewHolder", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class ProductListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context mContext;
    private List<? extends ProductListBean.Pdata> mList;

    public ProductListAdapter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mContext = context;
    }

    public ProductListAdapter(Context context, List<? extends ProductListBean.Pdata> list) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mContext = context;
        this.mList = list;
    }

    public final void setDataList(List<? extends ProductListBean.Pdata> list) {
        this.mList = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.rcv_prodcut_list_item, parent, false);
        Intrinsics.checkNotNull(viewInflate);
        return new ViewHolder(this, viewInflate);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final int bindingAdapterPosition = holder.getBindingAdapterPosition();
        TextView tv_devcie_name = holder.getTv_devcie_name();
        List<? extends ProductListBean.Pdata> list = this.mList;
        Intrinsics.checkNotNull(list);
        tv_devcie_name.setText(list.get(bindingAdapterPosition).getPname());
        RequestOptions requestOptionsDiskCacheStrategy = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Intrinsics.checkNotNullExpressionValue(requestOptionsDiskCacheStrategy, "diskCacheStrategy(...)");
        RequestOptions requestOptions = requestOptionsDiskCacheStrategy;
        List<? extends ProductListBean.Pdata> list2 = this.mList;
        Intrinsics.checkNotNull(list2);
        String picture = list2.get(position).picture_v3;
        String str = picture;
        if (str == null || str.length() == 0) {
            List<? extends ProductListBean.Pdata> list3 = this.mList;
            Intrinsics.checkNotNull(list3);
            picture = list3.get(position).getPicture();
        }
        Glide.with(this.mContext).load(picture).apply((BaseRequestOptions<?>) requestOptions).into(holder.getIv_device_icon());
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.ProductListAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProductListAdapter.onBindViewHolder$lambda$0(this.f$0, bindingAdapterPosition, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(ProductListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0.mContext, (Class<?>) DeviceConfirmActivity.class);
        List<? extends ProductListBean.Pdata> list = this$0.mList;
        Intrinsics.checkNotNull(list);
        intent.putExtra(StubApp.getString2(13306), list.get(i).getProductid());
        this$0.mContext.startActivity(intent);
    }

    public int getItemCount() {
        List<? extends ProductListBean.Pdata> list = this.mList;
        if (list == null) {
            return 0;
        }
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    /* compiled from: ProductListAdapter.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/deye/adapter/ProductListAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/deye/adapter/ProductListAdapter;Landroid/view/View;)V", "iv_device_icon", "Landroid/widget/ImageView;", "getIv_device_icon", "()Landroid/widget/ImageView;", "ll_root", "Landroid/widget/LinearLayout;", "getLl_root", "()Landroid/widget/LinearLayout;", "tv_devcie_name", "Landroid/widget/TextView;", "getTv_devcie_name", "()Landroid/widget/TextView;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_device_icon;
        private final LinearLayout ll_root;
        final /* synthetic */ ProductListAdapter this$0;
        private final TextView tv_devcie_name;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(ProductListAdapter productListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = productListAdapter;
            View viewFindViewById = itemView.findViewById(R.id.ll_root);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.ll_root = (LinearLayout) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.tv_devcie_name);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.tv_devcie_name = (TextView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(R.id.iv_device_icon);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
            this.iv_device_icon = (ImageView) viewFindViewById3;
        }

        public final LinearLayout getLl_root() {
            return this.ll_root;
        }

        public final TextView getTv_devcie_name() {
            return this.tv_devcie_name;
        }

        public final ImageView getIv_device_icon() {
            return this.iv_device_icon;
        }
    }
}
