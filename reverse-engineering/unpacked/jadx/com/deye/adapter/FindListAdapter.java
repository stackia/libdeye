package com.deye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amap.location.support.bean.location.AmapLocationNetwork;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.deye.utils.PagerUtils;
import com.deye.utils.UmUtils;
import com.deye.views.RoundedCornersTransform;
import com.deye.webview.AgentWebActivity;
import com.mxchipapp.R;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.FindItemBean;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FindListAdapter.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001dB\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B%\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u000bJ\u0016\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0011J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006J\u001c\u0010\u0015\u001a\u00020\u000f2\n\u0010\u0016\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0013H\u0016J\u001c\u0010\u0018\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0013H\u0016J\u0016\u0010\u001c\u001a\u00020\u000f2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0011R\u000e\u0010\f\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/deye/adapter/FindListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/deye/adapter/FindListAdapter$ViewHolder;", "context", "Landroid/content/Context;", "pageType", "", "(Landroid/content/Context;Ljava/lang/String;)V", "dataList", "", "Lio/fogcloud/sdk/fog/bean/FindItemBean;", "(Landroid/content/Context;Ljava/util/List;Ljava/lang/String;)V", "mContext", "mList", "addData", "", "data", "", "getItemCount", "", "getLastId", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setData", "ViewHolder", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class FindListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context mContext;
    private List<FindItemBean> mList;
    private String pageType;

    public FindListAdapter(Context context, String pageType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.pageType = StubApp.getString2(2546);
        this.mList = new ArrayList();
        this.mContext = context;
        this.pageType = pageType;
    }

    public FindListAdapter(Context context, List<FindItemBean> dataList, String pageType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dataList, "dataList");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.pageType = StubApp.getString2(2546);
        new ArrayList();
        this.mContext = context;
        this.mList = dataList;
        this.pageType = pageType;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.find_list_fragment_item, parent, false);
        Intrinsics.checkNotNull(viewInflate);
        return new ViewHolder(this, viewInflate);
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        int bindingAdapterPosition = holder.getBindingAdapterPosition();
        final FindItemBean findItemBean = this.mList.get(bindingAdapterPosition);
        int iDp2px = (this.mContext.getResources().getDisplayMetrics().widthPixels - DensityUtil.dp2px(56.0f)) / 2;
        holder.getIvCover().getLayoutParams().width = iDp2px;
        ViewGroup.LayoutParams layoutParams = holder.getIvCover().getLayoutParams();
        layoutParams.width = iDp2px;
        if (findItemBean.getCover_height() == 0 || findItemBean.getCover_width() == 0) {
            layoutParams.height = iDp2px;
        } else {
            layoutParams.height = (iDp2px * findItemBean.getCover_height()) / findItemBean.getCover_width();
        }
        holder.getIvCover().setLayoutParams(layoutParams);
        holder.getIvVideo().setVisibility(findItemBean.getType() == 1 ? 0 : 8);
        holder.getTvTitle().setText(this.mList.get(bindingAdapterPosition).getTitle());
        Glide.with(this.mContext).load(this.mList.get(position).getCover()).apply((BaseRequestOptions<?>) new RequestOptions().transform(new RoundedCornersTransform(this.mContext, DensityUtil.dp2px(12.0f), true, true, false, false))).into(holder.getIvCover());
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.FindListAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FindListAdapter.onBindViewHolder$lambda$0(this.f$0, position, findItemBean, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(FindListAdapter this$0, int i, FindItemBean dataItem, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dataItem, "$dataItem");
        if (this$0.mList.get(i).getUrl() != null) {
            if (dataItem.getType() == 1) {
                int i2 = (Intrinsics.areEqual(this$0.pageType, AmapLocationNetwork.RESULT_TYPE_CELL_ONLY) || Intrinsics.areEqual(this$0.pageType, AmapLocationNetwork.RESULT_TYPE_CELL_WITH_NEIGHBORS)) ? i : 0;
                UmUtils.INSTANCE.trackEvent(this$0.mContext, this$0.mList.get(i));
                PagerUtils.goVideoPlayerPage(this$0.mContext, this$0.mList.get(i), this$0.pageType, Integer.valueOf(i2));
            } else {
                AgentWebActivity.Companion companion = AgentWebActivity.INSTANCE;
                Context context = this$0.mContext;
                String url = this$0.mList.get(i).getUrl();
                Intrinsics.checkNotNull(url);
                companion.open(context, url, "");
            }
        }
    }

    public int getItemCount() {
        return this.mList.size();
    }

    public final void setData(List<FindItemBean> data) {
        this.mList.clear();
        if (data != null) {
            this.mList.addAll(data);
        }
        notifyDataSetChanged();
    }

    public final void addData(List<FindItemBean> data) {
        if (data != null) {
            this.mList.addAll(data);
        }
        notifyDataSetChanged();
    }

    public final String getLastId() {
        if (this.mList.isEmpty()) {
            return null;
        }
        return String.valueOf(this.mList.get(r0.size() - 1).getId());
    }

    /* compiled from: FindListAdapter.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/deye/adapter/FindListAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/deye/adapter/FindListAdapter;Landroid/view/View;)V", "ivCover", "Landroid/widget/ImageView;", "getIvCover", "()Landroid/widget/ImageView;", "ivVideo", "getIvVideo", "tvTitle", "Landroid/widget/TextView;", "getTvTitle", "()Landroid/widget/TextView;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivCover;
        private final ImageView ivVideo;
        final /* synthetic */ FindListAdapter this$0;
        private final TextView tvTitle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(FindListAdapter findListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = findListAdapter;
            View viewFindViewById = itemView.findViewById(R.id.iv_cover);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.ivCover = (ImageView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.tv_title);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.tvTitle = (TextView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(R.id.iv_video);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
            this.ivVideo = (ImageView) viewFindViewById3;
        }

        public final ImageView getIvCover() {
            return this.ivCover;
        }

        public final TextView getTvTitle() {
            return this.tvTitle;
        }

        public final ImageView getIvVideo() {
            return this.ivVideo;
        }
    }
}
