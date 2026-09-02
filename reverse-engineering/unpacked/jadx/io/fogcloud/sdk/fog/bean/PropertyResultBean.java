package io.fogcloud.sdk.fog.bean;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kotlin.Metadata;

/* compiled from: PropertyResultBean.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\bf\u0018\u00002\u00020\u0001:\u0002\u008e\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR \u0010\f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR \u0010\u000f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR \u0010\u0012\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR \u0010\u0015\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR \u0010\u0018\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\"\u0010\u001b\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R \u0010\"\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\bR \u0010%\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0006\"\u0004\b'\u0010\bR$\u0010(\u001a\b\u0018\u00010)R\u00020\u00008\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\"\u0010.\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b/\u0010\u001e\"\u0004\b0\u0010 R \u00101\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0006\"\u0004\b3\u0010\bR \u00104\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0006\"\u0004\b6\u0010\bR \u00107\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u0006\"\u0004\b9\u0010\bR \u0010:\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0006\"\u0004\b<\u0010\bR \u0010=\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u0006\"\u0004\b?\u0010\bR\"\u0010@\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\bA\u0010\u001e\"\u0004\bB\u0010 R \u0010C\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u0006\"\u0004\bE\u0010\bR \u0010F\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u0006\"\u0004\bH\u0010\bR\"\u0010I\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\bJ\u0010\u001e\"\u0004\bK\u0010 R \u0010L\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u0006\"\u0004\bN\u0010\bR \u0010O\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\u0006\"\u0004\bQ\u0010\bR \u0010R\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\u0006\"\u0004\bT\u0010\bR \u0010U\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010\u0006\"\u0004\bW\u0010\bR \u0010X\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010\u0006\"\u0004\bZ\u0010\bR \u0010[\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010\u0006\"\u0004\b]\u0010\bR \u0010^\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010\u0006\"\u0004\b`\u0010\bR \u0010a\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010\u0006\"\u0004\bc\u0010\bR \u0010d\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010\u0006\"\u0004\bf\u0010\bR \u0010g\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010\u0006\"\u0004\bi\u0010\bR \u0010j\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010\u0006\"\u0004\bl\u0010\bR \u0010m\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010\u0006\"\u0004\bo\u0010\bR \u0010p\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010\u0006\"\u0004\br\u0010\bR \u0010s\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u0010\u0006\"\u0004\bu\u0010\bR \u0010v\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bw\u0010\u0006\"\u0004\bx\u0010\bR \u0010y\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bz\u0010\u0006\"\u0004\b{\u0010\bR \u0010|\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b}\u0010\u0006\"\u0004\b~\u0010\bR\"\u0010\u007f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0080\u0001\u0010\u0006\"\u0005\b\u0081\u0001\u0010\bR#\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0083\u0001\u0010\u0006\"\u0005\b\u0084\u0001\u0010\bR%\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0002\u0010!\u001a\u0005\b\u0086\u0001\u0010\u001e\"\u0005\b\u0087\u0001\u0010 R%\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0002\u0010!\u001a\u0005\b\u0089\u0001\u0010\u001e\"\u0005\b\u008a\u0001\u0010 R#\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008c\u0001\u0010\u0006\"\u0005\b\u008d\u0001\u0010\b¨\u0006\u008f\u0001"}, d2 = {"Lio/fogcloud/sdk/fog/bean/PropertyResultBean;", "Ljava/io/Serializable;", "()V", "atmosphereLights", "", "getAtmosphereLights", "()Ljava/lang/String;", "setAtmosphereLights", "(Ljava/lang/String;)V", "compressorStatus", "getCompressorStatus", "setCompressorStatus", "currentAmbientTemperature", "getCurrentAmbientTemperature", "setCurrentAmbientTemperature", "currentCoilTemperature", "getCurrentCoilTemperature", "setCurrentCoilTemperature", "currentEnvironmentalHumidity", "getCurrentEnvironmentalHumidity", "setCurrentEnvironmentalHumidity", "currentExhaustTemperature", "getCurrentExhaustTemperature", "setCurrentExhaustTemperature", "demisting", "getDemisting", "setDemisting", "durationOfOperationOfBactericidalSlowRelease", "", "getDurationOfOperationOfBactericidalSlowRelease", "()Ljava/lang/Integer;", "setDurationOfOperationOfBactericidalSlowRelease", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "environmentalRating", "getEnvironmentalRating", "setEnvironmentalRating", "fan", "getFan", "setFan", "fault", "Lio/fogcloud/sdk/fog/bean/PropertyResultBean$Fault;", "getFault", "()Lio/fogcloud/sdk/fog/bean/PropertyResultBean$Fault;", "setFault", "(Lio/fogcloud/sdk/fog/bean/PropertyResultBean$Fault;)V", "filterRunningTime", "getFilterRunningTime", "setFilterRunningTime", "hkallowstatus", "getHkallowstatus", "setHkallowstatus", "hkmodeoperation", "getHkmodeoperation", "setHkmodeoperation", "keyLock", "getKeyLock", "setKeyLock", "mode", "getMode", "setMode", "negativeIon", "getNegativeIon", "setNegativeIon", "operatingTimeOfCarbonRodFilterElement", "getOperatingTimeOfCarbonRodFilterElement", "setOperatingTimeOfCarbonRodFilterElement", "power", "getPower", "setPower", "promptSound", "getPromptSound", "setPromptSound", "protocolVersion", "getProtocolVersion", "setProtocolVersion", "screendisplay", "getScreendisplay", "setScreendisplay", "setHumidity", "getSetHumidity", "setSetHumidity", "setTemperature", "getSetTemperature", "setSetTemperature", "sleep", "getSleep", "setSleep", "swingingWind", "getSwingingWind", "setSwingingWind", "timedOff", "getTimedOff", "setTimedOff", "timedOn", "getTimedOn", "setTimedOn", "timedShutdownHourSetting", "getTimedShutdownHourSetting", "setTimedShutdownHourSetting", "timedShutdownMinuteSettingTime", "getTimedShutdownMinuteSettingTime", "setTimedShutdownMinuteSettingTime", "timedShutdownTimeRemainingHours", "getTimedShutdownTimeRemainingHours", "setTimedShutdownTimeRemainingHours", "timedShutdownTimeRemainingMinutes", "getTimedShutdownTimeRemainingMinutes", "setTimedShutdownTimeRemainingMinutes", "timedStartupHoursSetTime", "getTimedStartupHoursSetTime", "setTimedStartupHoursSetTime", "timedStartupMinuteSettingTime", "getTimedStartupMinuteSettingTime", "setTimedStartupMinuteSettingTime", "timedStartupTimeRemainingHours", "getTimedStartupTimeRemainingHours", "setTimedStartupTimeRemainingHours", "timedStartupTimeRemainingMinutes", "getTimedStartupTimeRemainingMinutes", "setTimedStartupTimeRemainingMinutes", "uv", "getUv", "setUv", "waterPump", "getWaterPump", "setWaterPump", "waterStatus", "getWaterStatus", "setWaterStatus", "waterTank", "getWaterTank", "setWaterTank", "watertankcapacity", "getWatertankcapacity", "setWatertankcapacity", "wetCurtainOperationDuration", "getWetCurtainOperationDuration", "setWetCurtainOperationDuration", "windSpeed", "getWindSpeed", "setWindSpeed", "Fault", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class PropertyResultBean implements Serializable {

    @SerializedName("AtmosphereLights")
    private String atmosphereLights;

    @SerializedName("CompressorStatus")
    private String compressorStatus;

    @SerializedName("CurrentAmbientTemperature")
    private String currentAmbientTemperature;

    @SerializedName("CurrentCoilTemperature")
    private String currentCoilTemperature;

    @SerializedName("CurrentEnvironmentalHumidity")
    private String currentEnvironmentalHumidity;

    @SerializedName("CurrentExhaustTemperature")
    private String currentExhaustTemperature;

    @SerializedName("Demisting")
    private String demisting;

    @SerializedName("DurationOfOperationOfBactericidalSlowRelease")
    private Integer durationOfOperationOfBactericidalSlowRelease;

    @SerializedName("EnvironmentalRating")
    private String environmentalRating;

    @SerializedName("Fan")
    private String fan;

    @SerializedName("fault")
    private Fault fault;

    @SerializedName("FilterRunningTime")
    private Integer filterRunningTime;

    @SerializedName("Hkallowstatus")
    private String hkallowstatus;

    @SerializedName("Hkmodeoperation")
    private String hkmodeoperation;

    @SerializedName("KeyLock")
    private String keyLock;

    @SerializedName("Mode")
    private String mode;

    @SerializedName("NegativeIon")
    private String negativeIon;

    @SerializedName("OperatingTimeOfCarbonRodFilterElement")
    private Integer operatingTimeOfCarbonRodFilterElement;

    @SerializedName("Power")
    private String power;

    @SerializedName("PromptSound")
    private String promptSound;

    @SerializedName("ProtocolVersion")
    private Integer protocolVersion;

    @SerializedName("Screendisplay")
    private String screendisplay;

    @SerializedName("SetHumidity")
    private String setHumidity;

    @SerializedName("SetTemperature")
    private String setTemperature;

    @SerializedName("Sleep")
    private String sleep;

    @SerializedName("SwingingWind")
    private String swingingWind;

    @SerializedName("TimedOff")
    private String timedOff;

    @SerializedName("TimedOn")
    private String timedOn;

    @SerializedName("TimedShutdownHourSetting")
    private String timedShutdownHourSetting;

    @SerializedName("TimedShutdownMinuteSettingTime")
    private String timedShutdownMinuteSettingTime;

    @SerializedName("TimedShutdownTimeRemainingHours")
    private String timedShutdownTimeRemainingHours;

    @SerializedName("TimedShutdownTimeRemainingMinutes")
    private String timedShutdownTimeRemainingMinutes;

    @SerializedName("TimedStartupHoursSetTime")
    private String timedStartupHoursSetTime;

    @SerializedName("TimedStartupMinuteSettingTime")
    private String timedStartupMinuteSettingTime;

    @SerializedName("TimedStartupTimeRemainingHours")
    private String timedStartupTimeRemainingHours;

    @SerializedName("TimedStartupTimeRemainingMinutes")
    private String timedStartupTimeRemainingMinutes;

    @SerializedName("UV")
    private String uv;

    @SerializedName("WaterPump")
    private String waterPump;

    @SerializedName("Waterstatus")
    private String waterStatus;

    @SerializedName("WaterTank")
    private String waterTank;

    @SerializedName("Watertankcapacity")
    private Integer watertankcapacity;

    @SerializedName("WetCurtainOperationDuration")
    private Integer wetCurtainOperationDuration;

    @SerializedName("WindSpeed")
    private String windSpeed;

    public final String getCompressorStatus() {
        return this.compressorStatus;
    }

    public final void setCompressorStatus(String str) {
        this.compressorStatus = str;
    }

    public final String getCurrentAmbientTemperature() {
        return this.currentAmbientTemperature;
    }

    public final void setCurrentAmbientTemperature(String str) {
        this.currentAmbientTemperature = str;
    }

    public final String getCurrentCoilTemperature() {
        return this.currentCoilTemperature;
    }

    public final void setCurrentCoilTemperature(String str) {
        this.currentCoilTemperature = str;
    }

    public final String getCurrentEnvironmentalHumidity() {
        return this.currentEnvironmentalHumidity;
    }

    public final void setCurrentEnvironmentalHumidity(String str) {
        this.currentEnvironmentalHumidity = str;
    }

    public final String getCurrentExhaustTemperature() {
        return this.currentExhaustTemperature;
    }

    public final void setCurrentExhaustTemperature(String str) {
        this.currentExhaustTemperature = str;
    }

    public final String getDemisting() {
        return this.demisting;
    }

    public final void setDemisting(String str) {
        this.demisting = str;
    }

    public final String getEnvironmentalRating() {
        return this.environmentalRating;
    }

    public final void setEnvironmentalRating(String str) {
        this.environmentalRating = str;
    }

    public final String getFan() {
        return this.fan;
    }

    public final void setFan(String str) {
        this.fan = str;
    }

    public final String getUv() {
        return this.uv;
    }

    public final void setUv(String str) {
        this.uv = str;
    }

    public final String getKeyLock() {
        return this.keyLock;
    }

    public final void setKeyLock(String str) {
        this.keyLock = str;
    }

    public final String getMode() {
        return this.mode;
    }

    public final void setMode(String str) {
        this.mode = str;
    }

    public final String getNegativeIon() {
        return this.negativeIon;
    }

    public final void setNegativeIon(String str) {
        this.negativeIon = str;
    }

    public final String getScreendisplay() {
        return this.screendisplay;
    }

    public final void setScreendisplay(String str) {
        this.screendisplay = str;
    }

    public final String getHkallowstatus() {
        return this.hkallowstatus;
    }

    public final void setHkallowstatus(String str) {
        this.hkallowstatus = str;
    }

    public final String getHkmodeoperation() {
        return this.hkmodeoperation;
    }

    public final void setHkmodeoperation(String str) {
        this.hkmodeoperation = str;
    }

    public final String getPower() {
        return this.power;
    }

    public final void setPower(String str) {
        this.power = str;
    }

    public final Integer getProtocolVersion() {
        return this.protocolVersion;
    }

    public final void setProtocolVersion(Integer num) {
        this.protocolVersion = num;
    }

    public final String getSetHumidity() {
        return this.setHumidity;
    }

    public final void setSetHumidity(String str) {
        this.setHumidity = str;
    }

    public final String getSwingingWind() {
        return this.swingingWind;
    }

    public final void setSwingingWind(String str) {
        this.swingingWind = str;
    }

    public final String getTimedOff() {
        return this.timedOff;
    }

    public final void setTimedOff(String str) {
        this.timedOff = str;
    }

    public final String getTimedOn() {
        return this.timedOn;
    }

    public final void setTimedOn(String str) {
        this.timedOn = str;
    }

    public final String getTimedShutdownHourSetting() {
        return this.timedShutdownHourSetting;
    }

    public final void setTimedShutdownHourSetting(String str) {
        this.timedShutdownHourSetting = str;
    }

    public final String getTimedShutdownMinuteSettingTime() {
        return this.timedShutdownMinuteSettingTime;
    }

    public final void setTimedShutdownMinuteSettingTime(String str) {
        this.timedShutdownMinuteSettingTime = str;
    }

    public final String getTimedShutdownTimeRemainingHours() {
        return this.timedShutdownTimeRemainingHours;
    }

    public final void setTimedShutdownTimeRemainingHours(String str) {
        this.timedShutdownTimeRemainingHours = str;
    }

    public final String getTimedShutdownTimeRemainingMinutes() {
        return this.timedShutdownTimeRemainingMinutes;
    }

    public final void setTimedShutdownTimeRemainingMinutes(String str) {
        this.timedShutdownTimeRemainingMinutes = str;
    }

    public final String getTimedStartupHoursSetTime() {
        return this.timedStartupHoursSetTime;
    }

    public final void setTimedStartupHoursSetTime(String str) {
        this.timedStartupHoursSetTime = str;
    }

    public final String getTimedStartupMinuteSettingTime() {
        return this.timedStartupMinuteSettingTime;
    }

    public final void setTimedStartupMinuteSettingTime(String str) {
        this.timedStartupMinuteSettingTime = str;
    }

    public final String getTimedStartupTimeRemainingHours() {
        return this.timedStartupTimeRemainingHours;
    }

    public final void setTimedStartupTimeRemainingHours(String str) {
        this.timedStartupTimeRemainingHours = str;
    }

    public final String getTimedStartupTimeRemainingMinutes() {
        return this.timedStartupTimeRemainingMinutes;
    }

    public final void setTimedStartupTimeRemainingMinutes(String str) {
        this.timedStartupTimeRemainingMinutes = str;
    }

    public final String getWaterPump() {
        return this.waterPump;
    }

    public final void setWaterPump(String str) {
        this.waterPump = str;
    }

    public final String getWaterTank() {
        return this.waterTank;
    }

    public final void setWaterTank(String str) {
        this.waterTank = str;
    }

    public final String getWindSpeed() {
        return this.windSpeed;
    }

    public final void setWindSpeed(String str) {
        this.windSpeed = str;
    }

    public final String getSleep() {
        return this.sleep;
    }

    public final void setSleep(String str) {
        this.sleep = str;
    }

    public final Fault getFault() {
        return this.fault;
    }

    public final void setFault(Fault fault) {
        this.fault = fault;
    }

    public final String getSetTemperature() {
        return this.setTemperature;
    }

    public final void setSetTemperature(String str) {
        this.setTemperature = str;
    }

    public final String getAtmosphereLights() {
        return this.atmosphereLights;
    }

    public final void setAtmosphereLights(String str) {
        this.atmosphereLights = str;
    }

    public final String getPromptSound() {
        return this.promptSound;
    }

    public final void setPromptSound(String str) {
        this.promptSound = str;
    }

    public final Integer getOperatingTimeOfCarbonRodFilterElement() {
        return this.operatingTimeOfCarbonRodFilterElement;
    }

    public final void setOperatingTimeOfCarbonRodFilterElement(Integer num) {
        this.operatingTimeOfCarbonRodFilterElement = num;
    }

    public final Integer getDurationOfOperationOfBactericidalSlowRelease() {
        return this.durationOfOperationOfBactericidalSlowRelease;
    }

    public final void setDurationOfOperationOfBactericidalSlowRelease(Integer num) {
        this.durationOfOperationOfBactericidalSlowRelease = num;
    }

    public final Integer getWetCurtainOperationDuration() {
        return this.wetCurtainOperationDuration;
    }

    public final void setWetCurtainOperationDuration(Integer num) {
        this.wetCurtainOperationDuration = num;
    }

    public final Integer getFilterRunningTime() {
        return this.filterRunningTime;
    }

    public final void setFilterRunningTime(Integer num) {
        this.filterRunningTime = num;
    }

    public final Integer getWatertankcapacity() {
        return this.watertankcapacity;
    }

    public final void setWatertankcapacity(Integer num) {
        this.watertankcapacity = num;
    }

    public final String getWaterStatus() {
        return this.waterStatus;
    }

    public final void setWaterStatus(String str) {
        this.waterStatus = str;
    }

    /* compiled from: PropertyResultBean.kt */
    @Metadata(d1 = {"\u0000\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0003\b\u0098\u0001\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001c\u0010!\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001c\u0010$\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0006\"\u0004\b&\u0010\bR\u001c\u0010'\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\bR\u001c\u0010*\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0006\"\u0004\b,\u0010\bR\u001c\u0010-\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0006\"\u0004\b/\u0010\bR\u001c\u00100\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0006\"\u0004\b2\u0010\bR\u001c\u00103\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0006\"\u0004\b5\u0010\bR\u001c\u00106\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0006\"\u0004\b8\u0010\bR\u001c\u00109\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0006\"\u0004\b;\u0010\bR\u001c\u0010<\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0006\"\u0004\b>\u0010\bR\u001c\u0010?\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u0006\"\u0004\bA\u0010\bR\u001c\u0010B\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0006\"\u0004\bD\u0010\bR\u001c\u0010E\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0006\"\u0004\bG\u0010\bR\u001c\u0010H\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u0006\"\u0004\bJ\u0010\bR\u001c\u0010K\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u0006\"\u0004\bM\u0010\bR\u001c\u0010N\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010\u0006\"\u0004\bP\u0010\bR\u001c\u0010Q\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\u0006\"\u0004\bS\u0010\bR\u001c\u0010T\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010\u0006\"\u0004\bV\u0010\bR\u001c\u0010W\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010\u0006\"\u0004\bY\u0010\bR\u001c\u0010Z\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\u0006\"\u0004\b\\\u0010\bR\u001c\u0010]\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010\u0006\"\u0004\b_\u0010\bR\u001c\u0010`\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010\u0006\"\u0004\bb\u0010\bR\u001c\u0010c\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010\u0006\"\u0004\be\u0010\bR\u001c\u0010f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010\u0006\"\u0004\bh\u0010\bR\u001c\u0010i\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010\u0006\"\u0004\bk\u0010\bR\u001c\u0010l\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010\u0006\"\u0004\bn\u0010\bR\u001c\u0010o\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010\u0006\"\u0004\bq\u0010\bR\u001c\u0010r\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010\u0006\"\u0004\bt\u0010\bR\u001c\u0010u\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u0010\u0006\"\u0004\bw\u0010\bR\u001c\u0010x\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\by\u0010\u0006\"\u0004\bz\u0010\bR\u001c\u0010{\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b|\u0010\u0006\"\u0004\b}\u0010\bR\u001d\u0010~\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000f\n\u0000\u001a\u0004\b\u007f\u0010\u0006\"\u0005\b\u0080\u0001\u0010\bR\u001f\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010\u0006\"\u0005\b\u0083\u0001\u0010\bR\u001f\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0085\u0001\u0010\u0006\"\u0005\b\u0086\u0001\u0010\bR\u001f\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0088\u0001\u0010\u0006\"\u0005\b\u0089\u0001\u0010\bR\u001f\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008b\u0001\u0010\u0006\"\u0005\b\u008c\u0001\u0010\bR\u001f\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008e\u0001\u0010\u0006\"\u0005\b\u008f\u0001\u0010\bR\u001f\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0091\u0001\u0010\u0006\"\u0005\b\u0092\u0001\u0010\bR\u001f\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0094\u0001\u0010\u0006\"\u0005\b\u0095\u0001\u0010\bR\u001f\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0097\u0001\u0010\u0006\"\u0005\b\u0098\u0001\u0010\bR\u001f\u0010\u0099\u0001\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009a\u0001\u0010\u0006\"\u0005\b\u009b\u0001\u0010\b¨\u0006\u009c\u0001"}, d2 = {"Lio/fogcloud/sdk/fog/bean/PropertyResultBean$Fault;", "", "(Lio/fogcloud/sdk/fog/bean/PropertyResultBean;)V", "A1", "", "getA1", "()Ljava/lang/String;", "setA1", "(Ljava/lang/String;)V", "A2", "getA2", "setA2", "A3", "getA3", "setA3", "A4", "getA4", "setA4", "A5", "getA5", "setA5", "C5", "getC5", "setC5", "E0", "getE0", "setE0", "E1", "getE1", "setE1", "E2", "getE2", "setE2", "E3", "getE3", "setE3", "E4", "getE4", "setE4", "E5", "getE5", "setE5", "E6", "getE6", "setE6", "E7", "getE7", "setE7", "E8", "getE8", "setE8", "E9", "getE9", "setE9", "EA", "getEA", "setEA", "EB", "getEB", "setEB", "EC", "getEC", "setEC", "EE", "getEE", "setEE", "F1", "getF1", "setF1", "F2", "getF2", "setF2", "F3", "getF3", "setF3", "F4", "getF4", "setF4", "F5", "getF5", "setF5", "F6", "getF6", "setF6", "F7", "getF7", "setF7", "F8", "getF8", "setF8", "F9", "getF9", "setF9", "FL", "getFL", "setFL", "HS", "getHS", "setHS", "L1", "getL1", "setL1", "L2", "getL2", "setL2", "L3", "getL3", "setL3", "L4", "getL4", "setL4", "L6", "getL6", "setL6", "P0", "getP0", "setP0", "P1", "getP1", "setP1", "P2", "getP2", "setP2", "P3", "getP3", "setP3", "P4", "getP4", "setP4", "P5", "getP5", "setP5", "P6", "getP6", "setP6", "P7", "getP7", "setP7", "P8", "getP8", "setP8", "PL", "getPL", "setPL", "d1", "getD1", "setD1", "d2", "getD2", "setD2", "d3", "getD3", "setD3", "d4", "getD4", "setD4", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class Fault {
        private String A1;
        private String A2;
        private String A3;
        private String A4;
        private String A5;
        private String C5;
        private String E0;
        private String E1;
        private String E2;
        private String E3;
        private String E4;
        private String E5;
        private String E6;
        private String E7;
        private String E8;
        private String E9;
        private String EA;
        private String EB;
        private String EC;
        private String EE;
        private String F1;
        private String F2;
        private String F3;
        private String F4;
        private String F5;
        private String F6;
        private String F7;
        private String F8;
        private String F9;
        private String FL;
        private String HS;
        private String L1;
        private String L2;
        private String L3;
        private String L4;
        private String L6;
        private String P0;
        private String P1;
        private String P2;
        private String P3;
        private String P4;
        private String P5;
        private String P6;
        private String P7;
        private String P8;
        private String PL;
        private String d1;
        private String d2;
        private String d3;
        private String d4;

        public Fault() {
        }

        public final String getA1() {
            return this.A1;
        }

        public final void setA1(String str) {
            this.A1 = str;
        }

        public final String getA2() {
            return this.A2;
        }

        public final void setA2(String str) {
            this.A2 = str;
        }

        public final String getA3() {
            return this.A3;
        }

        public final void setA3(String str) {
            this.A3 = str;
        }

        public final String getA4() {
            return this.A4;
        }

        public final void setA4(String str) {
            this.A4 = str;
        }

        public final String getA5() {
            return this.A5;
        }

        public final void setA5(String str) {
            this.A5 = str;
        }

        public final String getD1() {
            return this.d1;
        }

        public final void setD1(String str) {
            this.d1 = str;
        }

        public final String getD2() {
            return this.d2;
        }

        public final void setD2(String str) {
            this.d2 = str;
        }

        public final String getPL() {
            return this.PL;
        }

        public final void setPL(String str) {
            this.PL = str;
        }

        public final String getD3() {
            return this.d3;
        }

        public final void setD3(String str) {
            this.d3 = str;
        }

        public final String getD4() {
            return this.d4;
        }

        public final void setD4(String str) {
            this.d4 = str;
        }

        public final String getHS() {
            return this.HS;
        }

        public final void setHS(String str) {
            this.HS = str;
        }

        public final String getFL() {
            return this.FL;
        }

        public final void setFL(String str) {
            this.FL = str;
        }

        public final String getC5() {
            return this.C5;
        }

        public final void setC5(String str) {
            this.C5 = str;
        }

        public final String getE0() {
            return this.E0;
        }

        public final void setE0(String str) {
            this.E0 = str;
        }

        public final String getE1() {
            return this.E1;
        }

        public final void setE1(String str) {
            this.E1 = str;
        }

        public final String getE2() {
            return this.E2;
        }

        public final void setE2(String str) {
            this.E2 = str;
        }

        public final String getE3() {
            return this.E3;
        }

        public final void setE3(String str) {
            this.E3 = str;
        }

        public final String getE4() {
            return this.E4;
        }

        public final void setE4(String str) {
            this.E4 = str;
        }

        public final String getE5() {
            return this.E5;
        }

        public final void setE5(String str) {
            this.E5 = str;
        }

        public final String getE6() {
            return this.E6;
        }

        public final void setE6(String str) {
            this.E6 = str;
        }

        public final String getE7() {
            return this.E7;
        }

        public final void setE7(String str) {
            this.E7 = str;
        }

        public final String getE8() {
            return this.E8;
        }

        public final void setE8(String str) {
            this.E8 = str;
        }

        public final String getE9() {
            return this.E9;
        }

        public final void setE9(String str) {
            this.E9 = str;
        }

        public final String getEA() {
            return this.EA;
        }

        public final void setEA(String str) {
            this.EA = str;
        }

        public final String getEB() {
            return this.EB;
        }

        public final void setEB(String str) {
            this.EB = str;
        }

        public final String getEC() {
            return this.EC;
        }

        public final void setEC(String str) {
            this.EC = str;
        }

        public final String getEE() {
            return this.EE;
        }

        public final void setEE(String str) {
            this.EE = str;
        }

        public final String getF1() {
            return this.F1;
        }

        public final void setF1(String str) {
            this.F1 = str;
        }

        public final String getF2() {
            return this.F2;
        }

        public final void setF2(String str) {
            this.F2 = str;
        }

        public final String getF3() {
            return this.F3;
        }

        public final void setF3(String str) {
            this.F3 = str;
        }

        public final String getF4() {
            return this.F4;
        }

        public final void setF4(String str) {
            this.F4 = str;
        }

        public final String getF5() {
            return this.F5;
        }

        public final void setF5(String str) {
            this.F5 = str;
        }

        public final String getF6() {
            return this.F6;
        }

        public final void setF6(String str) {
            this.F6 = str;
        }

        public final String getF7() {
            return this.F7;
        }

        public final void setF7(String str) {
            this.F7 = str;
        }

        public final String getF8() {
            return this.F8;
        }

        public final void setF8(String str) {
            this.F8 = str;
        }

        public final String getF9() {
            return this.F9;
        }

        public final void setF9(String str) {
            this.F9 = str;
        }

        public final String getL1() {
            return this.L1;
        }

        public final void setL1(String str) {
            this.L1 = str;
        }

        public final String getL2() {
            return this.L2;
        }

        public final void setL2(String str) {
            this.L2 = str;
        }

        public final String getL3() {
            return this.L3;
        }

        public final void setL3(String str) {
            this.L3 = str;
        }

        public final String getL4() {
            return this.L4;
        }

        public final void setL4(String str) {
            this.L4 = str;
        }

        public final String getL6() {
            return this.L6;
        }

        public final void setL6(String str) {
            this.L6 = str;
        }

        public final String getP0() {
            return this.P0;
        }

        public final void setP0(String str) {
            this.P0 = str;
        }

        public final String getP1() {
            return this.P1;
        }

        public final void setP1(String str) {
            this.P1 = str;
        }

        public final String getP2() {
            return this.P2;
        }

        public final void setP2(String str) {
            this.P2 = str;
        }

        public final String getP3() {
            return this.P3;
        }

        public final void setP3(String str) {
            this.P3 = str;
        }

        public final String getP4() {
            return this.P4;
        }

        public final void setP4(String str) {
            this.P4 = str;
        }

        public final String getP5() {
            return this.P5;
        }

        public final void setP5(String str) {
            this.P5 = str;
        }

        public final String getP6() {
            return this.P6;
        }

        public final void setP6(String str) {
            this.P6 = str;
        }

        public final String getP7() {
            return this.P7;
        }

        public final void setP7(String str) {
            this.P7 = str;
        }

        public final String getP8() {
            return this.P8;
        }

        public final void setP8(String str) {
            this.P8 = str;
        }
    }
}
