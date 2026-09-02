package com.deye.views.recycleview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.location.support.bean.location.AmapLocationNetwork;
import com.deye.utils.LanUtils;
import com.mxchipapp.R;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HumidifierModeView.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J \u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000bH\u0014J \u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010H\u0015¨\u0006\u0016"}, d2 = {"Lcom/deye/views/recycleview/HumidifierModeView;", "Lcom/deye/views/recycleview/DehumidifierModeView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "setAvailable", "", "available", "", "setImageDrawable", "iv", "Landroid/widget/ImageView;", "mode", "", "isSelect", "setModeText", "textView", "Landroid/widget/TextView;", "text", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class HumidifierModeView extends DehumidifierModeView {
    @Override // com.deye.views.recycleview.DehumidifierModeView
    public /* bridge */ /* synthetic */ void setAvailable(Boolean bool) {
        setAvailable(bool.booleanValue());
    }

    public HumidifierModeView(Context context) {
        super(context);
    }

    public HumidifierModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setAvailable(boolean available) {
        setAlpha(available ? 1.0f : 0.4f);
        setEnable(available);
    }

    @Override // com.deye.views.recycleview.DehumidifierModeView
    protected void setImageDrawable(ImageView iv, String mode, boolean isSelect) {
        Intrinsics.checkNotNullParameter(iv, "iv");
        Intrinsics.checkNotNullParameter(mode, "mode");
        String string2 = StubApp.getString2(8095);
        String string22 = StubApp.getString2(7778);
        String string23 = StubApp.getString2(7188);
        String string24 = StubApp.getString2(1764);
        String string25 = StubApp.getString2(2546);
        String string26 = StubApp.getString2(13020);
        if (isSelect) {
            switch (mode.hashCode()) {
                case 49:
                    if (mode.equals(string25)) {
                        iv.setBackgroundResource(R.drawable.icon_mode_sleep_select);
                        break;
                    }
                    break;
                case 50:
                    if (mode.equals(string24)) {
                        if (!this.mDeviceMode.equals(string26)) {
                            iv.setBackgroundResource(R.drawable.icon_mode_continue_select);
                            break;
                        } else {
                            iv.setBackgroundResource(R.drawable.icon_mode_strong_select);
                            break;
                        }
                    }
                    break;
                case 51:
                    if (mode.equals(string23)) {
                        if (!this.mDeviceMode.equals(string26)) {
                            iv.setBackgroundResource(R.drawable.icon_mode_automatic_select);
                            break;
                        } else {
                            iv.setBackgroundResource(R.drawable.icon_mode_constant_select);
                            break;
                        }
                    }
                    break;
                case 52:
                    if (mode.equals(string22)) {
                        iv.setBackgroundResource(R.drawable.icon_mode_manual_select);
                        break;
                    }
                    break;
                case 53:
                    if (mode.equals(string2)) {
                        iv.setBackgroundResource(R.drawable.icon_mode_air_dry_select);
                        break;
                    }
                    break;
            }
        }
        switch (mode.hashCode()) {
            case 49:
                if (mode.equals(string25)) {
                    iv.setBackgroundResource(R.drawable.icon_mode_sleep_normal);
                    break;
                }
                break;
            case 50:
                if (mode.equals(string24)) {
                    if (!this.mDeviceMode.equals(string26)) {
                        iv.setBackgroundResource(R.drawable.icon_mode_continue_normal);
                        break;
                    } else {
                        iv.setBackgroundResource(R.drawable.icon_mode_strong_normal);
                        break;
                    }
                }
                break;
            case 51:
                if (mode.equals(string23)) {
                    if (!this.mDeviceMode.equals(string26)) {
                        iv.setBackgroundResource(R.drawable.icon_mode_automatic_normal);
                        break;
                    } else {
                        iv.setBackgroundResource(R.drawable.icon_mode_constant_normal);
                        break;
                    }
                }
                break;
            case 52:
                if (mode.equals(string22)) {
                    iv.setBackgroundResource(R.drawable.icon_mode_manual_normal);
                    break;
                }
                break;
            case 53:
                if (mode.equals(string2)) {
                    iv.setBackgroundResource(R.drawable.icon_mode_air_dry_normal);
                    break;
                }
                break;
        }
    }

    @Override // com.deye.views.recycleview.DehumidifierModeView
    protected void setModeText(TextView textView, String text, String mode) {
        Intrinsics.checkNotNullParameter(textView, "textView");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(mode, "mode");
        if (Intrinsics.areEqual(mode, AmapLocationNetwork.RESULT_TYPE_FUSED) && !this.mDeviceMode.equals(StubApp.getString2(13020))) {
            textView.setText(StubApp.getString2(14670));
            return;
        }
        if (LanUtils.isZhLanguage()) {
            textView.setText(text + StubApp.getString2(14671));
        }
        textView.setText(text);
    }
}
