package com.deye.helper;

import android.os.Handler;
import com.deye.utils.TestTime;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.bean.PropertyResultBean;
import io.fogcloud.sdk.fog.log.LogDebug;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

/* compiled from: EquipmentFragmentHelper.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "result", "Lio/fogcloud/sdk/fog/bean/PropertyResultBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class EquipmentFragmentHelper$requestAllFogProperties$1$1 extends Lambda implements Function1<PropertyResultBean, Unit> {
    final /* synthetic */ DeviceListBean $bean;
    final /* synthetic */ Function0<Unit> $callBack;
    final /* synthetic */ List<DeviceListBean> $fogDevices;
    final /* synthetic */ Ref.IntRef $resultCount;
    final /* synthetic */ EquipmentFragmentHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    EquipmentFragmentHelper$requestAllFogProperties$1$1(DeviceListBean deviceListBean, EquipmentFragmentHelper equipmentFragmentHelper, Ref.IntRef intRef, List<? extends DeviceListBean> list, Function0<Unit> function0) {
        super(1);
        this.$bean = deviceListBean;
        this.this$0 = equipmentFragmentHelper;
        this.$resultCount = intRef;
        this.$fogDevices = list;
        this.$callBack = function0;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(PropertyResultBean propertyResultBean) {
        invoke2(propertyResultBean);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(final PropertyResultBean propertyResultBean) {
        LogDebug.INSTANCE.log(StubApp.getString2(14160) + this.$bean);
        Handler mHandler = this.this$0.getMHandler();
        final DeviceListBean deviceListBean = this.$bean;
        final Ref.IntRef intRef = this.$resultCount;
        final List<DeviceListBean> list = this.$fogDevices;
        final Function0<Unit> function0 = this.$callBack;
        mHandler.post(new Runnable() { // from class: com.deye.helper.EquipmentFragmentHelper$requestAllFogProperties$1$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                EquipmentFragmentHelper$requestAllFogProperties$1$1.invoke$lambda$0(deviceListBean, propertyResultBean, intRef, list, function0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(DeviceListBean bean, PropertyResultBean propertyResultBean, Ref.IntRef resultCount, List fogDevices, Function0 callBack) {
        Intrinsics.checkNotNullParameter(bean, "$bean");
        Intrinsics.checkNotNullParameter(resultCount, "$resultCount");
        Intrinsics.checkNotNullParameter(fogDevices, "$fogDevices");
        Intrinsics.checkNotNullParameter(callBack, "$callBack");
        TestTime.INSTANCE.logTime(StubApp.getString2(14159));
        EquipmentFragmentHelper.requestAllFogProperties$handleResult(resultCount, fogDevices, callBack, bean, propertyResultBean, false);
    }
}
