package com.deye.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aigestudio.wheelpicker.WheelPicker;
import com.deye.activity.config_net.DeviceScanQRActivity;
import com.deye.activity.device.base.BaseActivity;
import com.deye.activity.mine.FeedBackActivity;
import com.deye.adapter.AppUpgradeContentAdapter;
import com.deye.utils.ActivityRouterUtilsKt;
import com.deye.utils.BaseUtils;
import com.deye.utils.ChannelUtil;
import com.deye.utils.PermissionPageUtils;
import com.deye.utils.WebUrlManager;
import com.deye.views.EmojiFilter;
import com.deye.webview.AgentWebActivity;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.mxchipapp.R;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;
import com.stub.StubApp;
import com.umeng.socialize.net.dplus.CommonNetImpl;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class DialogHelper {
    public static final String TAG = StubApp.getString2(14151);

    public interface IUserInfoChoice {
        void onUserInfoChoice(int i);
    }

    public interface IWifiOtaDoneListener {
        void onWifiOtaDoneListener();
    }

    public interface OnDeleteTipBottomDialogListsner {

        public enum SelectItem {
            SURE,
            CANCEL
        }

        void onSelectedItem(SelectItem selectItem);
    }

    public static abstract class OnDialogListener {
        public void onCancel() {
        }

        public void onDismiss() {
        }

        public abstract void onSure(String str);
    }

    public interface OnEditSchedulerListsner {
        void onSchedulerResult(int i, int i2);
    }

    public interface OnSelectHeadPortraitListsner {

        public enum SelectItem {
            CAMERA,
            ALBUM,
            CANCEL
        }

        void onSelectedItem(SelectItem selectItem);
    }

    public static void oneBtnDialog(final BaseActivity baseActivity, final String str, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.one_btn_dialog_layout).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.1
            @Override // com.othershe.nicedialog.ViewConvertListener
            public void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setText(R.id.tv_content, str);
                viewHolder.setText(R.id.tv_sure, baseActivity.getResources().getString(R.string.sure_text));
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setWidth(290).setOutCancel(false).show(baseActivity.getSupportFragmentManager());
    }

    public static void cleanAccountUnbindDeviceDialog(BaseActivity baseActivity, final String str, final OnDialogListener onDialogListener) {
        if (baseActivity.isDestroyed() || baseActivity.isFinishing()) {
            return;
        }
        FragmentManager supportFragmentManager = baseActivity.getSupportFragmentManager();
        if (supportFragmentManager.isDestroyed()) {
            return;
        }
        NiceDialog.init().setLayoutId(R.layout.clean_account_unbind_device_dialog_layout).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.2
            @Override // com.othershe.nicedialog.ViewConvertListener
            public void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setText(R.id.tv_content, str);
                viewHolder.setText(R.id.tv_sure, StubApp.getString2(14138));
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.2.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setWidth(290).setOutCancel(false).show(supportFragmentManager);
    }

    public static void showPowerOffDialog(BaseActivity baseActivity, OnDialogListener onDialogListener) {
        showPowerOffDialog(baseActivity, StubApp.getOrigApplicationContext(baseActivity.getApplicationContext()).getResources().getString(R.string.power_off_dialog_tip_text), onDialogListener);
    }

    public static void showPowerOffDialog(BaseActivity baseActivity, final String str, final OnDialogListener onDialogListener) {
        try {
            NiceDialog.init().setLayoutId(R.layout.dialog_power_off).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.3
                @Override // com.othershe.nicedialog.ViewConvertListener
                protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                    viewHolder.setText(R.id.tv_title, str);
                    viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.3.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            baseNiceDialog.dismiss();
                            if (onDialogListener != null) {
                                onDialogListener.onCancel();
                            }
                        }
                    });
                    viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.3.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            baseNiceDialog.dismiss();
                            if (onDialogListener != null) {
                                onDialogListener.onSure("");
                            }
                        }
                    });
                }
            }).setOutCancel(false).show(baseActivity.getSupportFragmentManager());
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public static void showProtocolTipDialog(final FragmentActivity fragmentActivity, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.show_protocol).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.4
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                TextView textView = (TextView) viewHolder.getView(R.id.tv_register_protocol);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(StubApp.getString2(14141));
                String string2 = StubApp.getString2(13599);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor(string2)), 7, 13, 33);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor(string2)), 14, 20, 33);
                spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.deye.helper.DialogHelper.4.1
                    @Override // android.text.style.ClickableSpan
                    public void onClick(View view) {
                        WebUrlManager.INSTANCE.openUserAgreement(fragmentActivity);
                    }

                    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                    public void updateDrawState(TextPaint textPaint) {
                        super.updateDrawState(textPaint);
                        textPaint.setColor(Color.parseColor(StubApp.getString2(13599)));
                        textPaint.setUnderlineText(false);
                    }
                }, 7, 13, 33);
                spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.deye.helper.DialogHelper.4.2
                    @Override // android.text.style.ClickableSpan
                    public void onClick(View view) {
                        WebUrlManager.INSTANCE.openPrivacyPolicy(fragmentActivity);
                    }

                    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                    public void updateDrawState(TextPaint textPaint) {
                        super.updateDrawState(textPaint);
                        textPaint.setColor(Color.parseColor(StubApp.getString2(13599)));
                        textPaint.setUnderlineText(false);
                    }
                }, 14, 20, 33);
                textView.setText(spannableStringBuilder);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                textView.setHighlightColor(Color.parseColor(StubApp.getString2(14142)));
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.4.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onCancel();
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.4.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setOutCancel(false).show(fragmentActivity.getSupportFragmentManager());
    }

    public static void showSecondaryProtocolDialog(final FragmentActivity fragmentActivity, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.show_protocol_secondary).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.5
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                TextView textView = (TextView) viewHolder.getView(R.id.tv_secondary_protocol);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(StubApp.getString2(14146));
                String string2 = StubApp.getString2(13599);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor(string2)), 25, 31, 33);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor(string2)), 32, 42, 33);
                spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.deye.helper.DialogHelper.5.1
                    @Override // android.text.style.ClickableSpan
                    public void onClick(View view) {
                        AgentWebActivity.open(fragmentActivity, StubApp.getString2(14143), StubApp.getString2(13734));
                    }

                    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                    public void updateDrawState(TextPaint textPaint) {
                        super.updateDrawState(textPaint);
                        textPaint.setColor(Color.parseColor(StubApp.getString2(13599)));
                        textPaint.setUnderlineText(true);
                    }
                }, 25, 31, 33);
                spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.deye.helper.DialogHelper.5.2
                    @Override // android.text.style.ClickableSpan
                    public void onClick(View view) {
                        AgentWebActivity.open(fragmentActivity, StubApp.getString2(14144), StubApp.getString2(14145));
                    }

                    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                    public void updateDrawState(TextPaint textPaint) {
                        super.updateDrawState(textPaint);
                        textPaint.setColor(Color.parseColor(StubApp.getString2(13599)));
                        textPaint.setUnderlineText(true);
                    }
                }, 32, 42, 33);
                textView.setText(spannableStringBuilder);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                textView.setHighlightColor(Color.parseColor(StubApp.getString2(14142)));
                viewHolder.setOnClickListener(R.id.tv_disagree_exit, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.5.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        fragmentActivity.finish();
                        System.exit(0);
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_agree_continue, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.5.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setOutCancel(false).show(fragmentActivity.getSupportFragmentManager());
    }

    public static void showFirstProtocolTipDialog(final FragmentActivity fragmentActivity, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.show_protocol_first).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.6
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                TextView textView = (TextView) viewHolder.getView(R.id.tv_register_protocol);
                String string = fragmentActivity.getString(R.string.first_protocol_dialog_content);
                String string2 = fragmentActivity.getString(R.string.service_agreement_dialog_flag);
                String string3 = fragmentActivity.getString(R.string.privacy_policy_dialog_flag);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
                int iIndexOf = string.indexOf(string2);
                int length = string2.length() + iIndexOf;
                int iIndexOf2 = string.indexOf(string3);
                int length2 = string3.length() + iIndexOf2;
                String string22 = StubApp.getString2(13599);
                if (iIndexOf >= 0) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor(string22)), iIndexOf, length, 33);
                    spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.deye.helper.DialogHelper.6.1
                        @Override // android.text.style.ClickableSpan
                        public void onClick(View view) {
                            WebUrlManager.INSTANCE.openUserAgreement(fragmentActivity);
                        }

                        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                        public void updateDrawState(TextPaint textPaint) {
                            super.updateDrawState(textPaint);
                            textPaint.setColor(Color.parseColor(StubApp.getString2(13599)));
                            textPaint.setUnderlineText(false);
                        }
                    }, iIndexOf, length, 33);
                }
                if (iIndexOf2 >= 0) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor(string22)), iIndexOf2, length2, 33);
                    spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.deye.helper.DialogHelper.6.2
                        @Override // android.text.style.ClickableSpan
                        public void onClick(View view) {
                            WebUrlManager.INSTANCE.openPrivacyPolicy(fragmentActivity);
                        }

                        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                        public void updateDrawState(TextPaint textPaint) {
                            super.updateDrawState(textPaint);
                            textPaint.setColor(Color.parseColor(StubApp.getString2(13599)));
                            textPaint.setUnderlineText(false);
                        }
                    }, iIndexOf2, length2, 33);
                }
                textView.setText(spannableStringBuilder);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                textView.setHighlightColor(Color.parseColor(StubApp.getString2(14142)));
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.6.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onCancel();
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.6.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setOutCancel(false).show(fragmentActivity.getSupportFragmentManager());
    }

    public static void showDeleteDialog(FragmentActivity fragmentActivity, final String str, final String str2, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.dialog_delete).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.7
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                String str3 = str2;
                if (str3 != null && !str3.isEmpty()) {
                    viewHolder.setText(R.id.tv_sure, str2);
                }
                viewHolder.setText(R.id.tv_title, str);
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.7.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onCancel();
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.7.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setOutCancel(false).show(fragmentActivity.getSupportFragmentManager());
    }

    public static void showNormalDialog(FragmentActivity fragmentActivity, final String str, final String str2, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.dialog_normal).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.8
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                String str3 = str2;
                if (str3 != null && !str3.isEmpty()) {
                    viewHolder.setText(R.id.tv_sure, str2);
                }
                viewHolder.setText(R.id.tv_title, str);
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.8.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onCancel();
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.8.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setOutCancel(false).show(fragmentActivity.getSupportFragmentManager());
    }

    public static void showNormalDialog(FragmentActivity fragmentActivity, final String str, final String str2, final String str3, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.dialog_normal).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.9
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                String str4 = str2;
                if (str4 != null && !str4.isEmpty()) {
                    viewHolder.setText(R.id.tv_sure, str2);
                }
                String str5 = str3;
                if (str5 != null && !str5.isEmpty()) {
                    viewHolder.setText(R.id.tv_cancel, str3);
                }
                viewHolder.setText(R.id.tv_title, str);
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.9.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onCancel();
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.9.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setOutCancel(false).show(fragmentActivity.getSupportFragmentManager());
    }

    public static void showWaterPumpDialog(FragmentActivity fragmentActivity, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.dialog_water_pump).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.10
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.10.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onCancel();
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.10.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setOutCancel(false).show(fragmentActivity.getSupportFragmentManager());
    }

    public static void showWeakPwdDialog(FragmentActivity fragmentActivity, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.dialog_weak_pwd).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.11
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.11.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setOutCancel(false).show(fragmentActivity.getSupportFragmentManager());
    }

    public static void showRenameDialog(FragmentActivity fragmentActivity, final String str, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.show_rename).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.12
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                final EditText editText = (EditText) viewHolder.getView(R.id.et_rename);
                editText.setText(str);
                editText.setFilters(new InputFilter[]{new EmojiFilter(), new InputFilter.LengthFilter(15)});
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.12.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onCancel();
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.12.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (editText.getText().toString().isEmpty()) {
                            BaseUtils.showShortToast(StubApp.getString2(14134));
                            return;
                        }
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure(editText.getText().toString());
                        }
                    }
                });
            }
        }).setOutCancel(false).show(fragmentActivity.getSupportFragmentManager());
    }

    public static void showRateDialog(final FragmentActivity fragmentActivity) {
        NiceDialog.init().setLayoutId(R.layout.dialog_rate).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.13
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setOnClickListener(R.id.tv_rate_good, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.13.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (DialogHelper.isHuawei()) {
                            DialogHelper.toHuaweiMarket(fragmentActivity);
                        } else {
                            DialogHelper.toMarket(fragmentActivity);
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_rate_bad, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.13.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        fragmentActivity.startActivity(new Intent((Context) fragmentActivity, (Class<?>) FeedBackActivity.class));
                    }
                });
                viewHolder.setOnClickListener(R.id.iv_close, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.13.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                    }
                });
            }
        }).setOutCancel(false).show(fragmentActivity.getSupportFragmentManager());
    }

    public static void showButlerTipDialog(FragmentActivity fragmentActivity) {
        NiceDialog.init().setLayoutId(R.layout.dialog_butler_tip).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.14
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setOnClickListener(R.id.rl_root_view, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.14.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                    }
                });
            }
        }).setOutCancel(true).show(fragmentActivity.getSupportFragmentManager());
    }

    public static void toMarket(Context context) {
        try {
            Uri uri = Uri.parse(StubApp.getString2("14129"));
            if (ChannelUtil.isOversea()) {
                uri = Uri.parse(StubApp.getString2("14157"));
            }
            Intent intent = new Intent(StubApp.getString2("4900"), uri);
            intent.addFlags(CommonNetImpl.FLAG_AUTH);
            context.startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(context, StubApp.getString2(14156), 0);
        }
    }

    public static void toHuaweiMarket(Context context) {
        try {
            Intent intent = new Intent(StubApp.getString2("14152"));
            intent.putExtra(StubApp.getString2("14153"), StubApp.getString2("14154"));
            intent.setPackage(StubApp.getString2("14155"));
            context.startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(context, StubApp.getString2(14156), 0);
        }
    }

    public static boolean isHuawei() {
        String lowerCase = Build.BRAND != null ? Build.BRAND.toLowerCase() : "";
        String lowerCase2 = Build.MANUFACTURER != null ? Build.MANUFACTURER.toLowerCase() : "";
        String string2 = StubApp.getString2(6944);
        return lowerCase.contains(string2) || lowerCase2.contains(string2);
    }

    public static void showPushMsgDialog(final FragmentActivity fragmentActivity, final String str, final String str2, final String str3) {
        NiceDialog.init().setLayoutId(R.layout.dialog_push_msg).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.15
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setOnClickListener(R.id.tv_know, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.15.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (str3 == null || str3.isEmpty()) {
                            return;
                        }
                        AgentWebActivity.INSTANCE.open((Context) fragmentActivity, str3, "");
                    }
                });
                TextView textView = (TextView) viewHolder.getView(R.id.tv_title);
                TextView textView2 = (TextView) viewHolder.getView(R.id.tv_content);
                textView.setText(str);
                textView2.setText(str2);
            }
        }).setOutCancel(true).show(fragmentActivity.getSupportFragmentManager());
    }

    public static void showCallCustomerServiceDialog(final BaseActivity baseActivity, final String str) {
        NiceDialog.init().setLayoutId(R.layout.customer_service_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.16
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.16.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                    }
                });
                ((TextView) viewHolder.getView(R.id.tv_content)).setText(str);
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.16.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        String string2 = StubApp.getString2(10628);
                        try {
                            baseNiceDialog.dismiss();
                            Intent intent = new Intent(StubApp.getString2("10629"), Uri.parse(string2 + str.replace(StubApp.getString2("1004"), "")));
                            if (ActivityCompat.checkSelfPermission(baseActivity, StubApp.getString2("10144")) != 0) {
                                DialogHelper.callCustomerServiceFailDialog(baseActivity);
                            } else {
                                baseActivity.startActivity(intent);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).setOutCancel(false).setWidth(290).show(baseActivity.getSupportFragmentManager());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void callCustomerServiceFailDialog(final BaseActivity baseActivity) {
        NiceDialog.init().setLayoutId(R.layout.customer_service_no_permission_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.17
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.17.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.17.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        new PermissionPageUtils(baseActivity).getAppDetailSettingIntent(baseActivity);
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.17.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                    }
                });
            }
        }).setOutCancel(false).setWidth(290).show(baseActivity.getSupportFragmentManager());
    }

    public static void showModifyDeviceNameDialog(BaseActivity baseActivity, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.modify_device_name_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.18
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(final ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.18.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onCancel();
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.18.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        EditText editText = (EditText) viewHolder.getView(R.id.ed_device_name);
                        if (onDialogListener != null) {
                            onDialogListener.onSure("" + editText.getText().toString());
                        }
                    }
                });
            }
        }).setOutCancel(false).setMargin(35).show(baseActivity.getSupportFragmentManager());
    }

    public static void showCleanAccountDialog(final BaseActivity baseActivity, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.clean_account_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.19
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(final ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.19.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onCancel();
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.19.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        EditText editText = (EditText) viewHolder.getView(R.id.ed_password);
                        if (TextUtils.isEmpty(editText.getText().toString())) {
                            BaseUtils.showShortToast(baseActivity, StubApp.getString2(14092));
                            return;
                        }
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("" + editText.getText().toString());
                        }
                    }
                });
            }
        }).setOutCancel(false).setWidth(290).show(baseActivity.getSupportFragmentManager());
    }

    public void showOtaDialog(BaseActivity baseActivity, IWifiOtaDoneListener iWifiOtaDoneListener) {
        NiceDialog.init().setLayoutId(R.layout.link_wifi_ota_dialog_layout).setConvertListener(new AnonymousClass20(new AnimationDrawable(), baseActivity, new boolean[]{false}, new int[]{120}, iWifiOtaDoneListener)).setOutCancel(false).show(baseActivity.getSupportFragmentManager());
    }

    /* renamed from: com.deye.helper.DialogHelper$20, reason: invalid class name */
    class AnonymousClass20 extends ViewConvertListener {
        final /* synthetic */ BaseActivity val$activity;
        final /* synthetic */ AnimationDrawable val$animationDrawable;
        final /* synthetic */ int[] val$count;
        final /* synthetic */ IWifiOtaDoneListener val$iWifiOtaDoneListener;
        final /* synthetic */ boolean[] val$isStopLoop;

        AnonymousClass20(AnimationDrawable animationDrawable, BaseActivity baseActivity, boolean[] zArr, int[] iArr, IWifiOtaDoneListener iWifiOtaDoneListener) {
            this.val$animationDrawable = animationDrawable;
            this.val$activity = baseActivity;
            this.val$isStopLoop = zArr;
            this.val$count = iArr;
            this.val$iWifiOtaDoneListener = iWifiOtaDoneListener;
        }

        @Override // com.othershe.nicedialog.ViewConvertListener
        public void convertView(ViewHolder viewHolder, BaseNiceDialog baseNiceDialog) {
            ImageView imageView = (ImageView) viewHolder.getView(R.id.iv_loop);
            try {
                AnimationDrawable animationDrawable = this.val$animationDrawable;
                if (animationDrawable != null) {
                    animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00000), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00001), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00002), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00003), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00004), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00005), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00006), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00007), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00008), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00009), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00010), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00011), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00012), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00013), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00014), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00015), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00016), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00017), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00018), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00019), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00020), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00021), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00022), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00023), 100);
                    this.val$animationDrawable.addFrame(this.val$activity.getResources().getDrawable(R.drawable.firmwear_00024), 100);
                    imageView.setImageDrawable(this.val$animationDrawable);
                }
                AnimationDrawable animationDrawable2 = this.val$animationDrawable;
                if (animationDrawable2 != null && !animationDrawable2.isRunning()) {
                    this.val$animationDrawable.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            final TextView textView = (TextView) viewHolder.getView(R.id.tv_timer);
            new Thread(new Runnable() { // from class: com.deye.helper.DialogHelper.20.1
                @Override // java.lang.Runnable
                public void run() throws InterruptedException {
                    while (!AnonymousClass20.this.val$isStopLoop[0]) {
                        AnonymousClass20.this.val$activity.runOnUiThread(new Runnable() { // from class: com.deye.helper.DialogHelper.20.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                textView.setText("" + AnonymousClass20.this.val$count[0] + StubApp.getString2(1740));
                            }
                        });
                        try {
                            Thread.sleep(1000L);
                            int[] iArr = AnonymousClass20.this.val$count;
                            iArr[0] = iArr[0] - 1;
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                        if (AnonymousClass20.this.val$count[0] <= 0) {
                            if (AnonymousClass20.this.val$iWifiOtaDoneListener != null) {
                                AnonymousClass20.this.val$iWifiOtaDoneListener.onWifiOtaDoneListener();
                            }
                            try {
                                if (AnonymousClass20.this.val$animationDrawable != null && AnonymousClass20.this.val$animationDrawable.isRunning()) {
                                    AnonymousClass20.this.val$animationDrawable.stop();
                                }
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                            AnonymousClass20.this.val$isStopLoop[0] = true;
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    public static void twoBtnDialog(BaseActivity baseActivity, final boolean z, final String str, final String str2, final String str3, final String str4, final OnDialogListener onDialogListener) {
        if (baseActivity.isDestroyed() || baseActivity.isFinishing()) {
            return;
        }
        FragmentManager supportFragmentManager = baseActivity.getSupportFragmentManager();
        if (supportFragmentManager.isDestroyed()) {
            return;
        }
        NiceDialog.init().setLayoutId(R.layout.link_wifi_dialog_layout).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.21
            @Override // com.othershe.nicedialog.ViewConvertListener
            public void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                if (!TextUtils.isEmpty(str)) {
                    viewHolder.setText(R.id.tv_title, str);
                }
                viewHolder.setText(R.id.tv_cancel, str2);
                viewHolder.setText(R.id.tv_content, str4);
                viewHolder.setText(R.id.tv_sure, str3);
                if ("".equals(str2)) {
                    viewHolder.getView(R.id.tv_cancel).setVisibility(8);
                }
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.21.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onCancel();
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.21.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
                if (z && "".equals(str2)) {
                    viewHolder.setOnClickListener(R.id.ll_root_layout, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.21.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            System.out.println(StubApp.getString2(14135));
                            baseNiceDialog.dismiss();
                            if (onDialogListener != null) {
                                onDialogListener.onSure("");
                            }
                        }
                    });
                }
            }
        }).setOutCancel(z).show(supportFragmentManager);
    }

    public static void twoBtnDialog(BaseActivity baseActivity, boolean z, String str, String str2, String str3, OnDialogListener onDialogListener) {
        twoBtnDialog(baseActivity, z, "", str, str2, str3, onDialogListener);
    }

    public static void showBluetoothDialog(BaseActivity baseActivity, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.bluetooth_enable_dialog_layout).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.22
            @Override // com.othershe.nicedialog.ViewConvertListener
            public void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.22.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setOutCancel(false).show(baseActivity.getSupportFragmentManager());
    }

    public static void showScanQrTipDialog(BaseActivity baseActivity, final String str, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.link_wifi_dialog_layout).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.23
            @Override // com.othershe.nicedialog.ViewConvertListener
            public void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setText(R.id.tv_cancel, StubApp.getString2(14136));
                viewHolder.setText(R.id.tv_content, str);
                viewHolder.setText(R.id.tv_sure, StubApp.getString2(14137));
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.23.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onCancel();
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.23.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setWidth(290).setOutCancel(false).show(baseActivity.getSupportFragmentManager());
    }

    public static void showLocationDialog(final BaseActivity baseActivity) {
        NiceDialog.init().setLayoutId(R.layout.open_lacation_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.24
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.24.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.24.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        new PermissionPageUtils(baseActivity).getAppDetailSettingIntent(baseActivity);
                    }
                });
            }
        }).setOutCancel(false).setWidth(290).show(baseActivity.getSupportFragmentManager());
    }

    public static void showStopConfigNetDialog(BaseActivity baseActivity, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.stop_config_net_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.25
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.25.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.25.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setOutCancel(false).setWidth(290).show(baseActivity.getSupportFragmentManager());
    }

    public static void selectHeadPortraitDialog(BaseActivity baseActivity, final OnSelectHeadPortraitListsner onSelectHeadPortraitListsner) {
        NiceDialog.init().setLayoutId(R.layout.select_head_portrait_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.26
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setOnClickListener(R.id.rl_root, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.26.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_camera, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.26.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onSelectHeadPortraitListsner != null) {
                            onSelectHeadPortraitListsner.onSelectedItem(OnSelectHeadPortraitListsner.SelectItem.CAMERA);
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_album, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.26.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onSelectHeadPortraitListsner != null) {
                            onSelectHeadPortraitListsner.onSelectedItem(OnSelectHeadPortraitListsner.SelectItem.ALBUM);
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.26.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onSelectHeadPortraitListsner != null) {
                            onSelectHeadPortraitListsner.onSelectedItem(OnSelectHeadPortraitListsner.SelectItem.CANCEL);
                        }
                    }
                });
            }
        }).setOutCancel(false).setShowBottom(true).show(baseActivity.getSupportFragmentManager());
    }

    public static void deleteSchedulerDialog(BaseActivity baseActivity, final OnDeleteTipBottomDialogListsner onDeleteTipBottomDialogListsner) {
        NiceDialog.init().setLayoutId(R.layout.delete_tip_bottom_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.27
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setOnClickListener(R.id.rl_root, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.27.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.27.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDeleteTipBottomDialogListsner != null) {
                            onDeleteTipBottomDialogListsner.onSelectedItem(OnDeleteTipBottomDialogListsner.SelectItem.SURE);
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.27.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDeleteTipBottomDialogListsner != null) {
                            onDeleteTipBottomDialogListsner.onSelectedItem(OnDeleteTipBottomDialogListsner.SelectItem.CANCEL);
                        }
                    }
                });
            }
        }).setOutCancel(false).setShowBottom(true).show(baseActivity.getSupportFragmentManager());
    }

    public static void deleteUserDialog(FragmentActivity fragmentActivity, final String str, final String str2, final OnDeleteTipBottomDialogListsner onDeleteTipBottomDialogListsner) {
        NiceDialog.init().setLayoutId(R.layout.delete_tip_bottom_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.28
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                ((TextView) viewHolder.getView(R.id.tv_title)).setText("" + str);
                ((TextView) viewHolder.getView(R.id.tv_sure)).setText("" + str2);
                viewHolder.setOnClickListener(R.id.rl_root, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.28.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.28.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDeleteTipBottomDialogListsner != null) {
                            onDeleteTipBottomDialogListsner.onSelectedItem(OnDeleteTipBottomDialogListsner.SelectItem.SURE);
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.28.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDeleteTipBottomDialogListsner != null) {
                            onDeleteTipBottomDialogListsner.onSelectedItem(OnDeleteTipBottomDialogListsner.SelectItem.CANCEL);
                        }
                    }
                });
            }
        }).setOutCancel(false).setShowBottom(true).show(fragmentActivity.getSupportFragmentManager());
    }

    public static void editSchedulerDialog(BaseActivity baseActivity, final String str, final String str2, final OnEditSchedulerListsner onEditSchedulerListsner) {
        NiceDialog.init().setLayoutId(R.layout.edit_scheduler_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.29
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                String str3;
                String str4;
                WheelPicker view = viewHolder.getView(R.id.wheelDayPicker_hour);
                WheelPicker view2 = viewHolder.getView(R.id.wheelDayPicker_min);
                view.setLayerType(1, (Paint) null);
                view2.setLayerType(1, (Paint) null);
                view2.setItemAlign(0);
                view.setData(BaseUtils.getHour());
                view2.setData(BaseUtils.getMin());
                Date date = new Date();
                final int[] iArr = {date.getHours()};
                final int[] iArr2 = {date.getMinutes()};
                if (!"".equals(str) && (str4 = str) != null) {
                    iArr[0] = Integer.parseInt(str4);
                }
                if (!"".equals(str2) && (str3 = str2) != null) {
                    iArr2[0] = Integer.parseInt(str3);
                }
                view.setSelectedItemPosition(iArr[0], false);
                view2.setSelectedItemPosition(iArr2[0], false);
                view.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.deye.helper.DialogHelper.29.1
                    public void onItemSelected(WheelPicker wheelPicker, Object obj, int i) {
                        iArr[0] = i % 24;
                    }
                });
                view2.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.deye.helper.DialogHelper.29.2
                    public void onItemSelected(WheelPicker wheelPicker, Object obj, int i) {
                        iArr2[0] = i % 60;
                    }
                });
                viewHolder.setOnClickListener(R.id.ll_root_view, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.29.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        baseNiceDialog.dismiss();
                        if (onEditSchedulerListsner != null) {
                            onEditSchedulerListsner.onSchedulerResult(iArr[0], iArr2[0]);
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.29.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        baseNiceDialog.dismiss();
                        if (onEditSchedulerListsner != null) {
                            onEditSchedulerListsner.onSchedulerResult(iArr[0], iArr2[0]);
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.29.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        baseNiceDialog.dismiss();
                    }
                });
            }
        }).setOutCancel(false).setShowBottom(true).show(baseActivity.getSupportFragmentManager());
    }

    public static void showUserOffLineDialog(final BaseActivity baseActivity, String str, final String str2, final OnDialogListener onDialogListener) {
        try {
            NiceDialog.init().setLayoutId(R.layout.open_lacation_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.30
                @Override // com.othershe.nicedialog.ViewConvertListener
                protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) throws Resources.NotFoundException {
                    viewHolder.setText(R.id.tv_title, baseActivity.getResources().getString(R.string.login_abnormal));
                    String string = baseActivity.getResources().getString(R.string.offline_tip_content);
                    if (!TextUtils.isEmpty(str2)) {
                        string = str2;
                    }
                    viewHolder.setText(R.id.tv_content, string);
                    viewHolder.setText(R.id.tv_sure, baseActivity.getResources().getString(R.string.sure_text));
                    viewHolder.getView(R.id.tv_cancel).setVisibility(8);
                    viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.30.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            baseNiceDialog.dismiss();
                            if (onDialogListener != null) {
                                onDialogListener.onCancel();
                            }
                        }
                    });
                    viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.30.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            baseNiceDialog.dismiss();
                            if (onDialogListener != null) {
                                onDialogListener.onSure("");
                            }
                        }
                    });
                }
            }).setOutCancel(false).setWidth(290).show(baseActivity.getSupportFragmentManager());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deviceUnboundNoticeDialog(final BaseActivity baseActivity, final String str, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.open_lacation_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.31
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setText(R.id.tv_title, baseActivity.getResources().getString(R.string.device_unbound_hint_title));
                viewHolder.setText(R.id.tv_content, str);
                viewHolder.setText(R.id.tv_sure, baseActivity.getResources().getString(R.string.sure_text));
                viewHolder.getView(R.id.tv_cancel).setVisibility(8);
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.31.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onCancel();
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.31.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setOutCancel(false).setMargin(35).show(baseActivity.getSupportFragmentManager());
    }

    public static void updateVersionDialog(final FragmentActivity fragmentActivity, final String[] strArr, final Boolean bool, final View.OnClickListener onClickListener) {
        NiceDialog.init().setLayoutId(R.layout.update_version_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.32
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                if (bool.booleanValue()) {
                    viewHolder.getConvertView().findViewById(R.id.tv_cancel).setVisibility(8);
                }
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.32.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onClickListener != null) {
                            onClickListener.onClick(view);
                        }
                    }
                });
                RecyclerView view = viewHolder.getView(R.id.ry_content);
                TextView textView = (TextView) viewHolder.getView(R.id.tv_content);
                TextView textView2 = (TextView) viewHolder.getView(R.id.tv_title);
                if (strArr != null) {
                    textView2.setVisibility(0);
                    textView.setVisibility(8);
                    view.setVisibility(0);
                    view.setLayoutManager(new LinearLayoutManager(fragmentActivity));
                    view.setAdapter(new AppUpgradeContentAdapter((Activity) Objects.requireNonNull(fragmentActivity), strArr, view));
                } else {
                    textView2.setVisibility(8);
                    textView.setVisibility(0);
                    view.setVisibility(8);
                }
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.32.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        AppUpgradeHelper.INSTANCE.downLoadApk(fragmentActivity);
                        baseNiceDialog.dismiss();
                    }
                });
            }
        }).setOutCancel(false).setWidth(290).setHeight(350).show(((FragmentActivity) Objects.requireNonNull(fragmentActivity)).getSupportFragmentManager());
    }

    public static void wifiPasswordHintDialog(FragmentActivity fragmentActivity, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.link_wifi_dialog_layout).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.33
            @Override // com.othershe.nicedialog.ViewConvertListener
            public void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.getView(R.id.tv_title).setVisibility(0);
                viewHolder.setText(R.id.tv_cancel, StubApp.getString2(13727));
                viewHolder.setText(R.id.tv_content, StubApp.getString2(14139));
                viewHolder.setText(R.id.tv_sure, StubApp.getString2(14140));
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.33.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.33.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setWidth(290).setOutCancel(false).show(fragmentActivity.getSupportFragmentManager());
    }

    public static void requestPermissionDialog(final FragmentActivity fragmentActivity, final String str, final String str2, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.open_lacation_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.34
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setText(R.id.tv_title, str);
                viewHolder.setText(R.id.tv_content, str2);
                viewHolder.setText(R.id.tv_sure, fragmentActivity.getResources().getString(R.string.agree));
                viewHolder.setText(R.id.tv_cancel, fragmentActivity.getResources().getString(R.string.disagree));
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.34.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onCancel();
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.34.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setOutCancel(false).setMargin(35).show(fragmentActivity.getSupportFragmentManager());
    }

    public static void permissionDeniedHint(final FragmentActivity fragmentActivity, final String str, final OnDialogListener onDialogListener) throws Resources.NotFoundException {
        final String string = fragmentActivity.getResources().getString(R.string.permission_denied_title);
        NiceDialog.init().setLayoutId(R.layout.open_lacation_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.35
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setText(R.id.tv_title, string);
                viewHolder.setText(R.id.tv_content, str);
                viewHolder.setText(R.id.tv_sure, fragmentActivity.getResources().getString(R.string.route_to_app_detail));
                viewHolder.setText(R.id.tv_cancel, fragmentActivity.getResources().getString(R.string.cancel_text));
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.35.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onCancel();
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.35.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setOutCancel(false).setMargin(35).show(fragmentActivity.getSupportFragmentManager());
    }

    public static void switchOffNotificationDialog(final BaseActivity baseActivity, final String str, final String str2, final OnDialogListener onDialogListener) {
        NiceDialog.init().setLayoutId(R.layout.open_lacation_dialog).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.36
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                viewHolder.setText(R.id.tv_title, str2);
                viewHolder.setText(R.id.tv_content, str);
                viewHolder.setText(R.id.tv_sure, baseActivity.getResources().getString(R.string.agree));
                viewHolder.setText(R.id.tv_cancel, baseActivity.getResources().getString(R.string.disagree));
                viewHolder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.36.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onCancel();
                        }
                    }
                });
                viewHolder.setOnClickListener(R.id.tv_sure, new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.36.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        baseNiceDialog.dismiss();
                        if (onDialogListener != null) {
                            onDialogListener.onSure("");
                        }
                    }
                });
            }
        }).setOutCancel(false).setMargin(35).show(baseActivity.getSupportFragmentManager());
    }

    public static void popupUserInfoChoiceDialog(BaseActivity baseActivity, final List<String> list, final String str, final IUserInfoChoice iUserInfoChoice) {
        NiceDialog.init().setLayoutId(R.layout.user_info_fill_layout).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.37
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                final int[] iArr = {0};
                WheelPicker view = viewHolder.getView(R.id.wheel_user_info_choice);
                TextView textView = (TextView) viewHolder.getView(R.id.tv_cancel);
                ((TextView) viewHolder.getView(2131363004)).setText(str);
                TextView textView2 = (TextView) viewHolder.getView(R.id.tv_sure);
                view.setData(list);
                view.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.deye.helper.DialogHelper.37.1
                    public void onItemSelected(WheelPicker wheelPicker, Object obj, int i) {
                        iArr[0] = i;
                    }
                });
                view.setOnWheelChangeListener(new WheelPicker.OnWheelChangeListener() { // from class: com.deye.helper.DialogHelper.37.2
                    public void onWheelScrollStateChanged(int i) {
                    }

                    public void onWheelScrolled(int i) {
                    }

                    public void onWheelSelected(int i) {
                        iArr[0] = i;
                    }
                });
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.37.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        BaseNiceDialog baseNiceDialog2 = baseNiceDialog;
                        if (baseNiceDialog2 == null) {
                            return;
                        }
                        baseNiceDialog2.dismissAllowingStateLoss();
                    }
                });
                textView2.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.37.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        BaseNiceDialog baseNiceDialog2 = baseNiceDialog;
                        if (baseNiceDialog2 == null) {
                            return;
                        }
                        baseNiceDialog2.dismissAllowingStateLoss();
                        if (iUserInfoChoice != null) {
                            iUserInfoChoice.onUserInfoChoice(iArr[0]);
                        }
                    }
                });
            }
        }).setShowBottom(true).show(baseActivity.getSupportFragmentManager());
    }

    public static void popupUserAgeChoiceDialog(BaseActivity baseActivity, final String str, final IUserInfoChoice iUserInfoChoice) {
        NiceDialog.init().setLayoutId(R.layout.user_info_age_fill_layout).setConvertListener(new ViewConvertListener() { // from class: com.deye.helper.DialogHelper.38
            @Override // com.othershe.nicedialog.ViewConvertListener
            protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
                final int[] iArr = {0};
                viewHolder.getView(R.id.wheel_user_info_choice);
                TextView textView = (TextView) viewHolder.getView(R.id.tv_cancel);
                ((TextView) viewHolder.getView(2131363004)).setText(str);
                TextView textView2 = (TextView) viewHolder.getView(R.id.tv_sure);
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.38.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        BaseNiceDialog baseNiceDialog2 = baseNiceDialog;
                        if (baseNiceDialog2 == null) {
                            return;
                        }
                        baseNiceDialog2.dismissAllowingStateLoss();
                    }
                });
                textView2.setOnClickListener(new View.OnClickListener() { // from class: com.deye.helper.DialogHelper.38.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        BaseNiceDialog baseNiceDialog2 = baseNiceDialog;
                        if (baseNiceDialog2 == null) {
                            return;
                        }
                        baseNiceDialog2.dismissAllowingStateLoss();
                        if (iUserInfoChoice != null) {
                            iUserInfoChoice.onUserInfoChoice(iArr[0]);
                        }
                    }
                });
            }
        }).setShowBottom(true).show(baseActivity.getSupportFragmentManager());
    }

    public static void requestPermissionForCamera(final FragmentActivity fragmentActivity) {
        LogUtil.d(StubApp.getString2(14151), StubApp.getString2(14150) + fragmentActivity);
        if (fragmentActivity == null) {
            return;
        }
        if (XXPermissions.isGranted(fragmentActivity, new String[]{StubApp.getString2(10138)})) {
            fragmentActivity.startActivity(new Intent((Context) fragmentActivity, (Class<?>) DeviceScanQRActivity.class));
        } else {
            requestPermissionDialog(fragmentActivity, fragmentActivity.getString(R.string.request_permission_dialog_title), fragmentActivity.getString(R.string.camera_permission_hint), new OnDialogListener() { // from class: com.deye.helper.DialogHelper.39
                @Override // com.deye.helper.DialogHelper.OnDialogListener
                public void onCancel() {
                }

                @Override // com.deye.helper.DialogHelper.OnDialogListener
                public void onSure(String str) {
                    XXPermissions.with(fragmentActivity).permission(StubApp.getString2(10138)).request(new OnPermissionCallback() { // from class: com.deye.helper.DialogHelper.39.1
                        public void onGranted(List<String> list, boolean z) {
                            fragmentActivity.startActivity(new Intent((Context) fragmentActivity, (Class<?>) DeviceScanQRActivity.class));
                        }

                        public void onDenied(List<String> list, boolean z) throws Resources.NotFoundException {
                            if (z) {
                                DialogHelper.permissionDeniedHint(fragmentActivity, fragmentActivity.getResources().getString(R.string.camera) + fragmentActivity.getResources().getString(R.string.camera_denied_content), new OnDialogListener() { // from class: com.deye.helper.DialogHelper.39.1.1
                                    @Override // com.deye.helper.DialogHelper.OnDialogListener
                                    public void onSure(String str2) {
                                        ActivityRouterUtilsKt.goAppDetailSetting();
                                    }
                                });
                            }
                        }
                    });
                }
            });
        }
    }
}
