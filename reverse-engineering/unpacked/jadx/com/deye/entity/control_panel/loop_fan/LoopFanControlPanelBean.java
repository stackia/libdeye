package com.deye.entity.control_panel.loop_fan;

import com.deye.entity.control_panel.base.MXBaseBean;
import com.deye.entity.control_panel.dehumidifier.func.ModeBean;
import com.deye.entity.control_panel.dehumidifier.func.PowerBean;
import com.deye.entity.control_panel.dehumidifier.func.SpeedBean;
import com.deye.entity.control_panel.loop_fan.func.LampSwitchBean;
import com.stub.StubApp;
import java.util.Objects;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class LoopFanControlPanelBean extends MXBaseBean {
    private String deviceControlStrategy;
    private String deviceModel;
    private boolean hasBacteriolysis;
    private boolean hasCatalystRegeneration;
    private boolean hasDelayer;
    private boolean hasDeviceSuggest;
    private boolean hasHistoryData;
    private boolean hasHumidificationTip;
    private boolean hasScheduler;
    private LampSwitchBean lampSwitch;
    private ModeBean mode;
    private PowerBean power;
    private SpeedBean speed;
    private ModeBean wind_mode;

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

    public boolean isHasHistoryData() {
        return this.hasHistoryData;
    }

    public void setHasHistoryData(boolean z) {
        this.hasHistoryData = z;
    }

    public boolean isHasDeviceSuggest() {
        return this.hasDeviceSuggest;
    }

    public void setHasDeviceSuggest(boolean z) {
        this.hasDeviceSuggest = z;
    }

    public boolean isHasBacteriolysis() {
        return this.hasBacteriolysis;
    }

    public void setHasBacteriolysis(boolean z) {
        this.hasBacteriolysis = z;
    }

    public boolean isHasCatalystRegeneration() {
        return this.hasCatalystRegeneration;
    }

    public void setHasCatalystRegeneration(boolean z) {
        this.hasCatalystRegeneration = z;
    }

    public boolean isHasHumidificationTip() {
        return this.hasHumidificationTip;
    }

    public void setHasHumidificationTip(boolean z) {
        this.hasHumidificationTip = z;
    }

    public PowerBean getPower() {
        return this.power;
    }

    public void setPower(PowerBean powerBean) {
        this.power = powerBean;
    }

    public LampSwitchBean getLampSwitch() {
        return this.lampSwitch;
    }

    public void setLampSwitch(LampSwitchBean lampSwitchBean) {
        this.lampSwitch = lampSwitchBean;
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

    public ModeBean getWind_mode() {
        return this.wind_mode;
    }

    public void setWind_mode(ModeBean modeBean) {
        this.wind_mode = modeBean;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LoopFanControlPanelBean loopFanControlPanelBean = (LoopFanControlPanelBean) obj;
        return this.hasDelayer == loopFanControlPanelBean.hasDelayer && this.hasScheduler == loopFanControlPanelBean.hasScheduler && this.hasHistoryData == loopFanControlPanelBean.hasHistoryData && this.hasBacteriolysis == loopFanControlPanelBean.hasBacteriolysis && this.hasCatalystRegeneration == loopFanControlPanelBean.hasCatalystRegeneration && Objects.equals(this.deviceModel, loopFanControlPanelBean.deviceModel) && Objects.equals(this.deviceControlStrategy, loopFanControlPanelBean.deviceControlStrategy) && Objects.equals(this.power, loopFanControlPanelBean.power) && Objects.equals(this.lampSwitch, loopFanControlPanelBean.lampSwitch) && Objects.equals(this.speed, loopFanControlPanelBean.speed) && Objects.equals(this.mode, loopFanControlPanelBean.mode) && Objects.equals(this.wind_mode, loopFanControlPanelBean.wind_mode);
    }

    public int hashCode() {
        return Objects.hash(this.deviceModel, this.deviceControlStrategy, Boolean.valueOf(this.hasDelayer), Boolean.valueOf(this.hasScheduler), Boolean.valueOf(this.hasHistoryData), Boolean.valueOf(this.hasBacteriolysis), Boolean.valueOf(this.hasCatalystRegeneration), this.power, this.lampSwitch, this.speed, this.mode, this.wind_mode);
    }

    public String toString() {
        return StubApp.getString2(14024) + this.deviceModel + StubApp.getString2(14013) + this.deviceControlStrategy + StubApp.getString2(14014) + this.hasDelayer + StubApp.getString2(14015) + this.hasScheduler + StubApp.getString2(14016) + this.hasHistoryData + StubApp.getString2(14025) + this.hasBacteriolysis + StubApp.getString2(14026) + this.hasCatalystRegeneration + StubApp.getString2(8900) + this.power + StubApp.getString2(14027) + this.lampSwitch + StubApp.getString2(14022) + this.speed + StubApp.getString2(11099) + this.mode + StubApp.getString2(14028) + this.wind_mode + '}';
    }
}
