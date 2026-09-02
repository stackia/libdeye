package io.fogcloud.sdk.fog.helper.loop_fan;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.LoopFanBean;
import io.fogcloud.sdk.fog.helper.AppHexCharHelper;
import io.fogcloud.sdk.fog.log.LogUtil;
import io.fogcloud.sdk.fog.utils.DigitalTrans;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class LoopFanBeanString2Hex {
    private static String TAG = StubApp.getString2(45147);

    /* JADX WARN: Removed duplicated region for block: B:52:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0235  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getHexString(String str) {
        char c;
        String strValueOf;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String string2;
        String str14;
        String string22 = StubApp.getString2(45130);
        LoopFanBean loopFanBean = (LoopFanBean) JSON.parseObject(str, LoopFanBean.class);
        LogUtil.d(TAG, StubApp.getString2(45131) + str);
        char[] cArr = new char[15];
        if (loopFanBean == null) {
            c = 0;
            strValueOf = "";
            str2 = strValueOf;
            str3 = str2;
            str4 = str3;
            str5 = str4;
            str6 = str5;
            str7 = str6;
            str8 = str7;
            str9 = str8;
            str10 = str9;
            str11 = str10;
            str12 = str11;
            str13 = str12;
        } else {
            String formaldehyde_removal = loopFanBean.getFormaldehyde_removal();
            String string23 = StubApp.getString2(701);
            boolean zEquals = string23.equals(formaldehyde_removal);
            String string24 = StubApp.getString2(2546);
            if (zEquals) {
                string2 = StubApp.getString2(2737);
            } else {
                string2 = string24.equals(loopFanBean.getFormaldehyde_removal()) ? StubApp.getString2(12883) : string23;
            }
            if (string23.equals(loopFanBean.getDeodorization())) {
                string2 = string2 + string23;
            } else if (string24.equals(loopFanBean.getDeodorization())) {
                string2 = string2 + string24;
            }
            if (string23.equals(loopFanBean.getSuper_natural_wind()) || TextUtils.isEmpty(loopFanBean.getSuper_natural_wind())) {
                string2 = string2 + string23;
            } else if (string24.equals(loopFanBean.getSuper_natural_wind())) {
                string2 = string2 + string24;
            }
            if (string23.equals(loopFanBean.getLamp_off())) {
                string2 = string2 + string23;
            } else if (string24.equals(loopFanBean.getLamp_off())) {
                string2 = string2 + string24;
            }
            if (string23.equals(loopFanBean.getDisplay_switch())) {
                string2 = string2 + string23;
            } else if (string24.equals(loopFanBean.getDisplay_switch())) {
                string2 = string2 + string24;
            }
            if (string23.equals(loopFanBean.getHumidification())) {
                string2 = string2 + string23;
            } else if (string24.equals(loopFanBean.getHumidification())) {
                string2 = string2 + string24;
            }
            if (string23.equals(loopFanBean.getSys_switch())) {
                string2 = string2 + string23;
            } else if (string24.equals(loopFanBean.getSys_switch())) {
                string2 = string2 + string24;
            }
            boolean zEquals2 = string23.equals(loopFanBean.getSpeed());
            String string25 = StubApp.getString2(45132);
            String string26 = StubApp.getString2(45133);
            String string27 = StubApp.getString2(7778);
            String string28 = StubApp.getString2(45134);
            String string29 = StubApp.getString2(7188);
            String string210 = StubApp.getString2(45135);
            String string211 = StubApp.getString2(1764);
            String string212 = StubApp.getString2(26813);
            if (!zEquals2) {
                if (string24.equals(loopFanBean.getSpeed())) {
                    str14 = string210;
                } else if (string211.equals(loopFanBean.getSpeed())) {
                    str14 = string28;
                } else if (string29.equals(loopFanBean.getSpeed())) {
                    str14 = string26;
                } else {
                    str14 = string27.equals(loopFanBean.getSpeed()) ? string25 : string212;
                }
                if (!string23.equals(loopFanBean.getWind_mode())) {
                    if (string24.equals(loopFanBean.getWind_mode())) {
                        string25 = string210;
                    } else if (string211.equals(loopFanBean.getWind_mode())) {
                        string25 = string28;
                    } else if (string29.equals(loopFanBean.getWind_mode())) {
                        string25 = string26;
                    } else if (!string27.equals(loopFanBean.getWind_mode())) {
                        string25 = string212;
                    }
                    LogUtil.d(StubApp.getString2(45148) + string2);
                    String strValueOf2 = String.valueOf(DigitalTrans.binaryToAlgorism(string2));
                    LogUtil.d(StubApp.getString2(45149) + strValueOf2 + StubApp.getString2(4590));
                    String strValueOf3 = String.valueOf(0);
                    String strValueOf4 = String.valueOf(DigitalTrans.binaryToAlgorism(str14.concat(string25)));
                    String strValueOf5 = String.valueOf(Integer.parseInt(loopFanBean.getHum_set()));
                    String strValueOf6 = String.valueOf(Integer.parseInt(loopFanBean.getTemperature_set()));
                    String strValueOf7 = String.valueOf(Integer.parseInt(loopFanBean.getPoweroff_hour()));
                    String strValueOf8 = String.valueOf(Integer.parseInt(loopFanBean.getPoweroff_minute()));
                    String strValueOf9 = String.valueOf(Integer.parseInt(loopFanBean.getPoweron_hour()));
                    String strValueOf10 = String.valueOf(Integer.parseInt(loopFanBean.getPoweron_minute()));
                    c = 0;
                    String strValueOf11 = String.valueOf(0);
                    String strValueOf12 = String.valueOf(0);
                    String strValueOf13 = String.valueOf(0);
                    strValueOf = String.valueOf(0);
                    str13 = strValueOf13;
                    str12 = strValueOf12;
                    str11 = strValueOf11;
                    str10 = strValueOf9;
                    str9 = strValueOf8;
                    str8 = strValueOf7;
                    str7 = strValueOf6;
                    str6 = strValueOf5;
                    str5 = strValueOf3;
                    str4 = strValueOf4;
                    str3 = strValueOf10;
                    str2 = strValueOf2;
                }
            }
        }
        try {
            cArr[c] = '\r';
            cArr[1] = 2;
            cArr[2] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(str2)];
            cArr[3] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(str5)];
            cArr[4] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(str4)];
            cArr[5] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(str6)];
            cArr[6] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(str7)];
            cArr[7] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(str8)];
            cArr[8] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(str9)];
            cArr[9] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(str10)];
            cArr[10] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(str3)];
            cArr[11] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(str11)];
            cArr[12] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(str12)];
            cArr[13] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(str13)];
            cArr[14] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf)];
            LogUtil.d(TAG, string22 + String.valueOf(cArr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str15 = TAG;
        StringBuilder sbAppend = new StringBuilder(StubApp.getString2(45150)).append(str2);
        String string213 = StubApp.getString2(1026);
        LogUtil.d(str15, sbAppend.append(string213).append(str5).append(string213).append(str4).append(string213).append(str6).append(string213).append(str7).append(string213).append(str8).append(string213).append(str9).append(string213).append(str10).append(string213).append(str3).append(string213).append(str11).append(string213).append(str12).append(string213).append(str13).append(string213).append(strValueOf).toString());
        return String.valueOf(cArr);
    }
}
