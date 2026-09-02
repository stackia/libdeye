package com.deye.entity.control_panel.dehumidifier;

import com.deye.entity.control_panel.base.MXBaseBean;
import com.deye.entity.control_panel.dehumidifier.func.AnionBean;
import com.deye.entity.control_panel.dehumidifier.func.ButlerBean;
import com.deye.entity.control_panel.dehumidifier.func.ChildLockBean;
import com.deye.entity.control_panel.dehumidifier.func.DisplayScreenBean;
import com.deye.entity.control_panel.dehumidifier.func.HumidityBean;
import com.deye.entity.control_panel.dehumidifier.func.LightBean;
import com.deye.entity.control_panel.dehumidifier.func.ModeBean;
import com.deye.entity.control_panel.dehumidifier.func.PowerBean;
import com.deye.entity.control_panel.dehumidifier.func.SpeedBean;
import com.deye.entity.control_panel.dehumidifier.func.SwingWindBean;
import com.deye.entity.control_panel.dehumidifier.func.ToneBean;
import com.deye.entity.control_panel.dehumidifier.func.UvLightBean;
import com.deye.entity.control_panel.dehumidifier.func.WaterPumpBean;
import com.stub.StubApp;
import java.util.Objects;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class DehumidifierControlPanelBean extends MXBaseBean {
    private AnionBean anion;
    private ButlerBean butler;
    private ChildLockBean childLock;
    private String deviceControlStrategy;
    private String deviceModel;
    private DisplayScreenBean displayScreen;
    private boolean hasDelayer;
    private boolean hasDeviceSuggest;
    private boolean hasHistoryData;
    private boolean hasHumidityData;
    private boolean hasPartsManager;
    private boolean hasScheduler;
    private HumidityBean humidity;
    private LightBean light;
    private ModeBean mode;
    private PowerBean power;
    private SpeedBean speed;
    private SwingWindBean swingWind;
    private ToneBean tone;
    private UvLightBean uvLight;
    private WaterPumpBean waterPump;

    public ToneBean getTone() {
        return this.tone;
    }

    public void setTone(ToneBean toneBean) {
        this.tone = toneBean;
    }

    public ButlerBean getButler() {
        return this.butler;
    }

    public void setButler(ButlerBean butlerBean) {
        this.butler = butlerBean;
    }

    public DisplayScreenBean getDisplayScreen() {
        return this.displayScreen;
    }

    public void setDisplayScreen(DisplayScreenBean displayScreenBean) {
        this.displayScreen = displayScreenBean;
    }

    public LightBean getLight() {
        return this.light;
    }

    public void setLight(LightBean lightBean) {
        this.light = lightBean;
    }

    public UvLightBean getUvLight() {
        return this.uvLight;
    }

    public void setUvLight(UvLightBean uvLightBean) {
        this.uvLight = uvLightBean;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public String getDeviceControlStrategy() {
        return this.deviceControlStrategy;
    }

    public void setDeviceControlStrategy(String str) {
        this.deviceControlStrategy = str;
    }

    public boolean isHasDelayer() {
        return this.hasDelayer;
    }

    public void setHasDelayer(boolean z) {
        this.hasDelayer = z;
    }

    public boolean isHasScheduler() {
        return this.hasScheduler;
    }

    public void setHasScheduler(boolean z) {
        this.hasScheduler = z;
    }

    public boolean isHasDeviceSuggest() {
        return this.hasDeviceSuggest;
    }

    public void setHasDeviceSuggest(boolean z) {
        this.hasDeviceSuggest = z;
    }

    public boolean isHasHistoryData() {
        return this.hasHistoryData;
    }

    public void setHasHistoryData(boolean z) {
        this.hasHistoryData = z;
    }

    public boolean isHasPartsManager() {
        return this.hasPartsManager;
    }

    public void setHasPartsManager(boolean z) {
        this.hasPartsManager = z;
    }

    public PowerBean getPower() {
        return this.power;
    }

    public void setPower(PowerBean powerBean) {
        this.power = powerBean;
    }

    public SwingWindBean getSwingWind() {
        return this.swingWind;
    }

    public void setSwingWind(SwingWindBean swingWindBean) {
        this.swingWind = swingWindBean;
    }

    public WaterPumpBean getWaterPump() {
        return this.waterPump;
    }

    public void setWaterPump(WaterPumpBean waterPumpBean) {
        this.waterPump = waterPumpBean;
    }

    public ChildLockBean getChildLock() {
        return this.childLock;
    }

    public void setChildLock(ChildLockBean childLockBean) {
        this.childLock = childLockBean;
    }

    public AnionBean getAnion() {
        return this.anion;
    }

    public void setAnion(AnionBean anionBean) {
        this.anion = anionBean;
    }

    public SpeedBean getSpeed() {
        return this.speed;
    }

    public void setSpeed(SpeedBean speedBean) {
        this.speed = speedBean;
    }

    public ModeBean getMode() {
        return this.mode;
    }

    public void setMode(ModeBean modeBean) {
        this.mode = modeBean;
    }

    public HumidityBean getHumidity() {
        return this.humidity;
    }

    public void setHumidity(HumidityBean humidityBean) {
        this.humidity = humidityBean;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DehumidifierControlPanelBean dehumidifierControlPanelBean = (DehumidifierControlPanelBean) obj;
        return this.hasDelayer == dehumidifierControlPanelBean.hasDelayer && this.hasScheduler == dehumidifierControlPanelBean.hasScheduler && this.hasHistoryData == dehumidifierControlPanelBean.hasHistoryData && Objects.equals(this.deviceModel, dehumidifierControlPanelBean.deviceModel) && Objects.equals(this.deviceControlStrategy, dehumidifierControlPanelBean.deviceControlStrategy) && Objects.equals(this.power, dehumidifierControlPanelBean.power) && Objects.equals(this.swingWind, dehumidifierControlPanelBean.swingWind) && Objects.equals(this.waterPump, dehumidifierControlPanelBean.waterPump) && Objects.equals(this.childLock, dehumidifierControlPanelBean.childLock) && Objects.equals(this.uvLight, dehumidifierControlPanelBean.uvLight) && Objects.equals(this.anion, dehumidifierControlPanelBean.anion) && Objects.equals(this.speed, dehumidifierControlPanelBean.speed) && Objects.equals(this.mode, dehumidifierControlPanelBean.mode) && Objects.equals(this.humidity, dehumidifierControlPanelBean.humidity);
    }

    public int hashCode() {
        return Objects.hash(this.deviceModel, this.deviceControlStrategy, Boolean.valueOf(this.hasDelayer), Boolean.valueOf(this.hasScheduler), Boolean.valueOf(this.hasHistoryData), this.power, this.swingWind, this.waterPump, this.childLock, this.anion, this.speed, this.mode, this.humidity);
    }

    public String toString() {
        return StubApp.getString2(14012) + this.deviceModel + StubApp.getString2(14013) + this.deviceControlStrategy + StubApp.getString2(14014) + this.hasDelayer + StubApp.getString2(14015) + this.hasScheduler + StubApp.getString2(14016) + this.hasHistoryData + StubApp.getString2(8900) + this.power + StubApp.getString2(14017) + this.swingWind + StubApp.getString2(14018) + this.waterPump + StubApp.getString2(14019) + this.childLock + StubApp.getString2(14020) + this.uvLight + StubApp.getString2(14021) + this.anion + StubApp.getString2(14022) + this.speed + StubApp.getString2(11099) + this.mode + StubApp.getString2(14023) + this.humidity + '}';
    }

    public Boolean getHasHumidityData() {
        return Boolean.valueOf(this.hasHumidityData);
    }

    public void setHasHumidityData(boolean z) {
        this.hasHumidityData = z;
    }
}
