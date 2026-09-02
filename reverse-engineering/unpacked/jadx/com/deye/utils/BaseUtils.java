package com.deye.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.brentvatne.exoplayer.ReactExoplayerView;
import com.deye.MxchipApplication;
import com.deye.MyActivityManager;
import com.hjq.toast.Toaster;
import com.mxchipapp.R;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.log.LogUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class BaseUtils {
    public static int[] SMALL_IMG = {R.mipmap.clear_day, R.mipmap.clear_night, R.mipmap.partly_cloudy_day, R.mipmap.partly_cloudy_day, R.mipmap.cloudy_day, R.mipmap.rain, R.mipmap.snow, R.mipmap.wind, R.mipmap.fog, R.mipmap.haze, R.mipmap.sleet};
    public static String[] WEATHER_CN = {StubApp.getString2(14278), StubApp.getString2(14279), StubApp.getString2(14280), StubApp.getString2(14281), StubApp.getString2(14282), StubApp.getString2(14283), StubApp.getString2(14284), StubApp.getString2(14285), StubApp.getString2(14286), StubApp.getString2(14287), StubApp.getString2(14288)};
    private static ArrayList<String> hour = new ArrayList<>();
    private static ArrayList<String> min = new ArrayList<>();
    private static ArrayList<String> weather = new ArrayList<>();

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNullString(String str) {
        return str == null || str.length() <= 0;
    }

    public static void sendMessage(Handler handler, int i, String str) {
        Message message = new Message();
        message.what = i;
        message.obj = str;
        handler.sendMessage(message);
    }

    public static void sendMessageExtraArg(Handler handler, int i, String str, int i2) {
        Message message = new Message();
        message.what = i;
        message.obj = str;
        message.arg1 = i2;
        handler.sendMessage(message);
    }

    public static void showShortToast(Context context, CharSequence charSequence) {
        Toaster.show(charSequence);
    }

    public static void showShortToast(int i) {
        Activity currentActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (currentActivity != null) {
            Toaster.show(currentActivity.getResources().getString(i));
        }
    }

    public static void showShortToast(CharSequence charSequence) {
        Toaster.show(charSequence);
    }

    public static void showLongToast(CharSequence charSequence) {
        Toaster.showLong(charSequence);
    }

    public static void showLongToast(Context context, CharSequence charSequence) {
        Toaster.showLong(charSequence);
    }

    public static ArrayList<String> getMin() {
        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                min.add(StubApp.getString2(701) + i);
            } else {
                min.add(i + "");
            }
        }
        return min;
    }

    public static ArrayList<String> getHour() {
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                hour.add(StubApp.getString2(701) + i);
            } else {
                hour.add(i + "");
            }
        }
        return hour;
    }

    public static List<String> getWeekNum(String str) {
        List<String> arrayList = new ArrayList<>();
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isNullString(str)) {
            return arrayList;
        }
        arrayList = Arrays.asList(str.split(StubApp.getString2("450")));
        LogUtil.d(StubApp.getString2(14301) + arrayList);
        return arrayList;
    }

    public static String getWeekString(Context context, String str) {
        String str2;
        if (isNullString(str)) {
            return context.getString(R.string.week_execute_once);
        }
        String[] strArrSplit = str.split(StubApp.getString2(450));
        if (strArrSplit.length == 7) {
            return context.getString(R.string.week_everyday);
        }
        String string2 = StubApp.getString2(701);
        boolean zContains = str.contains(string2);
        String string22 = StubApp.getString2(8092);
        if (zContains && str.contains(string22) && strArrSplit.length == 2) {
            return context.getString(R.string.week_weekend);
        }
        if (!str.contains(string2) && !str.contains(string22) && strArrSplit.length == 5) {
            return context.getString(R.string.week_weekday);
        }
        int length = strArrSplit.length;
        String string23 = StubApp.getString2(626);
        if (length <= 0) {
            str2 = "";
        } else {
            str2 = context.getString(R.string.every) + string23;
        }
        for (String str3 : strArrSplit) {
            str3.hashCode();
            char c = 65535;
            switch (str3.hashCode()) {
                case 48:
                    if (str3.equals(string2)) {
                        c = 0;
                        break;
                    }
                    break;
                case 49:
                    if (str3.equals(StubApp.getString2(2546))) {
                        c = 1;
                        break;
                    }
                    break;
                case 50:
                    if (str3.equals(StubApp.getString2(1764))) {
                        c = 2;
                        break;
                    }
                    break;
                case 51:
                    if (str3.equals(StubApp.getString2(7188))) {
                        c = 3;
                        break;
                    }
                    break;
                case 52:
                    if (str3.equals(StubApp.getString2(7778))) {
                        c = 4;
                        break;
                    }
                    break;
                case 53:
                    if (str3.equals(StubApp.getString2(8095))) {
                        c = 5;
                        break;
                    }
                    break;
                case 54:
                    if (str3.equals(string22)) {
                        c = 6;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    str2 = str2 + context.getString(R.string.week_sunday) + string23;
                    break;
                case 1:
                    str2 = str2 + context.getString(R.string.week_monday) + string23;
                    break;
                case 2:
                    str2 = str2 + context.getString(R.string.week_tuesday) + string23;
                    break;
                case 3:
                    str2 = str2 + context.getString(R.string.week_wednesday) + string23;
                    break;
                case 4:
                    str2 = str2 + context.getString(R.string.week_thursday) + string23;
                    break;
                case 5:
                    str2 = str2 + context.getString(R.string.week_friday) + string23;
                    break;
                case 6:
                    str2 = str2 + context.getString(R.string.week_saturday) + string23;
                    break;
            }
        }
        return str2;
    }

    public static String getTimeString(String str) {
        int iIntValue = Integer.valueOf(str).intValue();
        if (iIntValue < 10) {
            return StubApp.getString2(701) + iIntValue;
        }
        return "" + iIntValue;
    }

    public static ArrayList<String> getWeather() {
        Collections.addAll(weather, StubApp.getString2(14290), StubApp.getString2(14291), StubApp.getString2(14292), StubApp.getString2(14293), StubApp.getString2(14294), StubApp.getString2(14295), StubApp.getString2(14296), StubApp.getString2(14297), StubApp.getString2(14298), StubApp.getString2(14299), StubApp.getString2(14300));
        return weather;
    }

    public static boolean isNetworkConnected(Context context) {
        return (context == null || ((ConnectivityManager) context.getSystemService(StubApp.getString2(790))).getActiveNetworkInfo() == null) ? false : true;
    }

    public static int getCode(String str) {
        return JSON.parseObject(JSON.parseObject(str).getString(StubApp.getString2(13082))).getInteger(StubApp.getString2(109)).intValue();
    }

    public static boolean isWifi5G(Context context) {
        int frequency = ((WifiManager) context.getSystemService(StubApp.getString2(9413))).getConnectionInfo().getFrequency();
        return frequency > 4900 && frequency < 5900;
    }

    public static double roundHalfUp(String str, int i) throws NumberFormatException {
        if (TextUtils.isEmpty(str)) {
            return ReactExoplayerView.DEFAULT_MIN_BUFFER_MEMORY_RESERVE;
        }
        try {
            Float.parseFloat(str);
            return new BigDecimal(str).setScale(i, 4).doubleValue();
        } catch (Exception unused) {
            return ReactExoplayerView.DEFAULT_MIN_BUFFER_MEMORY_RESERVE;
        }
    }

    public static int sp2px(float f) {
        return (int) ((f * MxchipApplication.getInstance().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int dp2px(float f) {
        return (int) ((f * MxchipApplication.getInstance().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static boolean isHttpUrl(String str) {
        boolean zMatches = Pattern.compile(StubApp.getString2(14302)).matcher(str.trim()).matches();
        if (zMatches) {
            return true;
        }
        return zMatches;
    }

    public static String findUrlByStr(String str) {
        Matcher matcher = Pattern.compile(StubApp.getString2(14289)).matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }
}
