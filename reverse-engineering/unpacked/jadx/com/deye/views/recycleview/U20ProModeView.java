package com.deye.views.recycleview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.deye.entity.control_panel.dehumidifier.func.ModeBean;
import com.deye.utils.ChannelUtil;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class U20ProModeView extends LinearLayout {
    private static final String[] DEHUMIDIFY_MODE_NAMES_CN;
    private static final String[] DEHUMIDIFY_MODE_NAMES_EN;
    private static final String[] PURIFY_MODE_NAMES_CN;
    private static final String[] PURIFY_MODE_NAMES_EN;
    private LinearLayout llModeContainer;
    private Context mContext;
    private int mCurrentCategory;
    protected DehumidifierBean mDehumidifierBean;
    protected String mDeviceMode;
    private IOnClickItemListener mIOnClickItemListener;
    private boolean mIsEnable;
    private ModeBean mModeBean;
    private int mModeSelectedItem;
    private TextView tvCategoryDehumidify;
    private TextView tvCategoryPurify;
    private static final String[] DEHUMIDIFY_MODES = {StubApp.getString2(7188), StubApp.getString2(701), StubApp.getString2(2546), StubApp.getString2(8092)};
    private static final String[] PURIFY_MODES = {StubApp.getString2(8030), StubApp.getString2(8034), StubApp.getString2(8412)};

    public interface IOnClickItemListener {
        void onModeSelected(String str);
    }

    static {
        String string2 = StubApp.getString2(14514);
        String string22 = StubApp.getString2(14673);
        String string23 = StubApp.getString2(14675);
        DEHUMIDIFY_MODE_NAMES_CN = new String[]{string2, string22, StubApp.getString2(14674), string23};
        String string24 = StubApp.getString2(14676);
        String string25 = StubApp.getString2(14677);
        String string26 = StubApp.getString2(13065);
        DEHUMIDIFY_MODE_NAMES_EN = new String[]{string24, string25, StubApp.getString2(14678), string26};
        PURIFY_MODE_NAMES_CN = new String[]{string2, string22, string23};
        PURIFY_MODE_NAMES_EN = new String[]{string24, string25, string26};
    }

    public U20ProModeView(Context context) {
        super(context);
        this.mIsEnable = true;
        this.mModeSelectedItem = -1;
        this.mCurrentCategory = 0;
        init(context);
    }

    public U20ProModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsEnable = true;
        this.mModeSelectedItem = -1;
        this.mCurrentCategory = 0;
        this.mContext = context;
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        setOrientation(1);
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
        if (dehumidifierBean.checkIsPowerOn()) {
            determineCategoryAndMode(mode);
        }
        initView();
    }

    private void determineCategoryAndMode(String str) {
        int i = 0;
        int i2 = 0;
        while (true) {
            String[] strArr = DEHUMIDIFY_MODES;
            if (i2 >= strArr.length) {
                while (true) {
                    String[] strArr2 = PURIFY_MODES;
                    if (i >= strArr2.length) {
                        return;
                    }
                    if (strArr2[i].equals(str)) {
                        this.mCurrentCategory = 1;
                        this.mModeSelectedItem = i;
                        return;
                    }
                    i++;
                }
            } else {
                if (strArr[i2].equals(str)) {
                    this.mCurrentCategory = 0;
                    this.mModeSelectedItem = i2;
                    return;
                }
                i2++;
            }
        }
    }

    public void initData(ModeBean modeBean, String str) {
        this.mModeBean = modeBean;
        this.mDeviceMode = str;
        initView();
    }

    private void initView() {
        removeAllViews();
        setBackground(getResources().getDrawable(R.drawable.bg_u20_mode));
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.u20pro_mode_category, (ViewGroup) this, false);
        addView(viewInflate);
        this.tvCategoryDehumidify = (TextView) viewInflate.findViewById(R.id.tv_category_dehumidify);
        this.tvCategoryPurify = (TextView) viewInflate.findViewById(R.id.tv_category_purify);
        this.tvCategoryDehumidify.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.recycleview.U20ProModeView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$0(view);
            }
        });
        this.tvCategoryPurify.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.recycleview.U20ProModeView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$1(view);
            }
        });
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        this.llModeContainer = linearLayout;
        linearLayout.setOrientation(0);
        this.llModeContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        addView(this.llModeContainer);
        updateCategoryUI();
        updateModeButtons();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(View view) {
        switchCategory(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$1(View view) {
        switchCategory(1);
    }

    private void switchCategory(int i) {
        DehumidifierBean dehumidifierBean;
        if (this.mIsEnable && (dehumidifierBean = this.mDehumidifierBean) != null && dehumidifierBean.checkIsPowerOn()) {
            if (this.mCurrentCategory != i) {
                this.mCurrentCategory = i;
                this.mModeSelectedItem = 1;
                updateCategoryUI();
                updateModeButtons();
            }
            IOnClickItemListener iOnClickItemListener = this.mIOnClickItemListener;
            if (iOnClickItemListener != null) {
                iOnClickItemListener.onModeSelected(i == 0 ? StubApp.getString2(701) : StubApp.getString2(8034));
            }
        }
    }

    private void updateCategoryUI() {
        if (this.mCurrentCategory == 0) {
            this.tvCategoryDehumidify.setTextColor(this.mContext.getColor(R.color.blue_text));
            this.tvCategoryDehumidify.setBackgroundResource(R.drawable.bg_u20_mode_item);
            this.tvCategoryDehumidify.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_dehumidify_select, 0, 0, 0);
        } else {
            this.tvCategoryDehumidify.setTextColor(this.mContext.getColor(R.color.color_dark));
            this.tvCategoryDehumidify.setBackground(null);
            this.tvCategoryDehumidify.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_dehumidify_normal, 0, 0, 0);
        }
        if (this.mCurrentCategory == 1) {
            this.tvCategoryPurify.setTextColor(this.mContext.getColor(R.color.blue_text));
            this.tvCategoryPurify.setBackgroundResource(R.drawable.bg_u20_mode_item);
            this.tvCategoryPurify.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_purify_select, 0, 0, 0);
        } else {
            this.tvCategoryPurify.setTextColor(this.mContext.getColor(R.color.color_dark));
            this.tvCategoryPurify.setBackground(null);
            this.tvCategoryPurify.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_purify_normal, 0, 0, 0);
        }
    }

    private void updateModeButtons() {
        this.llModeContainer.removeAllViews();
        final String[] currentCategoryModes = getCurrentCategoryModes();
        String[] currentCategoryModeNames = getCurrentCategoryModeNames();
        int length = currentCategoryModes.length;
        final int i = 0;
        while (i < length) {
            View view = (ConstraintLayout) LayoutInflater.from(this.mContext).inflate(R.layout.ry_mode_item, (ViewGroup) this.llModeContainer, false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
            layoutParams.weight = 1.0f;
            view.setLayoutParams(layoutParams);
            this.llModeContainer.addView(view);
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_ry_mode_root);
            TextView textView = (TextView) view.findViewById(R.id.tv_wind_speed);
            ImageView imageView = (ImageView) view.findViewById(R.id.cb_wind_speed);
            textView.setText(currentCategoryModeNames[i]);
            boolean z = i == this.mModeSelectedItem;
            if (z) {
                relativeLayout.setScaleX(1.0f);
                relativeLayout.setScaleY(1.0f);
            } else {
                relativeLayout.setScaleX(1.4f);
                relativeLayout.setScaleY(1.4f);
            }
            setImageDrawable(imageView, currentCategoryModes[i], z);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.recycleview.U20ProModeView$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$updateModeButtons$2(i, currentCategoryModes, view2);
                }
            });
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateModeButtons$2(int i, String[] strArr, View view) {
        DehumidifierBean dehumidifierBean;
        if (this.mIsEnable && (dehumidifierBean = this.mDehumidifierBean) != null && dehumidifierBean.checkIsPowerOn()) {
            this.mModeSelectedItem = i;
            IOnClickItemListener iOnClickItemListener = this.mIOnClickItemListener;
            if (iOnClickItemListener != null) {
                iOnClickItemListener.onModeSelected(strArr[i]);
            }
            updateModeButtons();
        }
    }

    private String[] getCurrentCategoryModes() {
        return this.mCurrentCategory == 0 ? DEHUMIDIFY_MODES : PURIFY_MODES;
    }

    private String[] getCurrentCategoryModeNames() {
        return ChannelUtil.isOversea() ? this.mCurrentCategory == 0 ? DEHUMIDIFY_MODE_NAMES_EN : PURIFY_MODE_NAMES_EN : this.mCurrentCategory == 0 ? DEHUMIDIFY_MODE_NAMES_CN : PURIFY_MODE_NAMES_CN;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void setImageDrawable(ImageView imageView, String str, boolean z) {
        char c = 6;
        String string2 = StubApp.getString2(8030);
        String string22 = StubApp.getString2(8412);
        String string23 = StubApp.getString2(8034);
        String string24 = StubApp.getString2(8092);
        String string25 = StubApp.getString2(7188);
        String string26 = StubApp.getString2(2546);
        String string27 = StubApp.getString2(701);
        if (!z) {
            str.hashCode();
            switch (str.hashCode()) {
                case 48:
                    if (!str.equals(string27)) {
                        c = 65535;
                        break;
                    } else {
                        c = 0;
                        break;
                    }
                case 49:
                    if (str.equals(string26)) {
                        c = 1;
                        break;
                    }
                    break;
                case 51:
                    if (str.equals(string25)) {
                        c = 2;
                        break;
                    }
                    break;
                case 54:
                    if (str.equals(string24)) {
                        c = 3;
                        break;
                    }
                    break;
                case 55:
                    if (str.equals(string23)) {
                        c = 4;
                        break;
                    }
                    break;
                case 56:
                    if (str.equals(string22)) {
                        c = 5;
                        break;
                    }
                    break;
                case 57:
                    if (!str.equals(string2)) {
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 4:
                    imageView.setBackgroundResource(R.drawable.icon_mode_manual_normal);
                    break;
                case 1:
                    imageView.setBackgroundResource(R.drawable.icon_mode_drying_normal);
                    break;
                case 2:
                case 6:
                    imageView.setBackgroundResource(R.drawable.icon_mode_automatic_normal);
                    break;
                case 3:
                case 5:
                    imageView.setBackgroundResource(R.drawable.icon_mode_sleep_normal);
                    break;
            }
        }
        str.hashCode();
        switch (str.hashCode()) {
            case 48:
                if (!str.equals(string27)) {
                    c = 65535;
                    break;
                } else {
                    c = 0;
                    break;
                }
            case 49:
                if (str.equals(string26)) {
                    c = 1;
                    break;
                }
                break;
            case 51:
                if (str.equals(string25)) {
                    c = 2;
                    break;
                }
                break;
            case 54:
                if (str.equals(string24)) {
                    c = 3;
                    break;
                }
                break;
            case 55:
                if (str.equals(string23)) {
                    c = 4;
                    break;
                }
                break;
            case 56:
                if (str.equals(string22)) {
                    c = 5;
                    break;
                }
                break;
            case 57:
                if (!str.equals(string2)) {
                }
                break;
        }
        switch (c) {
            case 0:
            case 4:
                imageView.setBackgroundResource(R.drawable.icon_mode_manual_select);
                break;
            case 1:
                imageView.setBackgroundResource(R.drawable.icon_mode_drying_select);
                break;
            case 2:
            case 6:
                imageView.setBackgroundResource(R.drawable.icon_mode_automatic_select);
                break;
            case 3:
            case 5:
                imageView.setBackgroundResource(R.drawable.icon_mode_sleep_select);
                break;
        }
    }

    public void setOnClickItemListener(IOnClickItemListener iOnClickItemListener) {
        this.mIOnClickItemListener = iOnClickItemListener;
    }
}
