package com.deye.views;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.mxchipapp.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GetCodeTextView.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\fH\u0016J\u0006\u0010\u0013\u001a\u00020\u0010R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/deye/views/GetCodeTextView;", "Landroidx/appcompat/widget/AppCompatTextView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "isRunning", "", "timer", "Landroid/os/CountDownTimer;", "finish", "", "setEnabled", "enabled", "start", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class GetCodeTextView extends AppCompatTextView {
    private boolean isRunning;
    private CountDownTimer timer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetCodeTextView(Context context) {
        super(context);
        Intrinsics.checkNotNull(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetCodeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNull(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetCodeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNull(context);
    }

    public void setEnabled(boolean enabled) {
        if (enabled && this.isRunning) {
            return;
        }
        super.setEnabled(enabled);
    }

    public final void start() {
        CountDownTimer countDownTimer = new CountDownTimer() { // from class: com.deye.views.GetCodeTextView.start.1
            {
                super(60000L, 1000L);
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                GetCodeTextView.this.isRunning = true;
                GetCodeTextView.this.setEnabled(false);
                GetCodeTextView getCodeTextView = GetCodeTextView.this;
                getCodeTextView.setText(getCodeTextView.getContext().getString(R.string.verification_code_sent_countdown, Long.valueOf(millisUntilFinished / 1000)));
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                GetCodeTextView getCodeTextView = GetCodeTextView.this;
                getCodeTextView.setText(getCodeTextView.getContext().getString(R.string.get_again));
                GetCodeTextView.this.isRunning = false;
                GetCodeTextView.this.setEnabled(true);
            }
        };
        this.timer = countDownTimer;
        countDownTimer.start();
    }

    public final void finish() {
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        setText(getContext().getString(R.string.get_again));
        this.isRunning = false;
        setEnabled(true);
    }
}
