package com.deye;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.deye.TabMainActivity;
import com.deye.activity.device.base.BaseActivity;
import com.deye.activity.login.ModifyPasswordAty;
import com.deye.activity.login.OverseaRegisterActivity;
import com.deye.entity.UserInfoBean;
import com.deye.entity.UserInfoResult;
import com.deye.event.AcceptEvent;
import com.deye.event.ReceiveMessageEvent;
import com.deye.fragment.EquipmentFragment;
import com.deye.helper.AppUpgradeHelper;
import com.deye.helper.DialogHelper;
import com.deye.helper.UserAvatarHelper;
import com.deye.utils.ChannelUtil;
import com.deye.utils.MMKVUtils;
import com.deye.viewmodels.MainViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.OnPermissionInterceptor;
import com.mxchipapp.R;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.bean.GlobalConfigBean;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import io.fogcloud.sdk.fog.log.LogUtil;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: TabMainActivity.kt */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 E2\u00020\u0001:\u0001EB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010#\u001a\u00020$H\u0002J\u0006\u0010%\u001a\u00020$J\u0006\u0010&\u001a\u00020$J\b\u0010'\u001a\u00020$H\u0016J\b\u0010(\u001a\u00020$H\u0002J\u000e\u0010)\u001a\b\u0012\u0004\u0012\u00020 0*H\u0014J\b\u0010+\u001a\u00020$H\u0003J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0004H\u0002J\u0006\u0010/\u001a\u00020$J\b\u00100\u001a\u00020$H\u0002J\u0010\u00101\u001a\u00020$2\u0006\u00102\u001a\u000203H\u0007J\u0012\u00104\u001a\u00020$2\b\u00105\u001a\u0004\u0018\u000106H\u0016J\b\u00107\u001a\u00020$H\u0014J\u0018\u00108\u001a\u00020\u00152\u0006\u00109\u001a\u00020\u00042\u0006\u0010:\u001a\u00020;H\u0016J\b\u0010<\u001a\u00020$H\u0014J\b\u0010=\u001a\u00020$H\u0002J\u0006\u0010>\u001a\u00020$J\b\u0010?\u001a\u00020$H\u0002J\u0010\u0010@\u001a\u00020$2\u0006\u0010.\u001a\u00020\u0004H\u0002J\u0010\u0010A\u001a\u00020$2\u0006\u0010B\u001a\u00020CH\u0007J\b\u0010D\u001a\u00020$H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u0004\u0018\u00010\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u0082.¢\u0006\u0004\n\u0002\u0010!R\u0018\u0010\"\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010!¨\u0006F"}, d2 = {"Lcom/deye/TabMainActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "currentIndex", "", "deviceCount", "getDeviceCount", "()I", "deviceList", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "getDeviceList", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "equipmentFragment", "Lcom/deye/fragment/EquipmentFragment;", "getEquipmentFragment", "()Lcom/deye/fragment/EquipmentFragment;", "fragments", "", "Landroidx/fragment/app/Fragment;", "isExit", "", "layoutInflater", "Landroid/view/LayoutInflater;", "mImageArray", "", "getMImageArray", "()[I", "mMainViewModel", "Lcom/deye/viewmodels/MainViewModel;", "mTextviewArray", "", "", "[Ljava/lang/String;", "mValuesArray", "checkLocationPermission", "", "checkNotificationPermission", "exit", "finishActivityOrRefreshUIForRemovedDevice", "getAppVersion", "getCurrentDeviceId", "", "getGlobalConfig", "getTabItemView", "Landroid/view/View;", "index", "getUserInfo", "initView", "onAcceptEvent", "acceptEvent", "Lcom/deye/event/AcceptEvent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onKeyDown", "keyCode", "event", "Landroid/view/KeyEvent;", "onResume", "showLocationPermissionDialog", "showNotificationPerDialog", "startHa", "switchToFragment", "updateMessageRedDot", "receiveMessageEvent", "Lcom/deye/event/ReceiveMessageEvent;", "uploadClientID", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class TabMainActivity extends BaseActivity {
    public static final String TAG = StubApp.getString2(13210);
    private int currentIndex = -1;
    private final Map<Integer, Fragment> fragments = new LinkedHashMap();
    private boolean isExit;
    private LayoutInflater layoutInflater;
    private final int[] mImageArray;
    private MainViewModel mMainViewModel;
    private String[] mTextviewArray;
    private String[] mValuesArray;

    /* compiled from: TabMainActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.TabMainActivity$getGlobalConfig$2, reason: invalid class name and case insensitive filesystem */
    static final class C01472<T> implements Consumer {
        public static final C01472<T> INSTANCE = new C01472<>();

        C01472() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Throwable it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
        }
    }

    static {
        StubApp.interface11(13847);
        INSTANCE = new Companion(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final native void checkLocationPermission();

    private final native void getAppVersion();

    /* JADX INFO: Access modifiers changed from: private */
    public final native EquipmentFragment getEquipmentFragment();

    private final native void getGlobalConfig();

    private final native View getTabItemView(int index);

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onCreate$lambda$0(TabMainActivity tabMainActivity);

    private final native void showLocationPermissionDialog();

    private final native void startHa();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void switchToFragment(int index);

    private final native void uploadClientID();

    public final native void checkNotificationPermission();

    public final native void exit();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void finishActivityOrRefreshUIForRemovedDevice();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native List<String> getCurrentDeviceId();

    public final native int getDeviceCount();

    public final native CopyOnWriteArrayList<DeviceListBean> getDeviceList();

    public final native int[] getMImageArray();

    public final native void getUserInfo();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final native void onAcceptEvent(AcceptEvent acceptEvent);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();

    @Override // com.deye.activity.device.base.BaseActivity
    public native boolean onKeyDown(int keyCode, KeyEvent event);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onResume();

    public final native void showNotificationPerDialog();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final native void updateMessageRedDot(ReceiveMessageEvent receiveMessageEvent);

    public TabMainActivity() {
        int[] iArr;
        if (!ChannelUtil.isOversea()) {
            iArr = new int[]{R.drawable.maintab_1_selector, R.drawable.maintab_2_selector, R.drawable.maintab_3_selector};
        } else {
            iArr = new int[]{R.drawable.maintab_1_selector, R.drawable.maintab_3_selector};
        }
        this.mImageArray = iArr;
    }

    /* compiled from: TabMainActivity.kt */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/deye/TabMainActivity$onCreate$2", "Lcom/deye/helper/DialogHelper$OnDialogListener;", "onSure", "", "text", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.TabMainActivity$onCreate$2, reason: invalid class name and case insensitive filesystem */
    public static final class C01502 extends DialogHelper.OnDialogListener {
        C01502() {
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String text) {
            if (ChannelUtil.isOversea()) {
                Intent intent = new Intent((Context) TabMainActivity.this.mContext, (Class<?>) OverseaRegisterActivity.class);
                intent.putExtra(StubApp.getString2(112), StubApp.getString2(13207));
                TabMainActivity.this.startActivity(intent);
                return;
            }
            TabMainActivity.this.startActivity(new Intent((Context) TabMainActivity.this.mContext, (Class<?>) ModifyPasswordAty.class));
        }
    }

    /* compiled from: TabMainActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/GlobalConfigBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.TabMainActivity$getGlobalConfig$1, reason: invalid class name and case insensitive filesystem */
    static final class C01461<T> implements Consumer {
        public static final C01461<T> INSTANCE = new C01461<>();

        C01461() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(BaseResult<GlobalConfigBean> it2) {
            GlobalConfigBean data;
            Intrinsics.checkNotNullParameter(it2, "it");
            BaseResult.MetaBean meta = it2.getMeta();
            if (meta == null || meta.getCode() != 0 || (data = it2.getData()) == null) {
                return;
            }
            MMKVUtils.INSTANCE.setGlobalConfig(data);
        }
    }

    /* compiled from: TabMainActivity.kt */
    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"com/deye/TabMainActivity$checkLocationPermission$1", "Lcom/hjq/permissions/OnPermissionInterceptor;", "launchPermissionRequest", "", "activity", "Landroid/app/Activity;", "allPermissions", "", "", "callback", "Lcom/hjq/permissions/OnPermissionCallback;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.TabMainActivity$checkLocationPermission$1, reason: invalid class name */
    public static final class AnonymousClass1 implements OnPermissionInterceptor {
        AnonymousClass1() {
        }

        public void launchPermissionRequest(Activity activity, List<String> allPermissions, OnPermissionCallback callback) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(allPermissions, "allPermissions");
            super.launchPermissionRequest(activity, allPermissions, callback);
        }
    }

    /* compiled from: TabMainActivity.kt */
    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\t\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u000b\u001a\u00020\bH\u0016¨\u0006\f"}, d2 = {"com/deye/TabMainActivity$checkLocationPermission$2", "Lcom/hjq/permissions/OnPermissionCallback;", "onDenied", "", "permissions", "", "", "doNotAskAgain", "", "onGranted", "list", "b", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.TabMainActivity$checkLocationPermission$2, reason: invalid class name */
    public static final class AnonymousClass2 implements OnPermissionCallback {
        AnonymousClass2() {
        }

        public void onGranted(List<String> list, boolean b) {
            MainViewModel mMainViewModel;
            Intrinsics.checkNotNullParameter(list, "list");
            LogUtil.d(StubApp.getString2(13200), StubApp.getString2(13201));
            EquipmentFragment equipmentFragment = TabMainActivity.this.getEquipmentFragment();
            if (equipmentFragment == null || (mMainViewModel = equipmentFragment.getMMainViewModel()) == null) {
                return;
            }
            mMainViewModel.requestLocation();
        }

        public void onDenied(List<String> permissions, boolean doNotAskAgain) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            LogUtil.d(StubApp.getString2(13200), StubApp.getString2(13201));
        }
    }

    /* compiled from: TabMainActivity.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/deye/TabMainActivity$showLocationPermissionDialog$1", "Lcom/deye/helper/DialogHelper$OnDialogListener;", "onCancel", "", "onSure", "text", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.TabMainActivity$showLocationPermissionDialog$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01511 extends DialogHelper.OnDialogListener {
        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onCancel() {
        }

        C01511() {
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            TabMainActivity.this.checkLocationPermission();
        }
    }

    /* compiled from: TabMainActivity.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/deye/TabMainActivity$showNotificationPerDialog$1", "Lcom/deye/helper/DialogHelper$OnDialogListener;", "onCancel", "", "onSure", "text", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.TabMainActivity$showNotificationPerDialog$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01521 extends DialogHelper.OnDialogListener {
        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onCancel() {
        }

        C01521() {
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            TabMainActivity.this.checkNotificationPermission();
        }
    }

    /* compiled from: TabMainActivity.kt */
    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\t\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\n\u001a\u00020\bH\u0016¨\u0006\u000b"}, d2 = {"com/deye/TabMainActivity$checkNotificationPermission$1", "Lcom/hjq/permissions/OnPermissionCallback;", "onDenied", "", "permissions", "", "", "doNotAskAgain", "", "onGranted", "allGranted", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.TabMainActivity$checkNotificationPermission$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01431 implements OnPermissionCallback {
        C01431() {
        }

        public void onGranted(List<String> permissions, boolean allGranted) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            LogUtil.d(StubApp.getString2(13203), StubApp.getString2(13201));
        }

        public void onDenied(List<String> permissions, boolean doNotAskAgain) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            LogUtil.d(StubApp.getString2(13203), StubApp.getString2(13202) + doNotAskAgain);
        }
    }

    /* compiled from: TabMainActivity.kt */
    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J=\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0016¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"com/deye/TabMainActivity$getAppVersion$1", "Lcom/deye/helper/AppUpgradeHelper$ICheckAppUpgradeListener;", "onFailure", "", "onSuccess", "version", "", "isNeedUpgrade", "", "tipArr", "", "showOnLaunch", "forceUpdate", "(Ljava/lang/String;Z[Ljava/lang/String;ZZ)V", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.TabMainActivity$getAppVersion$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01451 implements AppUpgradeHelper.ICheckAppUpgradeListener {
        @Override // com.deye.helper.AppUpgradeHelper.ICheckAppUpgradeListener
        public void onFailure() {
        }

        C01451() {
        }

        @Override // com.deye.helper.AppUpgradeHelper.ICheckAppUpgradeListener
        public void onSuccess(final String version, boolean isNeedUpgrade, String[] tipArr, boolean showOnLaunch, boolean forceUpdate) {
            Intrinsics.checkNotNullParameter(version, "version");
            if (isNeedUpgrade) {
                if (showOnLaunch || forceUpdate) {
                    boolean z = MMKV.mmkvWithID(StubApp.getString2(7195)).getBoolean(version, false);
                    if (forceUpdate || !z) {
                        DialogHelper.updateVersionDialog(TabMainActivity.this.mContext, tipArr, Boolean.valueOf(forceUpdate), new View.OnClickListener() { // from class: com.deye.TabMainActivity$getAppVersion$1$$ExternalSyntheticLambda0
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                TabMainActivity.C01451.onSuccess$lambda$0(version, view);
                            }
                        });
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSuccess$lambda$0(String version, View view) {
            Intrinsics.checkNotNullParameter(version, "$version");
            MMKV.mmkvWithID(StubApp.getString2(7195)).putBoolean(version, true);
        }
    }

    /* compiled from: TabMainActivity.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"com/deye/TabMainActivity$getUserInfo$1", "Lio/fogcloud/sdk/fog/callback/FogCallBack;", "onFailure", "", "code", "", "message", "", "onSuccess", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.TabMainActivity$getUserInfo$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01481 implements FogCallBack {
        C01481() {
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onSuccess(String message) {
            String string2 = StubApp.getString2(13205);
            Intrinsics.checkNotNullParameter(message, "message");
            try {
                LogUtil.d(StubApp.getString2("13206"), message);
                UserInfoBean data = ((UserInfoResult) new Gson().fromJson(message, UserInfoResult.class)).getData();
                MMKVUtils.INSTANCE.setUserInfo(data);
                String avatar = data.getAvatar();
                if (!Intrinsics.areEqual("", avatar)) {
                    UserAvatarHelper.downLoadFileToSD(avatar, UserAvatarHelper.getAvatarCachePath(StubApp.getOrigApplicationContext(MxchipApplication.getInstance().getApplicationContext())));
                }
                LogUtil.d(string2 + data.getNickname());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onFailure(int code, String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            LogUtil.d(StubApp.getString2(13204), message);
        }
    }

    /* compiled from: TabMainActivity.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\b"}, d2 = {"com/deye/TabMainActivity$initView$1", "Lcom/google/android/material/tabs/TabLayout$OnTabSelectedListener;", "onTabReselected", "", "tab", "Lcom/google/android/material/tabs/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.TabMainActivity$initView$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01491 implements TabLayout.OnTabSelectedListener {
        public void onTabReselected(TabLayout.Tab tab) {
        }

        public void onTabUnselected(TabLayout.Tab tab) {
        }

        C01491() {
        }

        public void onTabSelected(TabLayout.Tab tab) {
            Intrinsics.checkNotNullParameter(tab, "tab");
            TabMainActivity.this.switchToFragment(tab.getPosition());
        }
    }

    /* compiled from: TabMainActivity.kt */
    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/deye/TabMainActivity$exit$1", "Ljava/util/TimerTask;", "run", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.TabMainActivity$exit$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01441 extends TimerTask {
        C01441() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            TabMainActivity.this.isExit = false;
        }
    }

    /* compiled from: TabMainActivity.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"com/deye/TabMainActivity$uploadClientID$1", "Lio/fogcloud/sdk/fog/callback/FogCallBack;", "onFailure", "", "code", "", "message", "", "onSuccess", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.TabMainActivity$uploadClientID$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01531 implements FogCallBack {
        C01531() {
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onSuccess(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Log.d(StubApp.getString2(13210), StubApp.getString2(13211));
            TabMainActivity.this.setUploadCid(true);
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onFailure(int code, String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Log.d(StubApp.getString2(13210), StubApp.getString2(13208) + code + StubApp.getString2(13209) + message);
        }
    }
}
