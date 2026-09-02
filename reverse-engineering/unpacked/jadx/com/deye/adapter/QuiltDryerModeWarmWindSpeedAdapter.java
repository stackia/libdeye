package com.deye.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.deye.entity.control_panel.quilt_dryer.func.ModeWarmWindSpeedBean;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class QuiltDryerModeWarmWindSpeedAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private IOnClickItemListener mIOnClickItemListener;
    private String[] mSpeedIconNormal;
    private String[] mSpeedIconSelected;
    private String[] mSpeedName;
    private String[] mSpeedValue;
    private int mModeSelectedItem = -2;
    private int mTwoHourFlag = 0;
    private boolean mIsEnable = true;

    public interface IOnClickItemListener {
        void onOnClickItem(String str);
    }

    public void setEnable(boolean z) {
        this.mIsEnable = z;
        notifyDataSetChanged();
    }

    public void setSelectedItem(int i) {
        this.mModeSelectedItem = i;
        notifyDataSetChanged();
    }

    public void setSelectedItem(String str) {
        this.mModeSelectedItem = -2;
        int i = 0;
        if (TextUtils.equals(StubApp.getString2(8095), str) || TextUtils.equals(StubApp.getString2(8092), str)) {
            this.mTwoHourFlag = Integer.parseInt(StubApp.getString2(7778));
        } else {
            this.mTwoHourFlag = 0;
        }
        String strValueOf = String.valueOf(Integer.parseInt(str) - this.mTwoHourFlag);
        while (true) {
            String[] strArr = this.mSpeedValue;
            if (i < strArr.length) {
                if (strArr[i].equals(strValueOf)) {
                    this.mModeSelectedItem = i;
                }
                i++;
            } else {
                notifyDataSetChanged();
                return;
            }
        }
    }

    public QuiltDryerModeWarmWindSpeedAdapter(Context context, ModeWarmWindSpeedBean modeWarmWindSpeedBean) {
        this.context = context;
        this.mSpeedName = modeWarmWindSpeedBean.getName();
        this.mSpeedValue = modeWarmWindSpeedBean.getValue();
        this.mSpeedIconNormal = modeWarmWindSpeedBean.getIconNormal();
        this.mSpeedIconSelected = modeWarmWindSpeedBean.getIconSelected();
    }

    public void setOnClickItemListener(IOnClickItemListener iOnClickItemListener) {
        this.mIOnClickItemListener = iOnClickItemListener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.quilt_dryer_mode_wram_ry_wind_speed_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (!this.mIsEnable) {
            viewHolder.tv_cover_view.setVisibility(0);
        } else {
            viewHolder.tv_cover_view.setVisibility(8);
        }
        viewHolder.tv_wind_speed.setText(this.mSpeedName[i]);
        LogUtil.d(StubApp.getString2(13819) + this.mModeSelectedItem);
        if (i == this.mModeSelectedItem) {
            LogUtil.d(StubApp.getString2(13797) + viewHolder.cb_wind_speed.isChecked());
            viewHolder.cb_wind_speed.setChecked(true);
            viewHolder.tv_wind_speed.setSelected(true);
            viewHolder.cb_wind_speed.setBackground(getDrawable(this.mSpeedIconSelected[i]));
        } else {
            viewHolder.cb_wind_speed.setChecked(false);
            viewHolder.tv_wind_speed.setSelected(false);
            viewHolder.cb_wind_speed.setBackground(getDrawable(this.mSpeedIconNormal[i]));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.QuiltDryerModeWarmWindSpeedAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                QuiltDryerModeWarmWindSpeedAdapter.this.mModeSelectedItem = i;
                QuiltDryerModeWarmWindSpeedAdapter.this.notifyDataSetChanged();
                if (QuiltDryerModeWarmWindSpeedAdapter.this.mIOnClickItemListener != null) {
                    QuiltDryerModeWarmWindSpeedAdapter.this.mIOnClickItemListener.onOnClickItem(String.valueOf(Integer.parseInt(QuiltDryerModeWarmWindSpeedAdapter.this.mSpeedValue[i]) + QuiltDryerModeWarmWindSpeedAdapter.this.mTwoHourFlag));
                }
            }
        });
    }

    public int getItemCount() {
        return this.mSpeedName.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cb_wind_speed;
        private TextView tv_cover_view;
        private TextView tv_wind_speed;

        public ViewHolder(View view) {
            super(view);
            setIsRecyclable(false);
            this.tv_cover_view = (TextView) view.findViewById(R.id.tv_cover_view);
            this.tv_wind_speed = (TextView) view.findViewById(R.id.tv_wind_speed);
            this.cb_wind_speed = (CheckBox) view.findViewById(R.id.cb_wind_speed);
        }
    }

    private Drawable getDrawable(String str) throws IOException {
        InputStream inputStreamOpen;
        try {
            inputStreamOpen = this.context.getAssets().open(str);
        } catch (IOException e) {
            e.printStackTrace();
            inputStreamOpen = null;
        }
        return Drawable.createFromStream(inputStreamOpen, null);
    }
}
