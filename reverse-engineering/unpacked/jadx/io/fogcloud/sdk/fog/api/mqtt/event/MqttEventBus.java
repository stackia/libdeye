package io.fogcloud.sdk.fog.api.mqtt.event;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.Objects;

/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public class MqttEventBus {
    private static volatile MqttEventBus instance;
    private final Subject<Object> bus = PublishSubject.create().toSerialized();

    private MqttEventBus() {
    }

    public static MqttEventBus getInstance() {
        if (instance == null) {
            synchronized (MqttEventBus.class) {
                if (instance == null) {
                    instance = new MqttEventBus();
                }
            }
        }
        return instance;
    }

    public void post(Object obj) {
        this.bus.onNext(obj);
    }

    public <T> Disposable subscribe(final Class<T> cls, Consumer<T> consumer) {
        Observable<Object> observableFilter = this.bus.filter(new Predicate() { // from class: io.fogcloud.sdk.fog.api.mqtt.event.MqttEventBus$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return cls.isInstance(obj);
            }
        });
        Objects.requireNonNull(cls);
        return observableFilter.map(new MqttEventBus$$ExternalSyntheticLambda1(cls)).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer, new MqttEventBus$$ExternalSyntheticLambda2());
    }

    public <T> Disposable subscribeOnIo(final Class<T> cls, Consumer<T> consumer) {
        Observable<Object> observableFilter = this.bus.filter(new Predicate() { // from class: io.fogcloud.sdk.fog.api.mqtt.event.MqttEventBus$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return cls.isInstance(obj);
            }
        });
        Objects.requireNonNull(cls);
        return observableFilter.map(new MqttEventBus$$ExternalSyntheticLambda1(cls)).observeOn(Schedulers.io()).subscribe(consumer, new MqttEventBus$$ExternalSyntheticLambda2());
    }

    public Disposable subscribeAllMqttEvents(Consumer<MqttEvent> consumer) {
        return subscribe(MqttEvent.class, consumer);
    }

    public <T> Observable<T> toObservable(final Class<T> cls) {
        Observable<Object> observableFilter = this.bus.filter(new Predicate() { // from class: io.fogcloud.sdk.fog.api.mqtt.event.MqttEventBus$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return cls.isInstance(obj);
            }
        });
        Objects.requireNonNull(cls);
        return (Observable<T>) observableFilter.map(new MqttEventBus$$ExternalSyntheticLambda1(cls));
    }

    public boolean hasObservers() {
        return this.bus.hasObservers();
    }
}
