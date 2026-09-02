package com.deye.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class BluetoothManager {
    private BluetoothAdapter mBluetoothAdapter;
    private Context mCtx;
    private IBluetoothEnableListener mIBluetoothEnableListener;
    private IIsSupportBluetooth mIIsSupportBluetooth;
    private BluetoothAdapter.LeScanCallback mLeScanCallback = null;
    public BroadcastReceiver mBluetoothStatasBroadcastReceiver = null;
    private int mCanDiscoverbleBluetoothLeSecond = 300;

    public void setCanDiscoverbleBluetoothLeSecond(int i) {
        this.mCanDiscoverbleBluetoothLeSecond = i;
    }

    public void setIIsSupportBluetooth(IIsSupportBluetooth iIsSupportBluetooth) {
        this.mIIsSupportBluetooth = iIsSupportBluetooth;
        if (this.mBluetoothAdapter == null) {
            this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        IIsSupportBluetooth iIsSupportBluetooth2 = this.mIIsSupportBluetooth;
        if (iIsSupportBluetooth2 != null) {
            iIsSupportBluetooth2.isSupportBluetooth(this.mBluetoothAdapter != null);
        }
    }

    public BluetoothManager(Context context) {
        this.mCtx = StubApp.getOrigApplicationContext(context.getApplicationContext());
        if (this.mBluetoothAdapter == null) {
            this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
    }

    public boolean isBluetoothEnable() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.isEnabled();
        }
        return false;
    }

    public boolean turnOnBluetooth() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.enable();
        }
        return false;
    }

    public void observeBluetoothStatusChanged(boolean z, IBluetoothEnableListener iBluetoothEnableListener) {
        this.mIBluetoothEnableListener = iBluetoothEnableListener;
        registerReceiverBluetoothBroadcastReceiver();
        if (!isBluetoothEnable()) {
            IBluetoothEnableListener iBluetoothEnableListener2 = this.mIBluetoothEnableListener;
            if (iBluetoothEnableListener2 != null) {
                if (z) {
                    iBluetoothEnableListener2.onCurrentBluetoothEnable(turnOnBluetooth() ? 12 : 10);
                    return;
                } else {
                    iBluetoothEnableListener2.onCurrentBluetoothEnable(10);
                    return;
                }
            }
            return;
        }
        IBluetoothEnableListener iBluetoothEnableListener3 = this.mIBluetoothEnableListener;
        if (iBluetoothEnableListener3 != null) {
            iBluetoothEnableListener3.onCurrentBluetoothEnable(12);
        }
    }

    public void startLeScan(final ISearchBluetoothDeviceListener iSearchBluetoothDeviceListener) {
        if (this.mLeScanCallback == null) {
            this.mLeScanCallback = new BluetoothAdapter.LeScanCallback() { // from class: com.deye.bluetooth.BluetoothManager.1
                @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
                public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                    ISearchBluetoothDeviceListener iSearchBluetoothDeviceListener2 = iSearchBluetoothDeviceListener;
                    if (iSearchBluetoothDeviceListener2 != null) {
                        iSearchBluetoothDeviceListener2.onBluetoothDeviceFound(bluetoothDevice);
                    }
                }
            };
        }
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.startLeScan(this.mLeScanCallback);
        }
    }

    private void registerReceiverBluetoothBroadcastReceiver() {
        if (this.mBluetoothStatasBroadcastReceiver == null) {
            this.mBluetoothStatasBroadcastReceiver = new BroadcastReceiver() { // from class: com.deye.bluetooth.BluetoothManager.2
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    action.hashCode();
                    if (action.equals(StubApp.getString2(8680))) {
                        int intExtra = intent.getIntExtra(StubApp.getString2(8681), 0);
                        if (intExtra == 10) {
                            LogUtil.d(StubApp.getString2(13828));
                            if (BluetoothManager.this.mIBluetoothEnableListener != null) {
                                BluetoothManager.this.mIBluetoothEnableListener.onBluetoothStatusChanged(10);
                                return;
                            }
                            return;
                        }
                        if (intExtra != 12) {
                            return;
                        }
                        LogUtil.d(StubApp.getString2(13827));
                        if (BluetoothManager.this.mIBluetoothEnableListener != null) {
                            BluetoothManager.this.mIBluetoothEnableListener.onBluetoothStatusChanged(12);
                        }
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(StubApp.getString2(8680));
            Context context = this.mCtx;
            if (context != null) {
                context.registerReceiver(this.mBluetoothStatasBroadcastReceiver, intentFilter);
            }
        }
    }

    public void stopLeScan() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.stopLeScan(this.mLeScanCallback);
            this.mLeScanCallback = null;
        }
    }

    private void unregisterReceiverBluetoothBroadcastReceiver() {
        Context context;
        BroadcastReceiver broadcastReceiver = this.mBluetoothStatasBroadcastReceiver;
        if (broadcastReceiver != null && (context = this.mCtx) != null) {
            context.unregisterReceiver(broadcastReceiver);
        }
        this.mBluetoothStatasBroadcastReceiver = null;
    }

    public void onDestory() {
        stopLeScan();
        unregisterReceiverBluetoothBroadcastReceiver();
        this.mBluetoothAdapter = null;
        this.mIIsSupportBluetooth = null;
        this.mIBluetoothEnableListener = null;
        this.mCanDiscoverbleBluetoothLeSecond = 300;
        this.mCtx = null;
    }
}
