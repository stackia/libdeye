package io.fogcloud.sdk.fog;

import android.content.Context;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import com.stub.StubApp;
import io.fogcloud.sdk.fog.utils.EncryptedSharedPrefsUtil;
import io.fogcloud.sdk.fog.utils.SharedPrefsUtil;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class BaseApp extends MultiDexApplication {
    private static BaseApp instance;
    protected SharedPrefsUtil mSharedPrefsUtil;
    private String mToken = "";

    public void setToken(String str) {
        this.mToken = str;
        this.mSharedPrefsUtil.putValue(StubApp.getString2(13360), StubApp.getString2(13134), str);
    }

    public String getToken() {
        String str = this.mToken;
        if (str == null || "".equals(str)) {
            this.mToken = this.mSharedPrefsUtil.getValue(StubApp.getString2(13360), StubApp.getString2(13134), "");
        }
        return this.mToken;
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onCreate() {
        super.onCreate();
        instance = this;
        EncryptedSharedPrefsUtil.INSTANCE.init(this);
        this.mSharedPrefsUtil = new SharedPrefsUtil(this);
    }

    public static BaseApp getInstance() {
        return instance;
    }
}
