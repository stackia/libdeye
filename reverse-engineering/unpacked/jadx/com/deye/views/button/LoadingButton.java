package com.deye.views.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.deye.views.LVCircularRing;
import com.mxchipapp.R;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class LoadingButton extends LinearLayout {
    private TextView mBtn;
    private Context mCtx;
    private LVCircularRing mLVCircularRing;
    private RelativeLayout mRlRoot;

    public LoadingButton(Context context) {
        super(context);
    }

    public LoadingButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCtx = context;
        ((LayoutInflater) context.getSystemService(StubApp.getString2(11065))).inflate(R.layout.loading_button_layout, this);
        this.mBtn = (TextView) findViewById(R.id.btn);
        this.mLVCircularRing = (LVCircularRing) findViewById(R.id.lv_circularring);
        this.mRlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        this.mLVCircularRing.setVisibility(8);
        this.mRlRoot.setVisibility(8);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            this.mRlRoot.setVisibility(8);
        } else {
            this.mRlRoot.setVisibility(0);
        }
    }

    public void setBtnText(String str) {
        this.mBtn.setText(str);
    }

    public void startAnimation() {
        hideKeyboard();
        setClickable(false);
        this.mLVCircularRing.setVisibility(0);
        this.mLVCircularRing.startAnim();
    }

    public void stopAnimation() {
        setClickable(true);
        this.mLVCircularRing.setVisibility(8);
        this.mLVCircularRing.stopAnim();
    }

    protected void hideKeyboard() {
        ((InputMethodManager) this.mCtx.getSystemService(StubApp.getString2(2742))).hideSoftInputFromWindow(getWindowToken(), 0);
    }
}
