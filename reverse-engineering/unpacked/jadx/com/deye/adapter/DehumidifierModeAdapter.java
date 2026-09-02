package com.deye.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.deye.entity.control_panel.dehumidifier.func.ModeBean;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class DehumidifierModeAdapter extends RecyclerView.Adapter<ViewHolder> {
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

    public DehumidifierModeAdapter(Context context, ModeBean modeBean) {
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
        return new ViewHolder(LayoutInflater.from(this.mCtx).inflate(R.layout.ry_mode_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (!this.mIsEnable) {
            viewHolder.tv_cover_view.setVisibility(0);
        } else {
            viewHolder.tv_cover_view.setVisibility(8);
        }
        viewHolder.tv_wind_speed.setText(this.mSpeedName[i]);
        if (i == this.mModeSelectedItem) {
            LogUtil.d(StubApp.getString2(13797) + viewHolder.cb_wind_speed.isChecked());
            viewHolder.rl_ry_mode_root.setSelected(true);
            viewHolder.rl_ry_mode_root.setScaleX(1.3f);
            viewHolder.rl_ry_mode_root.setScaleY(1.3f);
            viewHolder.cb_wind_speed.setChecked(true);
            viewHolder.tv_wind_speed.setSelected(true);
            viewHolder.cb_wind_speed.setBackground(getDrawable(this.mSpeedIconSelected[i]));
        } else {
            viewHolder.rl_ry_mode_root.setSelected(false);
            viewHolder.rl_ry_mode_root.setScaleX(1.0f);
            viewHolder.rl_ry_mode_root.setScaleY(1.0f);
            viewHolder.cb_wind_speed.setChecked(false);
            viewHolder.tv_wind_speed.setSelected(false);
            viewHolder.cb_wind_speed.setBackground(getDrawable(this.mSpeedIconNormal[i]));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.DehumidifierModeAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DehumidifierModeAdapter.this.mModeSelectedItem = i;
                DehumidifierModeAdapter.this.notifyDataSetChanged();
                if (DehumidifierModeAdapter.this.mIOnClickItemListener != null) {
                    DehumidifierModeAdapter.this.mIOnClickItemListener.onOnClickItem(i);
                }
            }
        });
    }

    public int getItemCount() {
        return this.mSpeedName.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cb_wind_speed;
        private RelativeLayout rl_ry_mode_root;
        private TextView tv_cover_view;
        private TextView tv_wind_speed;

        public ViewHolder(View view) {
            super(view);
            setIsRecyclable(false);
            this.rl_ry_mode_root = (RelativeLayout) view.findViewById(R.id.rl_ry_mode_root);
            this.tv_cover_view = (TextView) view.findViewById(R.id.tv_cover_view);
            this.tv_wind_speed = (TextView) view.findViewById(R.id.tv_wind_speed);
            this.cb_wind_speed = (CheckBox) view.findViewById(R.id.cb_wind_speed);
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
