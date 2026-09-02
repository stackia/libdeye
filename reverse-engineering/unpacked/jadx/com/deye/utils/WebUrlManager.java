package com.deye.utils;

import android.content.Context;
import com.deye.MxchipApplication;
import com.deye.webview.AgentWebActivity;
import com.mxchipapp.R;
import com.stub.StubApp;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WebUrlManager.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0016\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\u0016\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fJ\"\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0013\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/deye/utils/WebUrlManager;", "", "()V", "BASE_URL_CN", "", "BASE_URL_OVERSEA", "getCurrentLanguage", "Lcom/deye/utils/WebUrlManager$Language;", "getTitle", "context", "Landroid/content/Context;", "urlType", "Lcom/deye/utils/WebUrlManager$UrlType;", "getUrl", "getUrlWithTitle", "Lkotlin/Pair;", "openAccountCancellationPolicy", "", "openCollectedPersonalInfo", "openPrivacyPolicy", "openThirdPartySharedInfo", "openUserAgreement", "Language", "UrlType", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class WebUrlManager {
    private static final String BASE_URL_CN = StubApp.getString2(14473);
    private static final String BASE_URL_OVERSEA = StubApp.getString2(14474);
    public static final WebUrlManager INSTANCE = new WebUrlManager();

    /* compiled from: WebUrlManager.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[UrlType.values().length];
            try {
                iArr[UrlType.USER_AGREEMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[UrlType.PRIVACY_POLICY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[UrlType.THIRD_PARTY_SHARED_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[UrlType.COLLECTED_PERSONAL_INFO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[UrlType.ACCOUNT_CANCELLATION_POLICY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private WebUrlManager() {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: WebUrlManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/deye/utils/WebUrlManager$UrlType;", "", "(Ljava/lang/String;I)V", "USER_AGREEMENT", "PRIVACY_POLICY", "THIRD_PARTY_SHARED_INFO", "COLLECTED_PERSONAL_INFO", "ACCOUNT_CANCELLATION_POLICY", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class UrlType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ UrlType[] $VALUES;
        public static final UrlType USER_AGREEMENT = new UrlType(StubApp.getString2(14468), 0);
        public static final UrlType PRIVACY_POLICY = new UrlType(StubApp.getString2(14469), 1);
        public static final UrlType THIRD_PARTY_SHARED_INFO = new UrlType(StubApp.getString2(14470), 2);
        public static final UrlType COLLECTED_PERSONAL_INFO = new UrlType(StubApp.getString2(14471), 3);
        public static final UrlType ACCOUNT_CANCELLATION_POLICY = new UrlType(StubApp.getString2(14472), 4);

        private static final /* synthetic */ UrlType[] $values() {
            return new UrlType[]{USER_AGREEMENT, PRIVACY_POLICY, THIRD_PARTY_SHARED_INFO, COLLECTED_PERSONAL_INFO, ACCOUNT_CANCELLATION_POLICY};
        }

        public static EnumEntries<UrlType> getEntries() {
            return $ENTRIES;
        }

        public static UrlType valueOf(String str) {
            return (UrlType) Enum.valueOf(UrlType.class, str);
        }

        public static UrlType[] values() {
            return (UrlType[]) $VALUES.clone();
        }

        private UrlType(String str, int i) {
        }

        static {
            UrlType[] urlTypeArr$values = $values();
            $VALUES = urlTypeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(urlTypeArr$values);
        }
    }

    public final Pair<String, String> getUrlWithTitle(Context context, UrlType urlType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(urlType, "urlType");
        return new Pair<>(getUrl(urlType), getTitle(context, urlType));
    }

    public final String getUrl(UrlType urlType) {
        Intrinsics.checkNotNullParameter(urlType, "urlType");
        if (ChannelUtil.isOversea()) {
            int i = WhenMappings.$EnumSwitchMapping$0[urlType.ordinal()];
            if (i == 1) {
                return StubApp.getString2(14479);
            }
            if (i == 2) {
                return StubApp.getString2(14478);
            }
            if (i == 3) {
                return StubApp.getString2(14477);
            }
            if (i == 4) {
                return StubApp.getString2(14476);
            }
            if (i == 5) {
                return StubApp.getString2(14475);
            }
            throw new NoWhenBranchMatchedException();
        }
        int i2 = WhenMappings.$EnumSwitchMapping$0[urlType.ordinal()];
        if (i2 == 1) {
            return StubApp.getString2(14143);
        }
        if (i2 == 2) {
            return StubApp.getString2(14144);
        }
        if (i2 == 3) {
            return StubApp.getString2(14482);
        }
        if (i2 == 4) {
            return StubApp.getString2(14481);
        }
        if (i2 == 5) {
            return StubApp.getString2(14480);
        }
        throw new NoWhenBranchMatchedException();
    }

    public final String getTitle(Context context, UrlType urlType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(urlType, "urlType");
        int i = WhenMappings.$EnumSwitchMapping$0[urlType.ordinal()];
        String string2 = StubApp.getString2(13221);
        if (i == 1) {
            String string = context.getString(R.string.service_agreement_text);
            Intrinsics.checkNotNullExpressionValue(string, string2);
            return string;
        }
        if (i == 2) {
            String string3 = context.getString(R.string.privacy_policy_text);
            Intrinsics.checkNotNullExpressionValue(string3, string2);
            return string3;
        }
        if (i == 3) {
            String string4 = context.getString(R.string.third_party_shared_info_list);
            Intrinsics.checkNotNullExpressionValue(string4, string2);
            return string4;
        }
        if (i == 4) {
            String string5 = context.getString(R.string.collected_personal_info_list);
            Intrinsics.checkNotNullExpressionValue(string5, string2);
            return string5;
        }
        if (i != 5) {
            throw new NoWhenBranchMatchedException();
        }
        String string6 = context.getString(R.string.account_cancellation_policy_title);
        Intrinsics.checkNotNullExpressionValue(string6, string2);
        return string6;
    }

    private final Language getCurrentLanguage() {
        int language = LanUtils.getLanguage((Context) MxchipApplication.getInstance());
        if (language == 1) {
            return Language.ZH;
        }
        if (language != 2) {
            return Intrinsics.areEqual(Locale.getDefault().getLanguage(), "zh") ? Language.ZH : Language.EN;
        }
        return Language.EN;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: WebUrlManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/deye/utils/WebUrlManager$Language;", "", "(Ljava/lang/String;I)V", "ZH", "EN", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Language {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Language[] $VALUES;
        public static final Language ZH = new Language(StubApp.getString2(7557), 0);
        public static final Language EN = new Language(StubApp.getString2(7558), 1);

        private static final /* synthetic */ Language[] $values() {
            return new Language[]{ZH, EN};
        }

        public static EnumEntries<Language> getEntries() {
            return $ENTRIES;
        }

        public static Language valueOf(String str) {
            return (Language) Enum.valueOf(Language.class, str);
        }

        public static Language[] values() {
            return (Language[]) $VALUES.clone();
        }

        private Language(String str, int i) {
        }

        static {
            Language[] languageArr$values = $values();
            $VALUES = languageArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(languageArr$values);
        }
    }

    public final void openUserAgreement(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Pair<String, String> urlWithTitle = getUrlWithTitle(context, UrlType.USER_AGREEMENT);
        AgentWebActivity.INSTANCE.open(context, urlWithTitle.component1(), urlWithTitle.component2());
    }

    public final void openPrivacyPolicy(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Pair<String, String> urlWithTitle = getUrlWithTitle(context, UrlType.PRIVACY_POLICY);
        AgentWebActivity.INSTANCE.open(context, urlWithTitle.component1(), urlWithTitle.component2());
    }

    public final void openThirdPartySharedInfo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Pair<String, String> urlWithTitle = getUrlWithTitle(context, UrlType.THIRD_PARTY_SHARED_INFO);
        AgentWebActivity.INSTANCE.open(context, urlWithTitle.component1(), urlWithTitle.component2());
    }

    public final void openCollectedPersonalInfo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Pair<String, String> urlWithTitle = getUrlWithTitle(context, UrlType.COLLECTED_PERSONAL_INFO);
        AgentWebActivity.INSTANCE.open(context, urlWithTitle.component1(), urlWithTitle.component2());
    }

    public final void openAccountCancellationPolicy(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Pair<String, String> urlWithTitle = getUrlWithTitle(context, UrlType.ACCOUNT_CANCELLATION_POLICY);
        AgentWebActivity.INSTANCE.open(context, urlWithTitle.component1(), urlWithTitle.component2());
    }
}
