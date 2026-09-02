package com.deye.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.deye.entity.PermissionJsonBean;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.mxchipapp.R;
import com.stub.StubApp;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PermissionItemView.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u0015\u001a\u00020\u0016J\u0011\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0002\u0010\u001aJ\u0006\u0010\u001b\u001a\u00020\u0016J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/deye/views/PermissionItemView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "itemData", "Lcom/deye/entity/PermissionJsonBean;", "line", "Landroid/view/View;", "llOption", "Landroid/widget/LinearLayout;", "tvOption", "Landroid/widget/TextView;", "tvPermissionMsg", "tvPermissionName", "check", "", "getPermissionArray", "", "", "()[Ljava/lang/String;", "hideLine", "init", "setData", "item", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class PermissionItemView extends FrameLayout {
    private Context context;
    private PermissionJsonBean itemData;
    private View line;
    private LinearLayout llOption;
    private TextView tvOption;
    private TextView tvPermissionMsg;
    private TextView tvPermissionName;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PermissionItemView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PermissionItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PermissionItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    private final void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.permission_item_view, (ViewGroup) this, true);
        View viewFindViewById = findViewById(R.id.tv_permission_name);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.tvPermissionName = (TextView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.tv_permission_msg);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.tvPermissionMsg = (TextView) viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.tv_option);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        this.tvOption = (TextView) viewFindViewById3;
        View viewFindViewById4 = findViewById(R.id.ll_option);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
        this.llOption = (LinearLayout) viewFindViewById4;
        View viewFindViewById5 = findViewById(R.id.line);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
        this.line = viewFindViewById5;
    }

    public final void setData(PermissionJsonBean item) {
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = this.tvPermissionName;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvPermissionName");
            textView = null;
        }
        textView.setText(item.getName());
        TextView textView3 = this.tvPermissionMsg;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvPermissionMsg");
        } else {
            textView2 = textView3;
        }
        textView2.setText(item.getContent());
        this.itemData = item;
        check();
    }

    public final void hideLine() {
        View view = this.line;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("line");
            view = null;
        }
        view.setVisibility(8);
    }

    public final void check() {
        final String[] permissionArray = getPermissionArray();
        Context context = this.context;
        String string2 = StubApp.getString2(14628);
        LinearLayout linearLayout = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            context = null;
        }
        final boolean zIsGranted = XXPermissions.isGranted(context, new String[][]{permissionArray});
        String string22 = StubApp.getString2(14629);
        if (zIsGranted) {
            TextView textView = this.tvOption;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                textView = null;
            }
            Context context2 = this.context;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                context2 = null;
            }
            textView.setText(context2.getString(R.string.permission_enabled));
            TextView textView2 = this.tvOption;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                textView2 = null;
            }
            Context context3 = this.context;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                context3 = null;
            }
            textView2.setTextColor(context3.getColor(R.color.dark40));
        } else {
            TextView textView3 = this.tvOption;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                textView3 = null;
            }
            Context context4 = this.context;
            if (context4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
                context4 = null;
            }
            textView3.setText(context4.getString(R.string.permission_enable));
            TextView textView4 = this.tvOption;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string22);
                textView4 = null;
            }
            textView4.setTextColor(Color.parseColor(StubApp.getString2(13225)));
        }
        LinearLayout linearLayout2 = this.llOption;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("llOption");
        } else {
            linearLayout = linearLayout2;
        }
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.deye.views.PermissionItemView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PermissionItemView.check$lambda$0(zIsGranted, this, permissionArray, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void check$lambda$0(boolean z, final PermissionItemView this$0, String[] permissionArray, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(permissionArray, "$permissionArray");
        Context context = null;
        String string2 = StubApp.getString2(14628);
        if (z) {
            Context context2 = this$0.context;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                context = context2;
            }
            XXPermissions.startPermissionActivity(context);
            return;
        }
        Context context3 = this$0.context;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
        } else {
            context = context3;
        }
        XXPermissions.with(context).permission(new String[][]{permissionArray}).request(new OnPermissionCallback() { // from class: com.deye.views.PermissionItemView$check$1$1
            public void onGranted(List<String> permissions, boolean allGranted) {
                Intrinsics.checkNotNullParameter(permissions, "permissions");
                if (allGranted) {
                    this.this$0.check();
                }
            }

            public void onDenied(List<String> permissions, boolean doNotAskAgain) {
                Intrinsics.checkNotNullParameter(permissions, "permissions");
                if (doNotAskAgain) {
                    Context context4 = this.this$0.context;
                    if (context4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("context");
                        context4 = null;
                    }
                    XXPermissions.startPermissionActivity(context4);
                }
            }
        });
    }

    public final String[] getPermissionArray() {
        PermissionJsonBean permissionJsonBean = this.itemData;
        String[] strArr = null;
        if (permissionJsonBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemData");
            permissionJsonBean = null;
        }
        int id = permissionJsonBean.getId();
        if (id == 1) {
            strArr = new String[]{StubApp.getString2(14630)};
        } else if (id == 2) {
            strArr = new String[]{StubApp.getString2(8631), StubApp.getString2(8633), StubApp.getString2(13387)};
        } else if (id == 3) {
            strArr = new String[]{StubApp.getString2(789)};
        } else if (id == 4) {
            strArr = new String[]{StubApp.getString2(10138)};
        }
        Intrinsics.checkNotNull(strArr);
        return strArr;
    }
}
