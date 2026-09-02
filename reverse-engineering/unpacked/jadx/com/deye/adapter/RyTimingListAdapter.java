package com.deye.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSON;
import com.amap.location.support.bean.location.AmapLocationNetwork;
import com.deye.activity.device.AddSchedulerAty;
import com.deye.activity.device.HumSchedulerAty;
import com.deye.activity.device.SchedulerListAty;
import com.deye.activity.device.base.BaseActivity;
import com.deye.adapter.RyTimingListAdapter;
import com.deye.configs.Constants;
import com.deye.entity.control_panel.dehumidifier.func.HumidityBean;
import com.deye.helper.DialogHelper;
import com.deye.utils.BaseUtils;
import com.deye.views.button.SwitchButton;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.api.http.RetrofitService;
import io.fogcloud.sdk.fog.bean.SchedulerBean;
import io.fogcloud.sdk.fog.bean.SchedulerDateBean;
import io.fogcloud.sdk.fog.bean.SchedulerHumBean;
import io.fogcloud.sdk.fog.callback.ControlDeviceCallBack;
import io.fogcloud.sdk.fog.log.LogUtil;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/* compiled from: RyTimingListAdapter.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 ,2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0007,-./012B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\rJ\u0014\u0010\u0014\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0017R\u00020\u0000H\u0003J\u0014\u0010\u0018\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0019R\u00020\u0000H\u0002J\u001a\u0010\u001a\u001a\u00020\u00152\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0018\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001eH\u0003J\b\u0010!\u001a\u00020\u001eH\u0016J\u0010\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u001eH\u0016J\u001a\u0010$\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00022\b\b\u0001\u0010#\u001a\u00020\u001eH\u0016J\u0018\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u001eH\u0016J\u0018\u0010)\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u001eH\u0002J\u0018\u0010+\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u001eH\u0002R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u00063"}, d2 = {"Lcom/deye/adapter/RyTimingListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "mProductId", "", "mDeviceId", "mContext", "Lcom/deye/activity/device/base/BaseActivity;", "mHumidityBean", "Lcom/deye/entity/control_panel/dehumidifier/func/HumidityBean;", "timingList", "", "Lio/fogcloud/sdk/fog/bean/SchedulerDateBean;", "(Ljava/lang/String;Ljava/lang/String;Lcom/deye/activity/device/base/BaseActivity;Lcom/deye/entity/control_panel/dehumidifier/func/HumidityBean;Ljava/util/List;)V", "hour", "isDelete", "", "minute", "getTimingList", "()Ljava/util/List;", "bindHumItemViewHolder", "", "viewHolder", "Lcom/deye/adapter/RyTimingListAdapter$HumItemViewHolder;", "bindTimeItemViewHolder", "Lcom/deye/adapter/RyTimingListAdapter$TimeItemViewHolder;", "deleteScheduler", "schedulerBean", "Lio/fogcloud/sdk/fog/bean/SchedulerBean;", "i", "", "deleteSucc", "message", "getItemCount", "getItemViewType", "position", "onBindViewHolder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "requestFail", "flag", "requestSuccess", "Companion", "HumEmptyViewHolder", "HumItemViewHolder", "TimeAddViewHolder", "TimeEmptyViewHolder", "TimeItemViewHolder", "TitleViewHolder", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class RyTimingListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_HUM_EMPTY = 2;
    public static final int VIEW_TYPE_HUM_ITEM = 1;
    public static final int VIEW_TYPE_HUM_TITLE = 0;
    public static final int VIEW_TYPE_TIME_ADD = 6;
    public static final int VIEW_TYPE_TIME_EMPTY = 5;
    public static final int VIEW_TYPE_TIME_ITEM = 4;
    public static final int VIEW_TYPE_TIME_TITLE = 3;
    private String hour;
    private boolean isDelete;
    private final BaseActivity mContext;
    private final String mDeviceId;
    private final HumidityBean mHumidityBean;
    private final String mProductId;
    private String minute;
    private final List<SchedulerDateBean> timingList;
    public static final String TAG = StubApp.getString2(13820);

    public final List<SchedulerDateBean> getTimingList() {
        return this.timingList;
    }

    public RyTimingListAdapter(String mProductId, String mDeviceId, BaseActivity mContext, HumidityBean humidityBean, List<SchedulerDateBean> timingList) {
        Intrinsics.checkNotNullParameter(mProductId, "mProductId");
        Intrinsics.checkNotNullParameter(mDeviceId, "mDeviceId");
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(timingList, "timingList");
        this.mProductId = mProductId;
        this.mDeviceId = mDeviceId;
        this.mContext = mContext;
        this.mHumidityBean = humidityBean;
        this.timingList = timingList;
        String string2 = StubApp.getString2(2737);
        this.hour = string2;
        this.minute = string2;
    }

    public int getItemViewType(int position) {
        return this.timingList.get(position).getViewType();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        switch (viewType) {
            case 0:
                View viewInflate = LayoutInflater.from((Context) this.mContext).inflate(R.layout.timing_list_item_hum_title, parent, false);
                Intrinsics.checkNotNull(viewInflate);
                return new TitleViewHolder(this, viewInflate);
            case 1:
                View viewInflate2 = LayoutInflater.from((Context) this.mContext).inflate(R.layout.timing_list_item_hum_item, parent, false);
                Intrinsics.checkNotNull(viewInflate2);
                return new HumItemViewHolder(this, viewInflate2);
            case 2:
                View viewInflate3 = LayoutInflater.from((Context) this.mContext).inflate(R.layout.timing_list_item_hum_empty, parent, false);
                Intrinsics.checkNotNull(viewInflate3);
                return new HumEmptyViewHolder(this, viewInflate3);
            case 3:
                View viewInflate4 = LayoutInflater.from((Context) this.mContext).inflate(R.layout.timing_list_item_time_title, parent, false);
                Intrinsics.checkNotNull(viewInflate4);
                return new TitleViewHolder(this, viewInflate4);
            case 4:
                View viewInflate5 = LayoutInflater.from((Context) this.mContext).inflate(R.layout.timing_list_item, parent, false);
                Intrinsics.checkNotNull(viewInflate5);
                return new TimeItemViewHolder(this, viewInflate5);
            case 5:
                View viewInflate6 = LayoutInflater.from((Context) this.mContext).inflate(R.layout.timing_list_item_item_empty, parent, false);
                Intrinsics.checkNotNull(viewInflate6);
                return new TimeEmptyViewHolder(this, viewInflate6);
            case 6:
                View viewInflate7 = LayoutInflater.from((Context) this.mContext).inflate(R.layout.timing_list_item_time_add, parent, false);
                Intrinsics.checkNotNull(viewInflate7);
                return new TimeAddViewHolder(this, viewInflate7);
            default:
                View viewInflate8 = LayoutInflater.from((Context) this.mContext).inflate(R.layout.timing_list_item, parent, false);
                Intrinsics.checkNotNull(viewInflate8);
                return new TimeItemViewHolder(this, viewInflate8);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        int itemViewType = getItemViewType(position);
        if (itemViewType == 1) {
            bindHumItemViewHolder((HumItemViewHolder) viewHolder);
        } else {
            if (itemViewType != 4) {
                return;
            }
            bindTimeItemViewHolder((TimeItemViewHolder) viewHolder);
        }
    }

    private final void bindHumItemViewHolder(final HumItemViewHolder viewHolder) {
        final SchedulerHumBean humBean = this.timingList.get(viewHolder.getAbsoluteAdapterPosition()).getHumBean();
        Intrinsics.checkNotNull(humBean);
        TextView tvMax = viewHolder.getTvMax();
        StringBuilder sbAppend = new StringBuilder().append(humBean.getThreshold_down());
        String string2 = StubApp.getString2(5130);
        tvMax.setText(sbAppend.append(string2).toString());
        viewHolder.getTvMin().setText(humBean.getThreshold_up() + string2);
        viewHolder.getSwitch_button().setChecked(humBean.getEnabled());
        viewHolder.getSwitch_button().setOnClick(new SwitchButton.OnClick() { // from class: com.deye.adapter.RyTimingListAdapter$$ExternalSyntheticLambda0
            @Override // com.deye.views.button.SwitchButton.OnClick
            public final void onClick(View view, boolean z) {
                RyTimingListAdapter.bindHumItemViewHolder$lambda$0(viewHolder, humBean, this, view, z);
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.RyTimingListAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RyTimingListAdapter.bindHumItemViewHolder$lambda$1(this.f$0, humBean, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindHumItemViewHolder$lambda$0(HumItemViewHolder viewHolder, SchedulerHumBean bean, final RyTimingListAdapter this$0, View view, boolean z) {
        Intrinsics.checkNotNullParameter(viewHolder, "$viewHolder");
        Intrinsics.checkNotNullParameter(bean, "$bean");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (viewHolder.getSwitch_button().isChecked()) {
            bean.setEnabled(true);
        } else {
            bean.setEnabled(false);
        }
        this$0.notifyDataSetChanged();
        String jSONString = JSON.toJSONString(bean);
        RequestBody.Companion companion = RequestBody.Companion;
        MediaType mediaType = Constants.JSON_Type;
        Intrinsics.checkNotNull(jSONString);
        RequestBody requestBodyCreate = companion.create(mediaType, jSONString);
        RetrofitService apiService = RetrofitManager.INSTANCE.getApiService();
        Integer trigger_id = bean.getTrigger_id();
        Intrinsics.checkNotNull(trigger_id);
        apiService.updateScheduleHum(trigger_id.intValue(), requestBodyCreate).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.deye.adapter.RyTimingListAdapter$bindHumItemViewHolder$1$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<SchedulerHumBean> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseResult.MetaBean meta = it2.getMeta();
                if (meta == null || meta.getCode() != 0) {
                    BaseUtils.showShortToast(this.this$0.mContext.getString(R.string.update_fail));
                } else {
                    BaseUtils.showShortToast(this.this$0.mContext.getString(R.string.update_success));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindHumItemViewHolder$lambda$1(RyTimingListAdapter this$0, SchedulerHumBean bean, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bean, "$bean");
        Intent intent = new Intent((Context) this$0.mContext, (Class<?>) HumSchedulerAty.class);
        String string2 = StubApp.getString2(13478);
        intent.putExtra(string2, true);
        intent.putExtra(StubApp.getString2(13055), this$0.mDeviceId);
        intent.putExtra(StubApp.getString2(13306), this$0.mProductId);
        intent.putExtra(StubApp.getString2(13485), this$0.mHumidityBean);
        intent.putExtra(StubApp.getString2(13486), bean);
        intent.putExtra(string2, false);
        this$0.mContext.startActivity(intent);
    }

    private final void bindTimeItemViewHolder(final TimeItemViewHolder viewHolder) {
        final int absoluteAdapterPosition = viewHolder.getAbsoluteAdapterPosition();
        final SchedulerBean schedulerBean = this.timingList.get(absoluteAdapterPosition).getSchedulerBean();
        viewHolder.getRl_root_view().setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.RyTimingListAdapter$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RyTimingListAdapter.bindTimeItemViewHolder$lambda$2(this.f$0, schedulerBean, view);
            }
        });
        if (this.isDelete) {
            viewHolder.getSwitch_button().setVisibility(8);
        } else {
            viewHolder.getSwitch_button().setVisibility(0);
        }
        this.timingList.size();
        Intrinsics.checkNotNull(schedulerBean);
        try {
            String string = JSON.parseObject(schedulerBean.commands).getString(StubApp.getString2("13821"));
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            if (Intrinsics.areEqual(string, AmapLocationNetwork.RESULT_TYPE_GPS)) {
                viewHolder.getTv_state().setText(this.mContext.getString(R.string.Off));
                viewHolder.getIv_state().setBackgroundResource(R.drawable.icon_no);
            }
            if (Intrinsics.areEqual(string, AmapLocationNetwork.RESULT_TYPE_WIFI_ONLY)) {
                viewHolder.getTv_state().setText(this.mContext.getString(R.string.on));
                viewHolder.getIv_state().setBackgroundResource(R.drawable.icon_yes);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        String str = schedulerBean.hour;
        String str2 = schedulerBean.minute;
        String str3 = StubApp.getString2(13822) + str;
        String string2 = StubApp.getString2(13820);
        Log.d(string2, str3);
        Log.d(string2, StubApp.getString2(13823) + str2);
        boolean zIsNotNull = BaseUtils.isNotNull(str);
        String string22 = StubApp.getString2(13824);
        if (zIsNotNull) {
            String timeString = BaseUtils.getTimeString(str);
            Intrinsics.checkNotNullExpressionValue(timeString, string22);
            this.hour = timeString;
        }
        if (BaseUtils.isNotNull(str2)) {
            String timeString2 = BaseUtils.getTimeString(str2);
            Intrinsics.checkNotNullExpressionValue(timeString2, string22);
            this.minute = timeString2;
        }
        viewHolder.getTv_time().setText(this.hour + StubApp.getString2(669) + this.minute);
        if (!BaseUtils.isNullString(String.valueOf(schedulerBean.enable))) {
            Log.d(StubApp.getString2(5607), new StringBuilder().append(schedulerBean.enable).append(absoluteAdapterPosition).toString());
            viewHolder.getSwitch_button().setChecked(schedulerBean.enable);
        }
        viewHolder.getTv_weeks().setText(BaseUtils.getWeekString((Context) this.mContext, schedulerBean.day_of_week));
        viewHolder.getSwitch_button().setOnClick(new SwitchButton.OnClick() { // from class: com.deye.adapter.RyTimingListAdapter$$ExternalSyntheticLambda5
            @Override // com.deye.views.button.SwitchButton.OnClick
            public final void onClick(View view, boolean z) {
                RyTimingListAdapter.bindTimeItemViewHolder$lambda$3(viewHolder, schedulerBean, this, absoluteAdapterPosition, view, z);
            }
        });
        viewHolder.getTv_delete_item().setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.RyTimingListAdapter$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RyTimingListAdapter.bindTimeItemViewHolder$lambda$4(this.f$0, schedulerBean, absoluteAdapterPosition, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindTimeItemViewHolder$lambda$2(RyTimingListAdapter this$0, SchedulerBean schedulerBean, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent((Context) this$0.mContext, (Class<?>) AddSchedulerAty.class);
        intent.putExtra(StubApp.getString2(13478), false);
        intent.putExtra(StubApp.getString2(13055), this$0.mDeviceId);
        intent.putExtra(StubApp.getString2(13306), this$0.mProductId);
        intent.putExtra(StubApp.getString2(13480), JSON.parseObject(schedulerBean != null ? schedulerBean.commands : null).getString(StubApp.getString2(13821)));
        intent.putExtra(StubApp.getString2(13479), schedulerBean != null ? schedulerBean.day_of_week : null);
        intent.putExtra(StubApp.getString2(13481), schedulerBean != null ? schedulerBean.hour : null);
        intent.putExtra(StubApp.getString2(13482), schedulerBean != null ? schedulerBean.minute : null);
        intent.putExtra(StubApp.getString2(13477), schedulerBean != null ? schedulerBean.groupid : null);
        intent.putExtra(StubApp.getString2(955), schedulerBean != null ? schedulerBean.name : null);
        this$0.mContext.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindTimeItemViewHolder$lambda$3(TimeItemViewHolder viewHolder, SchedulerBean schedulerBean, final RyTimingListAdapter this$0, int i, View view, boolean z) {
        Intrinsics.checkNotNullParameter(viewHolder, "$viewHolder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (viewHolder.getSwitch_button().isChecked()) {
            schedulerBean.enable = true;
        } else {
            schedulerBean.enable = false;
        }
        this$0.notifyDataSetChanged();
        BaseActivity baseActivity = this$0.mContext;
        baseActivity.showWaiting(baseActivity.getString(R.string.loading), true);
        String jSONString = JSON.toJSONString(this$0.timingList.get(i).getSchedulerBean());
        Log.d(StubApp.getString2(13825), jSONString);
        RequestBody.Companion companion = RequestBody.Companion;
        MediaType mediaType = Constants.JSON_Type;
        Intrinsics.checkNotNull(jSONString);
        DeYeHttpRequestManager.getInstance().updateTimingTask(companion.create(mediaType, jSONString), new ControlDeviceCallBack() { // from class: com.deye.adapter.RyTimingListAdapter$bindTimeItemViewHolder$2$1
            @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
            public void onSuccess(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                super.onSuccess(message);
                this.this$0.requestSuccess(message, 0);
            }

            @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
            public void onFailure(int code, String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                super.onFailure(code, message);
                this.this$0.requestFail(message, 0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindTimeItemViewHolder$lambda$4(final RyTimingListAdapter this$0, final SchedulerBean schedulerBean, final int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseActivity baseActivity = this$0.mContext;
        Intrinsics.checkNotNull(baseActivity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        DialogHelper.showDeleteDialog(baseActivity, this$0.mContext.getString(R.string.confirm_delete_schedule), this$0.mContext.getString(R.string.delete), new DialogHelper.OnDialogListener() { // from class: com.deye.adapter.RyTimingListAdapter$bindTimeItemViewHolder$3$1
            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onSure(String text) {
                this.this$0.deleteScheduler(schedulerBean, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteScheduler(SchedulerBean schedulerBean, final int i) {
        BaseActivity baseActivity = this.mContext;
        baseActivity.showWaiting(baseActivity.getString(R.string.loading), true);
        DeYeHttpRequestManager deYeHttpRequestManager = DeYeHttpRequestManager.getInstance();
        Intrinsics.checkNotNull(schedulerBean);
        deYeHttpRequestManager.deleteTask(schedulerBean.name, new ControlDeviceCallBack() { // from class: com.deye.adapter.RyTimingListAdapter.deleteScheduler.1
            @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
            public void onSuccess(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                super.onSuccess(message);
                RyTimingListAdapter.this.requestSuccess(message, 1);
                RyTimingListAdapter.this.deleteSucc(message, i);
            }

            @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
            public void onFailure(int code, String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                super.onFailure(code, message);
                RyTimingListAdapter.this.requestFail(message, 1);
            }
        });
    }

    public int getItemCount() {
        return this.timingList.size();
    }

    /* compiled from: RyTimingListAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/deye/adapter/RyTimingListAdapter$TitleViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/deye/adapter/RyTimingListAdapter;Landroid/view/View;)V", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class TitleViewHolder extends RecyclerView.ViewHolder {
        final /* synthetic */ RyTimingListAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TitleViewHolder(RyTimingListAdapter ryTimingListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = ryTimingListAdapter;
        }
    }

    /* compiled from: RyTimingListAdapter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/deye/adapter/RyTimingListAdapter$HumEmptyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/deye/adapter/RyTimingListAdapter;Landroid/view/View;)V", "tvSetting", "Landroid/widget/TextView;", "getTvSetting", "()Landroid/widget/TextView;", "setTvSetting", "(Landroid/widget/TextView;)V", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class HumEmptyViewHolder extends RecyclerView.ViewHolder {
        final /* synthetic */ RyTimingListAdapter this$0;
        private TextView tvSetting;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HumEmptyViewHolder(final RyTimingListAdapter ryTimingListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = ryTimingListAdapter;
            View viewFindViewById = itemView.findViewById(R.id.tv_setting);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            TextView textView = (TextView) viewFindViewById;
            this.tvSetting = textView;
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.RyTimingListAdapter$HumEmptyViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RyTimingListAdapter.HumEmptyViewHolder._init_$lambda$0(ryTimingListAdapter, view);
                }
            });
        }

        public final TextView getTvSetting() {
            return this.tvSetting;
        }

        public final void setTvSetting(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tvSetting = textView;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void _init_$lambda$0(RyTimingListAdapter this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intent intent = new Intent((Context) this$0.mContext, (Class<?>) HumSchedulerAty.class);
            intent.putExtra(StubApp.getString2(13478), true);
            intent.putExtra(StubApp.getString2(13055), this$0.mDeviceId);
            intent.putExtra(StubApp.getString2(13306), this$0.mProductId);
            intent.putExtra(StubApp.getString2(13485), this$0.mHumidityBean);
            this$0.mContext.startActivity(intent);
        }
    }

    /* compiled from: RyTimingListAdapter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/deye/adapter/RyTimingListAdapter$TimeAddViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/deye/adapter/RyTimingListAdapter;Landroid/view/View;)V", "ll_add_timing", "Landroid/widget/LinearLayout;", "getLl_add_timing", "()Landroid/widget/LinearLayout;", "setLl_add_timing", "(Landroid/widget/LinearLayout;)V", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class TimeAddViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout ll_add_timing;
        final /* synthetic */ RyTimingListAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TimeAddViewHolder(final RyTimingListAdapter ryTimingListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = ryTimingListAdapter;
            View viewFindViewById = itemView.findViewById(R.id.ll_add_timing);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            LinearLayout linearLayout = (LinearLayout) viewFindViewById;
            this.ll_add_timing = linearLayout;
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.RyTimingListAdapter$TimeAddViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RyTimingListAdapter.TimeAddViewHolder._init_$lambda$0(ryTimingListAdapter, view);
                }
            });
        }

        public final LinearLayout getLl_add_timing() {
            return this.ll_add_timing;
        }

        public final void setLl_add_timing(LinearLayout linearLayout) {
            Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
            this.ll_add_timing = linearLayout;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void _init_$lambda$0(RyTimingListAdapter this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intent intent = new Intent((Context) this$0.mContext, (Class<?>) AddSchedulerAty.class);
            intent.putExtra(StubApp.getString2(13478), true);
            intent.putExtra(StubApp.getString2(13055), this$0.mDeviceId);
            intent.putExtra(StubApp.getString2(13306), this$0.mProductId);
            this$0.mContext.startActivity(intent);
        }
    }

    /* compiled from: RyTimingListAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/deye/adapter/RyTimingListAdapter$TimeEmptyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/deye/adapter/RyTimingListAdapter;Landroid/view/View;)V", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class TimeEmptyViewHolder extends RecyclerView.ViewHolder {
        final /* synthetic */ RyTimingListAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TimeEmptyViewHolder(RyTimingListAdapter ryTimingListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = ryTimingListAdapter;
        }
    }

    /* compiled from: RyTimingListAdapter.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006\u0014"}, d2 = {"Lcom/deye/adapter/RyTimingListAdapter$HumItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/deye/adapter/RyTimingListAdapter;Landroid/view/View;)V", "switch_button", "Lcom/deye/views/button/SwitchButton;", "getSwitch_button", "()Lcom/deye/views/button/SwitchButton;", "setSwitch_button", "(Lcom/deye/views/button/SwitchButton;)V", "tvMax", "Landroid/widget/TextView;", "getTvMax", "()Landroid/widget/TextView;", "setTvMax", "(Landroid/widget/TextView;)V", "tvMin", "getTvMin", "setTvMin", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class HumItemViewHolder extends RecyclerView.ViewHolder {
        private SwitchButton switch_button;
        final /* synthetic */ RyTimingListAdapter this$0;
        private TextView tvMax;
        private TextView tvMin;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HumItemViewHolder(RyTimingListAdapter ryTimingListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = ryTimingListAdapter;
            View viewFindViewById = itemView.findViewById(R.id.tv_max);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.tvMax = (TextView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.tv_min);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.tvMin = (TextView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(R.id.switch_continue);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
            this.switch_button = (SwitchButton) viewFindViewById3;
        }

        public final TextView getTvMax() {
            return this.tvMax;
        }

        public final void setTvMax(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tvMax = textView;
        }

        public final TextView getTvMin() {
            return this.tvMin;
        }

        public final void setTvMin(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tvMin = textView;
        }

        public final SwitchButton getSwitch_button() {
            return this.switch_button;
        }

        public final void setSwitch_button(SwitchButton switchButton) {
            Intrinsics.checkNotNullParameter(switchButton, "<set-?>");
            this.switch_button = switchButton;
        }
    }

    /* compiled from: RyTimingListAdapter.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000e\"\u0004\b\u001f\u0010\u0010R\u001a\u0010 \u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u000e\"\u0004\b\"\u0010\u0010R\u001a\u0010#\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u000e\"\u0004\b%\u0010\u0010R\u001a\u0010&\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u000e\"\u0004\b(\u0010\u0010¨\u0006)"}, d2 = {"Lcom/deye/adapter/RyTimingListAdapter$TimeItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/deye/adapter/RyTimingListAdapter;Landroid/view/View;)V", "iv_state", "Landroid/widget/ImageView;", "getIv_state", "()Landroid/widget/ImageView;", "setIv_state", "(Landroid/widget/ImageView;)V", "line_divider", "Landroid/widget/TextView;", "getLine_divider", "()Landroid/widget/TextView;", "setLine_divider", "(Landroid/widget/TextView;)V", "rl_root_view", "Landroid/widget/RelativeLayout;", "getRl_root_view", "()Landroid/widget/RelativeLayout;", "setRl_root_view", "(Landroid/widget/RelativeLayout;)V", "switch_button", "Lcom/deye/views/button/SwitchButton;", "getSwitch_button", "()Lcom/deye/views/button/SwitchButton;", "setSwitch_button", "(Lcom/deye/views/button/SwitchButton;)V", "tv_delete_item", "getTv_delete_item", "setTv_delete_item", "tv_state", "getTv_state", "setTv_state", "tv_time", "getTv_time", "setTv_time", "tv_weeks", "getTv_weeks", "setTv_weeks", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class TimeItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_state;
        private TextView line_divider;
        private RelativeLayout rl_root_view;
        private SwitchButton switch_button;
        final /* synthetic */ RyTimingListAdapter this$0;
        private TextView tv_delete_item;
        private TextView tv_state;
        private TextView tv_time;
        private TextView tv_weeks;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TimeItemViewHolder(RyTimingListAdapter ryTimingListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = ryTimingListAdapter;
            setIsRecyclable(false);
            View viewFindViewById = itemView.findViewById(R.id.rl_root_view);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.rl_root_view = (RelativeLayout) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.tv_state);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.tv_state = (TextView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(R.id.tv_time);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
            this.tv_time = (TextView) viewFindViewById3;
            View viewFindViewById4 = itemView.findViewById(R.id.iv_state);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
            this.iv_state = (ImageView) viewFindViewById4;
            View viewFindViewById5 = itemView.findViewById(R.id.tv_weeks);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
            this.tv_weeks = (TextView) viewFindViewById5;
            View viewFindViewById6 = itemView.findViewById(R.id.tv_delete_item);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(...)");
            this.tv_delete_item = (TextView) viewFindViewById6;
            View viewFindViewById7 = itemView.findViewById(R.id.switch_button);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(...)");
            this.switch_button = (SwitchButton) viewFindViewById7;
        }

        public final RelativeLayout getRl_root_view() {
            return this.rl_root_view;
        }

        public final void setRl_root_view(RelativeLayout relativeLayout) {
            Intrinsics.checkNotNullParameter(relativeLayout, "<set-?>");
            this.rl_root_view = relativeLayout;
        }

        public final TextView getTv_state() {
            return this.tv_state;
        }

        public final void setTv_state(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_state = textView;
        }

        public final TextView getTv_time() {
            return this.tv_time;
        }

        public final void setTv_time(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_time = textView;
        }

        public final ImageView getIv_state() {
            return this.iv_state;
        }

        public final void setIv_state(ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.iv_state = imageView;
        }

        public final TextView getTv_weeks() {
            return this.tv_weeks;
        }

        public final void setTv_weeks(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_weeks = textView;
        }

        public final TextView getLine_divider() {
            return this.line_divider;
        }

        public final void setLine_divider(TextView textView) {
            this.line_divider = textView;
        }

        public final TextView getTv_delete_item() {
            return this.tv_delete_item;
        }

        public final void setTv_delete_item(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_delete_item = textView;
        }

        public final SwitchButton getSwitch_button() {
            return this.switch_button;
        }

        public final void setSwitch_button(SwitchButton switchButton) {
            Intrinsics.checkNotNullParameter(switchButton, "<set-?>");
            this.switch_button = switchButton;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteSucc(String message, final int i) {
        Integer integer = JSON.parseObject(JSON.parseObject(message).getString(StubApp.getString2(13082))).getInteger(StubApp.getString2(109));
        if (integer != null && integer.intValue() == 0) {
            this.mContext.runOnUiThread(new Runnable() { // from class: com.deye.adapter.RyTimingListAdapter$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    RyTimingListAdapter.deleteSucc$lambda$5(i, this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteSucc$lambda$5(int i, RyTimingListAdapter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i >= 0 && i < this$0.timingList.size()) {
            this$0.timingList.remove(i);
            this$0.notifyDataSetChanged();
        }
        BaseActivity baseActivity = this$0.mContext;
        SchedulerListAty schedulerListAty = baseActivity instanceof SchedulerListAty ? (SchedulerListAty) baseActivity : null;
        if (schedulerListAty != null) {
            schedulerListAty.requestTiming();
        }
        LogUtil.d(StubApp.getString2(13826) + this$0.timingList.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestSuccess(String message, final int flag) {
        final Integer integer = JSON.parseObject(JSON.parseObject(message).getString(StubApp.getString2(13082))).getInteger(StubApp.getString2(109));
        this.mContext.runOnUiThread(new Runnable() { // from class: com.deye.adapter.RyTimingListAdapter$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                RyTimingListAdapter.requestSuccess$lambda$6(this.f$0, integer, flag);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void requestSuccess$lambda$6(RyTimingListAdapter this$0, Integer num, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mContext.stopWaiting();
        if (num != null && num.intValue() == 0) {
            if (i == 0 || 1 != i) {
                return;
            }
            BaseActivity baseActivity = this$0.mContext;
            BaseUtils.showShortToast((Context) baseActivity, baseActivity.getString(R.string.delete_success));
            return;
        }
        if (i == 0) {
            BaseActivity baseActivity2 = this$0.mContext;
            BaseUtils.showShortToast((Context) baseActivity2, baseActivity2.getString(R.string.update_fail));
        } else if (1 == i) {
            BaseActivity baseActivity3 = this$0.mContext;
            BaseUtils.showShortToast((Context) baseActivity3, baseActivity3.getString(R.string.delete_fail));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestFail(String message, int flag) {
        BaseUtils.showShortToast(message);
    }
}
