package io.fogcloud.sdk.fog.bean;

import com.stub.StubApp;
import java.util.Objects;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class QuiltDryerBean {
    private String acarusKilling;
    private String anion_fault;
    private String bakingQuilt;
    private String fan_fault;
    private String inlet_temp_fault;
    private String lock_switch;
    private String lock_switch_down = StubApp.getString2(701);
    private String mode;
    private String outlet_temp_fault;
    private String standby_switch;
    private String sterilization_switch;
    private String sterilization_switch_down;
    private String sys_switch;
    private String warm;
    private String warmQuilt;

    public String getLock_switch_down() {
        return this.lock_switch_down;
    }

    public void setLock_switch_down(String str) {
        this.lock_switch_down = str;
    }

    public String getSterilization_switch_down() {
        return this.sterilization_switch_down;
    }

    public void setSterilization_switch_down(String str) {
        this.sterilization_switch_down = str;
    }

    public String getLock_switch() {
        return this.lock_switch;
    }

    public void setLock_switch(String str) {
        this.lock_switch = str;
    }

    public String getStandby_switch() {
        return this.standby_switch;
    }

    public void setStandby_switch(String str) {
        this.standby_switch = str;
    }

    public String getSterilization_switch() {
        return this.sterilization_switch;
    }

    public void setSterilization_switch(String str) {
        this.sterilization_switch = str;
    }

    public String getSys_switch() {
        return this.sys_switch;
    }

    public void setSys_switch(String str) {
        this.sys_switch = str;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String str) {
        this.mode = str;
    }

    public String getWarm() {
        return this.warm;
    }

    public void setWarm(String str) {
        this.warm = str;
    }

    public String getBakingQuilt() {
        return this.bakingQuilt;
    }

    public void setBakingQuilt(String str) {
        this.bakingQuilt = str;
    }

    public String getAcarusKilling() {
        return this.acarusKilling;
    }

    public void setAcarusKilling(String str) {
        this.acarusKilling = str;
    }

    public String getWarmQuilt() {
        return this.warmQuilt;
    }

    public void setWarmQuilt(String str) {
        this.warmQuilt = str;
    }

    public String getAnion_fault() {
        return this.anion_fault;
    }

    public void setAnion_fault(String str) {
        this.anion_fault = str;
    }

    public String getFan_fault() {
        return this.fan_fault;
    }

    public void setFan_fault(String str) {
        this.fan_fault = str;
    }

    public String getOutlet_temp_fault() {
        return this.outlet_temp_fault;
    }

    public void setOutlet_temp_fault(String str) {
        this.outlet_temp_fault = str;
    }

    public String getInlet_temp_fault() {
        return this.inlet_temp_fault;
    }

    public void setInlet_temp_fault(String str) {
        this.inlet_temp_fault = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QuiltDryerBean)) {
            return false;
        }
        QuiltDryerBean quiltDryerBean = (QuiltDryerBean) obj;
        if (Objects.equals(getLock_switch(), quiltDryerBean.getLock_switch()) && Objects.equals(getStandby_switch(), quiltDryerBean.getStandby_switch()) && Objects.equals(getSterilization_switch(), quiltDryerBean.getSterilization_switch()) && Objects.equals(getSys_switch(), quiltDryerBean.getSys_switch()) && Objects.equals(getMode(), quiltDryerBean.getMode()) && Objects.equals(getWarm(), quiltDryerBean.getWarm()) && Objects.equals(getBakingQuilt(), quiltDryerBean.getBakingQuilt()) && Objects.equals(getAcarusKilling(), quiltDryerBean.getAcarusKilling()) && Objects.equals(getWarmQuilt(), quiltDryerBean.getWarmQuilt())) {
            return true;
        }
        return Objects.equals(null, quiltDryerBean.getLock_switch()) && Objects.equals(null, quiltDryerBean.getStandby_switch()) && Objects.equals(null, quiltDryerBean.getSterilization_switch()) && Objects.equals(null, quiltDryerBean.getSys_switch()) && Objects.equals(null, quiltDryerBean.getMode()) && Objects.equals(null, quiltDryerBean.getWarm()) && Objects.equals(null, quiltDryerBean.getBakingQuilt()) && Objects.equals(null, quiltDryerBean.getAcarusKilling()) && Objects.equals(null, quiltDryerBean.getWarmQuilt());
    }

    public String toString() {
        return StubApp.getString2(45024) + this.lock_switch + StubApp.getString2(45025) + this.lock_switch_down + StubApp.getString2(45026) + this.standby_switch + StubApp.getString2(45027) + this.sterilization_switch + StubApp.getString2(45028) + this.sterilization_switch_down + StubApp.getString2(45029) + this.sys_switch + StubApp.getString2(45030) + this.mode + StubApp.getString2(45031) + this.warm + StubApp.getString2(45032) + this.bakingQuilt + StubApp.getString2(45033) + this.acarusKilling + StubApp.getString2(45034) + this.warmQuilt + StubApp.getString2(45035) + this.anion_fault + StubApp.getString2(45036) + this.fan_fault + StubApp.getString2(45037) + this.outlet_temp_fault + StubApp.getString2(45038) + this.inlet_temp_fault + StubApp.getString2(7034);
    }
}
