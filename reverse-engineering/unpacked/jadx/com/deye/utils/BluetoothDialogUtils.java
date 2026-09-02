package com.deye.utils;

import android.content.Intent;
import android.view.View;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import com.mxchipapp.R;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class BluetoothDialogUtils {
    public static void openBluetoothDialog(final FragmentActivity fragmentActivity) {
        NiceDialog.init().setLayoutId(R.layout.enable_bluetooth_dialog_layout).setConvertListener(new ViewConvertListener() { // from class: com.deye.utils.BluetoothDialogUtils.1
            @Override // com.othershe.nicedialog.ViewConvertListener
            public void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.getView(R.id.tv_title).setVisibility(0);
                viewHolder.setText(R.id.tv_title, R.string.tip_text);
                viewHolder.setText(R.id.tv_content, fragmentActivity.getResources().getString(R.string.open_ble_tip_text));
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.utils.BluetoothDialogUtils.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.utils.BluetoothDialogUtils.1.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (ActivityCompat.checkSelfPermission(fragmentActivity, StubApp.getString2(8633)) != 0) {
                            fragmentActivity.startActivity(new Intent(StubApp.getString2(14303)));
                        } else {
                            fragmentActivity.startActivityForResult(new Intent(StubApp.getString2(14304)), 16);
                        }
                    }
                });
            }
        }).setWidth(305).setOutCancel(true).show(fragmentActivity.getSupportFragmentManager());
    }
}
