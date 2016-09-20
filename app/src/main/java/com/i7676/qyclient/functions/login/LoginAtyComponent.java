package com.i7676.qyclient.functions.login;

import android.content.Context;
import com.i7676.qyclient.QyClientComponent;
import com.i7676.qyclient.annotations.PreActivity;
import com.i7676.qyclient.functions.login.navigation.LoginNavigator;
import dagger.Component;

/**
 * Created by Administrator on 2016/9/20.
 */

@PreActivity @Component(modules = LoginAtyModule.class, dependencies = QyClientComponent.class)
/*package*/ interface LoginAtyComponent {

  //void inject(LoginActivity activity);

  void inject(LoginAtyPresenter presenter);

  Context getLoginAtyContext();

  LoginActivity getLoginAtySelf();

  LoginNavigator getLoginNavigator();
}
