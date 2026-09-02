package com.deye.utils;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class LocationUtils {
    static LocationUtils locationUtils;
    private final LocationListener locationListener = new LocationListener() { // from class: com.deye.utils.LocationUtils.1
        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
            Log.i(StubApp.getString2(14338), StubApp.getString2(14339));
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
            Log.i(StubApp.getString2(14338), StubApp.getString2(14340));
        }
    };
    private LocationManager locationManager;

    private enum permission {
        ACCESS_COARSE_LOCATION,
        ACCESS_FINE_LOCATION
    }

    public static LocationUtils getInstance() {
        if (locationUtils == null) {
            locationUtils = new LocationUtils();
        }
        return locationUtils;
    }

    public String getLocations(Context context) {
        String string2 = StubApp.getString2(14344);
        String string22 = StubApp.getString2(14345);
        String string23 = StubApp.getString2(14346);
        DecimalFormat decimalFormat = new DecimalFormat(StubApp.getString2(14347));
        if (!checkPermission(context, permission.ACCESS_COARSE_LOCATION)) {
            Toast.makeText(context, StubApp.getString2(14348), 0).show();
        }
        try {
            this.locationManager = (LocationManager) context.getSystemService(StubApp.getString2("6283"));
            Criteria criteria = new Criteria();
            criteria.setAccuracy(1);
            criteria.setSpeedRequired(true);
            criteria.setCostAllowed(true);
            criteria.setBearingRequired(true);
            criteria.setAltitudeRequired(true);
            criteria.setPowerRequirement(1);
            String bestProvider = this.locationManager.getBestProvider(criteria, true);
            Log.i(StubApp.getString2("14338"), string22 + bestProvider);
            Location lastKnownLocation = this.locationManager.getLastKnownLocation(bestProvider);
            if (lastKnownLocation == null) {
                return string23;
            }
            string23 = decimalFormat.format(lastKnownLocation.getLatitude()) + StubApp.getString2("450") + decimalFormat.format(lastKnownLocation.getLongitude());
            LogUtil.d(string2 + convertAddress(context, lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude()));
            return string23;
        } catch (SecurityException e) {
            e.printStackTrace();
            return string23;
        } catch (Exception e2) {
            e2.printStackTrace();
            return string23;
        }
    }

    public String convertAddress(Context context, double d, double d2) throws IOException {
        String string2 = StubApp.getString2(1521);
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        StringBuilder sb = new StringBuilder();
        try {
            List<Address> fromLocation = geocoder.getFromLocation(d, d2, 1);
            if (!fromLocation.isEmpty()) {
                Address address = fromLocation.get(0);
                sb.append(address.getCountryName()).append(string2).append(address.getAdminArea()).append(string2).append(address.getLocality());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private boolean checkPermission(Context context, permission permissionVar) {
        return context.checkCallingOrSelfPermission(new StringBuilder(StubApp.getString2(14343)).append(permissionVar.toString()).toString()) == 0;
    }
}
