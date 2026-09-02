package com.deye.entity;

import io.fogcloud.sdk.fog.bean.BaseBean;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class TmallInfoBean extends BaseBean {
    private DataBean data;

    public TmallInfoBean() {
    }

    public TmallInfoBean(BaseBean.MetaBean metaBean, DataBean dataBean) {
        super(metaBean);
        this.data = dataBean;
    }

    public TmallInfoBean(DataBean dataBean) {
        this.data = dataBean;
    }

    public TmallInfoBean(BaseBean.MetaBean metaBean) {
        super(metaBean);
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public static class DataBean {
        private String tmallflag;
        private String user_id;

        public DataBean() {
        }

        public DataBean(String str, String str2) {
            this.tmallflag = str;
            this.user_id = str2;
        }

        public String getTmallflag() {
            return this.tmallflag;
        }

        public void setTmallflag(String str) {
            this.tmallflag = str;
        }

        public String getUser_id() {
            return this.user_id;
        }

        public void setUser_id(String str) {
            this.user_id = str;
        }
    }
}
