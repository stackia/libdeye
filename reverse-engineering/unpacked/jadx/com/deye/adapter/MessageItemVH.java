package com.deye.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.mxchipapp.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MessageListAdapter.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001a\u0010\u001a\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\u001a\u0010\u001d\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000e\"\u0004\b\u001f\u0010\u0010R\u001a\u0010 \u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u000e\"\u0004\b\"\u0010\u0010¨\u0006#"}, d2 = {"Lcom/deye/adapter/MessageItemVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "messageContainer", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getMessageContainer", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "setMessageContainer", "(Landroidx/constraintlayout/widget/ConstraintLayout;)V", "messageContent", "Landroid/widget/TextView;", "getMessageContent", "()Landroid/widget/TextView;", "setMessageContent", "(Landroid/widget/TextView;)V", "messageDelete", "Landroid/widget/ImageView;", "getMessageDelete", "()Landroid/widget/ImageView;", "setMessageDelete", "(Landroid/widget/ImageView;)V", "messageImage", "getMessageImage", "setMessageImage", "messageReadDot", "getMessageReadDot", "setMessageReadDot", "messageTime", "getMessageTime", "setMessageTime", "messageTitle", "getMessageTitle", "setMessageTitle", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class MessageItemVH extends RecyclerView.ViewHolder {
    private ConstraintLayout messageContainer;
    private TextView messageContent;
    private ImageView messageDelete;
    private ImageView messageImage;
    private ImageView messageReadDot;
    private TextView messageTime;
    private TextView messageTitle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MessageItemVH(View itemView) {
        super(itemView);
        Intrinsics.checkNotNullParameter(itemView, "itemView");
        ConstraintLayout constraintLayoutFindViewById = itemView.findViewById(R.id.message_container);
        Intrinsics.checkNotNullExpressionValue(constraintLayoutFindViewById, "findViewById(...)");
        this.messageContainer = constraintLayoutFindViewById;
        View viewFindViewById = itemView.findViewById(R.id.message_image);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.messageImage = (ImageView) viewFindViewById;
        View viewFindViewById2 = itemView.findViewById(R.id.message_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.messageTitle = (TextView) viewFindViewById2;
        View viewFindViewById3 = itemView.findViewById(R.id.message_content);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        this.messageContent = (TextView) viewFindViewById3;
        View viewFindViewById4 = itemView.findViewById(R.id.message_delete);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
        this.messageDelete = (ImageView) viewFindViewById4;
        View viewFindViewById5 = itemView.findViewById(R.id.message_time);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
        this.messageTime = (TextView) viewFindViewById5;
        View viewFindViewById6 = itemView.findViewById(R.id.message_read_dot);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(...)");
        this.messageReadDot = (ImageView) viewFindViewById6;
    }

    public final ImageView getMessageImage() {
        return this.messageImage;
    }

    public final void setMessageImage(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.messageImage = imageView;
    }

    public final TextView getMessageTitle() {
        return this.messageTitle;
    }

    public final void setMessageTitle(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.messageTitle = textView;
    }

    public final TextView getMessageContent() {
        return this.messageContent;
    }

    public final void setMessageContent(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.messageContent = textView;
    }

    public final ImageView getMessageDelete() {
        return this.messageDelete;
    }

    public final void setMessageDelete(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.messageDelete = imageView;
    }

    public final TextView getMessageTime() {
        return this.messageTime;
    }

    public final void setMessageTime(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.messageTime = textView;
    }

    public final ImageView getMessageReadDot() {
        return this.messageReadDot;
    }

    public final void setMessageReadDot(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.messageReadDot = imageView;
    }

    public final ConstraintLayout getMessageContainer() {
        return this.messageContainer;
    }

    public final void setMessageContainer(ConstraintLayout constraintLayout) {
        Intrinsics.checkNotNullParameter(constraintLayout, "<set-?>");
        this.messageContainer = constraintLayout;
    }
}
