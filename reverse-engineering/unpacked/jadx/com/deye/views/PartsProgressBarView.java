package com.deye.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ViewPartsProgressbarBinding;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class PartsProgressBarView extends FrameLayout {
    private final ViewPartsProgressbarBinding bing;
    private final Context context;

    public PartsProgressBarView(Context context) {
        this(context, null);
    }

    public PartsProgressBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PartsProgressBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.bing = ViewPartsProgressbarBinding.inflate(LayoutInflater.from(context), this, true);
        this.context = context;
    }

    public void updateMsg() {
        this.bing.tvMsg.setText(this.context.getString(R.string.maintenance_countdown));
    }

    public void setProgress(int i) {
        this.bing.progressBar.setProgress(i);
        this.bing.tvRemainingTime.setText(i + StubApp.getString2(5130));
        if (i < 5) {
            this.bing.ivStatus.setVisibility(0);
            this.bing.ivStatus.setBackgroundResource(R.drawable.icon_parts_lack);
            this.bing.progressBar.setProgressDrawable(ContextCompat.getDrawable(this.context, R.drawable.pb_red));
            this.bing.tvRemainingTime.setTextColor(Color.parseColor(StubApp.getString2(13487)));
            return;
        }
        if (i < 20) {
            this.bing.ivStatus.setVisibility(0);
            this.bing.ivStatus.setBackgroundResource(R.drawable.icon_parts_stress);
            this.bing.progressBar.setProgressDrawable(ContextCompat.getDrawable(this.context, R.drawable.pb_yellow));
            this.bing.tvRemainingTime.setTextColor(Color.parseColor(StubApp.getString2(14523)));
            return;
        }
        this.bing.ivStatus.setVisibility(8);
        this.bing.progressBar.setProgressDrawable(ContextCompat.getDrawable(this.context, R.drawable.pb_blue));
        this.bing.tvRemainingTime.setTextColor(Color.parseColor(StubApp.getString2(13600)));
    }
}
