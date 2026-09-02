package com.deye.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.stub.StubApp;
import com.vondear.rxtool.RxDataTool;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ProductListBean {
    private Data data;
    private Meta meta;

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

    public String toString() {
        return StubApp.getString2(14010) + this.meta + StubApp.getString2(14011) + this.data + '}';
    }

    public static class Meta {
        private int code;
        private String message;

        public void setCode(int i) {
            this.code = i;
        }

        public int getCode() {
            return this.code;
        }

        public void setMessage(String str) {
            this.message = str;
        }

        public String getMessage() {
            return this.message;
        }

        public String toString() {
            return StubApp.getString2(13993) + this.code + StubApp.getString2(13994) + this.message + StubApp.getString2(7034);
        }
    }

    public static class Data {
        private int owner;
        private List<Result> result;

        public void setOwner(int i) {
            this.owner = i;
        }

        public int getOwner() {
            return this.owner;
        }

        public void setResult(List<Result> list) {
            this.result = list;
        }

        public List<Result> getResult() {
            return this.result;
        }

        public String toString() {
            return StubApp.getString2(13991) + this.owner + StubApp.getString2(13992) + this.result + '}';
        }
    }

    public static class Pdata implements Parcelable {
        public static final Parcelable.Creator<Pdata> CREATOR = new Parcelable.Creator<Pdata>() { // from class: com.deye.entity.ProductListBean.Pdata.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Pdata createFromParcel(Parcel parcel) {
                return new Pdata(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Pdata[] newArray(int i) {
                return new Pdata[i];
            }
        };
        private String brand;
        private int configType;
        private String model;
        private String picture;
        public String picture_v3;
        private String pname;
        private String pnameJianPinYinLower;
        private String pnameQuanPinYinLower;
        private String productid;
        private String status;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Pdata() {
        }

        protected Pdata(Parcel parcel) {
            this.productid = parcel.readString();
            this.pname = parcel.readString();
            this.brand = parcel.readString();
            this.model = parcel.readString();
            this.picture = parcel.readString();
            this.status = parcel.readString();
            this.picture_v3 = parcel.readString();
            this.configType = parcel.readInt();
            this.pnameQuanPinYinLower = parcel.readString();
            this.pnameJianPinYinLower = parcel.readString();
        }

        public int getConfigType() {
            return this.configType;
        }

        public void setConfigType(int i) {
            this.configType = i;
        }

        public void setProductid(String str) {
            this.productid = str;
        }

        public String getProductid() {
            return this.productid;
        }

        public void setPname(String str) {
            this.pname = str;
        }

        public String getPname() {
            return this.pname;
        }

        public void setBrand(String str) {
            this.brand = str;
        }

        public String getBrand() {
            return this.brand;
        }

        public void setModel(String str) {
            this.model = str;
        }

        public String getModel() {
            return this.model;
        }

        public void setPicture(String str) {
            this.picture = str;
        }

        public String getPicture() {
            return this.picture;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String str) {
            this.status = str;
        }

        public String getPnameQuanPinYinLower() {
            String strCn2PYLower = RxDataTool.cn2PYLower(this.pname);
            this.pnameQuanPinYinLower = strCn2PYLower;
            return strCn2PYLower;
        }

        public String getPnameJianPinYinLower() {
            String jianPinLower = RxDataTool.toJianPinLower(this.pname);
            this.pnameJianPinYinLower = jianPinLower;
            return jianPinLower;
        }

        public String toString() {
            return StubApp.getString2(13995) + this.productid + StubApp.getString2(13996) + this.pname + StubApp.getString2(13997) + this.brand + StubApp.getString2(13998) + this.model + StubApp.getString2(13999) + this.picture + StubApp.getString2(14000) + this.status + StubApp.getString2(14001) + this.configType + StubApp.getString2(14002) + this.pnameQuanPinYinLower + StubApp.getString2(14003) + this.pnameJianPinYinLower + StubApp.getString2(7034);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.productid);
            parcel.writeString(this.pname);
            parcel.writeString(this.brand);
            parcel.writeString(this.model);
            parcel.writeString(this.picture);
            parcel.writeString(this.picture_v3);
            parcel.writeString(this.status);
            parcel.writeInt(this.configType);
            parcel.writeString(this.pnameQuanPinYinLower);
            parcel.writeString(this.pnameJianPinYinLower);
        }
    }

    public static class Result {
        private List<Pdata> pdata;
        private String ptype;
        private String ptypename;

        public void setPtype(String str) {
            this.ptype = str;
        }

        public String getPtype() {
            return this.ptype;
        }

        public List<Pdata> getPdata() {
            return this.pdata;
        }

        public void setPdata(List<Pdata> list) {
            this.pdata = list;
        }

        public String getPtypename() {
            return this.ptypename;
        }

        public void setPtypename(String str) {
            this.ptypename = str;
        }

        private String cloudTypeToLocalType(String str) {
            if (TextUtils.equals(str, StubApp.getString2(14004))) {
                return StubApp.getString2(14005);
            }
            if (TextUtils.equals(str, StubApp.getString2(14006))) {
                return StubApp.getString2(14007);
            }
            if (!TextUtils.equals(str, StubApp.getString2(14008))) {
                return StubApp.getString2(12998);
            }
            return StubApp.getString2(14009);
        }
    }
}
