package com.deye.helper;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.apis.utils.core.api.AMapUtilCoreApi;
import com.deye.MxchipApplication;
import com.deye.helper.LocationUtils;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class LocationStrategyImpl implements LocationStrategy {
    private static final String TAG = StubApp.getString2(14175);
    private LocationUtils.OnLocationChangeListener mListener;
    private AMapLocationClient mLocationClient;

    @Override // com.deye.helper.LocationStrategy
    public boolean register(long j, long j2, LocationUtils.OnLocationChangeListener onLocationChangeListener) {
        if (onLocationChangeListener == null) {
            return false;
        }
        this.mListener = onLocationChangeListener;
        try {
            if (this.mLocationClient == null) {
                this.mLocationClient = new AMapLocationClient(MxchipApplication.getInstance());
            }
            AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
            aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            aMapLocationClientOption.setOnceLocation(true);
            aMapLocationClientOption.setNeedAddress(true);
            aMapLocationClientOption.setHttpTimeOut(15000L);
            if (j > 0) {
                aMapLocationClientOption.setOnceLocation(false);
                aMapLocationClientOption.setInterval(j);
            }
            aMapLocationClientOption.setNeedAddress(true);
            this.mLocationClient.setLocationOption(aMapLocationClientOption);
            LogUtil.d(StubApp.getString2("13370"), StubApp.getString2("14176"));
            this.mLocationClient.setLocationListener(new AMapLocationListener() { // from class: com.deye.helper.LocationStrategyImpl.1
                @Override // com.amap.api.location.AMapLocationListener
                public void onLocationChanged(AMapLocation aMapLocation) {
                    if (aMapLocation == null) {
                        return;
                    }
                    if (aMapLocation.getErrorCode() != 0) {
                        Log.e(StubApp.getString2(14175), StubApp.getString2(14173) + aMapLocation.getErrorCode() + StubApp.getString2(14174) + aMapLocation.getErrorInfo());
                        return;
                    }
                    Location location = new Location(StubApp.getString2(8040));
                    location.setLatitude(aMapLocation.getLatitude());
                    location.setLongitude(aMapLocation.getLongitude());
                    location.setAccuracy(aMapLocation.getAccuracy());
                    location.setTime(aMapLocation.getTime());
                    Address addressConvertAmapAddress = LocationStrategyImpl.this.convertAmapAddress(aMapLocation);
                    if (LocationStrategyImpl.this.mListener != null) {
                        LocationStrategyImpl.this.mListener.getLastKnownLocation(location, addressConvertAmapAddress);
                    }
                }
            });
            this.mLocationClient.startLocation();
            return true;
        } catch (Exception e) {
            Log.e(StubApp.getString2(14175), StubApp.getString2(14177), e);
            return false;
        }
    }

    @Override // com.deye.helper.LocationStrategy
    public void unregister() {
        AMapLocationClient aMapLocationClient = this.mLocationClient;
        if (aMapLocationClient != null) {
            aMapLocationClient.stopLocation();
            this.mLocationClient.onDestroy();
            this.mLocationClient = null;
        }
        this.mListener = null;
    }

    @Override // com.deye.helper.LocationStrategy
    public Address getAddress(double d, double d2) throws IOException {
        try {
            List<Address> fromLocation = new Geocoder(MxchipApplication.getInstance(), Locale.getDefault()).getFromLocation(d, d2, 1);
            if (fromLocation == null || fromLocation.size() <= 0) {
                return null;
            }
            return fromLocation.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.deye.helper.LocationStrategy
    public void updatePrivacyAgree() {
        AMapUtilCoreApi.setCollectInfoEnable(true);
        AMapLocationClient.updatePrivacyAgree(MxchipApplication.getInstance(), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Address convertAmapAddress(AMapLocation aMapLocation) {
        if (aMapLocation.getCity() == null || aMapLocation.getCity().isEmpty()) {
            return null;
        }
        Address address = new Address(Locale.getDefault());
        address.setLatitude(aMapLocation.getLatitude());
        address.setLongitude(aMapLocation.getLongitude());
        address.setCountryName(aMapLocation.getCountry());
        address.setCountryCode(aMapLocation.getCountry());
        address.setAdminArea(aMapLocation.getProvince());
        address.setLocality(aMapLocation.getCity());
        address.setSubLocality(aMapLocation.getDistrict());
        address.setThoroughfare(aMapLocation.getStreet());
        address.setFeatureName(aMapLocation.getPoiName());
        address.setAddressLine(0, aMapLocation.getAddress());
        return address;
    }
}
