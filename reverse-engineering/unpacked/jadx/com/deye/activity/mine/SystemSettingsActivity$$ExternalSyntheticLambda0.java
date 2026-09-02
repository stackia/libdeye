package com.deye.activity.mine;

import android.content.DialogInterface;

/* compiled from: D8$$SyntheticClass */
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final /* synthetic */ class SystemSettingsActivity$$ExternalSyntheticLambda0 implements DialogInterface.OnClickListener {
    public final /* synthetic */ SystemSettingsActivity f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ SystemSettingsActivity$$ExternalSyntheticLambda0(SystemSettingsActivity systemSettingsActivity, int i) {
        this.f$0 = systemSettingsActivity;
        this.f$1 = i;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        SystemSettingsActivity.showLanguagePicker$lambda$1$lambda$0(this.f$0, this.f$1, dialogInterface, i);
    }
}
