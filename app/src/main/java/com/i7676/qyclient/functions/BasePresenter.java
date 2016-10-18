package com.i7676.qyclient.functions;

import net.grandcentrix.thirtyinch.TiPresenter;
import rx.Subscription;

/**
 * Created by Administrator on 2016/9/19.
 */
public abstract class BasePresenter<V extends BaseView> extends TiPresenter<V> {
    protected void doUnsubscribe(Subscription subscription) {
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
