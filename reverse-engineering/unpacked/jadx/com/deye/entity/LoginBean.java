package com.deye.entity;

import io.fogcloud.sdk.fog.bean.BaseBean;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class LoginBean extends BaseBean {
    private DataBean data;

    public LoginBean() {
    }

    public LoginBean(BaseBean.MetaBean metaBean, DataBean dataBean) {
        super(metaBean);
        this.data = dataBean;
    }

    public LoginBean(DataBean dataBean) {
        this.data = dataBean;
    }

    public LoginBean(BaseBean.MetaBean metaBean) {
        super(metaBean);
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public static class DataBean {
        private String clientid;
        private String token;

        public DataBean() {
        }

        public DataBean(String str, String str2) {
            this.token = str;
            this.clientid = str2;
        }

        public String getToken() {
            return this.token;
        }

        public void setToken(String str) {
            this.token = str;
        }

        public String getClientid() {
            return this.clientid;
        }

        public void setClientid(String str) {
            this.clientid = str;
        }
    }
}
