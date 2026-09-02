package com.deye.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.deye.configs.Constants;
import com.mxchipapp.databinding.ViewAppointmentModeBinding;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppointmentModeView.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001aB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\tJ\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0010R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/deye/views/AppointmentModeView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/mxchipapp/databinding/ViewAppointmentModeBinding;", "curSelectView", "Lcom/deye/views/AppointmentModeItemView;", "mProductId", "", "modeCheckListener", "Lcom/deye/views/AppointmentModeView$IModeCheckListener;", "init", "", "setCurSelectMode", "mode", "setModeCheckListener", "setProductId", "productId", "IModeCheckListener", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class AppointmentModeView extends LinearLayout {
    private ViewAppointmentModeBinding binding;
    private AppointmentModeItemView curSelectView;
    private String mProductId;
    private IModeCheckListener modeCheckListener;

    /* compiled from: AppointmentModeView.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/deye/views/AppointmentModeView$IModeCheckListener;", "", "onModeCheck", "", "mode", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface IModeCheckListener {
        void onModeCheck(int mode);
    }

    public final void setModeCheckListener(IModeCheckListener modeCheckListener) {
        Intrinsics.checkNotNullParameter(modeCheckListener, "modeCheckListener");
        this.modeCheckListener = modeCheckListener;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppointmentModeView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppointmentModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppointmentModeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    private final void init(Context context) {
        ViewAppointmentModeBinding viewAppointmentModeBindingInflate = ViewAppointmentModeBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(viewAppointmentModeBindingInflate, "inflate(...)");
        this.binding = viewAppointmentModeBindingInflate;
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.deye.views.AppointmentModeView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AppointmentModeView.init$lambda$0(this.f$0, view);
            }
        };
        ViewAppointmentModeBinding viewAppointmentModeBinding = this.binding;
        ViewAppointmentModeBinding viewAppointmentModeBinding2 = null;
        String string2 = StubApp.getString2(13474);
        if (viewAppointmentModeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding = null;
        }
        viewAppointmentModeBinding.itemViewAutomatic.setOnClickListener(onClickListener);
        ViewAppointmentModeBinding viewAppointmentModeBinding3 = this.binding;
        if (viewAppointmentModeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding3 = null;
        }
        viewAppointmentModeBinding3.itemViewAutomatic.setMode(3);
        ViewAppointmentModeBinding viewAppointmentModeBinding4 = this.binding;
        if (viewAppointmentModeBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding4 = null;
        }
        viewAppointmentModeBinding4.itemViewManual.setOnClickListener(onClickListener);
        ViewAppointmentModeBinding viewAppointmentModeBinding5 = this.binding;
        if (viewAppointmentModeBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding5 = null;
        }
        viewAppointmentModeBinding5.itemViewManual.setMode(4);
        ViewAppointmentModeBinding viewAppointmentModeBinding6 = this.binding;
        if (viewAppointmentModeBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding6 = null;
        }
        viewAppointmentModeBinding6.itemViewGentle.setOnClickListener(onClickListener);
        ViewAppointmentModeBinding viewAppointmentModeBinding7 = this.binding;
        if (viewAppointmentModeBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding7 = null;
        }
        viewAppointmentModeBinding7.itemViewGentle.setMode(2);
        ViewAppointmentModeBinding viewAppointmentModeBinding8 = this.binding;
        if (viewAppointmentModeBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding8 = null;
        }
        viewAppointmentModeBinding8.itemViewSleep.setOnClickListener(onClickListener);
        ViewAppointmentModeBinding viewAppointmentModeBinding9 = this.binding;
        if (viewAppointmentModeBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding9 = null;
        }
        viewAppointmentModeBinding9.itemViewSleep.setMode(1);
        ViewAppointmentModeBinding viewAppointmentModeBinding10 = this.binding;
        if (viewAppointmentModeBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding10 = null;
        }
        viewAppointmentModeBinding10.itemViewStrong.setOnClickListener(onClickListener);
        ViewAppointmentModeBinding viewAppointmentModeBinding11 = this.binding;
        if (viewAppointmentModeBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding11 = null;
        }
        viewAppointmentModeBinding11.itemViewStrong.setMode(2);
        ViewAppointmentModeBinding viewAppointmentModeBinding12 = this.binding;
        if (viewAppointmentModeBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding12 = null;
        }
        viewAppointmentModeBinding12.itemViewConstants.setOnClickListener(onClickListener);
        ViewAppointmentModeBinding viewAppointmentModeBinding13 = this.binding;
        if (viewAppointmentModeBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding13 = null;
        }
        viewAppointmentModeBinding13.itemViewConstants.setMode(3);
        ViewAppointmentModeBinding viewAppointmentModeBinding14 = this.binding;
        if (viewAppointmentModeBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding14 = null;
        }
        viewAppointmentModeBinding14.itemViewDry.setOnClickListener(onClickListener);
        ViewAppointmentModeBinding viewAppointmentModeBinding15 = this.binding;
        if (viewAppointmentModeBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            viewAppointmentModeBinding2 = viewAppointmentModeBinding15;
        }
        viewAppointmentModeBinding2.itemViewDry.setMode(5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(AppointmentModeView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppointmentModeItemView appointmentModeItemView = this$0.curSelectView;
        if (view == appointmentModeItemView) {
            return;
        }
        if (appointmentModeItemView != null) {
            Intrinsics.checkNotNull(appointmentModeItemView);
            appointmentModeItemView.setSelected(false);
        }
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type com.deye.views.AppointmentModeItemView");
        AppointmentModeItemView appointmentModeItemView2 = (AppointmentModeItemView) view;
        this$0.curSelectView = appointmentModeItemView2;
        Intrinsics.checkNotNull(appointmentModeItemView2);
        appointmentModeItemView2.setSelected(true);
        IModeCheckListener iModeCheckListener = this$0.modeCheckListener;
        if (iModeCheckListener != null) {
            Intrinsics.checkNotNull(iModeCheckListener);
            AppointmentModeItemView appointmentModeItemView3 = this$0.curSelectView;
            Intrinsics.checkNotNull(appointmentModeItemView3);
            iModeCheckListener.onModeCheck(appointmentModeItemView3.getMode());
        }
    }

    public final void setCurSelectMode(int mode) {
        String str = this.mProductId;
        ViewAppointmentModeBinding viewAppointmentModeBinding = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProductId");
            str = null;
        }
        boolean zIsH7Product = Constants.isH7Product(str);
        String string2 = StubApp.getString2(13474);
        if (!zIsH7Product) {
            ViewAppointmentModeBinding viewAppointmentModeBinding2 = this.binding;
            if (viewAppointmentModeBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                viewAppointmentModeBinding2 = null;
            }
            viewAppointmentModeBinding2.itemViewSleep.setSelected(mode == 1);
            ViewAppointmentModeBinding viewAppointmentModeBinding3 = this.binding;
            if (viewAppointmentModeBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                viewAppointmentModeBinding3 = null;
            }
            viewAppointmentModeBinding3.itemViewGentle.setSelected(mode == 2);
            ViewAppointmentModeBinding viewAppointmentModeBinding4 = this.binding;
            if (viewAppointmentModeBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                viewAppointmentModeBinding4 = null;
            }
            viewAppointmentModeBinding4.itemViewAutomatic.setSelected(mode == 3);
            ViewAppointmentModeBinding viewAppointmentModeBinding5 = this.binding;
            if (viewAppointmentModeBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                viewAppointmentModeBinding = viewAppointmentModeBinding5;
            }
            viewAppointmentModeBinding.itemViewManual.setSelected(mode == 4);
            return;
        }
        ViewAppointmentModeBinding viewAppointmentModeBinding6 = this.binding;
        if (viewAppointmentModeBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding6 = null;
        }
        viewAppointmentModeBinding6.itemViewStrong.setSelected(mode == 2);
        ViewAppointmentModeBinding viewAppointmentModeBinding7 = this.binding;
        if (viewAppointmentModeBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding7 = null;
        }
        viewAppointmentModeBinding7.itemViewConstants.setSelected(mode == 3);
        ViewAppointmentModeBinding viewAppointmentModeBinding8 = this.binding;
        if (viewAppointmentModeBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding8 = null;
        }
        viewAppointmentModeBinding8.itemViewSleep.setSelected(mode == 1);
        ViewAppointmentModeBinding viewAppointmentModeBinding9 = this.binding;
        if (viewAppointmentModeBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            viewAppointmentModeBinding = viewAppointmentModeBinding9;
        }
        viewAppointmentModeBinding.itemViewDry.setSelected(mode == 5);
    }

    public final void setProductId(String productId) {
        Intrinsics.checkNotNullParameter(productId, "productId");
        this.mProductId = productId;
        boolean zIsH7Product = Constants.isH7Product(productId);
        ViewAppointmentModeBinding viewAppointmentModeBinding = null;
        String string2 = StubApp.getString2(13474);
        if (zIsH7Product) {
            ViewAppointmentModeBinding viewAppointmentModeBinding2 = this.binding;
            if (viewAppointmentModeBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                viewAppointmentModeBinding2 = null;
            }
            viewAppointmentModeBinding2.itemViewAutomatic.setVisibility(8);
            ViewAppointmentModeBinding viewAppointmentModeBinding3 = this.binding;
            if (viewAppointmentModeBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                viewAppointmentModeBinding3 = null;
            }
            viewAppointmentModeBinding3.itemViewManual.setVisibility(8);
            ViewAppointmentModeBinding viewAppointmentModeBinding4 = this.binding;
            if (viewAppointmentModeBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                viewAppointmentModeBinding = viewAppointmentModeBinding4;
            }
            viewAppointmentModeBinding.itemViewGentle.setVisibility(8);
            return;
        }
        ViewAppointmentModeBinding viewAppointmentModeBinding5 = this.binding;
        if (viewAppointmentModeBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding5 = null;
        }
        viewAppointmentModeBinding5.itemViewStrong.setVisibility(8);
        ViewAppointmentModeBinding viewAppointmentModeBinding6 = this.binding;
        if (viewAppointmentModeBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            viewAppointmentModeBinding6 = null;
        }
        viewAppointmentModeBinding6.itemViewConstants.setVisibility(8);
        ViewAppointmentModeBinding viewAppointmentModeBinding7 = this.binding;
        if (viewAppointmentModeBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            viewAppointmentModeBinding = viewAppointmentModeBinding7;
        }
        viewAppointmentModeBinding.itemViewDry.setVisibility(8);
    }
}
