package com.deye.listener;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class OnRefreshViewManager {
    private static final OnRefreshViewManager manager = new OnRefreshViewManager();
    private OnRefreshViewListener onRefreshViewListener;

    private OnRefreshViewManager() {
    }

    public static OnRefreshViewManager getInstance() {
        return manager;
    }

    public void setOnRefreshViewListener(OnRefreshViewListener onRefreshViewListener) {
        this.onRefreshViewListener = onRefreshViewListener;
    }

    public void doOnRefreshViewListener() {
        OnRefreshViewListener onRefreshViewListener = this.onRefreshViewListener;
        if (onRefreshViewListener != null) {
            onRefreshViewListener.onRefresh();
        }
    }
}
