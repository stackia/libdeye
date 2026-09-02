package com.deye.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amap.location.support.bean.location.AmapLocationNetwork;
import com.deye.configs.Constants;
import com.deye.entity.control_panel.dehumidifier.func.SpeedBean;
import com.deye.utils.LanUtils;
import com.mxchipapp.R;
import com.mxchipapp.databinding.WindSpeedHumidifierParentViewBinding;
import com.stub.StubApp;
import com.zhouyou.view.seekbar.SignUtils;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: HumidifierWindSpeedParentView.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u001d\u001a\u00020\u0017H\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\u000e\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u000fJ\u0010\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u000fH\u0016J\u000e\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u0011J\u000e\u0010&\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u0017J\u000e\u0010'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)J\u0010\u0010*\u001a\u00020\u001f2\b\u0010+\u001a\u0004\u0018\u00010\u001bJ\u0010\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\tH\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u0018\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u0018\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/deye/views/HumidifierWindSpeedParentView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/mxchipapp/databinding/WindSpeedHumidifierParentViewBinding;", "llSleep", "mAvailable", "", "mHumidifierBean", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "mIsEnable", "mModeSelectedItem", "Ljava/lang/Integer;", "mSpeedName", "", "", "[Ljava/lang/String;", "mSpeedValue", "mlistener", "Lcom/deye/views/IWindChangeListener;", "productId", "getModeName", "init", "", "setAvailable", "available", "setEnabled", "enabled", "setHumidifierBean", "humidifierBean", "setProductId", "setSpeedData", "speedBean", "Lcom/deye/entity/control_panel/dehumidifier/func/SpeedBean;", "setWinChangeListener", "listener", "updateSpeedTextIcon", "step", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class HumidifierWindSpeedParentView extends LinearLayout {
    private WindSpeedHumidifierParentViewBinding binding;
    private Context context;
    private LinearLayout llSleep;
    private boolean mAvailable;
    private DehumidifierBean mHumidifierBean;
    private boolean mIsEnable;
    private Integer mModeSelectedItem;
    private String[] mSpeedName;
    private String[] mSpeedValue;
    private IWindChangeListener mlistener;
    private String productId;

    public HumidifierWindSpeedParentView(Context context) {
        super(context);
        this.mIsEnable = true;
        this.mAvailable = true;
        init(context);
    }

    public HumidifierWindSpeedParentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsEnable = true;
        this.mAvailable = true;
        init(context);
    }

    public HumidifierWindSpeedParentView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIsEnable = true;
        this.mAvailable = true;
        init(context);
    }

    public final void setAvailable(boolean available) {
        this.mAvailable = available;
        setAlpha(available ? 1.0f : 0.4f);
        WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding = this.binding;
        if (windSpeedHumidifierParentViewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            windSpeedHumidifierParentViewBinding = null;
        }
        windSpeedHumidifierParentViewBinding.maskView.setVisibility(available ? 8 : 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x016c  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setEnabled(boolean enabled) {
        String speed;
        Integer intOrNull;
        Integer num;
        super.setEnabled(enabled);
        this.mIsEnable = enabled;
        int iDp2px = SignUtils.dp2px(enabled ? 100 : 60);
        WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding = this.binding;
        String string2 = StubApp.getString2(13474);
        WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding2 = null;
        if (windSpeedHumidifierParentViewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            windSpeedHumidifierParentViewBinding = null;
        }
        ViewGroup.LayoutParams layoutParams = windSpeedHumidifierParentViewBinding.llContent.getLayoutParams();
        layoutParams.height = iDp2px;
        WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding3 = this.binding;
        if (windSpeedHumidifierParentViewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            windSpeedHumidifierParentViewBinding3 = null;
        }
        windSpeedHumidifierParentViewBinding3.llContent.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = getLayoutParams();
        layoutParams2.height = iDp2px;
        setLayoutParams(layoutParams2);
        WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding4 = this.binding;
        if (windSpeedHumidifierParentViewBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            windSpeedHumidifierParentViewBinding4 = null;
        }
        windSpeedHumidifierParentViewBinding4.fraSpeed.setPadding(0, SignUtils.dp2px(16), 0, SignUtils.dp2px(this.mIsEnable ? 12 : 16));
        if (this.mIsEnable) {
            WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding5 = this.binding;
            if (windSpeedHumidifierParentViewBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                windSpeedHumidifierParentViewBinding5 = null;
            }
            windSpeedHumidifierParentViewBinding5.llSpeedMode.setVisibility(8);
            WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding6 = this.binding;
            if (windSpeedHumidifierParentViewBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                windSpeedHumidifierParentViewBinding6 = null;
            }
            windSpeedHumidifierParentViewBinding6.windSeekBar.setVisibility(0);
        } else {
            WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding7 = this.binding;
            if (windSpeedHumidifierParentViewBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                windSpeedHumidifierParentViewBinding7 = null;
            }
            windSpeedHumidifierParentViewBinding7.llSpeedMode.setVisibility(0);
            WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding8 = this.binding;
            if (windSpeedHumidifierParentViewBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                windSpeedHumidifierParentViewBinding8 = null;
            }
            windSpeedHumidifierParentViewBinding8.windSeekBar.setVisibility(8);
        }
        if (this.mHumidifierBean == null) {
            return;
        }
        if (LanUtils.isEnLanguage()) {
            WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding9 = this.binding;
            if (windSpeedHumidifierParentViewBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                windSpeedHumidifierParentViewBinding9 = null;
            }
            windSpeedHumidifierParentViewBinding9.llSpeedMode.setVisibility(8);
        } else {
            WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding10 = this.binding;
            if (windSpeedHumidifierParentViewBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                windSpeedHumidifierParentViewBinding10 = null;
            }
            windSpeedHumidifierParentViewBinding10.llSpeedMode.setVisibility(0);
        }
        DehumidifierBean dehumidifierBean = this.mHumidifierBean;
        String string22 = StubApp.getString2(701);
        if (dehumidifierBean == null || dehumidifierBean.checkIsPowerOn()) {
            WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding11 = this.binding;
            if (windSpeedHumidifierParentViewBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                windSpeedHumidifierParentViewBinding11 = null;
            }
            windSpeedHumidifierParentViewBinding11.tvSpeedMode.setText(getModeName());
        } else {
            DehumidifierBean dehumidifierBean2 = this.mHumidifierBean;
            if (Intrinsics.areEqual(dehumidifierBean2 != null ? dehumidifierBean2.getFan_switch() : null, string22)) {
                WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding12 = this.binding;
                if (windSpeedHumidifierParentViewBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    windSpeedHumidifierParentViewBinding12 = null;
                }
                windSpeedHumidifierParentViewBinding12.tvSpeedMode.setText("");
            }
        }
        String[] strArr = this.mSpeedName;
        if (strArr != null) {
            DehumidifierBean dehumidifierBean3 = this.mHumidifierBean;
            Intrinsics.checkNotNull(dehumidifierBean3);
            if (!dehumidifierBean3.checkIsPowerOn()) {
                DehumidifierBean dehumidifierBean4 = this.mHumidifierBean;
                Intrinsics.checkNotNull(dehumidifierBean4);
                if (!Intrinsics.areEqual(dehumidifierBean4.getFan_switch(), string22)) {
                    DehumidifierBean dehumidifierBean5 = this.mHumidifierBean;
                    if (!Intrinsics.areEqual(AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY, dehumidifierBean5 != null ? dehumidifierBean5.getE1() : null)) {
                        DehumidifierBean dehumidifierBean6 = this.mHumidifierBean;
                        if (!Intrinsics.areEqual(dehumidifierBean6 != null ? dehumidifierBean6.getSpeed() : null, string22)) {
                            WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding13 = this.binding;
                            if (windSpeedHumidifierParentViewBinding13 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string2);
                                windSpeedHumidifierParentViewBinding13 = null;
                            }
                            windSpeedHumidifierParentViewBinding13.ivSpeedGear.setVisibility(0);
                            WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding14 = this.binding;
                            if (windSpeedHumidifierParentViewBinding14 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string2);
                                windSpeedHumidifierParentViewBinding14 = null;
                            }
                            windSpeedHumidifierParentViewBinding14.tvSpeedGear.setVisibility(0);
                            WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding15 = this.binding;
                            if (windSpeedHumidifierParentViewBinding15 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string2);
                                windSpeedHumidifierParentViewBinding15 = null;
                            }
                            windSpeedHumidifierParentViewBinding15.viewAuto.setVisibility(0);
                        }
                        num = this.mModeSelectedItem;
                        if (num != null) {
                        }
                    } else {
                        WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding16 = this.binding;
                        if (windSpeedHumidifierParentViewBinding16 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            windSpeedHumidifierParentViewBinding16 = null;
                        }
                        windSpeedHumidifierParentViewBinding16.ivSpeedGear.setVisibility(8);
                        WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding17 = this.binding;
                        if (windSpeedHumidifierParentViewBinding17 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            windSpeedHumidifierParentViewBinding17 = null;
                        }
                        windSpeedHumidifierParentViewBinding17.tvSpeedGear.setVisibility(8);
                        WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding18 = this.binding;
                        if (windSpeedHumidifierParentViewBinding18 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            windSpeedHumidifierParentViewBinding18 = null;
                        }
                        windSpeedHumidifierParentViewBinding18.viewAuto.setVisibility(8);
                        num = this.mModeSelectedItem;
                        if (num != null) {
                            int length = strArr.length;
                            Intrinsics.checkNotNull(num);
                            if (length >= num.intValue()) {
                                WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding19 = this.binding;
                                if (windSpeedHumidifierParentViewBinding19 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                                    windSpeedHumidifierParentViewBinding19 = null;
                                }
                                TextView textView = windSpeedHumidifierParentViewBinding19.tvSpeedGear;
                                Integer num2 = this.mModeSelectedItem;
                                Intrinsics.checkNotNull(num2);
                                textView.setText(strArr[num2.intValue()]);
                            }
                        }
                    }
                }
            }
        }
        DehumidifierBean dehumidifierBean7 = this.mHumidifierBean;
        if (dehumidifierBean7 == null || (speed = dehumidifierBean7.getSpeed()) == null || (intOrNull = StringsKt.toIntOrNull(speed)) == null) {
            return;
        }
        int iIntValue = intOrNull.intValue();
        WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding20 = this.binding;
        if (windSpeedHumidifierParentViewBinding20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            windSpeedHumidifierParentViewBinding2 = windSpeedHumidifierParentViewBinding20;
        }
        windSpeedHumidifierParentViewBinding2.windSeekBar.setCurStep(iIntValue);
        updateSpeedTextIcon(iIntValue);
    }

    public final void init(Context context) {
        this.context = context;
        WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBindingInflate = WindSpeedHumidifierParentViewBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(windSpeedHumidifierParentViewBindingInflate, "inflate(...)");
        this.binding = windSpeedHumidifierParentViewBindingInflate;
        if (windSpeedHumidifierParentViewBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            windSpeedHumidifierParentViewBindingInflate = null;
        }
        windSpeedHumidifierParentViewBindingInflate.windSeekBar.setWinChangeListener(new IWindChangeListener() { // from class: com.deye.views.HumidifierWindSpeedParentView$$ExternalSyntheticLambda0
            @Override // com.deye.views.IWindChangeListener
            public final void onWindChange(int i) {
                HumidifierWindSpeedParentView.init$lambda$3(this.f$0, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(HumidifierWindSpeedParentView this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updateSpeedTextIcon(i);
        IWindChangeListener iWindChangeListener = this$0.mlistener;
        if (iWindChangeListener != null) {
            iWindChangeListener.onWindChange(i);
        }
    }

    private final void updateSpeedTextIcon(int step) {
        WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding = this.binding;
        WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding2 = null;
        String string2 = StubApp.getString2(13474);
        if (windSpeedHumidifierParentViewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            windSpeedHumidifierParentViewBinding = null;
        }
        windSpeedHumidifierParentViewBinding.tvSpeedGear.setText(getResources().getString(R.string.humidifier_speed_level_format, Integer.valueOf(step)));
        if (this.mIsEnable) {
            WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding3 = this.binding;
            if (windSpeedHumidifierParentViewBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                windSpeedHumidifierParentViewBinding3 = null;
            }
            windSpeedHumidifierParentViewBinding3.tvSpeedGear.setTextColor(Color.parseColor(StubApp.getString2(13225)));
            if (step < 3) {
                WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding4 = this.binding;
                if (windSpeedHumidifierParentViewBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                } else {
                    windSpeedHumidifierParentViewBinding2 = windSpeedHumidifierParentViewBinding4;
                }
                windSpeedHumidifierParentViewBinding2.ivSpeedGear.setBackgroundResource(R.drawable.ic_seekbar_speed_min_blue);
                return;
            }
            if (step == 3) {
                WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding5 = this.binding;
                if (windSpeedHumidifierParentViewBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                } else {
                    windSpeedHumidifierParentViewBinding2 = windSpeedHumidifierParentViewBinding5;
                }
                windSpeedHumidifierParentViewBinding2.ivSpeedGear.setBackgroundResource(R.drawable.ic_seekbar_speed_middle_blue);
                return;
            }
            WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding6 = this.binding;
            if (windSpeedHumidifierParentViewBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                windSpeedHumidifierParentViewBinding2 = windSpeedHumidifierParentViewBinding6;
            }
            windSpeedHumidifierParentViewBinding2.ivSpeedGear.setBackgroundResource(R.drawable.ic_seekbar_speed_max_blue);
            return;
        }
        WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding7 = this.binding;
        if (windSpeedHumidifierParentViewBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            windSpeedHumidifierParentViewBinding7 = null;
        }
        windSpeedHumidifierParentViewBinding7.tvSpeedGear.setTextColor(Color.parseColor(StubApp.getString2(13488)));
        if (step < 3) {
            WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding8 = this.binding;
            if (windSpeedHumidifierParentViewBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                windSpeedHumidifierParentViewBinding2 = windSpeedHumidifierParentViewBinding8;
            }
            windSpeedHumidifierParentViewBinding2.ivSpeedGear.setBackgroundResource(R.drawable.ic_seekbar_speed_min_gray);
            return;
        }
        if (step == 3) {
            WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding9 = this.binding;
            if (windSpeedHumidifierParentViewBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                windSpeedHumidifierParentViewBinding2 = windSpeedHumidifierParentViewBinding9;
            }
            windSpeedHumidifierParentViewBinding2.ivSpeedGear.setBackgroundResource(R.drawable.ic_seekbar_speed_middle_gray);
            return;
        }
        WindSpeedHumidifierParentViewBinding windSpeedHumidifierParentViewBinding10 = this.binding;
        if (windSpeedHumidifierParentViewBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            windSpeedHumidifierParentViewBinding2 = windSpeedHumidifierParentViewBinding10;
        }
        windSpeedHumidifierParentViewBinding2.ivSpeedGear.setBackgroundResource(R.drawable.ic_seekbar_speed_max_gray);
    }

    public final void setProductId(String productId) {
        Intrinsics.checkNotNullParameter(productId, "productId");
        this.productId = productId;
    }

    public final void setHumidifierBean(DehumidifierBean humidifierBean) {
        Intrinsics.checkNotNullParameter(humidifierBean, "humidifierBean");
        String speed = humidifierBean.getSpeed();
        this.mHumidifierBean = humidifierBean;
        String[] strArr = this.mSpeedValue;
        if (strArr == null) {
            return;
        }
        Intrinsics.checkNotNull(strArr);
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String[] strArr2 = this.mSpeedValue;
            Intrinsics.checkNotNull(strArr2);
            if (Intrinsics.areEqual(strArr2[i], speed)) {
                this.mModeSelectedItem = Integer.valueOf(i);
            }
        }
        if (humidifierBean.checkIsPowerOn() && humidifierBean.getMode() != null && humidifierBean.getMode().equals(StubApp.getString2(1764))) {
            setEnabled(true);
        } else {
            setEnabled(false);
        }
    }

    public final void setSpeedData(SpeedBean speedBean) {
        Intrinsics.checkNotNullParameter(speedBean, "speedBean");
        this.mSpeedName = speedBean.getName();
        this.mSpeedValue = speedBean.getValue();
    }

    private final String getModeName() throws Resources.NotFoundException {
        String mode;
        String mode2;
        String mode3;
        String mode4;
        String mode5;
        String mode6;
        String mode7;
        String mode8;
        String mode9;
        boolean zIsH7Product = Constants.isH7Product(this.productId);
        String string2 = StubApp.getString2(8095);
        String string22 = StubApp.getString2(2546);
        String string23 = StubApp.getString2(7188);
        String string24 = StubApp.getString2(1764);
        String string25 = StubApp.getString2(13221);
        if (zIsH7Product) {
            DehumidifierBean dehumidifierBean = this.mHumidifierBean;
            if (dehumidifierBean != null && (mode9 = dehumidifierBean.getMode()) != null && mode9.equals(string24)) {
                String string = getResources().getString(R.string.humidifier_mode_h7_strong_on);
                Intrinsics.checkNotNullExpressionValue(string, string25);
                return string;
            }
            DehumidifierBean dehumidifierBean2 = this.mHumidifierBean;
            if (dehumidifierBean2 != null && (mode8 = dehumidifierBean2.getMode()) != null && mode8.equals(string23)) {
                String string3 = getResources().getString(R.string.humidifier_mode_h7_constant_on);
                Intrinsics.checkNotNullExpressionValue(string3, string25);
                return string3;
            }
            DehumidifierBean dehumidifierBean3 = this.mHumidifierBean;
            if (dehumidifierBean3 != null && (mode7 = dehumidifierBean3.getMode()) != null && mode7.equals(string22)) {
                String string4 = getResources().getString(R.string.sleep_mode_on);
                Intrinsics.checkNotNullExpressionValue(string4, string25);
                return string4;
            }
            DehumidifierBean dehumidifierBean4 = this.mHumidifierBean;
            if (dehumidifierBean4 != null && (mode6 = dehumidifierBean4.getMode()) != null && mode6.equals(string2)) {
                String string5 = getResources().getString(R.string.humidifier_mode_air_dry_on);
                Intrinsics.checkNotNullExpressionValue(string5, string25);
                return string5;
            }
            return "";
        }
        DehumidifierBean dehumidifierBean5 = this.mHumidifierBean;
        if (dehumidifierBean5 != null && (mode5 = dehumidifierBean5.getMode()) != null && mode5.equals(string24)) {
            String string6 = getResources().getString(R.string.humidifier_mode_continue_on);
            Intrinsics.checkNotNullExpressionValue(string6, string25);
            return string6;
        }
        DehumidifierBean dehumidifierBean6 = this.mHumidifierBean;
        if (dehumidifierBean6 != null && (mode4 = dehumidifierBean6.getMode()) != null && mode4.equals(StubApp.getString2(7778))) {
            String string7 = getResources().getString(R.string.humidifier_mode_manual_on);
            Intrinsics.checkNotNullExpressionValue(string7, string25);
            return string7;
        }
        DehumidifierBean dehumidifierBean7 = this.mHumidifierBean;
        if (dehumidifierBean7 != null && (mode3 = dehumidifierBean7.getMode()) != null && mode3.equals(string22)) {
            String string8 = getResources().getString(R.string.sleep_mode_on);
            Intrinsics.checkNotNullExpressionValue(string8, string25);
            return string8;
        }
        DehumidifierBean dehumidifierBean8 = this.mHumidifierBean;
        if (dehumidifierBean8 != null && (mode2 = dehumidifierBean8.getMode()) != null && mode2.equals(string23)) {
            String string9 = getResources().getString(R.string.humidifier_mode_smart_on);
            Intrinsics.checkNotNullExpressionValue(string9, string25);
            return string9;
        }
        DehumidifierBean dehumidifierBean9 = this.mHumidifierBean;
        if (dehumidifierBean9 != null && (mode = dehumidifierBean9.getMode()) != null && mode.equals(string2)) {
            String string10 = getResources().getString(R.string.humidifier_mode_air_dry_on);
            Intrinsics.checkNotNullExpressionValue(string10, string25);
            return string10;
        }
        return "";
    }

    public final void setWinChangeListener(IWindChangeListener listener) {
        this.mlistener = listener;
    }
}
