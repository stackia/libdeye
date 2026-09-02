package com.deye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.mxchipapp.R;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ProductTypeAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mCtx;
    private IOnClickItemListener mIOnClickItemListener;
    private List<String> mProductTypeList;
    private int mSelectedItem = 0;

    public interface IOnClickItemListener {
        void onOnClickItem(int i);
    }

    public int getSelectedItem() {
        return this.mSelectedItem;
    }

    public void setSelectedItem(int i) {
        this.mSelectedItem = i;
        notifyDataSetChanged();
    }

    public ProductTypeAdapter(Context context, List<String> list) {
        this.mCtx = context;
        this.mProductTypeList = list;
    }

    public void setOnClickItemListener(IOnClickItemListener iOnClickItemListener) {
        this.mIOnClickItemListener = iOnClickItemListener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.mCtx).inflate(R.layout.ry_product_type_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.tv_text.setText(this.mProductTypeList.get(i));
        if (i == this.mSelectedItem) {
            viewHolder.ll_root.setBackgroundColor(this.mCtx.getResources().getColor(R.color.color_F5F6F9));
            viewHolder.tv_text.setTextColor(this.mCtx.getResources().getColor(R.color.text_selected_color));
        } else {
            viewHolder.ll_root.setBackgroundColor(this.mCtx.getResources().getColor(R.color.color_white));
            viewHolder.tv_text.setTextColor(this.mCtx.getResources().getColor(R.color.color_text_3F3F3F));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.ProductTypeAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProductTypeAdapter.this.mSelectedItem = i;
                if (ProductTypeAdapter.this.mIOnClickItemListener != null) {
                    ProductTypeAdapter.this.mIOnClickItemListener.onOnClickItem(i);
                }
                ProductTypeAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public int getItemCount() {
        return this.mProductTypeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout ll_root;
        private TextView tv_text;

        public ViewHolder(View view) {
            super(view);
            setIsRecyclable(false);
            this.ll_root = (LinearLayout) view.findViewById(R.id.ll_root);
            this.tv_text = (TextView) view.findViewById(R.id.tv_text);
        }
    }
}
