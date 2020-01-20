package com.charles.myapp.learn.rxjava;

/**
 * Created by itstrongs on 2018/1/9.
 */
public interface Observer<T> {

    void onCompleted();

    void onError(Throwable t);

    void onNext(T var1);
}
