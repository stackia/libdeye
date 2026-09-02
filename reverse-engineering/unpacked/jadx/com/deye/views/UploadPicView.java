package com.deye.views;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.location.support.bean.location.AmapLocationNetwork;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.deye.utils.BaseUtils;
import com.deye.utils.PhotoPickerFileHelper;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mxchipapp.R;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.api.http.RetrofitUploadService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.reactivestreams.Subscription;

/* compiled from: UploadPicView.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u00016B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001c\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020#J\u0010\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u00020#H\u0002J\u0010\u00100\u001a\u0002012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\b\u00102\u001a\u000201H\u0002J\u000e\u00103\u001a\u0002012\u0006\u00104\u001a\u00020\u001dJ\b\u00105\u001a\u000201H\u0007R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u00067"}, d2 = {"Lcom/deye/views/UploadPicView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "ivDelete", "Landroid/widget/ImageView;", "getIvDelete", "()Landroid/widget/ImageView;", "setIvDelete", "(Landroid/widget/ImageView;)V", "ivPic", "getIvPic", "setIvPic", "listener", "Lcom/deye/views/UploadPicView$IUploadPicListener;", "getListener", "()Lcom/deye/views/UploadPicView$IUploadPicListener;", "setListener", "(Lcom/deye/views/UploadPicView$IUploadPicListener;)V", "llUploading", "Landroid/widget/LinearLayout;", "localMedia", "Lcom/luck/picture/lib/entity/LocalMedia;", "getLocalMedia", "()Lcom/luck/picture/lib/entity/LocalMedia;", "setLocalMedia", "(Lcom/luck/picture/lib/entity/LocalMedia;)V", "uploadUrl", "", "getUploadUrl", "()Ljava/lang/String;", "setUploadUrl", "(Ljava/lang/String;)V", "filesToMultipartBodyParts", "", "Lokhttp3/MultipartBody$Part;", AmapLocationNetwork.TYPE_OFFLINE_CELL, "Ljava/io/File;", "mimeType", "getFile", "path", "init", "", "loadLocal", "setDate", "media", "startUpload", "IUploadPicListener", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class UploadPicView extends FrameLayout {
    private ImageView ivDelete;
    private ImageView ivPic;
    private IUploadPicListener listener;
    private LinearLayout llUploading;
    private LocalMedia localMedia;
    private String uploadUrl;

    /* compiled from: UploadPicView.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/deye/views/UploadPicView$IUploadPicListener;", "", "onDelete", "", "onUploadFail", "onUploadSuccess", "url", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface IUploadPicListener {
        void onDelete();

        void onUploadFail();

        void onUploadSuccess(String url);
    }

    public final ImageView getIvPic() {
        return this.ivPic;
    }

    public final void setIvPic(ImageView imageView) {
        this.ivPic = imageView;
    }

    public final ImageView getIvDelete() {
        return this.ivDelete;
    }

    public final void setIvDelete(ImageView imageView) {
        this.ivDelete = imageView;
    }

    public final String getUploadUrl() {
        return this.uploadUrl;
    }

    public final void setUploadUrl(String str) {
        this.uploadUrl = str;
    }

    public final LocalMedia getLocalMedia() {
        return this.localMedia;
    }

    public final void setLocalMedia(LocalMedia localMedia) {
        this.localMedia = localMedia;
    }

    public final IUploadPicListener getListener() {
        return this.listener;
    }

    public final void setListener(IUploadPicListener iUploadPicListener) {
        this.listener = iUploadPicListener;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadPicView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadPicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadPicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    public final void init(Context context) {
        addView(LayoutInflater.from(context).inflate(R.layout.item_uplaod_pic, (ViewGroup) this, false), -2, -2);
        this.ivPic = (ImageView) findViewById(R.id.iv_pic);
        this.ivDelete = (ImageView) findViewById(R.id.iv_delete);
        this.llUploading = (LinearLayout) findViewById(R.id.ll_uploading);
        ImageView imageView = this.ivDelete;
        Intrinsics.checkNotNull(imageView);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.UploadPicView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UploadPicView.init$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(UploadPicView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        IUploadPicListener iUploadPicListener = this$0.listener;
        if (iUploadPicListener != null) {
            iUploadPicListener.onDelete();
        }
    }

    public final void setDate(LocalMedia media) {
        Intrinsics.checkNotNullParameter(media, "media");
        this.localMedia = media;
        startUpload();
    }

    public final void startUpload() {
        LocalMedia localMedia = this.localMedia;
        Intrinsics.checkNotNull(localMedia);
        String availablePath = localMedia.getAvailablePath();
        Intrinsics.checkNotNullExpressionValue(availablePath, "getAvailablePath(...)");
        File file = getFile(availablePath);
        RetrofitUploadService uploadService = RetrofitManager.INSTANCE.getUploadService();
        LocalMedia localMedia2 = this.localMedia;
        Intrinsics.checkNotNull(localMedia2);
        String mimeType = localMedia2.getMimeType();
        Intrinsics.checkNotNullExpressionValue(mimeType, "getMimeType(...)");
        uploadService.fileUpload(filesToMultipartBodyParts(file, mimeType)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer() { // from class: com.deye.views.UploadPicView.startUpload.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Subscription it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                LinearLayout linearLayout = UploadPicView.this.llUploading;
                if (linearLayout == null) {
                    return;
                }
                linearLayout.setVisibility(0);
            }
        }).doOnComplete(new Action() { // from class: com.deye.views.UploadPicView$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                UploadPicView.startUpload$lambda$1(this.f$0);
            }
        }).subscribe(new Consumer() { // from class: com.deye.views.UploadPicView.startUpload.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<Object> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseResult.MetaBean meta = it2.getMeta();
                Intrinsics.checkNotNull(meta);
                if (meta.getCode() == 0) {
                    Object data = it2.getData();
                    Intrinsics.checkNotNull(data, "null cannot be cast to non-null type kotlin.collections.List<*>");
                    UploadPicView uploadPicView = UploadPicView.this;
                    Object obj = ((List) data).get(0);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                    uploadPicView.setUploadUrl((String) obj);
                    ImageView ivDelete = UploadPicView.this.getIvDelete();
                    if (ivDelete != null) {
                        ivDelete.setVisibility(0);
                    }
                    ImageView ivPic = UploadPicView.this.getIvPic();
                    if (ivPic != null) {
                        ivPic.setVisibility(0);
                    }
                    UploadPicView.this.loadLocal();
                    IUploadPicListener listener = UploadPicView.this.getListener();
                    if (listener != null) {
                        String uploadUrl = UploadPicView.this.getUploadUrl();
                        Intrinsics.checkNotNull(uploadUrl);
                        listener.onUploadSuccess(uploadUrl);
                        return;
                    }
                    return;
                }
                BaseResult.MetaBean meta2 = it2.getMeta();
                BaseUtils.showShortToast(String.valueOf(meta2 != null ? meta2.getMessage() : null));
                IUploadPicListener listener2 = UploadPicView.this.getListener();
                if (listener2 != null) {
                    listener2.onUploadFail();
                }
            }
        }, new Consumer() { // from class: com.deye.views.UploadPicView.startUpload.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                IUploadPicListener listener = UploadPicView.this.getListener();
                if (listener != null) {
                    listener.onUploadFail();
                }
                it2.printStackTrace();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startUpload$lambda$1(UploadPicView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LinearLayout linearLayout = this$0.llUploading;
        if (linearLayout == null) {
            return;
        }
        linearLayout.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void loadLocal() {
        String str;
        LocalMedia localMedia = this.localMedia;
        Intrinsics.checkNotNull(localMedia);
        String availablePath = localMedia.getAvailablePath();
        RoundedCornersTransform roundedCornersTransform = new RoundedCornersTransform(getContext(), DensityUtil.dp2px(8.0f), true, true, true, true);
        ImageView imageView = this.ivPic;
        Intrinsics.checkNotNull(imageView);
        RequestManager requestManagerWith = Glide.with(imageView);
        if (PictureMimeType.isContent(availablePath)) {
            LocalMedia localMedia2 = this.localMedia;
            Intrinsics.checkNotNull(localMedia2);
            if (!localMedia2.isCut()) {
                LocalMedia localMedia3 = this.localMedia;
                Intrinsics.checkNotNull(localMedia3);
                str = !localMedia3.isCompressed() ? Uri.parse(availablePath) : availablePath;
            }
        }
        RequestBuilder requestBuilderDiskCacheStrategy = requestManagerWith.load(str).centerCrop().apply((BaseRequestOptions<?>) new RequestOptions().transform(roundedCornersTransform)).diskCacheStrategy(DiskCacheStrategy.ALL);
        ImageView imageView2 = this.ivPic;
        Intrinsics.checkNotNull(imageView2);
        requestBuilderDiskCacheStrategy.into(imageView2);
    }

    private final File getFile(String path) {
        if (PictureMimeType.isContent(path)) {
            PhotoPickerFileHelper photoPickerFileHelper = PhotoPickerFileHelper.INSTANCE;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            Uri uri = Uri.parse(path);
            Intrinsics.checkNotNullExpressionValue(uri, "parse(...)");
            File fileCopyUriToCache = photoPickerFileHelper.copyUriToCache(context, uri, StubApp.getString2(14644));
            return fileCopyUriToCache != null ? fileCopyUriToCache : new File(path);
        }
        return new File(path);
    }

    public final List<MultipartBody.Part> filesToMultipartBodyParts(File file, String mimeType) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        ArrayList arrayList = new ArrayList();
        RequestBody requestBodyCreate = RequestBody.Companion.create(MediaType.Companion.parse(mimeType), file);
        arrayList.add(MultipartBody.Part.Companion.createFormData(StubApp.getString2(14645), file.getName(), requestBodyCreate));
        return arrayList;
    }
}
