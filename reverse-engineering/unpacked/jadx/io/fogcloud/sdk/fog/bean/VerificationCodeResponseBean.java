package io.fogcloud.sdk.fog.bean;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class VerificationCodeResponseBean {
    private DataBean data;
    private MetaBean meta;

    public VerificationCodeResponseBean() {
    }

    public VerificationCodeResponseBean(MetaBean metaBean) {
        this.meta = metaBean;
    }

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

    public class DataBean {
        String type;

        public DataBean() {
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }
    }
}
