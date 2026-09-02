package com.deye.udp;

import android.util.Log;
import com.deye.entity.DevinfoBean;
import com.deye.entity.UdpSearchDeviceDto;
import com.deye.utils.BaseUtils;
import com.google.gson.Gson;
import com.stub.StubApp;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class UdpManager {
    private static final int PORT = 9009;
    private OnSearchCallBack mCallBack;
    private Thread mThread;
    private DatagramSocket mUdpSocket;
    private volatile boolean isReceive = false;
    private Gson mGson = new Gson();
    private Runnable mRunnable = new Runnable() { // from class: com.deye.udp.UdpManager.1
        @Override // java.lang.Runnable
        public void run() throws IOException {
            UdpManager.this.initUdpSocket();
            UdpManager.this.receiveData();
        }
    };

    public interface OnSearchCallBack {
        void onFailure(String str);

        void onSuccess(DevinfoBean devinfoBean);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initUdpSocket() throws SocketException {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(PORT);
            this.mUdpSocket = datagramSocket;
            datagramSocket.setReuseAddress(true);
            this.mUdpSocket.setBroadcast(true);
        } catch (Exception e) {
            e.printStackTrace();
            OnSearchCallBack onSearchCallBack = this.mCallBack;
            if (onSearchCallBack != null) {
                onSearchCallBack.onFailure(StubApp.getString2(14269));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void receiveData() throws IOException {
        UdpSearchDeviceDto udpSearchDeviceDto;
        while (this.isReceive) {
            byte[] bArr = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(bArr, 1024);
            try {
                if (BaseUtils.isNotNull(this.mUdpSocket)) {
                    this.mUdpSocket.receive(datagramPacket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (datagramPacket.getLength() <= 0) {
                return;
            }
            try {
                udpSearchDeviceDto = (UdpSearchDeviceDto) this.mGson.fromJson(new String(bArr).trim(), UdpSearchDeviceDto.class);
            } catch (Exception e2) {
                e2.printStackTrace();
                OnSearchCallBack onSearchCallBack = this.mCallBack;
                if (onSearchCallBack != null) {
                    onSearchCallBack.onFailure(StubApp.getString2(14272));
                }
            }
            if (udpSearchDeviceDto == null) {
                return;
            }
            DevinfoBean devinfoBean = (DevinfoBean) this.mGson.fromJson(udpSearchDeviceDto.getData(), DevinfoBean.class);
            devinfoBean.setStop_easylink(udpSearchDeviceDto.isStop_easylink());
            OnSearchCallBack onSearchCallBack2 = this.mCallBack;
            if (onSearchCallBack2 != null) {
                onSearchCallBack2.onSuccess(devinfoBean);
            }
            Log.d(StubApp.getString2("14270"), StubApp.getString2("14271") + udpSearchDeviceDto.getData());
        }
    }

    public void startSearchDevices() {
        Log.d(StubApp.getString2(10666), StubApp.getString2(14273));
        this.isReceive = true;
        Thread thread = this.mThread;
        if (thread == null || !thread.isAlive()) {
            Thread thread2 = new Thread(this.mRunnable);
            this.mThread = thread2;
            thread2.start();
        }
    }

    public void stopSearchDevices() {
        if (this.isReceive) {
            this.isReceive = false;
            DatagramSocket datagramSocket = this.mUdpSocket;
            if (datagramSocket != null) {
                if (!datagramSocket.isClosed()) {
                    this.mUdpSocket.close();
                    this.mUdpSocket.disconnect();
                }
                this.mUdpSocket = null;
            }
            Thread thread = this.mThread;
            if (thread != null) {
                thread.interrupt();
                this.mThread = null;
            }
        }
    }

    public void setOnSearchCallBack(OnSearchCallBack onSearchCallBack) {
        this.mCallBack = onSearchCallBack;
    }
}
