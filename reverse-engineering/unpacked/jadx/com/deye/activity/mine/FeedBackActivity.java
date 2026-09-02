package com.deye.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import com.deye.activity.BasePicSelectActivity;
import com.deye.activity.device.base.BaseActivity;
import com.deye.utils.BaseUtils;
import com.deye.views.UploadPicView;
import com.luck.picture.lib.entity.LocalMedia;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ActivityFeedBackBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.SimpleResultBean;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.reactivestreams.Subscription;

/* compiled from: FeedBackActivity.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\u0012\u0010\f\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J \u0010\u000f\u001a\u00020\n2\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\nH\u0007R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/deye/activity/mine/FeedBackActivity;", "Lcom/deye/activity/BasePicSelectActivity;", "()V", "MAX_COUNT", "", "getMAX_COUNT", "()I", "mAtyBinding", "Lcom/mxchipapp/databinding/ActivityFeedBackBinding;", "checkShowUpload", "", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPicResult", "result", "Ljava/util/ArrayList;", "Lcom/luck/picture/lib/entity/LocalMedia;", "Lkotlin/collections/ArrayList;", "preview", "uploadPicView", "Lcom/deye/views/UploadPicView;", "submit", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class FeedBackActivity extends BasePicSelectActivity {
    private final int MAX_COUNT = 4;
    private ActivityFeedBackBinding mAtyBinding;

    static {
        StubApp.interface11(14381);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$1(FeedBackActivity feedBackActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$4(FeedBackActivity feedBackActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onCreate$lambda$0(FeedBackActivity feedBackActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onPicResult$lambda$5(FeedBackActivity feedBackActivity, UploadPicView uploadPicView, View view);

    private final native void preview(UploadPicView uploadPicView);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void submit$lambda$6(FeedBackActivity feedBackActivity);

    public final native void checkShowUpload();

    public final native int getMAX_COUNT();

    public final native void initView();

    @Override // com.deye.activity.BasePicSelectActivity, com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // com.deye.activity.BasePicSelectActivity
    public native void onPicResult(ArrayList<LocalMedia> result);

    public final native void submit();

    /* compiled from: FeedBackActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/deye/activity/mine/FeedBackActivity$initView$4", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.FeedBackActivity$initView$4, reason: invalid class name */
    public static final class AnonymousClass4 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        AnonymousClass4() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            Intrinsics.checkNotNullParameter(s, "s");
            ActivityFeedBackBinding activityFeedBackBinding = FeedBackActivity.this.mAtyBinding;
            if (activityFeedBackBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAtyBinding");
                activityFeedBackBinding = null;
            }
            activityFeedBackBinding.btnSubmit.setEnabled(s.length() > 0);
        }
    }

    /* compiled from: FeedBackActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J(\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/deye/activity/mine/FeedBackActivity$initView$5", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.FeedBackActivity$initView$5, reason: invalid class name */
    public static final class AnonymousClass5 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Intrinsics.checkNotNullParameter(s, "s");
        }

        AnonymousClass5() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            Intrinsics.checkNotNullParameter(s, "s");
            if (s.length() > 300) {
                BaseUtils.showShortToast(FeedBackActivity.this.getString(R.string.word_limit_exceeded));
                s.delete(300, s.length());
            }
            ActivityFeedBackBinding activityFeedBackBinding = FeedBackActivity.this.mAtyBinding;
            if (activityFeedBackBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAtyBinding");
                activityFeedBackBinding = null;
            }
            activityFeedBackBinding.tvCount.setText(s.length() + StubApp.getString2(13745));
        }
    }

    /* compiled from: FeedBackActivity.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/deye/activity/mine/FeedBackActivity$onPicResult$2", "Lcom/deye/views/UploadPicView$IUploadPicListener;", "onDelete", "", "onUploadFail", "onUploadSuccess", "url", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.FeedBackActivity$onPicResult$2, reason: invalid class name */
    public static final class AnonymousClass2 implements UploadPicView.IUploadPicListener {
        final /* synthetic */ UploadPicView $uploadPicView;

        @Override // com.deye.views.UploadPicView.IUploadPicListener
        public void onUploadSuccess(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
        }

        AnonymousClass2(UploadPicView uploadPicView) {
            this.$uploadPicView = uploadPicView;
        }

        @Override // com.deye.views.UploadPicView.IUploadPicListener
        public void onDelete() {
            ActivityFeedBackBinding activityFeedBackBinding = FeedBackActivity.this.mAtyBinding;
            if (activityFeedBackBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAtyBinding");
                activityFeedBackBinding = null;
            }
            activityFeedBackBinding.llPic.removeView(this.$uploadPicView);
            FeedBackActivity.this.checkShowUpload();
        }

        @Override // com.deye.views.UploadPicView.IUploadPicListener
        public void onUploadFail() {
            ActivityFeedBackBinding activityFeedBackBinding = FeedBackActivity.this.mAtyBinding;
            if (activityFeedBackBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAtyBinding");
                activityFeedBackBinding = null;
            }
            activityFeedBackBinding.llPic.removeView(this.$uploadPicView);
            FeedBackActivity.this.checkShowUpload();
        }
    }

    /* compiled from: FeedBackActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lorg/reactivestreams/Subscription;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.FeedBackActivity$submit$1, reason: invalid class name */
    static final class AnonymousClass1<T> implements Consumer {
        AnonymousClass1() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Subscription it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            FeedBackActivity feedBackActivity = FeedBackActivity.this;
            BaseActivity.showLoading$default(feedBackActivity, (Context) feedBackActivity.mContext, null, 2, null);
        }
    }

    /* compiled from: FeedBackActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/SimpleResultBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.FeedBackActivity$submit$3, reason: invalid class name */
    static final class AnonymousClass3<T> implements Consumer {
        AnonymousClass3() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(BaseResult<SimpleResultBean> it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            BaseResult.MetaBean meta = it2.getMeta();
            if (meta != null && meta.getCode() == 0) {
                BaseUtils.showShortToast(FeedBackActivity.this.getString(R.string.submission_successful));
                FeedBackActivity.this.finish();
            } else {
                BaseResult.MetaBean meta2 = it2.getMeta();
                BaseUtils.showShortToast(String.valueOf(meta2 != null ? meta2.getMessage() : null));
            }
        }
    }

    /* compiled from: FeedBackActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.FeedBackActivity$submit$4, reason: invalid class name and case insensitive filesystem */
    static final class C01744<T> implements Consumer {
        public static final C01744<T> INSTANCE = new C01744<>();

        C01744() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Throwable it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            it2.printStackTrace();
        }
    }
}
