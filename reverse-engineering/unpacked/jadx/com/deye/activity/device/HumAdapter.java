package com.deye.activity.device;

import com.contrarywind.adapter.WheelAdapter;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class HumAdapter implements WheelAdapter {
    private final int maxValue;
    private final int minValue;
    private final int step;
    private final int total;

    public HumAdapter(int i, int i2, int i3, int i4) {
        this.maxValue = i;
        this.minValue = i2;
        this.step = i3;
        this.total = i4;
    }

    @Override // com.contrarywind.adapter.WheelAdapter
    public Object getItem(int i) {
        return String.valueOf(this.minValue + (i * this.step));
    }

    @Override // com.contrarywind.adapter.WheelAdapter
    public int getItemsCount() {
        return this.total;
    }

    @Override // com.contrarywind.adapter.WheelAdapter
    public int indexOf(Object obj) {
        try {
            return (((Integer) obj).intValue() - this.minValue) / this.step;
        } catch (Exception unused) {
            return -1;
        }
    }
}
