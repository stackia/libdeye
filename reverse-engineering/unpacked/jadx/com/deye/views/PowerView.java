package com.deye.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.amap.location.support.bean.location.AmapLocationNetwork;
import com.deye.helper.DialogHelper;
import com.deye.utils.LanUtils;
import com.deye.views.PowerView;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ViewPowerBinding;
import com.stub.StubApp;
import com.zhouyou.view.seekbar.SignUtils;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PowerView.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0001\u001eB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0010J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u000eJ\u000e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0012J\b\u0010\u001b\u001a\u00020\u0014H\u0002J\b\u0010\u001c\u001a\u00020\u0014H\u0002J\b\u0010\u001d\u001a\u00020\u0014H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/deye/views/PowerView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/mxchipapp/databinding/ViewPowerBinding;", "humidifierBean", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "mIsEnable", "", "onPowerClickListener", "Lcom/deye/views/PowerView$IOnPowerClickListener;", "init", "", "setAvailable", "available", "setBean", "bean", "setOnPowerClickListener", "listener", "updatePowerViewHk", "updateViewToPowerOff", "updateViewToPowerOn", "IOnPowerClickListener", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class PowerView extends LinearLayout {
    private ViewPowerBinding binding;
    private Context context;
    private DehumidifierBean humidifierBean;
    private boolean mIsEnable;
    private IOnPowerClickListener onPowerClickListener;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PowerView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mIsEnable = true;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PowerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mIsEnable = true;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PowerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mIsEnable = true;
        init(context);
    }

    private final void init(final Context context) {
        ViewPowerBinding viewPowerBindingInflate = ViewPowerBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(viewPowerBindingInflate, "inflate(...)");
        this.binding = viewPowerBindingInflate;
        this.context = context;
        if (viewPowerBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            viewPowerBindingInflate = null;
        }
        viewPowerBindingInflate.ivPower.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.PowerView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PowerView.init$lambda$0(this.f$0, context, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(final PowerView this$0, Context context, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        if (this$0.mIsEnable) {
            DehumidifierBean dehumidifierBean = this$0.humidifierBean;
            boolean zAreEqual = Intrinsics.areEqual(dehumidifierBean != null ? dehumidifierBean.getHkmodeoperation() : null, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY);
            String string2 = StubApp.getString2(701);
            if (zAreEqual) {
                DehumidifierBean dehumidifierBean2 = this$0.humidifierBean;
                if (dehumidifierBean2 != null) {
                    dehumidifierBean2.setHkmodeoperation(StubApp.getString2(7188));
                }
                DehumidifierBean dehumidifierBean3 = this$0.humidifierBean;
                if (dehumidifierBean3 != null) {
                    dehumidifierBean3.setFan_switch(string2);
                }
                this$0.updateViewToPowerOff();
                IOnPowerClickListener iOnPowerClickListener = this$0.onPowerClickListener;
                if (iOnPowerClickListener != null) {
                    iOnPowerClickListener.onPowerClick(false, string2, true);
                    return;
                }
                return;
            }
            DehumidifierBean dehumidifierBean4 = this$0.humidifierBean;
            if (Intrinsics.areEqual(string2, dehumidifierBean4 != null ? dehumidifierBean4.getSys_switch() : null)) {
                DehumidifierBean dehumidifierBean5 = this$0.humidifierBean;
                if (!Intrinsics.areEqual(dehumidifierBean5 != null ? dehumidifierBean5.getFan_switch() : null, string2)) {
                    DehumidifierBean dehumidifierBean6 = this$0.humidifierBean;
                    if (!Intrinsics.areEqual(dehumidifierBean6 != null ? dehumidifierBean6.L2 : null, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
                        DehumidifierBean dehumidifierBean7 = this$0.humidifierBean;
                        if (!Intrinsics.areEqual(dehumidifierBean7 != null ? dehumidifierBean7.getE3() : null, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
                            DehumidifierBean dehumidifierBean8 = this$0.humidifierBean;
                            if (!Intrinsics.areEqual(dehumidifierBean8 != null ? dehumidifierBean8.getP7() : null, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
                                DehumidifierBean dehumidifierBean9 = this$0.humidifierBean;
                                if (dehumidifierBean9 != null) {
                                    dehumidifierBean9.setFan_switch(string2);
                                }
                                this$0.updateViewToPowerOff();
                                IOnPowerClickListener iOnPowerClickListener2 = this$0.onPowerClickListener;
                                if (iOnPowerClickListener2 != null) {
                                    IOnPowerClickListener.DefaultImpls.onPowerClick$default(iOnPowerClickListener2, false, StubApp.getString2(701), false, 4, null);
                                    return;
                                }
                                return;
                            }
                        }
                    }
                }
                this$0.updateViewToPowerOn();
                IOnPowerClickListener iOnPowerClickListener3 = this$0.onPowerClickListener;
                if (iOnPowerClickListener3 != null) {
                    IOnPowerClickListener.DefaultImpls.onPowerClick$default(iOnPowerClickListener3, true, null, false, 4, null);
                    return;
                }
                return;
            }
            DialogHelper.showDeleteDialog((FragmentActivity) context, this$0.getResources().getString(R.string.power_off_dialog_tip_text), context.getString(R.string.sure_text), new DialogHelper.OnDialogListener() { // from class: com.deye.views.PowerView$init$1$1
                @Override // com.deye.helper.DialogHelper.OnDialogListener
                public void onSure(String text) {
                    this.this$0.updateViewToPowerOff();
                    PowerView.IOnPowerClickListener iOnPowerClickListener4 = this.this$0.onPowerClickListener;
                    if (iOnPowerClickListener4 != null) {
                        DehumidifierBean dehumidifierBean10 = this.this$0.humidifierBean;
                        PowerView.IOnPowerClickListener.DefaultImpls.onPowerClick$default(iOnPowerClickListener4, false, dehumidifierBean10 != null ? dehumidifierBean10.getFan_switch() : null, false, 4, null);
                    }
                }
            });
        }
    }

    public final void setBean(DehumidifierBean bean) {
        Intrinsics.checkNotNullParameter(bean, "bean");
        this.humidifierBean = bean;
        String fan_switch = bean != null ? bean.getFan_switch() : null;
        DehumidifierBean dehumidifierBean = this.humidifierBean;
        Log.e(StubApp.getString2(14633), StubApp.getString2(14632) + fan_switch + StubApp.getString2(13867) + (dehumidifierBean != null ? dehumidifierBean.getSys_switch() : null));
        if (Intrinsics.areEqual(bean.getHkmodeoperation(), AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
            updatePowerViewHk();
            return;
        }
        DehumidifierBean dehumidifierBean2 = this.humidifierBean;
        if (Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, dehumidifierBean2 != null ? dehumidifierBean2.getSys_switch() : null)) {
            updateViewToPowerOn();
        } else {
            updateViewToPowerOff();
        }
    }

    private final void updateViewToPowerOn() {
        ViewPowerBinding viewPowerBinding = this.binding;
        String string2 = StubApp.getString2(13474);
        Context context = null;
        if (viewPowerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewPowerBinding = null;
        }
        TextView textView = viewPowerBinding.tvPower;
        Context context2 = this.context;
        String string22 = StubApp.getString2(14628);
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            context2 = null;
        }
        textView.setText(context2.getString(R.string.turned_on));
        ViewPowerBinding viewPowerBinding2 = this.binding;
        if (viewPowerBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewPowerBinding2 = null;
        }
        viewPowerBinding2.ivPower.setPadding(0, 0, 0, 0);
        ViewPowerBinding viewPowerBinding3 = this.binding;
        if (viewPowerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewPowerBinding3 = null;
        }
        ImageView imageView = viewPowerBinding3.ivPower;
        Context context3 = this.context;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
        } else {
            context = context3;
        }
        imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_power_on));
    }

    private final void updatePowerViewHk() {
        ViewPowerBinding viewPowerBinding = this.binding;
        String string2 = StubApp.getString2(13474);
        Context context = null;
        if (viewPowerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewPowerBinding = null;
        }
        viewPowerBinding.ivPower.setPadding(0, SignUtils.dp2px(10), 0, 0);
        ViewPowerBinding viewPowerBinding2 = this.binding;
        if (viewPowerBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewPowerBinding2 = null;
        }
        TextView textView = viewPowerBinding2.tvPower;
        Context context2 = this.context;
        String string22 = StubApp.getString2(14628);
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            context2 = null;
        }
        textView.setText(context2.getString(R.string.butler_mode_shutdown));
        ViewPowerBinding viewPowerBinding3 = this.binding;
        if (viewPowerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewPowerBinding3 = null;
        }
        ImageView imageView = viewPowerBinding3.ivPower;
        Context context3 = this.context;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
        } else {
            context = context3;
        }
        imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_poweroff_now));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateViewToPowerOff() {
        DehumidifierBean dehumidifierBean = this.humidifierBean;
        Context context = null;
        boolean zAreEqual = Intrinsics.areEqual(dehumidifierBean != null ? dehumidifierBean.getFan_switch() : null, AmapLocationNetwork.RESULT_TYPE_GPS);
        String string2 = StubApp.getString2(14628);
        String string22 = StubApp.getString2(13474);
        if (!zAreEqual) {
            DehumidifierBean dehumidifierBean2 = this.humidifierBean;
            if (!Intrinsics.areEqual(dehumidifierBean2 != null ? dehumidifierBean2.L2 : null, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
                DehumidifierBean dehumidifierBean3 = this.humidifierBean;
                if (!Intrinsics.areEqual(dehumidifierBean3 != null ? dehumidifierBean3.getE3() : null, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
                    ViewPowerBinding viewPowerBinding = this.binding;
                    if (viewPowerBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string22);
                        viewPowerBinding = null;
                    }
                    viewPowerBinding.ivPower.setPadding(0, SignUtils.dp2px(10), 0, 0);
                    ViewPowerBinding viewPowerBinding2 = this.binding;
                    if (viewPowerBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string22);
                        viewPowerBinding2 = null;
                    }
                    TextView textView = viewPowerBinding2.tvPower;
                    Context context2 = this.context;
                    if (context2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                        context2 = null;
                    }
                    textView.setText(context2.getString(R.string.wet_curtain_drying));
                    ViewPowerBinding viewPowerBinding3 = this.binding;
                    if (viewPowerBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string22);
                        viewPowerBinding3 = null;
                    }
                    ImageView imageView = viewPowerBinding3.ivPower;
                    Context context3 = this.context;
                    if (context3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                    } else {
                        context = context3;
                    }
                    imageView.setImageDrawable(ContextCompat.getDrawable(context, LanUtils.isEnLanguage() ? R.drawable.icon_poweroff_now_en : R.drawable.icon_poweroff_now));
                    return;
                }
            }
        }
        ViewPowerBinding viewPowerBinding4 = this.binding;
        if (viewPowerBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            viewPowerBinding4 = null;
        }
        viewPowerBinding4.ivPower.setPadding(0, 0, 0, 0);
        ViewPowerBinding viewPowerBinding5 = this.binding;
        if (viewPowerBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            viewPowerBinding5 = null;
        }
        TextView textView2 = viewPowerBinding5.tvPower;
        Context context4 = this.context;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            context4 = null;
        }
        textView2.setText(context4.getString(R.string.turned_off));
        ViewPowerBinding viewPowerBinding6 = this.binding;
        if (viewPowerBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            viewPowerBinding6 = null;
        }
        ImageView imageView2 = viewPowerBinding6.ivPower;
        Context context5 = this.context;
        if (context5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            context = context5;
        }
        imageView2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_power_off));
    }

    public final void setAvailable(boolean available) {
        setAlpha(available ? 1.0f : 0.4f);
        this.mIsEnable = available;
    }

    /* compiled from: PowerView.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005H&¨\u0006\t"}, d2 = {"Lcom/deye/views/PowerView$IOnPowerClickListener;", "", "onPowerClick", "", "open", "", "fanStatus", "", "isHk", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface IOnPowerClickListener {
        void onPowerClick(boolean open, String fanStatus, boolean isHk);

        /* compiled from: PowerView.kt */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ void onPowerClick$default(IOnPowerClickListener iOnPowerClickListener, boolean z, String str, boolean z2, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException(StubApp.getString2(14631));
                }
                if ((i & 4) != 0) {
                    z2 = false;
                }
                iOnPowerClickListener.onPowerClick(z, str, z2);
            }
        }
    }

    public final void setOnPowerClickListener(IOnPowerClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onPowerClickListener = listener;
    }
}
