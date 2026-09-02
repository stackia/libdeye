package com.deye.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.deye.data.DeyeMessageRepository;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.bean.MessageBean;
import io.fogcloud.sdk.fog.bean.MessageResultBean;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import io.fogcloud.sdk.fog.helper.Configuration;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;

/* compiled from: MessageListViewModel.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015J\u001a\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0018\u001a\u00020\u0005H\u0007R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0007¨\u0006\u001a"}, d2 = {"Lcom/deye/viewmodels/MessageListViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "mNextKeyForNextPaging", "Landroidx/lifecycle/MutableLiveData;", "", "getMNextKeyForNextPaging", "()Landroidx/lifecycle/MutableLiveData;", "mNextKeyForNextPagingDeviceNotify", "", "getMNextKeyForNextPagingDeviceNotify", "messageRepository", "Lcom/deye/data/DeyeMessageRepository;", "resultData", "", "Lio/fogcloud/sdk/fog/bean/MessageBean;", "getResultData", "deleteMessage", "", "message", "fogCallBack", "Lio/fogcloud/sdk/fog/callback/FogCallBack;", "requestFirstNextPageMessage", "next", "page_size", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class MessageListViewModel extends ViewModel {
    public static final String TAG = StubApp.getString2(14505);
    private final DeyeMessageRepository messageRepository = new DeyeMessageRepository();
    private final MutableLiveData<List<MessageBean>> resultData = new MutableLiveData<>();
    private final MutableLiveData<Integer> mNextKeyForNextPaging = new MutableLiveData<>();
    private final MutableLiveData<String> mNextKeyForNextPagingDeviceNotify = new MutableLiveData<>();

    public final MutableLiveData<List<MessageBean>> getResultData() {
        return this.resultData;
    }

    public final MutableLiveData<Integer> getMNextKeyForNextPaging() {
        return this.mNextKeyForNextPaging;
    }

    public final MutableLiveData<String> getMNextKeyForNextPagingDeviceNotify() {
        return this.mNextKeyForNextPagingDeviceNotify;
    }

    public final void requestFirstNextPageMessage(String next, int page_size) {
        RetrofitManager.INSTANCE.getApiService().getMessageList(page_size, next).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.deye.viewmodels.MessageListViewModel.requestFirstNextPageMessage.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<MessageResultBean> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                MutableLiveData<String> mNextKeyForNextPagingDeviceNotify = MessageListViewModel.this.getMNextKeyForNextPagingDeviceNotify();
                MessageResultBean data = it2.getData();
                mNextKeyForNextPagingDeviceNotify.postValue(data != null ? data.getNext() : null);
                MutableLiveData<List<MessageBean>> resultData = MessageListViewModel.this.getResultData();
                MessageResultBean data2 = it2.getData();
                Intrinsics.checkNotNull(data2);
                resultData.postValue(data2.getList());
            }
        }, new Consumer() { // from class: com.deye.viewmodels.MessageListViewModel.requestFirstNextPageMessage.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                it2.printStackTrace();
            }
        });
    }

    public final void deleteMessage(MessageBean message, FogCallBack fogCallBack) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(fogCallBack, "fogCallBack");
        ArrayList arrayList = new ArrayList();
        String message_id = message.getMessage_id();
        Intrinsics.checkNotNull(message_id);
        arrayList.add(message_id);
        BuildersKt.launch$default(GlobalScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(Configuration.DELETE_DEVICE_MESSAGE_DELETE_BATCH(), arrayList, fogCallBack, null), 3, (Object) null);
    }

    /* compiled from: MessageListViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.deye.viewmodels.MessageListViewModel$deleteMessage$1", f = "MessageListViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.deye.viewmodels.MessageListViewModel$deleteMessage$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ FogCallBack $fogCallBack;
        final /* synthetic */ List<String> $messageIDList;
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, List<String> list, FogCallBack fogCallBack, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$url = str;
            this.$messageIDList = list;
            this.$fogCallBack = fogCallBack;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MessageListViewModel.this.new AnonymousClass1(this.$url, this.$messageIDList, this.$fogCallBack, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                DeyeMessageRepository deyeMessageRepository = MessageListViewModel.this.messageRepository;
                String url = this.$url;
                Intrinsics.checkNotNullExpressionValue(url, "$url");
                deyeMessageRepository.deleteMessage(url, this.$messageIDList, this.$fogCallBack);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException(StubApp.getString2(13735));
        }
    }
}
