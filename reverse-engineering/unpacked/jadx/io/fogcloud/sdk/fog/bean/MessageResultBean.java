package io.fogcloud.sdk.fog.bean;

import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;

/* compiled from: MessageResultBean.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lio/fogcloud/sdk/fog/bean/MessageResultBean;", "Ljava/io/Serializable;", "()V", "list", "", "Lio/fogcloud/sdk/fog/bean/MessageBean;", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "next", "", "getNext", "()Ljava/lang/String;", "setNext", "(Ljava/lang/String;)V", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class MessageResultBean implements Serializable {
    private List<MessageBean> list;
    private String next;

    public final List<MessageBean> getList() {
        return this.list;
    }

    public final void setList(List<MessageBean> list) {
        this.list = list;
    }

    public final String getNext() {
        return this.next;
    }

    public final void setNext(String str) {
        this.next = str;
    }
}
