package io.fogcloud.sdk.fog.api.http;

import kotlin.Metadata;

/* compiled from: BaseResult.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0003R\u001e\u0010\u0004\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lio/fogcloud/sdk/fog/api/http/BaseResult;", "T", "", "()V", "data", "getData", "()Ljava/lang/Object;", "setData", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "meta", "Lio/fogcloud/sdk/fog/api/http/BaseResult$MetaBean;", "getMeta", "()Lio/fogcloud/sdk/fog/api/http/BaseResult$MetaBean;", "setMeta", "(Lio/fogcloud/sdk/fog/api/http/BaseResult$MetaBean;)V", "MetaBean", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class BaseResult<T> {
    private T data;
    private MetaBean meta;

    public final T getData() {
        return this.data;
    }

    public final void setData(T t) {
        this.data = t;
    }

    public final MetaBean getMeta() {
        return this.meta;
    }

    public final void setMeta(MetaBean metaBean) {
        this.meta = metaBean;
    }

    /* compiled from: BaseResult.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0019\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/fogcloud/sdk/fog/api/http/BaseResult$MetaBean;", "", "()V", "message", "code", "", "(Ljava/lang/Object;I)V", "getCode", "()I", "setCode", "(I)V", "getMessage", "()Ljava/lang/Object;", "setMessage", "(Ljava/lang/Object;)V", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class MetaBean {
        private int code;
        private Object message;

        public final Object getMessage() {
            return this.message;
        }

        public final void setMessage(Object obj) {
            this.message = obj;
        }

        public final int getCode() {
            return this.code;
        }

        public final void setCode(int i) {
            this.code = i;
        }

        public MetaBean() {
        }

        public MetaBean(Object obj, int i) {
            this.message = obj;
            this.code = i;
        }
    }
}
