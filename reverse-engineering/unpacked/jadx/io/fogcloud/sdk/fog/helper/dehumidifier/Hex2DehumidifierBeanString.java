package io.fogcloud.sdk.fog.helper.dehumidifier;

import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.helper.Hex2DeviceBeanString;
import io.fogcloud.sdk.fog.utils.DigitalTrans;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class Hex2DehumidifierBeanString {
    private static String TAG = StubApp.getString2(45138);

    public static DehumidifierBean getDehumidfierBeanString(JSONObject jSONObject) {
        String string2 = StubApp.getString2(45141);
        Log.d(TAG, StubApp.getString2(45142) + jSONObject.toString());
        DehumidifierBean dehumidifierBeanHex2Json = null;
        try {
            String strValueOf = String.valueOf(jSONObject.getJSONObject(StubApp.getString2("6619")).getString(StubApp.getString2("100")));
            String strSubstring = strValueOf.substring(4, strValueOf.length());
            String strSubstring2 = strValueOf.substring(2, 4);
            if (!StubApp.getString2("8591").equals(strSubstring2) && !StubApp.getString2("1751").equals(strSubstring2)) {
                return null;
            }
            dehumidifierBeanHex2Json = hex2Json(Hex2DeviceBeanString.getBinaryStr(strSubstring));
            Log.d(TAG, string2 + JSON.toJSONString(dehumidifierBeanHex2Json));
            return dehumidifierBeanHex2Json;
        } catch (JSONException e) {
            e.printStackTrace();
            return dehumidifierBeanHex2Json;
        }
    }

    public static DehumidifierBean getDehumidfierBeanString(String str) {
        Log.d(TAG, StubApp.getString2(45139) + str);
        if (TextUtils.isEmpty(str) || str.equals(StubApp.getString2(13461))) {
            return null;
        }
        String strSubstring = str.substring(4);
        String strSubstring2 = str.substring(2, 4);
        if (!StubApp.getString2(8591).equals(strSubstring2) && !StubApp.getString2(1751).equals(strSubstring2)) {
            return null;
        }
        DehumidifierBean dehumidifierBeanHex2Json = hex2Json(Hex2DeviceBeanString.getBinaryStr(strSubstring));
        Log.d(TAG, StubApp.getString2(45140) + JSON.toJSONString(dehumidifierBeanHex2Json));
        return dehumidifierBeanHex2Json;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v76 */
    public static DehumidifierBean hex2Json(String[] strArr) {
        DehumidifierBean dehumidifierBean = new DehumidifierBean();
        ?? r2 = 0;
        int i = 0;
        while (i < strArr.length) {
            String string2 = StubApp.getString2(2546);
            String string22 = StubApp.getString2(701);
            if (i == 0) {
                int[] itemArray = Hex2DeviceBeanString.getItemArray(strArr[i]);
                for (int i2 = r2; i2 < itemArray.length; i2++) {
                    if (i2 == 0) {
                        int i3 = itemArray[i2];
                        if (i3 == 0) {
                            dehumidifierBean.setFan_switch(string22);
                        } else if (i3 == 1) {
                            dehumidifierBean.setFan_switch(string2);
                        }
                    } else if (i2 == 1) {
                        int i4 = itemArray[i2];
                        if (i4 == 0) {
                            dehumidifierBean.setWatertank_state(string22);
                        } else if (i4 == 1) {
                            dehumidifierBean.setWatertank_state(string2);
                        }
                    } else if (i2 == 2) {
                        int i5 = itemArray[i2];
                        if (i5 == 0) {
                            dehumidifierBean.setDefrost_state(string22);
                        } else if (i5 == 1) {
                            dehumidifierBean.setDefrost_state(string2);
                        }
                    } else if (i2 == 3) {
                        int i6 = itemArray[i2];
                        if (i6 == 0) {
                            dehumidifierBean.setPoweron_flag(string22);
                        } else if (i6 == 1) {
                            dehumidifierBean.setPoweron_flag(string2);
                        }
                    } else if (i2 == 4) {
                        int i7 = itemArray[i2];
                        if (i7 == 0) {
                            dehumidifierBean.setPoweroff_flag(string22);
                        } else if (i7 == 1) {
                            dehumidifierBean.setPoweroff_flag(string2);
                        }
                    } else if (i2 == 5) {
                        int i8 = itemArray[i2];
                        if (i8 == 0) {
                            dehumidifierBean.setLock_switch(string22);
                        } else if (i8 == 1) {
                            dehumidifierBean.setLock_switch(string2);
                        }
                    } else if (i2 == 6) {
                        int i9 = itemArray[i2];
                        if (i9 == 0) {
                            dehumidifierBean.setWind_switch(string22);
                        } else if (i9 == 1) {
                            dehumidifierBean.setWind_switch(string2);
                        }
                    } else if (i2 == 7) {
                        int i10 = itemArray[i2];
                        if (i10 == 0) {
                            dehumidifierBean.setSys_switch(string22);
                        } else if (i10 == 1) {
                            dehumidifierBean.setSys_switch(string2);
                        }
                    }
                }
            } else if (i == 1) {
                int[] itemArray2 = Hex2DeviceBeanString.getItemArray(strArr[i]);
                for (int i11 = r2; i11 < itemArray2.length; i11++) {
                    if (i11 != 0 && i11 != 1) {
                        if (i11 == 2) {
                            int i12 = itemArray2[i11];
                        } else if (i11 == 3) {
                            int i13 = itemArray2[i11];
                            if (i13 == 0) {
                                dehumidifierBean.setEnvironment_degree(string22);
                            } else if (i13 == 1) {
                                dehumidifierBean.setEnvironment_degree(string2);
                            }
                        } else if (i11 == 4) {
                            int i14 = itemArray2[i11];
                            if (i14 == 0) {
                                dehumidifierBean.setPress_state(string22);
                            } else if (i14 == 1) {
                                dehumidifierBean.setPress_state(string2);
                            }
                        } else if (i11 == 5) {
                            int i15 = itemArray2[i11];
                            if (i15 == 0) {
                                dehumidifierBean.setElectromagnetic_state(r2);
                            } else if (i15 == 1) {
                                dehumidifierBean.setElectromagnetic_state(true);
                            }
                        } else if (i11 == 6) {
                            int i16 = itemArray2[i11];
                            if (i16 == 0) {
                                dehumidifierBean.setPump_switch(string22);
                            } else if (i16 == 1) {
                                dehumidifierBean.setPump_switch(string2);
                            }
                        } else if (i11 == 7) {
                            int i17 = itemArray2[i11];
                            if (i17 == 0) {
                                dehumidifierBean.setAnion_switch(string22);
                            } else if (i17 == 1) {
                                dehumidifierBean.setAnion_switch(string2);
                            }
                        }
                    }
                }
            } else if (i == 2) {
                String str = strArr[i];
                String strSubstring = str.substring(r2, 4);
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
                    dehumidifierBean.setSpeed(string22);
                } else if (string210.equals(strSubstring)) {
                    dehumidifierBean.setSpeed(string2);
                } else if (string29.equals(strSubstring)) {
                    dehumidifierBean.setSpeed(string27);
                } else if (string28.equals(strSubstring)) {
                    dehumidifierBean.setSpeed(string25);
                } else if (string26.equals(strSubstring)) {
                    dehumidifierBean.setSpeed(string24);
                }
                if (string23.equals(strSubstring2)) {
                    dehumidifierBean.setMode(string22);
                } else if (string210.equals(strSubstring2)) {
                    dehumidifierBean.setMode(string2);
                } else if (string29.equals(strSubstring2)) {
                    dehumidifierBean.setMode(string27);
                } else if (string28.equals(strSubstring2)) {
                    dehumidifierBean.setMode(string25);
                } else if (string26.equals(strSubstring2)) {
                    dehumidifierBean.setMode(string24);
                } else if (StubApp.getString2(45136).equals(strSubstring2)) {
                    dehumidifierBean.setMode(StubApp.getString2(8095));
                } else if (StubApp.getString2(45137).equals(strSubstring2)) {
                    dehumidifierBean.setMode(StubApp.getString2(8092));
                }
            } else if (i == 3) {
                dehumidifierBean.setHum_set(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i])));
            } else if (i == 4) {
                dehumidifierBean.setPoweroff_hour(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i])));
            } else if (i == 5) {
                dehumidifierBean.setPoweroff_minute(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i])));
            } else if (i == 6) {
                dehumidifierBean.setPoweroff_remain_hour(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i])));
            } else if (i == 7) {
                dehumidifierBean.setPoweroff_remain_minute(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i])));
            } else if (i == 8) {
                dehumidifierBean.setPoweron_hour(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i])));
            } else if (i == 9) {
                dehumidifierBean.setPoweron_minute(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i])));
            } else if (i == 10) {
                dehumidifierBean.setPoweron_remain_hour(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i])));
            } else if (i == 11) {
                dehumidifierBean.setPoweron_remain_minute(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i])));
            } else if (i == 12) {
                dehumidifierBean.setCurrent_coil_temp(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i])));
            } else if (i == 13) {
                dehumidifierBean.setCurrent_env_temp(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i])));
            } else if (i == 14) {
                dehumidifierBean.setCurrent_env_hum(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i])));
            } else if (i == 15) {
                dehumidifierBean.setCurrent_exhaust_temp(String.valueOf(DigitalTrans.binaryToAlgorism(strArr[i])));
            } else if (i == 16) {
                int[] itemArray3 = Hex2DeviceBeanString.getItemArray(strArr[i]);
                for (int i18 = 0; i18 < itemArray3.length; i18++) {
                    if (i18 == 0) {
                        int i19 = itemArray3[i18];
                        if (i19 == 0) {
                            dehumidifierBean.setL1(string22);
                        } else if (i19 == 1) {
                            dehumidifierBean.setL1(string2);
                        }
                    } else if (i18 == 1) {
                        int i20 = itemArray3[i18];
                        if (i20 == 0) {
                            dehumidifierBean.setL3(string22);
                        } else if (i20 == 1) {
                            dehumidifierBean.setL3(string2);
                        }
                    } else if (i18 == 2) {
                        int i21 = itemArray3[i18];
                        if (i21 == 0) {
                            dehumidifierBean.setL4(string22);
                        } else if (i21 == 1) {
                            dehumidifierBean.setL4(string2);
                        }
                    } else if (i18 == 3) {
                        int i22 = itemArray3[i18];
                        if (i22 == 0) {
                            dehumidifierBean.setE6(string22);
                        } else if (i22 == 1) {
                            dehumidifierBean.setE6(string2);
                        }
                    } else if (i18 == 4) {
                        int i23 = itemArray3[i18];
                        if (i23 == 0) {
                            dehumidifierBean.setF6(string22);
                        } else if (i23 == 1) {
                            dehumidifierBean.setF6(string2);
                        }
                    } else if (i18 == 5) {
                        int i24 = itemArray3[i18];
                        if (i24 == 0) {
                            dehumidifierBean.setE8(string22);
                        } else if (i24 == 1) {
                            dehumidifierBean.setE8(string2);
                        }
                    } else if (i18 == 6) {
                        int i25 = itemArray3[i18];
                        if (i25 == 0) {
                            dehumidifierBean.setE2(string22);
                        } else if (i25 == 1) {
                            dehumidifierBean.setE2(string2);
                        }
                    } else if (i18 == 7) {
                        int i26 = itemArray3[i18];
                        if (i26 == 0) {
                            dehumidifierBean.setE1(string22);
                        } else if (i26 == 1) {
                            dehumidifierBean.setE1(string2);
                        }
                    }
                }
            } else if (i == 17) {
                int[] itemArray4 = Hex2DeviceBeanString.getItemArray(strArr[i]);
                for (int i27 = 0; i27 < itemArray4.length; i27++) {
                    if (i27 == 0) {
                        int i28 = itemArray4[i27];
                        if (i28 == 0) {
                            dehumidifierBean.setP6(string22);
                        } else if (i28 == 1) {
                            dehumidifierBean.setP6(string2);
                        }
                    } else if (i27 == 1) {
                        int i29 = itemArray4[i27];
                        if (i29 == 0) {
                            dehumidifierBean.setP7(string22);
                        } else if (i29 == 1) {
                            dehumidifierBean.setP7(string2);
                        }
                    } else if (i27 == 2) {
                        int i30 = itemArray4[i27];
                        if (i30 == 0) {
                            dehumidifierBean.setP8(string22);
                        } else if (i30 == 1) {
                            dehumidifierBean.setP8(string2);
                        }
                    } else if (i27 == 3) {
                        int i31 = itemArray4[i27];
                        if (i31 == 0) {
                            dehumidifierBean.setP5(string22);
                        } else if (i31 == 1) {
                            dehumidifierBean.setP5(string2);
                        }
                    } else if (i27 == 4) {
                        int i32 = itemArray4[i27];
                        if (i32 == 0) {
                            dehumidifierBean.setL2(string22);
                        } else if (i32 == 1) {
                            dehumidifierBean.setL2(string2);
                        }
                    } else if (i27 == 5) {
                        int i33 = itemArray4[i27];
                        if (i33 == 0) {
                            dehumidifierBean.setE5(string22);
                        } else if (i33 == 1) {
                            dehumidifierBean.setE5(string2);
                        }
                    } else if (i27 == 6) {
                        int i34 = itemArray4[i27];
                        if (i34 == 0) {
                            dehumidifierBean.setE7(string22);
                        } else if (i34 == 1) {
                            dehumidifierBean.setE7(string2);
                        }
                    } else if (i27 == 7) {
                        int i35 = itemArray4[i27];
                        if (i35 == 0) {
                            dehumidifierBean.setP4(string22);
                        } else if (i35 == 1) {
                            dehumidifierBean.setP4(string2);
                        }
                    }
                }
            } else if (i == 18) {
                int[] itemArray5 = Hex2DeviceBeanString.getItemArray(strArr[i]);
                for (int i36 = 0; i36 < itemArray5.length; i36++) {
                    if (i36 == 0) {
                        int i37 = itemArray5[i36];
                        if (i37 == 0) {
                            dehumidifierBean.setE0(string22);
                        } else if (i37 == 1) {
                            dehumidifierBean.setE0(string2);
                        }
                    } else if (i36 == 1) {
                        int i38 = itemArray5[i36];
                        if (i38 == 0) {
                            dehumidifierBean.setE3(string22);
                        } else if (i38 == 1) {
                            dehumidifierBean.setE3(string2);
                        }
                    } else if (i36 == 2) {
                        int i39 = itemArray5[i36];
                        if (i39 == 0) {
                            dehumidifierBean.setE4(string22);
                        } else if (i39 == 1) {
                            dehumidifierBean.setE4(string2);
                        }
                    } else if (i36 == 3) {
                        int i40 = itemArray5[i36];
                        if (i40 == 0) {
                            dehumidifierBean.setE9(string22);
                        } else if (i40 == 1) {
                            dehumidifierBean.setE9(string2);
                        }
                    } else if (i36 == 4) {
                        int i41 = itemArray5[i36];
                        if (i41 == 0) {
                            dehumidifierBean.setP0(string22);
                        } else if (i41 == 1) {
                            dehumidifierBean.setP0(string2);
                        }
                    } else if (i36 == 5) {
                        int i42 = itemArray5[i36];
                        if (i42 == 0) {
                            dehumidifierBean.setP1(string22);
                        } else if (i42 == 1) {
                            dehumidifierBean.setP1(string2);
                        }
                    } else if (i36 == 6) {
                        int i43 = itemArray5[i36];
                        if (i43 == 0) {
                            dehumidifierBean.setP2(string22);
                        } else if (i43 == 1) {
                            dehumidifierBean.setP2(string2);
                        }
                    } else if (i36 == 7) {
                        int i44 = itemArray5[i36];
                        if (i44 == 0) {
                            dehumidifierBean.setP3(string22);
                        } else if (i44 == 1) {
                            dehumidifierBean.setP3(string2);
                        }
                    }
                }
            } else if (i == 19) {
                int[] itemArray6 = Hex2DeviceBeanString.getItemArray(strArr[i]);
                for (int i45 = 0; i45 < itemArray6.length; i45++) {
                    if (i45 == 0) {
                        int i46 = itemArray6[i45];
                        if (i46 == 0) {
                            dehumidifierBean.setF1(string22);
                        } else if (i46 == 1) {
                            dehumidifierBean.setF1(string2);
                        }
                    } else if (i45 == 1) {
                        int i47 = itemArray6[i45];
                        if (i47 == 0) {
                            dehumidifierBean.setF2(string22);
                        } else if (i47 == 1) {
                            dehumidifierBean.setF2(string2);
                        }
                    } else if (i45 == 2) {
                        int i48 = itemArray6[i45];
                        if (i48 == 0) {
                            dehumidifierBean.setF3(string22);
                        } else if (i48 == 1) {
                            dehumidifierBean.setF3(string2);
                        }
                    } else if (i45 == 3) {
                        int i49 = itemArray6[i45];
                        if (i49 == 0) {
                            dehumidifierBean.setF4(string22);
                        } else if (i49 == 1) {
                            dehumidifierBean.setF4(string2);
                        }
                    } else if (i45 == 4) {
                        int i50 = itemArray6[i45];
                        if (i50 == 0) {
                            dehumidifierBean.setF5(string22);
                        } else if (i50 == 1) {
                            dehumidifierBean.setF5(string2);
                        }
                    } else if (i45 == 5) {
                        int i51 = itemArray6[i45];
                        if (i51 == 0) {
                            dehumidifierBean.setF7(string22);
                        } else if (i51 == 1) {
                            dehumidifierBean.setF7(string2);
                        }
                    } else if (i45 == 6) {
                        int i52 = itemArray6[i45];
                        if (i52 == 0) {
                            dehumidifierBean.setF8(string22);
                        } else if (i52 == 1) {
                            dehumidifierBean.setF8(string2);
                        }
                    } else if (i45 == 7) {
                        int i53 = itemArray6[i45];
                        if (i53 == 0) {
                            dehumidifierBean.setF9(string22);
                        } else if (i53 == 1) {
                            dehumidifierBean.setF9(string2);
                        }
                    }
                }
            }
            i++;
            r2 = 0;
        }
        return dehumidifierBean;
    }
}
