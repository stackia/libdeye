package com.deye.bluetooth;

import android.content.Context;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class BluetoothTools {
    private static volatile BluetoothTools sBluetoothTools;
    private BluetoothManager mBluetoothManager;

    public void setIIsSupportBluetooth(IIsSupportBluetooth iIsSupportBluetooth) {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager != null) {
            bluetoothManager.setIIsSupportBluetooth(iIsSupportBluetooth);
        }
    }

    private BluetoothTools(Context context) {
        if (this.mBluetoothManager == null) {
            this.mBluetoothManager = new BluetoothManager(context);
        }
    }

    public static BluetoothTools getInstance(Context context) {
        if (sBluetoothTools == null) {
            synchronized (BluetoothTools.class) {
                if (sBluetoothTools == null) {
                    sBluetoothTools = new BluetoothTools(context);
                }
            }
        }
        return sBluetoothTools;
    }

    public void startLeScan(ISearchBluetoothDeviceListener iSearchBluetoothDeviceListener) {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager != null) {
            bluetoothManager.startLeScan(iSearchBluetoothDeviceListener);
        }
    }

    public void stopLeScan() {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager != null) {
            bluetoothManager.stopLeScan();
        }
    }

    public void onDestory() {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager != null) {
            bluetoothManager.onDestory();
        }
    }

    public boolean isBluetoothEnable() {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager != null) {
            return bluetoothManager.isBluetoothEnable();
        }
        return false;
    }

    public boolean turnOnBluetooth() {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager != null) {
            return bluetoothManager.turnOnBluetooth();
        }
        return false;
    }

    public void observeBluetoothStatusChanged(IBluetoothEnableListener iBluetoothEnableListener) {
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager != null) {
            bluetoothManager.setCanDiscoverbleBluetoothLeSecond(300);
        }
        BluetoothManager bluetoothManager2 = this.mBluetoothManager;
        if (bluetoothManager2 != null) {
            bluetoothManager2.observeBluetoothStatusChanged(true, iBluetoothEnableListener);
        }
    }
}
