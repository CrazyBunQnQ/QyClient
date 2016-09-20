package com.i7676.qyclient.rx;

import com.i7676.qyclient.entity.ReqResult;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by HCol on 2016/9/20.
 */

public class ServerCallbackHandler<T> implements Func1<ReqResult<T>, Observable<T>> {
  @Override public Observable<T> call(ReqResult<T> tReqResult) {
    if (tReqResult.getRet() != 0) {
      // TODO 向 UI 层发送请求错误代码
      return null;
    }
    return Observable.just(tReqResult.getData());
  }
}
