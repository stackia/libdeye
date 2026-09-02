package com.deye.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;
import android.view.WindowManager;
import com.mxchipapp.R;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class RxDialog extends Dialog {
    protected Context mContext;
    protected WindowManager.LayoutParams mLayoutParams;

    public WindowManager.LayoutParams getLayoutParams() {
        return this.mLayoutParams;
    }

    public RxDialog(Context context, int i) {
        super(context, i);
        initView(context);
    }

    public RxDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        initView(context);
    }

    public RxDialog(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        requestWindowFeature(1);
        getWindow().setBackgroundDrawableResource(R.drawable.transparent_bg);
        this.mContext = context;
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        this.mLayoutParams = attributes;
        attributes.alpha = 1.0f;
        window.setAttributes(this.mLayoutParams);
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        if (layoutParams != null) {
            layoutParams.height = -1;
            this.mLayoutParams.gravity = 17;
        }
    }

    public RxDialog(Context context, float f, int i) {
        super(context);
        requestWindowFeature(1);
        getWindow().setBackgroundDrawableResource(R.drawable.transparent_bg);
        this.mContext = context;
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        this.mLayoutParams = attributes;
        attributes.alpha = 1.0f;
        window.setAttributes(this.mLayoutParams);
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        if (layoutParams != null) {
            layoutParams.height = -1;
            this.mLayoutParams.gravity = i;
        }
    }

    public void skipTools() {
        getWindow().setFlags(1024, 1024);
    }

    public void setFullScreen() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        window.setAttributes(attributes);
    }

    public void setFullScreenWidth() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
    }

    public void setFullScreenHeight() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -2;
        attributes.height = -1;
        window.setAttributes(attributes);
    }

    public void setOnWhole() {
        getWindow().setType(2003);
    }
}
