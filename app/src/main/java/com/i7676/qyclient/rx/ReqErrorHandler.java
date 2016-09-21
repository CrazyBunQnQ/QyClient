package com.i7676.qyclient.rx;

import com.i7676.qyclient.exception.ExceptionEngine;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by HCol on 2016/9/20.
 */

public class ReqErrorHandler<T> implements Func1<Throwable, Observable<T>> {
    @Override public Observable<T> call(Throwable throwable) {
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}
