package com.deye.entity;

import io.fogcloud.sdk.fog.bean.BaseBean;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class UserInfoResult extends BaseBean {
    private UserInfoBean data;

    public UserInfoResult(BaseBean.MetaBean metaBean) {
        super(metaBean);
    }

    public UserInfoResult(BaseBean.MetaBean metaBean, UserInfoBean userInfoBean) {
        super(metaBean);
        this.data = userInfoBean;
    }

    public UserInfoBean getData() {
        return this.data;
    }

    public void setData(UserInfoBean userInfoBean) {
        this.data = userInfoBean;
    }
}
