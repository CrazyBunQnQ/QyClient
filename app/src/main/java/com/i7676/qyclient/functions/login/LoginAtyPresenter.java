package com.i7676.qyclient.functions.login;

import android.accounts.NetworkErrorException;
import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.entity.ReqResult;
import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.rx.DefaultSubscriber;
import com.orhanobut.logger.Logger;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/20.
 */

public class LoginAtyPresenter extends BasePresenter<LoginAtyView> {

    @Inject YNetApiService mYNetApiService;

    @Override protected void onWakeUp() {
        super.onWakeUp();
        getView().showSignInFr();
    }

    public void signInUp(String accountInfo, String password) {
        mYNetApiService.login(accountInfo, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new DefaultSubscriber<ReqResult<UserEntity>>() {
                @Override public void onCompleted() {
                    getView().storeUser(QyClient.curUser);
                    getView().signUpSuccess();
                }

                @Override public void onError(Throwable e) {
                    if (e instanceof NetworkErrorException) {
                        getView().signUpFailed(e.getMessage());
                    } else {
                        Logger.e(">>> " + e.getMessage());
                        getView().signUpFailed("登录失败,请手动登录或重新注册");
                        getView().signUpFailed("用户名: " + accountInfo + "\n" + "密码: " + password);
                    }
                }

                @Override public void onNext(ReqResult<UserEntity> reqResult) {
                    if (reqResult.getRet() == 0) {
                        QyClient.curUser = reqResult.getData();
                        Logger.i(QyClient.curUser.toString());
                    } else {
                        onError(
                            new NetworkErrorException("Request error[" + reqResult.getRet() + "]"));
                    }
                }
            });
    }
}
