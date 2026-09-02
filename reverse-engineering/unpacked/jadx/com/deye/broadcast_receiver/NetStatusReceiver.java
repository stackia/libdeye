package com.deye.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class NetStatusReceiver extends BroadcastReceiver {
    public static final int NETSTATUS_INAVAILABLE = 0;
    public static final int NETSTATUS_MOBILE = 2;
    public static final int NETSTATUS_WIFI = 1;
    public static int netStatus;
    public static boolean updateSuccess;
    private INetStatusListener mINetStatusListener;

    public interface INetStatusListener {
        void getNetState(int i);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(StubApp.getString2(790));
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            if (networkInfo != null && (networkInfo.isConnected() || networkInfo.isConnectedOrConnecting())) {
                netStatus = 2;
            } else if ((networkInfo2 != null && networkInfo2.isConnected()) || (networkInfo2 != null && networkInfo2.isConnectedOrConnecting())) {
                netStatus = 1;
            } else {
                netStatus = 0;
            }
        } else if (activeNetworkInfo.isConnected() || activeNetworkInfo.isConnectedOrConnecting()) {
            if (networkInfo != null && (networkInfo.isConnected() || networkInfo.isConnectedOrConnecting())) {
                netStatus = 2;
            } else {
                netStatus = 1;
            }
        } else {
            netStatus = 0;
        }
        INetStatusListener iNetStatusListener = this.mINetStatusListener;
        if (iNetStatusListener != null) {
            iNetStatusListener.getNetState(netStatus);
        }
    }

    public void setNetStateListener(INetStatusListener iNetStatusListener) {
        this.mINetStatusListener = iNetStatusListener;
    }
}
