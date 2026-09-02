package com.deye.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.deye.entity.control_panel.dehumidifier.func.SpeedBean;
import com.deye.views.WindSpeedView;
import com.mxchipapp.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class WindSpeedAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private int mColorMode;
    private IOnClickItemListener mIOnClickItemListener;
    private boolean mIsEnable;
    private int mModeSelectedItem;
    private String[] mSpeedIconNormal;
    private String[] mSpeedIconSelected;
    private String[] mSpeedName;
    private String[] mSpeedValue;

    public interface IOnClickItemListener {
        void onOnClickItem(int i);
    }

    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((ViewHolder) viewHolder, i, (List<Object>) list);
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

    public void setSelectedItemAndStyle(String str, int i) {
        this.mModeSelectedItem = -2;
        int i2 = 0;
        while (true) {
            String[] strArr = this.mSpeedValue;
            if (i2 < strArr.length) {
                if (strArr[i2].equals(str)) {
                    this.mModeSelectedItem = i2;
                }
                i2++;
            } else {
                this.mColorMode = i;
                notifyDataSetChanged();
                return;
            }
        }
    }

    public WindSpeedAdapter(Context context, SpeedBean speedBean) {
        this.mModeSelectedItem = -2;
        this.mIsEnable = true;
        this.mColorMode = 0;
        this.context = context;
        this.mSpeedName = speedBean.getName();
        this.mSpeedValue = speedBean.getValue();
        this.mSpeedIconNormal = speedBean.getIconNormal();
        this.mSpeedIconSelected = speedBean.getIconSelected();
    }

    public WindSpeedAdapter(Context context, SpeedBean speedBean, int i) {
        this.mModeSelectedItem = -2;
        this.mIsEnable = true;
        this.mColorMode = 0;
        this.context = context;
        this.mSpeedName = speedBean.getName();
        this.mSpeedValue = speedBean.getValue();
        this.mSpeedIconNormal = speedBean.getIconNormal();
        this.mSpeedIconSelected = speedBean.getIconSelected();
        this.mColorMode = i;
    }

    public void updateStyleByMode(int i) {
        this.mColorMode = i;
        notifyDataSetChanged();
    }

    public void setOnClickItemListener(IOnClickItemListener iOnClickItemListener) {
        this.mIOnClickItemListener = iOnClickItemListener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.ry_wind_speed_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i, List<Object> list) {
        super.onBindViewHolder(viewHolder, i, list);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
        viewHolder.tv_wind_speed.setText(this.mSpeedName[bindingAdapterPosition]);
        if (bindingAdapterPosition == this.mModeSelectedItem) {
            viewHolder.cb_wind_speed.setChecked(true);
            viewHolder.tv_wind_speed.setSelected(true);
            viewHolder.tv_wind_speed.setTypeface(null, 1);
            viewHolder.cb_wind_speed.setBackground(getDrawable(this.mSpeedIconNormal[bindingAdapterPosition]));
        } else {
            viewHolder.cb_wind_speed.setChecked(false);
            viewHolder.tv_wind_speed.setSelected(false);
            viewHolder.tv_wind_speed.setTypeface(null, 0);
            viewHolder.cb_wind_speed.setBackground(getDrawable(this.mSpeedIconNormal[bindingAdapterPosition]));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.WindSpeedAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
    }

    private int getStyleIndex(int i) {
        if (i == 0) {
            return 0;
        }
        return i == getItemCount() - 1 ? 2 : 1;
    }

    public int getItemCount() {
        return this.mSpeedName.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cb_wind_speed;
        private TextView tv_cover_view;
        private TextView tv_wind_speed;
        private WindSpeedView windSpeedView;

        public ViewHolder(View view) {
            super(view);
            setIsRecyclable(false);
            this.tv_cover_view = (TextView) view.findViewById(R.id.tv_cover_view);
            this.tv_wind_speed = (TextView) view.findViewById(R.id.tv_wind_speed);
            this.cb_wind_speed = (CheckBox) view.findViewById(R.id.cb_wind_speed);
            this.windSpeedView = (WindSpeedView) view.findViewById(R.id.wind_spped_view);
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
