package com.deye.views.recycleview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.deye.entity.control_panel.dehumidifier.func.ModeBean;
import com.deye.utils.LanUtils;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class DehumidifierModeView extends LinearLayout {
    private Context mContext;
    protected DehumidifierBean mDehumidifierBean;
    protected String mDeviceMode;
    private IOnClickItemListener mIOnClickItemListener;
    private boolean mIsEnable;
    private ModeBean mModeBean;
    private int mModeSelectedItem;

    public interface IOnClickItemListener {
        void onOnClickItem(int i);
    }

    public DehumidifierModeView(Context context) {
        super(context);
        this.mIsEnable = true;
        this.mModeSelectedItem = -1;
    }

    public DehumidifierModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsEnable = true;
        this.mModeSelectedItem = -1;
        this.mContext = context;
    }

    public void setAvailable(Boolean bool) {
        setAlpha(bool.booleanValue() ? 1.0f : 0.4f);
        setEnable(bool.booleanValue());
    }

    public void setEnable(boolean z) {
        this.mIsEnable = z;
    }

    public void setDehumidifierBean(DehumidifierBean dehumidifierBean) {
        this.mDehumidifierBean = dehumidifierBean;
        String mode = dehumidifierBean.getMode();
        this.mModeSelectedItem = -1;
        ModeBean modeBean = this.mModeBean;
        if (modeBean == null || modeBean.getValue() == null) {
            return;
        }
        if (dehumidifierBean.checkIsPowerOn()) {
            for (int i = 0; i < this.mModeBean.getValue().length; i++) {
                if (this.mModeBean.getValue()[i].equals(mode)) {
                    this.mModeSelectedItem = i;
                }
            }
        }
        initView();
    }

    public void initData(ModeBean modeBean, String str) {
        this.mModeBean = modeBean;
        this.mDeviceMode = str;
        if (modeBean.getValue().length <= 0) {
            setVisibility(8);
        } else {
            initView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initView() {
        removeAllViews();
        int length = this.mModeBean.getValue().length;
        setWeightSum(length);
        for (final int i = 0; i < length; i++) {
            ConstraintLayout constraintLayoutInflate = LayoutInflater.from(this.mContext).inflate(R.layout.ry_mode_item, (ViewGroup) this, false);
            addView(constraintLayoutInflate);
            RelativeLayout relativeLayout = (RelativeLayout) constraintLayoutInflate.findViewById(R.id.rl_ry_mode_root);
            TextView textView = (TextView) constraintLayoutInflate.findViewById(R.id.tv_wind_speed);
            ImageView imageView = (ImageView) constraintLayoutInflate.findViewById(R.id.cb_wind_speed);
            if (LanUtils.isZhLanguage()) {
                setModeText(textView, this.mModeBean.getName()[i], this.mModeBean.getValue()[i]);
            } else {
                setModeText(textView, this.mModeBean.getNameEn()[i], this.mModeBean.getValue()[i]);
            }
            if (i == this.mModeSelectedItem) {
                relativeLayout.setScaleX(1.0f);
                relativeLayout.setScaleY(1.0f);
                textView.setTextColor(getResources().getColor(R.color.blue_text));
                setImageDrawable(imageView, this.mModeBean.getValue()[i], true);
            } else {
                relativeLayout.setScaleX(1.4f);
                relativeLayout.setScaleY(1.4f);
                textView.setTextColor(getResources().getColor(R.color.color_dark));
                setImageDrawable(imageView, this.mModeBean.getValue()[i], false);
            }
            constraintLayoutInflate.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.recycleview.DehumidifierModeView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (DehumidifierModeView.this.mIsEnable && DehumidifierModeView.this.mDehumidifierBean != null && DehumidifierModeView.this.mDehumidifierBean.checkIsPowerOn()) {
                        DehumidifierModeView.this.mModeSelectedItem = i;
                        if (DehumidifierModeView.this.mIOnClickItemListener != null) {
                            DehumidifierModeView.this.mIOnClickItemListener.onOnClickItem(i);
                        }
                        DehumidifierModeView.this.initView();
                    }
                }
            });
        }
    }

    protected void setModeText(TextView textView, String str, String str2) {
        textView.setText(str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0158  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void setImageDrawable(ImageView imageView, String str, boolean z) {
        char c;
        char c2;
        String str2 = this.mDeviceMode;
        String string2 = StubApp.getString2(1764);
        String string22 = StubApp.getString2(701);
        if (str2 != null && str2.equals(StubApp.getString2(13048))) {
            if (!z) {
                str.hashCode();
                if (str.equals(string22)) {
                    imageView.setBackgroundResource(R.drawable.icon_mode_manual_normal);
                    return;
                } else {
                    if (str.equals(string2)) {
                        imageView.setBackgroundResource(R.drawable.icon_mode_fresh_normal);
                        return;
                    }
                    return;
                }
            }
            str.hashCode();
            if (str.equals(string22)) {
                imageView.setBackgroundResource(R.drawable.icon_mode_manual_select);
                return;
            } else {
                if (str.equals(string2)) {
                    imageView.setBackgroundResource(R.drawable.icon_mode_fresh_select);
                    return;
                }
                return;
            }
        }
        String string23 = StubApp.getString2(8030);
        String string24 = StubApp.getString2(8412);
        String string25 = StubApp.getString2(8034);
        String string26 = StubApp.getString2(8092);
        String string27 = StubApp.getString2(7778);
        String string28 = StubApp.getString2(7188);
        String string29 = StubApp.getString2(2546);
        if (!z) {
            str.hashCode();
            switch (str.hashCode()) {
                case 48:
                    if (!str.equals(string22)) {
                        c2 = 65535;
                        break;
                    } else {
                        c2 = 0;
                        break;
                    }
                case 49:
                    if (str.equals(string29)) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 50:
                    if (str.equals(string2)) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 51:
                    if (str.equals(string28)) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 52:
                    if (str.equals(string27)) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 54:
                    if (str.equals(string26)) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 55:
                    if (str.equals(string25)) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 56:
                    if (str.equals(string24)) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 57:
                    if (str.equals(string23)) {
                        c2 = '\b';
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    imageView.setBackgroundResource(R.drawable.icon_mode_manual_normal);
                    break;
                case 1:
                    imageView.setBackgroundResource(R.drawable.icon_mode_drying_normal);
                    break;
                case 2:
                    imageView.setBackgroundResource(R.drawable.icon_mode_fresh_normal);
                    break;
                case 3:
                    imageView.setBackgroundResource(R.drawable.icon_mode_automatic_normal);
                    break;
                case 4:
                    imageView.setBackgroundResource(R.drawable.icon_mode_strong_normal);
                    break;
                case 5:
                    imageView.setBackgroundResource(R.drawable.icon_mode_sleep_normal);
                    break;
                case 6:
                    imageView.setBackgroundResource(R.drawable.icon_mode_fresh_normal);
                    break;
                case 7:
                    imageView.setBackgroundResource(R.drawable.icon_mode_fresh_normal);
                    break;
                case '\b':
                    imageView.setBackgroundResource(R.drawable.icon_mode_fresh_normal);
                    break;
            }
        }
        str.hashCode();
        switch (str.hashCode()) {
            case 48:
                if (!str.equals(string22)) {
                    c = 65535;
                    break;
                } else {
                    c = 0;
                    break;
                }
            case 49:
                if (str.equals(string29)) {
                    c = 1;
                    break;
                }
                break;
            case 50:
                if (str.equals(string2)) {
                    c = 2;
                    break;
                }
                break;
            case 51:
                if (str.equals(string28)) {
                    c = 3;
                    break;
                }
                break;
            case 52:
                if (str.equals(string27)) {
                    c = 4;
                    break;
                }
                break;
            case 54:
                if (str.equals(string26)) {
                    c = 5;
                    break;
                }
                break;
            case 55:
                if (str.equals(string25)) {
                    c = 6;
                    break;
                }
                break;
            case 56:
                if (str.equals(string24)) {
                    c = 7;
                    break;
                }
                break;
            case 57:
                if (str.equals(string23)) {
                    c = '\b';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                imageView.setBackgroundResource(R.drawable.icon_mode_manual_select);
                break;
            case 1:
                imageView.setBackgroundResource(R.drawable.icon_mode_drying_select);
                break;
            case 2:
                imageView.setBackgroundResource(R.drawable.icon_mode_fresh_select);
                break;
            case 3:
                imageView.setBackgroundResource(R.drawable.icon_mode_automatic_select);
                break;
            case 4:
                imageView.setBackgroundResource(R.drawable.icon_mode_strong_select);
                break;
            case 5:
                imageView.setBackgroundResource(R.drawable.icon_mode_sleep_select);
                break;
            case 6:
                imageView.setBackgroundResource(R.drawable.icon_mode_fresh_select);
                break;
            case 7:
                imageView.setBackgroundResource(R.drawable.icon_mode_fresh_select);
                break;
            case '\b':
                imageView.setBackgroundResource(R.drawable.icon_mode_fresh_select);
                break;
        }
    }

    public void setOnClickItemListener(IOnClickItemListener iOnClickItemListener) {
        this.mIOnClickItemListener = iOnClickItemListener;
    }

    protected Drawable getDrawable(String str) throws IOException {
        InputStream inputStreamOpen;
        try {
            inputStreamOpen = this.mContext.getAssets().open(str);
        } catch (IOException e) {
            e.printStackTrace();
            inputStreamOpen = null;
        }
        return Drawable.createFromStream(inputStreamOpen, null);
    }

    private int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, i, getContext().getResources().getDisplayMetrics());
    }
}
