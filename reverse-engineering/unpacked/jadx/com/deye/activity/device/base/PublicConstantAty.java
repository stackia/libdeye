package com.deye.activity.device.base;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.deye.ErrorCodeMap;
import com.deye.MxchipApplication;
import com.deye.activity.config_net.DeviceInfoActivity;
import com.deye.activity.device.ErrorListInfoAty;
import com.deye.activity.device.HumidityInfoAty;
import com.deye.activity.device.air_conditioner.base.AirConditionerControlPanelUIAty;
import com.deye.configs.Constants;
import com.deye.utils.PagerUtils;
import com.google.gson.Gson;
import com.luck.picture.lib.utils.DensityUtil;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public abstract class PublicConstantAty extends BaseActivity {
    public static final String TAG = StubApp.getString2(13564);
    protected DeviceListBean mDeviceListBean;
    private View mNoNetTipLayout;
    private FrameLayout mRlRootView;
    private View mWaterPumpLayout;
    private View mWaterTankLayout;
    protected int mWeatherInfoPosition;
    protected long mCurrentClickedTime = 0;
    protected ArrayList<String> mErrorTextList = new ArrayList<>();
    protected ArrayList<String> mErrorTextTempList = new ArrayList<>();
    protected String mPlace = "";
    protected String mTemperature = "";
    protected String mHumidity = "";
    protected String mDevinfoStr = "";
    protected String mDeviceId = "";
    protected String mProductId = "";
    protected boolean mIsCombo = false;
    protected String protocolVersion = StubApp.getString2(13561);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.deye.activity.device.base.BaseActivity
    public void onCreate(Bundle bundle) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        super.onCreate(bundle);
        MxchipApplication.getInstance().addActivity(this);
        this.mDeviceId = getIntent().getStringExtra(StubApp.getString2(13055));
        this.mProductId = getIntent().getStringExtra(StubApp.getString2(13306));
        this.mDevinfoStr = getIntent().getStringExtra(StubApp.getString2(13454));
        this.mTemperature = getIntent().getStringExtra(StubApp.getString2(13567));
        this.mHumidity = getIntent().getStringExtra(StubApp.getString2(13568));
        this.mPlace = getIntent().getStringExtra(StubApp.getString2(13569));
        this.mWeatherInfoPosition = getIntent().getIntExtra(StubApp.getString2(6267), 11);
        this.mIsCombo = getIntent().getBooleanExtra(StubApp.getString2(13570), false);
        this.protocolVersion = getIntent().getStringExtra(StubApp.getString2(13571));
        this.mDeviceListBean = (DeviceListBean) new Gson().fromJson(this.mDevinfoStr, DeviceListBean.class);
    }

    @Override // com.deye.activity.device.base.BaseActivity
    protected List<String> getCurrentDeviceId() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mDeviceId);
        return arrayList;
    }

    protected boolean isUseComboProtocol() {
        String str = StubApp.getString2(13563) + this.protocolVersion;
        String string2 = StubApp.getString2(13564);
        LogUtil.d(string2, str);
        LogUtil.d(string2, StubApp.getString2(13565) + this.mIsCombo);
        return this.mIsCombo && StubApp.getString2(13566).equals(this.protocolVersion);
    }

    @Override // com.deye.activity.device.base.BaseActivity
    public void finishActivityOrRefreshUIForRemovedDevice() {
        finish();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void goDeviceErrorDetails(ErrorCodeMap<String, String> errorCodeMap) {
        Intent intent = new Intent((Context) this, (Class<?>) ErrorListInfoAty.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(this.mErrorCodeKey, errorCodeMap);
        intent.putExtra(this.mErrorCodeKey, bundle);
        startActivity(intent);
    }

    protected void goBack() {
        finish();
    }

    protected void goHumidityInfo(String str) {
        Intent intent = new Intent(StubApp.getOrigApplicationContext(getApplicationContext()), (Class<?>) HumidityInfoAty.class);
        intent.putExtra(StubApp.getString2(13491), str);
        startActivity(intent);
    }

    protected void goDeviceDetailsForResult() {
        Intent intent = new Intent(StubApp.getOrigApplicationContext(getApplicationContext()), (Class<?>) DeviceInfoActivity.class);
        intent.putExtra(StubApp.getString2(13055), this.mDeviceId);
        intent.putExtra(StubApp.getString2(13454), JSON.toJSONString(this.mDeviceListBean));
        intent.putExtra(StubApp.getString2(13562), true);
        startActivityForResult(intent, 0);
    }

    protected boolean switchToBool(String str) {
        return !StubApp.getString2(701).equals(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String switchToString(boolean z) {
        return z ? StubApp.getString2(2546) : StubApp.getString2(701);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setLayoutParams() {
        this.mRlRootView = (FrameLayout) getWindow().getDecorView().findViewById(R.id.content);
        findViewById(com.mxchipapp.R.id.actionbar_black).getLayoutParams();
        int iDip2px = DensityUtil.dip2px(this.mContext, 44.0f) + getStatusBarHeight();
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(com.mxchipapp.R.id.rl_device_loading);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
        layoutParams.topMargin += iDip2px;
        relativeLayout.setLayoutParams(layoutParams);
        LogUtil.d(StubApp.getString2(13572) + iDip2px);
        View viewInflate = LayoutInflater.from(this).inflate(com.mxchipapp.R.layout.water_tank_layout, (ViewGroup) null);
        this.mWaterTankLayout = viewInflate;
        if (this instanceof AirConditionerControlPanelUIAty) {
            ((TextView) viewInflate.findViewById(com.mxchipapp.R.id.tv_water_msg)).setText(com.mxchipapp.R.string.device_auto_shutdown);
        }
        this.mWaterPumpLayout = LayoutInflater.from(this).inflate(com.mxchipapp.R.layout.water_pump_layout, (ViewGroup) null);
        View viewInflate2 = LayoutInflater.from(this).inflate(com.mxchipapp.R.layout.no_net_layout, (ViewGroup) null);
        this.mNoNetTipLayout = viewInflate2;
        viewInflate2.findViewById(com.mxchipapp.R.id.tv_more).setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.base.PublicConstantAty.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PagerUtils.goOfflineGuidePage(PublicConstantAty.this.mContext, PublicConstantAty.this.mProductId);
            }
        });
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.topMargin = iDip2px;
        this.mWaterTankLayout.setLayoutParams(layoutParams2);
        this.mNoNetTipLayout.setLayoutParams(layoutParams2);
        this.mWaterPumpLayout.setLayoutParams(layoutParams2);
    }

    protected void dismissNoNetTipDialog() {
        if (this.mNoNetTipLayout == null) {
            setLayoutParams();
        }
        if (this.mRlRootView.indexOfChild(this.mNoNetTipLayout) > 0) {
            this.mRlRootView.removeView(this.mNoNetTipLayout);
        }
    }

    protected void showNoNetTipDialog() {
        dismissNoNetTipDialog();
        dismissWaterTankDialog();
        dismissWaterPumpDialog();
        if (this.mNoNetTipLayout == null) {
            setLayoutParams();
        }
        View view = this.mNoNetTipLayout;
        if (view != null) {
            this.mRlRootView.addView(view);
        }
    }

    protected void dismissWaterTankDialog() {
        if (this.mWaterTankLayout == null) {
            setLayoutParams();
        }
        if (this.mRlRootView.indexOfChild(this.mWaterTankLayout) > 0) {
            this.mRlRootView.removeView(this.mWaterTankLayout);
        }
    }

    protected void showWaterTankDialog() {
        dismissNoNetTipDialog();
        dismissWaterTankDialog();
        dismissWaterPumpDialog();
        if (this.mWaterTankLayout == null) {
            setLayoutParams();
        }
        View view = this.mWaterTankLayout;
        if (view != null) {
            this.mRlRootView.addView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showWaterPumpDialog() {
        dismissNoNetTipDialog();
        dismissWaterTankDialog();
        dismissWaterPumpDialog();
        if (this.mWaterPumpLayout == null) {
            setLayoutParams();
        }
        View view = this.mWaterPumpLayout;
        if (view != null) {
            ((ImageView) view.findViewById(com.mxchipapp.R.id.iv_pump)).setImageResource(com.mxchipapp.R.drawable.icon_water_pump_open);
            this.mRlRootView.addView(this.mWaterPumpLayout);
        }
        this.mWaterPumpLayout.findViewById(com.mxchipapp.R.id.tv_close).setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.base.PublicConstantAty.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                PublicConstantAty.this.dismissWaterPumpDialog();
            }
        });
    }

    protected void showAddWaterDialog() {
        if (Constants.isH7Product(this.mProductId)) {
            return;
        }
        dismissNoNetTipDialog();
        dismissWaterTankDialog();
        dismissWaterPumpDialog();
        if (this.mWaterPumpLayout == null) {
            setLayoutParams();
        }
        View view = this.mWaterPumpLayout;
        if (view != null) {
            ((ImageView) view.findViewById(com.mxchipapp.R.id.iv_pump)).setImageResource(com.mxchipapp.R.drawable.icon_add_water);
            this.mRlRootView.addView(this.mWaterPumpLayout);
        }
        this.mWaterPumpLayout.findViewById(com.mxchipapp.R.id.tv_close).setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.base.PublicConstantAty.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                PublicConstantAty.this.dismissAddWaterDialog();
                PublicConstantAty.this.finish();
            }
        });
    }

    protected void dismissAddWaterDialog() {
        if (this.mWaterPumpLayout == null) {
            setLayoutParams();
        }
        if (this.mRlRootView.indexOfChild(this.mWaterPumpLayout) > 0) {
            this.mRlRootView.removeView(this.mWaterPumpLayout);
        }
    }

    protected void dismissWaterPumpDialog() {
        if (this.mWaterPumpLayout == null) {
            setLayoutParams();
        }
        if (this.mRlRootView.indexOfChild(this.mWaterPumpLayout) > 0) {
            this.mRlRootView.removeView(this.mWaterPumpLayout);
        }
    }
}
