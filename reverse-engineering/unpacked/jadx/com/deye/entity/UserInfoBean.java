package com.deye.entity;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: UserInfoBean.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 O2\u00020\u0001:\u0001OB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010J\u001a\u00020KH\u0016J\u0018\u0010L\u001a\u00020M2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010N\u001a\u00020KH\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u000bR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\t\"\u0004\b\u001a\u0010\u000bR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\t\"\u0004\b#\u0010\u000bR\u001c\u0010$\u001a\u0004\u0018\u00010%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010,\"\u0004\b-\u0010.R\u001c\u0010/\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\t\"\u0004\b1\u0010\u000bR\u001c\u00102\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\t\"\u0004\b4\u0010\u000bR\u001c\u00105\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\t\"\u0004\b7\u0010\u000bR\u001c\u00108\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\t\"\u0004\b:\u0010\u000bR\u001c\u0010;\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\t\"\u0004\b=\u0010\u000bR\u001c\u0010>\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\t\"\u0004\b@\u0010\u000bR\u001c\u0010A\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\t\"\u0004\bC\u0010\u000bR\u001c\u0010D\u001a\u0004\u0018\u00010EX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010I¨\u0006P"}, d2 = {"Lcom/deye/entity/UserInfoBean;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "()V", "avatar", "", "getAvatar", "()Ljava/lang/String;", "setAvatar", "(Ljava/lang/String;)V", "birthday", "getBirthday", "setBirthday", "city", "getCity", "setCity", "email", "getEmail", "setEmail", "emailverified", "getEmailverified", "setEmailverified", "enduserid", "getEnduserid", "setEnduserid", "extend", "Lorg/json/JSONObject;", "getExtend", "()Lorg/json/JSONObject;", "setExtend", "(Lorg/json/JSONObject;)V", "gender", "getGender", "setGender", "google_info", "Lcom/deye/entity/GoogleInfoBean;", "getGoogle_info", "()Lcom/deye/entity/GoogleInfoBean;", "setGoogle_info", "(Lcom/deye/entity/GoogleInfoBean;)V", "isTmall_flag", "", "()Z", "setTmall_flag", "(Z)V", "last_login", "getLast_login", "setLast_login", "nickname", "getNickname", "setNickname", "note", "getNote", "setNote", "phone", "getPhone", "setPhone", "phonearea", "getPhonearea", "setPhonearea", "phoneverified", "getPhoneverified", "setPhoneverified", "realname", "getRealname", "setRealname", "wechat_info", "Lcom/deye/entity/WeChatInfoBean;", "getWechat_info", "()Lcom/deye/entity/WeChatInfoBean;", "setWechat_info", "(Lcom/deye/entity/WeChatInfoBean;)V", "describeContents", "", "writeToParcel", "", "flags", "CREATOR", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class UserInfoBean implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String avatar;
    private String birthday;
    private String city;
    private String email;
    private String emailverified;
    private String enduserid;
    private JSONObject extend;
    private String gender;
    private GoogleInfoBean google_info;
    private boolean isTmall_flag;
    private String last_login;
    private String nickname;
    private String note;
    private String phone;
    private String phonearea;
    private String phoneverified;
    private String realname;
    private WeChatInfoBean wechat_info;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UserInfoBean() {
    }

    public final String getEnduserid() {
        return this.enduserid;
    }

    public final void setEnduserid(String str) {
        this.enduserid = str;
    }

    public final String getLast_login() {
        return this.last_login;
    }

    public final void setLast_login(String str) {
        this.last_login = str;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final void setPhone(String str) {
        this.phone = str;
    }

    public final String getPhonearea() {
        return this.phonearea;
    }

    public final void setPhonearea(String str) {
        this.phonearea = str;
    }

    public final String getPhoneverified() {
        return this.phoneverified;
    }

    public final void setPhoneverified(String str) {
        this.phoneverified = str;
    }

    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(String str) {
        this.email = str;
    }

    public final String getEmailverified() {
        return this.emailverified;
    }

    public final void setEmailverified(String str) {
        this.emailverified = str;
    }

    public final String getNickname() {
        return this.nickname;
    }

    public final void setNickname(String str) {
        this.nickname = str;
    }

    public final String getRealname() {
        return this.realname;
    }

    public final void setRealname(String str) {
        this.realname = str;
    }

    public final String getGender() {
        return this.gender;
    }

    public final void setGender(String str) {
        this.gender = str;
    }

    public final String getCity() {
        return this.city;
    }

    public final void setCity(String str) {
        this.city = str;
    }

    public final String getAvatar() {
        return this.avatar;
    }

    public final void setAvatar(String str) {
        this.avatar = str;
    }

    public final String getNote() {
        return this.note;
    }

    public final void setNote(String str) {
        this.note = str;
    }

    public final JSONObject getExtend() {
        return this.extend;
    }

    public final void setExtend(JSONObject jSONObject) {
        this.extend = jSONObject;
    }

    /* renamed from: isTmall_flag, reason: from getter */
    public final boolean getIsTmall_flag() {
        return this.isTmall_flag;
    }

    public final void setTmall_flag(boolean z) {
        this.isTmall_flag = z;
    }

    public final String getBirthday() {
        return this.birthday;
    }

    public final void setBirthday(String str) {
        this.birthday = str;
    }

    public final WeChatInfoBean getWechat_info() {
        return this.wechat_info;
    }

    public final void setWechat_info(WeChatInfoBean weChatInfoBean) {
        this.wechat_info = weChatInfoBean;
    }

    public final GoogleInfoBean getGoogle_info() {
        return this.google_info;
    }

    public final void setGoogle_info(GoogleInfoBean googleInfoBean) {
        this.google_info = googleInfoBean;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UserInfoBean(Parcel parcel) {
        this();
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.enduserid = parcel.readString();
        this.last_login = parcel.readString();
        this.phone = parcel.readString();
        this.phonearea = parcel.readString();
        this.phoneverified = parcel.readString();
        this.email = parcel.readString();
        this.emailverified = parcel.readString();
        this.nickname = parcel.readString();
        this.realname = parcel.readString();
        this.gender = parcel.readString();
        this.city = parcel.readString();
        this.avatar = parcel.readString();
        this.note = parcel.readString();
        this.isTmall_flag = parcel.readByte() != 0;
        this.birthday = parcel.readString();
        this.wechat_info = (WeChatInfoBean) parcel.readParcelable(WeChatInfoBean.class.getClassLoader());
        this.google_info = (GoogleInfoBean) parcel.readParcelable(GoogleInfoBean.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(this.enduserid);
        parcel.writeString(this.last_login);
        parcel.writeString(this.phone);
        parcel.writeString(this.phonearea);
        parcel.writeString(this.phoneverified);
        parcel.writeString(this.email);
        parcel.writeString(this.emailverified);
        parcel.writeString(this.nickname);
        parcel.writeString(this.realname);
        parcel.writeString(this.gender);
        parcel.writeString(this.city);
        parcel.writeString(this.avatar);
        parcel.writeString(this.note);
        parcel.writeByte(this.isTmall_flag ? (byte) 1 : (byte) 0);
        parcel.writeString(this.birthday);
        parcel.writeParcelable(this.wechat_info, flags);
        parcel.writeParcelable(this.google_info, flags);
    }

    /* compiled from: UserInfoBean.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/deye/entity/UserInfoBean$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/deye/entity/UserInfoBean;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/deye/entity/UserInfoBean;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.entity.UserInfoBean$CREATOR, reason: from kotlin metadata */
    public static final class Companion implements Parcelable.Creator<UserInfoBean> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfoBean createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new UserInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserInfoBean[] newArray(int size) {
            return new UserInfoBean[size];
        }
    }
}
