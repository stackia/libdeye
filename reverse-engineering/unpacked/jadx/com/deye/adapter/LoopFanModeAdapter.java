package com.deye.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.deye.entity.control_panel.dehumidifier.func.ModeBean;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class LoopFanModeAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mCtx;
    private IOnClickItemListener mIOnClickItemListener;
    private ModeBean mModeBean;
    private Map<String, Boolean> mIsGifShowing = new HashMap();
    private boolean mIsEnable = true;

    public interface IOnClickItemListener {
        void onOnClickItem(ModeBean modeBean, int i);
    }

    public void setEnable(boolean z) {
        this.mIsEnable = z;
    }

    public void setSelectedItem(int i) {
        notifyDataSetChanged();
    }

    public void setSelectedItem(ModeBean modeBean) {
        this.mModeBean = modeBean;
        notifyDataSetChanged();
    }

    public LoopFanModeAdapter(Context context, ModeBean modeBean) {
        this.mCtx = context;
        this.mModeBean = modeBean;
    }

    public void setOnClickItemListener(IOnClickItemListener iOnClickItemListener) {
        this.mIOnClickItemListener = iOnClickItemListener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.mCtx).inflate(R.layout.loop_fan_ry_mode_item, viewGroup, false));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        int i2;
        boolean z = this.mIsEnable;
        String string2 = StubApp.getString2(13805);
        String string22 = StubApp.getString2(13806);
        if (z) {
            if (StubApp.getString2(13807).equals(this.mModeBean.getError()[i]) || string22.equals(this.mModeBean.getError()[i]) || string2.equals(this.mModeBean.getError()[i])) {
                viewHolder.tv_cover_view.setVisibility(0);
                LogUtil.d(StubApp.getString2(13809));
            } else {
                LogUtil.d(StubApp.getString2(13808));
                viewHolder.tv_cover_view.setVisibility(8);
            }
        }
        viewHolder.tv_wind_speed.setText(this.mModeBean.getName()[i]);
        boolean zEquals = StubApp.getString2(7693).equals(this.mModeBean.getError()[i]);
        String string23 = StubApp.getString2(4590);
        if (zEquals || string22.equals(this.mModeBean.getError()[i])) {
            viewHolder.rl_ry_mode_root.setSelected(false);
            viewHolder.tv_wind_speed.setSelected(false);
            viewHolder.iv_wind_speed.setBackground(getDrawable(this.mModeBean.getIconError()[i]));
            viewHolder.tv_loop_fan_error.setVisibility(0);
            LogUtil.d(StubApp.getString2(13816) + i + string23 + this.mModeBean.getValue()[i]);
        } else {
            if (StubApp.getString2(8483).equals(this.mModeBean.getError()[i]) || string2.equals(this.mModeBean.getError()[i])) {
                viewHolder.rl_ry_mode_root.setSelected(false);
                viewHolder.tv_wind_speed.setSelected(false);
                viewHolder.tv_loop_fan_error.setVisibility(0);
                LogUtil.d(StubApp.getString2(13811) + this.mIsGifShowing.get(String.valueOf(i)));
                if (StubApp.getString2(13812).equals(this.mModeBean.getKey()[i])) {
                    i2 = R.drawable.humidification_error_gif;
                } else {
                    if (StubApp.getString2(13813).equals(this.mModeBean.getKey()[i])) {
                        i2 = 2131231084;
                    } else {
                        if (StubApp.getString2(13814).equals(this.mModeBean.getKey()[i])) {
                            i2 = R.drawable.deodorization_error_gif;
                        }
                    }
                }
                LogUtil.d(StubApp.getString2(13815) + i + string23 + this.mModeBean.getValue()[i]);
                showGifToImageView(viewHolder.iv_wind_speed, i2);
            } else {
                if (StubApp.getString2(2546).equals(this.mModeBean.getValue()[i])) {
                    viewHolder.rl_ry_mode_root.setSelected(true);
                    viewHolder.tv_wind_speed.setSelected(true);
                    viewHolder.iv_wind_speed.setBackground(getDrawable(this.mModeBean.getIconSelected()[i]));
                    viewHolder.tv_loop_fan_error.setVisibility(8);
                } else {
                    if (StubApp.getString2(701).equals(this.mModeBean.getValue()[i])) {
                        viewHolder.rl_ry_mode_root.setSelected(false);
                        viewHolder.tv_wind_speed.setSelected(false);
                        viewHolder.iv_wind_speed.setBackground(getDrawable(this.mModeBean.getIconNormal()[i]));
                        viewHolder.tv_loop_fan_error.setVisibility(8);
                    }
                }
                LogUtil.d(StubApp.getString2(13810) + i + string23 + this.mModeBean.getValue()[i]);
                showGifToImageView(viewHolder.iv_wind_speed, 0);
            }
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.LoopFanModeAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str = LoopFanModeAdapter.this.mModeBean.getValue()[i];
                String string24 = StubApp.getString2(2546);
                if (string24.equals(str)) {
                    LoopFanModeAdapter.this.mModeBean.getValue()[i] = StubApp.getString2(701);
                } else {
                    LoopFanModeAdapter.this.mModeBean.getValue()[i] = string24;
                }
                LoopFanModeAdapter.this.notifyDataSetChanged();
                if (LoopFanModeAdapter.this.mIOnClickItemListener != null) {
                    LoopFanModeAdapter.this.mIOnClickItemListener.onOnClickItem(LoopFanModeAdapter.this.mModeBean, i);
                }
            }
        });
    }

    private void showGifToImageView(ImageView imageView, int i) {
        Glide.with(this.mCtx).load(Integer.valueOf(i)).listener(new RequestListener<Drawable>() { // from class: com.deye.adapter.LoopFanModeAdapter.2
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                return false;
            }
        }).apply((BaseRequestOptions<?>) new RequestOptions().centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)).into(imageView);
    }

    public int getItemCount() {
        return this.mModeBean.getName().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_wind_speed;
        private RelativeLayout rl_ry_mode_root;
        private TextView tv_cover_view;
        private TextView tv_loop_fan_error;
        private TextView tv_wind_speed;

        public ViewHolder(View view) {
            super(view);
            LogUtil.d(StubApp.getString2(13804));
            this.rl_ry_mode_root = (RelativeLayout) view.findViewById(R.id.rl_ry_mode_root);
            this.tv_loop_fan_error = (TextView) view.findViewById(R.id.tv_loop_fan_error);
            this.tv_cover_view = (TextView) view.findViewById(R.id.tv_cover_view);
            this.tv_wind_speed = (TextView) view.findViewById(R.id.tv_wind_speed);
            this.iv_wind_speed = (ImageView) view.findViewById(R.id.iv_wind_speed);
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
