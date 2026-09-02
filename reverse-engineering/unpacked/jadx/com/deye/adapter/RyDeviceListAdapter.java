package com.deye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.deye.entity.ProductBean;
import com.deye.listener.OnItemClickListener;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import java.util.ArrayList;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class RyDeviceListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private ArrayList<ProductBean> mDeviceListBeanList;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RyDeviceListAdapter(Context context, ArrayList<ProductBean> arrayList) {
        this.mContext = context;
        this.mDeviceListBeanList = arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.device_list_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (i == this.mDeviceListBeanList.size() - 1) {
            viewHolder.v_driver_line.setVisibility(8);
        }
        if (BaseUtils.isNotNull(Integer.valueOf(this.mDeviceListBeanList.get(i).resID))) {
            viewHolder.iv_device_icon.setBackgroundResource(this.mDeviceListBeanList.get(i).resID);
        }
        viewHolder.rl_device_list_root.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.RyDeviceListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RyDeviceListAdapter.this.mOnItemClickListener != null) {
                    RyDeviceListAdapter.this.mOnItemClickListener.onItemClick(i);
                }
            }
        });
        viewHolder.tv_device_name.setText(this.mDeviceListBeanList.get(i).name);
    }

    public int getItemCount() {
        return this.mDeviceListBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_device_icon;
        RelativeLayout rl_device_list_root;
        TextView tv_device_name;
        TextView tv_status;
        View v_driver_line;

        public ViewHolder(View view) {
            super(view);
            setIsRecyclable(false);
            this.iv_device_icon = (ImageView) view.findViewById(R.id.iv_device_icon);
            this.tv_device_name = (TextView) view.findViewById(R.id.tv_device_name);
            this.rl_device_list_root = (RelativeLayout) view.findViewById(R.id.rl_device_list_root);
            this.v_driver_line = view.findViewById(R.id.v_driver_line);
        }
    }
}
