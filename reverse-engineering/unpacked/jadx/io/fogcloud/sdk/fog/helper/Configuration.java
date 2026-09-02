package io.fogcloud.sdk.fog.helper;

import com.stub.StubApp;
import com.ut.device.AidConstants;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class Configuration {
    public static ApiHostType HOST_TYPE = ApiHostType.PRODUCTION_HOST_API;
    public static String _APIHOST;
    public static String _PRODUCTION_HOST;
    public static String _PRODUCTION_HOST_OVERSEA;
    public static String _TEST_HOST;
    public static String _TEST_HOST_OVERSEA;
    private static boolean isOversea;

    public enum ApiHostType {
        PRODUCTION_HOST_API,
        TEST_HOST_API
    }

    static {
        String string2 = StubApp.getString2(45045);
        _PRODUCTION_HOST = string2;
        _TEST_HOST = StubApp.getString2(45046);
        _PRODUCTION_HOST_OVERSEA = StubApp.getString2(45047);
        _TEST_HOST_OVERSEA = StubApp.getString2(45048);
        _APIHOST = string2;
        isOversea = false;
    }

    private static String _URLHEAD() {
        return getApihost() + StubApp.getString2(45110);
    }

    private static String _URL_APK_HEAD() {
        return getApihost() + StubApp.getString2(45111);
    }

    private static String _URL_APP_HEAD() {
        return getApihost() + StubApp.getString2(20302);
    }

    public static String getApihost() {
        return _APIHOST;
    }

    private static String _COOKBOOK() {
        return getApihost() + StubApp.getString2(45108);
    }

    public static String _SCHEDULETASK() {
        return getApihost() + StubApp.getString2(45109);
    }

    public static String LOGININ() {
        return _URLHEAD() + StubApp.getString2(45083);
    }

    public static String GETVERCODE() {
        return _URLHEAD() + StubApp.getString2(45075);
    }

    public static String GETMQTTINFO() {
        return _URLHEAD() + StubApp.getString2(45071);
    }

    public static String CHECKVERCODE() {
        return _URLHEAD() + StubApp.getString2(45057);
    }

    public static String REFRESHTOKEN() {
        return _URLHEAD() + StubApp.getString2(45091);
    }

    public static String VERIFYTOKEN() {
        return _URLHEAD() + StubApp.getString2(45107);
    }

    public static String GETSHAREVERCODE() {
        return _URLHEAD() + StubApp.getString2(45073);
    }

    public static String ADDDEVBYVERCODE() {
        return _URLHEAD() + StubApp.getString2(45051);
    }

    public static String BINDDEVICE() {
        return _URLHEAD() + StubApp.getString2(45054);
    }

    public static String BIND_BLUETOOTH_DEVICE() {
        return _URLHEAD() + StubApp.getString2(45055);
    }

    public static String PRODUCTLIST() {
        return _URLHEAD() + StubApp.getString2(45089);
    }

    public static String MQTTINFO() {
        return _URLHEAD() + StubApp.getString2(45071);
    }

    public static String UNBINDDEVICE() {
        return _URLHEAD() + StubApp.getString2(45101);
    }

    public static String SENDCOMMAND() {
        return _URLHEAD() + StubApp.getString2(45096);
    }

    public static String SENDCOMMANDADV() {
        return _URLHEAD() + StubApp.getString2(45097);
    }

    public static String RESETPASSWORD() {
        return _URLHEAD() + StubApp.getString2(45093);
    }

    public static String UPDATEBINDROLE() {
        return _URLHEAD() + StubApp.getString2(45102);
    }

    public static String REMOVEBINDROLE() {
        return _URLHEAD() + StubApp.getString2(45092);
    }

    public static String TRANSFERADMIN() {
        return _URLHEAD() + StubApp.getString2(45100);
    }

    public static String GETDEVICELIST() {
        return _URLHEAD() + StubApp.getString2(45069);
    }

    public static String GETMEMBERLIST() {
        return _URLHEAD() + StubApp.getString2(45070);
    }

    public static String GETPROPERTIES() {
        return _URLHEAD() + StubApp.getString2(45072);
    }

    public static String SETPROPERTIES() {
        return _URLHEAD() + StubApp.getString2(45098);
    }

    public static String DEVICEINFO() {
        return _URLHEAD() + StubApp.getString2(45064);
    }

    public static String FOGMQTTINFO() {
        return _URLHEAD() + StubApp.getString2(45066);
    }

    public static String ONLINESTATUS() {
        return _URLHEAD() + StubApp.getString2(45087);
    }

    public static String NOWWEATHER() {
        return _URLHEAD() + StubApp.getString2(45086);
    }

    public static String OUTDOOR() {
        return _URLHEAD() + StubApp.getString2(45076);
    }

    public static String ADDSUBDEV() {
        return _URLHEAD() + StubApp.getString2(45052);
    }

    public static String SENDCMDSUB() {
        return _URLHEAD() + StubApp.getString2(45095);
    }

    public static String UPDATEDEVALIAS() {
        return _URLHEAD() + StubApp.getString2(45103);
    }

    public static String BINDCHECK() {
        return _URLHEAD() + StubApp.getString2(45053);
    }

    public static String GETCOOKBOOKLIST() {
        return _COOKBOOK() + StubApp.getString2(45068);
    }

    public static String GETCOOKBOOKINFO() {
        return _COOKBOOK() + StubApp.getString2(45067);
    }

    public static String ADDCOOKBOOKLIKENO() {
        return _COOKBOOK() + StubApp.getString2(45050);
    }

    public static String ADDCOOKBOOKFAVONO() {
        return _COOKBOOK() + StubApp.getString2(45049);
    }

    public static String DELCOOKBOOKLIKENO() {
        return _COOKBOOK() + StubApp.getString2(45060);
    }

    public static String SEARCHCOOKBOOKBYNAME() {
        return _COOKBOOK() + StubApp.getString2(45094);
    }

    public static String CBTYPEINFOLIST() {
        return _COOKBOOK() + StubApp.getString2(45056);
    }

    public static String getTopic(String str, String str2, String str3) {
        StringBuilder sbAppend = new StringBuilder().append(str);
        String string2 = StubApp.getString2(AidConstants.EVENT_REQUEST_FAILED);
        return sbAppend.append(string2).append(str2).append(string2).append(str3).append(StubApp.getString2(45113)).toString();
    }

    public static String getOnlineTopic(String str, String str2, String str3) {
        StringBuilder sbAppend = new StringBuilder().append(str);
        String string2 = StubApp.getString2(AidConstants.EVENT_REQUEST_FAILED);
        return sbAppend.append(string2).append(str2).append(string2).append(str3).append(StubApp.getString2(45112)).toString();
    }

    public static String MQTTHOST() {
        String apihost = getApihost();
        String string2 = StubApp.getString2(910);
        if (apihost.indexOf(string2) <= -1) {
            return StubApp.getString2(45085);
        }
        return getApihost().substring(getApihost().indexOf(string2) + 2);
    }

    public static String PUSH_SWITCH() {
        return getApihost() + StubApp.getString2(45090);
    }

    public static String CLEAN_ACCOUNT() {
        return getApihost() + StubApp.getString2(45059);
    }

    public static String SUBMIT_ISSUE() {
        return getApihost() + StubApp.getString2(45099);
    }

    public static String DEVICE_VERSION() {
        return getApihost() + StubApp.getString2(45065);
    }

    public static String USERINFO() {
        return _URLHEAD() + StubApp.getString2(45074);
    }

    public static String UPDATE_USERAVATAR() {
        return _URLHEAD() + StubApp.getString2(45105);
    }

    public static String GETUSERINFO() {
        return _URLHEAD() + StubApp.getString2(45074);
    }

    public static String LOGOUT() {
        return _URLHEAD() + StubApp.getString2(45084);
    }

    public static String GETWEATHER() {
        return _URLHEAD() + StubApp.getString2(45076);
    }

    public static String UPDATEVERSION() {
        return _URLHEAD() + StubApp.getString2(45104);
    }

    public static String CHECK_APK_INTEGRITY() {
        return _URL_APK_HEAD() + StubApp.getString2(45058);
    }

    public static String GET_MESSAGE_LIST() {
        return _URL_APP_HEAD() + StubApp.getString2(45081);
    }

    public static String GET_MESSAGE_IS_ALL_READ() {
        return _URL_APP_HEAD() + StubApp.getString2(45080);
    }

    public static String GET_BOOT_IMAGE() {
        return _URL_APP_HEAD() + StubApp.getString2(45079);
    }

    public static String GET_BANNER_VIEW_LIST() {
        return _URL_APP_HEAD() + StubApp.getString2(45078);
    }

    public static String GET_ADVERTISEMENT_INFO() {
        return _URL_APP_HEAD() + StubApp.getString2(45077);
    }

    public static String POST_MESSAGE_READ() {
        return _URL_APP_HEAD() + StubApp.getString2(45088);
    }

    public static String DELETE_MESSAGE_DELETE() {
        return _URL_APP_HEAD() + StubApp.getString2(45062);
    }

    public static String DELETE_MESSAGE_DELETE_BATCH() {
        return _URL_APP_HEAD() + StubApp.getString2(45063);
    }

    public static String DELETE_DEVICE_MESSAGE_DELETE_BATCH() {
        return _URL_APP_HEAD() + StubApp.getString2(45061);
    }

    public static String UPLOAD_CLIENT_ID() {
        return _URLHEAD() + StubApp.getString2(45106);
    }

    public static String IS_LOAD_URL_BY_BROWSER() {
        return _URLHEAD() + StubApp.getString2(45082);
    }

    public static void setOversea(boolean z) {
        isOversea = z;
        setHost(HOST_TYPE);
    }

    public static void setHost(ApiHostType apiHostType) {
        HOST_TYPE = apiHostType;
        if (ApiHostType.TEST_HOST_API.equals(apiHostType)) {
            _APIHOST = isOversea ? _TEST_HOST_OVERSEA : _TEST_HOST;
        } else if (ApiHostType.PRODUCTION_HOST_API.equals(apiHostType)) {
            _APIHOST = isOversea ? _PRODUCTION_HOST_OVERSEA : _PRODUCTION_HOST;
        }
    }
}
