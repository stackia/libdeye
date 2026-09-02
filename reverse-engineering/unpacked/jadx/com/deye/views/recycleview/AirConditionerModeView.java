package com.deye.views.recycleview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.mxchipapp.R;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class AirConditionerModeView extends DehumidifierModeView {
    public AirConditionerModeView(Context context) {
        super(context);
    }

    public AirConditionerModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x002d  */
    @Override // com.deye.views.recycleview.DehumidifierModeView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void setImageDrawable(ImageView imageView, String str, boolean z) {
        char c = 3;
        String string2 = StubApp.getString2(7188);
        String string22 = StubApp.getString2(1764);
        String string23 = StubApp.getString2(2546);
        String string24 = StubApp.getString2(701);
        if (!z) {
            str.hashCode();
            switch (str.hashCode()) {
                case 48:
                    if (!str.equals(string24)) {
                        c = 65535;
                        break;
                    } else {
                        c = 0;
                        break;
                    }
                case 49:
                    if (str.equals(string23)) {
                        c = 1;
                        break;
                    }
                    break;
                case 50:
                    if (str.equals(string22)) {
                        c = 2;
                        break;
                    }
                    break;
                case 51:
                    if (!str.equals(string2)) {
                    }
                    break;
            }
            switch (c) {
                case 0:
                    imageView.setBackgroundResource(R.drawable.icon_mode_refrigeration_unselect);
                    break;
                case 1:
                    imageView.setBackgroundResource(R.drawable.icon_mode_heating_unselect);
                    break;
                case 2:
                    imageView.setBackgroundResource(R.drawable.icon_mode_dehumidification_unselect);
                    break;
                case 3:
                    imageView.setBackgroundResource(R.drawable.icon_mode_blast_unselect);
                    break;
            }
        }
        str.hashCode();
        switch (str.hashCode()) {
            case 48:
                if (!str.equals(string24)) {
                    c = 65535;
                    break;
                } else {
                    c = 0;
                    break;
                }
            case 49:
                if (str.equals(string23)) {
                    c = 1;
                    break;
                }
                break;
            case 50:
                if (str.equals(string22)) {
                    c = 2;
                    break;
                }
                break;
            case 51:
                if (!str.equals(string2)) {
                }
                break;
        }
        switch (c) {
            case 0:
                imageView.setBackgroundResource(R.drawable.icon_mode_refrigeration_select);
                break;
            case 1:
                imageView.setBackgroundResource(R.drawable.icon_mode_heating_select);
                break;
            case 2:
                imageView.setBackgroundResource(R.drawable.icon_mode_dehumidification_select);
                break;
            case 3:
                imageView.setBackgroundResource(R.drawable.icon_mode_blast_select);
                break;
        }
    }
}
