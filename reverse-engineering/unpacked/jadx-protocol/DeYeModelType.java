package io.fogcloud.sdk.fog;

import android.text.TextUtils;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class DeYeModelType {
    public static final String DEYE_DEHUMIDIFIER = StubApp.getString2(12998);
    public static final String DEYE_LOOP_FAN = StubApp.getString2(12999);
    public static final String DEYE_QUILT_DRYER = StubApp.getString2(14008);

    public static String cloudTypeToLocalType(String str) {
        boolean zEquals = TextUtils.equals(str, StubApp.getString2(14004));
        String string2 = StubApp.getString2(12998);
        if (zEquals) {
            return string2;
        }
        if (TextUtils.equals(str, StubApp.getString2(14006))) {
            return StubApp.getString2(12999);
        }
        String string22 = StubApp.getString2(14008);
        return TextUtils.equals(str, string22) ? string22 : string2;
    }
}
