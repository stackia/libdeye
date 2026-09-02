package com.deye.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.deye.configs.Constants;
import com.deye.views.ArcSeekBar;
import com.mxchipapp.databinding.ArcHumidifierPannelBinding;
import com.stub.StubApp;
import com.zhouyou.view.seekbar.SignUtils;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.log.LogDebug;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HumidifierArcPanel.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0018\u0018\u00002\u00020\u0001:\u00014B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0002J\u000e\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\tJ0\u0010\"\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\t2\u0006\u0010'\u001a\u00020\tH\u0014J\u000e\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u0012J\u0010\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020\u0010H\u0016J\u000e\u0010,\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020\u0014J\u000e\u0010.\u001a\u00020\u001d2\u0006\u0010/\u001a\u00020\u0010J\u000e\u00100\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u00101\u001a\u00020\u001dH\u0002J\u0010\u00102\u001a\u00020\u001d2\u0006\u00103\u001a\u00020\tH\u0002R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/deye/views/HumidifierArcPanel;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "binding", "Lcom/mxchipapp/databinding/ArcHumidifierPannelBinding;", "canDrag", "", "mDehumidifierBean", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "mListener", "Lcom/deye/views/HumidifierArcPanel$OnTemperatureChangeListener;", "max", "min", "preCheckDrag", "Ljava/lang/Boolean;", "productId", "", "step", "init", "", "initClick", "initSeekBar", "onHumChange", "humidity", "onLayout", "changed", "left", "top", "right", "bottom", "setData", "dehumidifierBean", "setEnabled", "enabled", "setHumidityChangeListener", "listener", "setPreCheckDrag", "preCheck", "setProductId", "updateByMode", "updatePorgressView", "progress", "OnTemperatureChangeListener", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class HumidifierArcPanel extends FrameLayout {
    private ArcHumidifierPannelBinding binding;
    private boolean canDrag;
    private DehumidifierBean mDehumidifierBean;
    private OnTemperatureChangeListener mListener;
    private int max;
    private int min;
    private Boolean preCheckDrag;
    private String productId;
    private int step;

    /* compiled from: HumidifierArcPanel.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/deye/views/HumidifierArcPanel$OnTemperatureChangeListener;", "", "onChange", "", "temperature", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface OnTemperatureChangeListener {
        void onChange(int temperature);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HumidifierArcPanel(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.step = 5;
        this.min = 30;
        this.max = 90;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HumidifierArcPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.step = 5;
        this.min = 30;
        this.max = 90;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HumidifierArcPanel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.step = 5;
        this.min = 30;
        this.max = 90;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HumidifierArcPanel(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this.step = 5;
        this.min = 30;
        this.max = 90;
        init(context);
    }

    private final void init(Context context) {
        setWillNotDraw(false);
        ArcHumidifierPannelBinding arcHumidifierPannelBindingInflate = ArcHumidifierPannelBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(arcHumidifierPannelBindingInflate, "inflate(...)");
        this.binding = arcHumidifierPannelBindingInflate;
        initSeekBar();
        initClick();
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        ArcHumidifierPannelBinding arcHumidifierPannelBinding = this.binding;
        if (arcHumidifierPannelBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            arcHumidifierPannelBinding = null;
        }
        arcHumidifierPannelBinding.arcSeekBar.forbidTouch(enabled);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void setData(DehumidifierBean dehumidifierBean) throws NumberFormatException {
        boolean z;
        String mode;
        Intrinsics.checkNotNullParameter(dehumidifierBean, "dehumidifierBean");
        this.mDehumidifierBean = dehumidifierBean;
        Boolean bool = this.preCheckDrag;
        String string2 = StubApp.getString2(2546);
        if (bool != null) {
            Intrinsics.checkNotNull(bool);
            this.canDrag = bool.booleanValue();
        } else if (Intrinsics.areEqual(dehumidifierBean.getHkmodeoperation(), string2) || !dehumidifierBean.checkIsPowerOn()) {
            z = false;
            this.canDrag = z;
        } else {
            String mode2 = dehumidifierBean.getMode();
            z = true;
            if ((mode2 == null || !mode2.equals(StubApp.getString2(7778))) && ((mode = dehumidifierBean.getMode()) == null || !mode.equals(string2))) {
            }
            this.canDrag = z;
        }
        if (Intrinsics.areEqual(dehumidifierBean.getE2(), string2) || Intrinsics.areEqual(dehumidifierBean.L2, string2) || Intrinsics.areEqual(dehumidifierBean.getE3(), string2) || Intrinsics.areEqual(dehumidifierBean.getE5(), string2)) {
            this.canDrag = false;
        }
        updateByMode();
    }

    public final void setPreCheckDrag(boolean preCheck) {
        this.preCheckDrag = Boolean.valueOf(preCheck);
    }

    public final void setProductId(String productId) {
        Intrinsics.checkNotNullParameter(productId, "productId");
        this.productId = productId;
    }

    private final void initClick() {
        ArcHumidifierPannelBinding arcHumidifierPannelBinding = this.binding;
        ArcHumidifierPannelBinding arcHumidifierPannelBinding2 = null;
        String string2 = StubApp.getString2(13474);
        if (arcHumidifierPannelBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcHumidifierPannelBinding = null;
        }
        arcHumidifierPannelBinding.btnLeft.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.HumidifierArcPanel$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HumidifierArcPanel.initClick$lambda$0(this.f$0, view);
            }
        });
        ArcHumidifierPannelBinding arcHumidifierPannelBinding3 = this.binding;
        if (arcHumidifierPannelBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            arcHumidifierPannelBinding2 = arcHumidifierPannelBinding3;
        }
        arcHumidifierPannelBinding2.btnRight.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.HumidifierArcPanel$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HumidifierArcPanel.initClick$lambda$1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initClick$lambda$0(HumidifierArcPanel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isEnabled()) {
            ArcHumidifierPannelBinding arcHumidifierPannelBinding = this$0.binding;
            ArcHumidifierPannelBinding arcHumidifierPannelBinding2 = null;
            String string2 = StubApp.getString2(13474);
            if (arcHumidifierPannelBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                arcHumidifierPannelBinding = null;
            }
            int progress = arcHumidifierPannelBinding.arcSeekBar.getProgress() - this$0.step;
            int i = this$0.min;
            if (progress < i) {
                progress = i;
            }
            ArcHumidifierPannelBinding arcHumidifierPannelBinding3 = this$0.binding;
            if (arcHumidifierPannelBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                arcHumidifierPannelBinding2 = arcHumidifierPannelBinding3;
            }
            arcHumidifierPannelBinding2.arcSeekBar.setProgress(progress);
            this$0.onHumChange(progress);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initClick$lambda$1(HumidifierArcPanel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isEnabled()) {
            ArcHumidifierPannelBinding arcHumidifierPannelBinding = this$0.binding;
            ArcHumidifierPannelBinding arcHumidifierPannelBinding2 = null;
            String string2 = StubApp.getString2(13474);
            if (arcHumidifierPannelBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                arcHumidifierPannelBinding = null;
            }
            int progress = arcHumidifierPannelBinding.arcSeekBar.getProgress() + this$0.step;
            int i = this$0.max;
            if (progress > i) {
                progress = i;
            }
            ArcHumidifierPannelBinding arcHumidifierPannelBinding3 = this$0.binding;
            if (arcHumidifierPannelBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                arcHumidifierPannelBinding2 = arcHumidifierPannelBinding3;
            }
            arcHumidifierPannelBinding2.arcSeekBar.setProgress(progress);
            this$0.onHumChange(progress);
        }
    }

    private final void initSeekBar() {
        ArcHumidifierPannelBinding arcHumidifierPannelBinding = this.binding;
        ArcHumidifierPannelBinding arcHumidifierPannelBinding2 = null;
        String string2 = StubApp.getString2(13474);
        if (arcHumidifierPannelBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcHumidifierPannelBinding = null;
        }
        arcHumidifierPannelBinding.arcSeekBar.setMaxValue(this.max);
        ArcHumidifierPannelBinding arcHumidifierPannelBinding3 = this.binding;
        if (arcHumidifierPannelBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcHumidifierPannelBinding3 = null;
        }
        arcHumidifierPannelBinding3.arcSeekBar.setMinValue(this.min);
        ArcHumidifierPannelBinding arcHumidifierPannelBinding4 = this.binding;
        if (arcHumidifierPannelBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcHumidifierPannelBinding4 = null;
        }
        arcHumidifierPannelBinding4.arcSeekBar.setStep(5);
        ArcHumidifierPannelBinding arcHumidifierPannelBinding5 = this.binding;
        if (arcHumidifierPannelBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            arcHumidifierPannelBinding2 = arcHumidifierPannelBinding5;
        }
        arcHumidifierPannelBinding2.arcSeekBar.setOnProgressChangeListener(new ArcSeekBar.OnProgressChangeListener() { // from class: com.deye.views.HumidifierArcPanel.initSeekBar.1
            @Override // com.deye.views.ArcSeekBar.OnProgressChangeListener
            public void onStartTrackingTouch(ArcSeekBar seekBar) {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
            }

            @Override // com.deye.views.ArcSeekBar.OnProgressChangeListener
            public void onProgressChanged(ArcSeekBar seekBar, int progress, boolean isUser) {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                HumidifierArcPanel.this.updatePorgressView(seekBar.getProgress());
            }

            @Override // com.deye.views.ArcSeekBar.OnProgressChangeListener
            public void onStopTrackingTouch(ArcSeekBar seekBar) {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                HumidifierArcPanel.this.updatePorgressView(seekBar.getProgress());
                if (HumidifierArcPanel.this.canDrag) {
                    HumidifierArcPanel.this.onHumChange(seekBar.getProgress());
                }
            }
        });
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updatePorgressView(int progress) {
        ArcHumidifierPannelBinding arcHumidifierPannelBinding = this.binding;
        if (arcHumidifierPannelBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            arcHumidifierPannelBinding = null;
        }
        arcHumidifierPannelBinding.txtProgress.setValueText(String.valueOf(progress));
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x0175  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void updateByMode() throws NumberFormatException {
        DehumidifierBean dehumidifierBean;
        String mode;
        ArcHumidifierPannelBinding arcHumidifierPannelBinding;
        String mode2;
        String mode3;
        DehumidifierBean dehumidifierBean2;
        ArcHumidifierPannelBinding arcHumidifierPannelBinding2;
        Integer numValueOf;
        String current_env_hum;
        LogDebug logDebug = LogDebug.INSTANCE;
        DehumidifierBean dehumidifierBean3 = this.mDehumidifierBean;
        String mode4 = dehumidifierBean3 != null ? dehumidifierBean3.getMode() : null;
        DehumidifierBean dehumidifierBean4 = this.mDehumidifierBean;
        logDebug.log(StubApp.getString2(14518) + mode4 + StubApp.getString2(14519) + (dehumidifierBean4 != null ? Boolean.valueOf(dehumidifierBean4.checkIsPowerOn()) : null) + StubApp.getString2(626));
        DehumidifierBean dehumidifierBean5 = this.mDehumidifierBean;
        boolean z = dehumidifierBean5 != null && dehumidifierBean5.checkIsPowerOn();
        ArcHumidifierPannelBinding arcHumidifierPannelBinding3 = this.binding;
        String string2 = StubApp.getString2(13474);
        if (arcHumidifierPannelBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcHumidifierPannelBinding3 = null;
        }
        arcHumidifierPannelBinding3.txtProgress.setVisibility(z ? 0 : 8);
        ArcHumidifierPannelBinding arcHumidifierPannelBinding4 = this.binding;
        if (arcHumidifierPannelBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcHumidifierPannelBinding4 = null;
        }
        arcHumidifierPannelBinding4.llSettingHum.setVisibility(z ? 0 : 8);
        DehumidifierBean dehumidifierBean6 = this.mDehumidifierBean;
        String string22 = StubApp.getString2(2546);
        if (dehumidifierBean6 != null && (current_env_hum = dehumidifierBean6.getCurrent_env_hum()) != null) {
            int i = Integer.parseInt(current_env_hum);
            DehumidifierBean dehumidifierBean7 = this.mDehumidifierBean;
            boolean zAreEqual = Intrinsics.areEqual(dehumidifierBean7 != null ? dehumidifierBean7.getHkmodeoperation() : null, string22);
            String string23 = StubApp.getString2(13225);
            if (zAreEqual) {
                ArcHumidifierPannelBinding arcHumidifierPannelBinding5 = this.binding;
                if (arcHumidifierPannelBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    arcHumidifierPannelBinding5 = null;
                }
                arcHumidifierPannelBinding5.tvDesc.setText(StubApp.getString2(14612));
                ArcHumidifierPannelBinding arcHumidifierPannelBinding6 = this.binding;
                if (arcHumidifierPannelBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    arcHumidifierPannelBinding6 = null;
                }
                arcHumidifierPannelBinding6.tvDesc.setTextSize(22.0f);
                ArcHumidifierPannelBinding arcHumidifierPannelBinding7 = this.binding;
                if (arcHumidifierPannelBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    arcHumidifierPannelBinding7 = null;
                }
                arcHumidifierPannelBinding7.tvDesc.setTextColor(Color.parseColor(string23));
            } else {
                String string24 = StubApp.getString2(14524);
                String string25 = StubApp.getString2(14523);
                if (z) {
                    ArcHumidifierPannelBinding arcHumidifierPannelBinding8 = this.binding;
                    if (arcHumidifierPannelBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                        arcHumidifierPannelBinding8 = null;
                    }
                    arcHumidifierPannelBinding8.tvDesc.setTextSize(32.0f);
                    String string26 = StubApp.getString2(14614);
                    if (i > 65) {
                        ArcHumidifierPannelBinding arcHumidifierPannelBinding9 = this.binding;
                        if (arcHumidifierPannelBinding9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            arcHumidifierPannelBinding9 = null;
                        }
                        arcHumidifierPannelBinding9.tvDesc.setText(z ? StubApp.getString2(14615) : string26);
                        ArcHumidifierPannelBinding arcHumidifierPannelBinding10 = this.binding;
                        if (arcHumidifierPannelBinding10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            arcHumidifierPannelBinding10 = null;
                        }
                        arcHumidifierPannelBinding10.tvDesc.setTextColor(Color.parseColor(string23));
                    } else if (51 <= i && i < 66) {
                        ArcHumidifierPannelBinding arcHumidifierPannelBinding11 = this.binding;
                        if (arcHumidifierPannelBinding11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            arcHumidifierPannelBinding11 = null;
                        }
                        arcHumidifierPannelBinding11.tvDesc.setText(z ? StubApp.getString2(14616) : string26);
                        ArcHumidifierPannelBinding arcHumidifierPannelBinding12 = this.binding;
                        if (arcHumidifierPannelBinding12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            arcHumidifierPannelBinding12 = null;
                        }
                        arcHumidifierPannelBinding12.tvDesc.setTextColor(Color.parseColor(string24));
                    } else {
                        ArcHumidifierPannelBinding arcHumidifierPannelBinding13 = this.binding;
                        if (arcHumidifierPannelBinding13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            arcHumidifierPannelBinding13 = null;
                        }
                        arcHumidifierPannelBinding13.tvDesc.setTextColor(Color.parseColor(string25));
                        ArcHumidifierPannelBinding arcHumidifierPannelBinding14 = this.binding;
                        if (arcHumidifierPannelBinding14 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            arcHumidifierPannelBinding14 = null;
                        }
                        arcHumidifierPannelBinding14.tvDesc.setText(z ? StubApp.getString2(14617) : string26);
                    }
                } else {
                    DehumidifierBean dehumidifierBean8 = this.mDehumidifierBean;
                    if (Intrinsics.areEqual(dehumidifierBean8 != null ? dehumidifierBean8.getFan_switch() : null, string22)) {
                        ArcHumidifierPannelBinding arcHumidifierPannelBinding15 = this.binding;
                        if (arcHumidifierPannelBinding15 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            arcHumidifierPannelBinding15 = null;
                        }
                        arcHumidifierPannelBinding15.tvDesc.setText(StubApp.getString2(14613));
                        ArcHumidifierPannelBinding arcHumidifierPannelBinding16 = this.binding;
                        if (arcHumidifierPannelBinding16 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            arcHumidifierPannelBinding16 = null;
                        }
                        arcHumidifierPannelBinding16.tvDesc.setTextSize(22.0f);
                        if (i > 65) {
                            ArcHumidifierPannelBinding arcHumidifierPannelBinding17 = this.binding;
                            if (arcHumidifierPannelBinding17 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string2);
                                arcHumidifierPannelBinding17 = null;
                            }
                            arcHumidifierPannelBinding17.tvDesc.setTextColor(Color.parseColor(string23));
                        } else if (51 <= i && i < 66) {
                            ArcHumidifierPannelBinding arcHumidifierPannelBinding18 = this.binding;
                            if (arcHumidifierPannelBinding18 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string2);
                                arcHumidifierPannelBinding18 = null;
                            }
                            arcHumidifierPannelBinding18.tvDesc.setTextColor(Color.parseColor(string24));
                        } else {
                            ArcHumidifierPannelBinding arcHumidifierPannelBinding19 = this.binding;
                            if (arcHumidifierPannelBinding19 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string2);
                                arcHumidifierPannelBinding19 = null;
                            }
                            arcHumidifierPannelBinding19.tvDesc.setTextColor(Color.parseColor(string25));
                        }
                    }
                }
            }
        }
        ArcHumidifierPannelBinding arcHumidifierPannelBinding20 = this.binding;
        if (arcHumidifierPannelBinding20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcHumidifierPannelBinding20 = null;
        }
        arcHumidifierPannelBinding20.arcSeekBar.changeArcStyle(-1, this.canDrag);
        ArcHumidifierPannelBinding arcHumidifierPannelBinding21 = this.binding;
        if (arcHumidifierPannelBinding21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcHumidifierPannelBinding21 = null;
        }
        arcHumidifierPannelBinding21.btnLeft.setVisibility(this.canDrag ? 0 : 8);
        ArcHumidifierPannelBinding arcHumidifierPannelBinding22 = this.binding;
        if (arcHumidifierPannelBinding22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcHumidifierPannelBinding22 = null;
        }
        arcHumidifierPannelBinding22.btnRight.setVisibility(this.canDrag ? 0 : 8);
        ArcHumidifierPannelBinding arcHumidifierPannelBinding23 = this.binding;
        if (arcHumidifierPannelBinding23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcHumidifierPannelBinding23 = null;
        }
        arcHumidifierPannelBinding23.tvMin.setVisibility(this.canDrag ? 0 : 8);
        ArcHumidifierPannelBinding arcHumidifierPannelBinding24 = this.binding;
        if (arcHumidifierPannelBinding24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcHumidifierPannelBinding24 = null;
        }
        arcHumidifierPannelBinding24.tvMax.setVisibility(this.canDrag ? 0 : 8);
        ArcHumidifierPannelBinding arcHumidifierPannelBinding25 = this.binding;
        if (arcHumidifierPannelBinding25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcHumidifierPannelBinding25 = null;
        }
        CustomFontTextUnitView customFontTextUnitView = arcHumidifierPannelBinding25.txtProgress;
        String string27 = StubApp.getString2(5130);
        customFontTextUnitView.setUnitText(string27);
        ArcHumidifierPannelBinding arcHumidifierPannelBinding26 = this.binding;
        if (arcHumidifierPannelBinding26 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcHumidifierPannelBinding26 = null;
        }
        CustomFontTextUnitView customFontTextUnitView2 = arcHumidifierPannelBinding26.txtProgress;
        DehumidifierBean dehumidifierBean9 = this.mDehumidifierBean;
        String hum_set = dehumidifierBean9 != null ? dehumidifierBean9.getHum_set() : null;
        if (hum_set == null) {
            hum_set = "";
        }
        customFontTextUnitView2.setValueText(hum_set);
        ArcHumidifierPannelBinding arcHumidifierPannelBinding27 = this.binding;
        if (arcHumidifierPannelBinding27 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcHumidifierPannelBinding27 = null;
        }
        arcHumidifierPannelBinding27.txtProgress.setValueTextSpSize(30);
        DehumidifierBean dehumidifierBean10 = this.mDehumidifierBean;
        if (dehumidifierBean10 != null) {
            ArcHumidifierPannelBinding arcHumidifierPannelBinding28 = this.binding;
            if (arcHumidifierPannelBinding28 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                arcHumidifierPannelBinding28 = null;
            }
            CustomFontTextUnitView customFontTextUnitView3 = arcHumidifierPannelBinding28.tvIndoorTemp;
            String current_env_temp = dehumidifierBean10.getCurrent_env_temp();
            if (current_env_temp != null) {
                Intrinsics.checkNotNull(current_env_temp);
                numValueOf = Integer.valueOf(Integer.parseInt(current_env_temp) - 40);
            } else {
                numValueOf = null;
            }
            customFontTextUnitView3.setValueText(String.valueOf(numValueOf));
            ArcHumidifierPannelBinding arcHumidifierPannelBinding29 = this.binding;
            if (arcHumidifierPannelBinding29 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                arcHumidifierPannelBinding29 = null;
            }
            arcHumidifierPannelBinding29.tvIndoorTemp.setUnitText(StubApp.getString2(13800));
            ArcHumidifierPannelBinding arcHumidifierPannelBinding30 = this.binding;
            if (arcHumidifierPannelBinding30 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                arcHumidifierPannelBinding30 = null;
            }
            arcHumidifierPannelBinding30.tvIndoorHumidity.setValueText(dehumidifierBean10.getCurrent_env_hum());
            ArcHumidifierPannelBinding arcHumidifierPannelBinding31 = this.binding;
            if (arcHumidifierPannelBinding31 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                arcHumidifierPannelBinding31 = null;
            }
            arcHumidifierPannelBinding31.tvIndoorHumidity.setUnitText(string27);
            if (dehumidifierBean10.getHum_set() != null) {
                ArcHumidifierPannelBinding arcHumidifierPannelBinding32 = this.binding;
                if (arcHumidifierPannelBinding32 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    arcHumidifierPannelBinding32 = null;
                }
                ArcSeekBar arcSeekBar = arcHumidifierPannelBinding32.arcSeekBar;
                String hum_set2 = dehumidifierBean10.getHum_set();
                Intrinsics.checkNotNullExpressionValue(hum_set2, "getHum_set(...)");
                arcSeekBar.setProgress(Integer.parseInt(hum_set2));
            }
        }
        if (Constants.isH7Product(this.productId)) {
            DehumidifierBean dehumidifierBean11 = this.mDehumidifierBean;
            if (dehumidifierBean11 != null && (mode3 = dehumidifierBean11.getMode()) != null && mode3.equals(StubApp.getString2(7188)) && (dehumidifierBean2 = this.mDehumidifierBean) != null && dehumidifierBean2.checkIsPowerOn()) {
                ArcHumidifierPannelBinding arcHumidifierPannelBinding33 = this.binding;
                if (arcHumidifierPannelBinding33 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    arcHumidifierPannelBinding33 = null;
                }
                arcHumidifierPannelBinding33.llSettingHum.setVisibility(0);
                ArcHumidifierPannelBinding arcHumidifierPannelBinding34 = this.binding;
                if (arcHumidifierPannelBinding34 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    arcHumidifierPannelBinding2 = null;
                } else {
                    arcHumidifierPannelBinding2 = arcHumidifierPannelBinding34;
                }
                arcHumidifierPannelBinding2.tvDesc.setPadding(0, 0, 0, 0);
                return;
            }
            ArcHumidifierPannelBinding arcHumidifierPannelBinding35 = this.binding;
            if (arcHumidifierPannelBinding35 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                arcHumidifierPannelBinding35 = null;
            }
            arcHumidifierPannelBinding35.llSettingHum.setVisibility(8);
            ArcHumidifierPannelBinding arcHumidifierPannelBinding36 = this.binding;
            if (arcHumidifierPannelBinding36 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                arcHumidifierPannelBinding36 = null;
            }
            arcHumidifierPannelBinding36.tvDesc.setPadding(0, SignUtils.dp2px(20), 0, 0);
            return;
        }
        DehumidifierBean dehumidifierBean12 = this.mDehumidifierBean;
        if ((dehumidifierBean12 != null && (mode2 = dehumidifierBean12.getMode()) != null && mode2.equals(StubApp.getString2(7778))) || ((dehumidifierBean = this.mDehumidifierBean) != null && (mode = dehumidifierBean.getMode()) != null && mode.equals(string22))) {
            DehumidifierBean dehumidifierBean13 = this.mDehumidifierBean;
            if (!Intrinsics.areEqual(dehumidifierBean13 != null ? dehumidifierBean13.getHkmodeoperation() : null, string22)) {
                ArcHumidifierPannelBinding arcHumidifierPannelBinding37 = this.binding;
                if (arcHumidifierPannelBinding37 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    arcHumidifierPannelBinding37 = null;
                }
                arcHumidifierPannelBinding37.llSettingHum.setVisibility(0);
                ArcHumidifierPannelBinding arcHumidifierPannelBinding38 = this.binding;
                if (arcHumidifierPannelBinding38 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    arcHumidifierPannelBinding = null;
                } else {
                    arcHumidifierPannelBinding = arcHumidifierPannelBinding38;
                }
                arcHumidifierPannelBinding.tvDesc.setPadding(0, 0, 0, 0);
                return;
            }
        }
        ArcHumidifierPannelBinding arcHumidifierPannelBinding39 = this.binding;
        if (arcHumidifierPannelBinding39 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcHumidifierPannelBinding39 = null;
        }
        arcHumidifierPannelBinding39.llSettingHum.setVisibility(8);
        ArcHumidifierPannelBinding arcHumidifierPannelBinding40 = this.binding;
        if (arcHumidifierPannelBinding40 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcHumidifierPannelBinding40 = null;
        }
        arcHumidifierPannelBinding40.tvDesc.setPadding(0, SignUtils.dp2px(20), 0, 0);
    }

    public final void setHumidityChangeListener(OnTemperatureChangeListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mListener = listener;
    }

    public final void onHumChange(int humidity) {
        OnTemperatureChangeListener onTemperatureChangeListener = this.mListener;
        if (onTemperatureChangeListener != null) {
            onTemperatureChangeListener.onChange(humidity);
        }
    }
}
