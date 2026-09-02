package io.fogcloud.sdk.fog.bean;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BannerItemBean.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0018\u001a\u00020\u0010H\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0010H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u000b¨\u0006\u001d"}, d2 = {"Lio/fogcloud/sdk/fog/bean/BannerItemBean;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "()V", "content_url", "", "getContent_url", "()Ljava/lang/String;", "setContent_url", "(Ljava/lang/String;)V", "cover_image", "getCover_image", "setCover_image", "id", "", "getId", "()I", "setId", "(I)V", "title", "getTitle", "setTitle", "describeContents", "writeToParcel", "", "flags", "CREATOR", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class BannerItemBean implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String content_url;
    private String cover_image;
    private int id;
    private String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BannerItemBean() {
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int i) {
        this.id = i;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final String getCover_image() {
        return this.cover_image;
    }

    public final void setCover_image(String str) {
        this.cover_image = str;
    }

    public final String getContent_url() {
        return this.content_url;
    }

    public final void setContent_url(String str) {
        this.content_url = str;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BannerItemBean(Parcel parcel) {
        this();
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.id = parcel.readInt();
        this.title = parcel.readString();
        this.cover_image = parcel.readString();
        this.content_url = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.cover_image);
        parcel.writeString(this.content_url);
    }

    /* compiled from: BannerItemBean.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lio/fogcloud/sdk/fog/bean/BannerItemBean$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lio/fogcloud/sdk/fog/bean/FindItemBean;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lio/fogcloud/sdk/fog/bean/FindItemBean;", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: io.fogcloud.sdk.fog.bean.BannerItemBean$CREATOR, reason: from kotlin metadata */
    public static final class Companion implements Parcelable.Creator<FindItemBean> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FindItemBean createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new FindItemBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FindItemBean[] newArray(int size) {
            return new FindItemBean[size];
        }
    }
}
