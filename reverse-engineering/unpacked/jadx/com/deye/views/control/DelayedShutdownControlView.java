package com.deye.views.control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.deye.views.button.SwitchButton;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ViewDelayedShutdownControlBinding;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DelayedShutdownControlView.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001&B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0010J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0010H\u0016J\u001a\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u0007H\u0002J\u0010\u0010\u0019\u001a\u00020\u00122\b\u0010\u001a\u001a\u0004\u0018\u00010\fJ\u0010\u0010\u001b\u001a\u00020\u00122\b\u0010\u001a\u001a\u0004\u0018\u00010\u000eJ\u0018\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0007H\u0002J\u000e\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0010J\b\u0010 \u001a\u00020\u0012H\u0002J\u0010\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u0010H\u0002J\u0010\u0010#\u001a\u00020\u00122\b\u0010$\u001a\u0004\u0018\u00010%R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/deye/views/control/DelayedShutdownControlView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/mxchipapp/databinding/ViewDelayedShutdownControlBinding;", "onSwitchChangeListener", "Lcom/deye/views/control/DelayedShutdownControlView$OnSwitchChangeListener;", "onTimeClickListener", "Landroid/view/View$OnClickListener;", "isChecked", "", "setChecked", "", "checked", "setEnabled", "enabled", "setHours", "hours", "minutes", "setOnSwitchChangeListener", "listener", "setOnTimeClickListener", "setRemainingTime", "hour", "minute", "setSwitchEnabled", "setupListeners", "showTimeDisplay", "show", "updateStatus", "dehumidifierBean", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "OnSwitchChangeListener", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DelayedShutdownControlView extends LinearLayout {
    private final ViewDelayedShutdownControlBinding binding;
    private OnSwitchChangeListener onSwitchChangeListener;
    private View.OnClickListener onTimeClickListener;

    /* compiled from: DelayedShutdownControlView.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/deye/views/control/DelayedShutdownControlView$OnSwitchChangeListener;", "", "onSwitchChanged", "", "isChecked", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface OnSwitchChangeListener {
        void onSwitchChanged(boolean isChecked);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DelayedShutdownControlView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DelayedShutdownControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ DelayedShutdownControlView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DelayedShutdownControlView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_delayed_shutdown_control, this, true);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        ViewDelayedShutdownControlBinding viewDelayedShutdownControlBinding = (ViewDelayedShutdownControlBinding) viewDataBindingInflate;
        this.binding = viewDelayedShutdownControlBinding;
        viewDelayedShutdownControlBinding.switchButton.setRespondNow(false);
        setupListeners();
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.binding.switchButton.setEnabled(enabled);
    }

    private final void setupListeners() {
        this.binding.switchButton.setOnClick(new SwitchButton.OnClick() { // from class: com.deye.views.control.DelayedShutdownControlView$$ExternalSyntheticLambda0
            @Override // com.deye.views.button.SwitchButton.OnClick
            public final void onClick(View view, boolean z) {
                DelayedShutdownControlView.setupListeners$lambda$0(this.f$0, view, z);
            }
        });
        this.binding.rlTimeDisplay.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.control.DelayedShutdownControlView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DelayedShutdownControlView.setupListeners$lambda$1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupListeners$lambda$0(DelayedShutdownControlView this$0, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnSwitchChangeListener onSwitchChangeListener = this$0.onSwitchChangeListener;
        if (onSwitchChangeListener != null) {
            onSwitchChangeListener.onSwitchChanged(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupListeners$lambda$1(DelayedShutdownControlView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View.OnClickListener onClickListener = this$0.onTimeClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public final void setChecked(boolean checked) {
        this.binding.switchButton.setChecked(checked);
    }

    public final boolean isChecked() {
        return this.binding.switchButton.isChecked();
    }

    public final void setSwitchEnabled(boolean enabled) {
        this.binding.switchButton.setEnabled(enabled);
    }

    public final void setOnSwitchChangeListener(OnSwitchChangeListener listener) {
        this.onSwitchChangeListener = listener;
    }

    public final void setOnTimeClickListener(View.OnClickListener listener) {
        this.onTimeClickListener = listener;
    }

    private final void showTimeDisplay(boolean show) {
        this.binding.rlTimeDisplay.setVisibility(show ? 0 : 8);
    }

    static /* synthetic */ void setHours$default(DelayedShutdownControlView delayedShutdownControlView, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        delayedShutdownControlView.setHours(i, i2);
    }

    private final void setHours(int hours, int minutes) {
        if (hours > 0 || minutes > 0) {
            String string = getContext().getString(R.string.delayed_shutdown_hours_format, Integer.valueOf(hours));
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            this.binding.tvRemainingTime.setText(string);
            showTimeDisplay(true);
        } else {
            showTimeDisplay(false);
        }
        this.binding.tvRemainingTime.setTextColor(getResources().getColor(R.color.dark80));
    }

    private final void setRemainingTime(int hour, int minute) {
        String string;
        if (hour > 0 || minute > 0) {
            showTimeDisplay(true);
        } else {
            showTimeDisplay(false);
        }
        if (hour > 0) {
            string = getContext().getString(R.string.delayed_shutdown_remaining, Integer.valueOf(hour), Integer.valueOf(minute));
        } else {
            string = getContext().getString(R.string.delayed_shutdown_remaining_minutes_only, Integer.valueOf(minute));
        }
        Intrinsics.checkNotNull(string);
        this.binding.tvRemainingTime.setText(string);
        this.binding.tvRemainingTime.setTextColor(getResources().getColor(R.color.color_dark));
    }

    public final void updateStatus(DehumidifierBean dehumidifierBean) {
        String poweroff_remain_minute;
        Integer intOrNull;
        String poweroff_remain_hour;
        Integer intOrNull2;
        String poweroff_hour;
        Integer intOrNull3;
        int iIntValue = 0;
        boolean z = ((dehumidifierBean == null || (poweroff_hour = dehumidifierBean.getPoweroff_hour()) == null || (intOrNull3 = StringsKt.toIntOrNull(poweroff_hour)) == null) ? 0 : intOrNull3.intValue()) > 0;
        setChecked(z);
        if (z) {
            int iIntValue2 = (dehumidifierBean == null || (poweroff_remain_hour = dehumidifierBean.getPoweroff_remain_hour()) == null || (intOrNull2 = StringsKt.toIntOrNull(poweroff_remain_hour)) == null) ? 0 : intOrNull2.intValue();
            if (dehumidifierBean != null && (poweroff_remain_minute = dehumidifierBean.getPoweroff_remain_minute()) != null && (intOrNull = StringsKt.toIntOrNull(poweroff_remain_minute)) != null) {
                iIntValue = intOrNull.intValue();
            }
            setRemainingTime(iIntValue2, iIntValue);
            this.binding.rlTimeDisplay.setAlpha(1.0f);
            return;
        }
        showTimeDisplay(false);
    }
}
