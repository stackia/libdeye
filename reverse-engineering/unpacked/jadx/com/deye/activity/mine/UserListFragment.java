package com.deye.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.deye.adapter.RyUserListAdapter;
import com.deye.entity.UserBean;
import com.deye.fragment.BaseFragment;
import com.deye.helper.DialogHelper;
import com.deye.utils.BaseUtils;
import com.deye.utils.StringUtils;
import com.efs.sdk.base.protocol.ILogProtocol;
import com.mxchipapp.R;
import com.mxchipapp.databinding.DeviceUserListBinding;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import java.util.ArrayList;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;

/* compiled from: UserListFragment.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 /2\u00020\u0001:\u0001/B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0012\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J&\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#H\u0002J\u0006\u0010$\u001a\u00020\u0017J\u000e\u0010%\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#J\u000e\u0010&\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#J\u0018\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020*H\u0002J\u0018\u0010+\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#2\u0006\u0010,\u001a\u00020#H\u0002J\b\u0010-\u001a\u00020\u0017H\u0002J\u0010\u0010.\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/deye/activity/mine/UserListFragment;", "Lcom/deye/fragment/BaseFragment;", "()V", "mDeviceId", "", "mDeviceUserListBinding", "Lcom/mxchipapp/databinding/DeviceUserListBinding;", "mIsSetSuper", "", "mRyUserListAdapter", "Lcom/deye/adapter/RyUserListAdapter;", "mUserBean", "Lcom/deye/entity/UserBean;", "mUserBeanList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getMUserBeanList", "()Ljava/util/ArrayList;", "setMUserBeanList", "(Ljava/util/ArrayList;)V", "myHandler", "Landroid/os/Handler;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "removeBindRole", "i", "", "requestUserList", "sendDelete", "sendSuper", "setHeadPortrait", "imgUrl", "imageView", "Landroid/widget/ImageView;", "showDialogSuper", ILogProtocol.LOG_KEY_TYPE, "showUser", "transferAminUser", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class UserListFragment extends BaseFragment {
    private static final int DELETE_USER = 300;
    private static final int DISSMISS_WAITING_DIALOG = 600;
    private static final int DONT_HAVE_PERMITION = 100;
    private static final int REQUEST_SUCCESS = 200;
    private static final int SET_SUPER_USER = 400;
    private static final int SHOW_WAITING_DIALOG = 500;
    private String mDeviceId;
    private DeviceUserListBinding mDeviceUserListBinding;
    private boolean mIsSetSuper;
    private RyUserListAdapter mRyUserListAdapter;
    private UserBean mUserBean;
    private ArrayList<UserBean> mUserBeanList = new ArrayList<>();
    private final Handler myHandler;
    private static final String KEY_DEVICE_ID = StubApp.getString2(13789);

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public UserListFragment() {
        final Looper mainLooper = Looper.getMainLooper();
        this.myHandler = new Handler(mainLooper) { // from class: com.deye.activity.mine.UserListFragment$myHandler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                try {
                    int i = msg.what;
                    if (i == 200) {
                        this.this$0.showUser();
                    } else if (i == 300) {
                        this.this$0.showDialogSuper(msg.arg1, 0);
                    } else if (i == 400) {
                        this.this$0.showDialogSuper(msg.arg2, 1);
                    } else if (i == 500) {
                        UserListFragment userListFragment = this.this$0;
                        Context contextRequireContext = userListFragment.requireContext();
                        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
                        userListFragment.showLoading(contextRequireContext, StubApp.getString2("13790"));
                    } else if (i == 600) {
                        this.this$0.hideLoading();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public final ArrayList<UserBean> getMUserBeanList() {
        return this.mUserBeanList;
    }

    public final void setMUserBeanList(ArrayList<UserBean> arrayList) {
        this.mUserBeanList = arrayList;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.mDeviceId = arguments != null ? arguments.getString(StubApp.getString2(13789)) : null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, container, savedInstanceState);
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.device_user_list, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        this.mDeviceUserListBinding = (DeviceUserListBinding) viewDataBindingInflate;
        initView();
        BaseUtils.sendMessage(this.myHandler, 500, "");
        requestUserList();
        DeviceUserListBinding deviceUserListBinding = this.mDeviceUserListBinding;
        if (deviceUserListBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDeviceUserListBinding");
            deviceUserListBinding = null;
        }
        return deviceUserListBinding.getRoot();
    }

    private final void initView() {
        DeviceUserListBinding deviceUserListBinding = this.mDeviceUserListBinding;
        if (deviceUserListBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDeviceUserListBinding");
            deviceUserListBinding = null;
        }
        deviceUserListBinding.ryUserList.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    public final void requestUserList() {
        DeYeHttpRequestManager.getInstance().getMemberList(this.mDeviceId, new FogCallBack() { // from class: com.deye.activity.mine.UserListFragment.requestUserList.1
            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onSuccess(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                BaseUtils.sendMessage(UserListFragment.this.myHandler, UserListFragment.DISSMISS_WAITING_DIALOG, "");
                Log.d("", message);
                Integer integer = JSON.parseObject(JSON.parseObject(message).getString(StubApp.getString2(13082))).getInteger(StubApp.getString2(109));
                if (integer != null && integer.intValue() == 10353 && !UserListFragment.this.mIsSetSuper) {
                    BaseUtils.sendMessage(UserListFragment.this.myHandler, 100, "");
                }
                if (integer != null && integer.intValue() == 0) {
                    UserListFragment.this.setMUserBeanList(new ArrayList<>(JSON.parseArray(JSON.parseObject(message).getString(StubApp.getString2(100)), UserBean.class)));
                    if (UserListFragment.this.getMUserBeanList() != null) {
                        ArrayList<UserBean> mUserBeanList = UserListFragment.this.getMUserBeanList();
                        Intrinsics.checkNotNull(mUserBeanList);
                        if (mUserBeanList.size() > 0) {
                            BaseUtils.sendMessage(UserListFragment.this.myHandler, 200, "");
                        }
                    }
                }
            }

            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onFailure(int code, String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                BaseUtils.sendMessage(UserListFragment.this.myHandler, UserListFragment.DISSMISS_WAITING_DIALOG, "");
                Log.d(StubApp.getString2(5607), message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showUser() {
        ArrayList<UserBean> arrayList = this.mUserBeanList;
        if (arrayList != null) {
            final C01831 c01831 = new Function2<UserBean, UserBean, Integer>() { // from class: com.deye.activity.mine.UserListFragment.showUser.1
                @Override // kotlin.jvm.functions.Function2
                public final Integer invoke(UserBean userBean, UserBean userBean2) {
                    return Integer.valueOf(userBean.role - userBean2.role);
                }
            };
            CollectionsKt.sortWith(arrayList, new Comparator() { // from class: com.deye.activity.mine.UserListFragment$$ExternalSyntheticLambda0
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return UserListFragment.showUser$lambda$0(c01831, obj, obj2);
                }
            });
        }
        ArrayList<UserBean> arrayList2 = this.mUserBeanList;
        Intrinsics.checkNotNull(arrayList2);
        int size = arrayList2.size();
        DeviceUserListBinding deviceUserListBinding = null;
        String string2 = StubApp.getString2(13796);
        if (size > 0) {
            DeviceUserListBinding deviceUserListBinding2 = this.mDeviceUserListBinding;
            if (deviceUserListBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                deviceUserListBinding2 = null;
            }
            deviceUserListBinding2.ryUserList.setVisibility(0);
            ArrayList<UserBean> arrayList3 = this.mUserBeanList;
            Intrinsics.checkNotNull(arrayList3);
            this.mRyUserListAdapter = new RyUserListAdapter(this, arrayList3);
            DeviceUserListBinding deviceUserListBinding3 = this.mDeviceUserListBinding;
            if (deviceUserListBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                deviceUserListBinding = deviceUserListBinding3;
            }
            deviceUserListBinding.ryUserList.setAdapter(this.mRyUserListAdapter);
            return;
        }
        DeviceUserListBinding deviceUserListBinding4 = this.mDeviceUserListBinding;
        if (deviceUserListBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            deviceUserListBinding = deviceUserListBinding4;
        }
        deviceUserListBinding.ryUserList.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int showUser$lambda$0(Function2 tmp0, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        return ((Number) tmp0.invoke(obj, obj2)).intValue();
    }

    private final void setHeadPortrait(String imgUrl, ImageView imageView) {
        RequestOptions requestOptionsError = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE).placeholder(R.mipmap.head_icon).error(R.mipmap.head_icon);
        Intrinsics.checkNotNullExpressionValue(requestOptionsError, "error(...)");
        Glide.with(this).load(imgUrl).apply((BaseRequestOptions<?>) requestOptionsError).into(imageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDialogSuper(final int i, final int type) {
        String str;
        ArrayList<UserBean> arrayList = this.mUserBeanList;
        Intrinsics.checkNotNull(arrayList);
        String strMaskPhoneNumber = StringUtils.maskPhoneNumber(arrayList.get(i).phone);
        if (type == 0) {
            str = StubApp.getString2(13793) + strMaskPhoneNumber + StubApp.getString2(13794);
        } else if (type != 1) {
            str = "";
        } else {
            str = StubApp.getString2(13791) + strMaskPhoneNumber + StubApp.getString2(13792);
        }
        DialogHelper.showDeleteDialog(requireActivity(), StringUtils.stringFilter(str), StubApp.getString2(13795), new DialogHelper.OnDialogListener() { // from class: com.deye.activity.mine.UserListFragment.showDialogSuper.1
            @Override // com.deye.helper.DialogHelper.OnDialogListener
            public void onSure(String text) throws JSONException {
                int i2 = type;
                if (i2 == 0) {
                    this.removeBindRole(i);
                } else if (i2 == 1) {
                    this.transferAminUser(i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void transferAminUser(int i) throws JSONException {
        BaseUtils.sendMessage(this.myHandler, 500, "");
        DeYeHttpRequestManager deYeHttpRequestManager = DeYeHttpRequestManager.getInstance();
        String str = this.mDeviceId;
        ArrayList<UserBean> arrayList = this.mUserBeanList;
        Intrinsics.checkNotNull(arrayList);
        deYeHttpRequestManager.transferAminUser(str, arrayList.get(i).enduserid, new FogCallBack() { // from class: com.deye.activity.mine.UserListFragment.transferAminUser.1
            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onSuccess(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                Log.d(StubApp.getString2(5607), message);
                Integer integer = JSON.parseObject(JSON.parseObject(message).getString(StubApp.getString2(13082))).getInteger(StubApp.getString2(109));
                if (integer != null && integer.intValue() == 0) {
                    UserListFragment.this.mIsSetSuper = true;
                    UserListFragment.this.requestUserList();
                } else {
                    BaseUtils.sendMessage(UserListFragment.this.myHandler, UserListFragment.DISSMISS_WAITING_DIALOG, "");
                }
            }

            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onFailure(int code, String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                BaseUtils.sendMessage(UserListFragment.this.myHandler, UserListFragment.DISSMISS_WAITING_DIALOG, "");
                Log.d(StubApp.getString2(5607), message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeBindRole(int i) throws JSONException {
        BaseUtils.sendMessage(this.myHandler, 500, "");
        DeYeHttpRequestManager deYeHttpRequestManager = DeYeHttpRequestManager.getInstance();
        String str = this.mDeviceId;
        ArrayList<UserBean> arrayList = this.mUserBeanList;
        Intrinsics.checkNotNull(arrayList);
        deYeHttpRequestManager.removeBindRole(str, arrayList.get(i).enduserid, new FogCallBack() { // from class: com.deye.activity.mine.UserListFragment.removeBindRole.1
            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onSuccess(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                Integer integer = JSON.parseObject(JSON.parseObject(message).getString(StubApp.getString2(13082))).getInteger(StubApp.getString2(109));
                if (integer == null || integer.intValue() != 0) {
                    BaseUtils.sendMessage(UserListFragment.this.myHandler, UserListFragment.DISSMISS_WAITING_DIALOG, "");
                } else {
                    UserListFragment.this.requestUserList();
                }
            }

            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onFailure(int code, String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                BaseUtils.sendMessage(UserListFragment.this.myHandler, UserListFragment.DISSMISS_WAITING_DIALOG, "");
            }
        });
    }

    public final void sendDelete(int i) {
        Message messageObtainMessage = this.myHandler.obtainMessage();
        Intrinsics.checkNotNullExpressionValue(messageObtainMessage, "obtainMessage(...)");
        messageObtainMessage.what = 300;
        messageObtainMessage.arg1 = i;
        this.myHandler.sendMessage(messageObtainMessage);
    }

    public final void sendSuper(int i) {
        Message messageObtainMessage = this.myHandler.obtainMessage();
        Intrinsics.checkNotNullExpressionValue(messageObtainMessage, "obtainMessage(...)");
        messageObtainMessage.what = 400;
        messageObtainMessage.arg2 = i;
        this.myHandler.sendMessage(messageObtainMessage);
    }

    /* compiled from: UserListFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/deye/activity/mine/UserListFragment$Companion;", "", "()V", "DELETE_USER", "", "DISSMISS_WAITING_DIALOG", "DONT_HAVE_PERMITION", "KEY_DEVICE_ID", "", "REQUEST_SUCCESS", "SET_SUPER_USER", "SHOW_WAITING_DIALOG", "newInstance", "Lcom/deye/activity/mine/UserListFragment;", "deviceId", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UserListFragment newInstance(String deviceId) {
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            UserListFragment userListFragment = new UserListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(StubApp.getString2(13789), deviceId);
            userListFragment.setArguments(bundle);
            return userListFragment;
        }
    }
}
