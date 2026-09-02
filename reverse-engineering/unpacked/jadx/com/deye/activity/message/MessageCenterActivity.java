package com.deye.activity.message;

import android.os.Bundle;
import android.view.View;
import com.deye.activity.device.base.BaseActivity;
import com.deye.adapter.MessageListAdapter;
import com.deye.viewmodels.MessageListViewModel;
import com.mxchipapp.databinding.ActivityMessageCenterBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.MessageBean;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: MessageCenterActivity.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 52\u00020\u00012\u00020\u00022\u00020\u0003:\u00015B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0010H\u0002J\b\u0010$\u001a\u00020\"H\u0002J\b\u0010%\u001a\u00020\"H\u0002J\b\u0010&\u001a\u00020\"H\u0007J\u0006\u0010'\u001a\u00020\"J\u0006\u0010(\u001a\u00020\"J\u0012\u0010)\u001a\u00020\"2\b\u0010*\u001a\u0004\u0018\u00010+H\u0017J\u0010\u0010,\u001a\u00020\"2\u0006\u0010-\u001a\u00020.H\u0016J\b\u0010/\u001a\u00020\"H\u0014J\b\u00100\u001a\u00020\"H\u0014J\u0010\u00101\u001a\u00020\"2\u0006\u00102\u001a\u00020\bH\u0016J\u0014\u00103\u001a\u00020\"2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u00066"}, d2 = {"Lcom/deye/activity/message/MessageCenterActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "Lcom/deye/adapter/MessageListAdapter$IOnUpdateData;", "Lcom/deye/adapter/MessageListAdapter$IDeleteSingleItem;", "()V", "activityMessageCenterBinding", "Lcom/mxchipapp/databinding/ActivityMessageCenterBinding;", "isLastPage", "", "()Z", "setLastPage", "(Z)V", "isLoading", "setLoading", "mDeyeMessageList", "", "Lio/fogcloud/sdk/fog/bean/MessageBean;", "getMDeyeMessageList", "()Ljava/util/List;", "setMDeyeMessageList", "(Ljava/util/List;)V", "messageListAdapter", "Lcom/deye/adapter/MessageListAdapter;", "getMessageListAdapter", "()Lcom/deye/adapter/MessageListAdapter;", "setMessageListAdapter", "(Lcom/deye/adapter/MessageListAdapter;)V", "messageListViewModel", "Lcom/deye/viewmodels/MessageListViewModel;", "getMessageListViewModel", "()Lcom/deye/viewmodels/MessageListViewModel;", "setMessageListViewModel", "(Lcom/deye/viewmodels/MessageListViewModel;)V", "deleteMessage", "", "deyeMessage", "initAdapter", "initMessageCount", "initView", "loadFirstPage", "loadNextPage", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDeleteSingleItem", "position", "", "onDestroy", "onResume", "onUpdateData", "isSelected", "updateUi", "messageList", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class MessageCenterActivity extends BaseActivity implements MessageListAdapter.IOnUpdateData, MessageListAdapter.IDeleteSingleItem {
    public static final String TAG = StubApp.getString2(13738);
    private ActivityMessageCenterBinding activityMessageCenterBinding;
    private boolean isLastPage;
    private boolean isLoading;
    private List<MessageBean> mDeyeMessageList = new ArrayList();
    public MessageListAdapter messageListAdapter;
    protected MessageListViewModel messageListViewModel;

    static {
        StubApp.interface11(14312);
        INSTANCE = new Companion(null);
    }

    private final native void deleteMessage(MessageBean deyeMessage);

    private final native void initAdapter();

    private final native void initMessageCount();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$0(MessageCenterActivity messageCenterActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$2(MessageCenterActivity messageCenterActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$2$lambda$1(MessageCenterActivity messageCenterActivity);

    public final native List<MessageBean> getMDeyeMessageList();

    public final native MessageListAdapter getMessageListAdapter();

    protected final native MessageListViewModel getMessageListViewModel();

    public final native void initView();

    protected final native boolean isLastPage();

    protected final native boolean isLoading();

    public final native void loadFirstPage();

    public final native void loadNextPage();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // com.deye.adapter.MessageListAdapter.IDeleteSingleItem
    public native void onDeleteSingleItem(int position);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onResume();

    @Override // com.deye.adapter.MessageListAdapter.IOnUpdateData
    public native void onUpdateData(boolean isSelected);

    protected final native void setLastPage(boolean z);

    protected final native void setLoading(boolean z);

    public final native void setMDeyeMessageList(List<MessageBean> list);

    public final native void setMessageListAdapter(MessageListAdapter messageListAdapter);

    protected final native void setMessageListViewModel(MessageListViewModel messageListViewModel);

    public final native void updateUi(List<MessageBean> messageList);

    /* compiled from: MessageCenterActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "Lio/fogcloud/sdk/fog/bean/MessageBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.message.MessageCenterActivity$initView$3, reason: invalid class name */
    static final class AnonymousClass3 extends Lambda implements Function1<List<? extends MessageBean>, Unit> {
        AnonymousClass3() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends MessageBean> list) {
            invoke2((List<MessageBean>) list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(List<MessageBean> list) {
            MessageCenterActivity.this.hideLoading();
            MessageCenterActivity.this.setLoading(false);
            if (list != null) {
                MessageCenterActivity messageCenterActivity = MessageCenterActivity.this;
                List mutableList = CollectionsKt.toMutableList((Collection) list);
                LogUtil.d(StubApp.getString2(13002), StubApp.getString2(13737) + list.size());
                messageCenterActivity.setLastPage(list.size() != 20);
                messageCenterActivity.getMDeyeMessageList().addAll(mutableList);
                messageCenterActivity.updateUi(messageCenterActivity.getMDeyeMessageList());
            }
        }
    }

    /* compiled from: MessageCenterActivity.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\t"}, d2 = {"com/deye/activity/message/MessageCenterActivity$deleteMessage$1", "Lio/fogcloud/sdk/fog/callback/FogCallBack;", "onFailure", "", "code", "", "message", "", "onSuccess", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.message.MessageCenterActivity$deleteMessage$1, reason: invalid class name */
    public static final class AnonymousClass1 implements FogCallBack {
        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onFailure(int code, String message) {
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onSuccess(String message) {
        }

        AnonymousClass1() {
        }
    }
}
