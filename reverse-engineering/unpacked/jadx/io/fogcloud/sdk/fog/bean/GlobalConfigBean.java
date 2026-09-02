package io.fogcloud.sdk.fog.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class GlobalConfigBean implements Serializable, Parcelable {
    public static final Parcelable.Creator<GlobalConfigBean> CREATOR = new Parcelable.Creator<GlobalConfigBean>() { // from class: io.fogcloud.sdk.fog.bean.GlobalConfigBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GlobalConfigBean createFromParcel(Parcel parcel) {
            return new GlobalConfigBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GlobalConfigBean[] newArray(int i) {
            return new GlobalConfigBean[i];
        }
    };
    public boolean autoUploadBleLog;
    public boolean closeWifiScan;
    public int provision;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected GlobalConfigBean(Parcel parcel) {
        this.provision = parcel.readInt();
        this.autoUploadBleLog = parcel.readByte() != 0;
        this.closeWifiScan = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.provision);
        parcel.writeByte(this.autoUploadBleLog ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.closeWifiScan ? (byte) 1 : (byte) 0);
    }
}
