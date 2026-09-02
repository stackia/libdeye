package com.deye.entity;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class WeatherBean {
    public int aqi;
    public int cloudrate;
    public double humidity;
    public int pm25;
    public PrecipitationBean precipitation;
    public String skycon;
    public String status;
    public int temperature;
    public WindBean wind;

    public static class PrecipitationBean {
        public LocalBean local;
        public NearestBean nearest;

        public static class LocalBean {
            public String datasource;
            public int intensity;
            public String status;
        }

        public static class NearestBean {
            public int distance;
            public int intensity;
            public String status;
        }
    }

    public static class WindBean {
        public double direction;
        public double speed;
    }
}
