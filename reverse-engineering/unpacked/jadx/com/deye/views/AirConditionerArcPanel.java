package com.deye.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.deye.views.ArcSeekBar;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.log.LogDebug;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AirConditionerArcPanel.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0017\u0018\u00002\u00020\u0001:\u0001@B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0012\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010'H\u0002J\u0010\u0010)\u001a\u00020*2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010+\u001a\u00020*H\u0002J\b\u0010,\u001a\u00020*H\u0002J\u000e\u0010-\u001a\u00020*2\u0006\u0010.\u001a\u00020\tJ0\u0010/\u001a\u00020*2\u0006\u00100\u001a\u00020\u00112\u0006\u00101\u001a\u00020\t2\u0006\u00102\u001a\u00020\t2\u0006\u00103\u001a\u00020\t2\u0006\u00104\u001a\u00020\tH\u0014J\u000e\u00105\u001a\u00020*2\u0006\u00106\u001a\u00020\u0017J\u0010\u00107\u001a\u00020*2\u0006\u00108\u001a\u00020\u0011H\u0016J\u000e\u00109\u001a\u00020*2\u0006\u0010:\u001a\u00020\u0019J\u000e\u0010;\u001a\u00020*2\u0006\u0010<\u001a\u00020'J\b\u0010=\u001a\u00020*H\u0002J\u0010\u0010>\u001a\u00020*2\u0006\u0010?\u001a\u00020\tH\u0002R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lcom/deye/views/AirConditionerArcPanel;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "btnLeft", "Landroid/widget/Button;", "btnRight", "canDrag", "", "ivSettingMode", "Landroid/widget/ImageView;", "mArcSeekBar", "Lcom/deye/views/ArcSeekBar;", "mDehumidifierBean", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "mListener", "Lcom/deye/views/AirConditionerArcPanel$OnTemperatureChangeListener;", "mProgressText", "Lcom/deye/views/CustomFontTextUnitView;", "max", "min", "step", "tvMax", "Landroid/widget/TextView;", "tvMin", "tvPowerOff", "tvSetting", "tv_indoor_temp", "tv_outdoor_temp", "getSpeedMsg", "", "speed", "init", "", "initClick", "initSeekBar", "onHumChange", "humidity", "onLayout", "changed", "left", "top", "right", "bottom", "setData", "dehumidifierBean", "setEnabled", "enabled", "setHumidityChangeListener", "listener", "setOutDoorTemp", "temp", "updateByMode", "updatePorgressView", "progress", "OnTemperatureChangeListener", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class AirConditionerArcPanel extends FrameLayout {
    private Button btnLeft;
    private Button btnRight;
    private boolean canDrag;
    private ImageView ivSettingMode;
    private ArcSeekBar mArcSeekBar;
    private DehumidifierBean mDehumidifierBean;
    private OnTemperatureChangeListener mListener;
    private CustomFontTextUnitView mProgressText;
    private int max;
    private int min;
    private int step;
    private TextView tvMax;
    private TextView tvMin;
    private TextView tvPowerOff;
    private TextView tvSetting;
    private CustomFontTextUnitView tv_indoor_temp;
    private CustomFontTextUnitView tv_outdoor_temp;

    /* compiled from: AirConditionerArcPanel.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/deye/views/AirConditionerArcPanel$OnTemperatureChangeListener;", "", "onChange", "", "temperature", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface OnTemperatureChangeListener {
        void onChange(int temperature);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AirConditionerArcPanel(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.step = 1;
        this.min = 16;
        this.max = 32;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AirConditionerArcPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.step = 1;
        this.min = 16;
        this.max = 32;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AirConditionerArcPanel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.step = 1;
        this.min = 16;
        this.max = 32;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AirConditionerArcPanel(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this.step = 1;
        this.min = 16;
        this.max = 32;
        init(context);
    }

    private final void init(Context context) {
        setWillNotDraw(false);
        LayoutInflater.from(context).inflate(R.layout.arc_air_conditioner_pannel, (ViewGroup) this, true);
        View viewFindViewById = findViewById(R.id.txt_progress);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.mProgressText = (CustomFontTextUnitView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.tv_indoor_temp);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.tv_indoor_temp = (CustomFontTextUnitView) viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.tv_outdoor_temp);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        this.tv_outdoor_temp = (CustomFontTextUnitView) viewFindViewById3;
        View viewFindViewById4 = findViewById(R.id.tv_max);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
        this.tvMax = (TextView) viewFindViewById4;
        View viewFindViewById5 = findViewById(R.id.tv_min);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
        this.tvMin = (TextView) viewFindViewById5;
        View viewFindViewById6 = findViewById(R.id.tv_power_off);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(...)");
        this.tvPowerOff = (TextView) viewFindViewById6;
        View viewFindViewById7 = findViewById(R.id.btn_left);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(...)");
        this.btnLeft = (Button) viewFindViewById7;
        View viewFindViewById8 = findViewById(R.id.btn_right);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "findViewById(...)");
        this.btnRight = (Button) viewFindViewById8;
        View viewFindViewById9 = findViewById(R.id.iv_setting_mode);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById9, "findViewById(...)");
        this.ivSettingMode = (ImageView) viewFindViewById9;
        View viewFindViewById10 = findViewById(R.id.tv_setting);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById10, "findViewById(...)");
        this.tvSetting = (TextView) viewFindViewById10;
        initSeekBar();
        ArcSeekBar arcSeekBar = this.mArcSeekBar;
        ArcSeekBar arcSeekBar2 = null;
        String string2 = StubApp.getString2(14517);
        if (arcSeekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcSeekBar = null;
        }
        arcSeekBar.setMaxValue(this.max);
        ArcSeekBar arcSeekBar3 = this.mArcSeekBar;
        if (arcSeekBar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcSeekBar3 = null;
        }
        arcSeekBar3.setMinValue(this.min);
        ArcSeekBar arcSeekBar4 = this.mArcSeekBar;
        if (arcSeekBar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            arcSeekBar2 = arcSeekBar4;
        }
        arcSeekBar2.setStep(1);
        initClick();
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        ArcSeekBar arcSeekBar = this.mArcSeekBar;
        if (arcSeekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mArcSeekBar");
            arcSeekBar = null;
        }
        arcSeekBar.forbidTouch(enabled);
    }

    private final void initClick() {
        Button button = this.btnLeft;
        Button button2 = null;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnLeft");
            button = null;
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.AirConditionerArcPanel$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AirConditionerArcPanel.initClick$lambda$0(this.f$0, view);
            }
        });
        Button button3 = this.btnRight;
        if (button3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnRight");
        } else {
            button2 = button3;
        }
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.AirConditionerArcPanel$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AirConditionerArcPanel.initClick$lambda$1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initClick$lambda$0(AirConditionerArcPanel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isEnabled()) {
            ArcSeekBar arcSeekBar = this$0.mArcSeekBar;
            ArcSeekBar arcSeekBar2 = null;
            String string2 = StubApp.getString2(14517);
            if (arcSeekBar == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                arcSeekBar = null;
            }
            int progress = arcSeekBar.getProgress() - this$0.step;
            int i = this$0.min;
            if (progress < i) {
                progress = i;
            }
            ArcSeekBar arcSeekBar3 = this$0.mArcSeekBar;
            if (arcSeekBar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                arcSeekBar2 = arcSeekBar3;
            }
            arcSeekBar2.setProgress(progress);
            this$0.onHumChange(progress);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initClick$lambda$1(AirConditionerArcPanel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isEnabled()) {
            ArcSeekBar arcSeekBar = this$0.mArcSeekBar;
            ArcSeekBar arcSeekBar2 = null;
            String string2 = StubApp.getString2(14517);
            if (arcSeekBar == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                arcSeekBar = null;
            }
            int progress = arcSeekBar.getProgress() + this$0.step;
            int i = this$0.max;
            if (progress > i) {
                progress = i;
            }
            ArcSeekBar arcSeekBar3 = this$0.mArcSeekBar;
            if (arcSeekBar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                arcSeekBar2 = arcSeekBar3;
            }
            arcSeekBar2.setProgress(progress);
            this$0.onHumChange(progress);
        }
    }

    private final void initSeekBar() {
        View viewFindViewById = findViewById(R.id.arc_seek_bar);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        ArcSeekBar arcSeekBar = (ArcSeekBar) viewFindViewById;
        this.mArcSeekBar = arcSeekBar;
        ArcSeekBar arcSeekBar2 = null;
        String string2 = StubApp.getString2(14517);
        if (arcSeekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcSeekBar = null;
        }
        arcSeekBar.setMaxValue(this.max);
        ArcSeekBar arcSeekBar3 = this.mArcSeekBar;
        if (arcSeekBar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcSeekBar3 = null;
        }
        arcSeekBar3.setMinValue(this.min);
        ArcSeekBar arcSeekBar4 = this.mArcSeekBar;
        if (arcSeekBar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            arcSeekBar2 = arcSeekBar4;
        }
        arcSeekBar2.setOnProgressChangeListener(new ArcSeekBar.OnProgressChangeListener() { // from class: com.deye.views.AirConditionerArcPanel.initSeekBar.1
            @Override // com.deye.views.ArcSeekBar.OnProgressChangeListener
            public void onStartTrackingTouch(ArcSeekBar seekBar) {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
            }

            @Override // com.deye.views.ArcSeekBar.OnProgressChangeListener
            public void onProgressChanged(ArcSeekBar seekBar, int progress, boolean isUser) {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                AirConditionerArcPanel.this.updatePorgressView(seekBar.getProgress());
            }

            @Override // com.deye.views.ArcSeekBar.OnProgressChangeListener
            public void onStopTrackingTouch(ArcSeekBar seekBar) {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                AirConditionerArcPanel.this.updatePorgressView(seekBar.getProgress());
                if (AirConditionerArcPanel.this.canDrag) {
                    AirConditionerArcPanel.this.onHumChange(seekBar.getProgress());
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
        String mode;
        DehumidifierBean dehumidifierBean = this.mDehumidifierBean;
        if (dehumidifierBean == null || (mode = dehumidifierBean.getMode()) == null || !mode.equals(StubApp.getString2(7188))) {
            CustomFontTextUnitView customFontTextUnitView = this.mProgressText;
            if (customFontTextUnitView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mProgressText");
                customFontTextUnitView = null;
            }
            customFontTextUnitView.setValueText(String.valueOf(progress));
        }
    }

    public final void setData(DehumidifierBean dehumidifierBean) {
        String mode;
        Intrinsics.checkNotNullParameter(dehumidifierBean, "dehumidifierBean");
        this.mDehumidifierBean = dehumidifierBean;
        this.canDrag = (!dehumidifierBean.checkIsPowerOn() || (mode = dehumidifierBean.getMode()) == null || mode.equals(StubApp.getString2(7188))) ? false : true;
        updateByMode();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:78:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0170  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void updateByMode() {
        int i;
        Integer numValueOf;
        String mode;
        String mode2;
        ImageView imageView;
        CustomFontTextUnitView customFontTextUnitView;
        LogDebug logDebug = LogDebug.INSTANCE;
        DehumidifierBean dehumidifierBean = this.mDehumidifierBean;
        CustomFontTextUnitView customFontTextUnitView2 = null;
        String mode3 = dehumidifierBean != null ? dehumidifierBean.getMode() : null;
        DehumidifierBean dehumidifierBean2 = this.mDehumidifierBean;
        logDebug.log(StubApp.getString2(14518) + mode3 + StubApp.getString2(14519) + (dehumidifierBean2 != null ? Boolean.valueOf(dehumidifierBean2.checkIsPowerOn()) : null) + StubApp.getString2(626));
        DehumidifierBean dehumidifierBean3 = this.mDehumidifierBean;
        boolean z = dehumidifierBean3 != null && dehumidifierBean3.checkIsPowerOn();
        TextView textView = this.tvPowerOff;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvPowerOff");
            textView = null;
        }
        textView.setVisibility(z ? 8 : 0);
        ImageView imageView2 = this.ivSettingMode;
        String string2 = StubApp.getString2(14520);
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            imageView2 = null;
        }
        imageView2.setVisibility(z ? 0 : 8);
        CustomFontTextUnitView customFontTextUnitView3 = this.mProgressText;
        String string22 = StubApp.getString2(14521);
        if (customFontTextUnitView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            customFontTextUnitView3 = null;
        }
        customFontTextUnitView3.setVisibility(z ? 0 : 8);
        TextView textView2 = this.tvSetting;
        String string23 = StubApp.getString2(14522);
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string23);
            textView2 = null;
        }
        textView2.setVisibility(z ? 0 : 8);
        DehumidifierBean dehumidifierBean4 = this.mDehumidifierBean;
        String string24 = StubApp.getString2(2546);
        if (dehumidifierBean4 != null && (mode2 = dehumidifierBean4.getMode()) != null) {
            int iHashCode = mode2.hashCode();
            String string25 = StubApp.getString2(13225);
            switch (iHashCode) {
                case 48:
                    if (mode2.equals(StubApp.getString2(701))) {
                        ImageView imageView3 = this.ivSettingMode;
                        if (imageView3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            imageView3 = null;
                        }
                        imageView3.setImageResource(R.drawable.icon_panel_cold);
                        CustomFontTextUnitView customFontTextUnitView4 = this.mProgressText;
                        if (customFontTextUnitView4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string22);
                            customFontTextUnitView4 = null;
                        }
                        customFontTextUnitView4.setTextColor(Color.parseColor(string25));
                        i = 0;
                        break;
                    }
                    imageView = this.ivSettingMode;
                    if (imageView == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string2);
                        imageView = null;
                    }
                    imageView.setImageResource(R.drawable.icon_panel_blast);
                    customFontTextUnitView = this.mProgressText;
                    if (customFontTextUnitView == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(string22);
                        customFontTextUnitView = null;
                    }
                    customFontTextUnitView.setTextColor(Color.parseColor(StubApp.getString2(14524)));
                    i = 3;
                    break;
                case 49:
                    if (mode2.equals(string24)) {
                        ImageView imageView4 = this.ivSettingMode;
                        if (imageView4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            imageView4 = null;
                        }
                        imageView4.setImageResource(R.drawable.icon_panel_hot);
                        CustomFontTextUnitView customFontTextUnitView5 = this.mProgressText;
                        if (customFontTextUnitView5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string22);
                            customFontTextUnitView5 = null;
                        }
                        customFontTextUnitView5.setTextColor(Color.parseColor(StubApp.getString2(14523)));
                        i = 1;
                        break;
                    } else {
                        imageView = this.ivSettingMode;
                        if (imageView == null) {
                        }
                        imageView.setImageResource(R.drawable.icon_panel_blast);
                        customFontTextUnitView = this.mProgressText;
                        if (customFontTextUnitView == null) {
                        }
                        customFontTextUnitView.setTextColor(Color.parseColor(StubApp.getString2(14524)));
                        i = 3;
                        break;
                    }
                    break;
                case 50:
                    if (mode2.equals(StubApp.getString2(1764))) {
                        ImageView imageView5 = this.ivSettingMode;
                        if (imageView5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            imageView5 = null;
                        }
                        imageView5.setImageResource(R.drawable.icon_panel_dehumidification);
                        CustomFontTextUnitView customFontTextUnitView6 = this.mProgressText;
                        if (customFontTextUnitView6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string22);
                            customFontTextUnitView6 = null;
                        }
                        customFontTextUnitView6.setTextColor(Color.parseColor(string25));
                        i = 2;
                        break;
                    }
                    break;
            }
        } else {
            i = 0;
        }
        ArcSeekBar arcSeekBar = this.mArcSeekBar;
        String string26 = StubApp.getString2(14517);
        if (arcSeekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string26);
            arcSeekBar = null;
        }
        arcSeekBar.changeArcStyle(i, this.canDrag);
        Button button = this.btnLeft;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnLeft");
            button = null;
        }
        button.setVisibility(this.canDrag ? 0 : 8);
        Button button2 = this.btnRight;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnRight");
            button2 = null;
        }
        button2.setVisibility(this.canDrag ? 0 : 8);
        TextView textView3 = this.tvMin;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvMin");
            textView3 = null;
        }
        textView3.setVisibility(this.canDrag ? 0 : 8);
        TextView textView4 = this.tvMax;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvMax");
            textView4 = null;
        }
        textView4.setVisibility(this.canDrag ? 0 : 8);
        DehumidifierBean dehumidifierBean5 = this.mDehumidifierBean;
        String string27 = StubApp.getString2(13800);
        if (dehumidifierBean5 != null && (mode = dehumidifierBean5.getMode()) != null && mode.equals(StubApp.getString2(7188))) {
            CustomFontTextUnitView customFontTextUnitView7 = this.mProgressText;
            if (customFontTextUnitView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                customFontTextUnitView7 = null;
            }
            customFontTextUnitView7.setUnitText("");
            CustomFontTextUnitView customFontTextUnitView8 = this.mProgressText;
            if (customFontTextUnitView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                customFontTextUnitView8 = null;
            }
            DehumidifierBean dehumidifierBean6 = this.mDehumidifierBean;
            customFontTextUnitView8.setValueText(getSpeedMsg(dehumidifierBean6 != null ? dehumidifierBean6.getSpeed() : null));
            CustomFontTextUnitView customFontTextUnitView9 = this.mProgressText;
            if (customFontTextUnitView9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                customFontTextUnitView9 = null;
            }
            customFontTextUnitView9.setValueTextSpSize(30);
            TextView textView5 = this.tvSetting;
            if (textView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string23);
                textView5 = null;
            }
            textView5.setText(StubApp.getString2(14525));
        } else {
            CustomFontTextUnitView customFontTextUnitView10 = this.mProgressText;
            if (customFontTextUnitView10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                customFontTextUnitView10 = null;
            }
            customFontTextUnitView10.setUnitText(string27);
            CustomFontTextUnitView customFontTextUnitView11 = this.mProgressText;
            if (customFontTextUnitView11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                customFontTextUnitView11 = null;
            }
            DehumidifierBean dehumidifierBean7 = this.mDehumidifierBean;
            String str = dehumidifierBean7 != null ? dehumidifierBean7.temperature_set : null;
            customFontTextUnitView11.setValueText(str != null ? str : "");
            CustomFontTextUnitView customFontTextUnitView12 = this.mProgressText;
            if (customFontTextUnitView12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                customFontTextUnitView12 = null;
            }
            customFontTextUnitView12.setValueTextSpSize(36);
            TextView textView6 = this.tvSetting;
            if (textView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string23);
                textView6 = null;
            }
            textView6.setText(StubApp.getString2(14172));
        }
        DehumidifierBean dehumidifierBean8 = this.mDehumidifierBean;
        String string28 = StubApp.getString2(14526);
        if (dehumidifierBean8 != null) {
            CustomFontTextUnitView customFontTextUnitView13 = this.tv_indoor_temp;
            if (customFontTextUnitView13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string28);
                customFontTextUnitView13 = null;
            }
            String current_env_temp = dehumidifierBean8.getCurrent_env_temp();
            if (current_env_temp != null) {
                Intrinsics.checkNotNull(current_env_temp);
                numValueOf = Integer.valueOf(Integer.parseInt(current_env_temp) - 40);
            } else {
                numValueOf = null;
            }
            customFontTextUnitView13.setValueText(String.valueOf(numValueOf));
            CustomFontTextUnitView customFontTextUnitView14 = this.tv_indoor_temp;
            if (customFontTextUnitView14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string28);
                customFontTextUnitView14 = null;
            }
            customFontTextUnitView14.setUnitText(string27);
            if (dehumidifierBean8.temperature_set != null) {
                ArcSeekBar arcSeekBar2 = this.mArcSeekBar;
                if (arcSeekBar2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string26);
                    arcSeekBar2 = null;
                }
                String temperature_set = dehumidifierBean8.temperature_set;
                Intrinsics.checkNotNullExpressionValue(temperature_set, "temperature_set");
                arcSeekBar2.setProgress(Integer.parseInt(temperature_set));
            }
        }
        DehumidifierBean dehumidifierBean9 = this.mDehumidifierBean;
        boolean zEquals$default = StringsKt.equals$default(dehumidifierBean9 != null ? dehumidifierBean9.getMode() : null, string24, false, 2, (Object) null);
        String string29 = StubApp.getString2(14527);
        if (!zEquals$default) {
            CustomFontTextUnitView customFontTextUnitView15 = this.tv_outdoor_temp;
            if (customFontTextUnitView15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string29);
                customFontTextUnitView15 = null;
            }
            customFontTextUnitView15.setHighlight(false);
            CustomFontTextUnitView customFontTextUnitView16 = this.tv_outdoor_temp;
            if (customFontTextUnitView16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string29);
                customFontTextUnitView16 = null;
            }
            customFontTextUnitView16.setValueTextSpSize(16);
            CustomFontTextUnitView customFontTextUnitView17 = this.tv_outdoor_temp;
            if (customFontTextUnitView17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string29);
                customFontTextUnitView17 = null;
            }
            customFontTextUnitView17.setUnitTextSpSize(8);
            CustomFontTextUnitView customFontTextUnitView18 = this.tv_indoor_temp;
            if (customFontTextUnitView18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string28);
                customFontTextUnitView18 = null;
            }
            customFontTextUnitView18.setHighlight(false);
            CustomFontTextUnitView customFontTextUnitView19 = this.tv_indoor_temp;
            if (customFontTextUnitView19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string28);
                customFontTextUnitView19 = null;
            }
            customFontTextUnitView19.setValueTextSpSize(16);
            CustomFontTextUnitView customFontTextUnitView20 = this.tv_indoor_temp;
            if (customFontTextUnitView20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string28);
            } else {
                customFontTextUnitView2 = customFontTextUnitView20;
            }
            customFontTextUnitView2.setUnitTextSpSize(8);
            return;
        }
        CustomFontTextUnitView customFontTextUnitView21 = this.tv_outdoor_temp;
        if (customFontTextUnitView21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string29);
            customFontTextUnitView21 = null;
        }
        customFontTextUnitView21.setHighlight(true);
        CustomFontTextUnitView customFontTextUnitView22 = this.tv_outdoor_temp;
        if (customFontTextUnitView22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string29);
            customFontTextUnitView22 = null;
        }
        customFontTextUnitView22.setValueTextSpSize(16);
        CustomFontTextUnitView customFontTextUnitView23 = this.tv_outdoor_temp;
        if (customFontTextUnitView23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string29);
            customFontTextUnitView23 = null;
        }
        customFontTextUnitView23.setUnitTextSpSize(8);
        CustomFontTextUnitView customFontTextUnitView24 = this.tv_indoor_temp;
        if (customFontTextUnitView24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string28);
            customFontTextUnitView24 = null;
        }
        customFontTextUnitView24.setHighlight(true);
        CustomFontTextUnitView customFontTextUnitView25 = this.tv_indoor_temp;
        if (customFontTextUnitView25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string28);
            customFontTextUnitView25 = null;
        }
        customFontTextUnitView25.setValueTextSpSize(16);
        CustomFontTextUnitView customFontTextUnitView26 = this.tv_indoor_temp;
        if (customFontTextUnitView26 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string28);
        } else {
            customFontTextUnitView2 = customFontTextUnitView26;
        }
        customFontTextUnitView2.setUnitTextSpSize(8);
    }

    private final String getSpeedMsg(String speed) {
        String str = speed;
        if (str == null || str.length() == 0) {
            return "";
        }
        int iHashCode = speed.hashCode();
        String string2 = StubApp.getString2(14513);
        switch (iHashCode) {
            case 49:
                return !speed.equals(StubApp.getString2(2546)) ? "" : StubApp.getString2(14516);
            case 50:
                if (!speed.equals(StubApp.getString2(1764))) {
                    return "";
                }
                break;
            case 51:
                return !speed.equals(StubApp.getString2(7188)) ? "" : StubApp.getString2(14515);
            case 52:
                if (!speed.equals(StubApp.getString2(7778))) {
                    return "";
                }
                break;
            case 53:
                return speed.equals(StubApp.getString2(8095)) ? StubApp.getString2(14514) : "";
            default:
                return "";
        }
        return string2;
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

    public final void setOutDoorTemp(String temp) {
        Intrinsics.checkNotNullParameter(temp, "temp");
        CustomFontTextUnitView customFontTextUnitView = this.tv_outdoor_temp;
        CustomFontTextUnitView customFontTextUnitView2 = null;
        String string2 = StubApp.getString2(14527);
        if (customFontTextUnitView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            customFontTextUnitView = null;
        }
        customFontTextUnitView.setValueText(temp);
        CustomFontTextUnitView customFontTextUnitView3 = this.tv_outdoor_temp;
        if (customFontTextUnitView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            customFontTextUnitView2 = customFontTextUnitView3;
        }
        customFontTextUnitView2.setUnitText(StubApp.getString2(13800));
    }
}
