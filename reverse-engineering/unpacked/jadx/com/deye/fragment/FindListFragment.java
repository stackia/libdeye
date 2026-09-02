package com.deye.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.deye.adapter.FindListAdapter;
import com.deye.views.SpacesItemDecoration;
import com.mxchipapp.R;
import com.mxchipapp.databinding.FindListFragmentBinding;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.api.http.BaseResult;
import io.fogcloud.sdk.fog.api.http.RetrofitManager;
import io.fogcloud.sdk.fog.api.http.RetrofitService;
import io.fogcloud.sdk.fog.bean.ChannelsBean;
import io.fogcloud.sdk.fog.bean.FindPageBean;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.reactivestreams.Subscription;

/* compiled from: FindListFragment.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 -2\u00020\u0001:\u0001-B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0016H\u0002J\b\u0010\u001b\u001a\u00020\u0016H\u0002J\u0012\u0010\u001c\u001a\u00020\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J$\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0014\u0010%\u001a\u00020\u00162\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00160'J\u001a\u0010(\u001a\u00020\u00162\u0006\u0010)\u001a\u00020 2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J&\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000e2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00160'H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/deye/fragment/FindListFragment;", "Lcom/deye/fragment/BaseFragment;", "()V", "adapter", "Lcom/deye/adapter/FindListAdapter;", "getAdapter", "()Lcom/deye/adapter/FindListAdapter;", "setAdapter", "(Lcom/deye/adapter/FindListAdapter;)V", "binding", "Lcom/mxchipapp/databinding/FindListFragmentBinding;", "channelsBean", "Lio/fogcloud/sdk/fog/bean/ChannelsBean;", "curPageIndex", "", "isLoading", "", "()Z", "setLoading", "(Z)V", "totalPage", "finishRefresh", "", "getMaxPosition", "positions", "", "initView", "loadMoreData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onRefresh", "callBack", "Lkotlin/Function0;", "onViewCreated", "view", "requestPageList", "channelId", "pageIndex", "Companion", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class FindListFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public FindListAdapter adapter;
    private FindListFragmentBinding binding;
    private ChannelsBean channelsBean;
    private boolean isLoading;
    private int curPageIndex = 1;
    private int totalPage = 1;

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishRefresh() {
    }

    public final FindListAdapter getAdapter() {
        FindListAdapter findListAdapter = this.adapter;
        if (findListAdapter != null) {
            return findListAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        return null;
    }

    public final void setAdapter(FindListAdapter findListAdapter) {
        Intrinsics.checkNotNullParameter(findListAdapter, "<set-?>");
        this.adapter = findListAdapter;
    }

    /* renamed from: isLoading, reason: from getter */
    public final boolean getIsLoading() {
        return this.isLoading;
    }

    public final void setLoading(boolean z) {
        this.isLoading = z;
    }

    /* compiled from: FindListFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/deye/fragment/FindListFragment$Companion;", "", "()V", "newInstance", "Lcom/deye/fragment/FindListFragment;", "bean", "Lio/fogcloud/sdk/fog/bean/ChannelsBean;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FindListFragment newInstance(ChannelsBean bean) {
            Intrinsics.checkNotNullParameter(bean, "bean");
            Bundle bundle = new Bundle();
            bundle.putParcelable("bean", bean);
            FindListFragment findListFragment = new FindListFragment();
            findListFragment.setArguments(bundle);
            return findListFragment;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.channelsBean = arguments != null ? (ChannelsBean) arguments.getParcelable(StubApp.getString2(13544)) : null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.find_list_fragment, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        FindListFragmentBinding findListFragmentBinding = (FindListFragmentBinding) viewDataBindingInflate;
        this.binding = findListFragmentBinding;
        if (findListFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            findListFragmentBinding = null;
        }
        View root = findListFragmentBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private final void initView() {
        FindListFragmentBinding findListFragmentBinding = this.binding;
        FindListFragmentBinding findListFragmentBinding2 = null;
        String string2 = StubApp.getString2(13474);
        if (findListFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException(string2);
            findListFragmentBinding = null;
        }
        RecyclerView rcv = findListFragmentBinding.rcv;
        Intrinsics.checkNotNullExpressionValue(rcv, "rcv");
        Context context = getContext();
        if (context != null) {
            RecyclerView.LayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
            rcv.addItemDecoration(new SpacesItemDecoration(DensityUtil.dp2px(10.0f)));
            rcv.setLayoutManager(staggeredGridLayoutManager);
            setAdapter(new FindListAdapter(context, StubApp.getString2(2546)));
            FindListFragmentBinding findListFragmentBinding3 = this.binding;
            if (findListFragmentBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(string2);
            } else {
                findListFragmentBinding2 = findListFragmentBinding3;
            }
            findListFragmentBinding2.rcv.setAdapter(getAdapter());
        }
        this.curPageIndex = 1;
        ChannelsBean channelsBean = this.channelsBean;
        Intrinsics.checkNotNull(channelsBean);
        requestPageList(channelsBean.getChannel_id(), this.curPageIndex, new AnonymousClass2(this));
        rcv.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.deye.fragment.FindListFragment.initView.3
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                StaggeredGridLayoutManager layoutManager = recyclerView.getLayoutManager();
                Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.StaggeredGridLayoutManager");
                StaggeredGridLayoutManager staggeredGridLayoutManager2 = layoutManager;
                int[] iArrFindLastVisibleItemPositions = staggeredGridLayoutManager2.findLastVisibleItemPositions((int[]) null);
                Intrinsics.checkNotNullExpressionValue(iArrFindLastVisibleItemPositions, "findLastVisibleItemPositions(...)");
                int maxPosition = FindListFragment.this.getMaxPosition(iArrFindLastVisibleItemPositions);
                int itemCount = staggeredGridLayoutManager2.getItemCount();
                if (FindListFragment.this.getIsLoading() || maxPosition < itemCount - 3 || FindListFragment.this.curPageIndex >= FindListFragment.this.totalPage) {
                    return;
                }
                FindListFragment.this.loadMoreData();
            }
        });
    }

    /* compiled from: FindListFragment.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.fragment.FindListFragment$initView$2, reason: invalid class name */
    /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function0<Unit> {
        AnonymousClass2(Object obj) {
            super(0, obj, FindListFragment.class, StubApp.getString2(14077), StubApp.getString2(14076), 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((FindListFragment) this.receiver).finishRefresh();
        }
    }

    public final void onRefresh(Function0<Unit> callBack) {
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        ChannelsBean channelsBean = this.channelsBean;
        Intrinsics.checkNotNull(channelsBean);
        requestPageList(channelsBean.getChannel_id(), 1, callBack);
    }

    /* compiled from: FindListFragment.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.deye.fragment.FindListFragment$loadMoreData$1, reason: invalid class name */
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0<Unit> {
        AnonymousClass1(Object obj) {
            super(0, obj, FindListFragment.class, StubApp.getString2(14077), StubApp.getString2(14076), 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((FindListFragment) this.receiver).finishRefresh();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadMoreData() {
        ChannelsBean channelsBean = this.channelsBean;
        Intrinsics.checkNotNull(channelsBean);
        int channel_id = channelsBean.getChannel_id();
        int i = this.curPageIndex + 1;
        this.curPageIndex = i;
        requestPageList(channel_id, i, new AnonymousClass1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getMaxPosition(int[] positions) {
        int i = positions[0];
        for (int i2 : positions) {
            if (i2 > i) {
                i = i2;
            }
        }
        return i;
    }

    private final void requestPageList(int channelId, int pageIndex, final Function0<Unit> callBack) {
        Intrinsics.checkNotNullExpressionValue(RetrofitService.DefaultImpls.getFindItemList$default(RetrofitManager.INSTANCE.getApiService(), Integer.valueOf(channelId), "", pageIndex, 0, 8, null).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer() { // from class: com.deye.fragment.FindListFragment$requestPageList$flowable$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Subscription it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                this.this$0.setLoading(true);
            }
        }).doOnComplete(new Action() { // from class: com.deye.fragment.FindListFragment$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                FindListFragment.requestPageList$lambda$2(this.f$0);
            }
        }).subscribe(new Consumer() { // from class: com.deye.fragment.FindListFragment$requestPageList$flowable$3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(BaseResult<FindPageBean> it2) {
                FindPageBean data;
                Intrinsics.checkNotNullParameter(it2, "it");
                BaseResult.MetaBean meta = it2.getMeta();
                if (meta != null && meta.getCode() == 0 && (data = it2.getData()) != null) {
                    FindListFragment findListFragment = this;
                    Integer current_page = data.getCurrent_page();
                    if (current_page != null && current_page.intValue() == 1) {
                        findListFragment.getAdapter().setData(data.getData());
                    } else {
                        findListFragment.getAdapter().addData(data.getData());
                    }
                    Integer current_page2 = data.getCurrent_page();
                    Intrinsics.checkNotNull(current_page2);
                    findListFragment.curPageIndex = current_page2.intValue();
                    Integer page = data.getPage();
                    Intrinsics.checkNotNull(page);
                    findListFragment.totalPage = page.intValue();
                }
                callBack.invoke();
            }
        }, new Consumer() { // from class: com.deye.fragment.FindListFragment$requestPageList$flowable$4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                it2.printStackTrace();
            }
        }), "subscribe(...)");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestPageList$lambda$2(FindListFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.isLoading = false;
    }
}
