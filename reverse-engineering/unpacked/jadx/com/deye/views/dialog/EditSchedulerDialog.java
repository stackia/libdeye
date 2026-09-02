package com.deye.views.dialog;

import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.R;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class EditSchedulerDialog {
    private static final String TAG = StubApp.getString2(14663);
    private static volatile EditSchedulerDialog sEditSchedulerDialog;
    private NiceDialog mNiceDialog;

    public interface OnEditSchedulerListsner {
        void onSchedulerResult(int i, int i2);
    }

    private EditSchedulerDialog() {
    }

    public static EditSchedulerDialog getInstance() {
        if (sEditSchedulerDialog == null) {
            synchronized (EditSchedulerDialog.class) {
                if (sEditSchedulerDialog == null) {
                    sEditSchedulerDialog = new EditSchedulerDialog();
                }
            }
        }
        return sEditSchedulerDialog;
    }

    public void builder(BaseActivity baseActivity, int i, int i2, OnEditSchedulerListsner onEditSchedulerListsner) {
        if (this.mNiceDialog == null) {
            NiceDialog niceDialogInit = NiceDialog.init();
            this.mNiceDialog = niceDialogInit;
            niceDialogInit.setLayoutId(R.layout.edit_scheduler_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.views.dialog.EditSchedulerDialog.1
                @Override // com.othershe.nicedialog.ViewConvertListener
                protected void convertView(ViewHolder viewHolder, BaseNiceDialog baseNiceDialog) {
                    viewHolder.getView(R.id.wheelDayPicker_hour);
                    viewHolder.getView(R.id.wheelDayPicker_min);
                }
            }).setOutCancel(true).setShowBottom(true).show(baseActivity.getSupportFragmentManager());
        }
    }

    public void show(BaseActivity baseActivity, int i, int i2, OnEditSchedulerListsner onEditSchedulerListsner) {
        this.mNiceDialog.setLayoutId(R.layout.edit_scheduler_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.views.dialog.EditSchedulerDialog.2
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, BaseNiceDialog baseNiceDialog) {
                viewHolder.getView(R.id.wheelDayPicker_hour);
                viewHolder.getView(R.id.wheelDayPicker_min);
            }
        }).setOutCancel(true).setShowBottom(true).show(baseActivity.getSupportFragmentManager());
    }
}
