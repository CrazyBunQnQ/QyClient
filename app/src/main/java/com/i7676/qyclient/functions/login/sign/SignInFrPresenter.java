package com.i7676.qyclient.functions.login.sign;

import android.accounts.NetworkErrorException;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.entity.AccountEntity;
import com.i7676.qyclient.entity.ReqResult;
import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.net.YNetApiService;
import com.orhanobut.logger.Logger;
import javax.inject.Inject;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/20.
 */

public class SignInFrPresenter extends BasePresenter<SignInFrView> {

    @Inject YNetApiService mYNetApiService;

    void doSignIn(AccountEntity accountEntity) {
        mYNetApiService.login(accountEntity.getAccount(), accountEntity.getPassword())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<ReqResult<UserEntity>>() {
                @Override public void onCompleted() {
                }

                @Override public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override public void onNext(ReqResult<UserEntity> reqResult) {
                    if (reqResult.getRet() == 0) {
                        QyClient.curUser = reqResult.getData();
                        Logger.i(QyClient.curUser.toString());
                        getView().loginSuccess();
                        onCompleted();
                    } else {
                        onError(
                            new NetworkErrorException("Request error[" + reqResult.getRet() + "]"));
                    }
                }
            });
    }
}
