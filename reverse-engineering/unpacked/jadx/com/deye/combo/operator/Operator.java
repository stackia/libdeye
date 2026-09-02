package com.deye.combo.operator;

import android.os.SystemClock;
import com.deye.combo.utils.DataUtil;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class Operator {

    public interface WriteDataCallback {
        boolean writeData(byte[] bArr);
    }

    public static void sendStartFrame(final byte[] bArr, final WriteDataCallback writeDataCallback) {
        new Thread(new Runnable() { // from class: com.deye.combo.operator.Operator.1
            @Override // java.lang.Runnable
            public void run() {
                SystemClock.sleep(800L);
                writeDataCallback.writeData(Operator.startFrame(bArr));
            }
        }).start();
    }

    public static void sendOtherFrame(final byte[] bArr, final WriteDataCallback writeDataCallback) {
        new Thread(new Runnable() { // from class: com.deye.combo.operator.Operator.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator it2 = Operator.dataFrame(bArr).iterator();
                while (it2.hasNext()) {
                    byte[] bArr2 = (byte[]) it2.next();
                    SystemClock.sleep(150L);
                    writeDataCallback.writeData(bArr2);
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] startFrame(byte[] bArr) {
        int length = (bArr.length / 19) + 1;
        int length2 = bArr.length;
        int iCrc16 = DataUtil.crc16(bArr, bArr.length);
        return new byte[]{1, (byte) (length & 255), (byte) (length2 & 255), (byte) ((length2 >> 8) & 255), (byte) (iCrc16 & 255), (byte) ((iCrc16 >> 8) & 255)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static LinkedList dataFrame(byte[] bArr) {
        byte[] bArr2;
        LinkedList linkedList = new LinkedList();
        int length = bArr.length / 19;
        int i = length + 1;
        int length2 = bArr.length % 19;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 != length) {
                bArr2 = new byte[20];
                bArr2[0] = 3;
                int i3 = i2 * 19;
                for (int i4 = i3; i4 < i3 + 19; i4++) {
                    bArr2[(i4 % 19) + 1] = bArr[i4];
                }
            } else {
                bArr2 = new byte[length2 + 1];
                bArr2[0] = 4;
                int i5 = i2 * 19;
                for (int i6 = i5; i6 < i5 + length2; i6++) {
                    bArr2[(i6 % 19) + 1] = bArr[i6];
                }
            }
            linkedList.add(bArr2);
        }
        return linkedList;
    }
}
