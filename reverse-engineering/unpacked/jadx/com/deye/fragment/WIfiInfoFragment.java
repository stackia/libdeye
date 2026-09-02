package com.deye.fragment;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.deye.activity.config_net.ConfigNetGuideAty;
import com.deye.adapter.WifiInfoAdapter;
import com.deye.fragment.WIfiInfoFragment;
import com.deye.helper.WifiScanCallback;
import com.deye.helper.WifiScanResult;
import com.deye.helper.WifiScannerUtil;
import com.google.gson.Gson;
import com.mxchipapp.R;
import com.mxchipapp.databinding.FragmentWifiInfoBinding;
import com.stub.StubApp;
import com.uc.crashsdk.export.LogType;
import io.fogcloud.sdk.fog.log.LogDebug;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WIfiInfoFragment.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0006H\u0016J\u0012\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J$\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0018\u001a\u00020\fH\u0016J\b\u0010\u0019\u001a\u00020\fH\u0016J\b\u0010\u001a\u001a\u00020\fH\u0016J\b\u0010\u001b\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/deye/fragment/WIfiInfoFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "mBinding", "Lcom/mxchipapp/databinding/FragmentWifiInfoBinding;", "mContext", "Landroid/content/Context;", "rotateAnimator", "Landroid/animation/ObjectAnimator;", "wifiListener", "Lcom/deye/adapter/WifiInfoAdapter$IWifiListener;", "initView", "", "onAttach", "context", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onPause", "onResume", "onStart", "startScan", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class WIfiInfoFragment extends DialogFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentWifiInfoBinding mBinding;
    private Context mContext;
    private ObjectAnimator rotateAnimator;
    private WifiInfoAdapter.IWifiListener wifiListener;

    @JvmStatic
    public static final WIfiInfoFragment newInstance() {
        return INSTANCE.newInstance();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, R.style.FullScreenDialogTheme);
    }

    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setLayout(-1, -1);
        window.getDecorView().setSystemUiVisibility(LogType.UNEXP_ANR);
        window.addFlags(Integer.MIN_VALUE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        this.mContext = context;
        this.wifiListener = context instanceof WifiInfoAdapter.IWifiListener ? (WifiInfoAdapter.IWifiListener) context : null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, container, savedInstanceState);
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.fragment_wifi_info, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        this.mBinding = (FragmentWifiInfoBinding) viewDataBindingInflate;
        initView();
        FragmentWifiInfoBinding fragmentWifiInfoBinding = this.mBinding;
        if (fragmentWifiInfoBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
            fragmentWifiInfoBinding = null;
        }
        View root = fragmentWifiInfoBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    private final void initView() {
        FragmentWifiInfoBinding fragmentWifiInfoBinding = this.mBinding;
        FragmentWifiInfoBinding fragmentWifiInfoBinding2 = null;
        String string2 = StubApp.getString2(13230);
        if (fragmentWifiInfoBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            fragmentWifiInfoBinding = null;
        }
        fragmentWifiInfoBinding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        FragmentWifiInfoBinding fragmentWifiInfoBinding3 = this.mBinding;
        if (fragmentWifiInfoBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            fragmentWifiInfoBinding3 = null;
        }
        fragmentWifiInfoBinding3.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.WIfiInfoFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WIfiInfoFragment.initView$lambda$1(this.f$0, view);
            }
        });
        FragmentWifiInfoBinding fragmentWifiInfoBinding4 = this.mBinding;
        if (fragmentWifiInfoBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            fragmentWifiInfoBinding4 = null;
        }
        fragmentWifiInfoBinding4.ivRefresh.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.WIfiInfoFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WIfiInfoFragment.initView$lambda$2(this.f$0, view);
            }
        });
        FragmentWifiInfoBinding fragmentWifiInfoBinding5 = this.mBinding;
        if (fragmentWifiInfoBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            fragmentWifiInfoBinding2 = fragmentWifiInfoBinding5;
        }
        fragmentWifiInfoBinding2.tvNoWifi.setOnClickListener(new View.OnClickListener() { // from class: com.deye.fragment.WIfiInfoFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WIfiInfoFragment.initView$lambda$3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$1(WIfiInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$2(WIfiInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startScan();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$3(WIfiInfoFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.mContext, (Class<?>) ConfigNetGuideAty.class));
    }

    public void onResume() {
        super.onResume();
        startScan();
    }

    private final void startScan() {
        if (this.rotateAnimator == null) {
            FragmentWifiInfoBinding fragmentWifiInfoBinding = this.mBinding;
            if (fragmentWifiInfoBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                fragmentWifiInfoBinding = null;
            }
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(fragmentWifiInfoBinding.ivRefresh, StubApp.getString2(11243), 0.0f, 360.0f);
            this.rotateAnimator = objectAnimatorOfFloat;
            Intrinsics.checkNotNull(objectAnimatorOfFloat);
            objectAnimatorOfFloat.setDuration(1000L);
            ObjectAnimator objectAnimator = this.rotateAnimator;
            Intrinsics.checkNotNull(objectAnimator);
            objectAnimator.setRepeatCount(-1);
            ObjectAnimator objectAnimator2 = this.rotateAnimator;
            Intrinsics.checkNotNull(objectAnimator2);
            objectAnimator2.setInterpolator(new LinearInterpolator());
        }
        ObjectAnimator objectAnimator3 = this.rotateAnimator;
        if (objectAnimator3 != null) {
            objectAnimator3.start();
        }
        WifiScannerUtil.INSTANCE.startScan(new AnonymousClass1());
    }

    /* compiled from: WIfiInfoFragment.kt */
    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0016\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016¨\u0006\n"}, d2 = {"com/deye/fragment/WIfiInfoFragment$startScan$1", "Lcom/deye/helper/WifiScanCallback;", "onScanFailure", "", "error", "", "onScanResults", "results", "", "Lcom/deye/helper/WifiScanResult;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.fragment.WIfiInfoFragment$startScan$1, reason: invalid class name */
    public static final class AnonymousClass1 implements WifiScanCallback {
        AnonymousClass1() {
        }

        @Override // com.deye.helper.WifiScanCallback
        public void onScanResults(List<WifiScanResult> results) {
            Intrinsics.checkNotNullParameter(results, "results");
            LogDebug.INSTANCE.log(StubApp.getString2(14119) + new Gson().toJson(results));
            Log.e(StubApp.getString2(14120), new Gson().toJson(results));
            FragmentWifiInfoBinding fragmentWifiInfoBinding = WIfiInfoFragment.this.mBinding;
            FragmentWifiInfoBinding fragmentWifiInfoBinding2 = null;
            String string2 = StubApp.getString2(13230);
            if (fragmentWifiInfoBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                fragmentWifiInfoBinding = null;
            }
            View root = fragmentWifiInfoBinding.getRoot();
            final WIfiInfoFragment wIfiInfoFragment = WIfiInfoFragment.this;
            root.postDelayed(new Runnable() { // from class: com.deye.fragment.WIfiInfoFragment$startScan$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WIfiInfoFragment.AnonymousClass1.onScanResults$lambda$0(wIfiInfoFragment);
                }
            }, 500L);
            Context context = WIfiInfoFragment.this.mContext;
            Intrinsics.checkNotNull(context);
            WifiInfoAdapter.IWifiListener iWifiListener = WIfiInfoFragment.this.wifiListener;
            Intrinsics.checkNotNull(iWifiListener);
            WifiInfoAdapter wifiInfoAdapter = new WifiInfoAdapter(context, results, iWifiListener);
            FragmentWifiInfoBinding fragmentWifiInfoBinding3 = WIfiInfoFragment.this.mBinding;
            if (fragmentWifiInfoBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                fragmentWifiInfoBinding2 = fragmentWifiInfoBinding3;
            }
            fragmentWifiInfoBinding2.recyclerView.setAdapter(wifiInfoAdapter);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onScanResults$lambda$0(WIfiInfoFragment this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            ObjectAnimator objectAnimator = this$0.rotateAnimator;
            if (objectAnimator != null) {
                objectAnimator.end();
            }
        }

        @Override // com.deye.helper.WifiScanCallback
        public void onScanFailure(String error) {
            LogDebug.INSTANCE.log(StubApp.getString2(14118) + error);
        }
    }

    public void onPause() {
        super.onPause();
        WifiScannerUtil.INSTANCE.stopScan();
    }

    /* compiled from: WIfiInfoFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/deye/fragment/WIfiInfoFragment$Companion;", "", "()V", "newInstance", "Lcom/deye/fragment/WIfiInfoFragment;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final WIfiInfoFragment newInstance() {
            WIfiInfoFragment wIfiInfoFragment = new WIfiInfoFragment();
            new Bundle();
            return wIfiInfoFragment;
        }
    }
}
