package io.fogcloud.sdk.fog.api.mqtt.event;

import com.stub.StubApp;
import org.json.JSONObject;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class DeviceStatusEvent extends MqttEvent {
    private final String payload;
    private JSONObject payloadJson;
    private final String topic;

    public DeviceStatusEvent(int i, String str, String str2) {
        super(i);
        this.topic = str;
        this.payload = str2;
    }

    public String getTopic() {
        return this.topic;
    }

    public String getPayload() {
        return this.payload;
    }

    public JSONObject getPayloadJson() {
        if (this.payloadJson == null && this.payload != null) {
            try {
                this.payloadJson = new JSONObject(this.payload);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.payloadJson;
    }

    public static DeviceStatusEvent fromRawMessage(int i, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString(StubApp.getString2("13184"));
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(StubApp.getString2("6619"));
            return new DeviceStatusEvent(i, strOptString, jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.toString() : StubApp.getString2("13461"));
        } catch (Exception e) {
            e.printStackTrace();
            return new DeviceStatusEvent(i, "", str);
        }
    }

    @Override // io.fogcloud.sdk.fog.api.mqtt.event.MqttEvent
    public String toString() {
        StringBuilder sbAppend = new StringBuilder(StubApp.getString2(44988)).append(getPlatformName()).append(StubApp.getString2(44989)).append(this.topic).append(StubApp.getString2(44990));
        String str = this.payload;
        return sbAppend.append(str != null ? str.length() : 0).append('}').toString();
    }
}
