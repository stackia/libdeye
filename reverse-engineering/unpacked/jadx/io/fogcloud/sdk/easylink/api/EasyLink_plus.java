package io.fogcloud.sdk.easylink.api;

import android.content.Context;
import com.stub.StubApp;
import io.fogcloud.sdk.easylink.helper.Helper;
import io.fogcloud.sdk.easylink.plus.EasyLink_v2;
import io.fogcloud.sdk.easylink.plus.EasyLink_v3;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class EasyLink_plus {
    private static EasyLink_v2 e2;
    private static EasyLink_v3 e3;
    private static EasyLink_plus me;
    boolean sending = true;
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    private EasyLink_plus(Context context) {
        try {
            e2 = EasyLink_v2.getInstence();
            e3 = EasyLink_v3.getInstence();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EasyLink_plus getInstence(Context context) {
        if (me == null) {
            me = new EasyLink_plus(context);
        }
        return me;
    }

    public void setSmallMtu(boolean z) {
        e3.SetSmallMTU(z);
    }

    public void transmitSettings(String str, String str2, int i, final int i2, String str3, final String str4) throws UnsupportedEncodingException {
        byte[] bArr;
        String string2 = StubApp.getString2(567);
        try {
            final byte[] bytes = str.getBytes(string2);
            final byte[] bytes2 = str2.getBytes(string2);
            if (i != 0) {
                bArr = new byte[str3.getBytes().length + 5];
                String str5 = String.format(StubApp.getString2("34483"), Integer.valueOf(i));
                if (!"".equals(str3) || str3 != null) {
                    System.arraycopy(str3.getBytes(), 0, bArr, 0, str3.getBytes().length);
                    bArr[str3.getBytes().length] = 35;
                    System.arraycopy(Helper.hexStringToBytes(str5), 0, bArr, str3.getBytes().length + 1, 4);
                } else {
                    bArr[0] = 35;
                    System.arraycopy(Helper.hexStringToBytes(str5), 0, bArr, 1, 4);
                }
            } else {
                bArr = null;
            }
            final byte[] bArr2 = bArr;
            ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
            this.singleThreadExecutor = executorServiceNewSingleThreadExecutor;
            this.sending = true;
            executorServiceNewSingleThreadExecutor.execute(new Runnable() { // from class: io.fogcloud.sdk.easylink.api.EasyLink_plus.1
                @Override // java.lang.Runnable
                public void run() throws InterruptedException {
                    while (EasyLink_plus.this.sending) {
                        try {
                            EasyLink_plus.e2.transmitSettings(bytes, bytes2, bArr2, i2);
                            EasyLink_plus.e3.transmitSettings(bytes, bytes2, bArr2, str4, i2);
                            try {
                                Thread.sleep(10000L);
                                EasyLink_plus.e2.stopTransmitting();
                                EasyLink_plus.e3.stopTransmitting();
                                Thread.sleep(3000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void stopTransmitting() {
        this.sending = false;
        this.singleThreadExecutor.shutdown();
        e2.stopTransmitting();
        e3.stopTransmitting();
    }
}
