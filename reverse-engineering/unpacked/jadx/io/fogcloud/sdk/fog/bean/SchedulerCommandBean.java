package io.fogcloud.sdk.fog.bean;

import java.io.Serializable;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class SchedulerCommandBean implements Serializable {
    public int Mode;
    public int SetHumidity;
    public int WindSpeed;

    public String toSpeedText() {
        if (this.WindSpeed == 0) {
            return "未设置";
        }
        return this.WindSpeed + "档";
    }

    public String toHumidityText() {
        if (this.SetHumidity == 0) {
            return "未设置";
        }
        return this.SetHumidity + "%";
    }

    public String toModeText() {
        int i = this.Mode;
        if (i == 1) {
            return "睡眠模式";
        }
        if (i == 2) {
            return "持续加湿";
        }
        if (i == 3) {
            return "智能模式";
        }
        if (i == 4) {
            return "手动模式";
        }
        return "";
    }

    public String toH7ModeText() {
        int i = this.Mode;
        if (i == 1) {
            return "睡眠模式";
        }
        if (i == 2) {
            return "强力模式";
        }
        if (i == 3) {
            return "恒湿模式";
        }
        if (i == 5) {
            return "风干模式";
        }
        return "";
    }
}
