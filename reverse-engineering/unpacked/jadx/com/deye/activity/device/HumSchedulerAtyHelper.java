package com.deye.activity.device;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.callback.ControlDeviceCallBack;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import okhttp3.RequestBody;

/* compiled from: HumSchedulerAtyHelper.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'J\b\u0010(\u001a\u00020%H\u0002J\u001a\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020-H\u0002J\u001a\u0010.\u001a\u00020%2\b\u0010/\u001a\u0004\u0018\u00010\u00102\u0006\u00100\u001a\u00020!H\u0002J\u000e\u00101\u001a\u00020%2\u0006\u00102\u001a\u00020!J\u0010\u00103\u001a\u00020%2\b\u00102\u001a\u0004\u0018\u00010!J\u0012\u00104\u001a\u00020%2\b\u00105\u001a\u0004\u0018\u00010\u0010H\u0002J\u0012\u00106\u001a\u00020%2\b\u00105\u001a\u0004\u0018\u00010\u0010H\u0002J\u0006\u00107\u001a\u00020%J\u0006\u00108\u001a\u00020!J\u0010\u00109\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0004R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00020!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u0006:"}, d2 = {"Lcom/deye/activity/device/HumSchedulerAtyHelper;", "", "mActivity", "Lcom/deye/activity/device/HumSchedulerAty;", "(Lcom/deye/activity/device/HumSchedulerAty;)V", "HHMM_FORMAT", "Ljava/text/SimpleDateFormat;", "getHHMM_FORMAT", "()Ljava/text/SimpleDateFormat;", "setHHMM_FORMAT", "(Ljava/text/SimpleDateFormat;)V", "btnConfirm", "Landroid/widget/Button;", "dialog", "Lcom/othershe/nicedialog/BaseNiceDialog;", "friday", "Landroid/widget/LinearLayout;", "llDays", "getMActivity", "()Lcom/deye/activity/device/HumSchedulerAty;", "setMActivity", "monday", "saturday", "sunday", "thursday", "tuesday", "tvEveryDay", "Landroid/widget/TextView;", "tvMsg", "tvOnce", "tvRepeat", "wednesday", "weeks", "", "getWeeks", "()Ljava/lang/String;", "createTimingTask", "", "body", "Lokhttp3/RequestBody;", "initDays", "initDialog", "viewHolder", "Lcom/othershe/nicedialog/ViewHolder;", "isHum", "", "itemClick", "day", "s", "requestFail", "message", "requestSuccess", "setDaySelect", "layout", "setDayUnSelect", "showChoseWeekDialog", "todayHhMm", "updateTimingTask", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class HumSchedulerAtyHelper {
    private SimpleDateFormat HHMM_FORMAT;
    private Button btnConfirm;
    private BaseNiceDialog dialog;
    private LinearLayout friday;
    private LinearLayout llDays;
    private HumSchedulerAty mActivity;
    private LinearLayout monday;
    private LinearLayout saturday;
    private LinearLayout sunday;
    private LinearLayout thursday;
    private LinearLayout tuesday;
    private TextView tvEveryDay;
    private TextView tvMsg;
    private TextView tvOnce;
    private TextView tvRepeat;
    private LinearLayout wednesday;

    public HumSchedulerAtyHelper(HumSchedulerAty mActivity) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        this.mActivity = mActivity;
        this.HHMM_FORMAT = new SimpleDateFormat(StubApp.getString2(13489), Locale.getDefault());
    }

    public final HumSchedulerAty getMActivity() {
        return this.mActivity;
    }

    public final void setMActivity(HumSchedulerAty humSchedulerAty) {
        Intrinsics.checkNotNullParameter(humSchedulerAty, "<set-?>");
        this.mActivity = humSchedulerAty;
    }

    public final SimpleDateFormat getHHMM_FORMAT() {
        return this.HHMM_FORMAT;
    }

    public final void setHHMM_FORMAT(SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.HHMM_FORMAT = simpleDateFormat;
    }

    public final void showChoseWeekDialog() {
        this.dialog = NiceDialog.init().setLayoutId(R.layout.dialog_select_hum).setConvertListener(new ViewConvertListener() { // from class: com.deye.activity.device.HumSchedulerAtyHelper.showChoseWeekDialog.1
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, BaseNiceDialog baseNiceDialog) {
                Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
                Intrinsics.checkNotNullParameter(baseNiceDialog, "baseNiceDialog");
                HumSchedulerAtyHelper.this.initDialog(viewHolder, true);
            }
        }).setShowBottom(true).show(this.mActivity.getSupportFragmentManager());
    }

    static /* synthetic */ void initDialog$default(HumSchedulerAtyHelper humSchedulerAtyHelper, ViewHolder viewHolder, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        humSchedulerAtyHelper.initDialog(viewHolder, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initDialog(ViewHolder viewHolder, boolean isHum) {
        List listEmptyList;
        TextView textView;
        this.monday = (LinearLayout) viewHolder.getView(R.id.ll_day_monday);
        this.tuesday = (LinearLayout) viewHolder.getView(R.id.ll_day_tuesday);
        this.wednesday = (LinearLayout) viewHolder.getView(R.id.ll_day_wednesday);
        this.thursday = (LinearLayout) viewHolder.getView(R.id.ll_day_thursday);
        this.friday = (LinearLayout) viewHolder.getView(R.id.ll_day_friday);
        this.saturday = (LinearLayout) viewHolder.getView(R.id.ll_day_saturday);
        this.sunday = (LinearLayout) viewHolder.getView(R.id.ll_day_sunday);
        this.llDays = (LinearLayout) viewHolder.getView(R.id.ll_days);
        this.tvOnce = (TextView) viewHolder.getView(R.id.tv_once);
        this.tvEveryDay = (TextView) viewHolder.getView(R.id.tv_every);
        this.tvRepeat = (TextView) viewHolder.getView(R.id.tv_repeat);
        this.tvMsg = (TextView) viewHolder.getView(R.id.tv_msg);
        this.btnConfirm = (Button) viewHolder.getView(R.id.btn_confirm);
        if (isHum && (textView = this.tvOnce) != null) {
            textView.setVisibility(8);
        }
        View view = viewHolder.getView(R.id.iv_close);
        Intrinsics.checkNotNullExpressionValue(view, "getView(...)");
        ((ImageView) view).setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.HumSchedulerAtyHelper$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HumSchedulerAtyHelper.initDialog$lambda$0(this.f$0, view2);
            }
        });
        Button button = this.btnConfirm;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.HumSchedulerAtyHelper$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    HumSchedulerAtyHelper.initDialog$lambda$1(this.f$0, view2);
                }
            });
        }
        TextView textView2 = this.tvOnce;
        if (textView2 != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.HumSchedulerAtyHelper$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    HumSchedulerAtyHelper.initDialog$lambda$2(this.f$0, view2);
                }
            });
        }
        TextView textView3 = this.tvEveryDay;
        if (textView3 != null) {
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.HumSchedulerAtyHelper.initDialog.4
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    TextView textView4 = HumSchedulerAtyHelper.this.tvEveryDay;
                    if (textView4 != null) {
                        textView4.setSelected(true);
                    }
                    HumSchedulerAtyHelper.this.getMActivity().getMSelectedWeekList().clear();
                    HumSchedulerAtyHelper.this.getMActivity().getMSelectedWeekList().addAll(Arrays.asList(Arrays.copyOf(new String[]{StubApp.getString2(2546), StubApp.getString2(1764), StubApp.getString2(7188), StubApp.getString2(7778), StubApp.getString2(8095), StubApp.getString2(8092), StubApp.getString2(701)}, 7)));
                    TextView textView5 = HumSchedulerAtyHelper.this.tvRepeat;
                    if (textView5 != null) {
                        textView5.setSelected(false);
                    }
                    TextView textView6 = HumSchedulerAtyHelper.this.tvOnce;
                    if (textView6 != null) {
                        textView6.setSelected(false);
                    }
                    TextView textView7 = HumSchedulerAtyHelper.this.tvMsg;
                    if (textView7 != null) {
                        textView7.setSelected(false);
                    }
                    TextView textView8 = HumSchedulerAtyHelper.this.tvMsg;
                    if (textView8 != null) {
                        textView8.setVisibility(8);
                    }
                    LinearLayout linearLayout = HumSchedulerAtyHelper.this.llDays;
                    if (linearLayout != null) {
                        linearLayout.setVisibility(8);
                    }
                    HumSchedulerAtyHelper.this.getMActivity().getMActivityBinding().tvWeeks.setText(BaseUtils.getWeekString((Context) HumSchedulerAtyHelper.this.getMActivity().mContext, HumSchedulerAtyHelper.this.getWeeks()));
                }
            });
        }
        TextView textView4 = this.tvRepeat;
        if (textView4 != null) {
            textView4.setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.HumSchedulerAtyHelper.initDialog.5
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    HumSchedulerAtyHelper.this.getMActivity().getMSelectedWeekList().clear();
                    TextView textView5 = HumSchedulerAtyHelper.this.tvRepeat;
                    if (textView5 != null) {
                        textView5.setSelected(true);
                    }
                    LinearLayout linearLayout = HumSchedulerAtyHelper.this.llDays;
                    if (linearLayout != null) {
                        linearLayout.setVisibility(0);
                    }
                    TextView textView6 = HumSchedulerAtyHelper.this.tvOnce;
                    if (textView6 != null) {
                        textView6.setSelected(false);
                    }
                    TextView textView7 = HumSchedulerAtyHelper.this.tvEveryDay;
                    if (textView7 != null) {
                        textView7.setSelected(false);
                    }
                    TextView textView8 = HumSchedulerAtyHelper.this.tvMsg;
                    if (textView8 != null) {
                        textView8.setVisibility(8);
                    }
                    HumSchedulerAtyHelper.this.initDays();
                    HumSchedulerAtyHelper.this.getMActivity().getMActivityBinding().tvWeeks.setText(BaseUtils.getWeekString((Context) HumSchedulerAtyHelper.this.getMActivity().mContext, HumSchedulerAtyHelper.this.getWeeks()));
                }
            });
        }
        String[] strArr = new String[0];
        if (!BaseUtils.isNullString(this.mActivity.getMSelectedWeeks())) {
            String mSelectedWeeks = this.mActivity.getMSelectedWeeks();
            Intrinsics.checkNotNull(mSelectedWeeks);
            List listSplit = new Regex(StubApp.getString2(450)).split(mSelectedWeeks, 0);
            if (!listSplit.isEmpty()) {
                ListIterator listIterator = listSplit.listIterator(listSplit.size());
                while (listIterator.hasPrevious()) {
                    if (((String) listIterator.previous()).length() != 0) {
                        listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                        break;
                    }
                }
                listEmptyList = CollectionsKt.emptyList();
                strArr = (String[]) listEmptyList.toArray(new String[0]);
            } else {
                listEmptyList = CollectionsKt.emptyList();
                strArr = (String[]) listEmptyList.toArray(new String[0]);
            }
        }
        String mSelectedWeeks2 = this.mActivity.getMSelectedWeeks();
        Intrinsics.checkNotNull(mSelectedWeeks2);
        if (mSelectedWeeks2.length() == 0) {
            TextView textView5 = this.tvOnce;
            if (textView5 != null) {
                textView5.setSelected(true);
            }
        } else if (strArr.length == 7) {
            TextView textView6 = this.tvEveryDay;
            if (textView6 != null) {
                textView6.setSelected(true);
            }
        } else {
            initDays();
        }
        LinearLayout linearLayout = this.monday;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.HumSchedulerAtyHelper$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    HumSchedulerAtyHelper.initDialog$lambda$4(this.f$0, view2);
                }
            });
        }
        LinearLayout linearLayout2 = this.tuesday;
        if (linearLayout2 != null) {
            linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.HumSchedulerAtyHelper$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    HumSchedulerAtyHelper.initDialog$lambda$5(this.f$0, view2);
                }
            });
        }
        LinearLayout linearLayout3 = this.wednesday;
        if (linearLayout3 != null) {
            linearLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.HumSchedulerAtyHelper$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    HumSchedulerAtyHelper.initDialog$lambda$6(this.f$0, view2);
                }
            });
        }
        LinearLayout linearLayout4 = this.thursday;
        if (linearLayout4 != null) {
            linearLayout4.setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.HumSchedulerAtyHelper$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    HumSchedulerAtyHelper.initDialog$lambda$7(this.f$0, view2);
                }
            });
        }
        LinearLayout linearLayout5 = this.friday;
        if (linearLayout5 != null) {
            linearLayout5.setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.HumSchedulerAtyHelper$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    HumSchedulerAtyHelper.initDialog$lambda$8(this.f$0, view2);
                }
            });
        }
        LinearLayout linearLayout6 = this.saturday;
        if (linearLayout6 != null) {
            linearLayout6.setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.HumSchedulerAtyHelper$$ExternalSyntheticLambda10
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    HumSchedulerAtyHelper.initDialog$lambda$9(this.f$0, view2);
                }
            });
        }
        LinearLayout linearLayout7 = this.sunday;
        if (linearLayout7 != null) {
            linearLayout7.setOnClickListener(new View.OnClickListener() { // from class: com.deye.activity.device.HumSchedulerAtyHelper$$ExternalSyntheticLambda11
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    HumSchedulerAtyHelper.initDialog$lambda$10(this.f$0, view2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$0(HumSchedulerAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseNiceDialog baseNiceDialog = this$0.dialog;
        Intrinsics.checkNotNull(baseNiceDialog);
        baseNiceDialog.dismissAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$1(HumSchedulerAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseNiceDialog baseNiceDialog = this$0.dialog;
        Intrinsics.checkNotNull(baseNiceDialog);
        baseNiceDialog.dismissAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$2(HumSchedulerAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.tvOnce;
        if (textView != null) {
            textView.setSelected(true);
        }
        TextView textView2 = this$0.tvEveryDay;
        if (textView2 != null) {
            textView2.setSelected(false);
        }
        TextView textView3 = this$0.tvRepeat;
        if (textView3 != null) {
            textView3.setSelected(false);
        }
        TextView textView4 = this$0.tvMsg;
        if (textView4 != null) {
            textView4.setSelected(false);
        }
        TextView textView5 = this$0.tvMsg;
        if (textView5 != null) {
            textView5.setVisibility(8);
        }
        LinearLayout linearLayout = this$0.llDays;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        this$0.mActivity.getMSelectedWeekList().clear();
        this$0.mActivity.getMActivityBinding().tvWeeks.setText(BaseUtils.getWeekString((Context) this$0.mActivity.mContext, this$0.getWeeks()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$4(HumSchedulerAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.itemClick(this$0.monday, StubApp.getString2(2546));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$5(HumSchedulerAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.itemClick(this$0.tuesday, StubApp.getString2(1764));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$6(HumSchedulerAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.itemClick(this$0.wednesday, StubApp.getString2(7188));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$7(HumSchedulerAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.itemClick(this$0.thursday, StubApp.getString2(7778));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$8(HumSchedulerAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.itemClick(this$0.friday, StubApp.getString2(8095));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$9(HumSchedulerAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.itemClick(this$0.saturday, StubApp.getString2(8092));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$10(HumSchedulerAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.itemClick(this$0.sunday, StubApp.getString2(701));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initDays() {
        if (this.mActivity.getMSelectedWeekList().contains(StubApp.getString2(2546))) {
            setDaySelect(this.monday);
        } else {
            setDayUnSelect(this.monday);
        }
        if (this.mActivity.getMSelectedWeekList().contains(StubApp.getString2(1764))) {
            setDaySelect(this.tuesday);
        } else {
            setDayUnSelect(this.tuesday);
        }
        if (this.mActivity.getMSelectedWeekList().contains(StubApp.getString2(7188))) {
            setDaySelect(this.wednesday);
        } else {
            setDayUnSelect(this.wednesday);
        }
        if (this.mActivity.getMSelectedWeekList().contains(StubApp.getString2(7778))) {
            setDaySelect(this.thursday);
        } else {
            setDayUnSelect(this.thursday);
        }
        if (this.mActivity.getMSelectedWeekList().contains(StubApp.getString2(8095))) {
            setDaySelect(this.friday);
        } else {
            setDayUnSelect(this.friday);
        }
        if (this.mActivity.getMSelectedWeekList().contains(StubApp.getString2(8092))) {
            setDaySelect(this.saturday);
        } else {
            setDayUnSelect(this.saturday);
        }
        if (this.mActivity.getMSelectedWeekList().contains(StubApp.getString2(701))) {
            setDaySelect(this.sunday);
        } else {
            setDayUnSelect(this.sunday);
        }
    }

    private final void setDaySelect(LinearLayout layout) {
        TextView textView = this.tvOnce;
        Intrinsics.checkNotNull(textView);
        textView.setSelected(false);
        TextView textView2 = this.tvEveryDay;
        Intrinsics.checkNotNull(textView2);
        textView2.setSelected(false);
        TextView textView3 = this.tvRepeat;
        Intrinsics.checkNotNull(textView3);
        textView3.setSelected(true);
        TextView textView4 = this.tvMsg;
        Intrinsics.checkNotNull(textView4);
        textView4.setSelected(true);
        Intrinsics.checkNotNull(layout);
        layout.getChildAt(0).setSelected(true);
        layout.getChildAt(1).setSelected(true);
    }

    private final void setDayUnSelect(LinearLayout layout) {
        Intrinsics.checkNotNull(layout);
        layout.getChildAt(0).setSelected(false);
        layout.getChildAt(1).setSelected(false);
    }

    private final void itemClick(LinearLayout day, String s) {
        if (!this.mActivity.getMSelectedWeekList().contains(s)) {
            this.mActivity.getMSelectedWeekList().add(s);
            setDaySelect(day);
        } else {
            this.mActivity.getMSelectedWeekList().remove(s);
            setDayUnSelect(day);
        }
        if (!this.mActivity.getMSelectedWeekList().isEmpty()) {
            TextView textView = this.tvMsg;
            Intrinsics.checkNotNull(textView);
            textView.setVisibility(0);
            TextView textView2 = this.tvMsg;
            Intrinsics.checkNotNull(textView2);
            textView2.setText(BaseUtils.getWeekString((Context) this.mActivity.mContext, getWeeks()));
        } else {
            TextView textView3 = this.tvMsg;
            Intrinsics.checkNotNull(textView3);
            textView3.setVisibility(8);
        }
        this.mActivity.getMActivityBinding().tvWeeks.setText(BaseUtils.getWeekString((Context) this.mActivity.mContext, getWeeks()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getWeeks() throws NumberFormatException {
        int size = this.mActivity.getMSelectedWeekList().size();
        String string2 = StubApp.getString2(13490);
        if (size > 2) {
            int size2 = this.mActivity.getMSelectedWeekList().size() - 1;
            for (int i = 0; i < size2; i++) {
                int size3 = (this.mActivity.getMSelectedWeekList().size() - i) - 1;
                int i2 = 0;
                while (i2 < size3) {
                    String str = this.mActivity.getMSelectedWeekList().get(i2);
                    Intrinsics.checkNotNullExpressionValue(str, string2);
                    int i3 = Integer.parseInt(str);
                    int i4 = i2 + 1;
                    String str2 = this.mActivity.getMSelectedWeekList().get(i4);
                    Intrinsics.checkNotNullExpressionValue(str2, string2);
                    if (i3 > Integer.parseInt(str2)) {
                        String str3 = this.mActivity.getMSelectedWeekList().get(i2);
                        Intrinsics.checkNotNullExpressionValue(str3, string2);
                        this.mActivity.getMSelectedWeekList().set(i2, this.mActivity.getMSelectedWeekList().get(i4));
                        this.mActivity.getMSelectedWeekList().set(i4, str3);
                    }
                    i2 = i4;
                }
            }
        }
        int size4 = this.mActivity.getMSelectedWeekList().size();
        String str4 = "";
        if (size4 != 0) {
            if (size4 == 1) {
                String str5 = this.mActivity.getMSelectedWeekList().get(0);
                Intrinsics.checkNotNullExpressionValue(str5, string2);
                str4 = str5;
            } else {
                int size5 = this.mActivity.getMSelectedWeekList().size();
                for (int i5 = 0; i5 < size5; i5++) {
                    if (i5 == 0) {
                        String str6 = this.mActivity.getMSelectedWeekList().get(0);
                        Intrinsics.checkNotNull(str6);
                        str4 = str6;
                    } else {
                        str4 = str4 + StubApp.getString2(450) + ((Object) this.mActivity.getMSelectedWeekList().get(i5));
                    }
                }
            }
        }
        this.mActivity.setMSelectedWeeks(str4);
        return str4;
    }

    public final String todayHhMm() {
        String str = this.HHMM_FORMAT.format(new Date());
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public final void updateTimingTask(RequestBody body) {
        DeYeHttpRequestManager.getInstance().updateTimingTask(body, new ControlDeviceCallBack() { // from class: com.deye.activity.device.HumSchedulerAtyHelper.updateTimingTask.1
            @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
            public void onSuccess(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                super.onSuccess(message);
                HumSchedulerAtyHelper.this.requestSuccess(message);
            }

            @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
            public void onFailure(int code, String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                super.onFailure(code, message);
                HumSchedulerAtyHelper.this.requestFail(message);
            }
        });
    }

    public final void createTimingTask(RequestBody body) {
        DeYeHttpRequestManager.getInstance().createTimingTask(body, new ControlDeviceCallBack() { // from class: com.deye.activity.device.HumSchedulerAtyHelper.createTimingTask.1
            @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
            public void onSuccess(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                super.onSuccess(message);
                HumSchedulerAtyHelper.this.requestSuccess(message);
            }

            @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
            public void onFailure(int code, String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                super.onFailure(code, message);
                HumSchedulerAtyHelper.this.requestFail(message);
            }
        });
    }

    public final void requestSuccess(final String message) {
        this.mActivity.runOnUiThread(new Runnable() { // from class: com.deye.activity.device.HumSchedulerAtyHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                HumSchedulerAtyHelper.requestSuccess$lambda$11(this.f$0, message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestSuccess$lambda$11(HumSchedulerAtyHelper this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mActivity.stopWaiting();
        Integer integer = JSON.parseObject(JSON.parseObject(str).getString(StubApp.getString2(13082))).getInteger(StubApp.getString2(109));
        if (integer != null && integer.intValue() == 0) {
            this$0.mActivity.finish();
        }
    }

    public final void requestFail(final String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.mActivity.runOnUiThread(new Runnable() { // from class: com.deye.activity.device.HumSchedulerAtyHelper$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                HumSchedulerAtyHelper.requestFail$lambda$12(this.f$0, message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestFail$lambda$12(HumSchedulerAtyHelper this$0, String message) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(message, "$message");
        this$0.mActivity.stopWaiting();
        BaseUtils.showShortToast((Context) this$0.mActivity, message);
    }
}
