package com.deye.views;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.deye.adapter.DeviceLisViewPagerAdapter;
import com.deye.adapter.FindBannerAdapter;
import me.relex.circleindicator.BaseCircleIndicator;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class LoopCircleIndicator3 extends BaseCircleIndicator {
    private final RecyclerView.AdapterDataObserver mAdapterDataObserver;
    private final ViewPager2.OnPageChangeCallback mInternalPageChangeCallback;
    private ViewPager2 mViewpager;

    public LoopCircleIndicator3(Context context) {
        super(context);
        this.mInternalPageChangeCallback = new ViewPager2.OnPageChangeCallback() { // from class: com.deye.views.LoopCircleIndicator3.1
            public void onPageSelected(int i) {
                if (i == LoopCircleIndicator3.this.mLastPosition || LoopCircleIndicator3.this.mViewpager.getAdapter() == null || LoopCircleIndicator3.this.mViewpager.getAdapter().getItemCount() <= 0) {
                    return;
                }
                if (LoopCircleIndicator3.this.mViewpager.getAdapter() instanceof DeviceLisViewPagerAdapter) {
                    LoopCircleIndicator3.this.animatePageSelected(((DeviceLisViewPagerAdapter) LoopCircleIndicator3.this.mViewpager.getAdapter()).getRealPosition(i));
                } else if (LoopCircleIndicator3.this.mViewpager.getAdapter() instanceof FindBannerAdapter) {
                    LoopCircleIndicator3.this.animatePageSelected(((FindBannerAdapter) LoopCircleIndicator3.this.mViewpager.getAdapter()).getRealPosition(i));
                } else {
                    LoopCircleIndicator3.this.animatePageSelected(i);
                }
            }
        };
        this.mAdapterDataObserver = new RecyclerView.AdapterDataObserver() { // from class: com.deye.views.LoopCircleIndicator3.2
            public void onChanged() {
                super.onChanged();
                if (LoopCircleIndicator3.this.mViewpager == null) {
                    return;
                }
                RecyclerView.Adapter adapter = LoopCircleIndicator3.this.mViewpager.getAdapter();
                int itemCount = adapter != null ? adapter.getItemCount() : 0;
                if (itemCount == LoopCircleIndicator3.this.getChildCount()) {
                    return;
                }
                if (LoopCircleIndicator3.this.mLastPosition >= itemCount) {
                    LoopCircleIndicator3.this.mLastPosition = -1;
                } else {
                    LoopCircleIndicator3 loopCircleIndicator3 = LoopCircleIndicator3.this;
                    loopCircleIndicator3.mLastPosition = loopCircleIndicator3.mViewpager.getCurrentItem();
                }
                LoopCircleIndicator3.this.createIndicators();
            }

            public void onItemRangeChanged(int i, int i2) {
                super.onItemRangeChanged(i, i2);
                onChanged();
            }

            public void onItemRangeChanged(int i, int i2, Object obj) {
                super.onItemRangeChanged(i, i2, obj);
                onChanged();
            }

            public void onItemRangeInserted(int i, int i2) {
                super.onItemRangeInserted(i, i2);
                onChanged();
            }

            public void onItemRangeRemoved(int i, int i2) {
                super.onItemRangeRemoved(i, i2);
                onChanged();
            }

            public void onItemRangeMoved(int i, int i2, int i3) {
                super.onItemRangeMoved(i, i2, i3);
                onChanged();
            }
        };
    }

    public LoopCircleIndicator3(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mInternalPageChangeCallback = new ViewPager2.OnPageChangeCallback() { // from class: com.deye.views.LoopCircleIndicator3.1
            public void onPageSelected(int i) {
                if (i == LoopCircleIndicator3.this.mLastPosition || LoopCircleIndicator3.this.mViewpager.getAdapter() == null || LoopCircleIndicator3.this.mViewpager.getAdapter().getItemCount() <= 0) {
                    return;
                }
                if (LoopCircleIndicator3.this.mViewpager.getAdapter() instanceof DeviceLisViewPagerAdapter) {
                    LoopCircleIndicator3.this.animatePageSelected(((DeviceLisViewPagerAdapter) LoopCircleIndicator3.this.mViewpager.getAdapter()).getRealPosition(i));
                } else if (LoopCircleIndicator3.this.mViewpager.getAdapter() instanceof FindBannerAdapter) {
                    LoopCircleIndicator3.this.animatePageSelected(((FindBannerAdapter) LoopCircleIndicator3.this.mViewpager.getAdapter()).getRealPosition(i));
                } else {
                    LoopCircleIndicator3.this.animatePageSelected(i);
                }
            }
        };
        this.mAdapterDataObserver = new RecyclerView.AdapterDataObserver() { // from class: com.deye.views.LoopCircleIndicator3.2
            public void onChanged() {
                super.onChanged();
                if (LoopCircleIndicator3.this.mViewpager == null) {
                    return;
                }
                RecyclerView.Adapter adapter = LoopCircleIndicator3.this.mViewpager.getAdapter();
                int itemCount = adapter != null ? adapter.getItemCount() : 0;
                if (itemCount == LoopCircleIndicator3.this.getChildCount()) {
                    return;
                }
                if (LoopCircleIndicator3.this.mLastPosition >= itemCount) {
                    LoopCircleIndicator3.this.mLastPosition = -1;
                } else {
                    LoopCircleIndicator3 loopCircleIndicator3 = LoopCircleIndicator3.this;
                    loopCircleIndicator3.mLastPosition = loopCircleIndicator3.mViewpager.getCurrentItem();
                }
                LoopCircleIndicator3.this.createIndicators();
            }

            public void onItemRangeChanged(int i, int i2) {
                super.onItemRangeChanged(i, i2);
                onChanged();
            }

            public void onItemRangeChanged(int i, int i2, Object obj) {
                super.onItemRangeChanged(i, i2, obj);
                onChanged();
            }

            public void onItemRangeInserted(int i, int i2) {
                super.onItemRangeInserted(i, i2);
                onChanged();
            }

            public void onItemRangeRemoved(int i, int i2) {
                super.onItemRangeRemoved(i, i2);
                onChanged();
            }

            public void onItemRangeMoved(int i, int i2, int i3) {
                super.onItemRangeMoved(i, i2, i3);
                onChanged();
            }
        };
    }

    public LoopCircleIndicator3(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mInternalPageChangeCallback = new ViewPager2.OnPageChangeCallback() { // from class: com.deye.views.LoopCircleIndicator3.1
            public void onPageSelected(int i2) {
                if (i2 == LoopCircleIndicator3.this.mLastPosition || LoopCircleIndicator3.this.mViewpager.getAdapter() == null || LoopCircleIndicator3.this.mViewpager.getAdapter().getItemCount() <= 0) {
                    return;
                }
                if (LoopCircleIndicator3.this.mViewpager.getAdapter() instanceof DeviceLisViewPagerAdapter) {
                    LoopCircleIndicator3.this.animatePageSelected(((DeviceLisViewPagerAdapter) LoopCircleIndicator3.this.mViewpager.getAdapter()).getRealPosition(i2));
                } else if (LoopCircleIndicator3.this.mViewpager.getAdapter() instanceof FindBannerAdapter) {
                    LoopCircleIndicator3.this.animatePageSelected(((FindBannerAdapter) LoopCircleIndicator3.this.mViewpager.getAdapter()).getRealPosition(i2));
                } else {
                    LoopCircleIndicator3.this.animatePageSelected(i2);
                }
            }
        };
        this.mAdapterDataObserver = new RecyclerView.AdapterDataObserver() { // from class: com.deye.views.LoopCircleIndicator3.2
            public void onChanged() {
                super.onChanged();
                if (LoopCircleIndicator3.this.mViewpager == null) {
                    return;
                }
                RecyclerView.Adapter adapter = LoopCircleIndicator3.this.mViewpager.getAdapter();
                int itemCount = adapter != null ? adapter.getItemCount() : 0;
                if (itemCount == LoopCircleIndicator3.this.getChildCount()) {
                    return;
                }
                if (LoopCircleIndicator3.this.mLastPosition >= itemCount) {
                    LoopCircleIndicator3.this.mLastPosition = -1;
                } else {
                    LoopCircleIndicator3 loopCircleIndicator3 = LoopCircleIndicator3.this;
                    loopCircleIndicator3.mLastPosition = loopCircleIndicator3.mViewpager.getCurrentItem();
                }
                LoopCircleIndicator3.this.createIndicators();
            }

            public void onItemRangeChanged(int i2, int i22) {
                super.onItemRangeChanged(i2, i22);
                onChanged();
            }

            public void onItemRangeChanged(int i2, int i22, Object obj) {
                super.onItemRangeChanged(i2, i22, obj);
                onChanged();
            }

            public void onItemRangeInserted(int i2, int i22) {
                super.onItemRangeInserted(i2, i22);
                onChanged();
            }

            public void onItemRangeRemoved(int i2, int i22) {
                super.onItemRangeRemoved(i2, i22);
                onChanged();
            }

            public void onItemRangeMoved(int i2, int i22, int i3) {
                super.onItemRangeMoved(i2, i22, i3);
                onChanged();
            }
        };
    }

    public LoopCircleIndicator3(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mInternalPageChangeCallback = new ViewPager2.OnPageChangeCallback() { // from class: com.deye.views.LoopCircleIndicator3.1
            public void onPageSelected(int i22) {
                if (i22 == LoopCircleIndicator3.this.mLastPosition || LoopCircleIndicator3.this.mViewpager.getAdapter() == null || LoopCircleIndicator3.this.mViewpager.getAdapter().getItemCount() <= 0) {
                    return;
                }
                if (LoopCircleIndicator3.this.mViewpager.getAdapter() instanceof DeviceLisViewPagerAdapter) {
                    LoopCircleIndicator3.this.animatePageSelected(((DeviceLisViewPagerAdapter) LoopCircleIndicator3.this.mViewpager.getAdapter()).getRealPosition(i22));
                } else if (LoopCircleIndicator3.this.mViewpager.getAdapter() instanceof FindBannerAdapter) {
                    LoopCircleIndicator3.this.animatePageSelected(((FindBannerAdapter) LoopCircleIndicator3.this.mViewpager.getAdapter()).getRealPosition(i22));
                } else {
                    LoopCircleIndicator3.this.animatePageSelected(i22);
                }
            }
        };
        this.mAdapterDataObserver = new RecyclerView.AdapterDataObserver() { // from class: com.deye.views.LoopCircleIndicator3.2
            public void onChanged() {
                super.onChanged();
                if (LoopCircleIndicator3.this.mViewpager == null) {
                    return;
                }
                RecyclerView.Adapter adapter = LoopCircleIndicator3.this.mViewpager.getAdapter();
                int itemCount = adapter != null ? adapter.getItemCount() : 0;
                if (itemCount == LoopCircleIndicator3.this.getChildCount()) {
                    return;
                }
                if (LoopCircleIndicator3.this.mLastPosition >= itemCount) {
                    LoopCircleIndicator3.this.mLastPosition = -1;
                } else {
                    LoopCircleIndicator3 loopCircleIndicator3 = LoopCircleIndicator3.this;
                    loopCircleIndicator3.mLastPosition = loopCircleIndicator3.mViewpager.getCurrentItem();
                }
                LoopCircleIndicator3.this.createIndicators();
            }

            public void onItemRangeChanged(int i22, int i222) {
                super.onItemRangeChanged(i22, i222);
                onChanged();
            }

            public void onItemRangeChanged(int i22, int i222, Object obj) {
                super.onItemRangeChanged(i22, i222, obj);
                onChanged();
            }

            public void onItemRangeInserted(int i22, int i222) {
                super.onItemRangeInserted(i22, i222);
                onChanged();
            }

            public void onItemRangeRemoved(int i22, int i222) {
                super.onItemRangeRemoved(i22, i222);
                onChanged();
            }

            public void onItemRangeMoved(int i22, int i222, int i3) {
                super.onItemRangeMoved(i22, i222, i3);
                onChanged();
            }
        };
    }

    public void setViewPager(ViewPager2 viewPager2) {
        this.mViewpager = viewPager2;
        if (viewPager2 == null || viewPager2.getAdapter() == null) {
            return;
        }
        this.mLastPosition = -1;
        createIndicators();
        this.mViewpager.unregisterOnPageChangeCallback(this.mInternalPageChangeCallback);
        this.mViewpager.registerOnPageChangeCallback(this.mInternalPageChangeCallback);
        if (this.mViewpager.getAdapter() != null && (this.mViewpager.getAdapter() instanceof DeviceLisViewPagerAdapter)) {
            this.mInternalPageChangeCallback.onPageSelected(((DeviceLisViewPagerAdapter) viewPager2.getAdapter()).getRealPosition(this.mViewpager.getCurrentItem()));
        } else {
            this.mInternalPageChangeCallback.onPageSelected(this.mViewpager.getCurrentItem());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createIndicators() {
        int itemCount;
        RecyclerView.Adapter adapter = this.mViewpager.getAdapter();
        if (adapter == null) {
            itemCount = 0;
        } else if (adapter instanceof DeviceLisViewPagerAdapter) {
            itemCount = ((DeviceLisViewPagerAdapter) adapter).getRealSize();
        } else if (adapter instanceof FindBannerAdapter) {
            itemCount = ((FindBannerAdapter) adapter).getRealSize();
        } else {
            itemCount = adapter.getItemCount();
        }
        createIndicators(itemCount, this.mViewpager.getCurrentItem());
    }

    public RecyclerView.AdapterDataObserver getAdapterDataObserver() {
        return this.mAdapterDataObserver;
    }
}
