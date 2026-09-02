package io.fogcloud.sdk.easylink.plus;

import com.autonavi.aps.amapapi.config.Const;
import com.stub.StubApp;
import io.fogcloud.sdk.easylink.helper.ComHelper;
import io.fogcloud.sdk.easylink.helper.SinRC4;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class EasyLink_v3 {
    private static int START_FLAG1 = 1450;
    private static int START_FLAG2 = 1451;
    private static int START_FLAG3 = 1452;
    private static int UDP_START_PORT = 50000;
    private static EasyLink_v3 e3;
    private static int len;
    private static boolean stopSending;
    private boolean small_mtu;
    private static byte[] send_data = new byte[128];
    private static byte[] buffer = new byte[Const.LOG_LENGTH];
    private byte[] key = new byte[65];
    private byte[] ssid = new byte[65];
    private byte[] user_info = new byte[65];
    private boolean issendip = false;
    private InetAddress address = null;
    private DatagramPacket send_packet = null;
    private int port = 0;

    private EasyLink_v3() {
        stopSending = false;
    }

    public static EasyLink_v3 getInstence() {
        if (e3 == null) {
            e3 = new EasyLink_v3();
        }
        return e3;
    }

    public void transmitSettings(byte[] bArr, byte[] bArr2, byte[] bArr3, String str, final int i) {
        try {
            this.address = InetAddress.getByName(StubApp.getString2("44632"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ComHelper.checkPara(str)) {
            this.ssid = SinRC4.encry_RC4_byte(bArr, str);
            this.key = SinRC4.encry_RC4_byte(bArr2, str);
            if (bArr3 != null) {
                this.issendip = true;
                this.user_info = SinRC4.encry_RC4_byte(bArr3, str);
            }
        } else {
            this.ssid = bArr;
            this.key = bArr2;
            if (bArr3 != null) {
                this.issendip = true;
                this.user_info = bArr3;
            }
        }
        byte[] bArr4 = send_data;
        byte[] bArr5 = this.ssid;
        int i2 = 3;
        int length = bArr5.length + 3;
        byte[] bArr6 = this.key;
        bArr4[0] = (byte) (length + bArr6.length + (this.issendip ? this.user_info.length : 0) + 2);
        bArr4[1] = (byte) bArr5.length;
        bArr4[2] = (byte) bArr6.length;
        int i3 = 0;
        while (true) {
            byte[] bArr7 = this.ssid;
            if (i3 >= bArr7.length) {
                break;
            }
            send_data[i2] = bArr7[i3];
            i3++;
            i2++;
        }
        int i4 = 0;
        while (true) {
            byte[] bArr8 = this.key;
            if (i4 >= bArr8.length) {
                break;
            }
            send_data[i2] = bArr8[i4];
            i4++;
            i2++;
        }
        if (this.issendip) {
            int i5 = 0;
            while (true) {
                byte[] bArr9 = this.user_info;
                if (i5 >= bArr9.length) {
                    break;
                }
                send_data[i2] = bArr9[i5];
                i5++;
                i2++;
            }
        }
        short s = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            s = (short) (s + (send_data[i6] & 255));
        }
        byte[] bArr10 = send_data;
        bArr10[i2] = (byte) ((65535 & s) >> 8);
        bArr10[i2 + 1] = (byte) (s & 255);
        new Thread(new Runnable() { // from class: io.fogcloud.sdk.easylink.plus.EasyLink_v3.1
            @Override // java.lang.Runnable
            public void run() {
                boolean unused = EasyLink_v3.stopSending = false;
                EasyLink_v3.this.send(i);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send(int i) {
        while (!stopSending) {
            try {
                this.port = UDP_START_PORT;
                UDP_SEND(START_FLAG1, i);
                UDP_SEND(START_FLAG2, i);
                UDP_SEND(START_FLAG3, i);
                int i2 = 0;
                int i3 = 0;
                int i4 = 1;
                while (true) {
                    byte[] bArr = send_data;
                    if (i2 < bArr[0]) {
                        int i5 = (i4 * 256) + (bArr[i2] & 255);
                        len = i5;
                        UDP_SEND(i5, i);
                        if (i2 % 4 == 3) {
                            int i6 = i3 + 1;
                            int i7 = i3 + 1281;
                            len = i7;
                            UDP_SEND(i7, i);
                            i3 = i6;
                        }
                        i4++;
                        if (i4 == 5) {
                            i4 = 1;
                        }
                        i2++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void SetSmallMTU(boolean z) {
        this.small_mtu = z;
    }

    private void UDP_SEND(int i, int i2) throws InterruptedException, IOException {
        try {
            Thread.sleep(i2);
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.setBroadcast(true);
            if (this.small_mtu) {
                if (i > 1280) {
                    i -= 1280;
                }
                if (i < 64) {
                    i += 176;
                }
            }
            DatagramPacket datagramPacket = new DatagramPacket(buffer, i, this.address, this.port);
            this.send_packet = datagramPacket;
            datagramSocket.send(datagramPacket);
            datagramSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopTransmitting() {
        stopSending = true;
    }
}
