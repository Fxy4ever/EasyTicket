package com.fxy.easyticketlib.stream;

/**
 * create by:Fxymine4ever
 * time: 2019/4/8
 */
public interface Subscriber<T> {
    void onComplete();

    void onSubscribe();

    void onError(Throwable e);

    void onNext(T next);
}
