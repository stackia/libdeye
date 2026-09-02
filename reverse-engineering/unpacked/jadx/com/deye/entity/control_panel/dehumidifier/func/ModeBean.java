package com.deye.entity.control_panel.dehumidifier.func;

import com.deye.entity.control_panel.base.MXBaseBean;
import com.deye.utils.LanUtils;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes3.dex */
public class ModeBean extends MXBaseBean {
    private String[] error;
    private String[] iconError;
    private String[] iconErrorGif;
    private String[] iconNormal;
    private String[] iconSelected;
    private String[] key;
    private String[] name;
    private String[] nameEn;
    private String[] value;

    public String[] getNameEn() {
        return this.nameEn;
    }

    public void setNameEn(String[] strArr) {
        this.nameEn = strArr;
    }

    public String[] getIconNormal() {
        return this.iconNormal;
    }

    public void setIconNormal(String[] strArr) {
        this.iconNormal = strArr;
    }

    public String[] getIconSelected() {
        return this.iconSelected;
    }

    public void setIconSelected(String[] strArr) {
        this.iconSelected = strArr;
    }

    public String[] getIconError() {
        return this.iconError;
    }

    public void setIconError(String[] strArr) {
        this.iconError = strArr;
    }

    public String[] getIconErrorGif() {
        return this.iconErrorGif;
    }

    public void setIconErrorGif(String[] strArr) {
        this.iconErrorGif = strArr;
    }

    public String[] getName() {
        String[] strArr;
        return (!LanUtils.isEnLanguage() || (strArr = this.nameEn) == null || strArr.length <= 0) ? this.name : strArr;
    }

    public void setName(String[] strArr) {
        this.name = strArr;
    }

    public String[] getKey() {
        return this.key;
    }

    public void setKey(String[] strArr) {
        this.key = strArr;
    }

    public String[] getValue() {
        return this.value;
    }

    public void setValue(String[] strArr) {
        this.value = strArr;
    }

    public String[] getError() {
        return this.error;
    }

    public void setError(String[] strArr) {
        this.error = strArr;
    }
}
