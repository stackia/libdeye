package com.deye.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.deye.entity.control_panel.dehumidifier.func.HumidityBean;
import com.deye.utils.ChannelUtil;
import com.deye.utils.TemperatureUtil;
import com.deye.views.ArcSeekBar;
import com.mxchipapp.R;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.bean.LoopFanBean;
import io.fogcloud.sdk.fog.log.LogDebug;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ArcPanel.kt */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001UB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0010\u00106\u001a\u0002072\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u00108\u001a\u000207H\u0002J\b\u00109\u001a\u000207H\u0002J\u000e\u0010:\u001a\u0002072\u0006\u0010;\u001a\u00020\tJ0\u0010<\u001a\u0002072\u0006\u0010=\u001a\u00020\u00122\u0006\u0010>\u001a\u00020\t2\u0006\u0010?\u001a\u00020\t2\u0006\u0010@\u001a\u00020\t2\u0006\u0010A\u001a\u00020\tH\u0014J\u000e\u0010B\u001a\u0002072\u0006\u0010C\u001a\u00020\tJ\u0016\u0010D\u001a\u0002072\u0006\u0010*\u001a\u00020\t2\u0006\u0010(\u001a\u00020\tJ\u0016\u0010E\u001a\u0002072\u0006\u0010F\u001a\u00020!2\u0006\u0010\u0013\u001a\u00020\u0012J\u000e\u0010E\u001a\u0002072\u0006\u0010G\u001a\u00020%J\u0010\u0010H\u001a\u0002072\u0006\u0010I\u001a\u00020\u0012H\u0016J\u000e\u0010J\u001a\u0002072\u0006\u0010K\u001a\u00020#J\u000e\u0010L\u001a\u0002072\u0006\u0010M\u001a\u00020NJ\u000e\u0010O\u001a\u0002072\u0006\u0010P\u001a\u00020\u0012J\b\u0010Q\u001a\u000207H\u0002J\u0010\u0010R\u001a\u0002072\u0006\u0010S\u001a\u00020\tH\u0002J\b\u0010T\u001a\u000207H\u0002R\u000e\u0010\r\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010,\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0004\n\u0002\u0010-R\u000e\u0010.\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000200X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000200X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000200X\u0082.¢\u0006\u0002\n\u0000¨\u0006V"}, d2 = {"Lcom/deye/views/ArcPanel;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "CO", "btnLeft", "Landroid/widget/Button;", "btnRight", "canDrag", "", "isContinue", "isPurification", "()Z", "setPurification", "(Z)V", "ivCo", "Landroid/widget/ImageView;", "ivQuestion", "llSetting", "Landroid/widget/LinearLayout;", "ll_temp_hum", "mArcSeekBar", "Lcom/deye/views/ArcSeekBar;", "mDehumidifierBean", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "mListener", "Lcom/deye/views/ArcPanel$OnHumidityChangeListener;", "mLoopFanBean", "Lio/fogcloud/sdk/fog/bean/LoopFanBean;", "mProgressText", "Lcom/deye/views/CustomFontTextUnitView;", "max", "maxComfortHum", "min", "minComfortHum", "preCheckDrag", "Ljava/lang/Boolean;", "step", "tvDesc", "Landroid/widget/TextView;", "tvMax", "tvMin", "tv_indoor_hum", "tv_indoor_temp", "tv_purification_msg", "init", "", "initClick", "initSeekBar", "onHumChange", "humidity", "onLayout", "changed", "left", "top", "right", "bottom", "setCo", "co", "setComfortHum", "setData", "dehumidifierBean", "loopFanBean", "setEnabled", "enabled", "setHumidityChangeListener", "listener", "setMinMax", "humidityBean", "Lcom/deye/entity/control_panel/dehumidifier/func/HumidityBean;", "setPreCheckDrag", "preCheck", "updateByMode", "updatePorgressView", "progress", "updateView", "OnHumidityChangeListener", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class ArcPanel extends FrameLayout {
    private int CO;
    private Button btnLeft;
    private Button btnRight;
    private boolean canDrag;
    private boolean isContinue;
    private boolean isPurification;
    private ImageView ivCo;
    private ImageView ivQuestion;
    private LinearLayout llSetting;
    private LinearLayout ll_temp_hum;
    private ArcSeekBar mArcSeekBar;
    private DehumidifierBean mDehumidifierBean;
    private OnHumidityChangeListener mListener;
    private LoopFanBean mLoopFanBean;
    private CustomFontTextUnitView mProgressText;
    private int max;
    private int maxComfortHum;
    private int min;
    private int minComfortHum;
    private Boolean preCheckDrag;
    private int step;
    private TextView tvDesc;
    private TextView tvMax;
    private TextView tvMin;
    private CustomFontTextUnitView tv_indoor_hum;
    private CustomFontTextUnitView tv_indoor_temp;
    private TextView tv_purification_msg;

    /* compiled from: ArcPanel.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/deye/views/ArcPanel$OnHumidityChangeListener;", "", "onChange", "", "humidity", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface OnHumidityChangeListener {
        void onChange(int humidity);
    }

    /* renamed from: isPurification, reason: from getter */
    public final boolean getIsPurification() {
        return this.isPurification;
    }

    public final void setPurification(boolean z) {
        this.isPurification = z;
    }

    public final void setPreCheckDrag(boolean preCheck) {
        this.preCheckDrag = Boolean.valueOf(preCheck);
        DehumidifierBean dehumidifierBean = this.mDehumidifierBean;
        if (dehumidifierBean != null) {
            setData(dehumidifierBean, this.isContinue);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArcPanel(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.CO = 25;
        this.step = 1;
        this.min = 30;
        this.max = 80;
        this.minComfortHum = 51;
        this.maxComfortHum = 65;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArcPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.CO = 25;
        this.step = 1;
        this.min = 30;
        this.max = 80;
        this.minComfortHum = 51;
        this.maxComfortHum = 65;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArcPanel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.CO = 25;
        this.step = 1;
        this.min = 30;
        this.max = 80;
        this.minComfortHum = 51;
        this.maxComfortHum = 65;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArcPanel(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this.CO = 25;
        this.step = 1;
        this.min = 30;
        this.max = 80;
        this.minComfortHum = 51;
        this.maxComfortHum = 65;
        init(context);
    }

    private final void init(Context context) {
        setWillNotDraw(false);
        LayoutInflater.from(context).inflate(R.layout.arc_pannel, (ViewGroup) this, true);
        View viewFindViewById = findViewById(R.id.txt_progress);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.mProgressText = (CustomFontTextUnitView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.tv_desc);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.tvDesc = (TextView) viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.tv_indoor_temp);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        this.tv_indoor_temp = (CustomFontTextUnitView) viewFindViewById3;
        View viewFindViewById4 = findViewById(R.id.tv_indoor_hum);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
        this.tv_indoor_hum = (CustomFontTextUnitView) viewFindViewById4;
        View viewFindViewById5 = findViewById(R.id.ll_setting);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
        this.llSetting = (LinearLayout) viewFindViewById5;
        View viewFindViewById6 = findViewById(R.id.tv_max);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(...)");
        this.tvMax = (TextView) viewFindViewById6;
        View viewFindViewById7 = findViewById(R.id.tv_min);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(...)");
        this.tvMin = (TextView) viewFindViewById7;
        View viewFindViewById8 = findViewById(R.id.iv_co);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "findViewById(...)");
        this.ivCo = (ImageView) viewFindViewById8;
        View viewFindViewById9 = findViewById(R.id.btn_left);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById9, "findViewById(...)");
        this.btnLeft = (Button) viewFindViewById9;
        View viewFindViewById10 = findViewById(R.id.btn_right);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById10, "findViewById(...)");
        this.btnRight = (Button) viewFindViewById10;
        View viewFindViewById11 = findViewById(R.id.iv_question);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById11, "findViewById(...)");
        this.ivQuestion = (ImageView) viewFindViewById11;
        View viewFindViewById12 = findViewById(R.id.ll_temp_hum);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById12, "findViewById(...)");
        this.ll_temp_hum = (LinearLayout) viewFindViewById12;
        View viewFindViewById13 = findViewById(R.id.tv_purification_msg);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById13, "findViewById(...)");
        this.tv_purification_msg = (TextView) viewFindViewById13;
        initSeekBar();
        initClick();
        TextView textView = this.tvDesc;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvDesc");
            textView = null;
        }
        textView.setTextSize(ChannelUtil.isOversea() ? 28.0f : 32.0f);
    }

    public final void setComfortHum(int min, int max) {
        this.minComfortHum = min;
        this.maxComfortHum = max;
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
        ImageView imageView = null;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnLeft");
            button = null;
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.ArcPanel$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ArcPanel.initClick$lambda$1(this.f$0, view);
            }
        });
        Button button2 = this.btnRight;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnRight");
            button2 = null;
        }
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.ArcPanel$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ArcPanel.initClick$lambda$2(this.f$0, view);
            }
        });
        ImageView imageView2 = this.ivQuestion;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivQuestion");
        } else {
            imageView = imageView2;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.ArcPanel$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ArcPanel.initClick$lambda$3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initClick$lambda$1(ArcPanel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isEnabled()) {
            ArcSeekBar arcSeekBar = this$0.mArcSeekBar;
            String string2 = StubApp.getString2(14517);
            ArcSeekBar arcSeekBar2 = null;
            if (arcSeekBar == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                arcSeekBar = null;
            }
            int progress = arcSeekBar.getProgress();
            if (progress == this$0.CO) {
                return;
            }
            DehumidifierBean dehumidifierBean = this$0.mDehumidifierBean;
            if (Intrinsics.areEqual(dehumidifierBean != null ? dehumidifierBean.getHum_set() : null, String.valueOf(this$0.CO))) {
                return;
            }
            int i = progress - this$0.step;
            int i2 = this$0.min;
            if (i < i2) {
                i = i2;
            }
            ArcSeekBar arcSeekBar3 = this$0.mArcSeekBar;
            if (arcSeekBar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                arcSeekBar2 = arcSeekBar3;
            }
            arcSeekBar2.setProgress(i);
            this$0.onHumChange(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initClick$lambda$2(ArcPanel this$0, View view) {
        int progress;
        String hum_set;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isEnabled()) {
            ArcSeekBar arcSeekBar = this$0.mArcSeekBar;
            ArcSeekBar arcSeekBar2 = null;
            String string2 = StubApp.getString2(14517);
            if (arcSeekBar == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                arcSeekBar = null;
            }
            arcSeekBar.getProgress();
            DehumidifierBean dehumidifierBean = this$0.mDehumidifierBean;
            if (dehumidifierBean != null && (hum_set = dehumidifierBean.getHum_set()) != null && Integer.parseInt(hum_set) == this$0.CO) {
                progress = this$0.min;
            } else {
                ArcSeekBar arcSeekBar3 = this$0.mArcSeekBar;
                if (arcSeekBar3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    arcSeekBar3 = null;
                }
                progress = arcSeekBar3.getProgress() + this$0.step;
            }
            int i = this$0.max;
            if (progress > i) {
                progress = i;
            }
            ArcSeekBar arcSeekBar4 = this$0.mArcSeekBar;
            if (arcSeekBar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                arcSeekBar2 = arcSeekBar4;
            }
            arcSeekBar2.setProgress(progress);
            this$0.onHumChange(progress);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initClick$lambda$3(ArcPanel this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PopupWindow popupWindow = new PopupWindow();
        popupWindow.setWidth(-1);
        popupWindow.setHeight(-2);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        ImageView imageView = null;
        popupWindow.setContentView(LayoutInflater.from(this$0.getContext()).inflate(R.layout.co_popupwindow, (ViewGroup) null, false));
        ImageView imageView2 = this$0.ivCo;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivCo");
        } else {
            imageView = imageView2;
        }
        popupWindow.showAsDropDown(imageView, 0, 0);
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
        arcSeekBar2.setOnProgressChangeListener(new ArcSeekBar.OnProgressChangeListener() { // from class: com.deye.views.ArcPanel.initSeekBar.1
            @Override // com.deye.views.ArcSeekBar.OnProgressChangeListener
            public void onStartTrackingTouch(ArcSeekBar seekBar) {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
            }

            @Override // com.deye.views.ArcSeekBar.OnProgressChangeListener
            public void onProgressChanged(ArcSeekBar seekBar, int progress, boolean isUser) {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                if (progress % ArcPanel.this.step == 0) {
                    ArcPanel.this.updatePorgressView(progress);
                }
            }

            @Override // com.deye.views.ArcSeekBar.OnProgressChangeListener
            public void onStopTrackingTouch(ArcSeekBar seekBar) {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                ArcPanel.this.updatePorgressView(seekBar.getProgress());
                if (ArcPanel.this.canDrag) {
                    ArcPanel.this.onHumChange(seekBar.getProgress());
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
        CustomFontTextUnitView customFontTextUnitView = this.mProgressText;
        if (customFontTextUnitView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgressText");
            customFontTextUnitView = null;
        }
        customFontTextUnitView.setValueText(String.valueOf(progress));
    }

    public final void setData(DehumidifierBean dehumidifierBean, boolean isContinue) {
        Intrinsics.checkNotNullParameter(dehumidifierBean, "dehumidifierBean");
        this.mDehumidifierBean = dehumidifierBean;
        this.isContinue = isContinue;
        Boolean bool = this.preCheckDrag;
        if (bool != null) {
            Intrinsics.checkNotNull(bool);
            this.canDrag = bool.booleanValue();
        } else {
            this.canDrag = dehumidifierBean.checkIsPowerOn() && dehumidifierBean.checkIsDehumidifyMode();
        }
        updateByMode();
    }

    public final void setData(LoopFanBean loopFanBean) {
        Intrinsics.checkNotNullParameter(loopFanBean, "loopFanBean");
        this.mLoopFanBean = loopFanBean;
        int i = 0;
        this.canDrag = false;
        ArcSeekBar arcSeekBar = this.mArcSeekBar;
        CustomFontTextUnitView customFontTextUnitView = null;
        if (arcSeekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mArcSeekBar");
            arcSeekBar = null;
        }
        arcSeekBar.changeArcStyle(-1, false);
        Button button = this.btnLeft;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnLeft");
            button = null;
        }
        button.setVisibility(8);
        Button button2 = this.btnRight;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnRight");
            button2 = null;
        }
        button2.setVisibility(8);
        TextView textView = this.tvMin;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvMin");
            textView = null;
        }
        textView.setVisibility(8);
        TextView textView2 = this.tvMax;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvMax");
            textView2 = null;
        }
        textView2.setVisibility(8);
        LinearLayout linearLayout = this.llSetting;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("llSetting");
            linearLayout = null;
        }
        linearLayout.setVisibility(8);
        TextView textView3 = this.tvDesc;
        String string2 = StubApp.getString2(14529);
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            textView3 = null;
        }
        textView3.setText(getContext().getString(R.string.comfortable));
        TextView textView4 = this.tvDesc;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            textView4 = null;
        }
        textView4.setTextColor(Color.parseColor(StubApp.getString2(14524)));
        ImageView imageView = this.ivQuestion;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivQuestion");
            imageView = null;
        }
        imageView.setVisibility(8);
        LoopFanBean loopFanBean2 = this.mLoopFanBean;
        if (loopFanBean2 != null) {
            String current_env_temp = loopFanBean2.getCurrent_env_temp();
            if (current_env_temp != null) {
                Intrinsics.checkNotNull(current_env_temp);
                i = Integer.parseInt(current_env_temp) - 40;
            }
            CustomFontTextUnitView customFontTextUnitView2 = this.tv_indoor_temp;
            String string22 = StubApp.getString2(14526);
            if (customFontTextUnitView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                customFontTextUnitView2 = null;
            }
            customFontTextUnitView2.setValueText(TemperatureUtil.INSTANCE.formatTemperature(i));
            CustomFontTextUnitView customFontTextUnitView3 = this.tv_indoor_temp;
            if (customFontTextUnitView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                customFontTextUnitView3 = null;
            }
            customFontTextUnitView3.setUnitText(TemperatureUtil.INSTANCE.getTemperatureUnitSymbol());
            CustomFontTextUnitView customFontTextUnitView4 = this.tv_indoor_hum;
            if (customFontTextUnitView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_indoor_hum");
            } else {
                customFontTextUnitView = customFontTextUnitView4;
            }
            String current_env_hum = loopFanBean2.getCurrent_env_hum();
            if (current_env_hum == null) {
                current_env_hum = "";
            } else {
                Intrinsics.checkNotNull(current_env_hum);
            }
            customFontTextUnitView.setValueText(current_env_hum);
        }
    }

    private final void updateByMode() {
        boolean z;
        Context context;
        Context context2;
        String string2;
        String environment_degree;
        Integer intOrNull;
        String environment_degree2;
        Integer intOrNull2;
        Context context3;
        String current_env_hum;
        int i;
        int i2;
        LogDebug logDebug = LogDebug.INSTANCE;
        DehumidifierBean dehumidifierBean = this.mDehumidifierBean;
        TextView textView = null;
        String mode = dehumidifierBean != null ? dehumidifierBean.getMode() : null;
        boolean z2 = this.isContinue;
        DehumidifierBean dehumidifierBean2 = this.mDehumidifierBean;
        logDebug.log(StubApp.getString2(14518) + mode + StubApp.getString2(14528) + z2 + StubApp.getString2(14519) + (dehumidifierBean2 != null ? Boolean.valueOf(dehumidifierBean2.checkIsPowerOn()) : null) + StubApp.getString2(626));
        DehumidifierBean dehumidifierBean3 = this.mDehumidifierBean;
        boolean z3 = dehumidifierBean3 != null && dehumidifierBean3.checkIsPowerOn();
        DehumidifierBean dehumidifierBean4 = this.mDehumidifierBean;
        if (dehumidifierBean4 == null || (current_env_hum = dehumidifierBean4.getCurrent_env_hum()) == null || (i = Integer.parseInt(current_env_hum)) > (i2 = this.maxComfortHum)) {
            z = false;
        } else {
            z = (i > i2 || this.minComfortHum > i) ? 2 : true;
        }
        ArcSeekBar arcSeekBar = this.mArcSeekBar;
        if (arcSeekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mArcSeekBar");
            arcSeekBar = null;
        }
        arcSeekBar.changeArcStyle(-1, this.canDrag);
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
        TextView textView2 = this.tvMin;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvMin");
            textView2 = null;
        }
        textView2.setVisibility(this.canDrag ? 0 : 8);
        TextView textView3 = this.tvMax;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvMax");
            textView3 = null;
        }
        textView3.setVisibility(this.canDrag ? 0 : 8);
        CustomFontTextUnitView customFontTextUnitView = this.mProgressText;
        String string22 = StubApp.getString2(14521);
        if (customFontTextUnitView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            customFontTextUnitView = null;
        }
        customFontTextUnitView.setUnitText(StubApp.getString2(5130));
        CustomFontTextUnitView customFontTextUnitView2 = this.mProgressText;
        if (customFontTextUnitView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            customFontTextUnitView2 = null;
        }
        DehumidifierBean dehumidifierBean5 = this.mDehumidifierBean;
        String hum_set = dehumidifierBean5 != null ? dehumidifierBean5.getHum_set() : null;
        if (hum_set == null) {
            hum_set = "";
        }
        customFontTextUnitView2.setValueText(hum_set);
        String string23 = StubApp.getString2(14524);
        int i3 = R.string.turned_off;
        String string24 = StubApp.getString2(14529);
        if (!z) {
            TextView textView4 = this.tvDesc;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string24);
                textView4 = null;
            }
            if (z3) {
                context3 = getContext();
                i3 = R.string.humid;
            } else {
                context3 = getContext();
            }
            textView4.setText(context3.getString(i3));
            TextView textView5 = this.tvDesc;
            if (textView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string24);
                textView5 = null;
            }
            textView5.setTextColor(Color.parseColor(StubApp.getString2(13225)));
        } else if (z) {
            TextView textView6 = this.tvDesc;
            if (textView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string24);
                textView6 = null;
            }
            if (z3) {
                context2 = getContext();
                i3 = R.string.comfortable;
            } else {
                context2 = getContext();
            }
            textView6.setText(context2.getString(i3));
            TextView textView7 = this.tvDesc;
            if (textView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string24);
                textView7 = null;
            }
            textView7.setTextColor(Color.parseColor(string23));
        } else {
            TextView textView8 = this.tvDesc;
            if (textView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string24);
                textView8 = null;
            }
            textView8.setTextColor(Color.parseColor(StubApp.getString2(14523)));
            TextView textView9 = this.tvDesc;
            if (textView9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string24);
                textView9 = null;
            }
            if (z3) {
                context = getContext();
                i3 = R.string.dry;
            } else {
                context = getContext();
            }
            textView9.setText(context.getString(i3));
        }
        if (ChannelUtil.isOversea()) {
            TextView textView10 = this.tvDesc;
            if (textView10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string24);
                textView10 = null;
            }
            textView10.setTextSize(20.0f);
        }
        if (this.isPurification) {
            TextView textView11 = this.tvDesc;
            if (textView11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string24);
                textView11 = null;
            }
            textView11.setTextColor(Color.parseColor(string23));
            DehumidifierBean dehumidifierBean6 = this.mDehumidifierBean;
            if (dehumidifierBean6 != null && (environment_degree2 = dehumidifierBean6.getEnvironment_degree()) != null && (intOrNull2 = StringsKt.toIntOrNull(environment_degree2)) != null && intOrNull2.intValue() == 1) {
                string2 = StubApp.getString2(14530);
            } else {
                DehumidifierBean dehumidifierBean7 = this.mDehumidifierBean;
                if (dehumidifierBean7 == null || (environment_degree = dehumidifierBean7.getEnvironment_degree()) == null || (intOrNull = StringsKt.toIntOrNull(environment_degree)) == null || intOrNull.intValue() != 2) {
                    string2 = StubApp.getString2(14532);
                } else {
                    string2 = StubApp.getString2(14531);
                }
            }
            TextView textView12 = this.tvDesc;
            if (textView12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string24);
            } else {
                textView = textView12;
            }
            textView.setText(string2);
        }
        updateView();
    }

    private final void updateView() {
        int i;
        ImageView imageView = this.ivQuestion;
        String string2 = StubApp.getString2(14533);
        LinearLayout linearLayout = null;
        TextView textView = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            imageView = null;
        }
        imageView.setVisibility(this.isContinue ? 0 : 8);
        boolean z = this.isContinue;
        String string22 = StubApp.getString2(14534);
        String string23 = StubApp.getString2(14521);
        if (z && this.canDrag) {
            CustomFontTextUnitView customFontTextUnitView = this.mProgressText;
            if (customFontTextUnitView == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string23);
                customFontTextUnitView = null;
            }
            customFontTextUnitView.setVisibility(8);
            ImageView imageView2 = this.ivQuestion;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                imageView2 = null;
            }
            imageView2.setVisibility(0);
            ImageView imageView3 = this.ivCo;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                imageView3 = null;
            }
            imageView3.setVisibility(0);
        } else {
            CustomFontTextUnitView customFontTextUnitView2 = this.mProgressText;
            if (customFontTextUnitView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string23);
                customFontTextUnitView2 = null;
            }
            customFontTextUnitView2.setVisibility(0);
            ImageView imageView4 = this.ivQuestion;
            if (imageView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                imageView4 = null;
            }
            imageView4.setVisibility(8);
            ImageView imageView5 = this.ivCo;
            if (imageView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                imageView5 = null;
            }
            imageView5.setVisibility(8);
        }
        DehumidifierBean dehumidifierBean = this.mDehumidifierBean;
        String string24 = StubApp.getString2(14535);
        String string25 = StubApp.getString2(14526);
        if (dehumidifierBean != null) {
            String current_env_temp = dehumidifierBean.getCurrent_env_temp();
            if (current_env_temp != null) {
                Intrinsics.checkNotNull(current_env_temp);
                i = Integer.parseInt(current_env_temp) - 40;
            } else {
                i = 0;
            }
            CustomFontTextUnitView customFontTextUnitView3 = this.tv_indoor_temp;
            if (customFontTextUnitView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string25);
                customFontTextUnitView3 = null;
            }
            customFontTextUnitView3.setValueText(TemperatureUtil.INSTANCE.formatTemperature(i));
            CustomFontTextUnitView customFontTextUnitView4 = this.tv_indoor_temp;
            if (customFontTextUnitView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string25);
                customFontTextUnitView4 = null;
            }
            customFontTextUnitView4.setUnitText(TemperatureUtil.INSTANCE.getTemperatureUnitSymbol());
            CustomFontTextUnitView customFontTextUnitView5 = this.tv_indoor_hum;
            if (customFontTextUnitView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string24);
                customFontTextUnitView5 = null;
            }
            String current_env_hum = dehumidifierBean.getCurrent_env_hum();
            if (current_env_hum == null) {
                current_env_hum = "";
            } else {
                Intrinsics.checkNotNull(current_env_hum);
            }
            customFontTextUnitView5.setValueText(current_env_hum);
            if (dehumidifierBean.getHum_set() != null) {
                ArcSeekBar arcSeekBar = this.mArcSeekBar;
                if (arcSeekBar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mArcSeekBar");
                    arcSeekBar = null;
                }
                String hum_set = dehumidifierBean.getHum_set();
                Intrinsics.checkNotNullExpressionValue(hum_set, "getHum_set(...)");
                arcSeekBar.setProgress(Integer.parseInt(hum_set));
            }
        }
        boolean z2 = this.canDrag;
        String string26 = StubApp.getString2(14536);
        if (z2 && !this.isPurification) {
            LinearLayout linearLayout2 = this.llSetting;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string26);
                linearLayout2 = null;
            }
            linearLayout2.setVisibility(0);
            CustomFontTextUnitView customFontTextUnitView6 = this.tv_indoor_hum;
            if (customFontTextUnitView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string24);
                customFontTextUnitView6 = null;
            }
            customFontTextUnitView6.setHighlight(false);
            CustomFontTextUnitView customFontTextUnitView7 = this.tv_indoor_temp;
            if (customFontTextUnitView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string25);
                customFontTextUnitView7 = null;
            }
            customFontTextUnitView7.setHighlight(false);
        } else {
            LinearLayout linearLayout3 = this.llSetting;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string26);
                linearLayout3 = null;
            }
            linearLayout3.setVisibility(8);
            CustomFontTextUnitView customFontTextUnitView8 = this.tv_indoor_hum;
            if (customFontTextUnitView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string24);
                customFontTextUnitView8 = null;
            }
            customFontTextUnitView8.setHighlight(true);
            CustomFontTextUnitView customFontTextUnitView9 = this.tv_indoor_temp;
            if (customFontTextUnitView9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string25);
                customFontTextUnitView9 = null;
            }
            customFontTextUnitView9.setHighlight(true);
        }
        LinearLayout linearLayout4 = this.ll_temp_hum;
        String string27 = StubApp.getString2(14537);
        if (linearLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string27);
            linearLayout4 = null;
        }
        ViewGroup.LayoutParams layoutParams = linearLayout4.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        ((FrameLayout.LayoutParams) layoutParams).setMargins(0, this.canDrag ? DensityUtil.dp2px(40.0f) : DensityUtil.dp2px(25.0f), 0, 0);
        TextView textView2 = this.tvDesc;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvDesc");
            textView2 = null;
        }
        ViewGroup.LayoutParams layoutParams2 = textView2.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams2, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        ((FrameLayout.LayoutParams) layoutParams2).setMargins(0, 0, 0, this.canDrag ? DensityUtil.dp2px(40.0f) : DensityUtil.dp2px(25.0f));
        boolean z3 = this.isPurification;
        String string28 = StubApp.getString2(14538);
        if (z3) {
            LinearLayout linearLayout5 = this.llSetting;
            if (linearLayout5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string26);
                linearLayout5 = null;
            }
            linearLayout5.setVisibility(8);
            LinearLayout linearLayout6 = this.ll_temp_hum;
            if (linearLayout6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string27);
                linearLayout6 = null;
            }
            linearLayout6.setVisibility(8);
            TextView textView3 = this.tv_purification_msg;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string28);
            } else {
                textView = textView3;
            }
            textView.setVisibility(0);
            return;
        }
        TextView textView4 = this.tv_purification_msg;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string28);
            textView4 = null;
        }
        textView4.setVisibility(8);
        LinearLayout linearLayout7 = this.ll_temp_hum;
        if (linearLayout7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string27);
        } else {
            linearLayout = linearLayout7;
        }
        linearLayout.setVisibility(0);
    }

    public final void setHumidityChangeListener(OnHumidityChangeListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mListener = listener;
    }

    public final void setMinMax(HumidityBean humidityBean) {
        Intrinsics.checkNotNullParameter(humidityBean, "humidityBean");
        this.min = humidityBean.getMin();
        this.max = humidityBean.getMax();
        this.step = humidityBean.getStep();
        ArcSeekBar arcSeekBar = this.mArcSeekBar;
        String string2 = StubApp.getString2(14517);
        TextView textView = null;
        if (arcSeekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcSeekBar = null;
        }
        arcSeekBar.setMaxValue(this.max);
        ArcSeekBar arcSeekBar2 = this.mArcSeekBar;
        if (arcSeekBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcSeekBar2 = null;
        }
        arcSeekBar2.setMinValue(this.min);
        ArcSeekBar arcSeekBar3 = this.mArcSeekBar;
        if (arcSeekBar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            arcSeekBar3 = null;
        }
        arcSeekBar3.setStep(humidityBean.getStep());
        TextView textView2 = this.tvMin;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvMin");
            textView2 = null;
        }
        StringBuilder sbAppend = new StringBuilder().append(this.min);
        String string22 = StubApp.getString2(5130);
        textView2.setText(sbAppend.append(string22).toString());
        TextView textView3 = this.tvMax;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvMax");
        } else {
            textView = textView3;
        }
        textView.setText(this.max + string22);
    }

    public final void onHumChange(int humidity) {
        OnHumidityChangeListener onHumidityChangeListener = this.mListener;
        if (onHumidityChangeListener != null) {
            onHumidityChangeListener.onChange(humidity);
        }
        this.isContinue = humidity == this.CO;
        updateView();
    }

    public final void setCo(int co) {
        this.CO = co;
    }
}
