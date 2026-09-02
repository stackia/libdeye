package com.deye.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainGuideView.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0006\u0010\u0017\u001a\u00020\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/deye/views/MainGuideView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "btnJump", "Landroid/widget/Button;", "btnNext", "curStep", "ivGuide1", "Landroid/widget/ImageView;", "ivGuide2", "ivGuide3", "ivGuide4", "ivGuideCenter", "init", "", "initViewByStep", "onNextClick", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class MainGuideView extends FrameLayout {
    private Button btnJump;
    private Button btnNext;
    private int curStep;
    private ImageView ivGuide1;
    private ImageView ivGuide2;
    private ImageView ivGuide3;
    private ImageView ivGuide4;
    private ImageView ivGuideCenter;
    public static final String KEY_GUIDE = StubApp.getString2(14618);
    public static final String KEY_IS_SHOW = StubApp.getString2(14619);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainGuideView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.curStep = 1;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainGuideView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.curStep = 1;
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainGuideView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.curStep = 1;
        init(context);
    }

    private final void init(Context context) {
        MMKV.mmkvWithID(StubApp.getString2(14618)).getBoolean(StubApp.getString2(14619), false);
    }

    public final void initViewByStep() {
        ImageView imageView = this.ivGuide1;
        Button button = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivGuide1");
            imageView = null;
        }
        imageView.setVisibility(this.curStep == 1 ? 0 : 8);
        ImageView imageView2 = this.ivGuide2;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivGuide2");
            imageView2 = null;
        }
        imageView2.setVisibility(this.curStep == 2 ? 0 : 8);
        ImageView imageView3 = this.ivGuide3;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivGuide3");
            imageView3 = null;
        }
        imageView3.setVisibility(this.curStep == 3 ? 0 : 8);
        ImageView imageView4 = this.ivGuide4;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivGuide4");
            imageView4 = null;
        }
        imageView4.setVisibility(this.curStep == 4 ? 0 : 8);
        ImageView imageView5 = this.ivGuideCenter;
        if (imageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivGuideCenter");
            imageView5 = null;
        }
        imageView5.setVisibility(this.curStep > 1 ? 0 : 8);
        Button button2 = this.btnJump;
        String string2 = StubApp.getString2(14620);
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            button2 = null;
        }
        button2.setVisibility(this.curStep >= 4 ? 8 : 0);
        Button button3 = this.btnNext;
        String string22 = StubApp.getString2(14621);
        if (button3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            button3 = null;
        }
        int i = this.curStep;
        button3.setText(i < 4 ? StubApp.getString2(14622) + i + StubApp.getString2(14623) : StubApp.getString2(14624));
        Button button4 = this.btnJump;
        if (button4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            button4 = null;
        }
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.MainGuideView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainGuideView.initViewByStep$lambda$0(this.f$0, view);
            }
        });
        Button button5 = this.btnNext;
        if (button5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
        } else {
            button = button5;
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.MainGuideView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainGuideView.initViewByStep$lambda$1(this.f$0, view);
            }
        });
        setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.MainGuideView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainGuideView.initViewByStep$lambda$2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViewByStep$lambda$0(MainGuideView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MMKV.mmkvWithID(StubApp.getString2(14618)).putBoolean(StubApp.getString2(14619), true);
        this$0.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViewByStep$lambda$1(MainGuideView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onNextClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initViewByStep$lambda$2(MainGuideView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onNextClick();
    }

    private final void onNextClick() {
        int i = this.curStep;
        if (i == 4) {
            MMKV.mmkvWithID(StubApp.getString2(14618)).putBoolean(StubApp.getString2(14619), true);
            setVisibility(8);
        } else {
            this.curStep = i + 1;
            initViewByStep();
        }
    }
}
