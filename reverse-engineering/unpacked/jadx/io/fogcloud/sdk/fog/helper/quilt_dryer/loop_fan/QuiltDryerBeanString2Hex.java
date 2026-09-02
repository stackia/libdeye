package io.fogcloud.sdk.fog.helper.quilt_dryer.loop_fan;

import com.alibaba.fastjson.JSON;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.QuiltDryerBean;
import io.fogcloud.sdk.fog.helper.AppHexCharHelper;
import io.fogcloud.sdk.fog.log.LogUtil;
import io.fogcloud.sdk.fog.utils.DigitalTrans;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class QuiltDryerBeanString2Hex {
    private static String TAG = StubApp.getString2(45153);

    public static String getTimestamp2(long j) {
        byte[] bArr = new byte[8];
        for (int i = 7; i >= 0; i--) {
            bArr[i] = (byte) (255 & j);
            j >>= 8;
        }
        bArr[0] = 7;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < 8; i2++) {
            stringBuffer.append(Integer.toHexString(bArr[i2]) + StubApp.getString2(626));
        }
        LogUtil.d(StubApp.getString2(13874), StubApp.getString2(45013) + stringBuffer.toString());
        return DigitalTrans.StringToAsciiString(bytesToHex(bArr));
    }

    private static String bytesToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(String.format(StubApp.getString2(2291), Byte.valueOf(b)));
        }
        return sb.toString();
    }

    public static String getTimestamp(long j) {
        char[] cArr = new char[8];
        for (int i = 0; i < 7; i++) {
            cArr[7 - i] = (char) (255 & j);
            j >>= 8;
        }
        cArr[0] = 7;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < 8; i2++) {
            stringBuffer.append(Integer.toHexString(cArr[i2]) + StubApp.getString2(626));
        }
        LogUtil.d(StubApp.getString2(13874), StubApp.getString2(45013) + stringBuffer.toString());
        return String.valueOf(cArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x02d5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getHexString(String str) {
        String strValueOf;
        String strValueOf2;
        String strValueOf3;
        String strValueOf4;
        String strValueOf5;
        String strValueOf6;
        String strValueOf7;
        String strValueOf8;
        String string2;
        String string22;
        String str2;
        String str3;
        String string23;
        String str4;
        String string24 = StubApp.getString2(45154);
        QuiltDryerBean quiltDryerBean = (QuiltDryerBean) JSON.parseObject(str, QuiltDryerBean.class);
        LogUtil.d(TAG, StubApp.getString2(45155) + str);
        char[] cArr = new char[10];
        if (quiltDryerBean == null) {
            strValueOf = "";
            strValueOf2 = strValueOf;
            strValueOf3 = strValueOf2;
            strValueOf4 = strValueOf3;
            strValueOf5 = strValueOf4;
            strValueOf6 = strValueOf5;
            strValueOf7 = strValueOf6;
            strValueOf8 = strValueOf7;
        } else {
            String lock_switch_down = quiltDryerBean.getLock_switch_down();
            String string25 = StubApp.getString2(701);
            boolean zEquals = string25.equals(lock_switch_down);
            String string26 = StubApp.getString2(2546);
            if (zEquals) {
                string2 = StubApp.getString2(26833);
            } else if (!string26.equals(quiltDryerBean.getLock_switch_down())) {
                string2 = StubApp.getString2(2737);
            } else {
                string2 = StubApp.getString2(33179);
            }
            if (string25.equals(quiltDryerBean.getSterilization_switch_down())) {
                string2 = string2 + string25;
            } else if (string26.equals(quiltDryerBean.getSterilization_switch_down())) {
                string2 = string2 + string26;
            }
            if (string25.equals(quiltDryerBean.getStandby_switch())) {
                string2 = string2 + string25;
            } else if (string26.equals(quiltDryerBean.getStandby_switch())) {
                string2 = string2 + string26;
            }
            if (string25.equals(quiltDryerBean.getLock_switch())) {
                string2 = string2 + string25;
            } else if (string26.equals(quiltDryerBean.getLock_switch())) {
                string2 = string2 + string26;
            }
            if (string25.equals(quiltDryerBean.getSterilization_switch())) {
                string2 = string2 + string25;
            } else if (string26.equals(quiltDryerBean.getSterilization_switch())) {
                string2 = string2 + string26;
            }
            if (string25.equals(quiltDryerBean.getSys_switch())) {
                string2 = string2 + string25;
            } else if (string26.equals(quiltDryerBean.getSys_switch())) {
                string2 = string2 + string26;
            }
            LogUtil.d(StubApp.getString2(45156) + string2);
            boolean zEquals2 = string25.equals(quiltDryerBean.getMode());
            String string27 = StubApp.getString2(45133);
            String string28 = StubApp.getString2(7188);
            String string29 = StubApp.getString2(45134);
            String string210 = StubApp.getString2(45135);
            String string211 = StubApp.getString2(1764);
            String string212 = StubApp.getString2(26813);
            if (!zEquals2) {
                if (string26.equals(quiltDryerBean.getMode())) {
                    string22 = string210;
                } else if (string211.equals(quiltDryerBean.getMode())) {
                    string22 = string29;
                } else if (string28.equals(quiltDryerBean.getMode())) {
                    string22 = string27;
                } else {
                    string22 = StubApp.getString2(7778).equals(quiltDryerBean.getMode()) ? StubApp.getString2(45132) : string212;
                }
                String strConcat = string212.concat(string22);
                LogUtil.d(StubApp.getString2(45157) + strConcat);
                if (!string25.equals(quiltDryerBean.getBakingQuilt())) {
                    if (string26.equals(quiltDryerBean.getBakingQuilt())) {
                        str2 = string210;
                    } else if (string211.equals(quiltDryerBean.getBakingQuilt())) {
                        str2 = string29;
                    } else {
                        str2 = string28.equals(quiltDryerBean.getBakingQuilt()) ? string27 : string212;
                    }
                    if (string25.equals(quiltDryerBean.getWarm())) {
                        str3 = string27;
                    } else {
                        if (string26.equals(quiltDryerBean.getWarm())) {
                            str3 = string27;
                            string23 = string210;
                        } else if (string211.equals(quiltDryerBean.getWarm())) {
                            str3 = string27;
                            string23 = string29;
                        } else {
                            str3 = string27;
                            if (StubApp.getString2(8095).equals(quiltDryerBean.getWarm())) {
                                string23 = StubApp.getString2(45136);
                            } else if (StubApp.getString2(8092).equals(quiltDryerBean.getWarm())) {
                                string23 = StubApp.getString2(45137);
                            }
                        }
                        String strConcat2 = str2.concat(string23);
                        LogUtil.d(StubApp.getString2(45158) + strConcat2);
                        if (!string25.equals(quiltDryerBean.getWarmQuilt())) {
                            if (string26.equals(quiltDryerBean.getWarmQuilt())) {
                                str4 = string210;
                            } else {
                                str4 = string211.equals(quiltDryerBean.getWarmQuilt()) ? string29 : string212;
                            }
                            if (!string25.equals(quiltDryerBean.getAcarusKilling())) {
                                if (string26.equals(quiltDryerBean.getAcarusKilling())) {
                                    string212 = string210;
                                } else if (string211.equals(quiltDryerBean.getAcarusKilling())) {
                                    string212 = string29;
                                } else if (string28.equals(quiltDryerBean.getAcarusKilling())) {
                                    string212 = str3;
                                }
                            }
                            String strConcat3 = str4.concat(string212);
                            LogUtil.d(StubApp.getString2(45159) + strConcat3);
                            strValueOf2 = String.valueOf(DigitalTrans.binaryToAlgorism(string2));
                            strValueOf3 = String.valueOf(DigitalTrans.binaryToAlgorism(strConcat));
                            strValueOf4 = String.valueOf(DigitalTrans.binaryToAlgorism(strConcat2));
                            strValueOf = String.valueOf(DigitalTrans.binaryToAlgorism(strConcat3));
                            strValueOf5 = String.valueOf(DigitalTrans.binaryToAlgorism(""));
                            strValueOf6 = String.valueOf(DigitalTrans.binaryToAlgorism(""));
                            strValueOf7 = String.valueOf(DigitalTrans.binaryToAlgorism(""));
                            strValueOf8 = String.valueOf(DigitalTrans.binaryToAlgorism(""));
                        }
                    }
                    string23 = string212;
                    String strConcat22 = str2.concat(string23);
                    LogUtil.d(StubApp.getString2(45158) + strConcat22);
                    if (!string25.equals(quiltDryerBean.getWarmQuilt())) {
                    }
                }
            }
        }
        try {
            cArr[0] = '\b';
            cArr[1] = 2;
            cArr[2] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf2)];
            cArr[3] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf3)];
            cArr[4] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf4)];
            cArr[5] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf)];
            cArr[6] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf5)];
            cArr[7] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf6)];
            cArr[8] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf7)];
            cArr[9] = AppHexCharHelper.HEX_CHAR[Integer.parseInt(strValueOf8)];
            LogUtil.d(TAG, string24 + String.valueOf(cArr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str5 = TAG;
        StringBuilder sbAppend = new StringBuilder(StubApp.getString2(45160)).append(strValueOf2);
        String string213 = StubApp.getString2(1026);
        LogUtil.d(str5, sbAppend.append(string213).append(strValueOf3).append(string213).append(strValueOf4).append(string213).append(strValueOf).append(string213).append(strValueOf5).append(string213).append(strValueOf6).append(string213).append(strValueOf7).append(string213).append(strValueOf8).toString());
        return String.valueOf(cArr);
    }
}
