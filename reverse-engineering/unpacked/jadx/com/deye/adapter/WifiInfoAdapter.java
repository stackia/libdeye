package com.deye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.deye.helper.WifiScanResult;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ItemWifiInfoBinding;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: WifiInfoAdapter.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0019\u001aB#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0010H\u0016J\u0018\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0010H\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/deye/adapter/WifiInfoAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/deye/adapter/WifiInfoAdapter$ViewHolder;", "mContext", "Landroid/content/Context;", "groupList", "", "Lcom/deye/helper/WifiScanResult;", "wifiListener", "Lcom/deye/adapter/WifiInfoAdapter$IWifiListener;", "(Landroid/content/Context;Ljava/util/List;Lcom/deye/adapter/WifiInfoAdapter$IWifiListener;)V", "getGroupList", "()Ljava/util/List;", "getWifiListener", "()Lcom/deye/adapter/WifiInfoAdapter$IWifiListener;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "IWifiListener", "ViewHolder", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class WifiInfoAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final List<WifiScanResult> groupList;
    private final Context mContext;
    private final IWifiListener wifiListener;

    /* compiled from: WifiInfoAdapter.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/deye/adapter/WifiInfoAdapter$IWifiListener;", "", "onWifiSelect", "", "wifiScanResult", "Lcom/deye/helper/WifiScanResult;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface IWifiListener {
        void onWifiSelect(WifiScanResult wifiScanResult);
    }

    public final List<WifiScanResult> getGroupList() {
        return this.groupList;
    }

    public final IWifiListener getWifiListener() {
        return this.wifiListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public WifiInfoAdapter(Context mContext, List<? extends WifiScanResult> groupList, IWifiListener wifiListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(groupList, "groupList");
        Intrinsics.checkNotNullParameter(wifiListener, "wifiListener");
        this.mContext = mContext;
        this.groupList = groupList;
        this.wifiListener = wifiListener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemWifiInfoBinding itemWifiInfoBindingInflate = ItemWifiInfoBinding.inflate(LayoutInflater.from(this.mContext), parent, false);
        Intrinsics.checkNotNullExpressionValue(itemWifiInfoBindingInflate, "inflate(...)");
        return new ViewHolder(itemWifiInfoBindingInflate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, java.lang.Object] */
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = this.groupList.get(position);
        holder.getBinding().tvWifiName.setText(((WifiScanResult) objectRef.element).SSID);
        if (((WifiScanResult) objectRef.element).signalLevel == 3) {
            holder.getBinding().ivSignal.setBackgroundResource(R.drawable.icon_signal_level3);
        } else if (((WifiScanResult) objectRef.element).signalLevel == 2) {
            holder.getBinding().ivSignal.setBackgroundResource(R.drawable.icon_signal_level2);
        } else if (((WifiScanResult) objectRef.element).signalLevel == 1) {
            holder.getBinding().ivSignal.setBackgroundResource(R.drawable.icon_signal_level1);
        }
        if (((WifiScanResult) objectRef.element).is24GHz) {
            holder.getBinding().tvUse.setTextColor(this.mContext.getColor(R.color.blue_text));
            holder.getBinding().tvUse.setText(this.mContext.getString(R.string.use_this_wifi));
        } else {
            holder.getBinding().tvUse.setTextColor(this.mContext.getColor(R.color.dark60));
            holder.getBinding().tvUse.setText(this.mContext.getString(R.string.does_not_support_5ghz_wifi));
        }
        holder.getBinding().tvUse.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.WifiInfoAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiInfoAdapter.onBindViewHolder$lambda$0(objectRef, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onBindViewHolder$lambda$0(Ref.ObjectRef itemBean, WifiInfoAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(itemBean, "$itemBean");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((WifiScanResult) itemBean.element).is24GHz) {
            this$0.wifiListener.onWifiSelect((WifiScanResult) itemBean.element);
        }
    }

    public int getItemCount() {
        return this.groupList.size();
    }

    /* compiled from: WifiInfoAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/deye/adapter/WifiInfoAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/mxchipapp/databinding/ItemWifiInfoBinding;", "(Lcom/mxchipapp/databinding/ItemWifiInfoBinding;)V", "getBinding", "()Lcom/mxchipapp/databinding/ItemWifiInfoBinding;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemWifiInfoBinding binding;

        public final ItemWifiInfoBinding getBinding() {
            return this.binding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(ItemWifiInfoBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }
    }
}
