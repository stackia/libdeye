package com.deye.activity.login;

import android.os.Bundle;
import android.view.KeyEvent;
import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.databinding.HomePageAtyBinding;
import com.shuyu.gsyvideoplayer.model.GSYModel;
import com.shuyu.gsyvideoplayer.player.IPlayerInitSuccessListener;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.stub.StubApp;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class HomePageActivity extends BaseActivity {
    public static final String TAG = StubApp.getString2(13699);
    private HomePageAtyBinding mHomePageAtyBinding;
    private StandardGSYVideoPlayer videoPlayer;

    static {
        StubApp.interface11(14230);
    }

    private native void initView();

    private native void setNavigationBarColor();

    private native void toLoginPage();

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle bundle);

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onDestroy();

    @Override // com.deye.activity.device.base.BaseActivity
    public native boolean onKeyDown(int i, KeyEvent keyEvent);

    protected native void onPause();

    @Override // com.deye.activity.device.base.BaseActivity
    protected native void onResume();

    /* renamed from: com.deye.activity.login.HomePageActivity$1, reason: invalid class name */
    class AnonymousClass1 implements IPlayerInitSuccessListener {
        AnonymousClass1() {
        }

        @Override // com.shuyu.gsyvideoplayer.player.IPlayerInitSuccessListener
        public void onPlayerInitSuccess(IMediaPlayer iMediaPlayer, GSYModel gSYModel) {
            iMediaPlayer.setVolume(0.0f, 0.0f);
        }
    }
}
