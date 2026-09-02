package io.fogcloud.sdk.fog.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.efs.sdk.base.protocol.ILogProtocol;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FindItemBean.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b+\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 G2\u00020\u0001:\u0001GB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010C\u001a\u00020\u0019H\u0016J\u0018\u0010D\u001a\u00020E2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010F\u001a\u00020\u0019H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u000bR\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\t\"\u0004\b \u0010\u000bR\u001a\u0010!\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010\u001dR\u001a\u0010$\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001b\"\u0004\b&\u0010\u001dR\u001a\u0010'\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001b\"\u0004\b)\u0010\u001dR\u001a\u0010*\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001b\"\u0004\b,\u0010\u001dR\u001a\u0010-\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0012\"\u0004\b.\u0010\u0014R\u001a\u0010/\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0012\"\u0004\b0\u0010\u0014R\u001a\u00101\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u001b\"\u0004\b3\u0010\u001dR\u001a\u00104\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0012\"\u0004\b6\u0010\u0014R\u001a\u00107\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u001b\"\u0004\b9\u0010\u001dR\u001c\u0010:\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\t\"\u0004\b<\u0010\u000bR\u001a\u0010=\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u001b\"\u0004\b?\u0010\u001dR\u001c\u0010@\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\t\"\u0004\bB\u0010\u000b¨\u0006H"}, d2 = {"Lio/fogcloud/sdk/fog/bean/FindItemBean;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "()V", "ad_image", "", "getAd_image", "()Ljava/lang/String;", "setAd_image", "(Ljava/lang/String;)V", "ad_name", "getAd_name", "setAd_name", "ad_switch", "", "getAd_switch", "()Z", "setAd_switch", "(Z)V", "ad_url", "getAd_url", "setAd_url", "article_id", "", "getArticle_id", "()I", "setArticle_id", "(I)V", "cover", "getCover", "setCover", "cover_height", "getCover_height", "setCover_height", "cover_width", "getCover_width", "setCover_width", "favorite_count", "getFavorite_count", "setFavorite_count", "id", "getId", "setId", "is_favorited", "set_favorited", "is_liked", "set_liked", "like_count", "getLike_count", "setLike_count", "recommend_flag", "getRecommend_flag", "setRecommend_flag", "share_count", "getShare_count", "setShare_count", "title", "getTitle", "setTitle", ILogProtocol.LOG_KEY_TYPE, "getType", "setType", "url", "getUrl", "setUrl", "describeContents", "writeToParcel", "", "flags", "CREATOR", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class FindItemBean implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String ad_image;
    private String ad_name;
    private boolean ad_switch;
    private String ad_url;
    private int article_id;
    private String cover;
    private int cover_height;
    private int cover_width;
    private int favorite_count;
    private int id;
    private boolean is_favorited;
    private boolean is_liked;
    private int like_count;
    private boolean recommend_flag;
    private int share_count;
    private String title;
    private int type;
    private String url;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FindItemBean() {
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int i) {
        this.id = i;
    }

    public final int getArticle_id() {
        return this.article_id;
    }

    public final void setArticle_id(int i) {
        this.article_id = i;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final String getCover() {
        return this.cover;
    }

    public final void setCover(String str) {
        this.cover = str;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        this.url = str;
    }

    public final int getCover_width() {
        return this.cover_width;
    }

    public final void setCover_width(int i) {
        this.cover_width = i;
    }

    public final int getCover_height() {
        return this.cover_height;
    }

    public final void setCover_height(int i) {
        this.cover_height = i;
    }

    public final String getAd_name() {
        return this.ad_name;
    }

    public final void setAd_name(String str) {
        this.ad_name = str;
    }

    public final String getAd_image() {
        return this.ad_image;
    }

    public final void setAd_image(String str) {
        this.ad_image = str;
    }

    public final String getAd_url() {
        return this.ad_url;
    }

    public final void setAd_url(String str) {
        this.ad_url = str;
    }

    public final boolean getAd_switch() {
        return this.ad_switch;
    }

    public final void setAd_switch(boolean z) {
        this.ad_switch = z;
    }

    public final boolean getRecommend_flag() {
        return this.recommend_flag;
    }

    public final void setRecommend_flag(boolean z) {
        this.recommend_flag = z;
    }

    public final int getLike_count() {
        return this.like_count;
    }

    public final void setLike_count(int i) {
        this.like_count = i;
    }

    public final int getFavorite_count() {
        return this.favorite_count;
    }

    public final void setFavorite_count(int i) {
        this.favorite_count = i;
    }

    public final int getShare_count() {
        return this.share_count;
    }

    public final void setShare_count(int i) {
        this.share_count = i;
    }

    /* renamed from: is_liked, reason: from getter */
    public final boolean getIs_liked() {
        return this.is_liked;
    }

    public final void set_liked(boolean z) {
        this.is_liked = z;
    }

    /* renamed from: is_favorited, reason: from getter */
    public final boolean getIs_favorited() {
        return this.is_favorited;
    }

    public final void set_favorited(boolean z) {
        this.is_favorited = z;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FindItemBean(Parcel parcel) {
        this();
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.id = parcel.readInt();
        this.article_id = parcel.readInt();
        this.type = parcel.readInt();
        this.title = parcel.readString();
        this.cover = parcel.readString();
        this.url = parcel.readString();
        this.cover_width = parcel.readInt();
        this.cover_height = parcel.readInt();
        this.ad_name = parcel.readString();
        this.ad_image = parcel.readString();
        this.ad_url = parcel.readString();
        this.ad_switch = parcel.readByte() != 0;
        this.recommend_flag = parcel.readByte() != 0;
        this.like_count = parcel.readInt();
        this.favorite_count = parcel.readInt();
        this.share_count = parcel.readInt();
        this.is_liked = parcel.readByte() != 0;
        this.is_favorited = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(this.id);
        parcel.writeInt(this.article_id);
        parcel.writeInt(this.type);
        parcel.writeString(this.title);
        parcel.writeString(this.cover);
        parcel.writeString(this.url);
        parcel.writeInt(this.cover_width);
        parcel.writeInt(this.cover_height);
        parcel.writeString(this.ad_name);
        parcel.writeString(this.ad_image);
        parcel.writeString(this.ad_url);
        parcel.writeByte(this.ad_switch ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.recommend_flag ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.like_count);
        parcel.writeInt(this.favorite_count);
        parcel.writeInt(this.share_count);
        parcel.writeByte(this.is_liked ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.is_favorited ? (byte) 1 : (byte) 0);
    }

    /* compiled from: FindItemBean.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lio/fogcloud/sdk/fog/bean/FindItemBean$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lio/fogcloud/sdk/fog/bean/FindItemBean;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lio/fogcloud/sdk/fog/bean/FindItemBean;", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: io.fogcloud.sdk.fog.bean.FindItemBean$CREATOR, reason: from kotlin metadata */
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
