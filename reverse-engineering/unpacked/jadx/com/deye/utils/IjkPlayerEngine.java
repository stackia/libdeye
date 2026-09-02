package com.deye.utils;

import android.content.Context;
import android.view.View;
import com.luck.picture.lib.config.SelectorProviders;
import com.luck.picture.lib.engine.VideoPlayerEngine;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnPlayerListener;
import java.util.concurrent.CopyOnWriteArrayList;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class IjkPlayerEngine implements VideoPlayerEngine<IjkPlayerView> {
    private final CopyOnWriteArrayList<OnPlayerListener> listeners = new CopyOnWriteArrayList<>();

    public View onCreateVideoPlayer(Context context) {
        return new IjkPlayerView(context);
    }

    public void onStarPlayer(IjkPlayerView ijkPlayerView, LocalMedia localMedia) {
        ijkPlayerView.getMediaPlayer().setLooping(SelectorProviders.getInstance().getSelectorConfig().isLoopAutoPlay);
        ijkPlayerView.start(localMedia.getAvailablePath());
    }

    public void onResume(IjkPlayerView ijkPlayerView) {
        IjkMediaPlayer mediaPlayer = ijkPlayerView.getMediaPlayer();
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public void onPause(IjkPlayerView ijkPlayerView) {
        IjkMediaPlayer mediaPlayer = ijkPlayerView.getMediaPlayer();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public boolean isPlaying(IjkPlayerView ijkPlayerView) {
        IjkMediaPlayer mediaPlayer = ijkPlayerView.getMediaPlayer();
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    public void addPlayListener(OnPlayerListener onPlayerListener) {
        if (this.listeners.contains(onPlayerListener)) {
            return;
        }
        this.listeners.add(onPlayerListener);
    }

    public void removePlayListener(OnPlayerListener onPlayerListener) {
        if (onPlayerListener != null) {
            this.listeners.remove(onPlayerListener);
        } else {
            this.listeners.clear();
        }
    }

    public void onPlayerAttachedToWindow(final IjkPlayerView ijkPlayerView) {
        IjkMediaPlayer ijkMediaPlayerInitMediaPlayer = ijkPlayerView.initMediaPlayer();
        ijkMediaPlayerInitMediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() { // from class: com.deye.utils.IjkPlayerEngine.1
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                iMediaPlayer.start();
                for (int i = 0; i < IjkPlayerEngine.this.listeners.size(); i++) {
                    ((OnPlayerListener) IjkPlayerEngine.this.listeners.get(i)).onPlayerReady();
                }
            }
        });
        ijkMediaPlayerInitMediaPlayer.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() { // from class: com.deye.utils.IjkPlayerEngine.2
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                iMediaPlayer.reset();
                for (int i = 0; i < IjkPlayerEngine.this.listeners.size(); i++) {
                    ((OnPlayerListener) IjkPlayerEngine.this.listeners.get(i)).onPlayerEnd();
                }
                ijkPlayerView.clearCanvas();
            }
        });
        ijkMediaPlayerInitMediaPlayer.setOnErrorListener(new IMediaPlayer.OnErrorListener() { // from class: com.deye.utils.IjkPlayerEngine.3
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                for (int i3 = 0; i3 < IjkPlayerEngine.this.listeners.size(); i3++) {
                    ((OnPlayerListener) IjkPlayerEngine.this.listeners.get(i3)).onPlayerError();
                }
                return false;
            }
        });
    }

    public void onPlayerDetachedFromWindow(IjkPlayerView ijkPlayerView) {
        ijkPlayerView.release();
    }

    public void destroy(IjkPlayerView ijkPlayerView) {
        ijkPlayerView.release();
    }
}
