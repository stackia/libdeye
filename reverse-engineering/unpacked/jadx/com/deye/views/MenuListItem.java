package com.deye.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mxchipapp.R;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class MenuListItem extends RelativeLayout {
    private boolean isChecked;
    private Drawable rightIconDrawable;
    private ImageView right_icon;
    private String text_left;
    private String text_right;
    private TextView textview_left;
    private int textview_left_color;
    private TextView textview_right;

    public MenuListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isChecked = false;
        initAttrs(context, attributeSet);
        View.inflate(context, R.layout.menu_list_item, this);
        this.textview_left = (TextView) findViewById(R.id.item_text_left);
        this.textview_right = (TextView) findViewById(R.id.item_text_right);
        this.right_icon = (ImageView) findViewById(R.id.item_right_icon);
        initData();
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MenuListItem);
        this.text_left = typedArrayObtainStyledAttributes.getString(0);
        this.text_right = typedArrayObtainStyledAttributes.getString(3);
        this.rightIconDrawable = typedArrayObtainStyledAttributes.getDrawable(2);
        this.textview_left_color = typedArrayObtainStyledAttributes.getColor(1, -16777216);
    }

    private void initData() {
        String str = this.text_left;
        if (str != null) {
            this.textview_left.setText(str);
            this.textview_left.setTextColor(this.textview_left_color);
        }
        if (this.text_right != null) {
            this.textview_right.setVisibility(0);
            this.textview_right.setText(this.text_right);
        }
        if (this.rightIconDrawable != null) {
            this.right_icon.setVisibility(0);
            this.right_icon.setImageDrawable(this.rightIconDrawable);
        }
    }

    public TextView getLeftTextView() {
        return this.textview_left;
    }

    public TextView getRightTextView() {
        return this.textview_right;
    }

    public ImageView getRightIcon() {
        return this.right_icon;
    }

    public void onClick() {
        if (isChecked()) {
            this.isChecked = false;
            getRightIcon().setVisibility(8);
        } else {
            getRightIcon().setVisibility(0);
            this.isChecked = true;
        }
    }

    public void setChecked(boolean z) {
        if (z) {
            getRightIcon().setVisibility(0);
        } else {
            getRightIcon().setVisibility(8);
        }
        this.isChecked = z;
    }

    public boolean isChecked() {
        return this.isChecked;
    }
}
