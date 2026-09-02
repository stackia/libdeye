package io.fogcloud.sdk.fog.api.mqtt.event;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class MqttSubscriptionManager {
    private CompositeDisposable disposables = new CompositeDisposable();

    public void add(Disposable disposable) {
        if (this.disposables.isDisposed()) {
            this.disposables = new CompositeDisposable();
        }
        this.disposables.add(disposable);
    }

    public void dispose() {
        this.disposables.dispose();
    }

    public void clear() {
        this.disposables.clear();
    }

    public boolean isDisposed() {
        return this.disposables.isDisposed();
    }

    public int size() {
        return this.disposables.size();
    }
}
