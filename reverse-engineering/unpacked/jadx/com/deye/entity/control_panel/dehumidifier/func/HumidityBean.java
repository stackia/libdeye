package com.deye.entity.control_panel.dehumidifier.func;

import com.deye.entity.control_panel.base.MXBaseBean;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class HumidityBean extends MXBaseBean {
    public int co;
    int max;
    int min;
    String[] textArray;
    int tickNum;

    public String[] getTextArray() {
        return this.textArray;
    }

    public void setTextArray(String[] strArr) {
        this.textArray = strArr;
    }

    public int getTickNum() {
        return this.tickNum;
    }

    public void setTickNum(int i) {
        this.tickNum = i;
    }

    public int getMin() {
        return this.min;
    }

    public void setMin(int i) {
        this.min = i;
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int i) {
        this.max = i;
    }

    public int getNearByValue(int i) {
        int step = getStep();
        for (int i2 = 1; i2 < this.tickNum; i2++) {
            if (this.min + (step * i2) > i) {
                return i2 - 1;
            }
        }
        return 0;
    }

    public int getPositionByValue(int i) {
        int step = getStep();
        for (int i2 = 0; i2 < this.tickNum; i2++) {
            if (this.min + (step * i2) == i) {
                return i2;
            }
        }
        return 0;
    }

    public int getStep() {
        return (this.max - this.min) / (this.tickNum - 1);
    }
}
