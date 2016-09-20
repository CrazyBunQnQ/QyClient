package com.i7676.qyclient.functions.login;

import com.i7676.qyclient.functions.BasePresenter;
import com.i7676.qyclient.functions.login.navigation.LoginNavigator;
import javax.inject.Inject;

/**
 * Created by Administrator on 2016/9/20.
 */

/*package*/ class LoginAtyPresenter extends BasePresenter<LoginAtyView> {

  @Inject LoginNavigator navigator;

  @Override protected void onWakeUp() {
    super.onWakeUp();

    navigator.showSignIn();
  }
}
