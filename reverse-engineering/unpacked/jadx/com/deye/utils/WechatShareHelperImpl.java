package com.deye.utils;

import android.content.Context;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import com.deye.MyActivityManager;
import com.deye.MyReactActivity;
import com.mob.MobSDK;
import com.stub.StubApp;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WechatShareHelperImpl.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0016¨\u0006\u000e"}, d2 = {"Lcom/deye/utils/WechatShareHelperImpl;", "Lcom/deye/utils/WechatShareHelper;", "()V", "share", "", "context", "Landroid/content/Context;", "platform", "", "title", "", "desc", "thumbPath", "url", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class WechatShareHelperImpl implements WechatShareHelper {
    @Override // com.deye.utils.WechatShareHelper
    public void share(final Context context, int platform, String title, String desc, String thumbPath, String url) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(thumbPath, "thumbPath");
        Intrinsics.checkNotNullParameter(url, "url");
        if (MyActivityManager.getInstance().getCurrentActivity() instanceof MyReactActivity) {
            OnekeyShare onekeyShare = new OnekeyShare();
            if (platform == 0) {
                onekeyShare.setPlatform(Wechat.NAME);
            } else {
                onekeyShare.setPlatform(WechatMoments.NAME);
            }
            onekeyShare.setText(desc);
            onekeyShare.setTitle(title);
            onekeyShare.setUrl(url);
            onekeyShare.setImagePath(thumbPath);
            onekeyShare.setCallback(new PlatformActionListener() { // from class: com.deye.utils.WechatShareHelperImpl.share.1
                public void onCancel(Platform platform2, int action) {
                }

                public void onError(Platform platform2, int action, Throwable throwable) {
                }

                public void onComplete(Platform platform2, int action, HashMap<String, Object> data) {
                    BaseUtils.showShortToast(context, StubApp.getString2(14424));
                }
            });
            onekeyShare.show(MobSDK.getContext());
        }
    }
}
