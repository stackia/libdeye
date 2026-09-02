package com.deye.views;

import android.app.Activity;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.just.agentweb.IWebLayout;
import com.mxchipapp.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class SmartRefreshWebLayout implements IWebLayout {
    private final SmartRefreshLayout mSmartRefreshLayout;
    private final WebView mWebView;

    public SmartRefreshWebLayout(Activity activity) {
        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) activity.getLayoutInflater().inflate(R.layout.fragment_srl_web, (ViewGroup) null).findViewById(R.id.smarkLayout);
        this.mSmartRefreshLayout = smartRefreshLayout;
        this.mWebView = (WebView) smartRefreshLayout.findViewById(2131363300);
        smartRefreshLayout.setEnableRefresh(false);
    }

    public ViewGroup getLayout() {
        return this.mSmartRefreshLayout;
    }

    public WebView getWebView() {
        return this.mWebView;
    }
}
