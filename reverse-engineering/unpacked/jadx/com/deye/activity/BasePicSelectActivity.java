package com.deye.activity;

import android.net.Uri;
import android.os.Bundle;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import com.deye.activity.device.base.BaseActivity;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.style.PictureSelectorStyle;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* compiled from: BasePicSelectActivity.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010J\u000e\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0004J\u0016\u0010\u0013\u001a\u00020\u000b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0002J\u0012\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J \u0010\u001a\u001a\u00020\u000b2\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010H\u0016J\u0010\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u0004J\u0006\u0010\u001d\u001a\u00020\u000bJ\u000e\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/deye/activity/BasePicSelectActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "pendingMaxCount", "", "photoPickerLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroidx/activity/result/PickVisualMediaRequest;", "selectorStyle", "Lcom/luck/picture/lib/style/PictureSelectorStyle;", "doPreview", "", "position", "result", "Ljava/util/ArrayList;", "Lcom/luck/picture/lib/entity/LocalMedia;", "Lkotlin/collections/ArrayList;", "doSelectPic", "maxCount", "handlePhotoPickerResult", "uris", "", "Landroid/net/Uri;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPicResult", "requestPermissionGetPic", "maxCont", "setStyle", "startSelectPic", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class BasePicSelectActivity extends BaseActivity {
    private final PictureSelectorStyle selectorStyle = new PictureSelectorStyle();
    private int pendingMaxCount = 1;
    private final ActivityResultLauncher<PickVisualMediaRequest> photoPickerLauncher = registerForActivityResult((ActivityResultContract) new ActivityResultContracts.PickMultipleVisualMedia(4), new ActivityResultCallback() { // from class: com.deye.activity.BasePicSelectActivity$$ExternalSyntheticLambda0
        public final void onActivityResult(Object obj) {
            BasePicSelectActivity.photoPickerLauncher$lambda$0(this.f$0, (List) obj);
        }
    });

    static {
        StubApp.interface11(13849);
    }

    private final native void handlePhotoPickerResult(List<? extends Uri> uris);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void photoPickerLauncher$lambda$0(BasePicSelectActivity basePicSelectActivity, List list);

    public final native void doPreview(int position, ArrayList<LocalMedia> result);

    public final native void doSelectPic(int maxCount);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    public native void onPicResult(ArrayList<LocalMedia> result);

    public final native void requestPermissionGetPic(int maxCont);

    public final native void setStyle();

    public final native void startSelectPic(int maxCount);

    public static /* synthetic */ void requestPermissionGetPic$default(BasePicSelectActivity basePicSelectActivity, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException(StubApp.getString2(13224));
        }
        if ((i2 & 1) != 0) {
            i = 1;
        }
        basePicSelectActivity.requestPermissionGetPic(i);
    }
}
