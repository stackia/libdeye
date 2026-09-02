package com.deye.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.facebook.imagepipeline.common.RotationOptions;
import com.luck.picture.lib.engine.ImageEngine;
import com.luck.picture.lib.utils.ActivityCompatHelper;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class GlideEngine implements ImageEngine {
    /* synthetic */ GlideEngine(GlideEngineIA glideEngineIA) {
        this();
    }

    public void loadImage(Context context, String str, ImageView imageView) {
        if (ActivityCompatHelper.assertValidRequest(context)) {
            Glide.with(context).load(str).into(imageView);
        }
    }

    public void loadImage(Context context, ImageView imageView, String str, int i, int i2) {
        if (ActivityCompatHelper.assertValidRequest(context)) {
            Glide.with(context).load(str).override(i, i2).into(imageView);
        }
    }

    public void loadAlbumCover(Context context, String str, ImageView imageView) {
        if (ActivityCompatHelper.assertValidRequest(context)) {
            Glide.with(context).asBitmap().load(str).override(RotationOptions.ROTATE_180, RotationOptions.ROTATE_180).sizeMultiplier(0.5f).transform(new CenterCrop(), new RoundedCorners(8)).into(imageView);
        }
    }

    public void loadGridImage(Context context, String str, ImageView imageView) {
        if (ActivityCompatHelper.assertValidRequest(context)) {
            Glide.with(context).load(str).override(200, 200).centerCrop().into(imageView);
        }
    }

    public void pauseRequests(Context context) {
        if (ActivityCompatHelper.assertValidRequest(context)) {
            Glide.with(context).pauseRequests();
        }
    }

    public void resumeRequests(Context context) {
        if (ActivityCompatHelper.assertValidRequest(context)) {
            Glide.with(context).resumeRequests();
        }
    }

    private GlideEngine() {
    }

    private static final class InstanceHolder {
        static final GlideEngine instance = new GlideEngine(null);

        private InstanceHolder() {
        }
    }

    public static GlideEngine createGlideEngine() {
        return InstanceHolder.instance;
    }
}
