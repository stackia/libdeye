package com.deye.views.recycleview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.deye.adapter.QuiltDryerModeAdapter;
import com.deye.entity.control_panel.quilt_dryer.func.ModeBean;
import com.mxchipapp.R;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class QuiltDryerModeRecyclerView extends LinearLayout {
    private Context mContext;
    private QuiltDryerModeAdapter.IOnClickItemListener mIOnClickItemListener;
    private ModeBean mModeBean;
    private ModeBean mModeBeanFirst;
    private ModeBean mModeBeanSecond;
    private QuiltDryerModeAdapter mQuiltDryerModeAdapterFirst;
    private QuiltDryerModeAdapter mQuiltDryerModeAdapterSecond;
    private RecyclerView mRyModeFirst;
    private RecyclerView mRyModeSecond;

    public void setEnable(boolean z) {
        QuiltDryerModeAdapter quiltDryerModeAdapter = this.mQuiltDryerModeAdapterFirst;
        if (quiltDryerModeAdapter != null) {
            quiltDryerModeAdapter.setEnable(z);
        }
        QuiltDryerModeAdapter quiltDryerModeAdapter2 = this.mQuiltDryerModeAdapterSecond;
        if (quiltDryerModeAdapter2 != null) {
            quiltDryerModeAdapter2.setEnable(z);
        }
    }

    public void setOff(boolean z) {
        QuiltDryerModeAdapter quiltDryerModeAdapter = this.mQuiltDryerModeAdapterFirst;
        if (quiltDryerModeAdapter != null) {
            quiltDryerModeAdapter.setOff(z);
        }
        QuiltDryerModeAdapter quiltDryerModeAdapter2 = this.mQuiltDryerModeAdapterSecond;
        if (quiltDryerModeAdapter2 != null) {
            quiltDryerModeAdapter2.setOff(z);
        }
    }

    public void setSelectedItem(String str) {
        QuiltDryerModeAdapter quiltDryerModeAdapter = this.mQuiltDryerModeAdapterFirst;
        if (quiltDryerModeAdapter != null) {
            quiltDryerModeAdapter.setSelectedItem(str);
        }
        QuiltDryerModeAdapter quiltDryerModeAdapter2 = this.mQuiltDryerModeAdapterSecond;
        if (quiltDryerModeAdapter2 != null) {
            quiltDryerModeAdapter2.setSelectedItem(str);
        }
    }

    public void initData(ModeBean modeBean) {
        this.mModeBean = modeBean;
        if (modeBean.getValue().length <= 0) {
            this.mRyModeFirst.setVisibility(8);
            this.mRyModeSecond.setVisibility(8);
            setVisibility(8);
            return;
        }
        if (modeBean.getValue().length > 0 && modeBean.getValue().length <= 4) {
            this.mRyModeSecond.setVisibility(8);
            this.mQuiltDryerModeAdapterFirst = new QuiltDryerModeAdapter(this.mContext, modeBean);
            this.mRyModeFirst.setLayoutManager(new StaggeredGridLayoutManager(modeBean.getName().length, 1));
            this.mRyModeFirst.setAdapter(this.mQuiltDryerModeAdapterFirst);
            this.mQuiltDryerModeAdapterFirst.setOnClickItemListener(new QuiltDryerModeAdapter.IOnClickItemListener() { // from class: com.deye.views.recycleview.QuiltDryerModeRecyclerView.1
                @Override // com.deye.adapter.QuiltDryerModeAdapter.IOnClickItemListener
                public void onOnClickItem(int i) {
                    if (QuiltDryerModeRecyclerView.this.mIOnClickItemListener != null) {
                        QuiltDryerModeRecyclerView.this.mIOnClickItemListener.onOnClickItem(i);
                    }
                }
            });
            return;
        }
        if (modeBean.getValue().length == 5) {
            this.mRyModeSecond.setVisibility(0);
            ModeBean modeBean2 = new ModeBean();
            this.mModeBeanFirst = modeBean2;
            modeBean2.setKey(new String[]{modeBean.getKey()[0], modeBean.getKey()[1]});
            this.mModeBeanFirst.setName(new String[]{modeBean.getName()[0], modeBean.getName()[1]});
            this.mModeBeanFirst.setValue(new String[]{modeBean.getValue()[0], modeBean.getValue()[1]});
            this.mModeBeanFirst.setIconNormal(new String[]{modeBean.getIconNormal()[0], modeBean.getIconNormal()[1]});
            this.mModeBeanFirst.setIconOff(new String[]{modeBean.getIconNormal()[0], modeBean.getIconNormal()[1]});
            this.mModeBeanFirst.setIconSelected(new String[]{modeBean.getIconSelected()[0], modeBean.getIconSelected()[1]});
            ModeBean modeBean3 = new ModeBean();
            this.mModeBeanSecond = modeBean3;
            modeBean3.setKey(new String[]{modeBean.getKey()[2], modeBean.getKey()[3], modeBean.getKey()[4]});
            this.mModeBeanSecond.setName(new String[]{modeBean.getName()[2], modeBean.getName()[3], modeBean.getName()[4]});
            this.mModeBeanSecond.setValue(new String[]{modeBean.getValue()[2], modeBean.getValue()[3], modeBean.getValue()[4]});
            this.mModeBeanSecond.setIconNormal(new String[]{modeBean.getIconNormal()[2], modeBean.getIconNormal()[3], modeBean.getIconNormal()[4]});
            this.mModeBeanSecond.setIconOff(new String[]{modeBean.getIconNormal()[2], modeBean.getIconNormal()[3], modeBean.getIconNormal()[4]});
            this.mModeBeanSecond.setIconSelected(new String[]{modeBean.getIconSelected()[2], modeBean.getIconSelected()[3], modeBean.getIconSelected()[4]});
            this.mQuiltDryerModeAdapterFirst = new QuiltDryerModeAdapter(this.mContext, this.mModeBeanFirst);
            this.mRyModeFirst.setLayoutManager(new StaggeredGridLayoutManager(this.mModeBeanFirst.getName().length, 1));
            this.mRyModeFirst.setAdapter(this.mQuiltDryerModeAdapterFirst);
            this.mQuiltDryerModeAdapterSecond = new QuiltDryerModeAdapter(this.mContext, this.mModeBeanSecond);
            this.mRyModeSecond.setLayoutManager(new StaggeredGridLayoutManager(this.mModeBeanSecond.getName().length, 1));
            this.mRyModeSecond.setAdapter(this.mQuiltDryerModeAdapterSecond);
            this.mQuiltDryerModeAdapterFirst.setOnClickItemListener(new QuiltDryerModeAdapter.IOnClickItemListener() { // from class: com.deye.views.recycleview.QuiltDryerModeRecyclerView.2
                @Override // com.deye.adapter.QuiltDryerModeAdapter.IOnClickItemListener
                public void onOnClickItem(int i) {
                    QuiltDryerModeRecyclerView.this.mQuiltDryerModeAdapterSecond.setSelectedItem(-2);
                    if (QuiltDryerModeRecyclerView.this.mIOnClickItemListener != null) {
                        QuiltDryerModeRecyclerView.this.mIOnClickItemListener.onOnClickItem(i);
                    }
                }
            });
            this.mQuiltDryerModeAdapterSecond.setOnClickItemListener(new QuiltDryerModeAdapter.IOnClickItemListener() { // from class: com.deye.views.recycleview.QuiltDryerModeRecyclerView.3
                @Override // com.deye.adapter.QuiltDryerModeAdapter.IOnClickItemListener
                public void onOnClickItem(int i) {
                    QuiltDryerModeRecyclerView.this.mQuiltDryerModeAdapterFirst.setSelectedItem(-2);
                    if (QuiltDryerModeRecyclerView.this.mIOnClickItemListener != null) {
                        QuiltDryerModeRecyclerView.this.mIOnClickItemListener.onOnClickItem(i + 2);
                    }
                }
            });
        }
    }

    public QuiltDryerModeRecyclerView(Context context) {
        super(context);
    }

    public QuiltDryerModeRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        ((LayoutInflater) context.getSystemService(StubApp.getString2(11065))).inflate(R.layout.quilt_dryer_mode_recycleview_layout, this);
        this.mRyModeFirst = findViewById(R.id.ry_mode_first);
        this.mRyModeSecond = findViewById(R.id.ry_mode_second);
    }

    public void setOnClickItemListener(QuiltDryerModeAdapter.IOnClickItemListener iOnClickItemListener) {
        this.mIOnClickItemListener = iOnClickItemListener;
    }
}
