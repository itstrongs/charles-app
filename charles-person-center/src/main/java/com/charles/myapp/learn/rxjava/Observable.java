package com.charles.myapp.learn.rxjava;

/**
 * Created by itstrongs on 2018/1/9.
 */
public class Observable<T> {

    final OnSubscribe<T> onSubscribe;

    private Observable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    public static <T> Observable<T> create(OnSubscribe<T> onSubscribe) {
        return new Observable<T>(onSubscribe);
    }

    public void subscribe(Subscriber<? super T> subscriber) {
        subscriber.onStart();
        onSubscribe.call(subscriber);
    }

    public interface OnSubscribe<T> {
        void call(Subscriber<? super T> subscriber);
    }

    public interface Transformer<T, R> {
        R call(T from);
    }

    public <R> Observable<R> map(Transformer<? super T, ? extends R> transformer) {
        return create(new MapOnSubscribe<T, R>(this, transformer));
    }

    public class MapOnSubscribe<T, R> implements OnSubscribe<R> {

        final Observable<T> source;
        final Transformer<? super T, ? extends R> transformer;
        public MapOnSubscribe(Observable<T> source, Transformer<? super T, ? extends R> transformer) {
            this.source = source;
            this.transformer = transformer;
        }
        @Override
        public void call(Subscriber<? super R> subscriber) {
            source.subscribe(new MapSubscriber<R, T>(subscriber, transformer));
        }
    }

    public class MapSubscriber<T, R> extends Subscriber<R> {

        final Subscriber<? super T> actual;
        final Transformer<? super R, ? extends T> transformer;

        public MapSubscriber(Subscriber<? super T> actual, Transformer<? super R, ? extends T> transformer) {
            this.actual = actual;
            this.transformer = transformer;
        }

        @Override
        public void onCompleted() {
            actual.onCompleted();
        }

        @Override
        public void onError(Throwable t) {
            actual.onError(t);
        }

        @Override
        public void onNext(R var1) {
            actual.onNext(transformer.call(var1));
        }
    }

    public Observable<T> subscribeOn(final Scheduler scheduler) {
        return Observable.create(new OnSubscribe<T>() {
            @Override
            public void call(final Subscriber<? super T> subscriber) {
                subscriber.onStart();
                // 将事件的生产切换到新的线程。
                scheduler.createWorker().schedule(new Runnable() {
                    @Override
                    public void run() {
                        Observable.this.onSubscribe.call(subscriber);
                    }
                });
            }
        });
    }

    public Observable<T> observeOn(final Scheduler scheduler) {
        return Observable.create(new OnSubscribe<T>() {
            @Override
            public void call(final Subscriber<? super T> subscriber) {
                subscriber.onStart();
                final Scheduler.Worker worker = scheduler.createWorker();
                Observable.this.onSubscribe.call(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onCompleted();
                            }
                        });
                    }
                    @Override
                    public void onError(final Throwable t) {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onError(t);
                            }
                        });
                    }
                    @Override
                    public void onNext(final T var1) {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onNext(var1);
                            }
                        });
                    }
                });
            }
        });
    }
}