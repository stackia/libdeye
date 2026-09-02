package com.deye.configs;

import android.content.Context;
import com.deye.ErrorCodeMap;
import com.mxchipapp.R;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ErrorMapUtils {
    private static ErrorCodeMap<String, String> mB13A3ErrorMap;
    private static ErrorCodeMap<String, String> mD50GErrorMap;
    private static ErrorCodeMap<String, String> mE12A3ErrorMap;
    private static ErrorCodeMap<String, String> mM890TErrorMap;
    private static ErrorCodeMap<String, String> mP30ErrorMap;
    private static ErrorCodeMap<String, String> mQuiltDryerDJB_S10B_W_Map;
    private static ErrorCodeMap<String, String> mRT12ErrorMap;
    private static ErrorCodeMap<String, String> mU20A3ErrorMap;
    private static ErrorCodeMap<String, String> mV58A3ErrorMap;
    private static ErrorCodeMap<String, String> mV60A3ErrorMap;
    private static ErrorCodeMap<String, String> mX20A3ErrorMap;

    public static ErrorCodeMap<String, String> getV58A3ErrorMap(Context context) {
        if (mV58A3ErrorMap == null) {
            ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
            mV58A3ErrorMap = errorCodeMap;
            errorCodeMap.put(StubApp.getString2(13499), context.getString(R.string.fault_code_e0));
            mV58A3ErrorMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
            mV58A3ErrorMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
            mV58A3ErrorMap.put(StubApp.getString2(13500), context.getString(R.string.fault_code_e5));
            mV58A3ErrorMap.put(StubApp.getString2(13501), context.getString(R.string.fault_code_e6));
            mV58A3ErrorMap.put(StubApp.getString2(13526), context.getString(R.string.fault_code_e8));
            mV58A3ErrorMap.put(StubApp.getString2(13503), context.getString(R.string.fault_code_p0));
            mV58A3ErrorMap.put(StubApp.getString2(13504), context.getString(R.string.fault_code_p1));
            mV58A3ErrorMap.put(StubApp.getString2(13505), context.getString(R.string.fault_code_p4));
            mV58A3ErrorMap.put(StubApp.getString2(13506), context.getString(R.string.fault_code_p5));
            mV58A3ErrorMap.put(StubApp.getString2(11410), context.getString(R.string.fault_code_l2));
        }
        return mV58A3ErrorMap;
    }

    public static ErrorCodeMap<String, String> getV60A3ErrorMap(Context context) {
        ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
        errorCodeMap.put(StubApp.getString2(11410), context.getString(R.string.fault_code_l2));
        errorCodeMap.put(StubApp.getString2(13503), context.getString(R.string.fault_code_p0));
        errorCodeMap.put(StubApp.getString2(13504), context.getString(R.string.fault_code_p1));
        errorCodeMap.put(StubApp.getString2(13505), context.getString(R.string.fault_code_p4));
        errorCodeMap.put(StubApp.getString2(13506), context.getString(R.string.fault_code_p5));
        errorCodeMap.put(StubApp.getString2(13499), context.getString(R.string.fault_code_e0));
        errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
        errorCodeMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
        errorCodeMap.put(StubApp.getString2(13500), context.getString(R.string.fault_code_e5));
        errorCodeMap.put(StubApp.getString2(13501), context.getString(R.string.fault_code_e6));
        errorCodeMap.put(StubApp.getString2(13574), context.getString(R.string.fault_code_e7));
        errorCodeMap.put(StubApp.getString2(13526), context.getString(R.string.fault_code_e8));
        errorCodeMap.put(StubApp.getString2(13576), context.getString(R.string.fault_code_ea));
        errorCodeMap.put(StubApp.getString2(13577), context.getString(R.string.fault_code_eb));
        errorCodeMap.put(StubApp.getString2(13579), context.getString(R.string.fault_code_ee));
        return errorCodeMap;
    }

    public static ErrorCodeMap<String, String> getP40ErrorMap(Context context) {
        ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
        errorCodeMap.put(StubApp.getString2(11410), context.getString(R.string.fault_code_l2));
        errorCodeMap.put(StubApp.getString2(13503), context.getString(R.string.fault_code_p0));
        errorCodeMap.put(StubApp.getString2(13504), context.getString(R.string.fault_code_p1));
        errorCodeMap.put(StubApp.getString2(13505), context.getString(R.string.fault_code_p4));
        errorCodeMap.put(StubApp.getString2(13506), context.getString(R.string.fault_code_p5));
        errorCodeMap.put(StubApp.getString2(13499), context.getString(R.string.fault_code_e0));
        errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
        errorCodeMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
        errorCodeMap.put(StubApp.getString2(13500), context.getString(R.string.fault_code_e5));
        errorCodeMap.put(StubApp.getString2(13501), context.getString(R.string.fault_code_e6));
        errorCodeMap.put(StubApp.getString2(13574), context.getString(R.string.fault_code_e7));
        errorCodeMap.put(StubApp.getString2(13526), context.getString(R.string.fault_code_e8));
        errorCodeMap.put(StubApp.getString2(13576), context.getString(R.string.fault_code_ea));
        errorCodeMap.put(StubApp.getString2(13577), context.getString(R.string.fault_code_eb));
        errorCodeMap.put(StubApp.getString2(13579), context.getString(R.string.fault_code_ee));
        return errorCodeMap;
    }

    public static ErrorCodeMap<String, String> getE12A3ErrorMap(Context context) {
        if (mE12A3ErrorMap == null) {
            ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
            mE12A3ErrorMap = errorCodeMap;
            errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
            mE12A3ErrorMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
        }
        return mE12A3ErrorMap;
    }

    public static ErrorCodeMap<String, String> getX20A3ErrorMap(Context context) {
        if (mX20A3ErrorMap == null) {
            ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
            mX20A3ErrorMap = errorCodeMap;
            errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
            mX20A3ErrorMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
            mX20A3ErrorMap.put(StubApp.getString2(11260), context.getString(R.string.fault_code_l3));
            mX20A3ErrorMap.put(StubApp.getString2(13502), context.getString(R.string.fault_code_l4));
        }
        return mX20A3ErrorMap;
    }

    public static ErrorCodeMap<String, String> getT22A3ErrorMap(Context context) {
        new ErrorCodeMap();
        ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
        errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
        errorCodeMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
        errorCodeMap.put(StubApp.getString2(11410), context.getString(R.string.fault_code_l2_dc_fan));
        errorCodeMap.put(StubApp.getString2(11260), context.getString(R.string.fault_code_l3));
        errorCodeMap.put(StubApp.getString2(13502), context.getString(R.string.fault_code_l4));
        return errorCodeMap;
    }

    public static ErrorCodeMap<String, String> get6158ebErrorMap(Context context) {
        if (mX20A3ErrorMap == null) {
            ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
            mX20A3ErrorMap = errorCodeMap;
            errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
            mX20A3ErrorMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
            mX20A3ErrorMap.put(StubApp.getString2(13524), context.getString(R.string.fault_code_e3));
            mX20A3ErrorMap.put(StubApp.getString2(13525), context.getString(R.string.fault_code_e4));
        }
        return mX20A3ErrorMap;
    }

    public static ErrorCodeMap<String, String> getB12A3ErrorMap(Context context) {
        if (mX20A3ErrorMap == null) {
            ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
            mX20A3ErrorMap = errorCodeMap;
            errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
            mX20A3ErrorMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
            mX20A3ErrorMap.put(StubApp.getString2(11260), context.getString(R.string.fault_code_l3));
            mX20A3ErrorMap.put(StubApp.getString2(13502), context.getString(R.string.fault_code_l4));
        }
        return mX20A3ErrorMap;
    }

    public static ErrorCodeMap<String, String> getU20A3ErrorMap(Context context) {
        if (mU20A3ErrorMap == null) {
            ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
            mU20A3ErrorMap = errorCodeMap;
            errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
            mU20A3ErrorMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
            mU20A3ErrorMap.put(StubApp.getString2(11411), context.getString(R.string.fault_code_l1));
            mU20A3ErrorMap.put(StubApp.getString2(11410), context.getString(R.string.fault_code_l2));
            mU20A3ErrorMap.put(StubApp.getString2(11260), context.getString(R.string.fault_code_l3));
            mU20A3ErrorMap.put(StubApp.getString2(13502), context.getString(R.string.fault_code_l4));
            mU20A3ErrorMap.put(StubApp.getString2(13504), context.getString(R.string.fault_code_p1));
            mU20A3ErrorMap.put(StubApp.getString2(13507), context.getString(R.string.fault_code_p6));
        }
        return mU20A3ErrorMap;
    }

    public static ErrorCodeMap<String, String> getU20ProErrorMap(Context context) {
        new ErrorCodeMap();
        ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
        errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
        errorCodeMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
        errorCodeMap.put(StubApp.getString2(13532), context.getString(R.string.fault_code_f6));
        errorCodeMap.put(StubApp.getString2(11411), context.getString(R.string.fault_code_l1));
        errorCodeMap.put(StubApp.getString2(11410), context.getString(R.string.fault_code_l2));
        errorCodeMap.put(StubApp.getString2(11260), context.getString(R.string.fault_code_l3));
        errorCodeMap.put(StubApp.getString2(13502), context.getString(R.string.fault_code_l4));
        errorCodeMap.put(StubApp.getString2(13507), context.getString(R.string.fault_code_p6));
        return errorCodeMap;
    }

    public static ErrorCodeMap<String, String> getA12A452ErrorMap(Context context) {
        if (mU20A3ErrorMap == null) {
            ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
            mU20A3ErrorMap = errorCodeMap;
            errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1_t2_coil));
            mU20A3ErrorMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2_t4_ambient));
            mU20A3ErrorMap.put(StubApp.getString2(13524), context.getString(R.string.fault_code_e3_t3_coil));
            mU20A3ErrorMap.put(StubApp.getString2(13525), context.getString(R.string.fault_code_e4_compressor_drive));
            mU20A3ErrorMap.put(StubApp.getString2(13500), context.getString(R.string.fault_code_e5_eeprom));
            mU20A3ErrorMap.put(StubApp.getString2(13501), context.getString(R.string.fault_code_e6_oil_return));
            mU20A3ErrorMap.put(StubApp.getString2(13526), context.getString(R.string.fault_code_e8_tp_exhaust));
            mU20A3ErrorMap.put(StubApp.getString2(13527), context.getString(R.string.fault_code_f1_ipm_overvoltage));
            mU20A3ErrorMap.put(StubApp.getString2(13528), context.getString(R.string.fault_code_f2_ipm_undervoltage));
            mU20A3ErrorMap.put(StubApp.getString2(13529), context.getString(R.string.fault_code_f3_demagnetization));
            mU20A3ErrorMap.put(StubApp.getString2(13530), context.getString(R.string.fault_code_f4_pfc_overcurrent));
            mU20A3ErrorMap.put(StubApp.getString2(13531), context.getString(R.string.fault_code_f5_ipm_overcurrent));
            mU20A3ErrorMap.put(StubApp.getString2(13532), context.getString(R.string.fault_code_f6_communication));
            mU20A3ErrorMap.put(StubApp.getString2(13519), context.getString(R.string.fault_code_d1_upper_fan));
            mU20A3ErrorMap.put(StubApp.getString2(13520), context.getString(R.string.fault_code_d2_lower_fan));
            mU20A3ErrorMap.put(StubApp.getString2(13521), context.getString(R.string.fault_code_d3_water_motor));
            mU20A3ErrorMap.put(StubApp.getString2(13504), context.getString(R.string.fault_code_p1_input_current));
            mU20A3ErrorMap.put(StubApp.getString2(13533), context.getString(R.string.fault_code_p2_voltage));
            mU20A3ErrorMap.put(StubApp.getString2(13534), context.getString(R.string.fault_code_p3_ipm_hightemp));
            mU20A3ErrorMap.put(StubApp.getString2(7573), context.getString(R.string.fault_code_pl_drain_pump));
            mU20A3ErrorMap.put(StubApp.getString2(13957), context.getString(R.string.fault_code_fl_water_full));
            mU20A3ErrorMap.put(StubApp.getString2(13958), context.getString(R.string.fault_code_hs_defrost));
        }
        return mU20A3ErrorMap;
    }

    public static ErrorCodeMap<String, String> getD50B3ErrorMap(Context context) {
        if (mD50GErrorMap == null) {
            ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
            mD50GErrorMap = errorCodeMap;
            errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
            mD50GErrorMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
            mD50GErrorMap.put(StubApp.getString2(11410), context.getString(R.string.fault_code_l2));
            mD50GErrorMap.put(StubApp.getString2(11260), context.getString(R.string.fault_code_l3));
            mD50GErrorMap.put(StubApp.getString2(13502), context.getString(R.string.fault_code_l4));
            mD50GErrorMap.put(StubApp.getString2(13582), context.getString(R.string.fault_code_p8));
        }
        return mD50GErrorMap;
    }

    public static ErrorCodeMap<String, String> getC65DZErrorMap(Context context) {
        new ErrorCodeMap();
        ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
        errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
        errorCodeMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
        errorCodeMap.put(StubApp.getString2(13524), context.getString(R.string.fault_code_e3_refrigeration_abnormal));
        errorCodeMap.put(StubApp.getString2(13525), context.getString(R.string.fault_code_e4_phase_sequence));
        errorCodeMap.put(StubApp.getString2(13501), context.getString(R.string.fault_code_e6_exhaust_overheat));
        errorCodeMap.put(StubApp.getString2(13526), context.getString(R.string.fault_code_e8));
        errorCodeMap.put(StubApp.getString2(13530), context.getString(R.string.fault_code_f4));
        errorCodeMap.put(StubApp.getString2(11260), context.getString(R.string.fault_code_l3));
        errorCodeMap.put(StubApp.getString2(13502), context.getString(R.string.fault_code_l4));
        errorCodeMap.put(StubApp.getString2(13582), context.getString(R.string.fault_code_p8));
        return errorCodeMap;
    }

    public static ErrorCodeMap<String, String> getLoopFanDJC_R40B_WR50A_W_ErrorMap(Context context) {
        if (mD50GErrorMap == null) {
            ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
            mD50GErrorMap = errorCodeMap;
            errorCodeMap.put(StubApp.getString2(13499), context.getString(R.string.fault_code_e1_fan_stall));
            mD50GErrorMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1_fan_stall));
            mD50GErrorMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2_temp_humidity_sensor));
            mD50GErrorMap.put(StubApp.getString2(13524), context.getString(R.string.fault_code_e3_catalyst_mechanism));
            mD50GErrorMap.put(StubApp.getString2(13677), context.getString(R.string.fault_code_l0));
        }
        return mD50GErrorMap;
    }

    public static ErrorCodeMap<String, String> getQuiltDryerDJB_S10B_W_ErrorMap(Context context) {
        if (mQuiltDryerDJB_S10B_W_Map == null) {
            ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
            mQuiltDryerDJB_S10B_W_Map = errorCodeMap;
            errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1_inlet_temp));
            mQuiltDryerDJB_S10B_W_Map.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2_outlet_temp));
            mQuiltDryerDJB_S10B_W_Map.put(StubApp.getString2(11410), context.getString(R.string.fault_code_l2));
            mQuiltDryerDJB_S10B_W_Map.put(StubApp.getString2(13524), context.getString(R.string.fault_code_e3_ion_generator));
        }
        return mQuiltDryerDJB_S10B_W_Map;
    }

    public static ErrorCodeMap<String, String> get890TebErrorMap(Context context) {
        if (mM890TErrorMap == null) {
            ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
            mM890TErrorMap = errorCodeMap;
            errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1_temp_sensor));
            mM890TErrorMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2_humidity_sensor));
            mM890TErrorMap.put(StubApp.getString2(13524), context.getString(R.string.fault_code_e3_refrigeration_abnormal));
        }
        return mM890TErrorMap;
    }

    public static ErrorCodeMap<String, String> getU20AirErrorMap(Context context) {
        ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
        errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
        errorCodeMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
        errorCodeMap.put(StubApp.getString2(11410), context.getString(R.string.fault_code_l2));
        errorCodeMap.put(StubApp.getString2(11260), context.getString(R.string.fault_code_l3));
        errorCodeMap.put(StubApp.getString2(13502), context.getString(R.string.fault_code_l4));
        return errorCodeMap;
    }

    public static ErrorCodeMap<String, String> getY16ErrorMap(Context context) {
        ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
        errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
        errorCodeMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
        errorCodeMap.put(StubApp.getString2(11260), context.getString(R.string.fault_code_l3));
        errorCodeMap.put(StubApp.getString2(13502), context.getString(R.string.fault_code_l4));
        return errorCodeMap;
    }

    public static ErrorCodeMap<String, String> getSC60YErrorMap(Context context) {
        ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
        errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
        errorCodeMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
        errorCodeMap.put(StubApp.getString2(11410), context.getString(R.string.fault_code_l2_dc_fan));
        errorCodeMap.put(StubApp.getString2(11260), context.getString(R.string.fault_code_l3));
        errorCodeMap.put(StubApp.getString2(13502), context.getString(R.string.fault_code_l4));
        return errorCodeMap;
    }

    public static ErrorCodeMap<String, String> getF15ErrorMap(Context context) {
        ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
        errorCodeMap.put(StubApp.getString2(13524), context.getString(R.string.fault_code_e3_body_separation));
        errorCodeMap.put(StubApp.getString2(11410), context.getString(R.string.fault_code_l2));
        errorCodeMap.put(StubApp.getString2(13669), context.getString(R.string.fault_code_a1_replace_air_filter));
        errorCodeMap.put(StubApp.getString2(13671), context.getString(R.string.fault_code_a3_replace_wet_curtain));
        errorCodeMap.put(StubApp.getString2(13672), context.getString(R.string.fault_code_a4_replace_antiscale_filter));
        errorCodeMap.put(StubApp.getString2(13673), context.getString(R.string.fault_code_a5_replace_sterilizing_agent));
        errorCodeMap.put(StubApp.getString2(13500), context.getString(R.string.fault_code_e5_water_tank_low));
        errorCodeMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2_temp_humidity_sensor));
        return errorCodeMap;
    }

    public static ErrorCodeMap<String, String> getF15ProErrorMap(Context context) {
        ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
        errorCodeMap.put(StubApp.getString2(13524), context.getString(R.string.fault_code_e3_body_separation));
        errorCodeMap.put(StubApp.getString2(11410), context.getString(R.string.fault_code_l2));
        errorCodeMap.put(StubApp.getString2(13669), context.getString(R.string.fault_code_a1_replace_air_filter));
        errorCodeMap.put(StubApp.getString2(13671), context.getString(R.string.fault_code_a3_replace_wet_curtain));
        errorCodeMap.put(StubApp.getString2(13672), context.getString(R.string.fault_code_a4_replace_antiscale_filter));
        errorCodeMap.put(StubApp.getString2(13500), context.getString(R.string.fault_code_e5_water_tank_low));
        errorCodeMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2_temp_humidity_sensor));
        return errorCodeMap;
    }

    public static ErrorCodeMap<String, String> getH7ErrorMap(Context context) {
        ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
        errorCodeMap.put(StubApp.getString2(13507), context.getString(R.string.fault_code_p6_device_tilted));
        errorCodeMap.put(StubApp.getString2(13581), context.getString(R.string.fault_code_p7));
        errorCodeMap.put(StubApp.getString2(13669), context.getString(R.string.fault_code_e5_water_tank_low));
        errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1_fan_abnormal));
        errorCodeMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2_temp_humidity_fault));
        return errorCodeMap;
    }

    public static ErrorCodeMap<String, String> getA06A4ErrorMap(Context context) {
        ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
        errorCodeMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2_temp_humidity_fault));
        errorCodeMap.put(StubApp.getString2(13576), context.getString(R.string.fault_code_ea_condenser_inlet_temp));
        errorCodeMap.put(StubApp.getString2(13577), context.getString(R.string.fault_code_eb_ptc_surface));
        errorCodeMap.put(StubApp.getString2(13578), context.getString(R.string.fault_code_ec_circulation_motor));
        errorCodeMap.put(StubApp.getString2(11410), context.getString(R.string.fault_code_l2_dc_fan_repair));
        errorCodeMap.put(StubApp.getString2(11260), context.getString(R.string.fault_code_l3_high_temp_protection));
        errorCodeMap.put(StubApp.getString2(13502), context.getString(R.string.fault_code_l4_low_temp_protection));
        errorCodeMap.put(StubApp.getString2(13580), context.getString(R.string.fault_code_l6));
        return errorCodeMap;
    }

    public static ErrorCodeMap<String, String> getB13A3ErrorMap(Context context) {
        if (mB13A3ErrorMap == null) {
            ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
            mB13A3ErrorMap = errorCodeMap;
            errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
            mB13A3ErrorMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
            mB13A3ErrorMap.put(StubApp.getString2(11410), context.getString(R.string.fault_code_l2_dc_fan));
            mB13A3ErrorMap.put(StubApp.getString2(11260), context.getString(R.string.fault_code_l3));
            mB13A3ErrorMap.put(StubApp.getString2(13502), context.getString(R.string.fault_code_l4));
        }
        return mB13A3ErrorMap;
    }

    public static ErrorCodeMap<String, String> getRT12ErrorMap(Context context) {
        if (mRT12ErrorMap == null) {
            ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
            mRT12ErrorMap = errorCodeMap;
            errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
            mRT12ErrorMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
            mRT12ErrorMap.put(StubApp.getString2(11410), context.getString(R.string.fault_code_l2_dc_fan));
            mRT12ErrorMap.put(StubApp.getString2(11260), context.getString(R.string.fault_code_l3));
            mRT12ErrorMap.put(StubApp.getString2(13502), context.getString(R.string.fault_code_l4));
        }
        return mRT12ErrorMap;
    }

    public static ErrorCodeMap<String, String> getP30ErrorMap(Context context) {
        if (mP30ErrorMap == null) {
            ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
            mP30ErrorMap = errorCodeMap;
            errorCodeMap.put(StubApp.getString2(13522), context.getString(R.string.fault_code_e1));
            mP30ErrorMap.put(StubApp.getString2(13523), context.getString(R.string.fault_code_e2));
            mP30ErrorMap.put(StubApp.getString2(11410), context.getString(R.string.fault_code_l2_dc_fan));
            mP30ErrorMap.put(StubApp.getString2(11260), context.getString(R.string.fault_code_l3));
            mP30ErrorMap.put(StubApp.getString2(13502), context.getString(R.string.fault_code_l4));
        }
        return mP30ErrorMap;
    }
}
