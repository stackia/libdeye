package com.deye.activity.message;

import android.os.Bundle;
import android.view.View;
import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.databinding.ActivityMsgDetailBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.MessageBean;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgDetailActivity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0012\u0010\t\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0017J\b\u0010\f\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/deye/activity/message/MsgDetailActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "mViewDataBinding", "Lcom/mxchipapp/databinding/ActivityMsgDetailBinding;", "initView", "", "bean", "Lio/fogcloud/sdk/fog/bean/MessageBean;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "requestBean", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class MsgDetailActivity extends BaseActivity {
    private ActivityMsgDetailBinding mViewDataBinding;

    /* compiled from: MsgDetailActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.message.MsgDetailActivity$requestBean$3, reason: invalid class name */
    static final class AnonymousClass3<T> implements Consumer {
        public static final AnonymousClass3<T> INSTANCE = new AnonymousClass3<>();

        AnonymousClass3() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Throwable it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
        }
    }

    static {
        StubApp.interface11(14318);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$3(MessageBean messageBean, MsgDetailActivity msgDetailActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onCreate$lambda$0(MsgDetailActivity msgDetailActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void requestBean$lambda$1(MsgDetailActivity msgDetailActivity);

    public final native void initView(MessageBean bean);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    public final native void requestBean();

    /* compiled from: MsgDetailActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/MessageBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.message.MsgDetailActivity$requestBean$2, reason: invalid class name */
    static final class AnonymousClass2<T> implements Consumer {
        AnonymousClass2() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(BaseResult<MessageBean> it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            BaseResult.MetaBean meta = it2.getMeta();
            if (meta != null && meta.getCode() == 0) {
                MsgDetailActivity msgDetailActivity = MsgDetailActivity.this;
                MessageBean data = it2.getData();
                Intrinsics.checkNotNull(data);
                msgDetailActivity.initView(data);
                return;
            }
            MsgDetailActivity.this.finish();
        }
    }
}
