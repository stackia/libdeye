package io.fogcloud.sdk.fog.bean;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChannelsBean.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0012\u001a\u00020\u0007H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0007H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0017"}, d2 = {"Lio/fogcloud/sdk/fog/bean/ChannelsBean;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "()V", "channel_id", "", "getChannel_id", "()I", "setChannel_id", "(I)V", "channel_name", "", "getChannel_name", "()Ljava/lang/String;", "setChannel_name", "(Ljava/lang/String;)V", "describeContents", "writeToParcel", "", "flags", "CREATOR", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class ChannelsBean implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int channel_id;
    private String channel_name;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ChannelsBean() {
    }

    public final int getChannel_id() {
        return this.channel_id;
    }

    public final void setChannel_id(int i) {
        this.channel_id = i;
    }

    public final String getChannel_name() {
        return this.channel_name;
    }

    public final void setChannel_name(String str) {
        this.channel_name = str;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ChannelsBean(Parcel parcel) {
        this();
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.channel_id = parcel.readInt();
        this.channel_name = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(this.channel_id);
        parcel.writeString(this.channel_name);
    }

    /* compiled from: ChannelsBean.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lio/fogcloud/sdk/fog/bean/ChannelsBean$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lio/fogcloud/sdk/fog/bean/ChannelsBean;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lio/fogcloud/sdk/fog/bean/ChannelsBean;", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: io.fogcloud.sdk.fog.bean.ChannelsBean$CREATOR, reason: from kotlin metadata */
    public static final class Companion implements Parcelable.Creator<ChannelsBean> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChannelsBean createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ChannelsBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChannelsBean[] newArray(int size) {
            return new ChannelsBean[size];
        }
    }
}
