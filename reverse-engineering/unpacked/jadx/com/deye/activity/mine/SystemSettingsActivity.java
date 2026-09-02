package com.deye.activity.mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.databinding.ActivitySystemSettingsBinding;
import com.stub.StubApp;
import kotlin.Metadata;

/* compiled from: SystemSettingsActivity.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0005H\u0002J\b\u0010\u000e\u001a\u00020\nH\u0002J\b\u0010\u000f\u001a\u00020\nH\u0002J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0017J\u0012\u0010\u0013\u001a\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\nH\u0002J\b\u0010\u0017\u001a\u00020\nH\u0002J\b\u0010\u0018\u001a\u00020\nH\u0002J\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J\b\u0010\u001a\u001a\u00020\nH\u0002J\b\u0010\u001b\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/deye/activity/mine/SystemSettingsActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "currentLanguage", "", "currentTemperatureUnit", "mBinding", "Lcom/mxchipapp/databinding/ActivitySystemSettingsBinding;", "changeLanguage", "", "language", "changeTemperatureUnit", "unit", "initView", "loadCurrentSettings", "onClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "restartApp", "showLanguagePicker", "showTemperaturePicker", "syncLocaleToServer", "updateLanguageDisplay", "updateTemperatureDisplay", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class SystemSettingsActivity extends BaseActivity implements View.OnClickListener {
    private int currentLanguage;
    private int currentTemperatureUnit;
    private ActivitySystemSettingsBinding mBinding;

    static {
        StubApp.interface11(14439);
    }

    private final native void changeLanguage(int language);

    private final native void changeTemperatureUnit(int unit);

    private final native void initView();

    private final native void loadCurrentSettings();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void restartApp();

    private final native void showLanguagePicker();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void showLanguagePicker$lambda$1(Integer[] numArr, SystemSettingsActivity systemSettingsActivity, int i, int i2, int i3, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void showLanguagePicker$lambda$1$lambda$0(SystemSettingsActivity systemSettingsActivity, int i, DialogInterface dialogInterface, int i2);

    private final native void showTemperaturePicker();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void showTemperaturePicker$lambda$2(Integer[] numArr, SystemSettingsActivity systemSettingsActivity, int i, int i2, int i3, View view);

    private final native void syncLocaleToServer(int language);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void syncLocaleToServer$lambda$3(SystemSettingsActivity systemSettingsActivity);

    private final native void updateLanguageDisplay();

    private final native void updateTemperatureDisplay();

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);
}
