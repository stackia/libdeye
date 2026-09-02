package com.deye.activity.device.appointment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.deye.activity.device.base.BaseActivity;
import com.deye.helper.DialogHelper;
import com.deye.views.AddAppointmentCalendarView;
import com.deye.views.AppointmentModeView;
import com.mxchipapp.databinding.ActivityAddAppointmentBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.SchedulerGroupBean;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddAppointmentAty.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\f\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\rH\u0002J\u0012\u0010\u000f\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\bH\u0002J\b\u0010\u0014\u001a\u00020\rH\u0002J\u0017\u0010\u0015\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002¢\u0006\u0002\u0010\u0018J\u0017\u0010\u0019\u001a\u00020\r2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0017H\u0002¢\u0006\u0002\u0010\u0018J\u0010\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\bH\u0002J\b\u0010\u001d\u001a\u00020\rH\u0002J\b\u0010\u001e\u001a\u00020\rH\u0002J\b\u0010\u001f\u001a\u00020\rH\u0002J\b\u0010 \u001a\u00020\rH\u0002J\b\u0010!\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/deye/activity/device/appointment/AddAppointmentAty;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "binding", "Lcom/mxchipapp/databinding/ActivityAddAppointmentBinding;", "deviceId", "", "isCreate", "", "productId", "schedulerGroupBean", "Lio/fogcloud/sdk/fog/bean/SchedulerGroupBean;", "initData", "", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "saveAppointment", "force", "showConflictDialog", "showHumSetPicker", "curHumSet", "", "(Ljava/lang/Integer;)V", "showSpeedPicker", "curSpeed", "showTimePicker", "isStart", "updateAnionView", "updateHumSetView", "updateModeView", "updateSpeedView", "updateTimeMessageView", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class AddAppointmentAty extends BaseActivity {
    public static final int CODE_RES = 101;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private ActivityAddAppointmentBinding binding;
    private SchedulerGroupBean schedulerGroupBean;
    private String deviceId = "";
    private String productId = "";
    private boolean isCreate = true;

    static {
        StubApp.interface11(14055);
        INSTANCE = new Companion(null);
    }

    private final native void initData();

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$0(AddAppointmentAty addAppointmentAty, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$1(AddAppointmentAty addAppointmentAty, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$2(AddAppointmentAty addAppointmentAty, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$3(AddAppointmentAty addAppointmentAty, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$4(AddAppointmentAty addAppointmentAty, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$5(AddAppointmentAty addAppointmentAty, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void saveAppointment(boolean force);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void showConflictDialog();

    private final native void showHumSetPicker(Integer curHumSet);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void showHumSetPicker$lambda$13(AddAppointmentAty addAppointmentAty, List list, int i, int i2, int i3, View view);

    private final native void showSpeedPicker(Integer curSpeed);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void showSpeedPicker$lambda$15(AddAppointmentAty addAppointmentAty, List list, int i, int i2, int i3, View view);

    private final native void showTimePicker(boolean isStart);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void showTimePicker$lambda$10(SchedulerGroupBean.SchedulerSwitchBean schedulerSwitchBean, AddAppointmentAty addAppointmentAty, boolean z, Date date, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void showTimePicker$lambda$11(Date date);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void showTimePicker$lambda$12(View view);

    private final native void updateAnionView();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void updateHumSetView();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void updateModeView();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void updateSpeedView();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void updateTimeMessageView();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    /* compiled from: AddAppointmentAty.kt */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/deye/activity/device/appointment/AddAppointmentAty$initView$2", "Lcom/deye/views/AppointmentModeView$IModeCheckListener;", "onModeCheck", "", "mode", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.device.appointment.AddAppointmentAty$initView$2, reason: invalid class name */
    public static final class AnonymousClass2 implements AppointmentModeView.IModeCheckListener {
        AnonymousClass2() {
        }

        @Override // com.deye.views.AppointmentModeView.IModeCheckListener
        public void onModeCheck(int mode) {
            SchedulerGroupBean schedulerGroupBean = AddAppointmentAty.this.schedulerGroupBean;
            Intrinsics.checkNotNull(schedulerGroupBean);
            schedulerGroupBean.schedule_on.initCommandsIfNull();
            SchedulerGroupBean schedulerGroupBean2 = AddAppointmentAty.this.schedulerGroupBean;
            Intrinsics.checkNotNull(schedulerGroupBean2);
            schedulerGroupBean2.schedule_on.commands.Mode = mode;
            AddAppointmentAty.this.updateModeView();
            AddAppointmentAty.this.updateTimeMessageView();
            AddAppointmentAty.this.updateHumSetView();
            AddAppointmentAty.this.updateSpeedView();
        }
    }

    /* compiled from: AddAppointmentAty.kt */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/deye/activity/device/appointment/AddAppointmentAty$initView$3", "Lcom/deye/views/AddAppointmentCalendarView$OnDayCheckedListener;", "onCheck", "", "days", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.device.appointment.AddAppointmentAty$initView$3, reason: invalid class name */
    public static final class AnonymousClass3 implements AddAppointmentCalendarView.OnDayCheckedListener {
        AnonymousClass3() {
        }

        @Override // com.deye.views.AddAppointmentCalendarView.OnDayCheckedListener
        public void onCheck(String days) {
            Intrinsics.checkNotNullParameter(days, "days");
            SchedulerGroupBean schedulerGroupBean = AddAppointmentAty.this.schedulerGroupBean;
            Intrinsics.checkNotNull(schedulerGroupBean);
            schedulerGroupBean.day_of_week = days;
            AddAppointmentAty.this.updateTimeMessageView();
        }
    }

    static /* synthetic */ void saveAppointment$default(AddAppointmentAty addAppointmentAty, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        addAppointmentAty.saveAppointment(z);
    }

    /* compiled from: AddAppointmentAty.kt */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/deye/activity/device/appointment/AddAppointmentAty$showConflictDialog$1", "Lcom/deye/helper/DialogHelper$OnDialogListener;", "onSure", "", "text", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.device.appointment.AddAppointmentAty$showConflictDialog$1, reason: invalid class name */
    public static final class AnonymousClass1 extends DialogHelper.OnDialogListener {
        AnonymousClass1() {
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String text) {
            AddAppointmentAty.this.saveAppointment(true);
        }
    }

    /* compiled from: AddAppointmentAty.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\rJ&\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/deye/activity/device/appointment/AddAppointmentAty$Companion;", "", "()V", "CODE_RES", "", "openCreate", "", "context", "Landroid/app/Activity;", "productId", "", "deviceId", "dayId", "(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "openEdit", "schedulerGroupBean", "Lio/fogcloud/sdk/fog/bean/SchedulerGroupBean;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void openCreate(Activity context, String productId, String deviceId, Integer dayId) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(productId, "productId");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            Intent intent = new Intent(context, (Class<?>) AddAppointmentAty.class);
            intent.putExtra(StubApp.getString2(13055), deviceId);
            intent.putExtra(StubApp.getString2(13306), productId);
            intent.putExtra(StubApp.getString2(13542), true);
            if (dayId != null) {
                intent.putExtra(StubApp.getString2(13543), dayId.intValue());
            }
            context.startActivityForResult(intent, 100);
        }

        public final void openEdit(Activity context, String productId, String deviceId, SchedulerGroupBean schedulerGroupBean) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(productId, "productId");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            Intrinsics.checkNotNullParameter(schedulerGroupBean, "schedulerGroupBean");
            Intent intent = new Intent(context, (Class<?>) AddAppointmentAty.class);
            intent.putExtra(StubApp.getString2(13055), deviceId);
            intent.putExtra(StubApp.getString2(13306), productId);
            intent.putExtra(StubApp.getString2(13542), false);
            intent.putExtra(StubApp.getString2(13544), schedulerGroupBean);
            context.startActivityForResult(intent, 100);
        }
    }
}
