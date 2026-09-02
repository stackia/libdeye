package com.deye.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.deye.activity.mine.UserListFragment;
import com.deye.entity.UserBean;
import com.deye.entity.UserInfoBean;
import com.deye.utils.MMKVUtils;
import com.deye.utils.StringUtils;
import com.deye.utils.UserTypeUtil;
import com.deye.views.RoundCircleImageView;
import com.mxchipapp.R;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RyUserListAdapter.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001cB%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u001c\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u000fH\u0016J\u001c\u0010\u0014\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000fH\u0016J\u0018\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/deye/adapter/RyUserListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/deye/adapter/RyUserListAdapter$ViewHolder;", "userListActivity", "Lcom/deye/activity/mine/UserListFragment;", "userBeans", "Ljava/util/ArrayList;", "Lcom/deye/entity/UserBean;", "Lkotlin/collections/ArrayList;", "(Lcom/deye/activity/mine/UserListFragment;Ljava/util/ArrayList;)V", "curUserId", "", "mUserBeanList", "userListFragment", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setHeadPortrait", "imgUrl", "imageView", "Landroid/widget/ImageView;", "ViewHolder", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class RyUserListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final String curUserId;
    private ArrayList<UserBean> mUserBeanList;
    private final UserListFragment userListFragment;

    public RyUserListAdapter(UserListFragment userListActivity, ArrayList<UserBean> userBeans) {
        String enduserid;
        Intrinsics.checkNotNullParameter(userListActivity, "userListActivity");
        Intrinsics.checkNotNullParameter(userBeans, "userBeans");
        new ArrayList();
        this.mUserBeanList = userBeans;
        this.userListFragment = userListActivity;
        UserInfoBean userInfo = MMKVUtils.INSTANCE.getUserInfo();
        this.curUserId = (userInfo == null || (enduserid = userInfo.getEnduserid()) == null) ? "" : enduserid;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View viewInflate = LayoutInflater.from(this.userListFragment.getContext()).inflate(R.layout.user_list_item, parent, false);
        Intrinsics.checkNotNull(viewInflate);
        return new ViewHolder(this, viewInflate);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final int absoluteAdapterPosition = holder.getAbsoluteAdapterPosition();
        boolean zAreEqual = Intrinsics.areEqual(this.curUserId, this.mUserBeanList.get(absoluteAdapterPosition).enduserid);
        if (!TextUtils.isEmpty(this.mUserBeanList.get(absoluteAdapterPosition).phone)) {
            String strMaskPhoneNumber = StringUtils.maskPhoneNumber(this.mUserBeanList.get(absoluteAdapterPosition).phone);
            if (zAreEqual) {
                holder.getTv_user_name().setText(strMaskPhoneNumber + this.userListFragment.getString(R.string.me_suffix));
            } else {
                holder.getTv_user_name().setText(strMaskPhoneNumber);
            }
        } else if (TextUtils.isEmpty(this.mUserBeanList.get(absoluteAdapterPosition).nickname)) {
            holder.getTv_user_name().setText(this.userListFragment.getString(R.string.no_nickname));
        } else if (zAreEqual) {
            holder.getTv_user_name().setText(this.mUserBeanList.get(absoluteAdapterPosition).nickname + this.userListFragment.getString(R.string.me_suffix));
        } else {
            holder.getTv_user_name().setText(this.mUserBeanList.get(absoluteAdapterPosition).nickname);
        }
        holder.getTv_user_type().setText(UserTypeUtil.getUserType(this.mUserBeanList.get(absoluteAdapterPosition).role));
        holder.getIv_delete_user().setVisibility(zAreEqual ? 8 : 0);
        if (!Intrinsics.areEqual("", this.mUserBeanList.get(absoluteAdapterPosition).avatar) && this.mUserBeanList.get(absoluteAdapterPosition).avatar != null) {
            String avatar = this.mUserBeanList.get(absoluteAdapterPosition).avatar;
            Intrinsics.checkNotNullExpressionValue(avatar, "avatar");
            setHeadPortrait(avatar, holder.getIv_head());
        }
        holder.getIv_delete_user().setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.RyUserListAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RyUserListAdapter.onBindViewHolder$lambda$0(this.f$0, absoluteAdapterPosition, view);
            }
        });
        holder.getTv_set_super_user().setOnClickListener(new View.OnClickListener() { // from class: com.deye.adapter.RyUserListAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RyUserListAdapter.onBindViewHolder$lambda$1(this.f$0, absoluteAdapterPosition, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(RyUserListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.userListFragment.sendDelete(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$1(RyUserListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.userListFragment.sendSuper(i);
    }

    public int getItemCount() {
        return this.mUserBeanList.size();
    }

    /* compiled from: RyUserListAdapter.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001a\u0010\u001a\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016¨\u0006\u001d"}, d2 = {"Lcom/deye/adapter/RyUserListAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/deye/adapter/RyUserListAdapter;Landroid/view/View;)V", "iv_delete_user", "Landroid/widget/ImageView;", "getIv_delete_user", "()Landroid/widget/ImageView;", "setIv_delete_user", "(Landroid/widget/ImageView;)V", "iv_head", "Lcom/deye/views/RoundCircleImageView;", "getIv_head", "()Lcom/deye/views/RoundCircleImageView;", "setIv_head", "(Lcom/deye/views/RoundCircleImageView;)V", "tv_set_super_user", "Landroid/widget/TextView;", "getTv_set_super_user", "()Landroid/widget/TextView;", "setTv_set_super_user", "(Landroid/widget/TextView;)V", "tv_user_name", "getTv_user_name", "setTv_user_name", "tv_user_type", "getTv_user_type", "setTv_user_type", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_delete_user;
        private RoundCircleImageView iv_head;
        final /* synthetic */ RyUserListAdapter this$0;
        private TextView tv_set_super_user;
        private TextView tv_user_name;
        private TextView tv_user_type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(RyUserListAdapter ryUserListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.this$0 = ryUserListAdapter;
            setIsRecyclable(false);
            View viewFindViewById = itemView.findViewById(R.id.iv_head);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
            this.iv_head = (RoundCircleImageView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.tv_user_name);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            this.tv_user_name = (TextView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(R.id.tv_user_type);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
            this.tv_user_type = (TextView) viewFindViewById3;
            View viewFindViewById4 = itemView.findViewById(R.id.tv_set_super_user);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
            this.tv_set_super_user = (TextView) viewFindViewById4;
            View viewFindViewById5 = itemView.findViewById(R.id.iv_delete_user);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
            this.iv_delete_user = (ImageView) viewFindViewById5;
        }

        public final TextView getTv_user_name() {
            return this.tv_user_name;
        }

        public final void setTv_user_name(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_user_name = textView;
        }

        public final TextView getTv_user_type() {
            return this.tv_user_type;
        }

        public final void setTv_user_type(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_user_type = textView;
        }

        public final TextView getTv_set_super_user() {
            return this.tv_set_super_user;
        }

        public final void setTv_set_super_user(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tv_set_super_user = textView;
        }

        public final ImageView getIv_delete_user() {
            return this.iv_delete_user;
        }

        public final void setIv_delete_user(ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.iv_delete_user = imageView;
        }

        public final RoundCircleImageView getIv_head() {
            return this.iv_head;
        }

        public final void setIv_head(RoundCircleImageView roundCircleImageView) {
            Intrinsics.checkNotNullParameter(roundCircleImageView, "<set-?>");
            this.iv_head = roundCircleImageView;
        }
    }

    private final void setHeadPortrait(String imgUrl, ImageView imageView) {
        RequestOptions requestOptionsError = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE).placeholder(R.mipmap.head_icon).error(R.mipmap.head_icon);
        Intrinsics.checkNotNullExpressionValue(requestOptionsError, "error(...)");
        Glide.with(this.userListFragment).load(imgUrl).apply((BaseRequestOptions<?>) requestOptionsError).into(imageView);
    }
}
