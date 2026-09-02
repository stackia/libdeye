package com.deye.capture;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.deye.activity.mine.FeedBackActivity;
import com.mxchipapp.R;
import com.stub.StubApp;
import com.zhouyou.view.seekbar.SignUtils;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class FloatWindowManager {
    private static View floatView;
    private static WindowManager windowManager;

    public static void showFloatWindow(final Context context, final String str) {
        if (context == null || (context instanceof FeedBackActivity)) {
            return;
        }
        if (floatView == null) {
            floatView = LayoutInflater.from(context).inflate(R.layout.float_view_layout, (ViewGroup) null);
        }
        if (!floatView.isAttachedToWindow()) {
            windowManager = (WindowManager) context.getSystemService(StubApp.getString2(13557));
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2, 520, -3);
            layoutParams.gravity = 8388661;
            layoutParams.y = SignUtils.dp2px(120);
            windowManager.addView(floatView, layoutParams);
        }
        floatView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.capture.FloatWindowManager.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(context, (Class<?>) FeedBackActivity.class);
                intent.putExtra(StubApp.getString2(13746), str);
                context.startActivity(intent);
                FloatWindowManager.windowManager.removeView(FloatWindowManager.floatView);
            }
        });
    }

    public static void removeFloatWindow() {
        View view;
        WindowManager windowManager2 = windowManager;
        if (windowManager2 == null || (view = floatView) == null) {
            return;
        }
        try {
            windowManager2.removeView(view);
            floatView = null;
        } catch (Exception unused) {
        }
    }
}
