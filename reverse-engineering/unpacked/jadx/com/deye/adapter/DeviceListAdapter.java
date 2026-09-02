package com.deye.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.amap.location.support.bean.location.AmapLocationNetwork;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.deye.MxchipApplication;
import com.deye.activity.config_net.DeviceShowShareQrAty;
import com.deye.activity.config_net.ModifyDeviceNameAty;
import com.deye.adapter.DeviceListAdapter;
import com.deye.configs.Constants;
import com.deye.entity.control_panel.dehumidifier.func.ModeBean;
import com.deye.event.RefreshDeviceEvent;
import com.deye.helper.DialogHelper;
import com.deye.listener.OnItemClickListener;
import com.deye.utils.BaseUtils;
import com.deye.utils.PanelHelper;
import com.google.gson.Gson;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.bean.DehumidifierBean;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.bean.LoopFanBean;
import io.fogcloud.sdk.fog.callback.ManageDeviceCallBack;
import io.fogcloud.sdk.fog.log.LogDebug;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

/* compiled from: DeviceListAdapter.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001)B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002J\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\fJ\u001c\u0010\u0014\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0017\u001a\u00020\fH\u0016J\u001c\u0010\u0018\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0016\u0010\u001c\u001a\u00020\u00152\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0007J\u0010\u0010\u001e\u001a\u00020\u00152\b\u0010\u001f\u001a\u0004\u0018\u00010\nJ$\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\"2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00150$H\u0002J\u000e\u0010%\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\u0007J\u0016\u0010'\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/deye/adapter/DeviceListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/deye/adapter/DeviceListAdapter$ViewHolder;", "mContext", "Landroid/content/Context;", "mDeviceListBeanList", "", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "(Landroid/content/Context;Ljava/util/List;)V", "mOnItemClickListener", "Lcom/deye/listener/OnItemClickListener;", "getItemCount", "", "isEqual", "", "one", "Lio/fogcloud/sdk/fog/bean/DehumidifierBean;", "two", "isOnline", "index", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setDateList", "beanList", "setOnItemClickListener", "onItemClickListener", "unBind", "deviceId", "", "callback", "Lkotlin/Function1;", "updateDehumidifyBean", "bean", "updateOnlineState", "online", "ViewHolder", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class DeviceListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context mContext;
    private final List<DeviceListBean> mDeviceListBeanList;
    private OnItemClickListener mOnItemClickListener;

    public DeviceListAdapter(Context mContext, List<DeviceListBean> mDeviceListBeanList) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(mDeviceListBeanList, "mDeviceListBeanList");
        this.mContext = mContext;
        this.mDeviceListBeanList = mDeviceListBeanList;
    }

    public final void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public final void setDateList(List<DeviceListBean> beanList) {
        Intrinsics.checkNotNullParameter(beanList, "beanList");
        this.mDeviceListBeanList.clear();
        this.mDeviceListBeanList.addAll(beanList);
        notifyDataSetChanged();
    }

    public final void updateDehumidifyBean(DeviceListBean bean) {
        Intrinsics.checkNotNullParameter(bean, "bean");
        int i = 0;
        for (DeviceListBean deviceListBean : this.mDeviceListBeanList) {
            int i2 = i + 1;
            if (deviceListBean.getDevice_id().equals(bean.getDevice_id())) {
                if (!isEqual(deviceListBean.getDehumidifierBean(), bean.getDehumidifierBean())) {
                    LogDebug.INSTANCE.log(StubApp.getString2(13798) + i + StubApp.getString2(626) + bean.getDehumidifierBean());
                    deviceListBean.setDehumidifierBean(bean.getDehumidifierBean());
                    notifyItemChanged(i);
                } else {
                    LogDebug.INSTANCE.log(StubApp.getString2(13799) + i);
                }
            }
            i = i2;
        }
    }

    public final void updateOnlineState(DeviceListBean bean, boolean online) {
        Intrinsics.checkNotNullParameter(bean, "bean");
        int i = 0;
        for (DeviceListBean deviceListBean : this.mDeviceListBeanList) {
            int i2 = i + 1;
            if (deviceListBean.getDevice_id().equals(bean.getDevice_id())) {
                LogDebug.INSTANCE.log(StubApp.getString2(13802) + i + StubApp.getString2(13803) + online);
                deviceListBean.setOnline(Boolean.valueOf(online));
            }
            i = i2;
        }
    }

    private final boolean isEqual(DehumidifierBean one, DehumidifierBean two) {
        return one != null && two != null && one.checkIsPowerOn() == two.checkIsPowerOn() && one.getMode().equals(two.getMode());
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.fragment_equipment_item_rcy, parent, false);
        Intrinsics.checkNotNull(viewInflate);
        return new ViewHolder(this, viewInflate);
    }

    public void onBindViewHolder(final ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final int bindingAdapterPosition = holder.getBindingAdapterPosition();
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.DeviceListAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceListAdapter.onBindViewHolder$lambda$0(this.f$0, bindingAdapterPosition, view);
            }
        });
        if (this.mDeviceListBeanList.isEmpty()) {
            return;
        }
        final DeviceListBean deviceListBean = this.mDeviceListBeanList.get(bindingAdapterPosition);
        String product_icon = deviceListBean.getProduct_icon();
        if (!BaseUtils.isNullString(product_icon)) {
            RequestOptions requestOptionsDiskCacheStrategy = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            Intrinsics.checkNotNullExpressionValue(requestOptionsDiskCacheStrategy, "diskCacheStrategy(...)");
            Glide.with(this.mContext).load(product_icon).apply((BaseRequestOptions<?>) requestOptionsDiskCacheStrategy).into(holder.getIv_device_icon());
        }
        boolean zIsOnline = isOnline(bindingAdapterPosition);
        holder.getTv_device_name().setText(deviceListBean.getDevice_name());
        ModeBean mode = PanelHelper.INSTANCE.getPanelBean(this.mContext, deviceListBean.getProduct_id()).getMode();
        String[] value = mode.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        if (value.length == 0) {
            holder.getTv_device_state().setVisibility(8);
        } else {
            holder.getTv_device_state().setVisibility(0);
        }
        DehumidifierBean dehumidifierBean = deviceListBean.getDehumidifierBean();
        holder.getTv_device_name().setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, MxchipApplication.getInstance().getDrawable(zIsOnline ? R.drawable.icon_home_online : R.drawable.icon_home_offline), (Drawable) null);
        if (deviceListBean.isAirConditioner()) {
            holder.getTv_msg_left().setText(this.mContext.getString(R.string.indoor_temperature));
            holder.getTv_msg_right().setText(this.mContext.getString(R.string.set_temperature));
            TextView tv_left_unit = holder.getTv_left_unit();
            String string2 = StubApp.getString2(13800);
            tv_left_unit.setText(string2);
            holder.getTv_right_unit().setText(string2);
        } else {
            holder.getTv_msg_left().setText(this.mContext.getString(R.string.indoor_humidity));
            holder.getTv_msg_right().setText(this.mContext.getString(R.string.set_humidity));
            TextView tv_left_unit2 = holder.getTv_left_unit();
            String string22 = StubApp.getString2(5130);
            tv_left_unit2.setText(string22);
            holder.getTv_right_unit().setText(string22);
        }
        if (dehumidifierBean != null) {
            if (dehumidifierBean.checkIsPowerOn()) {
                int length = mode.getValue().length;
                for (int i = 0; i < length; i++) {
                    if (mode.getValue()[i].equals(dehumidifierBean.getMode())) {
                        holder.getTv_device_state().setText(mode.getName()[i]);
                        holder.getTv_device_state().setBackgroundResource(R.drawable.bg_list_item_on);
                    }
                }
            } else {
                holder.getTv_device_state().setText(this.mContext.getString(R.string.Off));
                holder.getTv_device_state().setBackgroundResource(R.drawable.bg_list_item_off);
            }
            if (deviceListBean.isAirConditioner()) {
                TextView tv_indoor_humidity = holder.getTv_indoor_humidity();
                Intrinsics.checkNotNullExpressionValue(dehumidifierBean.getCurrent_env_temp(), "getCurrent_env_temp(...)");
                tv_indoor_humidity.setText(String.valueOf(Integer.parseInt(r2) - 40));
                holder.getTv_set_humidity().setText(dehumidifierBean.temperature_set);
            } else {
                holder.getTv_indoor_humidity().setText(dehumidifierBean.getCurrent_env_hum());
                if (!Constants.isH7Product(deviceListBean.getProduct_id()) || Intrinsics.areEqual(deviceListBean.getDehumidifierBean().getMode(), AmapLocationNetwork.RESULT_TYPE_CELL_ONLY)) {
                    holder.getTv_set_humidity().setText(dehumidifierBean.getHum_set());
                    holder.getTv_right_unit().setVisibility(0);
                } else {
                    holder.getTv_set_humidity().setText(StubApp.getString2(13801));
                    holder.getTv_right_unit().setVisibility(8);
                }
            }
        }
        LoopFanBean loopFanBean = deviceListBean.loopFanBean;
        if (loopFanBean != null) {
            holder.getTv_device_state().setText("");
            holder.getTv_indoor_humidity().setText(loopFanBean.getCurrent_env_hum());
            holder.getTv_set_humidity().setText(loopFanBean.getHum_set());
        }
        holder.getLl_more().setVisibility(8);
        holder.getIv_more().setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.DeviceListAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceListAdapter.onBindViewHolder$lambda$3(holder, deviceListBean, view);
            }
        });
        holder.getLl_more().setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.DeviceListAdapter$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceListAdapter.onBindViewHolder$lambda$4(holder, view);
            }
        });
        holder.getLl_share().setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.DeviceListAdapter$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceListAdapter.onBindViewHolder$lambda$5(holder, this, deviceListBean, view);
            }
        });
        holder.getLl_rename().setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.DeviceListAdapter$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceListAdapter.onBindViewHolder$lambda$6(holder, this, deviceListBean, view);
            }
        });
        holder.getLl_delete().setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.DeviceListAdapter$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceListAdapter.onBindViewHolder$lambda$7(holder, this, deviceListBean, bindingAdapterPosition, view);
            }
        });
        CharSequence text = holder.getTv_device_state().getText();
        if (text == null || text.length() == 0) {
            holder.getTv_device_state().setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(DeviceListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnItemClickListener onItemClickListener = this$0.mOnItemClickListener;
        if (onItemClickListener != null) {
            Intrinsics.checkNotNull(onItemClickListener);
            onItemClickListener.onItemClick(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$3(ViewHolder holder, DeviceListBean deviceListBean, View view) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(deviceListBean, "$deviceListBean");
        holder.getLl_more().setVisibility(0);
        if (deviceListBean.getRole() == 1) {
            holder.getFra_share().setVisibility(0);
        } else {
            holder.getFra_share().setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$4(ViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        holder.getLl_more().setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$5(ViewHolder holder, DeviceListAdapter this$0, DeviceListBean deviceListBean, View view) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(deviceListBean, "$deviceListBean");
        holder.getLl_more().setVisibility(8);
        Intent intent = new Intent(this$0.mContext, (Class<?>) DeviceShowShareQrAty.class);
        intent.putExtra(StubApp.getString2(13055), deviceListBean.getDevice_id());
        intent.putExtra(StubApp.getString2(13453), deviceListBean.getDevice_name());
        this$0.mContext.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$6(ViewHolder holder, DeviceListAdapter this$0, DeviceListBean deviceListBean, View view) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(deviceListBean, "$deviceListBean");
        holder.getLl_more().setVisibility(8);
        Intent intent = new Intent(this$0.mContext, (Class<?>) ModifyDeviceNameAty.class);
        intent.putExtra(StubApp.getString2(13055), deviceListBean.getDevice_id());
        intent.putExtra(StubApp.getString2(13454), new Gson().toJson(deviceListBean));
        this$0.mContext.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$7(ViewHolder holder, final DeviceListAdapter this$0, final DeviceListBean deviceListBean, final int i, View view) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(deviceListBean, "$deviceListBean");
        holder.getLl_more().setVisibility(8);
        FragmentActivity fragmentActivity = this$0.mContext;
        Intrinsics.checkNotNull(fragmentActivity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        DialogHelper.showDeleteDialog(fragmentActivity, this$0.mContext.getString(R.string.confirm_delete_device_format, deviceListBean.getDevice_name()), "", new DialogHelper.OnDialogListener() { // from class: com.deye.adapter.DeviceListAdapter$onBindViewHolder$8$1
            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onSure(String text) throws JSONException {
                DeviceListAdapter deviceListAdapter = this.this$0;
                String device_id = deviceListBean.getDevice_id();
                Intrinsics.checkNotNullExpressionValue(device_id, "getDevice_id(...)");
                final DeviceListAdapter deviceListAdapter2 = this.this$0;
                final int i2 = i;
                deviceListAdapter.unBind(device_id, new Function1<Boolean, Unit>() { // from class: com.deye.adapter.DeviceListAdapter$onBindViewHolder$8$1$onSure$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z) {
                        deviceListAdapter2.mDeviceListBeanList.remove(i2);
                        deviceListAdapter2.notifyItemRemoved(i2);
                        EventBus.getDefault().post(new RefreshDeviceEvent());
                    }
                });
            }
        });
    }

    /* compiled from: DeviceListAdapter.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"com/deye/adapter/DeviceListAdapter$unBind$1", "Lio/fogcloud/sdk/fog/callback/ManageDeviceCallBack;", "onFailure", "", "code", "", "message", "", "onSuccess", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.adapter.DeviceListAdapter$unBind$1, reason: invalid class name */
    public static final class AnonymousClass1 implements ManageDeviceCallBack {
        final /* synthetic */ Function1<Boolean, Unit> $callback;
        final /* synthetic */ DeviceListAdapter this$0;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function1<? super Boolean, Unit> function1, DeviceListAdapter deviceListAdapter) {
            this.$callback = function1;
            this.this$0 = deviceListAdapter;
        }

        @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
        public void onSuccess(final String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Handler handler = new Handler(Looper.getMainLooper());
            final Function1<Boolean, Unit> function1 = this.$callback;
            final DeviceListAdapter deviceListAdapter = this.this$0;
            handler.post(new Runnable() { // from class: com.deye.adapter.DeviceListAdapter$unBind$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceListAdapter.AnonymousClass1.onSuccess$lambda$0(message, function1, deviceListAdapter);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSuccess$lambda$0(String message, Function1 callback, DeviceListAdapter this$0) {
            Intrinsics.checkNotNullParameter(message, "$message");
            Intrinsics.checkNotNullParameter(callback, "$callback");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Log.d(StubApp.getString2(13452), message);
            callback.invoke(true);
            BaseUtils.showShortToast(this$0.mContext, this$0.mContext.getString(R.string.unbind_success));
        }

        @Override // io.fogcloud.sdk.fog.callback.ManageDeviceCallBack
        public void onFailure(int code, String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            BaseUtils.showShortToast(this.this$0.mContext, this.this$0.mContext.getString(R.string.unbind_failed));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unBind(String deviceId, Function1<? super Boolean, Unit> callback) throws JSONException {
        DeYeHttpRequestManager.getInstance().unBindDevice(deviceId, new AnonymousClass1(callback, this));
    }

    public final boolean isOnline(int index) {
        if (this.mDeviceListBeanList.isEmpty()) {
            return false;
        }
        Boolean online = this.mDeviceListBeanList.get(index).getOnline();
        Intrinsics.checkNotNullExpressionValue(online, "getOnline(...)");
        return online.booleanValue();
    }

    public int getItemCount() {
        return this.mDeviceListBeanList.size();
    }

    /* compiled from: DeviceListAdapter.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u001a\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\u001a\u0010\u001d\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u001a\u0010 \u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0017\"\u0004\b\"\u0010\u0019R\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010&\"\u0004\b+\u0010(R\u001a\u0010,\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010&\"\u0004\b.\u0010(R\u001a\u0010/\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010&\"\u0004\b1\u0010(R\u001a\u00102\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010&\"\u0004\b4\u0010(R\u001a\u00105\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010&\"\u0004\b7\u0010(R\u001a\u00108\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010&\"\u0004\b:\u0010(R\u001a\u0010;\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010&\"\u0004\b=\u0010(¨\u0006>"}, d2 = {"Lcom/deye/adapter/DeviceListAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/deye/adapter/DeviceListAdapter;Landroid/view/View;)V", "fra_share", "Landroid/widget/FrameLayout;", "getFra_share", "()Landroid/widget/FrameLayout;", "setFra_share", "(Landroid/widget/FrameLayout;)V", "iv_device_icon", "Landroid/widget/ImageView;", "getIv_device_icon", "()Landroid/widget/ImageView;", "setIv_device_icon", "(Landroid/widget/ImageView;)V", "iv_more", "getIv_more", "setIv_more", "ll_delete", "Landroid/widget/LinearLayout;", "getLl_delete", "()Landroid/widget/LinearLayout;", "setLl_delete", "(Landroid/widget/LinearLayout;)V", "ll_more", "getLl_more", "setLl_more", "ll_rename", "getLl_rename", "setLl_rename", "ll_share", "getLl_share", "setLl_share", "tv_device_name", "Landroid/widget/TextView;", "getTv_device_name", "()Landroid/widget/TextView;", "setTv_device_name", "(Landroid/widget/TextView;)V", "tv_device_state", "getTv_device_state", "setTv_device_state", "tv_indoor_humidity", "getTv_indoor_humidity", "setTv_indoor_humidity", "tv_left_unit", "getTv_left_unit", "setTv_left_unit", "tv_msg_left", "getTv_msg_left", "setTv_msg_left", "tv_msg_right", "getTv_msg_right", "setTv_msg_right", "tv_right_unit", "getTv_right_unit", "setTv_right_unit", "tv_set_humidity", "getTv_set_humidity", "setTv_set_humidity", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout fra_share;
        private ImageView iv_device_icon;
        private ImageView iv_more;
        private LinearLayout ll_delete;
        private LinearLayout ll_more;
        private LinearLayout ll_rename;
        private LinearLayout ll_share;
        final /* synthetic */ DeviceListAdapter this$0;
        private TextView tv_device_name;
        private TextView tv_device_state;
        private TextView tv_indoor_humidity;
        private TextView tv_left_unit;
        private TextView tv_msg_left;
        private TextView tv_msg_right;
        private TextView tv_right_unit;
        private TextView tv_set_humidity;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(DeviceListAdapter deviceListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = deviceListAdapter;
            View viewFindViewById = itemView.findViewById(R.id.iv_device_icon);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.iv_device_icon = (ImageView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.tv_device_state);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.tv_device_state = (TextView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(R.id.tv_device_name);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
            this.tv_device_name = (TextView) viewFindViewById3;
            View viewFindViewById4 = itemView.findViewById(R.id.tv_indoor_humidity);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
            this.tv_indoor_humidity = (TextView) viewFindViewById4;
            View viewFindViewById5 = itemView.findViewById(R.id.tv_set_humidity);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
            this.tv_set_humidity = (TextView) viewFindViewById5;
            View viewFindViewById6 = itemView.findViewById(R.id.tv_msg_left);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(...)");
            this.tv_msg_left = (TextView) viewFindViewById6;
            View viewFindViewById7 = itemView.findViewById(R.id.tv_msg_right);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(...)");
            this.tv_msg_right = (TextView) viewFindViewById7;
            View viewFindViewById8 = itemView.findViewById(R.id.tv_left_unit);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "findViewById(...)");
            this.tv_left_unit = (TextView) viewFindViewById8;
            View viewFindViewById9 = itemView.findViewById(R.id.tv_right_unit);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById9, "findViewById(...)");
            this.tv_right_unit = (TextView) viewFindViewById9;
            View viewFindViewById10 = itemView.findViewById(R.id.iv_more);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById10, "findViewById(...)");
            this.iv_more = (ImageView) viewFindViewById10;
            View viewFindViewById11 = itemView.findViewById(R.id.ll_more);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById11, "findViewById(...)");
            this.ll_more = (LinearLayout) viewFindViewById11;
            View viewFindViewById12 = itemView.findViewById(R.id.ll_share);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById12, "findViewById(...)");
            this.ll_share = (LinearLayout) viewFindViewById12;
            View viewFindViewById13 = itemView.findViewById(R.id.fra_share);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById13, "findViewById(...)");
            this.fra_share = (FrameLayout) viewFindViewById13;
            View viewFindViewById14 = itemView.findViewById(R.id.ll_rename);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById14, "findViewById(...)");
            this.ll_rename = (LinearLayout) viewFindViewById14;
            View viewFindViewById15 = itemView.findViewById(R.id.ll_delete);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById15, "findViewById(...)");
            this.ll_delete = (LinearLayout) viewFindViewById15;
        }

        public final TextView getTv_device_state() {
            return this.tv_device_state;
        }

        public final void setTv_device_state(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_device_state = textView;
        }

        public final ImageView getIv_device_icon() {
            return this.iv_device_icon;
        }

        public final void setIv_device_icon(ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.iv_device_icon = imageView;
        }

        public final TextView getTv_device_name() {
            return this.tv_device_name;
        }

        public final void setTv_device_name(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_device_name = textView;
        }

        public final TextView getTv_indoor_humidity() {
            return this.tv_indoor_humidity;
        }

        public final void setTv_indoor_humidity(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_indoor_humidity = textView;
        }

        public final TextView getTv_set_humidity() {
            return this.tv_set_humidity;
        }

        public final void setTv_set_humidity(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_set_humidity = textView;
        }

        public final TextView getTv_msg_left() {
            return this.tv_msg_left;
        }

        public final void setTv_msg_left(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_msg_left = textView;
        }

        public final TextView getTv_msg_right() {
            return this.tv_msg_right;
        }

        public final void setTv_msg_right(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_msg_right = textView;
        }

        public final TextView getTv_left_unit() {
            return this.tv_left_unit;
        }

        public final void setTv_left_unit(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_left_unit = textView;
        }

        public final TextView getTv_right_unit() {
            return this.tv_right_unit;
        }

        public final void setTv_right_unit(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_right_unit = textView;
        }

        public final ImageView getIv_more() {
            return this.iv_more;
        }

        public final void setIv_more(ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.iv_more = imageView;
        }

        public final LinearLayout getLl_more() {
            return this.ll_more;
        }

        public final void setLl_more(LinearLayout linearLayout) {
            Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
            this.ll_more = linearLayout;
        }

        public final LinearLayout getLl_share() {
            return this.ll_share;
        }

        public final void setLl_share(LinearLayout linearLayout) {
            Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
            this.ll_share = linearLayout;
        }

        public final LinearLayout getLl_rename() {
            return this.ll_rename;
        }

        public final void setLl_rename(LinearLayout linearLayout) {
            Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
            this.ll_rename = linearLayout;
        }

        public final LinearLayout getLl_delete() {
            return this.ll_delete;
        }

        public final void setLl_delete(LinearLayout linearLayout) {
            Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
            this.ll_delete = linearLayout;
        }

        public final FrameLayout getFra_share() {
            return this.fra_share;
        }

        public final void setFra_share(FrameLayout frameLayout) {
            Intrinsics.checkNotNullParameter(frameLayout, "<set-?>");
            this.fra_share = frameLayout;
        }
    }
}
