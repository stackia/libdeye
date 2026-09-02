package com.deye.activity.device;

import android.os.Bundle;
import android.view.View;
import com.deye.activity.device.base.BaseActivity;
import com.deye.helper.AddScheduelrAtyHelper;
import com.mxchipapp.databinding.AddSchedulerAtyBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.SchedulerBean;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* compiled from: AddSchedulerAty.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010=\u001a\u00020>H\u0002J\b\u0010?\u001a\u00020>H\u0016J\u000e\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00160AH\u0014J\b\u0010B\u001a\u00020>H\u0002J\b\u0010C\u001a\u00020>H\u0002J\u0010\u0010D\u001a\u00020>2\u0006\u0010E\u001a\u00020FH\u0016J\u0012\u0010G\u001a\u00020>2\b\u0010H\u001a\u0004\u0018\u00010IH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010$\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0018\"\u0004\b&\u0010\u001aR\u001c\u0010'\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0018\"\u0004\b)\u0010\u001aR\u001c\u0010*\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0018\"\u0004\b,\u0010\u001aR\u001c\u0010-\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0018\"\u0004\b/\u0010\u001aR\u001c\u00100\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0018\"\u0004\b2\u0010\u001aR*\u00103\u001a\u0012\u0012\u0004\u0012\u00020\u001604j\b\u0012\u0004\u0012\u00020\u0016`5X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001c\u0010:\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0018\"\u0004\b<\u0010\u001a¨\u0006J"}, d2 = {"Lcom/deye/activity/device/AddSchedulerAty;", "Lcom/deye/activity/device/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "isCreate", "", "()Z", "setCreate", "(Z)V", "mAddScheduelrAtyHelper", "Lcom/deye/helper/AddScheduelrAtyHelper;", "getMAddScheduelrAtyHelper", "()Lcom/deye/helper/AddScheduelrAtyHelper;", "setMAddScheduelrAtyHelper", "(Lcom/deye/helper/AddScheduelrAtyHelper;)V", "mAddSchedulerAtyBinding", "Lcom/mxchipapp/databinding/AddSchedulerAtyBinding;", "getMAddSchedulerAtyBinding", "()Lcom/mxchipapp/databinding/AddSchedulerAtyBinding;", "setMAddSchedulerAtyBinding", "(Lcom/mxchipapp/databinding/AddSchedulerAtyBinding;)V", "mDeviceId", "", "getMDeviceId", "()Ljava/lang/String;", "setMDeviceId", "(Ljava/lang/String;)V", "mProductId", "getMProductId", "setMProductId", "mSchedulerBean", "Lio/fogcloud/sdk/fog/bean/SchedulerBean;", "getMSchedulerBean", "()Lio/fogcloud/sdk/fog/bean/SchedulerBean;", "setMSchedulerBean", "(Lio/fogcloud/sdk/fog/bean/SchedulerBean;)V", "mSchedulerCommands", "getMSchedulerCommands", "setMSchedulerCommands", "mSchedulerGroupId", "getMSchedulerGroupId", "setMSchedulerGroupId", "mSchedulerHour", "getMSchedulerHour", "setMSchedulerHour", "mSchedulerMinute", "getMSchedulerMinute", "setMSchedulerMinute", "mSchedulerName", "getMSchedulerName", "setMSchedulerName", "mSelectedWeekList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getMSelectedWeekList", "()Ljava/util/ArrayList;", "setMSelectedWeekList", "(Ljava/util/ArrayList;)V", "mSelectedWeeks", "getMSelectedWeeks", "setMSelectedWeeks", "createTiming", "", "finishActivityOrRefreshUIForRemovedDevice", "getCurrentDeviceId", "", "initData", "initView", "onClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class AddSchedulerAty extends BaseActivity implements View.OnClickListener {
    private boolean isCreate;
    private AddScheduelrAtyHelper mAddScheduelrAtyHelper;
    public AddSchedulerAtyBinding mAddSchedulerAtyBinding;
    private String mDeviceId;
    private String mProductId;
    private SchedulerBean mSchedulerBean;
    private String mSchedulerCommands;
    private String mSchedulerGroupId;
    private String mSchedulerHour;
    private String mSchedulerMinute;
    private String mSchedulerName;
    private ArrayList<String> mSelectedWeekList = new ArrayList<>();
    private String mSelectedWeeks;

    static {
        StubApp.interface11(13980);
    }

    private final native void createTiming();

    private final native void initData();

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$0(AddSchedulerAty addSchedulerAty, View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void finishActivityOrRefreshUIForRemovedDevice();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native List<String> getCurrentDeviceId();

    public final native AddScheduelrAtyHelper getMAddScheduelrAtyHelper();

    public final native AddSchedulerAtyBinding getMAddSchedulerAtyBinding();

    public final native String getMDeviceId();

    public final native String getMProductId();

    public final native SchedulerBean getMSchedulerBean();

    public final native String getMSchedulerCommands();

    public final native String getMSchedulerGroupId();

    public final native String getMSchedulerHour();

    public final native String getMSchedulerMinute();

    public final native String getMSchedulerName();

    public final native ArrayList<String> getMSelectedWeekList();

    public final native String getMSelectedWeeks();

    public final native boolean isCreate();

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    public final native void setCreate(boolean z);

    public final native void setMAddScheduelrAtyHelper(AddScheduelrAtyHelper addScheduelrAtyHelper);

    public final native void setMAddSchedulerAtyBinding(AddSchedulerAtyBinding addSchedulerAtyBinding);

    public final native void setMDeviceId(String str);

    public final native void setMProductId(String str);

    public final native void setMSchedulerBean(SchedulerBean schedulerBean);

    public final native void setMSchedulerCommands(String str);

    public final native void setMSchedulerGroupId(String str);

    public final native void setMSchedulerHour(String str);

    public final native void setMSchedulerMinute(String str);

    public final native void setMSchedulerName(String str);

    public final native void setMSelectedWeekList(ArrayList<String> arrayList);

    public final native void setMSelectedWeeks(String str);
}
