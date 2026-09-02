package com.deye.entity;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class DevinfoBean {
    public String MAC;
    public String fog_v3_deviceid;
    public String fog_v3_devicesn;
    public String fog_v3_productid;
    public boolean stop_easylink;

    public String getFog_v3_productid() {
        return this.fog_v3_productid;
    }

    public void setFog_v3_productid(String str) {
        this.fog_v3_productid = str;
    }

    public boolean getStop_easylink() {
        return this.stop_easylink;
    }

    public void setStop_easylink(boolean z) {
        this.stop_easylink = z;
    }

    public String getMAC() {
        return this.MAC;
    }

    public void setMAC(String str) {
        this.MAC = str;
    }

    public String getFog_v3_devicesn() {
        return this.fog_v3_devicesn;
    }

    public void setFog_v3_devicesn(String str) {
        this.fog_v3_devicesn = str;
    }

    public String getFog_v3_deviceid() {
        return this.fog_v3_deviceid;
    }

    public void setFog_v3_deviceid(String str) {
        this.fog_v3_deviceid = str;
    }

    public String toString() {
        return StubApp.getString2(13986) + this.MAC + StubApp.getString2(13987) + this.fog_v3_devicesn + StubApp.getString2(13988) + this.fog_v3_deviceid + StubApp.getString2(13989) + this.fog_v3_productid + StubApp.getString2(13990) + this.stop_easylink + '}';
    }
}
