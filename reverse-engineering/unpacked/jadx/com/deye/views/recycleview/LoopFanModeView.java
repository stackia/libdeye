package com.deye.views.recycleview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.deye.entity.control_panel.dehumidifier.func.ModeBean;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.LoopFanBean;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class LoopFanModeView extends LinearLayout {
    private Context mContext;
    private IOnClickItemListener mIOnClickItemListener;
    private boolean mIsEnable;
    private LoopFanBean mLoopFanBean;
    private ModeBean mModeBean;

    public interface IOnClickItemListener {
        void onOnClickItem(LoopFanBean loopFanBean);
    }

    public LoopFanModeView(Context context) {
        super(context);
        this.mIsEnable = true;
    }

    public LoopFanModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsEnable = true;
        this.mContext = context;
    }

    public void setEnable(boolean z) {
        this.mIsEnable = z;
    }

    public void setLoopFanBean(LoopFanBean loopFanBean) {
        this.mLoopFanBean = loopFanBean;
        initView();
    }

    public void initData(ModeBean modeBean) {
        this.mModeBean = modeBean;
        if (modeBean.getValue().length <= 0) {
            setVisibility(8);
        } else {
            initView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initView() {
        removeAllViews();
        int length = this.mModeBean.getValue().length;
        if (this.mLoopFanBean == null) {
            return;
        }
        setWeightSum(length);
        for (final int i = 0; i < length; i++) {
            ConstraintLayout constraintLayoutInflate = LayoutInflater.from(this.mContext).inflate(R.layout.ry_mode_item, (ViewGroup) this, false);
            addView(constraintLayoutInflate);
            TextView textView = (TextView) constraintLayoutInflate.findViewById(R.id.tv_wind_speed);
            ImageView imageView = (ImageView) constraintLayoutInflate.findViewById(R.id.cb_wind_speed);
            textView.setText(this.mModeBean.getName()[i]);
            final boolean z = true;
            String string2 = StubApp.getString2(2546);
            if (i == 0) {
                boolean z2 = string2.equals(this.mLoopFanBean.getWater_box_non_existent()) || string2.equals(this.mLoopFanBean.getWater_shortage());
                if (!this.mLoopFanBean.getHumidification().equals(string2)) {
                    if (z2) {
                        imageView.setBackgroundResource(R.drawable.icon_mode_humidification_normal_error);
                    } else {
                        imageView.setBackgroundResource(R.drawable.icon_mode_humidification_normal);
                    }
                    z = false;
                } else if (z2) {
                    imageView.setBackgroundResource(R.drawable.icon_mode_humidification_error);
                } else {
                    imageView.setBackgroundResource(R.drawable.icon_mode_humidification_select);
                }
            } else if (i == 1) {
                boolean z3 = string2.equals(this.mLoopFanBean.getDeodorant_liquid_box_non_existent()) || string2.equals(this.mLoopFanBean.getDeodorant_liquid_exhaust());
                if (!this.mLoopFanBean.getDeodorization().equals(string2)) {
                    if (z3) {
                        imageView.setBackgroundResource(R.drawable.icon_mode_deodorization_normal_error);
                    } else {
                        imageView.setBackgroundResource(R.drawable.icon_mode_deodorization_normal);
                    }
                    z = false;
                } else if (z3) {
                    imageView.setBackgroundResource(R.drawable.icon_mode_deodorization_error);
                } else {
                    imageView.setBackgroundResource(R.drawable.icon_mode_deodorization_select);
                }
            } else if (i != 2) {
                z = false;
            } else {
                boolean z4 = string2.equals(this.mLoopFanBean.getFormaldehyde_removing_liquid_box_non_existent()) || string2.equals(this.mLoopFanBean.getFormaldehyde_removing_liquid_exhaust());
                if (!this.mLoopFanBean.getFormaldehyde_removal().equals(string2)) {
                    if (z4) {
                        imageView.setBackgroundResource(R.drawable.icon_mode_formaldehyde_normal_error);
                    } else {
                        imageView.setBackgroundResource(R.drawable.icon_mode_formaldehyde_normal);
                    }
                    z = false;
                } else if (z4) {
                    imageView.setBackgroundResource(R.drawable.icon_mode_formaldehyde_error);
                } else {
                    imageView.setBackgroundResource(R.drawable.icon_mode_formaldehyde_select);
                }
            }
            if (z) {
                textView.setTextColor(Color.parseColor(StubApp.getString2(13225)));
            } else {
                textView.setTextColor(Color.parseColor(StubApp.getString2(13488)));
            }
            constraintLayoutInflate.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.recycleview.LoopFanModeView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (LoopFanModeView.this.mLoopFanBean.checkIsPowerOn()) {
                        String string22 = z ^ true ? StubApp.getString2(2546) : StubApp.getString2(701);
                        int i2 = i;
                        if (i2 == 0) {
                            LoopFanModeView.this.mLoopFanBean.setHumidification(string22);
                        } else if (i2 == 1) {
                            LoopFanModeView.this.mLoopFanBean.setDeodorization(string22);
                        } else {
                            LoopFanModeView.this.mLoopFanBean.setFormaldehyde_removal(string22);
                        }
                        if (LoopFanModeView.this.mIOnClickItemListener != null) {
                            LoopFanModeView.this.mIOnClickItemListener.onOnClickItem(LoopFanModeView.this.mLoopFanBean);
                        }
                        LoopFanModeView.this.initView();
                    }
                }
            });
        }
    }

    public void setOnClickItemListener(IOnClickItemListener iOnClickItemListener) {
        this.mIOnClickItemListener = iOnClickItemListener;
    }
}
