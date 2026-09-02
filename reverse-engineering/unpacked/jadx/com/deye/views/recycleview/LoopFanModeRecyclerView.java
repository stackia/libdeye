package com.deye.views.recycleview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.alibaba.fastjson.JSON;
import com.deye.adapter.LoopFanModeAdapter;
import com.deye.entity.control_panel.dehumidifier.func.ModeBean;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class LoopFanModeRecyclerView extends LinearLayout {
    private Context mContext;
    private LoopFanModeAdapter.IOnClickItemListener mIOnClickItemListener;
    private LoopFanModeAdapter mLoopFanModeAdapterFirst;
    private LoopFanModeAdapter mLoopFanModeAdapterSecond;
    private ModeBean mModeBean;
    private ModeBean mModeBeanFirst;
    private ModeBean mModeBeanSecond;
    private RecyclerView mRyWindSpeedFirst;
    private RecyclerView mRyWindSpeedSecond;

    public void setEnable(boolean z) {
        LoopFanModeAdapter loopFanModeAdapter = this.mLoopFanModeAdapterFirst;
        if (loopFanModeAdapter != null) {
            loopFanModeAdapter.setEnable(z);
        }
        LoopFanModeAdapter loopFanModeAdapter2 = this.mLoopFanModeAdapterSecond;
        if (loopFanModeAdapter2 != null) {
            loopFanModeAdapter2.setEnable(z);
        }
    }

    public void setSelectedItem(ModeBean modeBean) {
        LoopFanModeAdapter loopFanModeAdapter = this.mLoopFanModeAdapterFirst;
        if (loopFanModeAdapter != null) {
            loopFanModeAdapter.setSelectedItem(modeBean);
        }
        LoopFanModeAdapter loopFanModeAdapter2 = this.mLoopFanModeAdapterSecond;
        if (loopFanModeAdapter2 != null) {
            loopFanModeAdapter2.setSelectedItem(modeBean);
        }
    }

    public void initData(ModeBean modeBean) {
        this.mModeBean = modeBean;
        if (modeBean.getValue().length <= 0) {
            this.mRyWindSpeedFirst.setVisibility(8);
            this.mRyWindSpeedSecond.setVisibility(8);
            setVisibility(8);
            return;
        }
        if (modeBean.getValue().length > 0 && modeBean.getValue().length <= 4) {
            this.mRyWindSpeedSecond.setVisibility(8);
            this.mLoopFanModeAdapterFirst = new LoopFanModeAdapter(this.mContext, modeBean);
            this.mRyWindSpeedFirst.setLayoutManager(new StaggeredGridLayoutManager(modeBean.getName().length, 1));
            this.mRyWindSpeedFirst.setAdapter(this.mLoopFanModeAdapterFirst);
            this.mLoopFanModeAdapterFirst.setOnClickItemListener(new LoopFanModeAdapter.IOnClickItemListener() { // from class: com.deye.views.recycleview.LoopFanModeRecyclerView.1
                @Override // com.deye.adapter.LoopFanModeAdapter.IOnClickItemListener
                public void onOnClickItem(ModeBean modeBean2, int i) {
                    LoopFanModeRecyclerView.this.updateModeBean(modeBean2);
                    if (LoopFanModeRecyclerView.this.mIOnClickItemListener != null) {
                        LoopFanModeRecyclerView.this.mIOnClickItemListener.onOnClickItem(LoopFanModeRecyclerView.this.mModeBean, i);
                    }
                }
            });
            return;
        }
        if (modeBean.getValue().length == 5) {
            this.mRyWindSpeedSecond.setVisibility(0);
            ModeBean modeBean2 = new ModeBean();
            this.mModeBeanFirst = modeBean2;
            modeBean2.setKey(new String[]{modeBean.getKey()[0], modeBean.getKey()[1]});
            this.mModeBeanFirst.setName(new String[]{modeBean.getName()[0], modeBean.getName()[1]});
            this.mModeBeanFirst.setValue(new String[]{modeBean.getValue()[0], modeBean.getValue()[1]});
            this.mModeBeanFirst.setError(new String[]{modeBean.getError()[0], modeBean.getError()[1]});
            this.mModeBeanFirst.setIconNormal(new String[]{modeBean.getIconNormal()[0], modeBean.getIconNormal()[1]});
            this.mModeBeanFirst.setIconError(new String[]{modeBean.getIconError()[0], modeBean.getIconError()[1]});
            this.mModeBeanFirst.setIconErrorGif(new String[]{modeBean.getIconErrorGif()[0], modeBean.getIconErrorGif()[1]});
            this.mModeBeanFirst.setIconSelected(new String[]{modeBean.getIconSelected()[0], modeBean.getIconSelected()[1]});
            ModeBean modeBean3 = new ModeBean();
            this.mModeBeanSecond = modeBean3;
            modeBean3.setKey(new String[]{modeBean.getKey()[2], modeBean.getKey()[3], modeBean.getKey()[4]});
            this.mModeBeanSecond.setName(new String[]{modeBean.getName()[2], modeBean.getName()[3], modeBean.getName()[4]});
            this.mModeBeanSecond.setValue(new String[]{modeBean.getValue()[2], modeBean.getValue()[3], modeBean.getValue()[4]});
            this.mModeBeanSecond.setError(new String[]{modeBean.getError()[2], modeBean.getError()[3], modeBean.getError()[4]});
            this.mModeBeanSecond.setIconError(new String[]{modeBean.getIconError()[2], modeBean.getIconError()[3], modeBean.getIconError()[4]});
            this.mModeBeanSecond.setIconErrorGif(new String[]{modeBean.getIconErrorGif()[2], modeBean.getIconErrorGif()[3], modeBean.getIconErrorGif()[4]});
            this.mModeBeanSecond.setIconNormal(new String[]{modeBean.getIconNormal()[2], modeBean.getIconNormal()[3], modeBean.getIconNormal()[4]});
            this.mModeBeanSecond.setIconSelected(new String[]{modeBean.getIconSelected()[2], modeBean.getIconSelected()[3], modeBean.getIconSelected()[4]});
            this.mLoopFanModeAdapterFirst = new LoopFanModeAdapter(this.mContext, this.mModeBeanFirst);
            this.mRyWindSpeedFirst.setLayoutManager(new StaggeredGridLayoutManager(this.mModeBeanFirst.getName().length, 1));
            this.mRyWindSpeedFirst.setAdapter(this.mLoopFanModeAdapterFirst);
            this.mLoopFanModeAdapterSecond = new LoopFanModeAdapter(this.mContext, this.mModeBeanSecond);
            this.mRyWindSpeedSecond.setLayoutManager(new StaggeredGridLayoutManager(this.mModeBeanSecond.getName().length, 1));
            this.mRyWindSpeedSecond.setAdapter(this.mLoopFanModeAdapterSecond);
            this.mLoopFanModeAdapterFirst.setOnClickItemListener(new LoopFanModeAdapter.IOnClickItemListener() { // from class: com.deye.views.recycleview.LoopFanModeRecyclerView.2
                @Override // com.deye.adapter.LoopFanModeAdapter.IOnClickItemListener
                public void onOnClickItem(ModeBean modeBean4, int i) {
                    LoopFanModeRecyclerView.this.updateModeBean(modeBean4);
                    if (LoopFanModeRecyclerView.this.mIOnClickItemListener != null) {
                        LoopFanModeRecyclerView.this.mIOnClickItemListener.onOnClickItem(LoopFanModeRecyclerView.this.mModeBean, i);
                    }
                }
            });
            this.mLoopFanModeAdapterSecond.setOnClickItemListener(new LoopFanModeAdapter.IOnClickItemListener() { // from class: com.deye.views.recycleview.LoopFanModeRecyclerView.3
                @Override // com.deye.adapter.LoopFanModeAdapter.IOnClickItemListener
                public void onOnClickItem(ModeBean modeBean4, int i) {
                    LoopFanModeRecyclerView.this.updateModeBean(modeBean4);
                    if (LoopFanModeRecyclerView.this.mIOnClickItemListener != null) {
                        LoopFanModeRecyclerView.this.mIOnClickItemListener.onOnClickItem(LoopFanModeRecyclerView.this.mModeBean, i + 2);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateModeBean(ModeBean modeBean) {
        for (int i = 0; i < modeBean.getKey().length; i++) {
            try {
                for (int i2 = 0; i2 < this.mModeBean.getKey().length; i2++) {
                    if (this.mModeBean.getKey()[i2].equals(modeBean.getKey()[i])) {
                        this.mModeBean.getValue()[i2] = modeBean.getValue()[i];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        LogUtil.d(StubApp.getString2("14672") + JSON.toJSONString(this.mModeBean));
    }

    public LoopFanModeRecyclerView(Context context) {
        super(context);
    }

    public LoopFanModeRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        ((LayoutInflater) context.getSystemService(StubApp.getString2(11065))).inflate(R.layout.mode_recycleview_layout, this);
        this.mRyWindSpeedFirst = findViewById(R.id.ry_wind_speed_first);
        this.mRyWindSpeedSecond = findViewById(R.id.ry_wind_speed_second);
    }

    public void setOnClickItemListener(LoopFanModeAdapter.IOnClickItemListener iOnClickItemListener) {
        this.mIOnClickItemListener = iOnClickItemListener;
    }
}
