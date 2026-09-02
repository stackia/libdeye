package io.fogcloud.sdk.fog.helper.dehumidifier;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.helper.AppHexCharHelper;
import io.fogcloud.sdk.fog.utils.DigitalTrans;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class DehumidifierBeanString2Hex {
    private static String TAG = StubApp.getString2(45129);

    /* JADX WARN: Removed duplicated region for block: B:62:0x0220  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getHexString(String str) {
        char c;
        String strValueOf;
        String strValueOf2;
        String strValueOf3;
        String strValueOf4;
        String strValueOf5;
        String strValueOf6;
        String strValueOf7;
        String strValueOf8;
        String string2;
        String str2;
        String string22 = StubApp.getString2(45130);
        DehumidifierBean dehumidifierBean = (DehumidifierBean) JSON.parseObject(str, DehumidifierBean.class);
        Log.d(TAG, StubApp.getString2(45131) + str);
        char[] cArr = new char[10];
        if (dehumidifierBean == null) {
            c = 0;
            strValueOf = "";
            strValueOf2 = "";
            strValueOf3 = strValueOf2;
            strValueOf4 = strValueOf3;
            strValueOf5 = strValueOf4;
            strValueOf6 = strValueOf5;
            strValueOf7 = strValueOf6;
            strValueOf8 = strValueOf7;
        } else {
            String anion_switch = dehumidifierBean.getAnion_switch();
            String string23 = StubApp.getString2(701);
            boolean zEquals = string23.equals(anion_switch);
            String string24 = StubApp.getString2(2546);
            if (zEquals) {
                string2 = StubApp.getString2(2737);
            } else {
                string2 = string24.equals(dehumidifierBean.getAnion_switch()) ? StubApp.getString2(12883) : string23;
            }
            if (string23.equals(dehumidifierBean.getPump_switch())) {
                string2 = string2 + string23;
            } else if (string24.equals(dehumidifierBean.getPump_switch())) {
                string2 = string2 + string24;
            }
            if (string23.equals(dehumidifierBean.getPoweron_flag())) {
                string2 = string2 + string23;
            } else if (string24.equals(dehumidifierBean.getPoweron_flag())) {
                string2 = string2 + string24;
            }
            if (string23.equals(dehumidifierBean.getPoweroff_flag())) {
                string2 = string2 + string23;
            } else if (string24.equals(dehumidifierBean.getPoweroff_flag())) {
                string2 = string2 + string24;
            }
            if (string23.equals(dehumidifierBean.getLock_switch())) {
                string2 = string2 + string23;
            } else if (string24.equals(dehumidifierBean.getLock_switch())) {
                string2 = string2 + string24;
            }
            if (string23.equals(dehumidifierBean.getWind_switch())) {
                string2 = string2 + string23;
            } else if (string24.equals(dehumidifierBean.getWind_switch())) {
                string2 = string2 + string24;
            }
            if (string23.equals(dehumidifierBean.getSys_switch())) {
                string2 = string2 + string23;
            } else if (string24.equals(dehumidifierBean.getSys_switch())) {
                string2 = string2 + string24;
            }
            boolean zEquals2 = string24.equals(dehumidifierBean.getSpeed());
            String string25 = StubApp.getString2(45132);
            String string26 = StubApp.getString2(45133);
            String string27 = StubApp.getString2(7778);
            String string28 = StubApp.getString2(45134);
            String string29 = StubApp.getString2(7188);
            String string210 = StubApp.getString2(45135);
            String string211 = StubApp.getString2(1764);
            String string212 = StubApp.getString2(26813);
            if (zEquals2) {
                str2 = string210;
            } else if (string211.equals(dehumidifierBean.getSpeed())) {
                str2 = string28;
            } else if (string29.equals(dehumidifierBean.getSpeed())) {
                str2 = string26;
            } else {
                str2 = string27.equals(dehumidifierBean.getSpeed()) ? string25 : string212;
            }
            if (!string23.equals(dehumidifierBean.getMode())) {
                if (string24.equals(dehumidifierBean.getMode())) {
                    string25 = string210;
                } else if (string211.equals(dehumidifierBean.getMode())) {
                    string25 = string28;
                } else if (string29.equals(dehumidifierBean.getMode())) {
                    string25 = string26;
                } else if (!string27.equals(dehumidifierBean.getMode())) {
                    if (StubApp.getString2(8095).equals(dehumidifierBean.getMode())) {
                        string25 = StubApp.getString2(45136);
                    } else {
                        string25 = StubApp.getString2(8092).equals(dehumidifierBean.getMode()) ? StubApp.getString2(45137) : string212;
                    }
                }
                strValueOf = String.valueOf(DigitalTrans.binaryToAlgorism(string2));
                strValueOf3 = String.valueOf(DigitalTrans.binaryToAlgorism(str2.concat(string25)));
                strValueOf4 = String.valueOf(Integer.parseInt(dehumidifierBean.getHum_set()));
                strValueOf5 = String.valueOf(Integer.parseInt(dehumidifierBean.getPoweroff_hour()));
                strValueOf6 = String.valueOf(Integer.parseInt(dehumidifierBean.getPoweroff_minute()));
                strValueOf7 = String.valueOf(Integer.parseInt(dehumidifierBean.getPoweron_hour()));
                strValueOf2 = String.valueOf(Integer.parseInt(dehumidifierBean.getPoweron_minute()));
                c = 0;
                strValueOf8 = String.valueOf(0);
            }
        }
        try {
            cArr[c] = '\b';
            cArr[1] = 2;
            cArr[2] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf)];
            cArr[3] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf3)];
            cArr[4] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf4)];
            cArr[5] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf5)];
            cArr[6] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf6)];
            cArr[7] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf7)];
            cArr[8] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf2)];
            cArr[9] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf8)];
            Log.d(TAG, string22 + String.valueOf(cArr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(cArr);
    }
}
