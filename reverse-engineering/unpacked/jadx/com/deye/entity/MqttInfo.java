package com.deye.entity;

import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class MqttInfo {
    public String clientid;
    private int expire;
    public String mqtt_host;
    public String password;
    public String ssl_port;
    public Topic topic;
    public String username;
    public String ws_port;

    public class Topic {
        private List<String> all;
        private List<String> pub;
        private List<String> sub;

        public Topic() {
        }

        public void setAll(List<String> list) {
            this.all = list;
        }

        public List<String> getAll() {
            return this.all;
        }

        public void setPub(List<String> list) {
            this.pub = list;
        }

        public List<String> getPub() {
            return this.pub;
        }

        public void setSub(List<String> list) {
            this.sub = list;
        }

        public List<String> getSub() {
            return this.sub;
        }
    }
}
