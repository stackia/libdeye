package io.fogcloud.sdk.fog.helper;

import android.content.Intent;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.BaseApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class SendOffLineBr {
    public static void sendOffLineBr(String str, String str2) {
        Intent intent = new Intent();
        intent.setAction(StubApp.getString2(13560));
        intent.putExtra(StubApp.getString2(14218), str);
        intent.putExtra(StubApp.getString2(14215), str2);
        BaseApp.getInstance().sendBroadcast(intent);
    }
}
