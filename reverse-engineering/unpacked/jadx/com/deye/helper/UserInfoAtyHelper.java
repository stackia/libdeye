package com.deye.helper;

import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.aigestudio.wheelpicker.widgets.WheelAreaPicker;
import com.deye.activity.mine.UserInfoActivity;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import org.json.JSONException;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class UserInfoAtyHelper {
    private String mCityName;
    private UserInfoActivity mCtx;
    private String mProvince;

    public UserInfoAtyHelper(UserInfoActivity userInfoActivity) {
        this.mCtx = userInfoActivity;
    }

    public void showChoicePlace(final TextView textView, final String str) {
        NiceDialog.init().setLayoutId(R.layout.choice_place_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.UserInfoAtyHelper.1
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, BaseNiceDialog baseNiceDialog) {
                UserInfoAtyHelper.this.initViewAndEvent(str, textView, viewHolder, baseNiceDialog);
            }
        }).setShowBottom(true).show(this.mCtx.getSupportFragmentManager());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewAndEvent(String str, final TextView textView, ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
        RelativeLayout relativeLayout = (RelativeLayout) viewHolder.getView(R.id.ll_root_view);
        TextView textView2 = (TextView) viewHolder.getView(R.id.tv_cancel);
        TextView textView3 = (TextView) viewHolder.getView(R.id.tv_sure);
        final WheelAreaPicker view = viewHolder.getView(R.id.wheel_choice_place);
        getProvinceandCity(str);
        view.setLayerType(1, (Paint) null);
        view.setSelectProvince(this.mProvince);
        view.setSelectCity(this.mCityName);
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.UserInfoAtyHelper.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                baseNiceDialog.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.UserInfoAtyHelper.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                baseNiceDialog.dismiss();
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.UserInfoAtyHelper.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) throws JSONException {
                UserInfoAtyHelper.this.resetArea(textView, view.getProvince() + StubApp.getString2(450) + view.getCity(), baseNiceDialog);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetArea(final TextView textView, final String str, final BaseNiceDialog baseNiceDialog) throws JSONException {
        DeYeHttpRequestManager.getInstance().setUserArea(str, new FogCallBack() { // from class: com.deye.helper.UserInfoAtyHelper.5
            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onSuccess(String str2) {
                baseNiceDialog.dismiss();
                UserInfoAtyHelper.this.mCtx.runOnUiThread(new Runnable() { // from class: com.deye.helper.UserInfoAtyHelper.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        textView.setText(str);
                    }
                });
            }

            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onFailure(int i, String str2) {
                baseNiceDialog.dismiss();
                if (UserInfoAtyHelper.this.mCtx.isFinishing() || UserInfoAtyHelper.this.mCtx.isDestroyed()) {
                    return;
                }
                UserInfoAtyHelper.this.mCtx.runOnUiThread(new Runnable() { // from class: com.deye.helper.UserInfoAtyHelper.5.2
                    @Override // java.lang.Runnable
                    public void run() {
                        BaseUtils.showShortToast(UserInfoAtyHelper.this.mCtx, StubApp.getString2(14038));
                    }
                });
            }
        });
    }

    private void getProvinceandCity(String str) {
        if (BaseUtils.isNullString(str)) {
            return;
        }
        String[] strArrSplit = str.split(StubApp.getString2(450));
        this.mProvince = strArrSplit[0];
        this.mCityName = strArrSplit[1];
    }
}
