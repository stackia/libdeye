package com.deye.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.deye.activity.device.appointment.AddAppointmentAty;
import com.deye.activity.device.appointment.AppointmentListAty;
import com.deye.configs.Constants;
import com.deye.helper.DialogHelper;
import com.deye.utils.BaseUtils;
import com.deye.views.button.SwitchButton;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ItemSchedulerListBinding;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.api.http.RetrofitService;
import io.fogcloud.sdk.fog.bean.SchedulerGroupBean;
import io.fogcloud.sdk.fog.bean.SimpleResultBean;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SchedulerListAdapter.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001!B+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u001a\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00022\b\b\u0001\u0010\u0017\u001a\u00020\u0015H\u0016J\u0018\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0015H\u0016J\u0018\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0015H\u0002J\u001a\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\n2\b\b\u0002\u0010\u001f\u001a\u00020\u000fH\u0002J\u0010\u0010 \u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\nH\u0002R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/deye/adapter/SchedulerListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "mProductId", "", "mDeviceId", "mContext", "Lcom/deye/activity/device/appointment/AppointmentListAty;", "groupList", "", "Lio/fogcloud/sdk/fog/bean/SchedulerGroupBean;", "(Ljava/lang/String;Ljava/lang/String;Lcom/deye/activity/device/appointment/AppointmentListAty;Ljava/util/List;)V", "getGroupList", "()Ljava/util/List;", "isDelete", "", "bindTimeItemViewHolder", "", "viewHolder", "Lcom/deye/adapter/SchedulerListAdapter$ItemViewHolder;", "getItemCount", "", "onBindViewHolder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "reqDeleteBean", "bean", "reqUpdateBean", "force", "showConflictDialog", "ItemViewHolder", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class SchedulerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<SchedulerGroupBean> groupList;
    private boolean isDelete;
    private final AppointmentListAty mContext;
    private final String mDeviceId;
    private final String mProductId;

    public final List<SchedulerGroupBean> getGroupList() {
        return this.groupList;
    }

    public SchedulerListAdapter(String mProductId, String mDeviceId, AppointmentListAty mContext, List<SchedulerGroupBean> groupList) {
        Intrinsics.checkNotNullParameter(mProductId, "mProductId");
        Intrinsics.checkNotNullParameter(mDeviceId, "mDeviceId");
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(groupList, "groupList");
        this.mProductId = mProductId;
        this.mDeviceId = mDeviceId;
        this.mContext = mContext;
        this.groupList = groupList;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater.from((Context) this.mContext).inflate(R.layout.item_scheduler_list, parent, false);
        ItemSchedulerListBinding itemSchedulerListBindingInflate = ItemSchedulerListBinding.inflate(LayoutInflater.from((Context) this.mContext), parent, false);
        Intrinsics.checkNotNullExpressionValue(itemSchedulerListBindingInflate, "inflate(...)");
        return new ItemViewHolder(itemSchedulerListBindingInflate);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        bindTimeItemViewHolder((ItemViewHolder) viewHolder);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void bindTimeItemViewHolder(final ItemViewHolder viewHolder) {
        final int absoluteAdapterPosition = viewHolder.getAbsoluteAdapterPosition();
        final SchedulerGroupBean schedulerGroupBean = this.groupList.get(absoluteAdapterPosition);
        viewHolder.getBinding().rlRootView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.SchedulerListAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SchedulerListAdapter.bindTimeItemViewHolder$lambda$0(this.f$0, schedulerGroupBean, view);
            }
        });
        viewHolder.getBinding().tvStartTime.setText(schedulerGroupBean.schedule_on.getShowTimeText());
        viewHolder.getBinding().tvEndTime.setText(schedulerGroupBean.schedule_off.getShowTimeText());
        int i = schedulerGroupBean.schedule_on.commands.Mode;
        if (!Constants.isH7Product(this.mProductId)) {
            if (schedulerGroupBean.schedule_on.commands.SetHumidity != 0 || schedulerGroupBean.schedule_on.commands.WindSpeed != 0) {
                viewHolder.getBinding().llBottom.setVisibility(0);
                viewHolder.getBinding().tvHumiditySet.setText(schedulerGroupBean.schedule_on.commands.toHumidityText());
                viewHolder.getBinding().tvSpeedSet.setText(schedulerGroupBean.schedule_on.commands.toSpeedText());
                if (i == 1) {
                    viewHolder.getBinding().llHumSet.setVisibility(0);
                    viewHolder.getBinding().llSpeed.setVisibility(8);
                } else if (i == 2) {
                    viewHolder.getBinding().llHumSet.setVisibility(8);
                    viewHolder.getBinding().llSpeed.setVisibility(0);
                } else if (i != 4) {
                    viewHolder.getBinding().llHumSet.setVisibility(8);
                    viewHolder.getBinding().llSpeed.setVisibility(8);
                }
            } else {
                viewHolder.getBinding().llBottom.setVisibility(8);
            }
            if (i == 1) {
                viewHolder.getBinding().ivMode.setImageResource(R.drawable.icon_mode_sleep_small);
            } else if (i == 2) {
                viewHolder.getBinding().ivMode.setImageResource(R.drawable.icon_mode_continue_small);
            } else if (i == 3) {
                viewHolder.getBinding().ivMode.setImageResource(R.drawable.icon_mode_automatic_small);
            } else if (i == 4) {
                viewHolder.getBinding().ivMode.setImageResource(R.drawable.icon_mode_manual_small);
                viewHolder.getBinding().llHumSet.setVisibility(0);
                viewHolder.getBinding().llSpeed.setVisibility(8);
            }
        } else {
            if (schedulerGroupBean.schedule_on.commands.SetHumidity != 0 || schedulerGroupBean.schedule_on.commands.WindSpeed != 0) {
                viewHolder.getBinding().tvHumiditySet.setText(schedulerGroupBean.schedule_on.commands.toHumidityText());
                viewHolder.getBinding().llSpeed.setVisibility(8);
                if (i == 2) {
                    viewHolder.getBinding().llHumSet.setVisibility(0);
                } else {
                    viewHolder.getBinding().llHumSet.setVisibility(8);
                }
            } else {
                viewHolder.getBinding().llBottom.setVisibility(8);
            }
            if (i == 1) {
                viewHolder.getBinding().ivMode.setImageResource(R.drawable.icon_mode_sleep_small);
                viewHolder.getBinding().llHumSet.setVisibility(8);
                viewHolder.getBinding().llSpeed.setVisibility(8);
            } else if (i == 2) {
                viewHolder.getBinding().ivMode.setImageResource(R.drawable.icon_mode_strong_small);
                viewHolder.getBinding().llHumSet.setVisibility(8);
                viewHolder.getBinding().llSpeed.setVisibility(8);
            } else if (i == 3) {
                viewHolder.getBinding().ivMode.setImageResource(R.drawable.icon_mode_constant_small);
                viewHolder.getBinding().llHumSet.setVisibility(0);
                viewHolder.getBinding().llSpeed.setVisibility(8);
            } else if (i == 5) {
                viewHolder.getBinding().ivMode.setImageResource(R.drawable.icon_mode_drying_small);
                viewHolder.getBinding().llHumSet.setVisibility(8);
                viewHolder.getBinding().llSpeed.setVisibility(8);
            }
        }
        if (Constants.isH7Product(this.mProductId)) {
            viewHolder.getBinding().tvMode.setText(schedulerGroupBean.schedule_on.commands.toH7ModeText());
        } else {
            viewHolder.getBinding().tvMode.setText(schedulerGroupBean.schedule_on.commands.toModeText());
        }
        viewHolder.getBinding().switchButton.setChecked(schedulerGroupBean.enable);
        viewHolder.getBinding().switchButton.setOnClick(new SwitchButton.OnClick() { // from class: com.deye.adapter.SchedulerListAdapter$$ExternalSyntheticLambda2
            @Override // com.deye.views.button.SwitchButton.OnClick
            public final void onClick(View view, boolean z) {
                SchedulerListAdapter.bindTimeItemViewHolder$lambda$1(schedulerGroupBean, viewHolder, this, view, z);
            }
        });
        viewHolder.getBinding().tvDeleteItem.setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.SchedulerListAdapter$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SchedulerListAdapter.bindTimeItemViewHolder$lambda$2(this.f$0, schedulerGroupBean, absoluteAdapterPosition, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindTimeItemViewHolder$lambda$0(SchedulerListAdapter this$0, SchedulerGroupBean schedulerGroupBean, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(schedulerGroupBean, "$schedulerGroupBean");
        AddAppointmentAty.INSTANCE.openEdit((Activity) this$0.mContext, this$0.mProductId, this$0.mDeviceId, schedulerGroupBean);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindTimeItemViewHolder$lambda$1(SchedulerGroupBean schedulerGroupBean, ItemViewHolder viewHolder, SchedulerListAdapter this$0, View view, boolean z) {
        Intrinsics.checkNotNullParameter(schedulerGroupBean, "$schedulerGroupBean");
        Intrinsics.checkNotNullParameter(viewHolder, "$viewHolder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        schedulerGroupBean.enable = viewHolder.getBinding().switchButton.isChecked();
        reqUpdateBean$default(this$0, schedulerGroupBean, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindTimeItemViewHolder$lambda$2(final SchedulerListAdapter this$0, final SchedulerGroupBean schedulerGroupBean, final int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(schedulerGroupBean, "$schedulerGroupBean");
        AppointmentListAty appointmentListAty = this$0.mContext;
        Intrinsics.checkNotNull(appointmentListAty, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        DialogHelper.showDeleteDialog(appointmentListAty, this$0.mContext.getString(R.string.confirm_delete_schedule), this$0.mContext.getString(R.string.delete), new DialogHelper.OnDialogListener() { // from class: com.deye.adapter.SchedulerListAdapter$bindTimeItemViewHolder$3$1
            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onSure(String text) {
                this.this$0.reqDeleteBean(schedulerGroupBean, i);
            }
        });
    }

    static /* synthetic */ void reqUpdateBean$default(SchedulerListAdapter schedulerListAdapter, SchedulerGroupBean schedulerGroupBean, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        schedulerListAdapter.reqUpdateBean(schedulerGroupBean, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reqUpdateBean(final SchedulerGroupBean bean, boolean force) {
        AppointmentListAty appointmentListAty = this.mContext;
        appointmentListAty.showWaiting(appointmentListAty.getString(R.string.loading), true);
        bean.force = force;
        Intrinsics.checkNotNullExpressionValue(RetrofitManager.INSTANCE.getApiService().updateScheduleGroup(bean).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() { // from class: com.deye.adapter.SchedulerListAdapter$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                SchedulerListAdapter.reqUpdateBean$lambda$3(this.f$0);
            }
        }).subscribe(new Consumer() { // from class: com.deye.adapter.SchedulerListAdapter$reqUpdateBean$req$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<SimpleResultBean> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseResult.MetaBean meta = it2.getMeta();
                if (meta != null && meta.getCode() == 0) {
                    BaseUtils.showShortToast((Context) this.this$0.mContext, this.this$0.mContext.getString(R.string.update_success));
                    this.this$0.mContext.requestTiming();
                    return;
                }
                BaseResult.MetaBean meta2 = it2.getMeta();
                Intrinsics.checkNotNull(meta2);
                if (meta2.getCode() == 10501) {
                    this.this$0.showConflictDialog(bean);
                    return;
                }
                Context context = (Context) this.this$0.mContext;
                BaseResult.MetaBean meta3 = it2.getMeta();
                BaseUtils.showShortToast(context, String.valueOf(meta3 != null ? meta3.getMessage() : null));
            }
        }, new Consumer() { // from class: com.deye.adapter.SchedulerListAdapter$reqUpdateBean$req$3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                it2.printStackTrace();
                BaseUtils.showShortToast((Context) this.this$0.mContext, this.this$0.mContext.getString(R.string.update_fail));
            }
        }), "subscribe(...)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void reqUpdateBean$lambda$3(SchedulerListAdapter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mContext.stopWaiting();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showConflictDialog(final SchedulerGroupBean bean) {
        String string = this.mContext.getString(R.string.schedule_conflict);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        AppointmentListAty appointmentListAty = this.mContext;
        DialogHelper.showNormalDialog(appointmentListAty, string, appointmentListAty.getString(R.string.save), new DialogHelper.OnDialogListener() { // from class: com.deye.adapter.SchedulerListAdapter.showConflictDialog.1
            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onSure(String text) {
                SchedulerListAdapter.this.reqUpdateBean(bean, true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reqDeleteBean(SchedulerGroupBean bean, final int position) {
        AppointmentListAty appointmentListAty = this.mContext;
        appointmentListAty.showWaiting(appointmentListAty.getString(R.string.deleting), true);
        RetrofitService apiService = RetrofitManager.INSTANCE.getApiService();
        String group_id = bean.group_id;
        Intrinsics.checkNotNullExpressionValue(group_id, "group_id");
        Intrinsics.checkNotNullExpressionValue(apiService.deleteScheduleGroup(group_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() { // from class: com.deye.adapter.SchedulerListAdapter$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                SchedulerListAdapter.reqDeleteBean$lambda$4(this.f$0);
            }
        }).subscribe(new Consumer() { // from class: com.deye.adapter.SchedulerListAdapter$reqDeleteBean$req$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<SimpleResultBean> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseResult.MetaBean meta = it2.getMeta();
                if (meta == null || meta.getCode() != 0) {
                    Context context = (Context) this.this$0.mContext;
                    BaseResult.MetaBean meta2 = it2.getMeta();
                    BaseUtils.showShortToast(context, String.valueOf(meta2 != null ? meta2.getMessage() : null));
                } else {
                    BaseUtils.showShortToast((Context) this.this$0.mContext, this.this$0.mContext.getString(R.string.delete_success));
                    this.this$0.getGroupList().remove(position);
                    this.this$0.notifyItemRemoved(position);
                    this.this$0.mContext.requestTiming();
                }
            }
        }, new Consumer() { // from class: com.deye.adapter.SchedulerListAdapter$reqDeleteBean$req$3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                it2.printStackTrace();
                BaseUtils.showShortToast((Context) this.this$0.mContext, this.this$0.mContext.getString(R.string.delete_fail));
            }
        }), "subscribe(...)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void reqDeleteBean$lambda$4(SchedulerListAdapter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mContext.stopWaiting();
    }

    public int getItemCount() {
        return this.groupList.size();
    }

    /* compiled from: SchedulerListAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/deye/adapter/SchedulerListAdapter$ItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/mxchipapp/databinding/ItemSchedulerListBinding;", "(Lcom/mxchipapp/databinding/ItemSchedulerListBinding;)V", "getBinding", "()Lcom/mxchipapp/databinding/ItemSchedulerListBinding;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemSchedulerListBinding binding;

        public final ItemSchedulerListBinding getBinding() {
            return this.binding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ItemViewHolder(ItemSchedulerListBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }
    }
}
