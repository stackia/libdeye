package io.fogcloud.sdk.fog.api.mqtt.sdk.helper;

import com.stub.StubApp;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class MQTTErrCode {
    public static int BUSY_CODE = 4203;
    public static int CLOSED_CODE = 4204;
    public static int CONTEXT_CODE = 4202;
    public static int EMPTY_CODE = 4201;
    public static int EXCEPTION_CODE = 4206;
    public static int QOS_CODE = 4205;
    public static int SUCCESS_CODE;
    public static String SUCCESS = toJsonM(StubApp.getString2(6723));
    public static String EMPTY = toJsonM(StubApp.getString2(44619));
    public static String CONTEXT = toJsonM(StubApp.getString2(44622));
    public static String BUSY = toJsonM(StubApp.getString2(45002));
    public static String CLOSED = toJsonM(StubApp.getString2(45003));
    public static String QOSERR = toJsonM(StubApp.getString2(45004));
    public static int _CAN_CHECK_DEVICE_STATE_CODE = 4199;
    public static int _PAYLOAD_CODE = 4200;
    public static int _CON_CODE = 4210;
    public static int _TOPIC_CODE = 4211;
    public static int _STOP_CODE = 4212;
    public static int _SUB_CODE = 4213;
    public static int _RESUB_CODE = 4214;
    public static int _UNSUB_CODE = 4215;
    public static int _EXCEPTION_CODE = 4216;
    public static int _LOST_CODE = 4217;
    public static int _DISCON_CODE = 4218;
    public static int _PUB_CODE = 4219;
    public static String _CON_MSG = toJsonS(StubApp.getString2(33804));
    public static String _TOPIC_MSG = toJsonS(StubApp.getString2(45005));
    public static String _STOP_MSG = toJsonS(StubApp.getString2(36362));
    public static String _SUB_MSG = toJsonS(StubApp.getString2(45006));
    public static String _RESUB_MSG = toJsonS(StubApp.getString2(45007));
    public static String _UNSUB_MSG = toJsonS(StubApp.getString2(45008));
    public static String _EXCEP_MSG = toJsonS(StubApp.getString2(45009));
    public static String _LOST_MSG = toJsonS(StubApp.getString2(45010));
    public static String _PUB_MSG = toJsonS(StubApp.getString2(45011));
    public static String _DISCON_MSG = toJsonS(StubApp.getString2(17987));

    private static String toJsonM(String str) {
        return StubApp.getString2(44623) + str + StubApp.getString2(5764);
    }

    private static String toJsonS(String str) {
        return StubApp.getString2(45012) + str + StubApp.getString2(5764);
    }
}
