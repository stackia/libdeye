package com.deye.entity;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class BindCheckBean {
    private DataBean data;
    private MetaBean meta;

    public MetaBean getMeta() {
        return this.meta;
    }

    public void setMeta(MetaBean metaBean) {
        this.meta = metaBean;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public static class MetaBean {
        private int code;
        private String message;

        public int getCode() {
            return this.code;
        }

        public void setCode(int i) {
            this.code = i;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String str) {
            this.message = str;
        }
    }

    public static class DataBean {
        private String device_id;

        public String getDevice_id() {
            return this.device_id;
        }

        public void setDevice_id(String str) {
            this.device_id = str;
        }
    }
}
