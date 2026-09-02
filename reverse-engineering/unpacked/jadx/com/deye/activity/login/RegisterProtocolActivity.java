package com.deye.activity.login;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.databinding.RegisterProtocolAtyBinding;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class RegisterProtocolActivity extends BaseActivity {
    private RegisterProtocolAtyBinding mRegisterProtocolAtyBinding;
    private String mRegisterProtocolUrl = StubApp.getString2(13733);

    static {
        StubApp.interface11(14289);
    }

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    @Override // com.deye.activity.device.base.BaseActivity
    public native boolean onKeyDown(int i, KeyEvent keyEvent);

    /* renamed from: com.deye.activity.login.RegisterProtocolActivity$1, reason: invalid class name */
    class AnonymousClass1 extends WebViewClient {
        AnonymousClass1() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            RegisterProtocolActivity.this.mRegisterProtocolAtyBinding.webViewProgressBar.setVisibility(0);
        }
    }

    /* renamed from: com.deye.activity.login.RegisterProtocolActivity$2, reason: invalid class name */
    class AnonymousClass2 extends WebChromeClient {
        AnonymousClass2() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            RegisterProtocolActivity.this.mRegisterProtocolAtyBinding.webViewProgressBar.setProgress(i);
            if (i == 100) {
                RegisterProtocolActivity.this.mRegisterProtocolAtyBinding.webViewProgressBar.setVisibility(8);
            }
        }
    }

    /* renamed from: com.deye.activity.login.RegisterProtocolActivity$3, reason: invalid class name */
    class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RegisterProtocolActivity.this.finish();
        }
    }
}
