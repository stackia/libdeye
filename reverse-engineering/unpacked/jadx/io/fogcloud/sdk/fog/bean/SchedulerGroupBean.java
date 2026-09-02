package io.fogcloud.sdk.fog.bean;

import com.amap.location.support.bean.location.AmapLocationNetwork;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class SchedulerGroupBean implements Serializable {
    public String day_of_week;
    public boolean enable;
    public boolean force;
    public String group_id;
    public SchedulerSwitchBean schedule_off;
    public SchedulerSwitchBean schedule_on;

    public List<Integer> getDayOfWeekList() {
        ArrayList arrayList = new ArrayList();
        String str = this.day_of_week;
        if (str != null && !str.isEmpty()) {
            for (String str2 : this.day_of_week.split(",")) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str2)));
            }
        }
        return arrayList;
    }

    public static class SchedulerSwitchBean implements Serializable {
        public SchedulerCommandBean commands;
        public int hour;
        public int minute;
        public String task_id;

        public void initCommandsIfNull() {
            if (this.commands == null) {
                this.commands = new SchedulerCommandBean();
            }
        }

        public String getShowTimeText() {
            return getTimeString(this.hour) + ":" + getTimeString(this.minute);
        }

        private String getTimeString(int i) {
            if (i < 10) {
                return AmapLocationNetwork.RESULT_TYPE_GPS + i;
            }
            return "" + i;
        }
    }
}
