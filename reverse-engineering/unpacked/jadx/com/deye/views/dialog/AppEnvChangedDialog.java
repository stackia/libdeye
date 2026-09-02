package com.deye.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.mxchipapp.R;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class AppEnvChangedDialog extends Dialog {

    public interface IOnClickListsner {
        void onCancel();

        void onSure(String str);
    }

    public AppEnvChangedDialog(Context context, String str, final IOnClickListsner iOnClickListsner) {
        super(context, R.style.DialogTheme);
        setContentView(R.layout.dialog_app_env_changed_tip_layout);
        TextView textView = (TextView) findViewById(R.id.tv_content);
        TextView textView2 = (TextView) findViewById(R.id.tv_cancel);
        TextView textView3 = (TextView) findViewById(R.id.tv_sure);
        textView.setText(str);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.dialog.AppEnvChangedDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (iOnClickListsner != null) {
                    AppEnvChangedDialog.this.dismiss();
                    iOnClickListsner.onCancel();
                }
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.dialog.AppEnvChangedDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (iOnClickListsner != null) {
                    AppEnvChangedDialog.this.dismiss();
                    iOnClickListsner.onSure("");
                }
            }
        });
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.width = -1;
        window.setAttributes(attributes);
        setCancelable(true);
    }
}
