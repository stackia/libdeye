package com.deye.fragment;

import com.deye.helper.EquipmentFragmentHelper;
import com.deye.utils.BaseUtils;
import com.deye.utils.TestTime;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EquipmentFragment.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
final class EquipmentFragment$initData$flowable$1<T> implements Consumer {
    final /* synthetic */ EquipmentFragment this$0;

    EquipmentFragment$initData$flowable$1(EquipmentFragment equipmentFragment) {
        this.this$0 = equipmentFragment;
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(final BaseResult<List<DeviceListBean>> it2) {
        Object message;
        Intrinsics.checkNotNullParameter(it2, "it");
        EquipmentFragmentHelper equipmentFragmentHelper = this.this$0.mEquipmentFragmentHelper;
        Intrinsics.checkNotNull(equipmentFragmentHelper);
        equipmentFragmentHelper.getSTOP_REFRESH();
        BaseResult.MetaBean meta = it2.getMeta();
        if (meta != null && meta.getCode() == 0) {
            SmartRefreshLayout smartRefreshLayout = this.this$0.getDataBinding().srlRefreshLayout;
            final EquipmentFragment equipmentFragment = this.this$0;
            smartRefreshLayout.post(new Runnable() { // from class: com.deye.fragment.EquipmentFragment$initData$flowable$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    EquipmentFragment$initData$flowable$1.accept$lambda$1(it2, equipmentFragment);
                }
            });
        } else {
            BaseResult.MetaBean meta2 = it2.getMeta();
            if (meta2 == null || (message = meta2.getMessage()) == null) {
                return;
            }
            BaseUtils.showShortToast(message.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void accept$lambda$1(BaseResult it2, EquipmentFragment this$0) {
        Intrinsics.checkNotNullParameter(it2, "$it");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TestTime.INSTANCE.logTime(StubApp.getString2(14036));
        if (((List) it2.getData()) != null) {
            Object data = it2.getData();
            Intrinsics.checkNotNull(data);
            this$0.updateUIOnFetchDeviceListSuccess((List) data);
        }
    }
}
