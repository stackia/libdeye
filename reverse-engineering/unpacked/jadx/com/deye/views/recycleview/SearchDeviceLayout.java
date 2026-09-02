package com.deye.views.recycleview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.mxchipapp.R;
import com.stub.StubApp;
import com.vondear.rxtool.RxDataTool;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class SearchDeviceLayout extends LinearLayout {
    public ImageView ivScan;
    private EditText mEtContent;
    private IContentChanged mIContentChanged;
    private RelativeLayout mLlSearchDeviceRoot;
    private View.OnClickListener onScanClickListener;

    public interface IContentChanged {
        void onContentChanged(String str, String... strArr);

        void onFocusChange(boolean z);
    }

    public void setIContentChanged(IContentChanged iContentChanged) {
        this.mIContentChanged = iContentChanged;
    }

    public SearchDeviceLayout(Context context) {
        super(context);
    }

    public SearchDeviceLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ((LayoutInflater) context.getSystemService(StubApp.getString2(11065))).inflate(R.layout.search_device_layout, this);
        this.mLlSearchDeviceRoot = (RelativeLayout) findViewById(R.id.ll_search_device_root);
        this.mEtContent = (EditText) findViewById(R.id.et_content);
        ImageView imageView = (ImageView) findViewById(R.id.iv_scan);
        this.ivScan = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.recycleview.SearchDeviceLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SearchDeviceLayout.this.onScanClickListener != null) {
                    SearchDeviceLayout.this.onScanClickListener.onClick(view);
                }
            }
        });
        this.mEtContent.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.deye.views.recycleview.SearchDeviceLayout.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (SearchDeviceLayout.this.mIContentChanged != null) {
                    SearchDeviceLayout.this.mIContentChanged.onFocusChange(z);
                }
            }
        });
        this.mEtContent.addTextChangedListener(new TextWatcher() { // from class: com.deye.views.recycleview.SearchDeviceLayout.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (SearchDeviceLayout.this.mIContentChanged != null) {
                    SearchDeviceLayout.this.mIContentChanged.onContentChanged(editable.toString(), RxDataTool.cn2PYLower(editable.toString()), RxDataTool.toJianPinLower(editable.toString()));
                }
            }
        });
    }

    public void clearEditTextFocus() {
        ((InputMethodManager) getContext().getSystemService(StubApp.getString2(2742))).hideSoftInputFromWindow(this.mEtContent.getWindowToken(), 0);
        this.mEtContent.setText("");
        this.mEtContent.clearFocus();
        this.mLlSearchDeviceRoot.setFocusable(true);
        this.mLlSearchDeviceRoot.setFocusableInTouchMode(true);
    }

    public void setScanClickListener(View.OnClickListener onClickListener) {
        this.onScanClickListener = onClickListener;
    }
}
