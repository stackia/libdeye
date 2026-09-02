package com.deye.utils;

import android.R;
import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class SoftHideKeyBoardUtil {
    private static final String TAG = StubApp.getString2(14433);
    private int contentHeight;
    private final FrameLayout.LayoutParams frameLayoutParams;
    private boolean isfirst = true;
    private final View mChildOfContent;
    private int statusBarHeight;
    private int usableHeightPrevious;

    public static void assistActivity(View view) {
        new SoftHideKeyBoardUtil(view);
    }

    public static void assistActivity(Activity activity) {
        new SoftHideKeyBoardUtil(activity);
    }

    private SoftHideKeyBoardUtil(View view) {
        this.mChildOfContent = view;
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.deye.utils.SoftHideKeyBoardUtil$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                this.f$0.lambda$new$0();
            }
        });
        this.frameLayoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        if (this.isfirst) {
            this.contentHeight = this.mChildOfContent.getHeight();
            this.isfirst = false;
        }
        possiblyResizeChildOfContent();
    }

    private SoftHideKeyBoardUtil(Activity activity) {
        View childAt = ((FrameLayout) activity.findViewById(R.id.content)).getChildAt(0);
        this.mChildOfContent = childAt;
        childAt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.deye.utils.SoftHideKeyBoardUtil$$ExternalSyntheticLambda1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                this.f$0.lambda$new$1();
            }
        });
        this.frameLayoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1() {
        if (this.isfirst) {
            this.contentHeight = this.mChildOfContent.getHeight();
            this.isfirst = false;
        }
        possiblyResizeChildOfContent();
    }

    private void possiblyResizeChildOfContent() {
        int iComputeUsableHeight = computeUsableHeight();
        if (iComputeUsableHeight != this.usableHeightPrevious) {
            int height = this.mChildOfContent.getRootView().getHeight();
            if (height - iComputeUsableHeight > height / 4) {
                this.frameLayoutParams.height = this.contentHeight - 100;
            } else {
                this.frameLayoutParams.height = this.contentHeight;
            }
            this.mChildOfContent.requestLayout();
            this.usableHeightPrevious = iComputeUsableHeight;
        }
    }

    private int computeUsableHeight() {
        Rect rect = new Rect();
        this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return rect.bottom - rect.top;
    }
}
