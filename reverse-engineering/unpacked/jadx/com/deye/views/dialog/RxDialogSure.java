package com.deye.views.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mxchipapp.R;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class RxDialogSure extends RxDialog {
    private ImageView mIvLogo;
    private TextView mTvContent;
    private TextView mTvSure;
    private TextView mTvTitle;

    public RxDialogSure(Context context, int i) {
        super(context, i);
        initView();
    }

    public RxDialogSure(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        initView();
    }

    public RxDialogSure(Context context) {
        super(context);
        initView();
    }

    public RxDialogSure(Context context, float f, int i) {
        super(context, f, i);
        initView();
    }

    public ImageView getLogoView() {
        return this.mIvLogo;
    }

    public TextView getTitleView() {
        return this.mTvTitle;
    }

    public TextView getSureView() {
        return this.mTvSure;
    }

    public void setSureListener(View.OnClickListener onClickListener) {
        this.mTvSure.setOnClickListener(onClickListener);
    }

    public TextView getContentView() {
        return this.mTvContent;
    }

    public void setLogo(int i) {
        this.mIvLogo.setImageResource(i);
    }

    public void setTitle(String str) {
        this.mTvTitle.setText(str);
    }

    public void setSure(String str) {
        this.mTvSure.setText(str);
    }

    public void setContent(String str) {
        this.mTvContent.setText(str);
    }

    private void initView() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_sure, (ViewGroup) null);
        this.mTvSure = (TextView) viewInflate.findViewById(R.id.tv_sure);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_title);
        this.mTvTitle = textView;
        textView.setTextIsSelectable(true);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_content);
        this.mTvContent = textView2;
        textView2.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.mTvContent.setTextIsSelectable(true);
        this.mIvLogo = (ImageView) viewInflate.findViewById(R.id.iv_logo);
        setContentView(viewInflate);
    }
}
