package com.deye.views;

import android.content.Context;
import android.util.AttributeSet;
import com.mxchipapp.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class DeyeVideoPlayer extends StandardGSYVideoPlayer {
    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    public int getLayoutId() {
        return R.layout.video_player;
    }

    public DeyeVideoPlayer(Context context, Boolean bool) {
        super(context, bool);
    }

    public DeyeVideoPlayer(Context context) {
        super(context);
    }

    public DeyeVideoPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
