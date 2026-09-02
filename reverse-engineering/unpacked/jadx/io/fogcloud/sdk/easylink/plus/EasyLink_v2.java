package io.fogcloud.sdk.easylink.plus;

import com.stub.StubApp;
import io.fogcloud.sdk.easylink.helper.Helper;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Random;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class EasyLink_v2 {
    private static EasyLink_v2 e2;
    private static String ip;
    private static boolean stopSending;
    private byte[] key = new byte[65];
    private byte[] ssid = new byte[65];
    private byte[] user_info = new byte[65];
    private static String head = StubApp.getString2(44629);
    private static String syncHString = StubApp.getString2(44630);

    private EasyLink_v2() {
        stopSending = false;
    }

    public static EasyLink_v2 getInstence() {
        if (e2 == null) {
            e2 = new EasyLink_v2();
        }
        return e2;
    }

    public void transmitSettings(byte[] bArr, byte[] bArr2, byte[] bArr3, final int i) {
        this.ssid = bArr;
        this.key = bArr2;
        this.user_info = bArr3;
        new Thread(new Runnable() { // from class: io.fogcloud.sdk.easylink.plus.EasyLink_v2.1
            @Override // java.lang.Runnable
            public void run() {
                boolean unused = EasyLink_v2.stopSending = false;
                EasyLink_v2.this.send(i);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send(int i) {
        while (!stopSending) {
            try {
                sendSync(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void sendSync(int i) throws InterruptedException, IOException {
        byte[] bArrByteMerger;
        byte[] bytes = syncHString.getBytes();
        int length = this.user_info.length;
        int i2 = 0;
        if (length == 0) {
            length++;
            this.user_info = new byte[]{0};
        }
        byte[] bArr = this.ssid;
        byte length2 = (byte) bArr.length;
        byte[] bArr2 = this.key;
        byte[] bArrByteMerger2 = Helper.byteMerger(new byte[]{length2, (byte) bArr2.length}, Helper.byteMerger(bArr, bArr2));
        for (int i3 = 0; i3 < 5; i3++) {
            sendData(new DatagramPacket(bytes, 20, new InetSocketAddress(InetAddress.getByName(head), getRandomNumber())), head);
            Thread.sleep(i);
        }
        String string2 = StubApp.getString2(1382);
        String string22 = StubApp.getString2(5781);
        String string23 = StubApp.getString2(44631);
        if (length == 0) {
            while (i2 < bArrByteMerger2.length) {
                int i4 = i2 + 1;
                if (i4 < bArrByteMerger2.length) {
                    ip = string23 + (bArrByteMerger2[i2] & 255) + string2 + (bArrByteMerger2[i4] & 255);
                } else {
                    ip = string23 + (bArrByteMerger2[i2] & 255) + string22;
                }
                int i5 = (i2 / 2) + 20;
                sendData(new DatagramPacket(new byte[i5], i5, new InetSocketAddress(InetAddress.getByName(ip), getRandomNumber())), ip);
                Thread.sleep(i);
                i2 += 2;
            }
            return;
        }
        if (bArrByteMerger2.length % 2 == 0) {
            if (this.user_info.length == 0) {
                bArrByteMerger = Helper.byteMerger(bArrByteMerger2, new byte[]{(byte) length, 0, 0});
            } else {
                bArrByteMerger = Helper.byteMerger(bArrByteMerger2, new byte[]{(byte) length, 0});
            }
        } else {
            bArrByteMerger = Helper.byteMerger(bArrByteMerger2, new byte[]{0, (byte) length, 0});
        }
        byte[] bArrByteMerger3 = Helper.byteMerger(bArrByteMerger, this.user_info);
        while (i2 < bArrByteMerger3.length) {
            int i6 = i2 + 1;
            if (i6 < bArrByteMerger3.length) {
                ip = string23 + (bArrByteMerger3[i2] & 255) + string2 + (bArrByteMerger3[i6] & 255);
            } else {
                ip = string23 + (bArrByteMerger3[i2] & 255) + string22;
            }
            int i7 = (i2 / 2) + 20;
            sendData(new DatagramPacket(new byte[i7], i7, new InetSocketAddress(InetAddress.getByName(ip), getRandomNumber())), ip);
            Thread.sleep(i);
            i2 += 2;
        }
    }

    protected static void sendData(DatagramPacket datagramPacket, String str) throws IOException {
        MulticastSocket multicastSocket = new MulticastSocket(54064);
        multicastSocket.setReuseAddress(true);
        multicastSocket.setNetworkInterface(getWlanEth());
        multicastSocket.send(datagramPacket);
        multicastSocket.close();
    }

    public static NetworkInterface getWlanEth() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
            networkInterfaces = null;
        }
        StringBuilder sb = new StringBuilder();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
            sb.append(networkInterfaceNextElement.getName() + StubApp.getString2(626));
            if (networkInterfaceNextElement.getName().equals(StubApp.getString2(44618))) {
                return networkInterfaceNextElement;
            }
        }
        return null;
    }

    public void stopTransmitting() {
        stopSending = true;
    }

    private static int getRandomNumber() {
        int iNextInt = new Random().nextInt(65536);
        if (iNextInt < 10000) {
            return 65523;
        }
        return iNextInt;
    }
}
