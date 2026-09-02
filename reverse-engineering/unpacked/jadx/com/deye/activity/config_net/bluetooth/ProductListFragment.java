package com.deye.activity.config_net.bluetooth;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.deye.adapter.ProductListAdapter;
import com.deye.entity.ProductListBean;
import com.deye.fragment.BaseFragment;
import com.mxchipapp.R;
import com.mxchipapp.databinding.ProductListFragmentBinding;
import com.stub.StubApp;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProductListFragment.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u001a\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u000f2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\"\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/deye/activity/config_net/bluetooth/ProductListFragment;", "Lcom/deye/fragment/BaseFragment;", "()V", "binding", "Lcom/mxchipapp/databinding/ProductListFragmentBinding;", "datalist", "Ljava/util/ArrayList;", "Lcom/deye/entity/ProductListBean$Pdata;", "Lkotlin/collections/ArrayList;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class ProductListFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private ProductListFragmentBinding binding;
    private ArrayList<ProductListBean.Pdata> datalist;

    /* compiled from: ProductListFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¨\u0006\t"}, d2 = {"Lcom/deye/activity/config_net/bluetooth/ProductListFragment$Companion;", "", "()V", "newInstance", "Lcom/deye/activity/config_net/bluetooth/ProductListFragment;", "list", "Ljava/util/ArrayList;", "Lcom/deye/entity/ProductListBean$Pdata;", "Lkotlin/collections/ArrayList;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ProductListFragment newInstance(ArrayList<ProductListBean.Pdata> list) {
            Intrinsics.checkNotNullParameter(list, "list");
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("list", list);
            ProductListFragment productListFragment = new ProductListFragment();
            productListFragment.setArguments(bundle);
            return productListFragment;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.datalist = arguments != null ? arguments.getParcelableArrayList(StubApp.getString2(815)) : null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.product_list_fragment, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        ProductListFragmentBinding productListFragmentBinding = (ProductListFragmentBinding) viewDataBindingInflate;
        this.binding = productListFragmentBinding;
        if (productListFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            productListFragmentBinding = null;
        }
        View root = productListFragmentBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private final void initView() {
        ProductListFragmentBinding productListFragmentBinding = this.binding;
        ProductListFragmentBinding productListFragmentBinding2 = null;
        String string2 = StubApp.getString2(13474);
        if (productListFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            productListFragmentBinding = null;
        }
        RecyclerView rcv = productListFragmentBinding.rcv;
        Intrinsics.checkNotNullExpressionValue(rcv, "rcv");
        Context context = getContext();
        if (context != null) {
            RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), 1);
            RecyclerView.ItemDecoration dividerItemDecoration2 = new DividerItemDecoration(getContext(), 0);
            Drawable drawable = ContextCompat.getDrawable(context, R.drawable.divider_shape);
            Intrinsics.checkNotNull(drawable);
            dividerItemDecoration.setDrawable(drawable);
            dividerItemDecoration2.setDrawable(drawable);
            RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            rcv.addItemDecoration(dividerItemDecoration);
            rcv.addItemDecoration(dividerItemDecoration2);
            rcv.setLayoutManager(gridLayoutManager);
            ProductListAdapter productListAdapter = new ProductListAdapter(context, this.datalist);
            ProductListFragmentBinding productListFragmentBinding3 = this.binding;
            if (productListFragmentBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                productListFragmentBinding2 = productListFragmentBinding3;
            }
            productListFragmentBinding2.rcv.setAdapter(productListAdapter);
        }
    }
}
