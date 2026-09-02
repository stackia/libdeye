package io.fogcloud.sdk.easylink.helper;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class EasyLinkErrCode {
    public static int BUSY_CODE = 4003;
    public static int CALLBACK_CODE = 4005;
    public static int CLOSED_CODE = 4004;
    public static int CONTEXT_CODE = 4002;
    public static int EXCEPTION_CODE = 4006;
    public static int INVALID_CODE = 4001;
    public static int START_CODE = 0;
    public static int STOP_CODE = 4000;
    public static String SUCCESS = toJsonM(StubApp.getString2(6723));
    public static String INVALID = toJsonM(StubApp.getString2(44619));
    public static String BUSY = toJsonM(StubApp.getString2(44620));
    public static String CLOSED = toJsonM(StubApp.getString2(44621));
    public static String CONTEXT = toJsonM(StubApp.getString2(44622));

    private static String toJsonM(String str) {
        return StubApp.getString2(44623) + str + StubApp.getString2(5764);
    }
}
