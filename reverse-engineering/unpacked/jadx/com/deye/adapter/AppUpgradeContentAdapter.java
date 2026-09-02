package com.deye.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.mxchipapp.R;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class AppUpgradeContentAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Activity mActivity;
    String[] mTipArray;
    int maxHeight;
    RecyclerView recyclerView;

    public AppUpgradeContentAdapter(Activity activity, String[] strArr, RecyclerView recyclerView) {
        this.maxHeight = 0;
        this.mActivity = activity;
        this.recyclerView = recyclerView;
        this.maxHeight = activity.getWindowManager().getDefaultDisplay().getHeight() / 4;
        this.mTipArray = strArr;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.mActivity).inflate(R.layout.app_update_tip_item, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        String str = this.mTipArray[i];
        myViewHolder.tv_num.setText(String.valueOf(i + 1) + StubApp.getString2(1382));
        myViewHolder.tv_tip.setText(str);
        int i2 = this.recyclerView.getLayoutParams().height;
        if (i > 2 || i2 > this.maxHeight) {
            int height = this.mActivity.getWindowManager().getDefaultDisplay().getHeight();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.height = height / 4;
            this.recyclerView.setLayoutParams(layoutParams);
        }
    }

    public int getItemCount() {
        return this.mTipArray.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_num;
        TextView tv_tip;

        public MyViewHolder(View view) {
            super(view);
            this.tv_num = (TextView) view.findViewById(R.id.tv_num);
            this.tv_tip = (TextView) view.findViewById(R.id.tv_tip);
        }
    }
}
