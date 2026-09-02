package com.deye.views.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.mxchipapp.R;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class SwitchButton extends LinearLayout {
    private LinearLayout mLinearLayout;
    private OnClick mOnClick;
    private ImageView mSwitch;
    private boolean respondNow;

    public interface OnClick {
        void onClick(View view, boolean z);
    }

    public boolean isChecked() {
        return this.mSwitch.isSelected();
    }

    public void setChecked(boolean z) {
        this.mSwitch.setSelected(z);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        this.mSwitch.setEnabled(z);
    }

    public SwitchButton(Context context) {
        super(context);
        this.respondNow = true;
    }

    public SwitchButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.respondNow = true;
        this.mLinearLayout = this;
        ((LayoutInflater) context.getSystemService(StubApp.getString2(11065))).inflate(R.layout.switch_button_layout, this);
        ImageView imageView = (ImageView) findViewById(R.id.btn);
        this.mSwitch = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.button.SwitchButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SwitchButton.this.respondNow) {
                    SwitchButton.this.mSwitch.setSelected(!SwitchButton.this.mSwitch.isSelected());
                    if (SwitchButton.this.mOnClick != null) {
                        SwitchButton.this.mOnClick.onClick(SwitchButton.this.mLinearLayout, SwitchButton.this.mSwitch.isSelected());
                        return;
                    }
                    return;
                }
                if (SwitchButton.this.mOnClick != null) {
                    SwitchButton.this.mOnClick.onClick(SwitchButton.this.mLinearLayout, !SwitchButton.this.mSwitch.isSelected());
                }
            }
        });
    }

    public void setOnClick(OnClick onClick) {
        this.mOnClick = onClick;
    }

    public void setRespondNow(boolean z) {
        this.respondNow = z;
    }
}
