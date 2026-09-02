package io.fogcloud.sdk.fog.bean;

import java.io.Serializable;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class BaseBean implements Serializable {
    private MetaBean meta;

    public BaseBean() {
    }

    public BaseBean(MetaBean metaBean) {
        this.meta = metaBean;
    }

    public MetaBean getMeta() {
        return this.meta;
    }

    public void setMeta(MetaBean metaBean) {
        this.meta = metaBean;
    }

    public static class MetaBean {
        private int code;
        private String message;

        public MetaBean() {
        }

        public MetaBean(String str, int i) {
            this.message = str;
            this.code = i;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String str) {
            this.message = str;
        }

        public int getCode() {
            return this.code;
        }

        public void setCode(int i) {
            this.code = i;
        }
    }
}
