package com.deye.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amap.location.support.bean.location.AmapLocationNetwork;
import com.deye.entity.control_panel.dehumidifier.func.SpeedBean;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.bean.LoopFanBean;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AirWindSpeedParentView.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001-B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u001c\u001a\u00020\u0011H\u0002J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\u0017H\u0002J\u0010\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\tH\u0002J\b\u0010\"\u001a\u00020#H\u0002J\u000e\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\rJ\u000e\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020\u0011J\u0010\u0010(\u001a\u00020#2\b\u0010)\u001a\u0004\u0018\u00010\u000fJ\u000e\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020,R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u0018\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u0018\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u0018\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018¨\u0006."}, d2 = {"Lcom/deye/views/AirWindSpeedParentView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "llSleep", "mDehumidifierBean", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "mIOnClickItemListener", "Lcom/deye/views/AirWindSpeedParentView$IOnClickItemListener;", "mIsEnable", "", "mLoopFanBean", "Lio/fogcloud/sdk/fog/bean/LoopFanBean;", "mModeSelectedItem", "mSpeedIconNormal", "", "", "[Ljava/lang/String;", "mSpeedIconSelected", "mSpeedName", "mSpeedValue", "checkEnable", "getDrawable", "Landroid/graphics/drawable/Drawable;", "imgName", "getStyleIndex", "postion", "initViews", "", "setDehumidifierBean", "dehumidifierBean", "setEnable", "isEnable", "setOnClickItemListener", "iOnClickItemListener", "setSpeedData", "speedBean", "Lcom/deye/entity/control_panel/dehumidifier/func/SpeedBean;", "IOnClickItemListener", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class AirWindSpeedParentView extends LinearLayout {
    private Context context;
    private LinearLayout llSleep;
    private DehumidifierBean mDehumidifierBean;
    private IOnClickItemListener mIOnClickItemListener;
    private boolean mIsEnable;
    private LoopFanBean mLoopFanBean;
    private int mModeSelectedItem;
    private String[] mSpeedIconNormal;
    private String[] mSpeedIconSelected;
    private String[] mSpeedName;
    private String[] mSpeedValue;

    /* compiled from: AirWindSpeedParentView.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/deye/views/AirWindSpeedParentView$IOnClickItemListener;", "", "onOnClickItem", "", "position", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface IOnClickItemListener {
        void onOnClickItem(int position);
    }

    public AirWindSpeedParentView(Context context) {
        super(context);
        this.mModeSelectedItem = -2;
        this.mIsEnable = true;
    }

    public AirWindSpeedParentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mModeSelectedItem = -2;
        this.mIsEnable = true;
        this.context = context;
    }

    public AirWindSpeedParentView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mModeSelectedItem = -2;
        this.mIsEnable = true;
        this.context = context;
    }

    public final void setEnable(boolean isEnable) {
        this.mIsEnable = isEnable;
        ViewExtendsKt.setAvailable(this, isEnable && checkEnable());
        initViews();
    }

    public final void setDehumidifierBean(DehumidifierBean dehumidifierBean) {
        Intrinsics.checkNotNullParameter(dehumidifierBean, "dehumidifierBean");
        String speed = dehumidifierBean.getSpeed();
        this.mDehumidifierBean = dehumidifierBean;
        this.mModeSelectedItem = -2;
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
                this.mModeSelectedItem = i;
            }
        }
        initViews();
        ViewExtendsKt.setAvailable(this, checkEnable());
        if (Intrinsics.areEqual(StubApp.getString2(2546), dehumidifierBean.sleep_switch)) {
            LinearLayout linearLayout = this.llSleep;
            if (linearLayout == null) {
                return;
            }
            linearLayout.setVisibility(0);
            return;
        }
        LinearLayout linearLayout2 = this.llSleep;
        if (linearLayout2 == null) {
            return;
        }
        linearLayout2.setVisibility(8);
    }

    public final void setSpeedData(SpeedBean speedBean) {
        Intrinsics.checkNotNullParameter(speedBean, "speedBean");
        this.mSpeedName = speedBean.getName();
        this.mSpeedValue = speedBean.getValue();
        this.mSpeedIconNormal = speedBean.getIconNormal();
        this.mSpeedIconSelected = speedBean.getIconSelected();
    }

    private final void initViews() {
        AirWindSpeedParentView airWindSpeedParentView = this;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.context).inflate(R.layout.wind_air_speed_parent_view, (ViewGroup) airWindSpeedParentView, true).findViewById(R.id.ll_speeds);
        String[] strArr = this.mSpeedName;
        if (strArr != null) {
            Intrinsics.checkNotNull(strArr);
            if (strArr.length == 0) {
                return;
            }
            this.llSleep = (LinearLayout) findViewById(R.id.ll_sleep);
            Intrinsics.checkNotNull(this.mSpeedName);
            setWeightSum(r2.length);
            linearLayout.removeAllViews();
            String[] strArr2 = this.mSpeedName;
            Intrinsics.checkNotNull(strArr2);
            int length = strArr2.length;
            for (final int i = 0; i < length; i++) {
                DehumidifierBean dehumidifierBean = this.mDehumidifierBean;
                if (!Intrinsics.areEqual(dehumidifierBean != null ? dehumidifierBean.getMode() : null, AmapLocationNetwork.RESULT_TYPE_CELL_ONLY) || i != 0) {
                    View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.ry_wind_speed_item, (ViewGroup) airWindSpeedParentView, false);
                    TextView textView = (TextView) viewInflate.findViewById(R.id.tv_wind_speed);
                    CheckBox checkBox = (CheckBox) viewInflate.findViewById(R.id.cb_wind_speed);
                    WindSpeedView windSpeedView = (WindSpeedView) viewInflate.findViewById(R.id.wind_spped_view);
                    String[] strArr3 = this.mSpeedName;
                    Intrinsics.checkNotNull(strArr3);
                    textView.setText(strArr3[i]);
                    if (i == this.mModeSelectedItem) {
                        checkBox.setChecked(true);
                        textView.setSelected(true);
                        textView.setTypeface(null, 1);
                        String[] strArr4 = this.mSpeedIconSelected;
                        if (strArr4 != null) {
                            checkBox.setBackground(getDrawable(strArr4[i]));
                        }
                        windSpeedView.updateStyle(getStyleIndex(i), true);
                    } else {
                        checkBox.setChecked(false);
                        textView.setSelected(false);
                        textView.setTypeface(null, 0);
                        String[] strArr5 = this.mSpeedIconNormal;
                        if (strArr5 != null) {
                            checkBox.setBackground(getDrawable(strArr5[i]));
                        }
                        windSpeedView.updateStyle(getStyleIndex(i), false);
                    }
                    viewInflate.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.AirWindSpeedParentView$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            AirWindSpeedParentView.initViews$lambda$2(this.f$0, i, view);
                        }
                    });
                    linearLayout.addView(viewInflate);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViews$lambda$2(AirWindSpeedParentView this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.checkEnable()) {
            this$0.mModeSelectedItem = i;
            this$0.initViews();
            IOnClickItemListener iOnClickItemListener = this$0.mIOnClickItemListener;
            if (iOnClickItemListener != null) {
                Intrinsics.checkNotNull(iOnClickItemListener);
                iOnClickItemListener.onOnClickItem(i);
            }
        }
    }

    private final int getStyleIndex(int postion) {
        if (postion == 1) {
            DehumidifierBean dehumidifierBean = this.mDehumidifierBean;
            if (Intrinsics.areEqual(dehumidifierBean != null ? dehumidifierBean.getMode() : null, AmapLocationNetwork.RESULT_TYPE_CELL_ONLY)) {
                return 0;
            }
        }
        if (postion == 0) {
            return 0;
        }
        String[] strArr = this.mSpeedName;
        Intrinsics.checkNotNull(strArr);
        return postion == strArr.length - 1 ? 2 : 1;
    }

    private final boolean checkEnable() {
        DehumidifierBean dehumidifierBean;
        if (this.mIsEnable && (dehumidifierBean = this.mDehumidifierBean) != null) {
            Intrinsics.checkNotNull(dehumidifierBean);
            if (dehumidifierBean.checkIsPowerOn()) {
                DehumidifierBean dehumidifierBean2 = this.mDehumidifierBean;
                Intrinsics.checkNotNull(dehumidifierBean2);
                if (!Intrinsics.areEqual(dehumidifierBean2.sleep_switch, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void setOnClickItemListener(IOnClickItemListener iOnClickItemListener) {
        this.mIOnClickItemListener = iOnClickItemListener;
    }

    private final Drawable getDrawable(String imgName) throws IOException {
        InputStream inputStreamOpen;
        Context context = this.context;
        Intrinsics.checkNotNull(context);
        try {
            inputStreamOpen = context.getAssets().open(imgName);
        } catch (IOException e) {
            e.printStackTrace();
            inputStreamOpen = null;
        }
        return Drawable.createFromStream(inputStreamOpen, null);
    }
}
