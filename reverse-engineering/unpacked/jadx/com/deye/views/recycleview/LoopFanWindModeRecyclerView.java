package com.deye.views.recycleview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.deye.adapter.LoopFanWindModeAdapter;
import com.deye.entity.control_panel.dehumidifier.func.ModeBean;
import com.mxchipapp.R;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class LoopFanWindModeRecyclerView extends LinearLayout {
    private Context mContext;
    private LoopFanWindModeAdapter.IOnClickItemListener mIOnClickItemListener;
    private LoopFanWindModeAdapter mLoopFanWindModeAdapter;
    private RecyclerView mRyWindMode;
    private ModeBean mWindModeBean;

    public void setEnable(boolean z) {
        LoopFanWindModeAdapter loopFanWindModeAdapter = this.mLoopFanWindModeAdapter;
        if (loopFanWindModeAdapter != null) {
            loopFanWindModeAdapter.setEnable(z);
        }
    }

    public void setSelectedItem(String str) {
        LoopFanWindModeAdapter loopFanWindModeAdapter = this.mLoopFanWindModeAdapter;
        if (loopFanWindModeAdapter != null) {
            loopFanWindModeAdapter.setSelectedItem(str);
        }
    }

    public void initData(ModeBean modeBean) {
        this.mWindModeBean = modeBean;
        if (modeBean.getValue().length <= 0) {
            this.mRyWindMode.setVisibility(8);
            setVisibility(8);
        } else if (this.mWindModeBean.getValue().length > 0) {
            this.mLoopFanWindModeAdapter = new LoopFanWindModeAdapter(this.mContext, this.mWindModeBean);
            this.mRyWindMode.setLayoutManager(new StaggeredGridLayoutManager(3, 1));
            this.mRyWindMode.setAdapter(this.mLoopFanWindModeAdapter);
            this.mRyWindMode.setNestedScrollingEnabled(false);
            this.mRyWindMode.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() { // from class: com.deye.views.recycleview.LoopFanWindModeRecyclerView.1
                public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                    return false;
                }

                public void onRequestDisallowInterceptTouchEvent(boolean z) {
                }

                public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                }
            });
            this.mLoopFanWindModeAdapter.setOnClickItemListener(new LoopFanWindModeAdapter.IOnClickItemListener() { // from class: com.deye.views.recycleview.LoopFanWindModeRecyclerView.2
                @Override // com.deye.adapter.LoopFanWindModeAdapter.IOnClickItemListener
                public void onOnClickItem(int i) {
                    if (LoopFanWindModeRecyclerView.this.mIOnClickItemListener != null) {
                        LoopFanWindModeRecyclerView.this.mIOnClickItemListener.onOnClickItem(i);
                    }
                }
            });
        }
    }

    public LoopFanWindModeRecyclerView(Context context) {
        super(context);
    }

    public LoopFanWindModeRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        ((LayoutInflater) context.getSystemService(StubApp.getString2(11065))).inflate(R.layout.loop_fan_wind_mode_recycleview_layout, this);
        this.mRyWindMode = findViewById(R.id.ry_wind_mode_view);
    }

    public void setOnClickItemListener(LoopFanWindModeAdapter.IOnClickItemListener iOnClickItemListener) {
        this.mIOnClickItemListener = iOnClickItemListener;
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int halfSpace;

        public SpacesItemDecoration(int i) {
            this.halfSpace = i / 2;
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            if (view.getLayoutParams().getViewLayoutPosition() > 2) {
                rect.top = this.halfSpace;
            }
        }
    }
}
