package com.deye.activity.mine;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.deye.webview.SmartServiceWebActivity;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FeedBackActivity.kt */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/deye/activity/mine/FeedBackActivity$initView$clickableSpan$1", "Landroid/text/style/ClickableSpan;", "onClick", "", "widget", "Landroid/view/View;", "updateDrawState", "ds", "Landroid/text/TextPaint;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class FeedBackActivity$initView$clickableSpan$1 extends ClickableSpan {
    final /* synthetic */ FeedBackActivity this$0;

    FeedBackActivity$initView$clickableSpan$1(FeedBackActivity feedBackActivity) {
        this.this$0 = feedBackActivity;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View widget) {
        Intrinsics.checkNotNullParameter(widget, "widget");
        SmartServiceWebActivity.INSTANCE.open((Context) this.this$0.mContext);
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint ds) {
        Intrinsics.checkNotNullParameter(ds, "ds");
        super.updateDrawState(ds);
        ds.setColor(Color.parseColor(StubApp.getString2(13225)));
        ds.setUnderlineText(true);
    }
}
