package com.deye.fragment;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.alibaba.fastjson.JSONArray;
import com.deye.data.DeyeMessageRepository;
import com.deye.entity.BannerViewBean;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.helper.Configuration;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import org.json.JSONObject;

/* compiled from: BannerViewVM.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/deye/fragment/BannerViewVM;", "Landroidx/lifecycle/ViewModel;", "()V", "messageRepository", "Lcom/deye/data/DeyeMessageRepository;", "resultData", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/deye/entity/BannerViewBean;", "getResultData", "()Landroidx/lifecycle/MutableLiveData;", "getBannerViewList", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class BannerViewVM extends ViewModel {
    private final DeyeMessageRepository messageRepository = new DeyeMessageRepository();
    private final MutableLiveData<List<BannerViewBean>> resultData = new MutableLiveData<>();

    public BannerViewVM() {
        getBannerViewList();
    }

    public final MutableLiveData<List<BannerViewBean>> getResultData() {
        return this.resultData;
    }

    /* compiled from: BannerViewVM.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.deye.fragment.BannerViewVM$getBannerViewList$1", f = "BannerViewVM.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.deye.fragment.BannerViewVM$getBannerViewList$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BannerViewVM.this.new AnonymousClass1(continuation);
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
                DeyeMessageRepository deyeMessageRepository = BannerViewVM.this.messageRepository;
                String strGET_BANNER_VIEW_LIST = Configuration.GET_BANNER_VIEW_LIST();
                Intrinsics.checkNotNullExpressionValue(strGET_BANNER_VIEW_LIST, "GET_BANNER_VIEW_LIST(...)");
                final BannerViewVM bannerViewVM = BannerViewVM.this;
                deyeMessageRepository.doHttpGetRepository(strGET_BANNER_VIEW_LIST, new Function1<String, Unit>() { // from class: com.deye.fragment.BannerViewVM.getBannerViewList.1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(String str) {
                        invoke2(str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String it2) {
                        Intrinsics.checkNotNullParameter(it2, "it");
                        Log.d(StubApp.getString2(13002), StubApp.getString2(14034) + it2 + StubApp.getString2(14035));
                        String string = new JSONObject(it2).getJSONArray(StubApp.getString2(100)).toString();
                        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                        bannerViewVM.getResultData().postValue(JSONArray.parseArray(string, BannerViewBean.class));
                    }
                });
                return Unit.INSTANCE;
            }
            throw new IllegalStateException(StubApp.getString2(13735));
        }
    }

    private final void getBannerViewList() {
        BuildersKt.launch$default(GlobalScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3, (Object) null);
    }
}
