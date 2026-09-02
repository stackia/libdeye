package com.deye.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.credentials.CredentialManager;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialException;
import com.alibaba.fastjson.JSON;
import com.deye.activity.device.base.BaseActivity;
import com.deye.activity.mine.UserInfoActivity;
import com.deye.configs.Constants;
import com.deye.entity.GoogleInfoBean;
import com.deye.entity.UserInfoBean;
import com.deye.entity.WeChatInfoBean;
import com.deye.helper.DialogHelper;
import com.deye.helper.UserAvatarHelper;
import com.deye.helper.UserInfoAtyHelper;
import com.deye.utils.ActivityRouterUtilsKt;
import com.deye.utils.BaseUtils;
import com.deye.utils.MMKVUtils;
import com.hjq.permissions.OnPermissionCallback;
import com.mxchipapp.R;
import com.mxchipapp.databinding.PersonalCenterAtyBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.bean.LoginResult;
import io.fogcloud.sdk.fog.bean.ResponseDataBean;
import io.fogcloud.sdk.fog.bean.SimpleResultBean;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import io.fogcloud.sdk.fog.log.LogUtil;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/* compiled from: UserInfoActivity.kt */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 L2\u00020\u00012\u00020\u0002:\u0001LB\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\fH\u0003J\b\u0010 \u001a\u00020\u001eH\u0002J\u0010\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020\u001eH\u0002J\u0012\u0010(\u001a\u00020\u001e2\b\u0010\u001a\u001a\u0004\u0018\u00010\bH\u0002J\b\u0010)\u001a\u00020\u001eH\u0002J\b\u0010*\u001a\u00020\u001eH\u0003J\u0010\u0010+\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020-H\u0002J\"\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002002\b\u00102\u001a\u0004\u0018\u000103H\u0014J\u0010\u00104\u001a\u00020\u001e2\u0006\u00105\u001a\u000206H\u0016J\u0012\u00107\u001a\u00020\u001e2\b\u00108\u001a\u0004\u0018\u000109H\u0016J\b\u0010:\u001a\u00020\u001eH\u0014J\b\u0010;\u001a\u00020\u001eH\u0007J\b\u0010<\u001a\u00020\u001eH\u0014J\b\u0010=\u001a\u00020\u001eH\u0007J\u001b\u0010>\u001a\u00020\u001e2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\f0@H\u0002¢\u0006\u0002\u0010AJ\u001a\u0010B\u001a\u00020C2\b\u0010\u001a\u001a\u0004\u0018\u00010\b2\u0006\u0010D\u001a\u00020EH\u0002J\u0012\u0010F\u001a\u00020\u001e2\b\u0010G\u001a\u0004\u0018\u00010\fH\u0002J\b\u0010H\u001a\u00020\u001eH\u0002J\b\u0010I\u001a\u00020\u001eH\u0002J\u0006\u0010J\u001a\u00020\u001eJ\b\u0010K\u001a\u00020\u001eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lcom/deye/activity/mine/UserInfoActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "Landroid/view/View$OnClickListener;", "()V", "credentialManager", "Landroidx/credentials/CredentialManager;", "launcher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/net/Uri;", "mCropPhotoResultUri", "mEducationList", "", "", "mMode", "getMMode", "()Ljava/lang/String;", "setMMode", "(Ljava/lang/String;)V", "mPersonalCenterAtyBinding", "Lcom/mxchipapp/databinding/PersonalCenterAtyBinding;", "mUserAgeList", "mUserGenderList", "mUserInfoAtyHelper", "Lcom/deye/helper/UserInfoAtyHelper;", "photoPickerLauncher", "Landroidx/activity/result/PickVisualMediaRequest;", "uri", "userInfo", "Lcom/deye/entity/UserInfoBean;", "bindWithGoogleToken", "", "idToken", "doGoogleBind", "getTime", "date", "Ljava/util/Date;", "handleGoogleBindResult", "result", "Landroidx/credentials/GetCredentialResponse;", "initListener", "initUCrop", "initUserInfoDataList", "initView", "logoutFail", "isKillApp", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onGoogleOpt", "onResume", "onWeChatOpt", "requestPermissionCamera", "permissions", "", "([Ljava/lang/String;)V", "roadImageView", "Ljava/io/File;", "imageView", "Landroid/widget/ImageView;", "setHeadPortrait", "imgUrl", "showGenderPicker", "showTimePicker", "verifyStoragePicPermissions", "verifyStorageTakePhotoPermissions", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class UserInfoActivity extends BaseActivity implements View.OnClickListener {
    public static final String FILL_USER_INFO = StubApp.getString2(13247);
    public static final String SHOW_USER_INFO = StubApp.getString2(13771);
    public static final String TAG = StubApp.getString2(13764);
    private static final String WEB_CLIENT_ID = StubApp.getString2(13772);
    private CredentialManager credentialManager;
    private ActivityResultLauncher<Uri> launcher;
    private Uri mCropPhotoResultUri;
    private List<String> mEducationList;
    private PersonalCenterAtyBinding mPersonalCenterAtyBinding;
    private List<String> mUserAgeList;
    private List<String> mUserGenderList;
    private UserInfoAtyHelper mUserInfoAtyHelper;
    private Uri uri;
    private UserInfoBean userInfo;
    private String mMode = StubApp.getString2(13771);
    private final ActivityResultLauncher<PickVisualMediaRequest> photoPickerLauncher = registerForActivityResult((ActivityResultContract) new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback() { // from class: com.deye.activity.mine.UserInfoActivity$$ExternalSyntheticLambda6
        public final void onActivityResult(Object obj) {
            UserInfoActivity.photoPickerLauncher$lambda$0(this.f$0, (Uri) obj);
        }
    });

    static {
        StubApp.interface11(14486);
        INSTANCE = new Companion(null);
    }

    private final native void bindWithGoogleToken(String idToken);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void bindWithGoogleToken$lambda$8(UserInfoActivity userInfoActivity);

    private final native void doGoogleBind();

    /* JADX INFO: Access modifiers changed from: private */
    public final native String getTime(Date date);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void handleGoogleBindResult(GetCredentialResponse result);

    private final native void initListener();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initListener$lambda$10(UserInfoActivity userInfoActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initListener$lambda$9(UserInfoActivity userInfoActivity, View view);

    private final native void initUCrop(Uri uri);

    private final native void initUserInfoDataList();

    private final native void initView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$2(UserInfoActivity userInfoActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$3(UserInfoActivity userInfoActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$4(UserInfoActivity userInfoActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$5(UserInfoActivity userInfoActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$6(UserInfoActivity userInfoActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void initView$lambda$7(UserInfoActivity userInfoActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void logoutFail(boolean isKillApp);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onClick$lambda$16(UserInfoActivity userInfoActivity, DialogHelper.OnSelectHeadPortraitListsner.SelectItem selectItem);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onCreate$lambda$1(UserInfoActivity userInfoActivity, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void photoPickerLauncher$lambda$0(UserInfoActivity userInfoActivity, Uri uri);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void requestPermissionCamera(String[] permissions);

    private final native File roadImageView(Uri uri, ImageView imageView);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void setHeadPortrait(String imgUrl);

    private final native void showGenderPicker();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void showGenderPicker$lambda$14(UserInfoActivity userInfoActivity, int i, int i2, int i3, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void showGenderPicker$lambda$15(int i, int i2, int i3);

    private final native void showTimePicker();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void showTimePicker$lambda$11(UserInfoActivity userInfoActivity, Date date, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void showTimePicker$lambda$12(Date date);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void showTimePicker$lambda$13(View view);

    private final native void verifyStorageTakePhotoPermissions();

    public final native String getMMode();

    protected native void onActivityResult(int requestCode, int resultCode, Intent data);

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();

    public final native void onGoogleOpt();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onResume();

    public final native void onWeChatOpt();

    public final native void setMMode(String str);

    public final native void verifyStoragePicPermissions();

    /* compiled from: UserInfoActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.UserInfoActivity$onWeChatOpt$1, reason: invalid class name and case insensitive filesystem */
    static final class C01771 extends Lambda implements Function1<Map<String, String>, Unit> {
        C01771() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Map<String, String> map) {
            invoke2(map);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Map<String, String> it2) {
            Intrinsics.checkNotNullParameter(it2, "it");
            String str = it2.get(StubApp.getString2(936));
            if (str == null) {
                str = "";
            }
            String str2 = it2.get(StubApp.getString2(13770));
            String str3 = str2 != null ? str2 : "";
            UserInfoActivity userInfoActivity = UserInfoActivity.this;
            userInfoActivity.showLoading((Context) userInfoActivity.mContext, UserInfoActivity.this.getString(R.string.binding));
            Flowable<BaseResult<SimpleResultBean>> flowableObserveOn = RetrofitManager.INSTANCE.getApiService().weChatBind(str, str3).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            final UserInfoActivity userInfoActivity2 = UserInfoActivity.this;
            Flowable<BaseResult<SimpleResultBean>> flowableDoOnComplete = flowableObserveOn.doOnComplete(new Action() { // from class: com.deye.activity.mine.UserInfoActivity$onWeChatOpt$1$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    UserInfoActivity.C01771.invoke$lambda$0(userInfoActivity2);
                }
            });
            final UserInfoActivity userInfoActivity3 = UserInfoActivity.this;
            flowableDoOnComplete.subscribe(new Consumer() { // from class: com.deye.activity.mine.UserInfoActivity.onWeChatOpt.1.2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(BaseResult<SimpleResultBean> it3) {
                    Intrinsics.checkNotNullParameter(it3, "it");
                    BaseResult.MetaBean meta = it3.getMeta();
                    PersonalCenterAtyBinding personalCenterAtyBinding = null;
                    if (meta != null && meta.getCode() == 0) {
                        PersonalCenterAtyBinding personalCenterAtyBinding2 = userInfoActivity3.mPersonalCenterAtyBinding;
                        if (personalCenterAtyBinding2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mPersonalCenterAtyBinding");
                        } else {
                            personalCenterAtyBinding = personalCenterAtyBinding2;
                        }
                        personalCenterAtyBinding.tvWechat.setText(userInfoActivity3.getString(R.string.bound));
                        UserInfoBean userInfoBean = userInfoActivity3.userInfo;
                        if (userInfoBean == null) {
                            return;
                        }
                        userInfoBean.setWechat_info(new WeChatInfoBean());
                        return;
                    }
                    BaseResult.MetaBean meta2 = it3.getMeta();
                    BaseUtils.showShortToast(String.valueOf(meta2 != null ? meta2.getMessage() : null));
                }
            }, new Consumer() { // from class: com.deye.activity.mine.UserInfoActivity.onWeChatOpt.1.3
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Throwable it3) {
                    Intrinsics.checkNotNullParameter(it3, "it");
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(UserInfoActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.hideLoading();
        }
    }

    /* compiled from: UserInfoActivity.kt */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/deye/activity/mine/UserInfoActivity$onWeChatOpt$2", "Lcom/deye/helper/DialogHelper$OnDialogListener;", "onSure", "", "text", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.UserInfoActivity$onWeChatOpt$2, reason: invalid class name and case insensitive filesystem */
    public static final class C01782 extends DialogHelper.OnDialogListener {
        C01782() {
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String text) {
            UserInfoActivity userInfoActivity = UserInfoActivity.this;
            userInfoActivity.showLoading((Context) userInfoActivity.mContext, UserInfoActivity.this.getString(R.string.unbind_in_progress));
            Flowable<BaseResult<SimpleResultBean>> flowableObserveOn = RetrofitManager.INSTANCE.getApiService().weChatUnBind().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            final UserInfoActivity userInfoActivity2 = UserInfoActivity.this;
            Flowable<BaseResult<SimpleResultBean>> flowableDoOnComplete = flowableObserveOn.doOnComplete(new Action() { // from class: com.deye.activity.mine.UserInfoActivity$onWeChatOpt$2$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    UserInfoActivity.C01782.onSure$lambda$0(userInfoActivity2);
                }
            });
            final UserInfoActivity userInfoActivity3 = UserInfoActivity.this;
            flowableDoOnComplete.subscribe(new Consumer() { // from class: com.deye.activity.mine.UserInfoActivity$onWeChatOpt$2$onSure$2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(BaseResult<SimpleResultBean> it2) {
                    Object message;
                    Intrinsics.checkNotNullParameter(it2, "it");
                    BaseResult.MetaBean meta = it2.getMeta();
                    String string = null;
                    if (meta != null && meta.getCode() == 0) {
                        PersonalCenterAtyBinding personalCenterAtyBinding = userInfoActivity3.mPersonalCenterAtyBinding;
                        if (personalCenterAtyBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mPersonalCenterAtyBinding");
                            personalCenterAtyBinding = null;
                        }
                        personalCenterAtyBinding.tvWechat.setText(userInfoActivity3.getString(R.string.unbound));
                        UserInfoBean userInfoBean = userInfoActivity3.userInfo;
                        if (userInfoBean == null) {
                            return;
                        }
                        userInfoBean.setWechat_info(null);
                        return;
                    }
                    BaseResult.MetaBean meta2 = it2.getMeta();
                    if (meta2 != null && (message = meta2.getMessage()) != null) {
                        string = message.toString();
                    }
                    BaseUtils.showShortToast(string);
                }
            }, new Consumer() { // from class: com.deye.activity.mine.UserInfoActivity$onWeChatOpt$2$onSure$3
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Throwable it2) {
                    Intrinsics.checkNotNullParameter(it2, "it");
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSure$lambda$0(UserInfoActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.hideLoading();
        }
    }

    /* compiled from: UserInfoActivity.kt */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/deye/activity/mine/UserInfoActivity$onGoogleOpt$1", "Lcom/deye/helper/DialogHelper$OnDialogListener;", "onSure", "", "text", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.UserInfoActivity$onGoogleOpt$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01761 extends DialogHelper.OnDialogListener {
        C01761() {
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String text) {
            UserInfoActivity userInfoActivity = UserInfoActivity.this;
            userInfoActivity.showLoading((Context) userInfoActivity.mContext, UserInfoActivity.this.getString(R.string.unbind_in_progress));
            String jSONString = JSON.toJSONString(MapsKt.emptyMap());
            RequestBody.Companion companion = RequestBody.Companion;
            MediaType mediaType = Constants.JSON_Type;
            Intrinsics.checkNotNull(jSONString);
            Flowable<BaseResult<LoginResult>> flowableObserveOn = RetrofitManager.INSTANCE.getApiService().googleUnBind(companion.create(mediaType, jSONString)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            final UserInfoActivity userInfoActivity2 = UserInfoActivity.this;
            Flowable<BaseResult<LoginResult>> flowableDoOnComplete = flowableObserveOn.doOnComplete(new Action() { // from class: com.deye.activity.mine.UserInfoActivity$onGoogleOpt$1$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    UserInfoActivity.C01761.onSure$lambda$0(userInfoActivity2);
                }
            });
            final UserInfoActivity userInfoActivity3 = UserInfoActivity.this;
            Consumer<? super BaseResult<LoginResult>> consumer = new Consumer() { // from class: com.deye.activity.mine.UserInfoActivity$onGoogleOpt$1$onSure$2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(BaseResult<LoginResult> it2) {
                    String string;
                    Object message;
                    Intrinsics.checkNotNullParameter(it2, "it");
                    BaseResult.MetaBean meta = it2.getMeta();
                    if (meta != null && meta.getCode() == 0) {
                        PersonalCenterAtyBinding personalCenterAtyBinding = userInfoActivity3.mPersonalCenterAtyBinding;
                        String string2 = StubApp.getString2(13762);
                        if (personalCenterAtyBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            personalCenterAtyBinding = null;
                        }
                        personalCenterAtyBinding.tvGoogleId.setText(userInfoActivity3.getString(R.string.unbound));
                        PersonalCenterAtyBinding personalCenterAtyBinding2 = userInfoActivity3.mPersonalCenterAtyBinding;
                        if (personalCenterAtyBinding2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(string2);
                            personalCenterAtyBinding2 = null;
                        }
                        personalCenterAtyBinding2.tvGoogleId.setTextColor(userInfoActivity3.getColor(R.color.dark40));
                        UserInfoBean userInfoBean = userInfoActivity3.userInfo;
                        if (userInfoBean != null) {
                            userInfoBean.setGoogle_info(null);
                        }
                        MMKVUtils.INSTANCE.setUserInfo(userInfoActivity3.userInfo);
                        BaseUtils.showShortToast((Context) userInfoActivity3.mContext, userInfoActivity3.getString(R.string.google_unbind_success));
                        return;
                    }
                    Context context = (Context) userInfoActivity3.mContext;
                    BaseResult.MetaBean meta2 = it2.getMeta();
                    if (meta2 == null || (message = meta2.getMessage()) == null || (string = message.toString()) == null) {
                        string = userInfoActivity3.getString(R.string.google_unbind_failed);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    }
                    BaseUtils.showShortToast(context, string);
                }
            };
            final UserInfoActivity userInfoActivity4 = UserInfoActivity.this;
            flowableDoOnComplete.subscribe(consumer, new Consumer() { // from class: com.deye.activity.mine.UserInfoActivity$onGoogleOpt$1$onSure$3
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Throwable it2) {
                    Intrinsics.checkNotNullParameter(it2, "it");
                    BaseUtils.showShortToast((Context) userInfoActivity4.mContext, userInfoActivity4.getString(R.string.google_unbind_failed));
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSure$lambda$0(UserInfoActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.hideLoading();
        }
    }

    /* compiled from: UserInfoActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.deye.activity.mine.UserInfoActivity$doGoogleBind$1", f = "UserInfoActivity.kt", i = {}, l = {420}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.deye.activity.mine.UserInfoActivity$doGoogleBind$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ GetCredentialRequest $request;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(GetCredentialRequest getCredentialRequest, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$request = getCredentialRequest;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return UserInfoActivity.this.new AnonymousClass1(this.$request, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            String string2 = StubApp.getString2(13764);
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CredentialManager credentialManager = UserInfoActivity.this.credentialManager;
                    if (credentialManager == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("credentialManager");
                        credentialManager = null;
                    }
                    this.label = 1;
                    obj = credentialManager.getCredential((Context) UserInfoActivity.this, this.$request, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException(StubApp.getString2(13735));
                    }
                    ResultKt.throwOnFailure(obj);
                }
                UserInfoActivity.this.handleGoogleBindResult((GetCredentialResponse) obj);
            } catch (Exception e) {
                UserInfoActivity.this.hideLoading();
                LogUtil.e(string2, StubApp.getString2(13765) + e.getMessage());
                BaseUtils.showShortToast((Context) UserInfoActivity.this.mContext, UserInfoActivity.this.getString(R.string.google_bind_failed));
            } catch (GetCredentialException e2) {
                UserInfoActivity.this.hideLoading();
                LogUtil.e(string2, StubApp.getString2(13766) + e2.getMessage());
                BaseUtils.showShortToast((Context) UserInfoActivity.this.mContext, UserInfoActivity.this.getString(R.string.google_bind_failed));
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: UserInfoActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "response", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/LoginResult;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.UserInfoActivity$bindWithGoogleToken$2, reason: invalid class name */
    static final class AnonymousClass2<T> implements Consumer {
        AnonymousClass2() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(BaseResult<LoginResult> response) {
            Object string;
            UserInfoBean userInfoBean;
            Intrinsics.checkNotNullParameter(response, "response");
            BaseResult.MetaBean meta = response.getMeta();
            if (meta != null && meta.getCode() == 0) {
                PersonalCenterAtyBinding personalCenterAtyBinding = UserInfoActivity.this.mPersonalCenterAtyBinding;
                String string2 = StubApp.getString2(13762);
                if (personalCenterAtyBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    personalCenterAtyBinding = null;
                }
                personalCenterAtyBinding.tvGoogleId.setText(UserInfoActivity.this.getString(R.string.bound));
                PersonalCenterAtyBinding personalCenterAtyBinding2 = UserInfoActivity.this.mPersonalCenterAtyBinding;
                if (personalCenterAtyBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(string2);
                    personalCenterAtyBinding2 = null;
                }
                personalCenterAtyBinding2.tvGoogleId.setTextColor(UserInfoActivity.this.getColor(R.color.color_dark));
                UserInfoBean userInfoBean2 = UserInfoActivity.this.userInfo;
                if ((userInfoBean2 != null ? userInfoBean2.getGoogle_info() : null) == null && (userInfoBean = UserInfoActivity.this.userInfo) != null) {
                    userInfoBean.setGoogle_info(new GoogleInfoBean());
                }
                MMKVUtils.INSTANCE.setUserInfo(UserInfoActivity.this.userInfo);
                BaseUtils.showShortToast((Context) UserInfoActivity.this.mContext, UserInfoActivity.this.getString(R.string.google_bind_success));
                return;
            }
            BaseResult.MetaBean meta2 = response.getMeta();
            if (meta2 == null || (string = meta2.getMessage()) == null) {
                string = UserInfoActivity.this.getString(R.string.google_bind_failed);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            }
            BaseUtils.showShortToast((Context) UserInfoActivity.this.mContext, string.toString());
        }
    }

    /* compiled from: UserInfoActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "error", "", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.UserInfoActivity$bindWithGoogleToken$3, reason: invalid class name */
    static final class AnonymousClass3<T> implements Consumer {
        AnonymousClass3() {
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(Throwable error) {
            Intrinsics.checkNotNullParameter(error, "error");
            LogUtil.e(StubApp.getString2(13764), StubApp.getString2(13763) + error.getMessage());
            Context context = (Context) UserInfoActivity.this.mContext;
            String message = error.getMessage();
            if (message == null) {
                message = UserInfoActivity.this.getString(R.string.google_bind_failed);
                Intrinsics.checkNotNullExpressionValue(message, "getString(...)");
            }
            BaseUtils.showShortToast(context, message);
        }
    }

    /* compiled from: UserInfoActivity.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/deye/activity/mine/UserInfoActivity$verifyStorageTakePhotoPermissions$1", "Lcom/deye/helper/DialogHelper$OnDialogListener;", "onCancel", "", "onSure", "text", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.UserInfoActivity$verifyStorageTakePhotoPermissions$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01801 extends DialogHelper.OnDialogListener {
        final /* synthetic */ String[] $permissions;

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onCancel() {
        }

        C01801(String[] strArr) {
            this.$permissions = strArr;
        }

        @Override // com.deye.helper.DialogHelper.OnDialogListener
        public void onSure(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            UserInfoActivity.this.requestPermissionCamera(this.$permissions);
        }
    }

    /* compiled from: UserInfoActivity.kt */
    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\t\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\n\u001a\u00020\bH\u0016¨\u0006\u000b"}, d2 = {"com/deye/activity/mine/UserInfoActivity$requestPermissionCamera$1", "Lcom/hjq/permissions/OnPermissionCallback;", "onDenied", "", "permissions", "", "", "doNotAskAgain", "", "onGranted", "allGranted", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.UserInfoActivity$requestPermissionCamera$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01791 implements OnPermissionCallback {
        C01791() {
        }

        public void onGranted(List<String> permissions, boolean allGranted) {
            ActivityResultLauncher activityResultLauncher;
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (UserInfoActivity.this.uri == null || (activityResultLauncher = UserInfoActivity.this.launcher) == null) {
                return;
            }
            Uri uri = UserInfoActivity.this.uri;
            Intrinsics.checkNotNull(uri);
            activityResultLauncher.launch(uri);
        }

        public void onDenied(List<String> permissions, boolean doNotAskAgain) throws Resources.NotFoundException {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (doNotAskAgain) {
                String string = UserInfoActivity.this.getResources().getString(R.string.camera);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = UserInfoActivity.this.getResources().getString(R.string.camera_denied_content);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                DialogHelper.permissionDeniedHint(UserInfoActivity.this, string + string2, new DialogHelper.OnDialogListener() { // from class: com.deye.activity.mine.UserInfoActivity$requestPermissionCamera$1$onDenied$1
                    @Override // com.deye.helper.DialogHelper.OnDialogListener
                    public void onCancel() {
                    }

                    @Override // com.deye.helper.DialogHelper.OnDialogListener
                    public void onSure(String text) {
                        Intrinsics.checkNotNullParameter(text, "text");
                        ActivityRouterUtilsKt.goAppDetailSetting();
                    }
                });
            }
        }
    }

    /* compiled from: UserInfoActivity.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"com/deye/activity/mine/UserInfoActivity$onActivityResult$1", "Lio/fogcloud/sdk/fog/callback/FogCallBack;", "onFailure", "", "code", "", "message", "", "onSuccess", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.mine.UserInfoActivity$onActivityResult$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01751 implements FogCallBack {
        final /* synthetic */ String $imgPath;
        final /* synthetic */ UserInfoActivity this$0;

        C01751(String str, UserInfoActivity userInfoActivity) {
            this.$imgPath = str;
            this.this$0 = userInfoActivity;
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onSuccess(final String message) throws IOException {
            Intrinsics.checkNotNullParameter(message, "message");
            UserAvatarHelper.imgSaveToSD(this.$imgPath, UserAvatarHelper.getAvatarCachePath((Context) this.this$0.mContext));
            final UserInfoActivity userInfoActivity = this.this$0;
            userInfoActivity.runOnUiThread(new Runnable() { // from class: com.deye.activity.mine.UserInfoActivity$onActivityResult$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    UserInfoActivity.C01751.onSuccess$lambda$0(message, userInfoActivity);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSuccess$lambda$0(String message, UserInfoActivity this$0) {
            Intrinsics.checkNotNullParameter(message, "$message");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            LogUtil.d(StubApp.getString2(13767) + message);
            ResponseDataBean responseDataBean = (ResponseDataBean) JSON.parseObject(message, ResponseDataBean.class);
            if (responseDataBean != null && responseDataBean.getMeta().getCode() == 0) {
                this$0.setHeadPortrait(responseDataBean.getData().getImage_path());
                BaseUtils.showShortToast(StubApp.getOrigApplicationContext(this$0.getApplicationContext()), this$0.getString(R.string.avatar_update_successful));
            } else {
                BaseUtils.showShortToast(StubApp.getOrigApplicationContext(this$0.getApplicationContext()), this$0.getString(R.string.avatar_update_failed));
            }
            UserAvatarHelper.deleteAvatarFile();
        }

        @Override // io.fogcloud.sdk.fog.callback.FogCallBack
        public void onFailure(int code, String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            LogUtil.d(StubApp.getString2(13768) + code + StubApp.getString2(13769) + message);
            final UserInfoActivity userInfoActivity = this.this$0;
            userInfoActivity.runOnUiThread(new Runnable() { // from class: com.deye.activity.mine.UserInfoActivity$onActivityResult$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    UserInfoActivity.C01751.onFailure$lambda$1(userInfoActivity);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onFailure$lambda$1(UserInfoActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            BaseUtils.showShortToast(StubApp.getOrigApplicationContext(this$0.getApplicationContext()), this$0.getString(R.string.avatar_update_failed));
            UserAvatarHelper.deleteAvatarFile();
        }
    }
}
