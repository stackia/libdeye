package com.deye.combo.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import com.autonavi.aps.amapapi.config.Const;
import com.deye.combo.bean.WifiInfoBean;
import com.deye.combo.callback.FrameResponseCallback;
import com.deye.combo.callback.ScanResultCallback;
import com.deye.combo.log.BleLog;
import com.deye.combo.operator.Operator;
import com.deye.combo.utils.AESEncryptUtil;
import com.deye.combo.utils.BluetoothUtil;
import com.deye.combo.utils.DataUtil;
import com.google.gson.Gson;
import com.stub.StubApp;
import java.util.Iterator;
import java.util.UUID;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
class BluetoothConnection {
    public static final int GATT_ERROR = 133;
    private static final String TAG = StubApp.getString2(13881);
    private BluetoothGatt bluetoothGatt;
    private BluetoothGattService bluetoothGattService;
    private byte[] content;
    private String encrypt;
    private FrameResponseCallback frameResponseCallback;
    private BluetoothDevice mBluetoothDevice;
    private Context mContext;
    private WifiInfoBean mWifiInfo;
    private ScanResultCallback scanResultCallback;
    private boolean isRetried = false;
    private Operator.WriteDataCallback writeDataCallback = new Operator.WriteDataCallback() { // from class: com.deye.combo.bluetooth.BluetoothConnection.1
        @Override // com.deye.combo.operator.Operator.WriteDataCallback
        public boolean writeData(byte[] bArr) {
            BleLog.i(StubApp.getString2(13881), StubApp.getString2(13880) + DataUtil.bytes2Hex(bArr));
            return BluetoothConnection.this.writeData(bArr);
        }
    };
    private CountDownTimer endFrameResponseListener = new CountDownTimer(Const.L_SAME_TOAST, 1000) { // from class: com.deye.combo.bluetooth.BluetoothConnection.2
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            BleLog.w(StubApp.getString2(13883), StubApp.getString2(13882) + j);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            Operator.sendStartFrame(BluetoothConnection.this.content, BluetoothConnection.this.writeDataCallback);
        }
    };
    private BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() { // from class: com.deye.combo.bluetooth.BluetoothConnection.3
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(final BluetoothGatt bluetoothGatt, final int i, final int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            new Handler(BluetoothConnection.this.mContext.getMainLooper()).postDelayed(new Runnable() { // from class: com.deye.combo.bluetooth.BluetoothConnection.3.1
                @Override // java.lang.Runnable
                public void run() throws InterruptedException {
                    String str = StubApp.getString2(13884) + bluetoothGatt.getDevice().getName() + StubApp.getString2(1521) + i2 + StubApp.getString2(13885) + i;
                    String string2 = StubApp.getString2(13881);
                    BleLog.i(string2, str);
                    if (i == 0) {
                        int i3 = i2;
                        if (i3 == 2) {
                            if (BluetoothConnection.this.scanResultCallback != null) {
                                BluetoothConnection.this.scanResultCallback.onConnected(bluetoothGatt, i, i2);
                            }
                            bluetoothGatt.discoverServices();
                            return;
                        } else {
                            if (i3 == 0) {
                                if (BluetoothConnection.this.scanResultCallback != null) {
                                    BluetoothConnection.this.scanResultCallback.onDisconnceted(bluetoothGatt, i, i2);
                                }
                                BluetoothConnection.this.release();
                                return;
                            }
                            return;
                        }
                    }
                    bluetoothGatt.disconnect();
                    bluetoothGatt.close();
                    if (BluetoothConnection.this.isRetried) {
                        return;
                    }
                    BluetoothConnection.this.isRetried = true;
                    Log.d(string2, StubApp.getString2(13886) + BluetoothConnection.this.mContext + StubApp.getString2(13887) + BluetoothConnection.this.mBluetoothDevice);
                    if (i != 133 || BluetoothConnection.this.mContext == null || BluetoothConnection.this.mBluetoothDevice == null) {
                        return;
                    }
                    BluetoothConnection.this.connectGatt(BluetoothConnection.this.mContext, BluetoothConnection.this.mBluetoothDevice);
                }
            }, 100L);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            if (i == 0) {
                BluetoothConnection.this.bluetoothGattService = bluetoothGatt.getService(UUID.fromString(StubApp.getString2(13894)));
                if (BluetoothConnection.this.scanResultCallback != null) {
                    BluetoothConnection.this.scanResultCallback.onServiceDiscovery(bluetoothGatt, i);
                }
                BleLog.w(StubApp.getString2(13881), StubApp.getString2(13896) + BluetoothUtil.enableNotification(bluetoothGatt, true, BluetoothConnection.this.bluetoothGattService.getCharacteristic(UUID.fromString(StubApp.getString2(13895)))));
                Operator.sendStartFrame(BluetoothConnection.this.content, BluetoothConnection.this.writeDataCallback);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            String string2 = StubApp.getString2(13881);
            if (i == 0) {
                BleLog.i(string2, StubApp.getString2(13892) + DataUtil.bytes2Hex(bluetoothGattCharacteristic.getValue()));
                if (BluetoothConnection.this.scanResultCallback != null) {
                    BluetoothConnection.this.scanResultCallback.onCharacteristicWriteSuccess(bluetoothGattCharacteristic.getValue());
                    return;
                }
                return;
            }
            BleLog.i(string2, StubApp.getString2(13893) + DataUtil.bytes2Hex(bluetoothGattCharacteristic.getValue()));
            if (BluetoothConnection.this.scanResultCallback != null) {
                BluetoothConnection.this.scanResultCallback.onCharacteristicWriteFailure(bluetoothGattCharacteristic.getValue());
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            String str = StubApp.getString2(13888) + DataUtil.bytes2Hex(bluetoothGattCharacteristic.getValue());
            String string2 = StubApp.getString2(13889);
            BleLog.w(string2, str);
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (BluetoothConnection.this.scanResultCallback != null) {
                BluetoothConnection.this.scanResultCallback.onCharacteristicChanged(bluetoothGattCharacteristic.getValue());
            }
            if (value.length == 2 && value[0] == 2) {
                BleLog.w(string2, StubApp.getString2(13890) + DataUtil.bytes2Hex(bluetoothGattCharacteristic.getValue()));
                if (value[1] == 1) {
                    Operator.sendOtherFrame(BluetoothConnection.this.content, BluetoothConnection.this.writeDataCallback);
                    BluetoothConnection.this.endFrameResponseListener.start();
                    return;
                }
                return;
            }
            if (value.length == 5 && value[0] == 5) {
                BluetoothConnection.this.endFrameResponseListener.cancel();
                BleLog.w(string2, StubApp.getString2(13891) + DataUtil.bytes2Hex(bluetoothGattCharacteristic.getValue()));
                if (value[4] == 1) {
                    BluetoothConnection.this.bluetoothGatt.disconnect();
                    BluetoothConnection.this.frameResponseCallback.onEndFrameResponse(value);
                } else {
                    Operator.sendStartFrame(BluetoothConnection.this.content, BluetoothConnection.this.writeDataCallback);
                }
            }
        }
    };

    public BluetoothConnection(String str, WifiInfoBean wifiInfoBean, FrameResponseCallback frameResponseCallback) {
        this.mWifiInfo = wifiInfoBean;
        this.frameResponseCallback = frameResponseCallback;
        Gson gson = new Gson();
        String json = gson.toJson(this.mWifiInfo);
        String json2 = gson.toJson(createSafeWifiInfoBean(this.mWifiInfo));
        this.encrypt = AESEncryptUtil.encrypt(json, str, StubApp.getString2(13897));
        BleLog.i(StubApp.getString2(13889), StubApp.getString2(13898) + str + StubApp.getString2(13899) + json2 + StubApp.getString2(13900) + this.encrypt);
        this.content = DataUtil.stringSlice(this.encrypt);
    }

    private WifiInfoBean createSafeWifiInfoBean(WifiInfoBean wifiInfoBean) {
        if (wifiInfoBean == null) {
            return null;
        }
        WifiInfoBean wifiInfoBean2 = new WifiInfoBean();
        wifiInfoBean2.setSsid(wifiInfoBean.getSsid());
        wifiInfoBean2.setRandomNumber(wifiInfoBean.getRandomNumber());
        wifiInfoBean2.setPassword(StubApp.getString2(13901));
        if (wifiInfoBean.getParmas() != null) {
            WifiInfoBean.Parmas parmas = new WifiInfoBean.Parmas();
            parmas.api = wifiInfoBean.getParmas().api;
            wifiInfoBean2.setParmas(parmas);
        }
        return wifiInfoBean2;
    }

    public void connectGatt(Context context, BluetoothDevice bluetoothDevice) throws InterruptedException {
        this.mBluetoothDevice = bluetoothDevice;
        this.mContext = context;
        BluetoothGatt bluetoothGatt = this.bluetoothGatt;
        if (bluetoothGatt != null && bluetoothGatt.getConnectedDevices() != null) {
            Iterator<BluetoothDevice> it2 = this.bluetoothGatt.getConnectedDevices().iterator();
            while (it2.hasNext()) {
                if (it2.next().getAddress().equals(bluetoothDevice.getAddress())) {
                    this.bluetoothGatt.disconnect();
                }
            }
            this.bluetoothGatt.close();
            this.bluetoothGatt = null;
        }
        try {
            Thread.sleep(600L);
            this.bluetoothGatt = bluetoothDevice.connectGatt(context, true, this.bluetoothGattCallback, 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
            this.bluetoothGatt = bluetoothDevice.connectGatt(context, true, this.bluetoothGattCallback, 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean writeData(byte[] bArr) {
        BluetoothGattCharacteristic characteristic = this.bluetoothGattService.getCharacteristic(UUID.fromString(StubApp.getString2(13902)));
        characteristic.setValue(bArr);
        return this.bluetoothGatt.writeCharacteristic(characteristic);
    }

    public void registScanResultCallback(ScanResultCallback scanResultCallback) {
        this.scanResultCallback = scanResultCallback;
    }

    public void release() {
        this.scanResultCallback = null;
        this.bluetoothGatt.disconnect();
        this.bluetoothGatt.close();
        this.bluetoothGatt = null;
        this.bluetoothGattService = null;
        BleLog.w(StubApp.getString2(13881), StubApp.getString2(13903));
    }
}
