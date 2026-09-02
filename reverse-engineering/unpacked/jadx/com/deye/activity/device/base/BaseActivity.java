package com.deye.activity.device.base;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import androidx.fragment.app.FragmentActivity;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.deye.broadcast_receiver.NetStatusReceiver;
import com.deye.event.UpdateDeviceEvent;
import com.deye.helper.DialogHelper;
import com.deye.receiver.OffLineBroadcastReceiver;
import com.deye.utils.ActivityRouterUtilsKt;
import com.deye.utils.BaseUtils;
import com.deye.utils.LanUtils;
import com.deye.views.LVCircularRing;
import com.deye.views.dialog.LoadingDialog;
import com.mxchipapp.R;
import com.samluys.statusbar.StatusBarUtils;
import com.stub.StubApp;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: BaseActivity.kt */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000 k2\u00020\u0001:\u0001kB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0014J\u000e\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020\u001eJ\b\u0010.\u001a\u00020)H\u0016J\b\u0010/\u001a\u00020)H\u0016J\u0006\u00100\u001a\u00020)J\u000e\u00101\u001a\b\u0012\u0004\u0012\u00020\r02H\u0014J\b\u00103\u001a\u000204H\u0016J\u0010\u0010&\u001a\u00020%2\u0006\u00105\u001a\u00020%H\u0002J\u0006\u00106\u001a\u00020\u001eJ\u0018\u00107\u001a\u00020)2\u0006\u00108\u001a\u00020\u00002\u0006\u00109\u001a\u00020\u0004H\u0004J\u0010\u0010:\u001a\u00020)2\u0006\u0010;\u001a\u00020<H\u0004J\u0010\u0010=\u001a\u00020)2\u0006\u0010;\u001a\u00020<H\u0004J\u0006\u0010>\u001a\u00020)J\u0006\u0010?\u001a\u00020)J\u0006\u0010@\u001a\u00020)J\u0012\u0010A\u001a\u00020)2\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\u0010\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020FH\u0016J\b\u0010G\u001a\u00020)H\u0014J\u0018\u0010H\u001a\u00020)2\b\u0010I\u001a\u0004\u0018\u00010\r2\u0006\u0010J\u001a\u00020KJ\u0010\u0010L\u001a\u00020)2\u0006\u0010M\u001a\u00020NH\u0007J\u0018\u0010O\u001a\u00020\u00042\u0006\u0010P\u001a\u00020\u001e2\u0006\u0010Q\u001a\u00020RH\u0016J\b\u0010S\u001a\u00020)H\u0015J\b\u0010T\u001a\u00020)H\u0014J\b\u0010U\u001a\u00020)H\u0014J\u0006\u0010V\u001a\u00020)J\"\u0010W\u001a\u00020)2\u0006\u00108\u001a\u00020\u00002\b\u0010X\u001a\u0004\u0018\u00010\r2\b\u0010Y\u001a\u0004\u0018\u00010\rJ\u0010\u0010Z\u001a\u00020)2\u0006\u0010;\u001a\u00020<H\u0002J\u001a\u0010[\u001a\u00020)2\u0006\u0010\\\u001a\u00020+2\n\b\u0002\u0010]\u001a\u0004\u0018\u00010\rJ\u0010\u0010^\u001a\u00020)2\b\u0010_\u001a\u0004\u0018\u00010\rJ\u0018\u0010^\u001a\u00020)2\b\u0010_\u001a\u0004\u0018\u00010\r2\u0006\u0010`\u001a\u00020\u0004J\u0012\u0010a\u001a\u00020)2\b\u0010b\u001a\u0004\u0018\u00010cH\u0016J\u0010\u0010d\u001a\u00020)2\b\u0010b\u001a\u0004\u0018\u00010cJ\u0006\u0010e\u001a\u00020)J\u0006\u0010f\u001a\u00020)J\u000e\u0010f\u001a\u00020)2\u0006\u0010b\u001a\u00020cJ\u000e\u0010f\u001a\u00020)2\u0006\u0010g\u001a\u00020\u0004J \u0010h\u001a\u00020)2\u000e\u0010i\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u0001022\u0006\u0010j\u001a\u00020\rH\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005R\u001a\u0010\u0006\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0005\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\u00008\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\r8\u0004@\u0004X\u0085\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001f\u001a\u00020\u001e8F¢\u0006\u0006\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020\u001e8F¢\u0006\u0006\u001a\u0004\b#\u0010!R\u0014\u0010$\u001a\u00020%8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u0006l"}, d2 = {"Lcom/deye/activity/device/base/BaseActivity;", "Landroidx/fragment/app/FragmentActivity;", "()V", "isShowNavBar", "", "()Z", "isUploadCid", "setUploadCid", "(Z)V", "loadingDialog", "Lcom/deye/views/dialog/LoadingDialog;", "mContext", "mErrorCodeKey", "", "mLoadingDialog", "getMLoadingDialog", "()Lcom/deye/views/dialog/LoadingDialog;", "setMLoadingDialog", "(Lcom/deye/views/dialog/LoadingDialog;)V", "mLoadingView", "Lcom/deye/views/LVCircularRing;", "getMLoadingView", "()Lcom/deye/views/LVCircularRing;", "setMLoadingView", "(Lcom/deye/views/LVCircularRing;)V", "mNetStatusReceiver", "Lcom/deye/broadcast_receiver/NetStatusReceiver;", "mOffLineBroadcastReceiver", "Lcom/deye/receiver/OffLineBroadcastReceiver;", "mOnStartCount", "", "navigationBarHeight", "getNavigationBarHeight", "()I", "realHeight", "getRealHeight", "rootContext", "Landroid/app/Activity;", "getRootContext", "()Landroid/app/Activity;", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "baseSetContentView", "layoutResId", "finish", "finishActivityOrRefreshUIForRemovedDevice", "finishNoAnim", "getCurrentDeviceId", "", "getResources", "Landroid/content/res/Resources;", "act", "getStatusBarHeight", "goLoginPage", "activity", "isFirstPage", "hideInput", "view", "Landroid/view/View;", "hideKeyboard", "hideLoading", "keyDownToExit", "logout", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onDestroy", "onDeviceRemoveHintDialog", "noticeText", "listener", "Lcom/deye/helper/DialogHelper$OnDialogListener;", "onEventUpdateBoundDeviceList", "updateDeviceEvent", "Lcom/deye/event/UpdateDeviceEvent;", "onKeyDown", "keyCode", "event", "Landroid/view/KeyEvent;", "onResume", "onStart", "onStop", "registerNetStatusReceiver", "setOffLineTipDialog", "tipFlag", "content", "showKeyBoard", "showLoading", "context", "message", "showWaiting", "msg", "isCancelable", "startActivity", "intent", "Landroid/content/Intent;", "startActivityWithFadeIn", "stopWaiting", "toTabMainPage", "isSplash", "updateUI", "deviceIdList", "unBoundDevice", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class BaseActivity extends FragmentActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = StubApp.getString2(13554);
    private static String className = "";
    private boolean isUploadCid;
    private LoadingDialog loadingDialog;
    public BaseActivity mContext = this;
    protected String mErrorCodeKey = StubApp.getString2(75);
    private LoadingDialog mLoadingDialog;
    private LVCircularRing mLoadingView;
    private NetStatusReceiver mNetStatusReceiver;
    private OffLineBroadcastReceiver mOffLineBroadcastReceiver;
    private int mOnStartCount;

    public void finishActivityOrRefreshUIForRemovedDevice() {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        return true;
    }

    public final void showWaiting(String msg) {
    }

    public final LVCircularRing getMLoadingView() {
        return this.mLoadingView;
    }

    public final void setMLoadingView(LVCircularRing lVCircularRing) {
        this.mLoadingView = lVCircularRing;
    }

    public final LoadingDialog getMLoadingDialog() {
        return this.mLoadingDialog;
    }

    public final void setMLoadingDialog(LoadingDialog loadingDialog) {
        this.mLoadingDialog = loadingDialog;
    }

    /* renamed from: isUploadCid, reason: from getter */
    protected final boolean getIsUploadCid() {
        return this.isUploadCid;
    }

    protected final void setUploadCid(boolean z) {
        this.isUploadCid = z;
    }

    private final void showKeyBoard(View view) {
        Object systemService = view.getContext().getSystemService(StubApp.getString2(2742));
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).showSoftInput(view, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void startActivity(Intent intent) {
        super.startActivity(intent, ActivityOptions.makeCustomAnimation((Context) this, R.anim.slide_in_right, R.anim.slide_out_left).toBundle());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void startActivityWithFadeIn(Intent intent) {
        super.startActivity(intent, ActivityOptions.makeCustomAnimation((Context) this, R.anim.fade_in, R.anim.fade_out).toBundle());
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public final void finishNoAnim() {
        super.finish();
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanUtils.setAppLanguage(newBase));
    }

    public static /* synthetic */ void showLoading$default(BaseActivity baseActivity, Context context, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException(StubApp.getString2(13555));
        }
        if ((i & 2) != 0) {
            str = null;
        }
        baseActivity.showLoading(context, str);
    }

    public final void showLoading(Context context, String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        LoadingDialog loadingDialog = new LoadingDialog(context, message);
        this.loadingDialog = loadingDialog;
        loadingDialog.show();
    }

    public final void hideLoading() {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            return;
        }
        loadingDialog.dismiss();
    }

    protected final void hideKeyboard(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        Object systemService = getSystemService(StubApp.getString2(2742));
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public final int getStatusBarHeight() throws Resources.NotFoundException {
        Resources resources = getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(resources.getIdentifier(StubApp.getString2(1326), StubApp.getString2(1325), StubApp.getString2(827)));
        LogUtil.v(StubApp.getString2(13559), StubApp.getString2(13558) + dimensionPixelSize);
        return dimensionPixelSize;
    }

    protected List<String> getCurrentDeviceId() {
        return new ArrayList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onEventUpdateBoundDeviceList(UpdateDeviceEvent updateDeviceEvent) {
        Intrinsics.checkNotNullParameter(updateDeviceEvent, "updateDeviceEvent");
        String str = updateDeviceEvent.deviceId;
        List<String> currentDeviceId = getCurrentDeviceId();
        Intrinsics.checkNotNull(str);
        updateUI(currentDeviceId, str);
    }

    private final void updateUI(List<String> deviceIdList, String unBoundDevice) {
        if (deviceIdList == null || deviceIdList.size() == 0) {
            return;
        }
        Log.d(TAG, StubApp.getString2(13556) + unBoundDevice);
        int size = deviceIdList.size();
        for (int i = 0; i < size; i++) {
            if (Intrinsics.areEqual(unBoundDevice, deviceIdList.get(i))) {
                finishActivityOrRefreshUIForRemovedDevice();
            }
        }
    }

    public final void onDeviceRemoveHintDialog(String noticeText, DialogHelper.OnDialogListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        DialogHelper.deviceUnboundNoticeDialog(this, noticeText, listener);
    }

    public final int getNavigationBarHeight() {
        if (isShowNavBar()) {
            Resources resources = getResources();
            int identifier = resources.getIdentifier(StubApp.getString2(10280), StubApp.getString2(1325), StubApp.getString2(827));
            if (identifier > 0) {
                return resources.getDimensionPixelSize(identifier);
            }
        }
        return 0;
    }

    public final boolean isShowNavBar() {
        Rect rect = new Rect();
        try {
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            return rect.height() != getRealHeight() - getStatusBarHeight();
        } catch (ClassCastException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final int getRealHeight() {
        Object systemService = getSystemService(StubApp.getString2(13557));
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        Point point = new Point();
        ((WindowManager) systemService).getDefaultDisplay().getRealSize(point);
        return point.y;
    }

    protected void onStart() {
        super.onStart();
        int i = this.mOnStartCount;
        if (i < 1) {
            this.mOnStartCount = i + 1;
            View viewFindViewById = findViewById(R.id.actionbar);
            if (viewFindViewById != null) {
                ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
                layoutParams.height += getStatusBarHeight();
                viewFindViewById.setLayoutParams(layoutParams);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onCreate(Bundle savedInstanceState) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        requestWindowFeature(1);
        getWindow().addFlags(67108864);
        Activity activity = (Activity) this;
        StatusBarUtils.transparencyBar(activity);
        StatusBarUtils.StatusBarIconDark(activity);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(1);
        this.mContext = this;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(StubApp.getString2(13560));
        this.mOffLineBroadcastReceiver = new OffLineBroadcastReceiver();
        if (Build.VERSION.SDK_INT >= 26) {
            registerReceiver(this.mOffLineBroadcastReceiver, intentFilter, 2);
        } else {
            registerReceiver(this.mOffLineBroadcastReceiver, intentFilter);
        }
        registerNetStatusReceiver();
        EventBus.getDefault().register(this);
    }

    protected void onResume() {
        super.onResume();
    }

    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration configuration = new Configuration();
        configuration.setToDefaults();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        Intrinsics.checkNotNull(resources);
        return resources;
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mOnStartCount = 0;
        stopWaiting();
        unregisterReceiver(this.mOffLineBroadcastReceiver);
        unregisterReceiver(this.mNetStatusReceiver);
        EventBus.getDefault().unregister(this);
        if (this.mLoadingView != null) {
            this.mLoadingView = null;
        }
    }

    protected void onStop() {
        super.onStop();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected final Activity getRootContext() {
        return getRootContext((Activity) this);
    }

    private final Activity getRootContext(Activity act) {
        if (!act.isChild()) {
            return act;
        }
        Activity parent = act.getParent();
        Intrinsics.checkNotNullExpressionValue(parent, "getParent(...)");
        return getRootContext(parent);
    }

    protected final void hideInput(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        Object systemService = getSystemService(StubApp.getString2(2742));
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (keyCode == 4 && isChild()) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public final void baseSetContentView(int layoutResId) {
        setContentView(layoutResId);
    }

    public final void keyDownToExit() {
        Intent intent = new Intent(StubApp.getString2(7436));
        intent.setFlags(CommonNetImpl.FLAG_AUTH);
        intent.addCategory(StubApp.getString2(10273));
        startActivity(intent);
    }

    public final void showWaiting(String msg, boolean isCancelable) {
        stopWaiting();
        FragmentActivity fragmentActivity = this.mContext;
        Intrinsics.checkNotNull(fragmentActivity);
        LoadingDialog loadingDialog = new LoadingDialog((Context) fragmentActivity, msg);
        this.mLoadingDialog = loadingDialog;
        Intrinsics.checkNotNull(loadingDialog);
        loadingDialog.setCanceledOnTouchOutside(isCancelable);
        LoadingDialog loadingDialog2 = this.mLoadingDialog;
        Intrinsics.checkNotNull(loadingDialog2);
        loadingDialog2.show();
    }

    public final void stopWaiting() {
        if (BaseUtils.isNull(this.mLoadingDialog)) {
            return;
        }
        LoadingDialog loadingDialog = this.mLoadingDialog;
        Intrinsics.checkNotNull(loadingDialog);
        loadingDialog.dismiss();
        this.mLoadingDialog = null;
    }

    public final void setOffLineTipDialog(final BaseActivity activity, String tipFlag, String content) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (Intrinsics.areEqual("HomePageActivity", activity.getClass().getSimpleName())) {
            return;
        }
        DialogHelper.showUserOffLineDialog(activity, tipFlag, content, new DialogHelper.OnDialogListener() { // from class: com.deye.activity.device.base.BaseActivity.setOffLineTipDialog.1
            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onCancel() {
                BaseActivity.this.goLoginPage(activity, true);
            }

            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onSure(String text) {
                Intrinsics.checkNotNullParameter(text, "text");
                BaseActivity.this.goLoginPage(activity, true);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void toTabMainPage() {
        Intent intent = new Intent();
        intent.setFlags(268468224);
        ActivityRouterUtilsKt.routingForTabMainActivity((Context) this, intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void toTabMainPage(boolean isSplash) {
        Intent intent = new Intent();
        intent.putExtra(StubApp.getString2(13237), isSplash);
        intent.setFlags(268468224);
        ActivityRouterUtilsKt.routingForTabMainActivity((Context) this, intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void toTabMainPage(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        intent.setFlags(268468224);
        ActivityRouterUtilsKt.routingForTabMainActivity((Context) this, intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void logout() {
        Intent intent = new Intent();
        intent.putExtra(StubApp.getString2(13135), true);
        intent.setFlags(268468224);
        ActivityRouterUtilsKt.routingForHomePageActivity((Context) this, intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected final void goLoginPage(BaseActivity activity, boolean isFirstPage) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        PushServiceFactory.getCloudPushService().clearNotifications();
        Intent intent = new Intent();
        intent.setFlags(268468224);
        intent.putExtra(StubApp.getString2(13236), isFirstPage);
        ActivityRouterUtilsKt.routingForLoginActivity((Context) activity, intent);
        activity.finish();
    }

    public final void registerNetStatusReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(StubApp.getString2(781));
        NetStatusReceiver netStatusReceiver = new NetStatusReceiver();
        this.mNetStatusReceiver = netStatusReceiver;
        registerReceiver(netStatusReceiver, intentFilter);
        NetStatusReceiver netStatusReceiver2 = this.mNetStatusReceiver;
        Intrinsics.checkNotNull(netStatusReceiver2);
        netStatusReceiver2.setNetStateListener(new NetStatusReceiver.INetStatusListener() { // from class: com.deye.activity.device.base.BaseActivity$$ExternalSyntheticLambda0
            @Override // com.deye.broadcast_receiver.NetStatusReceiver.INetStatusListener
            public final void getNetState(int i) {
                BaseActivity.registerNetStatusReceiver$lambda$1(this.f$0, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registerNetStatusReceiver$lambda$1(BaseActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 0) {
            BaseUtils.showShortToast(StubApp.getOrigApplicationContext(this$0.getApplicationContext()), this$0.getResources().getString(R.string.network_is_error_qrcode));
            this$0.stopWaiting();
        }
    }

    /* compiled from: BaseActivity.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lcom/deye/activity/device/base/BaseActivity$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "className", "getClassName", "()Ljava/lang/String;", "setClassName", "(Ljava/lang/String;)V", "time", "getTime", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getClassName() {
            return BaseActivity.className;
        }

        public final void setClassName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            BaseActivity.className = str;
        }

        public final String getTime() {
            return String.valueOf(System.currentTimeMillis());
        }
    }
}
