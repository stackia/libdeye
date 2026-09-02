package com.deye.helper;

import android.graphics.Paint;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.aigestudio.wheelpicker.WheelPicker;
import com.deye.helper.DelayedShutdownTimePickerHelper;
import com.mxchipapp.R;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: DelayedShutdownTimePickerHelper.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\bJ\u0010\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/deye/helper/DelayedShutdownTimePickerHelper;", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "dialog", "Lcom/othershe/nicedialog/BaseNiceDialog;", "onTimeSelectedListener", "Lcom/deye/helper/DelayedShutdownTimePickerHelper$OnTimeSelectedListener;", "dismiss", "", "setOnTimeSelectedListener", "listener", "showTimePickerDialog", "currentHour", "", "OnTimeSelectedListener", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DelayedShutdownTimePickerHelper {
    private final FragmentActivity activity;
    private BaseNiceDialog dialog;
    private OnTimeSelectedListener onTimeSelectedListener;

    /* compiled from: DelayedShutdownTimePickerHelper.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/deye/helper/DelayedShutdownTimePickerHelper$OnTimeSelectedListener;", "", "onTimeSelected", "", "hour", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface OnTimeSelectedListener {
        void onTimeSelected(int hour);
    }

    public DelayedShutdownTimePickerHelper(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
    }

    public static /* synthetic */ void showTimePickerDialog$default(DelayedShutdownTimePickerHelper delayedShutdownTimePickerHelper, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 6;
        }
        delayedShutdownTimePickerHelper.showTimePickerDialog(i);
    }

    public final void showTimePickerDialog(int currentHour) {
        IntRange intRange = new IntRange(1, 24);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator<Integer> it2 = intRange.iterator();
        while (it2.hasNext()) {
            arrayList.add(String.valueOf(((IntIterator) it2).nextInt()));
        }
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = currentHour;
        this.dialog = NiceDialog.init().setLayoutId(R.layout.dialog_delayed_shutdown_time_picker).setConvertListener(new AnonymousClass1(arrayList, currentHour, intRef, this)).setShowBottom(true).show(this.activity.getSupportFragmentManager());
    }

    /* compiled from: DelayedShutdownTimePickerHelper.kt */
    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0014¨\u0006\b"}, d2 = {"com/deye/helper/DelayedShutdownTimePickerHelper$showTimePickerDialog$1", "Lcom/othershe/nicedialog/ViewConvertListener;", "convertView", "", "viewHolder", "Lcom/othershe/nicedialog/ViewHolder;", "baseNiceDialog", "Lcom/othershe/nicedialog/BaseNiceDialog;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.helper.DelayedShutdownTimePickerHelper$showTimePickerDialog$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ViewConvertListener {
        final /* synthetic */ int $currentHour;
        final /* synthetic */ List<String> $hours;
        final /* synthetic */ Ref.IntRef $selectedHour;
        final /* synthetic */ DelayedShutdownTimePickerHelper this$0;

        AnonymousClass1(List<String> list, int i, Ref.IntRef intRef, DelayedShutdownTimePickerHelper delayedShutdownTimePickerHelper) {
            this.$hours = list;
            this.$currentHour = i;
            this.$selectedHour = intRef;
            this.this$0 = delayedShutdownTimePickerHelper;
        }

        @Override // com.othershe.nicedialog.ViewConvertListener
        protected void convertView(ViewHolder viewHolder, BaseNiceDialog baseNiceDialog) {
            Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
            Intrinsics.checkNotNullParameter(baseNiceDialog, "baseNiceDialog");
            WheelPicker view = viewHolder.getView(R.id.wheelPicker_hour);
            Intrinsics.checkNotNullExpressionValue(view, "getView(...)");
            WheelPicker wheelPicker = view;
            wheelPicker.setData(this.$hours);
            wheelPicker.setLayerType(1, (Paint) null);
            wheelPicker.setSelectedItemPosition(RangesKt.coerceIn(this.$currentHour - 1, 0, 23), false);
            final Ref.IntRef intRef = this.$selectedHour;
            wheelPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.deye.helper.DelayedShutdownTimePickerHelper$showTimePickerDialog$1$$ExternalSyntheticLambda0
                public final void onItemSelected(WheelPicker wheelPicker2, Object obj, int i) {
                    DelayedShutdownTimePickerHelper.AnonymousClass1.convertView$lambda$0(intRef, wheelPicker2, obj, i);
                }
            });
            final DelayedShutdownTimePickerHelper delayedShutdownTimePickerHelper = this.this$0;
            viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DelayedShutdownTimePickerHelper$showTimePickerDialog$1$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    DelayedShutdownTimePickerHelper.AnonymousClass1.convertView$lambda$1(delayedShutdownTimePickerHelper, view2);
                }
            });
            final DelayedShutdownTimePickerHelper delayedShutdownTimePickerHelper2 = this.this$0;
            final Ref.IntRef intRef2 = this.$selectedHour;
            viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DelayedShutdownTimePickerHelper$showTimePickerDialog$1$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    DelayedShutdownTimePickerHelper.AnonymousClass1.convertView$lambda$2(delayedShutdownTimePickerHelper2, intRef2, view2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void convertView$lambda$0(Ref.IntRef selectedHour, WheelPicker wheelPicker, Object obj, int i) {
            Intrinsics.checkNotNullParameter(selectedHour, "$selectedHour");
            selectedHour.element = i + 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void convertView$lambda$1(DelayedShutdownTimePickerHelper this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            BaseNiceDialog baseNiceDialog = this$0.dialog;
            if (baseNiceDialog != null) {
                baseNiceDialog.dismiss();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void convertView$lambda$2(DelayedShutdownTimePickerHelper this$0, Ref.IntRef selectedHour, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(selectedHour, "$selectedHour");
            BaseNiceDialog baseNiceDialog = this$0.dialog;
            if (baseNiceDialog != null) {
                baseNiceDialog.dismiss();
            }
            OnTimeSelectedListener onTimeSelectedListener = this$0.onTimeSelectedListener;
            if (onTimeSelectedListener != null) {
                onTimeSelectedListener.onTimeSelected(selectedHour.element);
            }
        }
    }

    public final void setOnTimeSelectedListener(OnTimeSelectedListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onTimeSelectedListener = listener;
    }

    public final void dismiss() {
        BaseNiceDialog baseNiceDialog = this.dialog;
        if (baseNiceDialog != null) {
            baseNiceDialog.dismiss();
        }
    }
}
