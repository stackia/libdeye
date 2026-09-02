package com.deye.helper;

import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import com.amap.location.type.location.Location;
import com.deye.MxchipApplication;
import com.stub.StubApp;
import com.umeng.socialize.net.dplus.CommonNetImpl;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class LocationUtils {
    private static final int TWO_MINUTES = 120000;
    private static final LocationStrategy sStrategy = new LocationStrategyImpl();

    public interface OnLocationChangeListener {
        void getLastKnownLocation(Location location, Address address);

        void onLocationChanged(Location location, Address address);

        void onStatusChanged(String str, int i, Bundle bundle);
    }

    private LocationUtils() {
        throw new UnsupportedOperationException(StubApp.getString2(10194));
    }

    public static boolean isGpsEnabled() {
        return ((LocationManager) MxchipApplication.getInstance().getSystemService(StubApp.getString2(6283))).isProviderEnabled(StubApp.getString2(7482));
    }

    public static boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) MxchipApplication.getInstance().getSystemService(StubApp.getString2(6283));
        return locationManager.isProviderEnabled(StubApp.getString2(45)) || locationManager.isProviderEnabled(StubApp.getString2(7482));
    }

    public static void openGpsSettings() {
        MxchipApplication.getInstance().startActivity(new Intent(StubApp.getString2(14178)).addFlags(CommonNetImpl.FLAG_AUTH));
    }

    public static boolean register(long j, long j2, OnLocationChangeListener onLocationChangeListener) {
        return sStrategy.register(j, j2, onLocationChangeListener);
    }

    public static void updatePrivacyAgree() {
        sStrategy.updatePrivacyAgree();
    }

    public static void unregister() {
        sStrategy.unregister();
    }

    public static Address getAddress(double d, double d2) {
        return sStrategy.getAddress(d, d2);
    }

    public static String getCountryName(double d, double d2) {
        Address address = getAddress(d, d2);
        return address == null ? StubApp.getString2(Location.SubType.GNSS_NEMA_INS) : address.getCountryName();
    }

    public static String getLocality(double d, double d2) {
        Address address = getAddress(d, d2);
        return address == null ? StubApp.getString2(Location.SubType.GNSS_NEMA_INS) : address.getLocality();
    }

    public static String getStreet(double d, double d2) {
        Address address = getAddress(d, d2);
        return address == null ? StubApp.getString2(Location.SubType.GNSS_NEMA_INS) : address.getAddressLine(0);
    }

    public static boolean isBetterLocation(android.location.Location location, android.location.Location location2) {
        if (location2 == null) {
            return true;
        }
        long time = location.getTime() - location2.getTime();
        boolean z = time > 120000;
        boolean z2 = time < -120000;
        boolean z3 = time > 0;
        if (z) {
            return true;
        }
        if (z2) {
            return false;
        }
        int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
        boolean z4 = accuracy > 0;
        boolean z5 = accuracy < 0;
        boolean z6 = accuracy > 200;
        boolean zIsSameProvider = isSameProvider(location.getProvider(), location2.getProvider());
        if (z5) {
            return true;
        }
        if (!z3 || z4) {
            return z3 && !z6 && zIsSameProvider;
        }
        return true;
    }

    public static boolean isSameProvider(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }
}
