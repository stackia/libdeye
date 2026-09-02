package com.deye.activity;

import android.os.Bundle;
import android.view.View;
import co.lujun.androidtagview.TagView;
import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.databinding.ActivitySearchBinding;
import com.stub.StubApp;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SearchActivity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\u0006H\u0002J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH\u0002J\u0012\u0010\f\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/deye/activity/SearchActivity;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "mBinding", "Lcom/mxchipapp/databinding/ActivitySearchBinding;", "addKeyword", "", "keyword", "", "doSearch", "getKeywords", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class SearchActivity extends BaseActivity {
    private ActivitySearchBinding mBinding;

    static {
        StubApp.interface11(13859);
    }

    private final native void addKeyword(String keyword);

    private final native void doSearch();

    private final native List<String> getKeywords();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onCreate$lambda$0(SearchActivity searchActivity, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onCreate$lambda$1(SearchActivity searchActivity, View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    /* compiled from: SearchActivity.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u001a\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\u000b"}, d2 = {"com/deye/activity/SearchActivity$onCreate$3", "Lco/lujun/androidtagview/TagView$OnTagClickListener;", "onSelectedTagDrag", "", "position", "", "text", "", "onTagClick", "onTagCrossClick", "onTagLongClick", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.activity.SearchActivity$onCreate$3, reason: invalid class name */
    public static final class AnonymousClass3 implements TagView.OnTagClickListener {
        public void onSelectedTagDrag(int position, String text) {
        }

        public void onTagCrossClick(int position) {
        }

        public void onTagLongClick(int position, String text) {
        }

        AnonymousClass3() {
        }

        public void onTagClick(int position, String text) {
            ActivitySearchBinding activitySearchBinding = SearchActivity.this.mBinding;
            if (activitySearchBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mBinding");
                activitySearchBinding = null;
            }
            activitySearchBinding.editText.setText(text);
        }
    }
}
