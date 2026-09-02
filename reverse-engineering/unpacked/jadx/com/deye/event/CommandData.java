package com.deye.event;

import com.stub.StubApp;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CommandData.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0004H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\bR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Lcom/deye/event/CommandData;", "", "()V", "deviceType", "", "getDeviceType", "()Ljava/lang/String;", "setDeviceType", "(Ljava/lang/String;)V", "isByte", "", "()Z", "setByte", "(Z)V", "mCommand", "getMCommand", "setMCommand", "mCommandByte", "", "getMCommandByte", "()[B", "setMCommandByte", "([B)V", "toString", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class CommandData {
    private String deviceType = StubApp.getString2(12998);
    private boolean isByte;
    private String mCommand;
    private byte[] mCommandByte;

    /* renamed from: isByte, reason: from getter */
    public final boolean getIsByte() {
        return this.isByte;
    }

    public final void setByte(boolean z) {
        this.isByte = z;
    }

    public final String getMCommand() {
        return this.mCommand;
    }

    public final void setMCommand(String str) {
        this.mCommand = str;
    }

    public final byte[] getMCommandByte() {
        return this.mCommandByte;
    }

    public final void setMCommandByte(byte[] bArr) {
        this.mCommandByte = bArr;
    }

    public final String getDeviceType() {
        return this.deviceType;
    }

    public final void setDeviceType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceType = str;
    }

    public String toString() {
        String string;
        boolean z = this.isByte;
        String str = this.mCommand;
        byte[] bArr = this.mCommandByte;
        if (bArr != null) {
            string = Arrays.toString(bArr);
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        } else {
            string = null;
        }
        return StubApp.getString2(14029) + z + StubApp.getString2(14030) + str + StubApp.getString2(14031) + string + StubApp.getString2(14032) + this.deviceType + StubApp.getString2(14033);
    }
}
