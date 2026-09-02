package com.deye.utils;

import com.stub.StubApp;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;

/* compiled from: TemperatureUtil.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u000e\u001a\u00020\u0006J\u0006\u0010\u000f\u001a\u00020\u000bJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006¨\u0006\u0013"}, d2 = {"Lcom/deye/utils/TemperatureUtil;", "", "()V", "celsiusToFahrenheit", "", "celsius", "", "convertTemperature", "fahrenheitToCelsius", "fahrenheit", "formatTemperature", "", "decimalPlaces", "formatTemperatureInt", "getTemperatureUnit", "getTemperatureUnitSymbol", "saveTemperatureUnit", "", "unit", "app_homeRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public final class TemperatureUtil {
    public static final TemperatureUtil INSTANCE = new TemperatureUtil();

    public final double celsiusToFahrenheit(double celsius) {
        return ((celsius * 9.0d) / 5.0d) + 32.0d;
    }

    public final double fahrenheitToCelsius(double fahrenheit) {
        return ((fahrenheit - 32.0d) * 5.0d) / 9.0d;
    }

    private TemperatureUtil() {
    }

    public final int getTemperatureUnit() {
        return MMKVUtils.INSTANCE.getTemperatureUnit();
    }

    public final void saveTemperatureUnit(int unit) {
        MMKVUtils.INSTANCE.setTemperatureUnit(unit);
    }

    public final int celsiusToFahrenheit(int celsius) {
        return MathKt.roundToInt(((celsius * 9.0d) / 5.0d) + 32.0d);
    }

    public final int fahrenheitToCelsius(int fahrenheit) {
        return MathKt.roundToInt(((fahrenheit - 32.0d) * 5.0d) / 9.0d);
    }

    public final double convertTemperature(double celsius) {
        return getTemperatureUnit() == 1 ? celsiusToFahrenheit(celsius) : celsius;
    }

    public final int convertTemperature(int celsius) {
        return getTemperatureUnit() == 1 ? celsiusToFahrenheit(celsius) : celsius;
    }

    public static /* synthetic */ String formatTemperature$default(TemperatureUtil temperatureUtil, double d, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1;
        }
        return temperatureUtil.formatTemperature(d, i);
    }

    public final String formatTemperature(double celsius, int decimalPlaces) {
        String string2;
        double dConvertTemperature = convertTemperature(celsius);
        if (getTemperatureUnit() == 1) {
            string2 = StubApp.getString2(14444);
        } else {
            string2 = StubApp.getString2(13800);
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(StubApp.getString2(10433) + decimalPlaces + StubApp.getString2(14445), Arrays.copyOf(new Object[]{Double.valueOf(dConvertTemperature), string2}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public final String formatTemperatureInt(double celsius) {
        String string2;
        double dConvertTemperature = convertTemperature(celsius);
        if (getTemperatureUnit() == 1) {
            string2 = StubApp.getString2(14444);
        } else {
            string2 = StubApp.getString2(13800);
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(StubApp.getString2(14446), Arrays.copyOf(new Object[]{Integer.valueOf((int) dConvertTemperature), string2}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public final String formatTemperature(int celsius) {
        return String.valueOf(convertTemperature(celsius));
    }

    public final String getTemperatureUnitSymbol() {
        return getTemperatureUnit() == 1 ? StubApp.getString2(14447) : StubApp.getString2(14448);
    }
}
