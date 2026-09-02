package com.deye.helper;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.deye.activity.device.AddSchedulerAty;
import com.deye.utils.BaseUtils;
import com.mxchipapp.R;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.callback.ControlDeviceCallBack;
import it.innove.Peripheral;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import okhttp3.RequestBody;

/* compiled from: AddScheduelrAtyHelper.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'J\b\u0010(\u001a\u00020%H\u0002J\u0010\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020+H\u0002J\u001a\u0010,\u001a\u00020%2\b\u0010-\u001a\u0004\u0018\u00010\u00102\u0006\u0010.\u001a\u00020!H\u0002J\u000e\u0010/\u001a\u00020%2\u0006\u00100\u001a\u00020!J\u0010\u00101\u001a\u00020%2\b\u00100\u001a\u0004\u0018\u00010!J\u0012\u00102\u001a\u00020%2\b\u00103\u001a\u0004\u0018\u00010\u0010H\u0002J\u0012\u00104\u001a\u00020%2\b\u00103\u001a\u0004\u0018\u00010\u0010H\u0002J\u0006\u00105\u001a\u00020%J\u0006\u00106\u001a\u00020!J\u0010\u00107\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0004R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00020!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u00068"}, d2 = {"Lcom/deye/helper/AddScheduelrAtyHelper;", "", "mAddSchedulerAty", "Lcom/deye/activity/device/AddSchedulerAty;", "(Lcom/deye/activity/device/AddSchedulerAty;)V", "HHMM_FORMAT", "Ljava/text/SimpleDateFormat;", "getHHMM_FORMAT", "()Ljava/text/SimpleDateFormat;", "setHHMM_FORMAT", "(Ljava/text/SimpleDateFormat;)V", "btnConfirm", "Landroid/widget/Button;", "dialog", "Lcom/othershe/nicedialog/BaseNiceDialog;", "friday", "Landroid/widget/LinearLayout;", "llDays", "getMAddSchedulerAty", "()Lcom/deye/activity/device/AddSchedulerAty;", "setMAddSchedulerAty", "monday", "saturday", "sunday", "thursday", "tuesday", "tvEveryDay", "Landroid/widget/TextView;", "tvMsg", "tvOnce", "tvRepeat", "wednesday", "weeks", "", "getWeeks", "()Ljava/lang/String;", "createTimingTask", "", "body", "Lokhttp3/RequestBody;", "initDays", "initDialog", "viewHolder", "Lcom/othershe/nicedialog/ViewHolder;", "itemClick", "day", "s", "requestFail", "message", "requestSuccess", "setDaySelect", "layout", "setDayUnSelect", "showChoseWeekDialog", "todayHhMm", "updateTimingTask", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class AddScheduelrAtyHelper {
    private SimpleDateFormat HHMM_FORMAT;
    private Button btnConfirm;
    private BaseNiceDialog dialog;
    private LinearLayout friday;
    private LinearLayout llDays;
    private AddSchedulerAty mAddSchedulerAty;
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

    public AddScheduelrAtyHelper(AddSchedulerAty mAddSchedulerAty) {
        Intrinsics.checkNotNullParameter(mAddSchedulerAty, "mAddSchedulerAty");
        this.mAddSchedulerAty = mAddSchedulerAty;
        this.HHMM_FORMAT = new SimpleDateFormat(StubApp.getString2(13489), Locale.getDefault());
    }

    public final AddSchedulerAty getMAddSchedulerAty() {
        return this.mAddSchedulerAty;
    }

    public final void setMAddSchedulerAty(AddSchedulerAty addSchedulerAty) {
        Intrinsics.checkNotNullParameter(addSchedulerAty, "<set-?>");
        this.mAddSchedulerAty = addSchedulerAty;
    }

    public final SimpleDateFormat getHHMM_FORMAT() {
        return this.HHMM_FORMAT;
    }

    public final void setHHMM_FORMAT(SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.HHMM_FORMAT = simpleDateFormat;
    }

    public final void showChoseWeekDialog() {
        this.dialog = NiceDialog.init().setLayoutId(R.layout.dialog_select_hum).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.AddScheduelrAtyHelper.showChoseWeekDialog.1
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, BaseNiceDialog baseNiceDialog) {
                Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
                Intrinsics.checkNotNullParameter(baseNiceDialog, "baseNiceDialog");
                AddScheduelrAtyHelper.this.initDialog(viewHolder);
            }
        }).setShowBottom(true).show(this.mAddSchedulerAty.getSupportFragmentManager());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initDialog(ViewHolder viewHolder) {
        List listEmptyList;
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
        View view = viewHolder.getView(R.id.iv_close);
        Intrinsics.checkNotNullExpressionValue(view, "getView(...)");
        ((ImageView) view).setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.AddScheduelrAtyHelper$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AddScheduelrAtyHelper.initDialog$lambda$0(this.f$0, view2);
            }
        });
        Button button = this.btnConfirm;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.AddScheduelrAtyHelper$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AddScheduelrAtyHelper.initDialog$lambda$1(this.f$0, view2);
                }
            });
        }
        TextView textView = this.tvOnce;
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.AddScheduelrAtyHelper$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AddScheduelrAtyHelper.initDialog$lambda$2(this.f$0, view2);
                }
            });
        }
        TextView textView2 = this.tvEveryDay;
        if (textView2 != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.AddScheduelrAtyHelper.initDialog.4
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    TextView textView3 = AddScheduelrAtyHelper.this.tvEveryDay;
                    if (textView3 != null) {
                        textView3.setSelected(true);
                    }
                    AddScheduelrAtyHelper.this.getMAddSchedulerAty().getMSelectedWeekList().clear();
                    AddScheduelrAtyHelper.this.getMAddSchedulerAty().getMSelectedWeekList().addAll(Arrays.asList(Arrays.copyOf(new String[]{StubApp.getString2(2546), StubApp.getString2(1764), StubApp.getString2(7188), StubApp.getString2(7778), StubApp.getString2(8095), StubApp.getString2(8092), StubApp.getString2(701)}, 7)));
                    TextView textView4 = AddScheduelrAtyHelper.this.tvRepeat;
                    if (textView4 != null) {
                        textView4.setSelected(false);
                    }
                    TextView textView5 = AddScheduelrAtyHelper.this.tvOnce;
                    if (textView5 != null) {
                        textView5.setSelected(false);
                    }
                    TextView textView6 = AddScheduelrAtyHelper.this.tvMsg;
                    if (textView6 != null) {
                        textView6.setSelected(false);
                    }
                    TextView textView7 = AddScheduelrAtyHelper.this.tvMsg;
                    if (textView7 != null) {
                        textView7.setVisibility(8);
                    }
                    LinearLayout linearLayout = AddScheduelrAtyHelper.this.llDays;
                    if (linearLayout == null) {
                        return;
                    }
                    linearLayout.setVisibility(8);
                }
            });
        }
        TextView textView3 = this.tvRepeat;
        if (textView3 != null) {
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.AddScheduelrAtyHelper.initDialog.5
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    AddScheduelrAtyHelper.this.getMAddSchedulerAty().getMSelectedWeekList().clear();
                    TextView textView4 = AddScheduelrAtyHelper.this.tvRepeat;
                    if (textView4 != null) {
                        textView4.setSelected(true);
                    }
                    LinearLayout linearLayout = AddScheduelrAtyHelper.this.llDays;
                    if (linearLayout != null) {
                        linearLayout.setVisibility(0);
                    }
                    TextView textView5 = AddScheduelrAtyHelper.this.tvOnce;
                    if (textView5 != null) {
                        textView5.setSelected(false);
                    }
                    TextView textView6 = AddScheduelrAtyHelper.this.tvEveryDay;
                    if (textView6 != null) {
                        textView6.setSelected(false);
                    }
                    TextView textView7 = AddScheduelrAtyHelper.this.tvMsg;
                    if (textView7 != null) {
                        textView7.setVisibility(8);
                    }
                    AddScheduelrAtyHelper.this.initDays();
                }
            });
        }
        String[] strArr = new String[0];
        if (!BaseUtils.isNullString(this.mAddSchedulerAty.getMSelectedWeeks())) {
            String mSelectedWeeks = this.mAddSchedulerAty.getMSelectedWeeks();
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
        String mSelectedWeeks2 = this.mAddSchedulerAty.getMSelectedWeeks();
        Intrinsics.checkNotNull(mSelectedWeeks2);
        if (mSelectedWeeks2.length() == 0) {
            TextView textView4 = this.tvOnce;
            if (textView4 != null) {
                textView4.setSelected(true);
            }
        } else if (strArr.length == 7) {
            initDays();
        } else {
            initDays();
        }
        LinearLayout linearLayout = this.monday;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.AddScheduelrAtyHelper$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AddScheduelrAtyHelper.initDialog$lambda$4(this.f$0, view2);
                }
            });
        }
        LinearLayout linearLayout2 = this.tuesday;
        if (linearLayout2 != null) {
            linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.AddScheduelrAtyHelper$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AddScheduelrAtyHelper.initDialog$lambda$5(this.f$0, view2);
                }
            });
        }
        LinearLayout linearLayout3 = this.wednesday;
        if (linearLayout3 != null) {
            linearLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.AddScheduelrAtyHelper$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AddScheduelrAtyHelper.initDialog$lambda$6(this.f$0, view2);
                }
            });
        }
        LinearLayout linearLayout4 = this.thursday;
        if (linearLayout4 != null) {
            linearLayout4.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.AddScheduelrAtyHelper$$ExternalSyntheticLambda10
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AddScheduelrAtyHelper.initDialog$lambda$7(this.f$0, view2);
                }
            });
        }
        LinearLayout linearLayout5 = this.friday;
        if (linearLayout5 != null) {
            linearLayout5.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.AddScheduelrAtyHelper$$ExternalSyntheticLambda11
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AddScheduelrAtyHelper.initDialog$lambda$8(this.f$0, view2);
                }
            });
        }
        LinearLayout linearLayout6 = this.saturday;
        if (linearLayout6 != null) {
            linearLayout6.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.AddScheduelrAtyHelper$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AddScheduelrAtyHelper.initDialog$lambda$9(this.f$0, view2);
                }
            });
        }
        LinearLayout linearLayout7 = this.sunday;
        if (linearLayout7 != null) {
            linearLayout7.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.AddScheduelrAtyHelper$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AddScheduelrAtyHelper.initDialog$lambda$10(this.f$0, view2);
                }
            });
        }
        TextView textView5 = this.tvOnce;
        if (textView5 != null) {
            textView5.setSelected(true);
        }
        TextView textView6 = this.tvEveryDay;
        if (textView6 != null) {
            textView6.setSelected(false);
        }
        TextView textView7 = this.tvRepeat;
        if (textView7 != null) {
            textView7.setSelected(false);
        }
        TextView textView8 = this.tvMsg;
        if (textView8 != null) {
            textView8.setSelected(false);
        }
        TextView textView9 = this.tvMsg;
        if (textView9 != null) {
            textView9.setVisibility(8);
        }
        LinearLayout linearLayout8 = this.llDays;
        if (linearLayout8 == null) {
            return;
        }
        linearLayout8.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$0(AddScheduelrAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseNiceDialog baseNiceDialog = this$0.dialog;
        Intrinsics.checkNotNull(baseNiceDialog);
        baseNiceDialog.dismissAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$1(AddScheduelrAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mAddSchedulerAty.getMAddSchedulerAtyBinding().tvWeeks.setText(BaseUtils.getWeekString((Context) this$0.mAddSchedulerAty.mContext, this$0.getWeeks()));
        BaseNiceDialog baseNiceDialog = this$0.dialog;
        Intrinsics.checkNotNull(baseNiceDialog);
        baseNiceDialog.dismissAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$2(AddScheduelrAtyHelper this$0, View view) {
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
        this$0.mAddSchedulerAty.getMSelectedWeekList().clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$4(AddScheduelrAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.itemClick(this$0.monday, StubApp.getString2(2546));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$5(AddScheduelrAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.itemClick(this$0.tuesday, StubApp.getString2(1764));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$6(AddScheduelrAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.itemClick(this$0.wednesday, StubApp.getString2(7188));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$7(AddScheduelrAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.itemClick(this$0.thursday, StubApp.getString2(7778));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$8(AddScheduelrAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.itemClick(this$0.friday, StubApp.getString2(8095));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$9(AddScheduelrAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.itemClick(this$0.saturday, StubApp.getString2(8092));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initDialog$lambda$10(AddScheduelrAtyHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.itemClick(this$0.sunday, StubApp.getString2(701));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initDays() {
        if (this.mAddSchedulerAty.getMSelectedWeekList().contains(StubApp.getString2(2546))) {
            setDaySelect(this.monday);
        } else {
            setDayUnSelect(this.monday);
        }
        if (this.mAddSchedulerAty.getMSelectedWeekList().contains(StubApp.getString2(1764))) {
            setDaySelect(this.tuesday);
        } else {
            setDayUnSelect(this.tuesday);
        }
        if (this.mAddSchedulerAty.getMSelectedWeekList().contains(StubApp.getString2(7188))) {
            setDaySelect(this.wednesday);
        } else {
            setDayUnSelect(this.wednesday);
        }
        if (this.mAddSchedulerAty.getMSelectedWeekList().contains(StubApp.getString2(7778))) {
            setDaySelect(this.thursday);
        } else {
            setDayUnSelect(this.thursday);
        }
        if (this.mAddSchedulerAty.getMSelectedWeekList().contains(StubApp.getString2(8095))) {
            setDaySelect(this.friday);
        } else {
            setDayUnSelect(this.friday);
        }
        if (this.mAddSchedulerAty.getMSelectedWeekList().contains(StubApp.getString2(8092))) {
            setDaySelect(this.saturday);
        } else {
            setDayUnSelect(this.saturday);
        }
        if (this.mAddSchedulerAty.getMSelectedWeekList().contains(StubApp.getString2(701))) {
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
        if (!this.mAddSchedulerAty.getMSelectedWeekList().contains(s)) {
            this.mAddSchedulerAty.getMSelectedWeekList().add(s);
            setDaySelect(day);
        } else {
            this.mAddSchedulerAty.getMSelectedWeekList().remove(s);
            setDayUnSelect(day);
        }
        if (!this.mAddSchedulerAty.getMSelectedWeekList().isEmpty()) {
            TextView textView = this.tvMsg;
            Intrinsics.checkNotNull(textView);
            textView.setVisibility(0);
            TextView textView2 = this.tvMsg;
            Intrinsics.checkNotNull(textView2);
            textView2.setText(BaseUtils.getWeekString((Context) this.mAddSchedulerAty.mContext, getWeeks()));
        } else {
            TextView textView3 = this.tvMsg;
            Intrinsics.checkNotNull(textView3);
            textView3.setVisibility(8);
        }
        this.mAddSchedulerAty.getMAddSchedulerAtyBinding().tvWeeks.setText(BaseUtils.getWeekString((Context) this.mAddSchedulerAty.mContext, getWeeks()));
    }

    private final String getWeeks() {
        if (this.mAddSchedulerAty.getMSelectedWeekList().size() >= 2) {
            CollectionsKt.sortWith(this.mAddSchedulerAty.getMSelectedWeekList(), new Comparator<String>() { // from class: com.deye.helper.AddScheduelrAtyHelper$weeks$1
                @Override // java.util.Comparator
                public int compare(String o1, String o2) {
                    Intrinsics.checkNotNullParameter(o1, "o1");
                    Intrinsics.checkNotNullParameter(o2, "o2");
                    if (Integer.parseInt(o1) == 0) {
                        return 1;
                    }
                    if (Integer.parseInt(o2) == 0) {
                        return -1;
                    }
                    return Integer.parseInt(o1) - Integer.parseInt(o2);
                }
            });
        }
        int size = this.mAddSchedulerAty.getMSelectedWeekList().size();
        String str = "";
        if (size != 0) {
            if (size == 1) {
                String str2 = this.mAddSchedulerAty.getMSelectedWeekList().get(0);
                Intrinsics.checkNotNullExpressionValue(str2, "get(...)");
                str = str2;
            } else {
                int size2 = this.mAddSchedulerAty.getMSelectedWeekList().size();
                for (int i = 0; i < size2; i++) {
                    if (i == 0) {
                        String str3 = this.mAddSchedulerAty.getMSelectedWeekList().get(0);
                        Intrinsics.checkNotNull(str3);
                        str = str3;
                    } else {
                        str = str + StubApp.getString2(450) + ((Object) this.mAddSchedulerAty.getMSelectedWeekList().get(i));
                    }
                }
            }
        }
        this.mAddSchedulerAty.setMSelectedWeeks(str);
        return str;
    }

    public final String todayHhMm() {
        String str = this.HHMM_FORMAT.format(new Date());
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public final void updateTimingTask(RequestBody body) {
        DeYeHttpRequestManager.getInstance().updateTimingTask(body, new ControlDeviceCallBack() { // from class: com.deye.helper.AddScheduelrAtyHelper.updateTimingTask.1
            @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
            public void onSuccess(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                super.onSuccess(message);
                AddScheduelrAtyHelper.this.requestSuccess(message);
            }

            @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
            public void onFailure(int code, String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                super.onFailure(code, message);
                AddScheduelrAtyHelper.this.requestFail(message);
            }
        });
    }

    public final void createTimingTask(RequestBody body) {
        DeYeHttpRequestManager.getInstance().createTimingTask(body, new ControlDeviceCallBack() { // from class: com.deye.helper.AddScheduelrAtyHelper.createTimingTask.1
            @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
            public void onSuccess(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                super.onSuccess(message);
                AddScheduelrAtyHelper.this.requestSuccess(message);
            }

            @Override // io.fogcloud.sdk.fog.callback.ControlDeviceCallBack
            public void onFailure(int code, String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                super.onFailure(code, message);
                AddScheduelrAtyHelper.this.requestFail(message);
            }
        });
    }

    public final void requestSuccess(final String message) {
        this.mAddSchedulerAty.runOnUiThread(new Runnable() { // from class: com.deye.helper.AddScheduelrAtyHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AddScheduelrAtyHelper.requestSuccess$lambda$11(this.f$0, message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestSuccess$lambda$11(AddScheduelrAtyHelper this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mAddSchedulerAty.stopWaiting();
        String string = JSON.parseObject(str).getString(StubApp.getString2(13082));
        Integer integer = JSON.parseObject(string).getInteger(StubApp.getString2(109));
        String string2 = JSON.parseObject(string).getString(StubApp.getString2(Peripheral.GATT_AUTH_FAIL));
        if (integer != null && integer.intValue() == 0) {
            this$0.mAddSchedulerAty.finish();
        } else {
            BaseUtils.showShortToast((Context) this$0.mAddSchedulerAty, string2);
        }
    }

    public final void requestFail(final String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.mAddSchedulerAty.runOnUiThread(new Runnable() { // from class: com.deye.helper.AddScheduelrAtyHelper$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AddScheduelrAtyHelper.requestFail$lambda$12(this.f$0, message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestFail$lambda$12(AddScheduelrAtyHelper this$0, String message) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(message, "$message");
        this$0.mAddSchedulerAty.stopWaiting();
        BaseUtils.showShortToast((Context) this$0.mAddSchedulerAty, message);
    }
}
