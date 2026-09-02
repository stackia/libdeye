package com.deye.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.deye.activity.mine.QaDetailActivity;
import com.deye.entity.QaBean;
import com.mxchipapp.R;
import com.stub.StubApp;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QaAdapter.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0016B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u001d\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\b\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\rH\u0016J\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0016R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/deye/adapter/QaAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/deye/adapter/QaAdapter$ViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "list", "", "Lcom/deye/entity/QaBean;", "(Landroid/content/Context;Ljava/util/List;)V", "mContext", "mList", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class QaAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context mContext;
    private List<QaBean> mList;

    public QaAdapter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mContext = context;
    }

    public QaAdapter(Context context, List<QaBean> list) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(list, "list");
        this.mContext = context;
        this.mList = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.qa_item, parent, false);
        Intrinsics.checkNotNull(viewInflate);
        return new ViewHolder(viewInflate);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        int bindingAdapterPosition = holder.getBindingAdapterPosition();
        List<QaBean> list = this.mList;
        List<QaBean> list2 = null;
        String string2 = StubApp.getString2(13818);
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            list = null;
        }
        final QaBean qaBean = list.get(bindingAdapterPosition);
        holder.getTvTitle().setText(qaBean.getTitle());
        List<QaBean> list3 = this.mList;
        if (list3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            list2 = list3;
        }
        if (bindingAdapterPosition == list2.size() - 1) {
            holder.getLineView().setVisibility(8);
        } else {
            holder.getLineView().setVisibility(0);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.QaAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QaAdapter.onBindViewHolder$lambda$0(this.f$0, qaBean, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(QaAdapter this$0, QaBean dataItem, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dataItem, "$dataItem");
        Intent intent = new Intent(this$0.mContext, (Class<?>) QaDetailActivity.class);
        intent.putExtra(StubApp.getString2(13691), dataItem);
        this$0.mContext.startActivity(intent);
    }

    public int getItemCount() {
        List<QaBean> list = this.mList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mList");
            list = null;
        }
        return list.size();
    }

    /* compiled from: QaAdapter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/deye/adapter/QaAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "lineView", "getLineView", "()Landroid/view/View;", "tvTitle", "Landroid/widget/TextView;", "getTvTitle", "()Landroid/widget/TextView;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final View lineView;
        private final TextView tvTitle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View viewFindViewById = itemView.findViewById(R.id.line_view);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.lineView = viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.tv_title);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.tvTitle = (TextView) viewFindViewById2;
        }

        public final TextView getTvTitle() {
            return this.tvTitle;
        }

        public final View getLineView() {
            return this.lineView;
        }
    }
}
