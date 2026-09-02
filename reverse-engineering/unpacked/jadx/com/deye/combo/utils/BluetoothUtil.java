package com.deye.combo.utils;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import com.stub.StubApp;
import java.util.UUID;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class BluetoothUtil {
    public static boolean enableNotification(BluetoothGatt bluetoothGatt, boolean z, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGattDescriptor descriptor;
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null || !bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z) || (descriptor = bluetoothGattCharacteristic.getDescriptor(UUID.fromString(StubApp.getString2(13915)))) == null) {
            return false;
        }
        if (z) {
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        } else {
            descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        }
        return bluetoothGatt.writeDescriptor(descriptor);
    }
}
