package com.deye.entity;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PermissionJsonBean.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/deye/entity/PermissionJsonBean;", "Ljava/io/Serializable;", "id", "", "name", "", "content", "(ILjava/lang/String;Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "getId", "()I", "getName", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final /* data */ class PermissionJsonBean implements Serializable {
    private final String content;
    private final int id;
    private final String name;

    public static /* synthetic */ PermissionJsonBean copy$default(PermissionJsonBean permissionJsonBean, int i, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = permissionJsonBean.id;
        }
        if ((i2 & 2) != 0) {
            str = permissionJsonBean.name;
        }
        if ((i2 & 4) != 0) {
            str2 = permissionJsonBean.content;
        }
        return permissionJsonBean.copy(i, str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    public final PermissionJsonBean copy(int id, String name, String content) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(content, "content");
        return new PermissionJsonBean(id, name, content);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PermissionJsonBean)) {
            return false;
        }
        PermissionJsonBean permissionJsonBean = (PermissionJsonBean) other;
        return this.id == permissionJsonBean.id && Intrinsics.areEqual(this.name, permissionJsonBean.name) && Intrinsics.areEqual(this.content, permissionJsonBean.content);
    }

    public int hashCode() {
        return (((Integer.hashCode(this.id) * 31) + this.name.hashCode()) * 31) + this.content.hashCode();
    }

    public String toString() {
        return "PermissionJsonBean(id=" + this.id + ", name=" + this.name + ", content=" + this.content + ")";
    }

    public PermissionJsonBean(int i, String name, String content) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(content, "content");
        this.id = i;
        this.name = name;
        this.content = content;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getContent() {
        return this.content;
    }
}
