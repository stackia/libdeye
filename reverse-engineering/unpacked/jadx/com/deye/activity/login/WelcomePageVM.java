package com.deye.activity.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.deye.data.DeyeMessageRepository;
import com.deye.entity.BootImageBean;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.helper.Configuration;
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

/* compiled from: WelcomePageVM.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0007R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/deye/activity/login/WelcomePageVM;", "Landroidx/lifecycle/ViewModel;", "()V", "resultData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/deye/entity/BootImageBean;", "getResultData", "()Landroidx/lifecycle/MutableLiveData;", "getBootImage", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class WelcomePageVM extends ViewModel {
    private final MutableLiveData<BootImageBean> resultData = new MutableLiveData<>();

    public final MutableLiveData<BootImageBean> getResultData() {
        return this.resultData;
    }

    /* compiled from: WelcomePageVM.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.deye.activity.login.WelcomePageVM$getBootImage$1", f = "WelcomePageVM.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.deye.activity.login.WelcomePageVM$getBootImage$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DeyeMessageRepository $messageRepository;
        int label;
        final /* synthetic */ WelcomePageVM this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(DeyeMessageRepository deyeMessageRepository, WelcomePageVM welcomePageVM, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$messageRepository = deyeMessageRepository;
            this.this$0 = welcomePageVM;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$messageRepository, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException(StubApp.getString2(13735));
            }
            ResultKt.throwOnFailure(obj);
            DeyeMessageRepository deyeMessageRepository = this.$messageRepository;
            String strGET_BOOT_IMAGE = Configuration.GET_BOOT_IMAGE();
            Intrinsics.checkNotNullExpressionValue(strGET_BOOT_IMAGE, "GET_BOOT_IMAGE(...)");
            final WelcomePageVM welcomePageVM = this.this$0;
            deyeMessageRepository.doHttpGetRepository(strGET_BOOT_IMAGE, new Function1<String, Unit>() { // from class: com.deye.activity.login.WelcomePageVM.getBootImage.1.1
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
                    String string = new JSONObject(it2).getJSONObject(StubApp.getString2(100)).toString();
                    Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                    welcomePageVM.getResultData().postValue((BootImageBean) com.alibaba.fastjson.JSONObject.parseObject(string, BootImageBean.class));
                }
            });
            return Unit.INSTANCE;
        }
    }

    public final void getBootImage() {
        BuildersKt.launch$default(GlobalScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(new DeyeMessageRepository(), this, null), 3, (Object) null);
    }
}
