package com.deye.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.R;
import java.util.LinkedList;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class HumidityInfoAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private BaseActivity mActivity;
    private LinkedList<String> mTipTextList;

    public HumidityInfoAdapter(BaseActivity baseActivity, LinkedList<String> linkedList) {
        new LinkedList();
        this.mActivity = baseActivity;
        this.mTipTextList = linkedList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.mActivity).inflate(R.layout.item_humidity_info_layout, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.tvTipText.setText(this.mTipTextList.get(i));
        myViewHolder.vLine.setVisibility(i == getItemCount() + (-1) ? 8 : 0);
    }

    public int getItemCount() {
        return this.mTipTextList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTipText;
        View vLine;

        public MyViewHolder(View view) {
            super(view);
            this.tvTipText = (TextView) view.findViewById(R.id.tv_tip_text);
            this.vLine = view.findViewById(R.id.v_line);
        }
    }
}
