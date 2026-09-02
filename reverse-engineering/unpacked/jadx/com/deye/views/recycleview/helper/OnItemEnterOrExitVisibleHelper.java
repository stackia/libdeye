package com.deye.views.recycleview.helper;

import android.util.SparseArray;
import android.widget.AbsListView;
import android.widget.ListView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class OnItemEnterOrExitVisibleHelper {
    private int lastEnd;
    private int lastStart = -1;
    private SparseArray<Long> mLastRecordTime = new SparseArray<>();
    private SparseArray<Long> mAllTime = new SparseArray<>();
    private SparseArray<Long> mMaxTime = new SparseArray<>();
    private AbsListView.OnScrollListener listViewScrollListener = new AbsListView.OnScrollListener() { // from class: com.deye.views.recycleview.helper.OnItemEnterOrExitVisibleHelper.1
        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i != 0 || OnItemEnterOrExitVisibleHelper.this.mScrollStatusListener == null) {
                return;
            }
            OnItemEnterOrExitVisibleHelper.this.mScrollStatusListener.onListStop();
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            OnItemEnterOrExitVisibleHelper.this.dealScrollEvent(absListView.getFirstVisiblePosition(), absListView.getLastVisiblePosition());
        }
    };
    private RecyclerView.OnScrollListener recyclerScrollListener = new RecyclerView.OnScrollListener() { // from class: com.deye.views.recycleview.helper.OnItemEnterOrExitVisibleHelper.2
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            LinearLayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager != null) {
                int iFindFirstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int iFindLastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int i3 = iFindLastVisibleItemPosition - iFindFirstVisibleItemPosition;
                if (iFindLastVisibleItemPosition == 0) {
                    i3 = 0;
                }
                if (i3 != 0) {
                    OnItemEnterOrExitVisibleHelper.this.dealScrollEvent(iFindFirstVisibleItemPosition, iFindLastVisibleItemPosition);
                }
            }
        }
    };
    private OnScrollStatusListener mScrollStatusListener = new OnScrollStatusListener() { // from class: com.deye.views.recycleview.helper.OnItemEnterOrExitVisibleHelper.3
        @Override // com.deye.views.recycleview.helper.OnItemEnterOrExitVisibleHelper.OnScrollStatusListener
        public void onListStop() {
        }

        @Override // com.deye.views.recycleview.helper.OnItemEnterOrExitVisibleHelper.OnScrollStatusListener
        public void onSelectEnterPosition(int i) {
            LogUtil.d(StubApp.getString2(14679) + i);
            if (i < 0) {
                return;
            }
            OnItemEnterOrExitVisibleHelper.this.mLastRecordTime.append(i, Long.valueOf(System.currentTimeMillis()));
        }

        @Override // com.deye.views.recycleview.helper.OnItemEnterOrExitVisibleHelper.OnScrollStatusListener
        public void onSelectExitPosition(int i) {
            Long l;
            LogUtil.d(StubApp.getString2(14680) + i);
            if (i >= 0 && (l = (Long) OnItemEnterOrExitVisibleHelper.this.mLastRecordTime.get(i)) != null) {
                if (l.longValue() != 0) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - l.longValue();
                    if (OnItemEnterOrExitVisibleHelper.this.mMaxTime.get(i) == null || ((Long) OnItemEnterOrExitVisibleHelper.this.mMaxTime.get(i)).longValue() < jCurrentTimeMillis) {
                        OnItemEnterOrExitVisibleHelper.this.mMaxTime.append(i, Long.valueOf(jCurrentTimeMillis));
                    }
                    OnItemEnterOrExitVisibleHelper.this.mAllTime.append(i, Long.valueOf(jCurrentTimeMillis + (OnItemEnterOrExitVisibleHelper.this.mAllTime.get(i) != null ? ((Long) OnItemEnterOrExitVisibleHelper.this.mAllTime.get(i)).longValue() : 0L)));
                }
            }
        }
    };

    public interface OnScrollStatusListener {
        void onListStop();

        void onSelectEnterPosition(int i);

        void onSelectExitPosition(int i);
    }

    public void dealScrollEvent(int i, int i2) {
        if (i2 - i > 0) {
            int i3 = this.lastStart;
            if (i3 == -1) {
                this.lastStart = i;
                this.lastEnd = i2;
                while (i < this.lastEnd + 1) {
                    OnScrollStatusListener onScrollStatusListener = this.mScrollStatusListener;
                    if (onScrollStatusListener != null) {
                        onScrollStatusListener.onSelectEnterPosition(i);
                    }
                    i++;
                }
                return;
            }
            if (i != i3) {
                if (i > i3) {
                    while (i3 < i) {
                        OnScrollStatusListener onScrollStatusListener2 = this.mScrollStatusListener;
                        if (onScrollStatusListener2 != null) {
                            onScrollStatusListener2.onSelectExitPosition(i3);
                        }
                        i3++;
                    }
                } else {
                    for (int i4 = i; i4 < this.lastStart; i4++) {
                        OnScrollStatusListener onScrollStatusListener3 = this.mScrollStatusListener;
                        if (onScrollStatusListener3 != null) {
                            onScrollStatusListener3.onSelectEnterPosition(i4);
                        }
                    }
                }
                this.lastStart = i;
            }
            int i5 = this.lastEnd;
            if (i2 != i5) {
                if (i2 > i5) {
                    while (i5 < i2) {
                        OnScrollStatusListener onScrollStatusListener4 = this.mScrollStatusListener;
                        if (onScrollStatusListener4 != null) {
                            onScrollStatusListener4.onSelectEnterPosition(i5 + 1);
                        }
                        i5++;
                    }
                } else {
                    for (int i6 = i2; i6 < this.lastEnd; i6++) {
                        OnScrollStatusListener onScrollStatusListener5 = this.mScrollStatusListener;
                        if (onScrollStatusListener5 != null) {
                            onScrollStatusListener5.onSelectExitPosition(i6 + 1);
                        }
                    }
                }
                this.lastEnd = i2;
            }
        }
    }

    public void registerItemEnterAndExitListener(ListView listView) {
        listView.setOnScrollListener(this.listViewScrollListener);
    }

    public void registerItemEnterAndExitListener(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(this.recyclerScrollListener);
    }
}
