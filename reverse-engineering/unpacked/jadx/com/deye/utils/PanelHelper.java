package com.deye.utils;

import android.content.Context;
import com.alibaba.fastjson.JSON;
import com.deye.entity.control_panel.dehumidifier.DehumidifierControlPanelBean;
import com.mxchipapp.R;
import com.stub.StubApp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PanelHelper.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u000b"}, d2 = {"Lcom/deye/utils/PanelHelper;", "", "()V", "getDrawable", "", "productId", "", "getPanelBean", "Lcom/deye/entity/control_panel/dehumidifier/DehumidifierControlPanelBean;", "context", "Landroid/content/Context;", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class PanelHelper {
    public static final PanelHelper INSTANCE = new PanelHelper();

    private PanelHelper() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x043e  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0456  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0468  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0479  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x04a5  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x04bb  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x04e9  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final DehumidifierControlPanelBean getPanelBean(Context context, String productId) {
        String string2;
        Intrinsics.checkNotNullParameter(context, "context");
        if (productId != null) {
            int iHashCode = productId.hashCode();
            string2 = StubApp.getString2(13648);
            String string22 = StubApp.getString2(13609);
            String string23 = StubApp.getString2(13612);
            String string24 = StubApp.getString2(13621);
            String string25 = StubApp.getString2(13653);
            switch (iHashCode) {
                case -2121259445:
                    if (!productId.equals(StubApp.getString2(13447))) {
                        string2 = "";
                        break;
                    } else {
                        string2 = StubApp.getString2(13611);
                        break;
                    }
                case -2051528042:
                    if (productId.equals(StubApp.getString2(13446))) {
                        string2 = StubApp.getString2(13633);
                        break;
                    }
                    break;
                case -1479425774:
                    if (productId.equals(StubApp.getString2(13444))) {
                        string2 = string23;
                        break;
                    }
                    break;
                case -1376148786:
                    if (productId.equals(StubApp.getString2(13443))) {
                        string2 = StubApp.getString2(13614);
                        break;
                    }
                    break;
                case -1293175428:
                    if (productId.equals(StubApp.getString2(13442))) {
                        string2 = StubApp.getString2(13626);
                        break;
                    }
                    break;
                case -1192116076:
                    if (productId.equals(StubApp.getString2(13441))) {
                        string2 = StubApp.getString2(13624);
                        break;
                    }
                    break;
                case -1182160595:
                    if (productId.equals(StubApp.getString2(13440))) {
                        string2 = string25;
                        break;
                    }
                    break;
                case -1028755433:
                    if (productId.equals(StubApp.getString2(13439))) {
                        string2 = string24;
                        break;
                    }
                    break;
                case -863412840:
                    if (productId.equals(StubApp.getString2(13438))) {
                        string2 = StubApp.getString2(13661);
                        break;
                    }
                    break;
                case -707141345:
                    if (productId.equals(StubApp.getString2(13437))) {
                        string2 = StubApp.getString2(13601);
                        break;
                    }
                    break;
                case -684380250:
                    if (productId.equals(StubApp.getString2(13436))) {
                        string2 = StubApp.getString2(13657);
                        break;
                    }
                    break;
                case -669203127:
                    if (productId.equals(StubApp.getString2(13435))) {
                        string2 = StubApp.getString2(13625);
                        break;
                    }
                    break;
                case -559814530:
                    if (productId.equals(StubApp.getString2(13434))) {
                        string2 = StubApp.getString2(13622);
                        break;
                    }
                    break;
                case -399705960:
                    if (productId.equals(StubApp.getString2(13433))) {
                        string2 = StubApp.getString2(13645);
                        break;
                    }
                    break;
                case -360193437:
                    if (productId.equals(StubApp.getString2(13432))) {
                        string2 = StubApp.getString2(13616);
                        break;
                    }
                    break;
                case -358908287:
                    if (productId.equals(StubApp.getString2(13431))) {
                        string2 = StubApp.getString2(13637);
                        break;
                    }
                    break;
                case -310911671:
                    if (productId.equals(StubApp.getString2(13356))) {
                        string2 = StubApp.getString2(13650);
                        break;
                    }
                    break;
                case -216261114:
                    if (productId.equals(StubApp.getString2(13396))) {
                        string2 = StubApp.getString2(13498);
                        break;
                    }
                    break;
                case -207450233:
                    if (productId.equals(StubApp.getString2(13430))) {
                        string2 = StubApp.getString2(13641);
                        break;
                    }
                    break;
                case -178347431:
                    if (productId.equals(StubApp.getString2(13354))) {
                        string2 = StubApp.getString2(13655);
                        break;
                    }
                    break;
                case -122543548:
                    if (!productId.equals(StubApp.getString2(13429))) {
                    }
                    break;
                case -60953835:
                    if (productId.equals(StubApp.getString2(13428))) {
                        string2 = StubApp.getString2(13618);
                        break;
                    }
                    break;
                case 19922734:
                    if (!productId.equals(StubApp.getString2(13355))) {
                    }
                    break;
                case 41651821:
                    if (productId.equals(StubApp.getString2(13427))) {
                        string2 = StubApp.getString2(13651);
                        break;
                    }
                    break;
                case 89766214:
                    if (productId.equals(StubApp.getString2(13426))) {
                        string2 = StubApp.getString2(13634);
                        break;
                    }
                    break;
                case 169083815:
                    if (productId.equals(StubApp.getString2(13395))) {
                        string2 = StubApp.getString2(13664);
                        break;
                    }
                    break;
                case 328331063:
                    if (!productId.equals(StubApp.getString2(13425))) {
                    }
                    break;
                case 457826758:
                    if (productId.equals(StubApp.getString2(13424))) {
                        string2 = StubApp.getString2(13681);
                        break;
                    }
                    break;
                case 501388422:
                    if (!productId.equals(StubApp.getString2(13423))) {
                    }
                    break;
                case 509564895:
                    if (productId.equals(StubApp.getString2(13422))) {
                        string2 = StubApp.getString2(13623);
                        break;
                    }
                    break;
                case 604171478:
                    if (productId.equals(StubApp.getString2(13421))) {
                        string2 = StubApp.getString2(13620);
                        break;
                    }
                    break;
                case 672956835:
                    if (!productId.equals(StubApp.getString2(13420))) {
                    }
                    break;
                case 685390594:
                    if (productId.equals(StubApp.getString2(13419))) {
                        string2 = StubApp.getString2(13639);
                        break;
                    }
                    break;
                case 692443469:
                    if (productId.equals(StubApp.getString2(13418))) {
                        string2 = StubApp.getString2(13635);
                        break;
                    }
                    break;
                case 836371159:
                    if (!productId.equals(StubApp.getString2(13417))) {
                    }
                    break;
                case 892870982:
                    if (productId.equals(StubApp.getString2(13416))) {
                        string2 = string22;
                        break;
                    }
                    break;
                case 999470824:
                    if (!productId.equals(StubApp.getString2(13349))) {
                    }
                    break;
                case 1021448129:
                    if (productId.equals(StubApp.getString2(13415))) {
                        string2 = StubApp.getString2(13647);
                        break;
                    }
                    break;
                case 1035709668:
                    if (productId.equals(StubApp.getString2(13414))) {
                        string2 = StubApp.getString2(13654);
                        break;
                    }
                    break;
                case 1220291005:
                    if (!productId.equals(StubApp.getString2(13413))) {
                    }
                    break;
                case 1374214106:
                    if (!productId.equals(StubApp.getString2(13412))) {
                    }
                    break;
                case 1400513971:
                    if (!productId.equals(StubApp.getString2(13411))) {
                    }
                    break;
                case 1428687435:
                    if (!productId.equals(StubApp.getString2(13410))) {
                    }
                    break;
                case 1485107781:
                    if (!productId.equals(StubApp.getString2(13409))) {
                    }
                    break;
                case 1516469754:
                    if (!productId.equals(StubApp.getString2(13408))) {
                    }
                    break;
                case 1633379062:
                    if (!productId.equals(StubApp.getString2(13348))) {
                    }
                    break;
                case 1659254645:
                    if (!productId.equals(StubApp.getString2(13407))) {
                    }
                    break;
                case 1669942571:
                    if (productId.equals(StubApp.getString2(13406))) {
                        string2 = StubApp.getString2(13615);
                        break;
                    }
                    break;
                case 1713175096:
                    if (productId.equals(StubApp.getString2(13405))) {
                        string2 = StubApp.getString2(13638);
                        break;
                    }
                    break;
                case 1728471171:
                    if (productId.equals(StubApp.getString2(13404))) {
                        string2 = StubApp.getString2(13644);
                        break;
                    }
                    break;
                case 1747977970:
                    if (!productId.equals(StubApp.getString2(13403))) {
                    }
                    break;
                case 1752070527:
                    if (!productId.equals(StubApp.getString2(13402))) {
                    }
                    break;
                case 1756990026:
                    if (!productId.equals(StubApp.getString2(13401))) {
                    }
                    break;
                case 1778865536:
                    if (!productId.equals(StubApp.getString2(13353))) {
                    }
                    break;
                case 1801910713:
                    if (!productId.equals(StubApp.getString2(13400))) {
                    }
                    break;
                case 1931451897:
                    if (!productId.equals(StubApp.getString2(13347))) {
                    }
                    break;
                case 1973993191:
                    if (productId.equals(StubApp.getString2(13399))) {
                        string2 = StubApp.getString2(13649);
                        break;
                    }
                    break;
                case 1998570969:
                    if (!productId.equals(StubApp.getString2(13947))) {
                    }
                    break;
                case 2021966687:
                    if (productId.equals(StubApp.getString2(13398))) {
                        string2 = StubApp.getString2(13640);
                        break;
                    }
                    break;
                case 2098451586:
                    if (productId.equals(StubApp.getString2(13397))) {
                        string2 = StubApp.getString2(13613);
                        break;
                    }
                    break;
            }
        }
        Object object = JSON.parseObject(AssetsFileRead.getJson(string2, context), DehumidifierControlPanelBean.class);
        Intrinsics.checkNotNullExpressionValue(object, "parseObject(...)");
        return (DehumidifierControlPanelBean) object;
    }

    public final int getDrawable(String productId) {
        String string2;
        Intrinsics.checkNotNullParameter(productId, "productId");
        switch (productId.hashCode()) {
            case -2121259445:
                return !productId.equals(StubApp.getString2(13447)) ? R.drawable.product_v58a3 : R.drawable.product_8158t_890t_8158c;
            case -2051528042:
                return !productId.equals(StubApp.getString2(13446)) ? R.drawable.product_v58a3 : R.drawable.product_n20a3;
            case -2018215641:
                return !productId.equals(StubApp.getString2(13445)) ? R.drawable.product_v58a3 : R.drawable.product_8158t_890t_8158c;
            case -1479425774:
                return !productId.equals(StubApp.getString2(13444)) ? R.drawable.product_v58a3 : R.drawable.product_890c_8138c;
            case -1376148786:
                return !productId.equals(StubApp.getString2(13443)) ? R.drawable.product_v58a3 : R.drawable.product_620s;
            case -1293175428:
                return !productId.equals(StubApp.getString2(13442)) ? R.drawable.product_v58a3 : R.drawable.product_g25a3;
            case -1192116076:
                return !productId.equals(StubApp.getString2(13441)) ? R.drawable.product_v58a3 : R.drawable.product_e12a3;
            case -1182160595:
                return !productId.equals(StubApp.getString2(13440)) ? R.drawable.product_v58a3 : R.drawable.product_y26;
            case -1028755433:
                return !productId.equals(StubApp.getString2(13439)) ? R.drawable.product_v58a3 : R.drawable.product_c25dz;
            case -863412840:
                return !productId.equals(StubApp.getString2(13438)) ? R.drawable.product_v58a3 : R.drawable.product_h7;
            case -707141345:
                return !productId.equals(StubApp.getString2(13437)) ? R.drawable.product_v58a3 : R.drawable.product_rt12;
            case -684380250:
                return !productId.equals(StubApp.getString2(13436)) ? R.drawable.product_v58a3 : R.drawable.product_f15_pro;
            case -669203127:
                return !productId.equals(StubApp.getString2(13435)) ? R.drawable.product_v58a3 : R.drawable.product_es25;
            case -559814530:
                return !productId.equals(StubApp.getString2(13434)) ? R.drawable.product_v58a3 : R.drawable.product_d50a3_d50b3;
            case -399705960:
                return !productId.equals(StubApp.getString2(13433)) ? R.drawable.product_v58a3 : R.drawable.product_u20air;
            case -360193437:
                string2 = StubApp.getString2(13432);
                break;
            case -358908287:
                return !productId.equals(StubApp.getString2(13431)) ? R.drawable.product_v58a3 : R.drawable.product_l48a3;
            case -310911671:
                return !productId.equals(StubApp.getString2(13356)) ? R.drawable.product_v58a3 : R.drawable.product_w20a3;
            case -216261114:
                return !productId.equals(StubApp.getString2(13396)) ? R.drawable.product_v58a3 : R.drawable.product_a12a452;
            case -207450233:
                return !productId.equals(StubApp.getString2(13430)) ? R.drawable.product_v58a3 : R.drawable.product_f20c3_tm208fc_jd201fc;
            case -178347431:
                return !productId.equals(StubApp.getString2(13354)) ? R.drawable.product_v58a3 : R.drawable.product_z12a3_z20b3;
            case -122543548:
                return !productId.equals(StubApp.getString2(13429)) ? R.drawable.product_v58a3 : R.drawable.product_y16;
            case -60953835:
                return !productId.equals(StubApp.getString2(13428)) ? R.drawable.product_v58a3 : R.drawable.product_b12a3;
            case 19922734:
                return !productId.equals(StubApp.getString2(13355)) ? R.drawable.product_v58a3 : R.drawable.product_w20a3;
            case 41651821:
                return !productId.equals(StubApp.getString2(13427)) ? R.drawable.product_v58a3 : R.drawable.product_x20a3;
            case 89766214:
                return !productId.equals(StubApp.getString2(13426)) ? R.drawable.product_v58a3 : R.drawable.product_es25;
            case 169083815:
                return !productId.equals(StubApp.getString2(13395)) ? R.drawable.product_v58a3 : R.drawable.product_f15;
            case 328331063:
                return !productId.equals(StubApp.getString2(13425)) ? R.drawable.product_v58a3 : R.drawable.product_8220c_6138a;
            case 457826758:
                return !productId.equals(StubApp.getString2(13424)) ? R.drawable.product_v58a3 : R.drawable.product_loop_fan;
            case 501388422:
                return !productId.equals(StubApp.getString2(13423)) ? R.drawable.product_v58a3 : R.drawable.product_612s;
            case 509564895:
                return !productId.equals(StubApp.getString2(13422)) ? R.drawable.product_v58a3 : R.drawable.product_d50a3_d50b3;
            case 604171478:
                return !productId.equals(StubApp.getString2(13421)) ? R.drawable.product_v58a3 : R.drawable.product_b13a3;
            case 672956835:
                return !productId.equals(StubApp.getString2(13420)) ? R.drawable.product_v58a3 : R.drawable.product_c65dz;
            case 685390594:
                return !productId.equals(StubApp.getString2(13419)) ? R.drawable.product_v58a3 : R.drawable.product_sc60;
            case 692443469:
                return !productId.equals(StubApp.getString2(13418)) ? R.drawable.product_v58a3 : R.drawable.product_bpa40;
            case 836371159:
                return !productId.equals(StubApp.getString2(13417)) ? R.drawable.product_v58a3 : R.drawable.product_c105dz;
            case 892870982:
                return !productId.equals(StubApp.getString2(13416)) ? R.drawable.product_v58a3 : R.drawable.product_890c_8138c;
            case 999470824:
                return !productId.equals(StubApp.getString2(13349)) ? R.drawable.product_v58a3 : R.drawable.product_f20c3_tm208fc_jd201fc;
            case 1021448129:
                return !productId.equals(StubApp.getString2(13415)) ? R.drawable.product_v58a3 : R.drawable.product_u20pro;
            case 1035709668:
                return !productId.equals(StubApp.getString2(13414)) ? R.drawable.product_v58a3 : R.drawable.product_z12a3_z20b3;
            case 1220291005:
                return !productId.equals(StubApp.getString2(13413)) ? R.drawable.product_v58a3 : R.drawable.product_c45dz;
            case 1374214106:
                return !productId.equals(StubApp.getString2(13412)) ? R.drawable.product_v58a3 : R.drawable.product_y26;
            case 1400513971:
                return !productId.equals(StubApp.getString2(13411)) ? R.drawable.product_v58a3 : R.drawable.product_sc16;
            case 1428687435:
                string2 = StubApp.getString2(13410);
                break;
            case 1485107781:
                return !productId.equals(StubApp.getString2(13409)) ? R.drawable.product_v58a3 : R.drawable.product_f20c3_tm208fc_jd201fc;
            case 1516469754:
                return !productId.equals(StubApp.getString2(13408)) ? R.drawable.product_v58a3 : R.drawable.product_s12a3;
            case 1633379062:
                return !productId.equals(StubApp.getString2(13348)) ? R.drawable.product_v58a3 : R.drawable.product_f20c3_tm208fc_jd201fc;
            case 1659254645:
                return !productId.equals(StubApp.getString2(13407)) ? R.drawable.product_v58a3 : R.drawable.product_y16;
            case 1669942571:
                return !productId.equals(StubApp.getString2(13406)) ? R.drawable.product_v58a3 : R.drawable.product_8220c_6138a;
            case 1713175096:
                return !productId.equals(StubApp.getString2(13405)) ? R.drawable.product_v58a3 : R.drawable.product_rt12;
            case 1728471171:
                return !productId.equals(StubApp.getString2(13404)) ? R.drawable.product_v58a3 : R.drawable.product_u20a3;
            case 1747977970:
                return !productId.equals(StubApp.getString2(13403)) ? R.drawable.product_v58a3 : R.drawable.product_8138d;
            case 1752070527:
                return !productId.equals(StubApp.getString2(13402)) ? R.drawable.product_v58a3 : R.drawable.product_sc26;
            case 1756990026:
                return !productId.equals(StubApp.getString2(13401)) ? R.drawable.product_v58a3 : R.drawable.product_jd121ec;
            case 1778865536:
                return !productId.equals(StubApp.getString2(13353)) ? R.drawable.product_v58a3 : R.drawable.product_z12a3_z20b3;
            case 1801910713:
                return !productId.equals(StubApp.getString2(13400)) ? R.drawable.product_v58a3 : R.drawable.product_h7;
            case 1931451897:
                return !productId.equals(StubApp.getString2(13347)) ? R.drawable.product_v58a3 : R.drawable.product_f20c3_tm208fc_jd201fc;
            case 1973993191:
                return !productId.equals(StubApp.getString2(13399)) ? R.drawable.product_v58a3 : R.drawable.product_v60a3;
            case 2021966687:
                return !productId.equals(StubApp.getString2(13398)) ? R.drawable.product_v58a3 : R.drawable.product_t22a3;
            case 2098451586:
                return !productId.equals(StubApp.getString2(13397)) ? R.drawable.product_v58a3 : R.drawable.product_8158t_890t_8158c;
            default:
                return R.drawable.product_v58a3;
        }
        productId.equals(string2);
        return R.drawable.product_v58a3;
    }
}
