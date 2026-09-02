package io.fogcloud.sdk.fog.bean;

import java.util.List;
import kotlin.Metadata;

/* compiled from: FindPageBean.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\b¨\u0006\u0015"}, d2 = {"Lio/fogcloud/sdk/fog/bean/FindPageBean;", "", "()V", "current_page", "", "getCurrent_page", "()Ljava/lang/Integer;", "setCurrent_page", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "data", "", "Lio/fogcloud/sdk/fog/bean/FindItemBean;", "getData", "()Ljava/util/List;", "page", "getPage", "setPage", "total", "getTotal", "setTotal", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class FindPageBean {
    private Integer current_page;
    private final List<FindItemBean> data;
    private Integer page;
    private Integer total;

    public final Integer getTotal() {
        return this.total;
    }

    public final void setTotal(Integer num) {
        this.total = num;
    }

    public final Integer getPage() {
        return this.page;
    }

    public final void setPage(Integer num) {
        this.page = num;
    }

    public final Integer getCurrent_page() {
        return this.current_page;
    }

    public final void setCurrent_page(Integer num) {
        this.current_page = num;
    }

    public final List<FindItemBean> getData() {
        return this.data;
    }
}
