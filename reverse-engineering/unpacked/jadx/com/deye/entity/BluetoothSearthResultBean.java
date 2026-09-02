package com.deye.entity;

import android.graphics.Bitmap;
import java.util.Objects;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class BluetoothSearthResultBean {
    private String mac;
    private Bitmap picture;
    private String pname;
    private String productid;

    public String getProductid() {
        return this.productid;
    }

    public void setProductid(String str) {
        this.productid = str;
    }

    public String getPname() {
        return this.pname;
    }

    public void setPname(String str) {
        this.pname = str;
    }

    public Bitmap getPicture() {
        return this.picture;
    }

    public void setPicture(Bitmap bitmap) {
        this.picture = bitmap;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BluetoothSearthResultBean) {
            return getMac().equals(((BluetoothSearthResultBean) obj).getMac());
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(getMac());
    }
}
