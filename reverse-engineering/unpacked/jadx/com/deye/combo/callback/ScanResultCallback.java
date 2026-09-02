package com.deye.combo.callback;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.le.ScanResult;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public interface ScanResultCallback {
    void onBatchScanResults(List<ScanResult> list);

    void onCharacteristicChanged(byte[] bArr);

    void onCharacteristicWriteFailure(byte[] bArr);

    void onCharacteristicWriteSuccess(byte[] bArr);

    void onConnected(BluetoothGatt bluetoothGatt, int i, int i2);

    void onDisconnceted(BluetoothGatt bluetoothGatt, int i, int i2);

    void onScanFailed(int i);

    void onScanMatched(ScanResult scanResult);

    void onScanResult(int i, ScanResult scanResult);

    void onScanTimeOut();

    void onServiceDiscovery(BluetoothGatt bluetoothGatt, int i);

    void onStartConnect(String str);
}
