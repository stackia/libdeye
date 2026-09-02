package io.fogcloud.sdk.fog.bean;

import kotlin.Metadata;

/* compiled from: LocationWeather.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lio/fogcloud/sdk/fog/bean/LocationWeather;", "", "()V", "last_update", "", "getLast_update", "()Ljava/lang/String;", "setLast_update", "(Ljava/lang/String;)V", "location", "Lio/fogcloud/sdk/fog/bean/LocationInfoBean;", "getLocation", "()Lio/fogcloud/sdk/fog/bean/LocationInfoBean;", "setLocation", "(Lio/fogcloud/sdk/fog/bean/LocationInfoBean;)V", "realtime", "Lio/fogcloud/sdk/fog/bean/NowWeatherBean;", "getRealtime", "()Lio/fogcloud/sdk/fog/bean/NowWeatherBean;", "setRealtime", "(Lio/fogcloud/sdk/fog/bean/NowWeatherBean;)V", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class LocationWeather {
    private String last_update;
    private LocationInfoBean location;
    private NowWeatherBean realtime;

    public final LocationInfoBean getLocation() {
        return this.location;
    }

    public final void setLocation(LocationInfoBean locationInfoBean) {
        this.location = locationInfoBean;
    }

    public final NowWeatherBean getRealtime() {
        return this.realtime;
    }

    public final void setRealtime(NowWeatherBean nowWeatherBean) {
        this.realtime = nowWeatherBean;
    }

    public final String getLast_update() {
        return this.last_update;
    }

    public final void setLast_update(String str) {
        this.last_update = str;
    }
}
