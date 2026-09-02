package com.deye;

import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.deye.entity.ProductListBean;
import com.google.gson.Gson;
import com.stub.StubApp;
import com.tencent.mmkv.MMKV;
import io.fogcloud.sdk.fog.api.http.DeYeHttpRequestManager;
import io.fogcloud.sdk.fog.callback.FogCallBack;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProductManager.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0012\u001a\u00020\u000bR \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcom/deye/ProductManager;", "", "()V", "productListBean", "", "Lcom/deye/entity/ProductListBean$Pdata;", "getProductListBean", "()Ljava/util/List;", "setProductListBean", "(Ljava/util/List;)V", "addProductItem", "", "item", "checkIsBleConfig", "", "pid", "", "getProductIcon", "reqDeviceList", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class ProductManager {
    public static final ProductManager INSTANCE = new ProductManager();
    private static List<ProductListBean.Pdata> productListBean = new ArrayList();

    private ProductManager() {
    }

    public final List<ProductListBean.Pdata> getProductListBean() {
        return productListBean;
    }

    public final void setProductListBean(List<ProductListBean.Pdata> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        productListBean = list;
    }

    public final void reqDeviceList() {
        if (!productListBean.isEmpty()) {
            return;
        }
        DeYeHttpRequestManager.getInstance().getProductList(Integer.valueOf(MMKV.defaultMMKV().getBoolean(StubApp.getString2(13148), false) ? 2 : 1), new FogCallBack() { // from class: com.deye.ProductManager.reqDeviceList.1
            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onFailure(int code, String message) {
                Intrinsics.checkNotNullParameter(message, "message");
            }

            @Override // io.fogcloud.sdk.fog.callback.FogCallBack
            public void onSuccess(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                ProductListBean productListBean2 = (ProductListBean) JSON.parseObject(message, ProductListBean.class);
                if (productListBean2 == null || productListBean2.getMeta() == null || productListBean2.getMeta().getCode() != 0 || productListBean2.getData() == null || productListBean2.getData().getResult() == null) {
                    return;
                }
                List<ProductListBean.Result> result = productListBean2.getData().getResult();
                int size = result.size();
                for (int i = 0; i < size; i++) {
                    ProductListBean.Result result2 = result.get(i);
                    int size2 = result2.getPdata().size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        ProductListBean.Pdata pdata = result2.getPdata().get(i2);
                        if (!ProductManager.INSTANCE.getProductListBean().contains(pdata)) {
                            List<ProductListBean.Pdata> productListBean3 = ProductManager.INSTANCE.getProductListBean();
                            Intrinsics.checkNotNull(pdata);
                            productListBean3.add(pdata);
                        }
                    }
                }
            }
        });
    }

    public final void addProductItem(ProductListBean.Pdata item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (productListBean.contains(item)) {
            productListBean.add(item);
        }
    }

    public final String getProductIcon(String pid) {
        Intrinsics.checkNotNullParameter(pid, "pid");
        Log.e(StubApp.getString2(13147), new Gson().toJson(productListBean));
        for (ProductListBean.Pdata pdata : productListBean) {
            if (pdata.getProductid().equals(pid)) {
                if (pdata.picture_v3 != null && !TextUtils.isEmpty(pdata.picture_v3)) {
                    String picture_v3 = pdata.picture_v3;
                    Intrinsics.checkNotNullExpressionValue(picture_v3, "picture_v3");
                    return picture_v3;
                }
                String picture = pdata.getPicture();
                Intrinsics.checkNotNullExpressionValue(picture, "getPicture(...)");
                return picture;
            }
        }
        return "";
    }

    public final boolean checkIsBleConfig(String pid) {
        Intrinsics.checkNotNullParameter(pid, "pid");
        for (ProductListBean.Pdata pdata : productListBean) {
            if (pdata.getProductid().equals(pid)) {
                return pdata.getConfigType() == 2;
            }
        }
        return false;
    }
}
