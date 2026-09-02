package io.fogcloud.sdk.fog.bean;

import com.stub.StubApp;
import java.io.Serializable;
import kotlin.Metadata;

/* compiled from: SchedulerHumBean.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000f\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001e\u0010\u001e\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u0006%"}, d2 = {"Lio/fogcloud/sdk/fog/bean/SchedulerHumBean;", "Ljava/io/Serializable;", "()V", "day_of_week", "", "getDay_of_week", "()Ljava/lang/String;", "setDay_of_week", "(Ljava/lang/String;)V", "device_id", "getDevice_id", "setDevice_id", "enabled", "", "getEnabled", "()Z", "setEnabled", "(Z)V", "schedule_type", "getSchedule_type", "setSchedule_type", "threshold_down", "", "getThreshold_down", "()I", "setThreshold_down", "(I)V", "threshold_up", "getThreshold_up", "setThreshold_up", "trigger_id", "getTrigger_id", "()Ljava/lang/Integer;", "setTrigger_id", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "Companion", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class SchedulerHumBean implements Serializable {
    private String day_of_week;
    private String device_id;
    private boolean enabled;
    private String schedule_type;
    private int threshold_down;
    private int threshold_up;
    private Integer trigger_id;
    public static final String TYPE_CRON = StubApp.getString2(13484);
    public static final String TYPE_EVERY = StubApp.getString2(13483);
    public static final String TYPE_ONCE = StubApp.getString2(45039);

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    public final String getDevice_id() {
        return this.device_id;
    }

    public final void setDevice_id(String str) {
        this.device_id = str;
    }

    public final String getSchedule_type() {
        return this.schedule_type;
    }

    public final void setSchedule_type(String str) {
        this.schedule_type = str;
    }

    public final Integer getTrigger_id() {
        return this.trigger_id;
    }

    public final void setTrigger_id(Integer num) {
        this.trigger_id = num;
    }

    public final int getThreshold_up() {
        return this.threshold_up;
    }

    public final void setThreshold_up(int i) {
        this.threshold_up = i;
    }

    public final int getThreshold_down() {
        return this.threshold_down;
    }

    public final void setThreshold_down(int i) {
        this.threshold_down = i;
    }

    public final String getDay_of_week() {
        return this.day_of_week;
    }

    public final void setDay_of_week(String str) {
        this.day_of_week = str;
    }
}
