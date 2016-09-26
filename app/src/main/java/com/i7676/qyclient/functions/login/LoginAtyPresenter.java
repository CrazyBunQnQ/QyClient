package com.i7676.qyclient.functions.login;

import com.i7676.qyclient.functions.BasePresenter;

/**
 * Created by Administrator on 2016/9/20.
 */

public class LoginAtyPresenter extends BasePresenter<LoginAtyView> {

    @Override protected void onWakeUp() {
        super.onWakeUp();
        getView().showSignInFr();
    }
}
