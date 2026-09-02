package com.deye.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ErrorDialog {
    private static final String TAG = StubApp.getString2(14664);
    private static volatile ErrorDialog sErrorDialog;
    private Context mCtx;
    private Dialog mErrorDialog;
    private ICallBack mICallBack;

    public interface ICallBack {
        void onCallBack();
    }

    private ErrorDialog() {
    }

    public static ErrorDialog getInstance() {
        if (sErrorDialog == null) {
            synchronized (ErrorDialog.class) {
                if (sErrorDialog == null) {
                    sErrorDialog = new ErrorDialog();
                }
            }
        }
        return sErrorDialog;
    }

    public void build(Context context, boolean z, final ICallBack iCallBack) {
        dismiss();
        try {
            this.mCtx = context;
            Dialog dialog = new Dialog(context, R.style.loading_dialog);
            this.mErrorDialog = dialog;
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.deye.views.dialog.ErrorDialog.1
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    ICallBack iCallBack2;
                    if (i == 4 && (iCallBack2 = iCallBack) != null) {
                        iCallBack2.onCallBack();
                    }
                    return false;
                }
            });
            this.mErrorDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.deye.views.dialog.ErrorDialog.2
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    ICallBack iCallBack2 = iCallBack;
                    if (iCallBack2 != null) {
                        iCallBack2.onCallBack();
                    }
                }
            });
            this.mErrorDialog.setCancelable(z);
            this.mErrorDialog.setCanceledOnTouchOutside(z);
        } catch (Exception e) {
            LogUtil.d(StubApp.getString2(14665));
            e.printStackTrace();
        }
    }

    public void dismiss() {
        try {
            Dialog dialog = this.mErrorDialog;
            if (dialog != null) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            LogUtil.d(StubApp.getString2(14666));
            e.printStackTrace();
        }
    }

    public void show(int i, boolean z) {
        try {
            this.mErrorDialog.setCancelable(z);
            this.mErrorDialog.setCanceledOnTouchOutside(z);
            this.mErrorDialog.setContentView((RelativeLayout) LayoutInflater.from(this.mCtx).inflate(i, (ViewGroup) null).findViewById(R.id.ll_mobile_device_error_mask), new LinearLayout.LayoutParams(-1, -1));
            this.mErrorDialog.show();
        } catch (Exception e) {
            LogUtil.d(StubApp.getString2(14667));
            e.printStackTrace();
        }
    }

    public void show(Context context, int i) {
        try {
            this.mErrorDialog.setContentView((RelativeLayout) LayoutInflater.from(context).inflate(i, (ViewGroup) null).findViewById(R.id.ll_mobile_device_error_mask), new LinearLayout.LayoutParams(-1, -1));
            this.mErrorDialog.show();
        } catch (Exception e) {
            LogUtil.d(StubApp.getString2(14668));
            e.printStackTrace();
        }
    }
}
