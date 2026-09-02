package com.deye.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.mxchipapp.R;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class OffLineDialog extends Dialog {
    private static volatile OffLineDialog sOffLineDialog;
    private ClickListenerInterface clickListenerInterface;
    private Context context;

    public interface ClickListenerInterface {
        void doConfirm();
    }

    /* compiled from: D8$$SyntheticClass */
    /* renamed from: com.deye.views.dialog.OffLineDialog$clickListener-IA, reason: invalid class name */
    public final /* synthetic */ class clickListenerIA {
    }

    public static OffLineDialog getInstance(Context context) {
        if (sOffLineDialog == null) {
            synchronized (OffLineDialog.class) {
                if (sOffLineDialog == null) {
                    sOffLineDialog = new OffLineDialog(context);
                }
            }
        }
        return sOffLineDialog;
    }

    public void recycle() {
        if (sOffLineDialog != null) {
            sOffLineDialog = null;
        }
    }

    private OffLineDialog(Context context) {
        super(context, R.style.offline_dialog);
        this.context = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        init();
    }

    public void init() {
        View viewInflate = LayoutInflater.from(this.context).inflate(R.layout.offline_tip_dialog, (ViewGroup) null);
        setContentView(viewInflate);
        ((TextView) viewInflate.findViewById(R.id.tv_sure)).setOnClickListener(new clickListener(this, null));
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
        attributes.windowAnimations = 0;
        attributes.format = -3;
        attributes.width = (int) (displayMetrics.widthPixels * 1.0d);
        attributes.height = (int) (displayMetrics.heightPixels * 1.0d);
        window.setAttributes(attributes);
        setCancelable(false);
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private class clickListener implements View.OnClickListener {
        /* synthetic */ clickListener(OffLineDialog offLineDialog, clickListenerIA clicklisteneria) {
            this();
        }

        private clickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.getId() == 2131363206 && OffLineDialog.this.clickListenerInterface != null) {
                OffLineDialog.this.clickListenerInterface.doConfirm();
            }
        }
    }
}
