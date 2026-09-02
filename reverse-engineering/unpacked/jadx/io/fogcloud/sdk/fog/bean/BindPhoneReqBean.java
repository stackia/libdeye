package io.fogcloud.sdk.fog.bean;

import java.io.Serializable;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class BindPhoneReqBean implements Serializable {
    public String access_token;
    public String app_id;
    public String extend;
    public String open_id;
    public String phone_number;
    public String platform = "wechat";
    public String pushtype;
    public String vercode;

    public BindPhoneReqBean(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.open_id = str;
        this.phone_number = str2;
        this.access_token = str3;
        this.vercode = str4;
        this.app_id = str5;
        this.pushtype = str6;
        this.extend = str7;
    }
}
