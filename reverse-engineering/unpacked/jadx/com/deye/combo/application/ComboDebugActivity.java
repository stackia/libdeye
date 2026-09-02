package com.deye.combo.application;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.le.ScanResult;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import com.deye.combo.R;
import com.deye.combo.bluetooth.BluetoothSM;
import com.deye.combo.callback.BleLogIntercept;
import com.deye.combo.callback.FrameResponseCallback;
import com.deye.combo.callback.ScanResultCallback;
import com.deye.combo.utils.DataUtil;
import com.dianping.logan.Logan;
import com.stub.StubApp;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ComboDebugActivity extends AppCompatActivity {
    private NestedScrollView mNestedScrollView;
    private ScanResultCallback mScanResultCallback = new ScanResultCallback() { // from class: com.deye.combo.application.ComboDebugActivity.2
        @Override // com.deye.combo.callback.ScanResultCallback
        public void onBatchScanResults(List<ScanResult> list) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onCharacteristicChanged(byte[] bArr) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onCharacteristicWriteFailure(byte[] bArr) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onCharacteristicWriteSuccess(byte[] bArr) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onConnected(BluetoothGatt bluetoothGatt, int i, int i2) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onDisconnceted(BluetoothGatt bluetoothGatt, int i, int i2) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onScanMatched(ScanResult scanResult) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onScanResult(int i, ScanResult scanResult) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onServiceDiscovery(BluetoothGatt bluetoothGatt, int i) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onStartConnect(String str) {
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onScanFailed(int i) {
            ComboDebugActivity.this.showLog(StubApp.getString2(13868) + i);
        }

        @Override // com.deye.combo.callback.ScanResultCallback
        public void onScanTimeOut() {
            ComboDebugActivity.this.showLog(StubApp.getString2(13869));
        }
    };
    private StringBuffer mStringBuffer;
    private TextView mTvLog;

    static {
        StubApp.interface11(14676);
    }

    private native void clearLog();

    /* JADX INFO: Access modifiers changed from: private */
    public native void showLog(String str);

    public native void applyPermission();

    protected native void onCreate(Bundle bundle);

    public native void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);

    /* renamed from: com.deye.combo.application.ComboDebugActivity$1, reason: invalid class name */
    class AnonymousClass1 implements BleLogIntercept {
        AnonymousClass1() {
        }

        @Override // com.deye.combo.callback.BleLogIntercept
        public void onLog(String str, String str2) {
            Logan.w(str + StubApp.getString2(13292) + str2, 2);
            ComboDebugActivity.this.showLog(str + StubApp.getString2(13867) + str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        EditText editText = (EditText) findViewById(R.id.et_pid);
        EditText editText2 = (EditText) findViewById(R.id.et_ssid);
        EditText editText3 = (EditText) findViewById(R.id.et_pwd);
        String string = editText2.getText().toString();
        String string2 = editText3.getText().toString();
        String string3 = editText.getText().toString();
        showLog(StubApp.getString2(13871) + string3);
        showLog(StubApp.getString2(13872) + string);
        showLog(StubApp.getString2(13873) + string2);
        BluetoothSM.getInstance().startScan(string, string2, string3, new FrameResponseCallback() { // from class: com.deye.combo.application.ComboDebugActivity$$ExternalSyntheticLambda2
            @Override // com.deye.combo.callback.FrameResponseCallback
            public final void onEndFrameResponse(byte[] bArr) {
                this.f$0.lambda$onCreate$0(bArr);
            }
        }, getApplication());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(byte[] bArr) {
        showLog(StubApp.getString2(13870) + DataUtil.bytes2Hex(bArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2(View view) {
        clearLog();
    }

    /* renamed from: com.deye.combo.application.ComboDebugActivity$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {
        AnonymousClass3() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ComboDebugActivity.this.mTvLog.setText(ComboDebugActivity.this.mStringBuffer.toString());
            ComboDebugActivity.this.mNestedScrollView.fullScroll(130);
        }
    }
}
