package io.fogcloud.sdk.fog.bean;

import kotlin.Metadata;

/* compiled from: LocationWeather.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0006\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001c\u0010!\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\b¨\u0006$"}, d2 = {"Lio/fogcloud/sdk/fog/bean/NowWeatherBean;", "", "()V", "code", "", "getCode", "()Ljava/lang/String;", "setCode", "(Ljava/lang/String;)V", "feels_like", "", "getFeels_like", "()I", "setFeels_like", "(I)V", "rh", "getRh", "()Ljava/lang/Integer;", "setRh", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "temp", "", "getTemp", "()D", "setTemp", "(D)V", "text", "getText", "setText", "wind_class", "getWind_class", "setWind_class", "wind_dir", "getWind_dir", "setWind_dir", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class NowWeatherBean {
    private String code;
    private int feels_like;
    private Integer rh;
    private double temp;
    private String text;
    private String wind_class;
    private String wind_dir;

    public final String getText() {
        return this.text;
    }

    public final void setText(String str) {
        this.text = str;
    }

    public final String getCode() {
        return this.code;
    }

    public final void setCode(String str) {
        this.code = str;
    }

    public final double getTemp() {
        return this.temp;
    }

    public final void setTemp(double d) {
        this.temp = d;
    }

    public final int getFeels_like() {
        return this.feels_like;
    }

    public final void setFeels_like(int i) {
        this.feels_like = i;
    }

    public final Integer getRh() {
        return this.rh;
    }

    public final void setRh(Integer num) {
        this.rh = num;
    }

    public final String getWind_class() {
        return this.wind_class;
    }

    public final void setWind_class(String str) {
        this.wind_class = str;
    }

    public final String getWind_dir() {
        return this.wind_dir;
    }

    public final void setWind_dir(String str) {
        this.wind_dir = str;
    }
}
