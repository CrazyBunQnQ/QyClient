package com.i7676.qyclient.functions.login;

import com.i7676.qyclient.QyClient;
import com.i7676.qyclient.api.YNetApiService;
import com.i7676.qyclient.entity.UserEntity;
import com.i7676.qyclient.exception.ServerException;
import com.i7676.qyclient.functions.OneHasToolbarAtyPresenter;
import com.i7676.qyclient.rx.RxUtil;
import javax.inject.Inject;

/**
 * Created by Administrator on 2016/9/20.
 */

public class LoginAtyPresenter extends OneHasToolbarAtyPresenter<LoginAtyView> {

    @Inject YNetApiService mYNetApiService;

    @Override protected void onWakeUp() {
        super.onWakeUp();
        getView().showSignInFr();
    }

    public void signInUp(String accountInfo, String password) {
        mYNetApiService.login(accountInfo, password)
            .compose(RxUtil.<UserEntity>networkTransform())
            .subscribe(
                // next
                user -> {
                    QyClient.curUser = user;
                },
                // error
                error -> {
                    getView().signUpFailed(((ServerException) error).message);
                },
                // completed
                () -> {
                    getView().storeUser(QyClient.curUser);
                    getView().signUpSuccess();
                });
    }
}
