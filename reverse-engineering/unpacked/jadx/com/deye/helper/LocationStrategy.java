package com.deye.helper;

import android.location.Address;
import com.deye.helper.LocationUtils;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public interface LocationStrategy {
    public static final String TAG = "DeyeLocation";

    Address getAddress(double d, double d2);

    boolean register(long j, long j2, LocationUtils.OnLocationChangeListener onLocationChangeListener);

    void unregister();

    void updatePrivacyAgree();
}
