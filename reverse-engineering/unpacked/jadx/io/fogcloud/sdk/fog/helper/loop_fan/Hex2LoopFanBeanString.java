package io.fogcloud.sdk.fog.helper.loop_fan;

import com.alibaba.fastjson.JSON;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.LoopFanBean;
import io.fogcloud.sdk.fog.helper.Hex2DeviceBeanString;
import io.fogcloud.sdk.fog.log.LogUtil;
import io.fogcloud.sdk.fog.utils.DigitalTrans;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class Hex2LoopFanBeanString {
    private static String TAG = StubApp.getString2(45146);

    public static LoopFanBean getLoopFanBeanString(JSONObject jSONObject) {
        String string2 = StubApp.getString2(45141);
        LoopFanBean loopFanBeanHex2Json = null;
        try {
            String strValueOf = String.valueOf(jSONObject.getJSONObject(StubApp.getString2("6619")).getString(StubApp.getString2("100")));
            String strSubstring = strValueOf.substring(4, strValueOf.length());
            if (!StubApp.getString2("8591").equals(strValueOf.substring(2, 4))) {
                return null;
            }
            loopFanBeanHex2Json = hex2Json(Hex2DeviceBeanString.getBinaryStr(strSubstring));
            LogUtil.d(TAG, string2 + JSON.toJSONString(loopFanBeanHex2Json));
            return loopFanBeanHex2Json;
        } catch (JSONException e) {
            e.printStackTrace();
            return loopFanBeanHex2Json;
        }
    }

    public static LoopFanBean hex2Json(String[] strArr) {
        int i;
        LoopFanBean loopFanBean = new LoopFanBean();
        int i2 = 0;
        int i3 = 0;
        while (i3 < strArr.length) {
            String string2 = StubApp.getString2(2546);
            String string22 = StubApp.getString2(701);
            if (i3 == 0) {
                int[] itemArray = Hex2DeviceBeanString.getItemArray(strArr[i3]);
                for (int i4 = i2; i4 < itemArray.length; i4++) {
                    if (i4 == 0) {
                        int i5 = itemArray[i4];
                        if (i5 == 0) {
                            loopFanBean.setFormaldehyde_removing_liquid_exhaust(string22);
                        } else if (i5 == 1) {
                            loopFanBean.setFormaldehyde_removing_liquid_exhaust(string2);
                        }
                    } else if (i4 == 1) {
                        int i6 = itemArray[i4];
                        if (i6 == 0) {
                            loopFanBean.setFormaldehyde_removing_liquid_box_non_existent(string22);
                        } else if (i6 == 1) {
                            loopFanBean.setFormaldehyde_removing_liquid_box_non_existent(string2);
                        }
                    } else if (i4 == 2) {
                        int i7 = itemArray[i4];
                        if (i7 == 0) {
                            loopFanBean.setWater_shortage(string22);
                        } else if (i7 == 1) {
                            loopFanBean.setWater_shortage(string2);
                        }
                    } else if (i4 == 3) {
                        int i8 = itemArray[i4];
                        if (i8 == 0) {
                            loopFanBean.setWater_box_non_existent(string22);
                        } else if (i8 == 1) {
                            loopFanBean.setWater_box_non_existent(string2);
                        }
                    } else if (i4 == 4) {
                        int i9 = itemArray[i4];
                        if (i9 == 0) {
                            loopFanBean.setLamp_off(string22);
                        } else if (i9 == 1) {
                            loopFanBean.setLamp_off(string2);
                        }
                    } else if (i4 == 5) {
                        int i10 = itemArray[i4];
                        if (i10 == 0) {
                            loopFanBean.setDisplay_switch(string22);
                        } else if (i10 == 1) {
                            loopFanBean.setDisplay_switch(string2);
                        }
                    } else if (i4 == 6) {
                        int i11 = itemArray[i4];
                        if (i11 == 0) {
                            loopFanBean.setHumidification(string22);
                        } else if (i11 == 1) {
                            loopFanBean.setHumidification(string2);
                        }
                    } else if (i4 == 7) {
                        int i12 = itemArray[i4];
                        if (i12 == 0) {
                            loopFanBean.setSys_switch(string22);
                        } else if (i12 == 1) {
                            loopFanBean.setSys_switch(string2);
                        }
                    }
                }
            } else if (i3 == 1) {
                int[] itemArray2 = Hex2DeviceBeanString.getItemArray(strArr[i3]);
                for (int i13 = i2; i13 < itemArray2.length; i13++) {
                    if (i13 == 0) {
                        int i14 = itemArray2[i13];
                        if (i14 == 0) {
                            loopFanBean.setFormaldehyde_removal(string22);
                        } else if (i14 == 1) {
                            loopFanBean.setFormaldehyde_removal(string2);
                        }
                    } else if (i13 == 1) {
                        int i15 = itemArray2[i13];
                        if (i15 == 0) {
                            loopFanBean.setDeodorization(string22);
                        } else if (i15 == 1) {
                            loopFanBean.setDeodorization(string2);
                        }
                    } else if (i13 == 2) {
                        int i16 = itemArray2[i13];
                        if (i16 == 0) {
                            loopFanBean.setBacteriolysis_status(string22);
                        } else if (i16 == 1) {
                            loopFanBean.setBacteriolysis_status(string2);
                        }
                    } else if (i13 == 3) {
                        int i17 = itemArray2[i13];
                        if (i17 == 0) {
                            loopFanBean.setAlarm_status_of_bacteriolysis_maintenance(string22);
                        } else if (i17 == 1) {
                            loopFanBean.setAlarm_status_of_bacteriolysis_maintenance(string2);
                        }
                    } else if (i13 == 4) {
                        int i18 = itemArray2[i13];
                        if (i18 == 0) {
                            loopFanBean.setCatalyst_reduction_action(string22);
                        } else if (i18 == 1) {
                            loopFanBean.setCatalyst_reduction_action(string2);
                        }
                    } else if (i13 == 5) {
                        int i19 = itemArray2[i13];
                        if (i19 == 0) {
                            loopFanBean.setE3(string22);
                        } else if (i19 == 1) {
                            loopFanBean.setE3(string2);
                        }
                    } else if (i13 == 6) {
                        int i20 = itemArray2[i13];
                        if (i20 == 0) {
                            loopFanBean.setDeodorant_liquid_exhaust(string22);
                        } else if (i20 == 1) {
                            loopFanBean.setDeodorant_liquid_exhaust(string2);
                        }
                    } else if (i13 == 7) {
                        int i21 = itemArray2[i13];
                        if (i21 == 0) {
                            loopFanBean.setDeodorant_liquid_box_non_existent(string22);
                        } else if (i21 == 1) {
                            loopFanBean.setDeodorant_liquid_box_non_existent(string2);
                        }
                    }
                }
            } else {
                if (i3 == 2) {
                    String str = strArr[i3];
                    String strSubstring = str.substring(i2, 4);
                    String strSubstring2 = str.substring(4, 8);
                    String string23 = StubApp.getString2(26813);
                    boolean zEquals = string23.equals(strSubstring);
                    String string24 = StubApp.getString2(7778);
                    String string25 = StubApp.getString2(7188);
                    String string26 = StubApp.getString2(45132);
                    String string27 = StubApp.getString2(1764);
                    String string28 = StubApp.getString2(45133);
                    String string29 = StubApp.getString2(45134);
                    String string210 = StubApp.getString2(45135);
                    if (zEquals) {
                        loopFanBean.setSpeed(string22);
                    } else if (string210.equals(strSubstring)) {
                        loopFanBean.setSpeed(string2);
                    } else if (string29.equals(strSubstring)) {
                        loopFanBean.setSpeed(string27);
                    } else if (string28.equals(strSubstring)) {
                        loopFanBean.setSpeed(string25);
                    } else if (string26.equals(strSubstring)) {
                        loopFanBean.setSpeed(string24);
                    }
                    if (string23.equals(strSubstring2)) {
                        loopFanBean.setWind_mode(string22);
                    } else if (string210.equals(strSubstring2)) {
                        loopFanBean.setWind_mode(string2);
                    } else if (string29.equals(strSubstring2)) {
                        loopFanBean.setWind_mode(string27);
                    } else if (string28.equals(strSubstring2)) {
                        loopFanBean.setWind_mode(string25);
                    } else if (string26.equals(strSubstring2)) {
                        loopFanBean.setWind_mode(string24);
                    }
                } else if (i3 == 3) {
                    loopFanBean.setHum_set(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i3])));
                } else if (i3 == 4) {
                    loopFanBean.setTemperature_set(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i3])));
                } else if (i3 == 5) {
                    loopFanBean.setPoweroff_hour(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i3])));
                } else if (i3 == 6) {
                    loopFanBean.setPoweroff_minute(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i3])));
                } else if (i3 == 7) {
                    loopFanBean.setPoweroff_remain_hour(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i3])));
                } else if (i3 == 8) {
                    loopFanBean.setPoweroff_remain_minute(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i3])));
                } else if (i3 == 9) {
                    loopFanBean.setPoweron_hour(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i3])));
                } else if (i3 == 10) {
                    loopFanBean.setPoweron_minute(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i3])));
                } else if (i3 == 11) {
                    loopFanBean.setPoweron_remain_hour(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i3])));
                } else if (i3 == 12) {
                    loopFanBean.setPoweron_remain_minute(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i3])));
                } else if (i3 == 13) {
                    loopFanBean.setCurrent_env_temp(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i3])));
                } else if (i3 == 14) {
                    loopFanBean.setCurrent_env_hum(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i3])));
                } else if (i3 == 15) {
                    loopFanBean.setFormaldehyde_concentration(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i3])));
                } else if (i3 == 16) {
                    int[] itemArray3 = Hex2DeviceBeanString.getItemArray(strArr[i3]);
                    for (int i22 = 0; i22 < itemArray3.length; i22++) {
                        if (i22 == 0) {
                            int i23 = itemArray3[i22];
                        } else if (i22 == 1) {
                            int i24 = itemArray3[i22];
                        } else if (i22 == 2) {
                            int i25 = itemArray3[i22];
                        } else if (i22 == 3) {
                            int i26 = itemArray3[i22];
                        } else if (i22 == 4) {
                            int i27 = itemArray3[i22];
                            if (i27 == 0) {
                                loopFanBean.setE0(string22);
                            } else if (i27 == 1) {
                                loopFanBean.setE0(string2);
                            }
                        } else if (i22 == 5) {
                            int i28 = itemArray3[i22];
                            if (i28 == 0) {
                                loopFanBean.setE1(string22);
                            } else if (i28 == 1) {
                                loopFanBean.setE1(string2);
                            }
                        } else if (i22 == 6) {
                            int i29 = itemArray3[i22];
                            if (i29 == 0) {
                                loopFanBean.setE2(string22);
                            } else if (i29 == 1) {
                                loopFanBean.setE2(string2);
                            }
                        } else if (i22 == 7) {
                            int i30 = itemArray3[i22];
                            if (i30 == 0) {
                                loopFanBean.setL0(string22);
                            } else if (i30 == 1) {
                                loopFanBean.setL0(string2);
                            }
                        }
                    }
                } else {
                    if (i3 == 17) {
                        String str2 = strArr[i3];
                        i = 0;
                        String strSubstring3 = str2.substring(0, 4);
                        String strSubstring4 = str2.substring(4, 8);
                        loopFanBean.setAir_pollution_level(String.valueOf(Hex2DeviceBeanString.binaryStrToInt10(strSubstring3)));
                        loopFanBean.setAir_pollution_level_color(String.valueOf(Hex2DeviceBeanString.binaryStrToInt10(strSubstring4)));
                    }
                    i3++;
                    i2 = i;
                }
                i = 0;
                i3++;
                i2 = i;
            }
            i = i2;
            i3++;
            i2 = i;
        }
        return loopFanBean;
    }
}
