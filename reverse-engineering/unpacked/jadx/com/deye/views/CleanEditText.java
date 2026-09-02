package com.deye.views;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.mxchipapp.R;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class CleanEditText extends LinearLayout {
    private EditText mEdContent;
    private ImageView mIvDelete;
    private OnTextChangeListener mOnTextChangeListener;

    public interface OnTextChangeListener {
        void OnTextChange(String str);
    }

    public void setOnTextChangeListener(OnTextChangeListener onTextChangeListener) {
        this.mOnTextChangeListener = onTextChangeListener;
    }

    public CleanEditText(Context context) {
        super(context);
    }

    public CleanEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackground(getResources().getDrawable(R.drawable.radius_5_shape_normal));
        ((LayoutInflater) context.getSystemService(StubApp.getString2(11065))).inflate(R.layout.clean_edittext_layout, this);
        this.mEdContent = (EditText) findViewById(R.id.ed_content);
        this.mEdContent.setFilters(new InputFilter[]{new EmojiFilter(), new InputFilter.LengthFilter(15)});
        ImageView imageView = (ImageView) findViewById(R.id.iv_delete);
        this.mIvDelete = imageView;
        imageView.setVisibility(8);
        this.mIvDelete.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.CleanEditText.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CleanEditText.this.mEdContent.setText("");
            }
        });
        new Handler().postDelayed(new Runnable() { // from class: com.deye.views.CleanEditText.2
            @Override // java.lang.Runnable
            public void run() {
                CleanEditText cleanEditText = CleanEditText.this;
                cleanEditText.showKeyBoard(cleanEditText.mEdContent);
            }
        }, 100L);
        this.mEdContent.addTextChangedListener(new TextWatcher() { // from class: com.deye.views.CleanEditText.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() <= 0) {
                    CleanEditText.this.mIvDelete.setVisibility(8);
                } else {
                    CleanEditText.this.mIvDelete.setVisibility(0);
                }
                if (CleanEditText.this.mOnTextChangeListener != null) {
                    CleanEditText.this.mOnTextChangeListener.OnTextChange(editable.toString());
                }
            }
        });
    }

    public void setContentHint(String str) {
        this.mEdContent.setHint(str);
    }

    public void setContentHint(int i) {
        this.mEdContent.setHint(i);
    }

    public void setContentText(int i) {
        this.mEdContent.setText(i);
        EditText editText = this.mEdContent;
        editText.setSelection(editText.getText().toString().length());
    }

    public String getContentText() {
        return this.mEdContent.getText().toString();
    }

    public void setContentText(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mEdContent.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showKeyBoard(View view) {
        ((InputMethodManager) view.getContext().getSystemService(StubApp.getString2(2742))).showSoftInput(view, 0);
    }
}
