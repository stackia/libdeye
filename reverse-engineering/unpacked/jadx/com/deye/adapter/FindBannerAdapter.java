package com.deye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.deye.views.RoundedCornersTransform;
import com.deye.webview.AgentWebActivity;
import com.mxchipapp.R;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import io.fogcloud.sdk.fog.bean.BannerItemBean;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FindBannerAdapter.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001aB\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nJ\u0006\u0010\r\u001a\u00020\nJ\u001c\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\nH\u0016J\u001c\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nH\u0016J\u0014\u0010\u0016\u001a\u00020\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/deye/adapter/FindBannerAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/deye/adapter/FindBannerAdapter$ViewHolder;", "mContext", "Landroid/content/Context;", "mList", "", "Lio/fogcloud/sdk/fog/bean/BannerItemBean;", "(Landroid/content/Context;Ljava/util/List;)V", "getItemCount", "", "getRealPosition", "position", "getRealSize", "onBindViewHolder", "", "holder", "index", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setDateList", "beanList", "", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "ViewHolder", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class FindBannerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context mContext;
    private final List<BannerItemBean> mList;

    public final void setDateList(List<DeviceListBean> beanList) {
        Intrinsics.checkNotNullParameter(beanList, "beanList");
    }

    public FindBannerAdapter(Context mContext, List<BannerItemBean> mList) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(mList, "mList");
        this.mContext = mContext;
        this.mList = mList;
    }

    public final int getRealSize() {
        return this.mList.size();
    }

    public final int getRealPosition(int position) {
        return position % this.mList.size();
    }

    public int getItemCount() {
        return this.mList.isEmpty() ? 0 : Integer.MAX_VALUE;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.find_banner_item, parent, false);
        Intrinsics.checkNotNull(viewInflate);
        return new ViewHolder(this, viewInflate);
    }

    public void onBindViewHolder(ViewHolder holder, int index) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final int realPosition = getRealPosition(holder.getBindingAdapterPosition());
        Glide.with(this.mContext).load(this.mList.get(realPosition).getCover_image()).apply((BaseRequestOptions<?>) new RequestOptions().transform(new RoundedCornersTransform(this.mContext, DensityUtil.dp2px(12.0f), true, true, true, true))).into(holder.getIvBanner());
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.FindBannerAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FindBannerAdapter.onBindViewHolder$lambda$0(this.f$0, realPosition, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(FindBannerAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AgentWebActivity.Companion companion = AgentWebActivity.INSTANCE;
        Context context = this$0.mContext;
        String content_url = this$0.mList.get(i).getContent_url();
        Intrinsics.checkNotNull(content_url);
        AgentWebActivity.Companion.open$default(companion, context, content_url, null, 4, null);
    }

    /* compiled from: FindBannerAdapter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/deye/adapter/FindBannerAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/deye/adapter/FindBannerAdapter;Landroid/view/View;)V", "ivBanner", "Landroid/widget/ImageView;", "getIvBanner", "()Landroid/widget/ImageView;", "setIvBanner", "(Landroid/widget/ImageView;)V", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivBanner;
        final /* synthetic */ FindBannerAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(FindBannerAdapter findBannerAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = findBannerAdapter;
            View viewFindViewById = itemView.findViewById(R.id.iv_banner);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.ivBanner = (ImageView) viewFindViewById;
        }

        public final ImageView getIvBanner() {
            return this.ivBanner;
        }

        public final void setIvBanner(ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.ivBanner = imageView;
        }
    }
}
