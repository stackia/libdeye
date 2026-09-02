package io.fogcloud.sdk.fog.device_state_refresh_service.imp;

import io.fogcloud.sdk.fog.device_state_refresh_service.IDeviceStateService;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public final class DeviceStateService implements IDeviceStateService {
    private IControlPanelDataUpdate iDataUpdate = null;

    public interface IControlPanelDataUpdate {
        void onControlPanelDataUpdate(String str);
    }

    public void setDataUpdate(IControlPanelDataUpdate iControlPanelDataUpdate) {
        this.iDataUpdate = iControlPanelDataUpdate;
    }

    @Override // io.fogcloud.sdk.fog.device_state_refresh_service.IDeviceStateService
    public void postValue(String str, String... strArr) {
        for (int i = 0; i < strArr.length; i++) {
            IControlPanelDataUpdate iControlPanelDataUpdate = this.iDataUpdate;
            if (iControlPanelDataUpdate != null) {
                iControlPanelDataUpdate.onControlPanelDataUpdate(str);
            }
        }
    }
}
