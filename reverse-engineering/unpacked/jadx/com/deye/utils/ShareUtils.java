package com.deye.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import com.bumptech.glide.request.RequestOptions;
import com.deye.utils.ShareUtils;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.mob.MobSDK;
import com.mxchipapp.R;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;
import com.othershe.nicedialog.ViewConvertListener;
import com.othershe.nicedialog.ViewHolder;
import com.stub.StubApp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShareUtils.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004J\u001e\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u0004J \u0010\u000f\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011¨\u0006\u0013"}, d2 = {"Lcom/deye/utils/ShareUtils;", "", "()V", "getBitmapFromView", "Landroid/graphics/Bitmap;", "view", "Landroid/view/View;", "savePic", "", "context", "Landroidx/fragment/app/FragmentActivity;", "bitmap", "share", "isWechat", "", "showShareDialog", "productId", "", "productName", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class ShareUtils {
    public static final ShareUtils INSTANCE = new ShareUtils();

    private ShareUtils() {
    }

    /* compiled from: ShareUtils.kt */
    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0014¨\u0006\b"}, d2 = {"com/deye/utils/ShareUtils$showShareDialog$1", "Lcom/othershe/nicedialog/ViewConvertListener;", "convertView", "", "viewHolder", "Lcom/othershe/nicedialog/ViewHolder;", "baseNiceDialog", "Lcom/othershe/nicedialog/BaseNiceDialog;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.utils.ShareUtils$showShareDialog$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01951 extends ViewConvertListener {
        final /* synthetic */ FragmentActivity $context;
        final /* synthetic */ String $productId;
        final /* synthetic */ String $productName;

        C01951(String str, FragmentActivity fragmentActivity, String str2) {
            this.$productName = str;
            this.$context = fragmentActivity;
            this.$productId = str2;
        }

        @Override // com.othershe.nicedialog.ViewConvertListener
        protected void convertView(ViewHolder viewHolder, final BaseNiceDialog baseNiceDialog) {
            Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
            Intrinsics.checkNotNullParameter(baseNiceDialog, "baseNiceDialog");
            ImageView imageView = (ImageView) viewHolder.getView(R.id.iv_product);
            final FrameLayout frameLayout = (FrameLayout) viewHolder.getView(R.id.fra_content);
            TextView textView = (TextView) viewHolder.getView(R.id.tv_product_name);
            TextView textView2 = (TextView) viewHolder.getView(R.id.tv_info);
            SpannableString spannableString = new SpannableString(StubApp.getString2(14429));
            String shareContent = MMKVUtils.INSTANCE.getShareContent();
            if (shareContent != null && shareContent.length() != 0) {
                spannableString = new SpannableString(shareContent);
            }
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor(StubApp.getString2(13225))), 0, 1, 33);
            spannableString.setSpan(new AbsoluteSizeSpan(24, true), 0, 1, 33);
            textView2.setText(spannableString);
            textView.setText(this.$productName);
            new RequestOptions();
            frameLayout.setOutlineProvider(new ViewOutlineProvider() { // from class: com.deye.utils.ShareUtils$showShareDialog$1$convertView$1
                @Override // android.view.ViewOutlineProvider
                public void getOutline(View view, Outline outline) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    Intrinsics.checkNotNullParameter(outline, "outline");
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 50.0f);
                }
            });
            frameLayout.setClipToOutline(true);
            imageView.setImageBitmap(BitmapFactory.decodeResource(this.$context.getResources(), PanelHelper.INSTANCE.getDrawable(this.$productId)));
            viewHolder.setOnClickListener(R.id.iv_close, new View.OnClickListener() { // from class: com.deye.utils.ShareUtils$showShareDialog$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ShareUtils.C01951.convertView$lambda$0(baseNiceDialog, view);
                }
            });
            final FragmentActivity fragmentActivity = this.$context;
            viewHolder.setOnClickListener(R.id.ll_wechat, new View.OnClickListener() { // from class: com.deye.utils.ShareUtils$showShareDialog$1$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ShareUtils.C01951.convertView$lambda$1(baseNiceDialog, fragmentActivity, frameLayout, view);
                }
            });
            final FragmentActivity fragmentActivity2 = this.$context;
            viewHolder.setOnClickListener(R.id.ll_moments, new View.OnClickListener() { // from class: com.deye.utils.ShareUtils$showShareDialog$1$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ShareUtils.C01951.convertView$lambda$2(baseNiceDialog, fragmentActivity2, frameLayout, view);
                }
            });
            final FragmentActivity fragmentActivity3 = this.$context;
            viewHolder.setOnClickListener(R.id.ll_save, new View.OnClickListener() { // from class: com.deye.utils.ShareUtils$showShareDialog$1$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ShareUtils.C01951.convertView$lambda$3(fragmentActivity3, frameLayout, baseNiceDialog, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void convertView$lambda$0(BaseNiceDialog baseNiceDialog, View view) {
            Intrinsics.checkNotNullParameter(baseNiceDialog, "$baseNiceDialog");
            baseNiceDialog.dismiss();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void convertView$lambda$1(BaseNiceDialog baseNiceDialog, FragmentActivity fragmentActivity, FrameLayout frameLayout, View view) {
            Intrinsics.checkNotNullParameter(baseNiceDialog, "$baseNiceDialog");
            baseNiceDialog.dismiss();
            ShareUtils shareUtils = ShareUtils.INSTANCE;
            ShareUtils shareUtils2 = ShareUtils.INSTANCE;
            Intrinsics.checkNotNull(frameLayout);
            shareUtils.share(fragmentActivity, true, shareUtils2.getBitmapFromView(frameLayout));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void convertView$lambda$2(BaseNiceDialog baseNiceDialog, FragmentActivity fragmentActivity, FrameLayout frameLayout, View view) {
            Intrinsics.checkNotNullParameter(baseNiceDialog, "$baseNiceDialog");
            baseNiceDialog.dismiss();
            ShareUtils shareUtils = ShareUtils.INSTANCE;
            ShareUtils shareUtils2 = ShareUtils.INSTANCE;
            Intrinsics.checkNotNull(frameLayout);
            shareUtils.share(fragmentActivity, false, shareUtils2.getBitmapFromView(frameLayout));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void convertView$lambda$3(final FragmentActivity fragmentActivity, final FrameLayout frameLayout, final BaseNiceDialog baseNiceDialog, View view) {
            Intrinsics.checkNotNullParameter(baseNiceDialog, "$baseNiceDialog");
            XXPermissions.with((Context) fragmentActivity).permission(StubApp.getString2(14428)).request(new OnPermissionCallback() { // from class: com.deye.utils.ShareUtils$showShareDialog$1$convertView$5$1
                public void onGranted(List<String> permissions, boolean allGranted) throws IOException {
                    Intrinsics.checkNotNullParameter(permissions, "permissions");
                    if (!allGranted) {
                        BaseUtils.showShortToast(fragmentActivity, StubApp.getString2(14427));
                        return;
                    }
                    ShareUtils shareUtils = ShareUtils.INSTANCE;
                    FragmentActivity fragmentActivity2 = fragmentActivity;
                    ShareUtils shareUtils2 = ShareUtils.INSTANCE;
                    FrameLayout fraContent = frameLayout;
                    Intrinsics.checkNotNullExpressionValue(fraContent, "$fraContent");
                    shareUtils.savePic(fragmentActivity2, shareUtils2.getBitmapFromView(fraContent));
                    baseNiceDialog.dismiss();
                }

                public void onDenied(List<String> permissions, boolean doNotAskAgain) {
                    Intrinsics.checkNotNullParameter(permissions, "permissions");
                    if (doNotAskAgain) {
                        BaseUtils.showShortToast(fragmentActivity, StubApp.getString2(14425));
                        XXPermissions.startPermissionActivity(fragmentActivity, permissions);
                    } else {
                        BaseUtils.showShortToast(fragmentActivity, StubApp.getString2(14426));
                    }
                }
            });
        }
    }

    public final void showShareDialog(FragmentActivity context, String productId, String productName) {
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(productName, "productName");
        if (context == null) {
            return;
        }
        NiceDialog.init().setLayoutId(R.layout.dialog_share).setConvertListener(new C01951(productName, context, productId)).setOutCancel(true).show(context.getSupportFragmentManager());
    }

    public final void share(final FragmentActivity context, boolean isWechat, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        OnekeyShare onekeyShare = new OnekeyShare();
        ShareSDK.getPlatform(Wechat.NAME);
        if (isWechat) {
            onekeyShare.setPlatform(Wechat.NAME);
        } else {
            onekeyShare.setPlatform(WechatMoments.NAME);
        }
        onekeyShare.setText("");
        onekeyShare.setImageData(bitmap);
        onekeyShare.setCallback(new PlatformActionListener() { // from class: com.deye.utils.ShareUtils.share.1
            public void onCancel(Platform platform, int i) {
            }

            public void onError(Platform platform, int i, Throwable throwable) {
            }

            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                BaseUtils.showShortToast(context, StubApp.getString2(14424));
            }
        });
        onekeyShare.show(MobSDK.getContext());
    }

    public final Bitmap getBitmapFromView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        }
        view.draw(canvas);
        return bitmapCreateBitmap;
    }

    public final void savePic(FragmentActivity context, Bitmap bitmap) throws IOException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        String string = Environment.getExternalStorageDirectory().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        File file = new File(string, StubApp.getString2(14430) + System.currentTimeMillis() + StubApp.getString2(4824));
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        FileOutputStream fileOutputStream2 = fileOutputStream;
        fileOutputStream2.flush();
        fileOutputStream2.close();
        MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
        BaseUtils.showShortToast((Context) context, StubApp.getString2(14431));
    }
}
