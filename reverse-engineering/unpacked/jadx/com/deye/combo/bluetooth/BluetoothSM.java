package com.deye.combo.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.CountDownTimer;
import com.deye.combo.bean.WifiInfoBean;
import com.deye.combo.callback.FrameResponseCallback;
import com.deye.combo.callback.ScanResultCallback;
import com.deye.combo.log.BleLog;
import com.deye.combo.utils.DataUtil;
import com.stub.StubApp;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class BluetoothSM {
    private static volatile BluetoothSM INSTANCE;
    private static final String TAG = StubApp.getString2(13907);
    private static Context context;
    private String envUrl;
    private FrameResponseCallback frameResponseCallback;
    private String mac;
    private String password;
    private String productId;
    private ScanResultCallback scanResultCallback;
    private String ssid;
    private int mState = -1;
    private CountDownTimer scanTimer = new CountDownTimer(20000, 1000) { // from class: com.deye.combo.bluetooth.BluetoothSM.1
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            BleLog.i("", StubApp.getString2(13904));
            BluetoothSM.this.stopScan();
            if (BluetoothSM.this.scanResultCallback != null) {
                BluetoothSM.this.scanResultCallback.onScanTimeOut();
            }
        }
    };
    private ScanCallback scanCallback = new ScanCallback() { // from class: com.deye.combo.bluetooth.BluetoothSM.2
        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) throws InterruptedException {
            super.onScanResult(i, scanResult);
            BluetoothSM.this.scanResult(scanResult);
            if (BluetoothSM.this.scanResultCallback != null) {
                BluetoothSM.this.scanResultCallback.onScanResult(i, scanResult);
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
            if (BluetoothSM.this.scanResultCallback != null) {
                BluetoothSM.this.scanResultCallback.onBatchScanResults(list);
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
            if (BluetoothSM.this.scanResultCallback != null) {
                BluetoothSM.this.scanResultCallback.onScanFailed(i);
            }
        }
    };
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    public void setEnvUrl(String str) {
        this.envUrl = str;
    }

    public static BluetoothSM getInstance() {
        if (INSTANCE == null) {
            synchronized (BluetoothSM.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BluetoothSM();
                }
            }
        }
        return INSTANCE;
    }

    public static void init(Context context2) {
        context = StubApp.getOrigApplicationContext(context2.getApplicationContext());
    }

    private BluetoothSM() {
    }

    private void startScanTimer() {
        this.scanTimer.cancel();
        this.scanTimer.start();
    }

    private void stopScanTimer() {
        this.scanTimer.cancel();
    }

    public boolean isEnable() {
        return this.bluetoothAdapter.isEnabled();
    }

    public void startScan(String str, String str2, String str3, FrameResponseCallback frameResponseCallback, Context context2) {
        if (isEnable()) {
            this.ssid = str;
            this.password = str2;
            this.frameResponseCallback = frameResponseCallback;
            startScanTimer();
            this.productId = str3;
            stopScan();
            context = context2;
            this.mState = 1;
            this.bluetoothAdapter.getBluetoothLeScanner().startScan(this.scanCallback);
            this.mState = 2;
        }
    }

    public void stopScan() {
        BluetoothLeScanner bluetoothLeScanner;
        this.mState = 8;
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter == null || (bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner()) == null) {
            return;
        }
        bluetoothLeScanner.stopScan(this.scanCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scanResult(ScanResult scanResult) throws InterruptedException {
        if (scanResult.getScanRecord() == null || scanResult.getScanRecord().getBytes() == null) {
            return;
        }
        byte[] bytes = scanResult.getScanRecord().getBytes();
        if (scanResult.getScanRecord().getBytes()[5] == 34 && scanResult.getScanRecord().getBytes()[6] == 9) {
            String str = StubApp.getString2(13905) + scanResult.getDevice().getName() + StubApp.getString2(13906) + scanResult.getDevice().getAddress();
            String string2 = StubApp.getString2(13907);
            BleLog.i(string2, str);
            String strBytes2Hex = DataUtil.bytes2Hex(bytes, 16, 12);
            if (this.productId.contains(strBytes2Hex)) {
                this.mac = DataUtil.bytes2Hex(bytes, 13, 3);
                BleLog.i(string2, StubApp.getString2(13908) + this.productId + StubApp.getString2(13909) + this.mac);
                matched(scanResult);
                return;
            }
            BleLog.i(string2, StubApp.getString2(13910) + strBytes2Hex + StubApp.getString2(13911) + this.productId);
        }
    }

    private void matched(ScanResult scanResult) throws InterruptedException {
        stopScanTimer();
        stopScan();
        ScanResultCallback scanResultCallback = this.scanResultCallback;
        if (scanResultCallback != null) {
            scanResultCallback.onScanMatched(scanResult);
        }
        this.mState = 16;
        StubApp.getString2(2160).equals(scanResult.getDevice().getName());
        connectGatt(StubApp.getOrigApplicationContext(context.getApplicationContext()), scanResult.getDevice());
    }

    private void connectGatt(Context context2, BluetoothDevice bluetoothDevice) throws InterruptedException {
        System.currentTimeMillis();
        String str = this.mac + System.currentTimeMillis();
        WifiInfoBean wifiInfoBean = new WifiInfoBean();
        wifiInfoBean.setSsid(this.ssid);
        wifiInfoBean.setPassword(this.password);
        wifiInfoBean.setRandomNumber(str);
        WifiInfoBean.Parmas parmas = new WifiInfoBean.Parmas();
        parmas.api = this.envUrl;
        wifiInfoBean.setParmas(parmas);
        ScanResultCallback scanResultCallback = this.scanResultCallback;
        if (scanResultCallback != null) {
            scanResultCallback.onStartConnect(str);
        }
        BluetoothConnection bluetoothConnection = new BluetoothConnection(this.productId, wifiInfoBean, this.frameResponseCallback);
        bluetoothConnection.registScanResultCallback(this.scanResultCallback);
        bluetoothConnection.connectGatt(context2, bluetoothDevice);
    }

    public void registerScanResultCallback(ScanResultCallback scanResultCallback) {
        this.scanResultCallback = scanResultCallback;
    }

    private void unregisterScanResultCallback() {
        this.scanResultCallback = null;
    }

    public void release() {
        stopScan();
        unregisterScanResultCallback();
    }
}
