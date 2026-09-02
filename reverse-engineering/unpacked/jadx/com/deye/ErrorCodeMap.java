package com.deye;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.deye.configs.ErrorMapUtils;
import com.stub.StubApp;
import java.util.LinkedHashMap;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ErrorCodeMap<S, S1> extends LinkedHashMap<String, String> implements Parcelable {
    public static final Parcelable.Creator<ErrorCodeMap> CREATOR = new Parcelable.Creator<ErrorCodeMap>() { // from class: com.deye.ErrorCodeMap.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ErrorCodeMap createFromParcel(Parcel parcel) {
            return new ErrorCodeMap(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ErrorCodeMap[] newArray(int i) {
            return new ErrorCodeMap[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public ErrorCodeMap() {
    }

    protected ErrorCodeMap(Parcel parcel) {
    }

    public static ErrorCodeMap<String, String> goDeviceErrorDetails(Context context, String str) {
        Log.d(StubApp.getString2(13002), StubApp.getString2(13001) + str);
        ErrorCodeMap<String, String> errorCodeMap = new ErrorCodeMap<>();
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1759275791:
                if (str.equals(StubApp.getString2(13051))) {
                    c = 0;
                    break;
                }
                break;
            case -1689412051:
                if (str.equals(StubApp.getString2(13050))) {
                    c = 1;
                    break;
                }
                break;
            case -1583250384:
                if (str.equals(StubApp.getString2(13049))) {
                    c = 2;
                    break;
                }
                break;
            case -1581225426:
                if (str.equals(StubApp.getString2(13048))) {
                    c = 3;
                    break;
                }
                break;
            case -1580336633:
                if (str.equals(StubApp.getString2(13047))) {
                    c = 4;
                    break;
                }
                break;
            case -1580336602:
                if (str.equals(StubApp.getString2(13046))) {
                    c = 5;
                    break;
                }
                break;
            case -1579530354:
                if (str.equals(StubApp.getString2(13045))) {
                    c = 6;
                    break;
                }
                break;
            case -1577650638:
                if (str.equals(StubApp.getString2(13044))) {
                    c = 7;
                    break;
                }
                break;
            case -1571190796:
                if (str.equals(StubApp.getString2(13043))) {
                    c = '\b';
                    break;
                }
                break;
            case -1565647748:
                if (str.equals(StubApp.getString2(13042))) {
                    c = '\t';
                    break;
                }
                break;
            case -1564843913:
                if (str.equals(StubApp.getString2(13041))) {
                    c = '\n';
                    break;
                }
                break;
            case -1564726149:
                if (str.equals(StubApp.getString2(13040))) {
                    c = 11;
                    break;
                }
                break;
            case -1563705567:
                if (str.equals(StubApp.getString2(13039))) {
                    c = '\f';
                    break;
                }
                break;
            case -1563683464:
                if (str.equals(StubApp.getString2(13038))) {
                    c = '\r';
                    break;
                }
                break;
            case -1562879107:
                if (str.equals(StubApp.getString2(13037))) {
                    c = 14;
                    break;
                }
                break;
            case -1561955586:
                if (str.equals(StubApp.getString2(13036))) {
                    c = 15;
                    break;
                }
                break;
            case -1560136413:
                if (str.equals(StubApp.getString2(13035))) {
                    c = 16;
                    break;
                }
                break;
            case -1560108513:
                if (str.equals(StubApp.getString2(13034))) {
                    c = 17;
                    break;
                }
                break;
            case -1482981620:
                if (str.equals(StubApp.getString2(13033))) {
                    c = 18;
                    break;
                }
                break;
            case -1482981603:
                if (str.equals(StubApp.getString2(13032))) {
                    c = 19;
                    break;
                }
                break;
            case -1460548022:
                if (str.equals(StubApp.getString2(13031))) {
                    c = 20;
                    break;
                }
                break;
            case -1460548018:
                if (str.equals(StubApp.getString2(13030))) {
                    c = 21;
                    break;
                }
                break;
            case -1460547993:
                if (str.equals(StubApp.getString2(13029))) {
                    c = 22;
                    break;
                }
                break;
            case -1460547988:
                if (str.equals(StubApp.getString2(13028))) {
                    c = 23;
                    break;
                }
                break;
            case -1436872762:
                if (str.equals(StubApp.getString2(13027))) {
                    c = 24;
                    break;
                }
                break;
            case -1436871863:
                if (str.equals(StubApp.getString2(13026))) {
                    c = 25;
                    break;
                }
                break;
            case -1260946078:
                if (str.equals(StubApp.getString2(13025))) {
                    c = 26;
                    break;
                }
                break;
            case -684666464:
                if (str.equals(StubApp.getString2(13024))) {
                    c = 27;
                    break;
                }
                break;
            case -585039817:
                if (str.equals(StubApp.getString2(13023))) {
                    c = 28;
                    break;
                }
                break;
            case -585038856:
                if (str.equals(StubApp.getString2(13022))) {
                    c = 29;
                    break;
                }
                break;
            case 64032:
                if (str.equals(StubApp.getString2(13021))) {
                    c = 30;
                    break;
                }
                break;
            case 65496359:
                if (str.equals(StubApp.getString2(13020))) {
                    c = 31;
                    break;
                }
                break;
            case 529128590:
                if (str.equals(StubApp.getString2(13019))) {
                    c = ' ';
                    break;
                }
                break;
            case 1177390491:
                if (str.equals(StubApp.getString2(13018))) {
                    c = '!';
                    break;
                }
                break;
            case 1270127273:
                if (str.equals(StubApp.getString2(13017))) {
                    c = '\"';
                    break;
                }
                break;
            case 1270129908:
                if (str.equals(StubApp.getString2(13016))) {
                    c = '#';
                    break;
                }
                break;
            case 1271974317:
                if (str.equals(StubApp.getString2(13015))) {
                    c = '$';
                    break;
                }
                break;
            case 1271974318:
                if (str.equals(StubApp.getString2(13014))) {
                    c = '%';
                    break;
                }
                break;
            case 1271974334:
                if (str.equals(StubApp.getString2(13013))) {
                    c = '&';
                    break;
                }
                break;
            case 1272704797:
                if (str.equals(StubApp.getString2(13012))) {
                    c = '\'';
                    break;
                }
                break;
            case 1294225574:
                if (str.equals(StubApp.getString2(13011))) {
                    c = '(';
                    break;
                }
                break;
            case 1297448279:
                if (str.equals(StubApp.getString2(13010))) {
                    c = ')';
                    break;
                }
                break;
            case 1607602322:
                if (str.equals(StubApp.getString2(13009))) {
                    c = '*';
                    break;
                }
                break;
            case 1681631499:
                if (str.equals(StubApp.getString2(13008))) {
                    c = '+';
                    break;
                }
                break;
            case 1921300641:
                if (str.equals(StubApp.getString2(13007))) {
                    c = ',';
                    break;
                }
                break;
            case 1967490149:
                if (str.equals(StubApp.getString2(13006))) {
                    c = '-';
                    break;
                }
                break;
            case 1967490179:
                if (str.equals(StubApp.getString2(13005))) {
                    c = '.';
                    break;
                }
                break;
            case 2030385074:
                if (str.equals(StubApp.getString2(13004))) {
                    c = '/';
                    break;
                }
                break;
            case 2031884291:
                if (str.equals(StubApp.getString2(13003))) {
                    c = '0';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return ErrorMapUtils.getX20A3ErrorMap(context);
            case 1:
            case 20:
            case 22:
            case 23:
            case ')':
            case '.':
                return ErrorMapUtils.getSC60YErrorMap(context);
            case 2:
                return ErrorMapUtils.getA06A4ErrorMap(context);
            case 3:
                return ErrorMapUtils.getC65DZErrorMap(context);
            case 4:
                return ErrorMapUtils.getX20A3ErrorMap(context);
            case 5:
                return ErrorMapUtils.getD50B3ErrorMap(context);
            case 6:
                return ErrorMapUtils.getE12A3ErrorMap(context);
            case 7:
                return ErrorMapUtils.getX20A3ErrorMap(context);
            case '\b':
                return ErrorMapUtils.getX20A3ErrorMap(context);
            case '\t':
                return ErrorMapUtils.getT22A3ErrorMap(context);
            case '\n':
            case '\'':
                return ErrorMapUtils.getU20AirErrorMap(context);
            case 11:
                return ErrorMapUtils.getU20A3ErrorMap(context);
            case '\f':
                return ErrorMapUtils.getV58A3ErrorMap(context);
            case '\r':
                return ErrorMapUtils.getV60A3ErrorMap(context);
            case 14:
                return ErrorMapUtils.getX20A3ErrorMap(context);
            case 15:
                return ErrorMapUtils.getX20A3ErrorMap(context);
            case 16:
                return ErrorMapUtils.getX20A3ErrorMap(context);
            case 17:
                return ErrorMapUtils.getX20A3ErrorMap(context);
            case 18:
            case '\"':
            case '#':
            case '$':
            case '%':
                return ErrorMapUtils.get6158ebErrorMap(context);
            case 19:
            case '&':
                return ErrorMapUtils.get890TebErrorMap(context);
            case 21:
            case '-':
                return ErrorMapUtils.getY16ErrorMap(context);
            case 24:
            case 25:
                return ErrorMapUtils.getX20A3ErrorMap(context);
            case 26:
                return ErrorMapUtils.getU20AirErrorMap(context);
            case 27:
                return ErrorMapUtils.getA12A452ErrorMap(context);
            case 28:
                return ErrorMapUtils.getB12A3ErrorMap(context);
            case 29:
                return ErrorMapUtils.getB13A3ErrorMap(context);
            case 30:
            case ',':
                return ErrorMapUtils.getRT12ErrorMap(context);
            case 31:
                return ErrorMapUtils.getH7ErrorMap(context);
            case ' ':
                return ErrorMapUtils.getLoopFanDJC_R40B_WR50A_W_ErrorMap(context);
            case '!':
                return ErrorMapUtils.getF15ProErrorMap(context);
            case '(':
                return ErrorMapUtils.getP40ErrorMap(context);
            case '*':
                return ErrorMapUtils.getU20ProErrorMap(context);
            case '+':
                return ErrorMapUtils.getQuiltDryerDJB_S10B_W_ErrorMap(context);
            case '/':
                return ErrorMapUtils.getF15ErrorMap(context);
            case '0':
                return ErrorMapUtils.getP30ErrorMap(context);
            default:
                return errorCodeMap;
        }
    }
}
