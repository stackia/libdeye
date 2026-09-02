package com.deye.combo.bean;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class WifiInfoBean {
    private Parmas parmas;
    private String password;
    private String randomNumber;
    private String ssid;

    public static class Parmas {
        public String api;
    }

    public Parmas getParmas() {
        return this.parmas;
    }

    public void setParmas(Parmas parmas) {
        this.parmas = parmas;
    }

    public String getSsid() {
        return this.ssid;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getRandomNumber() {
        return this.randomNumber;
    }

    public void setRandomNumber(String str) {
        this.randomNumber = str;
    }
}
