package com.deye.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentActivity;
import com.deye.helper.DialogHelper;
import com.deye.views.button.SwitchButton;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ViewItemControlBinding;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ItemControlView extends FrameLayout {
    private final ViewItemControlBinding binding;
    private OnCheckListener onCheckListener;

    public interface OnCheckListener {
        void onChecked(View view, boolean z);
    }

    public ItemControlView(Context context) {
        this(context, null);
    }

    public ItemControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ItemControlView(final Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ViewItemControlBinding viewItemControlBindingInflate = ViewItemControlBinding.inflate(LayoutInflater.from(context), this, true);
        this.binding = viewItemControlBindingInflate;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ItemControlView, i, 0);
        String string = typedArrayObtainStyledAttributes.getString(1);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(2, true);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(3, false);
        typedArrayObtainStyledAttributes.recycle();
        viewItemControlBindingInflate.controlText.setText(string);
        viewItemControlBindingInflate.controlImg.setBackground(drawable);
        viewItemControlBindingInflate.viewDivider.setVisibility(z ? 0 : 8);
        viewItemControlBindingInflate.ivQuestion.setVisibility(z2 ? 0 : 8);
        viewItemControlBindingInflate.switchButton.setOnClick(new SwitchButton.OnClick() { // from class: com.deye.views.ItemControlView$$ExternalSyntheticLambda0
            @Override // com.deye.views.button.SwitchButton.OnClick
            public final void onClick(View view, boolean z3) {
                this.f$0.lambda$new$0(view, z3);
            }
        });
        viewItemControlBindingInflate.ivQuestion.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.ItemControlView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FragmentActivity fragmentActivity = context;
                if (fragmentActivity instanceof FragmentActivity) {
                    DialogHelper.showButlerTipDialog(fragmentActivity);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view, boolean z) {
        OnCheckListener onCheckListener = this.onCheckListener;
        if (onCheckListener != null) {
            onCheckListener.onChecked(this, z);
        }
    }

    public void setText(String str) {
        this.binding.controlText.setText(str);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.binding.switchButton.setEnabled(z);
    }

    public void setChecked(boolean z) {
        this.binding.switchButton.setChecked(z);
    }

    public boolean isChecked() {
        return this.binding.switchButton.isChecked();
    }

    public void setOnCheckListener(OnCheckListener onCheckListener) {
        this.onCheckListener = onCheckListener;
    }
}
