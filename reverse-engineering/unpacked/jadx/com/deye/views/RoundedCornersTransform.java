package com.deye.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: RoundedCornersTransform.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B?\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b¢\u0006\u0002\u0010\fJ\u0013\u0010\"\u001a\u00020\b2\b\u0010#\u001a\u0004\u0018\u00010$H\u0096\u0002J\b\u0010%\u001a\u00020&H\u0016J8\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020(2\u0006\u0010\u0003\u001a\u00020\u00042\u000e\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020(2\u0006\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00020&H\u0016J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0016R\u0016\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u000b\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015R\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015¨\u00060"}, d2 = {"Lcom/deye/views/RoundedCornersTransform;", "Lcom/bumptech/glide/load/Transformation;", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", "radius", "", "leftTop", "", "rightTop", "leftBottom", "rightBottom", "(Landroid/content/Context;FZZZZ)V", "id", "", "kotlin.jvm.PlatformType", "idBytes", "", "getLeftBottom", "()Z", "setLeftBottom", "(Z)V", "getLeftTop", "setLeftTop", "mBitmapPool", "Lcom/bumptech/glide/load/engine/bitmap_recycle/BitmapPool;", "getRadius", "()F", "setRadius", "(F)V", "getRightBottom", "setRightBottom", "getRightTop", "setRightTop", "equals", "other", "", "hashCode", "", "transform", "Lcom/bumptech/glide/load/engine/Resource;", "resource", "outWidth", "outHeight", "updateDiskCacheKey", "", "messageDigest", "Ljava/security/MessageDigest;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class RoundedCornersTransform implements Transformation<Bitmap> {
    private final String id;
    private final byte[] idBytes;
    private boolean leftBottom;
    private boolean leftTop;
    private final BitmapPool mBitmapPool;
    private float radius;
    private boolean rightBottom;
    private boolean rightTop;

    public RoundedCornersTransform(Context context, float f, boolean z, boolean z2, boolean z3, boolean z4) {
        this.radius = f;
        this.leftTop = z;
        this.rightTop = z2;
        this.leftBottom = z3;
        this.rightBottom = z4;
        Intrinsics.checkNotNull(context);
        BitmapPool bitmapPool = Glide.get(context).getBitmapPool();
        Intrinsics.checkNotNullExpressionValue(bitmapPool, "getBitmapPool(...)");
        this.mBitmapPool = bitmapPool;
        String id = getClass().getName();
        this.id = id;
        Intrinsics.checkNotNullExpressionValue(id, "id");
        byte[] bytes = id.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        this.idBytes = bytes;
    }

    public /* synthetic */ RoundedCornersTransform(Context context, float f, boolean z, boolean z2, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, f, (i & 4) != 0 ? true : z, (i & 8) != 0 ? true : z2, (i & 16) != 0 ? true : z3, (i & 32) != 0 ? true : z4);
    }

    public final float getRadius() {
        return this.radius;
    }

    public final void setRadius(float f) {
        this.radius = f;
    }

    public final boolean getLeftTop() {
        return this.leftTop;
    }

    public final void setLeftTop(boolean z) {
        this.leftTop = z;
    }

    public final boolean getRightTop() {
        return this.rightTop;
    }

    public final void setRightTop(boolean z) {
        this.rightTop = z;
    }

    public final boolean getLeftBottom() {
        return this.leftBottom;
    }

    public final void setLeftBottom(boolean z) {
        this.leftBottom = z;
    }

    public final boolean getRightBottom() {
        return this.rightBottom;
    }

    public final void setRightBottom(boolean z) {
        this.rightBottom = z;
    }

    @Override // com.bumptech.glide.load.Transformation
    public Resource<Bitmap> transform(Context context, Resource<Bitmap> resource, int outWidth, int outHeight) {
        int height;
        int width;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(resource, "resource");
        Bitmap bitmap = resource.get();
        Intrinsics.checkNotNullExpressionValue(bitmap, "get(...)");
        Bitmap bitmap2 = bitmap;
        if (outWidth > outHeight) {
            float f = outHeight;
            float f2 = outWidth;
            height = bitmap2.getWidth();
            width = (int) (bitmap2.getWidth() * (f / f2));
            if (width > bitmap2.getHeight()) {
                width = bitmap2.getHeight();
                height = (int) (bitmap2.getHeight() * (f2 / f));
            }
        } else if (outWidth < outHeight) {
            float f3 = outWidth;
            float f4 = outHeight;
            int height2 = bitmap2.getHeight();
            int height3 = (int) (bitmap2.getHeight() * (f3 / f4));
            if (height3 > bitmap2.getWidth()) {
                height = bitmap2.getWidth();
                width = (int) (bitmap2.getWidth() * (f4 / f3));
            } else {
                height = height3;
                width = height2;
            }
        } else {
            height = bitmap2.getHeight();
            width = height;
        }
        this.radius *= width / outHeight;
        Bitmap bitmap3 = this.mBitmapPool.get(height, width, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmap3, "get(...)");
        Canvas canvas = new Canvas(bitmap3);
        Paint paint = new Paint();
        BitmapShader bitmapShader = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        int width2 = (bitmap2.getWidth() - height) / 2;
        int height4 = (bitmap2.getHeight() - width) / 2;
        if (width2 != 0 || height4 != 0) {
            Matrix matrix = new Matrix();
            matrix.setTranslate(-width2, -height4);
            bitmapShader.setLocalMatrix(matrix);
        }
        paint.setShader(bitmapShader);
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight());
        float f5 = this.radius;
        canvas.drawRoundRect(rectF, f5, f5, paint);
        if (!this.leftTop) {
            float f6 = this.radius;
            canvas.drawRect(0.0f, 0.0f, f6, f6, paint);
        }
        if (!this.rightTop) {
            canvas.drawRect(canvas.getWidth() - this.radius, 0.0f, canvas.getWidth(), this.radius, paint);
        }
        if (!this.leftBottom) {
            float height5 = canvas.getHeight();
            float f7 = this.radius;
            canvas.drawRect(0.0f, height5 - f7, f7, canvas.getHeight(), paint);
        }
        if (!this.rightBottom) {
            canvas.drawRect(canvas.getWidth() - this.radius, canvas.getHeight() - this.radius, canvas.getWidth(), canvas.getHeight(), paint);
        }
        BitmapResource bitmapResourceObtain = BitmapResource.obtain(bitmap3, this.mBitmapPool);
        Intrinsics.checkNotNull(bitmapResourceObtain);
        return bitmapResourceObtain;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object other) {
        return other instanceof RoundedCornersTransform;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        Intrinsics.checkNotNullParameter(messageDigest, "messageDigest");
        messageDigest.update(this.idBytes);
    }
}
