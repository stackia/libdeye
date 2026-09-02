package com.deye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.deye.entity.ProductListBean;
import com.deye.listener.OnItemClickListener;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import java.util.ArrayList;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class RyBleSearchDeviceListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private ArrayList<ProductListBean.Pdata> mPdataArrayList;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RyBleSearchDeviceListAdapter(Context context, ArrayList<ProductListBean.Pdata> arrayList) {
        this.mContext = context;
        this.mPdataArrayList = arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.ble_search_device_list_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (i == this.mPdataArrayList.size() - 1) {
            viewHolder.v_driver_line.setVisibility(8);
        }
        if (BaseUtils.isNotNull(this.mPdataArrayList.get(i).getPicture())) {
            Glide.with(this.mContext).load(this.mPdataArrayList.get(i).getPicture()).apply((BaseRequestOptions<?>) new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)).into(viewHolder.iv_device_icon);
        }
        viewHolder.rl_device_list_root.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.RyBleSearchDeviceListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RyBleSearchDeviceListAdapter.this.mOnItemClickListener != null) {
                    RyBleSearchDeviceListAdapter.this.mOnItemClickListener.onItemClick(i);
                }
            }
        });
        viewHolder.tv_device_name.setText(this.mPdataArrayList.get(i).getPname());
    }

    public int getItemCount() {
        return this.mPdataArrayList.size();
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
