package io.fogcloud.sdk.fog.bean;

import com.google.gson.annotations.SerializedName;
import com.stub.StubApp;
import java.io.Serializable;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class DeviceListBean implements Serializable {
    public static final String DYD_B13A3 = StubApp.getString2(13421);
    public static final String DYD_LOOP_FAN = StubApp.getString2(13424);
    public static final String DYD_P40 = StubApp.getString2(13418);
    public static final String DYD_RT12 = StubApp.getString2(13405);
    public static final String DYP_A12A452 = StubApp.getString2(13396);
    public static final String DY_ES25A3 = StubApp.getString2(13435);
    public static final String DY_F15 = StubApp.getString2(13395);
    public static final String DY_F15Pro = StubApp.getString2(13436);
    public static final String DY_H7 = StubApp.getString2(13400);
    public static final String DY_P30 = StubApp.getString2(13426);
    private DehumidifierBean dehumidifierBean;
    private String device_id;
    private String device_name;
    private String gatewaytype;
    public boolean has_room;
    private boolean is_combo;
    public LocationBean location;
    public LoopFanBean loopFanBean;
    private String mac;
    private Boolean online;
    private Object payload;
    public String picture_v3;
    private int platform;
    private String product_icon;
    private String product_id;
    private String product_name;
    private String product_type;

    @SerializedName("protocol_version")
    private String protocolVersion;
    private int role;
    public String room_display_name;
    public int user_count;
    public Float work_time;

    public String getPayload() {
        Object obj = this.payload;
        if (obj instanceof String) {
            return obj.toString();
        }
        return "";
    }

    public void setPayload(String str) {
        this.payload = str;
    }

    public DehumidifierBean getDehumidifierBean() {
        return this.dehumidifierBean;
    }

    public void setDehumidifierBean(DehumidifierBean dehumidifierBean) {
        this.dehumidifierBean = dehumidifierBean;
    }

    public int getPlatform() {
        return this.platform;
    }

    public void setPlatform(int i) {
        this.platform = i;
    }

    public boolean isFogPlatform() {
        int i = this.platform;
        return i == 2 || i == 3;
    }

    public String getProtocolVersion() {
        return this.protocolVersion;
    }

    public void setProtocolVersion(String str) {
        this.protocolVersion = str;
    }

    public DeviceListBean() {
    }

    public DeviceListBean(String str, String str2, String str3, String str4, String str5, int i, Boolean bool, String str6, String str7, String str8, boolean z) {
        this.product_id = str;
        this.product_icon = str2;
        this.device_name = str3;
        this.gatewaytype = str4;
        this.mac = str5;
        this.role = i;
        this.online = bool;
        this.product_name = str6;
        this.device_id = str7;
        this.product_type = str8;
        this.is_combo = z;
    }

    public String getProduct_id() {
        return this.product_id;
    }

    public void setProduct_id(String str) {
        this.product_id = str;
    }

    public String getProduct_icon() {
        return this.product_icon;
    }

    public void setProduct_icon(String str) {
        this.product_icon = str;
    }

    public String getDevice_name() {
        return this.device_name;
    }

    public void setDevice_name(String str) {
        this.device_name = str;
    }

    public String getGatewaytype() {
        return this.gatewaytype;
    }

    public void setGatewaytype(String str) {
        this.gatewaytype = str;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public int getRole() {
        return this.role;
    }

    public void setRole(int i) {
        this.role = i;
    }

    public Boolean getOnline() {
        return this.online;
    }

    public void setOnline(Boolean bool) {
        this.online = bool;
    }

    public String getProduct_name() {
        return this.product_name;
    }

    public void setProduct_name(String str) {
        this.product_name = str;
    }

    public String getDevice_id() {
        return this.device_id;
    }

    public void setDevice_id(String str) {
        this.device_id = str;
    }

    public String getProduct_type() {
        return this.product_type;
    }

    public void setProduct_type(String str) {
        this.product_type = str;
    }

    public boolean isIs_combo() {
        return this.is_combo;
    }

    public void setIs_combo(boolean z) {
        this.is_combo = z;
    }

    public boolean isUseComboProtocol() {
        return this.is_combo && "combo_V1.0".equals(this.protocolVersion);
    }

    public String toString() {
        return "DeviceListBean{product_id='" + this.product_id + "', product_icon='" + this.product_icon + "', device_name='" + this.device_name + "', gatewaytype='" + this.gatewaytype + "', mac='" + this.mac + "', role='" + this.role + "', online='" + this.online + "', product_name='" + this.product_name + "', dehumidifierBean=" + this.dehumidifierBean + ", device_id='" + this.device_id + "', product_type='" + this.product_type + "', is_combo=" + this.is_combo + ", protocolVersion='" + this.protocolVersion + "', platform=" + this.platform + '}';
    }

    public String toSimpleString() {
        return "DeviceListBean{product_id='" + this.product_id + "', device_name='" + this.device_name + "', mac='" + this.mac + "', online='" + this.online + "', device_id='" + this.device_id + "', is_combo=" + this.is_combo + ", protocolVersion='" + this.protocolVersion + "', platform=" + this.platform + '}';
    }

    public boolean isLoopFan() {
        return this.product_id.equals("2369e8c238a411e99d4c00163e0c1b21");
    }

    public boolean isHumidifier() {
        return this.product_id.equals("bd057094877911ef92cc00505685f729") || this.product_id.equals("86900c3c3ac411f083fa0242ac480009") || this.product_id.equals("91d711763ac411f080fe0242ac480009");
    }

    public boolean isFanDevice() {
        return isHumidifier() || this.product_id.equals("be8f5e6a893111f0aebc0242ac480009") || this.product_id.equals("d71936c6951c11f0a8200242ac480009") || this.product_id.equals("e2d2d33ad99311f0abd90242ac480009") || this.product_id.equals("ef387edadb1011f0830f0242ac480009") || this.product_id.equals("537ed2b4d4c111f080e00242ac480009");
    }

    public boolean isAirConditioner() {
        return this.product_id.equals("293630043aa511ef803d0242ac480009");
    }
}
