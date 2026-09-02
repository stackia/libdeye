package io.fogcloud.sdk.fog.device_state_refresh_service.data_interceptor;

import java.util.HashMap;
import java.util.Map;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public abstract class IDataInterceptHandler<MetaDataObject, ResultObject> {
    protected Map<String, ResultObject> mInterceptedDataMap = new HashMap();

    public abstract Map<String, ResultObject> getInterceptedData(MetaDataObject metadataobject, ICurrentDeviceStateHandler iCurrentDeviceStateHandler);
}
