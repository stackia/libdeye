package com.deye.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.deye.MyReactActivity;
import com.deye.combo.bluetooth.BluetoothSM;
import com.google.gson.Gson;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.FindItemBean;
import java.util.List;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class PagerUtils {
    public static void goBleScanPage(final FragmentActivity fragmentActivity) {
        if (!BluetoothSM.getInstance().isEnable()) {
            BluetoothDialogUtils.openBluetoothDialog(fragmentActivity);
            return;
        }
        String[] strArr = {StubApp.getString2(8631), StubApp.getString2(8633), StubApp.getString2(13387)};
        if (Build.VERSION.SDK_INT >= 31) {
            XXPermissions.with(fragmentActivity).permission(strArr).request(new OnPermissionCallback() { // from class: com.deye.utils.PagerUtils.1
                public void onGranted(List<String> list, boolean z) {
                    if (z) {
                        PagerUtils.startActivity(fragmentActivity);
                    } else {
                        BaseUtils.showShortToast(StubApp.getString2(14369));
                    }
                }

                public void onDenied(List<String> list, boolean z) {
                    BaseUtils.showShortToast(StubApp.getString2(14369));
                }
            });
        } else {
            startActivity(fragmentActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startActivity(Context context) {
        MMKVUtils.INSTANCE.getGlobalConfig();
        Intent intent = new Intent(context, (Class<?>) MyReactActivity.class);
        intent.putExtra(StubApp.getString2(13144), StubApp.getString2(14131));
        context.startActivity(intent);
    }

    public static void goSettingRoom(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, (Class<?>) MyReactActivity.class);
        intent.putExtra(StubApp.getString2(13144), StubApp.getString2(14197));
        Bundle bundle = new Bundle();
        bundle.putString(StubApp.getString2(831), str);
        bundle.putString(StubApp.getString2(14371), str2);
        bundle.putString(StubApp.getString2(4671), StubApp.getString2(1764));
        if (str3 != null) {
            bundle.putString(StubApp.getString2(14374), str3);
        }
        intent.putExtra(StubApp.getString2(13145), bundle);
        context.startActivity(intent);
    }

    public static void goConfigSuccess(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, (Class<?>) MyReactActivity.class);
        intent.putExtra(StubApp.getString2(13144), StubApp.getString2(14370));
        Bundle bundle = new Bundle();
        bundle.putString(StubApp.getString2(831), str);
        bundle.putString(StubApp.getString2(14371), str3);
        bundle.putString(StubApp.getString2(14372), str2);
        bundle.putString(StubApp.getString2(4671), StubApp.getString2(2546));
        intent.putExtra(StubApp.getString2(13145), bundle);
        context.startActivity(intent);
    }

    public static void goVoiceGuidePage(Context context) {
        Intent intent = new Intent(context, (Class<?>) MyReactActivity.class);
        intent.putExtra(StubApp.getString2(13144), StubApp.getString2(14378));
        context.startActivity(intent);
    }

    public static void goOfflineGuidePage(Context context, String str) {
        Intent intent = new Intent(context, (Class<?>) MyReactActivity.class);
        intent.putExtra(StubApp.getString2(13144), StubApp.getString2(14373));
        Bundle bundle = new Bundle();
        bundle.putString(StubApp.getString2(14372), str);
        bundle.putBoolean(StubApp.getString2(13143), ChannelUtil.isOversea());
        intent.putExtra(StubApp.getString2(13145), bundle);
        context.startActivity(intent);
    }

    public static void goVideoPlayerPage(Context context, FindItemBean findItemBean, String str, Integer num) {
        Intent intent = new Intent(context, (Class<?>) MyReactActivity.class);
        intent.putExtra(StubApp.getString2(13144), StubApp.getString2(14375));
        Bundle bundle = new Bundle();
        bundle.putString(StubApp.getString2(14376), new Gson().toJson(findItemBean));
        bundle.putString(StubApp.getString2(7925), str);
        bundle.putInt(StubApp.getString2(14377), num.intValue());
        intent.putExtra(StubApp.getString2(13145), bundle);
        context.startActivity(intent);
    }
}
