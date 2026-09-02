package com.deye.utils;

import com.deye.entity.UserInfoBean;
import com.google.gson.Gson;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import io.fogcloud.sdk.fog.bean.GlobalConfigBean;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MMKVUtils.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010$\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001eJ\u000e\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u0004J\b\u0010\"\u001a\u0004\u0018\u00010#J\u0006\u0010$\u001a\u00020%J\u0010\u0010&\u001a\u0004\u0018\u00010\u00042\u0006\u0010!\u001a\u00020\u0004J\b\u0010'\u001a\u0004\u0018\u00010\u0004J\u0006\u0010(\u001a\u00020\u0011J\u000e\u0010)\u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u0004J\b\u0010*\u001a\u0004\u0018\u00010+J\b\u0010,\u001a\u0004\u0018\u00010\u0004J\u000e\u0010-\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u0004J\u000e\u0010.\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u0004J\u000e\u0010/\u001a\u00020%2\u0006\u00100\u001a\u00020\u0004J\u000e\u00101\u001a\u00020%2\u0006\u00102\u001a\u00020\u001eJ\u0006\u00103\u001a\u00020%J\u0016\u00104\u001a\u00020%2\u0006\u0010!\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u001bJ\u000e\u00106\u001a\u00020%2\u0006\u00107\u001a\u00020#J\u000e\u00108\u001a\u00020%2\u0006\u00109\u001a\u00020\u0004J\u0006\u0010:\u001a\u00020%J\u0016\u0010;\u001a\u00020%2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u0004J\u000e\u0010=\u001a\u00020%2\u0006\u0010>\u001a\u00020\u0004J\u000e\u0010?\u001a\u00020%2\u0006\u0010@\u001a\u00020\u0011J\u0016\u0010A\u001a\u00020%2\u0006\u0010!\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u001bJ\u0010\u0010B\u001a\u00020%2\b\u0010C\u001a\u0004\u0018\u00010+J\u001a\u0010D\u001a\u00020%2\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040FJ\u0016\u0010G\u001a\u00020%2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010H\u001a\u00020\u0011J\u0016\u0010I\u001a\u00020%2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006K"}, d2 = {"Lcom/deye/utils/MMKVUtils;", "", "()V", "AD_INFO", "", "AD_SHOW_TIME", "APP_VERSION", "GLOBAL_CONFIG", "KEY_BANNER_HIDDEN", "KEY_DEVICE_INFO", "KEY_DEVICE_LIST", "KEY_GLOBAL_CONFIG", "KEY_IS_SHOW_STORAGE_DIALOG", "KEY_LOCATION", "PREF_TEMPERATURE_UNIT", "SHARE_CONTENT", "TEMPERATURE_CELSIUS", "", "TEMPERATURE_FAHRENHEIT", "USER_AGREE_POLICY", "USER_NICKNAME", "USER_TOKEN", "Y16_HUM_SET", "Y16_SPEED", "key_display", "key_tone", "checkAgreePolicy", "", "getAdInfo", "getAdShowTime", "", "getBannerHideTime", "getDisplayScreen", "deviceId", "getGlobalConfig", "Lio/fogcloud/sdk/fog/bean/GlobalConfigBean;", "getLocationInfo", "", "getPropertyResult", "getShareContent", "getTemperatureUnit", "getTone", "getUserInfo", "Lcom/deye/entity/UserInfoBean;", "getWifiPwd", "getY16HumSet", "getY16Speed", "setAdInfo", "info", "setAdShowTime", "time", "setAgreePolicy", "setDisplayScreen", "check", "setGlobalConfig", "configBean", "setLocationInfo", "locationInfo", "setMineBannerHide", "setPropertyResult", "json", "setShareContent", "text", "setTemperatureUnit", "unit", "setTone", "setUserInfo", "bean", "setWIfiPwd", "map", "", "setY16HumSet", "humSet", "setY16Speed", "speed", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class MMKVUtils {
    public static final int TEMPERATURE_CELSIUS = 0;
    public static final int TEMPERATURE_FAHRENHEIT = 1;
    public static final String AD_INFO = StubApp.getString2(14349);
    public static final String AD_SHOW_TIME = StubApp.getString2(14350);
    public static final String APP_VERSION = StubApp.getString2(7195);
    public static final String GLOBAL_CONFIG = StubApp.getString2(14351);
    public static final String KEY_BANNER_HIDDEN = StubApp.getString2(14352);
    public static final String KEY_DEVICE_INFO = StubApp.getString2(14353);
    public static final String KEY_DEVICE_LIST = StubApp.getString2(14354);
    public static final String KEY_GLOBAL_CONFIG = StubApp.getString2(14355);
    public static final String KEY_IS_SHOW_STORAGE_DIALOG = StubApp.getString2(14356);
    public static final String KEY_LOCATION = StubApp.getString2(14357);
    public static final String PREF_TEMPERATURE_UNIT = StubApp.getString2(14358);
    public static final String SHARE_CONTENT = StubApp.getString2(14359);
    public static final String USER_AGREE_POLICY = StubApp.getString2(14360);
    public static final String USER_NICKNAME = StubApp.getString2(14361);
    public static final String USER_TOKEN = StubApp.getString2(13134);
    public static final String Y16_HUM_SET = StubApp.getString2(14362);
    public static final String Y16_SPEED = StubApp.getString2(14363);
    public static final String key_display = StubApp.getString2(14364);
    public static final String key_tone = StubApp.getString2(14365);
    public static final MMKVUtils INSTANCE = new MMKVUtils();

    private MMKVUtils() {
    }

    public final UserInfoBean getUserInfo() {
        return (UserInfoBean) MMKV.defaultMMKV().decodeParcelable(StubApp.getString2(14366), UserInfoBean.class);
    }

    public final void setUserInfo(UserInfoBean bean) {
        MMKV.defaultMMKV().encode(StubApp.getString2(14366), bean);
    }

    public final void setAgreePolicy() {
        MMKV.defaultMMKV().encode(StubApp.getString2(14360), true);
    }

    public final boolean checkAgreePolicy() {
        return MMKV.defaultMMKV().decodeBool(StubApp.getString2(14360), false);
    }

    public final String getAdInfo() {
        return MMKV.defaultMMKV().decodeString(StubApp.getString2(14349), "");
    }

    public final long getAdShowTime() {
        return MMKV.defaultMMKV().decodeLong(StubApp.getString2(14350));
    }

    public final void setAdShowTime(long time) {
        MMKV.defaultMMKV().encode(StubApp.getString2(14350), time);
    }

    public final void setAdInfo(String info) {
        Intrinsics.checkNotNullParameter(info, "info");
        MMKV.defaultMMKV().encode(StubApp.getString2(14349), info);
    }

    public final void setY16HumSet(String deviceId, int humSet) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        MMKV.mmkvWithID(deviceId).encode(StubApp.getString2(14362), humSet);
    }

    public final int getY16HumSet(String deviceId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        return MMKV.mmkvWithID(deviceId).decodeInt(StubApp.getString2(14362));
    }

    public final void setY16Speed(String deviceId, int speed) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        MMKV.mmkvWithID(deviceId).encode(StubApp.getString2(14363), speed);
    }

    public final int getY16Speed(String deviceId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        return MMKV.mmkvWithID(deviceId).decodeInt(StubApp.getString2(14363));
    }

    public final void setDisplayScreen(String deviceId, boolean check) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        MMKV.mmkvWithID(deviceId).encode(StubApp.getString2(14364), check);
    }

    public final boolean getDisplayScreen(String deviceId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        return MMKV.mmkvWithID(deviceId).decodeBool(StubApp.getString2(14364));
    }

    public final void setTone(String deviceId, boolean check) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        MMKV.mmkvWithID(deviceId).encode(StubApp.getString2(14365), check);
    }

    public final boolean getTone(String deviceId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        return MMKV.mmkvWithID(deviceId).decodeBool(StubApp.getString2(14365));
    }

    public final void setShareContent(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        MMKV.defaultMMKV().encode(StubApp.getString2(14359), text);
    }

    public final String getShareContent() {
        return MMKV.defaultMMKV().decodeString(StubApp.getString2(14359));
    }

    public final void setPropertyResult(String deviceId, String json) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(json, "json");
        MMKV.mmkvWithID(StubApp.getString2(14353)).encode(deviceId, json);
    }

    public final String getPropertyResult(String deviceId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        return MMKV.mmkvWithID(StubApp.getString2(14353)).decodeString(deviceId);
    }

    public final void setGlobalConfig(GlobalConfigBean configBean) {
        Intrinsics.checkNotNullParameter(configBean, "configBean");
        MMKV.mmkvWithID(StubApp.getString2(14351)).encode(StubApp.getString2(14355), configBean);
    }

    public final GlobalConfigBean getGlobalConfig() {
        return (GlobalConfigBean) MMKV.mmkvWithID(StubApp.getString2(14351)).decodeParcelable(StubApp.getString2(14355), GlobalConfigBean.class);
    }

    public final void setMineBannerHide() {
        MMKV.defaultMMKV().encode(StubApp.getString2(14352), System.currentTimeMillis());
    }

    public final long getBannerHideTime() {
        return MMKV.defaultMMKV().decodeLong(StubApp.getString2(14352));
    }

    public final void setLocationInfo(String locationInfo) {
        Intrinsics.checkNotNullParameter(locationInfo, "locationInfo");
        MMKV.defaultMMKV().encode(StubApp.getString2(14357), locationInfo);
    }

    public final void getLocationInfo() {
        MMKV.defaultMMKV().decodeString(StubApp.getString2(14357), "");
    }

    public final void setWIfiPwd(Map<String, String> map) throws RuntimeException {
        Intrinsics.checkNotNullParameter(map, "map");
        MMKV.mmkvWithID(StubApp.getString2(14368), 1, StubApp.getString2(14367)).encode(StubApp.getString2(9413), new Gson().toJson(map));
    }

    public final String getWifiPwd() {
        return MMKV.mmkvWithID(StubApp.getString2(14368), 1, StubApp.getString2(14367)).decodeString(StubApp.getString2(9413), "");
    }

    public final void setTemperatureUnit(int unit) {
        MMKV.defaultMMKV().encode(StubApp.getString2(14358), unit);
    }

    public final int getTemperatureUnit() {
        return MMKV.defaultMMKV().decodeInt(StubApp.getString2(14358), 0);
    }
}
