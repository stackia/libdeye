package com.deye.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.deye.views.SmartRefreshWebLayout;
import com.deye.webview.JsInterface;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.mxchipapp.R;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class BaseWebActivity extends AppCompatActivity {
    protected AgentWeb mAgentWeb;
    private AlertDialog mAlertDialog;
    private LinearLayout mLinearLayout;
    private TextView mTitleTextView;
    Toolbar mToolbar;
    private String passTitle;
    private WebViewClient mWebViewClient = new WebViewClient() { // from class: com.deye.webview.BaseWebActivity.3
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            return super.shouldOverrideUrlLoading(webView, webResourceRequest);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Log.i(StubApp.getString2(14685), StubApp.getString2(14686));
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
        }
    };
    private WebChromeClient mWebChromeClient = new WebChromeClient() { // from class: com.deye.webview.BaseWebActivity.4
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
        }
    };

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        getWindow().getDecorView().setSystemUiVisibility(9216);
        getWindow().setStatusBarColor(getColor(R.color.transparent));
        super.onCreate(bundle);
        setContentView(R.layout.activity_web);
        this.mLinearLayout = (LinearLayout) findViewById(R.id.container);
        Toolbar toolbarFindViewById = findViewById(2131363013);
        this.mToolbar = toolbarFindViewById;
        toolbarFindViewById.setTitleTextColor(-1);
        this.mTitleTextView = (TextView) findViewById(2131363014);
        setSupportActionBar(this.mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        this.mToolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.deye.webview.BaseWebActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BaseWebActivity.this.mAgentWeb != null && BaseWebActivity.this.mAgentWeb.getWebCreator() != null && BaseWebActivity.this.mAgentWeb.getWebCreator().getWebView() != null) {
                    WebView webView = BaseWebActivity.this.mAgentWeb.getWebCreator().getWebView();
                    if (webView.canGoBack()) {
                        webView.goBack();
                        return;
                    } else {
                        BaseWebActivity.this.finish();
                        return;
                    }
                }
                BaseWebActivity.this.finish();
            }
        });
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.mAgentWeb = AgentWeb.with(this).setAgentWebParent(this.mLinearLayout, new LinearLayout.LayoutParams(-1, -1)).useDefaultIndicator().setWebChromeClient(this.mWebChromeClient).setWebViewClient(this.mWebViewClient).setMainFrameErrorView(R.layout.agentweb_error_page, -1).setSecurityType(AgentWeb.SecurityType.STRICT_CHECK).setWebLayout(new SmartRefreshWebLayout(this)).setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK).interceptUnkownUrl().createAgentWeb().ready().go(getUrl());
        Log.i(StubApp.getString2(14685), StubApp.getString2(14690) + (System.currentTimeMillis() - jCurrentTimeMillis));
        String stringExtra = getIntent().getStringExtra(StubApp.getString2(14682));
        this.passTitle = stringExtra;
        if (stringExtra != null && !stringExtra.isEmpty()) {
            this.mTitleTextView.setText(this.passTitle);
        }
        this.mAgentWeb.getAgentWebSettings().getWebSettings().setMediaPlaybackRequiresUserGesture(false);
        this.mAgentWeb.getJsInterfaceHolder().addJavaObject(StubApp.getString2(14691), new JsInterface(new JsInterface.BridgeCallBack() { // from class: com.deye.webview.BaseWebActivity.2
            @Override // com.deye.webview.JsInterface.BridgeCallBack
            public void setPageTitle(String str) {
                BaseWebActivity.this.mTitleTextView.setText(str);
            }

            @Override // com.deye.webview.JsInterface.BridgeCallBack
            public void finishPage(String str) {
                BaseWebActivity.this.finish();
            }

            @Override // com.deye.webview.JsInterface.BridgeCallBack
            public void openPage(String str) {
                if (str.equals(StubApp.getString2(14684))) {
                    BaseWebActivity.this.startActivity(new Intent((Context) BaseWebActivity.this, (Class<?>) SmartServiceWeb2Activity.class));
                }
            }
        }));
    }

    public String getUrl() {
        return StubApp.getString2(14687);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mAgentWeb.handleKeyEvent(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    protected void onPause() {
        this.mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    protected void onResume() {
        this.mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Log.i(StubApp.getString2(14685), StubApp.getString2(14688) + i + StubApp.getString2(14689) + i2);
        super.onActivityResult(i, i2, intent);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mAgentWeb.getWebLifeCycle().onDestroy();
    }
}
