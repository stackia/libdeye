package com.deye.activity.config_net;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import com.alibaba.fastjson.JSON;
import com.deye.activity.device.base.BaseActivity;
import com.deye.adapter.MyDeviceListAdapter;
import com.deye.listener.OnItemClickListener;
import com.deye.utils.BaseUtils;
import com.google.gson.Gson;
import com.mxchipapp.R;
import com.mxchipapp.databinding.DeviceBindListBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import it.innove.Peripheral;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class DeviceListBindActivity extends BaseActivity implements View.OnClickListener {
    private static final int GET_DEVICE_LIST_FAIL = 200;
    private static final int GET_DEVICE_LIST_SUCCESS = 100;
    private DeviceBindListBinding mDeviceBindListBinding;
    private ArrayList<DeviceListBean> mDeviceListBeanList;
    private MyDeviceListAdapter mRyDeviceListBindAdapter;
    private boolean mIsAddDeviceFlag = false;
    private List<String> deviceIdList = new ArrayList();
    private Handler handler = new Handler() { // from class: com.deye.activity.config_net.DeviceListBindActivity.1
        /* JADX WARN: Type inference failed for: r1v1, types: [android.content.Context, com.deye.activity.config_net.DeviceListBindActivity] */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 100) {
                if (i != 200) {
                    return;
                }
                DeviceListBindActivity.this.stopWaiting();
                BaseUtils.showShortToast(StubApp.getOrigApplicationContext(DeviceListBindActivity.this.getApplicationContext()), DeviceListBindActivity.this.getString(R.string.device_list_fetch_failed));
                return;
            }
            DeviceListBindActivity.this.stopWaiting();
            if (DeviceListBindActivity.this.mDeviceListBeanList == null || DeviceListBindActivity.this.mDeviceListBeanList.size() < 1) {
                DeviceListBindActivity.this.mDeviceBindListBinding.llNoDevice.setVisibility(0);
                DeviceListBindActivity.this.mDeviceBindListBinding.ryDeviceList.setVisibility(8);
                return;
            }
            DeviceListBindActivity.this.mDeviceBindListBinding.llNoDevice.setVisibility(8);
            DeviceListBindActivity.this.mDeviceBindListBinding.ryDeviceList.setVisibility(0);
            DeviceListBindActivity deviceListBindActivity = DeviceListBindActivity.this;
            ?? r1 = DeviceListBindActivity.this;
            deviceListBindActivity.mRyDeviceListBindAdapter = new MyDeviceListAdapter(r1, ((DeviceListBindActivity) r1).mDeviceListBeanList);
            DeviceListBindActivity.this.mDeviceBindListBinding.ryDeviceList.setAdapter(DeviceListBindActivity.this.mRyDeviceListBindAdapter);
            DeviceListBindActivity.this.mRyDeviceListBindAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.deye.activity.config_net.DeviceListBindActivity.1.1
                @Override // com.deye.listener.OnItemClickListener
                public void onItemClick(int i2) {
                    DeviceListBindActivity.this.mIsAddDeviceFlag = false;
                    Intent intent = new Intent((Context) DeviceListBindActivity.this, (Class<?>) DeviceInfoActivity.class);
                    intent.putExtra(StubApp.getString2(13055), ((DeviceListBean) DeviceListBindActivity.this.mDeviceListBeanList.get(i2)).getDevice_id());
                    intent.putExtra(StubApp.getString2(13454), new Gson().toJson(DeviceListBindActivity.this.mDeviceListBeanList.get(i2)));
                    DeviceListBindActivity.this.startActivity(intent);
                }
            });
        }
    };

    static {
        StubApp.interface11(13946);
    }

    private native void initData();

    private native void initView();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void finishActivityOrRefreshUIForRemovedDevice();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native List<String> getCurrentDeviceId();

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onResume();

    /* renamed from: com.deye.activity.config_net.DeviceListBindActivity$2, reason: invalid class name */
    class AnonymousClass2 implements FogCallBack {
        AnonymousClass2() {
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onSuccess(String str) {
            Log.d(StubApp.getString2(Peripheral.GATT_AUTH_FAIL), str);
            String string = JSON.parseObject(str).getString(StubApp.getString2(100));
            Log.d(StubApp.getString2(13460), string);
            if (StubApp.getString2(13461).equals(string) || "".equals(string) || string == null) {
                return;
            }
            try {
                DeviceListBindActivity.this.mDeviceListBeanList = (ArrayList) JSON.parseArray(string, DeviceListBean.class);
                Iterator it2 = DeviceListBindActivity.this.mDeviceListBeanList.iterator();
                while (it2.hasNext()) {
                    DeviceListBindActivity.this.deviceIdList.add(((DeviceListBean) it2.next()).getDevice_id());
                }
                BaseUtils.sendMessage(DeviceListBindActivity.this.handler, 100, "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onFailure(int i, String str) {
            BaseUtils.sendMessage(DeviceListBindActivity.this.handler, 200, "");
        }
    }
}
