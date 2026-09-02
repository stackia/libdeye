package com.deye.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.google.gson.Gson;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ViewPartsBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.bean.PartBean;
import io.fogcloud.sdk.fog.log.LogUtil;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PartsView.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0016\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011J\u0016\u0010\u0013\u001a\u00020\u000e2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0002R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/deye/views/PartsView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/mxchipapp/databinding/ViewPartsBinding;", "init", "", "reqDetail", "deviceId", "", "productId", "updateViewByData", "list", "", "Lio/fogcloud/sdk/fog/bean/PartBean;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class PartsView extends FrameLayout {
    private ViewPartsBinding binding;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PartsView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PartsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PartsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    private final void init(Context context) {
        this.binding = ViewPartsBinding.inflate(LayoutInflater.from(context), this, true);
    }

    public final void reqDetail(String deviceId, String productId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullExpressionValue(RetrofitManager.INSTANCE.getApiService().getPartDetail(deviceId, productId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.deye.views.PartsView$reqDetail$flowable$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<List<PartBean>> it2) {
                List<PartBean> data;
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseResult.MetaBean meta = it2.getMeta();
                if (meta == null || meta.getCode() != 0 || (data = it2.getData()) == null) {
                    return;
                }
                this.this$0.updateViewByData(data);
            }
        }, new Consumer() { // from class: com.deye.views.PartsView$reqDetail$flowable$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
            }
        }), "subscribe(...)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void updateViewByData(List<? extends PartBean> list) {
        boolean z;
        boolean z2;
        LogUtil.e(StubApp.getString2(14626), new Gson().toJson(list));
        if (!list.isEmpty()) {
            z = false;
            z2 = false;
            for (PartBean partBean : list) {
                Integer num = partBean.replace_percentage;
                String string2 = StubApp.getString2(14627);
                String string22 = StubApp.getString2(13674);
                if (num != null) {
                    Integer num2 = partBean.replace_percentage;
                    Intrinsics.checkNotNullExpressionValue(num2, string22);
                    if (num2.intValue() >= 5) {
                        if (partBean.maintenance_percentage != null) {
                            Integer num3 = partBean.maintenance_percentage;
                            Intrinsics.checkNotNullExpressionValue(num3, string2);
                            if (num3.intValue() < 5) {
                                z = true;
                            }
                        }
                    }
                }
                if (partBean.replace_percentage != null) {
                    Integer num4 = partBean.replace_percentage;
                    Intrinsics.checkNotNullExpressionValue(num4, string22);
                    if (num4.intValue() >= 20) {
                        if (partBean.maintenance_percentage != null) {
                            Integer num5 = partBean.maintenance_percentage;
                            Intrinsics.checkNotNullExpressionValue(num5, string2);
                            if (num5.intValue() < 20) {
                            }
                        }
                    }
                    z2 = true;
                }
            }
        } else {
            z = false;
            z2 = false;
        }
        if (z) {
            ViewPartsBinding viewPartsBinding = this.binding;
            Intrinsics.checkNotNull(viewPartsBinding);
            viewPartsBinding.tvPartsState.setVisibility(0);
            ViewPartsBinding viewPartsBinding2 = this.binding;
            Intrinsics.checkNotNull(viewPartsBinding2);
            viewPartsBinding2.tvPartsState.setBackgroundResource(R.drawable.bg_parts_lack);
            ViewPartsBinding viewPartsBinding3 = this.binding;
            Intrinsics.checkNotNull(viewPartsBinding3);
            viewPartsBinding3.tvPartsState.setText(getContext().getString(R.string.component_status_shortage));
            ViewPartsBinding viewPartsBinding4 = this.binding;
            Intrinsics.checkNotNull(viewPartsBinding4);
            viewPartsBinding4.tvPartsState.setTextColor(Color.parseColor(StubApp.getString2(13487)));
            return;
        }
        if (z2) {
            ViewPartsBinding viewPartsBinding5 = this.binding;
            Intrinsics.checkNotNull(viewPartsBinding5);
            viewPartsBinding5.tvPartsState.setVisibility(0);
            ViewPartsBinding viewPartsBinding6 = this.binding;
            Intrinsics.checkNotNull(viewPartsBinding6);
            viewPartsBinding6.tvPartsState.setBackgroundResource(R.drawable.bg_parts_stress);
            ViewPartsBinding viewPartsBinding7 = this.binding;
            Intrinsics.checkNotNull(viewPartsBinding7);
            viewPartsBinding7.tvPartsState.setText(getContext().getString(R.string.component_status_tight));
            ViewPartsBinding viewPartsBinding8 = this.binding;
            Intrinsics.checkNotNull(viewPartsBinding8);
            viewPartsBinding8.tvPartsState.setTextColor(Color.parseColor(StubApp.getString2(14523)));
            return;
        }
        ViewPartsBinding viewPartsBinding9 = this.binding;
        Intrinsics.checkNotNull(viewPartsBinding9);
        viewPartsBinding9.tvPartsState.setVisibility(8);
    }
}
