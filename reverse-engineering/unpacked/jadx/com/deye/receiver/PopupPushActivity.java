package com.deye.receiver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.alibaba.sdk.android.push.AndroidPopupActivity;
import com.alibaba.sdk.android.push.popup.PopupNotifyClickListener;
import com.deye.activity.message.MsgDetailActivity;
import com.stub.StubApp;
import java.util.Map;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class PopupPushActivity extends AndroidPopupActivity {
    static final String TAG = StubApp.getString2(14223);

    static {
        StubApp.interface11(15150);
    }

    protected native void onCreate(Bundle bundle);

    protected native void onSysNoticeOpened(String str, String str2, Map<String, String> map);

    /* renamed from: com.deye.receiver.PopupPushActivity$1, reason: invalid class name */
    class AnonymousClass1 implements PopupNotifyClickListener {
        final /* synthetic */ Context val$context;

        AnonymousClass1(Context context) {
            this.val$context = context;
        }

        public void onSysNoticeOpened(String str, String str2, Map<String, String> map) {
            Log.d(StubApp.getString2(14223), StubApp.getString2(14220) + str + StubApp.getString2(14221) + str2 + StubApp.getString2(14222) + map);
            String string2 = StubApp.getString2(13228);
            if (map.containsKey(string2) && Integer.parseInt(map.get(string2)) == 9) {
                Intent intent = new Intent(this.val$context, (Class<?>) MsgDetailActivity.class);
                String string22 = StubApp.getString2(13229);
                intent.putExtra(string22, map.get(string22));
                PopupPushActivity.this.startActivity(intent);
            }
        }
    }
}
