package io.fogcloud.sdk.fog.api.mqtt.sdk.helper;

import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.text.SimpleDateFormat;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class MqttTimeStampHelper {
    public static SimpleDateFormat simpleDataFormat = new SimpleDateFormat(StubApp.getString2(13238));

    public static final byte[] time2byteArray(long j) {
        byte[] bArr = new byte[8];
        LogUtil.d(StubApp.getString2(13874), StubApp.getString2(45013) + simpleDataFormat.format(Long.valueOf(j)));
        for (int i = 0; i < 7; i++) {
            bArr[7 - i] = (byte) (255 & j);
            j >>= 8;
        }
        bArr[0] = 7;
        return bArr;
    }
}
