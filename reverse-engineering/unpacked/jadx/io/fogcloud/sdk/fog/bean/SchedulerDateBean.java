package io.fogcloud.sdk.fog.bean;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SchedulerDateBean.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006 "}, d2 = {"Lio/fogcloud/sdk/fog/bean/SchedulerDateBean;", "Ljava/io/Serializable;", "viewType", "", "schedulerBean", "Lio/fogcloud/sdk/fog/bean/SchedulerBean;", "humBean", "Lio/fogcloud/sdk/fog/bean/SchedulerHumBean;", "(ILio/fogcloud/sdk/fog/bean/SchedulerBean;Lio/fogcloud/sdk/fog/bean/SchedulerHumBean;)V", "getHumBean", "()Lio/fogcloud/sdk/fog/bean/SchedulerHumBean;", "setHumBean", "(Lio/fogcloud/sdk/fog/bean/SchedulerHumBean;)V", "getSchedulerBean", "()Lio/fogcloud/sdk/fog/bean/SchedulerBean;", "setSchedulerBean", "(Lio/fogcloud/sdk/fog/bean/SchedulerBean;)V", "getViewType", "()I", "setViewType", "(I)V", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final /* data */ class SchedulerDateBean implements Serializable {
    private SchedulerHumBean humBean;
    private SchedulerBean schedulerBean;
    private int viewType;

    public static /* synthetic */ SchedulerDateBean copy$default(SchedulerDateBean schedulerDateBean, int i, SchedulerBean schedulerBean, SchedulerHumBean schedulerHumBean, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = schedulerDateBean.viewType;
        }
        if ((i2 & 2) != 0) {
            schedulerBean = schedulerDateBean.schedulerBean;
        }
        if ((i2 & 4) != 0) {
            schedulerHumBean = schedulerDateBean.humBean;
        }
        return schedulerDateBean.copy(i, schedulerBean, schedulerHumBean);
    }

    /* renamed from: component1, reason: from getter */
    public final int getViewType() {
        return this.viewType;
    }

    /* renamed from: component2, reason: from getter */
    public final SchedulerBean getSchedulerBean() {
        return this.schedulerBean;
    }

    /* renamed from: component3, reason: from getter */
    public final SchedulerHumBean getHumBean() {
        return this.humBean;
    }

    public final SchedulerDateBean copy(int viewType, SchedulerBean schedulerBean, SchedulerHumBean humBean) {
        return new SchedulerDateBean(viewType, schedulerBean, humBean);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SchedulerDateBean)) {
            return false;
        }
        SchedulerDateBean schedulerDateBean = (SchedulerDateBean) other;
        return this.viewType == schedulerDateBean.viewType && Intrinsics.areEqual(this.schedulerBean, schedulerDateBean.schedulerBean) && Intrinsics.areEqual(this.humBean, schedulerDateBean.humBean);
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.viewType) * 31;
        SchedulerBean schedulerBean = this.schedulerBean;
        int iHashCode2 = (iHashCode + (schedulerBean == null ? 0 : schedulerBean.hashCode())) * 31;
        SchedulerHumBean schedulerHumBean = this.humBean;
        return iHashCode2 + (schedulerHumBean != null ? schedulerHumBean.hashCode() : 0);
    }

    public String toString() {
        return "SchedulerDateBean(viewType=" + this.viewType + ", schedulerBean=" + this.schedulerBean + ", humBean=" + this.humBean + ")";
    }

    public SchedulerDateBean(int i, SchedulerBean schedulerBean, SchedulerHumBean schedulerHumBean) {
        this.viewType = i;
        this.schedulerBean = schedulerBean;
        this.humBean = schedulerHumBean;
    }

    public /* synthetic */ SchedulerDateBean(int i, SchedulerBean schedulerBean, SchedulerHumBean schedulerHumBean, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : schedulerBean, (i2 & 4) != 0 ? null : schedulerHumBean);
    }

    public final int getViewType() {
        return this.viewType;
    }

    public final void setViewType(int i) {
        this.viewType = i;
    }

    public final SchedulerBean getSchedulerBean() {
        return this.schedulerBean;
    }

    public final void setSchedulerBean(SchedulerBean schedulerBean) {
        this.schedulerBean = schedulerBean;
    }

    public final SchedulerHumBean getHumBean() {
        return this.humBean;
    }

    public final void setHumBean(SchedulerHumBean schedulerHumBean) {
        this.humBean = schedulerHumBean;
    }
}
