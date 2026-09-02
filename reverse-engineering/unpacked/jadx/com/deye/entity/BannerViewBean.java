package com.deye.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BannerViewBean.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0014\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0011\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u0000H\u0096\u0002J\b\u0010 \u001a\u00020\u0004H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001e\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R \u0010\u0015\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001e\u0010\u0018\u001a\u00020\r8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0011R \u0010\u001b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\b¨\u0006!"}, d2 = {"Lcom/deye/entity/BannerViewBean;", "", "()V", "adUrl", "", "getAdUrl", "()Ljava/lang/String;", "setAdUrl", "(Ljava/lang/String;)V", "imageUrl", "getImageUrl", "setImageUrl", "order", "", "getOrder", "()I", "setOrder", "(I)V", "resourceInt", "getResourceInt", "setResourceInt", "showEnd", "getShowEnd", "setShowEnd", "showInterval", "getShowInterval", "setShowInterval", "showStart", "getShowStart", "setShowStart", "compareTo", "other", "toString", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class BannerViewBean implements Comparable<BannerViewBean> {

    @JSONField(name = "sort")
    private int order;
    private int resourceInt;

    @JSONField(name = "show_end")
    private String showEnd;

    @JSONField(name = "show_start")
    private String showStart;

    @JSONField(name = "image_url")
    private String imageUrl = "";

    @JSONField(name = "ad_url")
    private String adUrl = "";

    @JSONField(name = "show_interval")
    private int showInterval = 3000;

    public final int getResourceInt() {
        return this.resourceInt;
    }

    public final void setResourceInt(int i) {
        this.resourceInt = i;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final void setImageUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.imageUrl = str;
    }

    public final int getOrder() {
        return this.order;
    }

    public final void setOrder(int i) {
        this.order = i;
    }

    public final String getAdUrl() {
        return this.adUrl;
    }

    public final void setAdUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.adUrl = str;
    }

    public final String getShowStart() {
        return this.showStart;
    }

    public final void setShowStart(String str) {
        this.showStart = str;
    }

    public final String getShowEnd() {
        return this.showEnd;
    }

    public final void setShowEnd(String str) {
        this.showEnd = str;
    }

    public final int getShowInterval() {
        return this.showInterval;
    }

    public final void setShowInterval(int i) {
        this.showInterval = i;
    }

    @Override // java.lang.Comparable
    public int compareTo(BannerViewBean other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.order - other.order;
    }

    public String toString() {
        return StubApp.getString2(13977) + this.resourceInt + StubApp.getString2(13978) + this.imageUrl + StubApp.getString2(13979) + this.order + StubApp.getString2(13980) + this.adUrl + StubApp.getString2(13981) + this.showStart + StubApp.getString2(13982) + this.showEnd + StubApp.getString2(13983) + this.showInterval + StubApp.getString2(2345);
    }
}
