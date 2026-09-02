package com.deye.entity;

import io.fogcloud.sdk.fog.bean.BaseBean;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class CheckCodeBean extends BaseBean {
    private DataBean data;

    public CheckCodeBean() {
    }

    public CheckCodeBean(BaseBean.MetaBean metaBean) {
        super(metaBean);
    }

    public CheckCodeBean(BaseBean.MetaBean metaBean, DataBean dataBean) {
        super(metaBean);
        this.data = dataBean;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public class DataBean {
        private String client_id;
        private String clientid;
        private String token;

        public DataBean() {
        }

        public DataBean(String str, String str2, String str3) {
            this.token = str;
            this.client_id = str2;
            this.clientid = str3;
        }

        public String getToken() {
            return this.token;
        }

        public void setToken(String str) {
            this.token = str;
        }

        public String getClient_id() {
            return this.client_id;
        }

        public void setClient_id(String str) {
            this.client_id = str;
        }

        public String getClientid() {
            return this.clientid;
        }

        public void setClientid(String str) {
            this.clientid = str;
        }
    }
}
