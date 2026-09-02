package com.deye.utils;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import com.facebook.imagepipeline.common.RotationOptions;
import com.luck.picture.lib.config.PictureMimeType;
import com.stub.StubApp;
import java.io.IOException;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class IjkPlayerView extends FrameLayout implements TextureView.SurfaceTextureListener {
    private int mVideoRotation;
    private IjkMediaPlayer mediaPlayer;
    private IjkVideoTextureView textureView;

    public void clearCanvas() {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public IjkPlayerView(Context context) {
        super(context);
        init();
    }

    public IjkPlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public IjkPlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        IjkVideoTextureView ijkVideoTextureView = new IjkVideoTextureView(getContext());
        this.textureView = ijkVideoTextureView;
        ijkVideoTextureView.setSurfaceTextureListener(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        this.textureView.setLayoutParams(layoutParams);
        addView(this.textureView);
    }

    public IjkMediaPlayer initMediaPlayer() {
        if (this.mediaPlayer == null) {
            this.mediaPlayer = new IjkMediaPlayer();
        }
        this.mediaPlayer.setOnVideoSizeChangedListener(new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.deye.utils.IjkPlayerView.1
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
                IjkPlayerView.this.textureView.adjustVideoSize(i, i2, IjkPlayerView.this.mVideoRotation);
            }
        });
        this.mediaPlayer.setOnInfoListener(new IMediaPlayer.OnInfoListener() { // from class: com.deye.utils.IjkPlayerView.2
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                if (i != 10001) {
                    return false;
                }
                IjkPlayerView.this.mVideoRotation = i2;
                return false;
            }
        });
        this.mediaPlayer.setOption(4, StubApp.getString2(14316), 1L);
        return this.mediaPlayer;
    }

    public IjkMediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    public void start(String str) {
        try {
            if (PictureMimeType.isContent(str)) {
                this.mediaPlayer.setDataSource(getContext(), Uri.parse(str));
            } else {
                this.mediaPlayer.setDataSource(str);
            }
            SurfaceTexture surfaceTexture = this.textureView.getSurfaceTexture();
            if (surfaceTexture != null) {
                this.mediaPlayer.setSurface(new Surface(surfaceTexture));
            }
            this.mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.mediaPlayer.setSurface(new Surface(surfaceTexture));
    }

    public static class IjkVideoTextureView extends TextureView {
        private int mVideoHeight;
        private int mVideoRotation;
        private int mVideoWidth;

        public IjkVideoTextureView(Context context) {
            super(context);
        }

        public void adjustVideoSize(int i, int i2, int i3) {
            this.mVideoWidth = i;
            this.mVideoHeight = i2;
            this.mVideoRotation = i3;
            setRotation(i3);
            requestLayout();
        }

        @Override // android.view.View
        protected void onMeasure(int i, int i2) {
            int i3;
            int i4;
            int defaultSize = getDefaultSize(this.mVideoWidth, i);
            int defaultSize2 = getDefaultSize(this.mVideoHeight, i2);
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i2);
            int i5 = this.mVideoWidth;
            if (i5 > 0 && (i3 = this.mVideoHeight) > 0) {
                if (mode == 1073741824 && mode2 == 1073741824) {
                    if (i5 * size2 < size * i3) {
                        defaultSize = (i5 * size2) / i3;
                        defaultSize2 = size2;
                    } else {
                        if (i5 * size2 > size * i3) {
                            defaultSize2 = (i3 * size) / i5;
                            defaultSize = size;
                        }
                        defaultSize = size;
                        defaultSize2 = size2;
                    }
                } else if (mode == 1073741824) {
                    int i6 = (i3 * size) / i5;
                    if (mode2 != Integer.MIN_VALUE || i6 <= size2) {
                        defaultSize2 = i6;
                        defaultSize = size;
                    }
                    defaultSize = size;
                    defaultSize2 = size2;
                } else if (mode2 == 1073741824) {
                    defaultSize = (i5 * size2) / i3;
                    if (mode == Integer.MIN_VALUE && defaultSize > size) {
                        defaultSize = size;
                    }
                    defaultSize2 = size2;
                } else {
                    if (mode2 != Integer.MIN_VALUE || i3 <= size2) {
                        defaultSize = i5;
                        i4 = i3;
                    } else {
                        defaultSize = (size2 * i5) / i3;
                        i4 = size2;
                    }
                    if (mode != Integer.MIN_VALUE || defaultSize <= size) {
                        defaultSize2 = i4;
                    } else {
                        defaultSize2 = (i3 * size) / i5;
                        defaultSize = size;
                    }
                }
            }
            setMeasuredDimension(defaultSize, defaultSize2);
            if ((this.mVideoRotation + RotationOptions.ROTATE_180) % RotationOptions.ROTATE_180 != 0) {
                int[] iArrScaleSize = IjkPlayerView.scaleSize(size, size2, defaultSize2, defaultSize);
                setScaleX(iArrScaleSize[0] / defaultSize2);
                setScaleY(iArrScaleSize[1] / defaultSize);
            }
        }
    }

    public static int[] scaleSize(int i, int i2, int i3, int i4) {
        float f = i;
        float f2 = i2;
        float f3 = i3 / i4;
        if (f3 < f / f2) {
            i = (int) (f2 * f3);
        } else {
            i2 = (int) (f / f3);
        }
        return new int[]{i, i2};
    }

    public void release() {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.release();
            this.mediaPlayer.setOnPreparedListener((IMediaPlayer.OnPreparedListener) null);
            this.mediaPlayer.setOnCompletionListener((IMediaPlayer.OnCompletionListener) null);
            this.mediaPlayer.setOnErrorListener((IMediaPlayer.OnErrorListener) null);
            this.mediaPlayer.setOnInfoListener((IMediaPlayer.OnInfoListener) null);
            this.mediaPlayer = null;
        }
    }
}
