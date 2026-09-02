package io.fogcloud.sdk.fog.helper.quilt_dryer.loop_fan;

import com.alibaba.fastjson.JSON;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.QuiltDryerBean;
import io.fogcloud.sdk.fog.helper.Hex2DeviceBeanString;
import io.fogcloud.sdk.fog.log.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class Hex2QuiltDryerBeanString {
    private static String TAG = StubApp.getString2(45151);

    public static QuiltDryerBean getQuiltDryerBeanString(JSONObject jSONObject) {
        String string2 = StubApp.getString2(45152);
        QuiltDryerBean quiltDryerBeanHex2Json = null;
        try {
            String strValueOf = String.valueOf(jSONObject.getJSONObject(StubApp.getString2("6619")).getString(StubApp.getString2("100")));
            String strSubstring = strValueOf.substring(4, strValueOf.length());
            if (!StubApp.getString2("8591").equals(strValueOf.substring(2, 4))) {
                return null;
            }
            quiltDryerBeanHex2Json = hex2Json(Hex2DeviceBeanString.getBinaryStr(strSubstring));
            LogUtil.d(TAG, string2 + JSON.toJSONString(quiltDryerBeanHex2Json));
            return quiltDryerBeanHex2Json;
        } catch (JSONException e) {
            e.printStackTrace();
            return quiltDryerBeanHex2Json;
        }
    }

    private static QuiltDryerBean hex2Json(String[] strArr) {
        QuiltDryerBean quiltDryerBean = new QuiltDryerBean();
        for (int i = 0; i < strArr.length; i++) {
            String string2 = StubApp.getString2(2546);
            String string22 = StubApp.getString2(701);
            if (i == 0) {
                int[] itemArray = Hex2DeviceBeanString.getItemArray(strArr[i]);
                for (int i2 = 0; i2 < itemArray.length; i2++) {
                    if (i2 == 0) {
                        int i3 = itemArray[i2];
                    } else if (i2 == 1) {
                        int i4 = itemArray[i2];
                    } else if (i2 == 2) {
                        int i5 = itemArray[i2];
                    } else if (i2 == 3) {
                        int i6 = itemArray[i2];
                    } else if (i2 == 4) {
                        int i7 = itemArray[i2];
                        if (i7 == 0) {
                            quiltDryerBean.setStandby_switch(string22);
                        } else if (i7 == 1) {
                            quiltDryerBean.setStandby_switch(string2);
                        }
                    } else if (i2 == 5) {
                        int i8 = itemArray[i2];
                        if (i8 == 0) {
                            quiltDryerBean.setLock_switch(string22);
                        } else if (i8 == 1) {
                            quiltDryerBean.setLock_switch(string2);
                        }
                    } else if (i2 == 6) {
                        int i9 = itemArray[i2];
                        if (i9 == 0) {
                            quiltDryerBean.setSterilization_switch(string22);
                        } else if (i9 == 1) {
                            quiltDryerBean.setSterilization_switch(string2);
                        }
                    } else if (i2 == 7) {
                        int i10 = itemArray[i2];
                        if (i10 == 0) {
                            quiltDryerBean.setSys_switch(string22);
                        } else if (i10 == 1) {
                            quiltDryerBean.setSys_switch(string2);
                        }
                    }
                }
            } else if (i == 1) {
                int[] itemArray2 = Hex2DeviceBeanString.getItemArray(strArr[i]);
                for (int i11 = 0; i11 < itemArray2.length; i11++) {
                    if (i11 == 0) {
                        int i12 = itemArray2[i11];
                    } else if (i11 == 1) {
                        int i13 = itemArray2[i11];
                    } else if (i11 == 2) {
                        int i14 = itemArray2[i11];
                    } else if (i11 == 3) {
                        int i15 = itemArray2[i11];
                    } else if (i11 == 4) {
                        int i16 = itemArray2[i11];
                    } else if (i11 == 5) {
                        int i17 = itemArray2[i11];
                    } else if (i11 == 6) {
                        int i18 = itemArray2[i11];
                    } else if (i11 == 7) {
                        int i19 = itemArray2[i11];
                    }
                }
            } else {
                String string23 = StubApp.getString2(7188);
                String string24 = StubApp.getString2(45133);
                String string25 = StubApp.getString2(1764);
                String string26 = StubApp.getString2(45134);
                String string27 = StubApp.getString2(45135);
                String string28 = StubApp.getString2(26813);
                if (i == 2) {
                    String str = strArr[i];
                    str.substring(0, 4);
                    String strSubstring = str.substring(4, 8);
                    if (string28.equals(strSubstring)) {
                        quiltDryerBean.setMode(string22);
                    } else if (string27.equals(strSubstring)) {
                        quiltDryerBean.setMode(string2);
                    } else if (string26.equals(strSubstring)) {
                        quiltDryerBean.setMode(string25);
                    } else if (string24.equals(strSubstring)) {
                        quiltDryerBean.setMode(string23);
                    } else if (StubApp.getString2(45132).equals(strSubstring)) {
                        quiltDryerBean.setMode(StubApp.getString2(7778));
                    }
                } else if (i == 3) {
                    String str2 = strArr[i];
                    String strSubstring2 = str2.substring(0, 4);
                    String strSubstring3 = str2.substring(4, 8);
                    if (string28.equals(strSubstring2)) {
                        quiltDryerBean.setBakingQuilt(string22);
                    } else if (string27.equals(strSubstring2)) {
                        quiltDryerBean.setBakingQuilt(string2);
                    } else if (string26.equals(strSubstring2)) {
                        quiltDryerBean.setBakingQuilt(string25);
                    } else if (string24.equals(strSubstring2)) {
                        quiltDryerBean.setBakingQuilt(string23);
                    }
                    if (string28.equals(strSubstring3)) {
                        quiltDryerBean.setWarm(string22);
                    } else if (string27.equals(strSubstring3)) {
                        quiltDryerBean.setWarm(string2);
                    } else if (string26.equals(strSubstring3)) {
                        quiltDryerBean.setWarm(string25);
                    } else if (StubApp.getString2(45136).equals(strSubstring3)) {
                        quiltDryerBean.setWarm(StubApp.getString2(8095));
                    } else if (StubApp.getString2(45137).equals(strSubstring3)) {
                        quiltDryerBean.setWarm(StubApp.getString2(8092));
                    }
                } else if (i == 4) {
                    String str3 = strArr[i];
                    String strSubstring4 = str3.substring(0, 4);
                    String strSubstring5 = str3.substring(4, 8);
                    if (string28.equals(strSubstring4)) {
                        quiltDryerBean.setWarmQuilt(string22);
                    } else if (string27.equals(strSubstring4)) {
                        quiltDryerBean.setWarmQuilt(string2);
                    } else if (string26.equals(strSubstring4)) {
                        quiltDryerBean.setWarmQuilt(string25);
                    }
                    if (string28.equals(strSubstring5)) {
                        quiltDryerBean.setAcarusKilling(string22);
                    } else if (string27.equals(strSubstring5)) {
                        quiltDryerBean.setAcarusKilling(string2);
                    } else if (string26.equals(strSubstring5)) {
                        quiltDryerBean.setAcarusKilling(string25);
                    } else if (string24.equals(strSubstring5)) {
                        quiltDryerBean.setAcarusKilling(string23);
                    }
                } else if (i != 5 && i != 6 && i != 7 && i != 8 && i != 9 && i != 10 && i != 11 && i != 12 && i != 13 && i != 14 && i != 15 && i == 16) {
                    int[] itemArray3 = Hex2DeviceBeanString.getItemArray(strArr[i]);
                    int i20 = 0;
                    while (i20 < itemArray3.length) {
                        if (i20 == 0 || i20 == 1 || i20 == 2 || i20 == 3) {
                            int i21 = itemArray3[i20];
                        } else {
                            if (i20 == 4) {
                                int i22 = itemArray3[i20];
                                if (i22 == 0) {
                                    quiltDryerBean.setAnion_fault(string22);
                                } else if (i22 == 1) {
                                    quiltDryerBean.setAnion_fault(string2);
                                }
                            } else if (i20 == 5) {
                                int i23 = itemArray3[i20];
                                if (i23 == 0) {
                                    quiltDryerBean.setFan_fault(string22);
                                } else if (i23 == 1) {
                                    quiltDryerBean.setFan_fault(string2);
                                }
                            } else if (i20 == 6) {
                                int i24 = itemArray3[i20];
                                if (i24 == 0) {
                                    quiltDryerBean.setOutlet_temp_fault(string22);
                                } else if (i24 == 1) {
                                    quiltDryerBean.setOutlet_temp_fault(string2);
                                }
                                i20++;
                            } else {
                                if (i20 == 7) {
                                    int i25 = itemArray3[i20];
                                    if (i25 == 0) {
                                        quiltDryerBean.setInlet_temp_fault(string22);
                                    } else if (i25 == 1) {
                                        quiltDryerBean.setInlet_temp_fault(string2);
                                    }
                                }
                                i20++;
                            }
                            i20++;
                        }
                        i20++;
                    }
                }
            }
        }
        return quiltDryerBean;
    }
}
