package com.deye.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.deye.activity.message.AcceptActivity;
import com.deye.activity.message.MsgDetailActivity;
import com.mxchipapp.R;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.api.http.RetrofitService;
import io.fogcloud.sdk.fog.bean.MessageBean;
import io.fogcloud.sdk.fog.bean.SimpleResultBean;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MessageListAdapter.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 $2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003$%&B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0014H\u0017J\u0018\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0014H\u0016J\u0014\u0010\u001f\u001a\u00020\u00182\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0018\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0014H\u0007J\u0006\u0010#\u001a\u00020\u0018R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006'"}, d2 = {"Lcom/deye/adapter/MessageListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/deye/adapter/MessageItemVH;", "mContext", "Landroid/content/Context;", "mMessageList", "", "Lio/fogcloud/sdk/fog/bean/MessageBean;", "iOnUpdateData", "Lcom/deye/adapter/MessageListAdapter$IOnUpdateData;", "iDeleteSingleItem", "Lcom/deye/adapter/MessageListAdapter$IDeleteSingleItem;", "(Landroid/content/Context;Ljava/util/List;Lcom/deye/adapter/MessageListAdapter$IOnUpdateData;Lcom/deye/adapter/MessageListAdapter$IDeleteSingleItem;)V", "getMContext", "()Landroid/content/Context;", "getMMessageList", "()Ljava/util/List;", "setMMessageList", "(Ljava/util/List;)V", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "holder", "index", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setDataList", "messageList", "setMsgRead", "msgBean", "updateAllReadUi", "Companion", "IDeleteSingleItem", "IOnUpdateData", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class MessageListAdapter extends RecyclerView.Adapter<MessageItemVH> {
    public static final int TYPE_NORMAL = 0;
    private final IDeleteSingleItem iDeleteSingleItem;
    private final IOnUpdateData iOnUpdateData;
    private final Context mContext;
    private List<MessageBean> mMessageList;
    public static final String TAG = StubApp.getString2(13817);

    /* compiled from: MessageListAdapter.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/deye/adapter/MessageListAdapter$IDeleteSingleItem;", "", "onDeleteSingleItem", "", "position", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface IDeleteSingleItem {
        void onDeleteSingleItem(int position);
    }

    /* compiled from: MessageListAdapter.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/deye/adapter/MessageListAdapter$IOnUpdateData;", "", "onUpdateData", "", "isSelected", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface IOnUpdateData {
        void onUpdateData(boolean isSelected);
    }

    public int getItemViewType(int position) {
        return 0;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final List<MessageBean> getMMessageList() {
        return this.mMessageList;
    }

    public final void setMMessageList(List<MessageBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mMessageList = list;
    }

    public MessageListAdapter(Context mContext, List<MessageBean> mMessageList, IOnUpdateData iOnUpdateData, IDeleteSingleItem iDeleteSingleItem) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(mMessageList, "mMessageList");
        Intrinsics.checkNotNullParameter(iOnUpdateData, "iOnUpdateData");
        this.mContext = mContext;
        this.mMessageList = mMessageList;
        this.iOnUpdateData = iOnUpdateData;
        this.iDeleteSingleItem = iDeleteSingleItem;
    }

    public MessageItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.push_message_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflate(...)");
        return new MessageItemVH(viewInflate);
    }

    public final void setDataList(List<MessageBean> messageList) {
        Intrinsics.checkNotNullParameter(messageList, "messageList");
        this.mMessageList = messageList;
        notifyDataSetChanged();
    }

    public final void updateAllReadUi() {
        Iterator<MessageBean> it2 = this.mMessageList.iterator();
        while (it2.hasNext()) {
            it2.next().setMessage_status(1);
        }
        notifyDataSetChanged();
    }

    public void onBindViewHolder(MessageItemVH holder, int index) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final int absoluteAdapterPosition = holder.getAbsoluteAdapterPosition();
        final MessageBean messageBean = this.mMessageList.get(absoluteAdapterPosition);
        if (messageBean.getMessage_type() == 0) {
            holder.getMessageImage().setBackgroundResource(R.drawable.icon_msg_full_water);
        } else if (messageBean.getMessage_type() == 1) {
            holder.getMessageImage().setBackgroundResource(R.drawable.icon_msg_share);
        } else if (messageBean.getMessage_type() == 2 || messageBean.getMessage_type() == 3) {
            holder.getMessageImage().setBackgroundResource(R.drawable.icon_msg_order);
        } else if (messageBean.getMessage_type() == 9) {
            holder.getMessageImage().setBackgroundResource(R.drawable.icon_message_part);
        } else {
            holder.getMessageImage().setBackgroundResource(R.drawable.icon_msg_default);
        }
        if (messageBean.getMessage_status() == 0) {
            holder.getMessageReadDot().setVisibility(0);
        } else {
            holder.getMessageReadDot().setVisibility(8);
        }
        holder.getMessageContent().setText(messageBean.getMessage_sub_title());
        holder.getMessageTitle().setText(messageBean.getMessage_title());
        if (messageBean.getMessage_type() == 0) {
            holder.getMessageContent().setText(messageBean.getWater_tank_detail());
        }
        holder.getMessageTime().setText(messageBean.getMessage_time());
        holder.getMessageContainer().setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.MessageListAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MessageListAdapter.onBindViewHolder$lambda$0(messageBean, this, absoluteAdapterPosition, view);
            }
        });
        holder.getMessageTime().setVisibility(0);
        holder.getMessageDelete().setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.MessageListAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MessageListAdapter.onBindViewHolder$lambda$1(this.f$0, absoluteAdapterPosition, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(MessageBean msgBean, MessageListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(msgBean, "$msgBean");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int message_type = msgBean.getMessage_type();
        String string2 = StubApp.getString2(13229);
        if (message_type == 1) {
            this$0.setMsgRead(msgBean, i);
            Intent intent = new Intent(this$0.mContext, (Class<?>) AcceptActivity.class);
            intent.putExtra(string2, msgBean.getMessage_id());
            this$0.mContext.startActivity(intent);
            return;
        }
        this$0.setMsgRead(msgBean, i);
        Intent intent2 = new Intent(this$0.mContext, (Class<?>) MsgDetailActivity.class);
        intent2.putExtra(string2, msgBean.getMessage_id());
        this$0.mContext.startActivity(intent2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$1(MessageListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        IDeleteSingleItem iDeleteSingleItem = this$0.iDeleteSingleItem;
        if (iDeleteSingleItem != null) {
            iDeleteSingleItem.onDeleteSingleItem(i);
        }
    }

    public final void setMsgRead(final MessageBean msgBean, final int position) {
        Intrinsics.checkNotNullParameter(msgBean, "msgBean");
        if (msgBean.getMessage_status() != 0) {
            return;
        }
        RetrofitService apiService = RetrofitManager.INSTANCE.getApiService();
        String message_id = msgBean.getMessage_id();
        Intrinsics.checkNotNull(message_id);
        apiService.setMsgRead(message_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.deye.adapter.MessageListAdapter.setMsgRead.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<SimpleResultBean> it2) throws RuntimeException {
                Intrinsics.checkNotNullParameter(it2, "it");
                msgBean.setMessage_status(1);
                MMKV mmkvDefaultMMKV = MMKV.defaultMMKV();
                String string2 = StubApp.getString2(13739);
                int i = mmkvDefaultMMKV.getInt(string2, 0);
                if (i > 0) {
                    MMKV.defaultMMKV().putInt(string2, i - 1);
                }
                this.notifyItemChanged(position);
            }
        }, new Consumer() { // from class: com.deye.adapter.MessageListAdapter.setMsgRead.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
            }
        });
    }

    public int getItemCount() {
        return this.mMessageList.size();
    }
}
