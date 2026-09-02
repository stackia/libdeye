package io.fogcloud.sdk.fog.device_state_refresh_service.bean;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class BaseBean {
    private StateBean state;

    public StateBean getState() {
        return this.state;
    }

    public void setState(StateBean stateBean) {
        this.state = stateBean;
    }

    public static class StateBean {
        private ReportedBean reported;

        public ReportedBean getReported() {
            return this.reported;
        }

        public void setReported(ReportedBean reportedBean) {
            this.reported = reportedBean;
        }

        public static class ReportedBean {
            private String DeviceId;
            private String deviceId;

            public String getDeviceId() {
                return this.DeviceId;
            }

            public void setDeviceId(String str) {
                this.DeviceId = str;
            }
        }
    }

    public String toString() {
        return StubApp.getString2(45040) + this.state + '}';
    }
}
