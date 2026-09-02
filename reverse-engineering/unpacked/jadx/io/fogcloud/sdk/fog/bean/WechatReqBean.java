package io.fogcloud.sdk.fog.bean;

import java.io.Serializable;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class WechatReqBean implements Serializable {
    public String access_token;
    public String appid;
    public Extend extend;
    public String open_id;
    public String pushtype;

    public static class Extend implements Serializable {
        public String cid;
    }
}
