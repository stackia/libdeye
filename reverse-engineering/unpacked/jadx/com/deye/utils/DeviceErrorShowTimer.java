package com.deye.utils;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.stub.StubApp;
import java.util.ArrayList;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class DeviceErrorShowTimer extends CountDownTimer {
    private ImageView error_icon;
    private ArrayList<String> list;
    private volatile boolean mIsRunning;
    private TextView textView;
    private int time;

    @Override // android.os.CountDownTimer
    public void onFinish() {
    }

    public DeviceErrorShowTimer(ImageView imageView, TextView textView, long j, long j2, ArrayList<String> arrayList) {
        super(j, j2);
        this.time = 0;
        this.mIsRunning = false;
        Log.d(StubApp.getString2(14308), StubApp.getString2(14307) + j);
        this.textView = textView;
        this.error_icon = imageView;
        this.list = arrayList;
    }

    @Override // android.os.CountDownTimer
    public void onTick(long j) {
        if (BaseUtils.isNotNull(this.list) && this.list.size() > 0) {
            this.error_icon.setVisibility(0);
            if (this.time >= this.list.size()) {
                this.time = 0;
            }
            this.textView.setText(this.list.get(this.time));
            this.time++;
            return;
        }
        this.error_icon.setVisibility(8);
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }

    public void notifyList(ArrayList<String> arrayList) {
        this.list.clear();
        this.list.addAll(arrayList);
    }
}
