package io.fogcloud.sdk.fog.bean;

import com.efs.sdk.base.protocol.ILogProtocol;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MessageBean.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u00039:;B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u000eR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\f\"\u0004\b \u0010\u000eR\u001c\u0010!\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\f\"\u0004\b#\u0010\u000eR\u001a\u0010$\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0018\"\u0004\b&\u0010\u001aR\u001a\u0010'\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\f\"\u0004\b)\u0010\u000eR\u001c\u0010*\u001a\u0004\u0018\u00010+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001c\u00100\u001a\u0004\u0018\u000101X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001c\u00106\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\f\"\u0004\b8\u0010\u000e¨\u0006<"}, d2 = {"Lio/fogcloud/sdk/fog/bean/MessageBean;", "Ljava/io/Serializable;", "()V", "component_msg_detail", "Lio/fogcloud/sdk/fog/bean/MessageBean$PartMSgDetail;", "getComponent_msg_detail", "()Lio/fogcloud/sdk/fog/bean/MessageBean$PartMSgDetail;", "setComponent_msg_detail", "(Lio/fogcloud/sdk/fog/bean/MessageBean$PartMSgDetail;)V", "device_alias", "", "getDevice_alias", "()Ljava/lang/String;", "setDevice_alias", "(Ljava/lang/String;)V", "device_id", "getDevice_id", "setDevice_id", "message_id", "getMessage_id", "setMessage_id", "message_status", "", "getMessage_status", "()I", "setMessage_status", "(I)V", "message_sub_title", "getMessage_sub_title", "setMessage_sub_title", "message_time", "getMessage_time", "setMessage_time", "message_title", "getMessage_title", "setMessage_title", "message_type", "getMessage_type", "setMessage_type", "product_icon", "getProduct_icon", "setProduct_icon", "schedule_detail", "Lio/fogcloud/sdk/fog/bean/MessageBean$ScheduleDetail;", "getSchedule_detail", "()Lio/fogcloud/sdk/fog/bean/MessageBean$ScheduleDetail;", "setSchedule_detail", "(Lio/fogcloud/sdk/fog/bean/MessageBean$ScheduleDetail;)V", "share_detail", "Lio/fogcloud/sdk/fog/bean/MessageBean$ShareDetailBean;", "getShare_detail", "()Lio/fogcloud/sdk/fog/bean/MessageBean$ShareDetailBean;", "setShare_detail", "(Lio/fogcloud/sdk/fog/bean/MessageBean$ShareDetailBean;)V", "water_tank_detail", "getWater_tank_detail", "setWater_tank_detail", "PartMSgDetail", "ScheduleDetail", "ShareDetailBean", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class MessageBean implements Serializable {
    private PartMSgDetail component_msg_detail;
    private String device_alias;
    private String device_id;
    private String message_id;
    private int message_status;
    private String message_sub_title;
    private String message_time;
    private String message_title;
    private int message_type;
    private String product_icon = "";
    private ScheduleDetail schedule_detail;
    private ShareDetailBean share_detail;
    private String water_tank_detail;

    public final int getMessage_type() {
        return this.message_type;
    }

    public final void setMessage_type(int i) {
        this.message_type = i;
    }

    public final String getMessage_time() {
        return this.message_time;
    }

    public final void setMessage_time(String str) {
        this.message_time = str;
    }

    public final String getMessage_id() {
        return this.message_id;
    }

    public final void setMessage_id(String str) {
        this.message_id = str;
    }

    public final String getMessage_title() {
        return this.message_title;
    }

    public final void setMessage_title(String str) {
        this.message_title = str;
    }

    public final String getMessage_sub_title() {
        return this.message_sub_title;
    }

    public final void setMessage_sub_title(String str) {
        this.message_sub_title = str;
    }

    public final String getProduct_icon() {
        return this.product_icon;
    }

    public final void setProduct_icon(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.product_icon = str;
    }

    public final int getMessage_status() {
        return this.message_status;
    }

    public final void setMessage_status(int i) {
        this.message_status = i;
    }

    public final String getDevice_id() {
        return this.device_id;
    }

    public final void setDevice_id(String str) {
        this.device_id = str;
    }

    public final String getDevice_alias() {
        return this.device_alias;
    }

    public final void setDevice_alias(String str) {
        this.device_alias = str;
    }

    public final String getWater_tank_detail() {
        return this.water_tank_detail;
    }

    public final void setWater_tank_detail(String str) {
        this.water_tank_detail = str;
    }

    public final ShareDetailBean getShare_detail() {
        return this.share_detail;
    }

    public final void setShare_detail(ShareDetailBean shareDetailBean) {
        this.share_detail = shareDetailBean;
    }

    public final ScheduleDetail getSchedule_detail() {
        return this.schedule_detail;
    }

    public final void setSchedule_detail(ScheduleDetail scheduleDetail) {
        this.schedule_detail = scheduleDetail;
    }

    public final PartMSgDetail getComponent_msg_detail() {
        return this.component_msg_detail;
    }

    public final void setComponent_msg_detail(PartMSgDetail partMSgDetail) {
        this.component_msg_detail = partMSgDetail;
    }

    /* compiled from: MessageBean.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR\u001e\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u000e\u0010\u0006\"\u0004\b\u000f\u0010\bR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0013\"\u0004\b\u001e\u0010\u0015R\u001c\u0010\u001f\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015¨\u0006\""}, d2 = {"Lio/fogcloud/sdk/fog/bean/MessageBean$ShareDetailBean;", "Ljava/io/Serializable;", "()V", "accept", "", "getAccept", "()Ljava/lang/Integer;", "setAccept", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "expire_flag", "getExpire_flag", "setExpire_flag", "operation", "getOperation", "setOperation", "source_user_id", "", "getSource_user_id", "()Ljava/lang/String;", "setSource_user_id", "(Ljava/lang/String;)V", "source_user_name", "getSource_user_name", "setSource_user_name", "source_user_phone", "getSource_user_phone", "setSource_user_phone", "target_user_id", "getTarget_user_id", "setTarget_user_id", "vercode", "getVercode", "setVercode", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ShareDetailBean implements Serializable {
        private Integer accept;
        private Integer expire_flag;
        private Integer operation;
        private String source_user_id;
        private String source_user_name;
        private String source_user_phone;
        private String target_user_id;
        private String vercode;

        public final String getSource_user_id() {
            return this.source_user_id;
        }

        public final void setSource_user_id(String str) {
            this.source_user_id = str;
        }

        public final String getSource_user_name() {
            return this.source_user_name;
        }

        public final void setSource_user_name(String str) {
            this.source_user_name = str;
        }

        public final String getSource_user_phone() {
            return this.source_user_phone;
        }

        public final void setSource_user_phone(String str) {
            this.source_user_phone = str;
        }

        public final String getTarget_user_id() {
            return this.target_user_id;
        }

        public final void setTarget_user_id(String str) {
            this.target_user_id = str;
        }

        public final Integer getOperation() {
            return this.operation;
        }

        public final void setOperation(Integer num) {
            this.operation = num;
        }

        public final Integer getAccept() {
            return this.accept;
        }

        public final void setAccept(Integer num) {
            this.accept = num;
        }

        public final Integer getExpire_flag() {
            return this.expire_flag;
        }

        public final void setExpire_flag(Integer num) {
            this.expire_flag = num;
        }

        public final String getVercode() {
            return this.vercode;
        }

        public final void setVercode(String str) {
            this.vercode = str;
        }
    }

    /* compiled from: MessageBean.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lio/fogcloud/sdk/fog/bean/MessageBean$ScheduleDetail;", "Ljava/io/Serializable;", "()V", "operation", "", "getOperation", "()Ljava/lang/Integer;", "setOperation", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", ILogProtocol.LOG_KEY_TYPE, "getType", "setType", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ScheduleDetail implements Serializable {
        private Integer operation;
        private Integer type;

        public final Integer getType() {
            return this.type;
        }

        public final void setType(Integer num) {
            this.type = num;
        }

        public final Integer getOperation() {
            return this.operation;
        }

        public final void setOperation(Integer num) {
            this.operation = num;
        }
    }

    /* compiled from: MessageBean.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\b¨\u0006\u001b"}, d2 = {"Lio/fogcloud/sdk/fog/bean/MessageBean$PartMSgDetail;", "Ljava/io/Serializable;", "()V", "component_id", "", "getComponent_id", "()I", "setComponent_id", "(I)V", "device_id", "", "getDevice_id", "()Ljava/lang/String;", "setDevice_id", "(Ljava/lang/String;)V", "maintenance_percentage", "getMaintenance_percentage", "setMaintenance_percentage", "operation", "getOperation", "setOperation", "product_id", "getProduct_id", "setProduct_id", "replace_percentage", "getReplace_percentage", "setReplace_percentage", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class PartMSgDetail implements Serializable {
        private int component_id;
        private String device_id;
        private int maintenance_percentage;
        private String operation = "";
        private String product_id;
        private int replace_percentage;

        public final String getProduct_id() {
            return this.product_id;
        }

        public final void setProduct_id(String str) {
            this.product_id = str;
        }

        public final String getDevice_id() {
            return this.device_id;
        }

        public final void setDevice_id(String str) {
            this.device_id = str;
        }

        public final int getComponent_id() {
            return this.component_id;
        }

        public final void setComponent_id(int i) {
            this.component_id = i;
        }

        public final String getOperation() {
            return this.operation;
        }

        public final void setOperation(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.operation = str;
        }

        public final int getMaintenance_percentage() {
            return this.maintenance_percentage;
        }

        public final void setMaintenance_percentage(int i) {
            this.maintenance_percentage = i;
        }

        public final int getReplace_percentage() {
            return this.replace_percentage;
        }

        public final void setReplace_percentage(int i) {
            this.replace_percentage = i;
        }
    }
}
