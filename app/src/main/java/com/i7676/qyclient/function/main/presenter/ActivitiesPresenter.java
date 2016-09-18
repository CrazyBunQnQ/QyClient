package com.i7676.qyclient.function.main.presenter;

import com.i7676.qyclient.function.main.view.ActivitiesView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/14.
 */
public class ActivitiesPresenter extends BasePresenter<ActivitiesView> {

  @Override protected void onWakeUp() {
    super.onWakeUp();

    Observable.from(new String[] { "1", "2", "3" })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .doOnError(Throwable::printStackTrace)
        .subscribe(getView()::renderHistories);
  }
}
