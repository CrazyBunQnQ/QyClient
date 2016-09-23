package com.i7676.qyclient.rx;

import com.i7676.qyclient.exception.ApiException;
//import com.switfpass.pay.utils.DialogHelper;
import rx.Subscriber;

public class RxSubscriber<T> extends Subscriber<T> {

    @Override public void onStart() {
        super.onStart();
        // TODO where to get this fucking context
        //DialogHelper.showLoading("正在加载数据", context);
    }

    @Override public void onCompleted() {
        //DialogHelper.dismissLoading();
    }

    @Override public void onError(Throwable e) {

    }

    @Override public void onNext(T t) {

    }

    public abstract class ErrorSubscriber<T> extends Subscriber<T> {
        @Override public void onError(Throwable e) {
            if (e instanceof ApiException) {
                onError((ApiException) e);
            } else {
                onError(new ApiException(e, 123));
            }
        }

        /**
         * 错误回调
         */
        protected abstract void onError(ApiException ex);
    }
}