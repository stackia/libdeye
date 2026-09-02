package com.deye.activity.device.humidifier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.deye.activity.device.base.BaseActivity;
import com.mxchipapp.databinding.ActivityPartsGuideBinding;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PartsGuideAty.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0006\u0010\u0011\u001a\u00020\u000eJ\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\bH\u0002J\u0006\u0010\u0014\u001a\u00020\u000eJ\b\u0010\u0015\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/deye/activity/device/humidifier/PartsGuideAty;", "Lcom/deye/activity/device/base/BaseActivity;", "()V", "binding", "Lcom/mxchipapp/databinding/ActivityPartsGuideBinding;", "deviceId", "", "index", "", "maintenance_value", "partId", "productId", "replace_value", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "resetRemainingTime", "setSelect", "i", "updateTime", "updateView", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class PartsGuideAty extends BaseActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final int ID_F15_CARBON_FILTER;
    private static final int ID_F15_FILTER;
    private static final int ID_F15_WETCURTAIN;
    private static final int ID_SiLIVER_ION;
    private ActivityPartsGuideBinding binding;
    private int index;
    private int maintenance_value;
    private int partId;
    private int replace_value;
    private String deviceId = "";
    private String productId = "";

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onCreate$lambda$0(PartsGuideAty partsGuideAty, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onCreate$lambda$1(PartsGuideAty partsGuideAty, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void onCreate$lambda$2(PartsGuideAty partsGuideAty, View view);

    private final native void setSelect(int i);

    private final native void updateView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final native void updateView$lambda$3(PartsGuideAty partsGuideAty, View view);

    @Override // com.deye.activity.device.base.BaseActivity
    public native void onCreate(Bundle savedInstanceState);

    public final native void resetRemainingTime();

    public final native void updateTime();

    /* compiled from: PartsGuideAty.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JM\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u0013¢\u0006\u0002\u0010\u0018R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006¨\u0006\u0019"}, d2 = {"Lcom/deye/activity/device/humidifier/PartsGuideAty$Companion;", "", "()V", "ID_F15_CARBON_FILTER", "", "getID_F15_CARBON_FILTER", "()I", "ID_F15_FILTER", "getID_F15_FILTER", "ID_F15_WETCURTAIN", "getID_F15_WETCURTAIN", "ID_SiLIVER_ION", "getID_SiLIVER_ION", "open", "", "context", "Landroid/content/Context;", "partID", "deviceId", "", "productId", "maintenanceValue", "replaceValue", "operation", "(Landroid/content/Context;ILjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)V", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getID_F15_WETCURTAIN() {
            return PartsGuideAty.ID_F15_WETCURTAIN;
        }

        public final int getID_F15_FILTER() {
            return PartsGuideAty.ID_F15_FILTER;
        }

        public final int getID_F15_CARBON_FILTER() {
            return PartsGuideAty.ID_F15_CARBON_FILTER;
        }

        public final int getID_SiLIVER_ION() {
            return PartsGuideAty.ID_SiLIVER_ION;
        }

        public final void open(Context context, int partID, String deviceId, String productId, Integer maintenanceValue, Integer replaceValue, String operation) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intent intent = new Intent(context, (Class<?>) PartsGuideAty.class);
            intent.putExtra(StubApp.getString2(13592), partID);
            intent.putExtra(StubApp.getString2(13055), deviceId);
            intent.putExtra(StubApp.getString2(13306), productId);
            intent.putExtra(StubApp.getString2(13594), maintenanceValue);
            intent.putExtra(StubApp.getString2(13595), replaceValue);
            intent.putExtra("operation", operation);
            context.startActivity(intent);
        }
    }

    static {
        StubApp.interface11(14154);
        INSTANCE = new Companion(null);
        ID_F15_WETCURTAIN = 1;
        ID_F15_FILTER = 2;
        ID_F15_CARBON_FILTER = 3;
        ID_SiLIVER_ION = 4;
    }
}
