package com.deye.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.deye.fragment.FindListFragment;
import io.fogcloud.sdk.fog.bean.ChannelsBean;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FindPagerAdapter.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\nJ\b\u0010\u0011\u001a\u00020\nH\u0016J\u0016\u0010\u0012\u001a\u00020\u00132\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014H\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/deye/adapter/FindPagerAdapter;", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "fragmentActivity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "dataList", "", "Lio/fogcloud/sdk/fog/bean/ChannelsBean;", "fragmentCache", "", "", "Lcom/deye/fragment/FindListFragment;", "createFragment", "Landroidx/fragment/app/Fragment;", "position", "getFragmentByIndex", "index", "getItemCount", "setDataList", "", "", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class FindPagerAdapter extends FragmentStateAdapter {
    private final List<ChannelsBean> dataList;
    private final Map<Integer, FindListFragment> fragmentCache;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FindPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        this.fragmentCache = new LinkedHashMap();
        this.dataList = new ArrayList();
    }

    public final void setDataList(List<ChannelsBean> dataList) {
        Intrinsics.checkNotNullParameter(dataList, "dataList");
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public Fragment createFragment(int position) {
        FindListFragment findListFragmentNewInstance = FindListFragment.INSTANCE.newInstance(this.dataList.get(position));
        this.fragmentCache.put(Integer.valueOf(position), findListFragmentNewInstance);
        return findListFragmentNewInstance;
    }

    public final FindListFragment getFragmentByIndex(int index) {
        return this.fragmentCache.get(Integer.valueOf(index));
    }

    public int getItemCount() {
        return this.dataList.size();
    }
}
