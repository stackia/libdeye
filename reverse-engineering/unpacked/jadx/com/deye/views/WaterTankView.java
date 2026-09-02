package com.deye.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.deye.configs.Constants;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ViewWaterTankBinding;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class WaterTankView extends FrameLayout {
    private ViewWaterTankBinding binding;

    public WaterTankView(Context context) {
        super(context);
        init(context);
    }

    public WaterTankView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public WaterTankView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.binding = ViewWaterTankBinding.inflate(LayoutInflater.from(context), this, true);
    }

    public void setValue(String str, int i, boolean z) {
        if (str.equals(StubApp.getString2(13436)) || str.equals(StubApp.getString2(13395))) {
            if (i - 2 == 1) {
                this.binding.tvWaterYield.setText(StubApp.getString2(14655));
                this.binding.ivWater.setImageResource(R.drawable.icon_water_yield);
                return;
            } else if (z) {
                this.binding.tvWaterYield.setText(StubApp.getString2(14656));
                this.binding.ivWater.setImageResource(R.drawable.icon_water_yield_warn);
                return;
            } else {
                this.binding.tvWaterYield.setText(StubApp.getString2(14657));
                this.binding.ivWater.setImageResource(R.drawable.icon_water_yield);
                return;
            }
        }
        if (Constants.isH7Product(str)) {
            if (i == 0) {
                this.binding.tvWaterYield.setText(StubApp.getString2(14646));
                this.binding.ivWater.setImageResource(R.drawable.icon_water_yield_warn);
                return;
            }
            if (i == 1) {
                this.binding.tvWaterYield.setText(StubApp.getString2(14647));
                this.binding.ivWater.setImageResource(R.drawable.icon_water_yield);
                return;
            }
            if (i == 2) {
                this.binding.tvWaterYield.setText(StubApp.getString2(14648));
                this.binding.ivWater.setImageResource(R.drawable.icon_water_yield);
                return;
            }
            if (i == 3) {
                this.binding.tvWaterYield.setText(StubApp.getString2(14649));
                this.binding.ivWater.setImageResource(R.drawable.icon_water_yield);
                return;
            }
            if (i == 4) {
                this.binding.tvWaterYield.setText(StubApp.getString2(14650));
                this.binding.ivWater.setImageResource(R.drawable.icon_water_yield);
                return;
            }
            if (i == 5) {
                this.binding.tvWaterYield.setText(StubApp.getString2(14651));
                this.binding.ivWater.setImageResource(R.drawable.icon_water_yield);
                return;
            }
            if (i == 6) {
                this.binding.tvWaterYield.setText(StubApp.getString2(14652));
                this.binding.ivWater.setImageResource(R.drawable.icon_water_yield);
            } else if (i == 7) {
                this.binding.tvWaterYield.setText(StubApp.getString2(14653));
                this.binding.ivWater.setImageResource(R.drawable.icon_water_yield);
            } else if (i == 8) {
                this.binding.tvWaterYield.setText(StubApp.getString2(14654));
                this.binding.ivWater.setImageResource(R.drawable.icon_water_yield);
            }
        }
    }
}
