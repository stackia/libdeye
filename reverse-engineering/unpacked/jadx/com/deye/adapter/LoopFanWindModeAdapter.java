package com.deye.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.deye.entity.control_panel.dehumidifier.func.ModeBean;
import com.mxchipapp.R;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class LoopFanWindModeAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mCtx;
    private IOnClickItemListener mIOnClickItemListener;
    private String[] mSpeedIconNormal;
    private String[] mSpeedIconSelected;
    private String[] mSpeedName;
    private String[] mSpeedValue;
    private int mModeSelectedItem = -2;
    private boolean mIsEnable = true;

    public interface IOnClickItemListener {
        void onOnClickItem(int i);
    }

    public void setEnable(boolean z) {
        this.mIsEnable = z;
    }

    public void setSelectedItem(int i) {
        this.mModeSelectedItem = i;
        notifyDataSetChanged();
    }

    public void setSelectedItem(String str) {
        this.mModeSelectedItem = -2;
        int i = 0;
        while (true) {
            String[] strArr = this.mSpeedValue;
            if (i < strArr.length) {
                if (strArr[i].equals(str)) {
                    this.mModeSelectedItem = i;
                }
                i++;
            } else {
                notifyDataSetChanged();
                return;
            }
        }
    }

    public LoopFanWindModeAdapter(Context context, ModeBean modeBean) {
        this.mCtx = context;
        this.mSpeedName = modeBean.getName();
        this.mSpeedValue = modeBean.getValue();
        this.mSpeedIconNormal = modeBean.getIconNormal();
        this.mSpeedIconSelected = modeBean.getIconSelected();
    }

    public void setOnClickItemListener(IOnClickItemListener iOnClickItemListener) {
        this.mIOnClickItemListener = iOnClickItemListener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.mCtx).inflate(R.layout.loop_fan_ry_wind_mode_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final int absoluteAdapterPosition = viewHolder.getAbsoluteAdapterPosition();
        if (absoluteAdapterPosition == this.mModeSelectedItem) {
            viewHolder.rl_ry_mode_root.setSelected(true);
            if (!"".equals(this.mSpeedIconSelected[absoluteAdapterPosition])) {
                viewHolder.imageView.setImageDrawable(getDrawable(this.mSpeedIconSelected[absoluteAdapterPosition]));
            }
        } else {
            viewHolder.rl_ry_mode_root.setSelected(false);
            if (!"".equals(this.mSpeedIconNormal[absoluteAdapterPosition])) {
                viewHolder.imageView.setImageDrawable(getDrawable(this.mSpeedIconNormal[absoluteAdapterPosition]));
            }
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewHolder.imageView.getLayoutParams();
        layoutParams.bottomMargin = 0;
        layoutParams.topMargin = 0;
        if (absoluteAdapterPosition == 1 || absoluteAdapterPosition == 7) {
            layoutParams.width = (int) this.mCtx.getResources().getDimension(2131166096);
            layoutParams.height = (int) this.mCtx.getResources().getDimension(2131166943);
        } else if (absoluteAdapterPosition == 3 || absoluteAdapterPosition == 5) {
            layoutParams.width = (int) this.mCtx.getResources().getDimension(2131166411);
            layoutParams.height = (int) this.mCtx.getResources().getDimension(2131167068);
        } else if (absoluteAdapterPosition == 4) {
            layoutParams.width = (int) this.mCtx.getResources().getDimension(2131166422);
            layoutParams.height = (int) this.mCtx.getResources().getDimension(2131166943);
        } else {
            layoutParams.width = (int) this.mCtx.getResources().getDimension(2131166073);
            layoutParams.height = (int) this.mCtx.getResources().getDimension(2131166499);
        }
        if (absoluteAdapterPosition == 0 || absoluteAdapterPosition == 3 || absoluteAdapterPosition == 6) {
            layoutParams.addRule(21);
        } else if (absoluteAdapterPosition != 1 && absoluteAdapterPosition != 4 && absoluteAdapterPosition != 7) {
            layoutParams.addRule(20);
        } else if (absoluteAdapterPosition == 1) {
            layoutParams.addRule(14);
            layoutParams.addRule(12);
            layoutParams.bottomMargin = -10;
        } else if (absoluteAdapterPosition == 7) {
            layoutParams.addRule(14);
            layoutParams.addRule(10);
        } else {
            layoutParams.addRule(13);
            layoutParams.topMargin = -10;
        }
        viewHolder.imageView.setLayoutParams(layoutParams);
        try {
            if (-1 != Integer.parseInt(this.mSpeedValue[absoluteAdapterPosition])) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.LoopFanWindModeAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LoopFanWindModeAdapter.this.mModeSelectedItem = absoluteAdapterPosition;
                        LoopFanWindModeAdapter.this.notifyDataSetChanged();
                        if (LoopFanWindModeAdapter.this.mIOnClickItemListener != null) {
                            LoopFanWindModeAdapter.this.mIOnClickItemListener.onOnClickItem(absoluteAdapterPosition);
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getItemCount() {
        return this.mSpeedName.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private RelativeLayout rl_ry_mode_root;

        public ViewHolder(View view) {
            super(view);
            setIsRecyclable(false);
            this.rl_ry_mode_root = (RelativeLayout) view.findViewById(R.id.rl_ry_mode_root);
            this.imageView = (ImageView) view.findViewById(R.id.image_view);
        }
    }

    private Drawable getDrawable(String str) throws IOException {
        InputStream inputStreamOpen;
        try {
            inputStreamOpen = this.mCtx.getAssets().open(str);
        } catch (IOException e) {
            e.printStackTrace();
            inputStreamOpen = null;
        }
        return Drawable.createFromStream(inputStreamOpen, null);
    }
}
