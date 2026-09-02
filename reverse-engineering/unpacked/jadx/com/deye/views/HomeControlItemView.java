package com.deye.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewGroupKt;
import com.amap.location.support.bean.location.AmapLocationNetwork;
import com.deye.CommandManger;
import com.deye.activity.device.base.BaseActivity;
import com.deye.configs.Constants;
import com.deye.entity.control_panel.dehumidifier.func.ModeBean;
import com.deye.helper.DialogHelper;
import com.deye.utils.PanelHelper;
import com.mxchipapp.R;
import com.mxchipapp.databinding.HomeControlItemViewBinding;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.stub.StubApp;
import com.zhouyou.view.seekbar.SignUtils;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.bean.LoopFanBean;
import io.fogcloud.sdk.fog.log.LogDebug;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: HomeControlItemView.kt */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001d\u0018\u0000 J2\u00020\u0001:\u0002JKB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0002J\u0018\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\u001cH\u0002J\u0010\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020\u0010H\u0002J\b\u0010$\u001a\u00020 H\u0002J8\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00012\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020 H\u0002J0\u0010.\u001a\u00020/2\u0006\u0010+\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u00132\u0006\u0010-\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0012\u00101\u001a\u0004\u0018\u00010\u00142\u0006\u00102\u001a\u00020\u0013H\u0002J\b\u00103\u001a\u00020\u001cH\u0002J\b\u00104\u001a\u00020\u001cH\u0002J\u001a\u00105\u001a\u00020 2\u0006\u0010#\u001a\u00020\u00102\b\u00106\u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u00107\u001a\u00020\u001c2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u00108\u001a\u00020\u001cH\u0002J\u0018\u00109\u001a\u00020 2\u0006\u0010:\u001a\u00020\u00132\u0006\u0010;\u001a\u00020 H\u0002J\u001a\u0010<\u001a\u00020\u001c2\u0006\u0010=\u001a\u00020\t2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u0013J\u0018\u0010?\u001a\u00020\u001c2\u0006\u0010@\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010A\u001a\u00020\u001c2\u0006\u0010B\u001a\u00020 H\u0002J\u0016\u0010C\u001a\u00020\u001c2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\tJ\b\u0010D\u001a\u00020\u001cH\u0002J\b\u0010E\u001a\u00020\u001cH\u0002J\b\u0010F\u001a\u00020\u001cH\u0002J\b\u0010G\u001a\u00020\u001cH\u0002J\b\u0010H\u001a\u00020\u001cH\u0002J\b\u0010I\u001a\u00020\u001cH\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0003X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Lcom/deye/views/HomeControlItemView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/mxchipapp/databinding/HomeControlItemViewBinding;", "dehumidifierBean", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "deviceListBean", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "drawableCache", "", "", "Landroid/graphics/drawable/Drawable;", "loopFanBean", "Lio/fogcloud/sdk/fog/bean/LoopFanBean;", "mContext", "modeBean", "Lcom/deye/entity/control_panel/dehumidifier/func/ModeBean;", "realPosition", "bindHumItemViews", "", "bindLoopItemView", "bindU20ProSimplifiedModes", "isFault", "", "buildPanelItemViews", "checkDeviceBeanValid", "device", "checkFaultStatus", "configureIcon", "imageView", "Landroid/widget/ImageView;", "fraBg", "params", "Landroid/widget/FrameLayout$LayoutParams;", "iconSelected", "iconNormal", "isSelected", "createModeItemView", "Landroid/view/View;", "modeName", "getDrawable", "imgName", "handleChildLock", "handlePowerOff", "handleWaterTankState", "dehumidifier", "init", "initClickListener", "isU20ProModeSelected", "currentMode", "isDehumidifyCategory", "onPower", "power", "fanStatus", "sendModeCommand", "mode", "setControlsEnabled", "enabled", "setDataBean", "setPanelView", "showOfflineState", "updateButlerView", "updateFanDeviceView", "updateOnlineStatue", "updateViewByDevice", "Companion", "SimplifiedMode", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class HomeControlItemView extends FrameLayout {
    private static final float ICON_SIZE_LOOP_SELECTED = 32.0f;
    private static final float ICON_SIZE_LOOP_UNSELECTED = 44.0f;
    private static final float ICON_SIZE_SELECTED = 44.0f;
    private static final float ICON_SIZE_UNSELECTED = 24.0f;
    private static final float MARGIN_ICON = 16.0f;
    private static final float MARGIN_LOOP_ICON = 3.0f;
    private static final float MARGIN_TOP_SELECTED = 2.0f;
    private HomeControlItemViewBinding binding;
    private DehumidifierBean dehumidifierBean;
    private DeviceListBean deviceListBean;
    private final Map<String, Drawable> drawableCache;
    private LoopFanBean loopFanBean;
    private Context mContext;
    private ModeBean modeBean;
    private int realPosition;
    private static final Set<String> DEHUMIDIFY_MODES = SetsKt.setOf((Object[]) new String[]{StubApp.getString2(701), StubApp.getString2(7188), StubApp.getString2(2546), StubApp.getString2(8092)});
    private static final Set<String> PURIFY_MODES = SetsKt.setOf((Object[]) new String[]{StubApp.getString2(8034), StubApp.getString2(8412), StubApp.getString2(8030)});

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeControlItemView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.drawableCache = new LinkedHashMap();
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeControlItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.drawableCache = new LinkedHashMap();
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeControlItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.drawableCache = new LinkedHashMap();
        init(context);
    }

    private final void init(Context context) {
        this.mContext = context;
        HomeControlItemViewBinding homeControlItemViewBindingInflate = HomeControlItemViewBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(homeControlItemViewBindingInflate, "inflate(...)");
        this.binding = homeControlItemViewBindingInflate;
        initClickListener();
    }

    private final void initClickListener() {
        HomeControlItemViewBinding homeControlItemViewBinding = this.binding;
        HomeControlItemViewBinding homeControlItemViewBinding2 = null;
        String string2 = StubApp.getString2(13474);
        if (homeControlItemViewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            homeControlItemViewBinding = null;
        }
        homeControlItemViewBinding.llPowerOff.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.HomeControlItemView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Resources.NotFoundException {
                HomeControlItemView.initClickListener$lambda$0(this.f$0, view);
            }
        });
        HomeControlItemViewBinding homeControlItemViewBinding3 = this.binding;
        if (homeControlItemViewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            homeControlItemViewBinding2 = homeControlItemViewBinding3;
        }
        homeControlItemViewBinding2.llPowerOn.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.HomeControlItemView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeControlItemView.initClickListener$lambda$1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initClickListener$lambda$0(HomeControlItemView this$0, View view) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.handlePowerOff();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initClickListener$lambda$1(HomeControlItemView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceListBean deviceListBean = this$0.deviceListBean;
        if (deviceListBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceListBean");
            deviceListBean = null;
        }
        Boolean online = deviceListBean.getOnline();
        Intrinsics.checkNotNullExpressionValue(online, "getOnline(...)");
        if (online.booleanValue()) {
            onPower$default(this$0, 1, null, 2, null);
        }
    }

    private final void handlePowerOff() throws Resources.NotFoundException {
        DehumidifierBean dehumidifierBean;
        DeviceListBean deviceListBean = this.deviceListBean;
        String string2 = StubApp.getString2(14601);
        Context context = null;
        if (deviceListBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            deviceListBean = null;
        }
        boolean zIsHumidifier = deviceListBean.isHumidifier();
        String string22 = StubApp.getString2(701);
        String string23 = StubApp.getString2(2546);
        if (zIsHumidifier) {
            DehumidifierBean dehumidifierBean2 = this.dehumidifierBean;
            if (Intrinsics.areEqual(dehumidifierBean2 != null ? dehumidifierBean2.getHkmodeoperation() : null, string23)) {
                DehumidifierBean dehumidifierBean3 = this.dehumidifierBean;
                if (dehumidifierBean3 != null) {
                    dehumidifierBean3.setHkmodeoperation(StubApp.getString2(7188));
                }
                onPower(0, string22);
                return;
            }
        }
        DeviceListBean deviceListBean2 = this.deviceListBean;
        if (deviceListBean2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            deviceListBean2 = null;
        }
        if (deviceListBean2.isFanDevice() && (dehumidifierBean = this.dehumidifierBean) != null && !dehumidifierBean.checkIsPowerOn()) {
            DehumidifierBean dehumidifierBean4 = this.dehumidifierBean;
            if (Intrinsics.areEqual(dehumidifierBean4 != null ? dehumidifierBean4.getFan_switch() : null, string23)) {
                onPower(0, string22);
                return;
            }
        }
        Object obj = this.mContext;
        String string24 = StubApp.getString2(14605);
        if (obj == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string24);
            obj = null;
        }
        BaseActivity baseActivity = (BaseActivity) obj;
        Context context2 = this.mContext;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string24);
            context2 = null;
        }
        String string = context2.getResources().getString(R.string.power_off_dialog_tip_text);
        Context context3 = this.mContext;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string24);
        } else {
            context = context3;
        }
        DialogHelper.showDeleteDialog(baseActivity, string, context.getString(R.string.sure_text), new DialogHelper.OnDialogListener() { // from class: com.deye.views.HomeControlItemView.handlePowerOff.1
            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onSure(String text) {
                HomeControlItemView.this.onPower(0, StubApp.getString2(2546));
            }
        });
    }

    public final void setDataBean(DeviceListBean deviceListBean, int realPosition) {
        Intrinsics.checkNotNullParameter(deviceListBean, "deviceListBean");
        this.deviceListBean = deviceListBean;
        this.dehumidifierBean = deviceListBean.getDehumidifierBean();
        this.loopFanBean = deviceListBean.loopFanBean;
        this.realPosition = realPosition;
        PanelHelper panelHelper = PanelHelper.INSTANCE;
        Context context = this.mContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        ModeBean mode = panelHelper.getPanelBean(context, deviceListBean.getProduct_id()).getMode();
        Intrinsics.checkNotNullExpressionValue(mode, "getMode(...)");
        this.modeBean = mode;
        updateViewByDevice();
    }

    private final void updateViewByDevice() {
        DeviceListBean deviceListBean = this.deviceListBean;
        String string2 = StubApp.getString2(14601);
        HomeControlItemViewBinding homeControlItemViewBinding = null;
        if (deviceListBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            deviceListBean = null;
        }
        boolean zIsAirConditioner = deviceListBean.isAirConditioner();
        String string22 = StubApp.getString2(14605);
        String string23 = StubApp.getString2(13474);
        if (zIsAirConditioner) {
            HomeControlItemViewBinding homeControlItemViewBinding2 = this.binding;
            if (homeControlItemViewBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string23);
                homeControlItemViewBinding2 = null;
            }
            TextView textView = homeControlItemViewBinding2.tvOpenMsg;
            Context context = this.mContext;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                context = null;
            }
            textView.setText(context.getString(R.string.turn_on_air_conditioner));
        } else {
            DeviceListBean deviceListBean2 = this.deviceListBean;
            if (deviceListBean2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                deviceListBean2 = null;
            }
            if (deviceListBean2.isHumidifier()) {
                HomeControlItemViewBinding homeControlItemViewBinding3 = this.binding;
                if (homeControlItemViewBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string23);
                    homeControlItemViewBinding3 = null;
                }
                TextView textView2 = homeControlItemViewBinding3.tvOpenMsg;
                Context context2 = this.mContext;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string22);
                    context2 = null;
                }
                textView2.setText(context2.getString(R.string.turn_on_humidifier));
            } else {
                HomeControlItemViewBinding homeControlItemViewBinding4 = this.binding;
                if (homeControlItemViewBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string23);
                    homeControlItemViewBinding4 = null;
                }
                TextView textView3 = homeControlItemViewBinding4.tvOpenMsg;
                Context context3 = this.mContext;
                if (context3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string22);
                    context3 = null;
                }
                textView3.setText(context3.getString(R.string.turn_on_dehumidifier));
            }
        }
        PanelHelper panelHelper = PanelHelper.INSTANCE;
        DeviceListBean deviceListBean3 = this.deviceListBean;
        if (deviceListBean3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            deviceListBean3 = null;
        }
        String product_id = deviceListBean3.getProduct_id();
        Intrinsics.checkNotNullExpressionValue(product_id, "getProduct_id(...)");
        int drawable = panelHelper.getDrawable(product_id);
        HomeControlItemViewBinding homeControlItemViewBinding5 = this.binding;
        if (homeControlItemViewBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string23);
        } else {
            homeControlItemViewBinding = homeControlItemViewBinding5;
        }
        homeControlItemViewBinding.ivDeviceIcon.setImageResource(drawable);
        setPanelView();
    }

    private final void setPanelView() {
        DeviceListBean deviceListBean = this.deviceListBean;
        String string2 = StubApp.getString2(14601);
        DeviceListBean deviceListBean2 = null;
        if (deviceListBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            deviceListBean = null;
        }
        if (checkDeviceBeanValid(deviceListBean)) {
            updateButlerView();
            DeviceListBean deviceListBean3 = this.deviceListBean;
            if (deviceListBean3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                deviceListBean3 = null;
            }
            if (handleWaterTankState(deviceListBean3, this.dehumidifierBean)) {
                return;
            }
            PanelHelper panelHelper = PanelHelper.INSTANCE;
            Context context = this.mContext;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context = null;
            }
            DeviceListBean deviceListBean4 = this.deviceListBean;
            if (deviceListBean4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                deviceListBean4 = null;
            }
            panelHelper.getPanelBean(context, deviceListBean4.getProduct_id()).getMode();
            DeviceListBean deviceListBean5 = this.deviceListBean;
            if (deviceListBean5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                deviceListBean2 = deviceListBean5;
            }
            Boolean online = deviceListBean2.getOnline();
            Intrinsics.checkNotNullExpressionValue(online, "getOnline(...)");
            if (online.booleanValue()) {
                updateOnlineStatue();
            } else {
                showOfflineState();
            }
        }
    }

    private final void updateButlerView() {
        DeviceListBean deviceListBean = this.deviceListBean;
        String string2 = StubApp.getString2(14601);
        HomeControlItemViewBinding homeControlItemViewBinding = null;
        if (deviceListBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            deviceListBean = null;
        }
        boolean zIsHumidifier = deviceListBean.isHumidifier();
        String string22 = StubApp.getString2(13474);
        if (zIsHumidifier) {
            DeviceListBean deviceListBean2 = this.deviceListBean;
            if (deviceListBean2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                deviceListBean2 = null;
            }
            if (Intrinsics.areEqual(deviceListBean2.getDehumidifierBean().getHkmodeoperation(), AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
                HomeControlItemViewBinding homeControlItemViewBinding2 = this.binding;
                if (homeControlItemViewBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string22);
                } else {
                    homeControlItemViewBinding = homeControlItemViewBinding2;
                }
                homeControlItemViewBinding.tvButler.setVisibility(0);
                return;
            }
        }
        HomeControlItemViewBinding homeControlItemViewBinding3 = this.binding;
        if (homeControlItemViewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
        } else {
            homeControlItemViewBinding = homeControlItemViewBinding3;
        }
        homeControlItemViewBinding.tvButler.setVisibility(8);
    }

    private final void updateOnlineStatue() {
        LoopFanBean loopFanBean;
        HomeControlItemViewBinding homeControlItemViewBinding = this.binding;
        String string2 = StubApp.getString2(13474);
        HomeControlItemViewBinding homeControlItemViewBinding2 = null;
        if (homeControlItemViewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            homeControlItemViewBinding = null;
        }
        homeControlItemViewBinding.llPowerOn.setAlpha(1.0f);
        HomeControlItemViewBinding homeControlItemViewBinding3 = this.binding;
        if (homeControlItemViewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            homeControlItemViewBinding3 = null;
        }
        homeControlItemViewBinding3.llPowerOn.setVisibility(8);
        DeviceListBean deviceListBean = this.deviceListBean;
        if (deviceListBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceListBean");
            deviceListBean = null;
        }
        if (deviceListBean.isFanDevice()) {
            updateFanDeviceView();
        } else {
            DehumidifierBean dehumidifierBean = this.dehumidifierBean;
            if ((dehumidifierBean != null && dehumidifierBean.checkIsPowerOn()) || ((loopFanBean = this.loopFanBean) != null && loopFanBean.checkIsPowerOn())) {
                HomeControlItemViewBinding homeControlItemViewBinding4 = this.binding;
                if (homeControlItemViewBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    homeControlItemViewBinding4 = null;
                }
                homeControlItemViewBinding4.llPowerOff.setVisibility(0);
                HomeControlItemViewBinding homeControlItemViewBinding5 = this.binding;
                if (homeControlItemViewBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    homeControlItemViewBinding5 = null;
                }
                homeControlItemViewBinding5.llOptions.setVisibility(0);
                HomeControlItemViewBinding homeControlItemViewBinding6 = this.binding;
                if (homeControlItemViewBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    homeControlItemViewBinding6 = null;
                }
                homeControlItemViewBinding6.llOptionImages.setVisibility(0);
                HomeControlItemViewBinding homeControlItemViewBinding7 = this.binding;
                if (homeControlItemViewBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    homeControlItemViewBinding7 = null;
                }
                homeControlItemViewBinding7.tvPowerOffMessage.setVisibility(8);
                HomeControlItemViewBinding homeControlItemViewBinding8 = this.binding;
                if (homeControlItemViewBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    homeControlItemViewBinding8 = null;
                }
                TextView textView = homeControlItemViewBinding8.tvPowerOff;
                Context context = this.mContext;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    context = null;
                }
                textView.setText(context.getString(R.string.turned_on));
                HomeControlItemViewBinding homeControlItemViewBinding9 = this.binding;
                if (homeControlItemViewBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                } else {
                    homeControlItemViewBinding2 = homeControlItemViewBinding9;
                }
                homeControlItemViewBinding2.tvPowerOff.setPadding(0, SignUtils.dp2px(12), 0, 0);
                buildPanelItemViews();
            } else {
                HomeControlItemViewBinding homeControlItemViewBinding10 = this.binding;
                if (homeControlItemViewBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    homeControlItemViewBinding10 = null;
                }
                homeControlItemViewBinding10.llOptionImages.setVisibility(8);
                HomeControlItemViewBinding homeControlItemViewBinding11 = this.binding;
                if (homeControlItemViewBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    homeControlItemViewBinding11 = null;
                }
                homeControlItemViewBinding11.llOptions.setVisibility(8);
                HomeControlItemViewBinding homeControlItemViewBinding12 = this.binding;
                if (homeControlItemViewBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                } else {
                    homeControlItemViewBinding2 = homeControlItemViewBinding12;
                }
                homeControlItemViewBinding2.llPowerOn.setVisibility(0);
            }
        }
        handleChildLock();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void handleChildLock() {
        boolean z;
        Context context = null;
        if (checkFaultStatus()) {
            z = true;
        } else {
            DeviceListBean deviceListBean = this.deviceListBean;
            if (deviceListBean == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceListBean");
                deviceListBean = null;
            }
            if (deviceListBean.isAirConditioner()) {
                DehumidifierBean dehumidifierBean = this.dehumidifierBean;
                if (Intrinsics.areEqual(dehumidifierBean != null ? dehumidifierBean.getLock_switch() : null, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
                }
            }
            z = false;
        }
        setControlsEnabled(!z);
        HomeControlItemViewBinding homeControlItemViewBinding = this.binding;
        if (homeControlItemViewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            homeControlItemViewBinding = null;
        }
        TextView textView = homeControlItemViewBinding.tvAlert;
        Context context2 = this.mContext;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context = context2;
        }
        textView.setText(context.getString(z ? R.string.child_lock_enabled : R.string.control));
    }

    private final boolean checkFaultStatus() {
        DeviceListBean deviceListBean = this.deviceListBean;
        if (deviceListBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceListBean");
            deviceListBean = null;
        }
        if (!Constants.isH7Product(deviceListBean.getProduct_id())) {
            return false;
        }
        DehumidifierBean dehumidifierBean = this.dehumidifierBean;
        if (!Intrinsics.areEqual(dehumidifierBean != null ? dehumidifierBean.getP6() : null, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
            DehumidifierBean dehumidifierBean2 = this.dehumidifierBean;
            if (!Intrinsics.areEqual(dehumidifierBean2 != null ? dehumidifierBean2.getP7() : null, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
                return false;
            }
        }
        return true;
    }

    private final void setControlsEnabled(boolean enabled) {
        HomeControlItemViewBinding homeControlItemViewBinding = this.binding;
        if (homeControlItemViewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            homeControlItemViewBinding = null;
        }
        LinearLayout llOptions = homeControlItemViewBinding.llOptions;
        Intrinsics.checkNotNullExpressionValue(llOptions, "llOptions");
        ViewExtendsKt.setAvailable(llOptions, enabled);
        LinearLayout llPowerOn = homeControlItemViewBinding.llPowerOn;
        Intrinsics.checkNotNullExpressionValue(llPowerOn, "llPowerOn");
        ViewExtendsKt.setAvailable(llPowerOn, enabled);
        homeControlItemViewBinding.llPowerOn.setEnabled(enabled);
        LinearLayout llPowerOff = homeControlItemViewBinding.llPowerOff;
        Intrinsics.checkNotNullExpressionValue(llPowerOff, "llPowerOff");
        ViewExtendsKt.setAvailable(llPowerOff, enabled);
        homeControlItemViewBinding.llPowerOff.setEnabled(enabled);
        homeControlItemViewBinding.llOptionImages.setEnabled(enabled);
        LinearLayout llOptionImages = homeControlItemViewBinding.llOptionImages;
        Intrinsics.checkNotNullExpressionValue(llOptionImages, "llOptionImages");
        Iterator it2 = ViewGroupKt.getChildren(llOptionImages).iterator();
        while (it2.hasNext()) {
            ((View) it2.next()).setEnabled(enabled);
        }
    }

    private final void updateFanDeviceView() {
        DehumidifierBean dehumidifierBean = this.dehumidifierBean;
        HomeControlItemViewBinding homeControlItemViewBinding = null;
        boolean zAreEqual = Intrinsics.areEqual(dehumidifierBean != null ? dehumidifierBean.getHkmodeoperation() : null, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY);
        String string2 = StubApp.getString2(14605);
        String string22 = StubApp.getString2(13474);
        if (zAreEqual) {
            HomeControlItemViewBinding homeControlItemViewBinding2 = this.binding;
            if (homeControlItemViewBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                homeControlItemViewBinding2 = null;
            }
            homeControlItemViewBinding2.llPowerOff.setVisibility(0);
            HomeControlItemViewBinding homeControlItemViewBinding3 = this.binding;
            if (homeControlItemViewBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                homeControlItemViewBinding3 = null;
            }
            homeControlItemViewBinding3.llOptions.setVisibility(0);
            HomeControlItemViewBinding homeControlItemViewBinding4 = this.binding;
            if (homeControlItemViewBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                homeControlItemViewBinding4 = null;
            }
            homeControlItemViewBinding4.llOptionImages.setVisibility(8);
            HomeControlItemViewBinding homeControlItemViewBinding5 = this.binding;
            if (homeControlItemViewBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                homeControlItemViewBinding5 = null;
            }
            homeControlItemViewBinding5.tvPowerOffMessage.setVisibility(8);
            HomeControlItemViewBinding homeControlItemViewBinding6 = this.binding;
            if (homeControlItemViewBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                homeControlItemViewBinding6 = null;
            }
            TextView textView = homeControlItemViewBinding6.tvPowerOff;
            Context context = this.mContext;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                context = null;
            }
            textView.setText(context.getString(R.string.shut_down_now));
            HomeControlItemViewBinding homeControlItemViewBinding7 = this.binding;
            if (homeControlItemViewBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                homeControlItemViewBinding7 = null;
            }
            homeControlItemViewBinding7.tvPowerOff.setPadding(0, SignUtils.dp2px(4), 0, 0);
            HomeControlItemViewBinding homeControlItemViewBinding8 = this.binding;
            if (homeControlItemViewBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
            } else {
                homeControlItemViewBinding = homeControlItemViewBinding8;
            }
            homeControlItemViewBinding.llOptionImages.setVisibility(8);
            return;
        }
        DehumidifierBean dehumidifierBean2 = this.dehumidifierBean;
        String string23 = StubApp.getString2(14601);
        if (dehumidifierBean2 != null && dehumidifierBean2.checkIsPowerOn()) {
            HomeControlItemViewBinding homeControlItemViewBinding9 = this.binding;
            if (homeControlItemViewBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                homeControlItemViewBinding9 = null;
            }
            homeControlItemViewBinding9.llPowerOff.setVisibility(0);
            HomeControlItemViewBinding homeControlItemViewBinding10 = this.binding;
            if (homeControlItemViewBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                homeControlItemViewBinding10 = null;
            }
            homeControlItemViewBinding10.llOptions.setVisibility(0);
            HomeControlItemViewBinding homeControlItemViewBinding11 = this.binding;
            if (homeControlItemViewBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                homeControlItemViewBinding11 = null;
            }
            homeControlItemViewBinding11.llOptionImages.setVisibility(0);
            HomeControlItemViewBinding homeControlItemViewBinding12 = this.binding;
            if (homeControlItemViewBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                homeControlItemViewBinding12 = null;
            }
            homeControlItemViewBinding12.tvPowerOffMessage.setVisibility(8);
            HomeControlItemViewBinding homeControlItemViewBinding13 = this.binding;
            if (homeControlItemViewBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                homeControlItemViewBinding13 = null;
            }
            TextView textView2 = homeControlItemViewBinding13.tvPowerOff;
            Context context2 = this.mContext;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                context2 = null;
            }
            textView2.setText(context2.getString(R.string.turned_on));
            DeviceListBean deviceListBean = this.deviceListBean;
            if (deviceListBean == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string23);
                deviceListBean = null;
            }
            if (deviceListBean.isHumidifier()) {
                HomeControlItemViewBinding homeControlItemViewBinding14 = this.binding;
                if (homeControlItemViewBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string22);
                    homeControlItemViewBinding14 = null;
                }
                TextView textView3 = homeControlItemViewBinding14.tvPowerOffMessage;
                Context context3 = this.mContext;
                if (context3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    context3 = null;
                }
                textView3.setText(context3.getString(R.string.wet_curtain_drying));
            } else {
                HomeControlItemViewBinding homeControlItemViewBinding15 = this.binding;
                if (homeControlItemViewBinding15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string22);
                    homeControlItemViewBinding15 = null;
                }
                TextView textView4 = homeControlItemViewBinding15.tvPowerOffMessage;
                Context context4 = this.mContext;
                if (context4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    context4 = null;
                }
                textView4.setText(context4.getString(R.string.device_curtain_drying));
            }
            HomeControlItemViewBinding homeControlItemViewBinding16 = this.binding;
            if (homeControlItemViewBinding16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
            } else {
                homeControlItemViewBinding = homeControlItemViewBinding16;
            }
            homeControlItemViewBinding.tvPowerOff.setPadding(0, SignUtils.dp2px(16), 0, 0);
            buildPanelItemViews();
            return;
        }
        DehumidifierBean dehumidifierBean3 = this.dehumidifierBean;
        if (dehumidifierBean3 != null && !dehumidifierBean3.checkIsPowerOn()) {
            DehumidifierBean dehumidifierBean4 = this.dehumidifierBean;
            if (Intrinsics.areEqual(dehumidifierBean4 != null ? dehumidifierBean4.getFan_switch() : null, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
                DehumidifierBean dehumidifierBean5 = this.dehumidifierBean;
                if (!Intrinsics.areEqual(dehumidifierBean5 != null ? dehumidifierBean5.getL2() : null, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
                    DehumidifierBean dehumidifierBean6 = this.dehumidifierBean;
                    if (!Intrinsics.areEqual(dehumidifierBean6 != null ? dehumidifierBean6.getE3() : null, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
                        HomeControlItemViewBinding homeControlItemViewBinding17 = this.binding;
                        if (homeControlItemViewBinding17 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string22);
                            homeControlItemViewBinding17 = null;
                        }
                        homeControlItemViewBinding17.llPowerOff.setVisibility(0);
                        HomeControlItemViewBinding homeControlItemViewBinding18 = this.binding;
                        if (homeControlItemViewBinding18 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string22);
                            homeControlItemViewBinding18 = null;
                        }
                        homeControlItemViewBinding18.llOptions.setVisibility(0);
                        HomeControlItemViewBinding homeControlItemViewBinding19 = this.binding;
                        if (homeControlItemViewBinding19 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string22);
                            homeControlItemViewBinding19 = null;
                        }
                        homeControlItemViewBinding19.llOptionImages.setVisibility(8);
                        HomeControlItemViewBinding homeControlItemViewBinding20 = this.binding;
                        if (homeControlItemViewBinding20 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string22);
                            homeControlItemViewBinding20 = null;
                        }
                        homeControlItemViewBinding20.tvPowerOffMessage.setVisibility(0);
                        HomeControlItemViewBinding homeControlItemViewBinding21 = this.binding;
                        if (homeControlItemViewBinding21 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string22);
                            homeControlItemViewBinding21 = null;
                        }
                        TextView textView5 = homeControlItemViewBinding21.tvPowerOff;
                        Context context5 = this.mContext;
                        if (context5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            context5 = null;
                        }
                        textView5.setText(context5.getString(R.string.shut_down_now));
                        DeviceListBean deviceListBean2 = this.deviceListBean;
                        if (deviceListBean2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string23);
                            deviceListBean2 = null;
                        }
                        if (deviceListBean2.isHumidifier()) {
                            HomeControlItemViewBinding homeControlItemViewBinding22 = this.binding;
                            if (homeControlItemViewBinding22 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string22);
                                homeControlItemViewBinding22 = null;
                            }
                            TextView textView6 = homeControlItemViewBinding22.tvPowerOffMessage;
                            Context context6 = this.mContext;
                            if (context6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string2);
                                context6 = null;
                            }
                            textView6.setText(context6.getString(R.string.wet_curtain_drying));
                        } else {
                            HomeControlItemViewBinding homeControlItemViewBinding23 = this.binding;
                            if (homeControlItemViewBinding23 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string22);
                                homeControlItemViewBinding23 = null;
                            }
                            TextView textView7 = homeControlItemViewBinding23.tvPowerOffMessage;
                            Context context7 = this.mContext;
                            if (context7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(string2);
                                context7 = null;
                            }
                            textView7.setText(context7.getString(R.string.device_curtain_drying));
                        }
                        HomeControlItemViewBinding homeControlItemViewBinding24 = this.binding;
                        if (homeControlItemViewBinding24 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string22);
                            homeControlItemViewBinding24 = null;
                        }
                        homeControlItemViewBinding24.tvPowerOff.setPadding(0, SignUtils.dp2px(4), 0, 0);
                        HomeControlItemViewBinding homeControlItemViewBinding25 = this.binding;
                        if (homeControlItemViewBinding25 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string22);
                        } else {
                            homeControlItemViewBinding = homeControlItemViewBinding25;
                        }
                        homeControlItemViewBinding.llOptionImages.setVisibility(8);
                        return;
                    }
                }
            }
        }
        HomeControlItemViewBinding homeControlItemViewBinding26 = this.binding;
        if (homeControlItemViewBinding26 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            homeControlItemViewBinding26 = null;
        }
        homeControlItemViewBinding26.llOptionImages.setVisibility(8);
        HomeControlItemViewBinding homeControlItemViewBinding27 = this.binding;
        if (homeControlItemViewBinding27 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            homeControlItemViewBinding27 = null;
        }
        homeControlItemViewBinding27.llOptions.setVisibility(8);
        HomeControlItemViewBinding homeControlItemViewBinding28 = this.binding;
        if (homeControlItemViewBinding28 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
        } else {
            homeControlItemViewBinding = homeControlItemViewBinding28;
        }
        homeControlItemViewBinding.llPowerOn.setVisibility(0);
    }

    private final boolean checkDeviceBeanValid(DeviceListBean device) {
        if (device.isLoopFan() && device.loopFanBean == null) {
            return false;
        }
        return device.isLoopFan() || device.getDehumidifierBean() != null;
    }

    public static /* synthetic */ void onPower$default(HomeControlItemView homeControlItemView, int i, String str, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        homeControlItemView.onPower(i, str);
    }

    public final void onPower(int power, String fanStatus) {
        DeviceListBean deviceListBean = this.deviceListBean;
        DeviceListBean deviceListBean2 = null;
        String string2 = StubApp.getString2(14601);
        if (deviceListBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            deviceListBean = null;
        }
        boolean zIsLoopFan = deviceListBean.isLoopFan();
        String string22 = StubApp.getString2(14610);
        String string23 = StubApp.getString2(13668);
        if (zIsLoopFan) {
            DeviceListBean deviceListBean3 = this.deviceListBean;
            if (deviceListBean3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                deviceListBean3 = null;
            }
            LoopFanBean loopFanBean = deviceListBean3.loopFanBean;
            if (loopFanBean != null) {
                loopFanBean.setSys_switch(String.valueOf(power));
                CommandManger commandManger = CommandManger.INSTANCE;
                DeviceListBean deviceListBean4 = this.deviceListBean;
                if (deviceListBean4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    deviceListBean4 = null;
                }
                String device_id = deviceListBean4.getDevice_id();
                Intrinsics.checkNotNullExpressionValue(device_id, string23);
                DeviceListBean deviceListBean5 = this.deviceListBean;
                if (deviceListBean5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                } else {
                    deviceListBean2 = deviceListBean5;
                }
                String product_id = deviceListBean2.getProduct_id();
                Intrinsics.checkNotNullExpressionValue(product_id, string22);
                commandManger.sendLoopCommand(device_id, product_id, loopFanBean);
                updateViewByDevice();
                return;
            }
            return;
        }
        DeviceListBean deviceListBean6 = this.deviceListBean;
        if (deviceListBean6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            deviceListBean6 = null;
        }
        boolean zIsFanDevice = deviceListBean6.isFanDevice();
        String string24 = StubApp.getString2(14611);
        if (zIsFanDevice) {
            DeviceListBean deviceListBean7 = this.deviceListBean;
            if (deviceListBean7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                deviceListBean7 = null;
            }
            DehumidifierBean dehumidifierBean = deviceListBean7.getDehumidifierBean();
            if (dehumidifierBean != null) {
                CommandManger commandManger2 = CommandManger.INSTANCE;
                DeviceListBean deviceListBean8 = this.deviceListBean;
                if (deviceListBean8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    deviceListBean8 = null;
                }
                String device_id2 = deviceListBean8.getDevice_id();
                Intrinsics.checkNotNullExpressionValue(device_id2, string23);
                DeviceListBean deviceListBean9 = this.deviceListBean;
                if (deviceListBean9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    deviceListBean9 = null;
                }
                String product_id2 = deviceListBean9.getProduct_id();
                Intrinsics.checkNotNullExpressionValue(product_id2, string22);
                DeviceListBean deviceListBean10 = this.deviceListBean;
                if (deviceListBean10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    deviceListBean10 = null;
                }
                boolean zIsFogPlatform = deviceListBean10.isFogPlatform();
                DeviceListBean deviceListBean11 = this.deviceListBean;
                if (deviceListBean11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    deviceListBean11 = null;
                }
                boolean zIsUseComboProtocol = deviceListBean11.isUseComboProtocol();
                DeviceListBean deviceListBean12 = this.deviceListBean;
                if (deviceListBean12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                } else {
                    deviceListBean2 = deviceListBean12;
                }
                DehumidifierBean dehumidifierBean2 = deviceListBean2.getDehumidifierBean();
                Intrinsics.checkNotNullExpressionValue(dehumidifierBean2, string24);
                commandManger2.sendPowerCommand(device_id2, product_id2, power, zIsFogPlatform, zIsUseComboProtocol, dehumidifierBean2, true);
                dehumidifierBean.setSys_switch(String.valueOf(power));
                if (fanStatus != null) {
                    dehumidifierBean.setFan_switch(fanStatus);
                }
                updateViewByDevice();
                return;
            }
            return;
        }
        DeviceListBean deviceListBean13 = this.deviceListBean;
        if (deviceListBean13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            deviceListBean13 = null;
        }
        DehumidifierBean dehumidifierBean3 = deviceListBean13.getDehumidifierBean();
        if (dehumidifierBean3 != null) {
            CommandManger commandManger3 = CommandManger.INSTANCE;
            DeviceListBean deviceListBean14 = this.deviceListBean;
            if (deviceListBean14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                deviceListBean14 = null;
            }
            String device_id3 = deviceListBean14.getDevice_id();
            Intrinsics.checkNotNullExpressionValue(device_id3, string23);
            DeviceListBean deviceListBean15 = this.deviceListBean;
            if (deviceListBean15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                deviceListBean15 = null;
            }
            String product_id3 = deviceListBean15.getProduct_id();
            Intrinsics.checkNotNullExpressionValue(product_id3, string22);
            DeviceListBean deviceListBean16 = this.deviceListBean;
            if (deviceListBean16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                deviceListBean16 = null;
            }
            boolean zIsFogPlatform2 = deviceListBean16.isFogPlatform();
            DeviceListBean deviceListBean17 = this.deviceListBean;
            if (deviceListBean17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                deviceListBean17 = null;
            }
            boolean zIsUseComboProtocol2 = deviceListBean17.isUseComboProtocol();
            DeviceListBean deviceListBean18 = this.deviceListBean;
            if (deviceListBean18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                deviceListBean2 = deviceListBean18;
            }
            DehumidifierBean dehumidifierBean4 = deviceListBean2.getDehumidifierBean();
            Intrinsics.checkNotNullExpressionValue(dehumidifierBean4, string24);
            commandManger3.sendPowerCommand(device_id3, product_id3, power, zIsFogPlatform2, zIsUseComboProtocol2, dehumidifierBean4, true);
            dehumidifierBean3.setSys_switch(String.valueOf(power));
            updateViewByDevice();
        }
    }

    private final void showOfflineState() {
        HomeControlItemViewBinding homeControlItemViewBinding = this.binding;
        HomeControlItemViewBinding homeControlItemViewBinding2 = null;
        String string2 = StubApp.getString2(13474);
        if (homeControlItemViewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            homeControlItemViewBinding = null;
        }
        homeControlItemViewBinding.llOptions.setVisibility(8);
        HomeControlItemViewBinding homeControlItemViewBinding3 = this.binding;
        if (homeControlItemViewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            homeControlItemViewBinding3 = null;
        }
        homeControlItemViewBinding3.llPowerOn.setVisibility(0);
        HomeControlItemViewBinding homeControlItemViewBinding4 = this.binding;
        if (homeControlItemViewBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            homeControlItemViewBinding2 = homeControlItemViewBinding4;
        }
        homeControlItemViewBinding2.llPowerOn.setAlpha(0.4f);
    }

    private final void buildPanelItemViews() {
        HomeControlItemViewBinding homeControlItemViewBinding = this.binding;
        String string2 = StubApp.getString2(13474);
        DeviceListBean deviceListBean = null;
        if (homeControlItemViewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            homeControlItemViewBinding = null;
        }
        LinearLayout linearLayout = homeControlItemViewBinding.llOptionImages;
        ModeBean modeBean = this.modeBean;
        if (modeBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("modeBean");
            modeBean = null;
        }
        linearLayout.setWeightSum(modeBean.getValue().length);
        HomeControlItemViewBinding homeControlItemViewBinding2 = this.binding;
        if (homeControlItemViewBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            homeControlItemViewBinding2 = null;
        }
        homeControlItemViewBinding2.llOptionImages.removeAllViews();
        DeviceListBean deviceListBean2 = this.deviceListBean;
        String string22 = StubApp.getString2(14601);
        if (deviceListBean2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            deviceListBean2 = null;
        }
        if (deviceListBean2.isLoopFan()) {
            DeviceListBean deviceListBean3 = this.deviceListBean;
            if (deviceListBean3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                deviceListBean3 = null;
            }
            if (deviceListBean3.loopFanBean == null) {
                return;
            }
        }
        DeviceListBean deviceListBean4 = this.deviceListBean;
        if (deviceListBean4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            deviceListBean4 = null;
        }
        if (!deviceListBean4.isLoopFan()) {
            DeviceListBean deviceListBean5 = this.deviceListBean;
            if (deviceListBean5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                deviceListBean5 = null;
            }
            if (deviceListBean5.getDehumidifierBean() == null) {
                return;
            }
        }
        DeviceListBean deviceListBean6 = this.deviceListBean;
        if (deviceListBean6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
        } else {
            deviceListBean = deviceListBean6;
        }
        if (deviceListBean.isLoopFan()) {
            bindLoopItemView();
        } else {
            bindHumItemViews();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0164  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void bindLoopItemView() {
        ModeBean modeBean;
        char c;
        boolean z;
        HomeControlItemViewBinding homeControlItemViewBinding = this.binding;
        String string2 = StubApp.getString2(13474);
        if (homeControlItemViewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            homeControlItemViewBinding = null;
        }
        ViewGroup.LayoutParams layoutParams = homeControlItemViewBinding.llOptionImages.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
        ModeBean modeBean2 = this.modeBean;
        String string22 = StubApp.getString2(14600);
        if (modeBean2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            modeBean2 = null;
        }
        boolean z2 = false;
        if (modeBean2.getValue() == null) {
            HomeControlItemViewBinding homeControlItemViewBinding2 = this.binding;
            if (homeControlItemViewBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                homeControlItemViewBinding2 = null;
            }
            homeControlItemViewBinding2.llOptionImages.setPadding(0, 0, 0, 0);
            layoutParams2.setMargins(0, 0, 0, 0);
        } else {
            ModeBean modeBean3 = this.modeBean;
            if (modeBean3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                modeBean3 = null;
            }
            String[] value = modeBean3.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            if (value.length != 0) {
                HomeControlItemViewBinding homeControlItemViewBinding3 = this.binding;
                if (homeControlItemViewBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    homeControlItemViewBinding3 = null;
                }
                homeControlItemViewBinding3.llOptionImages.setPadding(DensityUtil.dp2px(8.0f), 0, DensityUtil.dp2px(8.0f), 0);
                layoutParams2.setMargins(0, 0, DensityUtil.dp2px(MARGIN_ICON), 0);
            }
        }
        HomeControlItemViewBinding homeControlItemViewBinding4 = this.binding;
        if (homeControlItemViewBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            homeControlItemViewBinding4 = null;
        }
        homeControlItemViewBinding4.llOptionImages.setLayoutParams(layoutParams2);
        DeviceListBean deviceListBean = this.deviceListBean;
        String string23 = StubApp.getString2(14601);
        if (deviceListBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string23);
            deviceListBean = null;
        }
        final LoopFanBean loopFanBean = deviceListBean.loopFanBean;
        LogDebug.INSTANCE.log(StubApp.getString2(14602) + loopFanBean.getHumidification() + StubApp.getString2(14603) + loopFanBean.getDeodorization() + StubApp.getString2(14604) + loopFanBean.getFormaldehyde_removal() + StubApp.getString2(626));
        ModeBean modeBean4 = this.modeBean;
        if (modeBean4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            modeBean4 = null;
        }
        String[] name = modeBean4.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        int length = name.length;
        final int i = 0;
        while (i < length) {
            String str = name[i];
            Context context = this.mContext;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context = null;
            }
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
            HomeControlItemViewBinding homeControlItemViewBinding5 = this.binding;
            if (homeControlItemViewBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                homeControlItemViewBinding5 = null;
            }
            View viewInflate = layoutInflaterFrom.inflate(R.layout.home_item_opt_image, homeControlItemViewBinding5.llOptionImages, z2);
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            if (i == 0) {
                DeviceListBean deviceListBean2 = this.deviceListBean;
                if (deviceListBean2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string23);
                    deviceListBean2 = null;
                }
                String humidification = deviceListBean2.loopFanBean.getHumidification();
                Intrinsics.checkNotNullExpressionValue(humidification, "getHumidification(...)");
                if (Integer.parseInt(humidification) == 1) {
                    booleanRef.element = true;
                }
            } else if (i == 1) {
                DeviceListBean deviceListBean3 = this.deviceListBean;
                if (deviceListBean3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string23);
                    deviceListBean3 = null;
                }
                String deodorization = deviceListBean3.loopFanBean.getDeodorization();
                Intrinsics.checkNotNullExpressionValue(deodorization, "getDeodorization(...)");
                if (Integer.parseInt(deodorization) == 1) {
                    booleanRef.element = true;
                }
            } else if (i == 2) {
                DeviceListBean deviceListBean4 = this.deviceListBean;
                if (deviceListBean4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string23);
                    deviceListBean4 = null;
                }
                String formaldehyde_removal = deviceListBean4.loopFanBean.getFormaldehyde_removal();
                Intrinsics.checkNotNullExpressionValue(formaldehyde_removal, "getFormaldehyde_removal(...)");
                if (Integer.parseInt(formaldehyde_removal) == 1) {
                    booleanRef.element = true;
                }
            }
            ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_option);
            FrameLayout frameLayout = (FrameLayout) viewInflate.findViewById(R.id.fra_bg);
            ViewGroup.LayoutParams layoutParams3 = imageView.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams3, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
            FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) layoutParams3;
            boolean z3 = booleanRef.element;
            String string24 = StubApp.getString2(13490);
            if (z3) {
                ModeBean modeBean5 = this.modeBean;
                if (modeBean5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string22);
                    modeBean5 = null;
                }
                String str2 = modeBean5.getIconSelected()[i];
                Intrinsics.checkNotNullExpressionValue(str2, string24);
                imageView.setImageDrawable(getDrawable(str2));
                frameLayout.setBackgroundResource(R.drawable.bg_white_circle);
                layoutParams4.height = DensityUtil.dp2px(ICON_SIZE_LOOP_SELECTED);
                layoutParams4.width = DensityUtil.dp2px(ICON_SIZE_LOOP_SELECTED);
                layoutParams4.setMargins(DensityUtil.dp2px(MARGIN_LOOP_ICON), DensityUtil.dp2px(MARGIN_LOOP_ICON), 0, 0);
                modeBean = null;
            } else {
                ModeBean modeBean6 = this.modeBean;
                if (modeBean6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string22);
                    modeBean6 = null;
                }
                String str3 = modeBean6.getIconNormal()[i];
                Intrinsics.checkNotNullExpressionValue(str3, string24);
                imageView.setImageDrawable(getDrawable(str3));
                layoutParams4.height = DensityUtil.dp2px(44.0f);
                layoutParams4.width = DensityUtil.dp2px(44.0f);
                layoutParams4.setMargins(0, 0, 0, 0);
                modeBean = null;
                frameLayout.setBackgroundDrawable(null);
            }
            TextView textView = (TextView) viewInflate.findViewById(R.id.tv_option);
            ModeBean modeBean7 = this.modeBean;
            if (modeBean7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                modeBean7 = modeBean;
            }
            textView.setText(modeBean7.getName()[i]);
            viewInflate.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.HomeControlItemView$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeControlItemView.bindLoopItemView$lambda$7(i, loopFanBean, booleanRef, this, view);
                }
            });
            ViewGroup.LayoutParams layoutParams5 = viewInflate.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams5, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            LinearLayout.LayoutParams layoutParams6 = (LinearLayout.LayoutParams) layoutParams5;
            ModeBean modeBean8 = this.modeBean;
            if (modeBean8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                modeBean8 = modeBean;
            }
            if (i != modeBean8.getName().length - 1) {
                c = CharCompanionObject.MIN_VALUE;
                z = false;
                layoutParams6.setMargins(0, 0, DensityUtil.dp2px(MARGIN_ICON), 0);
            } else {
                c = CharCompanionObject.MIN_VALUE;
                z = false;
                layoutParams6.setMargins(0, 0, 0, 0);
            }
            HomeControlItemViewBinding homeControlItemViewBinding6 = this.binding;
            if (homeControlItemViewBinding6 == 0) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                homeControlItemViewBinding6 = modeBean;
            }
            homeControlItemViewBinding6.llOptionImages.addView(viewInflate, layoutParams6);
            i++;
            z2 = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindLoopItemView$lambda$7(int i, LoopFanBean loopFanBean, Ref.BooleanRef isSelectItem, HomeControlItemView this$0, View view) {
        Intrinsics.checkNotNullParameter(isSelectItem, "$isSelectItem");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string2 = StubApp.getString2(701);
        String string22 = StubApp.getString2(2546);
        if (i == 0) {
            if (!isSelectItem.element) {
                string2 = string22;
            }
            loopFanBean.setHumidification(string2);
        } else if (i == 1) {
            if (!isSelectItem.element) {
                string2 = string22;
            }
            loopFanBean.setDeodorization(string2);
        } else if (i == 2) {
            if (!isSelectItem.element) {
                string2 = string22;
            }
            loopFanBean.setFormaldehyde_removal(string2);
        }
        CommandManger commandManger = CommandManger.INSTANCE;
        DeviceListBean deviceListBean = this$0.deviceListBean;
        String string23 = StubApp.getString2(14601);
        ModeBean modeBean = null;
        if (deviceListBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string23);
            deviceListBean = null;
        }
        String device_id = deviceListBean.getDevice_id();
        Intrinsics.checkNotNullExpressionValue(device_id, "getDevice_id(...)");
        DeviceListBean deviceListBean2 = this$0.deviceListBean;
        if (deviceListBean2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string23);
            deviceListBean2 = null;
        }
        String product_id = deviceListBean2.getProduct_id();
        Intrinsics.checkNotNullExpressionValue(product_id, "getProduct_id(...)");
        commandManger.sendLoopCommand(device_id, product_id, loopFanBean);
        ModeBean modeBean2 = this$0.modeBean;
        if (modeBean2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("modeBean");
        } else {
            modeBean = modeBean2;
        }
        loopFanBean.setMode(modeBean.getValue()[i]);
        this$0.buildPanelItemViews();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006c  */
    /* JADX WARN: Type inference failed for: r0v148, types: [T, io.fogcloud.sdk.fog.bean.DehumidifierBean] */
    /* JADX WARN: Type inference failed for: r0v8, types: [T, io.fogcloud.sdk.fog.bean.DehumidifierBean] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void bindHumItemViews() {
        HomeControlItemViewBinding homeControlItemViewBinding;
        HomeControlItemViewBinding homeControlItemViewBinding2 = this.binding;
        String string2 = StubApp.getString2(13474);
        if (homeControlItemViewBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            homeControlItemViewBinding2 = null;
        }
        ViewGroup.LayoutParams layoutParams = homeControlItemViewBinding2.llOptionImages.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
        ModeBean modeBean = this.modeBean;
        String string22 = StubApp.getString2(14600);
        if (modeBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            modeBean = null;
        }
        boolean z = false;
        if (modeBean.getValue() == null) {
            HomeControlItemViewBinding homeControlItemViewBinding3 = this.binding;
            if (homeControlItemViewBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                homeControlItemViewBinding3 = null;
            }
            homeControlItemViewBinding3.llOptionImages.setPadding(0, 0, 0, 0);
            layoutParams2.setMargins(0, 0, 0, 0);
        } else {
            ModeBean modeBean2 = this.modeBean;
            if (modeBean2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                modeBean2 = null;
            }
            String[] value = modeBean2.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            if (value.length != 0) {
                HomeControlItemViewBinding homeControlItemViewBinding4 = this.binding;
                if (homeControlItemViewBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    homeControlItemViewBinding4 = null;
                }
                homeControlItemViewBinding4.llOptionImages.setPadding(DensityUtil.dp2px(8.0f), 0, DensityUtil.dp2px(8.0f), 0);
                layoutParams2.setMargins(0, 0, DensityUtil.dp2px(MARGIN_ICON), 0);
            }
        }
        HomeControlItemViewBinding homeControlItemViewBinding5 = this.binding;
        if (homeControlItemViewBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            homeControlItemViewBinding5 = null;
        }
        homeControlItemViewBinding5.llOptionImages.setLayoutParams(layoutParams2);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        DeviceListBean deviceListBean = this.deviceListBean;
        String string23 = StubApp.getString2(14601);
        if (deviceListBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string23);
            deviceListBean = null;
        }
        objectRef.element = deviceListBean.getDehumidifierBean();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        DeviceListBean deviceListBean2 = this.deviceListBean;
        if (deviceListBean2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string23);
            deviceListBean2 = null;
        }
        boolean zIsHumidifier = deviceListBean2.isHumidifier();
        String string24 = StubApp.getString2(2546);
        if (zIsHumidifier) {
            DeviceListBean deviceListBean3 = this.deviceListBean;
            if (deviceListBean3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string23);
                deviceListBean3 = null;
            }
            objectRef.element = deviceListBean3.getDehumidifierBean();
            booleanRef.element = Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getE3(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).L2, string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getE5(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getE1(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getP6(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getP7(), string24);
        } else {
            booleanRef.element = Intrinsics.areEqual(((DehumidifierBean) objectRef.element).L2, string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getL3(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getL4(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getE4(), string24);
            DeviceListBean deviceListBean4 = this.deviceListBean;
            if (deviceListBean4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string23);
                deviceListBean4 = null;
            }
            if (Intrinsics.areEqual(deviceListBean4.getProduct_id(), "d71936c6951c11f0a8200242ac480009")) {
                booleanRef.element = Intrinsics.areEqual(((DehumidifierBean) objectRef.element).L2, string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getP0(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getP1(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getP4(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getP5(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getE0(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getE1(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getE2(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getE5(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getE6(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getE7(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getE8(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getEA(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getEB(), string24) || Intrinsics.areEqual(((DehumidifierBean) objectRef.element).getEE(), string24);
            }
        }
        DeviceListBean deviceListBean5 = this.deviceListBean;
        if (deviceListBean5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string23);
            deviceListBean5 = null;
        }
        if (Intrinsics.areEqual(deviceListBean5.getProduct_id(), "d1d7f86ee06c11f0854f0242ac480009")) {
            HomeControlItemViewBinding homeControlItemViewBinding6 = this.binding;
            if (homeControlItemViewBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                homeControlItemViewBinding = null;
            } else {
                homeControlItemViewBinding = homeControlItemViewBinding6;
            }
            homeControlItemViewBinding.llOptionImages.setWeightSum(MARGIN_TOP_SELECTED);
            T element = objectRef.element;
            Intrinsics.checkNotNullExpressionValue(element, "element");
            bindU20ProSimplifiedModes((DehumidifierBean) element, booleanRef.element);
            return;
        }
        ModeBean modeBean3 = this.modeBean;
        if (modeBean3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            modeBean3 = null;
        }
        int length = modeBean3.getValue().length;
        int i = 0;
        while (i < length) {
            final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
            ModeBean modeBean4 = this.modeBean;
            if (modeBean4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                modeBean4 = null;
            }
            if (Intrinsics.areEqual(modeBean4.getValue()[i], ((DehumidifierBean) objectRef.element).getMode())) {
                booleanRef2.element = true;
            }
            Context context = this.mContext;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context = null;
            }
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
            HomeControlItemViewBinding homeControlItemViewBinding7 = this.binding;
            if (homeControlItemViewBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                homeControlItemViewBinding7 = null;
            }
            View viewInflate = layoutInflaterFrom.inflate(R.layout.home_item_opt_image, homeControlItemViewBinding7.llOptionImages, z);
            ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_option);
            ViewGroup.LayoutParams layoutParams3 = imageView.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams3, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
            FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) layoutParams3;
            TextView textView = (TextView) viewInflate.findViewById(R.id.tv_option);
            boolean z2 = booleanRef2.element;
            String string25 = StubApp.getString2(13490);
            if (z2) {
                ModeBean modeBean5 = this.modeBean;
                if (modeBean5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string22);
                    modeBean5 = null;
                }
                String str = modeBean5.getIconSelected()[i];
                Intrinsics.checkNotNullExpressionValue(str, string25);
                imageView.setImageDrawable(getDrawable(str));
                layoutParams4.height = DensityUtil.dp2px(44.0f);
                layoutParams4.width = DensityUtil.dp2px(44.0f);
                textView.setTextColor(getResources().getColor(R.color.blue_text));
            } else {
                ModeBean modeBean6 = this.modeBean;
                if (modeBean6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string22);
                    modeBean6 = null;
                }
                String str2 = modeBean6.getIconNormal()[i];
                Intrinsics.checkNotNullExpressionValue(str2, string25);
                imageView.setImageDrawable(getDrawable(str2));
                layoutParams4.height = DensityUtil.dp2px(ICON_SIZE_UNSELECTED);
                layoutParams4.width = DensityUtil.dp2px(ICON_SIZE_UNSELECTED);
                textView.setTextColor(getResources().getColor(R.color.color_dark));
            }
            ModeBean modeBean7 = this.modeBean;
            if (modeBean7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                modeBean7 = null;
            }
            textView.setText(modeBean7.getName()[i]);
            if (booleanRef.element) {
                Intrinsics.checkNotNull(viewInflate);
                ViewExtendsKt.setAvailable(viewInflate, z);
            } else {
                Intrinsics.checkNotNull(viewInflate);
                ViewExtendsKt.setAvailable(viewInflate, true);
            }
            final int i2 = i;
            viewInflate.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.HomeControlItemView$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeControlItemView.bindHumItemViews$lambda$8(booleanRef2, booleanRef, this, i2, objectRef, view);
                }
            });
            HomeControlItemViewBinding homeControlItemViewBinding8 = this.binding;
            if (homeControlItemViewBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                homeControlItemViewBinding8 = null;
            }
            homeControlItemViewBinding8.llOptionImages.addView(viewInflate);
            i++;
            z = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void bindHumItemViews$lambda$8(Ref.BooleanRef isSelectItem, Ref.BooleanRef isFault, HomeControlItemView this$0, int i, Ref.ObjectRef dehumidifierBean, View view) {
        Intrinsics.checkNotNullParameter(isSelectItem, "$isSelectItem");
        Intrinsics.checkNotNullParameter(isFault, "$isFault");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dehumidifierBean, "$dehumidifierBean");
        if (isSelectItem.element || isFault.element) {
            return;
        }
        CommandManger commandManger = CommandManger.INSTANCE;
        DeviceListBean deviceListBean = this$0.deviceListBean;
        String string2 = StubApp.getString2(14601);
        ModeBean modeBean = null;
        if (deviceListBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            deviceListBean = null;
        }
        String device_id = deviceListBean.getDevice_id();
        Intrinsics.checkNotNullExpressionValue(device_id, "getDevice_id(...)");
        DeviceListBean deviceListBean2 = this$0.deviceListBean;
        if (deviceListBean2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            deviceListBean2 = null;
        }
        String product_id = deviceListBean2.getProduct_id();
        Intrinsics.checkNotNullExpressionValue(product_id, "getProduct_id(...)");
        ModeBean modeBean2 = this$0.modeBean;
        String string22 = StubApp.getString2(14600);
        if (modeBean2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
            modeBean2 = null;
        }
        String str = modeBean2.getValue()[i];
        Intrinsics.checkNotNullExpressionValue(str, "get(...)");
        DeviceListBean deviceListBean3 = this$0.deviceListBean;
        if (deviceListBean3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            deviceListBean3 = null;
        }
        boolean zIsFogPlatform = deviceListBean3.isFogPlatform();
        DeviceListBean deviceListBean4 = this$0.deviceListBean;
        if (deviceListBean4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            deviceListBean4 = null;
        }
        boolean zIsUseComboProtocol = deviceListBean4.isUseComboProtocol();
        T element = dehumidifierBean.element;
        Intrinsics.checkNotNullExpressionValue(element, "element");
        commandManger.sendModeCommand(device_id, product_id, str, zIsFogPlatform, zIsUseComboProtocol, (DehumidifierBean) element);
        DehumidifierBean dehumidifierBean2 = (DehumidifierBean) dehumidifierBean.element;
        ModeBean modeBean3 = this$0.modeBean;
        if (modeBean3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string22);
        } else {
            modeBean = modeBean3;
        }
        dehumidifierBean2.setMode(modeBean.getValue()[i]);
        this$0.buildPanelItemViews();
    }

    private final void bindU20ProSimplifiedModes(final DehumidifierBean dehumidifierBean, final boolean isFault) {
        SimplifiedMode[] simplifiedModeArr = new SimplifiedMode[2];
        Context context = this.mContext;
        String string2 = StubApp.getString2(14605);
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            context = null;
        }
        String string = context.getString(R.string.dehumidify);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        simplifiedModeArr[0] = new SimplifiedMode(string, StubApp.getString2(701), StubApp.getString2(14607), StubApp.getString2(14606), true);
        Context context2 = this.mContext;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            context2 = null;
        }
        String string3 = context2.getString(R.string.purify);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        simplifiedModeArr[1] = new SimplifiedMode(string3, StubApp.getString2(8034), StubApp.getString2(14609), StubApp.getString2(14608), false);
        for (final SimplifiedMode simplifiedMode : CollectionsKt.listOf((Object[]) simplifiedModeArr)) {
            String mode = dehumidifierBean.getMode();
            Intrinsics.checkNotNullExpressionValue(mode, "getMode(...)");
            final boolean zIsU20ProModeSelected = isU20ProModeSelected(mode, simplifiedMode.isDehumidifyCategory());
            View viewCreateModeItemView = createModeItemView(simplifiedMode.getIconSelected(), simplifiedMode.getIconNormal(), simplifiedMode.getName(), zIsU20ProModeSelected, isFault);
            viewCreateModeItemView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.HomeControlItemView$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeControlItemView.bindU20ProSimplifiedModes$lambda$10$lambda$9(zIsU20ProModeSelected, isFault, this, simplifiedMode, dehumidifierBean, view);
                }
            });
            HomeControlItemViewBinding homeControlItemViewBinding = this.binding;
            if (homeControlItemViewBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                homeControlItemViewBinding = null;
            }
            homeControlItemViewBinding.llOptionImages.addView(viewCreateModeItemView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindU20ProSimplifiedModes$lambda$10$lambda$9(boolean z, boolean z2, HomeControlItemView this$0, SimplifiedMode mode, DehumidifierBean dehumidifierBean, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mode, "$mode");
        Intrinsics.checkNotNullParameter(dehumidifierBean, "$dehumidifierBean");
        if (z || z2) {
            return;
        }
        this$0.sendModeCommand(mode.getValue(), dehumidifierBean);
    }

    private final boolean isU20ProModeSelected(String currentMode, boolean isDehumidifyCategory) {
        if (isDehumidifyCategory) {
            return DEHUMIDIFY_MODES.contains(currentMode);
        }
        return PURIFY_MODES.contains(currentMode);
    }

    private final View createModeItemView(String iconSelected, String iconNormal, String modeName, boolean isSelected, boolean isFault) {
        Context context = this.mContext;
        HomeControlItemViewBinding homeControlItemViewBinding = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        HomeControlItemViewBinding homeControlItemViewBinding2 = this.binding;
        if (homeControlItemViewBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            homeControlItemViewBinding = homeControlItemViewBinding2;
        }
        View viewInflate = layoutInflaterFrom.inflate(R.layout.home_item_opt_image, (ViewGroup) homeControlItemViewBinding.llOptionImages, false);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_option);
        FrameLayout frameLayout = (FrameLayout) viewInflate.findViewById(R.id.fra_bg);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_option);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        Intrinsics.checkNotNull(imageView);
        Intrinsics.checkNotNull(frameLayout);
        configureIcon(imageView, frameLayout, (FrameLayout.LayoutParams) layoutParams, iconSelected, iconNormal, isSelected);
        textView.setText(modeName);
        Intrinsics.checkNotNull(viewInflate);
        ViewExtendsKt.setAvailable(viewInflate, !isFault);
        return viewInflate;
    }

    private final void configureIcon(ImageView imageView, FrameLayout fraBg, FrameLayout.LayoutParams params, String iconSelected, String iconNormal, boolean isSelected) {
        if (isSelected) {
            imageView.setImageDrawable(getDrawable(iconSelected));
            fraBg.setBackgroundResource(R.drawable.bg_white_circle);
            params.height = DensityUtil.dp2px(44.0f);
            params.width = DensityUtil.dp2px(44.0f);
            return;
        }
        imageView.setImageDrawable(getDrawable(iconNormal));
        params.height = DensityUtil.dp2px(ICON_SIZE_UNSELECTED);
        params.width = DensityUtil.dp2px(ICON_SIZE_UNSELECTED);
    }

    private final void sendModeCommand(String mode, DehumidifierBean dehumidifierBean) {
        CommandManger commandManger = CommandManger.INSTANCE;
        DeviceListBean deviceListBean = this.deviceListBean;
        DeviceListBean deviceListBean2 = null;
        String string2 = StubApp.getString2(14601);
        if (deviceListBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            deviceListBean = null;
        }
        String device_id = deviceListBean.getDevice_id();
        Intrinsics.checkNotNullExpressionValue(device_id, "getDevice_id(...)");
        DeviceListBean deviceListBean3 = this.deviceListBean;
        if (deviceListBean3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            deviceListBean3 = null;
        }
        String product_id = deviceListBean3.getProduct_id();
        Intrinsics.checkNotNullExpressionValue(product_id, "getProduct_id(...)");
        DeviceListBean deviceListBean4 = this.deviceListBean;
        if (deviceListBean4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            deviceListBean4 = null;
        }
        boolean zIsFogPlatform = deviceListBean4.isFogPlatform();
        DeviceListBean deviceListBean5 = this.deviceListBean;
        if (deviceListBean5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            deviceListBean2 = deviceListBean5;
        }
        commandManger.sendModeCommand(device_id, product_id, mode, zIsFogPlatform, deviceListBean2.isUseComboProtocol(), dehumidifierBean);
        dehumidifierBean.setMode(mode);
        buildPanelItemViews();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: HomeControlItemView.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J;\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/deye/views/HomeControlItemView$SimplifiedMode;", "", "name", "", "value", "iconNormal", "iconSelected", "isDehumidifyCategory", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getIconNormal", "()Ljava/lang/String;", "getIconSelected", "()Z", "getName", "getValue", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final /* data */ class SimplifiedMode {
        private final String iconNormal;
        private final String iconSelected;
        private final boolean isDehumidifyCategory;
        private final String name;
        private final String value;

        public static /* synthetic */ SimplifiedMode copy$default(SimplifiedMode simplifiedMode, String str, String str2, String str3, String str4, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = simplifiedMode.name;
            }
            if ((i & 2) != 0) {
                str2 = simplifiedMode.value;
            }
            String str5 = str2;
            if ((i & 4) != 0) {
                str3 = simplifiedMode.iconNormal;
            }
            String str6 = str3;
            if ((i & 8) != 0) {
                str4 = simplifiedMode.iconSelected;
            }
            String str7 = str4;
            if ((i & 16) != 0) {
                z = simplifiedMode.isDehumidifyCategory;
            }
            return simplifiedMode.copy(str, str5, str6, str7, z);
        }

        /* renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component2, reason: from getter */
        public final String getValue() {
            return this.value;
        }

        /* renamed from: component3, reason: from getter */
        public final String getIconNormal() {
            return this.iconNormal;
        }

        /* renamed from: component4, reason: from getter */
        public final String getIconSelected() {
            return this.iconSelected;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getIsDehumidifyCategory() {
            return this.isDehumidifyCategory;
        }

        public final SimplifiedMode copy(String name, String value, String iconNormal, String iconSelected, boolean isDehumidifyCategory) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(iconNormal, "iconNormal");
            Intrinsics.checkNotNullParameter(iconSelected, "iconSelected");
            return new SimplifiedMode(name, value, iconNormal, iconSelected, isDehumidifyCategory);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SimplifiedMode)) {
                return false;
            }
            SimplifiedMode simplifiedMode = (SimplifiedMode) other;
            return Intrinsics.areEqual(this.name, simplifiedMode.name) && Intrinsics.areEqual(this.value, simplifiedMode.value) && Intrinsics.areEqual(this.iconNormal, simplifiedMode.iconNormal) && Intrinsics.areEqual(this.iconSelected, simplifiedMode.iconSelected) && this.isDehumidifyCategory == simplifiedMode.isDehumidifyCategory;
        }

        public int hashCode() {
            return (((((((this.name.hashCode() * 31) + this.value.hashCode()) * 31) + this.iconNormal.hashCode()) * 31) + this.iconSelected.hashCode()) * 31) + Boolean.hashCode(this.isDehumidifyCategory);
        }

        public String toString() {
            return StubApp.getString2(14596) + this.name + StubApp.getString2(11314) + this.value + StubApp.getString2(14597) + this.iconNormal + StubApp.getString2(14598) + this.iconSelected + StubApp.getString2(14599) + this.isDehumidifyCategory + StubApp.getString2(2345);
        }

        public SimplifiedMode(String name, String value, String iconNormal, String iconSelected, boolean z) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(iconNormal, "iconNormal");
            Intrinsics.checkNotNullParameter(iconSelected, "iconSelected");
            this.name = name;
            this.value = value;
            this.iconNormal = iconNormal;
            this.iconSelected = iconSelected;
            this.isDehumidifyCategory = z;
        }

        public final String getName() {
            return this.name;
        }

        public final String getValue() {
            return this.value;
        }

        public final String getIconNormal() {
            return this.iconNormal;
        }

        public final String getIconSelected() {
            return this.iconSelected;
        }

        public final boolean isDehumidifyCategory() {
            return this.isDehumidifyCategory;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean handleWaterTankState(DeviceListBean device, DehumidifierBean dehumidifier) {
        String string;
        HomeControlItemViewBinding homeControlItemViewBinding = null;
        boolean zAreEqual = Intrinsics.areEqual(dehumidifier != null ? dehumidifier.getWatertank_state() : null, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY);
        String string2 = StubApp.getString2(13474);
        if (!zAreEqual) {
            if (Intrinsics.areEqual(dehumidifier != null ? dehumidifier.waterStatus : null, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
            }
        } else if (!device.isAirConditioner()) {
            HomeControlItemViewBinding homeControlItemViewBinding2 = this.binding;
            if (homeControlItemViewBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                homeControlItemViewBinding2 = null;
            }
            TextView textView = homeControlItemViewBinding2.tvWaterFull;
            boolean zIsHumidifier = device.isHumidifier();
            String string22 = StubApp.getString2(14605);
            if (zIsHumidifier) {
                Context context = this.mContext;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string22);
                    context = null;
                }
                string = context.getString(R.string.home_device_adding_water);
            } else {
                Context context2 = this.mContext;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string22);
                    context2 = null;
                }
                string = context2.getString(R.string.home_water_tank_full);
            }
            textView.setText(string);
            HomeControlItemViewBinding homeControlItemViewBinding3 = this.binding;
            if (homeControlItemViewBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                homeControlItemViewBinding3 = null;
            }
            homeControlItemViewBinding3.tvWaterFull.setVisibility(0);
            HomeControlItemViewBinding homeControlItemViewBinding4 = this.binding;
            if (homeControlItemViewBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                homeControlItemViewBinding4 = null;
            }
            homeControlItemViewBinding4.llOptions.setVisibility(8);
            HomeControlItemViewBinding homeControlItemViewBinding5 = this.binding;
            if (homeControlItemViewBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                homeControlItemViewBinding = homeControlItemViewBinding5;
            }
            homeControlItemViewBinding.llPowerOn.setVisibility(8);
            return true;
        }
        HomeControlItemViewBinding homeControlItemViewBinding6 = this.binding;
        if (homeControlItemViewBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            homeControlItemViewBinding = homeControlItemViewBinding6;
        }
        homeControlItemViewBinding.tvWaterFull.setVisibility(8);
        return false;
    }

    private final Drawable getDrawable(String imgName) throws IOException {
        if (this.drawableCache.containsKey(imgName)) {
            return this.drawableCache.get(imgName);
        }
        Drawable drawable = null;
        try {
            Context context = this.mContext;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context = null;
            }
            InputStream inputStreamOpen = context.getAssets().open(imgName);
            try {
                Drawable drawableCreateFromStream = Drawable.createFromStream(inputStreamOpen, null);
                CloseableKt.closeFinally(inputStreamOpen, null);
                drawable = drawableCreateFromStream;
            } finally {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.drawableCache.put(imgName, drawable);
        return drawable;
    }
}
