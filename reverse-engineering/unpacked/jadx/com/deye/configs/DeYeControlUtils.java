package com.deye.configs;

import android.app.Activity;
import com.deye.ErrorCodeMap;
import com.deye.MyActivityManager;
import com.mxchipapp.R;
import com.stub.StubApp;
import java.util.LinkedList;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class DeYeControlUtils {
    public static final long LOOP_QUERY_DEVICE_STATE_PERIOD_3s = 10000;
    public static final int SEND_COMMAND_DIFF_TIME = 500;
    public static final int humidifier_speed_default_flag = 2;
    public static final int quilt_dryer_mode_default_flag = -2;
    public static final String A1 = StubApp.getString2(13669);
    public static final String A2 = StubApp.getString2(13670);
    public static final String A3 = StubApp.getString2(13671);
    public static final String A4 = StubApp.getString2(13672);
    public static final String A5 = StubApp.getString2(13673);
    public static final String DEVICE_LIST_ITEM_DEBUG_SHOW = StubApp.getString2(1764);
    public static final String DEVICE_LIST_ITEM_DIS_SHOW = StubApp.getString2(2546);
    public static final String DEVICE_LIST_ITEM_SHOW = StubApp.getString2(701);
    public static final String DJB_S10B_W = StubApp.getString2(13008);
    public static final String DJC_R40B_W = StubApp.getString2(13019);
    public static final String DYD_6158EB = StubApp.getString2(13016);
    public static final String DYD_ES25A3 = StubApp.getString2(13050);
    public static final String DYD_P40A3 = StubApp.getString2(13011);
    public static final String DYD_SC16 = StubApp.getString2(13006);
    public static final String DYD_SC25 = StubApp.getString2(13005);
    public static final String DYD_SC60Y = StubApp.getString2(13010);
    public static final String DYD_Y12 = StubApp.getString2(13031);
    public static final String DYD_Y16 = StubApp.getString2(13030);
    public static final String DYD_Y20 = StubApp.getString2(13029);
    public static final String DYD_Y25 = StubApp.getString2(13028);
    public static final String DYP_A12A452 = StubApp.getString2(13024);
    public static final String DY_612S = StubApp.getString2(13027);
    public static final String DY_6138A = StubApp.getString2(13017);
    public static final String DY_620S = StubApp.getString2(13026);
    public static final String DY_8138C = StubApp.getString2(13015);
    public static final String DY_8138D = StubApp.getString2(13014);
    public static final String DY_8138T = StubApp.getString2(13013);
    public static final String DY_890C = StubApp.getString2(13033);
    public static final String DY_890T = StubApp.getString2(13032);
    public static final String DY_A06A4 = StubApp.getString2(13049);
    public static final String DY_A10 = StubApp.getString2(13021);
    public static final String DY_B12A3 = StubApp.getString2(13023);
    public static final String DY_B13A3 = StubApp.getString2(13022);
    public static final String DY_C65DZ = StubApp.getString2(13048);
    public static final String DY_D50A3 = StubApp.getString2(13047);
    public static final String DY_D50B3 = StubApp.getString2(13046);
    public static final String DY_E12A3 = StubApp.getString2(13045);
    public static final String DY_F15 = StubApp.getString2(13004);
    public static final String DY_F15Pro = StubApp.getString2(13018);
    public static final String DY_G25A3 = StubApp.getString2(13044);
    public static final String DY_H7 = StubApp.getString2(13020);
    public static final String DY_N20A3 = StubApp.getString2(13043);
    public static final String DY_P30 = StubApp.getString2(13003);
    public static final String DY_RLS48A3 = StubApp.getString2(13051);
    public static final String DY_RT12 = StubApp.getString2(13007);
    public static final String DY_T22A3 = StubApp.getString2(13042);
    public static final String DY_TM208 = StubApp.getString2(13041);
    public static final String DY_TM208_QRCode = StubApp.getString2(13012);
    public static final String DY_U20A3 = StubApp.getString2(13040);
    public static final String DY_U20Pro = StubApp.getString2(13009);
    public static final String DY_U2OAIR = StubApp.getString2(13025);
    public static final String DY_V58A3 = StubApp.getString2(13039);
    public static final String DY_V60A3 = StubApp.getString2(13038);
    public static final String DY_W20A3 = StubApp.getString2(13037);
    public static final String DY_X20A3 = StubApp.getString2(13036);
    public static final String DY_Z12A3 = StubApp.getString2(13035);
    public static final String DY_Z20B3 = StubApp.getString2(13034);
    public static final String E0 = StubApp.getString2(13499);
    public static final String E1 = StubApp.getString2(13522);
    public static final String E10 = StubApp.getString2(13959);
    public static final String E2 = StubApp.getString2(13523);
    public static final String E3 = StubApp.getString2(13524);
    public static final String E4 = StubApp.getString2(13525);
    public static final String E5 = StubApp.getString2(13500);
    public static final String E6 = StubApp.getString2(13501);
    public static final String E7 = StubApp.getString2(13574);
    public static final String E8 = StubApp.getString2(13526);
    public static final String E9 = StubApp.getString2(13575);
    public static final String EA = StubApp.getString2(13576);
    public static final String EB = StubApp.getString2(13577);
    public static final String EC = StubApp.getString2(13578);
    public static final String EE = StubApp.getString2(13579);
    public static final String F1 = StubApp.getString2(13527);
    public static final String F2 = StubApp.getString2(13528);
    public static final String F3 = StubApp.getString2(13529);
    public static final String F4 = StubApp.getString2(13530);
    public static final String F5 = StubApp.getString2(13531);
    public static final String F6 = StubApp.getString2(13532);
    public static final String FL = StubApp.getString2(13957);
    public static final String HS = StubApp.getString2(13958);
    public static final String HUMIDITY_INFO_DEHUMIDIFIER = StubApp.getString2(13960);
    public static final String HUMIDITY_INFO_KEY = StubApp.getString2(13491);
    public static final String HUMIDITY_INFO_QUILT_DRYER = StubApp.getString2(13492);
    public static final String L0 = StubApp.getString2(13677);
    public static final String L1 = StubApp.getString2(11411);
    public static final String L10 = StubApp.getString2(13961);
    public static final String L2 = StubApp.getString2(11410);
    public static final String L3 = StubApp.getString2(11260);
    public static final String L4 = StubApp.getString2(13502);
    public static final String L5 = StubApp.getString2(13962);
    public static final String L6 = StubApp.getString2(13580);
    public static final String L7 = StubApp.getString2(13963);
    public static final String L8 = StubApp.getString2(13964);
    public static final String L9 = StubApp.getString2(13965);
    public static final String P0 = StubApp.getString2(13503);
    public static final String P1 = StubApp.getString2(13504);
    public static final String P10 = StubApp.getString2(13966);
    public static final String P2 = StubApp.getString2(13533);
    public static final String P3 = StubApp.getString2(13534);
    public static final String P4 = StubApp.getString2(13505);
    public static final String P5 = StubApp.getString2(13506);
    public static final String P6 = StubApp.getString2(13507);
    public static final String P7 = StubApp.getString2(13581);
    public static final String P8 = StubApp.getString2(13582);
    public static final String P9 = StubApp.getString2(13967);
    public static final String PL = StubApp.getString2(7573);
    public static final String air_wind_auto = StubApp.getString2(8095);
    public static final String air_wind_mute = StubApp.getString2(2546);
    public static final String d1 = StubApp.getString2(13519);
    public static final String d2 = StubApp.getString2(13520);
    public static final String d3 = StubApp.getString2(13521);
    public static final String d4 = StubApp.getString2(13968);
    public static final String loop_fan_mode_auto = StubApp.getString2(701);
    public static final String loop_fan_mode_automatic = StubApp.getString2(7778);
    public static final String loop_fan_mode_deodorization = StubApp.getString2(13814);
    public static final String loop_fan_mode_disable = StubApp.getString2(13807);
    public static final String loop_fan_mode_disable_gif = StubApp.getString2(13805);
    public static final String loop_fan_mode_disable_png = StubApp.getString2(13806);
    public static final String loop_fan_mode_error_gif = StubApp.getString2(8483);
    public static final String loop_fan_mode_error_png = StubApp.getString2(7693);
    public static final String loop_fan_mode_formaldehyde_removal = StubApp.getString2(13813);
    public static final String loop_fan_mode_humidification = StubApp.getString2(13812);
    public static final String mode_air_purification = StubApp.getString2(1764);
    public static final String mode_auto_purification = StubApp.getString2(8030);
    public static final String mode_automatic = StubApp.getString2(7188);
    public static final String mode_blast = StubApp.getString2(7188);
    public static final String mode_continued = StubApp.getString2(8095);
    public static final String mode_dehumidification = StubApp.getString2(1764);
    public static final String mode_dehumidify = StubApp.getString2(701);
    public static final String mode_dry_shoes = StubApp.getString2(7778);
    public static final String mode_drying = StubApp.getString2(2546);
    public static final String mode_heating = StubApp.getString2(2546);
    public static final String mode_humidifier_air_dry = StubApp.getString2(8095);
    public static final String mode_humidifier_continue = StubApp.getString2(1764);
    public static final String mode_humidifier_manual = StubApp.getString2(7778);
    public static final String mode_humidifier_sleep = StubApp.getString2(2546);
    public static final String mode_humidifier_smart = StubApp.getString2(7188);
    public static final String mode_manual_purification = StubApp.getString2(8034);
    public static final String mode_refrigeration = StubApp.getString2(701);
    public static final String mode_sleep = StubApp.getString2(8092);
    public static final String mode_sleep_purification = StubApp.getString2(8412);
    public static final String mode_turbo = StubApp.getString2(7778);
    public static final String quilt_dryer_mode_acarusKilling = StubApp.getString2(7188);
    public static final String quilt_dryer_mode_bakingQuilt = StubApp.getString2(1764);
    public static final String quilt_dryer_mode_no = StubApp.getString2(701);
    public static final String quilt_dryer_mode_warm = StubApp.getString2(2546);
    public static final String quilt_dryer_mode_warmQuilt = StubApp.getString2(7778);
    public static final String quilt_dryer_mode_warm_high_two_hour = StubApp.getString2(8092);
    public static final String quilt_dryer_mode_warm_low_two_hour = StubApp.getString2(8095);
    public static final String quilt_dryer_mode_warm_two_hour_flag = StubApp.getString2(7778);
    public static final String switch_off = StubApp.getString2(701);
    public static final String switch_on = StubApp.getString2(2546);
    public static final String wind_full = StubApp.getString2(7778);
    public static final String wind_high = StubApp.getString2(7188);
    public static final String wind_low = StubApp.getString2(2546);
    public static final String wind_middle = StubApp.getString2(1764);
    public static final String wind_no = StubApp.getString2(701);

    public static int getQuiltDryerAcarusKillingValue(int i) {
        if (i == 0) {
            i = 1;
        }
        return i * 30;
    }

    public static int getQuiltDryerBakingQuiltValue(int i) {
        if (i == 0) {
            i = 1;
        }
        return i * 30;
    }

    public static String getErrorText(String str) {
        Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity == null) {
            return String.format(StubApp.getString2(13956), str);
        }
        String string = currentActivity.getString(R.string.machine_fault_detail, str);
        if (str.equals(StubApp.getString2(13957))) {
            return currentActivity.getString(R.string.water_full_stop_fl);
        }
        if (str.equals(StubApp.getString2(13958))) {
            return currentActivity.getString(R.string.low_temp_defrost_hs);
        }
        return str.equals(StubApp.getString2(13579)) ? currentActivity.getString(R.string.fault_code_ee) : string;
    }

    public static String getErrorText(String str, String str2) {
        if (StubApp.getString2(13432).equals(str2)) {
            ErrorCodeMap<String, String> a06A4ErrorMap = ErrorMapUtils.getA06A4ErrorMap(MyActivityManager.getInstance().getCurrentActivity());
            if (a06A4ErrorMap.containsKey(str)) {
                return a06A4ErrorMap.get(str);
            }
        }
        return getErrorText(str);
    }

    public static String getCurrentEnvTemp(String str) throws NumberFormatException {
        try {
            int i = Integer.parseInt(str);
            if (i == 0) {
                return "25℃";
            }
            return (i - 40) + StubApp.getString2("13800");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getLoopFanCurrentEnvTemp(String str) throws NumberFormatException {
        try {
            int i = Integer.parseInt(str);
            if (i == 0) {
                return "25";
            }
            return String.valueOf(i - 40);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getHumText(int i) {
        Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity == null) {
            return "";
        }
        if (i < 51) {
            return currentActivity.getString(R.string.indoor_humidity_dry_label);
        }
        if (i > 50 && i < 66) {
            return currentActivity.getString(R.string.indoor_humidity_comfortable_label);
        }
        if (i <= 65) {
            return "";
        }
        return currentActivity.getString(R.string.indoor_humidity_humid_label);
    }

    public static String getLoopFanTempText(int i) {
        Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity == null) {
            return "";
        }
        return currentActivity.getString(R.string.indoor_temperature_label);
    }

    public static String getAdviceLevel(int i) {
        Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity == null) {
            return "";
        }
        if (i < 51) {
            return currentActivity.getString(R.string.humidity_advice_dry_power_save);
        }
        if (i > 50 && i < 66) {
            return currentActivity.getString(R.string.humidity_advice_comfortable_body);
        }
        if (i <= 65) {
            return "";
        }
        return currentActivity.getString(R.string.humidity_advice_humid_high_fan);
    }

    public static String getLoopFanAdviceLevel(int i) {
        Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity == null) {
            return "";
        }
        if (i < 51) {
            return currentActivity.getString(R.string.humidity_advice_dry_power_save);
        }
        if (i > 50 && i < 66) {
            return currentActivity.getString(R.string.humidity_advice_comfortable_body);
        }
        if (i <= 65) {
            return "";
        }
        return currentActivity.getString(R.string.humidity_advice_humid_high_fan);
    }

    public static LinkedList<String> getDehumidifierHumidityTipList() {
        Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity == null) {
            return new LinkedList<>();
        }
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add(currentActivity.getString(R.string.humidity_tip_bedroom_range));
        linkedList.add(currentActivity.getString(R.string.humidity_tip_living_room_range));
        linkedList.add(currentActivity.getString(R.string.humidity_tip_kitchen_range));
        linkedList.add(currentActivity.getString(R.string.humidity_tip_bathroom_range));
        linkedList.add(currentActivity.getString(R.string.humidity_tip_basement_range));
        return linkedList;
    }

    public static LinkedList<String> getQuiltDryerBakingQuiltTipList() {
        Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity == null) {
            return new LinkedList<>();
        }
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add(currentActivity.getString(R.string.quilt_dryer_tip_30min_preheat));
        linkedList.add(currentActivity.getString(R.string.quilt_dryer_tip_60min_dry));
        linkedList.add(currentActivity.getString(R.string.quilt_dryer_tip_90min_fully_dry));
        return linkedList;
    }

    public static int getQuiltDryerAcarusKillingIndex(int i) {
        return i / 30;
    }

    public static int getQuiltDryerBakingQuiltIndex(int i) {
        return i / 30;
    }
}
