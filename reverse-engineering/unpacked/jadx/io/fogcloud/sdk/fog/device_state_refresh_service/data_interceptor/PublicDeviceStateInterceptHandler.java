package io.fogcloud.sdk.fog.device_state_refresh_service.data_interceptor;

import com.alibaba.fastjson.JSON;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class PublicDeviceStateInterceptHandler<DeviceStateBean> extends IDataInterceptHandler<String, DeviceStateBean> {
    @Override // io.fogcloud.sdk.fog.device_state_refresh_service.data_interceptor.IDataInterceptHandler
    public Map<String, DeviceStateBean> getInterceptedData(String str, ICurrentDeviceStateHandler iCurrentDeviceStateHandler) {
        Class cls = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        JSON.parseObject(str, cls);
        JSON.parseObject(str, cls);
        return null;
    }
}
