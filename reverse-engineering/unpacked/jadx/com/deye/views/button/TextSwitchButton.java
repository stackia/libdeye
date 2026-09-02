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
public class TextSwitchButton extends LinearLayout {
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

    public TextSwitchButton(Context context) {
        super(context);
        this.respondNow = true;
    }

    public TextSwitchButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.respondNow = true;
        this.mLinearLayout = this;
        ((LayoutInflater) context.getSystemService(StubApp.getString2(11065))).inflate(R.layout.switch_button_layout_text, this);
        ImageView imageView = (ImageView) findViewById(R.id.btn);
        this.mSwitch = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.button.TextSwitchButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextSwitchButton.this.respondNow) {
                    TextSwitchButton.this.mSwitch.setSelected(!TextSwitchButton.this.mSwitch.isSelected());
                    if (TextSwitchButton.this.mOnClick != null) {
                        TextSwitchButton.this.mOnClick.onClick(TextSwitchButton.this.mLinearLayout, TextSwitchButton.this.mSwitch.isSelected());
                        return;
                    }
                    return;
                }
                if (TextSwitchButton.this.mOnClick != null) {
                    TextSwitchButton.this.mOnClick.onClick(TextSwitchButton.this.mLinearLayout, !TextSwitchButton.this.mSwitch.isSelected());
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
