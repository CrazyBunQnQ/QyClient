package com.i7676.qyclient.rx;

import com.i7676.qyclient.api.ServerConstans;
import com.i7676.qyclient.entity.ReqResult;
import com.i7676.qyclient.exception.ServerException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/13.
 */

public class RxUtil {
    public static <DATA> Observable.Transformer<ReqResult<DATA>, DATA> networkTransform() {
        return reqResultObservable -> {
            return reqResultObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(dataReqResult -> {
                    if (dataReqResult.getRet() == ServerConstans.SUCCESS) {
                        return Observable.just(dataReqResult.getData());
                    } else {
                        return Observable.error(
                            new ServerException(dataReqResult.getRet(), dataReqResult.getMsg()));
                    }
                });
        };
    }
}
