package io.fogcloud.sdk.fog.bean;

import java.io.Serializable;
import kotlin.Metadata;

/* compiled from: AdvertiseInfoBean.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0015\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0006\"\u0004\b\u0012\u0010\bR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0006\"\u0004\b\u0015\u0010\bR\u001e\u0010\u0016\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000eR\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0006\"\u0004\b\u001b\u0010\bR\u001e\u0010\u001c\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u001d\u0010\f\"\u0004\b\u001e\u0010\u000e¨\u0006\u001f"}, d2 = {"Lio/fogcloud/sdk/fog/bean/AdvertiseInfoBean;", "Ljava/io/Serializable;", "()V", "ad_url", "", "getAd_url", "()Ljava/lang/String;", "setAd_url", "(Ljava/lang/String;)V", "id", "", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "image_url", "getImage_url", "setImage_url", "show_end", "getShow_end", "setShow_end", "show_interval", "getShow_interval", "setShow_interval", "show_start", "getShow_start", "setShow_start", "status", "getStatus", "setStatus", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class AdvertiseInfoBean implements Serializable {
    private String ad_url;
    private Integer id;
    private String image_url;
    private String show_end;
    private Integer show_interval;
    private String show_start;
    private Integer status;

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final Integer getStatus() {
        return this.status;
    }

    public final void setStatus(Integer num) {
        this.status = num;
    }

    public final String getImage_url() {
        return this.image_url;
    }

    public final void setImage_url(String str) {
        this.image_url = str;
    }

    public final Integer getShow_interval() {
        return this.show_interval;
    }

    public final void setShow_interval(Integer num) {
        this.show_interval = num;
    }

    public final String getAd_url() {
        return this.ad_url;
    }

    public final void setAd_url(String str) {
        this.ad_url = str;
    }

    public final String getShow_start() {
        return this.show_start;
    }

    public final void setShow_start(String str) {
        this.show_start = str;
    }

    public final String getShow_end() {
        return this.show_end;
    }

    public final void setShow_end(String str) {
        this.show_end = str;
    }
}
